package mobile.bambu.vivecafe.Views.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.bambu.vivecafe.R;

/**
 * Created by Bambu on 06/11/2016.
 */

public class FundacionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fundacion, container, false);
        return view;
    }
}
