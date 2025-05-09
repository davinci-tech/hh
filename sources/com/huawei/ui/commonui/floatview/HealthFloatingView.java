package com.huawei.ui.commonui.floatview;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ixx;
import defpackage.nmh;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.ntd;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class HealthFloatingView extends FloatingSidingView {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8831a;
    private boolean b;
    private final ImageView c;
    private String d;
    private Context e;
    private OnCompleteListener f;
    private String g;
    private String h;
    private int i;
    private final ImageView j;
    private boolean k;
    private long l;

    public HealthFloatingView(Context context) {
        this(context, R.layout.commonui_custom_floating_view);
    }

    public HealthFloatingView(Context context, int i) {
        super(context, null);
        this.b = false;
        this.f8831a = false;
        this.i = 0;
        this.l = -1L;
        this.f = new OnCompleteListener() { // from class: com.huawei.ui.commonui.floatview.HealthFloatingView.3
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public void onComplete(Task task) {
                if (HealthFloatingView.this.b) {
                    HealthFloatingView.this.setVisibility(8);
                } else {
                    HealthFloatingView.this.setVisibility(0);
                }
            }
        };
        inflate(context, i, this);
        this.e = context;
        this.j = (ImageView) findViewById(R.id.icon);
        this.c = (ImageView) findViewById(R.id.close);
        h();
        setCompleteListener(this.f);
    }

    public void setPositionId(int i) {
        this.i = i;
    }

    public void setResourceId(String str) {
        this.h = str;
    }

    public void setResourceName(String str) {
        this.g = str;
    }

    public void setAlgId(String str) {
        this.d = str;
    }

    public void setSmartRecommend(boolean z) {
        this.k = z;
    }

    public boolean i() {
        return this.b;
    }

    public void setIconImage(Drawable drawable) {
        this.j.setImageDrawable(drawable);
    }

    public void setIconImage(Drawable drawable, RelativeLayout.LayoutParams layoutParams, RelativeLayout.LayoutParams layoutParams2) {
        this.c.setLayoutParams(layoutParams2);
        this.j.setLayoutParams(layoutParams);
        this.j.setImageDrawable(drawable);
    }

    public void setIconImage(Bitmap bitmap) {
        this.j.setImageBitmap(bitmap);
    }

    public void setIconImage(String str) {
        LogUtil.a("HealthFloatingView", "setIconImage imgUrl=", str);
        if (m()) {
            return;
        }
        nrf.cIF_(str, new RequestOptions(), new RequestListener<Drawable>() { // from class: com.huawei.ui.commonui.floatview.HealthFloatingView.5
            @Override // com.bumptech.glide.request.RequestListener
            /* renamed from: cAs_, reason: merged with bridge method [inline-methods] */
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                LogUtil.a("HealthFloatingView", "setIconImage onResourceReady resource=", drawable);
                HealthFloatingView.this.c.setVisibility(0);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                LogUtil.a("HealthFloatingView", "setIconImage onLoadFailed ", ExceptionUtils.d(glideException));
                return false;
            }
        }, this.j, false);
    }

    private boolean m() {
        Context context = this.e;
        return context == null || ((Activity) context).isFinishing();
    }

    private void h() {
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.floatview.HealthFloatingView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!TextUtils.isEmpty(HealthFloatingView.this.h) && HealthFloatingView.this.i != 0 && !TextUtils.isEmpty(HealthFloatingView.this.h)) {
                    HealthFloatingView.this.n();
                }
                HealthFloatingView.this.f();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        HashMap hashMap = new HashMap();
        hashMap.put("resourceId", this.h);
        hashMap.put("resourcePositionId", Integer.valueOf(this.i));
        hashMap.put("event", 4);
        hashMap.put("resourceName", this.g);
        hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.l));
        hashMap.put("algId", this.d);
        hashMap.put("smartRecommend", Boolean.valueOf(this.k));
        hashMap.put("pullOrder", 1);
        ixx.d().d(this.e.getApplicationContext(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    private boolean cAn_(Activity activity) {
        return cAo_(cAp_(activity));
    }

    private boolean cAo_(FrameLayout frameLayout) {
        if (frameLayout != null && cAq_(frameLayout)) {
            LogUtil.a("HealthFloatingView", "decorView EXSIT.");
            frameLayout.addView(this);
            this.f8831a = true;
        }
        return this.f8831a;
    }

    private boolean cAq_(FrameLayout frameLayout) {
        int i = this.i;
        if (i == 0) {
            LogUtil.a("HealthFloatingView", "mPositionId = 0.");
            return true;
        }
        LogUtil.a("HealthFloatingView", "mPositionId = ", Integer.valueOf(i));
        View findViewById = frameLayout.findViewById(this.i);
        HealthFloatingView healthFloatingView = findViewById instanceof HealthFloatingView ? (HealthFloatingView) findViewById : null;
        if (healthFloatingView == null) {
            LogUtil.a("HealthFloatingView", "floatingView == null");
        } else {
            LogUtil.a("HealthFloatingView", "floatingView exsit. positionId: ", Integer.valueOf(this.i));
        }
        return healthFloatingView == null;
    }

    private FrameLayout cAp_(Activity activity) {
        return (FrameLayout) activity.getWindow().getDecorView().findViewById(android.R.id.content);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.a("HealthFloatingView", "onConfig");
        super.onConfigurationChanged(configuration);
        o();
    }

    private FrameLayout.LayoutParams getParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = GravityCompat.END;
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, (int) this.e.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d), layoutParams.bottomMargin);
        return layoutParams;
    }

    public void a() {
        LogUtil.a("HealthFloatingView", "add box: ", Integer.valueOf(this.i));
        if (this.f8831a) {
            LogUtil.a("HealthFloatingView", "floatingview mIsAdd. no repeat.");
        } else {
            if (m()) {
                return;
            }
            c(this.e);
        }
    }

    public void cAr_(FrameLayout.LayoutParams layoutParams) {
        LogUtil.a("HealthFloatingView", "add box with layoutParams");
        if (this.f8831a || m()) {
            return;
        }
        setLayoutParams(layoutParams);
        cAn_((Activity) this.e);
    }

    private void c(Context context) {
        LogUtil.a("HealthFloatingView", "addFloatingView: ", Integer.valueOf(this.i));
        o();
        if (!cAn_((Activity) context)) {
            LogUtil.a("HealthFloatingView", "floating view already attached: ", Integer.valueOf(this.i));
        } else {
            nmh.a(this.i, this);
            nmh.a(this.i);
        }
    }

    private void o() {
        setLayoutParams(getParams());
        float e = nrr.e(this.e, getResources().getConfiguration().screenHeightDp - 280);
        LogUtil.a("HealthFloatingView", "floatY: ", Float.valueOf(e));
        setY(e);
    }

    public void f() {
        if (!m() && this.f8831a) {
            nmh.b(this.i);
            b(this.e);
        }
    }

    private void b(Context context) {
        FrameLayout cAp_ = cAp_((Activity) context);
        if (cAp_ != null) {
            cAp_.removeView(this);
        }
    }

    public void setlistener(View.OnClickListener onClickListener) {
        setClickListener(onClickListener);
    }

    public void setCloseBtnlistener(View.OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public void c() {
        LogUtil.a("HealthFloatingView", "hideView: ", Integer.valueOf(this.i));
        if (!this.b) {
            b();
        }
        ntd.b().cMD_(this, true);
        this.b = true;
    }

    public void j() {
        LogUtil.a("HealthFloatingView", "showView: ", Integer.valueOf(this.i));
        if (this.b) {
            setVisibility(0);
            e();
        }
        ntd.b().cMD_(this, true);
        this.b = false;
    }

    public void e(boolean z) {
        if (z) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
    }

    public void g() {
        if (this.b) {
            setVisibility(0);
        }
        this.b = false;
    }

    public void d() {
        if (!this.b) {
            setVisibility(8);
        }
        this.b = true;
    }
}
