package com.example.navdrawer.ui.main;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.navdrawer.MainActivity;
import com.example.navdrawer.R;
import com.example.navdrawer.databinding.FragmentTabbedPortfolioBinding;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentTabbedPortfolioBinding binding;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentTabbedPortfolioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.sectionLabel;
        // TABBED VIEWS FUNCTIONALITY GOES HERE
        // Extract IDs
        TextView tv = root.findViewById(R.id.tvPortfolio);
        ImageView iv1 = root.findViewById(R.id.ivPortfolio1);
        ImageView iv2 = root.findViewById(R.id.ivPortfolio2);
        ImageView iv3 = root.findViewById(R.id.ivPortfolio3);
        ImageView ivEnlarged = root.findViewById(R.id.image_enlarged);
        String globalDesc =
                "An application developed in HTML5, JQuery and CSS." +
                        "Features custom styling and graphics created by me.";
        String hcDesc =
                "An application developed entirely in ASP.NET. " +
                        "It features user authentication and authorization, controlled access, " +
                        "and graphics created by me.";
        String synthDesc =
                "My first ever application developed in HTML and CSS. " +
                        "Features custom styling and graphics created by me.";

        // Create visibility
        // Tab 1: Global
        if(getArguments().getInt(ARG_SECTION_NUMBER) == 1)
        {
            tv.setText(globalDesc);
            iv1.setImageResource(R.drawable.proj1img1);
            iv2.setImageResource(R.drawable.proj1img2);
            iv3.setImageResource(R.drawable.proj1img3);
            ivEnlarged.setVisibility(View.GONE);
        }
        // Tab 2: HC
        if(getArguments().getInt(ARG_SECTION_NUMBER) == 2)
        {
            tv.setText(hcDesc);
            iv1.setImageResource(R.drawable.proj3img1);
            iv2.setImageResource(R.drawable.proj3img2);
            iv3.setImageResource(R.drawable.proj3img3);
            ivEnlarged.setVisibility(View.GONE);
        }
        // Tab 3: Synth
        if(getArguments().getInt(ARG_SECTION_NUMBER) == 3)
        {
            tv.setText(synthDesc);
            iv1.setImageResource(R.drawable.proj2img1);
            iv2.setImageResource(R.drawable.proj2img2);
            iv3.setImageResource(R.drawable.proj2img3);
            ivEnlarged.setVisibility(View.GONE);
        }
        // Onclick for Thumbnails
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivEnlarged.setImageDrawable(iv1.getDrawable());
                ivEnlarged.setVisibility(View.VISIBLE);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivEnlarged.setImageDrawable(iv2.getDrawable());
                ivEnlarged.setVisibility(View.VISIBLE);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivEnlarged.setImageDrawable(iv3.getDrawable());
                ivEnlarged.setVisibility(View.VISIBLE);
            }
        });
        // Onclick for Enlarged Image: Tap again to make it smaller.
        ivEnlarged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivEnlarged.setVisibility(View.GONE);
            }
        });

        // TABBED VIEWS FUNCTIONALITY ENDS HERE
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}