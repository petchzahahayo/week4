package com.example.administrator.petchtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText getname;
    Button submit;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Names names = new Names();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getname = (EditText)findViewById(R.id.nameadd);
        submit = (Button)findViewById(R.id.btn_add);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getvalue();

            }
        });
        Swipe();


    }




    public void  getvalue(){

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("29");
        names.setName(getname.getText().toString());

        myRef.setValue(names);
    }


    public void Swipe(){

        RelativeLayout constraintLayout = findViewById(R.id.conA);
        constraintLayout.setOnTouchListener(new Swipe(this){
            @Override
            public void onSwipeLeft() {


            }
            @Override
            public void onSwipeRight() {

            }

            @Override
            public void onSwipeTop() {

            }

            @Override
            public void onSwipeBottom() {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));

            }

        });
    }
    //ไม่มีอะไรแต่่ห้ามลืมใส่
    private void slideActivity(Class<?> otherclass){
        Intent intent = new Intent(this,otherclass);
        startActivity(intent);
    }


}
