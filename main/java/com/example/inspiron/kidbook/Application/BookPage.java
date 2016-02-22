package com.example.inspiron.kidbook.Application;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inspiron.kidbook.Adapter.PageListAdapter;
import com.example.inspiron.kidbook.MainMenu.IMenu;
import com.example.inspiron.kidbook.Manager.PageManager;
import com.example.inspiron.kidbook.R;
import com.example.inspiron.kidbook.StateStack.State;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by INSPIRON on 6/28/2015.
 */
public class BookPage implements State,IMenu {
    PageManager mManager;
    Activity mActivity;

    int pageNumber;
    int pageIcon;
    String firstLine;
    String content;
    String backgroundName;
    String pageIconName;
    boolean inStack = false;
    boolean running;

    Typeface fontType;
    public static Bitmap pageBackground;
    static RelativeLayout pageLayout = null;
    Thread pageThread;

    public BookPage(String pageFile,PageManager mManager, Activity activity) {
        this.mManager = mManager;
        this.mActivity = activity;
        firstLine = content;
        fontType = Typeface.createFromAsset(mActivity.getAssets(), "stealingkissesinthemoonlight.ttf");
        readFile(pageFile);

    }

    private void readFile(String pageFile) {
        InputStream file ;
        try {
            file = this.mActivity.getAssets().open(pageFile);
        }catch (Exception e){
            Toast.makeText(mActivity, "Error open file:" + pageFile , Toast.LENGTH_LONG).show();
            return;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(file));
        try {
            this.backgroundName = buffer.readLine();
            this.pageIconName = buffer.readLine();
            this.pageNumber = Integer.valueOf(buffer.readLine());

            StringBuilder text = new StringBuilder();
            String line;
            while ((line =  buffer.readLine()) != null){
                text.append(line);
                text.append('\n');
            }
            this.content = text.toString();
            this.firstLine = this.content.substring(0, 50);
            buffer.close();
        }catch (Exception e){
            Toast.makeText(mActivity, "Error reading buffer", Toast.LENGTH_LONG).show();
            return;
        }

    }
    public int getPageNumber() {
        return pageNumber;
    }
    public void initialize(final int pNum){

        createPageLayout(mManager, mActivity,pNum);
    }
    public void reset(){
        pageBackground = null;
        pageLayout = null;
    }
    private void createPageLayout(final PageManager mManager, Activity mainActivity, final int pNum) {
        if (pageLayout == null){
            pageLayout = new RelativeLayout(mainActivity);
            MainActivity.BUTTONS_LAYOUT.addView(BookPage.pageLayout);
            View view = mainActivity.getLayoutInflater().inflate(R.layout.page, pageLayout, false);
            pageLayout.addView(view);

            Button next = (Button)BookPage.pageLayout.findViewById(R.id.next);
            next.setTypeface(this.fontType);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    BookPage temp = mManager.nextPage(pNum);
                    if ( temp != null)
                        mManager.push(temp);
                }
            });

            int backgroundID = mActivity.getResources().getIdentifier(this.backgroundName, "drawable", mActivity.getPackageName());

            pageBackground =  BitmapFactory.decodeResource(mActivity.getResources(), backgroundID);
            this.hideContent();
        }

    }

    public String getFirstLine() {
        return firstLine;
    }
    public int getPageIcon() {
        return pageIcon;
    }

    @Override
    public void onPushed() {
        this.inStack = true;
        final int pNum = this.pageNumber;
        initialize(pNum);
        GameView.bg.setImage(pageBackground);
        showContent();
    }

    @Override
    public void onPop() {
        this.inStack = false;
        hideContent();
    }

    @Override
    public void onReturnTop() {
        showContent();
    }

    @Override
    public void onBeingPressed() {
        hideContent();
    }

    @Override
    public void showContent() {
        TextView content = (TextView) BookPage.pageLayout.findViewById(R.id.content);
        content.setText(this.content);
        content.setTypeface(this.fontType);
        TextView pagenumber =  (TextView)BookPage.pageLayout.findViewById(R.id.pagenumber);
        pagenumber.setText("Page: " + String.valueOf(this.pageNumber + 1));
        pagenumber.setTypeface(this.fontType);
        pageLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContent() {
        pageLayout.setVisibility(View.INVISIBLE);
    }

    public boolean getInStack() {
        return inStack;
    }

    public void setManager(PageManager manager) {
        this.mManager = manager;
    }
}
