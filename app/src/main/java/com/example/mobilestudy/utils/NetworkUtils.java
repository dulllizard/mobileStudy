package com.example.mobilestudy.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String BASE_URL = "https://kudago.com/public-api/v1.4/";
    private static final String EVENT_LIST = "events/";
    private static final String PARAM_FIELDS_KEY = "fields";
    private static final String PARAM_FIELDS_VALUE = "id,title,place,description,categories,images";
    private static final String PARAM_LOCATION_KEY = "location";
    private static final String PARAM_LOCATION_VALUE = "krasnoyarsk";
    private static final String PARAM_ACTUAL_SINCE_KEY = "actual_since";
    private static final String PARAM_ACTUAL_SINCE_VALUE = "1709226000";
    private static final String PARAM_CATEGORIES_KEY = "categories";
    private static final String PARAM_CATEGORIES_VALUE = "festival";

    public static URL generateURL(String userId) {
        Uri builtUri = Uri.parse(BASE_URL + EVENT_LIST)
                .buildUpon()
                .appendQueryParameter(PARAM_FIELDS_KEY, PARAM_FIELDS_VALUE)
                .appendQueryParameter(PARAM_LOCATION_KEY, PARAM_LOCATION_VALUE)
                .appendQueryParameter(PARAM_ACTUAL_SINCE_KEY, PARAM_ACTUAL_SINCE_VALUE)
                .appendQueryParameter(PARAM_CATEGORIES_KEY, PARAM_CATEGORIES_VALUE)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasNext = scanner.hasNext();

            if (hasNext) {
                return scanner.next();
            }
            else {
                return null;
            }
        }
        finally {
            urlConnection.disconnect();
        }

    }
}
