package com.devandroid.fbatista.criptoinfo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.devandroid.fbatista.criptoinfo.adapter.CryptoCurrencyAdapter;
import com.devandroid.fbatista.criptoinfo.model.CryptoCurrency;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescricaoActivity extends AppCompatActivity {

    private static final String TAG = DescricaoActivity.class.getSimpleName();
    private ImageView mImgCurrency;
    private TextView mTextViewNameCurrency;
    private TextView mTextViewChangeCurrency;
    private TextView mTextViewValueCurrency;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao);

        Bundle bundle = getIntent().getExtras();
        CryptoCurrency cryptoCurrency = (CryptoCurrency) bundle.getSerializable(MainActivity.CURRENCY_KEY);

        mImgCurrency = findViewById(R.id.img_currency_image);
        mTextViewChangeCurrency = findViewById(R.id.tv_change_24hr);
        mTextViewNameCurrency = findViewById(R.id.tv_currency_name);
        mTextViewValueCurrency = findViewById(R.id.tv_currency_current_value);

        mTextViewNameCurrency.setText(String.valueOf(cryptoCurrency.getName()) + " " + String.valueOf(cryptoCurrency.getSymbol()));
        mTextViewValueCurrency.setText("$ " + String.valueOf(cryptoCurrency.getPrice_usd()));
        mTextViewChangeCurrency.setText(String.valueOf(cryptoCurrency.getPercent_change_24h()) + " %");


        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference logoStorageReference = storageReference.child(
                "coin-logos/" + cryptoCurrency.getSymbol().toLowerCase() + ".png");

        logoStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).resize(72, 72).into(mImgCurrency);
                Log.d(TAG, "onSucessStorage :" + uri);

            }
        });









    }

}
