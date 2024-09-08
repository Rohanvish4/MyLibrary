package com.RoVoT.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class AlreadyReadBooksActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_already_read_books);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        TextView txtEmpty = findViewById(R.id.txtEmpty);
        if (Utils.getInstance(this).getAlreadyReadBooks() == null || Utils.getInstance(this).getAlreadyReadBooks().isEmpty()) {
            txtEmpty.setVisibility(View.VISIBLE);
        }
        RecyclerView recyclerView = findViewById(R.id.bookRecView);
        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this, "alreadyRead");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}