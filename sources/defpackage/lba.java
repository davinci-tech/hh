package defpackage;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;
import com.huawei.indoorequip.viewmodel.IndoorEquipViewModel;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class lba extends IndoorTvBaseDisplay implements SportLifecycle {
    private static float e;

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f14739a;
    private RelativeLayout aa;
    private boolean ab;
    private ImageView ac;
    private boolean ad;
    private LinearLayout ae;
    private HealthTextView af;
    private HealthTextView ag;
    private String ah;
    private ImageView ai;
    private HealthTextView aj;
    private HealthTextView ak;
    private LinearLayout al;
    private HealthProgressBar am;
    private HealthTextView an;
    private int ao;
    private HealthTextView ap;
    private HealthTextView aq;
    private HealthTextView ar;
    private HealthTextView as;
    private ImageView at;
    private LinearLayout au;
    private ImageView av;
    private ImageView aw;
    private HealthTextView ax;
    private HealthTextView ay;
    private LinearLayout az;
    private HealthTextView b;
    private HealthTextView ba;
    private BaseRealTimeDynamicChartViewModel bb;
    private HealthTextView bc;
    private HealthTextView bd;
    private HealthTextView be;
    private ViewStub bf;
    private HealthTextView bg;
    private ImageView bh;
    private LinearLayout bj;
    private HealthTextView bk;
    private LinearLayout bm;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView f;
    private int g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private ImageView n;
    private HealthTextView o;
    private HealthTextView p;
    private LinearLayout q;
    private LinearLayout r;
    private LinearLayout s;
    private BaseRealTimeDynamicChartViewModel t;
    private Handler u;
    private BaseRealTimeDynamicChartViewModel v;
    private BaseRealTimeDynamicChartViewModel w;
    private boolean x;
    private boolean y;
    private boolean z;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        this.ao = 2;
        LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "tv sport state: ", 2);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        this.ao = 1;
        e(1);
        LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "tv sport state: ", Integer.valueOf(this.ao));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        this.ao = 1;
        e(1);
        LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "tv sport state: ", Integer.valueOf(this.ao));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        this.ao = 3;
        LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "tv sport state: ", 3);
    }

    public lba(Context context, Display display, boolean z, IndoorEquipViewModel indoorEquipViewModel) {
        super(context, display);
        this.z = false;
        this.x = false;
        this.ad = false;
        this.y = false;
        this.ab = false;
        this.ao = 0;
        this.ah = d(Locale.getDefault());
        this.u = new Handler() { // from class: lba.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    LogUtil.b("IDEQ_IndoorTreadmillTvDisplay", "msg == null");
                    return;
                }
                super.handleMessage(message);
                int i = message.what;
                if (i == 2020) {
                    LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "accept update ui msg");
                    if (message.obj instanceof HashMap) {
                        lba.this.d((HashMap) message.obj);
                        return;
                    }
                    return;
                }
                if (i == 10010) {
                    lba.this.mRunway.setBackgroundResource(R.drawable._2131431341_res_0x7f0b0fad);
                    lba.this.d(10020);
                } else {
                    if (i != 10020) {
                        return;
                    }
                    lba.this.mRunway.setBackgroundResource(R.drawable._2131431342_res_0x7f0b0fae);
                    lba.this.d(10010);
                }
            }
        };
        this.mHasWear = z;
        this.mContext = context;
        this.mViewModel = indoorEquipViewModel;
        if (this.mViewModel != null) {
            this.ao = this.mViewModel.getSportStatus();
            this.z = this.mViewModel.t();
            this.mHasRunPostureDevice = this.mViewModel.p();
        }
        LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "Constructor, hasRunPostureDevice = ", Boolean.valueOf(this.mHasRunPostureDevice), ",ishasWear = ", Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        Handler handler = this.u;
        if (handler != null && this.ao == 1) {
            handler.sendEmptyMessageDelayed(i, lbv.a(e));
            return;
        }
        if (handler != null) {
            handler.removeMessages(10010);
            this.u.removeMessages(10020);
        }
        this.y = false;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mHasRunPostureDevice) {
            setContentView(R.layout.ie_data_show_treadmill_aw70_tv);
            this.g = 1;
        } else {
            setContentView(R.layout.ie_data_show_treadmill_without_aw70_tv);
            this.g = 0;
        }
        initWindowConfig();
        e(this.ao);
        m();
        this.ab = true;
    }

    @Override // android.app.Presentation
    public void onDisplayRemoved() {
        LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "Treadmill tv display destroy.");
    }

    private void m() {
        BitmapDrawable cKn_;
        if (this.mHasRunPostureDevice) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.include_landscape_aw70_other_tv);
            this.ae = linearLayout;
            linearLayout.setVisibility(0);
            if (this.mViewModel.q()) {
                findViewById(R.id.view_zero_one_line).setVisibility(0);
                findViewById(R.id.rl_three_line_data).setVisibility(8);
                findViewById(R.id.view_three_four_line).setVisibility(8);
                findViewById(R.id.rl_four_line_data).setVisibility(8);
            }
        } else {
            this.ae = (LinearLayout) findViewById(R.id.leftlayout);
        }
        this.m = (HealthTextView) findViewById(R.id.duration_type);
        this.d = (HealthTextView) findViewById(R.id.calories_type);
        this.f14739a = (LinearLayout) findViewById(R.id.calories_layout);
        this.b = (HealthTextView) findViewById(R.id.calories_value);
        this.c = (HealthTextView) findViewById(R.id.calories_unit);
        this.bc = (HealthTextView) findViewById(R.id.step_type);
        this.au = (LinearLayout) findViewById(R.id.step_layout);
        this.az = (LinearLayout) findViewById(R.id.step_type_layout);
        this.at = (ImageView) findViewById(R.id.step_device);
        this.aq = (HealthTextView) findViewById(R.id.step_value);
        this.bg = (HealthTextView) findViewById(R.id.step_unit);
        initNormalView();
        this.mLogoLayout = (LinearLayout) findViewById(R.id.logoLayout);
        this.mRunway = (ImageView) findViewById(R.id.runway);
        this.mRunwayBackground = (ImageView) findViewById(R.id.runwaybackground);
        this.am = (HealthProgressBar) findViewById(R.id.hw_recycler_loading_hpb);
        this.mBtIcon = (ImageView) findViewById(R.id.ie_bt_icon);
        if (LanguageUtil.bc(this.mContext) && (cKn_ = nrz.cKn_(this.mContext, R.id.ie_bt_icon)) != null) {
            this.mBtIcon.setImageDrawable(cKn_);
        }
        this.mBtBoltConnectIcon = (ImageView) findViewById(R.id.ie_bolt_icon);
        if (this.z) {
            this.mBtBoltConnectIcon.setVisibility(0);
            this.mBtBoltConnectIcon.setImageResource(lbv.a(this.mViewModel.n()));
            changeBtBoltIconSize();
        }
        this.mLogoImage = (ImageView) findViewById(R.id.logoImage);
        this.ak = (HealthTextView) findViewById(R.id.logoText);
        i();
        a();
        c();
        g();
        y();
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void initNormalView() {
        findViewById(R.id.view_stub_speed).setVisibility(0);
        if (isTargetType()) {
            initTargetLayout();
            if (lbf.c(this.mViewModel)) {
                ((LinearLayout) findViewById(R.id.distance_left_view)).setVisibility(0);
                findViewById(R.id.duration_left_view).setVisibility(8);
                l();
            }
            if (lbf.a(this.mViewModel)) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.distance_right_view);
                this.i = (HealthTextView) findViewById(R.id.distance_right_value);
                this.f = (HealthTextView) findViewById(R.id.distance_right_type);
                linearLayout.setVisibility(0);
                bVo_(linearLayout);
                k();
            }
        } else {
            ViewStub viewStub = (ViewStub) findViewById(R.id.view_stub_heartrate_distance);
            this.bf = viewStub;
            viewStub.setVisibility(0);
        }
        this.m.setText(R.string._2130839907_res_0x7f020963);
        this.k = (HealthTextView) findViewById(R.id.duration_value);
        this.j = (HealthTextView) findViewById(R.id.distance_value);
        this.h = (HealthTextView) findViewById(R.id.distance_unit);
        this.ar = (HealthTextView) findViewById(R.id.speed_type);
        this.as = (HealthTextView) findViewById(R.id.speed_value);
        this.ap = (HealthTextView) findViewById(R.id.speed_unit);
        this.aa = (RelativeLayout) findViewById(R.id.heartrate_layout);
        this.mHeartRateImage = (ImageView) findViewById(R.id.heartrate_image);
        this.ac = (ImageView) findViewById(R.id.heartrate_device);
        this.mBackground = (ImageView) findViewById(R.id.background_layout_tv);
        if (this.mHasWear) {
            this.mHeartRateType = (HealthTextView) findViewById(R.id.heartrate_type);
            this.mHeartRate = (HealthTextView) findViewById(R.id.heartrate_value);
            this.mHeartRateUnit = (HealthTextView) findViewById(R.id.heartrate_unit);
        }
    }

    private void l() {
        this.i = (HealthTextView) findViewById(R.id.distance_left_value);
        this.f = (HealthTextView) findViewById(R.id.distance_left_type);
        if (this.mHasRunPostureDevice) {
            a(this.i, 105.0f);
            a(this.f, 105.0f);
        }
    }

    private void k() {
        View findViewById = findViewById(R.id.calorie_right_view);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        } else {
            this.f14739a.setVisibility(8);
        }
    }

    private void a() {
        if (this.mHasWear) {
            q();
            ThreadPoolManager.d().execute(new Runnable() { // from class: lbe
                @Override // java.lang.Runnable
                public final void run() {
                    lba.this.b();
                }
            });
        } else {
            p();
        }
        if (this.mHasRunPostureDevice) {
            o();
            n();
            return;
        }
        this.al = (LinearLayout) findViewById(R.id.rightlayout);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.step_frequency_value);
        this.ax = healthTextView;
        healthTextView.setTextSize(0, this.mScale * this.ax.getTextSize());
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.step_frequency_type);
        this.ay = healthTextView2;
        healthTextView2.setTextSize(0, this.mScale * this.ay.getTextSize());
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.step_frequency_unit);
        this.ba = healthTextView3;
        healthTextView3.setTextSize(0, this.mScale * this.ba.getTextSize());
        this.aw = (ImageView) findViewById(R.id.step_frequency_device);
        this.av = (ImageView) findViewById(R.id.step_frequency_arrow);
        HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.pace_type);
        this.an = healthTextView4;
        healthTextView4.setTextSize(0, this.mScale * this.an.getTextSize());
        HealthTextView healthTextView5 = (HealthTextView) findViewById(R.id.pace_value);
        this.aj = healthTextView5;
        healthTextView5.setTextSize(0, this.mScale * this.aj.getTextSize());
    }

    /* synthetic */ void b() {
        if (this.mViewModel != null) {
            initHeartRateZone(this.mViewModel.getSportType());
        }
    }

    private void p() {
        ag();
        if (isTargetType()) {
            return;
        }
        this.aa.setVisibility(8);
        this.j.setTextSize(0, dp2px(80.0f) * this.mScale);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, nsn.c(this.mContext, this.mScale * 3.0f));
        layoutParams.addRule(14);
        this.j.setLayoutParams(layoutParams);
        this.h.setTextSize(0, dp2px(13.0f) * this.mScale);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(3, R.id.distance_value);
        layoutParams2.addRule(14);
        this.h.setLayoutParams(layoutParams2);
    }

    private void q() {
        if (isTargetType()) {
            ag();
            return;
        }
        this.aa.setVisibility(0);
        if (this.mHeartRateType.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mHeartRateType.getLayoutParams();
            layoutParams.width = (int) (this.mScale * dp2px(85.0f));
            this.mHeartRateType.setLayoutParams(layoutParams);
            ViewGroup.LayoutParams layoutParams2 = this.bf.getLayoutParams();
            layoutParams2.width = -2;
            this.bf.setLayoutParams(layoutParams2);
        }
    }

    private void ag() {
        this.mBackground.setVisibility(0);
        changeBackgroundSize();
    }

    private void n() {
        if (this.bd.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.bd.getLayoutParams();
            layoutParams.width = (int) (this.mScale * dp2px(93.3f));
            this.bd.setLayoutParams(layoutParams);
        }
        if (this.p.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.p.getLayoutParams();
            layoutParams2.width = (int) (this.mScale * dp2px(80.0f));
            this.p.setLayoutParams(layoutParams2);
        }
        if (this.af.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.af.getLayoutParams();
            layoutParams3.width = (int) (this.mScale * dp2px(93.3f));
            this.af.setLayoutParams(layoutParams3);
        }
        if (this.ag.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.ag.getLayoutParams();
            layoutParams4.width = (int) (this.mScale * dp2px(93.3f));
            this.ag.setLayoutParams(layoutParams4);
        }
    }

    private void i() {
        if (this.mHasRunPostureDevice) {
            this.k.setTextSize(0, dp2px(20.0f));
            this.as.setTextSize(0, dp2px(20.0f));
        } else {
            this.ap.setTextSize(0, dp2px(13.0f));
        }
        this.as.setTextSize(0, this.mScale * this.as.getTextSize());
        this.ap.setTextSize(0, this.mScale * this.ap.getTextSize());
        this.ap.setMaxWidth((int) (this.mScale * this.ap.getMaxWidth()));
        this.ar.setTextSize(0, this.mScale * this.ar.getTextSize());
        if (this.mHasWear && !isTargetType()) {
            this.mHeartRateType.setTextSize(0, this.mScale * this.mHeartRateType.getTextSize());
            this.mHeartRate.setTextSize(0, this.mScale * this.mHeartRate.getTextSize());
            this.mHeartRateUnit.setTextSize(0, this.mScale * this.mHeartRateUnit.getTextSize());
            this.mHeartRateUnit.setMaxWidth((int) (this.mScale * this.mHeartRateUnit.getMaxWidth()));
        }
        if (!isTargetType()) {
            this.j.setTextSize(0, this.mScale * this.j.getTextSize());
            this.h.setTextSize(0, this.mScale * this.h.getTextSize());
        } else {
            HealthTextView healthTextView = this.i;
            if (healthTextView != null && this.f != null) {
                healthTextView.setTextSize(0, this.mScale * this.i.getTextSize());
                this.f.setTextSize(0, this.mScale * this.f.getTextSize());
            }
        }
        this.k.setTextSize(0, this.mScale * this.k.getTextSize());
        this.ak.setTextSize(0, this.mScale * this.ak.getTextSize());
        this.m.setTextSize(0, this.mScale * this.m.getTextSize());
        this.d.setTextSize(0, this.mScale * this.d.getTextSize());
        this.b.setTextSize(0, this.mScale * this.b.getTextSize());
        this.c.setTextSize(0, this.mScale * this.c.getTextSize());
        this.bc.setTextSize(0, this.mScale * this.bc.getTextSize());
        this.aq.setTextSize(0, this.mScale * this.aq.getTextSize());
        this.bg.setTextSize(0, this.mScale * this.bg.getTextSize());
        f();
    }

    private void f() {
        HealthTextView healthTextView;
        if ((LanguageUtil.aa(this.mContext) || LanguageUtil.bf(this.mContext) || LanguageUtil.bb(this.mContext) || LanguageUtil.an(this.mContext) || LanguageUtil.ba(this.mContext) || LanguageUtil.q(this.mContext) || LanguageUtil.ag(this.mContext)) && this.mHeartRateUnit != null) {
            this.mHeartRateUnit.setTextSize(0, this.mHeartRateUnit.getTextSize() * 0.7f);
        }
        if ((LanguageUtil.az(this.mContext) || LanguageUtil.c(this.mContext) || LanguageUtil.ba(this.mContext) || LanguageUtil.ay(this.mContext)) && (healthTextView = this.ag) != null) {
            healthTextView.setTextSize(0, healthTextView.getTextSize() * 0.7f);
        }
    }

    private void c() {
        ImageView imageView;
        changeBtIconSize();
        changeRunwaySize();
        bVm_(this.at);
        changeLogoSize();
        ImageView imageView2 = this.aw;
        if (imageView2 != null) {
            bVm_(imageView2);
        }
        if (this.mHasWear) {
            e();
        }
        if (this.mHasRunPostureDevice && (imageView = this.ai) != null) {
            if (imageView.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ai.getLayoutParams();
                layoutParams.width = (int) (this.mScale * dp2px(40.0f));
                layoutParams.height = (int) (this.mScale * dp2px(40.0f));
                layoutParams.setMarginStart((int) (this.mScale * dp2px(137.8f)));
                this.ai.setLayoutParams(layoutParams);
            }
            if (this.bh.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.bh.getLayoutParams();
                layoutParams2.width = (int) (this.mScale * dp2px(5.0f));
                layoutParams2.setMarginStart((int) (this.mScale * dp2px(4.0f)));
                this.bh.setLayoutParams(layoutParams2);
            }
            if (this.n.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.n.getLayoutParams();
                layoutParams3.width = (int) (this.mScale * dp2px(5.0f));
                LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "changeImageSize before", Float.valueOf(this.mContext.getResources().getDisplayMetrics().density));
                LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "changeImageSize after", Float.valueOf(this.mCurrentDisplayDensity));
                layoutParams3.setMarginStart((int) (this.mScale * dp2px(4.0f)));
                this.n.setLayoutParams(layoutParams3);
            }
            if (this.al.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.al.getLayoutParams();
                layoutParams4.width = (int) (this.mScale * dp2px(180.0f));
                LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "RightLayout.width = ", Float.valueOf(this.mScale * dp2px(180.0f)));
                this.al.setLayoutParams(layoutParams4);
                return;
            }
            return;
        }
        ai();
    }

    private void ai() {
        if (this.av.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.av.getLayoutParams();
            layoutParams.width = (int) (this.mScale * dp2px(5.0f));
            layoutParams.setMargins(0, 0, 0, (int) (this.mScale * dp2px(6.0f)));
            layoutParams.setMarginEnd((int) (this.mScale * dp2px(4.0f)));
            this.av.setLayoutParams(layoutParams);
        }
    }

    private void e() {
        if (isTargetType()) {
            return;
        }
        if (this.mHeartRateImage.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mHeartRateImage.getLayoutParams();
            layoutParams.width = (int) (this.mScale * dp2px(196.0f));
            layoutParams.height = (int) (this.mScale * dp2px(163.0f));
            this.mHeartRateImage.setLayoutParams(layoutParams);
        }
        if (this.ac.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.ac.getLayoutParams();
            layoutParams2.width = (int) (this.mScale * dp2px(12.0f));
            layoutParams2.height = (int) (this.mScale * dp2px(12.0f));
            this.ac.setLayoutParams(layoutParams2);
        }
    }

    private void g() {
        ImageView imageView;
        d();
        changeLogoMargin();
        if (this.g == 1) {
            h();
        } else if (this.al.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.al.getLayoutParams();
            layoutParams.setMargins(0, (int) (this.mScale * dp2px(73.5f)), 0, (int) (this.mScale * dp2px(73.5f)));
            layoutParams.setMarginStart(((int) this.mScale) * dp2px(146.5f));
            layoutParams.width = ((int) this.mScale) * dp2px(184.8f);
            this.al.setLayoutParams(layoutParams);
        }
        if (this.mHasWear && (imageView = this.ac) != null) {
            if (imageView.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.ac.getLayoutParams();
                layoutParams2.setMargins(0, (int) (this.mScale * dp2px(2.0f)), 0, 0);
                this.ac.setLayoutParams(layoutParams2);
                if (this.mHeartRateType != null && (this.mHeartRateType.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mHeartRateType.getLayoutParams();
                    layoutParams3.setMargins(0, (int) (this.mScale * dp2px(43.5f)), 0, 0);
                    this.mHeartRateType.setLayoutParams(layoutParams3);
                }
            } else if (this.ac.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.ac.getLayoutParams();
                layoutParams4.setMargins(4, 0, 0, 0);
                this.ac.setLayoutParams(layoutParams4);
            }
        }
        HealthTextView healthTextView = this.h;
        if (healthTextView == null || !(healthTextView.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            return;
        }
        ((RelativeLayout.LayoutParams) this.h.getLayoutParams()).setMarginStart((int) (this.mScale * dp2px(24.0f)));
    }

    private void h() {
        bVo_(this.f14739a);
        bVo_(this.au);
        bVo_(this.r);
        if (this.ai.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ai.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            layoutParams.setMarginStart((int) (this.mScale * dp2px(137.8f)));
            this.ai.setLayoutParams(layoutParams);
        }
        if (this.al.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.al.getLayoutParams();
            layoutParams2.setMargins(0, (int) (this.mScale * dp2px(52.0f)), 0, (int) (this.mScale * dp2px(51.8f)));
            layoutParams2.setMarginEnd((int) (this.mScale * dp2px(20.0f)));
            this.al.setLayoutParams(layoutParams2);
        }
        ae();
        ah();
    }

    private void ae() {
        a(this.k, 105.0f);
        a(this.m, 105.0f);
        a(this.as, 105.0f);
        a(this.ar, 105.0f);
        a(this.ag, 105.0f);
        a(this.af, 105.0f);
        bVn_(this.bm, 105.0f);
        bVn_(this.bj, 105.0f);
    }

    private void bVn_(LinearLayout linearLayout, float f) {
        if (linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.width = (int) (this.mScale * dp2px(f));
            linearLayout.setLayoutParams(layoutParams);
        }
    }

    private void a(HealthTextView healthTextView, float f) {
        if (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
            layoutParams.width = (int) (this.mScale * dp2px(f));
            healthTextView.setLayoutParams(layoutParams);
        }
    }

    private void ah() {
        a(this.b, 100.0f);
        a(this.d, 100.0f);
        a(this.aq, 100.0f);
        if (lbf.a(this.mViewModel)) {
            a(this.i, 100.0f);
        }
        bVn_(this.az, 100.0f);
        bVn_(this.s, 100.0f);
        bVn_(this.q, 100.0f);
    }

    private void bVo_(LinearLayout linearLayout) {
        if (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            layoutParams.setMarginStart((int) (this.mScale * dp2px(105.0f)));
            linearLayout.setLayoutParams(layoutParams);
        }
    }

    private void o() {
        this.ad = true;
        this.bd = (HealthTextView) findViewById(R.id.swingAngle_type);
        this.be = (HealthTextView) findViewById(R.id.swingAngle_value);
        this.bj = (LinearLayout) findViewById(R.id.swingAngle_value_layout);
        this.bk = (HealthTextView) findViewById(R.id.swingAngle_unit);
        this.bh = (ImageView) findViewById(R.id.swingAngleArrow);
        this.bm = (LinearLayout) findViewById(R.id.swingAngle_type_layout);
        this.af = (HealthTextView) findViewById(R.id.way_of_landing_type);
        this.ag = (HealthTextView) findViewById(R.id.way_of_landing);
        this.ai = (ImageView) findViewById(R.id.way_of_landing_image);
        this.p = (HealthTextView) findViewById(R.id.Eversion_type);
        this.r = (LinearLayout) findViewById(R.id.Eversion_layout);
        this.s = (LinearLayout) findViewById(R.id.Eversion_type_layout);
        this.q = (LinearLayout) findViewById(R.id.Eversion_value_layout);
        this.l = (HealthTextView) findViewById(R.id.Eversion_value);
        this.o = (HealthTextView) findViewById(R.id.Eversion_unit);
        this.n = (ImageView) findViewById(R.id.eversionExcursionArrow);
        t();
        if (this.p.getText().length() > 10 || this.bb.getTitleTextLength() > 4 || this.t.getTitleTextLength() > 4 || this.v.getTitleTextLength() > 4 || this.w.getTitleTextLength() > 4) {
            this.x = true;
            HealthTextView healthTextView = this.p;
            healthTextView.setTextSize(0, healthTextView.getTextSize() * 0.7f);
            HealthTextView healthTextView2 = this.bd;
            healthTextView2.setTextSize(0, healthTextView2.getTextSize() * 0.7f);
            HealthTextView healthTextView3 = this.m;
            healthTextView3.setTextSize(0, healthTextView3.getTextSize() * 0.7f);
            HealthTextView healthTextView4 = this.d;
            healthTextView4.setTextSize(0, healthTextView4.getTextSize() * 0.7f);
            HealthTextView healthTextView5 = this.ar;
            healthTextView5.setTextSize(0, healthTextView5.getTextSize() * 0.7f);
            HealthTextView healthTextView6 = this.bc;
            healthTextView6.setTextSize(0, healthTextView6.getTextSize() * 0.7f);
            HealthTextView healthTextView7 = this.af;
            healthTextView7.setTextSize(0, healthTextView7.getTextSize() * 0.7f);
            this.bb.setSmallTextSize();
            this.t.setSmallTextSize();
            this.v.setSmallTextSize();
            this.w.setSmallTextSize();
        }
    }

    private void t() {
        this.al = (LinearLayout) findViewById(R.id.rightlayout);
        this.af.setTextSize(0, this.mScale * this.af.getTextSize());
        this.ag.setTextSize(0, this.mScale * this.ag.getTextSize());
        this.bd.setTextSize(0, this.mScale * this.bd.getTextSize());
        this.be.setTextSize(0, this.mScale * this.be.getTextSize());
        this.bk.setTextSize(0, this.mScale * this.bk.getTextSize());
        this.p.setTextSize(0, this.mScale * this.p.getTextSize());
        this.l.setTextSize(0, this.mScale * this.l.getTextSize());
        this.o.setTextSize(0, this.mScale * this.o.getTextSize());
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) findViewById(R.id.step_frequency_magnet);
        RealTimeDynamicChartView realTimeDynamicChartView2 = (RealTimeDynamicChartView) findViewById(R.id.touchdown_magnet);
        RealTimeDynamicChartView realTimeDynamicChartView3 = (RealTimeDynamicChartView) findViewById(R.id.ground_impact_magnet);
        RealTimeDynamicChartView realTimeDynamicChartView4 = (RealTimeDynamicChartView) findViewById(R.id.ground_shock_peak);
        realTimeDynamicChartView3.setVisibility((this.mViewModel.t() || this.mViewModel.q()) ? 8 : 0);
        realTimeDynamicChartView4.setVisibility(this.mViewModel.t() ? 0 : 8);
        this.bb = new lcw(realTimeDynamicChartView);
        this.t = new lci(realTimeDynamicChartView2);
        this.v = new lcj(realTimeDynamicChartView3);
        this.w = new lch(realTimeDynamicChartView4);
        this.bb.setDefaultOrdinateY();
        this.t.setDefaultOrdinateY();
        this.v.setDefaultOrdinateY();
        this.w.setDefaultOrdinateY();
        this.bb.setScale(this.mScale);
        this.t.setScale(this.mScale);
        this.v.setScale(this.mScale);
        this.w.setScale(this.mScale);
    }

    private void y() {
        this.d.setText(this.mContext.getString(R.string._2130840229_res_0x7f020aa5));
        this.c.setText(this.mContext.getString(R.string._2130847442_res_0x7f0226d2));
        this.ar.setText(this.mContext.getString(R.string._2130839826_res_0x7f020912));
        this.m.setText(this.mContext.getString(R.string._2130839907_res_0x7f020963));
        if (this.aq != null) {
            this.bc.setText(this.mContext.getString(R.string._2130840233_res_0x7f020aa9));
        }
        BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel = this.bb;
        if (baseRealTimeDynamicChartViewModel != null) {
            baseRealTimeDynamicChartViewModel.updateConfiguration(this.mContext);
        }
        if (this.ax != null) {
            this.ay.setText(this.mContext.getString(R.string._2130845058_res_0x7f021d82));
            this.ba.setText(this.mContext.getString(R.string._2130839766_res_0x7f0208d6));
        }
        HealthTextView healthTextView = this.an;
        if (healthTextView != null) {
            healthTextView.setText(R.string._2130846033_res_0x7f022151);
        }
        if (this.mHasWear && this.mHeartRate != null && !isTargetType()) {
            this.mHeartRateUnit.setText(lbv.d(0, this.mContext));
        }
        if (this.mHasRunPostureDevice && this.ad) {
            this.t.updateConfiguration(this.mContext);
            this.v.updateConfiguration(this.mContext);
            this.w.updateConfiguration(this.mContext);
            this.af.setText(this.mContext.getString(R.string._2130840274_res_0x7f020ad2));
            this.bd.setText(this.mContext.getString(R.string._2130840276_res_0x7f020ad4));
            this.p.setText(this.mContext.getString(R.string._2130840275_res_0x7f020ad3));
        }
    }

    private void e(int i) {
        Handler handler;
        this.ao = i;
        if (this.y || i != 1 || (handler = this.u) == null) {
            return;
        }
        this.y = true;
        handler.sendEmptyMessage(10010);
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void setBtConnectState(String str) {
        if (AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED.equals(str) || AbstractFitnessClient.ACTION_SERVICE_REDISCOVERIED.equals(str)) {
            this.am.setVisibility(8);
            this.mBtIcon.setImageDrawable(lbv.bVR_(this.mContext, R.drawable._2131430551_res_0x7f0b0c97));
        } else if (AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING.equals(str)) {
            this.am.setVisibility(0);
            this.mBtIcon.setImageDrawable(lbv.bVR_(this.mContext, R.drawable._2131430591_res_0x7f0b0cbf));
        } else {
            this.am.setVisibility(8);
            this.mBtIcon.setImageDrawable(lbv.bVR_(this.mContext, R.drawable._2131430552_res_0x7f0b0c98));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<Integer, Object> map) {
        if (w()) {
            j();
        }
        if (this.mHasWear && this.mHeartRate != null && !isTargetType()) {
            updateHeartRate(e(map, 20004));
        }
        this.b.setText(lbv.e(e(map, 6), 1, 0));
        this.k.setText(UnitUtil.d(e(map, 2)));
        b(map, UnitUtil.h());
        b(map);
        if (this.mTargetModeWithProgressViewHolder != null) {
            this.mTargetModeWithProgressViewHolder.d(map);
        }
        if (this.aq != null) {
            int e2 = e(map, 8);
            this.aq.setText(lbv.e(e2, 1, 0));
            this.bg.setText(this.mContext.getResources().getQuantityString(R.plurals._2130903251_res_0x7f0300d3, e2, ""));
        }
        BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel = this.bb;
        if (baseRealTimeDynamicChartViewModel != null) {
            baseRealTimeDynamicChartViewModel.pushNewData(e(map, 4), e);
        }
        if (this.ax != null) {
            a(e(map, 4));
        }
        if (map.get(20002) != null && this.mHasRunPostureDevice && this.ad) {
            e((ffs) map.get(20002));
        } else if (map.get(20002) == null && this.ad) {
            af();
        } else {
            LogUtil.c("IDEQ_IndoorTreadmillTvDisplay", "updateUi, hasRunPostureDevice = ", Boolean.valueOf(this.mHasRunPostureDevice));
        }
        c(map);
    }

    private int e(Map<Integer, Object> map, int i) {
        Object obj = map.get(Integer.valueOf(i));
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    private void c(Map<Integer, Object> map) {
        String string;
        String string2;
        int e2 = e(map, 1);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float f = UnitUtil.h() ? (e2 / 1000.0f) * 0.621371f : e2 / 1000.0f;
        if (lbf.d(this.mViewModel)) {
            return;
        }
        if (lbf.a(this.mViewModel) || lbf.c(this.mViewModel)) {
            this.i.setText(decimalFormat.format(f));
            HealthTextView healthTextView = this.f;
            if (UnitUtil.h()) {
                string = this.mContext.getResources().getQuantityString(R.plurals._2130903253_res_0x7f0300d5, e2, "");
            } else {
                string = this.mContext.getString(R.string._2130840225_res_0x7f020aa1);
            }
            healthTextView.setText(string);
            return;
        }
        this.j.setText(decimalFormat.format(f));
        HealthTextView healthTextView2 = this.h;
        if (UnitUtil.h()) {
            string2 = this.mContext.getResources().getQuantityString(R.plurals._2130903253_res_0x7f0300d5, e2, "");
        } else {
            string2 = this.mContext.getString(R.string._2130840225_res_0x7f020aa1);
        }
        healthTextView2.setText(string2);
    }

    private void b(Map<Integer, Object> map) {
        if (this.aj == null) {
            return;
        }
        if (map == null || map.get(14) == null || ((Integer) map.get(14)).intValue() == 0) {
            this.aj.setText(getResources().getString(R.string._2130851304_res_0x7f0235e8));
            return;
        }
        float floatValue = new BigDecimal(((Integer) map.get(14)).intValue()).divide(new BigDecimal(100), 2, 4).floatValue();
        if (UnitUtil.h()) {
            floatValue = (float) UnitUtil.d(floatValue, 3);
        }
        this.aj.setText(gvv.a(floatValue));
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void updateUi(Map<Integer, Object> map) {
        if (map == null || !this.ab) {
            LogUtil.b("IDEQ_IndoorTreadmillTvDisplay", "updateUi, indoorEquipDataStructForShow = null");
            return;
        }
        LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "start update tv ui :", map.toString());
        Message obtainMessage = this.u.obtainMessage(2020);
        obtainMessage.obj = map;
        this.u.sendMessage(obtainMessage);
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void updateProgress(int i) {
        if (this.mTargetModeWithProgressViewHolder != null) {
            this.mTargetModeWithProgressViewHolder.a(i);
        }
    }

    private void j() {
        float f;
        LogUtil.a("IDEQ_IndoorTreadmillTvDisplay", "changeTextLanguage");
        if (!LanguageUtil.m(this.mContext) && !this.x) {
            this.x = true;
            f = 0.7f;
        } else if (LanguageUtil.m(this.mContext) && this.x) {
            this.x = false;
            f = 1.4f;
        } else {
            f = 1.0f;
        }
        c(f);
        HealthTextView healthTextView = this.bd;
        if (healthTextView != null) {
            healthTextView.setTextSize(0, healthTextView.getTextSize() * f);
            this.bd.setText(this.mContext.getString(R.string._2130840276_res_0x7f020ad4));
        }
        HealthTextView healthTextView2 = this.p;
        if (healthTextView2 != null) {
            healthTextView2.setTextSize(0, healthTextView2.getTextSize() * f);
            this.p.setText(this.mContext.getString(R.string._2130840275_res_0x7f020ad3));
        }
        HealthTextView healthTextView3 = this.af;
        if (healthTextView3 != null) {
            healthTextView3.setTextSize(0, f * healthTextView3.getTextSize());
            this.af.setText(this.mContext.getString(R.string._2130840274_res_0x7f020ad2));
        }
        HealthTextView healthTextView4 = this.ak;
        if (healthTextView4 != null) {
            healthTextView4.setText(this.mContext.getString(R.string.IDS_app_name_health));
        }
        if (this.mHeartRateUnit != null && this.mHeartRateType != null) {
            this.mHeartRateUnit.setText(lbv.d(0, this.mContext));
        }
        if (this.mViewModel.t()) {
            r();
        } else {
            s();
        }
    }

    private void c(float f) {
        HealthTextView healthTextView;
        HealthTextView healthTextView2 = this.ay;
        if (healthTextView2 != null && this.ba != null) {
            healthTextView2.setText(this.mContext.getString(R.string._2130845058_res_0x7f021d82));
            this.ba.setText(this.mContext.getString(R.string._2130839766_res_0x7f0208d6));
        }
        HealthTextView healthTextView3 = this.d;
        if (healthTextView3 != null && (healthTextView = this.c) != null) {
            healthTextView.setTextSize(0, healthTextView3.getTextSize() * f);
            this.d.setText(this.mContext.getString(R.string._2130840229_res_0x7f020aa5));
            this.c.setText(this.mContext.getString(R.string._2130847442_res_0x7f0226d2));
        }
        HealthTextView healthTextView4 = this.an;
        if (healthTextView4 != null) {
            healthTextView4.setTextSize(0, healthTextView4.getTextSize() * f);
            this.an.setText(R.string._2130846033_res_0x7f022151);
        }
        HealthTextView healthTextView5 = this.bc;
        if (healthTextView5 != null && this.bg != null) {
            healthTextView5.setTextSize(0, healthTextView5.getTextSize() * f);
            this.bc.setText(this.mContext.getString(R.string._2130845058_res_0x7f021d82));
            this.bg.setText(this.mContext.getResources().getQuantityString(R.plurals._2130903251_res_0x7f0300d3, 2, ""));
        }
        HealthTextView healthTextView6 = this.m;
        if (healthTextView6 != null) {
            healthTextView6.setTextSize(0, healthTextView6.getTextSize() * f);
            this.m.setText(this.mContext.getString(R.string._2130839907_res_0x7f020963));
        }
        HealthTextView healthTextView7 = this.ar;
        if (healthTextView7 != null) {
            healthTextView7.setTextSize(0, f * healthTextView7.getTextSize());
            this.ar.setText(this.mContext.getString(R.string._2130839826_res_0x7f020912));
        }
    }

    private void s() {
        BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel = this.v;
        if (baseRealTimeDynamicChartViewModel == null || this.t == null) {
            return;
        }
        baseRealTimeDynamicChartViewModel.updateConfiguration(this.mContext);
        this.t.updateConfiguration(this.mContext);
        BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel2 = this.bb;
        if (baseRealTimeDynamicChartViewModel2 != null) {
            baseRealTimeDynamicChartViewModel2.updateConfiguration(this.mContext);
            if (this.bb.getTitleTextLength() > 4 || this.v.getTitleTextLength() > 4 || this.t.getTitleTextLength() > 4) {
                this.bb.setSmallTextSize();
                this.v.setSmallTextSize();
                this.t.setSmallTextSize();
            } else {
                this.bb.setNormalTextSize();
                this.v.setNormalTextSize();
                this.t.setNormalTextSize();
            }
        }
    }

    private void r() {
        BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel = this.w;
        if (baseRealTimeDynamicChartViewModel == null || this.t == null) {
            return;
        }
        baseRealTimeDynamicChartViewModel.updateConfiguration(this.mContext);
        this.t.updateConfiguration(this.mContext);
        BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel2 = this.bb;
        if (baseRealTimeDynamicChartViewModel2 != null) {
            baseRealTimeDynamicChartViewModel2.updateConfiguration(this.mContext);
            if (this.bb.getTitleTextLength() > 4 || this.w.getTitleTextLength() > 4 || this.t.getTitleTextLength() > 4) {
                this.bb.setSmallTextSize();
                this.w.setSmallTextSize();
                this.t.setSmallTextSize();
            } else {
                this.bb.setNormalTextSize();
                this.w.setNormalTextSize();
                this.t.setNormalTextSize();
            }
        }
    }

    private String d(Locale locale) {
        if (locale == null) {
            return "";
        }
        return locale.getLanguage() + Constants.LINK + locale.getCountry();
    }

    private boolean w() {
        String d = d(Locale.getDefault());
        if (d.equals(this.ah)) {
            return false;
        }
        this.ah = d;
        return true;
    }

    private void a(int i) {
        int b = lbv.b("step frequency", i, e);
        if (i <= 0) {
            this.ax.setText(getResources().getString(R.string._2130851304_res_0x7f0235e8));
        } else {
            this.ax.setText(lbv.e(i, 1, 0));
        }
        if (b == 1) {
            ad();
        } else {
            aa();
        }
    }

    private void b(Map<Integer, Object> map, boolean z) {
        if (map.get(3) == null || ((Integer) map.get(3)).intValue() == 0) {
            HealthTextView healthTextView = this.as;
            if (healthTextView != null) {
                healthTextView.setText(getResources().getString(R.string._2130851304_res_0x7f0235e8));
                e = 0.0f;
                return;
            }
            return;
        }
        float intValue = ((Integer) map.get(3)).intValue() / 100.0f;
        e = intValue;
        if (z) {
            if (this.as == null || this.ap == null) {
                return;
            }
            this.as.setText(new DecimalFormat("0.0").format(intValue * 0.621371f));
            this.ar.setText(this.mContext.getResources().getString(R.string._2130839825_res_0x7f020911));
            return;
        }
        if (this.as == null || this.ap == null) {
            return;
        }
        this.as.setText(new DecimalFormat("0.0").format(intValue));
        this.ar.setText(this.mContext.getString(R.string._2130839826_res_0x7f020912));
    }

    private void af() {
        this.ag.setText(this.mContext.getString(R.string._2130840281_res_0x7f020ad9));
        this.ai.setImageDrawable(null);
        if (this.mViewModel.t()) {
            this.w.pushNewData(0, 0.0f);
        } else {
            this.v.pushNewData(0, 0.0f);
        }
        this.t.pushNewData(0, 0.0f);
        this.be.setText(lbv.e(0.0d, 1, 0));
        ac();
        this.l.setText(lbv.e(0.0d, 1, 0));
        u();
    }

    private void e(ffs ffsVar) {
        int d = ffsVar.d();
        int h = ffsVar.h();
        int c = ffsVar.c();
        int i = d > h ? d : h;
        if (i > c) {
            c = i;
        }
        if (this.ag == null) {
            LogUtil.h("IDEQ_IndoorTreadmillTvDisplay", "updatePosture, mLandingWay = null");
            return;
        }
        c(d, h, c);
        this.t.pushNewData(ffsVar.b(), e);
        this.v.pushNewData(ffsVar.e(), e);
        this.w.pushNewData(ffsVar.m());
        int i2 = ffsVar.i();
        this.be.setText(lbv.e(i2, 1, 0));
        int b = lbv.b("swing angle", i2, e);
        if (b == 1) {
            z();
        } else if (b == 2) {
            ab();
        } else {
            ac();
        }
        int a2 = ffsVar.a();
        int b2 = lbv.b("eversion angle", a2, e);
        this.l.setText(lbv.e(a2, 1, 0));
        if (b2 == 1) {
            x();
        } else if (b2 == 2) {
            v();
        } else {
            u();
        }
    }

    private void c(int i, int i2, int i3) {
        if (i3 <= 0 || i3 == i2) {
            this.ag.setText(this.mContext.getString(R.string._2130840272_res_0x7f020ad0));
            this.ai.setImageDrawable(lbv.bVS_(this.mContext, R.drawable._2131428632_res_0x7f0b0518));
        } else if (i3 == i) {
            this.ag.setText(this.mContext.getString(R.string._2130840271_res_0x7f020acf));
            this.ai.setImageDrawable(lbv.bVS_(this.mContext, R.drawable._2131428633_res_0x7f0b0519));
        } else {
            this.ag.setText(this.mContext.getString(R.string._2130842726_res_0x7f021466));
            this.ai.setImageDrawable(lbv.bVS_(this.mContext, R.drawable._2131428631_res_0x7f0b0517));
        }
    }

    private void ad() {
        this.av.setVisibility(0);
        this.av.setBackgroundResource(R.drawable._2131427551_res_0x7f0b00df);
        this.ax.setTextColor(-14774785);
        this.ba.setTextColor(-14774785);
    }

    private void aa() {
        this.av.setVisibility(4);
        this.ax.setTextColor(-1);
        this.ba.setTextColor(-1);
    }

    private void ab() {
        this.bh.setVisibility(0);
        this.bh.setBackgroundResource(R.drawable._2131427558_res_0x7f0b00e6);
        this.be.setTextColor(-53241);
        this.bk.setTextColor(-53241);
    }

    private void ac() {
        this.bh.setVisibility(4);
        this.be.setTextColor(-1);
        this.bk.setTextColor(-1);
    }

    private void z() {
        this.bh.setVisibility(0);
        this.bh.setBackgroundResource(R.drawable._2131427551_res_0x7f0b00df);
        this.be.setTextColor(-14774785);
        this.bk.setTextColor(-14774785);
    }

    private void v() {
        this.n.setVisibility(0);
        this.n.setBackgroundResource(R.drawable._2131427558_res_0x7f0b00e6);
        this.l.setTextColor(-53241);
        this.o.setTextColor(-53241);
    }

    private void u() {
        this.n.setVisibility(4);
        this.l.setTextColor(-1);
        this.o.setTextColor(-1);
    }

    private void x() {
        this.n.setVisibility(0);
        this.n.setBackgroundResource(R.drawable._2131427551_res_0x7f0b00df);
        this.l.setTextColor(-14774785);
        this.o.setTextColor(-14774785);
    }

    private void bVm_(ImageView imageView) {
        if (imageView == null) {
            LogUtil.h("IDEQ_IndoorTreadmillTvDisplay", "STEP IMAGE IS NULL");
            return;
        }
        if (imageView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.width = (int) (this.mScale * dp2px(16.0f));
            layoutParams.height = (int) (this.mScale * dp2px(16.0f));
            layoutParams.setMarginStart(((int) this.mScale) * dp2px(4.0f));
            imageView.setLayoutParams(layoutParams);
        }
    }

    private void d() {
        if (this.ae.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ae.getLayoutParams();
            if (this.g == 1) {
                layoutParams.setMargins(0, (int) (this.mScale * dp2px(64.0f)), 0, (int) (this.mScale * dp2px(51.8f)));
                layoutParams.setMarginStart(((int) this.mScale) * dp2px(16.0f));
                layoutParams.width = ((int) this.mScale) * dp2px(287.8f);
            } else {
                layoutParams.setMargins(0, (int) (this.mScale * dp2px(73.5f)), 0, (int) (this.mScale * dp2px(73.5f)));
                layoutParams.setMarginStart(((int) this.mScale) * dp2px(30.0f));
                layoutParams.width = ((int) this.mScale) * dp2px(184.8f);
            }
            this.ae.setLayoutParams(layoutParams);
        }
    }
}
