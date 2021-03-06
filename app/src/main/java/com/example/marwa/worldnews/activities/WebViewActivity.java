package com.example.marwa.worldnews.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.marwa.worldnews.R;
import com.example.marwa.worldnews.utility.Utility;

public class WebViewActivity extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        final ProgressBar loadingIndicator = (ProgressBar) findViewById(R.id.indicator);

        Intent intent = getIntent();
        url = intent.getStringExtra("news");
        WebView web = (WebView) findViewById(R.id.webView);
        loadingIndicator.setVisibility(View.VISIBLE);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(url);
        
        web.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                // Hide Loading Indicator
                loadingIndicator.setVisibility(View.GONE);
            }
        });


    }

    /**
     * Initialize the contents of the Activity's options menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/share.xml file.
        getMenuInflater().inflate(R.menu.share, menu);
        return true;
    }

    /**
     * Setup the specific action that occurs when any of the items in the Options Menu are selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share) {
            Utility.shareNewsStory(this,url);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
