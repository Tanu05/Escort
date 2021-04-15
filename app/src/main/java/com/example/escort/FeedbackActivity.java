package com.example.escort;

import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

public class FeedbackActivity extends AppCompatActivity {
    EditText namedata,emaildata,messagedata;
    TextView tvfeedback;
    RatingBar ratingBar;
    Button send;
    FeedbackInfo feedbackInfo;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        namedata = findViewById(R.id.namedata);
        emaildata = findViewById(R.id.emaildata);
        messagedata = findViewById(R.id.messagedata);

        tvfeedback = findViewById(R.id.tvfeedback);
        ratingBar = findViewById(R.id.ratingBar);

        send = findViewById(R.id.sendfeedback);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        feedbackInfo = new FeedbackInfo();

        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String name = namedata.getText().toString();
                String email = emaildata.getText().toString();
                String message = messagedata.getText().toString();
                String UniqueID= Settings.Secure.getString(getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);
                float rating = ratingBar.getRating();

                if (TextUtils.isEmpty(name)){
                    if(TextUtils.isEmpty(email)){
                        if (TextUtils.isEmpty(message)) {
                            Toast.makeText(FeedbackActivity.this,"Add proper data.",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
//                    if (email.trim().matches(emailPattern)){
//
//                    }
//                    else {
//                        Toast.makeText(FeedbackActivity.this,"Add proper Email.",Toast.LENGTH_SHORT).show();
//                    }
                else {
                    addDatatoFirebase(name, email, message, rating, UniqueID);
                }
            }
        });


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0 || rating == 0.5){
                    tvfeedback.setText("Very Bad");
                }
                else if (rating == 1 || rating == 1.5){
                    tvfeedback.setText("Bad");
                }
                else if (rating == 2 || rating == 3|| rating == 2.5 || rating == 3.5){
                    tvfeedback.setText("Neutral");
                }
                else if (rating == 4 || rating == 4.5){
                    tvfeedback.setText("Satisfied");
                }
                else {
                    tvfeedback.setText("Very Satisfied");
                }
            }
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId()==android.R.id.home){
//            finish();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }

    private void addDatatoFirebase(String name, String email, String message, float rating,String UniqueID) {
        feedbackInfo.setName(name);
        feedbackInfo.setEmail(email);
        feedbackInfo.setMessage(message);
        feedbackInfo.setRating(rating);
        feedbackInfo.setId(UniqueID);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(feedbackInfo);
                Toast.makeText(FeedbackActivity.this,"Data added",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FeedbackActivity.this,"Failed" + error,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
