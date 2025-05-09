package com.tencent.open;

import android.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.operation.ble.BleConstants;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.b;
import com.tencent.open.b.h;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.j;
import com.tencent.open.utils.m;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class TDialog extends c {
    static final FrameLayout.LayoutParams c = new FrameLayout.LayoutParams(-1, -1);
    static Toast d = null;
    private static WeakReference<ProgressDialog> f;
    private WeakReference<Context> e;
    private String g;
    private OnTimeListener h;
    private IUiListener i;
    private FrameLayout j;
    private com.tencent.open.c.b k;
    private Handler l;
    private boolean m;
    private QQToken n;

    class THandler extends Handler {
        private OnTimeListener b;

        public THandler(OnTimeListener onTimeListener, Looper looper) {
            super(looper);
            this.b = onTimeListener;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SLog.d("openSDK_LOG.TDialog", "--handleMessage--msg.WHAT = " + message.what);
            int i = message.what;
            if (i == 1) {
                this.b.a((String) message.obj);
                return;
            }
            if (i == 2) {
                this.b.onCancel();
                return;
            }
            if (i != 3) {
                if (i != 5 || TDialog.this.e == null || TDialog.this.e.get() == null) {
                    return;
                }
                TDialog.d((Context) TDialog.this.e.get(), (String) message.obj);
                return;
            }
            if (TDialog.this.e == null || TDialog.this.e.get() == null) {
                return;
            }
            TDialog.c((Context) TDialog.this.e.get(), (String) message.obj);
        }
    }

    static class OnTimeListener extends DefaultUiListener {

        /* renamed from: a, reason: collision with root package name */
        String f11323a;
        String b;
        private WeakReference<Context> c;
        private String d;
        private IUiListener e;

        public OnTimeListener(Context context, String str, String str2, String str3, IUiListener iUiListener) {
            this.c = new WeakReference<>(context);
            this.d = str;
            this.f11323a = str2;
            this.b = str3;
            this.e = iUiListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str) {
            try {
                onComplete(m.d(str));
            } catch (JSONException e) {
                e.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            h.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, jSONObject.optInt("ret", -6), this.f11323a, false);
            IUiListener iUiListener = this.e;
            if (iUiListener != null) {
                iUiListener.onComplete(jSONObject);
                this.e = null;
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            String str;
            if (uiError.errorMessage != null) {
                str = uiError.errorMessage + this.f11323a;
            } else {
                str = this.f11323a;
            }
            h a2 = h.a();
            a2.a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, uiError.errorCode, str, false);
            IUiListener iUiListener = this.e;
            if (iUiListener != null) {
                iUiListener.onError(uiError);
                this.e = null;
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
            IUiListener iUiListener = this.e;
            if (iUiListener != null) {
                iUiListener.onCancel();
                this.e = null;
            }
        }
    }

    public TDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, R.style.Theme.Translucent.NoTitleBar);
        this.m = false;
        this.n = null;
        this.e = new WeakReference<>(context);
        this.g = str2;
        this.h = new OnTimeListener(context, str, str2, qQToken.getAppId(), iUiListener);
        this.l = new THandler(this.h, context.getMainLooper());
        this.i = iUiListener;
        this.n = qQToken;
    }

    @Override // com.tencent.open.c, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        a.a(getWindow());
        a();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.open.TDialog.1
            @Override // java.lang.Runnable
            public void run() {
                View decorView;
                View childAt;
                Window window = TDialog.this.getWindow();
                if (window == null || (decorView = window.getDecorView()) == null || (childAt = ((ViewGroup) decorView).getChildAt(0)) == null) {
                    return;
                }
                childAt.setPadding(0, 0, 0, 0);
            }
        });
        b();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        OnTimeListener onTimeListener = this.h;
        if (onTimeListener != null) {
            onTimeListener.onCancel();
        }
        super.onBackPressed();
    }

    private void a() {
        try {
            new TextView(this.e.get()).setText(BleConstants.WEIGHT_KEY);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            com.tencent.open.c.b bVar = new com.tencent.open.c.b(this.e.get());
            this.k = bVar;
            bVar.setLayoutParams(layoutParams);
            layoutParams.gravity = 17;
            com.tencent.open.c.c cVar = new com.tencent.open.c.c(this.e.get());
            this.j = cVar;
            cVar.setLayoutParams(layoutParams);
            this.j.setBackgroundColor(-1);
            this.j.addView(this.k);
            setContentView(this.j);
        } catch (Throwable th) {
            SLog.e("openSDK_LOG.TDialog", "onCreateView exception", th);
            a.a(this, this.l);
        }
    }

    @Override // com.tencent.open.c
    protected void a(String str) {
        SLog.d("openSDK_LOG.TDialog", "--onConsoleMessage--");
        try {
            this.f11347a.a(this.k, str);
        } catch (Exception unused) {
        }
    }

    private void b() {
        this.k.setVerticalScrollBarEnabled(false);
        this.k.setHorizontalScrollBarEnabled(false);
        com.tencent.open.c.b bVar = this.k;
        FbWebViewClient fbWebViewClient = new FbWebViewClient();
        if (bVar instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(bVar, fbWebViewClient);
        } else {
            bVar.setWebViewClient(fbWebViewClient);
        }
        this.k.setWebChromeClient(this.b);
        this.k.clearFormData();
        WebSettings settings = this.k.getSettings();
        if (settings == null) {
            return;
        }
        com.tencent.open.web.a.a(this.k);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        WeakReference<Context> weakReference = this.e;
        if (weakReference != null && weakReference.get() != null) {
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(this.e.get().getApplicationContext().getDir("databases", 0).getPath());
        }
        settings.setDomStorageEnabled(true);
        this.f11347a.a(new JsListener(), "sdk_js_if");
        com.tencent.open.c.b bVar2 = this.k;
        String str = this.g;
        bVar2.loadUrl(str);
        WebViewInstrumentation.loadUrl(bVar2, str);
        this.k.setLayoutParams(c);
        this.k.setVisibility(4);
    }

    class JsListener extends b.C0290b {
        private JsListener() {
        }

        public void onAddShare(String str) {
            SLog.d("openSDK_LOG.TDialog", "JsListener onAddShare");
            onComplete(str);
        }

        public void onInvite(String str) {
            onComplete(str);
        }

        public void onCancelAddShare(String str) {
            SLog.e("openSDK_LOG.TDialog", "JsListener onCancelAddShare" + str);
            onCancel("cancel");
        }

        public void onCancelLogin() {
            onCancel("");
        }

        public void onCancelInvite() {
            SLog.e("openSDK_LOG.TDialog", "JsListener onCancelInvite");
            onCancel("");
        }

        public void onComplete(String str) {
            TDialog.this.l.obtainMessage(1, str).sendToTarget();
            SLog.e("openSDK_LOG.TDialog", "JsListener onComplete" + str);
            TDialog.this.dismiss();
        }

        public void onCancel(String str) {
            SLog.e("openSDK_LOG.TDialog", "JsListener onCancel --msg = " + str);
            TDialog.this.l.obtainMessage(2, str).sendToTarget();
            TDialog.this.dismiss();
        }

        public void showMsg(String str) {
            TDialog.this.l.obtainMessage(3, str).sendToTarget();
        }

        public void onLoad(String str) {
            TDialog.this.l.obtainMessage(4, str).sendToTarget();
        }
    }

    class FbWebViewClient extends WebViewClient {
        private FbWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse;
            SLog.v("openSDK_LOG.TDialog", "Redirect URL: " + str);
            if (str.startsWith(j.a().a((Context) TDialog.this.e.get(), "auth://tauth.qq.com/"))) {
                TDialog.this.h.onComplete(m.c(str));
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            }
            if (str.startsWith(Constants.CANCEL_URI)) {
                TDialog.this.h.onCancel();
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            }
            if (str.startsWith(Constants.CLOSE_URI)) {
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            }
            if (!str.startsWith(Constants.DOWNLOAD_URI) && !str.endsWith(".apk")) {
                return str.startsWith("auth://progress");
            }
            try {
                if (str.startsWith(Constants.DOWNLOAD_URI)) {
                    parse = Uri.parse(Uri.decode(str.substring(11)));
                } else {
                    parse = Uri.parse(Uri.decode(str));
                }
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, parse);
                intent.addFlags(268435456);
                if (TDialog.this.e != null && TDialog.this.e.get() != null) {
                    ((Context) TDialog.this.e.get()).startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            TDialog.this.h.onError(new UiError(i, str, str2));
            if (TDialog.this.e != null && TDialog.this.e.get() != null) {
                Toast.makeText((Context) TDialog.this.e.get(), "网络连接异常或系统错误", 0).show();
            }
            TDialog.this.dismiss();
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            SLog.v("openSDK_LOG.TDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            TDialog.this.k.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, String str) {
        try {
            JSONObject d2 = m.d(str);
            int i = d2.getInt("type");
            String string = d2.getString("msg");
            if (i == 0) {
                Toast toast = d;
                if (toast == null) {
                    d = Toast.makeText(context, string, 0);
                } else {
                    toast.setView(toast.getView());
                    d.setText(string);
                    d.setDuration(0);
                }
                d.show();
                return;
            }
            if (i == 1) {
                Toast toast2 = d;
                if (toast2 == null) {
                    d = Toast.makeText(context, string, 1);
                } else {
                    toast2.setView(toast2.getView());
                    d.setText(string);
                    d.setDuration(1);
                }
                d.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            JSONObject d2 = m.d(str);
            int i = d2.getInt("action");
            String string = d2.getString("msg");
            if (i == 1) {
                WeakReference<ProgressDialog> weakReference = f;
                if (weakReference != null && weakReference.get() != null) {
                    f.get().setMessage(string);
                    if (!f.get().isShowing()) {
                        f.get().show();
                    }
                }
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage(string);
                f = new WeakReference<>(progressDialog);
                progressDialog.show();
            } else if (i == 0) {
                WeakReference<ProgressDialog> weakReference2 = f;
                if (weakReference2 == null) {
                    return;
                }
                if (weakReference2.get() != null && f.get().isShowing()) {
                    f.get().dismiss();
                    f = null;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
