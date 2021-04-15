package com.example.escort;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PlaceToVisitAdaptor extends ArrayAdapter<PlaceToVisit> {


    public PlaceToVisitAdaptor(@NonNull Context context, @NonNull ArrayList<PlaceToVisit> listOfPlaces) {
        super(context, 0, listOfPlaces);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link PlaceToVisit} object located at this position in the list
        PlaceToVisit currentPlaceToVisit = getItem(position);

        // Find the TextView in the list_item.xml layout and set the text to the current
        // position's name.
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.text_view);
        defaultTextView.setText(currentPlaceToVisit.getPlaceName());

        // Find the ImageView in the list_item.xml layout and set the image to the current
        // position's image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);
        imageView.setImageResource(currentPlaceToVisit.getImageResourceId());

        // Return the whole list item layout (containing 1 TextView and 1 ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
