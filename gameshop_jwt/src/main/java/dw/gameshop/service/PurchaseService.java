package dw.gameshop.service;

import dw.gameshop.dto.PurchaseDTO;
import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.exception.UnauthorizedUserException;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.repository.PurchaseRepository;
import dw.gameshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    public Purchase savePurchase(Purchase purchase) {
        purchase.setPurchaseTime(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }

    public List<PurchaseDTO> savePurchaseList(List<PurchaseDTO> purchaseList) {
        return purchaseList.stream().map(purchaseDTO -> {
            Purchase purchase = new Purchase(
                    0,
                    purchaseDTO.getGame(),
                    userRepository.findById(purchaseDTO.getUser().getUsername())
                            .orElseThrow(()->new ResourceNotFoundException("No User")),
                    LocalDateTime.now()
            );
            return purchaseRepository.save(purchase);
        }).map(Purchase::toDto).toList();
    }

    public List<PurchaseDTO> getAllPurchases() {
        return purchaseRepository.findAll().stream().map(Purchase::toDto).toList();
    }

    // [관리자 권한] 유저별 구매내역 조회 - 관리자 권한이 있어야 확인 가능
    public List<PurchaseDTO> getPurchaseListByUserName(String userName) {
        // 유저이름으로 유저객체 찾기
        Optional<User> userOptional = userRepository.findById(userName);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("해당 유저 이름이 없습니다. : " + userName);
        }
        return purchaseRepository.findByUser(userOptional.get()).stream()
                .map(PurchaseDTO::toPurchaseDto)
                .collect(Collectors.toList());
    }

    // [일반 권한] 현재 세션 유저의 구매내역 조회
    public List<PurchaseDTO> getPurchaseListByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedUserException("User is not authenticated");
        }
        String userId = authentication.getName();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("해당 유저가 없습니다. ID : " + userId);
        }
        return purchaseRepository.findByUser(userOptional.get()).stream()
                .map(PurchaseDTO::toPurchaseDto)
                .collect(Collectors.toList());
    }
}







