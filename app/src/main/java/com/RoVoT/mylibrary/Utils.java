package com.RoVoT.mylibrary;

import java.util.ArrayList;

public class Utils {
    //Database class

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;

    public Utils() {
        if (null == allBooks) {
            allBooks = new ArrayList<>();
            initData();
        }
        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();
        }
        if (null == wantToReadBooks) {
            wantToReadBooks = new ArrayList<>();

        }
        if (null == currentlyReadingBooks) {
            currentlyReadingBooks = new ArrayList<>();
        }
        if (null == favoriteBooks) {
            favoriteBooks = new ArrayList<>();
        }

    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public static Utils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    private void initData() {

        allBooks.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
                "A work of maddening brilliance", "Long Description"));
        allBooks.add(new Book(2, "The Myth of Sisyphus", "Albert Camus", 250, "https://miro.medium.com/max/500/1*DDsOx6D3oe8ZxcA-OTfIDA.jpeg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought.",
                "Long Description"));
        allBooks.add(new Book(3, "Pride and Prejudice", "Jane Austen", 432, "https://images-na.ssl-images-amazon.com/images/I/51wScUt0gZL._SX331_BO1,204,203,200_.jpg",
                "A witty satire of social conventions in 19th-century England.", "Long Description"));
        allBooks.add(new Book(4, "To Kill a Mockingbird", "Harper Lee", 324, "https://cdn.britannica.com/21/182021-050-666DB6B1/book-cover-To-Kill-a-Mockingbird-many-1961.jpg",
                "A powerful story of racial injustice and the loss of innocence.", "Long Description"));
        allBooks.add(new Book(5, "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 224, "https://images-na.ssl-images-amazon.com/images/I/51xwGSNX-EL._SX329_BO1,204,203,200_.jpg",
                "A hilarious and absurd science fiction comedy.", "Long Description"));


        allBooks.add(new Book(8, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 309, "https://images-na.ssl-images-amazon.com/images/I/51UoqRAxwEL._SX331_BO1,204,203,200_.jpg",
                "The first book in the beloved Harry Potter series, introducing the world of Hogwarts and magic.",
                "On his eleventh birthday, Harry Potter discovers he's a wizard and is destined to attend Hogwarts School of Witchcraft and Wizardry. He learns about his past, faces magical challenges, and uncovers a dark secret lurking within the castle walls."));

        allBooks.add(new Book(9, "The Little Prince", "Antoine de Saint-Exupéry", 96, "https://images-na.ssl-images-amazon.com/images/I/51T8OXMiB5L._SX315_BO1,204,203,200_.jpg",
                "A poetic and philosophical tale about a pilot who meets a young prince from another planet.",
                "Stranded in the desert, a pilot encounters a young prince who has fallen to Earth from a tiny asteroid. Through their conversations, the Little Prince teaches the pilot about the importance of love, friendship, and the simple joys of life."));





        allBooks.add(new Book(14, "Gone Girl", "Gillian Flynn", 432,
                "https://geekritiqued.files.wordpress.com/2015/02/img_3144.jpg?w=1500&h=768&crop=1",
                "A suspenseful thriller exploring the dark side of a marriage and the power of manipulation.",
                "On their fifth wedding anniversary, Nick Dunne discovers his wife, Amy, has disappeared. As the police investigation unfolds, Nick becomes the prime suspect, and a twisted game of deception and manipulation ensues."));

        allBooks.add(new Book(15, "The Hunger Games", "Suzanne Collins", 374,
                "https://cdn.kobo.com/book-images/77f4eff7-2111-4ae4-87d1-18bd0ef88e61/1200/1200/False/the-ballad-of-songbirds-and-snakes-a-hunger-games-novel.jpg",
                "A dystopian young adult novel set in Panem, where children are forced to fight to the death.",
                "In a post-apocalyptic North America, teenagers are selected to participate in the Hunger Games, a televised event where they must fight to the death until only one survivor remains. Katniss Everdeen volunteers to take her sister's place, and her defiance sparks a rebellion."));




    }

    public Book getBookById(int id) {
        for (Book b : allBooks) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;

    }
    public boolean addToalreadyReadBooks(Book book) {
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book) {
        return wantToReadBooks.add(book);
    }
    public boolean addToFavoriteBooks(Book book){
        return favoriteBooks.add(book);
    }

    public boolean addToCurrentlyReading(Book book) {
        return currentlyReadingBooks.add(book);
    }

    public boolean removeFromAlreadyRead(Book book) {
        return alreadyReadBooks.remove(book);
    }

    public boolean removeFromWantToRead(Book book) {
        return wantToReadBooks.remove(book);
    }

    public boolean removeFromCurrentlyReading(Book book) {
        return currentlyReadingBooks.remove(book);
    }

    public boolean removeFromFavoriteBooks(Book book) {
        return favoriteBooks.remove(book);
    }
}
