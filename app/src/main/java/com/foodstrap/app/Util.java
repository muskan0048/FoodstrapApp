package com.foodstrap.app;

import android.net.Uri;

public class Util {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "foodstrap.db";

    public static final String TABLE_NAME = "Cart";
    public static final String COL_FID = "food_id";
    public static final String COL_fNAME = "fNAME";
    public static final String COL_Price = "Price";
    public static final String COL_quantity = "quantity";
    public static final String COL_TOTAL = "Total";



    public static final String CREATE_TAB_QUERY = "create table Cart(" +
            "food_id text," +
            "fNAME text," +
            "Price text," +
            "quantity text," +
            "Total text" +
            ")";

    public static final Uri USER_URI = Uri.parse("content://com.foodstrap.app.cp/"+TABLE_NAME);

    public static final String KEY_USER = "keyUser";


}
