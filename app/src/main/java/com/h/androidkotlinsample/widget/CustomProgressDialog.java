package com.h.androidkotlinsample.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.h.androidkotlinsample.R;

public class CustomProgressDialog extends Dialog {
    private final Context mContext;

    public CustomProgressDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_custom_progress);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

//        ImageView loadingView = findViewById(R.id.loading_image);
//        Glide.with(mContext).asGif().load(R.drawable.loading).into(loadingView);
    }

    public static CustomProgressDialog show(Activity activity) {
        CustomProgressDialog dialog = new CustomProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }

    @Override
    public void show() {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.show();
    }
}