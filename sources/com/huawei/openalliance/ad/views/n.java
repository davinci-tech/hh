package com.huawei.openalliance.ad.views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
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
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class n extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    protected Context f8123a;
    int b;
    private AppInfo c;
    private TextView d;
    private TextView e;
    private ImageView f;
    private ImageView g;
    private Button h;
    private RelativeLayout i;
    private RelativeLayout j;
    private com.huawei.openalliance.ad.views.interfaces.y k;
    private Handler l;
    private Animator m;
    private ContentRecord n;
    private ng o;
    private View.OnClickListener p;

    public void setOnCloseListener(com.huawei.openalliance.ad.views.interfaces.y yVar) {
        this.k = yVar;
    }

    public void setDownloadSource(int i) {
        this.o.a(i);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void a(ContentRecord contentRecord, String str) {
        this.n = contentRecord;
        this.o = new ng(this.f8123a, contentRecord, 2);
        ContentRecord contentRecord2 = this.n;
        if (contentRecord2 == null || contentRecord2.ae() == null) {
            ho.b("PPSFullScreenNotifyOptimizeView", "contentRecord or appInfo is null");
            return;
        }
        this.c = this.n.ae();
        if (!TextUtils.isEmpty(str)) {
            this.c.s(str);
        }
        MetaData h = this.n.h();
        if (h != null) {
            this.c.k(h.n());
        }
        String appName = this.c.getAppName();
        if (TextUtils.isEmpty(appName)) {
            this.d.setVisibility(8);
        } else {
            this.d.setText(appName);
        }
        String o = this.c.o();
        if (!TextUtils.isEmpty(o)) {
            this.e.setText(o);
        }
        String p = this.c.p();
        if (!TextUtils.isEmpty(p)) {
            this.h.setText(p);
        }
        this.b = this.c.q();
        a(this.f, this.c.getIconUrl());
    }

    public void a() {
        this.l.post(new Runnable() { // from class: com.huawei.openalliance.ad.views.n.2
            @Override // java.lang.Runnable
            public void run() {
                if (n.this.m != null) {
                    n.this.m.start();
                    n.this.setVisibility(0);
                }
            }
        });
    }

    private void b() {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("scaleX", 0.8f, 1.05f, 1.0f), PropertyValuesHolder.ofFloat("scaleY", 0.8f, 1.05f, 1.0f), PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f, 1.0f));
        this.m = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setDuration(200L);
        this.m.setInterpolator(new LinearInterpolator());
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private String f8126a;
        private WeakReference<ImageView> b;
        private dk c;
        private Context d;

        @Override // java.lang.Runnable
        public void run() {
            if (this.d == null) {
                return;
            }
            rt rtVar = new rt();
            rtVar.b(false);
            rtVar.c(true);
            rtVar.a("icon");
            rtVar.c(this.f8126a);
            ru a2 = new rr(this.d, rtVar).a();
            if (a2 == null) {
                return;
            }
            String a3 = a2.a();
            if (TextUtils.isEmpty(a3)) {
                return;
            }
            String c = this.c.c(a3);
            if (TextUtils.isEmpty(c)) {
                return;
            }
            az.a(this.d, c, new az.a() { // from class: com.huawei.openalliance.ad.views.n.a.1
                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a() {
                }

                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a(final Drawable drawable) {
                    if (drawable == null) {
                        return;
                    }
                    dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.n.a.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ImageView imageView = (ImageView) a.this.b.get();
                            if (imageView == null) {
                                return;
                            }
                            imageView.setBackground(null);
                            imageView.setImageDrawable(drawable);
                        }
                    });
                }
            });
        }

        public a(String str, ImageView imageView, Context context) {
            this.f8126a = str;
            this.b = new WeakReference<>(imageView);
            context = context != null ? context.getApplicationContext() : context;
            this.d = context;
            this.c = dh.a(context, "normal");
        }
    }

    private void a(ImageView imageView, String str) {
        if (TextUtils.isEmpty(str) || imageView == null || this.f8123a == null) {
            return;
        }
        ho.b("PPSFullScreenNotifyOptimizeView", "load app icon:" + cz.f(str));
        com.huawei.openalliance.ad.utils.m.d(new a(str, imageView, this.f8123a));
    }

    private void a(Context context, AttributeSet attributeSet) {
        inflate(context, R.layout.hiad_full_screen_notity_optimize_layout, this);
        setVisibility(4);
        this.f8123a = context;
        this.f = (ImageView) findViewById(R.id.app_icon_optimize);
        this.d = (TextView) findViewById(R.id.app_name_tv_optimize);
        this.e = (TextView) findViewById(R.id.notify_tv_optimize);
        this.g = (ImageView) findViewById(R.id.app_close_optimize);
        this.i = (RelativeLayout) findViewById(R.id.app_view_optimize);
        this.j = (RelativeLayout) findViewById(R.id.app_valid_click_optimize);
        this.h = (Button) findViewById(R.id.app_open_btn_optimize);
        this.i.setOnClickListener(this.p);
        this.j.setOnClickListener(this.p);
        this.h.setOnClickListener(this.p);
        this.g.setOnClickListener(this.p);
        b();
        if (ao.n(context)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
            layoutParams.removeRule(15);
            this.f.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.g.getLayoutParams();
            layoutParams2.removeRule(15);
            this.g.setLayoutParams(layoutParams2);
        }
    }

    public n(Context context) {
        super(context);
        this.l = new Handler();
        this.p = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.n.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ho.b("PPSFullScreenNotifyOptimizeView", "onClick, insActvNotifyCfg: %s", Integer.valueOf(n.this.b));
                if (view.getId() != R.id.app_close_optimize) {
                    if (view.getId() == R.id.app_valid_click_optimize || view.getId() == R.id.app_open_btn_optimize || (view.getId() == R.id.app_view_optimize && n.this.b == 2)) {
                        n.this.o.b(n.this.b);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
                n.this.o.a("0");
                n.this.k.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        a(context, (AttributeSet) null);
    }
}
