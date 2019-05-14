package com.example.yourmeizi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    Button but;
    TextView responseText;
    RecyclerView recyclerView;

    private List<Meizi> meiziList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but = findViewById(R.id.add);
        responseText = findViewById(R.id.text);
        but.setOnClickListener(this);

        recyclerView = findViewById(R.id.recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MeiziAdapter adapter = new MeiziAdapter(meiziList);
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.add) {
            sendRequestWithOkhttp();
        }
    }

    private void sendRequestWithOkhttp() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.gracg.com/works/index/type/essence")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseHtml(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void parseHtml(String html) {
        Meizi mm = new Meizi();
        Document document = Jsoup.parse(html);
        Elements elements = document.select("img");
        for (Element element : elements) {
            String image = element.attr("src");
            Log.d(TAG, "parseHtml: " + image);
            mm.setImgId(image);
            meiziList.add(mm);
        }
    }
}
