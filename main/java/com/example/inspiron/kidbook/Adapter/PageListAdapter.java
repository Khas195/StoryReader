package com.example.inspiron.kidbook.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inspiron.kidbook.Application.BookPage;
import com.example.inspiron.kidbook.R;

import java.util.List;

/**
 * Created by INSPIRON on 6/28/2015.
 */
public class PageListAdapter extends ArrayAdapter<BookPage> {
    Activity mainActivity;
    List<BookPage> myPages;
    public PageListAdapter(Activity activity, List<BookPage> myPages){
        super(activity, R.layout.pageview, myPages);
        mainActivity = activity;
        this.myPages = myPages;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View pageView = convertView;
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        if (pageView == null){
            pageView = inflater.inflate(R.layout.pageview, parent, false);
        }
        BookPage currentPage = myPages.get(position);

        ImageView imageView = (ImageView) pageView.findViewById(R.id.pageIcon);
        imageView.setImageResource(currentPage.getPageIcon());

        TextView pageNumber = (TextView) pageView.findViewById(R.id.pageNumber);
        pageNumber.setText("Page: " + String.valueOf(currentPage.getPageNumber() + 1));

        TextView firstLine = (TextView) pageView.findViewById(R.id.firstLine);
        firstLine.setText(currentPage.getFirstLine());
        return pageView;
    }
}
