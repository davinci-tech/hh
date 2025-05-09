package com.huawei.openalliance.ad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import com.huawei.openalliance.ad.inter.HiAd;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public abstract class jh implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    protected static final Map<View, jh> d = new ConcurrentHashMap();
    private View b;
    private boolean c;
    private long e;
    private int f;
    private BroadcastReceiver k;

    /* renamed from: a, reason: collision with root package name */
    private String f7122a = "ViewMonitor";
    private Rect g = new Rect();
    private boolean h = true;
    private boolean i = true;
    private BroadcastReceiver j = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.jh.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            ho.b(jh.this.f7122a, "receive screen state: %s", action);
            if (TextUtils.equals("android.intent.action.SCREEN_ON", action) || TextUtils.equals("android.intent.action.SCREEN_OFF", action) || TextUtils.equals("android.intent.action.USER_PRESENT", action)) {
                jh.this.d();
                jh.this.f();
            }
        }
    };

    protected void a() {
    }

    protected void a(int i) {
    }

    protected void a(long j, int i) {
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        if (ho.a()) {
            ho.a(this.f7122a, "onScrollChanged");
        }
        f();
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        if (ho.a()) {
            ho.a(this.f7122a, "onGlobalLayout");
        }
        f();
    }

    public boolean n() {
        return this.c && this.b.isShown();
    }

    public void m() {
        if (this.c) {
            ho.b(this.f7122a, "onViewHidden");
            this.c = false;
            long currentTimeMillis = System.currentTimeMillis() - this.e;
            if (ho.a()) {
                ho.a(this.f7122a, "max physical visible area percentage: %d duration: %d", Integer.valueOf(this.f), Long.valueOf(currentTimeMillis));
            }
            a(currentTimeMillis, this.f);
            this.f = 0;
        }
    }

    public void l() {
        if (this.c) {
            return;
        }
        ho.b(this.f7122a, "onViewShown");
        this.c = true;
        this.e = System.currentTimeMillis();
        a();
    }

    public int k() {
        boolean z = this.h && this.b.isShown() && this.b.getLocalVisibleRect(this.g);
        int width = this.b.getWidth() * this.b.getHeight();
        if (!z || width <= 0) {
            return 0;
        }
        return ((this.g.width() * this.g.height()) * 100) / width;
    }

    public void j() {
        ho.b(this.f7122a, "onViewVisibilityChanged");
        f();
    }

    public void i() {
        if (ho.a()) {
            ho.a(this.f7122a, "onViewDetachedFromWindow");
        }
        e();
        m();
    }

    public void h() {
        ho.b(this.f7122a, "onViewAttachedToWindow");
        c();
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0043, code lost:
    
        if (r2 <= 0) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void f() {
        /*
            r4 = this;
            boolean r0 = r4.h
            if (r0 == 0) goto L18
            android.view.View r0 = r4.b
            boolean r0 = r0.isShown()
            if (r0 == 0) goto L18
            android.view.View r0 = r4.b
            android.graphics.Rect r1 = r4.g
            boolean r0 = r0.getLocalVisibleRect(r1)
            if (r0 == 0) goto L18
            r0 = 1
            goto L19
        L18:
            r0 = 0
        L19:
            android.view.View r1 = r4.b
            int r1 = r1.getWidth()
            android.view.View r2 = r4.b
            int r2 = r2.getHeight()
            int r1 = r1 * r2
            if (r0 == 0) goto L46
            if (r1 <= 0) goto L46
            android.graphics.Rect r2 = r4.g
            int r2 = r2.width()
            android.graphics.Rect r3 = r4.g
            int r3 = r3.height()
            int r2 = r2 * r3
            int r2 = r2 * 100
            int r2 = r2 / r1
            int r1 = r4.f
            if (r2 <= r1) goto L40
            r4.f = r2
        L40:
            r4.a(r2)
            if (r2 > 0) goto L46
            goto L4c
        L46:
            if (r0 == 0) goto L4c
            r4.l()
            goto L4f
        L4c:
            r4.m()
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.jh.f():void");
    }

    private void e() {
        ho.b(this.f7122a, "unregisterObservers");
        View view = this.b;
        if (view == null) {
            return;
        }
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (this.i && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnGlobalLayoutListener(this);
            viewTreeObserver.removeOnScrollChangedListener(this);
        }
        this.b.setOnSystemUiVisibilityChangeListener(null);
        if (this.k != null) {
            HiAd.a(this.b.getContext()).a(this.k);
            this.k = null;
        }
        d.remove(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Context context = this.b.getContext();
        this.h = com.huawei.openalliance.ad.utils.dd.a(context) && !com.huawei.openalliance.ad.utils.dd.b(context);
        if (ho.a()) {
            ho.a(this.f7122a, "checkScreenState screen available: %s ", Boolean.valueOf(this.h));
        }
    }

    private void c() {
        ho.b(this.f7122a, "registerObservers");
        View view = this.b;
        if (view == null) {
            return;
        }
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        Map<View, jh> map = d;
        jh jhVar = map.get(this.b);
        if (jhVar != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(jhVar);
            viewTreeObserver.removeOnGlobalLayoutListener(jhVar);
        }
        map.put(this.b, this);
        if (this.i && viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
            viewTreeObserver.addOnScrollChangedListener(this);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.k = this.j;
        HiAd.a(this.b.getContext()).a(this.k, intentFilter);
        this.h = true;
    }

    private void b() {
        if (this.b != null) {
            this.f7122a = this.b.getClass().getSimpleName() + "ViewMonitor";
        }
    }

    public jh(View view) {
        this.b = view;
        b();
    }
}
