package com.example.navdrawer;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatViewFragment extends Fragment {

    private TextView tvAppTime;
    private Handler handler = new Handler();
    private Runnable updateRunnable;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatViewFragment newInstance(String param1, String param2) {
        StatViewFragment fragment = new StatViewFragment();
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
        View view = inflater.inflate(R.layout.fragment_stat_view, container, false);

        // Input fields
        EditText etName = view.findViewById(R.id.etName);
        Spinner spPronouns = view.findViewById(R.id.spPronouns);
        RatingBar rbReviewScore = view.findViewById(R.id.rbReviewScore);
        EditText etReviewComment = view.findViewById(R.id.etReviewComment);
        Spinner spReceiveUpdates = view.findViewById(R.id.spReceiveUpdates);
        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        Button btnEditReview = view.findViewById(R.id.btnEditReview);

        // Display container
        LinearLayout containerSubmittedInfo = view.findViewById(R.id.containerSubmittedInfo);
        LinearLayout containerInputs = view.findViewById(R.id.containerInputs);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvPronouns = view.findViewById(R.id.tvPronouns);
        TextView tvReviewScore = view.findViewById(R.id.tvReviewScore);
        TextView tvReviewComment = view.findViewById(R.id.tvReviewComment);
        TextView tvReceiveUpdates = view.findViewById(R.id.tvReceiveUpdates);
        TextView tvWatchedFriends = view.findViewById(R.id.tvWatchedFriends);
        Switch switchWatchedFriends = view.findViewById(R.id.switchWatchedFriends);

        // Restore state from SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("AppState", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("IsSubmitted", false)) {
            String name = sharedPreferences.getString("Name", "");
            String pronouns = sharedPreferences.getString("Pronouns", "");
            float reviewScore = sharedPreferences.getFloat("ReviewScore", 0);
            String reviewComment = sharedPreferences.getString("ReviewComment", "");
            String receiveUpdates = sharedPreferences.getString("ReceiveUpdates", "");
            boolean watchedFriends = sharedPreferences.getBoolean("WatchedFriends", false);

            // Populate the UI
            tvName.setText("Name: " + name);
            tvPronouns.setText("Pronouns: " + pronouns);
            tvReviewScore.setText("Review Score: " + reviewScore);
            tvReviewComment.setText("Review Comment: " + reviewComment);
            tvReceiveUpdates.setText("Receive Updates: " + receiveUpdates);
            tvWatchedFriends.setText("Watched Friends: " + (watchedFriends ? "Yes" : "No"));

            // Set visibility
            containerSubmittedInfo.setVisibility(View.VISIBLE);
            containerInputs.setVisibility(View.GONE);
        } else {
            containerSubmittedInfo.setVisibility(View.GONE);
            containerInputs.setVisibility(View.VISIBLE);
        }

        btnSubmit.setOnClickListener(v -> {
            // Fetch user inputs
            String name = etName.getText().toString();
            String pronouns = spPronouns.getSelectedItem().toString();
            float reviewScore = rbReviewScore.getRating();
            String reviewComment = etReviewComment.getText().toString();
            String receiveUpdates = spReceiveUpdates.getSelectedItem().toString();
            boolean watchedFriends = switchWatchedFriends.isChecked();

            // Save State to SharedPreferences
            //SharedPreferences sharedPreferences = requireContext().getSharedPreferences("AppState", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Name", name);
            editor.putString("Pronouns", pronouns);
            editor.putFloat("ReviewScore", reviewScore);
            editor.putString("ReviewComment", reviewComment);
            editor.putString("ReceiveUpdates", receiveUpdates);
            editor.putBoolean("WatchedFriends", watchedFriends);
            editor.putBoolean("IsSubmitted", true);
            editor.commit();

            // Display submitted information
            tvName.setText("Name: " + name);
            tvPronouns.setText("Pronouns: " + pronouns);
            tvReviewScore.setText("Review Score: " + reviewScore);
            tvReviewComment.setText("Review Comment: " + reviewComment);
            tvReceiveUpdates.setText("Receive Updates: " + receiveUpdates);
            tvWatchedFriends.setText("Watched Friends: " + (watchedFriends ? "Yes" : "No"));

            // Make the container visible
            containerSubmittedInfo.setVisibility(View.VISIBLE);
            containerInputs.setVisibility(View.GONE);
        });

        btnEditReview.setOnClickListener(v -> {
            // Reset submitted state
            //SharedPreferences sharedPreferences = requireContext().getSharedPreferences("AppState", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("IsSubmitted", false);
            editor.commit();

            // Hide submitted info container and show input container
            containerSubmittedInfo.setVisibility(View.GONE);
            containerInputs.setVisibility(View.VISIBLE);
        });


        // Runtime statistics
        tvAppTime = view.findViewById(R.id.tvAppTime);

        // Update uptime every second
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                if (getActivity() instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    long secondsElapsed = mainActivity.getSecondsElapsed();
                    tvAppTime.setText("You've had the app open for " + secondsElapsed + " seconds!");
                }
                handler.postDelayed(this, 5000); // Match interval with MyService
            }
        };
        handler.post(updateRunnable);

        return view;
    }
}