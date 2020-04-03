package id.sch.sman1garut.app.sman1garut;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import id.sch.sman1garut.app.sman1garut.SPreferenced.PrefManager;
import id.sch.sman1garut.app.sman1garut.adapter.KegiatanAdapter;
import id.sch.sman1garut.app.sman1garut.adapter.TugasAdapter;
import id.sch.sman1garut.app.sman1garut.api.client;
import id.sch.sman1garut.app.sman1garut.models.model_kegiatan.DataKegiatan;
import id.sch.sman1garut.app.sman1garut.models.model_tugas.DataTugas;

public class TugasAct extends AppCompatActivity {

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView RVTugas;
    private TugasAdapter mAdapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan);
        mContext=this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        getSupportActionBar().setTitle("List Tugas Hari Ini");
        //Make call to AsyncTask
        new AsyncFetch().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(TugasAct.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL(client.getBaseUrl() + "fitur/tugas");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    
                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            List<DataTugas> data = new ArrayList<>();

            pdLoading.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray dataArray = jsonObject.getJSONArray("data");
                String kelas = new PrefManager(mContext).getKelas();
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject json_data = dataArray.getJSONObject(i);
                    String target = json_data.getString("target");
                    if(target.equals(kelas)) {
                        DataTugas tugasData = new DataTugas();
                        tugasData.Id = json_data.getInt("id");
                        tugasData.Author = json_data.getString("author");
                        tugasData.Judul = json_data.getString("judul");
                        tugasData.Tanggal = json_data.getString("tanggal");
                        tugasData.Deskripsi = json_data.getString("deskripsi");
                        data.add(tugasData);
                    }
                }

                // Setup and Handover data to recyclerview
                RVTugas = (RecyclerView) findViewById(R.id.recycler);
                mAdapter = new TugasAdapter(TugasAct.this, data);
                RVTugas.setAdapter(mAdapter);
                RVTugas.setLayoutManager(new LinearLayoutManager(TugasAct.this));

            } catch (JSONException e) {
                Toast.makeText(TugasAct.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
