package com.huawei.openalliance.ad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.webkit.ProxyConfig;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.openalliance.ad.activity.SafeIntent;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback;
import com.huawei.openalliance.ad.tf;
import com.huawei.openalliance.ad.utils.i;
import com.huawei.operation.utils.Constants;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
public class nv extends jj<com.huawei.openalliance.ad.views.interfaces.w> implements oj<com.huawei.openalliance.ad.views.interfaces.w> {
    private static final String d = "nv";
    private qq e;
    private Context f;
    private AdLandingPageData g;
    private IPPSWebEventCallback i;
    private com.huawei.openalliance.ad.analysis.c j;
    private com.huawei.openalliance.ad.analysis.h k;
    private long m;
    private Long n;
    private String p;
    private boolean h = false;
    private Boolean l = false;
    private boolean o = false;

    String b(String str) {
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
    @Override // com.huawei.openalliance.ad.oj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long k() {
        /*
            r7 = this;
            java.lang.Long r0 = r7.n
            r1 = 0
            if (r0 == 0) goto L11
            long r3 = java.lang.System.currentTimeMillis()
            java.lang.Long r0 = r7.n
            long r5 = r0.longValue()
            goto L1d
        L11:
            long r3 = r7.m
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 <= 0) goto L1f
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r7.m
        L1d:
            long r3 = r3 - r5
            goto L20
        L1f:
            r3 = r1
        L20:
            com.huawei.openalliance.ad.inter.data.AdLandingPageData r0 = r7.g
            int r0 = r0.getAdType()
            r5 = 7
            if (r0 != r5) goto L2e
            boolean r0 = r7.o
            if (r0 != 0) goto L2e
            goto L2f
        L2e:
            r1 = r3
        L2f:
            boolean r0 = com.huawei.openalliance.ad.ho.a()
            if (r0 == 0) goto L44
            java.lang.String r0 = com.huawei.openalliance.ad.nv.d
            java.lang.Long r3 = java.lang.Long.valueOf(r1)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r4 = "getWebHasShownTime, webShowTime, duration: %s"
            com.huawei.openalliance.ad.ho.a(r0, r4, r3)
        L44:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.nv.k():long");
    }

    public void j() {
        this.k.b(this.g);
    }

    public void i() {
        this.k.a(this.g);
    }

    public void h() {
        this.j.b(this.g);
    }

    @Override // com.huawei.openalliance.ad.oj
    public void d(long j) {
        this.n = Long.valueOf(j);
        this.o = true;
    }

    @Override // com.huawei.openalliance.ad.oj
    public void c(long j) {
        if (ho.a()) {
            ho.a(d, "setWebOpenTime, webOpenTime= %s", Long.valueOf(j));
        }
        this.m = j;
        this.n = null;
    }

    @Override // com.huawei.openalliance.ad.oj
    public void c() {
        this.j.a(this.g);
    }

    @Override // com.huawei.openalliance.ad.oj
    public String c(String str) {
        if (com.huawei.openalliance.ad.utils.cz.g(str, this.p) && com.huawei.openalliance.ad.utils.ao.a(this.g)) {
            str = com.huawei.openalliance.ad.utils.cz.c(this.g.getAppInfo().getAppName());
        }
        return str == null ? this.f.getResources().getString(R.string._2130851066_res_0x7f0234fa) : TextUtils.equals(str, Constants.ABOUT_BLANK) ? " " : str;
    }

    @Override // com.huawei.openalliance.ad.oj
    public void b(qq qqVar) {
        this.e = qqVar;
    }

    @Override // com.huawei.openalliance.ad.oj
    public void b() {
        IPPSWebEventCallback iPPSWebEventCallback = this.i;
        if (iPPSWebEventCallback != null) {
            iPPSWebEventCallback.onWebloadFinish();
        }
        if (ho.a()) {
            ho.a(d, "onWebloadFinish");
        }
        if (this.n == null) {
            this.n = Long.valueOf(System.currentTimeMillis());
        }
        qq qqVar = this.e;
        if (qqVar != null) {
            qqVar.h();
        }
    }

    @Override // com.huawei.openalliance.ad.oj
    public boolean a(WebView webView, Uri uri) {
        Intent intent;
        ContentRecord a2;
        Intent a3 = a(uri);
        if (a3 == null) {
            if (!fh.b(this.f).k(com.huawei.openalliance.ad.utils.cz.k(uri.toString()))) {
                return false;
            }
            c();
            return true;
        }
        try {
            intent = b(a3);
            if (intent != null) {
                try {
                    h();
                    a3 = intent;
                } catch (RuntimeException | Exception unused) {
                    ho.c(d, "shouldOverrideUrlLoading error");
                    this.f.startActivity(intent);
                    return false;
                }
            }
            i.a a4 = a(a3);
            if (a4 == null) {
                ho.b(d, "shouldOverrideUrlLoading, queryIntentActivities failed");
                return true;
            }
            String a5 = a4.a();
            if (bz.b(this.f) && !fh.b(this.f).j(a5)) {
                ho.b(d, "shouldOverrideUrlLoading, whilelist check failed");
                return true;
            }
            Boolean l = fh.b(this.f).l(a5);
            a3.addFlags(268435456);
            tf.a aVar = new tf.a();
            if (this.f7126a != null) {
                a2 = this.f7126a;
            } else {
                qq qqVar = this.e;
                a2 = qqVar == null ? null : qqVar.a();
            }
            aVar.a(a2).c(a5).a(a3);
            if (l != null) {
                if (l.booleanValue()) {
                    com.huawei.openalliance.ad.utils.i.a(this.f, a3, aVar.a());
                }
                return true;
            }
            if (fh.b(this.f).aS()) {
                a(webView, a4, a3, aVar.a());
            } else {
                com.huawei.openalliance.ad.utils.i.a(this.f, a3, aVar.a());
            }
            return true;
        } catch (RuntimeException | Exception unused2) {
            intent = a3;
        }
    }

    @Override // com.huawei.openalliance.ad.oj
    public void a(String str, String str2, String str3) {
        this.j.a(str, str2, str3, this.g);
    }

    @Override // com.huawei.openalliance.ad.oj
    public void a(String str, WebView webView) {
        if (!com.huawei.openalliance.ad.utils.cz.b(str)) {
            if (!str.startsWith("https://") && !str.startsWith("http://")) {
                str = "https://" + str;
            }
            str = b(str);
        }
        if (webView != null) {
            if (a(str, webView.getContext())) {
                str = Constants.ABOUT_BLANK;
            }
            webView.loadUrl(str);
            WebViewInstrumentation.loadUrl(webView, str);
            com.huawei.openalliance.ad.utils.dj.a(new a(webView), 1000L);
        }
    }

    @Override // com.huawei.openalliance.ad.oj
    public void a(Object obj, String str, WebView webView) {
        if (webView != null) {
            ho.a(d, "inject js");
            webView.addJavascriptInterface(obj, str);
        }
    }

    @Override // com.huawei.openalliance.ad.oj
    public void a(IPPSWebEventCallback iPPSWebEventCallback) {
        this.i = iPPSWebEventCallback;
    }

    @Override // com.huawei.openalliance.ad.oj
    public final void a(AdLandingPageData adLandingPageData) {
        if (adLandingPageData != null) {
            this.g = adLandingPageData;
            Context context = this.f;
            ou ouVar = new ou(context, sc.a(context, adLandingPageData.getAdType()));
            this.e = ouVar;
            ouVar.a(adLandingPageData.x());
        }
    }

    @Override // com.huawei.openalliance.ad.oj
    public void a(WebView webView) {
        String str;
        StringBuilder sb;
        if (webView == null) {
            return;
        }
        b(webView);
        WebSettings settings = webView.getSettings();
        a(settings);
        try {
            String userAgentString = settings.getUserAgentString();
            if (userAgentString != null && userAgentString.indexOf(com.huawei.openalliance.ad.constant.Constants.PPS_UA) < 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(userAgentString);
                sb2.append(" HuaweiPPS/3.4.74.310");
                if (this.f != null) {
                    sb2.append(" ");
                    sb2.append(this.f.getPackageName());
                    sb2.append("/");
                    sb2.append(com.huawei.openalliance.ad.utils.i.a(this.f));
                }
                userAgentString = sb2.toString();
                settings.setUserAgentString(userAgentString);
            }
            ho.a(d, "UserAgent:%s", userAgentString);
        } catch (RuntimeException e) {
            e = e;
            str = d;
            sb = new StringBuilder("add useragent RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.d(str, sb.toString());
        } catch (Exception e2) {
            e = e2;
            str = d;
            sb = new StringBuilder("add useragent Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.d(str, sb.toString());
        }
    }

    @Override // com.huawei.openalliance.ad.oj
    public void a(int i, int i2) {
        this.k.a(i, i2, pf.a(this.g));
    }

    @Override // com.huawei.openalliance.ad.oj
    public void a(int i) {
        long j;
        IPPSWebEventCallback iPPSWebEventCallback = this.i;
        if (iPPSWebEventCallback != null) {
            iPPSWebEventCallback.onWebClose(i);
        }
        if (this.n != null) {
            j = System.currentTimeMillis() - this.n.longValue();
            this.n = null;
        } else if (this.m > 0) {
            j = System.currentTimeMillis() - this.m;
            this.m = 0L;
        } else {
            j = 0;
        }
        long j2 = (this.g.getAdType() != 7 || this.o) ? j : 0L;
        if (ho.a()) {
            ho.a(d, "onWebClose, duration: %s", Long.valueOf(j2));
        }
        qq qqVar = this.e;
        if (qqVar != null) {
            qqVar.a(i, j2);
        }
    }

    @Override // com.huawei.openalliance.ad.oj
    public void a() {
        IPPSWebEventCallback iPPSWebEventCallback = this.i;
        if (iPPSWebEventCallback != null) {
            iPPSWebEventCallback.onWebOpen();
        }
        qq qqVar = this.e;
        if (qqVar != null) {
            qqVar.g();
        }
    }

    private Set<String> l() {
        MetaData metaData;
        AdLandingPageData adLandingPageData = this.g;
        if (adLandingPageData != null && (metaData = (MetaData) com.huawei.openalliance.ad.utils.be.b(adLandingPageData.s(), MetaData.class, new Class[0])) != null) {
            List<String> D = metaData.D();
            if (!com.huawei.openalliance.ad.utils.bg.a(D)) {
                return new HashSet(D);
            }
        }
        return new HashSet();
    }

    public static void b(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(false);
        settings.setCacheMode(-1);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setGeolocationEnabled(false);
        settings.setAllowContentAccess(false);
    }

    private Intent b(Intent intent) {
        SafeIntent safeIntent = new SafeIntent(intent);
        Set<String> l = l();
        String scheme = safeIntent.getScheme();
        if (!com.huawei.openalliance.ad.utils.bg.a(l) && a(safeIntent, l, scheme)) {
            return safeIntent;
        }
        Set<String> bu = fh.b(this.f).bu();
        if (com.huawei.openalliance.ad.utils.bg.a(bu)) {
            return null;
        }
        if (a(safeIntent, bu, scheme)) {
            return safeIntent;
        }
        if (this.l.booleanValue() || !com.huawei.openalliance.ad.constant.Constants.SCHEME_MARKET.equalsIgnoreCase(scheme) || !com.huawei.openalliance.ad.utils.i.a(this.f, "com.huawei.appmarket")) {
            return null;
        }
        safeIntent.setPackage("com.huawei.appmarket");
        return safeIntent;
    }

    private boolean a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!fh.b(context).k(com.huawei.openalliance.ad.utils.cz.k(str))) {
            return false;
        }
        ho.b(d, "url is blocked");
        c();
        return true;
    }

    private boolean a(Intent intent, Set<String> set, String str) {
        boolean z;
        Iterator<String> it = set.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            String[] split = it.next().split(":");
            if (split.length == 2 && (split[0].equalsIgnoreCase(str) || split[0].equalsIgnoreCase("*"))) {
                z = true;
                intent.setPackage(split[1]);
                if (com.huawei.openalliance.ad.constant.Constants.SCHEME_MARKET.equalsIgnoreCase(str)) {
                    this.l = true;
                }
                if (a(intent) != null) {
                    break;
                }
            }
        }
        return z;
    }

    private void a(final Object obj) {
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.nv.1
            @Override // java.lang.Runnable
            public void run() {
                com.huawei.openalliance.ad.utils.ck.a(obj, nv.this.g.e());
            }
        });
    }

    private void a(WebView webView, final i.a aVar, final Intent intent, final tf tfVar) {
        View inflate = LayoutInflater.from(this.f).inflate(R.layout.hiad_open_app_dialog, (ViewGroup) null);
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.hiad_open_app_nomore_remind);
        TextView textView = (TextView) inflate.findViewById(R.id.hiad_open_app_tips);
        String b = aVar.b();
        if (TextUtils.isEmpty(b) || !bz.b(this.f)) {
            b = this.f.getString(R.string._2130851063_res_0x7f0234f7);
            checkBox.setVisibility(8);
        }
        textView.setText(this.f.getString(R.string._2130851106_res_0x7f023522, b));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.openalliance.ad.nv.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                nv.this.h = z;
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this.f);
        builder.setView(inflate);
        builder.setPositiveButton(R.string._2130851069_res_0x7f0234fd, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.nv.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                nv.this.i();
                com.huawei.openalliance.ad.utils.i.a(nv.this.f, intent, tfVar);
                if (nv.this.h) {
                    fh.b(nv.this.f).a(aVar.a(), true);
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        builder.setNeutralButton(R.string._2130851072_res_0x7f023500, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.nv.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                nv.this.j();
                if (nv.this.h) {
                    fh.b(nv.this.f).a(aVar.a(), false);
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        AlertDialog create = builder.create();
        if (!(webView.getContext() instanceof Activity)) {
            create.getWindow().setType(2003);
        }
        create.show();
    }

    private void a(Context context, com.huawei.openalliance.ad.views.interfaces.w wVar) {
        this.f = context;
        a((nv) wVar);
        this.j = new com.huawei.openalliance.ad.analysis.c(context);
        this.k = new com.huawei.openalliance.ad.analysis.h(context);
        this.p = fh.b(context).am();
    }

    private i.a a(Intent intent) {
        Set<i.a> a2 = com.huawei.openalliance.ad.utils.i.a(this.f, intent);
        if (a2 != null && !a2.isEmpty() && (a2.size() <= 1 || !bz.b(this.f))) {
            Iterator<i.a> it = a2.iterator();
            if (it.hasNext()) {
                return it.next();
            }
        }
        return null;
    }

    private Intent a(Uri uri) {
        String str;
        String str2;
        try {
            String scheme = uri.getScheme();
            if (!scheme.equals("http") && !scheme.equals(ProxyConfig.MATCH_HTTPS)) {
                if (!scheme.equals(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK)) {
                    return new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, uri);
                }
                Intent parseUri = Intent.parseUri(uri.toString(), 1);
                if (parseUri.getData() != null) {
                    parseUri = parseUri.setDataAndTypeAndNormalize(parseUri.getData(), parseUri.getType());
                }
                parseUri.setComponent(null);
                parseUri.setSelector(null);
                parseUri.addCategory("android.intent.category.BROWSABLE");
                return parseUri;
            }
            return null;
        } catch (RuntimeException unused) {
            str = d;
            str2 = "getIntent RuntimeException";
            ho.c(str, str2);
            return null;
        } catch (Exception unused2) {
            str = d;
            str2 = "getIntent Exception";
            ho.c(str, str2);
            return null;
        }
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private WebView f7344a;

        @Override // java.lang.Runnable
        public void run() {
            WebView webView = this.f7344a;
            if (webView != null) {
                webView.clearHistory();
            }
        }

        public a(WebView webView) {
            this.f7344a = webView;
        }
    }

    public nv(Context context, com.huawei.openalliance.ad.views.interfaces.w wVar) {
        a(context, wVar);
    }

    public nv(Context context, AdLandingPageData adLandingPageData, com.huawei.openalliance.ad.views.interfaces.w wVar) {
        a(context, wVar);
        a(adLandingPageData);
    }
}
