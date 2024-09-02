package com.RoVoT.mylibrary;

//import static com.RoVoT.mylibrary.BooksRecViewAdapter.BOOK_ID_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthorName, txtPages, txtDescription, txtViewLongDesc;

    private Button btnAddToFavorites;
    private Button btnAddToCurrentlyReading;
    private Button btnAddToAlreadyRead;
    private Button btnAddToWantToRead;
    private ImageView bookImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);


        initViews();


        /*
          Intent intent = getIntent();: This line retrieves the Intent that started the current Activity. An Intent is an object that carries information between Android components, such as Activities or Services.
          if (null != intent): This check ensures that an Intent was actually used to start the Activity. If the Intent is null, it means the Activity was started without any external data.
          int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);: This line attempts to extract an integer value associated with the key BOOK_ID_KEY from the Intent. If the key is not found, the default value -1 is returned. This suggests that the Intent is expected to contain an ID representing a book.
          if (bookId != -1): This condition checks if a valid book ID was found in the Intent. If the ID is -1, it means no book ID was passed.
          Book incomingBook = Utils.getInstance().getBookById(bookId);: Assuming a valid book ID was found, this line calls a method getBookById on a utility class (likely a singleton) to retrieve the corresponding Book object.
          if (null != incomingBook): This check verifies that a Book object was successfully retrieved using the ID.
          setData(incomingBook);: If a valid Book object was found, this line likely calls a method within the Activity to display or utilize the book data.

         */
        Intent intent = getIntent();

        if (null != intent) {


            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);

                    //To Handle all the buttons in BookActivity
                    handAddToCurrentlyReadingBooks(incomingBook);
                    handleAddToWantToReadBook(incomingBook);
                    handleAlreadyReadBook(incomingBook);
                    handleAddToFavorites(incomingBook);



                }
            }


        }


    }

    private void handAddToCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance().getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;
        for (Book b : currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;
                break;
            }
        }
        if(existInCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
            btnAddToAlreadyRead.setEnabled(false);
            btnAddToWantToRead.setEnabled(false);

        } else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToCurrentlyReading(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingBooksActivity.class);
                        startActivity(intent);


                    } else {
                        Toast.makeText(BookActivity.this, "something wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAddToFavorites(final Book book) {
        ArrayList<Book> favoriteBooks = Utils.getInstance().getFavoriteBooks();
        boolean existInFavoriteBooks = false;

        for (Book b : favoriteBooks) {
            if (b.getId() == book.getId()) {
                existInFavoriteBooks = true;
                break;
            }
        }
        if (existInFavoriteBooks) {
            btnAddToFavorites.setEnabled(false);

        } else {
            btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToFavoriteBooks(book)) {
                        Toast.makeText(BookActivity.this, "Book Addded", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(BookActivity.this, "something wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }



    }

    private void handleAlreadyReadBook(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
                break;
            }
        }
        if (existInAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
            btnAddToWantToRead.setEnabled(false);
            btnAddToCurrentlyReading.setEnabled(false);


        } else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToalreadyReadBooks(book)) {
                        Toast.makeText(BookActivity.this, "Book Addded", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBooksActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(BookActivity.this, "something wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }

    private void handleAddToWantToReadBook(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();
        boolean existInWishlist = false;

        for (Book b : wantToReadBooks) {
            if (b.getId() == book.getId()) {
                existInWishlist = true;
                break;
            }
        }
        if (existInWishlist) {
            btnAddToWantToRead.setEnabled(false);
            btnAddToCurrentlyReading.setEnabled(false);
            btnAddToAlreadyRead.setEnabled(false);
            btnAddToWantToRead.setText("Already Added");
        } else {
            btnAddToWantToRead.setOnClickListener(v -> {
                if (Utils.getInstance().addToWantToRead(book)) {
                    Toast.makeText(BookActivity.this, "Book Addded", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, WantToReadBookActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(BookActivity.this, "something wrong happened", Toast.LENGTH_SHORT).show();
                }
            });


        }


    }


    private void setData(@NonNull Book book) {
        txtBookName.setText(book.getName());
        txtAuthorName.setText(book.getAuther());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getShortDesc());
        txtViewLongDesc.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImgUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(bookImage);
    }

    private void initViews() {
        txtAuthorName = findViewById(R.id.txtAutherName);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);
        txtViewLongDesc = findViewById(R.id.textViewLongdesc);

        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);

        bookImage = findViewById(R.id.imgBook2);
    }
}