package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay;
import com.huawei.indoorequip.viewmodel.IndoorEquipViewModel;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class lat extends IndoorTvBaseDisplay {
    private static float b;

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f14731a;
    private LinearLayout aa;
    private HealthTextView ab;
    private int ac;
    private HealthTextView ad;
    private HealthTextView ae;
    private HealthTextView af;
    private HealthTextView ag;
    private HealthTextView ah;
    private SupportDataRange ai;
    private lcb aj;
    private HealthTextView am;
    private LinearLayout c;
    private LinearLayout d;
    private HealthImageView e;
    private HealthTextView f;
    private HealthTextView g;
    private LinearLayout h;
    private HealthTextView i;
    private LinearLayout j;
    private LinearLayout k;
    private View l;
    private LinearLayout m;
    private Handler n;
    private HealthTextView o;
    private HealthProgressBar p;
    private boolean q;
    private boolean r;
    private HealthImageView s;
    private HealthTextView t;
    private HealthTextView u;
    private HealthTextView v;
    private HealthTextView w;
    private LinearLayout x;
    private HealthTextView y;
    private int z;

    public lat(Context context, Display display, boolean z, IndoorEquipViewModel indoorEquipViewModel) {
        super(context, display);
        this.ac = 0;
        this.q = false;
        this.r = false;
        this.n = new Handler() { // from class: lat.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    LogUtil.b("IDEQ_HeartRateControlSportTvDisplay", "msg == null");
                    return;
                }
                super.handleMessage(message);
                int i = message.what;
                if (i == 2020) {
                    LogUtil.a("IDEQ_HeartRateControlSportTvDisplay", "accept update ui msg");
                    if (message.obj instanceof HashMap) {
                        HashMap hashMap = (HashMap) message.obj;
                        if (lat.this.aj != null) {
                            lat.this.aj.e(hashMap);
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (i == 10010) {
                    lat.this.mRunway.setBackgroundResource(R.drawable._2131431341_res_0x7f0b0fad);
                    lat.this.b(10020);
                } else {
                    if (i != 10020) {
                        return;
                    }
                    lat.this.mRunway.setBackgroundResource(R.drawable._2131431342_res_0x7f0b0fae);
                    lat.this.b(10010);
                }
            }
        };
        this.mHasWear = z;
        this.mContext = context;
        this.mViewModel = indoorEquipViewModel;
        if (this.mViewModel != null) {
            this.z = this.mViewModel.getSportType();
            this.ac = this.mViewModel.getSportStatus();
            SupportDataRange a2 = this.mViewModel.a();
            this.ai = a2;
            if (a2 == null) {
                LogUtil.a("IDEQ_HeartRateControlSportTvDisplay", "mSupportDataRange == null");
            } else {
                LogUtil.a("IDEQ_HeartRateControlSportTvDisplay", "mSupportDataRange", a2, ":", Integer.valueOf(a2.getMinLevel()), Constants.LINK, Integer.valueOf(this.ai.getMaxLevel()));
            }
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ie_data_show_heart_rate_control_sport_tv);
        initWindowConfig();
        d(this.ac);
        i();
        this.r = true;
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void updateUi(Map<Integer, Object> map) {
        if (map == null || !this.r) {
            LogUtil.b("IDEQ_HeartRateControlSportTvDisplay", "updateUi, indoorEquipDataStructForShow = null");
            return;
        }
        LogUtil.a("IDEQ_HeartRateControlSportTvDisplay", "start update tv ui :", map.toString());
        Message obtainMessage = this.n.obtainMessage(2020);
        obtainMessage.obj = map;
        this.n.sendMessage(obtainMessage);
    }

    public void b(Map<String, Object> map) {
        lcb lcbVar = this.aj;
        if (lcbVar != null) {
            lcbVar.b(map);
        }
    }

    public void a(int i) {
        LogUtil.a("IDEQ_HeartRateControlSportTvDisplay", "update control status: ", Integer.valueOf(i));
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void updateProgress(int i) {
        if (this.mTargetModeWithProgressViewHolder != null) {
            this.mTargetModeWithProgressViewHolder.a(i);
        }
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void initNormalView() {
        this.mRunwayBackground = (ImageView) findViewById(R.id.runwaybackground);
        this.mRunway = (ImageView) findViewById(R.id.runway);
        this.p = (HealthProgressBar) findViewById(R.id.hw_recycler_loading_hpb);
        this.mBtIcon = (ImageView) findViewById(R.id.ie_bt_icon);
        this.mBtIcon.setImageDrawable(lbv.bVR_(this.mContext, lbj.c(this.z, true)));
        this.mLogoLayout = (LinearLayout) findViewById(R.id.logoLayout);
        this.mBtBoltConnectIcon = (ImageView) findViewById(R.id.ie_wear_icon);
        this.mLogoImage = (ImageView) findViewById(R.id.logoImage);
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void setBtConnectState(String str) {
        if (AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED.equals(str) || AbstractFitnessClient.ACTION_SERVICE_REDISCOVERIED.equals(str)) {
            this.p.setVisibility(8);
            this.mBtIcon.setImageDrawable(lbv.bVR_(this.mContext, lbj.c(this.z, true)));
        } else if (AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING.equals(str)) {
            this.p.setVisibility(0);
            this.mBtIcon.setImageDrawable(lbv.bVR_(this.mContext, lbj.a(this.z)));
        } else {
            this.p.setVisibility(8);
            this.mBtIcon.setImageDrawable(lbv.bVR_(this.mContext, lbj.c(this.z, false)));
        }
    }

    @Override // android.app.Presentation
    public void onDisplayRemoved() {
        LogUtil.a("IDEQ_HeartRateControlSportTvDisplay", "tv display destroy.");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        this.ac = 2;
        LogUtil.a("IDEQ_HeartRateControlSportTvDisplay", "tv sport state: ", 2);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        this.ac = 1;
        d(1);
        LogUtil.a("IDEQ_HeartRateControlSportTvDisplay", "tv sport state: ", Integer.valueOf(this.ac));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        this.ac = 1;
        d(1);
        LogUtil.a("IDEQ_HeartRateControlSportTvDisplay", "tv sport state: ", Integer.valueOf(this.ac));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        this.ac = 3;
        LogUtil.a("IDEQ_HeartRateControlSportTvDisplay", "tv sport state: ", 3);
    }

    private void d(int i) {
        Handler handler;
        this.ac = i;
        if (this.q || i != 1 || (handler = this.n) == null) {
            return;
        }
        this.q = true;
        handler.sendEmptyMessage(10010);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        Handler handler = this.n;
        if (handler != null && this.ac == 1) {
            handler.sendEmptyMessageDelayed(i, lbv.a(b));
            return;
        }
        if (handler != null) {
            handler.removeMessages(10010);
            this.n.removeMessages(10020);
        }
        this.q = false;
    }

    private void i() {
        initNormalView();
        this.t = (HealthTextView) findViewById(R.id.logoText);
        this.k = (LinearLayout) findViewById(R.id.deviceLayout);
        this.i = (HealthTextView) findViewById(R.id.tv_current_hr_value);
        this.f = (HealthTextView) findViewById(R.id.tv_current_hr_unit);
        this.s = (HealthImageView) findViewById(R.id.iv_heart);
        this.m = (LinearLayout) findViewById(R.id.current_stage_layout);
        this.o = (HealthTextView) findViewById(R.id.tv_current_stage);
        this.ae = (HealthTextView) findViewById(R.id.tv_stage_divider);
        this.ah = (HealthTextView) findViewById(R.id.tv_total_stage);
        this.ab = (HealthTextView) findViewById(R.id.tv_stage_name);
        this.ad = (HealthTextView) findViewById(R.id.tv_stage_duration);
        this.x = (LinearLayout) findViewById(R.id.recommend_layout);
        this.w = (HealthTextView) findViewById(R.id.tv_recommend_hr_interval);
        this.v = (HealthTextView) findViewById(R.id.tv_recommend_hr_unit);
        this.y = (HealthTextView) findViewById(R.id.tv_recommend_hr_title);
        this.af = (HealthTextView) findViewById(R.id.tv_recommend_cadence_title);
        this.ag = (HealthTextView) findViewById(R.id.tv_recommend_cadence_interval);
        this.u = (HealthTextView) findViewById(R.id.tv_recommend_cadence_unit);
        this.aa = (LinearLayout) findViewById(R.id.ll_sport_data_layout);
        this.f14731a = (HealthTextView) findViewById(R.id.tv_chart_legend);
        this.h = (LinearLayout) findViewById(R.id.current_hr_layout);
        this.j = (LinearLayout) findViewById(R.id.current_cadence_layout);
        this.d = (LinearLayout) findViewById(R.id.chart_legend_layout);
        this.am = (HealthTextView) findViewById(R.id.tv_current_cadence_value);
        this.g = (HealthTextView) findViewById(R.id.tv_current_cadence_unit);
        this.e = (HealthImageView) findViewById(R.id.iv_cadence);
        this.c = (LinearLayout) findViewById(R.id.recommend_cadence_layout);
        this.l = findViewById(R.id.center_divider);
        h();
        d();
        c();
        a();
        g();
        lcb lcbVar = new lcb(this.mContext, getWindow().getDecorView().getRootView(), this.mViewModel, true);
        this.aj = lcbVar;
        lcbVar.b(this.mScale, this.mCurrentDisplayDensity, this.mCurrentDisplayWidth, this.ai);
    }

    private void h() {
        this.t.setTextSize(0, this.mScale * dp2px(14.0f));
        this.i.setTextSize(0, this.mScale * dp2px(24.0f));
        this.f.setTextSize(0, this.mScale * dp2px(14.0f));
        this.o.setTextSize(0, this.mScale * dp2px(16.0f));
        this.ae.setTextSize(0, this.mScale * dp2px(14.0f));
        this.ah.setTextSize(0, this.mScale * dp2px(14.0f));
        this.ab.setTextSize(0, this.mScale * dp2px(10.0f));
        this.ad.setTextSize(0, this.mScale * dp2px(10.0f));
        this.w.setTextSize(0, this.mScale * dp2px(16.0f));
        this.v.setTextSize(0, this.mScale * dp2px(14.0f));
        this.u.setTextSize(0, this.mScale * dp2px(14.0f));
        this.y.setTextSize(0, this.mScale * dp2px(12.0f));
        this.f14731a.setTextSize(0, this.mScale * dp2px(10.0f));
        this.af.setTextSize(0, this.mScale * dp2px(14.0f));
        this.ag.setTextSize(0, this.mScale * dp2px(16.0f));
        this.am.setTextSize(0, this.mScale * dp2px(24.0f));
        this.g.setTextSize(0, this.mScale * dp2px(14.0f));
    }

    private void d() {
        LinearLayout linearLayout = this.h;
        if (linearLayout != null && (linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.h.getLayoutParams();
            layoutParams.height = (int) (this.mScale * dp2px(32.0f));
            layoutParams.setMargins(0, (int) (this.mScale * dp2px(4.0f)), 0, 0);
            this.h.setLayoutParams(layoutParams);
        }
        LinearLayout linearLayout2 = this.j;
        if (linearLayout2 != null && (linearLayout2.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.j.getLayoutParams();
            layoutParams2.height = (int) (this.mScale * dp2px(32.0f));
            layoutParams2.setMargins(0, (int) (this.mScale * dp2px(4.0f)), 0, 0);
            this.j.setLayoutParams(layoutParams2);
        }
        LinearLayout linearLayout3 = this.m;
        if (linearLayout3 != null && (linearLayout3.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.m.getLayoutParams();
            layoutParams3.height = (int) (this.mScale * dp2px(32.0f));
            layoutParams3.setMargins(0, (int) (this.mScale * dp2px(4.0f)), 0, 0);
            this.m.setLayoutParams(layoutParams3);
        }
        LinearLayout linearLayout4 = this.aa;
        if (linearLayout4 == null || !(linearLayout4.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.aa.getLayoutParams();
        layoutParams4.height = (int) (this.mScale * dp2px(80.0f));
        layoutParams4.setMargins((int) (this.mScale * dp2px(12.0f)), 0, (int) (this.mScale * dp2px(12.0f)), 0);
        this.aa.setLayoutParams(layoutParams4);
    }

    private void c() {
        changeRunwaySize();
        changeLogoSize();
        if (this.mBtIcon != null && (this.mBtIcon.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mBtIcon.getLayoutParams();
            layoutParams.width = (int) (this.mScale * dp2px(40.0f));
            layoutParams.height = (int) (this.mScale * dp2px(40.0f));
            this.mBtIcon.setLayoutParams(layoutParams);
        }
        if (this.mBtBoltConnectIcon != null && (this.mBtBoltConnectIcon.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mBtBoltConnectIcon.getLayoutParams();
            layoutParams2.width = (int) (this.mScale * dp2px(40.0f));
            layoutParams2.height = (int) (this.mScale * dp2px(40.0f));
            layoutParams2.setMarginStart(((int) this.mScale) * dp2px(4.0f));
            this.mBtBoltConnectIcon.setLayoutParams(layoutParams2);
        }
        HealthImageView healthImageView = this.s;
        if (healthImageView != null && (healthImageView.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.s.getLayoutParams();
            layoutParams3.width = (int) (this.mScale * dp2px(16.0f));
            layoutParams3.height = (int) (this.mScale * dp2px(16.0f));
            layoutParams3.setMargins(0, 0, 0, (int) (this.mScale * dp2px(4.0f)));
            layoutParams3.setMarginEnd(((int) this.mScale) * dp2px(6.0f));
            this.s.setLayoutParams(layoutParams3);
        }
        HealthImageView healthImageView2 = this.e;
        if (healthImageView2 != null && (healthImageView2.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.e.getLayoutParams();
            layoutParams4.width = (int) (this.mScale * dp2px(16.0f));
            layoutParams4.height = (int) (this.mScale * dp2px(16.0f));
            layoutParams4.setMargins(0, 0, 0, (int) (this.mScale * dp2px(4.0f)));
            layoutParams4.setMarginEnd(((int) this.mScale) * dp2px(6.0f));
            this.e.setLayoutParams(layoutParams4);
        }
        View view = this.l;
        if (view == null || !(view.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.l.getLayoutParams();
        layoutParams5.width = (int) (this.mScale * dp2px(0.5f));
        layoutParams5.height = (int) (this.mScale * dp2px(40.0f));
        this.l.setLayoutParams(layoutParams5);
    }

    private void a() {
        changeLogoMargin();
        LinearLayout linearLayout = this.k;
        if (linearLayout != null && (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.k.getLayoutParams();
            layoutParams.setMargins(0, (int) (this.mScale * dp2px(20.0f)), 0, 0);
            layoutParams.setMarginStart((int) (this.mScale * dp2px(24.0f)));
            this.k.setLayoutParams(layoutParams);
        }
        HealthProgressBar healthProgressBar = this.p;
        if (healthProgressBar != null && (healthProgressBar.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.p.getLayoutParams();
            layoutParams2.setMargins(0, (int) (this.mScale * dp2px(20.0f)), 0, 0);
            layoutParams2.setMarginStart((int) (this.mScale * dp2px(24.0f)));
            this.p.setLayoutParams(layoutParams2);
        }
        HealthTextView healthTextView = this.t;
        if (healthTextView != null && (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.t.getLayoutParams();
            layoutParams3.setMarginStart((int) (this.mScale * dp2px(8.0f)));
            this.t.setLayoutParams(layoutParams3);
        }
        f();
        e();
        b();
    }

    private void f() {
        HealthTextView healthTextView = this.f;
        if (healthTextView != null && (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, (int) (this.mScale * dp2px(2.0f)));
            layoutParams.setMarginStart((int) (this.mScale * dp2px(4.0f)));
            this.f.setLayoutParams(layoutParams);
        }
        HealthTextView healthTextView2 = this.g;
        if (healthTextView2 != null && (healthTextView2.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.g.getLayoutParams();
            layoutParams2.setMargins(0, 0, 0, (int) (this.mScale * dp2px(2.0f)));
            layoutParams2.setMarginStart((int) (this.mScale * dp2px(4.0f)));
            this.g.setLayoutParams(layoutParams2);
        }
        LinearLayout linearLayout = this.m;
        if (linearLayout != null && (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.m.getLayoutParams();
            layoutParams3.setMargins(0, 0, 0, (int) (this.mScale * dp2px(2.0f)));
            layoutParams3.setMarginStart((int) (this.mScale * dp2px(24.0f)));
            this.m.setLayoutParams(layoutParams3);
        }
        HealthTextView healthTextView3 = this.ab;
        if (healthTextView3 != null && (healthTextView3.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.ab.getLayoutParams();
            layoutParams4.setMarginStart((int) (this.mScale * dp2px(8.0f)));
            this.ab.setLayoutParams(layoutParams4);
        }
        HealthTextView healthTextView4 = this.ad;
        if (healthTextView4 == null || !(healthTextView4.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            return;
        }
        LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) this.ad.getLayoutParams();
        layoutParams5.setMarginStart((int) (this.mScale * dp2px(8.0f)));
        this.ad.setLayoutParams(layoutParams5);
    }

    private void e() {
        LinearLayout linearLayout = this.x;
        if (linearLayout != null && (linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.x.getLayoutParams();
            layoutParams.setMargins(0, (int) (this.mScale * dp2px(2.0f)), 0, 0);
            this.x.setLayoutParams(layoutParams);
        }
        LinearLayout linearLayout2 = this.c;
        if (linearLayout2 != null && (linearLayout2.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.c.getLayoutParams();
            layoutParams2.setMargins(0, (int) (this.mScale * dp2px(2.0f)), 0, 0);
            this.c.setLayoutParams(layoutParams2);
        }
        HealthTextView healthTextView = this.v;
        if (healthTextView != null && (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.v.getLayoutParams();
            layoutParams3.setMarginStart((int) (this.mScale * dp2px(4.0f)));
            this.v.setLayoutParams(layoutParams3);
        }
        HealthTextView healthTextView2 = this.u;
        if (healthTextView2 != null && (healthTextView2.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.u.getLayoutParams();
            layoutParams4.setMarginStart((int) (this.mScale * dp2px(4.0f)));
            this.u.setLayoutParams(layoutParams4);
        }
        HealthTextView healthTextView3 = this.y;
        if (healthTextView3 == null || !(healthTextView3.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            return;
        }
        LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) this.y.getLayoutParams();
        layoutParams5.setMarginEnd((int) (this.mScale * dp2px(4.0f)));
        this.y.setLayoutParams(layoutParams5);
    }

    private void b() {
        LinearLayout linearLayout = this.d;
        if (linearLayout == null || !(linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.d.getLayoutParams();
        layoutParams.setMarginStart((int) (this.mScale * dp2px(24.0f)));
        this.d.setLayoutParams(layoutParams);
    }

    private void g() {
        this.t.setText(this.mContext.getString(R.string.IDS_app_name_health));
        this.f.setText(this.mContext.getString(R.string.IDS_main_watch_heart_rate_unit_string));
        this.v.setText(this.mContext.getString(R.string.IDS_main_watch_heart_rate_unit_string));
        this.y.setText(this.mContext.getString(R.string._2130840341_res_0x7f020b15));
        this.f14731a.setText(this.mContext.getString(R.string._2130841430_res_0x7f020f56));
    }
}
