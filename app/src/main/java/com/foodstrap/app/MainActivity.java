package com.foodstrap.app;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    Double lat, lng;
    List<Restaurant> data;
    ListView lv;
    List<String> your_array_list;
    RecyclerView recyclerView;
    RestaurantRecycler mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.ab);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.print("Hi I am created");
        your_array_list = new ArrayList<String>();
        databaseReference = FirebaseDatabase.getInstance().getReference("restaurants");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                data= new ArrayList<>();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    System.out.println("Key is: "+postSnapshot.getKey()+"Menu is"+ postSnapshot.child("menu").getValue()
                            +"Address Lat: "+ postSnapshot.child("address").child("lat").getValue()+"Address Long: "+ postSnapshot.child("address").child("long").getValue());
                    String rid=postSnapshot.getKey();
                    String rname= postSnapshot.child("dname").getValue().toString();
                    String cuisines;
                    if(postSnapshot.child("cuisines").child("cuisines").getValue()=="" || postSnapshot.child("cuisines").child("cuisines").getValue()==null){
                        cuisines="food";
                    }
                    else {
                        cuisines = postSnapshot.child("cuisines").child("cuisines").getValue().toString();
                    }
                    String rating="";
                    String cost2=postSnapshot.child("cost2").getValue().toString();
                 String img="";
                    String mpack=postSnapshot.child("mpackc").getValue().toString();
                    String cooktime=postSnapshot.child("cooktime").getValue().toString();
                    String mtype=postSnapshot.child("mtype").getValue().toString();
                    Restaurant restaurant= new Restaurant(rid, rname, cuisines, rating, cost2, img, mpack, cooktime, mtype);
                    System.out.print("addedd");
                    data.add(restaurant);
                    if(postSnapshot.child("address").child("lat").getValue().toString()!=null || postSnapshot.child("address").child("lat").getValue().toString()!="" ) {
                        lat = Double.parseDouble(postSnapshot.child("address").child("lat").getValue().toString());
                        lng = Double.parseDouble(postSnapshot.child("address").child("long").getValue().toString());
                        float[] distance = new float[2];
                        Location.distanceBetween(lat, lng,
                                30.684330, 76.860470, distance);
                        if (distance[0] / 1000 > 2.5) {
                      //      Toast.makeText(getBaseContext(), "Outside", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getBaseContext(), "Inside", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                mAdapter = new RestaurantRecycler(MainActivity.this, data);
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
