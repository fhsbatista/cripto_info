package com.devandroid.fbatista.criptoinfo.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.devandroid.fbatista.criptoinfo.R;

public class RateDialogPlayStoreFrag extends RateDialogFrag {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rate_dialog_playstore, container);

        Button mButton = view.findViewById(R.id.bt_no);
        mButton.setOnClickListener(this);

        mButton = view.findViewById(R.id.bt_yes);
        mButton.setOnClickListener(this);


        return view;


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bt_no:
                dismiss();
                break;

            case R.id.bt_yes:
                Toast.makeText(getActivity(), "Aqui vai a playstore", Toast.LENGTH_SHORT).show();
                dismiss();
                break;


        }
    }
}
