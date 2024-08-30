package com.RoVoT.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

      private Button btnAllBooks, btnCurrentlyReading, btnReadBooks, btnWishlist, btnFavBooks , btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


     initView();
     btnAllBooks.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
             startActivity(intent);
         }
     });

    }

    private void initView() {
        btnAllBooks=findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnReadBooks= findViewById(R.id.btnReadBooks);
        btnWishlist = findViewById(R.id.btnWishlist);
        btnFavBooks = findViewById(R.id.btnFavBooks);
        btnAbout = findViewById(R.id.btnAbout);

    }
}