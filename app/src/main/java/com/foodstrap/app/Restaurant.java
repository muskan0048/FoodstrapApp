package com.foodstrap.app;

public class Restaurant {
    String rid;
    String rname;
    String cuisines;
    String rating;
    String cost2;
    String img;
    String mpack;
    String cooktime;
    String otype;

    public Restaurant(String rid, String rname, String cuisines, String rating, String cost2, String img, String mpack, String cooktime, String otype) {
        this.rid = rid;
        this.rname = rname;
        this.cuisines = cuisines;
        this.rating = rating;
        this.cost2 = cost2;
        this.img = img;
        this.mpack = mpack;
        this.cooktime = cooktime;
        this.otype = otype;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCost2() {
        return cost2;
    }

    public void setCost2(String cost2) {
        this.cost2 = cost2;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMpack() {
        return mpack;
    }

    public void setMpack(String mpack) {
        this.mpack = mpack;
    }

    public String getCooktime() {
        return cooktime;
    }

    public void setCooktime(String cooktime) {
        this.cooktime = cooktime;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }
}
