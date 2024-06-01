package com.example.hackerton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private int textZoom = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 초기 로그인 프래그먼트를 로드합니다.
        if (savedInstanceState == null) {
            loadFragment(new Frag0_Login());
        }

        Button buttonZoomIn = findViewById(R.id.button_zoom_in);
        Button buttonZoomOut = findViewById(R.id.button_zoom_out);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        buttonZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textZoom < 200) {
                    textZoom += 10;
                    if (webView != null) {
                        webView.getSettings().setTextZoom(textZoom);
                    }
                }
            }
        });

        buttonZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textZoom > 50) {
                    textZoom -= 10;
                    if (webView != null) {
                        webView.getSettings().setTextZoom(textZoom);
                    }
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Fragment1());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Fragment2());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Fragment3());
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

        // 웹뷰 인스턴스 업데이트
        transaction.runOnCommit(new Runnable() {
            @Override
            public void run() {
                if (fragment instanceof Fragment1) {
                    webView = ((Fragment1) fragment).getWebView();
                } else if (fragment instanceof Fragment2) {
                    webView = ((Fragment2) fragment).getWebView();
                } else if (fragment instanceof Fragment3) {
                    webView = ((Fragment3) fragment).getWebView();
                }

                if (webView != null) {
                    webView.getSettings().setTextZoom(textZoom);
                }
            }
        });
    }
}
