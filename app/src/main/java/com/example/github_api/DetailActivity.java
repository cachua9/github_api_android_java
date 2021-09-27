package com.example.github_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.github_api.library.MyItem;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameView;
    private TextView languageView;
    private TextView infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = (ImageView) findViewById(R.id.imageView);
        nameView = (TextView) findViewById(R.id.nameView);
        languageView = (TextView) findViewById(R.id.languageView);
        infoView = (TextView) findViewById(R.id.infoView);


        MyItem item = getIntent().getParcelableExtra("item");
        if(item != null){
            nameView.setText(item.getName());
            languageView.setText("Written by " + item.getLanguage());
            infoView.setText(item.getStargazersCount() + " stars\n"
                    + item.getWatchersCount() + " watchers\n"
                    + item.getForksCount() + " forks\n"
                    + item.getOpenIssuesCount() + " open issues"
            );
        }
    }
}