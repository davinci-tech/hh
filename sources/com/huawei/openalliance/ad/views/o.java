package com.huawei.openalliance.ad.views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.ng;
import com.huawei.openalliance.ad.rr;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.ru;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dj;

/* loaded from: classes5.dex */
public class o extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    protected Context f8129a;
    private AppInfo b;
    private View c;
    private View d;
    private View e;
    private TextView f;
    private TextView g;
    private ImageView h;
    private ImageView i;
    private int j;
    private int k;
    private int l;
    private dk m;
    private com.huawei.openalliance.ad.views.interfaces.y n;
    private Handler o;
    private Animator p;
    private ContentRecord q;
    private ng r;
    private View.OnTouchListener s;

    public void setOnCloseListener(com.huawei.openalliance.ad.views.interfaces.y yVar) {
        this.n = yVar;
    }

    public void setDownloadSource(int i) {
        this.r.a(i);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.l = this.c.getMeasuredWidth();
        c();
    }

    public void a(ContentRecord contentRecord, String str) {
        this.q = contentRecord;
        this.r = new ng(this.f8129a, contentRecord, 1);
        ContentRecord contentRecord2 = this.q;
        if (contentRecord2 == null || contentRecord2.ae() == null) {
            ho.b("PPSFullScreenNotifyView", "contentRecord or appInfo is null");
            return;
        }
        this.b = this.q.ae();
        if (!TextUtils.isEmpty(str)) {
            this.b.s(str);
        }
        MetaData h = this.q.h();
        if (h != null) {
            this.b.k(h.n());
        }
        String appName = this.b.getAppName();
        if (!TextUtils.isEmpty(appName)) {
            this.f.setText(appName);
        }
        String o = this.b.o();
        if (!TextUtils.isEmpty(o)) {
            this.g.setText(o);
        }
        a(this.h, this.b.getIconUrl());
    }

    public void a(int i, int i2) {
        this.j = i;
        this.k = i2;
    }

    public void a() {
        this.o.post(new Runnable() { // from class: com.huawei.openalliance.ad.views.o.5
            @Override // java.lang.Runnable
            public void run() {
                if (o.this.p != null) {
                    o.this.p.start();
                    o.this.setVisibility(0);
                }
            }
        });
    }

    private void c() {
        if (this.j != this.k) {
            View findViewById = findViewById(R.id.app_view);
            ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
            layoutParams.width = (int) (this.j - com.huawei.openalliance.ad.utils.dk.a(this.f8129a, 24));
            findViewById.setLayoutParams(layoutParams);
            int i = (this.l - this.j) / 2;
            View findViewById2 = findViewById(R.id.layout_start);
            ViewGroup.LayoutParams layoutParams2 = findViewById2.getLayoutParams();
            layoutParams2.width = i;
            findViewById2.setLayoutParams(layoutParams2);
            View findViewById3 = findViewById(R.id.layout_end);
            ViewGroup.LayoutParams layoutParams3 = findViewById3.getLayoutParams();
            layoutParams3.width = i;
            findViewById3.setLayoutParams(layoutParams3);
        }
    }

    private void b() {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("scaleX", 0.8f, 1.05f, 1.0f), PropertyValuesHolder.ofFloat("scaleY", 0.8f, 1.05f, 1.0f), PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f, 1.0f));
        this.p = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setDuration(200L);
        this.p.setInterpolator(new LinearInterpolator());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(View view, MotionEvent motionEvent) {
        String str;
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                str = action == 3 ? "ACTION_CANCEL" : "ACTION_DOWN";
            } else {
                ho.b("PPSFullScreenNotifyView", "ACTION_UP");
                this.r.b(-1);
                this.n.c();
            }
            return true;
        }
        ho.b("PPSFullScreenNotifyView", str);
        return true;
    }

    private void a(ImageView imageView, String str) {
        if (TextUtils.isEmpty(str) || imageView == null) {
            return;
        }
        ho.b("PPSFullScreenNotifyView", "load app icon:" + cz.f(str));
        com.huawei.openalliance.ad.utils.m.d(new AnonymousClass6(str, imageView));
    }

    /* renamed from: com.huawei.openalliance.ad.views.o$6, reason: invalid class name */
    class AnonymousClass6 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8135a;
        final /* synthetic */ ImageView b;

        @Override // java.lang.Runnable
        public void run() {
            rt rtVar = new rt();
            rtVar.b(false);
            rtVar.c(true);
            rtVar.a("icon");
            rtVar.c(this.f8135a);
            ru a2 = new rr(o.this.f8129a, rtVar).a();
            if (a2 != null) {
                String a3 = a2.a();
                if (TextUtils.isEmpty(a3)) {
                    return;
                }
                String c = o.this.m.c(a3);
                if (TextUtils.isEmpty(c)) {
                    return;
                }
                az.a(o.this.f8129a, c, new az.a() { // from class: com.huawei.openalliance.ad.views.o.6.1
                    @Override // com.huawei.openalliance.ad.utils.az.a
                    public void a() {
                    }

                    @Override // com.huawei.openalliance.ad.utils.az.a
                    public void a(final Drawable drawable) {
                        if (drawable != null) {
                            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.o.6.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    AnonymousClass6.this.b.setBackground(null);
                                    AnonymousClass6.this.b.setImageDrawable(drawable);
                                }
                            });
                        }
                    }
                });
            }
        }

        AnonymousClass6(String str, ImageView imageView) {
            this.f8135a = str;
            this.b = imageView;
        }
    }

    private void a(Context context, AttributeSet attributeSet) {
        inflate(context, R.layout.hiad_full_screen_notity_layout, this);
        setVisibility(4);
        this.f8129a = context;
        this.m = dh.a(context, "normal");
        this.c = findViewById(R.id.app_layout);
        this.d = findViewById(R.id.layout_start);
        this.e = findViewById(R.id.layout_end);
        this.h = (ImageView) findViewById(R.id.app_icon);
        this.f = (TextView) findViewById(R.id.app_name_tv);
        this.g = (TextView) findViewById(R.id.notify_tv);
        ImageView imageView = (ImageView) findViewById(R.id.app_close);
        this.i = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.o.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                o.this.r.a("0");
                o.this.n.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.d.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.o.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return true;
                }
                o.this.r.a("2");
                o.this.n.c();
                return true;
            }
        });
        this.e.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.o.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return true;
                }
                o.this.r.a("2");
                o.this.n.c();
                return true;
            }
        });
        setOnTouchListener(this.s);
        b();
        if (ao.n(context)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.h.getLayoutParams();
            layoutParams.removeRule(15);
            this.h.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.i.getLayoutParams();
            layoutParams2.removeRule(15);
            this.i.setLayoutParams(layoutParams2);
        }
    }

    public o(Context context) {
        super(context);
        this.o = new Handler();
        this.s = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.o.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return o.this.a(view, motionEvent);
            }
        };
        a(context, (AttributeSet) null);
    }
}
