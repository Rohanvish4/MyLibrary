package com.RoVoT.mylibrary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CurrentlyReadingBooksActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CurrentlyReadingBooksActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_currently_reading_books);

        TextView txtEmpty = findViewById(R.id.txtEmpty);
        if (Utils.getCurrentlyReadingBooks() == null || Utils.getCurrentlyReadingBooks().isEmpty()) {
            txtEmpty.setVisibility(View.VISIBLE);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Consider using a static inner class or top-level class for the listener
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//

        RecyclerView recyclerView2 = findViewById(R.id.bookRecVieww);
        BooksRecViewAdapter adapter2 = new BooksRecViewAdapter(this, "currentlyReading");
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        // Consider fetching the list in a background thread or using lazy initialization
        adapter2.setBooks(Utils.getCurrentlyReadingBooks());
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}