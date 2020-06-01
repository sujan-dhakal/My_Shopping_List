package com.example.myshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText add_item;
    private ImageView add_button;
    private ListView list_view;
    private TextView date;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_item = findViewById(R.id.add_item);
        add_button = findViewById(R.id.add_button);
        list_view = findViewById(R.id.items_list);

        date = findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();
        String current_date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        date.setText(current_date);

        items = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, items);
        list_view.setAdapter(adapter);

        add_button.setOnClickListener(this);
        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                items.remove(position);
                adapter.notifyDataSetChanged();
            return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_button:
                String input = add_item.getText().toString();
                adapter.add(input);
                add_item.setText("");
                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}