package ru.astra.products.domain;

import java.math.BigDecimal;

public class ProductResponse {
    private Integer accountNumber;
    private BigDecimal balance;
    private ProductType productType;

    public ProductResponse() {
    }

    public ProductResponse(Integer accountNumber, BigDecimal balance, ProductType productType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.productType = productType;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
