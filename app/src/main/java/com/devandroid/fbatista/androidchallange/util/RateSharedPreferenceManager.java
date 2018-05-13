package com.devandroid.fbatista.androidchallange.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class RateSharedPreferenceManager {

    public static final String LAUNCH_KEY = "launch_key";
    public static final int LAUNCH_TIMES = 3;
    public static final String  TIME_KEY = "time_key";
    public static final long TIME_OF_USE = 3 * 24 * 60 * 60 * 1000;
    public static final String DONT_ASK_AGAIN = "dont_ask_again";
    private static final String TAG = RateSharedPreferenceManager.class.getSimpleName();


    private static SharedPreferences getSharedPreference(Context context){

        return context.getSharedPreferences("preferences", Context.MODE_PRIVATE);

    }


    public static void updateLaunchTimes(Context context){

        SharedPreferences sp = getSharedPreference(context);
        sp.edit().putInt(LAUNCH_KEY , 0).apply();

    }

    public static void updateTime(Context context){

        SharedPreferences sp = getSharedPreference(context);
        Long currentTime = System.currentTimeMillis();



//        Log.d(TAG , "TEMPO ATUAL :" + currentTime + " TIMEOFUSE: " + currentTime + TIME_OF_USE);
        sp.edit().putLong(TIME_KEY, currentTime + TIME_OF_USE).apply();

    }

    public static void updateLaunchTimes(Context context, Bundle savedInstanceState){

        if (savedInstanceState != null) {
            return;
        }

        SharedPreferences sp = getSharedPreference(context);
        int launchTimes = sp.getInt(LAUNCH_KEY, 0);
        sp.edit().putInt(LAUNCH_KEY, launchTimes + 1).apply();

    }

    private static boolean isLaunchTimesvalid(Context context){

        SharedPreferences sp = getSharedPreference(context);
        int launchTimes = sp.getInt(LAUNCH_KEY, 0);

//        Log.d(TAG + " launchTimes", String.valueOf(launchTimes));
//        Log.d(TAG + " isLaunchTimesvalid", String.valueOf(launchTimes>0 && launchTimes % LAUNCH_TIMES == 0));
        return launchTimes>0 && launchTimes % LAUNCH_TIMES == 0;
    }


    private static boolean isTimeValid(Context context){

        SharedPreferences sp = getSharedPreference(context);
        long timeRequired  = sp.getLong(TIME_KEY, 0);

        if(timeRequired == 0){
            updateTime(context);
            timeRequired  = sp.getLong(TIME_KEY, 0);
        }

//        Log.d(TAG + " isTimeValid", String.valueOf(timeRequired < System.currentTimeMillis()));
        return timeRequired < System.currentTimeMillis();


    }

    public static void activeDontAskAgain(Context context){

        SharedPreferences sp = getSharedPreference(context);
        sp.edit().putBoolean(DONT_ASK_AGAIN, true).apply();
    }

    private static boolean isActiveDontAskAgain(Context context){

        SharedPreferences sp = getSharedPreference(context);

//        Log.d(TAG + "isActiveDontAskAgain", String.valueOf(sp.getBoolean(DONT_ASK_AGAIN, false)));
        return sp.getBoolean(DONT_ASK_AGAIN, false);

    }

    public static boolean canShowDialog(Context context){

//        Log.d(TAG, "CANSHOWDIALOG");
        return !isActiveDontAskAgain(context)
                && (isTimeValid(context) || (isLaunchTimesvalid(context)));
    }





}
