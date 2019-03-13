package com.example.uploadcontentband;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {
    TokenManager tokenManager;
    WebView wvLogin;
    FileManager fileManager;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initInstance();
        btn.setOnClickListener(v -> {
            tokenManager.setAuthorizationCode();

            String resultHtml = tokenManager.getAuthorizationCode();
            fileManager.writeFile(resultHtml);
            Log.d("파일 만들고싶다", resultHtml);

            Log.d("파일 연다", fileManager.readFile());
        });



//        wvLogin.loadUrl("file:///" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/openBand/aa.html");

//        tokenManager.getToken();

    }

    private void initView() {
        wvLogin = findViewById(R.id.wvLogin);
        btn = findViewById(R.id.btn);
    }

    private void initInstance() {
        tokenManager = new TokenManager();
        fileManager = new FileManager("aa");
    }
}
