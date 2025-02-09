package com.rijndael.kraloyun;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDatabaseLoad {

    private DatabaseReference mFirebaseDatabase,mFirebaseDatabase2,mFirebaseDatabase3,mFirebaseDatabase4;
    private FirebaseDatabase mFirebaseInstance;

public Context mContext;

    public static String[] resimler;
    public static String[] linkler;
    public static String[] isimler;


    public static int[] oyunmod;

    public static int say1,say2,say3,say4;

    public Drawable[] bitmaps;

    boolean oneshoot;



    public void initFirebase(){

        if(!oneshoot) {


            mFirebaseInstance = FirebaseDatabase.getInstance();
            mFirebaseDatabase = mFirebaseInstance.getReference("OyunLink");
            mFirebaseDatabase2 = mFirebaseInstance.getReference("OyunResim");
            mFirebaseDatabase3 = mFirebaseInstance.getReference("OyunIsim");
            mFirebaseDatabase4 = mFirebaseInstance.getReference("OyunMod");

            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    linkler = new String[(int) dataSnapshot.getChildrenCount()];

                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        String link = (String) messageSnapshot.getValue();
                        linkler[say1] = link;
                        say1++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

            mFirebaseDatabase2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    resimler = new String[(int) dataSnapshot.getChildrenCount()];
                    bitmaps = new Drawable[(int) dataSnapshot.getChildrenCount()];
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        String resim = (String) messageSnapshot.getValue();
                        resimler[say2] = resim;
                        say2++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });


            mFirebaseDatabase3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    isimler = new String[(int) dataSnapshot.getChildrenCount()];

                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        String link = (String) messageSnapshot.getValue();
                        isimler[say3] = link;
                        say3++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

            mFirebaseDatabase4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    oyunmod = new int[(int) dataSnapshot.getChildrenCount()];

                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        String mod = (String) messageSnapshot.getValue();
                        oyunmod[say4] = Integer.parseInt(mod);
                        say4++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

            oneshoot = true;
        }

    }





}
