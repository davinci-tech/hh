package com.huawei.openalliance.ad.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.activity.b;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.cs;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.x;
import com.huawei.openalliance.ad.views.interfaces.y;
import com.huawei.openalliance.ad.views.n;
import com.huawei.openalliance.ad.views.o;
import com.huawei.openalliance.ad.views.viewpager.WrapContentHeightGalleryView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class PPSFullScreenNotifyActivity extends b implements y {

    /* renamed from: a, reason: collision with root package name */
    private int f6589a;
    private int b;
    private int c;
    private View d;
    private View e;
    private o f;
    private n g;
    private ContentRecord h;
    private String i;
    private WrapContentHeightGalleryView j;
    private cs l;
    private b.a m;
    private Handler n;
    private int q;
    private List<View> k = new ArrayList();
    private boolean o = false;
    private com.huawei.openalliance.ad.views.viewpager.d r = new com.huawei.openalliance.ad.views.viewpager.d() { // from class: com.huawei.openalliance.ad.activity.PPSFullScreenNotifyActivity.2
        @Override // com.huawei.openalliance.ad.views.viewpager.d
        public void a(int i) {
        }

        @Override // com.huawei.openalliance.ad.views.viewpager.d
        public void a(int i, float f, int i2) {
        }

        @Override // com.huawei.openalliance.ad.views.viewpager.d
        public void b(int i) {
            if (i != 0 || PPSFullScreenNotifyActivity.this.j.getCurrentItem() == 1) {
                return;
            }
            ho.b("PPSFullScreenNotifyActivity", "onPageScrollStateChanged, state = " + i);
            com.huawei.openalliance.ad.analysis.a aVar = new com.huawei.openalliance.ad.analysis.a();
            aVar.b(cz.a((Object) 1));
            PPSFullScreenNotifyActivity.this.l.a(PPSFullScreenNotifyActivity.this.h, "3", aVar);
            PPSFullScreenNotifyActivity.this.c();
        }
    };

    private boolean b(int i) {
        return i == 1 || i == 2;
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    protected void onNewIntent(Intent intent) {
        ho.b("PPSFullScreenNotifyActivity", "onNewIntent");
        super.onNewIntent(intent);
        this.o = true;
        a(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        if (ho.a()) {
            ho.a("PPSFullScreenNotifyActivity", "onDestroy");
        }
        super.onDestroy();
        b((Context) this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.b, com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ho.b("PPSFullScreenNotifyActivity", "onCreate");
        a(getIntent());
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onBackPressed() {
        boolean V = fh.b(this).V();
        ho.b("PPSFullScreenNotifyActivity", "onBackPressed dialogDismiss: %S", Boolean.valueOf(V));
        if (V) {
            super.onBackPressed();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.y
    public void c() {
        o oVar = this.f;
        if (oVar != null) {
            oVar.setVisibility(8);
        }
        n nVar = this.g;
        if (nVar != null) {
            nVar.setVisibility(8);
        }
        finish();
    }

    @Override // com.huawei.openalliance.ad.activity.b
    protected void b() {
        Handler handler = this.n;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.huawei.openalliance.ad.activity.PPSFullScreenNotifyActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    PPSFullScreenNotifyActivity.this.finish();
                }
            }, 300L);
        }
    }

    public void a(int i) {
        int e = com.huawei.openalliance.ad.utils.d.e(this);
        int d = com.huawei.openalliance.ad.utils.d.d(this);
        if (i == 0 || i == 8) {
            this.f6589a = (x.n(this) || (x.q(this) && x.r(this))) ? (e * 2) / 3 : e / 2;
            this.b = e;
            return;
        }
        if (x.n(this) || (x.q(this) && x.r(this))) {
            this.f6589a = (d * 2) / 3;
        } else {
            this.f6589a = d;
        }
        this.b = d;
    }

    @Override // com.huawei.openalliance.ad.activity.e
    protected void a() {
        setContentView(R.layout.hiad_activity_full_screen_notify);
        this.p = (ViewGroup) findViewById(R.id.hiad_installed_notify_layout);
        this.p.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.activity.PPSFullScreenNotifyActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (1 == PPSFullScreenNotifyActivity.this.q) {
                    com.huawei.openalliance.ad.analysis.a aVar = new com.huawei.openalliance.ad.analysis.a();
                    aVar.b(cz.a((Object) 1));
                    PPSFullScreenNotifyActivity.this.l.a(PPSFullScreenNotifyActivity.this.h, "2", aVar);
                    PPSFullScreenNotifyActivity.this.c();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void f() {
        ArrayList arrayList = new ArrayList();
        this.k = arrayList;
        WrapContentHeightGalleryView wrapContentHeightGalleryView = this.j;
        if (wrapContentHeightGalleryView != null) {
            wrapContentHeightGalleryView.setAdapter(new com.huawei.openalliance.ad.download.app.o(arrayList));
        }
    }

    private void e() {
        ho.b("PPSFullScreenNotifyActivity", "initOptimizeView");
        f();
        n nVar = new n(this);
        this.g = nVar;
        nVar.setOnCloseListener(this);
        this.g.a(this.h, this.i);
        this.g.setDownloadSource(this.c);
        this.k.add(this.g);
        WrapContentHeightGalleryView wrapContentHeightGalleryView = (WrapContentHeightGalleryView) findViewById(R.id.hiad_view_pager);
        this.j = wrapContentHeightGalleryView;
        wrapContentHeightGalleryView.setAdapter(new com.huawei.openalliance.ad.download.app.o(this.k));
        this.j.setCurrentItem(1);
        this.g.a();
    }

    private void d() {
        ho.b("PPSFullScreenNotifyActivity", "initView");
        f();
        View view = new View(this);
        this.d = view;
        view.setBackgroundColor(0);
        o oVar = new o(this);
        this.f = oVar;
        oVar.setOnCloseListener(this);
        this.f.a(this.h, this.i);
        this.f.a(this.f6589a, this.b);
        this.f.setDownloadSource(this.c);
        View view2 = new View(this);
        this.e = view2;
        view2.setBackgroundColor(0);
        this.k.add(this.d);
        this.k.add(this.f);
        this.k.add(this.e);
        WrapContentHeightGalleryView wrapContentHeightGalleryView = (WrapContentHeightGalleryView) findViewById(R.id.hiad_view_pager);
        this.j = wrapContentHeightGalleryView;
        wrapContentHeightGalleryView.setAdapter(new com.huawei.openalliance.ad.download.app.o(this.k));
        this.j.setCurrentItem(1);
        this.j.a(this.r);
        this.f.a();
    }

    private void b(Context context) {
        b.a aVar = this.m;
        if (aVar != null) {
            context.unregisterReceiver(aVar);
            this.m = null;
        }
    }

    private void a(Intent intent) {
        String str;
        if (intent == null) {
            str = "intent is null";
        } else {
            SafeIntent safeIntent = new SafeIntent(intent);
            String stringExtra = safeIntent.getStringExtra(Constants.CONTENT_KEY);
            this.c = safeIntent.getIntExtra("download_source", 1);
            ContentRecord contentRecord = (ContentRecord) be.b(stringExtra, ContentRecord.class, new Class[0]);
            this.h = contentRecord;
            if (contentRecord != null && contentRecord.ae() != null) {
                AppInfo ae = this.h.ae();
                if (safeIntent.hasExtra("unique_id")) {
                    String stringExtra2 = safeIntent.getStringExtra("unique_id");
                    this.i = stringExtra2;
                    this.h.v(stringExtra2);
                }
                int k = dd.k(this);
                dd.a(this, k);
                a(k);
                int appActivateStyle = HiAd.getInstance(this).getAppActivateStyle();
                int n = ae.n();
                if (b(appActivateStyle)) {
                    this.q = appActivateStyle;
                } else if (b(n)) {
                    this.q = n;
                } else {
                    this.q = 1;
                }
                int i = this.q;
                if (i == 1) {
                    d();
                } else if (i == 2) {
                    e();
                }
                a((Context) this);
                this.l = new com.huawei.openalliance.ad.analysis.c(this);
                com.huawei.openalliance.ad.analysis.a aVar = new com.huawei.openalliance.ad.analysis.a();
                aVar.b(cz.a(Integer.valueOf(this.q)));
                this.l.a(this.h, "5", aVar);
                if (this.o) {
                    this.l.a(this.h, "4", aVar);
                    this.o = false;
                    return;
                }
                return;
            }
            str = "contentRecord or appInfo is null";
        }
        ho.b("PPSFullScreenNotifyActivity", str);
        finish();
    }

    private void a(Context context) {
        b(context);
        this.n = new Handler(Looper.myLooper());
        this.m = new b.a();
        ao.a(context, this.m, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), "android.permission.WRITE_SECURE_SETTINGS", null);
    }
}
