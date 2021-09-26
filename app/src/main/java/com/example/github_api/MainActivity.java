package com.example.github_api;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.github_api.library.MyHttpRequest;
import com.example.github_api.library.MyResponse;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText input;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        input = findViewById(R.id.inputText);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getText().toString() != "") {
                    new AsyncCaller().execute();
                }
                else {
                    Toast.makeText(
                            MainActivity.this, "Please select image",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
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

                myResponse = MyHttpRequest.search_repo(input.getText().toString());

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
                    textView.setText(myResponse.getData());
                } else {
                    textView.setText("Error code: " + myResponse.getCode() + "\nMessage: " + myResponse.getData());
                }
            }else{
                textView.setText("Network error");
            }

            pdLoading.dismiss();
        }

    }
}