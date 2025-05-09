package com.huawei.openalliance.ad.activity;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.tl;
import com.huawei.openalliance.ad.ts;
import com.huawei.openalliance.ad.utils.be;

/* loaded from: classes5.dex */
public class PPSShareActivity extends e {
    private tl b;

    /* renamed from: a, reason: collision with root package name */
    private boolean f6605a = true;
    private final DialogInterface.OnDismissListener c = new DialogInterface.OnDismissListener() { // from class: com.huawei.openalliance.ad.activity.PPSShareActivity.1
        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            PPSShareActivity.this.finish();
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onResume() {
        super.onResume();
        ho.b("PPSShareActivity", "onResume");
        if (this.f6605a) {
            this.f6605a = false;
            return;
        }
        tl tlVar = this.b;
        if (tlVar != null) {
            tlVar.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        tl tlVar = this.b;
        if (tlVar != null) {
            tlVar.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        ho.b("PPSShareActivity", "onCreate");
        super.onCreate(bundle);
        ts b = b();
        if (b == null) {
            finish();
            return;
        }
        tl tlVar = new tl(this, b);
        this.b = tlVar;
        tlVar.a();
        this.b.a(this.c);
        new com.huawei.openalliance.ad.analysis.c(getApplicationContext()).a("shareClick", b.f());
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        tl tlVar = this.b;
        if (tlVar != null) {
            tlVar.b();
        }
    }

    @Override // com.huawei.openalliance.ad.activity.e
    protected void a() {
        setContentView(R.layout.hiad_activity_share);
    }

    private ts b() {
        SafeIntent safeIntent = (SafeIntent) getIntent();
        try {
            ts tsVar = new ts(safeIntent.getStringExtra("title"), safeIntent.getStringExtra("description"), safeIntent.getStringExtra("imageUrl"), safeIntent.getStringExtra(JsbMapKeyNames.H5_CSHARE_URL));
            tsVar.a(getPackageManager().getApplicationInfo(getPackageName(), 0).icon);
            tsVar.a((ContentRecord) be.b(safeIntent.getStringExtra(MapKeyNames.CONTENT_RECORD), ContentRecord.class, new Class[0]));
            return tsVar;
        } catch (RuntimeException e) {
            ho.c("PPSShareActivity", "getIntentParams runtime exception: %s", e.getClass().getSimpleName());
            return null;
        } catch (Throwable th) {
            ho.c("PPSShareActivity", "getIntentParams error: %s", th.getClass().getSimpleName());
            return null;
        }
    }
}
