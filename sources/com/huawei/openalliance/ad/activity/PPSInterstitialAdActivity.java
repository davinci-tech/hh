package com.huawei.openalliance.ad.activity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.views.PPSInterstitialView;
import com.huawei.openalliance.ad.views.r;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class PPSInterstitialAdActivity extends e implements PPSInterstitialView.b {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6595a = new byte[0];
    private static final ConcurrentHashMap<String, com.huawei.openalliance.ad.inter.listeners.a> b = new ConcurrentHashMap<>();
    private com.huawei.openalliance.ad.inter.data.d c;
    private String d;
    private r e;
    private String f;
    private int g;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onResume() {
        ho.b("PPSInterstitialAdActivity", "onResume");
        r rVar = this.e;
        if (rVar != null) {
            rVar.resumeVideoView();
        }
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onPause() {
        ho.b("PPSInterstitialAdActivity", "onPause");
        r rVar = this.e;
        if (rVar != null) {
            rVar.pauseView();
        }
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        r rVar = this.e;
        if (rVar != null) {
            rVar.a();
            this.e.destroyView();
            this.e = null;
        }
    }

    class a implements com.huawei.openalliance.ad.inter.listeners.a {
        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void f() {
            com.huawei.openalliance.ad.inter.listeners.a aVar = (com.huawei.openalliance.ad.inter.listeners.a) PPSInterstitialAdActivity.b.get(PPSInterstitialAdActivity.this.f);
            if (aVar != null) {
                aVar.f();
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void e() {
            com.huawei.openalliance.ad.inter.listeners.a aVar = (com.huawei.openalliance.ad.inter.listeners.a) PPSInterstitialAdActivity.b.get(PPSInterstitialAdActivity.this.f);
            if (aVar != null) {
                aVar.e();
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void d() {
            com.huawei.openalliance.ad.inter.listeners.a aVar = (com.huawei.openalliance.ad.inter.listeners.a) PPSInterstitialAdActivity.b.get(PPSInterstitialAdActivity.this.f);
            if (aVar != null) {
                aVar.d();
            }
            PPSInterstitialAdActivity.this.finish();
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void c() {
            com.huawei.openalliance.ad.inter.listeners.a aVar = (com.huawei.openalliance.ad.inter.listeners.a) PPSInterstitialAdActivity.b.get(PPSInterstitialAdActivity.this.f);
            if (aVar != null) {
                aVar.c();
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void b() {
            com.huawei.openalliance.ad.inter.listeners.a aVar = (com.huawei.openalliance.ad.inter.listeners.a) PPSInterstitialAdActivity.b.get(PPSInterstitialAdActivity.this.f);
            if (aVar != null) {
                aVar.b();
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void a(int i, int i2) {
            com.huawei.openalliance.ad.inter.listeners.a aVar = (com.huawei.openalliance.ad.inter.listeners.a) PPSInterstitialAdActivity.b.get(PPSInterstitialAdActivity.this.f);
            if (aVar != null) {
                aVar.a(i, i2);
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void a() {
            com.huawei.openalliance.ad.inter.listeners.a aVar = (com.huawei.openalliance.ad.inter.listeners.a) PPSInterstitialAdActivity.b.get(PPSInterstitialAdActivity.this.f);
            if (aVar != null) {
                aVar.a();
            }
        }

        private a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ho.b("PPSInterstitialAdActivity", "onCreate");
        try {
            this.d = getIntent().getStringExtra("sdk_version");
            com.huawei.openalliance.ad.inter.data.d c = dc.c();
            this.c = c;
            if (c == null) {
                finish();
                return;
            }
            this.f = c.getUniqueId();
            dd.a(this, dd.k(this));
            this.e.setOnCloseListener(this);
            this.e.a(new a());
            this.e.a(this.c, getResources().getConfiguration().orientation, this.d);
        } catch (IllegalStateException e) {
            ho.c("PPSInterstitialAdActivity", "init interstitial ad " + e.getClass().getSimpleName());
        } catch (Throwable th) {
            ho.b("PPSInterstitialAdActivity", "init interstitial ad fail " + th.getClass().getSimpleName());
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
        com.huawei.openalliance.ad.inter.listeners.a aVar = b.get(this.f);
        if (aVar != null) {
            aVar.d();
        }
    }

    @Override // com.huawei.openalliance.ad.views.PPSInterstitialView.b
    public void b() {
        finish();
    }

    @Override // com.huawei.openalliance.ad.activity.e
    protected void a() {
        int bQ = fh.b(this).bQ();
        this.g = bQ;
        if (bQ != 1 && bQ != 0) {
            this.g = bz.a(this).d() ? 1 : 0;
        }
        ho.a("PPSInterstitialAdActivity", "iteAdFs %d", Integer.valueOf(this.g));
        r rVar = new r(this);
        this.e = rVar;
        setContentView(rVar);
        Window window = getWindow();
        try {
            if (this.g == 1) {
                window.getDecorView().setBackground(getResources().getDrawable(R.color._2131297901_res_0x7f09066d));
            } else {
                window.getDecorView().setBackground(getResources().getDrawable(R.color._2131297924_res_0x7f090684));
                window.getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() & (-17));
            }
            if (Build.VERSION.SDK_INT >= 28) {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.layoutInDisplayCutoutMode = 1;
                window.setAttributes(attributes);
            }
        } catch (Throwable th) {
            ho.b("PPSInterstitialAdActivity", "interstitial adapterONotch error " + th.getClass().getSimpleName());
        }
    }

    public static void a(String str, com.huawei.openalliance.ad.inter.listeners.a aVar) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "registerInterstitialAdStatusListener key is null";
        } else {
            if (aVar != null) {
                synchronized (f6595a) {
                    b.put(str, aVar);
                }
                return;
            }
            str2 = "registerInterstitialAdStatusListener listner is null";
        }
        ho.c("PPSInterstitialAdActivity", str2);
    }
}
