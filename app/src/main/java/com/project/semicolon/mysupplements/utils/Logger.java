package com.project.semicolon.mysupplements.utils;

import android.util.Log;

public class Logger {
    public static final String TAG = Logger.class.getSimpleName();

    public static void verbose(String message) {
        Log.v(TAG, message);
    }

    public static void error(String error) {
        Log.e(TAG, error);
    }
}
