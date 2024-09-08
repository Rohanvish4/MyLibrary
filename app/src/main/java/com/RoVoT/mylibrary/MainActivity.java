package com.RoVoT.mylibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks, btnCurrentlyReading, btnReadBooks, btnWishlist, btnFavBooks, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);





        initView();
        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);

            }
        });

        btnReadBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AlreadyReadBooksActivity.class);
                startActivity(intent);

            }
        });


        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WantToReadBookActivity.class);
                startActivity(intent);

            }
        });

        btnFavBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteBooksActivity.class);
                startActivity(intent);

            }
        });
        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentlyReadingBooksActivity.class);
                startActivity(intent);


            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));

                builder.setMessage("Designed And Developed with Love By Rohan \n" +
                        "Click"+ " GET REPO " +"for the Source Code of this Application");

                builder.setPositiveButton("Get Repo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();


            }
        });



        Utils.getInstance(this);
    }

    private void initView() {
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnReadBooks = findViewById(R.id.btnReadBooks);
        btnWishlist = findViewById(R.id.btnWishlist);
        btnFavBooks = findViewById(R.id.btnFavBooks);
        btnAbout = findViewById(R.id.btnAbout);

    }
}