package com.devandroid.fbatista.androidchallange.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import com.devandroid.fbatista.androidchallange.R;

public class RateDialogFrag extends DialogFragment
                                implements RatingBar.OnRatingBarChangeListener
                                , View.OnClickListener{

    public static final String KEY = "fragment_rate";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rate_dialog, container);

        RatingBar mRatingBar = view.findViewById(R.id.rb_rate);
        mRatingBar.setOnRatingBarChangeListener(this);

        Button mButton = view.findViewById(R.id.bt_later);
        mButton.setOnClickListener(this);

        mButton = view.findViewById(R.id.bt_never);
        mButton.setOnClickListener(this);

        return view;


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.bt_later :

                RateSharedPreferenceManager.updateTime(getActivity());
                RateSharedPreferenceManager.updateLaunchTimes(getActivity());
                dismiss();
                break;
            case R.id.bt_never :

                RateSharedPreferenceManager.activeDontAskAgain(getActivity());
                dismiss();
                break;

        }

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

        if(rating >= 4) {
            RateDialogManager.showDialogPlaystore(getActivity());
            RateSharedPreferenceManager.activeDontAskAgain(getActivity());
            dismiss();
        }else if(rating > 0 ){
            RateDialogManager.showDialogFeedback(getActivity(), rating);
            RateSharedPreferenceManager.updateLaunchTimes(getActivity());
            RateSharedPreferenceManager.updateTime(getActivity());
            dismiss();
        }



    }
}
