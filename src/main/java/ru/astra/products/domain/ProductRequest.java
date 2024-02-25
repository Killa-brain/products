package ru.astra.products.domain;

import java.math.BigDecimal;

public class ProductRequest {
    private Long userId;
    private Integer accountNumber;
    private BigDecimal balance;
    private String productType;

    public ProductRequest() {
    }

    public ProductRequest(Long userId, Integer accountNumber, BigDecimal balance, String productType) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.productType = productType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
