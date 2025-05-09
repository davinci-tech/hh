package com.huawei.openalliance.ad.activity;

import android.R;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.huawei.appgallery.agd.api.PendingResult;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.openalliance.ad.a;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.qq;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.m;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class AgProtocolActivity extends f {
    private static final List<String> d;

    /* renamed from: a, reason: collision with root package name */
    String f6555a;
    int b;
    String c;
    private final com.huawei.openalliance.ad.a e = new a(this);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            if (HiAd.getInstance(this).getCurActivity() != null) {
                ho.b("resolution", "current activity not null, inherit activity attributes.");
                dd.a(this, HiAd.getInstance(this).getCurActivity());
            } else {
                ho.b("resolution", "current activity is null, skip inherit activity attributes.");
            }
            dd.a(getWindow().getDecorView().findViewById(R.id.content), this);
        } catch (Throwable th) {
            ho.c("resolution", "adaptRingScreen err: %s", th.getClass().getSimpleName());
        }
        super.onCreate(bundle);
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.activity.AgProtocolActivity.1
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = AgProtocolActivity.this.getIntent();
                try {
                    PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra(BaseGmsClient.KEY_PENDING_INTENT);
                    AgProtocolActivity.this.b = intent.getIntExtra("pendingIntent.type", 6);
                    AgProtocolActivity.this.f6555a = intent.getStringExtra("task.pkg");
                    AgProtocolActivity.this.c = intent.getStringExtra(MapKeyNames.AG_ACTION_NAME);
                    AgProtocolActivity.this.b();
                    int i = AgProtocolActivity.this.b == 6 ? 101 : AgProtocolActivity.this.b == 8888 ? 102 : 100;
                    Intent intent2 = new Intent();
                    Bundle bundle2 = new Bundle();
                    bundle2.putBinder(PendingResult.RESOLUTION_EXTRA_BUNDLE_BINDER, AgProtocolActivity.this.e.asBinder());
                    bundle2.putInt(PendingResult.RESOLUTION_EXTRA_BUNDLE_REQUESTCODE, i);
                    intent2.putExtra(PendingResult.RESOLUTION_EXTRA_BUNDLE, bundle2);
                    if (AgProtocolActivity.d.contains(AgProtocolActivity.this.getPackageName())) {
                        intent2.putExtra(PendingResult.RESOLUTION_EXTRA_AUTOFINISH, 1);
                    }
                    ho.b("resolution", "resolution type=" + AgProtocolActivity.this.b);
                    AgProtocolActivity.this.startIntentSenderForResult(pendingIntent.getIntentSender(), i, intent2, 0, 0, 0);
                } catch (Exception e) {
                    ho.b("resolution", " startIntentSenderForResult error:e=" + e.getClass().getName());
                    AgProtocolActivity.this.finish();
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        String str;
        String str2;
        int i3;
        super.onActivityResult(i, i2, intent);
        ho.b("resolution", "requestCode=" + i + "resultCode=" + i2);
        if (100 != i) {
            if (101 == i) {
                str = this.f6555a;
                str2 = this.c;
                i3 = 1003;
            } else if (102 == i) {
                if (i2 == -1) {
                    ho.b("resolution", "install hiapp");
                    str = this.f6555a;
                    str2 = this.c;
                    i3 = 1004;
                } else {
                    str = this.f6555a;
                    str2 = this.c;
                    i3 = 1005;
                }
            }
            com.huawei.openalliance.ad.download.ag.f.a(this, i3, str, str2, null);
        } else if (1001 == i2) {
            ho.b("resolution", "AG agree protocol");
            com.huawei.openalliance.ad.download.ag.f.a(this, 1001, this.f6555a, this.c, null);
        } else {
            ho.b("resolution", "AG disagree protocol");
            str = this.f6555a;
            str2 = this.c;
            i3 = 1002;
            com.huawei.openalliance.ad.download.ag.f.a(this, i3, str, str2, null);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        qq C;
        Context applicationContext = getApplicationContext();
        AppDownloadTask a2 = com.huawei.openalliance.ad.download.ag.d.a(applicationContext).a(this.f6555a);
        new com.huawei.openalliance.ad.analysis.c(applicationContext).a(this.f6555a, (a2 == null || (C = a2.C()) == null) ? null : C.a(), this.b, this.c, "openAgProtocolActivity");
    }

    static class a extends a.AbstractBinderC0171a {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<AgProtocolActivity> f6557a;

        @Override // com.huawei.openalliance.ad.a
        public void a(final int i) {
            ho.b("resolution", "onActivityCancel requestCode=" + i);
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.activity.AgProtocolActivity.a.1
                @Override // java.lang.Runnable
                public void run() {
                    AgProtocolActivity agProtocolActivity = a.this.f6557a == null ? null : (AgProtocolActivity) a.this.f6557a.get();
                    if (agProtocolActivity != null) {
                        agProtocolActivity.onActivityResult(i, 0, null);
                    }
                }
            });
        }

        public a(AgProtocolActivity agProtocolActivity) {
            this.f6557a = new WeakReference<>(agProtocolActivity);
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        d = arrayList;
        arrayList.add(Constants.HW_INTELLIEGNT_PACKAGE);
    }
}
