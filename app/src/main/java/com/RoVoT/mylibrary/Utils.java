package com.RoVoT.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";

    private static Utils instance;
    private static SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (null == getAllBooks()) {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getWantToReadBooks()) {
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getFavoriteBooks()) {
            editor.putString(FAVORITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {
        //TODO: add initial data

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
                "A work of maddening brilliance", "Long Description"));
        books.add(new Book(2, "The Myth of Sisyphus", "Albert Camus", 250, "https://miro.medium.com/max/500/1*DDsOx6D3oe8ZxcA-OTfIDA.jpeg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought.",
                "Long Description"));



        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();
    }

    public static Utils getInstance(Context context) {
        if (null != instance) {
            return instance;
        }else {
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
        return books;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
        return books;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return books;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS, null), type);
        return books;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }

        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean addToWantToRead (Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean addToFavorite (Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean removeFromWantToRead (Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean removeFromCurrentlyReading (Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean removeFromFavorites (Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
