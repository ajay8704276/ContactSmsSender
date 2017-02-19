package com.app.kisan.network.demo.Rest;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by adyro on 18-02-2017.
 */

public class DownloadJsonResponse {
    private Context mContext;

    public DownloadJsonResponse(Context mContext) {
        this.mContext = mContext;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("contact_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
