package com.RoVoT.mylibrary;

import static com.RoVoT.mylibrary.BookActivity.BOOK_ID_KEY;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BooksRecViewAdapter extends RecyclerView.Adapter<BooksRecViewAdapter.ViewHolder> {

    private static final String TAG = "BooksRecViewAdapter: started";
//    private static final String TAG = "BookRecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();
    private final Context mContext;
    private final String parentActivity;

//    public BooksRecViewAdapter(Context mContext) {
//        this.mContext = mContext;
//    }


    public BooksRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtName.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImgUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgBook);


        holder.parent.setOnClickListener(v -> {

            Intent intent = new Intent(mContext, BookActivity.class);
            intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
            mContext.startActivity(intent);


        });
        holder.txtAuther.setText(books.get(position).getAuther());
        holder.txtDescription.setText(books.get(position).getShortDesc());


        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.btnDown.setVisibility(View.GONE);

            switch (parentActivity) {
                case "allBooks":
                    holder.btnDelete.setVisibility(View.GONE);
                    break;
                case "alreadyRead":
                    holder.btnDelete.setVisibility(View.VISIBLE);
                    holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                            builder.setPositiveButton("Yes", (dialog, which) -> {
                                if (Utils.getInstance(mContext).removeFromAlreadyRead(books.get(position))) {
                                    Toast.makeText(mContext, "Book Removed, Please reopen this Section", Toast.LENGTH_SHORT).show();
                                    notifyItemRemoved(position);
                                }
                            });
                            builder.setNegativeButton("No", (dialog, which) -> Toast.makeText(mContext, "Task Aborted", Toast.LENGTH_SHORT).show());

                            builder.create().show();

                        }
                    });
                    break;
                case "wantToRead":
                    holder.btnDelete.setVisibility(View.VISIBLE);
                    holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                            builder.setPositiveButton("Yes", (dialog, which) -> {
                                if (Utils.getInstance(mContext).removeFromWantToRead(books.get(position))) {
                                    Toast.makeText(mContext, "Book Removed, Please reopen this Section", Toast.LENGTH_SHORT).show();
                                    notifyItemRemoved(position);
                                }
                            });
                            builder.setNegativeButton("No", (dialog, which) -> Toast.makeText(mContext, "Task Aborted", Toast.LENGTH_SHORT).show());

                            builder.create().show();

                        }
                    });


                    break;
                case "currentlyReading":
                    holder.btnDelete.setVisibility(View.VISIBLE);
                    holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                            builder.setPositiveButton("Yes", (dialog, which) -> {
                                if (Utils.getInstance(mContext).removeFromCurrentlyReading(books.get(position))) {
                                    Toast.makeText(mContext, "Book Removed, Please reopen this Section", Toast.LENGTH_SHORT).show();
                                    notifyItemRemoved(position);
                                }
                            });
                            builder.setNegativeButton("No", (dialog, which) -> Toast.makeText(mContext, "Task Aborted", Toast.LENGTH_SHORT).show());

                            builder.create().show();


                        }
                    });

                    break;
                default:
                    holder.btnDelete.setVisibility(View.VISIBLE);
                    holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                            builder.setPositiveButton("Yes", (dialog, which) -> {
                                if (Utils.getInstance(mContext).removeFromFavorites(books.get(position))) {
                                    Toast.makeText(mContext, "Book Removed, Please reopen this Section", Toast.LENGTH_SHORT).show();
                                    notifyItemRemoved(position);
                                }
                            });
                            builder.setNegativeButton("No", (dialog, which) -> Toast.makeText(mContext, "Task Aborted", Toast.LENGTH_SHORT).show());

                            builder.create().show();

                        }
                    });


                    break;
            }
        }


        else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.btnDown.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private ImageView imgBook;
        private TextView txtName;


        private ImageView btnDown, btnUp;
        private RelativeLayout expandedRelLayout;
        private TextView txtAuther, txtDescription, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtName);


            btnDown = itemView.findViewById(R.id.btnDown);
            btnUp = itemView.findViewById(R.id.btnUp);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            txtAuther = itemView.findViewById(R.id.txtAuther);
            txtDescription = itemView.findViewById(R.id.shortDesc);
            btnDelete = itemView.findViewById(R.id.btnDelete);


            btnDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());  //to get position of item in new class(ViewHolder)
                    /*book.setExpanded(!book.isExpanded());*/

                    // Expand the item if it’s collapsed
                    book.setExpanded(!book.isExpanded()); // Collapse the item if it’s expanded
                    notifyItemChanged(getAdapterPosition());


                }
            });
            btnUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
//                    book.setExpanded(!book.isExpanded());

                    if (book.isExpanded()) {
                        book.setExpanded(false); // Collapse the item if it’s expanded
                    } else {
                        book.setExpanded(true); // Expand the item if it’s collapsed
                    }

                    notifyItemChanged(getAdapterPosition());


                }
            });
            


        }
    }


}
