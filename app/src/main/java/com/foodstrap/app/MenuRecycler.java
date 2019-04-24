package com.foodstrap.app;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ms.square.android.expandabletextview.ExpandableTextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MenuRecycler extends RecyclerView.Adapter<MenuRecycler.ProductViewHolder1> {

    ContentValues values;
    ContentResolver resolver;
    private MenuActivity mCtx;
    private List<Menu> menuList;
    int i=0;
    int q=0;
    int quan;
    String resid;
    List<String> s11, s22, p11, p22, v11, v22, selitem;
    Double pr=0.0;
    CustomAdapter customAdapter;
    List<Custom> data1, data2;
    DatabaseReference databaseReference, databaseReference1;
    RadioGroup.LayoutParams rprms;
    LinearLayout.LayoutParams rprm;

    public MenuRecycler(MenuActivity mCtx, String resid, List<Menu> menuList) {
        this.mCtx = mCtx;
        this.resid=resid;
        this.menuList = menuList;
    }

    @Override
    public ProductViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_menurecycler, null);
        resolver = mCtx.getContentResolver();
        return new ProductViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder1 holder, final int position) {
        resolver = mCtx.getContentResolver();
        final Menu menu = menuList.get(position);
        databaseReference = FirebaseDatabase.getInstance().getReference("cart").child("9478018890");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("restaurants").child(resid).child("menu");

        String veg = menu.getItype();
        String veg1 = "https://www.image.amazerecipe.com/2016/06/vegetarian-symbol.png";
        String nonveg = "http://www.iec.edu.in/app/webroot/img/Icons/84246.png";
        String drinks = "http://www.eatlogos.com/food_and_drinks/png/vector_orange_drinks_logo.png";
        if (veg == "Veg") {
            Glide.with(mCtx)
                    .load(veg1)
                    .into(holder.Logo);
        } else if (veg == "Non Veg") {
            Glide.with(mCtx)
                    .load(nonveg)
                    .into(holder.Logo);
        } else {
            Glide.with(mCtx)
                    .load(drinks)
                    .into(holder.Logo);
        }
        holder.textViewFdescription.setText(menu.getIdesc().toString());
        String url = "http://hungerbite.com/admin/uploads/";
        String url2 = menu.getIlogo();
        String url3 = url + url2;
        holder.textViewFoodname.setText(menu.getIname().toString());
        holder.textViewForiginal.setText(menu.getIprice());

        if(menu.getIstatus().toString().equalsIgnoreCase("no")){
            holder.bcart.setText("Out of stock");
            holder.bcart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    return;
                }
            });
        }


        holder.bcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menu.getIcustom().equals("no")) {
                    quan++;
                    q = 0;
                    holder.v1.setVisibility(View.INVISIBLE);
                    holder.v2.setVisibility(View.VISIBLE);
                    ++q;
                    final String s = menu.getIname();
                    String s1 = menu.getIprice();
                    String id = menu.getIid().toString();
                    Double a = Double.parseDouble(s1);
                    Double r = (a * q);
                    pr = pr + a;
                    values = new ContentValues();
                    values.put(Util.COL_fNAME, s);
                    values.put(Util.COL_Price, s1);
                    values.put(Util.COL_quantity, q);
                    values.put(Util.COL_FID, id);
                    values.put(Util.COL_TOTAL, r);
                    final String b = menu.getIlogo();
                    final String ids = databaseReference.push().getKey();
                    Cart cart = new Cart(ids, id, s, s1, q, r);
                    databaseReference.child(ids).setValue(cart);
                   // System.out.println("inserted" + menu.getIcustom());
                    //  Toast.makeText(mCtx,menu.getIcustom(),Toast.LENGTH_LONG).show();

                    (mCtx).showCart(b, quan, pr, s);
                    holder.binc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            quan++;
                            ++q;
                            final String s = menu.getIname();
                            String s1 = menu.getIprice();
                            String id = menu.getIid();
                            Double a = Double.parseDouble(s1);
                            Double r = (a * q);
                            pr = pr + a;
                            values = new ContentValues();
                            values.put(Util.COL_fNAME, s);
                            values.put(Util.COL_Price, s1);
                            values.put(Util.COL_quantity, q);
                            values.put(Util.COL_FID, id);
                            values.put(Util.COL_TOTAL, r);
                            holder.qty.setText("" + q);
                            Cart cart = new Cart(ids, id, s, s1, q, r);
                            databaseReference.child(ids).setValue(cart);
                        }
                    });

                    holder.qty.setText("" + q);
                    holder.bdec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (q > 0) {
                                --quan;
                                --q;
                                final String s = menu.getIname();
                                String s1 = menu.getIprice();
                                String id = menu.getIid();
                                Double a = Double.parseDouble(s1);
                                Double r = (a * q);
                                pr = pr - a;
                                values = new ContentValues();
                                values.put(Util.COL_fNAME, s);
                                values.put(Util.COL_Price, s1);
                                values.put(Util.COL_quantity, q);
                                values.put(Util.COL_FID, id);
                                values.put(Util.COL_TOTAL, r);
                                holder.qty.setText("" + q);
                                Cart cart = new Cart(ids, id, s, s1, q, r);
                                databaseReference.child(ids).setValue(cart);
                                holder.qty.setText("" + q);
                                if (q == 0) {
                                    holder.v1.setVisibility(View.VISIBLE);
                                    holder.v2.setVisibility(View.INVISIBLE);
                                    String where1 = Util.COL_FID + " = " + menu.getIid();
                                    databaseReference.child(ids).removeValue();

                                }
                            }
                        }
                    });
                } else {

                    final String id = menu.getIid().toString();
                    LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View customView = inflater.inflate(R.layout.bottomdialog_layout, null);
                    final RadioGroup recyclerView= customView.findViewById(R.id.rec);
                    final RadioGroup r2= customView.findViewById(R.id.r2);
                    final TextView rect= customView.findViewById(R.id.rect);
                    final TextView recv= customView.findViewById(R.id.recv);
                    final TextView title= customView.findViewById(R.id.fn);
                    final TextView total= customView.findViewById(R.id.tt);
                    final Button add= customView.findViewById(R.id.add);

                    title.setText(menu.getIname());
                    total.setText(menu.getIprice());

                    new BottomDialog.Builder(mCtx)
                            .setCustomView(customView)
                            .show();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                    alertDialogBuilder.setMessage("Item is customisable");
                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(mCtx, "You clicked yes button", Toast.LENGTH_LONG).show();
                                }
                            });
                    databaseReference1.child(menu.getIid()).child("customisable").orderByChild("title").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            data1= new ArrayList<>();
                            data2= new ArrayList<>();
                            s11= new ArrayList<>();
                            s22= new ArrayList<>();
                            p11= new ArrayList<>();
                            p22= new ArrayList<>();
                            v11= new ArrayList<>();
                            v22= new ArrayList<>();
                            selitem= new ArrayList<>();

                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                System.out.print("Key is"+postSnapshot.getKey()+"name is"+postSnapshot.child("name").getValue());
                                String sid=postSnapshot.getKey();
                                String stitle=postSnapshot.child("title").getValue().toString();
                                String  sname=postSnapshot.child("oname").getValue().toString();
                                String sprice=postSnapshot.child("oprice").getValue().toString();
                                String stype=postSnapshot.child("otype").getValue().toString();
                                String smax=postSnapshot.child("max").getValue().toString();
                                String smin=postSnapshot.child("min").getValue().toString();
                                String sreq=postSnapshot.child("required").getValue().toString();

                                if(smax.equalsIgnoreCase("")){
                                    data1.add(new Custom(sid, stitle, sname, sprice, stype, smax, smin, sreq));
                                    System.out.println("added");
                                    s11.add(sname);
                                    p11.add(sprice);
                                    v11.add(stype);
                                    rect.setText(stitle);
                                }
                                else {
                                    data2.add(new Custom(sid, stitle, sname, sprice, stype, smax, smin, sreq));
                                    s22.add(sname);
                                    p22.add(sprice);
                                    v22.add(stype);
                                    recv.setText(stitle);
                                }
                                System.out.print("addedd");
                            }

                            for (int i=0; i<data1.size(); i++){
                                RadioButton radioButton = new RadioButton(mCtx);
                                int a=Integer.parseInt(p11.get(i));
                                int b= Integer.parseInt(menu.getIprice().toString().trim());
                                int c=a+b;
                                radioButton.setText(s11.get(i)+" "+"\u20B9"+c);
                                radioButton.setId(1000+i);
                                rprms= new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                recyclerView.addView(radioButton, rprms);

                            }
                            for ( int j=0; j<data2.size(); j++){

                                final int r=j;
                                CheckBox cb = new CheckBox(mCtx);
                                cb.setText(s22.get(j)+" "+"\u20B9"+p22.get(j));
                               cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                   @Override
                                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                       String msg="";
                                       if(isChecked){
                                           msg=p22.get(r);
                                           selitem.add(s22.get(r));
                                       }

                                       Toast.makeText(mCtx, msg, Toast.LENGTH_SHORT).show();
                                       int x= Integer.parseInt(total.getText().toString().trim());
                                       int y=Integer.parseInt(p22.get(r));
                                       int z=x+y;
                                       total.setText(""+z);
                                   }
                               });

                                cb.setId(2000+j);
                                r2.addView(cb);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    recyclerView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            RadioButton radioButton = group.findViewById(checkedId);
                            String selt=radioButton.getText().toString();
                            selitem.add(selt);
                            Toast.makeText(mCtx,selt
                                    , Toast.LENGTH_SHORT).show();

                        }
                    });

                   add.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           final String ids = databaseReference.push().getKey();
                           Cart cart = new Cart(ids, id, selitem.toString(), total.getText().toString(), 1,Double.parseDouble(total.getText().toString()) );
                           databaseReference.child(ids).setValue(cart);
                       }
                   });
                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return menuList.size();    }

    class ProductViewHolder1 extends RecyclerView.ViewHolder {

        TextView textViewFoodname,textViewFpricediscou,textViewForiginal,qty, textViewrupee, category;
        ImageView Logo;
        View v1,v2;
        ExpandableTextView textViewFdescription;
        Button bcart;
        TextView bdec,binc;

        ProductViewHolder1(View view) {
            super(view);
            v1= itemView.findViewById(R.id.a);
            v2= itemView.findViewById(R.id.b);
            textViewFoodname = itemView.findViewById(R.id.textViewFoodname);
            textViewFpricediscou = itemView.findViewById(R.id.textViewFpricediscou);
            textViewForiginal = itemView.findViewById(R.id.textViewForiginal);
            textViewrupee = itemView.findViewById(R.id.textView5);
            Logo = itemView.findViewById(R.id.imageView4);
            bcart = itemView.findViewById(R.id.bcart);
            qty = itemView.findViewById(R.id.textView);
            bdec = itemView.findViewById(R.id.buttondec);
            textViewFdescription = itemView.findViewById(R.id.textViewFdescription);
            binc = itemView.findViewById(R.id.buttoninc);
        }
    }
}