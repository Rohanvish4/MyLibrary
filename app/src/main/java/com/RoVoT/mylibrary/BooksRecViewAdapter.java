package com.RoVoT.mylibrary;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class BooksRecViewAdapter extends RecyclerView.Adapter<BooksRecViewAdapter.ViewHolder> {

    private static final String TAG = "BooksRecViewAdapter: started";

    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;

    public BooksRecViewAdapter(Context mContext) {
        this.mContext = mContext;
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
                .into(holder.imgBook);

        holder.txtAuther.setText(books.get(position).getAuther());
        holder.txtDescription.setText(books.get(position).getAuther());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, books.get(position).getName() + " Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, BookActivity.class);
                mContext.startActivity(intent);

            }
        });


        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.btnDown.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.btnDown.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

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
        private TextView txtAuther, txtDescription;

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
