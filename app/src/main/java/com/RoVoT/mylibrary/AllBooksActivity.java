package com.RoVoT.mylibrary;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BooksRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_books);


        booksRecView = findViewById(R.id.booksRecView);
        adapter = new BooksRecViewAdapter(this);

         booksRecView.setAdapter(adapter);
         booksRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> books = new ArrayList<Book>();

        books.add(new Book(1234, "Rohann", "RohanAuther", 328, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPGvORt0Fd08oil_91lM2L8XUDtJG4_scCDMmfzZE-q0GnwIr97-6vBmnYjf9oqbK4CUI&usqp=CAU",
                "short", "long Desc"));

        adapter.setBooks(books);




    }
}