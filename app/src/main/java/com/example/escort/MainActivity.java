package com.example.escort;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escort.ui.main.SectionsPagerAdapter;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab,fab1,fab2,fab3;
    private Animation fabOpen,fabClose,rotateForward,rotateBackward;
    private boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        fab =  (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);

        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Camera fab click",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);

            }
        });
        fab2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Feedback fab click",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), FeedbackActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Save fab click",Toast.LENGTH_SHORT).show();
                showChangeLanguageDialog();
            }
        });

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },100);
        }
    }

    private void showChangeLanguageDialog() {
        final String[] listItems={"हिंदी","मराठी","ગુજરાતી","தமிழ்","English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language......");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    setLocale("hi");
                    recreate();
                }
                else if (which == 1){
                    setLocale("mr");
                    recreate();
                }
                else if (which == 2){
                    setLocale("gu");
                    recreate();
                }
                else if (which == 3){
                    setLocale("ta");
                    recreate();
                }
                else if (which == 4){
                    setLocale("en");
                    recreate();
                }

                //dismiss
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();

        mDialog.show();
    }
 
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }

    public void onToggleClick(View view){
        Toast.makeText(this,"CustomToggle",Toast.LENGTH_SHORT).show();
    }
    

    public void animateFab(){
        if (isOpen){
            fab.startAnimation(rotateForward);
            fab1.startAnimation(fabClose);
            fab2.startAnimation(fabClose);
            fab3.startAnimation(fabClose);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isOpen=false;
        }
        else {
            fab.startAnimation(rotateBackward);
            fab1.startAnimation(fabOpen);
            fab2.startAnimation(fabOpen);
            fab3.startAnimation(fabOpen);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isOpen=true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");

//            imageView.setImageBitmap(captureImage);
        }
    }
}