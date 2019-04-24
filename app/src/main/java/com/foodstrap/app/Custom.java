package com.foodstrap.app;

public class Custom {

    String sid;
    String stitle;
    String sname;
    String sprice;
    String stype;
    String smax;
    String smin;
    String sreq;

    public Custom(String sid, String stitle, String sname, String sprice, String stype, String smax, String smin, String sreq) {
        this.sid = sid;
        this.stitle = stitle;
        this.sname = sname;
        this.sprice = sprice;
        this.stype = stype;
        this.smax = smax;
        this.smin = smin;
        this.sreq = sreq;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSprice() {
        return sprice;
    }

    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getSmax() {
        return smax;
    }

    public void setSmax(String smax) {
        this.smax = smax;
    }

    public String getSmin() {
        return smin;
    }

    public void setSmin(String smin) {
        this.smin = smin;
    }

    public String getSreq() {
        return sreq;
    }

    public void setSreq(String sreq) {
        this.sreq = sreq;
    }
}
