package com.example.gyh.fastindexview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FastIndexView mFastIndexView = findViewById(R.id.fiv);
        mTextView = findViewById(R.id.tv);
        mFastIndexView.setOnSelectListener(new FastIndexView.OnSelectListener() {
            @Override
            public void onSelect(String textValue) {
                mTextView.setText(textValue);
            }
        });
    }
}
