package com.master.custombroadcastreceiver;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = (TextView) findViewById(R.id.txtCrono);
        new Chrono().execute("","");

    }

    private class Chrono extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i < 30; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
                publishProgress(i);

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Integer... values) {
            number.setText(String.valueOf(values[0]));
            if(values[0]==15){
                Intent intent = new Intent();
                intent.setAction("com.master.CUSTOM_EVENT");
                sendBroadcast(intent);
            }
        }


    }
}
