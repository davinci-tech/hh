package com.huawei.hwcloudjs.api;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.huawei.hwcloudjs.support.enables.INoProguard;

/* loaded from: classes9.dex */
public interface WebviewFileChooserListenner extends INoProguard {
    void onActivityResult(int i, int i2, Intent intent);

    boolean onShowFileChooser(Activity activity, WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams);

    void openFileChooser(Activity activity, ValueCallback<Uri> valueCallback);

    void openFileChooser(Activity activity, ValueCallback<Uri> valueCallback, String str);

    void openFileChooser(Activity activity, ValueCallback<Uri> valueCallback, String str, String str2);
}
