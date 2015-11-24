package com.crevelatedlove.asd.gson;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private Post post;
    public final static String POST_TITLE= "";
    public final static String POST_DATE= "";
    public final static String POST_URL= "";
    public final static String POST_AUTHOR= "";
    public final static String POST_THUMBNAIL= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        post = new Post(intent.getStringExtra(POST_URL), intent.getStringExtra(POST_TITLE),
                intent.getStringExtra(POST_DATE), intent.getStringExtra(POST_AUTHOR),
                intent.getStringExtra(POST_THUMBNAIL)                );

        TextView mURL = (TextView)findViewById(R.id.textView);
        TextView mDate = (TextView)findViewById(R.id.textView2);
        TextView mAuthor = (TextView)findViewById(R.id.textView3);
        TextView mThumbnail = (TextView)findViewById(R.id.textView4);
        mURL.setText(post.url);
        mDate.setText(post.title);
        mAuthor.setText(post.author);
        mThumbnail.setText(post.thumbnail);
    }

}

