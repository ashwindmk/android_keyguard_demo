package com.ashwin.android.keyguarddemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int CODE_AUTHENTICATION_VERIFICATION = 256;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        securityCheck();
    }

    private void securityCheck() {
        final String title = "Authentication required";
        final String description = "Unlock device";
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            if (keyguardManager.isKeyguardSecure()) {
                // Deprecated since API 29, use Biometric library (androidx.biometric:biometric:1.0.1) instead.
                Intent intent = keyguardManager.createConfirmDeviceCredentialIntent(title, description);
                startActivityForResult(intent, CODE_AUTHENTICATION_VERIFICATION);
            } else {
                Toast.makeText(this, "No any security setup done by user", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Android version must be above Lollipop (21)", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_AUTHENTICATION_VERIFICATION && resultCode == RESULT_OK) {
            gotoPersonalActivity();
        } else {
            Toast.makeText(this, "Unable to verify user", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void gotoPersonalActivity() {
        Intent intent = new Intent(this, PersonalActivity.class);
        startActivity(intent);
        finish();
    }
}
