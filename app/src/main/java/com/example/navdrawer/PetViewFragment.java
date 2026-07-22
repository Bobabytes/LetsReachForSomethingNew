package com.example.navdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PetViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PetViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PetViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PetViewFragment newInstance(String param1, String param2) {
        PetViewFragment fragment = new PetViewFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pet_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize WebView
        WebView browser = view.findViewById(R.id.wvPetview);
        browser.setWebViewClient(new WebViewClient());


        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setDomStorageEnabled(true);
        browser.getSettings().setAllowContentAccess(true);
//        browser.getSettings().setUseWideViewPort(true);
        browser.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        browser.loadUrl("https://www.reddit.com/r/lookatmydog/");

        // Set up RadioGroup listener
        RadioGroup group = view.findViewById(R.id.rgPetview);
        group.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            if (checkedId == R.id.rbDogs)
                browser.loadUrl("https://www.reddit.com/r/lookatmydog/");
            else if (checkedId == R.id.rbCats)
                browser.loadUrl("https://www.reddit.com/r/catpics/");
            else if (checkedId == R.id.rbBunnies)
                browser.loadUrl("https://www.reddit.com/r/Bunnies/");
            else if (checkedId == R.id.rbFerrets)
                browser.loadUrl("https://www.reddit.com/r/ferrets/");
        });
    }
}