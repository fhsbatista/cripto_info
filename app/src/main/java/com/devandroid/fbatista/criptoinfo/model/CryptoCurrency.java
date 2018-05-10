package com.devandroid.fbatista.criptoinfo.model;

import java.io.Serializable;

public class CryptoCurrency implements Serializable{

    public static final String FIXED_URL = "https://coincap.io/images/coins/";
    private String name;
    private String symbol;
    private Double price_usd;
    private Double percent_change_24h;
    private String thumbnail_url;

    public CryptoCurrency(String name, String symbol, Double price_usd, Double percent_change_24h) {
        this.name = name;
        this.symbol = symbol;
        this.price_usd = price_usd;
        this.percent_change_24h = percent_change_24h;
    }


    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getPrice_usd() {
        return price_usd;
    }

    public Double getPercent_change_24h() {
        return percent_change_24h;
    }
}
