package com.crevelatedlove.asd.gson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InsertPost extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextAdd;
    private final static String TAG = "APIXELA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_post);


        editTextName = (EditText) findViewById(R.id.editText);
        editTextAdd = (EditText) findViewById(R.id.editText2);
    }

    public void Insert(View view) {
        String name = editTextName.getText().toString();
        String add = editTextAdd.getText().toString();

        insertToDatabase(name,add);
    }

    private void insertToDatabase(final String name, final String add){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String POST_PARAMS = "name=" + params[0] + "&address=" + params[1];
                URL obj = null;
                HttpURLConnection con = null;
                try {
                    obj = new URL("http://www.apixela.net/android/insert-db.php");
                    con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("POST");

                    // For POST only - BEGIN
                    con.setDoOutput(true);
                    OutputStream os = con.getOutputStream();
                    os.write(POST_PARAMS.getBytes());
                    os.flush();
                    os.close();
                    // For POST only - END
                    int responseCode = con.getResponseCode();
                    Log.i(TAG, "POST Response Code :: " + responseCode);

                    if (responseCode == HttpURLConnection.HTTP_OK) { //success
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        // print result
                        Log.i(TAG, response.toString());
                        return ("success");
                    } else {
                        Log.i(TAG, "POST request did not work.");
                        return ("POST request did not work.");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return ("success");
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                TextView textViewResult = (TextView) findViewById(R.id.textView7);
                textViewResult.setText("Inserted");
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, add);
    }

}
