package com.example.atmfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Maker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maker);

        TextView message = findViewById(R.id.Txv_message);
        message.setText("此款APP可提供用戶快速地找到屏東市所有的ATM，利用相關操作，可導航、地圖查看、地圖縮放、指北針等功能，若是點擊bank的icon，也有該分行的資訊可閱覽，所有資料都是採用政府開放的OPEN DATA，再經過資料分析及比對，將早已關閉的商家及ATM剔除，" +
                "以免使用者跑到現場才發現早已不存在的機台或是地點。\n\n\n最後，希望各位使用者能盡情使用APP，若有任何意見歡迎告訴我們，我們將會迅速修正並改進，謝謝！");
    }
}
