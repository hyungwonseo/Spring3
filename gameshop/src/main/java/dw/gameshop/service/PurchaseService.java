package dw.gameshop.service;

import dw.gameshop.dto.PurchaseDTO;
import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.repository.PurchaseRepository;
import dw.gameshop.repository.UserRepository;
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

    public Purchase savePurchase(Purchase purchase) {
        //구매확정 바로 직전, 현재시간을 저장함
        purchase.setPurchaseTime(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }

    public List<PurchaseDTO> savePurchaseList(List<Purchase> purchaseList) {
        List<Purchase> savedPurchaseList = purchaseList.stream()
                .map((purchase)->{
                    //구매확정 바로 직전, 현재시간을 저장함
                    purchase.setPurchaseTime(LocalDateTime.now());
                    return purchaseRepository.save(purchase);
                })
                .toList();
        return savedPurchaseList.stream().map(PurchaseDTO::toPurchaseDto)
                .collect(Collectors.toList());
    }

    public List<PurchaseDTO> getAllPurchases() {
        return purchaseRepository.findAll().stream().map(PurchaseDTO::toPurchaseDto)
                .collect(Collectors.toList());
    }

    public List<PurchaseDTO> getPurchaseListByUser(String userId) {
        // 유저아이디로 유저객체 찾기
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("해당 유저가 없습니다. ID : " + userId);
        }
        return purchaseRepository.findByUser(userOptional.get()).stream()
                .map(PurchaseDTO::toPurchaseDto)
                .collect(Collectors.toList());
    }

    //유저 이름으로 구매한 게임 찾기
    public List<PurchaseDTO> getPurchaseListByUserName(String userName) {
        // 유저이름으로 유저객체 찾기
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("해당 유저 이름이 없습니다. : " + userName);
        }
        return purchaseRepository.findByUser(userOptional.get()).stream()
                .map(PurchaseDTO::toPurchaseDto)
                .collect(Collectors.toList());
    }

    // 현재 세션 유저 이름으로 구매한 게임 찾기
    public List<PurchaseDTO> getPurchaseListByCurrentUser() {
        return null;
    }
}







