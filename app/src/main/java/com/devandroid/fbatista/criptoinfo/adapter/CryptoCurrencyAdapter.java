package com.devandroid.fbatista.criptoinfo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devandroid.fbatista.criptoinfo.R;
import com.devandroid.fbatista.criptoinfo.model.CryptoCurrency;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CryptoCurrencyAdapter extends RecyclerView.Adapter<CryptoCurrencyAdapter.MyViewHolder> {

    private List<CryptoCurrency> currencies;
    private Context context;

    public CryptoCurrencyAdapter(List<CryptoCurrency> currencies, Context context) {
        this.currencies = currencies;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_crypto_currency
        ,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CryptoCurrency currency = currencies.get(position);
        holder.mName.setText(currency.getName());
        holder.mSymbol.setText(currency.getSymbol());
        holder.mPrice.setText("$ " + currency.getPrice_usd().toString());
        holder.mChange.setText(currency.getPercent_change_24h().toString() + " %");

        if(currency.getPercent_change_24h() >= 0){
            holder.mChange.setTextColor(context.getResources().getColor(R.color.colorPrimaryDarkPositivo));
        }else{
            holder.mChange.setTextColor(context.getResources().getColor(R.color.colorPrimaryDarkNegativo));

        }

        Picasso.get().load(currency.getThumbnail_url()).resize(72,72).into(holder.mThumbnail);

    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mName;
        TextView mSymbol;
        TextView mPrice;
        TextView mChange;
        ImageView mThumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name_text_view);
            mSymbol = itemView.findViewById(R.id.symbol_text_view);
            mPrice = itemView.findViewById(R.id.price_text_view);
            mChange = itemView.findViewById(R.id.percent_change_text_view);
            mThumbnail = itemView.findViewById(R.id.thumbnail_image_view);
        }
    }
}
