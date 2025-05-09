package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.request.transition.Transition;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.huawei.health.R;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.healthcloud.plugintrack.model.JudgeRootBean;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.model.PostureJudgeBean;
import com.huawei.healthcloud.plugintrack.model.ShareInitCallback;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.healthcloud.plugintrack.model.TrackLineChartHolderImpl;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.healthcloud.plugintrack.ui.fragmentutils.TrackDetailItemDrawer;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.healthcloud.plugintrack.ui.view.DetailItemContainer;
import com.huawei.healthcloud.plugintrack.ui.view.PaceIntervalShare;
import com.huawei.healthcloud.plugintrack.ui.view.RunRouteView;
import com.huawei.healthcloud.plugintrack.ui.view.SkippingPerformanceView;
import com.huawei.healthcloud.plugintrack.ui.view.SportDetailItem;
import com.huawei.healthcloud.plugintrack.ui.view.TrackShareDetailCustomTitleLayout;
import com.huawei.healthcloud.plugintrack.ui.view.TrackShareDetailMapAndDetail;
import com.huawei.healthcloud.plugintrack.ui.view.TrackShareViewGroup;
import com.huawei.healthcloud.plugintrack.ui.view.TriathlonShareViewGroup;
import com.huawei.healthcloud.plugintrack.ui.viewholder.CourseActionViewHolder;
import com.huawei.healthcloud.plugintrack.ui.viewholder.RopeSkippingHolder;
import com.huawei.healthcloud.plugintrack.ui.viewholder.RowingMachineHolder;
import com.huawei.healthcloud.plugintrack.ui.viewholder.TrackChartViewHolder;
import com.huawei.healthcloud.plugintrack.util.HotTrackDrawCustomTarget;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.tablewidget.HealthTableWidget;
import com.huawei.ui.commonui.view.trackview.TrackLineChartHolder;
import defpackage.cwa;
import defpackage.enc;
import defpackage.enf;
import defpackage.enj;
import defpackage.enm;
import defpackage.ffs;
import defpackage.ffw;
import defpackage.gvv;
import defpackage.gwe;
import defpackage.gwg;
import defpackage.gxp;
import defpackage.gya;
import defpackage.gze;
import defpackage.hji;
import defpackage.hjs;
import defpackage.hjt;
import defpackage.hjw;
import defpackage.hkb;
import defpackage.hln;
import defpackage.hlp;
import defpackage.hmh;
import defpackage.hml;
import defpackage.hnd;
import defpackage.hoi;
import defpackage.hpu;
import defpackage.hpx;
import defpackage.koq;
import defpackage.nrt;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class TrackShareAllDataFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f3755a;
    private int c;
    private List<PostureJudgeBean> h;
    private TrackShareDetailMapAndDetail j;
    private ShareInitCallback l;
    private RunRouteView n;
    private View p;
    private hjw s = null;
    private Context e = null;
    private TrackShareViewGroup o = null;
    private HealthScrollView k = null;
    private Resources m = null;
    private List<String> t = new ArrayList();
    private int g = 16;
    private int q = 100;
    private int b = 100;
    private Bundle f = null;
    private hmh d = null;
    private final String r = "viewTask";
    private boolean i = false;

    private boolean e(int i, float f, float f2, int i2) {
        return ((f > 1.0f ? 1 : (f == 1.0f ? 0 : -1)) >= 0 || (f2 > 1.0f ? 1 : (f2 == 1.0f ? 0 : -1)) >= 0) || (i > 0 || i2 > 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Track_TrackShareAllDataFragment", "onCreate");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        if (layoutInflater == null) {
            throw new AssertionError("LayoutInflater not found.");
        }
        LogUtil.a("Track_TrackShareAllDataFragment", "onCreateView");
        this.f = getArguments();
        View inflate = layoutInflater.inflate(R.layout.fragment_track_share_all_data, viewGroup, false);
        FragmentActivity activity = getActivity();
        this.e = activity;
        this.m = activity.getResources();
        this.g = nsn.c(this.e, 16.0f);
        this.t.add("viewTask");
        this.o = (TrackShareViewGroup) inflate.findViewById(R.id.track_scrollview_share);
        this.k = (HealthScrollView) inflate.findViewById(R.id.track_share_all_data_scroll);
        this.p = inflate.findViewById(R.id.track_share_all_white);
        hjw c = gxp.a().c();
        this.s = c;
        if (c == null) {
            LogUtil.h("Track_TrackShareAllDataFragment", "mTrackDetailDataManager is null");
            return null;
        }
        int c2 = nsn.c(this.e, 250.0f);
        Bundle bundle2 = this.f;
        if (bundle2 != null) {
            i = bundle2.getInt("allDataWidth", c2);
            this.f3755a = this.f.getInt("allDataHeight", nsn.c(this.e, 400.0f));
        } else {
            this.f3755a = nsn.c(this.e, 250.0f);
            i = c2;
        }
        this.c = nsn.c(this.e, (i / c2) * 380.0f);
        d(i);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        List<String> list = this.t;
        if (list != null) {
            list.remove(str);
        }
        if (!koq.b(this.t) || this.l == null) {
            return;
        }
        LogUtil.a("Track_TrackShareAllDataFragment", "ShareInitCallback is finish");
        this.l.onFinish();
    }

    private void d(int i) {
        ViewGroup.LayoutParams layoutParams = this.k.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = this.f3755a;
        this.k.setLayoutParams(layoutParams);
        Object systemService = this.e.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.b("Track_TrackShareAllDataFragment", "object is not instanceof WindowManager");
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        ViewGroup.LayoutParams layoutParams2 = this.o.getLayoutParams();
        layoutParams2.width = i2;
        this.o.setLayoutParams(layoutParams2);
        as();
        au();
        ao();
        final float f = (i * 1.0f) / i2;
        b(i2);
        this.o.setScaleX(f);
        this.o.setScaleY(f);
        this.k.setScrollViewListener(new ScrollViewListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackShareAllDataFragment.2
            @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
            public void onScrollChanged(HealthScrollView healthScrollView, int i3, int i4, int i5, int i6) {
                int measuredHeight;
                if (TrackShareAllDataFragment.this.o == null || TrackShareAllDataFragment.this.k.getScrollY() <= (measuredHeight = ((int) (TrackShareAllDataFragment.this.o.getMeasuredHeight() * f)) - TrackShareAllDataFragment.this.c)) {
                    return;
                }
                TrackShareAllDataFragment.this.k.setScrollY(measuredHeight);
            }
        });
        this.k.post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackShareAllDataFragment.1
            @Override // java.lang.Runnable
            public void run() {
                if (TrackShareAllDataFragment.this.o.getMeasuredHeight() * f < TrackShareAllDataFragment.this.f3755a) {
                    ViewGroup.LayoutParams layoutParams3 = TrackShareAllDataFragment.this.p.getLayoutParams();
                    layoutParams3.height = (int) ((TrackShareAllDataFragment.this.f3755a - (TrackShareAllDataFragment.this.o.getMeasuredHeight() * f)) / 2.0f);
                    TrackShareAllDataFragment.this.p.setLayoutParams(layoutParams3);
                }
                TrackShareAllDataFragment.this.a("viewTask");
            }
        });
    }

    private void b(int i) {
        if (LanguageUtil.bc(this.e)) {
            this.o.setPivotX(i);
            this.o.setPivotY(0.0f);
        } else {
            this.o.setPivotX(0.0f);
            this.o.setPivotY(0.0f);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.a("Track_TrackShareAllDataFragment", "onResume");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Track_TrackShareAllDataFragment", "onDestroy");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        LogUtil.a("Track_TrackShareAllDataFragment", "onDetach");
    }

    private void as() {
        this.b = 100;
        this.q = 100;
        Context context = this.e;
        if (context != null && nrt.a(context)) {
            this.i = true;
        }
        if (this.i) {
            this.q = 101;
        }
        ap();
    }

    private void ao() {
        int i = this.b;
        if (i == 101) {
            this.o.setAllChildViewBackgroudSource(R.drawable.track_share_device_card_bg, 0);
            this.o.setBackgroundResource(R.drawable._2131431928_res_0x7f0b11f8);
        } else if (i == 102) {
            this.o.setAllChildViewBackgroudSource(R.drawable.track_share_device_card_bg, 0);
            this.o.setBackgroundResource(R.drawable._2131431928_res_0x7f0b11f8);
        } else if (this.i) {
            this.o.setAllChildViewBackgroudSource(R.drawable.track_share_device_card_bg, 1);
            this.o.setBackgroundResource(R.drawable._2131431928_res_0x7f0b11f8);
        } else {
            this.o.setAllChildViewBackgroudSource(R.drawable._2131431920_res_0x7f0b11f0, 0);
            this.o.setBackgroundColor(this.m.getColor(R.color._2131298163_res_0x7f090773));
        }
        this.j.h();
    }

    private void au() {
        if (this.o == null) {
            return;
        }
        if (this.s.e().requestSportType() == 512) {
            p();
            b(this.s.o());
            a(this.s.o());
            d();
            return;
        }
        p();
        int requestSportType = this.s.e().requestSportType();
        if (aw()) {
            o();
            t();
            l();
            s();
        }
        y();
        if (gwg.a(this.s.e())) {
            r();
        }
        k();
        n();
        ab();
        g();
        al();
        af();
        if (!aw() && requestSportType != 265 && !hji.g(requestSportType)) {
            ad();
        }
        ag();
        b();
        x();
        w();
        ak();
        ah();
        ac();
        a();
        u();
        ae();
        ai();
        q();
        aa();
        z();
        v();
        d();
    }

    private void aa() {
        if (gvv.c(this.s.e()).equals("291")) {
            RowingMachineHolder rowingMachineHolder = new RowingMachineHolder(this.e);
            rowingMachineHolder.c(this.s.j().requestSegmentList());
            this.o.addView(rowingMachineHolder.blc_());
        }
    }

    private void z() {
        if (this.s.e().requestSportType() == 283) {
            MotionData j = this.s.j();
            if (j == null) {
                LogUtil.b("Track_TrackShareAllDataFragment", "drawRopeSkippingView motionData is null");
                return;
            }
            List<CommonSegment> requestSegmentList = j.requestSegmentList();
            if (koq.b(requestSegmentList)) {
                LogUtil.h("Track_TrackShareAllDataFragment", "drawRopeSkippingView is not intermittent jump");
                return;
            }
            RopeSkippingHolder ropeSkippingHolder = new RopeSkippingHolder(this.e);
            ropeSkippingHolder.d(requestSegmentList);
            this.o.addView(ropeSkippingHolder.bkW_());
        }
    }

    private void v() {
        if (this.s.av()) {
            SkippingPerformanceView skippingPerformanceView = new SkippingPerformanceView(this.e);
            skippingPerformanceView.a();
            skippingPerformanceView.setIsSingleRecord(true);
            Pair<float[], float[]> a2 = this.s.a();
            if (a2 != null) {
                skippingPerformanceView.setData(a2.first, a2.second, true);
            }
            Pair<float[], float[]> c = this.s.c();
            skippingPerformanceView.getRadarView().setTextSize(nsn.c(this.e, 10.0f));
            if (c != null) {
                skippingPerformanceView.setData(c.first, c.second, false);
            }
            skippingPerformanceView.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
            this.o.addView(skippingPerformanceView);
        }
    }

    private void b(List<gya> list) {
        if (list != null) {
            TrackLineChartHolderImpl trackLineChartHolderImpl = new TrackLineChartHolderImpl(this.e.getApplicationContext());
            hkb hkbVar = new hkb(trackLineChartHolderImpl, this.s);
            for (gya gyaVar : list) {
                if (gyaVar.b() != null && gyaVar.c() != null) {
                    b(hkbVar, gyaVar);
                }
            }
            TrackLineChartHolder.setInstance(trackLineChartHolderImpl);
        }
    }

    private void b(hkb hkbVar, gya gyaVar) {
        int requestSportType = gyaVar.b().requestSportType();
        if (requestSportType == 258) {
            hkbVar.a(SportDetailChartDataType.HEART_RATE, gyaVar.b(), gyaVar.c());
            return;
        }
        if (requestSportType == 259) {
            hkbVar.a(SportDetailChartDataType.SPEED_RATE, gyaVar.b(), gyaVar.c());
        } else if (requestSportType == 262 || requestSportType == 266) {
            hkbVar.a(SportDetailChartDataType.REALTIME_PACE, gyaVar.b(), gyaVar.c());
        }
    }

    private void a(List<gya> list) {
        if (list == null) {
            LogUtil.b("Track_TrackShareAllDataFragment", "drawAllItemOfTriathlon DetailInfoExtList is null");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).b() != null && list.get(i).c() != null) {
                d(list.get(i).b(), list.get(i).c());
            } else {
                c(list.get(i));
            }
            if (i != list.size() - 1) {
                b(list.get(i));
            }
        }
    }

    private void b(gya gyaVar) {
        View inflate = View.inflate(this.e, R.layout.triathlon_change, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.change_name);
        healthTextView.setText(String.format(BaseApplication.getContext().getString(R.string._2130839851_res_0x7f02092b), hji.c(gyaVar.d().getChangeIntervalTime())));
        if (this.b == 100) {
            healthTextView.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        }
        this.o.addView(inflate);
    }

    private void d(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        TriathlonShareViewGroup triathlonShareViewGroup = new TriathlonShareViewGroup(this.e, this.q);
        b(triathlonShareViewGroup, motionPathSimplify);
        d(triathlonShareViewGroup, motionPathSimplify, motionPath);
        e(triathlonShareViewGroup, motionPathSimplify, motionPath);
        this.o.addView(triathlonShareViewGroup, layoutParams);
    }

    private void c(gya gyaVar) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        TriathlonShareViewGroup triathlonShareViewGroup = new TriathlonShareViewGroup(this.e, this.q);
        d(triathlonShareViewGroup, gyaVar);
        e(triathlonShareViewGroup, gyaVar);
        this.o.addView(triathlonShareViewGroup, layoutParams);
    }

    private Bitmap bgs_() {
        if (new SecureRandom().nextInt(2) + 1 == 1) {
            return BitmapFactory.decodeResource(getResources(), R.drawable._2131427587_res_0x7f0b0103);
        }
        return BitmapFactory.decodeResource(getResources(), R.drawable._2131427588_res_0x7f0b0104);
    }

    private void p() {
        Bitmap aVH_;
        this.j = new TrackShareDetailMapAndDetail(this.e, this.q);
        int requestSportType = this.s.e().requestSportType();
        if (aw()) {
            this.j.c().b();
            aVH_ = (!LanguageUtil.m(this.e) || Utils.o()) ? null : bgs_();
        } else {
            aVH_ = gxp.a().aVH_();
        }
        if (aVH_ == null) {
            i(this.j);
        } else {
            this.j.setImgMap(aVH_, requestSportType, this.s.ax());
            this.j.setLayoutStyle(this.b, true, requestSportType);
        }
        b(this.j);
        bgu_(aVH_);
        TrackShareDetailCustomTitleLayout c = this.j.c();
        if (c != null) {
            MotionPathSimplify e = this.s.e();
            c.setSportStartTime(UnitUtil.a("yyyy/M/d H:mm", e.requestStartTime()));
            if (e.requestRunCourseId() != null) {
                String d = gwg.d(this.e, this.s.e().requestRunCourseId(), this.s.e().getExtendDataString("courseName", ""));
                if (TextUtils.isEmpty(d)) {
                    c.setTextSportType(gwg.e(this.e, e.requestSportType()));
                } else {
                    c.setTextSportType(d);
                }
            } else if (gwg.a(e)) {
                c.setTextSportType(getString(R.string._2130845268_res_0x7f021e54));
            } else if (this.s.ax()) {
                c.setTextSportType(getString(R.string._2130847254_res_0x7f022616));
            } else {
                c.setTextSportType(gwg.e(this.e, e.requestSportType()));
            }
            d(c, e);
        }
        e(this.j);
        d(this.j);
        c(this.j);
        a(this.j);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.leftMargin = this.m.getDimensionPixelOffset(R.dimen._2131365060_res_0x7f0a0cc4);
        this.j.setLayoutParams(layoutParams);
        this.o.addView(this.j);
    }

    private void bgu_(Bitmap bitmap) {
        if (this.s.e().requestSportType() == 512) {
            this.j.setShowPaceVisibility(8);
            this.j.setDivideLineVisibility(0);
            return;
        }
        if (aw()) {
            this.j.setShowPaceVisibility(8);
            this.j.setDivideLineVisibility(8);
            return;
        }
        if (bitmap == null) {
            this.j.setShowPaceVisibility(8);
            this.j.setDivideLineVisibility(8);
            return;
        }
        Map<Integer, Float> bb = this.s.bb();
        if (bb != null && bb.size() >= 2 && this.s.a(this.e)) {
            Float[] e = this.s.e(bb);
            if (e != null && e.length >= 2) {
                this.j.setMaxAndMinPaceVisibility(0);
                c(this.s.e().requestSportType(), gvv.e(bb));
                return;
            } else {
                this.j.setShowPaceVisibility(8);
                this.j.setDivideLineVisibility(0);
                return;
            }
        }
        this.j.setShowPaceVisibility(8);
        this.j.setDivideLineVisibility(8);
    }

    private void c(int i, Float[] fArr) {
        String e = e(fArr[0], i);
        String e2 = e(fArr[1], i);
        if (hji.g(i) && hji.c(this.s)) {
            if (hji.d(this.s)) {
                e2 = hji.c(this.s, false);
                e = hji.a(this.s, false);
            } else {
                this.j.setMaxAndMinPaceVisibility(8);
            }
        }
        a(e2, e, i, this.e);
        MotionPath d = this.s.d();
        if (d != null) {
            Float[] e3 = gvv.e(d.requestPaceMap());
            this.j.setImgPaceGradientColors(gwe.b(e3[1].floatValue(), e3[0].floatValue(), i));
        }
    }

    private String e(Float f, int i) {
        String a2 = gvv.a(f.floatValue());
        if (hji.g(i)) {
            return hji.o(f.floatValue());
        }
        if (i == 266 || i == 262) {
            return hji.o(f.floatValue() / 10.0f);
        }
        LogUtil.c("Track_TrackShareAllDataFragment", "not show speed");
        return a2;
    }

    private void a(String str, String str2, int i, Context context) {
        if (hji.g(i) && context != null && str != null && str2 != null) {
            StringBuffer stringBuffer = new StringBuffer(str);
            StringBuffer stringBuffer2 = new StringBuffer(str2);
            stringBuffer.append(" ");
            stringBuffer2.append(" ");
            if (UnitUtil.h()) {
                stringBuffer.append(context.getResources().getString(R.string._2130844079_res_0x7f0219af));
                stringBuffer2.append(context.getResources().getString(R.string._2130844079_res_0x7f0219af));
            } else {
                stringBuffer.append(context.getResources().getString(R.string._2130844078_res_0x7f0219ae));
                stringBuffer2.append(context.getResources().getString(R.string._2130844078_res_0x7f0219ae));
            }
            String stringBuffer3 = stringBuffer.toString();
            str2 = stringBuffer2.toString();
            str = stringBuffer3;
        }
        this.j.setMaxAndMinPace(str, str2);
    }

    private void i(TrackShareDetailMapAndDetail trackShareDetailMapAndDetail) {
        trackShareDetailMapAndDetail.j();
        trackShareDetailMapAndDetail.setLayoutStyle(this.b, false, this.s.e().requestSportType());
    }

    private void e(TrackShareDetailMapAndDetail trackShareDetailMapAndDetail) {
        int requestSportType = this.s.e().requestSportType();
        int requestDeviceType = this.s.e().requestDeviceType();
        int requestTrackType = this.s.e().requestTrackType();
        int requestSportDataSource = this.s.e().requestSportDataSource();
        int e = gvv.e(requestTrackType, requestDeviceType);
        gwg.b(this.e);
        if (requestDeviceType == 46) {
            trackShareDetailMapAndDetail.setImgDevice(R.drawable._2131431945_res_0x7f0b1209);
            trackShareDetailMapAndDetail.setTextDeviceVisibility(8);
            return;
        }
        if (aw()) {
            trackShareDetailMapAndDetail.setImgDeviceVisibility(8);
            trackShareDetailMapAndDetail.setTextDeviceVisibility(0);
            Context context = this.e;
            trackShareDetailMapAndDetail.setTextDevice(0, cwa.d(requestDeviceType, context, context.getPackageName(), this.s.e().requestProductId()), requestSportType);
            return;
        }
        if (requestSportType == 283 && requestSportDataSource == 5) {
            String aq = aq();
            if (TextUtils.isEmpty(aq)) {
                trackShareDetailMapAndDetail.setTextDeviceVisibility(8);
                return;
            } else {
                trackShareDetailMapAndDetail.setVisibility(0);
                trackShareDetailMapAndDetail.setTextDevice(aq);
                return;
            }
        }
        if (this.b == 100) {
            trackShareDetailMapAndDetail.setImgDeviceVisibility(8);
            if (e == 0) {
                String b = hpx.b(this.s.e());
                if (TextUtils.isEmpty(b)) {
                    trackShareDetailMapAndDetail.setTextDeviceVisibility(8);
                    return;
                } else {
                    trackShareDetailMapAndDetail.setVisibility(0);
                    trackShareDetailMapAndDetail.setTextDevice(b);
                    return;
                }
            }
            trackShareDetailMapAndDetail.setTextDeviceVisibility(0);
            Context context2 = this.e;
            trackShareDetailMapAndDetail.setTextDevice(cwa.d(requestDeviceType, context2, context2.getPackageName(), this.s.e().requestProductId()));
            return;
        }
        d(trackShareDetailMapAndDetail, requestDeviceType, requestSportType);
    }

    private String aq() {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(PluginPayAdapter.KEY_DEVICE_INFO_NAME, 0);
        return sharedPreferences != null ? sharedPreferences.getString("device_name_key", "") : "";
    }

    private void d(TrackShareDetailMapAndDetail trackShareDetailMapAndDetail, int i, int i2) {
        if (trackShareDetailMapAndDetail.a(i) == 0) {
            trackShareDetailMapAndDetail.setImgDeviceVisibility(0);
            trackShareDetailMapAndDetail.setTextDeviceVisibility(8);
        } else {
            trackShareDetailMapAndDetail.setImgDeviceVisibility(8);
            trackShareDetailMapAndDetail.setTextDeviceVisibility(0);
            Context context = this.e;
            trackShareDetailMapAndDetail.setTextDevice(0, cwa.d(i, context, context.getPackageName(), this.s.e().requestProductId()), i2);
        }
    }

    private void d(TrackShareDetailCustomTitleLayout trackShareDetailCustomTitleLayout, MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null || aw()) {
            return;
        }
        int requestChiefSportDataType = motionPathSimplify.requestChiefSportDataType();
        if (2 == requestChiefSportDataType || 1 == requestChiefSportDataType) {
            if (motionPathSimplify.requestTotalCalories() != 0) {
                trackShareDetailCustomTitleLayout.setTextChiefData(hji.c(motionPathSimplify.requestTotalCalories()));
            } else {
                trackShareDetailCustomTitleLayout.setTextChiefData("--");
            }
            trackShareDetailCustomTitleLayout.setTextChiefUnit(this.m.getString(R.string._2130839711_res_0x7f02089f));
            return;
        }
        if (motionPathSimplify.requestSportType() == 220) {
            String quantityString = this.e.getResources().getQuantityString(R.plurals._2130903269_res_0x7f0300e5, motionPathSimplify.getExtendDataInt("golfSwingCount"), "");
            trackShareDetailCustomTitleLayout.setTextChiefData(motionPathSimplify.requestExtendDataMap().get("golfSwingCount"));
            trackShareDetailCustomTitleLayout.setTextChiefUnit(quantityString);
            return;
        }
        if (requestChiefSportDataType == 7) {
            e(trackShareDetailCustomTitleLayout, motionPathSimplify);
            return;
        }
        if (requestChiefSportDataType == 11) {
            a(trackShareDetailCustomTitleLayout, motionPathSimplify);
            return;
        }
        if (this.s.e().requestTotalDistance() == 0) {
            trackShareDetailCustomTitleLayout.setTextChiefData("--");
        }
        if (motionPathSimplify.requestSportType() == 262 || motionPathSimplify.requestSportType() == 266) {
            b(trackShareDetailCustomTitleLayout);
            return;
        }
        if (UnitUtil.h()) {
            trackShareDetailCustomTitleLayout.setTextChiefUnit(this.m.getString(R.string._2130841383_res_0x7f020f27));
        } else {
            trackShareDetailCustomTitleLayout.setTextChiefUnit(this.m.getString(R.string._2130841382_res_0x7f020f26));
        }
        int requestTotalDistance = this.s.e().requestTotalDistance();
        if (requestTotalDistance > 0) {
            trackShareDetailCustomTitleLayout.setTextChiefData(hji.e(requestTotalDistance));
        } else {
            trackShareDetailCustomTitleLayout.setTextChiefData("--");
        }
    }

    private void a(TrackShareDetailCustomTitleLayout trackShareDetailCustomTitleLayout, MotionPathSimplify motionPathSimplify) {
        long requestTotalTime = motionPathSimplify.requestTotalTime();
        if (requestTotalTime <= 0) {
            ReleaseLogUtil.d("Track_TrackShareAllDataFragment", "initChiefTime() totalTime is null");
            trackShareDetailCustomTitleLayout.setTextChiefData("--");
        } else {
            trackShareDetailCustomTitleLayout.setTextChiefData(gvv.c(requestTotalTime, R.style.sport_day_hour_min_num_38dp, R.style.sport_day_hour_min_unit_14dp));
            trackShareDetailCustomTitleLayout.setTextChiefUnit(null);
        }
    }

    private void e(TrackShareDetailCustomTitleLayout trackShareDetailCustomTitleLayout, MotionPathSimplify motionPathSimplify) {
        int extendDataInt = motionPathSimplify.getExtendDataInt("skipNum");
        if (extendDataInt > 0) {
            trackShareDetailCustomTitleLayout.setTextChiefData(UnitUtil.e(extendDataInt, 1, 0));
        } else {
            trackShareDetailCustomTitleLayout.setTextChiefData("--");
        }
        trackShareDetailCustomTitleLayout.setTextChiefUnit(this.m.getQuantityString(R.plurals._2130903274_res_0x7f0300ea, extendDataInt, ""));
    }

    private void b(TrackShareDetailCustomTitleLayout trackShareDetailCustomTitleLayout) {
        if (UnitUtil.h()) {
            trackShareDetailCustomTitleLayout.setTextChiefUnit(this.m.getQuantityString(R.plurals._2130903227_res_0x7f0300bb, (int) Math.round(UnitUtil.e(this.s.e().requestTotalDistance(), 2))));
        } else {
            trackShareDetailCustomTitleLayout.setTextChiefUnit(this.m.getString(R.string._2130841568_res_0x7f020fe0));
        }
        int requestTotalDistance = this.s.e().requestTotalDistance();
        if (requestTotalDistance > 0) {
            trackShareDetailCustomTitleLayout.setTextChiefData(hji.i(requestTotalDistance));
        } else {
            trackShareDetailCustomTitleLayout.setTextChiefData("--");
        }
    }

    private void b(TrackShareDetailMapAndDetail trackShareDetailMapAndDetail) {
        if (!aw()) {
            trackShareDetailMapAndDetail.setRadarViewVisibility(8);
            return;
        }
        trackShareDetailMapAndDetail.setRadarViewVisibility(0);
        trackShareDetailMapAndDetail.getBasketballRadarView().setRadarScore(hji.c(this.s.e().requestSportData()));
        Map<String, Integer> requestSportData = this.s.e().requestSportData();
        if (requestSportData.containsKey("overall_score")) {
            trackShareDetailMapAndDetail.getBasketballRadarView().setAverageScore(this.s.e().requestSportData().get("overall_score").intValue());
        } else {
            LogUtil.b("Track_TrackShareAllDataFragment", "no score");
        }
        trackShareDetailMapAndDetail.getBasketballRadarView().setTotalScoreGone();
        if (requestSportData.containsKey("active_time")) {
            trackShareDetailMapAndDetail.getBasketballRadarView().setActiveTime(requestSportData.get("active_time").intValue());
        }
        trackShareDetailMapAndDetail.getBasketballRadarView().setBasketballStamp(true);
    }

    private boolean aw() {
        return this.s.e().requestSportType() == 271 && this.s.e().requestChiefSportDataType() == 5;
    }

    private void r() {
        MotionPathSimplify e = this.s.e();
        View inflate = View.inflate(this.e, R.layout.track_share_item_lactic, null);
        if (LanguageUtil.f(this.e)) {
            ((TextView) inflate.findViewById(R.id.share_lactate_threshold_title)).setTextSize(0, nsn.c(this.e, 12.0f));
        }
        bgr_((LinearLayout) inflate.findViewById(R.id.track_detail_lactic_data_items), e, this.m.getDimensionPixelOffset(R.dimen._2131362563_res_0x7f0a0303));
        this.o.addView(inflate);
    }

    private void bgr_(LinearLayout linearLayout, MotionPathSimplify motionPathSimplify, int i) {
        String string;
        SportDetailItem.b bVar = new SportDetailItem.b(this.m.getDrawable(R.drawable.ic_heart_rate), this.m.getString(R.string._2130845160_res_0x7f021de8), getString(R.string._2130845321_res_0x7f021e89), "");
        SportDetailItem.b bVar2 = new SportDetailItem.b(this.m.getDrawable(R.drawable._2131429729_res_0x7f0b0961), this.m.getString(R.string._2130845161_res_0x7f021de9), getString(R.string._2130845321_res_0x7f021e89), "");
        int extendDataInt = motionPathSimplify.getExtendDataInt("lthrHr");
        if (extendDataInt > 0) {
            bVar.d(UnitUtil.e(extendDataInt, 1, 0));
            bVar.e(this.m.getString(R.string.IDS_main_watch_heart_rate_unit_string));
        }
        int extendDataInt2 = motionPathSimplify.getExtendDataInt("lthrPace");
        if (extendDataInt2 > 0) {
            boolean h = UnitUtil.h();
            bVar2.d(gvv.a(h ? (float) UnitUtil.d(extendDataInt2, 3) : extendDataInt2));
            StringBuilder sb = new StringBuilder("/");
            if (h) {
                string = this.m.getString(R.string._2130844081_res_0x7f0219b1);
            } else {
                string = this.m.getString(R.string._2130844082_res_0x7f0219b2);
            }
            sb.append(string);
            bVar2.e(sb.toString());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(bVar);
        arrayList.add(bVar2);
        new TrackDetailItemDrawer.TrackDetailItemDrawHelper(this.e).bhC_(linearLayout, i, arrayList, true, this.m.getColor(R.color._2131299236_res_0x7f090ba4));
    }

    private void k() {
        if (this.s.e() == null || this.s.d() == null) {
            return;
        }
        if (this.s.z()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "drawHeartRateDataView hide");
            return;
        }
        this.d = new hmh(this.e, 1, this.q);
        HwSportTypeInfo d = hln.c(com.huawei.haf.application.BaseApplication.e()).d(this.s.e().requestSportType());
        if (d == null) {
            LogUtil.h("Track_TrackShareAllDataFragment", "drawHeartRateDataView sportTypeInfoById is null");
            return;
        }
        View buildView = this.d.buildView(d.getHeartPostureType(), this.s.e().requestHeartRateZoneType());
        this.d.c(this.s, ffw.b((List<HeartRateData>) this.s.d().requestHeartRateList()), this.s.e().requestAvgHeartRate());
        HwHealthBaseCombinedChart acquireHeartRateChart = this.d.acquireHeartRateChart();
        if (acquireHeartRateChart == null) {
            LogUtil.a("Track_TrackShareAllDataFragment", "drawHeartRateDataView chart is null");
            return;
        }
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            trackLineChartHolder = new TrackLineChartHolderImpl(this.e.getApplicationContext());
        }
        acquireHeartRateChart.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        trackLineChartHolder.addHeartRateDataLayer(acquireHeartRateChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        acquireHeartRateChart.setTouchEnabled(false);
        acquireHeartRateChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        acquireHeartRateChart.refresh();
        this.o.addView(buildView);
    }

    private void n() {
        if (this.s.e() == null || this.s.d() == null) {
            LogUtil.b("Track_TrackShareAllDataFragment", "acquireMotionPathSimplify is null or acquireMotionPath is null");
            return;
        }
        if (this.s.ag()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "drawHeartRateDataView hide");
            return;
        }
        TrackChartViewHolder at = at();
        at.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = at.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            LogUtil.b("Track_TrackShareAllDataFragment", "trackLineChartHolder is null");
            return;
        }
        trackLineChartHolder.addRecoverHeartRateLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        BaseApplication.getContext().getResources();
        XAxis xAxis = combinedChart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(120);
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackShareAllDataFragment.4
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            public String getFormattedValue(float f, AxisBase axisBase) {
                DecimalFormat decimalFormat = new DecimalFormat("#");
                int round = Math.round(f / 60.0f);
                if (decimalFormat.format(round).equals("0")) {
                    return nsj.c(TrackShareAllDataFragment.this.e, TrackShareAllDataFragment.this.s.e().requestEndTime(), 1);
                }
                return TrackShareAllDataFragment.this.getResources().getQuantityString(R.plurals._2130903305_res_0x7f030109, round, Integer.valueOf(round));
            }
        });
        combinedChart.getDescription().setText("");
        combinedChart.refresh();
        this.o.addView(at);
    }

    private TrackChartViewHolder at() {
        int i;
        int i2;
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 124);
        List<HeartRateData> requestHeartRecoveryRateList = this.s.d().requestHeartRecoveryRateList();
        Iterator<HeartRateData> it = requestHeartRecoveryRateList.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                i2 = 0;
                break;
            }
            HeartRateData next = it.next();
            if (next.acquireHeartRate() > 0) {
                i2 = next.acquireHeartRate();
                break;
            }
        }
        int size = requestHeartRecoveryRateList.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            if (requestHeartRecoveryRateList.get(size).acquireHeartRate() > 0) {
                i = requestHeartRecoveryRateList.get(size).acquireHeartRate();
                break;
            }
            size--;
        }
        trackChartViewHolder.c((i2 - i) / 2.0f);
        trackChartViewHolder.a(i2, i);
        return trackChartViewHolder;
    }

    private void t() {
        hjw hjwVar = this.s;
        if (hjwVar == null) {
            LogUtil.a("Track_TrackShareAllDataFragment", "drawMovingSpeedView mTrackDetailDataManager is null");
            return;
        }
        MotionPathSimplify e = hjwVar.e();
        if (e == null) {
            LogUtil.h("Track_TrackShareAllDataFragment", "drawMovingSpeedView motionPathSimplify is null");
            return;
        }
        int requestTotalDistance = e.requestTotalDistance();
        double d = gvv.b((double) (e.requestTotalTime() / 1000.0f)) ? 0.0d : ((requestTotalDistance * 1.0f) / r2) * 3.6f;
        float intValue = (e.requestSportData() == null || !e.requestSportData().containsKey("max_spriting_speed") || e.requestSportData().get("max_spriting_speed") == null) ? 0.0f : (this.s.e().requestSportData().get("max_spriting_speed").intValue() * 3.6f) / 10.0f;
        if (UnitUtil.h()) {
            d = UnitUtil.e(d, 3);
            intValue = (float) UnitUtil.e(intValue, 3);
        }
        if (gvv.b(intValue) || gvv.b(d) || this.s.d() == null || !this.s.d().isValidSpeedList()) {
            return;
        }
        hmh hmhVar = new hmh(this.e, 3, this.q);
        View buildView = hmhVar.buildView(hln.c(com.huawei.haf.application.BaseApplication.e()).d(this.s.e().requestSportType()).getHeartPostureType(), 0);
        hmhVar.c(this.s, intValue, (float) d);
        this.o.addView(buildView);
    }

    private void g() {
        MotionPathSimplify e = this.s.e();
        if (SportSupportUtil.b(e)) {
            CourseActionViewHolder courseActionViewHolder = new CourseActionViewHolder(this.e);
            View bkB_ = courseActionViewHolder.bkB_(true);
            if (courseActionViewHolder.e(e)) {
                this.o.addView(courseActionViewHolder.b(true));
                this.o.addView(bkB_);
            }
        }
    }

    private void ab() {
        if (!RunningRouteUtils.a(this.s.e().requestSportType())) {
            LogUtil.a("Track_TrackShareAllDataFragment", "not support punch track");
            return;
        }
        Map<String, String> requestExtendDataMap = this.s.e().requestExtendDataMap();
        if (!hpu.c(requestExtendDataMap)) {
            av();
            return;
        }
        String str = requestExtendDataMap.get("hotPathId");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_TrackShareAllDataFragment", "hotPathId is null in drawRunRouteView");
            return;
        }
        RunRouteView runRouteView = new RunRouteView(this.e);
        this.n = runRouteView;
        runRouteView.e(false);
        this.o.addView(this.n);
        this.n.setHotPathName(requestExtendDataMap.get("hotPathName"));
        final String str2 = requestExtendDataMap.get("finishState");
        final long ar = ar();
        if (ar != -1) {
            this.n.setHotTrackParticipateNum(str2, (int) ar);
        }
        this.t.add("runRouteTask");
        final String str3 = "runRouteTask";
        hpu.d(str, new HotTrackDrawCustomTarget<Drawable>() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackShareAllDataFragment.5
            @Override // com.huawei.healthcloud.plugintrack.util.HotTrackDrawCustomTarget
            public void onGetHotTrackDetailInfo(enc encVar) {
                if (ar == -1) {
                    TrackShareAllDataFragment.this.n.setHotTrackParticipateNum(str2, (int) encVar.j());
                }
            }

            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: bgw_, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                LogUtil.a("Track_TrackShareAllDataFragment", "onResourceReady");
                TrackShareAllDataFragment.this.n.setHotPathDrawable(drawable);
                TrackShareAllDataFragment.this.a(str3);
            }

            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
                LogUtil.h("Track_TrackShareAllDataFragment", "onLoadCleared");
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable drawable) {
                super.onLoadFailed(drawable);
                LogUtil.h("Track_TrackShareAllDataFragment", "onLoadFailed");
                TrackShareAllDataFragment.this.a(str3);
            }
        });
    }

    private void av() {
        enj a2 = new gze(getActivity(), this.s.e(), 2).a();
        if (a2 == null || a2.a() == null) {
            LogUtil.a("Track_TrackShareAllDataFragment", "not punch track");
            return;
        }
        enf a3 = a2.a();
        long d = a3.d();
        String h = a3.h();
        enm f = a3.f();
        String b = f != null ? f.b() : "";
        RunRouteView runRouteView = new RunRouteView(this.e);
        this.n = runRouteView;
        runRouteView.e(false);
        this.n.setHotPathName(h);
        this.n.setHotTrackParticipateNum("", (int) d);
        this.n.setHotPathUrl(b);
        this.o.addView(this.n);
    }

    private long ar() {
        FragmentActivity activity = getActivity();
        if (activity instanceof TrackDetailActivity) {
            return ((TrackDetailActivity) activity).c();
        }
        return -1L;
    }

    private void al() {
        Map<String, Integer> requestSportData;
        if (this.s.e() == null || this.s.d() == null || (requestSportData = this.s.e().requestSportData()) == null) {
            return;
        }
        int intValue = requestSportData.get("max_met") != null ? ((int) (requestSportData.get("max_met").intValue() * 3.5f)) / 65536 : 0;
        float intValue2 = requestSportData.get("etraining_effect") != null ? requestSportData.get("etraining_effect").intValue() / 10.0f : 0.0f;
        float intValue3 = requestSportData.get("anaerobic_exercise_etraining_effect") != null ? requestSportData.get("anaerobic_exercise_etraining_effect").intValue() / 10.0f : 0.0f;
        int hours = requestSportData.get("recovery_time") != null ? (int) TimeUnit.MINUTES.toHours(requestSportData.get("recovery_time").intValue()) : 0;
        int extendDataInt = this.s.e().getExtendDataInt("eteAlgoKey", 0);
        if (e(intValue, intValue2, intValue3, hours)) {
            hoi hoiVar = new hoi(this.e, this.q);
            View bnG_ = hoiVar.bnG_();
            hoiVar.d(!hji.b(this.s).booleanValue() ? 0 : intValue, intValue2, intValue3, hours, extendDataInt);
            this.o.addView(bnG_);
        }
    }

    private void q() {
        View bkV_;
        if (this.s.e() == null || this.s.d() == null || this.s.b(1)) {
            return;
        }
        hjw hjwVar = this.s;
        Map<Integer, Float> e = hjwVar.e(hjwVar.e().requestSportType());
        Map<Integer, Float> a2 = gvv.a(e);
        gvv.a(a2, e, this.s);
        if (a2 == null || a2.size() == 0) {
            return;
        }
        if (this.s.aw()) {
            hnd hndVar = new hnd(this.e, this.q);
            bkV_ = hndVar.blB_();
            hndVar.a(this.s, a2);
        } else {
            hml hmlVar = new hml(this.e, this.q);
            hmlVar.b(this.s.e().requestSportType());
            bkV_ = hmlVar.bkV_();
            hmlVar.d(this.s, a2);
        }
        bkV_.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        this.o.addView(bkV_);
    }

    private void ag() {
        if (this.s.e() == null || this.s.d() == null) {
            return;
        }
        if (this.s.ar()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "drawStepRateView hide");
            return;
        }
        int e = ffw.e((StepRateData) Collections.max(this.s.d().requestStepRateList(), new Comparator<StepRateData>() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackShareAllDataFragment.3
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(StepRateData stepRateData, StepRateData stepRateData2) {
                if (stepRateData == null || stepRateData2 == null) {
                    return 0;
                }
                return ffw.e(stepRateData) - ffw.e(stepRateData2);
            }
        }));
        int requestAvgStepRate = this.s.e().requestAvgStepRate();
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 2);
        trackChartViewHolder.c(requestAvgStepRate);
        trackChartViewHolder.e(e);
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addStepRateDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void b() {
        if (this.s.e() == null || this.s.d() == null) {
            return;
        }
        if (this.s.u()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "cadenceView hide");
            return;
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 13);
        trackChartViewHolder.c(this.s.g().get(SportDetailChartDataType.CADENCE).h());
        trackChartViewHolder.e(this.s.g().get(SportDetailChartDataType.CADENCE).f());
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addCadenceRateDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void y() {
        if (this.s.d() == null || this.s.d().requestResistanceList() == null || this.s.d().requestResistanceList().size() <= 0) {
            return;
        }
        hmh hmhVar = new hmh(this.e, 4, this.q);
        View buildView = hmhVar.buildView(hln.c(com.huawei.haf.application.BaseApplication.e()).d(this.s.e().requestSportType()).getHeartPostureType(), 0);
        hmhVar.c(this.s, 0.0f, 0.0f);
        this.o.addView(buildView);
    }

    private void w() {
        if (this.s.e() == null || this.s.d() == null) {
            return;
        }
        if (this.s.ai()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "powerView hide");
            return;
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 15);
        trackChartViewHolder.c(this.s.g().get(SportDetailChartDataType.POWER).h());
        trackChartViewHolder.e(this.s.g().get(SportDetailChartDataType.POWER).f());
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addPowerDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void ak() {
        if (this.s.e() == null || this.s.d() == null) {
            return;
        }
        if (this.s.ae()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "weightView hide");
            return;
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 24);
        trackChartViewHolder.c(this.s.g().get(SportDetailChartDataType.PEAK_WEIGHT).h());
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addWeightDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void ah() {
        if (this.s.aj()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "drawSkippingSpeedView skip hide");
            return;
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 16);
        trackChartViewHolder.c(this.s.e().getExtendDataInt("skipSpeed", 0));
        trackChartViewHolder.e(hji.g(this.s.d().requestSkippingSpeedList()));
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            trackLineChartHolder = new TrackLineChartHolderImpl(this.e.getApplicationContext());
        }
        trackLineChartHolder.addSkippingSpeedDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void x() {
        TrackChartViewHolder trackChartViewHolder;
        if (this.s.e() == null || this.s.d() == null) {
            return;
        }
        if (this.s.aa()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "paddleView hide");
            return;
        }
        if (gvv.c(this.s.e()).equals("291")) {
            trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 25);
        } else {
            trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 14);
        }
        trackChartViewHolder.c(this.s.g().get(SportDetailChartDataType.PADDLE_FREQUENCY).h());
        trackChartViewHolder.e(this.s.g().get(SportDetailChartDataType.PADDLE_FREQUENCY).f());
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addPaddleFreqDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void ai() {
        if (this.s.e() == null || this.s.d() == null) {
            return;
        }
        if (this.s.ao()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "spo2view hide");
            return;
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 10);
        trackChartViewHolder.c(this.s.e().requestMinSpo2());
        trackChartViewHolder.e(this.s.e().requestMaxSpo2());
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addSpo2DataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void l() {
        hjw hjwVar = this.s;
        if (hjwVar == null) {
            LogUtil.h("Track_TrackShareAllDataFragment", "drawJumpHeight mTrackDetailDataManager is null");
            return;
        }
        MotionPathSimplify e = hjwVar.e();
        MotionPath d = this.s.d();
        if (e == null || d == null) {
            LogUtil.h("Track_TrackShareAllDataFragment", "drawJumpHeight motionPathSimplify or motionPath is null");
            return;
        }
        if (this.s.w()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "jumpHeight hide");
            return;
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 12);
        if (!UnitUtil.h()) {
            trackChartViewHolder.c(hji.d(d.requestJumpDataList()));
            trackChartViewHolder.e(hji.c(d.requestJumpDataList()));
        } else {
            trackChartViewHolder.c((int) UnitUtil.e(hji.d(d.requestJumpDataList()), 0));
            trackChartViewHolder.e((int) UnitUtil.e(hji.c(d.requestJumpDataList()), 0));
        }
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addJumpHeightDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void s() {
        hjw hjwVar = this.s;
        if (hjwVar == null) {
            LogUtil.h("Track_TrackShareAllDataFragment", "drawJumpTime mTrackDetailDataManager is null");
            return;
        }
        MotionPathSimplify e = hjwVar.e();
        MotionPath d = this.s.d();
        if (e == null || d == null) {
            LogUtil.h("Track_TrackShareAllDataFragment", "drawJumpTime motionPathSimplify or motionPath is null");
            return;
        }
        if (this.s.w()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "drawJumpTime hide");
            return;
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 11);
        trackChartViewHolder.c(hji.b(d.requestJumpDataList()));
        trackChartViewHolder.e(hji.a(d.requestJumpDataList()));
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addJumpTimeDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void d() {
        this.o.addView(View.inflate(this.e, R.layout.track_share_bottom_layout, null));
    }

    private void o() {
        hjw hjwVar = this.s;
        if (hjwVar == null) {
            LogUtil.h("Track_TrackShareAllDataFragment", "drawJumpData mTrackDetailDataManager is null");
            return;
        }
        MotionPathSimplify e = hjwVar.e();
        MotionPath d = this.s.d();
        if (e == null || d == null || !aw()) {
            return;
        }
        View inflate = View.inflate(this.e, R.layout.basketball_share_jump_view, null);
        inflate.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        DetailItemContainer detailItemContainer = (DetailItemContainer) inflate.findViewById(R.id.basketball_share_jump_container);
        TrackDetailItemDrawer trackDetailItemDrawer = new TrackDetailItemDrawer(e, d, this.e, this.q);
        trackDetailItemDrawer.e(detailItemContainer, true, 2, this.g);
        if (trackDetailItemDrawer.e() > 0) {
            this.o.addView(inflate);
        }
    }

    public void d(TrackShareDetailMapAndDetail trackShareDetailMapAndDetail) {
        TrackDetailItemDrawer trackDetailItemDrawer = new TrackDetailItemDrawer(this.s.e(), this.s.d(), this.e, this.q);
        if (trackShareDetailMapAndDetail == null) {
            LogUtil.b("Track_TrackShareAllDataFragment", "inputMapAndDetail is null");
        } else {
            trackDetailItemDrawer.e(trackShareDetailMapAndDetail.d(), true, 0, this.m.getDimensionPixelOffset(R.dimen._2131362563_res_0x7f0a0303));
        }
    }

    private void b(TriathlonShareViewGroup triathlonShareViewGroup, MotionPathSimplify motionPathSimplify) {
        triathlonShareViewGroup.d().setText(gwg.e(this.e, motionPathSimplify.requestSportType()));
    }

    private void d(TriathlonShareViewGroup triathlonShareViewGroup, gya gyaVar) {
        triathlonShareViewGroup.d().setText(gwg.e(this.e, gyaVar.d().getSportType()));
    }

    private void d(TriathlonShareViewGroup triathlonShareViewGroup, MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        new TrackDetailItemDrawer(motionPathSimplify, motionPath, this.e, this.q).e(triathlonShareViewGroup.c(), true, 1, this.m.getDimensionPixelOffset(R.dimen._2131364635_res_0x7f0a0b1b));
    }

    private void e(TriathlonShareViewGroup triathlonShareViewGroup, gya gyaVar) {
        new TrackDetailItemDrawer(gyaVar, this.e, this.q).a(triathlonShareViewGroup.c(), true, this.g);
    }

    private void e(TriathlonShareViewGroup triathlonShareViewGroup, MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        TrackChartViewHolder b;
        int requestSportType = motionPathSimplify.requestSportType();
        if (requestSportType == 258) {
            View bgt_ = bgt_(motionPathSimplify, motionPath);
            if (bgt_ != null) {
                triathlonShareViewGroup.bjN_().addView(bgt_);
                return;
            }
            return;
        }
        if (requestSportType == 259) {
            TrackChartViewHolder e = e(motionPathSimplify, motionPath);
            if (e != null) {
                triathlonShareViewGroup.bjN_().addView(e);
                return;
            }
            return;
        }
        if ((requestSportType == 262 || requestSportType == 266) && (b = b(motionPathSimplify, motionPath)) != null) {
            triathlonShareViewGroup.bjN_().addView(b);
        }
    }

    public View bgv_() {
        return this.o;
    }

    private void af() {
        if (this.s.ak()) {
            return;
        }
        this.o.addView(e(this.s.e(), this.s.d()));
    }

    private TrackChartViewHolder e(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        if (!motionPath.isValidSpeedList()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "SpeedList is not valid");
            return null;
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 3);
        trackChartViewHolder.c((float) hji.b(motionPathSimplify.requestAvgPace()));
        trackChartViewHolder.e((float) ffw.c(motionPath.requestRealTimeSpeedList()));
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return null;
        }
        trackLineChartHolder.setSportType(motionPathSimplify.requestSportType());
        trackLineChartHolder.addTrackSpeedDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        return trackChartViewHolder;
    }

    private void a() {
        if (this.s.q()) {
            return;
        }
        float requestMinAltitude = this.s.e().requestMinAltitude();
        float requestMaxAltitude = this.s.e().requestMaxAltitude();
        if (!hji.c(requestMinAltitude, requestMaxAltitude)) {
            Float[] d = hji.d(this.s.d().requestAltitudeList());
            requestMaxAltitude = d[0].floatValue();
            requestMinAltitude = d[1].floatValue();
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 100);
        trackChartViewHolder.c(requestMinAltitude);
        trackChartViewHolder.e(requestMaxAltitude);
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 13.8f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addTrackAltitudeDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void ad() {
        if (this.s.ah()) {
            return;
        }
        this.o.addView(b(this.s.e(), this.s.d()));
    }

    private View bgt_(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        TrackLineChartHolder trackLineChartHolder;
        if (motionPathSimplify == null || motionPath == null || !motionPath.isValidHeartRateList()) {
            return null;
        }
        int b = ffw.b((List<HeartRateData>) motionPath.requestHeartRateList());
        int requestAvgHeartRate = motionPathSimplify.requestAvgHeartRate();
        if (motionPath.requestHeartRateList().size() > 1 && (b == 0 || requestAvgHeartRate == 0 || requestAvgHeartRate > b)) {
            return null;
        }
        hmh hmhVar = new hmh(this.e, 1, this.q);
        View buildView = hmhVar.buildView(hln.c(com.huawei.haf.application.BaseApplication.e()).d(this.s.e().requestSportType()).getHeartPostureType(), motionPathSimplify.requestHeartRateZoneType());
        HwHealthBaseCombinedChart acquireHeartRateChart = hmhVar.acquireHeartRateChart();
        if (acquireHeartRateChart == null || (trackLineChartHolder = TrackLineChartHolder.getInstance()) == null) {
            return null;
        }
        hmhVar.c(null, b, requestAvgHeartRate);
        trackLineChartHolder.setSportType(motionPathSimplify.requestSportType());
        trackLineChartHolder.addHeartRateDataLayer(acquireHeartRateChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        acquireHeartRateChart.setTouchEnabled(false);
        acquireHeartRateChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        acquireHeartRateChart.refresh();
        return buildView;
    }

    private TrackChartViewHolder b(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        TrackChartViewHolder trackChartViewHolder;
        if (!motionPath.isValidSpeedList()) {
            LogUtil.a("Track_TrackShareAllDataFragment", "PaceList is not valid");
            return null;
        }
        int requestSportType = motionPathSimplify.requestSportType();
        float requestAvgPace = motionPathSimplify.requestAvgPace();
        float a2 = ffw.a(motionPath.requestRealTimePaceList());
        if (requestSportType == 262 || requestSportType == 266) {
            requestAvgPace /= 10.0f;
            a2 /= 10.0f;
            if (UnitUtil.h()) {
                requestAvgPace = (float) UnitUtil.d(requestAvgPace, 2);
                a2 = (float) UnitUtil.d(a2, 2);
            }
            trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 6);
        } else if (requestSportType == 274) {
            a2 /= 2.0f;
            if (UnitUtil.h()) {
                requestAvgPace = (float) UnitUtil.d(requestAvgPace / 5.0f, 2);
                a2 = (float) UnitUtil.d(a2 / 5.0f, 2);
            }
            trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 17);
        } else if (hji.j(requestSportType)) {
            requestAvgPace /= 2.0f;
            a2 /= 2.0f;
            trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 26);
        } else {
            if (UnitUtil.h()) {
                float d = (float) UnitUtil.d(requestAvgPace, 3);
                a2 = (float) UnitUtil.d(a2, 3);
                requestAvgPace = d;
            }
            trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 9);
        }
        trackChartViewHolder.c(requestAvgPace);
        trackChartViewHolder.e(a2);
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return null;
        }
        trackLineChartHolder.setSportType(motionPathSimplify.requestSportType());
        trackLineChartHolder.addTrackRealTimePaceDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        if (requestSportType == 264 || requestSportType == 258 || requestSportType == 280) {
            LogUtil.a("Track_TrackShareAllDataFragment", "add pace chart");
            trackChartViewHolder.a();
            trackChartViewHolder.blJ_(new PaceIntervalShare(this.e, motionPath, motionPathSimplify));
        }
        return trackChartViewHolder;
    }

    private void u() {
        if (this.s.af()) {
            return;
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 5);
        Map<String, Integer> requestSportData = this.s.e().requestSportData();
        if (requestSportData == null) {
            LogUtil.a("Track_TrackShareAllDataFragment", "drawPullFreqView sportData is null");
            return;
        }
        if (requestSportData.containsKey("swim_pull_freq") && requestSportData.get("swim_pull_freq") != null) {
            trackChartViewHolder.c(requestSportData.get("swim_pull_freq").intValue());
        } else {
            LogUtil.a("Track_TrackShareAllDataFragment", "stroke rate is null");
            trackChartViewHolder.c(0.0f);
        }
        trackChartViewHolder.e(ffw.j(this.s.d().requestPullFreqList()));
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addTrackPullFreqDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void ae() {
        if (this.s.as()) {
            return;
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 4);
        Map<String, Integer> requestSportData = this.s.e().requestSportData();
        if (requestSportData != null && requestSportData.get(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF) != null) {
            trackChartViewHolder.c(requestSportData.get(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF).intValue());
        } else {
            trackChartViewHolder.c(0.0f);
            LogUtil.a("Track_TrackShareAllDataFragment", "sportData or avgswolf is null");
        }
        trackChartViewHolder.e(ffw.g(this.s.d().requestSwolfList()));
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addTrackSwolfDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void ac() {
        if (this.s.an()) {
            return;
        }
        if (!this.s.y()) {
            i();
            b(SportDetailChartDataType.GROUND_CONTACT_TIME);
        }
        if (!this.s.ad()) {
            f();
        }
        if (!this.s.ab()) {
            m();
            b(SportDetailChartDataType.HANG_TIME);
        }
        if (!this.s.x()) {
            j();
            b(SportDetailChartDataType.GROUND_HANG_TIME_RATE);
        }
        if (!this.s.aq()) {
            am();
        }
        if (!this.s.au()) {
            an();
        }
        if (!this.s.ac()) {
            e();
        }
        if (this.s.v()) {
            return;
        }
        h();
    }

    private void ap() {
        JudgeRootBean judgeRootBean = (JudgeRootBean) HiJsonUtil.e(hjt.e(BaseApplication.getContext()), JudgeRootBean.class);
        if (judgeRootBean == null) {
            return;
        }
        List<PostureJudgeBean> judge = judgeRootBean.getJudge();
        this.h = judge;
        if (judge == null) {
            LogUtil.b("Track_TrackShareAllDataFragment", "mPostureJudgeBeanList is null");
        } else {
            LogUtil.a("Track_TrackShareAllDataFragment", "mPostureJudgeBeanList.toString", judge.toString());
        }
    }

    private void i() {
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 7);
        ArrayList<ffs> requestRunningPostureList = this.s.d().requestRunningPostureList();
        trackChartViewHolder.c(this.s.e().requestAvgGroundContactTime());
        trackChartViewHolder.e(ffw.k(requestRunningPostureList));
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addRunningPostureDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true).a(true), 7, this.s.e().requestAvgGroundContactTime());
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void b(SportDetailChartDataType sportDetailChartDataType) {
        if (koq.b(this.h) || this.s.l()) {
            LogUtil.h("Track_TrackShareAllDataFragment", "invalid posture table");
            return;
        }
        MotionPath d = this.s.d();
        hjs hjsVar = new hjs(BaseApplication.getContext(), sportDetailChartDataType, this.h, hjt.b(hjt.c(d, this.h.get(0)), sportDetailChartDataType, d.requestRunningPostureList(), this.h));
        float[] e = hjt.e(new HealthColumnSystem(this.e), true);
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.posture_table, (ViewGroup) null);
        hjsVar.b((HealthTableWidget) inflate.findViewById(R.id.pace_table_layout), e);
        this.o.addView(inflate);
    }

    private void m() {
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 18);
        ArrayList<ffs> requestRunningPostureList = this.s.d().requestRunningPostureList();
        trackChartViewHolder.c(this.s.e().requestAverageHangTime());
        trackChartViewHolder.e(ffw.h(requestRunningPostureList));
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addRunningPostureDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true).a(true), 16, this.s.e().requestAverageHangTime());
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void am() {
        ArrayList<ffs> requestRunningPostureList = this.s.d().requestRunningPostureList();
        float extendDataFloat = this.s.e().getExtendDataFloat("avg_v_osc");
        float m = ffw.m(requestRunningPostureList);
        if (UnitUtil.h()) {
            extendDataFloat = (float) UnitUtil.e(this.s.e().getExtendDataFloat("avg_v_osc"), 0);
            m = (float) UnitUtil.e(m, 0);
        }
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 23);
        trackChartViewHolder.c(extendDataFloat);
        trackChartViewHolder.e(m);
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addRunningPostureDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true).a(true), 18, this.s.e().getExtendDataFloat("avg_v_osc"));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void an() {
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 22);
        ArrayList<ffs> requestRunningPostureList = this.s.d().requestRunningPostureList();
        trackChartViewHolder.c(this.s.e().getExtendDataFloat("avg_v_s_r"));
        trackChartViewHolder.e(ffw.o(requestRunningPostureList));
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addRunningPostureDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true).a(true), 20, this.s.e().getExtendDataFloat("avg_v_s_r"));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void e() {
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 20);
        ArrayList<ffs> requestRunningPostureList = this.s.d().requestRunningPostureList();
        trackChartViewHolder.c(this.s.e().getExtendDataFloat("avg_i_p"));
        trackChartViewHolder.e(ffw.i(requestRunningPostureList));
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addRunningPostureDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true).a(true), 21, this.s.e().getExtendDataFloat("avg_i_p"));
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void h() {
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 21);
        float a2 = (float) UnitUtil.a(this.s.e().getExtendDataFloat("avg_gc_tb"), 1);
        trackChartViewHolder.c(a2);
        trackChartViewHolder.e(100.0f - a2);
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addRunningPostureDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true).a(true), 19, 50.0f);
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void j() {
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 19);
        ArrayList<ffs> requestRunningPostureList = this.s.d().requestRunningPostureList();
        trackChartViewHolder.c(this.s.e().requestGroundHangTimeRate());
        trackChartViewHolder.e(ffw.n(requestRunningPostureList));
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addRunningPostureDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true).a(true), 17, this.s.e().requestGroundHangTimeRate());
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void f() {
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.e, this.q, true, 8);
        ArrayList<ffs> requestRunningPostureList = this.s.d().requestRunningPostureList();
        trackChartViewHolder.c(this.s.e().requestAvgGroundImpactAcceleration());
        trackChartViewHolder.e(ffw.f(requestRunningPostureList));
        trackChartViewHolder.setPadding(-16, nsn.c(this.e, 12.0f), -16, nsn.c(this.e, 16.0f));
        HwHealthBaseCombinedChart combinedChart = trackChartViewHolder.getCombinedChart();
        TrackLineChartHolder trackLineChartHolder = TrackLineChartHolder.getInstance();
        if (trackLineChartHolder == null) {
            return;
        }
        trackLineChartHolder.addRunningPostureDataLayer(combinedChart, new TrackLineChartHolder.b().c(this.q == 100).b(true), 8);
        combinedChart.setTouchEnabled(false);
        combinedChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        combinedChart.refresh();
        this.o.addView(trackChartViewHolder);
    }

    private void a(TrackShareDetailMapAndDetail trackShareDetailMapAndDetail) {
        hjw hjwVar = this.s;
        if (hjwVar != null && hjwVar.d() != null && !this.s.an()) {
            int requestAvgGroundContactTime = this.s.e().requestAvgGroundContactTime();
            int requestAvgGroundImpactAcceleration = this.s.e().requestAvgGroundImpactAcceleration();
            int requestAvgEversionExcursion = this.s.e().requestAvgEversionExcursion();
            int requestAvgSwingAngle = this.s.e().requestAvgSwingAngle();
            float requestAvgPace = this.s.e().requestAvgPace();
            float f = ((double) Math.abs(requestAvgPace)) > 1.0E-6d ? 3600.0f / requestAvgPace : 0.0f;
            int requestAvgForeFootStrikePattern = this.s.e().requestAvgForeFootStrikePattern();
            int requestAvgWholeFootStrikePattern = this.s.e().requestAvgWholeFootStrikePattern();
            int requestAvgHindFootStrikePattern = this.s.e().requestAvgHindFootStrikePattern();
            double d = requestAvgForeFootStrikePattern + requestAvgWholeFootStrikePattern + requestAvgHindFootStrikePattern;
            int floor = (int) Math.floor((requestAvgWholeFootStrikePattern / d) * 100.0d);
            int floor2 = (int) Math.floor((requestAvgHindFootStrikePattern / d) * 100.0d);
            int i = (100 - floor) - floor2;
            if (i < 0) {
                i = 0;
            }
            hlp hlpVar = new hlp(f, requestAvgGroundContactTime, requestAvgGroundImpactAcceleration, requestAvgSwingAngle, requestAvgEversionExcursion, i, floor, floor2, this.s.e().getExtendDataFloat("avg_v_i_r"), this.s.e().getExtendDataFloat("avg_i_p"), this.s.e().getExtendDataFloat("avg_v_osc"), (float) UnitUtil.a(this.s.e().getExtendDataFloat("avg_gc_tb"), 1), this.s.e().getExtendDataFloat("avg_v_s_r"));
            hlpVar.d((int) d);
            trackShareDetailMapAndDetail.e(hlpVar, this.h);
            return;
        }
        trackShareDetailMapAndDetail.b();
    }

    private void c(TrackShareDetailMapAndDetail trackShareDetailMapAndDetail) {
        if (this.s.e().requestSportType() == 266 || this.s.e().requestSportType() == 262) {
            boolean f = f(trackShareDetailMapAndDetail);
            boolean g = g(trackShareDetailMapAndDetail);
            boolean j = j(trackShareDetailMapAndDetail);
            if (f || g || j) {
                trackShareDetailMapAndDetail.i();
                return;
            } else {
                trackShareDetailMapAndDetail.a();
                return;
            }
        }
        trackShareDetailMapAndDetail.a();
    }

    private boolean f(TrackShareDetailMapAndDetail trackShareDetailMapAndDetail) {
        String quantityString;
        float requestAvgPace = this.s.e().requestAvgPace();
        if (requestAvgPace > 0.0f) {
            String f = hji.f(requestAvgPace);
            if (UnitUtil.h()) {
                quantityString = this.m.getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
            } else {
                quantityString = this.m.getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 100, 100);
            }
            trackShareDetailMapAndDetail.d(f, quantityString);
            return true;
        }
        trackShareDetailMapAndDetail.f();
        return false;
    }

    private boolean g(TrackShareDetailMapAndDetail trackShareDetailMapAndDetail) {
        if (this.s.e().requestSportData() == null || this.s.e().requestSportData().get("swim_pull_freq") == null) {
            trackShareDetailMapAndDetail.e();
            return false;
        }
        int intValue = this.s.e().requestSportData().get("swim_pull_freq").intValue();
        if (intValue <= 0) {
            trackShareDetailMapAndDetail.e();
            return false;
        }
        trackShareDetailMapAndDetail.c(UnitUtil.e(intValue, 1, 0), this.m.getQuantityString(R.plurals._2130903224_res_0x7f0300b8, intValue));
        return true;
    }

    private boolean j(TrackShareDetailMapAndDetail trackShareDetailMapAndDetail) {
        if (this.s.e().requestSportData() == null || this.s.e().requestSportData().get(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF) == null) {
            trackShareDetailMapAndDetail.g();
            return false;
        }
        int intValue = this.s.e().requestSportData().get(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF).intValue();
        if (intValue <= 0) {
            trackShareDetailMapAndDetail.g();
            return false;
        }
        trackShareDetailMapAndDetail.b(UnitUtil.e(intValue, 1, 0));
        return true;
    }

    public void d(ShareInitCallback shareInitCallback) {
        this.l = shareInitCallback;
    }
}
