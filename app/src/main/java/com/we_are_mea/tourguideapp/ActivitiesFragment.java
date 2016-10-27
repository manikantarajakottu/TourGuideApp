package com.we_are_mea.tourguideapp;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {

    String activityName = "";
    int activityImageId;
    int imageColor = R.color.colorPrimary;
    int containerColor = R.color.padres_orange;
    int choiceTextColor = R.color.padres_yellow;

    OnActivitySelectedListener mCallback;


    public ActivitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public void OnAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (ActivitiesFragment.OnActivitySelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " should be implemented OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.choice_list, container, false);


        final ArrayList<Items> items = new ArrayList<Items>();
        items.add(new Items("Writing", R.drawable.padre));
        items.add(new Items("Working", R.drawable.squareplaceholder));
        items.add(new Items("Watching TV Shows", R.drawable.squareplaceholder));
        items.add(new Items("Watching Movies", R.drawable.squareplaceholder));
        items.add(new Items("Swimming", R.drawable.squareplaceholder));
        items.add(new Items("Gardening", R.drawable.squareplaceholder));
        items.add(new Items("Napping", R.drawable.squareplaceholder));

        for (Items item : items ) {
            item.setmSelectedText("");
        }

        final ItemAdapter mItemAdapter = new ItemAdapter(getActivity(), items, containerColor,
                imageColor, R.color.padres_light, choiceTextColor);

        ListView mainListView = (ListView) rootview.findViewById(R.id.list);



        mainListView.setAdapter(mItemAdapter);





        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView mainImage = (ImageView)rootview.findViewById(R.id.item_main_image);

                activityImageId = items.get(position).getmListImage();

                activityName = items.get(position).getmChoiceHeader();


                mainImage.setImageResource(items.get(position).getmListImage());


                if (items.get(position).getmSelectedText() == "") {

                    items.get(position).setmSelectedText("I am\ndoing\nthis!");
                } else {
                    items.get(position).setmSelectedText("");

                }


                mCallback.onActivitySelected(activityName, activityImageId, imageColor,
                        containerColor, choiceTextColor);




                mItemAdapter.notifyDataSetChanged();



            }
        });



        return rootview;
    }


    // Container Activity must implement this interface
    public interface OnActivitySelectedListener {
        public void onActivitySelected(String activity, int activityImageId, int imageColor,
                                       int containerColor, int choiceTextColor);

    }


}
