package com.devandroid.fbatista.androidchallange.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devandroid.fbatista.androidchallange.R;

public class RateDialogFeedbackFrag extends RateDialogFrag {

    public static final String RATING_KEY = "rating";
    private float rating;
    private EditText mEditTextFeedback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rate_dialog_feedback, container);

        Button mButton = view.findViewById(R.id.bt_no);
        mButton.setOnClickListener(this);

        mButton = view.findViewById(R.id.bt_send);
        mButton.setOnClickListener(this);

        mEditTextFeedback = view.findViewById(R.id.et_feedback);

        if (savedInstanceState != null) {

            Float rating = savedInstanceState.getFloat(RATING_KEY);


        }

        return view;

    }

    @Override
    public void onClick(View v) {

        String feedback = mEditTextFeedback.getText().toString();

        switch (v.getId()){


            case R.id.bt_no:
                dismiss();
                break;

            case R.id.bt_send:
                if(!feedback.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setType("text/plain");
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fernandohsbatista@gmail.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Avaliação aplicativo");
                    intent.putExtra(Intent.EXTRA_TEXT, "Estrelas: " + rating + "\n\nAvaliação: " + feedback);
                    getActivity().startActivity(Intent.createChooser(intent, "Enviar e-mail"));
                }else{
                    Toast.makeText(getActivity(), "Preencha o feedback", Toast.LENGTH_SHORT).show();
                }

                dismiss();
                break;

        }

    }
    public void setRating(float rating){
        this.rating = rating;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putFloat(RATING_KEY, rating);
        super.onSaveInstanceState(outState);


    }
}
