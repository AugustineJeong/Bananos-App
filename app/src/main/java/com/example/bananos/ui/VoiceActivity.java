package com.example.bananos.ui;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bananos.R;
import com.example.bananos.data.ListOfItems;
import com.example.bananos.model.GroceryItem;
import com.example.bananos.model.ItemList;
import com.example.bananos.stringParser.FindCheapestTotalPriceStore;
import com.example.bananos.stringParser.StoreFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class VoiceActivity extends AppCompatActivity {
    ImageButton voiceButton;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private ArrayList<String> inputItems = new ArrayList<>();
    private ListOfItems listOfItems;
    private String cheapestStore;
    private List<GroceryItem> groceryItemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        voiceButton = findViewById(R.id.voiceIcon);

        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    public void clickListPage(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putStringArrayListExtra("key", inputItems);
        intent.putExtra("key2", cheapestStore);

        startActivity(intent);
    }

    public void speak() {
        //intent to show speech to text dialog
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something");

        // start intent
        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && null != data) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                assert result != null;
                Toast toast = Toast.makeText(this, result.get(0), Toast.LENGTH_SHORT);
                toast.show();

                String voiceInput = result.get(0);
                ItemList dataBase = new ItemList();
                StoreFinder storeFinder = new StoreFinder(dataBase.getList(), voiceInput);

                listOfItems = new ListOfItems();
                listOfItems.getGroceryItemList().addAll(storeFinder.findAllMatching());

                groceryItemList.addAll(listOfItems.getGroceryItemList());

                List<GroceryItem> temporaryGroceryItemList = listOfItems.getGroceryItemList();

                for (GroceryItem groceryItem : temporaryGroceryItemList) {
                        inputItems.add(groceryItem.about());
                }

                FindCheapestTotalPriceStore findCheapestTotalPriceStore =
                        new FindCheapestTotalPriceStore(groceryItemList);

                cheapestStore = "The cheapest overall store is: \n"+
                        findCheapestTotalPriceStore.getCheapestStore();

            }
        }
    }
}
