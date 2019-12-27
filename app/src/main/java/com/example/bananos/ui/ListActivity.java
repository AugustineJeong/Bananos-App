package com.example.bananos.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bananos.R;
import com.example.bananos.data.ListOfItems;
import com.example.bananos.model.GroceryItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {
    Intent intent;
    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        textView = findViewById(R.id.viewList);

        textView.setMovementMethod(new ScrollingMovementMethod());

        intent = getIntent();
        List<String> itemList = getIntent().getStringArrayListExtra("key");
        assert itemList != null;
        if (!itemList.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("");
            for (String string : itemList) {
                stringBuilder = stringBuilder.append(string).append("\n");
            }

            textView.setText(stringBuilder.toString());


            textView2 = findViewById(R.id.textView);

            textView2.setText(getIntent().getStringExtra("key2"));

        }


    }

    public void clickVoicePage(View view) {
        Intent intent = new Intent(this, VoiceActivity.class);
        startActivity(intent);
    }
}
