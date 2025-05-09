package defpackage;

import android.content.Context;
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
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;
import com.huawei.indoorequip.viewmodel.IndoorEquipViewModel;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class lax extends IndoorTvBaseDisplay implements SportLifecycle {
    private static float e;

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f14735a;
    private HealthTextView b;
    private Map<SportDetailChartDataType, BaseRealTimeDynamicChartViewModel> c;
    private Handler d;
    private ImageView f;
    private boolean g;
    private boolean h;
    private lcd i;
    private RelativeLayout j;
    private String k;
    private HealthProgressBar l;
    private String m;
    private HealthTextView n;
    private boolean o;
    private SupportDataRange p;
    private int r;
    private int s;
    private ViewStub t;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        this.r = 2;
        LogUtil.a("IDEQ_IndoorEquipTvDisplay", "tv sport state: ", 2);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        this.r = 1;
        c(1);
        LogUtil.a("IDEQ_IndoorEquipTvDisplay", "tv sport state: ", Integer.valueOf(this.r));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        this.r = 1;
        c(1);
        LogUtil.a("IDEQ_IndoorEquipTvDisplay", "tv sport state: ", Integer.valueOf(this.r));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        this.r = 3;
        LogUtil.a("IDEQ_IndoorEquipTvDisplay", "tv sport state: ", 3);
    }

    public lax(Context context, Display display, boolean z, IndoorEquipViewModel indoorEquipViewModel) {
        super(context, display);
        this.g = false;
        this.c = new HashMap();
        this.h = false;
        this.o = false;
        this.r = 0;
        this.m = c(Locale.getDefault());
        this.d = new Handler() { // from class: lax.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    LogUtil.b("IDEQ_IndoorEquipTvDisplay", "msg == null");
                    return;
                }
                super.handleMessage(message);
                int i = message.what;
                if (i == 1014) {
                    LogUtil.a("IDEQ_IndoorEquipTvDisplay", "accept update ui msg");
                    if (message.obj instanceof HashMap) {
                        lax.this.b((HashMap) message.obj);
                        return;
                    }
                    return;
                }
                if (i == 10010) {
                    lax.this.mRunway.setBackgroundResource(R.drawable._2131431341_res_0x7f0b0fad);
                    lax.this.a(10020);
                    return;
                }
                if (i != 10011) {
                    switch (i) {
                        case 10020:
                            lax.this.mRunway.setBackgroundResource(R.drawable._2131431342_res_0x7f0b0fae);
                            lax.this.a(10010);
                            break;
                        case 10021:
                            lax.this.mRunway.setBackgroundResource(R.drawable._2131431265_res_0x7f0b0f61);
                            lax.this.a(10011);
                            break;
                        case 10022:
                            lax.this.mRunway.setBackgroundResource(R.drawable._2131431267_res_0x7f0b0f63);
                            lax.this.a(10023);
                            break;
                        case 10023:
                            lax.this.mRunway.setBackgroundResource(R.drawable._2131431268_res_0x7f0b0f64);
                            lax.this.a(10024);
                            break;
                        case 10024:
                            lax.this.mRunway.setBackgroundResource(R.drawable._2131431269_res_0x7f0b0f65);
                            lax.this.a(10021);
                            break;
                    }
                    return;
                }
                lax.this.mRunway.setBackgroundResource(R.drawable._2131431266_res_0x7f0b0f62);
                lax.this.a(10022);
            }
        };
        this.mHasWear = z;
        this.mViewModel = indoorEquipViewModel;
        if (this.mViewModel != null) {
            this.s = this.mViewModel.getSportType();
            this.r = this.mViewModel.getSportStatus();
            this.p = this.mViewModel.a();
            this.k = this.mViewModel.r();
            this.mHasRunPostureDevice = this.mViewModel.p();
            SupportDataRange supportDataRange = this.p;
            if (supportDataRange == null) {
                LogUtil.a("IDEQ_IndoorEquipTvDisplay", "mSupportDataRange == null");
                return;
            }
            LogUtil.a("IDEQ_IndoorEquipTvDisplay", "mSupportDataRange", supportDataRange, Integer.valueOf(supportDataRange.getMinLevel()), Integer.valueOf(this.p.getMaxLevel()));
        }
        LogUtil.a("IDEQ_IndoorEquipTvDisplay", "Constructor, hasRunPostureDevice = ", Boolean.valueOf(this.mHasRunPostureDevice), ",ishasWear = ", Boolean.valueOf(z), ",sportType=", Integer.valueOf(this.s));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        Handler handler = this.d;
        if (handler != null && this.r == 1) {
            handler.sendEmptyMessageDelayed(i, lbv.a(e));
            return;
        }
        if (handler != null) {
            if (this.s == 274) {
                handler.removeMessages(10021);
                this.d.removeMessages(10011);
                this.d.removeMessages(10022);
                this.d.removeMessages(10023);
                this.d.removeMessages(10024);
            } else {
                handler.removeMessages(10010);
                this.d.removeMessages(10020);
            }
        }
        this.h = false;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ie_data_show_landscape_aw70_tv);
        initWindowConfig();
        c(this.r);
        m();
        this.o = true;
    }

    @Override // android.app.Presentation
    public void onDisplayRemoved() {
        LogUtil.a("IDEQ_IndoorEquipTvDisplay", "Tv display destroy.", Integer.valueOf(this.s));
    }

    private void m() {
        initNormalView();
        this.mLogoLayout = (LinearLayout) findViewById(R.id.logoLayout);
        this.mRunway = (ImageView) findViewById(R.id.runway);
        this.mRunwayBackground = (ImageView) findViewById(R.id.runwaybackground);
        if (this.s == 274) {
            LogUtil.a("IDEQ_IndoorEquipTvDisplay", "set Ripple.");
            this.mRunwayBackground.setBackgroundResource(R.drawable._2131431264_res_0x7f0b0f60);
            this.mRunway.setBackgroundResource(R.drawable._2131431265_res_0x7f0b0f61);
        }
        this.l = (HealthProgressBar) findViewById(R.id.hw_recycler_loading_hpb);
        this.mBtIcon = (ImageView) findViewById(R.id.ie_bt_icon);
        this.mBtIcon.setImageDrawable(lbv.bVR_(this.mContext, lbj.c(this.s, true)));
        this.mBtBoltConnectIcon = (ImageView) findViewById(R.id.ie_bolt_icon);
        if (this.mViewModel != null && this.mViewModel.t()) {
            this.mBtBoltConnectIcon.setVisibility(0);
            this.mBtBoltConnectIcon.setImageResource(lbv.a(this.mViewModel.n()));
            changeBtBoltIconSize();
        }
        this.mLogoImage = (ImageView) findViewById(R.id.logoImage);
        this.n = (HealthTextView) findViewById(R.id.logoText);
        this.i = new lcd(getWindow().getDecorView().getRootView(), this.mHasWear, this.mViewModel, this.mContext);
        LogUtil.a("IDEQ_IndoorEquipTvDisplay", "mSupportDataRange get", Integer.valueOf(this.p.getMaxLevel()), "min", Integer.valueOf(this.p.getMinLevel()));
        this.i.e(this.mScale, this.mCurrentDisplayDensity, this.p);
        f();
        e();
        d();
        c();
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void initNormalView() {
        this.t = (ViewStub) findViewById(R.id.view_stub_heartrate_distance);
        if (isTargetType()) {
            initTargetLayout();
        } else {
            this.t.setVisibility(0);
        }
        this.f14735a = (HealthTextView) findViewById(R.id.distance_value);
        this.b = (HealthTextView) findViewById(R.id.distance_unit);
        this.j = (RelativeLayout) findViewById(R.id.heartrate_layout);
        this.mHeartRateImage = (ImageView) findViewById(R.id.heartrate_image);
        this.f = (ImageView) findViewById(R.id.heartrate_device);
        this.mBackground = (ImageView) findViewById(R.id.background_layout_tv);
        if (this.mHasWear) {
            this.mHeartRateType = (HealthTextView) findViewById(R.id.heartrate_type);
            this.mHeartRate = (HealthTextView) findViewById(R.id.heartrate_value);
            this.mHeartRateUnit = (HealthTextView) findViewById(R.id.heartrate_unit);
        }
    }

    private void e() {
        if (isTargetType()) {
            this.mBackground.setVisibility(0);
            changeBackgroundSize();
        } else {
            h();
        }
        k();
        l();
        i();
    }

    private void h() {
        if (this.mHasWear) {
            this.j.setVisibility(0);
            if (this.mHeartRateType.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mHeartRateType.getLayoutParams();
                layoutParams.width = (int) (this.mScale * dp2px(85.0f));
                this.mHeartRateType.setLayoutParams(layoutParams);
                ViewGroup.LayoutParams layoutParams2 = this.t.getLayoutParams();
                layoutParams2.width = -2;
                this.t.setLayoutParams(layoutParams2);
            }
            this.mHeartRateUnit.setText(lbv.d(0, this.mContext));
            ThreadPoolManager.d().execute(new Runnable() { // from class: laz
                @Override // java.lang.Runnable
                public final void run() {
                    lax.this.a();
                }
            });
            return;
        }
        this.j.setVisibility(8);
        this.mBackground.setVisibility(0);
        changeBackgroundSize();
        this.f14735a.setTextSize(0, dp2px(80.0f) * this.mScale);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(0, 0, 0, nsn.c(this.mContext, this.mScale * 3.0f));
        layoutParams3.addRule(14);
        this.f14735a.setLayoutParams(layoutParams3);
        this.b.setTextSize(0, dp2px(13.0f) * this.mScale);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(3, R.id.distance_value);
        layoutParams4.addRule(14);
        this.b.setLayoutParams(layoutParams4);
    }

    /* synthetic */ void a() {
        initHeartRateZone(this.s);
    }

    private void k() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.land_left_data_layout);
        if (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            if (this.s == 265) {
                layoutParams.setMarginStart((int) (this.mScale * dp2px(4.0f)));
            } else {
                layoutParams.setMarginStart((int) (this.mScale * dp2px(10.0f)));
            }
            linearLayout.setLayoutParams(layoutParams);
        }
    }

    private void l() {
        if (this.s == 281) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.land_right_data_layout);
            if (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.setMarginStart((int) (this.mScale * dp2px(4.0f)));
                layoutParams.setMargins(0, (int) (this.mScale * dp2px(35.0f)), 0, (int) (this.mScale * dp2px(85.0f)));
                linearLayout.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.land_layout_chart);
        if (linearLayout2.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) linearLayout2.getLayoutParams();
            layoutParams2.width = (int) (this.mScale * dp2px(180.0f));
            layoutParams2.setMargins(0, (int) (this.mScale * dp2px(5.0f)), 0, (int) (this.mScale * dp2px(51.8f)));
            layoutParams2.setMarginEnd((int) (this.mScale * dp2px(20.0f)));
            linearLayout2.setLayoutParams(layoutParams2);
        }
    }

    private void f() {
        if (!isTargetType()) {
            if (this.mHasWear) {
                this.mHeartRateType.setTextSize(0, this.mScale * this.mHeartRateType.getTextSize());
                this.mHeartRate.setTextSize(0, this.mScale * this.mHeartRate.getTextSize());
                this.mHeartRateUnit.setTextSize(0, this.mScale * this.mHeartRateUnit.getTextSize());
                this.mHeartRateUnit.setMaxWidth((int) (this.mScale * this.mHeartRateUnit.getMaxWidth()));
            }
            this.f14735a.setTextSize(0, this.mScale * this.f14735a.getTextSize());
            this.b.setTextSize(0, this.mScale * this.b.getTextSize());
            j();
        }
        this.n.setTextSize(0, this.mScale * this.n.getTextSize());
    }

    private void j() {
        if ((LanguageUtil.aa(this.mContext) || LanguageUtil.bf(this.mContext) || LanguageUtil.bb(this.mContext) || LanguageUtil.an(this.mContext) || LanguageUtil.ba(this.mContext) || LanguageUtil.q(this.mContext) || LanguageUtil.ag(this.mContext) || LanguageUtil.p(this.mContext)) && this.mHeartRateUnit != null) {
            this.mHeartRateUnit.setTextSize(0, this.mHeartRateUnit.getTextSize() * 0.7f);
        }
    }

    private void d() {
        changeBtIconSize();
        changeRunwaySize();
        changeLogoSize();
        if (this.mHasWear) {
            b();
        }
    }

    private void b() {
        if (this.mHeartRateImage == null || this.f == null) {
            return;
        }
        if (this.mHeartRateImage.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mHeartRateImage.getLayoutParams();
            layoutParams.width = (int) (this.mScale * dp2px(196.0f));
            layoutParams.height = (int) (this.mScale * dp2px(163.0f));
            this.mHeartRateImage.setLayoutParams(layoutParams);
        }
        if (this.f.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
            layoutParams2.width = (int) (this.mScale * dp2px(12.0f));
            layoutParams2.height = (int) (this.mScale * dp2px(12.0f));
            this.f.setLayoutParams(layoutParams2);
        }
    }

    private void c() {
        changeLogoMargin();
        if (isTargetType()) {
            return;
        }
        if (this.mHasWear) {
            if (this.f.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
                layoutParams.setMargins(0, (int) (this.mScale * dp2px(2.0f)), 0, 0);
                this.f.setLayoutParams(layoutParams);
            }
            if (this.mHeartRateType.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mHeartRateType.getLayoutParams();
                layoutParams2.setMargins(0, (int) (this.mScale * dp2px(43.5f)), 0, 0);
                this.mHeartRateType.setLayoutParams(layoutParams2);
            }
        }
        if (this.b.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) this.b.getLayoutParams()).setMarginStart((int) (this.mScale * dp2px(24.0f)));
        }
    }

    private void i() {
        SportDetailChartDataType[] a2;
        if (this.mViewModel == null || (a2 = lbj.a(this.s, this.mViewModel.t(), this.mViewModel.q(), this.mViewModel.r())) == null || a2.length == 0) {
            return;
        }
        LogUtil.a("IDEQ_IndoorEquipTvDisplay", "sport chart:", Arrays.toString(a2));
        for (SportDetailChartDataType sportDetailChartDataType : a2) {
            d(sportDetailChartDataType, c(sportDetailChartDataType));
        }
    }

    private BaseRealTimeDynamicChartViewModel c(SportDetailChartDataType sportDetailChartDataType) {
        BaseRealTimeDynamicChartViewModel lcwVar;
        switch (AnonymousClass4.f14736a[sportDetailChartDataType.ordinal()]) {
            case 1:
                lcwVar = new lcw(c(R.id.divide_step_rate_chart, R.id.step_rate_chart));
                break;
            case 2:
                lcwVar = new lcj(c(R.id.divide_ground_impact_magnet, R.id.ground_impact_magnet));
                break;
            case 3:
                lcwVar = new lch(c(R.id.divide_ground_shock_peak, R.id.ground_shock_peak));
                break;
            case 4:
                lcwVar = new lci(c(R.id.divide_touchdown_magnet, R.id.touchdown_magnet));
                this.g = true;
                break;
            case 5:
                lcwVar = new lcp(c(R.id.divide_rt_pace_chart, R.id.rt_pace_chart));
                break;
            case 6:
                lcwVar = new lcs(c(R.id.divide_paddle_freq_magnet, R.id.paddle_freq_magnet), this.k);
                break;
            case 7:
                lcwVar = new lcf(c(R.id.divide_cadence_chart, R.id.cadence_chart));
                break;
            case 8:
                lcwVar = new lcr(c(R.id.divide_power_chart, R.id.power_chart));
                break;
            case 9:
                lcwVar = new lct(c(R.id.divide_speed_rate_chart, R.id.speed_rate_chart));
                break;
            default:
                lcwVar = null;
                break;
        }
        c(sportDetailChartDataType, lcwVar);
        return lcwVar;
    }

    private void c(SportDetailChartDataType sportDetailChartDataType, BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel) {
        if (baseRealTimeDynamicChartViewModel != null) {
            if (sportDetailChartDataType == SportDetailChartDataType.CADENCE && this.s == 273) {
                baseRealTimeDynamicChartViewModel.setOrdinateY(0, 120);
            } else {
                baseRealTimeDynamicChartViewModel.setDefaultOrdinateY();
            }
        }
    }

    private RealTimeDynamicChartView c(int i, int i2) {
        View findViewById = findViewById(i);
        if (findViewById != null) {
            findViewById.setVisibility(0);
            findViewById.getLayoutParams().height = (int) (this.mScale * dp2px(16.0f));
        }
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) findViewById(i2);
        if (realTimeDynamicChartView != null) {
            realTimeDynamicChartView.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = realTimeDynamicChartView.getLayoutParams();
            if (layoutParams == null) {
                return realTimeDynamicChartView;
            }
            layoutParams.height = ((int) this.mScale) * dp2px(88.0f);
            realTimeDynamicChartView.setLayoutParams(layoutParams);
        }
        return realTimeDynamicChartView;
    }

    private void d(SportDetailChartDataType sportDetailChartDataType, BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel) {
        if (baseRealTimeDynamicChartViewModel != null) {
            baseRealTimeDynamicChartViewModel.setScale(this.mScale);
            if (baseRealTimeDynamicChartViewModel.getTitleTextLength() > 4) {
                baseRealTimeDynamicChartViewModel.setSmallTextSize();
            } else {
                baseRealTimeDynamicChartViewModel.setNormalTextSize();
            }
            baseRealTimeDynamicChartViewModel.updateConfiguration(this.mContext);
            this.c.put(sportDetailChartDataType, baseRealTimeDynamicChartViewModel);
        }
    }

    private void d(Map<Integer, Object> map) {
        for (Map.Entry<SportDetailChartDataType, BaseRealTimeDynamicChartViewModel> entry : this.c.entrySet()) {
            SportDetailChartDataType key = entry.getKey();
            BaseRealTimeDynamicChartViewModel value = entry.getValue();
            if (value != null) {
                int i = AnonymousClass4.f14736a[key.ordinal()];
                if (i != 1) {
                    if (i == 2 || i == 3 || i == 4) {
                        a(map, key, value);
                    } else {
                        c(key, map, value);
                    }
                } else if (map.get(4) != null) {
                    value.pushNewData(((Integer) map.get(4)).intValue(), e);
                }
            }
        }
    }

    private void c(SportDetailChartDataType sportDetailChartDataType, Map<Integer, Object> map, BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel) {
        switch (sportDetailChartDataType) {
            case REALTIME_PACE:
                if (map.get(14) != null) {
                    baseRealTimeDynamicChartViewModel.pushNewData(((Integer) map.get(14)).intValue());
                    break;
                }
                break;
            case PADDLE_FREQUENCY:
                if (map.get(26) != null) {
                    baseRealTimeDynamicChartViewModel.pushNewData(map.get(26) instanceof Float ? ((Float) map.get(26)).floatValue() : 0.0f);
                    break;
                }
                break;
            case CADENCE:
                if (map.get(31) != null) {
                    baseRealTimeDynamicChartViewModel.pushNewData(((Integer) map.get(31)).intValue());
                    break;
                }
                break;
            case POWER:
                if (map.get(7) != null) {
                    baseRealTimeDynamicChartViewModel.pushNewData(((Integer) map.get(7)).intValue());
                    break;
                }
                break;
            case SPEED_RATE:
                if (map.get(3) != null) {
                    baseRealTimeDynamicChartViewModel.pushNewData(((Integer) map.get(3)).intValue() / 100.0f);
                    break;
                }
                break;
        }
    }

    private void a(Map<Integer, Object> map, SportDetailChartDataType sportDetailChartDataType, BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel) {
        if (map.get(20002) != null) {
            ffs ffsVar = (ffs) map.get(20002);
            if (ffsVar != null && this.mHasRunPostureDevice && this.g) {
                e(ffsVar, sportDetailChartDataType);
            } else if (ffsVar == null && this.g) {
                baseRealTimeDynamicChartViewModel.pushNewData(0, 0.0f);
            } else {
                LogUtil.b("IDEQ_IndoorEquipTvDisplay", "updateUi, hasRunPostureDevice = ", Boolean.valueOf(this.mHasRunPostureDevice));
            }
        }
    }

    void c(int i) {
        Handler handler;
        this.r = i;
        if (this.h || i != 1 || (handler = this.d) == null) {
            return;
        }
        this.h = true;
        if (this.s == 274) {
            handler.sendEmptyMessage(10011);
        } else {
            handler.sendEmptyMessage(10010);
        }
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void setBtConnectState(String str) {
        if (AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED.equals(str) || AbstractFitnessClient.ACTION_SERVICE_REDISCOVERIED.equals(str)) {
            this.l.setVisibility(8);
            this.mBtIcon.setImageDrawable(lbv.bVR_(this.mContext, lbj.c(this.s, true)));
        } else if (AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING.equals(str)) {
            this.l.setVisibility(0);
            this.mBtIcon.setImageDrawable(lbv.bVR_(this.mContext, lbj.a(this.s)));
        } else {
            this.l.setVisibility(8);
            this.mBtIcon.setImageDrawable(lbv.bVR_(this.mContext, lbj.c(this.s, false)));
        }
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void updateUi(Map<Integer, Object> map) {
        if (map == null || !this.o) {
            LogUtil.a("IDEQ_IndoorEquipTvDisplay", "updateUi, indoorEquipDataStructForShow = null");
            return;
        }
        Message obtainMessage = this.d.obtainMessage(1014);
        obtainMessage.obj = map;
        this.d.sendMessage(obtainMessage);
    }

    @Override // com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay
    public void updateProgress(int i) {
        if (this.mTargetModeWithProgressViewHolder != null) {
            this.mTargetModeWithProgressViewHolder.a(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Map<Integer, Object> map) {
        String string;
        if (o()) {
            g();
            this.i.e(this.mScale, this.mCurrentDisplayDensity, this.p);
        }
        if (this.mHasWear && this.mHeartRate != null) {
            updateHeartRate(((Integer) map.get(20004)).intValue());
        }
        if (this.mHasRunPostureDevice || this.s != 264) {
            d(map);
        }
        lcd lcdVar = this.i;
        if (lcdVar != null) {
            lcdVar.a(map);
        }
        if (this.mTargetModeWithProgressViewHolder != null) {
            this.mTargetModeWithProgressViewHolder.d(map);
        }
        if (this.s == 274) {
            Object obj = map.get(26);
            if (obj instanceof Float) {
                e = ((Float) obj).floatValue();
            }
        } else {
            if (map.get(3) instanceof Integer) {
                e = ((Integer) r0).intValue() / 100.0f;
            }
        }
        Object obj2 = map.get(1);
        int intValue = obj2 instanceof Integer ? ((Integer) obj2).intValue() : 0;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float f = UnitUtil.h() ? (intValue / 1000.0f) * 0.621371f : intValue / 1000.0f;
        HealthTextView healthTextView = this.f14735a;
        if (healthTextView == null || this.b == null) {
            return;
        }
        healthTextView.setText(decimalFormat.format(f));
        HealthTextView healthTextView2 = this.b;
        if (UnitUtil.h()) {
            string = this.mContext.getResources().getQuantityString(R.plurals._2130903253_res_0x7f0300d5, intValue, "");
        } else {
            string = this.mContext.getString(R.string._2130840225_res_0x7f020aa1);
        }
        healthTextView2.setText(string);
    }

    private void g() {
        LogUtil.a("IDEQ_IndoorEquipTvDisplay", "changeTextLanguage");
        HealthTextView healthTextView = this.n;
        if (healthTextView != null) {
            healthTextView.setText(this.mContext.getString(R.string.IDS_app_name_health));
        }
        if (this.mHeartRateUnit == null || this.mHeartRateType == null) {
            return;
        }
        this.mHeartRateUnit.setText(lbv.d(0, this.mContext));
    }

    private void e(ffs ffsVar, SportDetailChartDataType sportDetailChartDataType) {
        int b = ffsVar.b();
        if (sportDetailChartDataType.equals(SportDetailChartDataType.GROUND_CONTACT_TIME)) {
            this.c.get(sportDetailChartDataType).pushNewData(b, e);
        }
        int e2 = ffsVar.e();
        if (sportDetailChartDataType.equals(SportDetailChartDataType.GROUND_IMPACT_ACCELERATION)) {
            this.c.get(sportDetailChartDataType).pushNewData(e2, e);
        }
        float m = ffsVar.m();
        if (sportDetailChartDataType.equals(SportDetailChartDataType.ACTIVE_PEAK)) {
            this.c.get(sportDetailChartDataType).pushNewData(m, e);
        }
    }

    private String c(Locale locale) {
        if (locale == null) {
            return "";
        }
        return locale.getLanguage() + Constants.LINK + locale.getCountry();
    }

    private boolean o() {
        String c = c(Locale.getDefault());
        if (c.equals(this.m)) {
            return false;
        }
        this.m = c;
        return true;
    }
}
