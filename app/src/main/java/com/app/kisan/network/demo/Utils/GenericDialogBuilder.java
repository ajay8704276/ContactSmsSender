package com.app.kisan.network.demo.Utils;

/**
 * Created by adyro on 18-02-2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.app.kisan.network.demo.R;

/**
 * Created by Ajay Kumar on 7/17/2016.
 */

public class GenericDialogBuilder extends ProgressDialog {

    public static ProgressDialog dialog;

    public GenericDialogBuilder(Context mContext) {
        super(mContext);

    }

    public GenericDialogBuilder(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);
    }


    public static ProgressDialog buildDialog(Context mContext, String msg, boolean isCancelable) {

        dialog = new GenericDialogBuilder(mContext);
        dialog.setCancelable(isCancelable);
        dialog.setMessage(msg);
        return dialog;
    }

    public static ProgressDialog buildDialog(Context mContext, String msg, boolean isCancelable, boolean isDeterminate) {

        dialog = new GenericDialogBuilder(mContext, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
        dialog.setMessage(msg);
        dialog.setCancelable(isCancelable);
        dialog.setIndeterminate(isDeterminate);

        return dialog;
    }

    public static ProgressDialog builddefaultDialog(Context mContext, String msg, boolean isCancelable, boolean isDeterminate) {

        dialog = new ProgressDialog(mContext);
        dialog.setMessage(msg);
        dialog.setCancelable(isCancelable);
        dialog.setIndeterminate(isDeterminate);
        return dialog;
    }
}
