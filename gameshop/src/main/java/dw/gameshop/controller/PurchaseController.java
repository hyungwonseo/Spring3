package dw.gameshop.controller;

import dw.gameshop.dto.PurchaseDTO;
import dw.gameshop.model.Purchase;
import dw.gameshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/products/purchase")
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(
                purchaseService.savePurchase(purchase),
                HttpStatus.CREATED);
    }

    @PostMapping("/products/purchaselist")
    public ResponseEntity<List<PurchaseDTO>> savePurchaseList(@RequestBody List<Purchase> purchaseList) {
        return new ResponseEntity<>(
                purchaseService.savePurchaseList(purchaseList),
                HttpStatus.CREATED);
    }

    @GetMapping("/products/purchase")
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases() {
        return new ResponseEntity<>(
                purchaseService.getAllPurchases(),
                HttpStatus.OK);
    }

    @GetMapping("/products/purchase/id/{userId}")
    public ResponseEntity<List<PurchaseDTO>> getPurchaseListByUser(@PathVariable String userId) {
        return new ResponseEntity<>(
                purchaseService.getPurchaseListByUser(userId),
                HttpStatus.OK);
    }

    @GetMapping("/products/purchase/name/{userName}")
    public ResponseEntity<List<PurchaseDTO>> getPurchaseListByUserName(
            @PathVariable String userName) {
        return new ResponseEntity<>(
                purchaseService.getPurchaseListByUserName(userName),
                HttpStatus.OK);
    }

    @GetMapping("/products/purchase/current")
    public ResponseEntity<List<PurchaseDTO>> getPurchaseListByCurrentUser() {
        return new ResponseEntity<>(
                purchaseService.getPurchaseListByCurrentUser(),
                HttpStatus.OK);
    }
}









