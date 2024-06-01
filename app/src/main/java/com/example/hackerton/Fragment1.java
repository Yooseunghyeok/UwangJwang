package com.example.hackerton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        webView = view.findViewById(R.id.webview1);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setTextZoom(100); // 기본 텍스트 줌 설정 (기본 값은 100)
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://jeongminr.github.io/");
        return view;
    }

    public WebView getWebView() {
        return webView;
    }
}
