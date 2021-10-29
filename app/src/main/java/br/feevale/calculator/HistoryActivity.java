package br.feevale.calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    ListView listExpressions;
    ArrayList<String> expressions = new ArrayList<>();
    ExpressionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar != null) supportActionBar.hide();

        Intent it = getIntent();
        expressions = (ArrayList<String>) it.getSerializableExtra("listExp");

        listExpressions = findViewById(R.id.listExpressions);

        adapter = new ExpressionAdapter(expressions, this);

        adapter.notifyDataSetChanged();

        listExpressions.setAdapter(adapter);

    }

    public void back(View v) {
        finish();
    }
}