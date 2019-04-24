package com.foodstrap.app;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    String rid;
    DatabaseReference databaseReference;
    List<Menu> data;
    RecyclerView recyclerView;
    MenuRecycler mAdapter;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent rcv= getIntent();
        rid= rcv.getStringExtra("rid");
        Toast.makeText(getApplicationContext(), rid, Toast.LENGTH_SHORT).show();
        init();
    }

    public void showCart(final String gst, final int items, final Double total, final String name){

    }

    void init(){
        recyclerView=(RecyclerView) findViewById(R.id.ab);
        button=(Button) findViewById(R.id.abcn);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.print("Hi I am created");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MenuActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("restaurants").child(rid).child("menu");
        databaseReference.orderByChild("category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                data= new ArrayList<>();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    System.out.print("Key is"+postSnapshot.getKey()+"name is"+postSnapshot.child("name").getValue());
                    String iid=postSnapshot.getKey();
                    String iname=postSnapshot.child("name").getValue().toString();
                    String iprice=postSnapshot.child("price").getValue().toString();
                    String icat=postSnapshot.child("category").getValue().toString();
                    String isubcat=postSnapshot.child("subcategory").getValue().toString();
                    String open=postSnapshot.child("open").getValue().toString();
                    String open2=postSnapshot.child("open2").getValue().toString();
                    String close=postSnapshot.child("close").getValue().toString();
                    String close2=postSnapshot.child("close2").getValue().toString();
                    String itype=postSnapshot.child("type").getValue().toString();
                    String iotype=postSnapshot.child("ordertype").getValue().toString();
                    String idesc=postSnapshot.child("description").getValue().toString();
                    String idis=postSnapshot.child("discountable").getValue().toString();
                    String igst=postSnapshot.child("gstyn").getValue().toString();
                    String istatus;

                    if(postSnapshot.child("status").getValue()=="" || postSnapshot.child("status").getValue()==null){
                        istatus="yes";
                    }
                    else {
                        istatus = "no";

                    }
                    String icustom;
                    if(postSnapshot.child("customisable").getValue()=="" || postSnapshot.child("customisable").getValue()==null){
                        icustom="no";
                    }
                    else {
                        icustom = postSnapshot.child("customisable").getValue().toString();

                    }
                    String ilogo="";
                    Menu menu= new Menu(iid, iname, iprice, icat, isubcat, open, open2, close, close2, itype, iotype, idesc, idis, igst, icustom, ilogo, istatus);
                    System.out.print("addedd");
                    data.add(menu);
                }
                mAdapter = new MenuRecycler(MenuActivity.this,rid, data);
                System.out.print("bbb");
                recyclerView.setAdapter(mAdapter);
                System.out.print("aaa");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error"+databaseError.getMessage());
            }
        });

    }
}
