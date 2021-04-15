package com.example.escort;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CategoryBuildingsFragment extends Fragment {
    public CategoryBuildingsFragment(){

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.category_list, container, false);

        // Crbldgse the list for the bldgs Category. All texts are stored as Strings.
        final ArrayList<PlaceToVisit> listOfPlaces = new ArrayList<PlaceToVisit>();
        listOfPlaces.add(new PlaceToVisit(getString(R.string.bldgs_item1_name), R.drawable.hutatma_chowk,
                getString(R.string.bldgs_item1_description), getString(R.string.bldgs_item1_information),getString(R.string.bldgs_direction1)));
        listOfPlaces.add(new PlaceToVisit(getString(R.string.bldgs_item2_name), R.drawable.taj_mahal_palace,
                getString(R.string.bldgs_item2_description), getString(R.string.bldgs_item2_information),getString(R.string.bldgs_direction2)));
        listOfPlaces.add(new PlaceToVisit(getString(R.string.bldgs_item3_name), R.drawable.david_sasson_library,
                getString(R.string.bldgs_item3_description), getString(R.string.bldgs_item3_information),getString(R.string.bldgs_direction3)));
        listOfPlaces.add(new PlaceToVisit(getString(R.string.bldgs_item4_name), R.drawable.phoenix_mall,
                getString(R.string.bldgs_item4_description), getString(R.string.bldgs_item4_information),getString(R.string.bldgs_direction4)));


        // Create an {@link PlaceToVisitAdapter}, whose data source is a list of {@link PlaceToVisit}s.
        // Then find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        PlaceToVisitAdaptor adapter = new PlaceToVisitAdaptor(getActivity(), listOfPlaces);
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we crbldgsed above, so that the
        // {@link ListView} will display list items for each {@link PlaceToVisit} in the list.
        listView.setAdapter(adapter);

        // Sets the action after clicking on each items. Will export the information contained at
        // that position to the PlaceToVisitDetails Activity.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Context context = listView.getContext();
                Intent intent = new Intent(context, PlaceToVisitDetails.class);
                intent.putExtra("placeName", listOfPlaces.get(position).getPlaceName());
                intent.putExtra("placeImage", listOfPlaces.get(position).getImageResourceId());
                intent.putExtra("placeDescription", listOfPlaces.get(position).getPlaceDescription());
                intent.putExtra("placeInformation", listOfPlaces.get(position).getPlaceInformation());
                intent.putExtra("placeDirection", listOfPlaces.get(position).getmDirection());
                context.startActivity(intent);
            }
        });
        return rootView;
    }
}
