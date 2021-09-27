package com.example.github_api;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.github_api.library.MyHttpRequest;
import com.example.github_api.library.MyItem;
import com.example.github_api.library.MyResponse;

import java.io.File;
import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameView;
    private TextView languageView;
    private TextView infoView;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("Detail");

        imageView = (ImageView) findViewById(R.id.imageView);
        nameView = (TextView) findViewById(R.id.nameView);
        languageView = (TextView) findViewById(R.id.languageView);
        infoView = (TextView) findViewById(R.id.infoView);

        MyItem item = getIntent().getParcelableExtra("item");
        if(item != null){
            new DetailActivity.AsyncCaller().execute(item.getOwnerIconUrl());
            nameView.setText(item.getName());
            languageView.setText("Written by " + item.getLanguage());
            infoView.setText(item.getStargazersCount() + " stars\n"
                    + item.getWatchersCount() + " watchers\n"
                    + item.getForksCount() + " forks\n"
                    + item.getOpenIssuesCount() + " open issues"
            );
        }
    }

    private Bitmap download_image(String url){
        Bitmap bitmap = null;

        try {
            InputStream input = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private class AsyncCaller extends AsyncTask<String, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(String... params) {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here

            bitmap = download_image(params[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //this method will be running on UI thread
            if(bitmap != null){
                imageView.setImageBitmap(bitmap);
            }

        }
    }
}