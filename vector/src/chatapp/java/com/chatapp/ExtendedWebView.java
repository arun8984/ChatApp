package com.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import im.vector.R;

public class ExtendedWebView extends AppCompatActivity implements AdvanceWebView.Listener{

    private String url = "";

    private AdvanceWebView webView;
    SharedPreferences settings;


    Activity activity;
    private ProgressDialog progDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception e) {
        }
        setUrl();
        activity = this;
        progDailog = ProgressDialog.show(activity, "Loading", "Please wait...", true);
        progDailog.setCancelable(false);
        webView = findViewById(R.id.webView);
        webView.setListener(this, this);
        webView.setGeolocationEnabled(false);
        webView.setMixedContentAllowed(true);
        webView.setCookiesEnabled(true);
        webView.setDesktopMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setThirdPartyCookiesEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

        });
        webView.addHttpHeader("X-Requested-With", "");
        webView.loadUrl(url);
    }

    private void setUrl() {
        Bundle b = getIntent().getExtras();
        url = b.getString("Bundle");
        if (url.equalsIgnoreCase("Why")) {
            url = "https://fnfcalls.com/";
            setTitle("Why FNF Call ?");
        } else if (url.equalsIgnoreCase("Credit")) {
            String userName = settings.getString("Username", "");
            String password = settings.getString("Password", "");
            url = "https://billing.fnfcalls.com/crm/customer/mobile_payment.php?pr_login=" + userName + "&pr_password=" + password + "&mobiledone=submit_log";
            setTitle("Buy Credit");
        } else if (url.equalsIgnoreCase("TopupA")) {
            String userName = settings.getString("Username", "");
            String password = settings.getString("Password", "");
            url = "https://billing.fnfcalls.com/crm/customer/billing_mobile_app.php?pr_login=" + userName + "&pr_password=" + password + "&mobiledone=submit_log";
            setTitle("Topup");
        } else if (url.equalsIgnoreCase("TopupB")) {
            String userName = settings.getString("Username", "");
            String password = settings.getString("Password", "");
            url = "http://sourcefabric.airtime.pro/api/live-info";
            setTitle("Topup");
        } else if (url.equalsIgnoreCase("data")) {
            String userName = settings.getString("Username", "");
            String password = settings.getString("Password", "");
            url = "https://billing.fnfcalls.com/crm/customer/billing_mobile_data_app.php?pr_login=" + userName + "&pr_password=" + password + "&mobiledone=submit_log";
            setTitle("Data");
        } else if (url.equalsIgnoreCase("electric")) {
            String userName = settings.getString("Username", "");
            String password = settings.getString("Password", "");
            url ="https://billing.fnfcalls.com/crm/customer/billing_electricity_app.php?pr_login=" + userName + "&pr_password=" + password + "&mobiledone=submit_log";
            setTitle("Electricity");
        } else if (url.equalsIgnoreCase("tv")) {
            String userName = settings.getString("Username", "");
            String password = settings.getString("Password", "");
            url ="  https://billing.fnfcalls.com/crm/customer/billing_television_app.php?pr_login=" + userName + "&pr_password=" + password + "&mobile_done=submit_log";
            setTitle("Television");
        } else if (url.equalsIgnoreCase("directory")) {
            url = " https://onessapp.com/business-directory/ ";
            setTitle("Directory");
        }
    }

    public void setTitle(String title) {
        try {
            getSupportActionBar().setTitle(title);
        } catch (Exception e) {
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        progDailog.show();

    }

    @Override
    public void onPageFinished(String url) {
        progDailog.dismiss();
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }
}
