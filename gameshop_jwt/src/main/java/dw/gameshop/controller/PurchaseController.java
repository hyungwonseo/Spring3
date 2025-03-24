package dw.gameshop.controller;

import dw.gameshop.dto.PurchaseDTO;
import dw.gameshop.model.Purchase;
import dw.gameshop.service.PurchaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/save")
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(
                purchaseService.savePurchase(purchase),
                HttpStatus.CREATED);
    }

    @PostMapping("/save/list")
    public ResponseEntity<List<PurchaseDTO>> savePurchaseList(@RequestBody List<PurchaseDTO> purchaseList) {
        return new ResponseEntity<>(
                purchaseService.savePurchaseList(purchaseList),
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases() {
        return new ResponseEntity<>(
                purchaseService.getAllPurchases(),
                HttpStatus.OK);
    }

    // 유저별 구매내역 조회. 관리자 권한이 있어야 조회 가능
    @GetMapping("/user/{userName}")
    public ResponseEntity<List<PurchaseDTO>> getPurchaseListByUserName(@PathVariable String userName,
                                                                       HttpServletRequest request) {
        return new ResponseEntity<>(
                purchaseService.getPurchaseListByUserName(userName, request),
                HttpStatus.OK);
    }

    // 현재 로그인한 유저의 구매정보 조회
    @GetMapping("/current-user")
    public ResponseEntity<List<PurchaseDTO>> getPurchaseListByCurrentUser(HttpServletRequest request) {
        return new ResponseEntity<>(
                purchaseService.getPurchaseListByCurrentUser(request),
                HttpStatus.OK);
    }
}









