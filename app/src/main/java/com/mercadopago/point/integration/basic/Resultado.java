package com.mercadopago.point.integration.basic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercadopago.point.integration.R;

public class Resultado extends AppCompatActivity {

    TextView installments;
    TextView amount;
    TextView ccType;
    TextView paymentId;
    TextView error;
    TextView errorDetail;
    ImageView image;

    public final static String RESULT_PAYMENT_ID = "paymentId";

    public final static String RESULT_STATUS_OK = "OK";
    public final static String RESULT_STATUS_FAILED = "FAILED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        image = (ImageView) findViewById(R.id.icon);
        paymentId = (TextView) findViewById(R.id.payment_id);
        installments = (TextView) findViewById(R.id.installments);
        amount = (TextView) findViewById(R.id.amount);
        ccType = (TextView) findViewById(R.id.cc_type);
        error = (TextView) findViewById(R.id.error);
        errorDetail = (TextView) findViewById(R.id.error_detail);



        Intent launcherIntent = getIntent();
        Bundle data = launcherIntent.getExtras();
        if (data != null) {
            String result = data.getString(BundleCodes.RESULT_STATUS);
            setStatus(result);
            paymentId.setText(String.valueOf(data.getLong(RESULT_PAYMENT_ID)));
            installments.setText(String.valueOf(data.getInt(BundleCodes.INSTALLMENTS)));
            amount.setText(String.valueOf(data.getDouble(BundleCodes.AMOUNT)));
            ccType.setText(data.getString(BundleCodes.CARD_TYPE));
            error.setText(data.getString(BundleCodes.ERROR));
            errorDetail.setText(data.getString(BundleCodes.ERROR_DETAIL));

        }

        Uri uri = launcherIntent.getData();
        if (uri != null) {
            String result = uri.getQueryParameter(BundleCodes.RESULT_STATUS);
            setStatus(result);
            paymentId.setText(uri.getQueryParameter(RESULT_PAYMENT_ID));
            installments.setText(uri.getQueryParameter(BundleCodes.INSTALLMENTS));
            amount.setText(uri.getQueryParameter(BundleCodes.AMOUNT));
            ccType.setText(uri.getQueryParameter(BundleCodes.CARD_TYPE));
            error.setText(uri.getQueryParameter(BundleCodes.ERROR));
            errorDetail.setText(uri.getQueryParameter(BundleCodes.ERROR_DETAIL));
        }

    }

    private void setStatus(String status) {
        if (RESULT_STATUS_OK.equals(status)) {
            if (android.os.Build.VERSION.SDK_INT >= 22) {
                image.setImageDrawable(getDrawable(R.drawable.ok));
            } else {
                image.setImageDrawable(getResources().getDrawable(R.drawable.ok));
            }
        }
        if (RESULT_STATUS_FAILED.equals(status)) {
            if (android.os.Build.VERSION.SDK_INT >= 22) {
                image.setImageDrawable(getDrawable(R.drawable.fail));
            } else {
                image.setImageDrawable(getResources().getDrawable(R.drawable.fail));
            }
        }
    }
}
