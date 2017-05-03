package com.hyphenate.chatuidemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.hyphenate.chatuidemo.R;


/**
 * 公共的webview，不需要什么参数，类似20周年
 * 
 * @author DY
 *
 */
public class PublicWebviewActivity extends BaseActivity {
	private WebView agency_matters;
	private TextView tv_center;
	private String url, title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_public_webview);
		Intent intent = getIntent();
		if (intent != null) {
			url = intent.getStringExtra("url");
			title = intent.getStringExtra("title");
		}
		initView();
	}

	private void initView() {

		agency_matters = (WebView) findViewById(R.id.agency_matters);
		WebSettings wSet = agency_matters.getSettings();
		wSet.setJavaScriptEnabled(true);
		// 设置可以支持缩放
		wSet.setSupportZoom(true);
		// 设置出现缩放工具
		wSet.setBuiltInZoomControls(true);
		// 扩大比例的缩放
		wSet.setUseWideViewPort(true);
		wSet.setLoadWithOverviewMode(true);
		// webview不使用缓存
		wSet.setCacheMode(WebSettings.LOAD_NO_CACHE);
		agency_matters.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {

				view.loadUrl("javascript:window.handler.showTitle(document.title);");
				super.onPageFinished(view, url);
			}
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.startsWith("tel")) {
					String mobile = url.substring(url.lastIndexOf("/") + 1);
					Uri uri = Uri.parse("tel:" + mobile);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
					// 这个超连接,java已经处理了，webview不要处理了
					return true;
				}

				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		if (!TextUtils.isEmpty(url)) {
			agency_matters.loadUrl(url);
		}

	}


}
