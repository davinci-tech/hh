package com.huawei.openalliance.ad.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.analysis.h;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.openalliance.ad.views.interfaces.q;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class SplashFeedbackActivity extends c {

    /* renamed from: a, reason: collision with root package name */
    private q f6607a;
    private String b;

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
            ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
            return onOptionsItemSelected;
        }
        finish();
        ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
        return false;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private Context f6609a;
        private int b;
        private String c;

        @JavascriptInterface
        public void submit(String str, int i) {
            String a2;
            String str2;
            ho.a("SplashFeedbackActivity", "submit:%s %s", str, Integer.valueOf(i));
            h hVar = new h(this.f6609a.getApplicationContext());
            hVar.a("148", "" + this.b, "" + i, cz.d(str));
            if (i == 1) {
                hVar.a("147", "", "", "");
                Map<String, String> a3 = !TextUtils.isEmpty(fh.b(this.f6609a).ca()) ? be.a(fh.b(this.f6609a).ca()) : null;
                if (a3 == null) {
                    a3 = new HashMap<>();
                }
                int i2 = this.b;
                if (i2 == 1 || i2 == 4) {
                    a2 = cz.a(Long.valueOf(System.currentTimeMillis()));
                    str2 = Constants.SWIPE_TYPE;
                } else {
                    if (i2 == 2 || i2 == 3) {
                        a2 = cz.a(Long.valueOf(System.currentTimeMillis()));
                        str2 = Constants.TWIST_TYPE;
                    }
                    fh.b(this.f6609a).u(a3.toString());
                }
                a3.put(str2, a2);
                fh.b(this.f6609a).u(a3.toString());
            }
            back();
        }

        @JavascriptInterface
        public boolean openLinkInBrowser(String str) {
            return ao.a(this.f6609a, str);
        }

        @JavascriptInterface
        public boolean isDarkMode() {
            return dd.r(this.f6609a);
        }

        @JavascriptInterface
        public String getSplashFeedbackBtnText() {
            return this.c;
        }

        @JavascriptInterface
        public void back() {
            ho.a("SplashFeedbackActivity", "back");
            Context context = this.f6609a;
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        }

        public a(Context context, int i, String str) {
            this.f6609a = context;
            this.b = i;
            this.c = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        q qVar = this.f6607a;
        if (qVar != null) {
            qVar.d();
        }
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.openalliance.ad.activity.e
    protected void a() {
        ho.a("SplashFeedbackActivity", "initLayout");
        setContentView(R.layout.pure_web_activity_layout);
        new h(getApplicationContext()).a("146", "", "", "");
        int intExtra = new SafeIntent(getIntent()).getIntExtra(MapKeyNames.SPLASH_CLICKABLE_TYPE, 0);
        this.b = fh.b(this).bZ();
        q qVar = (q) findViewById(R.id.webview);
        this.f6607a = qVar;
        qVar.a(new a(this, intExtra, this.b), "_ISplashFeedbackJS");
        m.b(new Runnable() { // from class: com.huawei.openalliance.ad.activity.SplashFeedbackActivity.1
            @Override // java.lang.Runnable
            public void run() {
                String a2 = fh.b(SplashFeedbackActivity.this).a(SplashFeedbackActivity.this, "h5Server");
                String a3 = cz.a(SplashFeedbackActivity.this, "haid_h5_content_server");
                if (TextUtils.isEmpty(a2) && bz.a(SplashFeedbackActivity.this).d()) {
                    ho.b("SplashFeedbackActivity", "grs url return null or empty, use local defalut url.");
                    a2 = a3;
                }
                String a4 = SplashFeedbackActivity.this.a(a2 + "/cch5/pps-jssdk/h5-splashfeedback/index.html", SplashFeedbackActivity.this);
                if (TextUtils.isEmpty(a4)) {
                    ho.b("SplashFeedbackActivity", "url is null");
                    SplashFeedbackActivity.this.finish();
                } else {
                    ho.b("SplashFeedbackActivity", "url= %s", dl.a(a4));
                    SplashFeedbackActivity.this.f6607a.a(a4);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, Context context) {
        if (cz.b(str)) {
            return "";
        }
        return str + "?language=" + (Locale.getDefault().getLanguage().toLowerCase(Locale.getDefault()) + Constants.LINK + Locale.getDefault().getCountry().toUpperCase(Locale.getDefault())) + Constants.SCRIPT + dd.t(context) + Constants.VERSION + System.currentTimeMillis();
    }
}
