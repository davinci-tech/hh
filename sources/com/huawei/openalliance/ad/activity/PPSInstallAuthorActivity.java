package com.huawei.openalliance.ad.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.app.a;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.y;
import com.huawei.openalliance.ad.views.interfaces.g;

/* loaded from: classes5.dex */
public class PPSInstallAuthorActivity extends e {

    /* renamed from: a, reason: collision with root package name */
    private static g f6593a;
    private static int b;

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    protected void onNewIntent(Intent intent) {
        ho.b("PPSInstallAuthorActivity", "onNewIntent");
        super.onNewIntent(intent);
        a(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        e();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ho.b("PPSInstallAuthorActivity", "onCreate");
        a(getIntent());
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.openalliance.ad.activity.e
    protected void a() {
        setContentView(R.layout.hiad_activity_install_author);
    }

    private static void e() {
        f6593a = null;
    }

    static /* synthetic */ int c() {
        int i = b;
        b = i - 1;
        return i;
    }

    public static void a(g gVar) {
        if (gVar == null) {
            ho.c("PPSInstallAuthorActivity", "registerInstallListener is null");
        } else {
            f6593a = gVar;
        }
    }

    private void a(Intent intent) {
        final ContentRecord contentRecord = (ContentRecord) be.b(new SafeIntent(intent).getStringExtra(Constants.CONTENT_KEY), ContentRecord.class, new Class[0]);
        if (contentRecord == null) {
            ho.c("PPSInstallAuthorActivity", "init failed! contentRecord is null");
            finish();
            return;
        }
        b++;
        ho.a("PPSInstallAuthorActivity", "showDialogCnt is:" + b);
        y.a(this, contentRecord, new a.InterfaceC0176a() { // from class: com.huawei.openalliance.ad.activity.PPSInstallAuthorActivity.1
            @Override // com.huawei.openalliance.ad.download.app.a.InterfaceC0176a
            public void b() {
                PPSInstallAuthorActivity.c();
                if (PPSInstallAuthorActivity.b <= 0) {
                    int unused = PPSInstallAuthorActivity.b = 0;
                    ho.a("PPSInstallAuthorActivity", "close activity");
                    PPSInstallAuthorActivity.this.finish();
                }
            }

            @Override // com.huawei.openalliance.ad.download.app.a.InterfaceC0176a
            public void a() {
                ho.b("PPSInstallAuthorActivity", "continue install");
                if (PPSInstallAuthorActivity.f6593a != null) {
                    PPSInstallAuthorActivity.f6593a.a(com.huawei.openalliance.ad.download.app.e.h().a(contentRecord.ae().getPackageName()));
                }
            }
        });
    }
}
