package dw.gameshop.service;

import dw.gameshop.dto.PurchaseDTO;
import dw.gameshop.dto.UserDTO;
import dw.gameshop.exception.PermissionDeniedException;
import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.repository.PurchaseRepository;
import dw.gameshop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
                    userRepository.findById(purchaseDTO.getUser().getUserName())
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
    public List<PurchaseDTO> getPurchaseListByUserName(String userName, HttpServletRequest request) {
        User currentUser = userService.getCurrentUser(request);
        if (!currentUser.getAuthority().getAuthorityName().equals("ADMIN")) {
            throw new PermissionDeniedException("권한이 없습니다.");
        }
        User user = userRepository.findById(userName)
                .orElseThrow(()->new ResourceNotFoundException("해당 유저가 없습니다. ID : " + userName));
        return purchaseRepository.findByUser(user).stream()
                .map(Purchase::toDto).toList();
    }

    // [일반 권한] 현재 세션 유저의 구매내역 조회
    public List<PurchaseDTO> getPurchaseListByCurrentUser(HttpServletRequest request) {
        User currentUser = userService.getCurrentUser(request);
        return purchaseRepository.findByUser(currentUser).stream()
                .map(Purchase::toDto).toList();
    }
}







