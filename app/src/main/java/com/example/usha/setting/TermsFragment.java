package com.example.usha.setting;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usha.R;

public class TermsFragment extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_terms_of_service);

        mWebView = findViewById(R.id.wvLayout);
        mWebView.loadUrl("https://www.headingwarm.com/");
    }
}
