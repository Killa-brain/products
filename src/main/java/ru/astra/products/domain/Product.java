package ru.astra.products.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Long id;
    private Long userId;
    private Integer accountNumber;
    private BigDecimal balance;
    private ProductType productType;

    public Product() {
    }

    public Product(Long id, Long userId, Integer accountNumber, BigDecimal balance, ProductType productType) {
        this.id = id;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.productType = productType;
    }

    public Product(Long userId, Integer accountNumber, BigDecimal balance, ProductType productType) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(accountNumber, product.accountNumber) && Objects.equals(balance, product.balance) && productType == product.productType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, productType);
    }

    @Override
    public String toString() {
        return "Product{" +
                ", userId=" + userId +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", productType=" + productType +
                '}';
    }
}
