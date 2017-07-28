package com.code4a.rectloading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.code4a.rectloadingview.RectLoadingView;

public class MainActivity extends AppCompatActivity {

    RectLoadingView rectLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rectLoadingView = (RectLoadingView) findViewById(R.id.avi);
    }

    public void hideClick(View view) {
        rectLoadingView.hide();
        // or rectLoadingView.smoothToHide();
    }

    public void showClick(View view) {
        rectLoadingView.show();
        // or rectLoadingView.smoothToShow();
    }
}
