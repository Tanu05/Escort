package com.example.escort;

import android.view.View;
import android.widget.Toast;

public class PlaceToVisit {

        //Name of the place to visit.
        private String mPlaceName;

        //Image resource ID for the place to visit.
        private int mImageResourceId;

        //Description of the place to visit.
        private String mPlaceDescription;

        //Information regarding the place to visit.
        private String mPlaceInformation;

        private String mDirection;


    public PlaceToVisit(String placeName, int imageResourceId, String placeDescription, String placeInformation, String placeDirection) {
            mPlaceName = placeName;
            mImageResourceId = imageResourceId;
            mPlaceDescription = placeDescription;
            mPlaceInformation = placeInformation;
            mDirection = placeDirection;
        }

    //Get the name of the place.
        public String getPlaceName () {
            return mPlaceName;
        }

        //Return the image resource ID of this location.
        public int getImageResourceId () {
            return mImageResourceId;
        }

        //Get the description of the place to visit.
        public String getPlaceDescription () {
            return mPlaceDescription;
        }

        public String getmDirection() {
            return mDirection;
        }

        public void setmDirection(String mDirection) {
            this.mDirection = mDirection;
        }

        public void setmPlaceName(String mPlaceName) {
            this.mPlaceName = mPlaceName;
        }

        public void setmImageResourceId(int mImageResourceId) {
            this.mImageResourceId = mImageResourceId;
        }

        public void setmPlaceDescription(String mPlaceDescription) {
            this.mPlaceDescription = mPlaceDescription;
        }

        public void setmPlaceInformation(String mPlaceInformation) {
            this.mPlaceInformation = mPlaceInformation;
        }

        //Get the information regarding the place to visit.
            public String getPlaceInformation () {
                return mPlaceInformation;
            }


}
