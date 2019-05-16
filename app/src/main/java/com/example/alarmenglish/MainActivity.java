package com.example.alarmenglish;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

//import com.google.cloud.translate.Translate;
//import com.google.cloud.translate.TranslateOptions;
//import com.google.cloud.translate.Translation;

public class MainActivity extends AppCompatActivity {
    EditText txtRequest;
    TextView txtResult;
    Button btnokok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControl();
    }

    private void requesstPermission() {
        String permissions[] = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, 0);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this, new String[]{permissions[i]}, 0);
        }
    }

    private void initControl() {
        txtRequest = findViewById(R.id.txtRequest);
        txtResult = findViewById(R.id.txtResult);
        btnokok = findViewById(R.id.btnokok);
        btnokok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Translate translate = TranslateOptions.getDefaultInstance().getService();

                // The text to translate
                String text = txtRequest.getText() + "";

                // Translates some text into Russian
                Translation translation =
                        translate.translate(
                                text,
                                Translate.TranslateOption.sourceLanguage("en"),
                                Translate.TranslateOption.targetLanguage("ru"));
                Toast.makeText(MainActivity.this, translation.getTranslatedText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
