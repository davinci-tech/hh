package com.huawei.health.marketing.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.TriggerEventBase;
import com.huawei.health.marketing.datatype.templates.MemberPromotionTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.eil;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.ntd;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class HealthFloatBar extends FrameLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    protected boolean f2870a;
    protected HealthButton b;
    protected Context c;
    protected MemberPromotionTemplate d;
    protected ImageView e;
    protected ImageView f;
    protected ImageView g;
    protected HealthTextView h;
    protected int i;
    protected int j;
    private TriggerEventBase k;
    private boolean l;
    private ResourceBriefInfo m;
    private long n;
    private int o;
    private int q;

    public HealthFloatBar(Context context) {
        this(context, R.layout.bottom_floating_bar);
    }

    public HealthFloatBar(Context context, int i) {
        super(context, null);
        this.f2870a = true;
        this.l = false;
        inflate(context, i, this);
        this.c = context;
    }

    public void a(ResourceBriefInfo resourceBriefInfo, int i) {
        LogUtil.a("HealthFloatBar", "initData resourceBriefInfo is", resourceBriefInfo.toString());
        setId(i);
        this.m = resourceBriefInfo;
        this.o = i;
        getTriggerPage();
        this.d = (MemberPromotionTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), MemberPromotionTemplate.class);
        e();
    }

    private void getTriggerPage() {
        ResourceBriefInfo resourceBriefInfo = this.m;
        if (resourceBriefInfo == null || resourceBriefInfo.getMarketingRule() == null) {
            return;
        }
        for (TriggerEventBase triggerEventBase : this.m.getMarketingRule().getTriggerEvents()) {
            if (triggerEventBase.getEventType() == 49) {
                this.k = triggerEventBase;
                return;
            }
        }
    }

    protected void e() {
        ImageView imageView = (ImageView) findViewById(R.id.marketing_bar_image);
        this.f = imageView;
        imageView.setOnClickListener(this);
        this.e = (ImageView) findViewById(R.id.marketing_close_icon);
        HealthButton healthButton = (HealthButton) findViewById(R.id.marketing_float_bar_button);
        this.b = healthButton;
        healthButton.setOnClickListener(this);
        this.h = (HealthTextView) findViewById(R.id.marketing_float_bar_text);
        d();
        this.i = nrr.e(this.c, 366.0f);
        this.j = nrr.e(this.c, 35.0f);
        boolean ag = nsn.ag(this.c);
        if (ag) {
            this.i = nrr.e(this.c, 485.0f);
            this.j = nrr.e(this.c, 36.0f);
        }
        ViewGroup.LayoutParams layoutParams = this.f.getLayoutParams();
        layoutParams.width = this.i;
        layoutParams.height = this.j;
        this.f.setLayoutParams(layoutParams);
        setTextParams(ag);
        setIconParams(ag);
        setLayoutParams(getParams());
        if (this.d == null) {
            return;
        }
        if (nsn.ag(this.c)) {
            setIconImage(this.f, this.d.getTahitiPicture());
        } else {
            setIconImage(this.f, this.d.getPicture());
        }
    }

    private void setTextParams(boolean z) {
        if (this.h.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.h.getLayoutParams();
            if (z) {
                marginLayoutParams.setMarginStart(nrr.e(this.c, 36.0f));
            } else {
                marginLayoutParams.setMarginStart(nrr.e(this.c, 48.0f));
            }
            this.h.setLayoutParams(marginLayoutParams);
        }
    }

    private void setIconParams(boolean z) {
        if (this.e.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.e.getLayoutParams();
            if (z) {
                marginLayoutParams.setMarginEnd(nrr.e(this.c, 0.0f));
            } else {
                marginLayoutParams.setMarginEnd(nrr.e(this.c, 10.0f));
            }
            this.e.setLayoutParams(marginLayoutParams);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.cLk_(view)) {
            LogUtil.a("HealthFloatBar", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.f || view == this.g || view == this.b) {
            eil.a(this.d.getLinkValue(), this.o, this.m);
            MarketingBiUtils.b(this.o, this.m, this.n);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.e.setVisibility(8);
        this.b.setVisibility(8);
        this.h.setVisibility(8);
        e();
    }

    public void setIconImage(ImageView imageView, String str) {
        if (!b() && !TextUtils.isEmpty(str)) {
            nrf.cIv_(str, new RequestOptions(), imageView);
            LogUtil.a("HealthFloatBar", "setIconImage after load");
            c(true);
            this.n = System.currentTimeMillis();
            MarketingBiUtils.d(this.o, this.m);
            return;
        }
        c(false);
    }

    protected void c(boolean z) {
        if (!z) {
            LogUtil.h("HealthFloatBar", "float image not loaded");
            return;
        }
        this.e.setVisibility(0);
        if (this.d.isButtonVisibility()) {
            this.b.setVisibility(0);
            this.b.setText(this.d.getButtonName());
        } else {
            this.b.setVisibility(8);
        }
        this.h.setVisibility(0);
        this.h.setText(this.d.getTheme());
    }

    private boolean b() {
        Context context = this.c;
        if (context instanceof Activity) {
            return ((Activity) context).isFinishing();
        }
        LogUtil.h("HealthFloatBar", "mContext is not activity.");
        return false;
    }

    protected void d() {
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.marketing.views.HealthFloatBar.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HealthFloatBar.this.h();
                MarketingBiUtils.c(HealthFloatBar.this.o, HealthFloatBar.this.m, HealthFloatBar.this.n);
                HealthFloatBar.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        ReleaseLogUtil.e("R_HealthFloatBar", "setResourceCloseRecord. closeTime = ", Long.valueOf(System.currentTimeMillis()), "; resourceName = ", this.m.getResourceName(), "; contentType = ", Integer.valueOf(this.m.getContentType()));
        eil.a(this.o, this.q, this.m);
        SharedPreferenceManager.e(Integer.toString(10000), "marketing_float_bar_close_time", System.currentTimeMillis());
    }

    private void apl_(Activity activity) {
        apm_(apn_(activity));
    }

    private void apm_(FrameLayout frameLayout) {
        if (frameLayout == null || !apo_(frameLayout)) {
            return;
        }
        setTag(Integer.valueOf(this.m.getContentType()));
        frameLayout.addView(this);
        this.l = true;
    }

    private FrameLayout apn_(Activity activity) {
        return (FrameLayout) activity.getWindow().getDecorView().findViewById(android.R.id.content);
    }

    protected FrameLayout.LayoutParams getParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.i, -2);
        layoutParams.gravity = 81;
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, 0, 200);
        return layoutParams;
    }

    public void setTypeId(int i) {
        this.q = i;
    }

    public void setPageVisibility(String str) {
        TriggerEventBase triggerEventBase = this.k;
        if (triggerEventBase == null) {
            e(0);
            this.f2870a = true;
        } else if (triggerEventBase.getEventValue().contains(str)) {
            LogUtil.a("HealthFloatBar", "setvisible visible");
            e(0);
            this.f2870a = true;
        } else {
            LogUtil.a("HealthFloatBar", "setvisible Gone");
            e(8);
            this.f2870a = false;
        }
    }

    protected void e(int i) {
        setVisibility(i);
        ObserverManagerUtil.c("HEALTH_FLOAT_BAR_STATE_CHANGE", Integer.valueOf(i));
        ntd.b().cMD_(this, true);
    }

    public void a() {
        if (this.l || b()) {
            return;
        }
        Context context = this.c;
        if (context instanceof Activity) {
            apl_((Activity) context);
        }
    }

    public void c() {
        if (b() || !this.l) {
            return;
        }
        Context context = this.c;
        if (context instanceof Activity) {
            app_((Activity) context);
        }
        ObserverManagerUtil.c("HEALTH_FLOAT_BAR_STATE_CHANGE", 8);
    }

    private void app_(Activity activity) {
        FrameLayout apn_ = apn_(activity);
        if (apn_ != null) {
            apn_.removeView(this);
        }
    }

    private boolean apo_(FrameLayout frameLayout) {
        int i = this.o;
        if (i == 0) {
            LogUtil.a("HealthFloatBar", "mPositionId = 0.");
            return true;
        }
        LogUtil.a("HealthFloatBar", "mPositionId = ", Integer.valueOf(i));
        HealthFloatBar healthFloatBar = (HealthFloatBar) frameLayout.findViewById(this.o);
        if (healthFloatBar == null) {
            LogUtil.a("HealthFloatBar", "floatBar == null");
        } else {
            LogUtil.a("HealthFloatBar", "floatBar exsit. positionId: ", Integer.valueOf(this.o));
        }
        return healthFloatBar == null;
    }
}
