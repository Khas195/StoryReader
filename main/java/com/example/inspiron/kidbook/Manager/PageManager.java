package com.example.inspiron.kidbook.Manager;

import android.app.Activity;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inspiron.kidbook.Adapter.PageListAdapter;
import com.example.inspiron.kidbook.Application.GameDirector;
import com.example.inspiron.kidbook.Application.MainActivity;
import com.example.inspiron.kidbook.Application.Book;
import com.example.inspiron.kidbook.Application.BookPage;
import com.example.inspiron.kidbook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by INSPIRON on 6/28/2015.
 */
public class PageManager {
    Activity mainActivity;

    List<BookPage> pagesList;
    ListView pagesListView;
    Book book;

    public PageManager(Activity mainActivity, Book book, final String fileName) {
        this.mainActivity = mainActivity;
        this.book = book;

        this.pagesList = new ArrayList<>();
        populatePageList(fileName);
        populateListView();
        MainActivity.BUTTONS_LAYOUT.addView(pagesListView);
    }


    private void populatePageList(String fileName) {
        AssetManager tempManager = mainActivity.getAssets();

        String[] pageFileList = null;
        try {
            int i = 0;
            while (pageFileList == null && i <= 10) {
                pageFileList = tempManager.list(fileName);
                i++;
            }
        }catch ( Exception e){
            Toast.makeText(mainActivity, "Error open file:" + fileName , Toast.LENGTH_LONG).show();
            return;
        }
        for ( int i = 0; i < pageFileList.length; i++){
            if ( pageFileList[i] != "") {
                pagesList.add(new BookPage(fileName + "/" + pageFileList[i], this, mainActivity));
            }
        }
    }
    private void populateListView() {
        ArrayAdapter<BookPage> adapter = new PageListAdapter(mainActivity, this.pagesList);
        pagesListView = new ListView(mainActivity);
        pagesListView.setAdapter(adapter);
        pagesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mainActivity, "Press BACK to view previous Page", Toast.LENGTH_LONG).show();
                GameDirector.getInstance().push(pagesList.get(position));
            }
        });
    }
    public BookPage nextPage(int currentPage){
        if ( currentPage < pagesList.size()-1){
            return pagesList.get(++currentPage);
        }
        else return null;
    }
    public void push(BookPage bookPage) {
        if ( !bookPage.getInStack()) {
            GameDirector.getInstance().push(bookPage);
        }
    }

    public void hideList() {
        this.pagesListView.setVisibility(View.INVISIBLE);
    }

    public void showList() {
        this.pagesListView.setVisibility(View.VISIBLE);
    }

    public void destruct() {
        for ( int i = 0; i < this.pagesList.size(); i++){
            pagesList.get(i).setManager(null);
            pagesList.get(i).reset();
        }
        pagesListView = null;
        mainActivity = null;
        book = null;
    }
}
