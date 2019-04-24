package com.foodstrap.app;

public class Cart {
    String cid;
    String iid;
    String cname;
    String cprice;
    int cqty;
    Double ctotal;

    public Cart(String cid, String iid, String cname, String cprice, int cqty, Double ctotal) {
        this.cid = cid;
        this.iid = iid;
        this.cname = cname;
        this.cprice = cprice;
        this.cqty = cqty;
        this.ctotal = ctotal;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCprice() {
        return cprice;
    }

    public void setCprice(String cprice) {
        this.cprice = cprice;
    }

    public int getCqty() {
        return cqty;
    }

    public void setCqty(int cqty) {
        this.cqty = cqty;
    }

    public Double getCtotal() {
        return ctotal;
    }

    public void setCtotal(Double ctotal) {
        this.ctotal = ctotal;
    }
}
