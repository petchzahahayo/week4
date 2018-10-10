package com.example.administrator.petchtest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {

    TextView textView;
    DatabaseReference databaseReference;
    static int count = 29;
    Names names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        names = new Names();
        textView = findViewById(R.id.show);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Swipe();

        final Query query = databaseReference.orderByKey().equalTo(Integer.toString(count));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    names = ds.getValue(Names.class);
                    String id = ds.getKey();
                    textView.setText(id + " " + names.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void Swipe(){

        RelativeLayout constraintLayout = findViewById(R.id.conB);
        constraintLayout.setOnTouchListener(new Swipe(this){
            @Override
            public void onSwipeLeft() {
                count++;
                final Query query = databaseReference.orderByKey().equalTo(Integer.toString(count));
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            names = ds.getValue(Names.class);
                            String id = ds.getKey();
                            textView.setText(id + " " + names.getName());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(Main2Activity.this, "Plus", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeRight() {
                count--;
                final Query query = databaseReference.orderByKey().equalTo(Integer.toString(count));
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            names = ds.getValue(Names.class);
                            String id = ds.getKey();
                            textView.setText(id + " " + names.getName());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(Main2Activity.this, "Minus", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeTop() {
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
            }

            @Override
            public void onSwipeBottom() {
                Toast.makeText(Main2Activity.this, "Can't Swipe Up", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
