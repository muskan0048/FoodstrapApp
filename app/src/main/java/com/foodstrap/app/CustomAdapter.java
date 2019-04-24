package com.foodstrap.app;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yy.mobile.rollingtextview.RollingTextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Custom> {

    MenuActivity context;
    int resource;
    int a;
    List<Custom> customList, customList1;


    public CustomAdapter(MenuActivity context, int resource, List<Custom> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        customList = objects;
        customList1 = new ArrayList<>();
        customList1.addAll(customList);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        view = LayoutInflater.from(context).inflate(resource, parent, false);
        Custom custom= customList.get(position);

        custom.getSname();
        custom.getSprice();
        custom.getStype();
        return view;
    }



}


