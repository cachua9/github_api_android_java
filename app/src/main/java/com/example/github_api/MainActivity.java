package com.example.github_api;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.github_api.library.MyItem;
import com.example.github_api.library.MyHttpRequest;
import com.example.github_api.library.MyResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String input;

    private SearchView searchView;
    private ListView listView;
    private ArrayAdapter adapter;

    private ArrayList<MyItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Search");

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.listView);

        searchView.setQueryHint("GitHub のリポジトリを検索できるよー");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                input = query;
                new AsyncCaller().execute();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(adapter != null) {
                    adapter.getFilter().filter(newText);
                    if(newText.equals("")){
                        adapter.clear();
                    }
                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                open_detail(items.get(position));
            }
        });
    }

    private void open_detail(MyItem item){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("item", (Parcelable) item);
        startActivity(intent);
    }

    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        MyResponse myResponse;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.show();
        }
        @Override
        protected Void doInBackground(Void... params) {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here

            try{

                myResponse = MyHttpRequest.search_repo(input, 30);

            }catch (Exception e){

                Log.d("doInBackground", "Loi request");

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //this method will be running on UI thread

            if(myResponse != null) {
                if (myResponse.getCode() == 200) {
                    show_result(myResponse.getData());
                } else {
                    Toast.makeText(
                            MainActivity.this, "Error code: " + myResponse.getCode() + "\nMessage: " + myResponse.getData(),
                            Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(
                        MainActivity.this, "Network error",
                        Toast.LENGTH_SHORT).show();
            }

            pdLoading.dismiss();
        }
    }

    private void show_result(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray json_items = jsonObject.getJSONArray("items");

            items = new ArrayList<MyItem>();

            for(int i = 0; i < json_items.length(); i++){
                JSONObject json_item = json_items.getJSONObject(i);

                String name = json_item.optString("full_name");
                String ownerIconUrl = json_item.optJSONObject("owner").optString("avatar_url");
                String language = json_item.optString("language");
                Long stargazersCount = json_item.optLong("stargazers_count");
                Long watchersCount = json_item.optLong("watchers_count");
                Long forksCount = json_item.optLong("forks_count");
                Long openIssuesCount = json_item.optLong("open_issues_count");

                MyItem item = new MyItem(name, ownerIconUrl, language, stargazersCount, watchersCount, forksCount, openIssuesCount);
                items.add(item);
            }

            Log.d("Items size", String.valueOf(items.size()));

            if(items != null) {
                ArrayList<String> list = new ArrayList<String>();
                for (int i = 0; i < items.size(); i++) {
                    list.add(items.get(i).getName());
                }
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter);
            }

        } catch (Exception e){

        }
    }
}