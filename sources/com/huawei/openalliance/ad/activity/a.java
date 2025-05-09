package com.huawei.openalliance.ad.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.NotifyMessageNames;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ji;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.x;
import com.huawei.openalliance.ad.views.ac;
import com.huawei.openalliance.ad.views.h;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class a extends e implements ji.a {
    private static Context s;

    /* renamed from: a, reason: collision with root package name */
    protected int f6613a;
    protected int b;
    protected int c;
    protected int d;
    protected RelativeLayout e;
    protected View f;
    protected View g;
    protected int[] h;
    protected int[] i;
    protected h j;
    protected h k;
    protected h l;
    protected ImageView m;
    protected ImageView n;
    protected ImageView o;
    private C0173a q;
    private boolean r = false;

    @Override // com.huawei.openalliance.ad.activity.e
    protected void a() {
    }

    protected void b() {
    }

    protected void c() {
    }

    protected int d() {
        return 0;
    }

    protected void e() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        k();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            requestWindowFeature(1);
            getWindow().setBackgroundDrawableResource(R.color._2131298002_res_0x7f0906d2);
            getWindow().setFlags(AppRouterExtras.COLDSTART, AppRouterExtras.COLDSTART);
            setContentView(d());
            s = getApplicationContext();
            h();
            if (!i()) {
                ho.c("BaseDialogActivity", "getIntentExtra return false");
                b();
                finish();
                return;
            }
            j();
            getWindow().addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
            c();
            n();
            l();
            m();
            g();
            o();
            e();
        } catch (Throwable th) {
            ho.c("BaseDialogActivity", "onCreate ex: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        finish();
    }

    protected void k() {
        try {
            C0173a c0173a = this.q;
            if (c0173a != null) {
                unregisterReceiver(c0173a);
            }
            ji.a().b(NotifyMessageNames.FEEDBACK_RECEIVE, this);
        } catch (Throwable th) {
            ho.c("BaseDialogActivity", "unRegisterReceiver: %s", th.getClass().getSimpleName());
        }
    }

    protected void j() {
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(1280);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
    }

    protected boolean i() {
        try {
            SafeIntent safeIntent = new SafeIntent(getIntent());
            this.h = safeIntent.getIntArrayExtra(MapKeyNames.ANCHOR_LOCATION);
            this.i = safeIntent.getIntArrayExtra(MapKeyNames.ANCHOR_SIZE);
            if (!a(this.h) && !a(this.i)) {
                if (dd.c()) {
                    int[] iArr = this.h;
                    int i = (this.f6613a - iArr[0]) - this.i[0];
                    iArr[0] = i;
                    ho.b("BaseDialogActivity", "rtl mAnchorViewLoc[x,y]= %d, %d", Integer.valueOf(i), Integer.valueOf(this.h[1]));
                }
                if (dd.b((Activity) this)) {
                    int y = dd.y(this);
                    int[] iArr2 = this.h;
                    iArr2[1] = iArr2[1] - y;
                    ho.a("BaseDialogActivity", "windowing mode is freeform");
                    ho.a("BaseDialogActivity", "initDevicesInfo dragBarHeight: %s", Integer.valueOf(y));
                }
                return true;
            }
            ho.c("BaseDialogActivity", "mAnchorViewLoc or mAnchorViewSize is unavailable");
            return false;
        } catch (Throwable th) {
            ho.c("BaseDialogActivity", "getIntentExtra error: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    protected void h() {
        int i;
        if (Build.VERSION.SDK_INT >= 30) {
            this.f6613a = getWindowManager().getCurrentWindowMetrics().getBounds().width();
            i = getWindowManager().getCurrentWindowMetrics().getBounds().height();
        } else {
            Point point = new Point();
            getWindowManager().getDefaultDisplay().getSize(point);
            this.f6613a = point.x;
            i = point.y;
        }
        this.b = i;
        ho.a("BaseDialogActivity", "initDevicesInfo screenWidth: %s, screenHeight: %s", Integer.valueOf(this.f6613a), Integer.valueOf(this.b));
        this.c = dd.k(this);
        this.d = ao.a(this, 22.0f);
    }

    protected void g() {
        ImageView imageView;
        float f;
        int a2 = ao.a(this, 36.0f);
        int i = this.d;
        int i2 = (this.f6613a - i) - a2;
        int i3 = (this.h[0] + (this.i[0] >> 1)) - (a2 >> 1);
        if (i3 >= i) {
            i = i3;
        }
        if (i <= i2) {
            i2 = i;
        }
        if (dd.c()) {
            imageView = this.o;
            f = -i2;
        } else {
            imageView = this.o;
            f = i2;
        }
        imageView.setX(f);
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
        ho.b("BaseDialogActivity", "finish");
        RelativeLayout relativeLayout = this.e;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(4);
        }
    }

    protected void f() {
        float f;
        int a2;
        int i;
        ho.b("BaseDialogActivity", "getRealOrientation orientation %s", Integer.valueOf(this.c));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.l.getLayoutParams();
        int abs = Math.abs((int) this.o.getX());
        int a3 = ao.a(this, 36.0f);
        int i2 = (a3 >> 1) + abs;
        double d = a3 * 0.5d;
        int viewWidthPercent = (int) ((this.f6613a * (1.0f - this.l.getViewWidthPercent()) * 0.5d) + ao.a(this, 16.0f) + d);
        int viewWidthPercent2 = (int) (((this.f6613a * ((this.l.getViewWidthPercent() * 0.5d) + 0.5d)) - ao.a(this, 16.0f)) - d);
        ho.a("BaseDialogActivity", "locationX: %s, locationX2: %s", Integer.valueOf(viewWidthPercent), Integer.valueOf(viewWidthPercent2));
        ho.a("BaseDialogActivity", "curImgX: %s, curImgWidth: %s, curImgCenter: %s", Integer.valueOf(abs), Integer.valueOf(a3), Integer.valueOf(i2));
        int i3 = this.c;
        if (1 != i3 && 9 != i3) {
            layoutParams.removeRule(14);
            this.l.setLayoutParams(layoutParams);
            int i4 = this.f6613a;
            if (i2 >= i4 / 3) {
                if (i2 < (i4 * 2) / 3) {
                    i = i2 - (this.l.getViewWith() >> 1);
                    this.l.setPaddingStart(i);
                    dd.a(this, new b(this));
                } else {
                    f = 16.0f;
                    abs = abs + a3 + ao.a(this, f);
                    a2 = this.l.getViewWith();
                }
            }
            a2 = ao.a(this, 16.0f);
        } else if (i2 < viewWidthPercent) {
            ho.a("BaseDialogActivity", "curImgCenter < locationX");
            layoutParams.removeRule(14);
            this.l.setLayoutParams(layoutParams);
            a2 = ao.a(this, 16.0f);
        } else {
            f = 16.0f;
            if (i2 <= viewWidthPercent2) {
                ho.a("BaseDialogActivity", "locationX =< curImgCenter =< locationX2");
                layoutParams.addRule(14);
                this.l.setLayoutParams(layoutParams);
                dd.a(this, new b(this));
            }
            ho.a("BaseDialogActivity", "curImgCenter > locationX2");
            layoutParams.removeRule(14);
            this.l.setLayoutParams(layoutParams);
            abs = abs + a3 + ao.a(this, f);
            a2 = this.l.getViewWith();
        }
        i = abs - a2;
        this.l.setPaddingStart(i);
        dd.a(this, new b(this));
    }

    @Override // com.huawei.openalliance.ad.ji.a
    public void a(String str, Intent intent) {
        if (TextUtils.isEmpty(str) || intent == null) {
            ho.b("BaseDialogActivity", "msgName or msgData is empty!");
            return;
        }
        ho.a("BaseDialogActivity", "onMessageNotify msgName:%s", str);
        try {
            String action = intent.getAction();
            ho.b("BaseDialogActivity", "FeedbackEventReceiver action = %s", action);
            if ("com.huawei.ads.feedback.action.ANCHOR_LOCATION_CHANGE".equals(action) || "com.huawei.ads.feedback.action.FINISH_FEEDBACK_ACTIVITY".equals(action)) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.activity.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ho.b("BaseDialogActivity", "anchor point changed, do finish.");
                        a.this.finish();
                    }
                });
            }
        } catch (Throwable th) {
            ho.c("BaseDialogActivity", "error: " + th.getClass().getSimpleName());
        }
    }

    private void o() {
        try {
            this.q = new C0173a();
            registerReceiver(this.q, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), "android.permission.WRITE_SECURE_SETTINGS", null);
            ao.a(getBaseContext(), this.q, new IntentFilter(Constants.CLICK_STATUS_BAR_ACTION), Constants.SYSTEM_UI_PERMISSION, null);
            ji.a().a(NotifyMessageNames.FEEDBACK_RECEIVE, this);
        } catch (Throwable th) {
            ho.c("BaseDialogActivity", "registerReceiver error: %s", th.getClass().getSimpleName());
        }
    }

    private void n() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.e.setForceDarkAllowed(false);
        }
    }

    private void m() {
        ViewGroup.LayoutParams layoutParams = this.f.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.width = this.h[0];
            layoutParams2.height = this.h[1];
            this.f.setLayoutParams(layoutParams2);
        }
        ViewGroup.LayoutParams layoutParams3 = this.g.getLayoutParams();
        if (layoutParams3 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
            layoutParams4.width = this.i[0];
            layoutParams4.height = this.i[1];
            this.g.setLayoutParams(layoutParams4);
        }
    }

    private void l() {
        int i;
        if (this.h[1] + (this.i[1] >> 1) > (this.b >> 1)) {
            this.k.setVisibility(8);
            this.m.setVisibility(0);
            this.n.setVisibility(8);
            this.l = this.j;
            this.o = this.m;
            int h = ao.h(this);
            if (bz.a(this).a(this)) {
                h = Math.max(h, bz.a(this).a(this.e));
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.l.getLayoutParams();
            layoutParams.setMargins(0, h, 0, 0);
            this.l.setLayoutParams(layoutParams);
            return;
        }
        this.j.setVisibility(8);
        this.m.setVisibility(8);
        this.n.setVisibility(0);
        this.l = this.k;
        this.o = this.n;
        boolean n = x.n(this);
        boolean z = x.o(this) && (1 == (i = this.c) || 9 == i);
        boolean z2 = x.q(this) && x.r(this);
        if (n || z || z2) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.l.getLayoutParams();
            layoutParams2.setMargins(0, 0, 0, Math.max(ao.a(this, 40.0f), dd.g(this)));
            this.l.setLayoutParams(layoutParams2);
        }
    }

    private boolean a(int[] iArr) {
        return iArr == null || iArr.length != 2;
    }

    /* renamed from: com.huawei.openalliance.ad.activity.a$a, reason: collision with other inner class name */
    class C0173a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                ho.b("BaseDialogActivity", "intent is empty");
                return;
            }
            String action = intent.getAction();
            ho.b("BaseDialogActivity", "FeedbackEventReceiver action = %s", action);
            if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action) || Constants.CLICK_STATUS_BAR_ACTION.equals(action)) {
                a.this.finish();
            }
        }

        private C0173a() {
        }
    }

    static class b implements ac {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<a> f6616a;

        @Override // com.huawei.openalliance.ad.views.ac
        public void a(int i) {
            a aVar = this.f6616a.get();
            if (aVar == null || aVar.r) {
                return;
            }
            ho.b("BaseDialogActivity", "got safePadding: %s", Integer.valueOf(i));
            aVar.a(i);
        }

        public b(a aVar) {
            this.f6616a = new WeakReference<>(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        h hVar = this.l;
        if (hVar != null) {
            hVar.a(i);
        }
        if (this.o != null) {
            this.d += i;
            g();
        }
        this.r = true;
    }
}
