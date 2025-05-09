package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.model.JudgeRootBean;
import com.huawei.healthcloud.plugintrack.model.PostureJudgeBean;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.healthcloud.plugintrack.model.TrackLineChartHolderImpl;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackLineChartActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.HeartRateFrag;
import com.huawei.healthcloud.plugintrack.ui.viewholder.TrackChartViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.view.trackview.TrackLineChartHolder;
import defpackage.bkz;
import defpackage.ffn;
import defpackage.ffw;
import defpackage.gvv;
import defpackage.gxs;
import defpackage.hix;
import defpackage.hji;
import defpackage.hjm;
import defpackage.hjs;
import defpackage.hjt;
import defpackage.hkb;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.knw;
import defpackage.koc;
import defpackage.kog;
import defpackage.kol;
import defpackage.nkz;
import defpackage.nld;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes4.dex */
public class HeartRateFrag extends SportResultBaseFragment {
    private static List<gxs> d;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private int ah;
    private HealthTextView e;
    private HealthColumnSystem h;
    private List<HeartRateData> k;
    private ArrayList<HeartRateData> m;
    private HealthRingChart o;
    private HealthProgressBar p;
    private List<kog> q;
    private hjm r;
    private ExecutorService s;
    private List<PostureJudgeBean> t;
    private HealthTextView v;
    private ArrayList<StepRateData> w;
    private List<kol> x;
    private float[] z;
    private b g = new b(Looper.getMainLooper());
    private Map<SportDetailChartDataType, hix> j = new HashMap(20);
    private LinearLayout i = null;
    private LinearLayout f = null;
    private boolean n = false;
    private boolean l = false;
    private int u = 0;
    private String y = "";
    private TrackLineChartHolderImpl ae = null;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            throw new AssertionError("LayoutInflater not found.");
        }
        if (o()) {
            return null;
        }
        h();
        this.ae = new TrackLineChartHolderImpl(this.c.getApplicationContext());
        if (m() && k()) {
            this.u = this.f3732a.e().requestSportType();
            this.y = gvv.c(this.f3732a.e());
            u();
            y();
            LogUtil.a("Track_HeartRateFrag", "onCreateView--------");
            View inflate = layoutInflater.inflate(R.layout.track_sug_fm_detail_heartrate, (ViewGroup) null);
            if (!(inflate instanceof FrameLayout)) {
                LogUtil.b("Track_HeartRateFrag", "objectFrameLayout is not instanceof FrameLayout");
                return null;
            }
            FrameLayout frameLayout = (FrameLayout) inflate;
            this.b = frameLayout;
            ben_(frameLayout);
            p();
            return frameLayout;
        }
        LogUtil.b("Track_HeartRateFrag", "mMotionPath or mSimplifyData null");
        getActivity().finish();
        return null;
    }

    private void y() {
        this.r = new hjm(this.f3732a.d(), this.f3732a.e(), this.c, this.f3732a.e().requestSportType());
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("Track_HeartRateFrag", "onConfigurationChanged isTahitiModel: ", Boolean.valueOf(nsn.ag(getContext())));
        ai();
        hjm hjmVar = this.r;
        if (hjmVar != null) {
            hjmVar.d();
        }
    }

    private void ai() {
        Iterator<Map.Entry<SportDetailChartDataType, hix>> it = this.j.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().a().b();
        }
    }

    private void p() {
        if (k()) {
            if (this.f3732a.e().requestDeviceType() != 32 || ((this.f3732a.r() && this.f3732a.ar()) || this.f3732a.e().requestSportDataSource() == 5)) {
                this.e.setVisibility(8);
            }
        }
    }

    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.a("Track_HeartRateFrag", "message is null");
                return;
            }
            super.handleMessage(message);
            if (message.what == 106) {
                if (message.obj instanceof int[]) {
                    HeartRateFrag.this.d((int[]) message.obj, message.arg1, message.getData().getString(HwExerciseConstants.JSON_NAME_RECORD_FLAG));
                    return;
                }
                return;
            }
            LogUtil.h("Track_HeartRateFrag", "MyHandler is wrong");
        }
    }

    private void u() {
        MotionPath d2 = this.f3732a.d();
        if (d2 == null) {
            LogUtil.a("Track_HeartRateFrag", "motionPath is null return");
            return;
        }
        this.x = d2.requestSwolfList();
        this.q = d2.requestPullFreqList();
        t();
        v();
        this.ae.setSportType(this.u);
        this.ae.setSportMode(this.y);
    }

    private void t() {
        JudgeRootBean judgeRootBean = (JudgeRootBean) HiJsonUtil.e(hjt.e(BaseApplication.getContext()), JudgeRootBean.class);
        if (judgeRootBean == null) {
            return;
        }
        List<PostureJudgeBean> judge = judgeRootBean.getJudge();
        this.t = judge;
        if (judge == null) {
            LogUtil.b("Track_HeartRateFrag", "mPostureJudgeBeanList is null");
        } else {
            LogUtil.a("Track_HeartRateFrag", "mPostureJudgeBeanList.toString", judge.toString());
        }
    }

    private void v() {
        hkb hkbVar = new hkb(this.ae, this.f3732a);
        hkbVar.a(SportDetailChartDataType.HEART_RATE, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.STEP_RATE, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.ALTITUDE, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.SPEED_RATE, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.REALTIME_PACE, this.f3732a.e(), this.f3732a.d());
        this.ae.j(this.x);
        this.ae.h(this.q);
        hkbVar.a(SportDetailChartDataType.GROUND_CONTACT_TIME, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.HANG_TIME, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.GROUND_HANG_TIME_RATE, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.GROUND_IMPACT_ACCELERATION, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.SPO2, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.JUMP_TIME, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.JUMP_HEIGHT, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.CADENCE, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.PADDLE_FREQUENCY, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.POWER, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.HEART_RATE_RECOVERY, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.VERTICAL_OSCILLATION, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.VERTICAL_RATIO, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.GC_TIME_BALANCE, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.ACTIVE_PEAK, this.f3732a.e(), this.f3732a.d());
        hkbVar.a(SportDetailChartDataType.PEAK_WEIGHT, this.f3732a.e(), this.f3732a.d());
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        LogUtil.b("Track_HeartRateFrag", "onStart ---- --------------");
        if (this.s == null) {
            this.s = Executors.newSingleThreadExecutor();
        }
        this.s.execute(new Runnable() { // from class: hgz
            @Override // java.lang.Runnable
            public final void run() {
                HeartRateFrag.this.g();
            }
        });
    }

    public /* synthetic */ void g() {
        if (this.n) {
            LogUtil.b("Track_HeartRateFrag", "mMotionPath or mSimplifyData null");
        } else {
            x();
        }
    }

    private void ben_(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.heart_rate_chart_show_layout);
        this.f = linearLayout;
        this.o = (HealthRingChart) linearLayout.findViewById(R.id.ring_view);
        this.p = (HealthProgressBar) view.findViewById(R.id.sug_detail_loading);
        this.i = (LinearLayout) view.findViewById(R.id.heart_rate_large_scale_layout);
        this.v = (HealthTextView) view.findViewById(R.id.swin_heart_tip);
        this.e = (HealthTextView) view.findViewById(R.id.text_accuracy_tip);
        bes_(view);
        ber_(view);
        ad();
        ab();
        w();
        TrackLineChartHolder.setInstance(this.ae);
        aa();
        f();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportResultBaseFragment
    protected int c() {
        int i = this.u;
        if (i == 258) {
            return 4154;
        }
        return i == 264 ? 4175 : -1;
    }

    private void ab() {
        int i = this.u;
        if ((i == 258 || i == 264) && !this.f3732a.an()) {
            for (Map.Entry<SportDetailChartDataType, hix> entry : this.j.entrySet()) {
                e(entry.getKey(), entry.getValue());
            }
        }
    }

    private void e(SportDetailChartDataType sportDetailChartDataType, hix hixVar) {
        int i = AnonymousClass4.c[sportDetailChartDataType.ordinal()];
        if ((i != 1 && i != 2 && i != 3) || hixVar.i() || bkz.e(this.t)) {
            return;
        }
        MotionPath d2 = this.f3732a.d();
        if (this.f3732a.l()) {
            LogUtil.h("Track_HeartRateFrag", "invalidPostureJudgeList");
            return;
        }
        hjs hjsVar = new hjs(BaseApplication.getContext(), sportDetailChartDataType, this.t, hjt.b(hjt.c(d2, this.t.get(0)), sportDetailChartDataType, d2.requestRunningPostureList(), this.t));
        float[] e = hjt.e(new HealthColumnSystem(BaseApplication.getContext()), false);
        hjsVar.a(BaseApplication.getContext().getResources().getConfiguration().fontScale);
        hjsVar.b(hixVar.j(), e);
        hixVar.c(hjsVar);
    }

    private void ber_(View view) {
        Map<SportDetailChartDataType, hix> g = this.f3732a.g();
        this.j = g;
        for (Map.Entry<SportDetailChartDataType, hix> entry : g.entrySet()) {
            beo_(view, entry.getKey(), entry.getValue());
        }
    }

    private void beo_(View view, SportDetailChartDataType sportDetailChartDataType, hix hixVar) {
        switch (sportDetailChartDataType) {
            case GROUND_CONTACT_TIME:
                hixVar.bgC_(view, R.id.layout_ground_contact_time);
                break;
            case HEART_RATE:
                hixVar.bgE_(view, R.id.layout_heartrate);
                hixVar.bgF_(view, R.id.view_div_heart);
                break;
            case STEP_RATE:
                hixVar.bgE_(view, R.id.layout_steprate);
                hixVar.bgF_(view, R.id.view_div_step);
                break;
            case SPEED_RATE:
                hixVar.bgE_(view, R.id.layout_speed);
                hixVar.bgF_(view, R.id.view_div_speed);
                break;
            case ALTITUDE:
                hixVar.bgE_(view, R.id.layout_altitude);
                hixVar.bgF_(view, R.id.view_div_altitude);
                break;
            case CADENCE:
                hixVar.bgE_(view, R.id.layout_cadencerate);
                hixVar.bgF_(view, R.id.view_div_cadence);
                break;
            case PADDLE_FREQUENCY:
                hixVar.bgE_(view, R.id.layout_paddle);
                hixVar.bgF_(view, R.id.view_div_paddle);
                break;
            case REALTIME_PACE:
                hixVar.bgE_(view, R.id.layout_rt_pace);
                hixVar.bgF_(view, R.id.view_div_rt_pace);
                break;
            case PULL_FREQ:
                hixVar.bgE_(view, R.id.layout_pull_freq);
                hixVar.bgF_(view, R.id.view_div_pull_freq);
                break;
            case SWOLF:
                hixVar.bgE_(view, R.id.layout_swolf);
                break;
            case SPO2:
                hixVar.bgE_(view, R.id.layout_spo2);
                hixVar.bgF_(view, R.id.view_div_spo2);
                break;
            default:
                bep_(view, sportDetailChartDataType, hixVar);
                break;
        }
    }

    private void bep_(View view, SportDetailChartDataType sportDetailChartDataType, hix hixVar) {
        int i = AnonymousClass4.c[sportDetailChartDataType.ordinal()];
        if (i == 1) {
            hixVar.bgC_(view, R.id.layout_ground_hang_time_rate);
        }
        if (i == 2) {
            hixVar.bgC_(view, R.id.layout_hang_time);
            return;
        }
        switch (i) {
            case 14:
                hixVar.bgE_(view, R.id.layout_ground_impact_acceleration);
                hixVar.bgF_(view, R.id.view_div_ground_impact_acceleration_tip);
                break;
            case 15:
                hixVar.bgE_(view, R.id.layout_gc_time_balance);
                hixVar.bgF_(view, R.id.view_div_gc_time_balance);
                break;
            case 16:
                hixVar.bgE_(view, R.id.layout_vertical_oscillation);
                hixVar.bgF_(view, R.id.view_div_vertical_oscillation);
                break;
            case 17:
                hixVar.bgE_(view, R.id.layout_vertical_ratio);
                hixVar.bgF_(view, R.id.view_div_vertical_ratio);
                break;
            case 18:
                hixVar.bgE_(view, R.id.layout_jump_height);
                break;
            case 19:
                hixVar.bgE_(view, R.id.layout_jump_time);
                hixVar.bgF_(view, R.id.view_div_jump_time);
                break;
            case 20:
                hixVar.bgE_(view, R.id.layout_power);
                hixVar.bgF_(view, R.id.view_div_power);
                break;
            case 21:
                hixVar.bgE_(view, R.id.layout_restore_heart_rate);
                hixVar.bgF_(view, R.id.view_restore_heart_rate);
                break;
            default:
                beq_(view, sportDetailChartDataType, hixVar);
                break;
        }
    }

    private void beq_(View view, SportDetailChartDataType sportDetailChartDataType, hix hixVar) {
        int i = AnonymousClass4.c[sportDetailChartDataType.ordinal()];
        if (i == 22) {
            hixVar.bgE_(view, R.id.layout_active_peak);
            hixVar.bgF_(view, R.id.view_div_active_peak);
        } else if (i == 23) {
            hixVar.bgE_(view, R.id.layout_peak_weight);
            hixVar.bgF_(view, R.id.view_div_peak_weight);
        } else {
            LogUtil.h("Track_HeartRateFrag", "The chart type is not exist.", sportDetailChartDataType.toString());
        }
    }

    private void bes_(View view) {
        LogUtil.a("Track_HeartRateFrag", "init pace");
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.c);
        this.h = healthColumnSystem;
        float[] c = this.r.c(this.u, healthColumnSystem, false);
        this.z = c;
        this.r.bgO_(view, c, this.h, false);
        c(this.r.a());
    }

    private static void c(List<gxs> list) {
        d = list;
    }

    public static List<gxs> a() {
        if (d != null) {
            return new ArrayList(d);
        }
        return Collections.EMPTY_LIST;
    }

    private void ad() {
        for (Map.Entry<SportDetailChartDataType, hix> entry : this.j.entrySet()) {
            j(entry.getKey(), entry.getValue());
        }
    }

    private void j(SportDetailChartDataType sportDetailChartDataType, hix hixVar) {
        hixVar.a(d(sportDetailChartDataType));
    }

    private TrackChartViewHolder d(SportDetailChartDataType sportDetailChartDataType) {
        switch (sportDetailChartDataType) {
            case HEART_RATE:
                return new TrackChartViewHolder(this.c, 100, false, 1);
            case STEP_RATE:
                return new TrackChartViewHolder(this.c, 100, false, 2);
            case SPEED_RATE:
                return new TrackChartViewHolder(this.c, 100, false, 3);
            case ALTITUDE:
                return new TrackChartViewHolder(this.c, 100, false, 100);
            case CADENCE:
                return new TrackChartViewHolder(this.c, 100, false, 13);
            case PADDLE_FREQUENCY:
            default:
                return e(sportDetailChartDataType);
            case REALTIME_PACE:
                int i = this.u;
                if (i == 266 || i == 262) {
                    return new TrackChartViewHolder(this.c, 100, false, 6);
                }
                if (i == 274) {
                    return new TrackChartViewHolder(this.c, 100, false, 17);
                }
                if (hji.j(i)) {
                    return new TrackChartViewHolder(this.c, 100, false, 26);
                }
                return new TrackChartViewHolder(this.c, 100, false, 9);
            case PULL_FREQ:
                return new TrackChartViewHolder(this.c, 100, false, 5);
        }
    }

    private TrackChartViewHolder e(SportDetailChartDataType sportDetailChartDataType) {
        int i = AnonymousClass4.c[sportDetailChartDataType.ordinal()];
        if (i == 1) {
            return new TrackChartViewHolder(this.c, 100, false, 19);
        }
        if (i == 2) {
            return new TrackChartViewHolder(this.c, 100, false, 18);
        }
        if (i != 3) {
            switch (i) {
                case 12:
                    return new TrackChartViewHolder(this.c, 100, false, 4);
                case 13:
                    return new TrackChartViewHolder(this.c, 100, false, 10);
                case 14:
                    return new TrackChartViewHolder(this.c, 100, false, 8);
                default:
                    switch (i) {
                        case 18:
                            return new TrackChartViewHolder(this.c, 100, false, 12);
                        case 19:
                            return new TrackChartViewHolder(this.c, 100, false, 11);
                        case 20:
                            return new TrackChartViewHolder(this.c, 100, false, 15);
                        case 21:
                            return new TrackChartViewHolder(this.c, 100, false, 124);
                        default:
                            return c(sportDetailChartDataType);
                    }
            }
        }
        return new TrackChartViewHolder(this.c, 100, false, 7);
    }

    private TrackChartViewHolder c(SportDetailChartDataType sportDetailChartDataType) {
        int i = AnonymousClass4.c[sportDetailChartDataType.ordinal()];
        if (i == 9) {
            if ("291".equals(this.y)) {
                return new TrackChartViewHolder(this.c, 100, false, 25);
            }
            return new TrackChartViewHolder(this.c, 100, false, 14);
        }
        if (i == 22) {
            return new TrackChartViewHolder(this.c, 100, false, 20);
        }
        if (i != 23) {
            switch (i) {
                case 15:
                    return new TrackChartViewHolder(this.c, 100, false, 21);
                case 16:
                    return new TrackChartViewHolder(this.c, 100, false, 23);
                case 17:
                    return new TrackChartViewHolder(this.c, 100, false, 22);
                default:
                    return null;
            }
        }
        return new TrackChartViewHolder(this.c, 100, false, 24);
    }

    private void w() {
        Iterator<Map.Entry<SportDetailChartDataType, hix>> it = this.j.entrySet().iterator();
        while (it.hasNext()) {
            hix value = it.next().getValue();
            TrackChartViewHolder a2 = value.a();
            value.d(a2.getCombinedChart());
            value.d(a2.d());
            value.bgD_(a2.blI_());
            value.e().setLogEnabled(true);
            if (nsn.cLh_(getActivity()) && value.d() != null) {
                value.d().setVisibility(8);
                value.bgA_().setVisibility(8);
            }
            value.bgB_().addView(a2);
        }
    }

    private void c(hix hixVar, String str, int i) {
        e(hixVar, str, i, this.c, this.u);
    }

    public void e(hix hixVar, final String str, final int i, final Context context, final int i2) {
        HealthTextView d2 = hixVar.d();
        HwHealthBaseCombinedChart e = hixVar.e();
        d2.setOnClickListener(new View.OnClickListener() { // from class: hhm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeartRateFrag.this.bew_(context, str, i, i2, view);
            }
        });
        e.setOnSingleTapConfirmedListener(new HwHealthBaseCombinedChart.OnSingleTapConfirmedListener() { // from class: hhn
            @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.OnSingleTapConfirmedListener
            public final boolean isOnSingleTapConfirmed(MotionEvent motionEvent) {
                return HeartRateFrag.this.bex_(context, str, i, i2, motionEvent);
            }
        });
    }

    public /* synthetic */ void bew_(Context context, String str, int i, int i2, View view) {
        d(context, str, i, i2);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ boolean bex_(Context context, String str, int i, int i2, MotionEvent motionEvent) {
        d(context, str, i, i2);
        return true;
    }

    private void aa() {
        for (Map.Entry<SportDetailChartDataType, hix> entry : this.j.entrySet()) {
            SportDetailChartDataType key = entry.getKey();
            hix value = entry.getValue();
            HealthTextView d2 = value.d();
            if (nsn.cLh_(getActivity()) && value.e() != null) {
                value.e().setOnSingleTapConfirmedListener(null);
            } else {
                d(key, value, d2);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        Map<SportDetailChartDataType, hix> map = this.j;
        if (map == null || map.isEmpty()) {
            LogUtil.h("Track_HeartRateFrag", "onMultiWindowModeChanged mChartModelMap is null.");
            return;
        }
        Iterator<Map.Entry<SportDetailChartDataType, hix>> it = this.j.entrySet().iterator();
        while (it.hasNext()) {
            a(z, it.next());
        }
    }

    private void a(boolean z, Map.Entry<SportDetailChartDataType, hix> entry) {
        if (entry == null) {
            LogUtil.h("Track_HeartRateFrag", "onMultiWindowModeChanged entry is null.");
            return;
        }
        hix value = entry.getValue();
        if (value == null) {
            LogUtil.h("Track_HeartRateFrag", "onMultiWindowModeChanged chartModel is null.");
            return;
        }
        HealthTextView d2 = value.d();
        ImageView bgA_ = value.bgA_();
        if (d2 == null) {
            LogUtil.h("Track_HeartRateFrag", "onMultiWindowModeChanged detailBtn is null.");
            return;
        }
        if (z) {
            d2.setVisibility(8);
            bgA_.setVisibility(8);
            if (value.e() != null) {
                value.e().setOnSingleTapConfirmedListener(null);
                return;
            }
            return;
        }
        d2.setVisibility(0);
        d(entry.getKey(), value, d2);
    }

    private void d(SportDetailChartDataType sportDetailChartDataType, hix hixVar, HealthTextView healthTextView) {
        ImageView bgA_ = hixVar.bgA_();
        int i = AnonymousClass4.c[sportDetailChartDataType.ordinal()];
        if (i != 13) {
            switch (i) {
                case 4:
                    c(hixVar, "BASELINE_HEART_RATE", 0);
                    hji.bgM_(0, bgA_, ObserveLabels.HEART_RATE);
                    break;
                case 5:
                    c(hixVar, "BASELINE_STEP_FRE", 1);
                    hji.bgM_(1, bgA_, this.u == 273 ? "CROSS_TRAINER_STEP_RATE" : "STEP_RATE");
                    break;
                case 6:
                    c(hixVar, "BASELINE_SPEED_RATE", 3);
                    break;
                case 7:
                    c(hixVar, "BASELINE_ALTITUDE", 2);
                    break;
                case 8:
                    c(hixVar, "BASELINE_CADENCE_FRE", 12);
                    hji.bgM_(12, bgA_, this.u == 273 ? "CROSS_TRAINER_CADENCE" : "CADENCE");
                    break;
                case 9:
                    c(hixVar, "BASELINE_PADDLE_FRE", 13);
                    break;
                case 10:
                    c(hixVar, "BASELINE_REALTIME_PACE", 6);
                    hji.bgM_(6, bgA_, "REALTIME_PACE");
                    break;
                default:
                    bet_(sportDetailChartDataType, hixVar, healthTextView, bgA_);
                    break;
            }
        }
        c(hixVar, "BASELINE_SPO2", 9);
    }

    private void bet_(SportDetailChartDataType sportDetailChartDataType, hix hixVar, HealthTextView healthTextView, ImageView imageView) {
        int i = AnonymousClass4.c[sportDetailChartDataType.ordinal()];
        if (i == 1) {
            c(hixVar, "BASELINE_GROUND_HANG_TIME_RATE", 17);
            hji.bgM_(17, imageView, "IMPACT_HANG_TIME_RATE");
            return;
        }
        if (i == 2) {
            c(hixVar, "BASELINE_HANG_TIME", 16);
            hji.bgM_(16, imageView, "HANG_TIME");
            return;
        }
        if (i != 3) {
            switch (i) {
                case 14:
                    c(hixVar, "BASELINE_GROUND_IMPACT_ACCELERATION", 8);
                    hji.bgM_(8, imageView, "LANDING_IMPACT");
                    break;
                case 15:
                    c(hixVar, "BASELINE_GC_TIME_BALANCE", 19);
                    hji.bgM_(19, imageView, "PARAMETER_GROUND_TIME_BALANCE");
                    break;
                case 16:
                    c(hixVar, "BASELINE_VERTICAL_OSCILLATION", 18);
                    hji.bgM_(18, imageView, "PARAMETER_VERTICAL_OSCILLATION");
                    break;
                case 17:
                    c(hixVar, "BASELINE_VERTICAL_RATIO", 20);
                    hji.bgM_(20, imageView, "VERTICAL_STRIKE_RATE");
                    break;
                default:
                    beu_(sportDetailChartDataType, hixVar, healthTextView, imageView);
                    break;
            }
            return;
        }
        c(hixVar, "BASELINE_GROUND_CONTACT_TIME", 7);
        hji.bgM_(7, imageView, "GROUND_CONTACT_TIME");
    }

    private void beu_(SportDetailChartDataType sportDetailChartDataType, hix hixVar, HealthTextView healthTextView, ImageView imageView) {
        int i = AnonymousClass4.c[sportDetailChartDataType.ordinal()];
        if (i == 11) {
            c(hixVar, "BASELINE_PULL_FREQ", 5);
        }
        if (i == 12) {
            c(hixVar, "BASELINE_SWOLF", 4);
            return;
        }
        switch (i) {
            case 18:
                c(hixVar, "BASELINE_JUMP_HEIGHT", 11);
                break;
            case 19:
                c(hixVar, "BASELINE_JUMP_TIME", 10);
                break;
            case 20:
                c(hixVar, "BASELINE_POWER", 14);
                break;
            case 21:
                healthTextView.setVisibility(8);
                hji.bgM_(29, imageView, "RECOVERY_HEART_RATE");
                break;
            case 22:
                c(hixVar, "BASELINE_ACTIVE_PEAK", 21);
                hji.bgM_(21, imageView, "GROUND_IMPACT_PEAK");
                break;
            case 23:
                c(hixVar, "BASELINE_WEIGHT", 18);
                break;
        }
    }

    private void d(Context context, String str, int i, int i2) {
        if (this.l) {
            return;
        }
        b();
        if (context == null) {
            return;
        }
        e(context, str, i2);
        a(i);
    }

    private void e(Context context, String str, int i) {
        if (!(context instanceof Activity)) {
            LogUtil.h("Track_HeartRateFrag", "wrong context");
            return;
        }
        Intent intent = new Intent(context, (Class<?>) TrackLineChartActivity.class);
        intent.putExtra("KEY_BASELINE", str);
        intent.putExtra(BleConstants.SPORT_TYPE, i);
        intent.putExtra("rowerStrength", this.y);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("Track_HeartRateFrag", "launchHorizontalScreenActivity() exception: ", LogAnonymous.b((Throwable) e));
        }
    }

    private void a(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("chartType", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.BI_TRACK_ENTER_HORIZONTAL_CHART_1040032.value(), hashMap, 0);
    }

    public void b() {
        this.g.postDelayed(new Runnable() { // from class: hhc
            @Override // java.lang.Runnable
            public final void run() {
                HeartRateFrag.this.i();
            }
        }, 500L);
        this.l = true;
    }

    public /* synthetic */ void i() {
        this.l = false;
    }

    public boolean e() {
        return this.l;
    }

    private void x() {
        LogUtil.b("Track_HeartRateFrag", "initData ---- --------------");
        this.n = true;
        int requestTrackType = this.f3732a.e().requestTrackType();
        MotionPath d2 = this.f3732a.d();
        if (d2 == null) {
            LogUtil.a("Track_HeartRateFrag", "motionPath is null return");
            return;
        }
        this.w = ffw.d(d2.requestStepRateList(), requestTrackType, 10000);
        this.m = d2.requestHeartRateList();
        this.k = d2.requestHeartRecoveryRateList();
        r();
        HandlerExecutor.e(new Runnable() { // from class: hhi
            @Override // java.lang.Runnable
            public final void run() {
                HeartRateFrag.this.ag();
            }
        });
    }

    private void r() {
        for (Map.Entry<SportDetailChartDataType, hix> entry : this.j.entrySet()) {
            a(entry.getKey(), entry.getValue());
        }
    }

    private void a(SportDetailChartDataType sportDetailChartDataType, hix hixVar) {
        switch (AnonymousClass4.c[sportDetailChartDataType.ordinal()]) {
            case 1:
                h(hixVar);
                break;
            case 2:
                i(hixVar);
                break;
            case 3:
                j(hixVar);
                break;
            case 4:
                n(hixVar);
                break;
            case 5:
                v(hixVar);
                break;
            case 6:
                t(hixVar);
                break;
            case 7:
                c(hixVar);
                break;
            case 8:
                b(hixVar);
                break;
            case 9:
                k(hixVar);
                break;
            case 10:
                r(hixVar);
                break;
            case 11:
                s(hixVar);
                break;
            case 12:
                y(hixVar);
                break;
            case 13:
                w(hixVar);
                break;
            case 14:
                g(hixVar);
                break;
            default:
                b(sportDetailChartDataType, hixVar);
                break;
        }
    }

    private void b(SportDetailChartDataType sportDetailChartDataType, hix hixVar) {
        switch (sportDetailChartDataType) {
            case GC_TIME_BALANCE:
                f(hixVar);
                break;
            case VERTICAL_OSCILLATION:
                x(hixVar);
                break;
            case VERTICAL_RATIO:
                u(hixVar);
                break;
            case JUMP_HEIGHT:
                m(hixVar);
                break;
            case JUMP_TIME:
                o(hixVar);
                break;
            case POWER:
                l(hixVar);
                break;
            case HEART_RATE_RECOVERY:
                q(hixVar);
                break;
            case ACTIVE_PEAK:
                a(hixVar);
                break;
            case PEAK_WEIGHT:
                p(hixVar);
                break;
        }
    }

    private void p(hix hixVar) {
        if (this.f3732a.ae()) {
            aa(hixVar);
            return;
        }
        int extendDataInt = this.f3732a.e().getExtendDataInt("peak_weight");
        if (UnitUtil.h()) {
            extendDataInt = (int) Math.round(UnitUtil.h(extendDataInt));
        }
        hixVar.c(extendDataInt);
    }

    private void q(final hix hixVar) {
        final int i;
        if (hixVar == null) {
            LogUtil.b("Track_HeartRateFrag", "in configRecoverHeartRate chartModel is null");
            return;
        }
        if (this.f3732a.ag()) {
            aa(hixVar);
            return;
        }
        List<HeartRateData> list = this.k;
        if (list != null && list.size() > 0) {
            final int i2 = 0;
            hixVar.bgB_().setVisibility(0);
            hixVar.g().setVisibility(0);
            Iterator<HeartRateData> it = this.k.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = 0;
                    break;
                }
                HeartRateData next = it.next();
                if (next.acquireHeartRate() > 0) {
                    i = next.acquireHeartRate();
                    break;
                }
            }
            int size = this.k.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                if (this.k.get(size).acquireHeartRate() > 0) {
                    i2 = this.k.get(size).acquireHeartRate();
                    break;
                }
                size--;
            }
            hixVar.c((i - i2) / 2.0f);
            HandlerExecutor.e(new Runnable() { // from class: hha
                @Override // java.lang.Runnable
                public final void run() {
                    hix.this.a().a(i, i2);
                }
            });
            return;
        }
        hixVar.bgB_().setVisibility(8);
        hixVar.g().setVisibility(8);
    }

    private void w(hix hixVar) {
        if (hixVar.i()) {
            aa(hixVar);
        }
    }

    private void j(hix hixVar) {
        if (hixVar.i()) {
            aa(hixVar);
        } else {
            hixVar.e(ffw.k(this.f3732a.d().requestRunningPostureList()));
            hixVar.c(this.f3732a.e().requestAvgGroundContactTime());
        }
    }

    private void i(hix hixVar) {
        if (hixVar.i()) {
            aa(hixVar);
        } else {
            hixVar.e(ffw.h(this.f3732a.d().requestRunningPostureList()));
            hixVar.c(this.f3732a.e().requestAverageHangTime());
        }
    }

    private void x(hix hixVar) {
        if (hixVar.i()) {
            aa(hixVar);
            return;
        }
        float m = ffw.m(this.f3732a.d().requestRunningPostureList());
        if (UnitUtil.h()) {
            m = (float) UnitUtil.e(m, 0);
        }
        hixVar.e(m);
        hixVar.c(s());
    }

    private void u(hix hixVar) {
        if (hixVar.i()) {
            aa(hixVar);
        } else {
            hixVar.e(ffw.o(this.f3732a.d().requestRunningPostureList()));
            hixVar.c(this.f3732a.e().getExtendDataFloat("avg_v_s_r"));
        }
    }

    private void f(hix hixVar) {
        if (hixVar.i()) {
            aa(hixVar);
            return;
        }
        float a2 = (float) UnitUtil.a(this.f3732a.e().getExtendDataFloat("avg_gc_tb"), 1);
        hixVar.c(a2);
        hixVar.e(100.0f - a2);
    }

    private void a(hix hixVar) {
        if (hixVar.i()) {
            aa(hixVar);
        } else {
            hixVar.e(ffw.i(this.f3732a.d().requestRunningPostureList()));
            hixVar.c(this.f3732a.e().getExtendDataFloat("avg_i_p"));
        }
    }

    private void h(hix hixVar) {
        if (hixVar.i()) {
            aa(hixVar);
        } else {
            hixVar.e(ffw.n(this.f3732a.d().requestRunningPostureList()));
            hixVar.c(this.f3732a.e().requestGroundHangTimeRate());
        }
    }

    private void g(hix hixVar) {
        if (hixVar.i()) {
            aa(hixVar);
        } else {
            hixVar.e(ffw.f(this.f3732a.d().requestRunningPostureList()));
            hixVar.c(this.f3732a.e().requestAvgGroundImpactAcceleration());
        }
    }

    private void m(hix hixVar) {
        int e;
        int e2;
        if (this.f3732a.w()) {
            aa(hixVar);
            return;
        }
        if (!UnitUtil.h()) {
            e = hji.c(this.f3732a.d().requestJumpDataList());
            e2 = hji.d(this.f3732a.d().requestJumpDataList());
        } else {
            e = (int) UnitUtil.e(hji.c(this.f3732a.d().requestJumpDataList()), 0);
            e2 = (int) UnitUtil.e(hji.d(this.f3732a.d().requestJumpDataList()), 0);
        }
        hixVar.c(e2);
        hixVar.e(e);
    }

    private void o(hix hixVar) {
        if (this.f3732a.w()) {
            aa(hixVar);
            return;
        }
        int a2 = hji.a(this.f3732a.d().requestJumpDataList());
        hixVar.c(hji.b(this.f3732a.d().requestJumpDataList()));
        hixVar.e(a2);
    }

    private void c(hix hixVar) {
        if (this.f3732a.r()) {
            aa(hixVar);
        }
    }

    private void t(hix hixVar) {
        if (this.f3732a.al()) {
            aa(hixVar);
        }
    }

    private void v(hix hixVar) {
        if (this.f3732a.ar()) {
            aa(hixVar);
            return;
        }
        int e = ffw.e((StepRateData) Collections.max(this.w, new Comparator() { // from class: hhj
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return HeartRateFrag.d((StepRateData) obj, (StepRateData) obj2);
            }
        }));
        hixVar.c(this.f3732a.e().requestAvgStepRate());
        hixVar.e(e);
    }

    public static /* synthetic */ int d(StepRateData stepRateData, StepRateData stepRateData2) {
        return ffw.e(stepRateData) - ffw.e(stepRateData2);
    }

    private void b(hix hixVar) {
        int c;
        if (this.f3732a.u()) {
            aa(hixVar);
            return;
        }
        if (this.u == 273 && this.f3732a.e().getExtendDataInt("crossTrainerCadence") > 0) {
            c = this.f3732a.e().getExtendDataInt("crossTrainerCadence");
        } else {
            c = hji.c(this.f3732a.e(), this.f3732a.d());
        }
        List<ffn> requestRidePostureDataList = this.f3732a.d().requestRidePostureDataList();
        int e = !requestRidePostureDataList.isEmpty() ? ((ffn) Collections.max(requestRidePostureDataList, new Comparator() { // from class: hhh
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return HeartRateFrag.d((ffn) obj, (ffn) obj2);
            }
        })).e() : 0;
        hixVar.c(c);
        hixVar.e(e);
    }

    public static /* synthetic */ int d(ffn ffnVar, ffn ffnVar2) {
        return ffnVar.e() - ffnVar2.e();
    }

    private void k(hix hixVar) {
        if (this.f3732a.aa()) {
            aa(hixVar);
            return;
        }
        List<knw> requestPaddleFrequencyList = this.f3732a.d().requestPaddleFrequencyList();
        float b2 = !requestPaddleFrequencyList.isEmpty() ? ((knw) Collections.max(requestPaddleFrequencyList, new Comparator() { // from class: hhf
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return HeartRateFrag.d((knw) obj, (knw) obj2);
            }
        })).b() : 0.0f;
        hixVar.c(hji.c(this.f3732a.e()));
        hixVar.e(b2);
    }

    public static /* synthetic */ int d(knw knwVar, knw knwVar2) {
        if (knwVar.b() - knwVar2.b() < 0.0f) {
            return -1;
        }
        return knwVar.b() - knwVar2.b() == 0.0f ? 0 : 1;
    }

    private void l(hix hixVar) {
        if (this.f3732a.ai()) {
            aa(hixVar);
            return;
        }
        List<koc> requestPowerList = this.f3732a.d().requestPowerList();
        int b2 = !requestPowerList.isEmpty() ? ((koc) Collections.max(requestPowerList, new Comparator() { // from class: hhb
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return HeartRateFrag.a((koc) obj, (koc) obj2);
            }
        })).b() : 0;
        hixVar.c(hji.e(r0.requestPowerList()));
        hixVar.e(b2);
    }

    public static /* synthetic */ int a(koc kocVar, koc kocVar2) {
        return kocVar.b() - kocVar2.b();
    }

    private void n(hix hixVar) {
        if (this.f3732a.z()) {
            z(hixVar);
            return;
        }
        int b2 = ffw.b((List<HeartRateData>) this.m);
        hixVar.c(this.f3732a.e().requestAvgHeartRate());
        hixVar.e(b2);
        HandlerExecutor.e(new Runnable() { // from class: hhd
            @Override // java.lang.Runnable
            public final void run() {
                HeartRateFrag.this.j();
            }
        });
    }

    public /* synthetic */ void j() {
        if (this.f3732a != null) {
            c(this.m, this.f3732a.e());
        }
    }

    private void y(hix hixVar) {
        if (this.f3732a.as()) {
            aa(hixVar);
        }
    }

    private void r(hix hixVar) {
        if (this.f3732a.ah() || hji.g(this.f3732a.e().requestSportType())) {
            aa(hixVar);
        }
    }

    private void s(hix hixVar) {
        if (this.f3732a.af()) {
            aa(hixVar);
        }
    }

    private void aa(final hix hixVar) {
        LogUtil.a("Track_HeartRateFrag", "gone Chart");
        HandlerExecutor.e(new Runnable() { // from class: hhe
            @Override // java.lang.Runnable
            public final void run() {
                HeartRateFrag.e(hix.this);
            }
        });
    }

    public static /* synthetic */ void e(hix hixVar) {
        if (hixVar.bgB_() != null) {
            hixVar.bgB_().setVisibility(8);
        }
        if (hixVar.g() != null) {
            hixVar.g().setVisibility(8);
        }
    }

    private void z(final hix hixVar) {
        HandlerExecutor.e(new Runnable() { // from class: hhg
            @Override // java.lang.Runnable
            public final void run() {
                HeartRateFrag.this.d(hixVar);
            }
        });
    }

    public /* synthetic */ void d(hix hixVar) {
        LogUtil.a("Track_HeartRateFrag", "mHeartRateList is empty null==========");
        this.i.setVisibility(8);
        hixVar.bgB_().setVisibility(8);
        this.v.setVisibility(8);
        hixVar.g().setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        if (!isAdded()) {
            LogUtil.b("Track_HeartRateFrag", "fragment is not added to activity");
            return;
        }
        this.p.setVisibility(8);
        if (z() && this.ad == 0 && this.ah == 0) {
            this.f.setVisibility(8);
        } else {
            ArrayList arrayList = new ArrayList(10);
            arrayList.add(Integer.valueOf(this.ac));
            arrayList.add(Integer.valueOf(this.ab));
            arrayList.add(Integer.valueOf(this.aa));
            arrayList.add(Integer.valueOf(this.ad));
            arrayList.add(Integer.valueOf(this.ah));
            HealthRingChart healthRingChart = this.o;
            if (healthRingChart != null) {
                healthRingChart.d(arrayList);
            }
        }
        int i = this.u;
        if (i == 262 || i == 266) {
            this.v.setVisibility(0);
        }
        n();
        q();
        ac();
        af();
    }

    private boolean z() {
        return this.ac == 0 && this.ab == 0 && this.aa == 0;
    }

    private void n() {
        for (Map.Entry<SportDetailChartDataType, hix> entry : this.j.entrySet()) {
            c(entry.getKey(), entry.getValue());
        }
    }

    private void c(SportDetailChartDataType sportDetailChartDataType, hix hixVar) {
        switch (sportDetailChartDataType) {
            case HANG_TIME:
                this.ae.addRunningPostureDataLayer(hixVar.e(), 16, this.f3732a.e().requestAverageHangTime());
                break;
            case GROUND_CONTACT_TIME:
                this.ae.addRunningPostureDataLayer(hixVar.e(), 7, this.f3732a.e().requestAvgGroundContactTime());
                break;
            case HEART_RATE:
                this.ae.addHeartRateDataLayer(hixVar.e());
                break;
            case STEP_RATE:
                this.ae.addStepRateDataLayer(hixVar.e());
                break;
            case SPEED_RATE:
                this.ae.addTrackSpeedDataLayer(hixVar.e());
                break;
            case ALTITUDE:
                this.ae.addTrackAltitudeDataLayer(hixVar.e());
                break;
            case CADENCE:
                this.ae.addCadenceRateDataLayer(hixVar.e());
                break;
            case PADDLE_FREQUENCY:
                this.ae.addPaddleFreqDataLayer(hixVar.e());
                break;
            case REALTIME_PACE:
                this.ae.addTrackRealTimePaceDataLayer(hixVar.e());
                break;
            case PULL_FREQ:
                this.ae.addTrackPullFreqDataLayer(hixVar.e());
                break;
            case SWOLF:
                this.ae.addTrackSwolfDataLayer(hixVar.e());
                break;
            case SPO2:
                this.ae.addSpo2DataLayer(hixVar.e());
                break;
            default:
                d(sportDetailChartDataType, hixVar);
                break;
        }
    }

    private void d(SportDetailChartDataType sportDetailChartDataType, hix hixVar) {
        int i = AnonymousClass4.c[sportDetailChartDataType.ordinal()];
        if (i == 1) {
            this.ae.addRunningPostureDataLayer(hixVar.e(), 17, this.f3732a.e().requestGroundHangTimeRate());
        }
        switch (i) {
            case 14:
                this.ae.addRunningPostureDataLayer(hixVar.e(), 8);
                break;
            case 15:
                this.ae.addRunningPostureDataLayer(hixVar.e(), 19, 50.0f);
                break;
            case 16:
                this.ae.addRunningPostureDataLayer(hixVar.e(), 18, this.f3732a.e().getExtendDataFloat("avg_v_osc"));
                break;
            case 17:
                this.ae.addRunningPostureDataLayer(hixVar.e(), 20, this.f3732a.e().getExtendDataFloat("avg_v_s_r"));
                break;
            case 18:
                this.ae.addJumpHeightDataLayer(hixVar.e());
                break;
            case 19:
                this.ae.addJumpTimeDataLayer(hixVar.e());
                break;
            case 20:
                this.ae.addPowerDataLayer(hixVar.e());
                break;
            case 21:
                this.ae.addRecoverHeartRateLayer(hixVar.e());
                break;
            case 22:
                this.ae.addRunningPostureDataLayer(hixVar.e(), 21, this.f3732a.e().getExtendDataFloat("avg_i_p"));
                break;
            case 23:
                this.ae.addWeightDataLayer(hixVar.e());
                break;
        }
    }

    private void ac() {
        Iterator<Map.Entry<SportDetailChartDataType, hix>> it = this.j.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().e().refresh();
        }
    }

    private void q() {
        for (Map.Entry<SportDetailChartDataType, hix> entry : this.j.entrySet()) {
            hix value = entry.getValue();
            SportDetailChartDataType key = entry.getKey();
            value.e().setTouchEnabled(false);
            value.e().setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
            hji.e(key, value, this.c, this.f3732a);
        }
    }

    private void af() {
        for (Map.Entry<SportDetailChartDataType, hix> entry : this.j.entrySet()) {
            SportDetailChartDataType key = entry.getKey();
            hix value = entry.getValue();
            a(key, value, value.a());
        }
    }

    private void a(SportDetailChartDataType sportDetailChartDataType, hix hixVar, TrackChartViewHolder trackChartViewHolder) {
        switch (AnonymousClass4.c[sportDetailChartDataType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                trackChartViewHolder.c(hixVar.h());
                trackChartViewHolder.e(hixVar.f());
                break;
            case 6:
                ac(hixVar);
                break;
            case 7:
                ad(hixVar);
                break;
            case 10:
                b(trackChartViewHolder);
                break;
            case 11:
                ab(hixVar);
                break;
            case 12:
                ai(hixVar);
                break;
            case 13:
                trackChartViewHolder.c(this.f3732a.e().requestMinSpo2());
                trackChartViewHolder.e(this.f3732a.e().requestMaxSpo2());
                break;
            default:
                d(sportDetailChartDataType, hixVar, trackChartViewHolder);
                break;
        }
    }

    private void d(SportDetailChartDataType sportDetailChartDataType, hix hixVar, TrackChartViewHolder trackChartViewHolder) {
        if (AnonymousClass4.c[sportDetailChartDataType.ordinal()] != 23) {
            return;
        }
        trackChartViewHolder.c(hixVar.h());
    }

    private float s() {
        if (!UnitUtil.h()) {
            return this.f3732a.e().getExtendDataFloat("avg_v_osc");
        }
        return (float) UnitUtil.e(this.f3732a.e().getExtendDataFloat("avg_v_osc"), 0);
    }

    private void ac(hix hixVar) {
        TrackChartViewHolder a2 = hixVar.a();
        float b2 = (float) hji.b(this.f3732a.e().requestAvgPace());
        float c = (float) ffw.c(this.f3732a.d().requestRealTimeSpeedList());
        hixVar.c(b2);
        hixVar.e(c);
        a2.c(hixVar.h());
        a2.e(hixVar.f());
    }

    private void ad(hix hixVar) {
        float requestMinAltitude = this.f3732a.e().requestMinAltitude();
        float requestMaxAltitude = this.f3732a.e().requestMaxAltitude();
        if (!hji.c(requestMinAltitude, requestMaxAltitude)) {
            Float[] d2 = hji.d(this.f3732a.d().requestAltitudeList());
            requestMaxAltitude = d2[0].floatValue();
            requestMinAltitude = d2[1].floatValue();
        }
        hixVar.c(requestMinAltitude);
        hixVar.e(requestMaxAltitude);
        hixVar.a().c(hixVar.h());
        hixVar.a().e(hixVar.f());
    }

    private void b(TrackChartViewHolder trackChartViewHolder) {
        double d2;
        MotionPathSimplify e = this.f3732a.e();
        if (this.f3732a.ah()) {
            return;
        }
        float requestAvgPace = e.requestAvgPace();
        float a2 = ffw.a(this.f3732a.d().requestRealTimePaceList());
        int i = this.u;
        if (i == 266 || i == 262) {
            requestAvgPace /= 10.0f;
            a2 /= 10.0f;
            if (UnitUtil.h()) {
                requestAvgPace = (float) UnitUtil.d(requestAvgPace, 2);
                d2 = UnitUtil.d(a2, 2);
                a2 = (float) d2;
            }
            trackChartViewHolder.c(requestAvgPace);
            trackChartViewHolder.e(a2);
        }
        if (i == 274) {
            a2 /= 2.0f;
            if (UnitUtil.h()) {
                requestAvgPace = (float) UnitUtil.d(requestAvgPace / 5.0f, 2);
                d2 = UnitUtil.d(a2 / 5.0f, 2);
                a2 = (float) d2;
            }
        } else if (hji.j(i)) {
            requestAvgPace /= 2.0f;
            a2 /= 2.0f;
        } else if (UnitUtil.h()) {
            float d3 = (float) UnitUtil.d(requestAvgPace, 3);
            a2 = (float) UnitUtil.d(a2, 3);
            requestAvgPace = d3;
        }
        trackChartViewHolder.c(requestAvgPace);
        trackChartViewHolder.e(a2);
    }

    private void ab(hix hixVar) {
        TrackChartViewHolder a2 = hixVar.a();
        MotionPathSimplify e = this.f3732a.e();
        int i = this.u;
        if (i == 266 || i == 262) {
            Map<String, Integer> requestSportData = e.requestSportData();
            if (requestSportData == null) {
                hixVar.bgB_().setVisibility(8);
                hixVar.g().setVisibility(8);
            } else {
                if (this.f3732a.af()) {
                    return;
                }
                if (requestSportData.get("swim_pull_freq") != null) {
                    a2.c(requestSportData.get("swim_pull_freq").intValue());
                } else {
                    a2.c(0.0f);
                }
                a2.e(ffw.j(this.q));
            }
        }
    }

    private void ai(hix hixVar) {
        TrackChartViewHolder a2 = hixVar.a();
        MotionPathSimplify e = this.f3732a.e();
        int i = this.u;
        if (i == 266 || i == 262) {
            Map<String, Integer> requestSportData = e.requestSportData();
            if (requestSportData == null) {
                hixVar.bgB_().setVisibility(8);
            } else {
                if (this.f3732a.as()) {
                    return;
                }
                if (requestSportData.get(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF) != null) {
                    a2.c(requestSportData.get(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF).intValue());
                } else {
                    a2.c(0.0f);
                }
                a2.e(ffw.g(this.x));
            }
        }
    }

    public void c(final ArrayList<HeartRateData> arrayList, final MotionPathSimplify motionPathSimplify) {
        if (arrayList == null || motionPathSimplify == null) {
            LogUtil.b("Track_HeartRateFrag", "arrayngeRingData() mHeartRateList is null!");
            return;
        }
        ffw.d();
        this.u = motionPathSimplify.requestSportType();
        jdx.b(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.HeartRateFrag.5
            @Override // java.lang.Runnable
            public void run() {
                int extendDataInt = motionPathSimplify.getExtendDataInt("sportHeartPosture", -1);
                LogUtil.a("Track_HeartRateFrag", "arrangeRingData posture = ", Integer.valueOf(extendDataInt));
                if (extendDataInt == -1) {
                    extendDataInt = 1;
                }
                String extendDataString = motionPathSimplify.getExtendDataString("isTrustHeartRate");
                UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
                int requestHeartRateZoneType = motionPathSimplify.requestHeartRateZoneType();
                LogUtil.a("Track_HeartRateFrag", "arrangeRingData devices classifyMethod= ", Integer.valueOf(requestHeartRateZoneType));
                int[] c = ffw.c(arrayList, requestHeartRateZoneType, motionPathSimplify.requestTotalTime(), extendDataString, userProfileMgrApi.getLocalUserInfo(), extendDataInt);
                LogUtil.a("Track_HeartRateFrag", "timeArea = ", Arrays.toString(c));
                Message obtainMessage = HeartRateFrag.this.g.obtainMessage();
                obtainMessage.what = 106;
                obtainMessage.obj = c;
                HeartZoneConf e = ffw.e(extendDataInt, requestHeartRateZoneType, userProfileMgrApi.getLocalUserInfo());
                LogUtil.a("Track_HeartRateFrag", "arrangeRingData ClassifyMethod= ", Integer.valueOf(e.getClassifyMethod()));
                obtainMessage.arg1 = e.getClassifyMethod();
                Map<String, String> requestExtendDataMap = motionPathSimplify.requestExtendDataMap();
                String str = requestExtendDataMap.containsKey(HwExerciseConstants.JSON_NAME_RECORD_FLAG) ? requestExtendDataMap.get(HwExerciseConstants.JSON_NAME_RECORD_FLAG) : null;
                LogUtil.c("Track_HeartRateFrag", "arrangeRingData() recordFlag = ", str);
                Bundle bundle = new Bundle();
                bundle.putString(HwExerciseConstants.JSON_NAME_RECORD_FLAG, str);
                obtainMessage.setData(bundle);
                HeartRateFrag.this.g.sendMessage(obtainMessage);
            }
        });
    }

    public void bev_(View view, Context context) {
        this.c = context;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layout_heart_rate_chart_show);
        this.f = linearLayout;
        this.o = (HealthRingChart) linearLayout.findViewById(R.id.ring_view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int[] iArr, int i, String str) {
        ArrayList arrayList = new ArrayList(10);
        if (i == 3) {
            arrayList.add(this.c.getString(R.string._2130845602_res_0x7f021fa2));
            arrayList.add(this.c.getString(R.string._2130842685_res_0x7f02143d));
            arrayList.add(this.c.getString(R.string._2130842686_res_0x7f02143e));
            arrayList.add(this.c.getString(R.string._2130842687_res_0x7f02143f));
            arrayList.add(this.c.getString(R.string._2130845603_res_0x7f021fa3));
        } else if (i == 1) {
            arrayList.add(this.c.getString(R.string._2130842683_res_0x7f02143b));
            arrayList.add(this.c.getString(R.string._2130842684_res_0x7f02143c));
            arrayList.add(this.c.getString(R.string._2130842685_res_0x7f02143d));
            arrayList.add(this.c.getString(R.string._2130842686_res_0x7f02143e));
            arrayList.add(this.c.getString(R.string._2130842687_res_0x7f02143f));
        } else {
            arrayList.add(this.c.getString(R.string._2130841807_res_0x7f0210cf));
            arrayList.add(this.c.getString(R.string._2130841808_res_0x7f0210d0));
            arrayList.add(this.c.getString(R.string._2130841809_res_0x7f0210d1));
            arrayList.add(this.c.getString(R.string._2130841894_res_0x7f021126));
            arrayList.add(this.c.getString(R.string._2130841810_res_0x7f0210d2));
        }
        this.ah = iArr[0];
        this.ad = iArr[1];
        this.aa = iArr[2];
        this.ab = iArr[3];
        this.ac = iArr[4];
        List<nkz> b2 = b(arrayList);
        if (this.ah + this.ad + this.aa + this.ab + this.ac == 0.0f) {
            return;
        }
        HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.c, new nld().c(false).b(true), b2);
        healthRingChartAdapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: hhk
            @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
            public final String format(nkz nkzVar) {
                return HeartRateFrag.this.b(nkzVar);
            }
        });
        this.o.setAdapter(healthRingChartAdapter);
        if (LanguageUtil.j(this.c)) {
            this.o.setDesc(this.c.getResources().getString(R.string._2130841430_res_0x7f020f56), this.c.getResources().getString(R.string._2130843120_res_0x7f0215f0));
        }
        if (z() && this.ad == 0 && this.ah == 0) {
            this.f.setVisibility(8);
        } else {
            this.f.setVisibility(0);
        }
    }

    public /* synthetic */ String b(nkz nkzVar) {
        return a((int) nkzVar.i(), this.c);
    }

    private List<nkz> b(List<String> list) {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(Integer.valueOf(this.ac));
        arrayList.add(Integer.valueOf(this.ab));
        arrayList.add(Integer.valueOf(this.aa));
        arrayList.add(Integer.valueOf(this.ad));
        arrayList.add(Integer.valueOf(this.ah));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.c, R.color._2131298929_res_0x7f090a71)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.c, R.color._2131298927_res_0x7f090a6f)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.c, R.color._2131298925_res_0x7f090a6d)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.c, R.color._2131298931_res_0x7f090a73)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.c, R.color._2131298933_res_0x7f090a75)));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.c, R.color._2131298928_res_0x7f090a70)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.c, R.color._2131298926_res_0x7f090a6e)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.c, R.color._2131298924_res_0x7f090a6c)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.c, R.color._2131298930_res_0x7f090a72)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.c, R.color._2131298932_res_0x7f090a74)));
        int size = list.size();
        ArrayList arrayList4 = new ArrayList(size);
        int i = 0;
        while (i < size) {
            arrayList4.add(new nkz(i < list.size() ? list.get(i) : "", ((Integer) arrayList.get(i)).intValue(), i < arrayList2.size() ? ((Integer) arrayList2.get(i)).intValue() : 0, i < arrayList3.size() ? ((Integer) arrayList3.get(i)).intValue() : 0));
            i++;
        }
        return arrayList4;
    }

    public static String a(int i, Context context) {
        if (context == null) {
            return null;
        }
        if (i > 0 && i < 60) {
            return context.getString(R.string._2130839496_res_0x7f0207c8, 1);
        }
        return nsn.e(i / 60, context);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ExecutorService executorService = this.s;
        if (executorService != null) {
            executorService.shutdown();
        }
        b bVar = this.g;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
        }
        ffw.d();
        l();
        Map<SportDetailChartDataType, hix> map = this.j;
        if (map != null) {
            map.clear();
        }
        this.f3732a = null;
    }

    private static void l() {
        d = null;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportResultBaseFragment
    protected String d() {
        return "Track_HeartRateFrag";
    }
}
