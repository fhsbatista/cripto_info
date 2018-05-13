package com.devandroid.fbatista.androidchallange;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.devandroid.fbatista.androidchallange.adapter.CryptoCurrencyAdapter;
import com.devandroid.fbatista.androidchallange.model.CryptoCurrency;
import com.devandroid.fbatista.androidchallange.util.RateDialogManager;
import com.devandroid.fbatista.androidchallange.util.RecyclerViewItemClickListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private CryptoCurrencyAdapter mCryptoCurrencyAdapter;
    private List<CryptoCurrency> currencies = new ArrayList<CryptoCurrency>();
    private TextView mConexao;
    private ProgressBar mProgressBar;
    private TextView mAtualizacaoLabel;
    private Button mRefresh;
    private Toolbar toolbar;
    public static final String CURRENCY_KEY = "int_currency_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recycler_view);
        mConexao = findViewById(R.id.conexao_text_view);
        mProgressBar = findViewById(R.id.load_info_progress_bar);
        mAtualizacaoLabel = findViewById(R.id.atualizacao_text_view);
        mRefresh = findViewById(R.id.refresh_button);

        //Chamando manager que verifica se e o momento de pedir avaliaçao e feedback do usuario
        RateDialogManager.showRateDialog(this, savedInstanceState);


        //Configurando a RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        //Configura o adapter
        mCryptoCurrencyAdapter = new CryptoCurrencyAdapter(currencies, this);
        mRecyclerView.setAdapter(mCryptoCurrencyAdapter);

        //Configurando click em item da recyclerview
        mRecyclerView.addOnItemTouchListener(
                new RecyclerViewItemClickListener(
                        getApplicationContext(),
                        mRecyclerView,
                        new RecyclerViewItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                CryptoCurrency cryptoCurrency = currencies.get(position);
                                Log.d(TAG, "moeda: " + cryptoCurrency.getName());
                                Intent intent = new Intent(MainActivity.this, DescricaoActivity.class);
                                intent.putExtra(CURRENCY_KEY, cryptoCurrency);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarConexao();
            }
        });

        verificarConexao();
    }

    private void verificarConexao(){
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        if (isOnline()) {

            atualizarDadosMoedas();
            mRecyclerView.setVisibility(View.VISIBLE);
            mConexao.setVisibility(View.GONE);
            mRefresh.setVisibility(View.GONE);
        }
        else {
            mConexao.setVisibility(View.VISIBLE);
            mRefresh.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }





    private void atualizarDadosMoedas() {
        CoinCapService coinCapService = CoinCapService.retrofit.create(CoinCapService.class);
        Call<List<CryptoCurrency>> call = coinCapService.getCryptoCurrency(20);
        call.enqueue(new Callback<List<CryptoCurrency>>() {

            @Override
            public void onResponse(Call<List<CryptoCurrency>> call, Response<List<CryptoCurrency>> response) {
                int code = response.code();
                int listSize = response.body().size();
                mProgressBar.setMax(listSize);
                int progressBarIndex = 0;
                if (code == 200) {

                    for (CryptoCurrency cryptoCurrency : response.body()) {
                        String url = cryptoCurrency.FIXED_URL + cryptoCurrency.getName()
                                .replaceAll(" ", "") + ".png";
                        cryptoCurrency.setThumbnail_url(url.trim());
                        progressBarIndex++;
                        mProgressBar.setProgress(progressBarIndex);
                        currencies.add(cryptoCurrency);
                    }

                    mProgressBar.setVisibility(View.GONE);
                    mCryptoCurrencyAdapter.notifyDataSetChanged();

                    String horaAtualizacao = new SimpleDateFormat(" dd/MM HH:mm")
                            .format(Calendar.getInstance().getTime());
                    mAtualizacaoLabel.setText("Ult. Atualizaçao : " + horaAtualizacao);

                }
            }

            @Override
            public void onFailure(Call<List<CryptoCurrency>> call, Throwable t) {

            }
        });

    }

    private boolean isOnline() {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        return networkInfo != null && networkInfo.isConnected();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.refresh_menu: {
                verificarConexao();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
