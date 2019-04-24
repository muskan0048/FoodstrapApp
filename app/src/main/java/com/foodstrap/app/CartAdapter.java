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

public class CartAdapter extends ArrayAdapter<Cart> {

    public ContentResolver resolver;
    ShowActivity context;
    int resource;
    int a;
    List<String> keys;
    List<Cart> cartList1, cartList2;
    TextView bdel, badd;
    DatabaseReference databaseReference;

    public CartAdapter(ShowActivity context, int resource, List<Cart> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        cartList1 = objects;
        cartList2 = new ArrayList<>();
        cartList2.addAll(cartList1);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        view = LayoutInflater.from(context).inflate(resource, parent, false);
        final RollingTextView qty = (RollingTextView) view.findViewById(R.id.qt);
        keys= new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("cart").child("9478018890");

        TextView txtName = (TextView) view.findViewById(R.id.tvnamef);
        final TextView txtEmail = (TextView) view.findViewById(R.id.tvpricef);
        bdel = (TextView) view.findViewById(R.id.button2);
        badd = (TextView) view.findViewById(R.id.button5);
        final Cart cart = cartList1.get(position);
        txtName.setText(cart.getCname());
        txtEmail.setText("" + cart.getCtotal());
        qty.setText(""+cart.getCqty());
        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /* databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            System.out.println( "Hello"+postSnapshot.getKey());
                        }}
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("Error"+databaseError.getMessage());
                    }
                });*/
                a = Integer.parseInt(qty.getText().toString().trim());
                ++a;
                Toast.makeText(context, ""+cart.getCid(),Toast.LENGTH_LONG ).show();
                resolver = getContext().getContentResolver();
                ContentValues values = new ContentValues();
                values.put(Util.COL_fNAME, cart.getCname());
                values.put(Util.COL_Price, cart.getCprice());
                values.put(Util.COL_quantity, a);
                values.put(Util.COL_FID, cart.getCid());
                Double pr = Double.parseDouble(cart.getCprice().toString().trim());
                final Double r = pr * a;
                values.put(Util.COL_TOTAL, r);
                Cart cart1= new Cart(cart.getCid(), cart.getIid(), cart.getCname(), cart.getCprice(),a,r);
                databaseReference.child(cart.getCid()).setValue(cart1);

                qty.setText("" + a);
                    txtEmail.setText("" + r);
                    context.updateUser();
            }
        });
        final View finalView = view;
        final View finalView1 = view;
        bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = Integer.parseInt(qty.getText().toString().trim());
                --a;
                Toast.makeText(context, ""+cart.getCid(),Toast.LENGTH_LONG ).show();
                resolver = getContext().getContentResolver();
                ContentValues values = new ContentValues();
                values.put(Util.COL_fNAME, cart.getCname());
                values.put(Util.COL_Price, cart.getCprice());
                values.put(Util.COL_quantity, a);
                values.put(Util.COL_FID, cart.getCid());
                Double pr = Double.parseDouble(cart.getCprice().toString().trim());
                final Double r = pr * a;
                values.put(Util.COL_TOTAL, r);
                Cart cart1= new Cart(cart.getCid(), cart.getIid(), cart.getCname(), cart.getCprice(),a,r);
                databaseReference.child(cart.getCid()).setValue(cart1);

                qty.setText("" + a);
                txtEmail.setText("" + r);
                context.updateUser();
                    if (a == 0) {
                       // finalView1.setVisibility(View.GONE);
                        //cartList1.remove(cart);
                      //  cartList1.remove(position);
                     //   CartAdapter.super.remove(cart);
                        databaseReference.child(cart.getCid()).removeValue();
                        context.updateUser();

                    }
            }
        });

        return view;
    }

    public void remove(int position) {
        notifyDataSetChanged();
    }


}


