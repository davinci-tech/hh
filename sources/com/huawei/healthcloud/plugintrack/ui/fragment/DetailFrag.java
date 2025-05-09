package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.health.sport.utils.RunPopularRoutesUtil;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.model.JudgeRootBean;
import com.huawei.healthcloud.plugintrack.model.PostureJudgeBean;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.healthcloud.plugintrack.model.TrackLineChartHolderImpl;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag;
import com.huawei.healthcloud.plugintrack.ui.fragmentutils.TrackDetailItemDrawer;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.AverageHangTimeAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.EversionExcursionAngleAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundContactTimeAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundHangTimeRateAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundImpactAccelerationAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundImpactLoadRateAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundShockPeakAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundTouchdownBalanceAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundVerticalAmplitudeAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundVerticalAmplitudeRatioAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.StrikePatternAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.SwingAngleAdvice;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.healthcloud.plugintrack.ui.view.BasketballScoreView;
import com.huawei.healthcloud.plugintrack.ui.view.DetailItemContainer;
import com.huawei.healthcloud.plugintrack.ui.view.JumpDataView;
import com.huawei.healthcloud.plugintrack.ui.view.RunRouteView;
import com.huawei.healthcloud.plugintrack.ui.view.ShowProgress;
import com.huawei.healthcloud.plugintrack.ui.view.SportDetailItem;
import com.huawei.healthcloud.plugintrack.ui.view.sharegroup.SpeedPercentView;
import com.huawei.healthcloud.plugintrack.ui.viewholder.CourseActionViewHolder;
import com.huawei.healthcloud.plugintrack.ui.viewholder.TrackChartViewHolder;
import com.huawei.healthcloud.plugintrack.util.HotTrackDrawCustomTarget;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.BasketballAdvice;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.view.TotalDataRectView;
import com.huawei.ui.commonui.view.trackview.TrackLineChartHolder;
import com.huawei.up.model.UserInfomation;
import defpackage.caj;
import defpackage.enc;
import defpackage.enf;
import defpackage.enj;
import defpackage.enm;
import defpackage.ffs;
import defpackage.ffw;
import defpackage.gnm;
import defpackage.gvv;
import defpackage.gwg;
import defpackage.gze;
import defpackage.hix;
import defpackage.hji;
import defpackage.hjr;
import defpackage.hjt;
import defpackage.hkb;
import defpackage.hkd;
import defpackage.hln;
import defpackage.hpo;
import defpackage.hpu;
import defpackage.ixt;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nmj;
import defpackage.nrh;
import defpackage.nrr;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.nsj;
import defpackage.nsk;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class DetailFrag extends SportResultBaseFragment implements View.OnClickListener {
    private LinearLayout aa;
    private HealthDivider ab;
    private LinearLayout ac;
    private LinearLayout ad;
    private HealthDivider ae;
    private LinearLayout af;
    private HealthDivider ag;
    private HealthDivider ah;
    private LinearLayout ai;
    private String aj;
    private JumpDataView ak;
    private boolean am;
    private LinearLayout an;
    private LinearLayout ao;
    private LinearLayout ap;
    private long aq;
    private LinearLayout ar;
    private LinearLayout as;
    private HealthTextView at;
    private List<PostureJudgeBean> au;
    private TotalDataRectView av;
    private RunRouteView aw;
    private HealthTextView az;
    private LinearLayout ba;
    private Map<String, Integer> bb;
    private LinearLayout bc;
    private LinearLayout bd;
    private LinearLayout be;
    private LinearLayout bf;
    private LinearLayout bg;
    private HealthTextView bh;
    private HealthTextView bj;
    private LinearLayout bk;
    private HealthTextView bl;
    private LinearLayout bm;
    private HealthTextView bn;
    private HealthTextView bo;
    private HealthTextView bp;
    private HealthTextView bq;
    private ShowProgress br;
    private HealthTextView bt;
    private ShowProgress bv;
    private ShowProgress bw;
    private HealthTextView bx;
    private HealthTextView by;
    private UserProfileMgrApi bz;
    private ImageView ca;
    private hkd cb;
    private HealthTextView k;
    private HealthTextView m;
    private UserInfomation n;
    private BasketballScoreView q;
    private HealthTextView r;
    private LinearLayout u;
    private LinearLayout w;
    private LinearLayout x;
    private LinearLayout y;
    private HealthDivider z;
    private static final int f = Color.parseColor("#D94142");
    private static final int g = Color.parseColor("#FF7500");
    private static final int h = Color.parseColor("#FF9800");
    private static final int d = Color.parseColor("#FFC51A");
    private static final int j = Color.parseColor("#64BB5C");
    private static final int i = Color.parseColor("#4CBABF");
    private static final int e = Color.parseColor("#00AAEE");
    private List<Integer> o = new ArrayList(4);
    private Map<SportDetailChartDataType, hix> t = new HashMap(20);
    private Resources ax = null;
    private TrackLineChartHolderImpl bu = null;
    private ArrayList<RunningPostureAdviceBase> ay = new ArrayList<>(16);
    private ArrayList<BasketballAdvice> l = new ArrayList<>(10);
    private DetailItemContainer s = null;
    private DetailItemContainer v = null;
    private TrackDetailItemDrawer bi = null;
    private int p = 0;
    private hkb bs = null;
    private HeartRateFrag al = null;

    private boolean d(int i2) {
        return i2 == 0;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("Track_DetailFrag", "onCreateView()");
        this.am = LanguageUtil.bc(BaseApplication.getContext());
        if (o()) {
            return null;
        }
        h();
        if (!k()) {
            return null;
        }
        be();
        this.bz = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        this.bu = new TrackLineChartHolderImpl(this.c.getApplicationContext());
        this.ax = getResources();
        this.b = layoutInflater.inflate(R.layout.track_show_detail_fragment, viewGroup, false);
        l();
        this.cb = new hkd(this.b, this.c);
        this.s = (DetailItemContainer) this.b.findViewById(R.id.track_detail_container);
        this.v = (DetailItemContainer) this.b.findViewById(R.id.track_golf_activity_container);
        this.az = (HealthTextView) this.b.findViewById(R.id.track_detail_showtime);
        v();
        x();
        au();
        an();
        BaseActivity.cancelLayoutById(this.bk, this.be);
        ar();
        this.aj = this.ax.getString(R.string._2130850262_res_0x7f0231d6);
        w();
        y();
        bed_((LinearLayout) this.b.findViewById(R.id.track_detail));
        return this.b;
    }

    private void bed_(LinearLayout linearLayout) {
        CourseActionViewHolder courseActionViewHolder = new CourseActionViewHolder(this.c);
        View bkB_ = courseActionViewHolder.bkB_(aw());
        boolean e2 = courseActionViewHolder.e(this.f3732a.e());
        if (aw() && e2) {
            bkB_ = bec_(bkB_);
            this.b.setBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        }
        if (SportSupportUtil.b(this.f3732a.e())) {
            linearLayout.addView(courseActionViewHolder.b(false), linearLayout.indexOfChild(this.b.findViewById(R.id.layout_marketing)));
        }
        linearLayout.addView(bkB_, linearLayout.indexOfChild(this.b.findViewById(R.id.layout_marketing)));
    }

    private boolean aw() {
        return bb() || bc();
    }

    private void l() {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.track_history_detail_data_layout, (ViewGroup) null);
        bej_(inflate);
        if (aw()) {
            inflate = bec_(inflate);
            this.b.setBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        }
        ((LinearLayout) this.b.findViewById(R.id.layout_history_detail_data)).addView(inflate, -1, -2);
    }

    private void bej_(View view) {
        if (bc()) {
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.track_detail_percent);
            int extendDataInt = this.f3732a.e().getExtendDataInt("rankAbsolute");
            if (extendDataInt > 0) {
                healthTextView.setVisibility(0);
                healthTextView.setText(this.c.getResources().getQuantityString(R.plurals._2130903417_res_0x7f030179, extendDataInt, UnitUtil.e(extendDataInt, 1, 0)));
                return;
            }
            int extendDataInt2 = this.f3732a.e().getExtendDataInt("rank");
            if (extendDataInt2 > 0) {
                healthTextView.setVisibility(0);
                healthTextView.setText(getString(R.string._2130846201_res_0x7f0221f9, UnitUtil.e(extendDataInt2, 2, 0)));
            }
        }
    }

    private void an() {
        if (!RunningRouteUtils.a(this.f3732a.e().requestSportType())) {
            LogUtil.a("Track_DetailFrag", "not support route track");
            return;
        }
        Map<String, String> requestExtendDataMap = this.f3732a.e().requestExtendDataMap();
        if (!hpu.c(requestExtendDataMap)) {
            LogUtil.a("Track_DetailFrag", "not manual punch track");
            bh();
        } else {
            e(requestExtendDataMap);
        }
    }

    private void e(Map<String, String> map) {
        final String str = map.get("hotPathId");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_DetailFrag", "hotPathId is null in initManualPunchCard");
            return;
        }
        final int e2 = hjr.e(this.f3732a.e());
        RunRouteView runRouteView = (RunRouteView) this.b.findViewById(R.id.layout_run_route_View);
        this.aw = runRouteView;
        d(runRouteView, str, 0, e2);
        this.aw.setHotPathName(map.get("hotPathName"));
        final String str2 = map.get("finishState");
        final long t = t();
        if (t != -1) {
            this.aw.setHotTrackParticipateNum(str2, (int) t);
        }
        hpu.d(str, new HotTrackDrawCustomTarget<Drawable>() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag.2
            @Override // com.huawei.healthcloud.plugintrack.util.HotTrackDrawCustomTarget
            public void onGetHotTrackDetailInfo(enc encVar) {
                if (DetailFrag.this.aw != null && encVar != null) {
                    DetailFrag.this.aw.e(true);
                    DetailFrag.this.aw.setVisibility(0);
                    int g2 = encVar.g();
                    if (g2 != e2) {
                        ReleaseLogUtil.d("Track_DetailFrag", "pathClassCloud is: ", Integer.valueOf(g2));
                        DetailFrag detailFrag = DetailFrag.this;
                        detailFrag.d(detailFrag.aw, str, 0, g2);
                    }
                    if (t == -1) {
                        long j2 = encVar.j();
                        LogUtil.a("Track_DetailFrag", "participateNum is ", Long.valueOf(encVar.j()));
                        DetailFrag.this.aw.setHotTrackParticipateNum(str2, (int) j2);
                        if (encVar.p() == 6) {
                            LogUtil.a("Track_DetailFrag", "path is offline");
                            DetailFrag.this.aw.setRouteStateShow();
                            DetailFrag detailFrag2 = DetailFrag.this;
                            detailFrag2.d(detailFrag2.aw, str, 6, g2);
                            return;
                        }
                        return;
                    }
                    return;
                }
                ReleaseLogUtil.c("Track_DetailFrag", "mRunRouteView is null in onGetHotTrackDetailInfo");
            }

            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: bem_, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                LogUtil.a("Track_DetailFrag", "onResourceReady");
                if (DetailFrag.this.aw != null) {
                    DetailFrag.this.aw.setHotPathDrawable(drawable);
                }
            }

            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
                LogUtil.h("Track_DetailFrag", "onLoadCleared");
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable drawable) {
                super.onLoadFailed(drawable);
                LogUtil.h("Track_DetailFrag", "onLoadFailed");
            }
        });
    }

    /* renamed from: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag$5, reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        AnonymousClass5() {
        }

        @Override // java.lang.Runnable
        public void run() {
            gze gzeVar = new gze(DetailFrag.this.getActivity(), DetailFrag.this.f3732a.e(), 2);
            enj a2 = gzeVar.a();
            if (a2 == null || a2.a() == null) {
                LogUtil.a("Track_DetailFrag", "not punch track");
                return;
            }
            gzeVar.c();
            final enf a3 = a2.a();
            HandlerExecutor.a(new Runnable() { // from class: hgw
                @Override // java.lang.Runnable
                public final void run() {
                    DetailFrag.AnonymousClass5.this.c(a3);
                }
            });
        }

        public /* synthetic */ void c(enf enfVar) {
            DetailFrag.this.d(enfVar);
        }
    }

    private void bh() {
        ThreadPoolManager.d().execute(new AnonymousClass5());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(enf enfVar) {
        if (this.b == null) {
            ReleaseLogUtil.d("Track_DetailFrag", "mRootView is null in initRouteView");
            return;
        }
        RunRouteView runRouteView = (RunRouteView) this.b.findViewById(R.id.layout_run_route_View);
        if (runRouteView == null) {
            ReleaseLogUtil.d("Track_DetailFrag", "mRootView is null in initRouteView");
            return;
        }
        long d2 = enfVar.d();
        String i2 = enfVar.i();
        String h2 = enfVar.h();
        int o = enfVar.o();
        int b = enfVar.b();
        enm f2 = enfVar.f();
        String b2 = f2 != null ? f2.b() : "";
        runRouteView.e(true);
        runRouteView.setVisibility(0);
        runRouteView.setHotPathName(h2);
        runRouteView.setHotTrackParticipateNum("", (int) d2);
        d(runRouteView, i2, o, b);
        if (o == 6) {
            runRouteView.setRouteStateShow();
        }
        runRouteView.setHotPathUrl(b2);
    }

    private long t() {
        FragmentActivity activity = getActivity();
        if (activity instanceof TrackDetailActivity) {
            return ((TrackDetailActivity) activity).c();
        }
        return -1L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(RunRouteView runRouteView, final String str, final int i2, final int i3) {
        runRouteView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.a(500)) {
                    LogUtil.a("Track_DetailFrag", "view click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                } else if (nsn.ae(DetailFrag.this.c)) {
                    LogUtil.a("Track_DetailFrag", "Pad not support Popular Routes");
                    ViewClickInstrumentation.clickOnView(view);
                } else if (i2 == 6) {
                    nrh.b(DetailFrag.this.c, R.string._2130847353_res_0x7f022679);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    RunPopularRoutesUtil.e(DetailFrag.this.c, 3, new RunPopularRoutesUtil.DialogCallBack() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag.3.4
                        @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
                        public void goNext() {
                            LogUtil.a("Track_DetailFrag", "Start jump To ClockingRankActivity");
                            Intent intent = new Intent(DetailFrag.this.c, (Class<?>) ClockingRankActivity.class);
                            intent.putExtra("PATH_ID", str);
                            intent.putExtra("pathClass", i3);
                            gnm.aPB_(DetailFrag.this.c, intent);
                        }

                        @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
                        public void notGoNext() {
                            LogUtil.a("Track_DetailFrag", "Cancle start jump To ClockingRankActivity");
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    private void au() {
        ((HealthTextView) this.b.findViewById(R.id.track_detail_showtime)).setText(UnitUtil.a(new Date(), 21));
        this.n = this.bz.getUserInfo();
        this.bm = (LinearLayout) this.b.findViewById(R.id.track_detail_show_wear);
        this.bg = (LinearLayout) this.b.findViewById(R.id.track_detail_show_swim);
        this.y = (LinearLayout) this.b.findViewById(R.id.track_detail_show_golf_stat_title);
        this.w = (LinearLayout) this.b.findViewById(R.id.track_detail_golf_stat_title);
        this.bk = (LinearLayout) this.b.findViewById(R.id.track_detail_show_wear_content);
        this.be = (LinearLayout) this.b.findViewById(R.id.track_detail_show_swim_content);
    }

    private void v() {
        this.bl = (HealthTextView) this.b.findViewById(R.id.track_detail_xg);
        this.bv = (ShowProgress) this.b.findViewById(R.id.track_detail_sp_xg);
        this.k = (HealthTextView) this.b.findViewById(R.id.track_detail_anaerobic_title);
        this.by = (HealthTextView) this.b.findViewById(R.id.training_effect_show);
        this.at = (HealthTextView) this.b.findViewById(R.id.track_recovery_time_msg);
    }

    private void y() {
        if (LanguageUtil.b(this.c)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.bq.getLayoutParams();
            layoutParams.removeRule(4);
            layoutParams.addRule(8, R.id.track_detail_show_speed);
            layoutParams.addRule(6, R.id.track_detail_show_speed);
            this.bq.setLayoutParams(layoutParams);
            this.bq.setGravity(17);
        }
    }

    private void be() {
        if (k()) {
            this.bb = this.f3732a.e().requestSportData();
            int extendDataInt = this.f3732a.e().getExtendDataInt("eteAlgoKey", 0);
            this.p = extendDataInt;
            LogUtil.a("Track_DetailFrag", "detailFrag ete algo = ", Integer.valueOf(extendDataInt));
            Map<String, Integer> map = this.bb;
            if (map != null) {
                if (map.containsKey("anaerobic_exercise_etraining_effect")) {
                    LogUtil.a("Track_DetailFrag", "ANAEROBIC", this.bb.get("anaerobic_exercise_etraining_effect"));
                }
                if (this.bb.containsKey("etraining_effect")) {
                    LogUtil.a("Track_DetailFrag", "AEROBIC", this.bb.get("etraining_effect"));
                }
                if (this.bb.containsKey("max_met")) {
                    LogUtil.a("Track_DetailFrag", "MAX_MET", this.bb.get("max_met"));
                }
                if (this.bb.containsKey("recovery_time")) {
                    LogUtil.a("Track_DetailFrag", "RECOVERY_TIME", this.bb.get("recovery_time"));
                }
            }
            this.f3732a.e().printReleaseSimplifyLog("Track_DetailFrag");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        DetailItemContainer detailItemContainer;
        super.onConfigurationChanged(configuration);
        if (this.bi != null && (detailItemContainer = this.s) != null && this.v != null) {
            detailItemContainer.removeAllViews();
            this.v.removeAllViews();
            int dimensionPixelOffset = this.ax.getDimensionPixelOffset(R.dimen._2131364635_res_0x7f0a0b1b);
            this.bi.e(this.s, false, 0, dimensionPixelOffset);
            this.bi.e(this.v, false, 3, dimensionPixelOffset);
        }
        LinearLayout linearLayout = this.an;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
            bee_(this.an, this.f3732a.e(), this.ax.getDimensionPixelOffset(R.dimen._2131364635_res_0x7f0a0b1b));
        }
        j();
    }

    private void j() {
        if (nsn.l()) {
            LogUtil.a("Track_DetailFrag", "adaptMateX, isTahitiModel: ", Boolean.valueOf(nsn.ag(getContext())));
            b((HealthTextView) this.ap.findViewById(R.id.track_detail_posture_item_value), (HealthTextView) this.ap.findViewById(R.id.track_detail_posture_item_name));
            b((HealthTextView) this.x.findViewById(R.id.track_detail_posture_item_value), (HealthTextView) this.x.findViewById(R.id.track_detail_posture_item_name));
            b((HealthTextView) this.aa.findViewById(R.id.track_detail_posture_item_value), (HealthTextView) this.aa.findViewById(R.id.track_detail_posture_item_name));
            b((HealthTextView) this.af.findViewById(R.id.track_detail_posture_item_value), (HealthTextView) this.af.findViewById(R.id.track_detail_posture_item_name));
            b((HealthTextView) this.ai.findViewById(R.id.track_detail_posture_item_value), (HealthTextView) this.ai.findViewById(R.id.track_detail_posture_item_name));
            b((HealthTextView) this.ad.findViewById(R.id.track_detail_posture_item_value), (HealthTextView) this.ad.findViewById(R.id.track_detail_posture_item_name));
            n();
            r();
        }
    }

    private void n() {
        b((HealthTextView) this.ac.findViewById(R.id.track_detail_posture_item_value), (HealthTextView) this.ac.findViewById(R.id.track_detail_posture_item_name));
        b((HealthTextView) this.u.findViewById(R.id.track_detail_posture_item_value), (HealthTextView) this.u.findViewById(R.id.track_detail_posture_item_name));
        b((HealthTextView) this.bd.findViewById(R.id.track_detail_posture_item_value), (HealthTextView) this.bd.findViewById(R.id.track_detail_posture_item_name));
        b((HealthTextView) this.ar.findViewById(R.id.track_detail_posture_item_value), (HealthTextView) this.ar.findViewById(R.id.track_detail_posture_item_name));
        b((HealthTextView) this.ao.findViewById(R.id.track_detail_posture_item_value), (HealthTextView) this.ao.findViewById(R.id.track_detail_posture_item_name));
        bg();
    }

    private void r() {
        bea_((LinearLayout) this.b.findViewById(R.id.track_detail_show_running_posture_title));
        bea_((LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_swing_angle));
        bea_((LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_eversion_excursion));
        bea_((LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_ground_impact_acceleration));
        bea_((LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_ground_contact_time));
        bea_((LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_hang_time));
        bea_((LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_ground_hang_time_rate));
        LinearLayout linearLayout = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_strike_pattern);
        bea_((LinearLayout) linearLayout.findViewById(R.id.track_detail_posture_item_title_layout));
        bea_((LinearLayout) linearLayout.findViewById(R.id.track_posture_proportionbar));
    }

    private void beb_(boolean z, LinearLayout linearLayout) {
        if (linearLayout == null) {
            return;
        }
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.track_detail_posture_item_level);
        if (z) {
            healthTextView.setMaxWidth(nsn.c(this.c, 242.0f));
        } else {
            healthTextView.setMaxWidth(nsn.c(this.c, 86.0f));
        }
    }

    private void bg() {
        boolean ag = nsn.ag(getContext());
        beb_(ag, this.ap);
        beb_(ag, this.x);
        beb_(ag, this.aa);
        beb_(ag, this.af);
        beb_(ag, this.ai);
        beb_(ag, this.ad);
        beb_(ag, this.ac);
        beb_(ag, this.u);
        beb_(ag, this.bd);
        beb_(ag, this.ar);
        beb_(ag, this.ao);
    }

    private void b(HealthTextView healthTextView, HealthTextView healthTextView2) {
        int i2;
        if (healthTextView2 != null) {
            i2 = healthTextView2.getWidth();
        } else {
            ReleaseLogUtil.c("Track_DetailFrag", "itemNameView is null !");
            i2 = 0;
        }
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.c, 0);
        if (healthTextView != null && (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
            if (nsn.ag(this.c)) {
                int d2 = (((int) healthColumnSystem.d(2)) + nrr.b(this.c)) - i2;
                if (d2 < 0) {
                    d2 = nsn.c(this.c, 12.0f);
                }
                layoutParams.setMarginStart(d2);
            } else {
                layoutParams.setMarginStart(nsn.c(this.c, 12.0f));
            }
            healthTextView.setLayoutParams(layoutParams);
            return;
        }
        ReleaseLogUtil.c("Track_DetailFrag", "itemValueView is null or layoutParams isn't the instance of LinearLauout.LayoutParams");
    }

    private void bea_(LinearLayout linearLayout) {
        if (linearLayout != null && (linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMarginStart(this.ax.getDimensionPixelOffset(R.dimen._2131364635_res_0x7f0a0b1b));
            layoutParams.setMarginEnd(this.ax.getDimensionPixelOffset(R.dimen._2131364634_res_0x7f0a0b1a));
            linearLayout.setLayoutParams(layoutParams);
            return;
        }
        ReleaseLogUtil.c("Track_DetailFrag", "linearLayout is null or layoutParams isn't the instance of LinearLauout.LayoutParams");
    }

    private void x() {
        this.m = (HealthTextView) this.b.findViewById(R.id.track_detail_aerobic_title);
        this.bh = (HealthTextView) this.b.findViewById(R.id.track_detail_anaerobic_xg);
        this.br = (ShowProgress) this.b.findViewById(R.id.track_detail_sp_anaerobic_xg);
        this.r = (HealthTextView) this.b.findViewById(R.id.sug_detail_ox);
        this.bx = (HealthTextView) this.b.findViewById(R.id.sug_detail_ox_unit);
        this.ca = (ImageView) this.b.findViewById(R.id.track_detail_vo2max_arrow);
        this.bw = (ShowProgress) this.b.findViewById(R.id.track_detail_sp_ox);
        this.bj = (HealthTextView) this.b.findViewById(R.id.track_detail_ritire_duration);
        this.bo = (HealthTextView) this.b.findViewById(R.id.track_detail_ritire_time);
        this.bp = (HealthTextView) this.b.findViewById(R.id.track_detail_show_speed);
        this.bq = (HealthTextView) this.b.findViewById(R.id.track_detail_show_speed_unit);
        this.bn = (HealthTextView) this.b.findViewById(R.id.smart_coach_after_sport_data_interpretation);
        this.ba = (LinearLayout) this.b.findViewById(R.id.layout_smart_coach_sport_data_interpretation);
        this.bt = (HealthTextView) this.b.findViewById(R.id.track_ox_msg);
    }

    private void ar() {
        this.bc = (LinearLayout) this.b.findViewById(R.id.track_show_running_posture);
        BaseActivity.cancelLayoutById((LinearLayout) this.b.findViewById(R.id.track_show_running_posture_content));
        ((ImageView) this.b.findViewById(R.id.posture_information_icon)).setOnClickListener(new View.OnClickListener() { // from class: hgu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DetailFrag.beh_(view);
            }
        });
        LinearLayout linearLayout = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_ground_contact_time);
        this.ap = linearLayout;
        linearLayout.setOnClickListener(this);
        LinearLayout linearLayout2 = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_hang_time);
        this.ar = linearLayout2;
        linearLayout2.setOnClickListener(this);
        LinearLayout linearLayout3 = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_ground_hang_time_rate);
        this.ao = linearLayout3;
        linearLayout3.setOnClickListener(this);
        LinearLayout linearLayout4 = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_ground_impact_acceleration);
        this.x = linearLayout4;
        linearLayout4.setOnClickListener(this);
        LinearLayout linearLayout5 = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_ground_touchdown_balance);
        this.aa = linearLayout5;
        linearLayout5.setOnClickListener(this);
        this.ae = (HealthDivider) this.b.findViewById(R.id.track_detail_running_posture_ground_touchdown_balance_line);
        LinearLayout linearLayout6 = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_ground_vertical_amplitude);
        this.af = linearLayout6;
        linearLayout6.setOnClickListener(this);
        this.ag = (HealthDivider) this.b.findViewById(R.id.track_detail_running_posture_ground_vertical_amplitude_line);
        LinearLayout linearLayout7 = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_ground_vertical_amplitude_ratio);
        this.ai = linearLayout7;
        linearLayout7.setOnClickListener(this);
        this.ah = (HealthDivider) this.b.findViewById(R.id.track_detail_running_posture_ground_vertical_amplitude_ratio_line);
        LinearLayout linearLayout8 = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_ground_shock_peak);
        this.ad = linearLayout8;
        linearLayout8.setOnClickListener(this);
        this.z = (HealthDivider) this.b.findViewById(R.id.track_detail_running_posture_ground_shock_peak_line);
        LinearLayout linearLayout9 = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_ground_impact_load_rate);
        this.ac = linearLayout9;
        linearLayout9.setOnClickListener(this);
        this.ab = (HealthDivider) this.b.findViewById(R.id.track_detail_running_posture_ground_impact_load_rate_line);
        LinearLayout linearLayout10 = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_eversion_excursion);
        this.u = linearLayout10;
        linearLayout10.setOnClickListener(this);
        LinearLayout linearLayout11 = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_swing_angle);
        this.bd = linearLayout11;
        linearLayout11.setOnClickListener(this);
        LinearLayout linearLayout12 = (LinearLayout) this.b.findViewById(R.id.track_detail_running_posture_strike_pattern);
        this.bf = linearLayout12;
        linearLayout12.setOnClickListener(this);
        ak();
        bg();
    }

    public static /* synthetic */ void beh_(View view) {
        caj.a().a("FOOT_DATA");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void w() {
        ViewStub viewStub;
        if (k()) {
            this.bb = this.f3732a.e().requestSportData();
            if (LanguageUtil.bt(this.c)) {
                this.az.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH).format(Long.valueOf(this.f3732a.e().requestStartTime())));
            } else {
                this.az.setText(nsj.c(this.c, this.f3732a.e().requestStartTime(), 21));
            }
            aa();
            TrackDetailItemDrawer trackDetailItemDrawer = new TrackDetailItemDrawer(this.f3732a.e(), this.f3732a.d(), this.c, 100);
            this.bi = trackDetailItemDrawer;
            trackDetailItemDrawer.e(this.s, false, 0, this.ax.getDimensionPixelOffset(R.dimen._2131364635_res_0x7f0a0b1b));
            this.bi.e(this.v, false, 3, this.ax.getDimensionPixelOffset(R.dimen._2131364635_res_0x7f0a0b1b));
            if (this.f3732a.e().requestTrackType() == 1 && (viewStub = (ViewStub) this.b.findViewById(R.id.viewstub_track_detail_auto_track)) != null) {
                viewStub.inflate().setVisibility(0);
            }
            bu();
            bd();
            bm();
            bk();
            bt();
            bo();
            bn();
            br();
            f();
        }
    }

    private void br() {
        ViewStub viewStub = (ViewStub) this.b.findViewById(R.id.track_detail_lactic_data);
        final MotionPathSimplify e2 = this.f3732a.e();
        if (e2 != null) {
            Map<String, String> requestExtendDataMap = e2.requestExtendDataMap();
            String str = requestExtendDataMap.containsKey(HwExerciseConstants.JSON_NAME_RECORD_FLAG) ? requestExtendDataMap.get(HwExerciseConstants.JSON_NAME_RECORD_FLAG) : "";
            LogUtil.c("Track_DetailFrag", "showLacticData() recordFlag = ", str);
            if (TextUtils.isEmpty(str) || !"7".equals(str)) {
                return;
            }
            View inflate = viewStub.inflate();
            if (LanguageUtil.bc(this.c)) {
                ((ImageView) inflate.findViewById(R.id.track_detail_lactic_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            }
            if (LanguageUtil.f(this.c)) {
                ((TextView) inflate.findViewById(R.id.track_detail_lactic_item_title)).setTextSize(0, nsn.c(this.c, 12.0f));
            }
            LinearLayout linearLayout = (LinearLayout) this.b.findViewById(R.id.track_detail_lactic_data_items);
            this.an = linearLayout;
            bee_(linearLayout, e2, this.ax.getDimensionPixelOffset(R.dimen._2131364635_res_0x7f0a0b1b));
            inflate.setOnClickListener(new View.OnClickListener() { // from class: hgv
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DetailFrag.this.bel_(e2, view);
                }
            });
        }
    }

    public /* synthetic */ void bel_(MotionPathSimplify motionPathSimplify, View view) {
        LogUtil.a("Track_DetailFrag", "click lactate threshold");
        if (nsn.o()) {
            LogUtil.h("Track_DetailFrag", "showLacticData isFastClick.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClassName(this.c, "com.huawei.ui.main.stories.health.activity.healthdata.LacticActivity");
            intent.putExtra("key_bundle_health_last_data_time", motionPathSimplify.requestStartTime());
            this.c.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Track_DetailFrag", "showLacticData exception.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportResultBaseFragment
    protected int c() {
        int requestSportType = this.f3732a.e().requestSportType();
        if (requestSportType == 258) {
            return 4155;
        }
        return requestSportType == 264 ? 4176 : -1;
    }

    private void bee_(LinearLayout linearLayout, MotionPathSimplify motionPathSimplify, int i2) {
        String string;
        SportDetailItem.b bVar = new SportDetailItem.b(this.ax.getDrawable(R.drawable.ic_heart_rate), this.ax.getString(R.string._2130845160_res_0x7f021de8), getString(R.string._2130845321_res_0x7f021e89), "");
        SportDetailItem.b bVar2 = new SportDetailItem.b(this.ax.getDrawable(R.drawable._2131429729_res_0x7f0b0961), this.ax.getString(R.string._2130845161_res_0x7f021de9), getString(R.string._2130845321_res_0x7f021e89), "");
        int extendDataInt = motionPathSimplify.getExtendDataInt("lthrHr");
        if (extendDataInt > 0) {
            bVar.d(UnitUtil.e(extendDataInt, 1, 0));
            bVar.e(this.ax.getString(R.string.IDS_main_watch_heart_rate_unit_string));
        }
        int extendDataInt2 = motionPathSimplify.getExtendDataInt("lthrPace");
        if (extendDataInt2 > 0) {
            boolean h2 = UnitUtil.h();
            bVar2.d(gvv.a(h2 ? (float) UnitUtil.d(extendDataInt2, 3) : extendDataInt2));
            StringBuilder sb = new StringBuilder("/");
            if (h2) {
                string = this.ax.getString(R.string._2130844081_res_0x7f0219b1);
            } else {
                string = this.ax.getString(R.string._2130844082_res_0x7f0219b2);
            }
            sb.append(string);
            bVar2.e(sb.toString());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(bVar);
        arrayList.add(bVar2);
        new TrackDetailItemDrawer.TrackDetailItemDrawHelper(this.c).bhC_(linearLayout, i2, arrayList, false, this.ax.getColor(R.color._2131299236_res_0x7f090ba4));
    }

    private LinearLayout bec_(View view) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.c).inflate(R.layout.track_history_detail_card_layout, (ViewGroup) null);
        ((HealthCardView) linearLayout.findViewById(R.id.history_detail_card_view)).addView(view, -1, -2);
        return linearLayout;
    }

    private boolean bb() {
        return this.f3732a.e().requestSportType() == 283;
    }

    private boolean bc() {
        return this.f3732a.e().requestSportType() == 290;
    }

    private void bn() {
        LogUtil.a("Track_DetailFrag", "showRopeSkippingChart");
        if (m()) {
            MotionPathSimplify e2 = this.f3732a.e();
            MotionPath d2 = this.f3732a.d();
            if (bb() || bc()) {
                this.al = new HeartRateFrag();
                this.bs = new hkb(this.bu, this.f3732a);
                e(e2, d2);
            }
            if (bb()) {
                d(e2, d2);
                c(e2, d2);
            }
        }
    }

    private void e(final MotionPathSimplify motionPathSimplify, final MotionPath motionPath) {
        LogUtil.a("Track_DetailFrag", "showRopeSkippingHeartRateChart");
        if (this.f3732a.z()) {
            return;
        }
        this.bs.a(SportDetailChartDataType.HEART_RATE, motionPathSimplify, motionPath);
        this.b.findViewById(R.id.layout_ai_skipping_heartrate_card).setVisibility(0);
        hix hixVar = new hix();
        hixVar.bgE_(this.b, R.id.layout_ai_skipping_heartrate);
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.c, 100, false, 1);
        trackChartViewHolder.c(motionPathSimplify.requestAvgHeartRate());
        trackChartViewHolder.e(ffw.b((List<HeartRateData>) motionPath.requestHeartRateList()));
        hixVar.d(trackChartViewHolder.getCombinedChart());
        hixVar.d(trackChartViewHolder.d());
        hixVar.e().setLogEnabled(true);
        hixVar.bgB_().addView(trackChartViewHolder);
        hji.bgM_(0, trackChartViewHolder.blI_(), ObserveLabels.HEART_RATE);
        TrackLineChartHolder.setInstance(this.bu);
        d(nsn.cLh_(getActivity()), hixVar, "BASELINE_HEART_RATE", 0, motionPathSimplify.requestSportType());
        this.bu.addHeartRateDataLayer(hixVar.e());
        hixVar.e().setTouchEnabled(false);
        hixVar.e().setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        hixVar.e().refresh();
        this.t.put(SportDetailChartDataType.HEART_RATE, hixVar);
        this.al.bev_(this.b, this.c);
        HandlerExecutor.e(new Runnable() { // from class: hgx
            @Override // java.lang.Runnable
            public final void run() {
                DetailFrag.this.c(motionPath, motionPathSimplify);
            }
        });
    }

    public /* synthetic */ void c(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        this.al.c(motionPath.requestHeartRateList(), motionPathSimplify);
    }

    private void d(boolean z, hix hixVar, String str, int i2, int i3) {
        if (hixVar == null) {
            LogUtil.h("Track_DetailFrag", "processIconAndOnClickListener chartModel is null.");
            return;
        }
        if (z) {
            if (hixVar.d() != null) {
                hixVar.d().setVisibility(8);
            }
            if (hixVar.e() != null) {
                hixVar.e().setOnSingleTapConfirmedListener(null);
                return;
            }
            return;
        }
        if (hixVar.d() != null) {
            hixVar.d().setVisibility(0);
        }
        HeartRateFrag heartRateFrag = this.al;
        if (heartRateFrag != null) {
            heartRateFrag.e(hixVar, str, i2, this.c, i3);
        }
    }

    private void d(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        if (this.f3732a.ag()) {
            return;
        }
        this.bs.a(SportDetailChartDataType.HEART_RATE_RECOVERY, motionPathSimplify, motionPath);
        this.b.findViewById(R.id.layout_ai_skipping_recover_heartrate_card).setVisibility(0);
        hix hixVar = new hix();
        hixVar.bgE_(this.b, R.id.layout_ai_skipping_recover_heartrate);
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.c, 100, false, 124);
        hixVar.d(trackChartViewHolder.getCombinedChart());
        hixVar.a(trackChartViewHolder);
        hixVar.d(trackChartViewHolder.d());
        hixVar.e().setLogEnabled(true);
        hixVar.bgB_().addView(trackChartViewHolder);
        TrackLineChartHolder.setInstance(this.bu);
        trackChartViewHolder.d().setVisibility(8);
        hji.bgM_(0, trackChartViewHolder.blI_(), "RECOVERY_HEART_RATE");
        this.bu.addRecoverHeartRateLayer(hixVar.e());
        d(motionPath, hixVar);
        hixVar.e().setTouchEnabled(false);
        hixVar.e().setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        hji.e(SportDetailChartDataType.HEART_RATE_RECOVERY, hixVar, this.c, this.f3732a);
        hixVar.e().refresh();
    }

    private void d(MotionPath motionPath, hix hixVar) {
        int i2;
        int i3;
        List<HeartRateData> requestHeartRecoveryRateList = motionPath.requestHeartRecoveryRateList();
        if (requestHeartRecoveryRateList == null || requestHeartRecoveryRateList.size() <= 0) {
            return;
        }
        Iterator<HeartRateData> it = requestHeartRecoveryRateList.iterator();
        while (true) {
            i2 = 0;
            if (!it.hasNext()) {
                i3 = 0;
                break;
            }
            HeartRateData next = it.next();
            if (next.acquireHeartRate() > 0) {
                i3 = next.acquireHeartRate();
                break;
            }
        }
        int size = requestHeartRecoveryRateList.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            if (requestHeartRecoveryRateList.get(size).acquireHeartRate() > 0) {
                i2 = requestHeartRecoveryRateList.get(size).acquireHeartRate();
                break;
            }
            size--;
        }
        hixVar.a().c((i3 - i2) / 2.0f);
        hixVar.a().a(i3, i2);
    }

    private void c(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        LogUtil.a("Track_DetailFrag", "showRopeSkippingSpeedChart");
        if (this.f3732a.aj()) {
            return;
        }
        this.bs.a(SportDetailChartDataType.SKIPPING_SPEED, motionPathSimplify, motionPath);
        this.b.findViewById(R.id.layout_ai_skipping_card).setVisibility(0);
        hix hixVar = new hix();
        hixVar.bgE_(this.b, R.id.layout_ai_skipping_speed);
        TrackChartViewHolder trackChartViewHolder = new TrackChartViewHolder(this.c, 100, false, 16);
        trackChartViewHolder.c(motionPathSimplify.getExtendDataInt("skipSpeed", 0));
        trackChartViewHolder.e(hji.g(motionPath.requestSkippingSpeedList()));
        hixVar.d(trackChartViewHolder.getCombinedChart());
        hixVar.d(trackChartViewHolder.d());
        hixVar.e().setLogEnabled(true);
        hixVar.bgB_().addView(trackChartViewHolder);
        TrackLineChartHolder.setInstance(this.bu);
        d(nsn.cLh_(getActivity()), hixVar, "BASELINE_SKIPPING_SPEED", 15, motionPathSimplify.requestSportType());
        this.bu.addSkippingSpeedDataLayer(hixVar.e());
        hixVar.e().setTouchEnabled(false);
        hixVar.e().setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
        hixVar.e().refresh();
        this.t.put(SportDetailChartDataType.SKIPPING_SPEED, hixVar);
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        if (this.f3732a == null) {
            LogUtil.h("Track_DetailFrag", "onMultiWindowModeChanged mTrackDetailDataManager is null.;");
            return;
        }
        MotionPathSimplify e2 = this.f3732a.e();
        if (e2 == null) {
            LogUtil.h("Track_DetailFrag", "onMultiWindowModeChanged motionPathSimplify is null.");
            return;
        }
        for (Map.Entry<SportDetailChartDataType, hix> entry : this.t.entrySet()) {
            a(entry.getKey(), entry.getValue(), z, e2.requestSportType());
        }
    }

    private void a(SportDetailChartDataType sportDetailChartDataType, hix hixVar, boolean z, int i2) {
        if (sportDetailChartDataType == SportDetailChartDataType.HEART_RATE) {
            d(z, hixVar, "BASELINE_HEART_RATE", 0, i2);
        } else if (sportDetailChartDataType == SportDetailChartDataType.SKIPPING_SPEED) {
            d(z, hixVar, "BASELINE_SKIPPING_SPEED", 15, i2);
        } else {
            LogUtil.h("Track_DetailFrag", "chart does not exist.");
        }
    }

    private void bt() {
        if (!hji.a(this.f3732a.e().requestSportType(), this.f3732a.e().requestSportDataSource()) || koq.b(this.f3732a.d().requestResistanceList())) {
            ReleaseLogUtil.d("Track_DetailFrag", "not support Resistance list");
            return;
        }
        SpeedPercentView speedPercentView = (SpeedPercentView) this.b.findViewById(R.id.resistance_chart_view);
        ((LinearLayout) this.b.findViewById(R.id.resistance_chart_layout)).setVisibility(0);
        speedPercentView.setData(this.f3732a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i2) {
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.suggestion.ui.BasketballSuggestionActivity");
        intent.putExtra("basketballFlag", i2);
        intent.putParcelableArrayListExtra("basketballAdviceList", this.l);
        if (AppBundle.e() != null) {
            ReleaseLogUtil.e("Track_DetailFrag", "start launching the PluginFitnessAdvice module");
            intent.putExtra("moduleName", "PluginFitnessAdvice");
            AppBundle.e().launchActivity(this.c, intent, null);
        } else {
            try {
                this.c.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("Track_DetailFrag", "startBasketballSuggestionActivity exception.");
            }
        }
    }

    private void c(List<ixt> list) {
        BasketballAdvice basketballAdvice = new BasketballAdvice(-1, 0);
        BasketballAdvice basketballAdvice2 = new BasketballAdvice(-1, 1);
        if (list != null) {
            basketballAdvice.setValue(hji.d(list));
            basketballAdvice2.setValue(hji.b(list));
        }
        this.l.add(basketballAdvice);
        this.l.add(basketballAdvice2);
    }

    private void bk() {
        int i2;
        int i3;
        if (this.f3732a.e().requestSportType() == 271 && this.f3732a.e().requestChiefSportDataType() == 5) {
            bf();
            aq();
            int i4 = 0;
            this.ak.setVisibility(0);
            Map<String, Integer> requestSportData = this.f3732a.e().requestSportData();
            if (requestSportData == null) {
                ReleaseLogUtil.c("Track_DetailFrag", "sportData is null");
                return;
            }
            List<ixt> requestJumpDataList = this.f3732a.d() != null ? this.f3732a.d().requestJumpDataList() : null;
            if (requestJumpDataList != null) {
                i2 = hji.d(requestJumpDataList);
                this.ak.setJumpHeight(i2);
                LogUtil.c("Track_DetailFrag", "countAverageJumpHeight(trackJumpDataList)", Integer.valueOf(i2));
                i3 = hji.b(requestJumpDataList);
                this.ak.setJumpDuration(i3);
                LogUtil.c("Track_DetailFrag", "countAverageJumptime(trackJumpDataList)", 0);
            } else {
                i2 = 0;
                i3 = 0;
            }
            if (requestSportData.containsKey("jump_times") && requestSportData.get("jump_times") != null) {
                i4 = requestSportData.get("jump_times").intValue();
                this.ak.setJumpTimes(i4);
                LogUtil.c("Track_DetailFrag", "SPORT_DATA_KEY_JUMP_TIMES", Integer.valueOf(i4));
            }
            if (d(i3) && d(i4) && d(i2)) {
                this.ak.setVisibility(8);
            }
            ab();
            ((LinearLayout) this.b.findViewById(R.id.track_detail_show_distance)).setVisibility(8);
            c(requestSportData);
            c(requestJumpDataList);
            bl();
        }
    }

    private void bl() {
        this.ak.getHeightLayout().setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DetailFrag.this.f(0);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ak.getAverageJumpDurationLayout().setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DetailFrag.this.f(1);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void aq() {
        BasketballScoreView basketballScoreView = (BasketballScoreView) this.b.findViewById(R.id.basketball_score);
        this.q = basketballScoreView;
        basketballScoreView.setVisibility(0);
        BaseActivity.cancelLayoutById(this.q);
        this.ak = (JumpDataView) this.b.findViewById(R.id.jump_view);
    }

    private void bf() {
        if (LanguageUtil.m(getContext()) && !Utils.o() && CommonUtil.ag(getContext())) {
            this.b.findViewById(R.id.basketball_knowlege).setVisibility(0);
        } else {
            this.b.findViewById(R.id.basketball_knowlege).setVisibility(8);
        }
    }

    private void c(Map<String, Integer> map) {
        double d2;
        this.as = (LinearLayout) this.b.findViewById(R.id.track_detail_linear_layout_moving);
        SpeedPercentView speedPercentView = (SpeedPercentView) this.b.findViewById(R.id.speed_percent_view);
        ReleaseLogUtil.e("Track_DetailFrag", "all zero", Boolean.valueOf(this.f3732a.d().isValidSpeedList()));
        this.as.setVisibility(0);
        if (!this.f3732a.d().isValidSpeedList()) {
            this.as.setVisibility(8);
            return;
        }
        HealthTextView healthTextView = (HealthTextView) this.b.findViewById(R.id.tv_track_detail_avg_speed);
        if (LanguageUtil.j(this.c) || LanguageUtil.bn(this.c) || LanguageUtil.bi(this.c)) {
            healthTextView.setText(getString(R.string._2130843165_res_0x7f02161d));
        }
        HealthTextView healthTextView2 = (HealthTextView) this.b.findViewById(R.id.track_detail_moving_speed);
        HealthTextView healthTextView3 = (HealthTextView) this.b.findViewById(R.id.max_spriting_speed);
        speedPercentView.setData(this.f3732a);
        if (!map.containsKey("max_spriting_speed")) {
            d2 = 0.0d;
        } else if (UnitUtil.h()) {
            d2 = UnitUtil.e((map.get("max_spriting_speed").intValue() * 3.6f) / 10.0f, 3);
        } else {
            d2 = (map.get("max_spriting_speed").intValue() * 3.6f) / 10.0f;
        }
        d(d2, healthTextView2, healthTextView3);
    }

    private void d(double d2, HealthTextView healthTextView, HealthTextView healthTextView2) {
        healthTextView2.setText(UnitUtil.e(d2, 1, 1));
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.b.findViewById(R.id.track_share_heart_title);
        int requestTotalDistance = this.f3732a.e().requestTotalDistance();
        long requestTotalTime = this.f3732a.e().requestTotalTime() / 1000;
        if (UnitUtil.h()) {
            r15 = requestTotalTime != 0 ? UnitUtil.e(((requestTotalDistance * 1.0d) / requestTotalTime) * 3.5999999046325684d, 3) : 0.0d;
            healthSubHeader.setHeadTitleText(this.c.getString(R.string._2130843209_res_0x7f021649));
        } else if (requestTotalTime != 0) {
            r15 = ((requestTotalDistance * 1.0d) / requestTotalTime) * 3.5999999046325684d;
        }
        double d3 = r15;
        healthTextView.setText(UnitUtil.e(d3, 1, 1));
        if (gvv.b(d2) || gvv.b(d3)) {
            this.as.setVisibility(8);
        }
    }

    private void ab() {
        Map<String, Integer> requestSportData = this.f3732a.e().requestSportData();
        if (requestSportData == null) {
            ReleaseLogUtil.c("Track_DetailFrag", "sportData is null");
            return;
        }
        int[] c = hji.c(requestSportData);
        int i2 = 0;
        if (c != null) {
            for (int i3 : c) {
                LogUtil.c("Track_DetailFrag", "each Score is", Integer.valueOf(i3));
            }
        }
        if (requestSportData.containsKey("overall_score") && requestSportData.get("overall_score") != null) {
            i2 = requestSportData.get("overall_score").intValue();
        }
        if (requestSportData.containsKey("active_time") && requestSportData.get("active_time") != null) {
            this.q.setActiveTime(requestSportData.get("active_time").intValue());
        }
        this.q.setRadarScore(c);
        this.q.setAverageScore(i2);
        this.q.setBasketballStamp(true);
        this.q.setScoreInfoText(this.c);
    }

    private void bu() {
        LogUtil.a("Track_DetailFrag", " showSwim");
        if (this.f3732a.e().requestSportType() == 266 || this.f3732a.e().requestSportType() == 262) {
            boolean az = az();
            boolean ba = ba();
            boolean ay = ay();
            if (az || ba || ay) {
                this.bg.setVisibility(0);
                ((LinearLayout) this.b.findViewById(R.id.track_detail_show_swim_title)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        caj.a().a("SWIM_PULL_TIMES");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                return;
            } else {
                this.bg.setVisibility(8);
                return;
            }
        }
        this.bg.setVisibility(8);
    }

    private void bo() {
        LogUtil.a("Track_DetailFrag", " showGolf");
        if (this.f3732a.e().requestSportType() == 220) {
            u();
            this.w.setVisibility(0);
            ((LinearLayout) this.b.findViewById(R.id.track_detail_show_golf_stat_title)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    caj.a().a("BACK_SWING");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else {
            this.y.setVisibility(8);
            this.w.setVisibility(8);
        }
    }

    private void u() {
        MotionPathSimplify e2 = this.f3732a.e();
        boolean z = e2.requestSportDataSource() == 2;
        boolean z2 = ((e2.getExtendDataDouble("golfBackSwingTime") > (-1.0d) ? 1 : (e2.getExtendDataDouble("golfBackSwingTime") == (-1.0d) ? 0 : -1)) == 0 && (e2.getExtendDataDouble("golfDownSwingTime") > (-1.0d) ? 1 : (e2.getExtendDataDouble("golfDownSwingTime") == (-1.0d) ? 0 : -1)) == 0) && ((e2.getExtendDataDouble("golfSwingTempo") > (-1.0d) ? 1 : (e2.getExtendDataDouble("golfSwingTempo") == (-1.0d) ? 0 : -1)) == 0 && (e2.getExtendDataDouble("golfMaxSwingSpeed") > (-1.0d) ? 1 : (e2.getExtendDataDouble("golfMaxSwingSpeed") == (-1.0d) ? 0 : -1)) == 0 && (e2.getExtendDataDouble("golfSwingSpeed") > (-1.0d) ? 1 : (e2.getExtendDataDouble("golfSwingSpeed") == (-1.0d) ? 0 : -1)) == 0);
        if (z || z2) {
            this.y.setVisibility(8);
        } else {
            this.y.setVisibility(0);
        }
    }

    private boolean az() {
        String quantityString;
        float requestAvgPace = this.f3732a.e().requestAvgPace();
        if (requestAvgPace > 0.0f) {
            String f2 = hji.f(requestAvgPace);
            if (UnitUtil.h()) {
                quantityString = this.ax.getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
            } else {
                quantityString = this.ax.getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 100, 100);
            }
            ((HealthTextView) this.b.findViewById(R.id.track_detail_swim_avg_pace)).setText(f2);
            ((HealthTextView) this.b.findViewById(R.id.track_detail_swim_avg_pace_unit)).setText(quantityString);
            return true;
        }
        this.b.findViewById(R.id.track_detail_swim_avg_pace_layout).setVisibility(8);
        return false;
    }

    private boolean ba() {
        Map<String, Integer> map = this.bb;
        if (map == null || map.get("swim_pull_freq") == null) {
            this.b.findViewById(R.id.track_detail_swim_avg_stroke_rate_layout).setVisibility(8);
            this.b.findViewById(R.id.track_detail_swim_avg_pace_line).setVisibility(8);
            return false;
        }
        int intValue = this.bb.get("swim_pull_freq").intValue();
        if (intValue <= 0) {
            this.b.findViewById(R.id.track_detail_swim_avg_stroke_rate_layout).setVisibility(8);
            this.b.findViewById(R.id.track_detail_swim_avg_pace_line).setVisibility(8);
            return false;
        }
        String quantityString = this.ax.getQuantityString(R.plurals._2130903224_res_0x7f0300b8, intValue);
        ((HealthTextView) this.b.findViewById(R.id.track_detail_swim_avg_stroke_rate)).setText(UnitUtil.e(intValue, 1, 0));
        ((HealthTextView) this.b.findViewById(R.id.track_detail_swim_avg_stroke_rate_unit)).setText(quantityString);
        return true;
    }

    private boolean ay() {
        Map<String, Integer> map = this.bb;
        if (map == null || map.get(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF) == null) {
            this.b.findViewById(R.id.track_detail_swim_avg_swolf_layout).setVisibility(8);
            this.b.findViewById(R.id.track_detail_swim_avg_stroke_rate_line).setVisibility(8);
            return false;
        }
        int intValue = this.bb.get(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF).intValue();
        if (intValue <= 0) {
            this.b.findViewById(R.id.track_detail_swim_avg_swolf_layout).setVisibility(8);
            this.b.findViewById(R.id.track_detail_swim_avg_stroke_rate_line).setVisibility(8);
            return false;
        }
        ((HealthTextView) this.b.findViewById(R.id.track_detail_swim_avg_swolf)).setText(UnitUtil.e(intValue, 1, 0));
        return true;
    }

    private void bd() {
        if (this.bb != null) {
            if (this.n != null) {
                i();
                return;
            } else {
                this.bm.setVisibility(8);
                ThreadPoolManager.d().execute(new Runnable() { // from class: hgy
                    @Override // java.lang.Runnable
                    public final void run() {
                        DetailFrag.this.g();
                    }
                });
                return;
            }
        }
        this.bm.setVisibility(8);
    }

    public /* synthetic */ void g() {
        s();
        this.bz.waitInit();
        this.n = this.bz.getLocalUserInfo();
        HandlerExecutor.a(new Runnable() { // from class: hgp
            @Override // java.lang.Runnable
            public final void run() {
                DetailFrag.this.i();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: bv, reason: merged with bridge method [inline-methods] */
    public void i() {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            LogUtil.h("Track_DetailFrag", "activity is finishing");
            return;
        }
        bj();
        bi();
        if (hji.b(this.f3732a).booleanValue()) {
            b(false);
        } else {
            this.b.findViewById(R.id.layout_vo2max).setVisibility(8);
        }
        bp();
        bs();
        bq();
        if (!LanguageUtil.m(this.c) || Utils.o()) {
            return;
        }
        bw();
    }

    private void bq() {
        if (koq.c(this.o)) {
            a(this.o.get(r0.size() - 1).intValue());
        } else {
            this.bm.setVisibility(8);
        }
    }

    private void a(int i2) {
        if (i2 == 0) {
            this.b.findViewById(R.id.divide_excise_effect).setVisibility(8);
        } else if (i2 == 1) {
            this.b.findViewById(R.id.divide_anaerobic).setVisibility(8);
        } else {
            if (i2 != 2) {
                return;
            }
            this.b.findViewById(R.id.divide_vo2max).setVisibility(8);
        }
    }

    private void bs() {
        LinearLayout linearLayout = (LinearLayout) this.b.findViewById(R.id.track_detail_show_wear_title);
        if (this.b.findViewById(R.id.layout_excise_effect).getVisibility() == 8 && this.b.findViewById(R.id.layout_vo2max).getVisibility() == 8 && this.b.findViewById(R.id.layout_recover_time).getVisibility() == 8 && this.b.findViewById(R.id.layout_aerobic_excise_effect).getVisibility() == 8) {
            linearLayout.setVisibility(8);
            this.bm.setVisibility(8);
        } else {
            this.bm.setVisibility(0);
            bek_(linearLayout);
        }
    }

    private void bek_(LinearLayout linearLayout) {
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DetailFrag.this.p == 0) {
                    hji.d(4, DetailFrag.this.c, DetailFrag.this.p);
                } else {
                    caj.a().a("AEROBIC_TRAINING_STRESS");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void bp() {
        if (this.bb.get("recovery_time") != null) {
            int hours = (int) TimeUnit.MINUTES.toHours(this.bb.get("recovery_time").intValue());
            if (bb()) {
                c(hours);
                return;
            } else {
                e(hours);
                this.o.add(3);
                return;
            }
        }
        this.b.findViewById(R.id.layout_recover_time).setVisibility(8);
    }

    private void e(int i2) {
        if (i2 <= 0) {
            this.b.findViewById(R.id.layout_recover_time).setVisibility(8);
            return;
        }
        b(i2, this.bj, this.bo);
        if (i2 > 35) {
            b(i2).add(10, -35);
        }
        if (this.p == 1) {
            this.at.setVisibility(8);
        } else {
            this.at.setText(hji.c(i2, this.f3732a.e().requestEndTime(), this.c, this.p));
        }
    }

    private void c(int i2) {
        this.b.findViewById(R.id.layout_recover_time).setVisibility(8);
        if (i2 <= 0) {
            return;
        }
        this.b.findViewById(R.id.layout_ai_skipping_train_card).setVisibility(0);
        LinearLayout linearLayout = (LinearLayout) this.b.findViewById(R.id.track_detail_show_wear_title_skiping);
        linearLayout.setVisibility(0);
        bek_(linearLayout);
        b(i2, (HealthTextView) this.b.findViewById(R.id.track_detail_ritire_duration_skipping), (HealthTextView) this.b.findViewById(R.id.track_detail_ritire_time_skipping));
    }

    private void b(int i2, HealthTextView healthTextView, HealthTextView healthTextView2) {
        healthTextView.setText(hji.bgN_("\\d", this.c.getResources().getQuantityString(R.plurals._2130903133_res_0x7f03005d, i2, Integer.valueOf(i2)), R.style.track_detail_time_b, R.style.track_detail_time_s, getContext()));
        healthTextView2.setText(c(b(i2).getTimeInMillis()));
    }

    public int b() {
        return CommonUtil.e(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1005), 0);
    }

    public int e() {
        if (this.n == null) {
            s();
            this.n = this.bz.getLocalUserInfo();
        }
        return this.n.getAgeOrDefaultValue();
    }

    private void s() {
        if (this.bz == null) {
            LogUtil.a("Track_DetailFrag", "getOrDefault.");
            this.bz = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        }
    }

    public void b(boolean z) {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            LogUtil.h("Track_DetailFrag", "showVo2maxAbility activity is null or finishing or destroyed");
            return;
        }
        if (this.b == null) {
            ReleaseLogUtil.d("Track_DetailFrag", "mRootView is null in showVo2maxAbility.");
            return;
        }
        Map<String, Integer> map = this.bb;
        if (map != null && map.get("max_met") != null) {
            int intValue = ((int) (this.bb.get("max_met").intValue() * 3.5f)) / 65536;
            LogUtil.a("Track_DetailFrag", "userVo2max=", Integer.valueOf(intValue));
            if (z) {
                this.n = this.bz.getLocalUserInfo();
            }
            Integer[] c = hji.c(this.n);
            this.bw.b(intValue).c(true).c(hji.a(c)).d(f, g, h, d, j, i, e).d(c).a(this.am);
            if (intValue > 0) {
                this.bx.setVisibility(0);
                this.r.setText(UnitUtil.e(intValue, 1, 0));
                if (LanguageUtil.bc(this.c)) {
                    this.ca.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
                } else {
                    this.ca.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
                }
                int b = b();
                if (b == 0) {
                    this.bt.setText(String.format(getString(R.string._2130842096_res_0x7f0211f0), hji.a(this.c, c, intValue, true)));
                } else if (b == 1) {
                    this.bt.setText(String.format(getString(R.string._2130842097_res_0x7f0211f1), hji.a(this.c, c, intValue, true)));
                } else {
                    this.bt.setText(String.format(getString(R.string._2130843782_res_0x7f021886), hji.a(this.c, c, intValue, true)));
                }
                this.b.findViewById(R.id.layout_vo2max).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag.8
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        DetailFrag.this.q();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                this.o.add(2);
                bq();
                return;
            }
            this.b.findViewById(R.id.layout_vo2max).setVisibility(8);
            return;
        }
        this.b.findViewById(R.id.layout_vo2max).setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        ComponentName componentName = new ComponentName(this.c.getPackageName(), "com.huawei.ui.main.stories.health.activity.healthdata.Vo2maxActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("fromDetailFrag", true);
        getActivity().startActivityForResult(intent, 0);
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("dataType", 0);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.BI_TRACK_DETAIL_SKIP_1040040.value(), hashMap, 0);
    }

    private void bj() {
        if (this.bb.get("etraining_effect") != null) {
            float intValue = this.bb.get("etraining_effect").intValue() / 10.0f;
            LogUtil.h("Track_DetailFrag", "showAerobicExerciseEffect trainingEffect ", Float.valueOf(intValue));
            if (intValue >= 1.0f) {
                this.bl.setText(UnitUtil.e(intValue, 1, 1));
                double round = (Math.round(intValue * 10.0f) * 1.0f) / 10.0f;
                if (round <= 1.9d) {
                    this.by.setText(this.c.getString(R.string._2130839854_res_0x7f02092e));
                } else if (round <= 2.9d) {
                    this.by.setText(this.c.getString(R.string._2130839855_res_0x7f02092f));
                } else if (round <= 3.9d) {
                    this.by.setText(this.c.getString(R.string._2130839856_res_0x7f020930));
                } else if (round <= 4.9d) {
                    this.by.setText(this.c.getString(R.string._2130839857_res_0x7f020931));
                } else {
                    this.by.setText(this.c.getString(R.string._2130839858_res_0x7f020932));
                }
                if (this.p == 0) {
                    this.m.setText(R.string._2130842764_res_0x7f02148c);
                } else {
                    this.m.setText(R.string._2130844088_res_0x7f0219b8);
                }
                this.o.add(0);
            } else {
                this.b.findViewById(R.id.layout_excise_effect).setVisibility(8);
            }
            float h2 = hji.h(intValue);
            this.bv.b(h2).c(false).c(0.0f).d(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f).a(hji.a(this.c, h2, this.p)).a(this.am);
            return;
        }
        this.b.findViewById(R.id.layout_excise_effect).setVisibility(8);
    }

    private void bi() {
        if (this.bb.get("anaerobic_exercise_etraining_effect") != null) {
            float intValue = this.bb.get("anaerobic_exercise_etraining_effect").intValue() / 10.0f;
            if (intValue >= 1.0f) {
                this.bh.setText(UnitUtil.e(intValue, 1, 1));
                this.o.add(1);
            } else {
                this.b.findViewById(R.id.layout_aerobic_excise_effect).setVisibility(8);
            }
            float h2 = hji.h(intValue);
            this.br.b(h2).c(false).c(0.0f).d(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f).a(hji.a(this.c, h2, this.p)).a(this.am);
            if (this.p == 0) {
                this.k.setText(R.string._2130842731_res_0x7f02146b);
                return;
            } else {
                this.k.setText(R.string._2130844089_res_0x7f0219b9);
                return;
            }
        }
        this.b.findViewById(R.id.layout_aerobic_excise_effect).setVisibility(8);
    }

    private void aa() {
        int requestSportType = this.f3732a.e().requestSportType();
        int requestChiefSportDataType = this.f3732a.e().requestChiefSportDataType();
        if (requestSportType != 266 && requestSportType != 262) {
            if (this.f3732a.e().requestChiefSportDataType() == 6) {
                ai();
                return;
            } else {
                a(requestSportType, requestChiefSportDataType);
                return;
            }
        }
        if (requestChiefSportDataType == 1 || requestChiefSportDataType == 2) {
            a();
        } else {
            as();
        }
    }

    private void a(int i2, int i3) {
        if (i3 == 1 || i3 == 2) {
            a();
            return;
        }
        if (i2 == 283) {
            ao();
            return;
        }
        if (gvv.c(this.f3732a.e()).equals("291")) {
            a();
        } else if (i3 == 11) {
            z();
        } else {
            ad();
        }
    }

    private void ao() {
        if (!k() || !this.f3732a.e().requestExtendDataMap().containsKey("skipNum") || this.f3732a.e().getExtendDataInt("skipNum", 0) <= 0) {
            ReleaseLogUtil.d("Track_DetailFrag", "initSkippingNumber() mTrackDetailDataManager is null");
            this.bp.setText(this.aj);
            this.bq.setText(this.c.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 0, ""));
        } else {
            int extendDataInt = this.f3732a.e().getExtendDataInt("skipNum", 0);
            this.bp.setText(UnitUtil.e(extendDataInt, 1, 0));
            this.bq.setText(this.c.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, extendDataInt, ""));
        }
    }

    private void z() {
        long requestTotalTime = this.f3732a.e().requestTotalTime();
        if (requestTotalTime <= 0) {
            ReleaseLogUtil.d("Track_DetailFrag", "initChiefTime() totalTime is null");
            this.bp.setText(this.aj);
        } else {
            this.bp.setText(gvv.c(requestTotalTime, R.style.sport_day_hour_min_num_52dp, R.style.sport_day_hour_min_unit_14dp));
            this.bq.setVisibility(8);
        }
    }

    private void ad() {
        if (UnitUtil.h()) {
            this.bq.setText(R.string._2130841383_res_0x7f020f27);
        } else {
            this.bq.setText(R.string._2130841382_res_0x7f020f26);
        }
        if (!k() || this.f3732a.e().requestTotalDistance() == 0) {
            ReleaseLogUtil.d("Track_DetailFrag", "initDefaultDistance() mTrackDetailDataManager is null");
            this.bp.setText(this.aj);
        } else {
            this.bp.setText(hji.e(this.f3732a.e().requestTotalDistance()));
        }
    }

    private void as() {
        if (this.bb == null && this.f3732a.e().requestSportDataSource() != 2) {
            ad();
            return;
        }
        if (gwg.b(this.f3732a.e()) && this.f3732a.e().requestSportDataSource() != 2) {
            ((LinearLayout) this.b.findViewById(R.id.track_detail_show_distance)).setVisibility(8);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.az.getLayoutParams();
            layoutParams.bottomMargin = this.ax.getDimensionPixelOffset(R.dimen._2131363703_res_0x7f0a0777);
            this.az.setLayoutParams(layoutParams);
            return;
        }
        if (UnitUtil.h()) {
            this.bq.setText(this.ax.getQuantityString(R.plurals._2130903227_res_0x7f0300bb, (int) Math.round(UnitUtil.e(this.f3732a.e().requestTotalDistance(), 2))));
        } else {
            this.bq.setText(R.string._2130841568_res_0x7f020fe0);
        }
        if (this.f3732a.e().requestTotalDistance() == 0) {
            this.bp.setText(this.aj);
        } else {
            this.bp.setText(hji.i(this.f3732a.e().requestTotalDistance()));
        }
    }

    public void a() {
        String str;
        int i2;
        if (!k() || this.f3732a.e().requestTotalCalories() <= 0) {
            ReleaseLogUtil.d("Track_DetailFrag", "initChiefCalorie() mTrackDetailDataManager is null");
            str = this.aj;
            i2 = 0;
        } else {
            i2 = this.f3732a.e().requestTotalCalories() / 1000;
            str = hji.c(this.f3732a.e().requestTotalCalories());
        }
        String a2 = nsf.a(R.plurals._2130903423_res_0x7f03017f, i2, str);
        SpannableString spannableString = new SpannableString(a2);
        nsi.cKH_(spannableString, a2, new nmj(nsk.cKP_()));
        nsi.cKH_(spannableString, a2, new AbsoluteSizeSpan(nsf.b(R.dimen._2131365064_res_0x7f0a0cc8)));
        nsi.cKH_(spannableString, a2, new ForegroundColorSpan(nsf.c(R.color._2131299236_res_0x7f090ba4)));
        nsi.cKH_(spannableString, str, new nmj(nsk.cKO_()));
        nsi.cKH_(spannableString, str, new AbsoluteSizeSpan(nsf.b(R.dimen._2131365071_res_0x7f0a0ccf)));
        this.bp.setText(spannableString);
        this.bq.setVisibility(8);
    }

    private void ai() {
        int extendDataInt = this.f3732a.e().getExtendDataInt("golfSwingCount");
        this.bq.setText(this.c.getResources().getQuantityString(R.plurals._2130903269_res_0x7f0300e5, extendDataInt, ""));
        if (!k() || extendDataInt == 0) {
            LogUtil.h("Track_DetailFrag", "initGolfCount() mTrackDetailDataManager is null");
            this.bp.setText(this.aj);
        } else {
            this.bp.setText(UnitUtil.e(extendDataInt, 1, 0));
        }
    }

    private void bm() {
        this.ay.clear();
        if (m() && this.f3732a.d().requestRunningPostureList() != null && this.f3732a.d().requestRunningPostureList().size() > 0) {
            if (ffs.a(this.f3732a.e())) {
                this.bc.setVisibility(8);
                return;
            }
            ae();
            ac();
            ag();
            at();
            av();
            ax();
            ap();
            am();
            af();
            ah();
            float requestAvgPace = this.f3732a.e().requestAvgPace();
            e(((double) Math.abs(requestAvgPace)) > 1.0E-6d ? 3600.0f / requestAvgPace : 0.0f);
            this.av = (TotalDataRectView) this.bf.findViewById(R.id.proportion_bar);
            al();
            aj();
            if (LanguageUtil.m(getContext()) && !Utils.o()) {
                this.bc.findViewById(R.id.track_detail_running_posture_surpport).setVisibility(0);
                return;
            } else {
                this.bc.findViewById(R.id.track_detail_running_posture_surpport).setVisibility(8);
                return;
            }
        }
        this.bc.setVisibility(8);
    }

    private void al() {
        int requestAvgForeFootStrikePattern = this.f3732a.e().requestAvgForeFootStrikePattern();
        int requestAvgWholeFootStrikePattern = this.f3732a.e().requestAvgWholeFootStrikePattern();
        int requestAvgHindFootStrikePattern = this.f3732a.e().requestAvgHindFootStrikePattern();
        double d2 = requestAvgForeFootStrikePattern + requestAvgWholeFootStrikePattern + requestAvgHindFootStrikePattern;
        if (Double.compare(d2, 0.0d) <= 0) {
            this.bf.setVisibility(8);
            return;
        }
        int floor = (int) Math.floor((requestAvgWholeFootStrikePattern / d2) * 100.0d);
        int floor2 = (int) Math.floor((requestAvgHindFootStrikePattern / d2) * 100.0d);
        int i2 = (100 - floor) - floor2;
        if (i2 < 0) {
            i2 = 0;
        }
        LogUtil.a("Track_DetailFrag", "showAW70 foreFootAvg = ", Integer.valueOf(i2), " hindFootAvg = ", Integer.valueOf(floor2), " wholeFootAvg = ", Integer.valueOf(floor));
        this.ay.add(new StrikePatternAdvice(i2, floor, floor2));
        this.av.setColors(getResources().getColor(R.color._2131299277_res_0x7f090bcd), getResources().getColor(R.color._2131299281_res_0x7f090bd1), getResources().getColor(R.color._2131299280_res_0x7f090bd0));
        this.av.setViewData(i2, floor, floor2);
        this.av.postInvalidate();
        String e2 = UnitUtil.e(floor2, 2, 0);
        String e3 = UnitUtil.e(i2, 2, 0);
        String e4 = UnitUtil.e(floor, 2, 0);
        ((HealthTextView) this.bf.findViewById(R.id.running_posture_avg_foot_strike_pattern_fore_value)).setText(UnitUtil.bCR_(this.c, "\\d+.\\d+|\\d+", e3, R.style.strike_pattern_text_result_k, R.style.strike_pattern_text_result_n));
        ((HealthTextView) this.bf.findViewById(R.id.running_posture_avg_foot_strike_pattern_whole_value)).setText(UnitUtil.bCR_(this.c, "\\d+.\\d+|\\d+", e4, R.style.strike_pattern_text_result_k, R.style.strike_pattern_text_result_n));
        ((HealthTextView) this.bf.findViewById(R.id.running_posture_avg_foot_strike_pattern_hind_value)).setText(UnitUtil.bCR_(this.c, "\\d+.\\d+|\\d+", e2, R.style.strike_pattern_text_result_k, R.style.strike_pattern_text_result_n));
    }

    private void ac() {
        int requestAverageHangTime = this.f3732a.e().requestAverageHangTime();
        if (requestAverageHangTime <= 0) {
            this.ar.setVisibility(8);
            this.b.findViewById(R.id.track_detail_hang_time_line).setVisibility(8);
            return;
        }
        int round = Math.round(this.f3732a.e().requestAvgPace());
        String e2 = UnitUtil.e(requestAverageHangTime, 1, 0);
        AverageHangTimeAdvice averageHangTimeAdvice = new AverageHangTimeAdvice(this.f3732a.e().requestAverageHangTime(), round, this.au, SportDetailChartDataType.HANG_TIME);
        ((HealthTextView) this.ar.findViewById(R.id.track_detail_posture_item_name)).setText(R.string._2130843163_res_0x7f02161b);
        ((HealthTextView) this.ar.findViewById(R.id.track_detail_posture_item_value)).setText(e2);
        HealthTextView healthTextView = (HealthTextView) this.ar.findViewById(R.id.track_detail_posture_item_level);
        a(healthTextView);
        healthTextView.setText(averageHangTimeAdvice.acquireLevelShortTip());
        healthTextView.setTextColor(getResources().getColor(gvv.d(averageHangTimeAdvice.acquireLevel())));
        ((HealthTextView) this.ar.findViewById(R.id.track_detail_posture_item_unit)).setText(R.string._2130842713_res_0x7f021459);
        this.ay.add(averageHangTimeAdvice);
    }

    private void ag() {
        float requestGroundHangTimeRate = this.f3732a.e().requestGroundHangTimeRate();
        int round = Math.round(this.f3732a.e().requestAvgPace());
        if (requestGroundHangTimeRate <= 0.0f) {
            this.ao.setVisibility(8);
            this.b.findViewById(R.id.track_detail_ground_hang_time_rate_line).setVisibility(8);
            return;
        }
        String e2 = UnitUtil.e(requestGroundHangTimeRate, 1, 1);
        GroundHangTimeRateAdvice groundHangTimeRateAdvice = new GroundHangTimeRateAdvice((int) (requestGroundHangTimeRate * 100.0f), round, this.au, SportDetailChartDataType.GROUND_HANG_TIME_RATE);
        ((HealthTextView) this.ao.findViewById(R.id.track_detail_posture_item_name)).setText(R.string._2130843726_res_0x7f02184e);
        ((HealthTextView) this.ao.findViewById(R.id.track_detail_posture_item_value)).setText(e2);
        HealthTextView healthTextView = (HealthTextView) this.ao.findViewById(R.id.track_detail_posture_item_level);
        a(healthTextView);
        healthTextView.setText(groundHangTimeRateAdvice.acquireLevelShortTip());
        healthTextView.setTextColor(getResources().getColor(gvv.d(groundHangTimeRateAdvice.acquireLevel())));
        this.ay.add(groundHangTimeRateAdvice);
    }

    private void ae() {
        int requestAvgGroundContactTime = this.f3732a.e().requestAvgGroundContactTime();
        if (requestAvgGroundContactTime <= 0) {
            this.ap.setVisibility(8);
            this.b.findViewById(R.id.track_detail_ground_contact_time_line).setVisibility(8);
            return;
        }
        int round = Math.round(this.f3732a.e().requestAvgPace());
        String e2 = UnitUtil.e(this.f3732a.e().requestAvgGroundContactTime(), 1, 0);
        GroundContactTimeAdvice groundContactTimeAdvice = new GroundContactTimeAdvice(requestAvgGroundContactTime, round, this.au, SportDetailChartDataType.GROUND_CONTACT_TIME);
        ((HealthTextView) this.ap.findViewById(R.id.track_detail_posture_item_name)).setText(R.string._2130842711_res_0x7f021457);
        ((HealthTextView) this.ap.findViewById(R.id.track_detail_posture_item_value)).setText(e2);
        HealthTextView healthTextView = (HealthTextView) this.ap.findViewById(R.id.track_detail_posture_item_level);
        a(healthTextView);
        healthTextView.setText(groundContactTimeAdvice.acquireLevelShortTip());
        healthTextView.setTextColor(getResources().getColor(gvv.d(groundContactTimeAdvice.acquireLevel())));
        ((HealthTextView) this.ap.findViewById(R.id.track_detail_posture_item_unit)).setText(R.string._2130842713_res_0x7f021459);
        this.ay.add(groundContactTimeAdvice);
    }

    private void aj() {
        if (LanguageUtil.bc(this.c)) {
            ((ImageView) this.ap.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) this.x.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) this.aa.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) this.af.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) this.ai.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) this.ad.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) this.ac.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) this.u.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) this.bd.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) this.bf.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) this.ar.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) this.ao.findViewById(R.id.track_detail_posture_item_more_arrow)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        }
    }

    private void e(float f2) {
        if (this.f3732a.e().requestAvgSwingAngle() <= 0.0f) {
            this.bd.setVisibility(8);
            this.b.findViewById(R.id.track_detail_swing_angle_line).setVisibility(8);
            return;
        }
        SwingAngleAdvice swingAngleAdvice = new SwingAngleAdvice(this.f3732a.e().requestAvgSwingAngle(), f2);
        ((HealthTextView) this.bd.findViewById(R.id.track_detail_posture_item_name)).setText(R.string._2130842723_res_0x7f021463);
        ((HealthTextView) this.bd.findViewById(R.id.track_detail_posture_item_value)).setText(getResources().getQuantityString(R.plurals._2130903247_res_0x7f0300cf, this.f3732a.e().requestAvgSwingAngle(), Integer.valueOf(this.f3732a.e().requestAvgSwingAngle())));
        HealthTextView healthTextView = (HealthTextView) this.bd.findViewById(R.id.track_detail_posture_item_level);
        a(healthTextView);
        healthTextView.setText(swingAngleAdvice.acquireLevelShortTip());
        healthTextView.setTextColor(getResources().getColor(gvv.d(swingAngleAdvice.acquireLevel())));
        this.ay.add(swingAngleAdvice);
    }

    private void ah() {
        if (this.f3732a.e().requestAvgEversionExcursion() <= -101.0f) {
            this.u.setVisibility(8);
            this.b.findViewById(R.id.track_detail_eversion_excursion_line).setVisibility(8);
            return;
        }
        EversionExcursionAngleAdvice eversionExcursionAngleAdvice = new EversionExcursionAngleAdvice(this.f3732a.e().requestAvgEversionExcursion());
        ((HealthTextView) this.u.findViewById(R.id.track_detail_posture_item_name)).setText(R.string._2130842722_res_0x7f021462);
        ((HealthTextView) this.u.findViewById(R.id.track_detail_posture_item_value)).setText(getResources().getQuantityString(R.plurals._2130903247_res_0x7f0300cf, this.f3732a.e().requestAvgEversionExcursion(), Integer.valueOf(this.f3732a.e().requestAvgEversionExcursion())));
        HealthTextView healthTextView = (HealthTextView) this.u.findViewById(R.id.track_detail_posture_item_level);
        a(healthTextView);
        healthTextView.setText(eversionExcursionAngleAdvice.acquireLevelShortTip());
        healthTextView.setTextColor(getResources().getColor(gvv.d(eversionExcursionAngleAdvice.acquireLevel())));
        this.ay.add(eversionExcursionAngleAdvice);
    }

    private void at() {
        if (beg_("avg_gc_tb", this.aa, this.ae)) {
            LogUtil.a("Track_DetailFrag", "initTouchdownBalanceAndLevel return");
            return;
        }
        GroundTouchdownBalanceAdvice groundTouchdownBalanceAdvice = new GroundTouchdownBalanceAdvice((float) UnitUtil.a(this.f3732a.e().getExtendDataFloat("avg_gc_tb"), 1));
        String string = this.c.getResources().getString(LanguageUtil.h(this.c) ? R.string._2130849078_res_0x7f022d36 : R.string._2130845183_res_0x7f021dff, UnitUtil.e(groundTouchdownBalanceAdvice.getValue(), 2, 1), UnitUtil.e(100.0f - groundTouchdownBalanceAdvice.getValue(), 2, 1));
        ((HealthTextView) this.aa.findViewById(R.id.track_detail_posture_item_name)).setText(R.string._2130845182_res_0x7f021dfe);
        ((HealthTextView) this.aa.findViewById(R.id.track_detail_posture_item_value)).setText(string);
        HealthTextView healthTextView = (HealthTextView) this.aa.findViewById(R.id.track_detail_posture_item_level);
        healthTextView.setAutoTextInfo(14, 1, 2);
        healthTextView.setText(groundTouchdownBalanceAdvice.acquireLevelShortTip());
        healthTextView.setTextColor(getResources().getColor(gvv.d(groundTouchdownBalanceAdvice.acquireLevel())));
        this.ay.add(groundTouchdownBalanceAdvice);
    }

    private boolean beg_(String str, LinearLayout linearLayout, HealthDivider healthDivider) {
        if (this.f3732a.e().getExtendDataFloat(str) <= 0.0f) {
            linearLayout.setVisibility(8);
            healthDivider.setVisibility(8);
            return true;
        }
        linearLayout.setVisibility(0);
        healthDivider.setVisibility(0);
        return false;
    }

    private void av() {
        String quantityString;
        if (beg_("avg_v_osc", this.af, this.ag)) {
            LogUtil.a("Track_DetailFrag", "initVerticalAmplitudeAdviceAndLevel return");
            return;
        }
        GroundVerticalAmplitudeAdvice groundVerticalAmplitudeAdvice = new GroundVerticalAmplitudeAdvice(this.f3732a.e().getExtendDataFloat("avg_v_osc"));
        ((HealthTextView) this.af.findViewById(R.id.track_detail_posture_item_name)).setText(R.string._2130845169_res_0x7f021df1);
        float b = b(groundVerticalAmplitudeAdvice.getValue());
        if (!UnitUtil.h()) {
            double d2 = b;
            quantityString = this.c.getResources().getQuantityString(R.plurals._2130903214_res_0x7f0300ae, UnitUtil.e(d2), UnitUtil.e(d2, 1, 1));
        } else {
            double d3 = b;
            quantityString = this.c.getResources().getQuantityString(R.plurals._2130903219_res_0x7f0300b3, UnitUtil.e(d3), UnitUtil.e(d3, 1, 1));
        }
        ((HealthTextView) this.af.findViewById(R.id.track_detail_posture_item_value)).setText(quantityString);
        HealthTextView healthTextView = (HealthTextView) this.af.findViewById(R.id.track_detail_posture_item_level);
        a(healthTextView);
        healthTextView.setText(groundVerticalAmplitudeAdvice.acquireLevelShortTip());
        healthTextView.setTextColor(getResources().getColor(gvv.d(groundVerticalAmplitudeAdvice.acquireLevel())));
        this.ay.add(groundVerticalAmplitudeAdvice);
    }

    private float b(float f2) {
        return !UnitUtil.h() ? f2 : (float) UnitUtil.e(f2, 0);
    }

    private void ax() {
        float extendDataFloat = this.f3732a.e().getExtendDataFloat("avg_v_s_r");
        if (Float.compare(extendDataFloat, 0.0f) <= 0 || this.f3732a.au()) {
            this.ai.setVisibility(8);
            this.ah.setVisibility(8);
            LogUtil.a("Track_DetailFrag", "initVerticalAmplitudeRatioAdviceAndLevel return");
            return;
        }
        this.ai.setVisibility(0);
        this.ah.setVisibility(0);
        GroundVerticalAmplitudeRatioAdvice groundVerticalAmplitudeRatioAdvice = new GroundVerticalAmplitudeRatioAdvice(extendDataFloat);
        String e2 = UnitUtil.e(groundVerticalAmplitudeRatioAdvice.getValue(), 2, 1);
        ((HealthTextView) this.ai.findViewById(R.id.track_detail_posture_item_name)).setText(R.string._2130845170_res_0x7f021df2);
        ((HealthTextView) this.ai.findViewById(R.id.track_detail_posture_item_value)).setText(e2);
        HealthTextView healthTextView = (HealthTextView) this.ai.findViewById(R.id.track_detail_posture_item_level);
        a(healthTextView);
        healthTextView.setText(groundVerticalAmplitudeRatioAdvice.acquireLevelShortTip());
        healthTextView.setTextColor(getResources().getColor(gvv.d(groundVerticalAmplitudeRatioAdvice.acquireLevel())));
        this.ay.add(groundVerticalAmplitudeRatioAdvice);
    }

    private void ap() {
        if (beg_("avg_i_p", this.ad, this.z)) {
            LogUtil.a("Track_DetailFrag", "initShockPeakAdviceAndLevel return");
            return;
        }
        GroundShockPeakAdvice groundShockPeakAdvice = new GroundShockPeakAdvice(this.f3732a.e().getExtendDataFloat("avg_i_p"));
        String e2 = UnitUtil.e(groundShockPeakAdvice.getValue(), 1, 1);
        ((HealthTextView) this.ad.findViewById(R.id.track_detail_posture_item_name)).setText(R.string._2130845988_res_0x7f022124);
        ((HealthTextView) this.ad.findViewById(R.id.track_detail_posture_item_value)).setText(e2);
        HealthTextView healthTextView = (HealthTextView) this.ad.findViewById(R.id.track_detail_posture_item_level);
        a(healthTextView);
        healthTextView.setText(groundShockPeakAdvice.acquireLevelShortTip());
        healthTextView.setTextColor(getResources().getColor(gvv.d(groundShockPeakAdvice.acquireLevel())));
        ((HealthTextView) this.ad.findViewById(R.id.track_detail_posture_item_unit)).setText(R.string._2130845180_res_0x7f021dfc);
        this.ay.add(groundShockPeakAdvice);
    }

    private void am() {
        if (beg_("avg_v_i_r", this.ac, this.ab)) {
            LogUtil.a("Track_DetailFrag", "initImpactLoadRateAdviceAndLevel return");
            return;
        }
        GroundImpactLoadRateAdvice groundImpactLoadRateAdvice = new GroundImpactLoadRateAdvice(this.f3732a.e().getExtendDataFloat("avg_v_i_r"));
        String e2 = UnitUtil.e(groundImpactLoadRateAdvice.getValue(), 1, 1);
        ((HealthTextView) this.ac.findViewById(R.id.track_detail_posture_item_name)).setText(R.string._2130845178_res_0x7f021dfa);
        ((HealthTextView) this.ac.findViewById(R.id.track_detail_posture_item_value)).setText(e2);
        HealthTextView healthTextView = (HealthTextView) this.ac.findViewById(R.id.track_detail_posture_item_level);
        a(healthTextView);
        healthTextView.setText(groundImpactLoadRateAdvice.acquireLevelShortTip());
        healthTextView.setTextColor(getResources().getColor(gvv.d(groundImpactLoadRateAdvice.acquireLevel())));
        HealthTextView healthTextView2 = (HealthTextView) this.ac.findViewById(R.id.track_detail_posture_item_unit);
        healthTextView2.setAutoTextInfo(9, 1, 2);
        healthTextView2.setText(this.c.getResources().getQuantityString(R.plurals._2130903324_res_0x7f03011c, 1, ""));
        this.ay.add(groundImpactLoadRateAdvice);
    }

    private void af() {
        int requestAvgGroundImpactAcceleration = this.f3732a.e().requestAvgGroundImpactAcceleration();
        if (requestAvgGroundImpactAcceleration <= 0) {
            this.x.setVisibility(8);
            this.b.findViewById(R.id.track_detail_ground_impact_acceleration_line).setVisibility(8);
            return;
        }
        this.x.setVisibility(0);
        GroundImpactAccelerationAdvice groundImpactAccelerationAdvice = new GroundImpactAccelerationAdvice(requestAvgGroundImpactAcceleration);
        String e2 = UnitUtil.e(requestAvgGroundImpactAcceleration, 1, 0);
        ((HealthTextView) this.x.findViewById(R.id.track_detail_posture_item_name)).setText(R.string._2130842717_res_0x7f02145d);
        ((HealthTextView) this.x.findViewById(R.id.track_detail_posture_item_value)).setText(e2);
        HealthTextView healthTextView = (HealthTextView) this.x.findViewById(R.id.track_detail_posture_item_level);
        a(healthTextView);
        healthTextView.setText(groundImpactAccelerationAdvice.acquireLevelShortTip());
        healthTextView.setTextColor(getResources().getColor(gvv.d(groundImpactAccelerationAdvice.acquireLevel())));
        ((HealthTextView) this.x.findViewById(R.id.track_detail_posture_item_unit)).setText(R.string._2130842716_res_0x7f02145c);
        this.ay.add(groundImpactAccelerationAdvice);
    }

    private void ak() {
        JudgeRootBean judgeRootBean = (JudgeRootBean) HiJsonUtil.e(hjt.e(BaseApplication.getContext()), JudgeRootBean.class);
        if (judgeRootBean == null) {
            LogUtil.b("Track_DetailFrag", "judgeRootBean is null");
        } else {
            this.au = judgeRootBean.getJudge();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        RunningPostureAdviceBase bef_ = bef_(view);
        if (bef_ == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (System.currentTimeMillis() - this.aq < 1000) {
            LogUtil.h("Track_DetailFrag", "double click");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        this.aq = System.currentTimeMillis();
        int b = b(bef_.getRunningPostureFragment());
        LogUtil.a("Track_DetailFrag", "onClick curPostureFlag = ", Integer.valueOf(b));
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.putExtra("runningPostureFlag", b);
        intent.putParcelableArrayListExtra("runningPostureAdvices", this.ay);
        bei_(intent);
        LogUtil.a("Track_DetailFrag", "onClick RUNNING_POSTURE_ADVICES = ", this.ay);
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.suggestion.ui.runningposture.RuningPostureSuggestActivity");
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("postureId", Integer.valueOf(bef_.getPostureId()));
        if (!(bef_ instanceof StrikePatternAdvice)) {
            hashMap.put("status", Integer.valueOf(hpo.a(this.ay.get(b).acquireLevelShortTip())));
        }
        ixx.d().d(this.c, AnalyticsValue.BI_TRACK_ENTER_POSTURE_1040046.value(), hashMap, 0);
        if (AppBundle.e() != null) {
            ReleaseLogUtil.e("Track_DetailFrag", "start launching the PluginFitnessAdvice module");
            intent.putExtra("moduleName", "PluginFitnessAdvice");
            AppBundle.e().launchActivity(this.c, intent, null);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        try {
            this.c.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Track_DetailFrag", "onClick ActivityNotFoundException.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void bei_(Intent intent) {
        MotionPath d2 = this.f3732a.d();
        if (koq.b(this.au) || this.f3732a.l()) {
            LogUtil.h("Track_DetailFrag", "beanList is null");
            return;
        }
        HashMap hashMap = new HashMap(3);
        ArrayList<SportDetailChartDataType> arrayList = new ArrayList(3);
        arrayList.add(SportDetailChartDataType.GROUND_HANG_TIME_RATE);
        arrayList.add(SportDetailChartDataType.HANG_TIME);
        arrayList.add(SportDetailChartDataType.GROUND_CONTACT_TIME);
        for (SportDetailChartDataType sportDetailChartDataType : arrayList) {
            hashMap.put(sportDetailChartDataType, hjt.b(hjt.c(d2, this.au.get(0)), sportDetailChartDataType, d2.requestRunningPostureList(), this.au));
        }
        intent.putExtra("runningPostureJudgeList", hashMap);
    }

    private RunningPostureAdviceBase bef_(View view) {
        if (view.getId() == R.id.track_detail_running_posture_ground_contact_time) {
            return new GroundContactTimeAdvice(0, 0, this.au, SportDetailChartDataType.GROUND_CONTACT_TIME);
        }
        if (view.getId() == R.id.track_detail_running_posture_hang_time) {
            return new AverageHangTimeAdvice(0, 0, this.au, SportDetailChartDataType.HANG_TIME);
        }
        if (view.getId() == R.id.track_detail_running_posture_ground_hang_time_rate) {
            return new GroundHangTimeRateAdvice(0, 0, this.au, SportDetailChartDataType.GROUND_HANG_TIME_RATE);
        }
        if (view.getId() == R.id.track_detail_running_posture_ground_impact_acceleration) {
            return new GroundImpactAccelerationAdvice(0);
        }
        if (view.getId() == R.id.track_detail_running_posture_eversion_excursion) {
            return new EversionExcursionAngleAdvice(0);
        }
        if (view.getId() == R.id.track_detail_running_posture_swing_angle) {
            return new SwingAngleAdvice(0, 0.0f);
        }
        if (view.getId() == R.id.track_detail_running_posture_strike_pattern) {
            return new StrikePatternAdvice(0, 0, 0);
        }
        if (view.getId() == R.id.track_detail_running_posture_ground_touchdown_balance) {
            return new GroundTouchdownBalanceAdvice(0.0f);
        }
        if (view.getId() == R.id.track_detail_running_posture_ground_vertical_amplitude) {
            return new GroundVerticalAmplitudeAdvice(0.0f);
        }
        if (view.getId() == R.id.track_detail_running_posture_ground_vertical_amplitude_ratio) {
            return new GroundVerticalAmplitudeRatioAdvice(0.0f);
        }
        if (view.getId() == R.id.track_detail_running_posture_ground_shock_peak) {
            return new GroundShockPeakAdvice(0.0f);
        }
        if (view.getId() == R.id.track_detail_running_posture_ground_impact_load_rate) {
            return new GroundImpactLoadRateAdvice(0.0f);
        }
        LogUtil.h("Track_DetailFrag", "not in five view");
        return null;
    }

    private int b(String str) {
        if (this.ay != null && str != null) {
            for (int i2 = 0; i2 < this.ay.size(); i2++) {
                if (this.ay.get(i2).getRunningPostureFragment().equals(str)) {
                    return i2;
                }
            }
        }
        return 0;
    }

    private void bw() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag.1
            @Override // java.lang.Runnable
            public void run() {
                final String p = DetailFrag.this.p();
                FragmentActivity activity = DetailFrag.this.getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (DetailFrag.this.isDetached()) {
                                return;
                            }
                            String str = p;
                            if (str != null && !str.isEmpty()) {
                                DetailFrag.this.ba.setVisibility(0);
                                DetailFrag.this.bn.setText(p);
                            } else {
                                LogUtil.c("Track_DetailFrag", "showSmartCoachSummary summaryText is null.");
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String p() {
        float intValue = this.bb.get("etraining_effect") != null ? this.bb.get("etraining_effect").intValue() / 10.0f : 0.0f;
        int hours = this.bb.get("recovery_time") != null ? (int) TimeUnit.MINUTES.toHours(this.bb.get("recovery_time").intValue()) : 0;
        if (intValue >= 1.0f && hours != 0) {
            int requestAvgHeartRate = this.f3732a.e().requestAvgHeartRate();
            HwSportTypeInfo d2 = hln.c(BaseApplication.getContext()).d(this.f3732a.e().requestSportType());
            if (d2 == null) {
                return "";
            }
            HeartZoneConf e2 = ffw.e(d2.getHeartPostureType(), this.f3732a.e().requestHeartRateZoneType(), this.bz.getLocalUserInfo());
            if (e2 == null) {
                LogUtil.b("Track_DetailFrag", "heartZoneConf is null in getAfterSportOverallEvaluation");
                return "";
            }
            int maxThreshold = e2.getMaxThreshold();
            if (requestAvgHeartRate != 0 && maxThreshold != 0) {
                return b(intValue, hours, a((requestAvgHeartRate * 1.0f) / maxThreshold));
            }
        }
        return "";
    }

    private int a(float f2) {
        if (f2 >= 1.0f) {
            LogUtil.h("Track_DetailFrag", "getExerciseIntensity input is invalid.");
        }
        double d2 = f2;
        if (d2 < 0.5d) {
            return 0;
        }
        if (d2 < 0.6d) {
            return 1;
        }
        if (d2 < 0.7d) {
            return 2;
        }
        if (d2 < 0.8d) {
            return 3;
        }
        return d2 < 0.9d ? 4 : 5;
    }

    private String c(int i2, int i3) {
        if (i2 <= 35) {
            if (i3 == 0) {
                return this.c.getString(R.string._2130849931_res_0x7f02308b);
            }
            if (i3 == 1) {
                return this.c.getString(R.string._2130849932_res_0x7f02308c);
            }
            if (i3 == 2) {
                return this.c.getString(R.string._2130849933_res_0x7f02308d);
            }
            LogUtil.h("Track_DetailFrag", "getStringByWearSportData no match string.");
        } else if (i3 >= 3) {
            return this.c.getString(R.string._2130849934_res_0x7f02308e);
        }
        return "";
    }

    private String b(float f2, int i2, int i3) {
        if (f2 < 1.0f) {
            return "";
        }
        if (f2 < 2.0f) {
            return c(i2, i3);
        }
        if (f2 < 3.0f) {
            return d(i2, i3);
        }
        if (f2 < 4.0f) {
            return b(i2, i3);
        }
        if (f2 < 5.0f) {
            return e(i2, i3);
        }
        return this.c.getString(R.string._2130839858_res_0x7f020932);
    }

    private String d(int i2, int i3) {
        StringBuilder sb = new StringBuilder(16);
        a(i2, i3, sb);
        return sb.toString();
    }

    private void a(int i2, int i3, StringBuilder sb) {
        if (i3 == 0) {
            if (i2 <= 53) {
                sb.append(this.c.getString(R.string._2130849923_res_0x7f023083));
            }
        } else if (i3 == 1) {
            if (i2 <= 53) {
                sb.append(this.c.getString(R.string._2130849924_res_0x7f023084));
            }
        } else if (i3 == 2) {
            c(i2, sb, R.string._2130849925_res_0x7f023085, R.string._2130849925_res_0x7f023085);
        } else if (i3 == 3) {
            c(i2, sb, R.string._2130849926_res_0x7f023086, R.string._2130849926_res_0x7f023086);
        } else if (i2 > 53) {
            sb.append(this.c.getString(R.string._2130849922_res_0x7f023082));
        }
    }

    private void c(int i2, StringBuilder sb, int i3, int i4) {
        if (i2 <= 35) {
            sb.append(this.c.getString(i3));
        } else if (i2 <= 53) {
            sb.append(this.c.getString(i4));
            sb.append(hji.c(i2, this.f3732a.e().requestEndTime(), this.c, this.p));
        } else {
            LogUtil.h("Track_DetailFrag", "getAfterSportSummaryTwoTrainEffect unused branch.");
        }
    }

    private String b(int i2, int i3) {
        String string;
        StringBuilder sb = new StringBuilder(16);
        if (i3 <= 1) {
            string = this.c.getString(R.string._2130849917_res_0x7f02307d);
        } else if (i3 == 2) {
            string = this.c.getString(R.string._2130849919_res_0x7f02307f);
        } else if (i3 == 3) {
            string = this.c.getString(R.string._2130849920_res_0x7f023080);
        } else if (i3 == 4) {
            string = this.c.getString(R.string._2130849918_res_0x7f02307e);
        } else {
            string = this.c.getString(R.string._2130849921_res_0x7f023081);
        }
        sb.append(string);
        if (i2 <= 35 || i2 > 96) {
            LogUtil.c("Track_DetailFrag", "getAfterSportSummaryFourTrainEffect unused branch.");
            return sb.toString();
        }
        sb.append(hji.c(i2, this.f3732a.e().requestEndTime(), this.c, this.p));
        return sb.toString();
    }

    private String e(int i2, int i3) {
        String string;
        StringBuilder sb = new StringBuilder(16);
        if (i3 <= 2) {
            string = this.c.getString(R.string._2130849927_res_0x7f023087);
        } else if (i3 == 3) {
            string = this.c.getString(R.string._2130849929_res_0x7f023089);
        } else if (i3 == 4) {
            string = this.c.getString(R.string._2130849928_res_0x7f023088);
        } else {
            string = this.c.getString(R.string._2130849930_res_0x7f02308a);
        }
        sb.append(string);
        if (i2 <= 35 || i2 > 96) {
            LogUtil.c("Track_DetailFrag", "getAfterSportSummaryFourTrainEffect unused branch.");
            return sb.toString();
        }
        sb.append(hji.c(i2, this.f3732a.e().requestEndTime(), this.c, this.p));
        return sb.toString();
    }

    private String c(long j2) {
        return nsj.c(this.c, j2, 131091);
    }

    private Calendar b(int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.f3732a.e().requestEndTime());
        calendar.add(10, i2);
        return calendar;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        hkd hkdVar = this.cb;
        if (hkdVar != null) {
            hkdVar.e(this.f3732a);
        }
    }

    private void a(HealthTextView healthTextView) {
        if (LanguageUtil.h(this.c) || LanguageUtil.p(this.c)) {
            LogUtil.h("Track_DetailFrag", "isChinese or isEnglish()");
        } else {
            healthTextView.setAutoTextInfo(9, 1, 2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        hkd hkdVar = this.cb;
        if (hkdVar != null) {
            hkdVar.b();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportResultBaseFragment
    protected String d() {
        return "Track_DetailFrag";
    }
}
