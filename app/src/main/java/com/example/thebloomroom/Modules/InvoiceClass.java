package com.example.thebloomroom.Modules;

public class InvoiceClass {

    private String invoiceId;
    private String productId;
    private int qty;
    private int total;

    public InvoiceClass() {}

    //Constructors
    public InvoiceClass(String invoiceId, String productId, int qty, int total) {
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.qty = qty;
        this.total = total;
    }

    //Getters and Setters

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
