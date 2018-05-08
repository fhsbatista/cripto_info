package com.devandroid.fbatista.criptoinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DescricaoActivity extends AppCompatActivity {

    private ImageView mImgCurrency;
    private TextView mTextViewNameCurrency;
    private TextView mTextViewChangeCurrency;
    private TextView mTextViewValueCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao);

        mImgCurrency = findViewById(R.id.img_currency_image);
        mTextViewChangeCurrency = findViewById(R.id.tv_change_24hr);
        mTextViewNameCurrency = findViewById(R.id.tv_currency_name);
        mTextViewValueCurrency = findViewById(R.id.tv_currency_current_value);




    }
}
