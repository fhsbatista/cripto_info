package com.devandroid.fbatista.criptoinfo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeStateReceiver extends BroadcastReceiver {

    private Context mContext;//O contexto e enviado no onReceiver, mas preciso dele como variavel global

    public static final String NOTIFY_NETWORK_CHANGE = "NOTIFY_NETWORK_CHANGE";
    public static final String EXTRA_IS_CONNECTED = "EXTRA_IS_CONNECTED";


    @Override
    public void onReceive(Context context, Intent intent) {



        mContext = context;

        if(isOnline())
            Toast.makeText(context, "Estou conectado!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "deu ruim", Toast.LENGTH_SHORT).show();


    }

    private boolean isOnline() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            if((networkInfo.getType() == ConnectivityManager.TYPE_WIFI) ||
                    networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                return true;

            }
            return false;
        }
        return false;


    }
}
