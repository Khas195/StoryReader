package com.example.inspiron.kidbook.Manager;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.inspiron.kidbook.Adapter.BookListAdapter;
import com.example.inspiron.kidbook.Application.GameDirector;
import com.example.inspiron.kidbook.Application.MainActivity;
import com.example.inspiron.kidbook.Application.Book;
import com.example.inspiron.kidbook.R;
import com.example.inspiron.kidbook.StateStack.StateStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by INSPIRON on 6/28/2015.
 */
public class BookManager {
    StateStack bookStack;
    List<Book> booksList;
    Activity mainActivity;
    ListView booksListView;
    int numberOfBooks;

    public BookManager( Activity activity) {
        bookStack = new StateStack();
        booksList = new ArrayList<>();
        mainActivity = activity;

        this.init();
    }
    private void init() {
        populateBookList();
        populateListView();
        MainActivity.BUTTONS_LAYOUT.addView(booksListView);
    }
    private void populateBookList() {
        Scanner scan = null;

        try{
            scan = new Scanner(mainActivity.getResources().openRawResource(R.raw.booklistfile));
        }catch (Exception e){
            System.exit(1);
        }

        numberOfBooks = scan.nextInt();
        scan.nextLine();
        for ( int i = 0; i < numberOfBooks; i++){
            String bookTitle = scan.nextLine();
            String author = scan.nextLine();
            String publish = scan.nextLine();
            String iconName = scan.nextLine();
            String backgroundName = scan.nextLine();
            String fileName = scan.nextLine();

            booksList.add(new Book(iconName, backgroundName, author, publish, bookTitle,fileName,mainActivity));
        }
        scan.close();
    }
    private void populateListView() {
        ArrayAdapter<Book> adapter = new BookListAdapter(mainActivity, booksList);
        booksListView = new ListView(mainActivity);
        booksListView.setAdapter(adapter);
        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                GameDirector.getInstance().push(booksList.get(position));
            }
        });
    }
    public void showContent() {
        this.booksListView.setVisibility(View.VISIBLE);
    }

    public void hideContent() {
        this.booksListView.setVisibility(View.INVISIBLE);
    }

}
