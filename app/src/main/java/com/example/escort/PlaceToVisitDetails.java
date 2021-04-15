package com.example.escort;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.BreakIterator;

public class PlaceToVisitDetails extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the item_details.xml layout file
        setContentView(R.layout.item_details);

        // Set up the toolbar, to prepare for the Collapsing effect.
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Receive the intents that were sent from the selected item previously from the list of
        // {@link PlaceToVisit} objects.
        Intent intent = getIntent();
        String placeName = intent.getExtras().getString("placeName");
        int placeImage = intent.getExtras().getInt("placeImage");
        String placeDescription = intent.getExtras().getString("placeDescription");
        String placeInformation = intent.getExtras().getString("placeInformation");
        String placeDirection = intent.getExtras().getString("placeDirection");

        // Set the toolbar title to be the name of the selected item and set the Collapsing effect.
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(placeName);

        // Set the top ImageView to be the image imported from the selected item.
        ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(placeImage).apply(RequestOptions.centerCropTransform()).into(imageView);

        // Set the description TextView to be the description imported from the selected item.
        TextView descriptionTextView = (TextView) findViewById(R.id.textview_description);
        descriptionTextView.setText(placeDescription);

        // Set the information TextView to be the information imported from the selected item.
        TextView informationTextView = (TextView) findViewById(R.id.textview_information);
        informationTextView.setText(placeInformation);

        TextView  directionTextView = (TextView) findViewById(R.id.textview_direction);
        directionTextView.setText(placeDirection);
        Linkify.addLinks(directionTextView,Linkify.WEB_URLS);
//
//        TextView link = (TextView) findViewById(R.id.textview_direction);
//        link.setText(placeDirection);
//        Linkify.addLinks(placeDirection,Linkify.MAP_ADDRESSES);
//        link.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
