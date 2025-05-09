package com.huawei.sim.esim.view;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.sim.esim.view.adapter.WirelessAdapter;
import com.huawei.sim.multisim.MultiSimConfigActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.webview.WebViewActivity;
import defpackage.ixx;
import defpackage.ktx;
import defpackage.nbq;
import defpackage.nbu;
import defpackage.ncf;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes6.dex */
public class WirelessManagerActivity extends BaseActivity {
    private ListView b;
    private Context c;
    private WirelessAdapter d;
    private View f;
    private HealthTextView j;

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<nbu> f8698a = new ArrayList<>(16);
    private View.OnClickListener e = new View.OnClickListener() { // from class: com.huawei.sim.esim.view.WirelessManagerActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                WirelessManagerActivity.this.c.startActivity(new Intent(WirelessManagerActivity.this, (Class<?>) EsimActivationActivity.class));
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("WirelessManagerActivity", "esimListener ActivityNotFoundException");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private View.OnClickListener h = new View.OnClickListener() { // from class: com.huawei.sim.esim.view.WirelessManagerActivity.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("WirelessManagerActivity", "onclick multisim");
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MULTISIM_1170001.value(), new HashMap(16), 0);
            if (!WirelessManagerActivity.this.c()) {
                WirelessManagerActivity.this.e();
            } else {
                try {
                    Intent intent = new Intent("android.intent.action.MAIN");
                    intent.addCategory("android.intent.category.LAUNCHER");
                    intent.setComponent(new ComponentName("com.huawei.hwmultisim", "com.huawei.hwmultisim.views.IntroduceActivity"));
                    WirelessManagerActivity.this.startActivity(intent);
                } catch (Exception unused) {
                    LogUtil.b("WirelessManagerActivity", "can not start multisim apk");
                    WirelessManagerActivity.this.e();
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wireless_activity);
        this.c = this;
        ncf.g(this);
        b();
        d();
        nbq.e(this).c(true);
    }

    private void d() {
        ListView listView = (ListView) findViewById(R.id.listView);
        this.b = listView;
        listView.setAdapter((ListAdapter) this.d);
        this.j = (HealthTextView) findViewById(R.id.wirless_manager_txt);
        this.f = findViewById(R.id.wirless_manager_lint);
        if (ktx.e().o()) {
            this.j.setVisibility(0);
            this.f.setVisibility(0);
            String string = getString(R.string._2130847942_res_0x7f0228c6);
            String string2 = getString(R.string._2130847943_res_0x7f0228c7);
            String[] split = String.format(Locale.ENGLISH, string, string2).split(string2);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (split.length > 0) {
                SpannableString spannableString = new SpannableString(split[0]);
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color._2131299241_res_0x7f090ba9)), 0, spannableString.length(), 33);
                spannableStringBuilder.append((CharSequence) spannableString);
            }
            SpannableString spannableString2 = new SpannableString(string2);
            spannableString2.setSpan(new ClickableSpan() { // from class: com.huawei.sim.esim.view.WirelessManagerActivity.3
                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(WirelessManagerActivity.this.getResources().getColor(R.color._2131296319_res_0x7f09003f));
                    textPaint.setUnderlineText(false);
                }

                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    WirelessManagerActivity.this.a();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }, 0, spannableString2.length(), 33);
            spannableStringBuilder.append((CharSequence) spannableString2);
            if (split.length > 1) {
                SpannableString spannableString3 = new SpannableString(split[1]);
                spannableString3.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color._2131299241_res_0x7f090ba9)), 0, spannableString3.length(), 33);
                spannableStringBuilder.append((CharSequence) spannableString3);
            }
            this.j.setText(spannableStringBuilder);
            this.j.setMovementMethod(LinkMovementMethod.getInstance());
            this.j.setHighlightColor(ContextCompat.getColor(this, R.color._2131296323_res_0x7f090043));
            return;
        }
        this.j.setVisibility(8);
        this.f.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("WirelessManagerActivity", "startHelpInstructions enter");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.sim.esim.view.WirelessManagerActivity.5
            @Override // java.lang.Runnable
            public void run() {
                final String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainTipsResDbankcdn");
                WirelessManagerActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.sim.esim.view.WirelessManagerActivity.5.5
                    @Override // java.lang.Runnable
                    public void run() {
                        WirelessManagerActivity.this.e(url);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (this.c != null) {
            try {
                Intent intent = new Intent(this.c, (Class<?>) WebViewActivity.class);
                if (!Utils.o()) {
                    if (LanguageUtil.m(this.c)) {
                        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str + "/SmartWear/Leo/EMUI8.0/C001B001/zh-CN/zh-cn_bookmap_0047655720.html?pos=3");
                    } else {
                        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str + "/SmartWear/Leo/EMUI8.0/C001B001/en-US/content/en-us_bookmap_0209307492.html?pos=3");
                    }
                } else if (LanguageUtil.m(this.c)) {
                    intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str + "/handbook/SmartWear/Leo/EMUI8.0/C001B001/zh-CN/content/zh-cn_topic_0204333414.html?pos=7");
                } else {
                    intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str + "/handbook/SmartWear/Leo/EMUI8.0/C001B001/en-US/content/en-us_topic_0044851531.html?pos=8");
                }
                intent.putExtra("WebViewActivity.TITLE", getString(R.string._2130847943_res_0x7f0228c7));
                this.c.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("WirelessManagerActivity", "startWebView ActivityNotFoundException");
            }
        }
    }

    private void b() {
        nbu nbuVar = new nbu();
        if (ktx.e().o()) {
            LogUtil.a("WirelessManagerActivity", "create esim menu");
            if (LanguageUtil.m(this.c) && !Utils.o() && (CommonUtil.bh() || ncf.e())) {
                LogUtil.a("WirelessManagerActivity", "create multisim menu");
                nbu nbuVar2 = new nbu();
                nbuVar2.c(getString(R.string._2130847947_res_0x7f0228cb));
                nbuVar2.crJ_(this.h);
                this.f8698a.add(nbuVar2);
            }
            nbuVar.c(getString(R.string._2130847914_res_0x7f0228aa));
            nbuVar.b(getString(R.string._2130847916_res_0x7f0228ac));
            nbuVar.crJ_(this.e);
            this.f8698a.add(nbuVar);
            this.d = new WirelessAdapter(this.f8698a, this);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        nbq.e(this).c(false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        try {
            this.c.getPackageManager().getPackageInfo("com.huawei.hwmultisim", 128);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("WirelessManagerActivity", "no multisim apk");
            sqo.o("no multisim apk");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            this.c.startActivity(new Intent(this, (Class<?>) MultiSimConfigActivity.class));
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WirelessManagerActivity", "startMultiSimMenu ActivityNotFoundException");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
