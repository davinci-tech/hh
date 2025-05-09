package com.huawei.openalliance.ad.activity;

import android.content.res.Configuration;
import android.view.MenuItem;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.views.interfaces.q;

/* loaded from: classes5.dex */
public class OpenPrivacyPerActivity extends c {

    /* renamed from: a, reason: collision with root package name */
    private q f6571a;

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        q qVar = this.f6571a;
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
        ho.b("PriPerActivity", "initLayout");
        setContentView(R.layout.pure_web_activity_layout);
        String stringExtra = new SafeIntent(getIntent()).getStringExtra("url");
        this.f6571a = (q) findViewById(R.id.webview);
        if (cz.b(stringExtra)) {
            ho.d("PriPerActivity", "param err");
        } else {
            this.f6571a.a(stringExtra);
        }
    }
}
