package com.example.task6d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class Paul extends AppCompatActivity {
    private static final String PAYPAL_CLIENT_ID = "AfZtQU_ss8Jcnd-B3w_gAoej4ZYtanxPdbCeUp1d5EoBngLTKFzyz1MZBmeq_qWiqFXCbtSyJntfEh3P";
    private static final int PAYPAL_REQUEST_CODE = 123;

    private Button pay;
    private PayPalConfiguration paypalConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paul);

        pay = findViewById(R.id.button5);
        paypalConfig = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(PAYPAL_CLIENT_ID);

        Intent paypalServiceIntent = new Intent(this, PayPalService.class);
        paypalServiceIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
        startService(paypalServiceIntent);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePayPalPayment();
            }
        });
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    private void makePayPalPayment() {
        PayPalPayment payment = new PayPalPayment(new BigDecimal("10.00"), "USD", "Payment description",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    String paymentId = confirmation.getProofOfPayment().getPaymentId();
                    // Process the payment
                    processPayment(paymentId);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // Payment cancelled by the user
                Toast.makeText(this, "Payment cancelled", Toast.LENGTH_SHORT).show();
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                // Invalid payment or configuration
                Toast.makeText(this, "Invalid payment or configuration", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void processPayment(String paymentId) {
        // Process the payment with the received payment ID
        Toast.makeText(this, "Payment successful. Payment ID: " + paymentId, Toast.LENGTH_SHORT).show();
    }
}
