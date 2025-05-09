package com.alipay.sdk.m.x;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import defpackage.lv;
import defpackage.md;
import defpackage.mg;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class e extends LinearLayout {
    public static Handler e = new Handler(Looper.getMainLooper());

    /* renamed from: a, reason: collision with root package name */
    public ProgressBar f882a;
    public ImageView b;
    public TextView c;
    public ImageView d;
    public WebView f;
    public final c g;
    public g h;
    public f i;
    public h j;
    public final float k;
    public final lv l;
    public View.OnClickListener o;

    public class a implements DownloadListener {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f883a;

        public a(Context context) {
            this.f883a = context;
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            try {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
                intent.setFlags(268435456);
                this.f883a.startActivity(intent);
            } catch (Throwable unused) {
            }
        }
    }

    public class b extends WebChromeClient {
        public b() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            return e.this.i.a(e.this, str, str2, str3, jsPromptResult);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (!e.this.g.b) {
                e.this.f882a.setVisibility(8);
            } else {
                if (i > 90) {
                    e.this.f882a.setVisibility(4);
                    return;
                }
                if (e.this.f882a.getVisibility() == 4) {
                    e.this.f882a.setVisibility(0);
                }
                e.this.f882a.setProgress(i);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            e.this.i.c(e.this, str);
        }
    }

    public static final class c {
        public boolean b;
        public boolean d;

        public c(boolean z, boolean z2) {
            this.d = z;
            this.b = z2;
        }
    }

    public class d implements View.OnClickListener {

        public class c implements Runnable {
            public final /* synthetic */ View d;

            public c(View view) {
                this.d = view;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.d.setEnabled(true);
            }
        }

        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            h hVar = e.this.j;
            if (hVar != null) {
                view.setEnabled(false);
                e.e.postDelayed(new c(view), 256L);
                if (view == e.this.d) {
                    hVar.b(e.this);
                } else if (view == e.this.b) {
                    hVar.a(e.this);
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* renamed from: com.alipay.sdk.m.x.e$e, reason: collision with other inner class name */
    public class C0016e extends WebViewClient {
        public C0016e() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (e.this.h.b(e.this, str)) {
                return;
            }
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (e.this.h.d(e.this, str)) {
                return;
            }
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (e.this.h.a(e.this, i, str, str2)) {
                return;
            }
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (e.this.h.a(e.this, sslErrorHandler, sslError)) {
                return;
            }
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (e.this.h.a(e.this, str)) {
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    public interface f {
        boolean a(e eVar, String str, String str2, String str3, JsPromptResult jsPromptResult);

        void c(e eVar, String str);
    }

    public interface g {
        boolean a(e eVar, int i, String str, String str2);

        boolean a(e eVar, SslErrorHandler sslErrorHandler, SslError sslError);

        boolean a(e eVar, String str);

        boolean b(e eVar, String str);

        boolean d(e eVar, String str);
    }

    public interface h {
        void a(e eVar);

        void b(e eVar);
    }

    public e(Context context, lv lvVar, c cVar) {
        this(context, null, lvVar, cVar);
    }

    public ImageView getBackButton() {
        return this.d;
    }

    public ProgressBar getProgressbar() {
        return this.f882a;
    }

    public ImageView getRefreshButton() {
        return this.b;
    }

    public TextView getTitle() {
        return this.c;
    }

    public String getUrl() {
        return this.f.getUrl();
    }

    public WebView getWebView() {
        return this.f;
    }

    public void setChromeProxy(f fVar) {
        this.i = fVar;
        if (fVar == null) {
            this.f.setWebChromeClient(null);
        } else {
            this.f.setWebChromeClient(new b());
        }
    }

    public void setWebClientProxy(g gVar) {
        this.h = gVar;
        if (gVar == null) {
            WebView webView = this.f;
            if (webView instanceof WebView) {
                APMSH5LoadInstrument.setWebViewClient(webView, null);
                return;
            } else {
                webView.setWebViewClient(null);
                return;
            }
        }
        WebView webView2 = this.f;
        C0016e c0016e = new C0016e();
        if (webView2 instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView2, c0016e);
        } else {
            webView2.setWebViewClient(c0016e);
        }
    }

    public void setWebEventProxy(h hVar) {
        this.j = hVar;
    }

    public e(Context context, AttributeSet attributeSet, lv lvVar, c cVar) {
        super(context, attributeSet);
        this.o = new d();
        this.g = cVar == null ? new c(false, false) : cVar;
        this.l = lvVar;
        this.k = context.getResources().getDisplayMetrics().density;
        setOrientation(1);
        e(context);
        c(context);
        d(context);
    }

    private void d(Context context) {
        WebView webView = new WebView(context);
        this.f = webView;
        webView.setVerticalScrollbarOverlay(true);
        a(this.f, context);
        WebSettings settings = this.f.getSettings();
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setUseWideViewPort(true);
        settings.setAppCacheMaxSize(5242880L);
        settings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(false);
        this.f.setVerticalScrollbarOverlay(true);
        this.f.setDownloadListener(new a(context));
        try {
            try {
                this.f.removeJavascriptInterface("searchBoxJavaBridge_");
                this.f.removeJavascriptInterface("accessibility");
                this.f.removeJavascriptInterface("accessibilityTraversal");
            } catch (Exception unused) {
                Method method = this.f.getClass().getMethod("removeJavascriptInterface", new Class[0]);
                if (method != null) {
                    method.invoke(this.f, "searchBoxJavaBridge_");
                    method.invoke(this.f, "accessibility");
                    method.invoke(this.f, "accessibilityTraversal");
                }
            }
        } catch (Throwable unused2) {
        }
        com.alipay.sdk.m.x.c.a(this.f);
        addView(this.f, new LinearLayout.LayoutParams(-1, -1));
    }

    private void e(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-218103809);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setVisibility(this.g.d ? 0 : 8);
        ImageView imageView = new ImageView(context);
        this.d = imageView;
        imageView.setOnClickListener(this.o);
        this.d.setScaleType(ImageView.ScaleType.CENTER);
        this.d.setImageDrawable(mg.aY_("iVBORw0KGgoAAAANSUhEUgAAAEgAAABIBAMAAACnw650AAAAFVBMVEUAAAARjusRkOkQjuoRkeoRj+oQjunya570AAAABnRSTlMAinWeSkk7CjRNAAAAZElEQVRIx+3MOw6AIBQF0YsrMDGx1obaLeGH/S9BQgkJ82rypp4ceTN1ilvyKizmZIAyU7FML0JVYig55BBAfQ2EU4V4CpZJ+2AiSj11C6rUoTannBpRn4W6xNQjLBSI2+TN0w/+3HT2wPClrQAAAABJRU5ErkJggg==", context));
        this.d.setPadding(c(12), 0, c(12), 0);
        linearLayout.addView(this.d, new LinearLayout.LayoutParams(-2, -2));
        View view = new View(context);
        view.setBackgroundColor(-2500135);
        linearLayout.addView(view, new LinearLayout.LayoutParams(c(1), c(25)));
        TextView textView = new TextView(context);
        this.c = textView;
        textView.setTextColor(-15658735);
        this.c.setTextSize(17.0f);
        this.c.setMaxLines(1);
        this.c.setEllipsize(TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(c(17), 0, 0, 0);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.c, layoutParams);
        ImageView imageView2 = new ImageView(context);
        this.b = imageView2;
        imageView2.setOnClickListener(this.o);
        this.b.setScaleType(ImageView.ScaleType.CENTER);
        this.b.setImageDrawable(mg.aY_("iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAMAAABiM0N1AAAAmVBMVEUAAAARj+oQjuoRkOsVk/AQj+oRjuoQj+oSkO3///8Rj+kRj+oQkOsTk+whm/8Qj+oRj+oQj+oSkus2p/8QjuoQj+oQj+oQj+oQj+oRj+oTkuwRj+oQj+oRj+oRj+oSkOsSkO0ZlfMbk+8XnPgQj+oRj+oQj+oQj+sSj+sRkOoSkescqv8Rj+oQj+oSj+sXku4Rj+kQjuoQjumXGBCVAAAAMnRSTlMAxPtPF8ry7CoB9npbGwe6lm0wBODazb1+aSejm5GEYjcTDwvls6uJc0g/CdWfRCF20AXrk5QAAAJqSURBVFjD7ZfXmpswEIUFphmDCxi3talurGvm/R8uYSDe5FNBwlzsxf6XmvFBmiaZ/PCdWDk9CWn61OhHCMAaXfoRAth7wx6EkMXnWyrho4yg4bDpquI8Jy78Q7eoj9cmUFijsaLM0JsD9CD0uQAa9aNdPuCFvbA7B9t/Becap8Pu6Q/2jcyH81VHc/WCHDQZXwbvtUhQ61iDlqadncU6Rp31yGkZIzOAu7AjtPpYGREzq/pY5DRFHS1siyO6HfkOKTrMjdb2qevV4zosK7MbkFY2LmYk55hL6juCIFWMOI2KGzblmho3b18EIbxL1hs6r5m2Q2WaEElwS3NW4xh6ZZJuzTtUsBKT4G0h35s4y1mNgkNoS6TZ8SKBXTZQGBNYdPTozXGYKoyLAmOasttjThT4xT6Ch+2qIjRhV9Ja3NC87Kyo5We1vCNEMW1T+j1VLZ9UhE54Q1DL52r5piJ0YxdegvWlHOwTu76uKkJX+MOTHno4YFSEbHYdhViojsLrCTg/MKnhKWaEYzvkZFM8aOkPH7iTSvoFZKD7jGEJbarkRaxQyOeWvGVIbsji152jK7TbDgRzcIuz7SGj89BFU8d30TqWeDtrILxyTkD1IXfvmHseuU3lVHDz607bw0f3xDqejm5ncd0j8VDwfoibRy8RcgTkWHBvocbDbMlJsQAkGnAOHwGy90kLmQY1Wkob07/GaCNRIzdoWK7/+6y/XkLDJCcynOGFuUrKIMuCMonNr9VpSOQoIxBgJ0SacGbzZNy4ICrkscvU2fpElYz+U3sd+aQThjfVmjNa5i15kLcojM3Gz8kP34jf4VaV3X55gNEAAAAASUVORK5CYII=", context));
        this.b.setPadding(c(12), 0, c(12), 0);
        linearLayout.addView(this.b, new LinearLayout.LayoutParams(-2, -2));
        addView(linearLayout, new LinearLayout.LayoutParams(-1, c(48)));
    }

    private void c(Context context) {
        ProgressBar progressBar = new ProgressBar(context, null, R.style.Widget.ProgressBar.Horizontal);
        this.f882a = progressBar;
        progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_horizontal));
        this.f882a.setMax(100);
        this.f882a.setBackgroundColor(-218103809);
        addView(this.f882a, new LinearLayout.LayoutParams(-1, c(2)));
    }

    public void a(WebView webView, Context context) {
        String userAgentString = webView.getSettings().getUserAgentString();
        webView.getSettings().setUserAgentString(userAgentString + md.j(context));
    }

    public void e(String str) {
        WebView webView = this.f;
        webView.loadUrl(str);
        WebViewInstrumentation.loadUrl(webView, str);
        com.alipay.sdk.m.x.c.a(this.f);
    }

    public void e(String str, byte[] bArr) {
        this.f.postUrl(str, bArr);
    }

    public void a() {
        removeAllViews();
        this.f.removeAllViews();
        WebView webView = this.f;
        if (webView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView, null);
        } else {
            webView.setWebViewClient(null);
        }
        this.f.setWebChromeClient(null);
        this.f.destroy();
    }

    private int c(int i) {
        return (int) (i * this.k);
    }
}
