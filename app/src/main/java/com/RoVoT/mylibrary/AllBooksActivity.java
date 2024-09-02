package com.RoVoT.mylibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_books);


        RecyclerView booksRecView = findViewById(R.id.booksRecView);
        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this, "allBooks");

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

//        ArrayList<Book> books = new ArrayList<Book>();
//
//        books.add(new Book(1234, "Rohann", "RohanAuther", 328, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPGvORt0Fd08oil_91lM2L8XUDtJG4_scCDMmfzZE-q0GnwIr97-6vBmnYjf9oqbK4CUI&usqp=CAU",
//                "short", "long Desc"));


        Utils.getInstance();
        adapter.setBooks(Utils.getAllBooks());


    }
}