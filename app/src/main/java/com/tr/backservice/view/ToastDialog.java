package com.tr.backservice.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.tr.backservice.R;

public class ToastDialog extends AlertDialog {

    private TextView tvMsg;
    private String mMsg = "";

    public ToastDialog(Context context) {
        super(context);
    }

    public ToastDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private void init(Context context) {
        mMsg = context.getString(R.string.toast_usb_not_exist);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_toast_util);

        //
        tvMsg = (TextView) findViewById(R.id.tv_msg);
        tvMsg.setText(mMsg);
    }

    public void setMsg(String msg) {
        mMsg = msg;
        if (tvMsg != null) {
            tvMsg.setText(msg);
        }
    }
}
