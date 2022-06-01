package com.example.nanodg.tambalban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.nanodg.tambalban.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PnlPemilikActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvslmt;
    CardView tambah,btnlogout,btnlihat,btnaduan,btnpesan,btnbengkel,btnaksesoris;
    FirebaseAuth firebaseAuth;
    String alias;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnl_pemilik);

        tvslmt = (TextView)findViewById(R.id.tvslmt);
        tambah = (CardView) findViewById(R.id.btntambah) ;
        btnlogout = (CardView)findViewById(R.id.btnlogout);
        btnlihat = (CardView)findViewById(R.id.btnlihat);
        btnlogout.setOnClickListener(this);
        btnlihat.setOnClickListener(this);
        tambah.setOnClickListener(this);
        initToolbar();
        /**
         * FIREBASE LOGIN
         */
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginUserActivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        alias = user.getEmail();

        /**
         * Merubah Email ke Username
         */
        DatabaseReference mUserContactsRef =  FirebaseDatabase.getInstance().getReference().child("Users");
        mUserContactsRef.orderByChild("email").equalTo(alias).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                //Log.e("barang1", dataSnapshot.toString());
                for (DataSnapshot userContact : dataSnapshot.getChildren()) {

                    User user = userContact.getValue(User.class);
                   tvslmt.setText("Selamat Datang : "+ user.getUsername());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Panel Pemilik");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                onBackPressed();

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == btnbengkel){

            startActivity(new Intent(this, TambahBengkelActivity.class));
        }
        if(view == btnaksesoris){

            startActivity(new Intent(this, TambahAksesorisActivity.class));
        }

        if(view == tambah){

            startActivity(new Intent(this, TambahActivity.class));
        }
        if(view == btnpesan){

            startActivity(new Intent(this, PesanPemilikActivity.class));
        }
        if(view == btnlihat){

            startActivity(new Intent(this, InfoDataAnctivity.class));
        }
        if(view == btnaduan){

            startActivity(new Intent(this, FirebaseDBReadAduanActivity.class));
        }
        if (view == btnlogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity

            //starting login activity
            startActivity(new Intent(this, LoginUserActivity.class));
            finish();
        }
    }
    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, PnlPemilikActivity.class);
    }


}
