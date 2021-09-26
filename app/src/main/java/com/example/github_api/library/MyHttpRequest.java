package com.example.github_api.library;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyHttpRequest {
    private static final String root_url = "https://api.github.com/";

    @NotNull
    public static MyResponse search_repo(String repo) throws IOException {
        URL url = new URL(root_url + "search/repositories?q=" + repo);
        Log.d("URL", url.toString());

        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.setRequestProperty("accept", "application/vnd.github.v3+json");

        return new MyResponse(httpConn);
    }

}
