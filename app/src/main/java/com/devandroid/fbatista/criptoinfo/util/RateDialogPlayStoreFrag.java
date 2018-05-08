package com.devandroid.fbatista.criptoinfo.util;

import android.content.Intent;
import android.net.Uri;
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

        switch (v.getId()) {

            case R.id.bt_no:

                break;

            case R.id.bt_yes:
                String packageName = "com.devandroid.fbatista.criptoinfo";
                Intent it;

                try {
                    it = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + packageName));
                    startActivity(it);
                } catch (android.content.ActivityNotFoundException e) {

                    it = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/" +
                                    "details?id=" + packageName));
                    startActivity(it);

                    break;


                }
                dismiss();
        }
    }
}
