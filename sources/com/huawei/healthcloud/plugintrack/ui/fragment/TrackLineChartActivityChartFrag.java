package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.healthcloud.plugintrack.ui.fragment.TrackLineChartActivityChartFrag;
import com.huawei.healthcloud.plugintrack.ui.view.CustomTrackChartTitleBar;
import com.huawei.healthcloud.plugintrack.ui.view.linechart.HorizontalMarkerView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.bubblelayout.HealthBubbleLayout;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.view.trackview.TrackLineChartHolder;
import defpackage.hji;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nnd;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class TrackLineChartActivityChartFrag extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private SportDetailChartDataType f3742a;
    private HealthBubbleLayout b;
    private Context e;
    private boolean j;
    private CustomTrackChartTitleBar i = null;
    private HwHealthBaseCombinedChart d = null;
    private TrackLineChartHolder m = null;
    private View o = null;
    private int f = 0;
    private int c = -1;
    private int n = 0;
    private HorizontalMarkerView g = null;
    private Map<SportDetailChartDataType, HwHealthBaseBarLineDataSet> h = new HashMap(16);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.b("Track_TrackLineChartActivityChartFrag", "inflater is null");
            return null;
        }
        this.e = getContext();
        if (nsn.ag(BaseApplication.getContext())) {
            this.o = layoutInflater.inflate(R.layout.track_linechart_frag_bigcd, (ViewGroup) null);
        } else {
            this.o = layoutInflater.inflate(R.layout.track_linechart_frag, (ViewGroup) null);
        }
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        this.m = trackLineChartHolder;
        if (trackLineChartHolder == null) {
            LogUtil.h("Track_TrackLineChartActivityChartFrag", "mTrackLineChartHolder null,destroy!!! kill by mine");
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.MainActivity");
            intent.setFlags(268468224);
            startActivity(intent);
            requireActivity().finish();
            return null;
        }
        h();
        this.d = (HwHealthBaseCombinedChart) this.o.findViewById(R.id.track_new_heartview);
        j();
        Intent intent2 = getActivity().getIntent();
        if (intent2 == null) {
            LogUtil.h("Track_TrackLineChartActivityChartFrag", "intent null,return");
            return this.o;
        }
        Bundle extras = intent2.getExtras();
        if (extras == null) {
            LogUtil.h("Track_TrackLineChartActivityChartFrag", "bundle isEmpty,return");
            return this.o;
        }
        String str = (String) extras.getCharSequence("KEY_BASELINE");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_TrackLineChartActivityChartFrag", "baseLine isEmpty,return");
            return this.o;
        }
        e(str);
        bfJ_(extras);
        g();
        return this.o;
    }

    private void bfJ_(Bundle bundle) {
        f();
        List<SportDetailChartDataType> bfI_ = bfI_(bundle);
        if (this.m.isExistBoltData()) {
            this.i.setShowMode(1);
        }
        this.i.setIconClickable(bfI_);
        this.n = bfI_.size();
        this.f = 1;
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackLineChartActivityChartFrag.1
            @Override // java.lang.Runnable
            public void run() {
                TrackLineChartActivityChartFrag.this.c();
            }
        });
    }

    private void f() {
        this.i.setOnLineStatusChangedListener(new CustomTrackChartTitleBar.OnLineStatusChangedListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackLineChartActivityChartFrag.2
            @Override // com.huawei.healthcloud.plugintrack.ui.view.CustomTrackChartTitleBar.OnLineStatusChangedListener
            public void onLineStatusChanged(SportDetailChartDataType sportDetailChartDataType, boolean z) {
                if (sportDetailChartDataType == null) {
                    LogUtil.b("Track_TrackLineChartActivityChartFrag", "chartLayerType is null");
                    return;
                }
                LogUtil.a("Track_TrackLineChartActivityChartFrag", "onLineStatusChanged refresh");
                TrackLineChartActivityChartFrag.this.e(sportDetailChartDataType, z);
                HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet = (HwHealthBaseBarLineDataSet) TrackLineChartActivityChartFrag.this.h.get(sportDetailChartDataType);
                if (hwHealthBaseBarLineDataSet != null && !z) {
                    TrackLineChartActivityChartFrag.this.m.removeDataSet(TrackLineChartActivityChartFrag.this.d, hwHealthBaseBarLineDataSet);
                    TrackLineChartActivityChartFrag.this.h.put(sportDetailChartDataType, null);
                    TrackLineChartActivityChartFrag.this.d(false);
                } else if (hwHealthBaseBarLineDataSet == null && z) {
                    HwHealthBaseBarLineDataSet a2 = TrackLineChartActivityChartFrag.this.a(sportDetailChartDataType);
                    TrackLineChartActivityChartFrag.this.d(true);
                    TrackLineChartActivityChartFrag.this.h.put(sportDetailChartDataType, a2);
                } else {
                    LogUtil.c("Track_TrackLineChartActivityChartFrag", "dataSet is null and is Selected is false");
                }
                TrackLineChartActivityChartFrag.this.d.refresh();
                TrackLineChartActivityChartFrag.this.b();
                TrackLineChartActivityChartFrag.this.a();
            }
        });
        this.i.setOnBackListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackLineChartActivityChartFrag.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TrackLineChartActivityChartFrag.this.getActivity().finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void g() {
        boolean z = this.e.getSharedPreferences("retrack_file", 0).getBoolean("is_first_time_use_add_comparison_data", true);
        this.j = z;
        if (z && this.n > 1) {
            this.b.setVisibility(0);
            this.b.setOnClickListener(new View.OnClickListener() { // from class: hiv
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TrackLineChartActivityChartFrag.this.bfK_(view);
                }
            });
        } else {
            this.b.setVisibility(8);
        }
    }

    public /* synthetic */ void bfK_(View view) {
        a();
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a() {
        HealthBubbleLayout healthBubbleLayout;
        if (this.e == null || (healthBubbleLayout = this.b) == null || healthBubbleLayout.getVisibility() == 8) {
            return;
        }
        this.b.setVisibility(8);
        SharedPreferences.Editor edit = this.e.getSharedPreferences("retrack_file", 0).edit();
        edit.putBoolean("is_first_time_use_add_comparison_data", false);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet;
        SportDetailChartDataType d = d();
        if (d == null || (hwHealthBaseBarLineDataSet = this.h.get(d)) == null) {
            return;
        }
        hwHealthBaseBarLineDataSet.setColor(d(hwHealthBaseBarLineDataSet.getColor(), z));
    }

    private SportDetailChartDataType d() {
        int i = 0;
        SportDetailChartDataType sportDetailChartDataType = null;
        for (Map.Entry<SportDetailChartDataType, HwHealthBaseBarLineDataSet> entry : this.h.entrySet()) {
            if (entry.getValue() != null) {
                sportDetailChartDataType = entry.getKey();
                i++;
            }
        }
        if (i == 1) {
            return sportDetailChartDataType;
        }
        return null;
    }

    private int d(int i, boolean z) {
        return Color.argb(z ? 127 : 255, (16711680 & i) >> 16, (65280 & i) >> 8, i & 255);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        HwHealthBaseCombinedChart hwHealthBaseCombinedChart;
        if (this.i == null || (hwHealthBaseCombinedChart = this.d) == null) {
            return;
        }
        List<T> dataSets = ((nnd) hwHealthBaseCombinedChart.getData()).getDataSets();
        if (koq.b(dataSets)) {
            LogUtil.h("Track_TrackLineChartActivityChartFrag", "setTitle not find data,lineDataSets size zero,return");
            return;
        }
        ArrayList arrayList = new ArrayList(dataSets.size());
        for (T t : dataSets) {
            if (t instanceof HwHealthLineDataSet) {
                arrayList.add(((HwHealthLineDataSet) t).c().d());
            } else if (t instanceof HwHealthBarDataSet) {
                arrayList.add(((HwHealthBarDataSet) t).d().d());
            } else {
                LogUtil.b("Track_TrackLineChartActivityChartFrag", "dataSet type is error. pls check");
            }
        }
        int size = arrayList.size();
        String str = "";
        if (size == 1) {
            str = "" + ((String) arrayList.get(0));
        } else if (size == 2) {
            str = "" + String.format(BaseApplication.getContext().getResources().getString(R.string._2130840043_res_0x7f0209eb), arrayList.get(0), arrayList.get(1));
        } else if (size == 3) {
            str = "" + String.format(BaseApplication.getContext().getResources().getString(R.string._2130840044_res_0x7f0209ec), arrayList.get(0), arrayList.get(1), arrayList.get(2));
        } else {
            LogUtil.h("Track_TrackLineChartActivityChartFrag", "lineDataSets.size not support:", Integer.valueOf(arrayList.size()));
        }
        this.i.setTitle(str);
    }

    private void h() {
        this.i = (CustomTrackChartTitleBar) this.o.findViewById(R.id.linechart_titlebar);
        this.g = new HorizontalMarkerView(BaseApplication.getContext());
        this.b = (HealthBubbleLayout) this.o.findViewById(R.id.track_click_add_comparison_data_layout);
        LinearLayout linearLayout = (LinearLayout) this.o.findViewById(R.id.markerview);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 16;
        linearLayout.addView(this.g, layoutParams);
    }

    private List<SportDetailChartDataType> bfI_(Bundle bundle) {
        int i;
        ArrayList arrayList = new ArrayList(16);
        if (this.m.isExistHeartRateData()) {
            arrayList.add(SportDetailChartDataType.HEART_RATE);
        }
        if (this.m.isExistStepRateData()) {
            arrayList.add(SportDetailChartDataType.STEP_RATE);
        }
        if (this.m.isExistCadenceRateData()) {
            arrayList.add(SportDetailChartDataType.CADENCE);
        }
        if (this.m.isExistPaddleFreqData()) {
            arrayList.add(SportDetailChartDataType.PADDLE_FREQUENCY);
        }
        if (this.m.isExistAltitudeData()) {
            arrayList.add(SportDetailChartDataType.ALTITUDE);
        }
        if (bundle != null) {
            i = bundle.getInt(BleConstants.SPORT_TYPE);
        } else {
            LogUtil.h("Track_TrackLineChartActivityChartFrag", "constructExistLine(), bundle == null");
            i = 0;
        }
        if (this.m.isExistSpeedData() && hji.g(i)) {
            arrayList.add(SportDetailChartDataType.SPEED_RATE);
        }
        a(arrayList, i);
        return arrayList;
    }

    private void a(List<SportDetailChartDataType> list, int i) {
        if (this.m.isExistSwolfData()) {
            list.add(SportDetailChartDataType.SWOLF);
        }
        if (this.m.isExistPullFreqData()) {
            list.add(SportDetailChartDataType.PULL_FREQ);
        }
        if (this.m.isExistRealTimePaceData() && !hji.g(i)) {
            list.add(SportDetailChartDataType.REALTIME_PACE);
        }
        if (this.m.isExistRunningPostureData()) {
            if (this.m.isContractTimeValid()) {
                list.add(SportDetailChartDataType.GROUND_CONTACT_TIME);
            }
            c(list);
            if (this.m.isGroundImpactAccelerationValid()) {
                list.add(SportDetailChartDataType.GROUND_IMPACT_ACCELERATION);
            }
        }
        if (this.m.isExistSpo2Data()) {
            list.add(SportDetailChartDataType.SPO2);
        }
        if (this.m.isExistPowerData()) {
            list.add(SportDetailChartDataType.POWER);
        }
        if (this.m.isExistSkippingSpeedData()) {
            list.add(SportDetailChartDataType.SKIPPING_SPEED);
        }
        if (this.m.isExistWeightFreqData()) {
            list.add(SportDetailChartDataType.PEAK_WEIGHT);
        }
    }

    private void c(List<SportDetailChartDataType> list) {
        if (this.m.isHangTimeValid()) {
            list.add(SportDetailChartDataType.HANG_TIME);
        }
        if (this.m.isImpactHangRateValid()) {
            list.add(SportDetailChartDataType.GROUND_HANG_TIME_RATE);
        }
        if (this.m.isVerticalOscillationValid()) {
            list.add(SportDetailChartDataType.VERTICAL_OSCILLATION);
        }
        if (this.m.isVerticalRatioValid()) {
            list.add(SportDetailChartDataType.VERTICAL_RATIO);
        }
        if (this.m.isActivePeakValid()) {
            list.add(SportDetailChartDataType.ACTIVE_PEAK);
        }
        if (this.m.isGcBalanceValid()) {
            list.add(SportDetailChartDataType.GC_TIME_BALANCE);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void e(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1993965677:
                if (str.equals("BASELINE_HEART_RATE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1294142870:
                if (str.equals("BASELINE_GROUND_CONTACT_TIME")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1007500616:
                if (str.equals("BASELINE_PULL_FREQ")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -947843413:
                if (str.equals("BASELINE_SWOLF")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -737439296:
                if (str.equals("BASELINE_STEP_FRE")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -359440335:
                if (str.equals("BASELINE_CADENCE_FRE")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -130690212:
                if (str.equals("BASELINE_ALTITUDE")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1633261:
                if (str.equals("BASELINE_REALTIME_PACE")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 509438174:
                if (str.equals("BASELINE_PADDLE_FRE")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1002784818:
                if (str.equals("BASELINE_SPEED_RATE")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.f3742a = SportDetailChartDataType.HEART_RATE;
                this.c = 0;
                break;
            case 1:
                this.f3742a = SportDetailChartDataType.GROUND_CONTACT_TIME;
                this.c = 7;
                break;
            case 2:
                this.f3742a = SportDetailChartDataType.PULL_FREQ;
                this.c = 5;
                break;
            case 3:
                this.f3742a = SportDetailChartDataType.SWOLF;
                this.c = 4;
                break;
            case 4:
                this.f3742a = SportDetailChartDataType.STEP_RATE;
                this.c = 1;
                break;
            case 5:
                this.f3742a = SportDetailChartDataType.CADENCE;
                this.c = 12;
                break;
            case 6:
                this.f3742a = SportDetailChartDataType.ALTITUDE;
                this.c = 2;
                break;
            case 7:
                this.f3742a = SportDetailChartDataType.REALTIME_PACE;
                this.c = 6;
                break;
            case '\b':
                this.f3742a = SportDetailChartDataType.PADDLE_FREQUENCY;
                this.c = 13;
                break;
            case '\t':
                this.f3742a = SportDetailChartDataType.SPEED_RATE;
                this.c = 3;
                break;
            default:
                c(str);
                break;
        }
        this.i.setBaseLine(this.f3742a);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void c(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -861866342:
                if (str.equals("BASELINE_SPO2")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -630497476:
                if (str.equals("BASELINE_VERTICAL_OSCILLATION")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 532293211:
                if (str.equals("BASELINE_GROUND_IMPACT_ACCELERATION")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 759592915:
                if (str.equals("BASELINE_GC_TIME_BALANCE")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 959158142:
                if (str.equals("BASELINE_ACTIVE_PEAK")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1334101788:
                if (str.equals("BASELINE_VERTICAL_RATIO")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1703035136:
                if (str.equals("BASELINE_HANG_TIME")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1876991683:
                if (str.equals("BASELINE_GROUND_HANG_TIME_RATE")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.f3742a = SportDetailChartDataType.SPO2;
                this.c = 9;
                break;
            case 1:
                this.f3742a = SportDetailChartDataType.VERTICAL_OSCILLATION;
                this.c = 18;
                break;
            case 2:
                this.f3742a = SportDetailChartDataType.GROUND_IMPACT_ACCELERATION;
                this.c = 8;
                break;
            case 3:
                this.f3742a = SportDetailChartDataType.GC_TIME_BALANCE;
                this.c = 19;
                break;
            case 4:
                this.f3742a = SportDetailChartDataType.ACTIVE_PEAK;
                this.c = 21;
                break;
            case 5:
                this.f3742a = SportDetailChartDataType.VERTICAL_RATIO;
                this.c = 20;
                break;
            case 6:
                this.f3742a = SportDetailChartDataType.HANG_TIME;
                this.c = 16;
                break;
            case 7:
                this.f3742a = SportDetailChartDataType.GROUND_HANG_TIME_RATE;
                this.c = 17;
                break;
            default:
                b(str);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void b(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2045246780:
                if (str.equals("BASELINE_JUMP_TIME")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -950844821:
                if (str.equals("BASELINE_POWER")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 231569171:
                if (str.equals("BASELINE_SKIPPING_SPEED")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 779335090:
                if (str.equals("BASELINE_WEIGHT")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1265507166:
                if (str.equals("BASELINE_JUMP_HEIGHT")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.f3742a = SportDetailChartDataType.JUMP_TIME;
            this.c = 10;
            return;
        }
        if (c == 1) {
            this.f3742a = SportDetailChartDataType.POWER;
            this.c = 14;
            return;
        }
        if (c == 2) {
            this.f3742a = SportDetailChartDataType.SKIPPING_SPEED;
            this.c = 15;
        } else if (c == 3) {
            this.f3742a = SportDetailChartDataType.PEAK_WEIGHT;
            this.c = 18;
        } else if (c == 4) {
            this.f3742a = SportDetailChartDataType.JUMP_HEIGHT;
            this.c = 11;
        } else {
            LogUtil.h("Track_TrackLineChartActivityChartFrag", "baseLine unrecognized!!!");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        LogUtil.b("Track_TrackLineChartActivityChartFrag", "onStart ---- --------------");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.b("Track_TrackLineChartActivityChartFrag", "initData ---- --------------");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackLineChartActivityChartFrag.5
            @Override // java.lang.Runnable
            public void run() {
                TrackLineChartActivityChartFrag.this.i();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        e();
    }

    private void e() {
        HwHealthBaseBarLineDataSet a2 = a(this.f3742a);
        if (a2 == null) {
            return;
        }
        this.h.put(this.f3742a, a2);
        LogUtil.a("Track_TrackLineChartActivityChartFrag", "initBaseLine refresh");
        this.d.refresh();
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HwHealthBaseBarLineDataSet a(SportDetailChartDataType sportDetailChartDataType) {
        switch (AnonymousClass6.e[sportDetailChartDataType.ordinal()]) {
            case 1:
                return this.m.addHeartRateDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case 2:
                return this.m.addStepRateDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case 3:
                return this.m.addCadenceRateDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case 4:
                return this.m.addPaddleFreqDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case 5:
                return this.m.addTrackAltitudeDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case 6:
                return this.m.addTrackSpeedDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case 7:
                return this.m.addTrackSwolfDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case 8:
                return this.m.addTrackPullFreqDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case 9:
                return this.m.addTrackRealTimePaceDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case 10:
                return this.m.addRunningPostureDataLayer(this.d, new TrackLineChartHolder.b().d(true).a(true), 7);
            case 11:
                return this.m.addRunningPostureDataLayer(this.d, new TrackLineChartHolder.b().d(true).a(true), 16);
            default:
                return d(sportDetailChartDataType);
        }
    }

    private HwHealthBaseBarLineDataSet d(SportDetailChartDataType sportDetailChartDataType) {
        switch (sportDetailChartDataType) {
            case GROUND_HANG_TIME_RATE:
                return this.m.addRunningPostureDataLayer(this.d, new TrackLineChartHolder.b().d(true).a(true), 17);
            case GROUND_IMPACT_ACCELERATION:
                return this.m.addRunningPostureDataLayer(this.d, new TrackLineChartHolder.b().d(true), 8);
            case GC_TIME_BALANCE:
                return this.m.addRunningPostureDataLayer(this.d, new TrackLineChartHolder.b().d(true).a(true), 19);
            case VERTICAL_OSCILLATION:
                return this.m.addRunningPostureDataLayer(this.d, new TrackLineChartHolder.b().d(true).a(true), 18);
            case VERTICAL_RATIO:
                return this.m.addRunningPostureDataLayer(this.d, new TrackLineChartHolder.b().d(true).a(true), 20);
            case ACTIVE_PEAK:
                return this.m.addRunningPostureDataLayer(this.d, new TrackLineChartHolder.b().d(true).a(true), 21);
            case SPO2:
                return this.m.addSpo2DataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case JUMP_TIME:
                return this.m.addJumpTimeDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case JUMP_HEIGHT:
                return this.m.addJumpHeightDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            case POWER:
                return this.m.addPowerDataLayer(this.d, new TrackLineChartHolder.b().d(true));
            default:
                return b(sportDetailChartDataType);
        }
    }

    private HwHealthBaseBarLineDataSet b(SportDetailChartDataType sportDetailChartDataType) {
        int i = AnonymousClass6.e[sportDetailChartDataType.ordinal()];
        if (i == 22) {
            return this.m.addSkippingSpeedDataLayer(this.d, new TrackLineChartHolder.b().d(true));
        }
        if (i != 23) {
            return null;
        }
        return this.m.addWeightDataLayer(this.d, new TrackLineChartHolder.b().d(true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(SportDetailChartDataType sportDetailChartDataType, boolean z) {
        HashMap hashMap = new HashMap(16);
        if (z) {
            this.f++;
            hashMap.put("click", 1);
        } else {
            this.f--;
            hashMap.put("click", 0);
        }
        int i = AnonymousClass6.e[sportDetailChartDataType.ordinal()];
        if (i != 21) {
            switch (i) {
                case 1:
                    hashMap.put("clickButton", 0);
                    break;
                case 2:
                    hashMap.put("clickButton", 1);
                    break;
                case 3:
                    hashMap.put("clickButton", 12);
                    break;
                case 4:
                    hashMap.put("clickButton", 13);
                    break;
                case 5:
                    hashMap.put("clickButton", 2);
                    break;
                case 6:
                    hashMap.put("clickButton", 3);
                    break;
                default:
                    hashMap.put("clickButton", -1);
                    break;
            }
        } else {
            hashMap.put("clickButton", 14);
        }
        hashMap.put("base", Integer.valueOf(this.c));
        hashMap.put("totalChart", Integer.valueOf(this.n));
        hashMap.put("layerNums", Integer.valueOf(this.f));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.BI_TRACK_ADD_CHART_LAYER_1040033.value(), hashMap, 0);
    }

    private void j() {
        this.d.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackLineChartActivityChartFrag.3
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
            public void onTextChanged(String str, List<HwHealthMarkerView.a> list) {
                if (str == null || list == null) {
                    throw new AssertionError("LayoutInflater not found.");
                }
                if (list.size() == 1) {
                    TrackLineChartActivityChartFrag.this.g.e(str, list.get(0));
                    return;
                }
                if (list.size() == 2) {
                    TrackLineChartActivityChartFrag.this.g.a(str, list.get(0), list.get(1));
                } else if (list.size() == 3) {
                    TrackLineChartActivityChartFrag.this.g.a(str, list.get(0), list.get(1), list.get(2));
                } else {
                    LogUtil.b("Track_TrackLineChartActivityChartFrag", "data size is more than 3");
                }
            }
        });
    }
}
