package com.huawei.openalliance.ad.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.text.TextUtils;
import android.view.MenuItem;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.NotifyMessageNames;
import com.huawei.openalliance.ad.constant.UrlConstant;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ji;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.openalliance.ad.views.interfaces.q;
import java.util.Locale;

/* loaded from: classes5.dex */
public class AdComplainActivity extends c implements ji.a {
    private static com.huawei.openalliance.ad.views.feedback.f c;

    /* renamed from: a, reason: collision with root package name */
    private q f6554a;
    private String b;

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
            ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
            return onOptionsItemSelected;
        }
        d();
        finish();
        ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ho.a("AdComplainActivity", "onDestroy");
        ji.a().b(NotifyMessageNames.AD_COMPLAIN_MESSAGE_NAME, this);
        q qVar = this.f6554a;
        if (qVar != null) {
            qVar.d();
        }
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        d();
    }

    @Override // com.huawei.openalliance.ad.activity.c
    public String b() {
        return this.b;
    }

    @Override // com.huawei.openalliance.ad.ji.a
    public void a(String str, Intent intent) {
        if (TextUtils.isEmpty(str) || intent == null) {
            ho.b("AdComplainActivity", "param is empty!");
            return;
        }
        ho.a("AdComplainActivity", "onMessageNotify msgName:%s", str);
        try {
            if (NotifyMessageNames.AD_COMPLAIN_ACTION.equals(new SafeIntent(intent).getAction())) {
                finish();
            }
        } catch (Throwable th) {
            ho.b("AdComplainActivity", "ad complain err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.activity.e
    protected void a() {
        ho.b("AdComplainActivity", "initLayout");
        if (Constants.MAGEZINE_PKG_NAME.equalsIgnoreCase(getApplicationContext().getPackageName())) {
            if (Build.VERSION.SDK_INT >= 27) {
                setShowWhenLocked(true);
            } else {
                ho.c("AdComplainActivity", "api is too low to support showWhenLocked.");
            }
        }
        setContentView(R.layout.pure_web_activity_layout);
        String stringExtra = new SafeIntent(getIntent()).getStringExtra(MapKeyNames.COMPLAIN_H5_TITLE);
        this.b = stringExtra;
        if (stringExtra == null) {
            this.b = "";
        }
        q qVar = (q) findViewById(R.id.webview);
        this.f6554a = qVar;
        qVar.a(c, Constants.GET_COMPLAIN_ADD_INFO_JS_NAME);
        String a2 = a(fh.b(this).a(this, UrlConstant.BASE_COMPLAIN_H5_URL));
        if (TextUtils.isEmpty(a2)) {
            ho.c("AdComplainActivity", "url is empty");
            finish();
        } else {
            ho.b("AdComplainActivity", "fullUrl= %s", dl.a(a2));
            this.f6554a.a(a2);
        }
        ji.a().a(NotifyMessageNames.AD_COMPLAIN_MESSAGE_NAME, this);
    }

    private void d() {
        com.huawei.openalliance.ad.views.feedback.f fVar = c;
        if (fVar != null) {
            fVar.b();
        }
    }

    public static void a(com.huawei.openalliance.ad.views.feedback.f fVar) {
        c = fVar;
    }

    private String a(String str) {
        if (cz.b(str)) {
            return "";
        }
        return str + Locale.getDefault().getLanguage().toLowerCase(Locale.getDefault()) + Constants.LINK + Locale.getDefault().getCountry().toLowerCase(Locale.getDefault());
    }
}
