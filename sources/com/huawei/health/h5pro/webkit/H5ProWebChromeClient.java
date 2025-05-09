package com.huawei.health.h5pro.webkit;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogPrintOperate;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.utils.PermissionUtil;
import com.huawei.health.h5pro.vengine.H5ProAppLoadListener;
import com.huawei.health.h5pro.webkit.trustlist.H5ProTrustListChecker;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes3.dex */
public class H5ProWebChromeClient extends WebChromeClient {
    public static final String[] b = {"/data/data/", "/data/user/"};

    /* renamed from: a, reason: collision with root package name */
    public final Context f2466a;
    public WebChromeClient.FileChooserParams c;
    public PermissionRequest d;
    public final H5ProAppLoadListener e;
    public ValueCallback<Uri[]> f;
    public final WebKitInstance g;
    public String[] h;
    public WeakReference<WebChromeCustomViewListener> i;

    public void setCustomViewListener(WebChromeCustomViewListener webChromeCustomViewListener) {
        this.i = new WeakReference<>(webChromeCustomViewListener);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        LogUtil.i(b(), "onShowFileChooser: enter");
        this.f = valueCallback;
        Context context = this.f2466a;
        if (context == null || !(context instanceof Activity)) {
            LogUtil.i(b(), "onShowFileChooser: mH5proContext is null or is not activity");
            a();
            return false;
        }
        final WeakReference weakReference = new WeakReference((Activity) this.f2466a);
        this.c = fileChooserParams;
        PermissionUtil.getInstance().checkAndRequestPermissions((Activity) weakReference.get(), PermissionUtil.e, 1002, new H5ProPermissionCallback() { // from class: com.huawei.health.h5pro.webkit.H5ProWebChromeClient.1
            @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
            public void onGranted() {
                H5ProWebChromeClient.this.XV_((Activity) weakReference.get());
            }

            @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
            public void onForeverDenied(String[] strArr) {
                H5ProWebChromeClient.this.a();
            }

            @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
            public void onDenied(String str) {
                H5ProWebChromeClient.this.a();
            }
        });
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        super.onShowCustomView(view, customViewCallback);
        WebChromeCustomViewListener webChromeCustomViewListener = (WebChromeCustomViewListener) GeneralUtil.getReferent(this.i);
        if (webChromeCustomViewListener != null) {
            webChromeCustomViewListener.onShowCustomView(view, customViewCallback);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Context context;
        PermissionRequest permissionRequest;
        String[] strArr2;
        if (i == 1001 || i == 1002) {
            int length = iArr.length;
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = true;
                    break;
                } else if (iArr[i2] != 0) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i == 1001) {
                if (z && (permissionRequest = this.d) != null && (strArr2 = this.h) != null) {
                    permissionRequest.grant(strArr2);
                }
                this.d = null;
                this.h = null;
                return;
            }
            if (i == 1002) {
                if (z && (context = this.f2466a) != null && (context instanceof Activity)) {
                    XV_((Activity) context);
                } else {
                    a();
                }
            }
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        LogUtil.i(b(), "onReceivedTitle: " + str);
        if (!this.g.isTitleValid(str)) {
            str = "";
        }
        this.g.updateTitle(str);
        this.e.onReceiveTitle(this.g, str);
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        LogUtil.i(b(), String.format(Locale.ROOT, "onProgressChanged: %s", Integer.valueOf(i)));
        if (WebKitUtil.isRemoteUrl(webView.getUrl())) {
            this.e.onProgressChanged(this.g, i);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequest(PermissionRequest permissionRequest) {
        String[] resources;
        if (permissionRequest == null || (resources = permissionRequest.getResources()) == null || resources.length == 0) {
            return;
        }
        if (!H5ProTrustListChecker.isTrusted(this.g)) {
            LogUtil.w(b(), "onPermissionRequest: untrusted");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : resources) {
            if (TextUtils.equals("android.webkit.resource.AUDIO_CAPTURE", str) || TextUtils.equals("android.webkit.resource.VIDEO_CAPTURE", str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        if (PermissionUtil.getInstance().checkWebKitPermission(this.f2466a, strArr)) {
            permissionRequest.grant(strArr);
        } else if (this.f2466a instanceof Activity) {
            this.d = permissionRequest;
            this.h = strArr;
            PermissionUtil.getInstance().requestWebKitPermission((Activity) this.f2466a, this.h, 1001);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onHideCustomView() {
        WebChromeCustomViewListener webChromeCustomViewListener = (WebChromeCustomViewListener) GeneralUtil.getReferent(this.i);
        if (webChromeCustomViewListener != null) {
            webChromeCustomViewListener.onHideCustomView();
        }
        super.onHideCustomView();
    }

    @Override // android.webkit.WebChromeClient
    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        callback.invoke(str, PermissionUtil.getInstance().checkPermission(this.f2466a, new String[]{"android.permission.ACCESS_FINE_LOCATION"}) || PermissionUtil.getInstance().checkPermission(this.f2466a, new String[]{"android.permission.ACCESS_COARSE_LOCATION"}), false);
        super.onGeolocationPermissionsShowPrompt(str, callback);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        WebKitInstance webKitInstance = this.g;
        LogUtil.i(String.format(Locale.ROOT, "H5PRO_AppLog[%s]", webKitInstance != null ? webKitInstance.getAppFlag() : ""), LogPrintOperate.isDecodePrint(this.g), consoleMessage.message());
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri[] uriArr;
        LogUtil.i(b(), "onActivityResult: requestCode=" + i + " resultCode=" + i2 + " data=" + intent);
        if (i != 51 || this.f == null) {
            return;
        }
        if (i2 == -1) {
            if (intent == null) {
                a();
            }
            ClipData clipData = intent.getClipData();
            if (clipData != null) {
                uriArr = new Uri[clipData.getItemCount()];
                for (int i3 = 0; i3 < clipData.getItemCount(); i3++) {
                    uriArr[i3] = clipData.getItemAt(i3).getUri();
                    LogUtil.i(b(), "result: ", uriArr[i3].getPath());
                }
            } else {
                String dataString = intent.getDataString();
                uriArr = !TextUtils.isEmpty(dataString) ? new Uri[]{Uri.parse(dataString)} : null;
            }
            ArrayList arrayList = new ArrayList();
            if (uriArr != null && uriArr.length > 0) {
                for (Uri uri : uriArr) {
                    if (XU_(uri)) {
                        arrayList.add(uri);
                    }
                }
            }
            this.f.onReceiveValue(arrayList.toArray(new Uri[0]));
        } else if (i2 == 0) {
            a();
        }
        this.f = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void XV_(Activity activity) {
        String join;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        WebChromeClient.FileChooserParams fileChooserParams = this.c;
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", fileChooserParams != null && fileChooserParams.getMode() == 1);
        ArrayList arrayList = new ArrayList(1);
        WebChromeClient.FileChooserParams fileChooserParams2 = this.c;
        if (fileChooserParams2 != null && fileChooserParams2.getAcceptTypes() != null && this.c.getAcceptTypes().length > 0) {
            for (String str : this.c.getAcceptTypes()) {
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                }
            }
        }
        if (arrayList.isEmpty()) {
            join = "*/*";
        } else {
            intent.putExtra("android.intent.extra.MIME_TYPES", (String[]) arrayList.toArray(new String[0]));
            join = String.join(",", arrayList);
        }
        intent.setType(join);
        LogUtil.i(b(), "showFileChooser: types -> " + arrayList);
        if (activity == null) {
            LogUtil.w(b(), "showFileChooser: activity is null");
        } else {
            activity.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 51);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        ValueCallback<Uri[]> valueCallback = this.f;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(null);
        }
        this.f = null;
    }

    private String b() {
        return LogUtil.getTag(this.g, "WebChromeClient");
    }

    private boolean XU_(Uri uri) {
        if (uri == null || uri.getPath().contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE)) {
            return false;
        }
        for (String str : b) {
            if (uri.getPath().startsWith(str)) {
                return false;
            }
        }
        return true;
    }

    public H5ProWebChromeClient(Context context, WebKitInstance webKitInstance, H5ProAppLoadListener h5ProAppLoadListener) {
        this.f2466a = context;
        this.g = webKitInstance;
        this.e = h5ProAppLoadListener;
    }
}
