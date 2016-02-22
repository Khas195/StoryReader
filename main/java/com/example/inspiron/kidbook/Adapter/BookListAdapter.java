package com.example.inspiron.kidbook.Adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inspiron.kidbook.Application.Book;
import com.example.inspiron.kidbook.R;

import java.util.List;

/**
 * Created by INSPIRON on 6/28/2015.
 */
public class BookListAdapter extends ArrayAdapter<Book> {
    Activity mainActivity;
    List<Book> mBooks;
    public BookListAdapter(Activity activity, List<Book> myBooks){
        super(activity, R.layout.bookview, myBooks );
        mainActivity = activity;
        mBooks = myBooks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View bookView = convertView;
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        if (bookView == null){
            bookView = inflater.inflate(R.layout.bookview, parent, false);
        }

        Book currentBook = mBooks.get(position);

        Typeface fontType = Typeface.createFromAsset(mainActivity.getAssets(), "LimeGloryCaps.ttf");

        ImageView imageView = (ImageView)bookView.findViewById(R.id.imageView);
        imageView.setImageResource(currentBook.getBookIconID());

        TextView bookTitle = (TextView)bookView.findViewById(R.id.bookTitle);
        bookTitle.setText(currentBook.getBookTitle());
        bookTitle.setTypeface(fontType);

        TextView author = (TextView)bookView.findViewById(R.id.Author);
        author.setText(currentBook.getAuthorName());
        author.setTypeface(fontType);

        TextView publishedDate = (TextView)bookView.findViewById(R.id.PublishedDate);
        publishedDate.setText(currentBook.getPublishedDate());
        publishedDate.setTypeface(fontType);

        return bookView;
    }
}
