package com.example.navdrawer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FoodViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodViewFragment newInstance(String param1, String param2) {
        FoodViewFragment fragment = new FoodViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_view, container, false);

        CheckBox cbPizza = view.findViewById(R.id.cbPizza);
        CheckBox cbBurgers = view.findViewById(R.id.cbBurgers);
        CheckBox cbPasta = view.findViewById(R.id.cbPasta);
        Button btnGo = view.findViewById(R.id.btnGo);

        btnGo.setOnClickListener(v -> {
            ArrayList<String> selectedFoods = new ArrayList<>();
            if (cbPizza.isChecked()) selectedFoods.add("Pizza");
            if (cbBurgers.isChecked()) selectedFoods.add("Burgers");
            if (cbPasta.isChecked()) selectedFoods.add("Pasta");

            // Create an Intent and attach a Bundle
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("selectedFoods", selectedFoods);
            intent.putExtras(bundle); // Attach the bundle to the intent
            startActivity(intent);
        });
        // Inflate the layout for this fragment
        return view;
    }
}