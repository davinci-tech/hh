package com.huawei.health.suggestion.ui.plan.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.SparseBooleanArray;
import android.util.SparseLongArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.marketing.views.IViewVisibilityChange;
import com.huawei.health.plan.model.intplan.ReplacePlanBean;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.health.suggestion.ui.plan.adapter.AiDietRecordAdapter;
import com.huawei.health.suggestion.ui.plan.adapter.CourseAdapter;
import com.huawei.health.suggestion.ui.plan.adapter.CourseRecordAdapter;
import com.huawei.health.suggestion.ui.plan.adapter.RecommendFoodPagerAdapter;
import com.huawei.health.suggestion.ui.plan.fragment.AiFatReductionPlanFragment;
import com.huawei.health.suggestion.ui.plan.util.RecommendFoodTransformer;
import com.huawei.health.suggestion.ui.plan.util.WeekPlanAdjustHelper;
import com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.weight.WeightApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.calendarview.HealthCalendarView;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.commonui.viewpager.HealthNativeViewPager;
import com.huawei.ui.commonui.viewpager.pagetransformer.OverlayLayerPageTransformer;
import com.huawei.ui.main.stories.health.weight.callback.WeightCallback;
import defpackage.ase;
import defpackage.bzs;
import defpackage.cau;
import defpackage.cfi;
import defpackage.dph;
import defpackage.ffl;
import defpackage.fis;
import defpackage.fiu;
import defpackage.fnz;
import defpackage.fuo;
import defpackage.fxu;
import defpackage.fyo;
import defpackage.fys;
import defpackage.fyv;
import defpackage.fyw;
import defpackage.fze;
import defpackage.gds;
import defpackage.gge;
import defpackage.ggr;
import defpackage.ggu;
import defpackage.gib;
import defpackage.gsd;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.koq;
import defpackage.kot;
import defpackage.moj;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qtm;
import defpackage.quh;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class AiFatReductionPlanFragment extends BaseFragment implements CourseAdapter.OnItemClickListener, ViewPager.OnPageChangeListener, IViewVisibilityChange {
    private LinearLayout aa;
    private HealthButton ac;
    private HealthCardView ad;
    private HealthNativeViewPager ae;
    private HealthTextView af;
    private AiDietRecordAdapter ah;
    private HealthTextView ai;
    private HealthCalendarView ak;
    private WeakReference<AiPlanWeekDetailViewHolder> al;
    private fuo am;
    private IntAction ao;
    private boolean ap;
    private IntPlan aq;
    private HealthTextView ar;
    private HealthProgressBar as;
    private boolean au;
    private boolean av;
    private boolean ax;
    private HealthImageView b;
    private boolean ba;
    private boolean bb;
    private boolean bc;
    private String bd;
    private String be;
    private SparseLongArray bg;
    private int bh;
    private HealthTextView bi;
    private HealthTextView bj;
    private HealthTextView bk;
    private Guideline bl;
    private HealthRecycleView bo;
    private HealthTextView bp;
    private HealthTextView bq;
    private HealthImageView br;
    private HealthTextView bs;
    private HealthImageView bt;
    private NoTitleCustomAlertDialog bu;
    private HealthTextView bv;
    private HealthTextView bw;
    private int bx;
    private int by;
    private int bz;
    private int c;
    private float ca;
    private WeekPlanAdjustHelper cd;
    private CustomAlertDialog d;
    private Context e;
    private CourseAdapter f;
    private int g;
    private CourseAdapter h;
    private CourseRecordAdapter j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthImageView m;
    private LinearLayout n;
    private HealthButton o;
    private HealthTextView p;
    private LinearLayout r;
    private ConstraintLayout s;
    private HealthTextView v;
    private int w;
    private HealthTextView x;
    private LinearLayout y;
    private RecommendFoodPagerAdapter z;
    private final Resources bn = BaseApplication.e().getResources();
    private final b aj = new b(this);
    private final Map<String, FitWorkout> ag = new ConcurrentHashMap();
    private final List<RecordData> bm = new CopyOnWriteArrayList();
    private final SparseBooleanArray aw = new SparseBooleanArray();
    private final Context i = BaseApplication.e();
    private final AtomicBoolean at = new AtomicBoolean(true);
    private List<Integer> cb = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f3255a = new CopyOnWriteArrayList();
    private List<IntAction> t = new CopyOnWriteArrayList();
    private List<IntAction> q = new CopyOnWriteArrayList();
    private double ay = 65.0d;
    private double az = 20.0d;
    private int u = -1;
    private int an = 0;
    private int ab = -1;
    private final Observer bf = new Observer() { // from class: fvt
        @Override // com.huawei.haf.design.pattern.Observer
        public final void notify(String str, Object[] objArr) {
            AiFatReductionPlanFragment.this.d(str, objArr);
        }
    };

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public /* synthetic */ void d(String str, Object[] objArr) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -1338826912:
                if (str.equals("EXIT_APP")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -162921225:
                if (str.equals("PLAN_ON_RESUME")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1241300171:
                if (str.equals("SPORT_ENTRANCE_IS_VISIBLE_TO_USER")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1264861613:
                if (str.equals("WATCH_PLAN_REPORT_DIALOG")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            g();
            return;
        }
        if (c2 == 1) {
            q();
            return;
        }
        if (c2 != 2) {
            if (c2 == 3) {
                HealthImageView healthImageView = this.br;
                if (healthImageView != null) {
                    healthImageView.setVisibility(8);
                    return;
                }
                return;
            }
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "mObserver label ", str, " objects ", objArr);
            return;
        }
        if (objArr == null || objArr.length <= 0) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "mObserver objects ", objArr);
            return;
        }
        Object obj = objArr[0];
        if (!(obj instanceof Boolean)) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "mObserver object ", obj);
            return;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        this.bc = booleanValue;
        if (booleanValue) {
            w();
            p();
            c();
            ad();
            q();
            a(this.u);
            return;
        }
        r();
    }

    public AiFatReductionPlanFragment() {
    }

    public AiFatReductionPlanFragment(fuo fuoVar) {
        this.am = fuoVar;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.c("Suggestion_AiFatReductionPlanFragment", "onCreate");
        this.bc = Boolean.parseBoolean(SharedPreferenceManager.b(this.i, Integer.toString(20002), "EXIT_APP_AT_SPORT_TAB"));
        fuo fuoVar = this.am;
        if (fuoVar != null) {
            this.ba = fuoVar.c();
            this.am.b(this);
        }
        ObserverManagerUtil.d(this.bf, "EXIT_APP");
        ObserverManagerUtil.d(this.bf, "PLAN_ON_RESUME");
        ObserverManagerUtil.d(this.bf, "SPORT_ENTRANCE_IS_VISIBLE_TO_USER");
        ObserverManagerUtil.d(this.bf, "WATCH_PLAN_REPORT_DIALOG");
        ArrayList arrayList = new ArrayList();
        arrayList.add(100);
        arrayList.add(23);
        HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(arrayList, new a(this));
        this.ca = fnz.e();
        this.bx = fnz.b();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sug_layout_ai_fat_reduction_plan, viewGroup, false);
        LogUtil.c("Suggestion_AiFatReductionPlanFragment", "onCreateView");
        aHk_(inflate);
        aHl_(inflate);
        aHm_(inflate);
        this.bi = (HealthTextView) inflate.findViewById(R.id.plan_tip);
        t();
        return inflate;
    }

    private void aHn_(View view, int i, BaseRecyclerAdapter baseRecyclerAdapter) {
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(i);
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this.i));
        healthRecycleView.setIsConsumption(true);
        healthRecycleView.setIsScroll(false);
        healthRecycleView.setAdapter(baseRecyclerAdapter);
    }

    private void aHk_(View view) {
        this.s = (ConstraintLayout) view.findViewById(R.id.course_layout);
        CourseAdapter courseAdapter = new CourseAdapter(true);
        this.f = courseAdapter;
        courseAdapter.b(this);
        aHn_(view, R.id.course_recycler_in_plan, this.f);
        this.y = (LinearLayout) view.findViewById(R.id.course_status_layout);
        this.bt = (HealthImageView) view.findViewById(R.id.course_card_status_image);
        this.bv = (HealthTextView) view.findViewById(R.id.course_card_status_text);
        this.x = (HealthTextView) view.findViewById(R.id.course_recycler_out_plan_title);
        CourseAdapter courseAdapter2 = new CourseAdapter(false);
        this.h = courseAdapter2;
        courseAdapter2.b(this);
        aHn_(view, R.id.course_recycler_out_plan, this.h);
        CourseRecordAdapter courseRecordAdapter = new CourseRecordAdapter();
        this.j = courseRecordAdapter;
        aHn_(view, R.id.course_recycler_out_plan_record, courseRecordAdapter);
        this.r = (LinearLayout) view.findViewById(R.id.course_fold_layout);
        this.p = (HealthTextView) view.findViewById(R.id.course_fold_text);
        this.m = (HealthImageView) view.findViewById(R.id.course_fold_image);
        this.n = (LinearLayout) view.findViewById(R.id.course_event_layout);
        this.k = (HealthTextView) view.findViewById(R.id.course_event_text);
        this.o = (HealthButton) view.findViewById(R.id.course_event_button);
        this.v = (HealthTextView) view.findViewById(R.id.course_tip);
        this.l = (HealthTextView) view.findViewById(R.id.course_add_button);
    }

    private void aHl_(View view) {
        this.ad = (HealthCardView) view.findViewById(R.id.diet_card);
        this.af = (HealthTextView) view.findViewById(R.id.diet_recipe_recommendations_title);
        HealthNativeViewPager healthNativeViewPager = (HealthNativeViewPager) view.findViewById(R.id.diet_recipe_recommendations_pager);
        this.ae = healthNativeViewPager;
        healthNativeViewPager.setPageTransformer(true, new RecommendFoodTransformer(new OverlayLayerPageTransformer.b(3).e(0.85f).e(85).b(150).c(150), 3));
        this.ae.setOffscreenPageLimit(3);
        this.ae.addOnPageChangeListener(this);
        this.z = new RecommendFoodPagerAdapter();
        this.aa = (LinearLayout) view.findViewById(R.id.diet_analysis);
        this.ac = (HealthButton) view.findViewById(R.id.diet_analysis_button);
        this.ah = new AiDietRecordAdapter();
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.diet_record_recycler);
        this.bo = healthRecycleView;
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this.i));
        this.bo.setIsConsumption(true);
        this.bo.setIsScroll(false);
        this.bo.setAdapter(this.ah);
    }

    private void aHm_(View view) {
        aHj_(view);
    }

    private void aa() {
        b(gsd.i());
        kot.a().c(new ResponseCallback() { // from class: fvq
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                AiFatReductionPlanFragment.this.b(i, (Float) obj);
            }
        });
    }

    public /* synthetic */ void b(int i, Float f) {
        LogUtil.c("Suggestion_AiFatReductionPlanFragment", "setupTargetWeight errorCode ", Integer.valueOf(i), " floatData ", f);
        b(f == null ? 0.0f : f.floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(final float f) {
        String str;
        if (!HandlerExecutor.b()) {
            HandlerExecutor.e(new Runnable() { // from class: fux
                @Override // java.lang.Runnable
                public final void run() {
                    AiFatReductionPlanFragment.this.b(f);
                }
            });
            return;
        }
        boolean h = UnitUtil.h();
        if (f <= 0.0f) {
            str = "--";
        } else if (h) {
            str = UnitUtil.e(UnitUtil.h(f), 1, 1);
        } else {
            str = UnitUtil.e(f, 1, 1);
        }
        this.bw.setText(str);
        this.bs.setText(nsf.h(h ? R.string._2130841559_res_0x7f020fd7 : R.string._2130841560_res_0x7f020fd8));
    }

    private void ac() {
        c(gsd.g());
        ThreadPoolManager.d().execute(new Runnable() { // from class: fuv
            @Override // java.lang.Runnable
            public final void run() {
                AiFatReductionPlanFragment.this.a();
            }
        });
    }

    public /* synthetic */ void a() {
        c(j());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void c(final float f) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.e(new Runnable() { // from class: fus
                @Override // java.lang.Runnable
                public final void run() {
                    AiFatReductionPlanFragment.this.c(f);
                }
            });
            return;
        }
        if (f <= 0.0f) {
            this.bp.setText(R.string._2130851561_res_0x7f0236e9);
            ReleaseLogUtil.c("Suggestion_AiFatReductionPlanFragment", "cannot get start weight");
        } else if (UnitUtil.h()) {
            this.bq.setText(R.string._2130841559_res_0x7f020fd7);
            this.bp.setText(UnitUtil.e(UnitUtil.h(f), 1, 1));
        } else {
            this.bq.setText(R.string._2130841560_res_0x7f020fd8);
            this.bp.setText(UnitUtil.e(f, 1, 1));
        }
    }

    private void aHj_(View view) {
        BitmapDrawable cKm_;
        this.ai = (HealthTextView) view.findViewById(R.id.fat_rate_value);
        this.bw = (HealthTextView) view.findViewById(R.id.target_weight_value);
        this.bs = (HealthTextView) view.findViewById(R.id.target_weight_unit);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.target_weight_layout);
        this.bp = (HealthTextView) view.findViewById(R.id.start_weight_value);
        this.bq = (HealthTextView) view.findViewById(R.id.start_weight_unit);
        this.as = (HealthProgressBar) view.findViewById(R.id.sug_current_plan_progress_item_horizontal);
        this.ar = (HealthTextView) view.findViewById(R.id.int_plan_progress_text);
        this.bl = (Guideline) view.findViewById(R.id.progress_guide_line);
        this.bk = (HealthTextView) view.findViewById(R.id.plan_completed_days_and_rate);
        HealthImageView healthImageView = (HealthImageView) view.findViewById(R.id.ai_fitness_plan_edit_btn);
        this.b = healthImageView;
        healthImageView.setOnClickListener(new View.OnClickListener() { // from class: fup
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AiFatReductionPlanFragment.this.aHs_(view2);
            }
        });
        if (LanguageUtil.bc(this.i) && (cKm_ = nrz.cKm_(this.i, nsf.cKq_(R.drawable._2131428053_res_0x7f0b02d5))) != null) {
            this.b.setImageDrawable(cKm_);
        }
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: fur
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AiFatReductionPlanFragment.this.aHt_(view2);
            }
        });
        this.bj = (HealthTextView) view.findViewById(R.id.plan_report_btn);
        this.br = (HealthImageView) view.findViewById(R.id.report_dot);
    }

    public /* synthetic */ void aHs_(View view) {
        z();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aHt_(View view) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), AnalyticsValue.INT_PLAN_1120033.value(), hashMap, 0);
        JumpUtil.a(this.e, "8");
        ViewClickInstrumentation.clickOnView(view);
    }

    private float j() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("custom.start_weight_base");
        float j = userPreference == null ? 0.0f : CommonUtil.j(userPreference.getValue());
        gsd.d(j);
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        HiAggregateOption h = h();
        if (h == null) {
            ReleaseLogUtil.c("R_Suggestion_AiFatReductionPlanFragment", "aggregateOption is null");
        } else {
            g(gsd.b());
            HiHealthManager.d(getContext()).aggregateHiHealthData(h, new c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(final float f) {
        HandlerExecutor.e(new Runnable() { // from class: fve
            @Override // java.lang.Runnable
            public final void run() {
                AiFatReductionPlanFragment.this.e(f);
            }
        });
    }

    public /* synthetic */ void e(float f) {
        if (f > 0.0f) {
            this.ai.setText(aHi_(f));
        } else {
            this.ai.setText(R.string._2130851561_res_0x7f0236e9);
        }
    }

    static class c implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<AiFatReductionPlanFragment> f3259a;

        private c(AiFatReductionPlanFragment aiFatReductionPlanFragment) {
            this.f3259a = new WeakReference<>(aiFatReductionPlanFragment);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            float f;
            if (koq.c(list)) {
                Iterator<HiHealthData> it = list.iterator();
                while (it.hasNext()) {
                    f = (float) it.next().getDouble(BleConstants.BODY_FAT_RATE);
                    if (dph.j(f)) {
                        gsd.c(f);
                        break;
                    }
                }
            }
            f = 0.0f;
            AiFatReductionPlanFragment aiFatReductionPlanFragment = this.f3259a.get();
            if (aiFatReductionPlanFragment != null) {
                aiFatReductionPlanFragment.g(f);
            } else {
                LogUtil.a("Suggestion_AiFatReductionPlanFragment", "aiFatReductionPlanFragment is null");
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("Suggestion_AiFatReductionPlanFragment", "errorCode intentType", Integer.valueOf(i2));
        }
    }

    private SpannableString aHi_(double d2) {
        String e2 = UnitUtil.e(d2, 1, 1);
        SpannableString spannableString = new SpannableString(UnitUtil.e(d2, 2, 1));
        if (e2.length() <= spannableString.length()) {
            spannableString.setSpan(new TextAppearanceSpan(this.i, R.style.weight_body_index_value_text_style), 0, e2.length(), 33);
            spannableString.setSpan(new TextAppearanceSpan(this.i, R.style.weight_body_index_unit_text_style), e2.length(), spannableString.length(), 33);
        }
        return spannableString;
    }

    private static String e(cfi cfiVar) {
        if (cfiVar == null) {
            return "NULL";
        }
        String i = cfiVar.i();
        String i2 = MultiUsersManager.INSTANCE.getMainUser().i();
        return (TextUtils.isEmpty(i) || TextUtils.isEmpty(i2) || TextUtils.equals(i, i2)) ? "NULL" : i;
    }

    private HiAggregateOption h() {
        IntPlan intPlan = this.aq;
        if (intPlan == null || intPlan.getPlanTimeInfo() == null) {
            ReleaseLogUtil.c("R_Suggestion_AiFatReductionPlanFragment", "int plan or time info is null, return");
            return null;
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(DateFormatUtil.c(ase.h(this.aq)), System.currentTimeMillis());
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.BODY_FAT_RATE});
        hiAggregateOption.setFilter(e(MultiUsersManager.INSTANCE.getMainUser()));
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        LogUtil.c("Suggestion_AiFatReductionPlanFragment", "aggregateOption = ", hiAggregateOption.toString());
        return hiAggregateOption;
    }

    private void x() {
        IntPlan intPlan = this.aq;
        if (intPlan == null) {
            LogUtil.a("Suggestion_AiFatReductionPlanFragment", "int plan is null");
            return;
        }
        ggu.aMv_(intPlan, this.bj, this.br);
        aa();
        ac();
        v();
        u();
        y();
        this.b.setVisibility(this.bb ? 0 : 8);
    }

    private void u() {
        IntPlan intPlan = this.aq;
        if (intPlan == null || intPlan.getStat("progress") == null) {
            ReleaseLogUtil.a("Suggestion_AiFatReductionPlanFragment", "setupProgressBar mIntPlan == null || mIntPlan.getStat(StatInfo.STAT_TYPE_PROGRESS) == null");
            return;
        }
        float floatValue = ((Float) this.aq.getStat("progress").getValue()).floatValue();
        if (floatValue > 0.0f && floatValue <= 6.0f) {
            this.as.setProgress(6);
            this.ar.setVisibility(8);
            return;
        }
        this.as.setProgress(Math.round(floatValue));
        this.ar.setVisibility(0);
        this.ar.setText(UnitUtil.e(floatValue, 2, 0));
        if (nsn.t() || nsn.a(3.1f)) {
            LogUtil.c("Suggestion_AiFatReductionPlanFragment", "adjust font size");
            this.ar.setTextSize(1, 12.0f);
        }
        ViewGroup.LayoutParams layoutParams = this.bl.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).guidePercent = floatValue / 100.0f;
            this.bl.setLayoutParams(layoutParams);
        }
    }

    private void v() {
        long currentTimeMillis;
        HealthCalendarView healthCalendarView = this.ak;
        if (healthCalendarView != null && healthCalendarView.getSelectedCalendar() != null) {
            currentTimeMillis = this.ak.getSelectedCalendar().transformCalendar().getTimeInMillis();
        } else {
            currentTimeMillis = System.currentTimeMillis();
        }
        this.bk.setText(ggu.d(currentTimeMillis, this.aq));
    }

    public void a(List<List<fiu>> list, quh quhVar, long j) {
        b(list, quhVar, j);
        e(quhVar, j);
        c(list, quhVar, j);
        w();
    }

    private void b(List<List<fiu>> list, quh quhVar, long j) {
        HealthNativeViewPager healthNativeViewPager = this.ae;
        if (healthNativeViewPager == null || this.z == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initDietPager mDietViewPager ", healthNativeViewPager, " mDietPagerAdapter ", this.z);
            return;
        }
        if (!ase.e()) {
            HealthTextView healthTextView = this.af;
            if (healthTextView != null) {
                healthTextView.setText(nsf.h(R.string._2130845505_res_0x7f021f41));
                return;
            }
            return;
        }
        RecommendFoodPagerAdapter recommendFoodPagerAdapter = this.z;
        IntPlan intPlan = this.aq;
        WeakReference<AiPlanWeekDetailViewHolder> weakReference = this.al;
        recommendFoodPagerAdapter.d(list, quhVar, intPlan, weakReference == null ? null : weakReference.get(), j);
        this.ae.setAdapter(this.z);
        if (koq.c(list)) {
            this.an = (this.z.getCount() / list.size()) + fyv.c(list, quhVar, j);
            HealthNativeViewPager healthNativeViewPager2 = this.ae;
            if (healthNativeViewPager2 != null) {
                healthNativeViewPager2.setVisibility(0);
            }
        }
        LogUtil.c("Suggestion_AiFatReductionPlanFragment", "initDietPager:", Integer.valueOf(this.an), " mCurrentPosition：", Integer.valueOf(this.u));
        int i = this.u;
        if (i >= 0 && i < this.z.getCount()) {
            this.ae.setCurrentItem(this.u);
        } else {
            this.ae.setCurrentItem(this.an);
        }
    }

    private void e(quh quhVar, final long j) {
        if (this.aa == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initDietAnalysis mDietAnalysis is null");
            return;
        }
        if (!fyv.c(quhVar, j) || !ase.b()) {
            this.aa.setVisibility(8);
            return;
        }
        this.aa.setVisibility(0);
        HealthButton healthButton = this.ac;
        if (healthButton == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initDietAnalysis mDietAnalysisButton is null");
        } else {
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: fuw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AiFatReductionPlanFragment.this.aHu_(j, view);
                }
            });
        }
    }

    public /* synthetic */ void aHu_(long j, View view) {
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initDietAnalysis api is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addGlobalBiParam(WebViewHelp.BI_KEY_PULL_FROM, "weightControlPlan").addPath("#/DietAnalysis?date=" + (jdl.t(j) / 1000) + "&from=3");
        bzs.e().loadH5ProApp(this.e, "com.huawei.health.h5.diet-diary", builder);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(List<List<fiu>> list, quh quhVar, final long j) {
        AiDietRecordAdapter aiDietRecordAdapter = this.ah;
        if (aiDietRecordAdapter == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initDietRecord mDietRecordAdapter is null");
        } else {
            aiDietRecordAdapter.a(list, quhVar, j);
            this.ah.e(new AiDietRecordAdapter.OnItemClickListener() { // from class: fvo
                @Override // com.huawei.health.suggestion.ui.plan.adapter.AiDietRecordAdapter.OnItemClickListener
                public final void onItemClick(int i) {
                    AiFatReductionPlanFragment.c(j, i);
                }
            });
        }
    }

    public static /* synthetic */ void c(long j, int i) {
        JumpUtil.c(jdl.t(j), i, 0);
        HandlerExecutor.d(new Runnable() { // from class: fvc
            @Override // java.lang.Runnable
            public final void run() {
                ary.a().e("PLAN_UPDATE");
            }
        }, 2000L);
    }

    private void r() {
        this.aj.removeMessages(30);
        if (this.ae != null && this.an > 0 && n()) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "resetRecommendFood mInitPosition ", Integer.valueOf(this.an), " mCurrentPosition ", Integer.valueOf(this.u));
            this.ae.setCurrentItem(this.an);
        }
        this.at.set(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.aj.removeMessages(30);
        if (i <= -1) {
            return;
        }
        this.u = i;
        this.aj.sendEmptyMessageDelayed(30, this.au ? PreConnectManager.CONNECT_INTERNAL : 4000L);
        this.au = false;
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.aj.removeMessages(30);
        if (this.ae != null && this.bc && this.ba && this.av && !n()) {
            this.ae.setCurrentItem(this.u + 1);
        }
    }

    private boolean aHo_(View view) {
        HealthTextView healthTextView = this.af;
        boolean cKv_ = (healthTextView == null || healthTextView.getVisibility() != 0) ? false : ScrollUtil.cKv_(view, this.af);
        if (!cKv_) {
            return false;
        }
        HealthNativeViewPager healthNativeViewPager = this.ae;
        if (healthNativeViewPager != null && healthNativeViewPager.getVisibility() == 0) {
            cKv_ = ScrollUtil.cKv_(view, this.ae);
        }
        if (!cKv_) {
            return false;
        }
        LinearLayout linearLayout = this.aa;
        if (linearLayout != null && linearLayout.getVisibility() == 0) {
            return ScrollUtil.cKv_(view, this.aa);
        }
        HealthRecycleView healthRecycleView = this.bo;
        if (healthRecycleView == null || healthRecycleView.getVisibility() != 0) {
            return false;
        }
        return ScrollUtil.cKv_(view, this.bo);
    }

    private boolean n() {
        RecommendFoodPagerAdapter recommendFoodPagerAdapter = this.z;
        if (recommendFoodPagerAdapter == null || this.u < recommendFoodPagerAdapter.getCount() - 1) {
            return false;
        }
        ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "isLastPosition mCurrentPosition ", Integer.valueOf(this.u), " mDietPagerAdapter ", this.z);
        return true;
    }

    public void c(Context context, IntPlan intPlan, AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder, List<Integer> list, HealthRecycleView healthRecycleView) {
        this.e = context;
        this.aq = intPlan;
        this.f3255a = list;
        WeakReference<AiPlanWeekDetailViewHolder> weakReference = new WeakReference<>(aiPlanWeekDetailViewHolder);
        this.al = weakReference;
        AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder2 = weakReference.get();
        if (aiPlanWeekDetailViewHolder2 != null) {
            this.ak = aiPlanWeekDetailViewHolder2.d();
        }
        if (getView() != null) {
            t();
        }
        if (healthRecycleView == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "setData recyclerView is null");
        } else {
            healthRecycleView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: fuz
                @Override // android.view.View.OnScrollChangeListener
                public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                    AiFatReductionPlanFragment.this.aHy_(view, i, i2, i3, i4);
                }
            });
            healthRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.plan.fragment.AiFatReductionPlanFragment.5
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i == 0 && AiFatReductionPlanFragment.this.av) {
                        AiFatReductionPlanFragment aiFatReductionPlanFragment = AiFatReductionPlanFragment.this;
                        aiFatReductionPlanFragment.a(aiFatReductionPlanFragment.u);
                    }
                }
            });
        }
    }

    public /* synthetic */ void aHy_(View view, int i, int i2, int i3, int i4) {
        this.av = aHo_(view);
    }

    public void e() {
        this.al = null;
    }

    private void t() {
        WeakReference<AiPlanWeekDetailViewHolder> weakReference = this.al;
        AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder = weakReference == null ? null : weakReference.get();
        if (aiPlanWeekDetailViewHolder == null || this.aq == null || koq.b(this.f3255a, 3)) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "setCourseData holder ", aiPlanWeekDetailViewHolder, " mIntPlan ", this.aq, " mCalenderList ", this.f3255a);
            return;
        }
        this.by = DateFormatUtil.b(System.currentTimeMillis());
        this.bz = this.f3255a.get(1).intValue();
        int intValue = this.f3255a.get(3).intValue();
        this.w = intValue;
        long c2 = fyo.c(this.aq, this.bz, intValue);
        int b2 = DateFormatUtil.b(c2);
        this.bb = b2 == this.by;
        this.ap = fyw.a(c2);
        if (!fys.a(this.aq, this.bz, this.w)) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "setCourseData mIntPlan ", this.aq, " mWeekNumber ", Integer.valueOf(this.bz), " mDayNumber ", Integer.valueOf(this.w));
            return;
        }
        if (this.aw.indexOfKey(b2) < 0) {
            this.aw.append(b2, true);
        }
        this.ab = ase.a(this.aq.getDayInfo(this.bz, this.w));
        int f = f();
        l();
        c(f);
        k();
        m();
        o();
        x();
        b(f);
        p();
        fys.c(this.aq, (ResponseCallback<Boolean>) new ResponseCallback() { // from class: fvl
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                AiFatReductionPlanFragment.this.e(i, (Boolean) obj);
            }
        });
    }

    public /* synthetic */ void e(int i, Boolean bool) {
        if (i != 0 || bool == null) {
            return;
        }
        this.ax = bool.booleanValue();
        ad();
    }

    private void w() {
        LogUtil.d("Suggestion_AiFatReductionPlanFragment", "setEnterDietEvent mIsToday ", Boolean.valueOf(this.bb), " mIsVisibleSportEntrance:", Boolean.valueOf(this.bc), " mIsVisibleToUser:", Boolean.valueOf(this.ba), " mIsSupportLoopRecommendFood:", Boolean.valueOf(this.av), " mIsEnterDietEvent:", Boolean.valueOf(this.at.get()));
        if (this.bb && this.bc && this.ba && this.av && this.at.get()) {
            ggr.b();
            this.at.set(false);
        }
    }

    private void p() {
        LogUtil.d("Suggestion_AiFatReductionPlanFragment", "setCourseEvent mIsToday ", Boolean.valueOf(this.bb), " mIsVisibleSportEntrance:", Boolean.valueOf(this.bc), " mIsVisibleToUser:", Boolean.valueOf(this.ba));
        if (this.bb && this.bc && this.ba) {
            ggr.c(this.t, this.q, this.bm, this.ag);
        }
    }

    private void z() {
        CustomAlertDialog a2 = fze.a(this.e, this.aq, (ResponseCallback<Object>) new ResponseCallback() { // from class: fvd
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                AiFatReductionPlanFragment.this.b(i, obj);
            }
        });
        this.d = a2;
        if (a2 == null || a2.isShowing()) {
            return;
        }
        this.d.show();
        ggr.d(1, 0, 0);
    }

    public /* synthetic */ void b(int i, Object obj) {
        if (i == 0) {
            g(i);
        } else if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    c(obj);
                } else if (i == 4) {
                    if (this.cd == null) {
                        this.cd = new WeekPlanAdjustHelper(this.e);
                    }
                    this.cd.c(this.aq);
                } else {
                    ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "showAdjustDialog clickType ", Integer.valueOf(i), " object ", obj);
                }
            } else if (obj == null) {
                nrh.b(this.i, R.string._2130848820_res_0x7f022c34);
            } else if (fyw.o(this.aq)) {
                nrh.b(this.i, R.string._2130848811_res_0x7f022c2b);
            } else if (fyw.j(this.aq)) {
                nrh.b(this.i, R.string._2130848834_res_0x7f022c42);
            } else {
                b(0, 0);
            }
        } else if (obj == null) {
            nrh.b(this.i, R.string._2130848809_res_0x7f022c29);
            ggr.d(1, 3, 0);
        } else if (fyw.o(this.aq)) {
            nrh.b(this.i, R.string._2130848811_res_0x7f022c2b);
        } else if (fyw.j(this.aq)) {
            nrh.b(this.i, R.string._2130848834_res_0x7f022c42);
        } else {
            ggr.d(1, 1, 0);
            g(i);
        }
        CustomAlertDialog customAlertDialog = this.d;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
    }

    private void g(int i) {
        if (!CommonUtil.aa(this.i)) {
            nrh.b(this.i, R.string._2130841762_res_0x7f0210a2);
            return;
        }
        if (i == 0) {
            fys.a(this.aq, DateFormatUtil.b(System.currentTimeMillis()), 0, new e(this, "TYPE_UPDATE_PLAN_CALENDAR_TRAIN"));
        } else if (i == 1) {
            fys.a(this.aq, DateFormatUtil.b(System.currentTimeMillis()), 1, new e(this, "TYPE_UPDATE_PLAN_CALENDAR_REST"));
        } else {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "updatePlanCalendar clickType ", Integer.valueOf(i));
        }
    }

    private void b(int i, int i2) {
        final boolean z = i != 0;
        ggr.d(z ? 2 : 1, 4, 0);
        fze.a(this.e, this.aq, i, i2, new ResponseCallback() { // from class: fvu
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i3, Object obj) {
                AiFatReductionPlanFragment.this.c(z, i3, (Integer) obj);
            }
        });
    }

    public /* synthetic */ void c(boolean z, int i, Integer num) {
        if (!CommonUtil.aa(this.i)) {
            nrh.b(this.i, R.string._2130841762_res_0x7f0210a2);
        } else {
            this.bh = num.intValue();
            fys.e(this.aq, DateFormatUtil.b(System.currentTimeMillis()), this.bh, new e(this, z ? "TYPE_LEAVE_MENSTRUAL" : "TYPE_LEAVE"));
        }
    }

    private void c(Object obj) {
        ggr.d(1, 6, 0);
        fze.b(this.e, obj, new ResponseCallback() { // from class: fuy
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj2) {
                AiFatReductionPlanFragment.this.c(i, obj2);
            }
        });
    }

    public /* synthetic */ void c(int i, Object obj) {
        if (!CommonUtil.aa(this.i)) {
            nrh.b(this.i, R.string._2130841762_res_0x7f0210a2);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (obj instanceof Calendar) {
            this.g = jdl.e(currentTimeMillis, ((Calendar) obj).getTimeInMillis());
        }
        fys.a(this.aq, DateFormatUtil.b(currentTimeMillis), (ResponseCallback<Object>) new e(this, "TYPE_CANCEL_LEAVE"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int f() {
        if (this.bb) {
            return 1;
        }
        return DateFormatUtil.b(fyo.c(this.aq, this.bz, this.w)) < this.by ? 0 : 3;
    }

    private void d(final int i, final CourseAdapter courseAdapter, final List<IntAction> list, final String str) {
        List<FitWorkout> arrayList = new ArrayList<>();
        if (this.bb) {
            if (cau.a(str + "time").equals(String.valueOf(this.by))) {
                arrayList = moj.b(cau.a(str), FitWorkout[].class);
                if (koq.c(arrayList)) {
                    a(arrayList, courseAdapter, list, i);
                }
            }
        }
        final List<FitWorkout> list2 = arrayList;
        if (koq.b(list)) {
            courseAdapter.c(this.ag, list, this.ap, i, this.ca);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        for (IntAction intAction : list) {
            if (intAction != null) {
                String actionId = intAction.getActionId();
                if (!TextUtils.isEmpty(actionId)) {
                    arrayList2.add(new ffl.d(actionId).e(this.aq.getPlanId()).b());
                }
            }
        }
        if (koq.b(arrayList2)) {
            courseAdapter.c(this.ag, list, this.ap, i, this.ca);
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initCourse api is null");
            courseAdapter.c(this.ag, list, this.ap, i, this.ca);
        } else {
            courseApi.getCourseByIds(arrayList2, false, new UiCallback<List<FitWorkout>>() { // from class: com.huawei.health.suggestion.ui.plan.fragment.AiFatReductionPlanFragment.1
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str2) {
                    ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initCourse errorCode ", Integer.valueOf(i2), " errorInfo ", str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitWorkout> list3) {
                    LogUtil.c("Suggestion_AiFatReductionPlanFragment", "onSuccess");
                    if (AiFatReductionPlanFragment.this.bb) {
                        if (koq.b(list2, list3, true)) {
                            LogUtil.a("Suggestion_AiFatReductionPlanFragment", "same data");
                            return;
                        }
                        String e2 = moj.e(list3);
                        LogUtil.c("Suggestion_AiFatReductionPlanFragment", "set cache ", e2, " cache time is ", Integer.valueOf(AiFatReductionPlanFragment.this.by));
                        cau.d(str + "time", String.valueOf(AiFatReductionPlanFragment.this.by));
                        cau.d(str, e2);
                    }
                    if (!koq.b(list3)) {
                        AiFatReductionPlanFragment.this.a(list3, courseAdapter, list, i);
                    } else {
                        ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initCourse list ", list3);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(final List<FitWorkout> list, final CourseAdapter courseAdapter, final List<IntAction> list2, final int i) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.e(new Runnable() { // from class: fvj
                @Override // java.lang.Runnable
                public final void run() {
                    AiFatReductionPlanFragment.this.a(list, courseAdapter, list2, i);
                }
            });
            return;
        }
        LogUtil.c("Suggestion_AiFatReductionPlanFragment", "setWorkouts");
        for (FitWorkout fitWorkout : list) {
            if (fitWorkout != null) {
                this.ag.put(fitWorkout.acquireId(), fitWorkout);
            }
        }
        courseAdapter.c(this.ag, list2, this.ap, i, this.ca);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (this.aq == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initCourse mIntPlan is null");
            return;
        }
        LogUtil.c("Suggestion_AiFatReductionPlanFragment", "initCourse");
        List<IntAction> b2 = fys.b(this.aq, this.bz, this.w);
        this.t = b2;
        d(i, this.f, b2, "inPlanCourse");
        List<IntAction> e2 = fys.e(this.aq, this.bz, this.w);
        this.q = e2;
        d(i, this.h, e2, "outPlanCourse");
    }

    private void l() {
        int i = this.ab;
        if (i == 0) {
            this.y.setVisibility(8);
            return;
        }
        if (i == 1) {
            this.y.setVisibility(0);
            this.bt.setImageResource(R.drawable._2131430241_res_0x7f0b0b61);
            this.bv.setText(R.string._2130848796_res_0x7f022c1c);
        } else {
            if (i == 2) {
                this.y.setVisibility(0);
                this.bt.setImageResource(R.drawable._2131430639_res_0x7f0b0cef);
                this.bv.setText(R.string._2130848797_res_0x7f022c1d);
                return;
            }
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initCourseStatus mDayStatus ", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.aq == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initCourseOutPlanRecordList mIntPlan is null");
        } else {
            this.c = koq.b(this.q) ? 0 : this.q.size();
            ase.e(this.aq, this.bz, this.w, new IBaseResponseCallback() { // from class: fvi
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    AiFatReductionPlanFragment.this.e(i, obj);
                }
            });
        }
    }

    public /* synthetic */ void e(int i, Object obj) {
        List<String> a2 = fys.a(this.t);
        List<String> a3 = fys.a(this.q);
        this.bm.clear();
        ArrayList arrayList = new ArrayList();
        for (RecordData recordData : (List) obj) {
            if (recordData != null && recordData.getSportType() > 0 && recordData.getIsInPlan() != 1) {
                String workoutId = recordData.getWorkoutId();
                if (!a2.contains(workoutId) && !a3.contains(workoutId)) {
                    this.bm.add(recordData);
                    arrayList.add(recordData);
                }
            }
        }
        LogUtil.c("Suggestion_AiFatReductionPlanFragment", "initCourseOutPlanRecordList recordDataList ", HiJsonUtil.e(arrayList), " courseIdInPlanList ", a2, " courseIdOutPlanList ", a3);
        e((List<RecordData>) arrayList);
    }

    private void e(final List<RecordData> list) {
        HandlerExecutor.e(new Runnable() { // from class: fvk
            @Override // java.lang.Runnable
            public final void run() {
                AiFatReductionPlanFragment.this.a(list);
            }
        });
    }

    public /* synthetic */ void a(final List list) {
        int size = koq.c(list) ? list.size() : 0;
        this.x.setVisibility((this.c > 0 || size > 0) ? 0 : 8);
        if (this.c + size > 3) {
            this.r.setVisibility(0);
            this.r.setOnClickListener(new View.OnClickListener() { // from class: fuu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AiFatReductionPlanFragment.this.aHA_(list, view);
                }
            });
            d((List<RecordData>) list);
        } else {
            this.h.d(size > 0);
            this.r.setVisibility(8);
            c((List<RecordData>) list);
        }
    }

    public /* synthetic */ void aHA_(List list, View view) {
        int b2 = DateFormatUtil.b(fyo.c(this.aq, this.bz, this.w));
        this.aw.append(b2, !r1.get(b2));
        d((List<RecordData>) list);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(List<RecordData> list) {
        this.j.a(list);
        this.j.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: fun
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public final void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                AiFatReductionPlanFragment.this.a(recyclerHolder, i, obj);
            }
        });
    }

    public /* synthetic */ void a(RecyclerHolder recyclerHolder, int i, Object obj) {
        if (!(obj instanceof RecordData)) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initCourseOutPlanRecord object ", obj);
            return;
        }
        RecordData recordData = (RecordData) obj;
        if (recordData.getSportType() == 10001) {
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi == null) {
                ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initCourseOutPlanRecord api is null");
                AppRouter.b("/PluginFitnessAdvice/FitnessResultActivity").zF_(new Bundle()).c(this.e);
                return;
            }
            WorkoutRecord acquireWorkoutRecordByExerciseTime = courseApi.acquireWorkoutRecordByExerciseTime(LoginInit.getInstance(this.i).getAccountInfo(1011), recordData.getEndTime(), recordData.getWorkoutId());
            Bundle bundle = new Bundle();
            if (acquireWorkoutRecordByExerciseTime != null) {
                bundle.putParcelable("workout_record", acquireWorkoutRecordByExerciseTime);
            }
            ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "initCourseOutPlanRecord workoutRecord ", acquireWorkoutRecordByExerciseTime);
            AppRouter.b("/PluginFitnessAdvice/FitnessResultActivity").zF_(bundle).c(this.e);
            return;
        }
        d(recordData);
    }

    private void d(List<RecordData> list) {
        Drawable drawable;
        boolean z = this.aw.get(DateFormatUtil.b(fyo.c(this.aq, this.bz, this.w)));
        int i = 0;
        this.h.d(!z || this.c < 3);
        if (this.r.getVisibility() != 0) {
            return;
        }
        if (z) {
            ArrayList arrayList = new ArrayList();
            if (koq.c(list)) {
                int size = list.size() - 1;
                int i2 = this.c;
                while (true) {
                    if (i >= 3 - i2) {
                        break;
                    }
                    if (i > size) {
                        ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initCourseFold recordDataList index ", Integer.valueOf(i), " lastIndex ", Integer.valueOf(size));
                        break;
                    } else {
                        arrayList.add(list.get(i));
                        i++;
                    }
                }
            }
            c((List<RecordData>) arrayList);
            drawable = ContextCompat.getDrawable(this.i, R.drawable._2131428279_res_0x7f0b03b7);
            this.p.setText(R.string._2130841418_res_0x7f020f4a);
        } else {
            c(list);
            drawable = ContextCompat.getDrawable(this.i, R.drawable._2131428280_res_0x7f0b03b8);
            this.p.setText(R.string.IDS_device_health_retract);
        }
        if (drawable != null) {
            drawable = nrf.cJH_(drawable, ContextCompat.getColor(this.i, R.color._2131296913_res_0x7f090291));
        }
        this.m.setImageDrawable(drawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        int i = this.ab;
        if (i == 0) {
            i();
        } else if (i == 1 || i == 2) {
            e(20);
        } else {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initCourseEvent mDayStatus ", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (!this.bb || this.bx != 1 || this.ab != 0) {
            this.n.setVisibility(8);
        } else {
            fys.b(new e(this, "TYPE_MENSTRUAL"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj) {
        if (!this.bb || !(obj instanceof SparseLongArray)) {
            this.n.setVisibility(8);
            return;
        }
        SparseLongArray sparseLongArray = (SparseLongArray) obj;
        this.bg = sparseLongArray;
        final int aIA_ = fys.aIA_(sparseLongArray);
        final int aIz_ = fys.aIz_(this.bg);
        ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "refreshCourseEventMenstrual status ", Integer.valueOf(aIA_), " lastDate ", Integer.valueOf(aIz_), " mDayStatus ", Integer.valueOf(this.ab), " mUserGender ", Integer.valueOf(this.bx), " mIsToday ", Boolean.valueOf(this.bb));
        if (aIA_ == 0) {
            this.n.setVisibility(8);
        } else if (aIA_ == 1) {
            this.n.setVisibility(0);
            this.k.setText(R.string._2130848800_res_0x7f022c20);
        } else if (aIA_ != 2) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "refreshCourseEventMenstrual mMenstrualArray ", this.bg);
        } else {
            this.n.setVisibility(0);
            this.k.setText(R.string._2130848801_res_0x7f022c21);
        }
        if (this.n.getVisibility() != 0) {
            return;
        }
        c();
        this.o.setVisibility(0);
        this.o.setText(R.string._2130848803_res_0x7f022c23);
        this.o.setOnClickListener(new View.OnClickListener() { // from class: fum
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiFatReductionPlanFragment.this.aHw_(aIA_, aIz_, view);
            }
        });
        if (this.l.getVisibility() == 0) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.s.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.bottomMargin = this.bn.getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
            this.s.setLayoutParams(layoutParams2);
        }
    }

    public /* synthetic */ void aHw_(int i, int i2, View view) {
        b(i, i2);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        if (this.bc && this.ba && this.bx == 1 && this.ab == 0 && !fxu.c().e()) {
            NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.bu;
            if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
                int aIA_ = fys.aIA_(this.bg);
                if (aIA_ == 1 || aIA_ == 2) {
                    int aIz_ = fys.aIz_(this.bg);
                    if (fys.aIB_(aIA_, this.bg)) {
                        b(aIA_, aIz_);
                        fys.d(DateFormatUtil.b(System.currentTimeMillis()));
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        if (this.bc && this.ba && this.ax && !fxu.c().e()) {
            if (!HandlerExecutor.b()) {
                HandlerExecutor.e(new Runnable() { // from class: fut
                    @Override // java.lang.Runnable
                    public final void run() {
                        AiFatReductionPlanFragment.this.ad();
                    }
                });
                return;
            }
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.e);
            builder.e(R.string._2130851536_res_0x7f0236d0).czC_(R.string.sug_continue_plan_device, new View.OnClickListener() { // from class: fvf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AiFatReductionPlanFragment.aHq_(view);
                }
            }).czy_(R.string._2130848364_res_0x7f022a6c, R.color._2131299241_res_0x7f090ba9, new View.OnClickListener() { // from class: fvh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AiFatReductionPlanFragment.this.aHz_(view);
                }
            });
            if (this.bu == null) {
                this.bu = builder.e();
            }
            NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.bu;
            if (noTitleCustomAlertDialog == null) {
                ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "showStopPlanDialog mStopPlanDialog is null");
            } else {
                if (noTitleCustomAlertDialog.isShowing()) {
                    return;
                }
                this.bu.show();
                fys.e(this.aq);
                this.ax = false;
            }
        }
    }

    public static /* synthetic */ void aHq_(View view) {
        ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "showStopPlanDialog setPositiveButton view ", view);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aHz_(View view) {
        IntPlan intPlan = this.aq;
        if (intPlan == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "showStopPlanDialog mIntPlan is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            ggu.a(intPlan, false, new UiCallback() { // from class: com.huawei.health.suggestion.ui.plan.fragment.AiFatReductionPlanFragment.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "showStopPlanDialog onFailure errorCode ", Integer.valueOf(i), " errorInfo ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onSuccess(Object obj) {
                    LogUtil.c("R_Suggestion_AiFatReductionPlanFragment", "showStopPlanDialog onSuccess object ", obj);
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void q() {
        ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "refreshPlan mIsVisibleSportEntrance ", Boolean.valueOf(this.bc), " mIsVisibleToUser ", Boolean.valueOf(this.ba));
        if (this.bc && this.ba) {
            WeakReference<AiPlanWeekDetailViewHolder> weakReference = this.al;
            if (weakReference == null) {
                ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "refreshPlan mHolderWeakReference is null");
                return;
            }
            AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder = weakReference.get();
            if (aiPlanWeekDetailViewHolder == null) {
                ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "refreshPlan holder is null");
            } else if (fys.a()) {
                aiPlanWeekDetailViewHolder.c(true, true);
            }
        }
    }

    private void e(int i) {
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "getWeightData api is null type ", Integer.valueOf(i));
            return;
        }
        long c2 = fyo.c(this.aq, this.bz, this.w);
        long t = i != 20 ? 0L : jdl.t(c2);
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(t, jdl.e(c2));
        hiAggregateOption.setAggregateType(0);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setCount(1);
        weightApi.getWeightData(hiAggregateOption, new d(this, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Object obj) {
        int i;
        this.n.setVisibility(0);
        this.k.setText(this.ab == 2 ? R.string._2130848799_res_0x7f022c1f : R.string._2130848798_res_0x7f022c1e);
        if (!koq.e(obj, qtm.class)) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "refreshCourseEventWeight object ", obj);
            return;
        }
        this.o.setVisibility(8);
        ArrayList arrayList = (ArrayList) obj;
        if (koq.c(arrayList)) {
            qtm qtmVar = (qtm) arrayList.get(0);
            if (qtmVar == null) {
                ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "refreshCourseEventWeight bean is null");
                this.n.setVisibility(8);
                return;
            }
            double e2 = qtmVar.e();
            if (UnitUtil.h()) {
                e2 = UnitUtil.h(e2);
                i = R.plurals._2130903216_res_0x7f0300b0;
            } else {
                i = R.plurals._2130903215_res_0x7f0300af;
            }
            this.k.setText(nsf.b(R.string._2130848654_res_0x7f022b8e, nsf.a(i, UnitUtil.e(e2), UnitUtil.e(e2, 1, qtmVar.a()))));
            return;
        }
        if (this.bb) {
            e(21);
            this.o.setVisibility(0);
            this.o.setText(R.string._2130848802_res_0x7f022c22);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Object obj) {
        qtm qtmVar;
        if (koq.e(obj, qtm.class)) {
            ArrayList arrayList = (ArrayList) obj;
            if (koq.c(arrayList) && (qtmVar = (qtm) arrayList.get(0)) != null) {
                this.ay = qtmVar.e();
                this.az = qtmVar.b();
            }
        }
        this.o.setOnClickListener(new View.OnClickListener() { // from class: fvr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiFatReductionPlanFragment.this.aHx_(view);
            }
        });
    }

    public /* synthetic */ void aHx_(View view) {
        ggu.d(this.e, this.ay, this.az);
        this.aj.postDelayed(new Runnable() { // from class: fvm
            @Override // java.lang.Runnable
            public final void run() {
                ary.a().e("PLAN_UPDATE");
            }
        }, 1000L);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void o() {
        this.v.setVisibility(8);
        IntDayPlan dayInfo = this.aq.getDayInfo(this.bz, this.w);
        if (dayInfo == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "initCourseTip intDayPlan is null");
            return;
        }
        if (dayInfo.getPunchFlag() != 1 || fys.a(dayInfo)) {
            return;
        }
        this.v.setVisibility(0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(fyo.c(this.aq, this.bz, this.w));
        this.v.setText(this.bn.getString(R.string._2130848698_res_0x7f022bba, (calendar.get(2) + 1) + "/" + calendar.get(5)));
    }

    public /* synthetic */ void aHr_(View view) {
        d(11);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(int i) {
        int i2 = 8;
        if (i == 0) {
            this.l.setVisibility(8);
            this.bi.setVisibility(8);
        } else if (i == 1 || i == 3) {
            HealthTextView healthTextView = this.l;
            if (this.ap && this.c < 3) {
                i2 = 0;
            }
            healthTextView.setVisibility(i2);
            this.l.setOnClickListener(new View.OnClickListener() { // from class: fvp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AiFatReductionPlanFragment.this.aHr_(view);
                }
            });
            this.bi.setVisibility(0);
            this.bi.setText(this.ap ? R.string._2130848704_res_0x7f022bc0 : R.string._2130848695_res_0x7f022bb7);
        } else {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "addCourseOutPlan default status ", Integer.valueOf(i));
        }
        ViewGroup.LayoutParams layoutParams = this.s.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.bottomMargin = this.bn.getDimensionPixelSize((this.ab == 0 || this.l.getVisibility() == 0) ? R.dimen._2131363063_res_0x7f0a04f7 : R.dimen._2131362008_res_0x7f0a00d8);
            this.s.setLayoutParams(layoutParams2);
        }
    }

    private void d(RecordData recordData) {
        String recordId = recordData.getRecordId();
        if (TextUtils.isEmpty(recordId)) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "jumpTrackDetail recordId ", recordId);
        } else {
            fis.d().a(recordId);
        }
    }

    private void d(int i) {
        Intent intent = new Intent(this.i, (Class<?>) SportAllCourseActivity.class);
        if (i == 11) {
            intent.putExtra("KEY_SUG_COURSE_ADD_STATUS", true);
            ggr.a(1, this.t);
        } else if (i == 20) {
            intent.putExtra("KEY_SUG_COURSE_REPLACE_STATUS", true);
            IntAction intAction = this.ao;
            if (intAction != null) {
                String actionId = intAction.getActionId();
                intent.putExtra("KEY_SUG_COURSE_REPLACE_ID", actionId);
                FitWorkout fitWorkout = this.ag.get(actionId);
                if (fitWorkout != null) {
                    this.bd = fitWorkout.acquireName();
                }
            }
            ggr.a(2, this.t);
        } else {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "jumpAllCourse requestCode ", Integer.valueOf(i));
        }
        ArrayList<String> arrayList = new ArrayList<>();
        if (koq.c(this.q)) {
            for (IntAction intAction2 : this.q) {
                if (intAction2 != null) {
                    arrayList.add(intAction2.getActionId());
                }
            }
        }
        intent.putStringArrayListExtra("KEY_SUG_COURSE_ADD_IDS", arrayList);
        intent.putExtra("SKIP_ALL_COURSE_KEY", "FITNESS_COURSE");
        startActivityForResult(intent, i);
        ggr.c(gge.c("FITNESS_COURSE"), 8);
    }

    private void c(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (this.aq == null || this.ao == null || koq.b(arrayList)) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "replaceCourse mIntPlan ", this.aq, " mIntAction ", this.ao, " courseIdList ", arrayList);
        } else {
            if (!CommonUtil.aa(this.i)) {
                nrh.b(this.i, R.string._2130841762_res_0x7f0210a2);
                return;
            }
            if (koq.c(arrayList2)) {
                this.be = arrayList2.get(0);
            }
            fys.e(new ReplacePlanBean.Builder().planId(this.aq.getPlanId()).planType(1).weekNo(this.bz).dayNo(this.w).propensityWorkoutId(arrayList.get(0)).replacedWorkoutId(this.ao.getActionId()).build(), new e(this, "TYPE_REPLACE"));
        }
    }

    private void e(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (koq.b(arrayList)) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "addCourse courseIdList ", arrayList);
            return;
        }
        if (this.c + arrayList.size() > 3) {
            nrh.b(this.i, R.string._2130848643_res_0x7f022b83);
            return;
        }
        if (this.f3255a.size() <= 3) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "addCourse mCalenderList ", this.f3255a);
            return;
        }
        int size = arrayList.size();
        ArrayList arrayList3 = new ArrayList();
        final HashMap hashMap = new HashMap();
        for (int i = 0; i < size; i++) {
            arrayList3.add(fys.b(arrayList.get(i)));
            String str = "";
            String str2 = koq.d(arrayList2, i) ? arrayList2.get(i) : "";
            if (!TextUtils.isEmpty(str2)) {
                str = str2;
            }
            hashMap.put(arrayList.get(i), str);
        }
        fys.c(fys.a(arrayList3, this.aq.getPlanId(), 0, this.bz, this.w), (ResponseCallback<Object>) new ResponseCallback() { // from class: fva
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                AiFatReductionPlanFragment.this.a(hashMap, i2, obj);
            }
        });
        fys.c();
    }

    public /* synthetic */ void a(Map map, int i, Object obj) {
        if (i != 0) {
            return;
        }
        for (Map.Entry entry : map.entrySet()) {
            if (entry != null) {
                HashMap hashMap = new HashMap(3);
                hashMap.put("click", 1);
                hashMap.put("courseId", entry.getKey());
                hashMap.put("courseName", entry.getValue());
                ixx.d().d(this.i, AnalyticsValue.INT_PLAN_1120036.value(), hashMap, 0);
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.plan.adapter.CourseAdapter.OnItemClickListener
    public void onCourseClick(boolean z, int i, int i2, IntAction intAction) {
        if (intAction == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "onCourseClick intAction is null");
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.i, AnalyticsValue.INT_PLAN_1120029.value(), hashMap, 0);
        FitWorkout fitWorkout = this.ag.get(intAction.getActionId());
        if (fitWorkout == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "onCourseClick fitWorkout is null");
            return;
        }
        if (this.aq.getPlanTimeInfo() == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "onCourseClick planTimeInfo is null");
            return;
        }
        long j = ase.j(this.aq);
        long e2 = gib.e(j, this.bz, this.w);
        if (i == 0) {
            if (this.aq.getDayInfo(this.bz, this.w) == null || i2 < 0) {
                e2 = 0;
            } else {
                e2 = intAction.getPunchFlag() == 1 ? -3L : -2L;
            }
        }
        long j2 = e2;
        if (fitWorkout.isRunModelCourse()) {
            gds.e(this.e, fitWorkout, this.aq, j2, i2 + 1);
        } else {
            gds.d(this.e, fitWorkout, this.aq, i2 + 1, j2);
        }
        ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "onCourseClick isInPlan ", Boolean.valueOf(z), " status ", Integer.valueOf(i), " position ", Integer.valueOf(i2), " intAction ", intAction, " courseBeginTime ", Long.valueOf(j2), " planBeginTime ", Long.valueOf(j));
    }

    @Override // com.huawei.health.suggestion.ui.plan.adapter.CourseAdapter.OnItemClickListener
    public void onCourseRecordClick(boolean z, int i, int i2, final IntAction intAction) {
        ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "onCourseRecordClick isInPlan ", Boolean.valueOf(z), " status ", Integer.valueOf(i), " position ", Integer.valueOf(i2), " intAction ", intAction);
        if (intAction == null) {
            return;
        }
        ase.e(this.aq, this.bz, this.w, new IBaseResponseCallback() { // from class: fvb
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i3, Object obj) {
                AiFatReductionPlanFragment.this.e(intAction, i3, obj);
            }
        });
    }

    public /* synthetic */ void e(IntAction intAction, int i, Object obj) {
        if (!koq.e(obj, RecordData.class)) {
            LogUtil.a("Suggestion_AiFatReductionPlanFragment", "getRecordDataList onResult obj is not instanceof List<RecordData>");
            return;
        }
        RecordData a2 = fys.a((List) obj, intAction.getActionId());
        if (a2 == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "onCourseRecordClick recordData is null");
            return;
        }
        if (intAction.getActionType() == IntAction.ActionType.RUN) {
            d(a2);
            return;
        }
        String workoutId = a2.getWorkoutId();
        if (TextUtils.isEmpty(workoutId)) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "onCourseRecordClick workoutId ", workoutId);
            return;
        }
        List<WorkoutRecord> d2 = fys.d(this.aq.getPlanId());
        if (koq.b(d2)) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "onCourseRecordClick list ", d2);
            AppRouter.b("/PluginFitnessAdvice/FitnessResultActivity").zF_(new Bundle()).c(this.e);
            return;
        }
        WorkoutRecord d3 = fys.d(d2, a2);
        Bundle bundle = new Bundle();
        if (d3 != null) {
            if (d3.getRecordModeType() == 1) {
                LogUtil.c("Suggestion_AiFatReductionPlanFragment", "onCourseRecordClick H5 AI_FITNESS_COURSE");
                H5ProLaunchOption.Builder addPath = new H5ProLaunchOption.Builder().addCustomizeArg("workoutRecord", new Gson().toJson(d3)).addPath("#/resultRecord?pullFrom=1");
                addPath.addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, bzs.e().getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL)).enableImageCache();
                bzs.e().loadH5ProApp(this.e, "com.huawei.health.h5.ai-fitness-course", addPath);
                return;
            }
            bundle.putParcelable("workout_record", d3);
        }
        ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "onCourseRecordClick workoutRecord ", d3);
        AppRouter.b("/PluginFitnessAdvice/FitnessResultActivity").zF_(bundle).c(this.e);
    }

    @Override // com.huawei.health.suggestion.ui.plan.adapter.CourseAdapter.OnItemClickListener
    public void onCourseStatusClick(boolean z, int i, int i2, IntAction intAction) {
        ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "onCourseStatusClick isInPlan ", Boolean.valueOf(z), " status ", Integer.valueOf(i), " position ", Integer.valueOf(i2), " intAction ", intAction);
        this.ao = intAction;
        d(20);
    }

    @Override // com.huawei.health.suggestion.ui.plan.adapter.CourseAdapter.OnItemClickListener
    public void onLongClick(int i, int i2, final IntAction intAction) {
        ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "onLongClick status ", Integer.valueOf(i), " position ", Integer.valueOf(i2), " intAction ", intAction);
        if (intAction == null || intAction.getPunchFlag() == 1) {
            return;
        }
        new NoTitleCustomAlertDialog.Builder(this.e).e(R.string._2130848644_res_0x7f022b84).czC_(R.string._2130848357_res_0x7f022a65, new View.OnClickListener() { // from class: fvn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiFatReductionPlanFragment.this.aHv_(intAction, view);
            }
        }).czz_(R.string._2130839509_res_0x7f0207d5, new View.OnClickListener() { // from class: fvs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiFatReductionPlanFragment.aHp_(view);
            }
        }).e().show();
    }

    public /* synthetic */ void aHv_(IntAction intAction, View view) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(fys.b(intAction.getActionId()));
        fys.c(fys.a(arrayList, this.aq.getPlanId(), 1, this.bz, this.w), new e(this, "TYPE_DELETE"));
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void aHp_(View view) {
        ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "onLongClick setNegativeButton view ", view);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1 || intent == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "onActivityResult intent ", intent, " resultCode ", Integer.valueOf(i2), " requestCode ", Integer.valueOf(i));
            return;
        }
        ArrayList<String> stringArrayListExtra = intent.hasExtra("KEY_SUG_COURSE_IDS_RESULT") ? intent.getStringArrayListExtra("KEY_SUG_COURSE_IDS_RESULT") : null;
        ArrayList<String> stringArrayListExtra2 = intent.hasExtra("KEY_SUG_COURSE_NAMES_RESULT") ? intent.getStringArrayListExtra("KEY_SUG_COURSE_NAMES_RESULT") : null;
        if (i == 11) {
            e(stringArrayListExtra, stringArrayListExtra2);
        } else if (i == 20) {
            c(stringArrayListExtra, stringArrayListExtra2);
        } else {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "onActivityResult default requestCode ", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (!Boolean.parseBoolean(SharedPreferenceManager.b(this.i, Integer.toString(20002), "EXIT_APP_AT_SPORT_TAB"))) {
            r();
            return;
        }
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "onResume activity is null");
            return;
        }
        String simpleName = wa_.getClass().getSimpleName();
        if (!"MainActivity".equals(simpleName)) {
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "onResume simpleName ", simpleName);
            r();
        } else {
            a(this.u);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        r();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        g();
        ObserverManagerUtil.c(this.bf);
    }

    private void g() {
        ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "finish mIsVisibleSportEntrance ", Boolean.valueOf(this.bc), " mIsVisibleToUser ", Boolean.valueOf(this.ba), " mIsSupportLoopRecommendFood ", Boolean.valueOf(this.av), " mCurrentPosition ", Integer.valueOf(this.u), " mTypeList ", this.cb);
        HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(this.cb, new HiUnSubscribeListener() { // from class: fvg
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public final void onResult(boolean z) {
                ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "finish isSuccess ", Boolean.valueOf(z));
            }
        });
        this.aj.removeCallbacksAndMessages(null);
    }

    @Override // com.huawei.health.marketing.views.IViewVisibilityChange
    public void onVisibilityChange(boolean z) {
        this.ba = z;
        ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "setUserVisibleHint mIsVisibleSportEntrance ", Boolean.valueOf(this.bc), " mIsVisibleToUser ", Boolean.valueOf(this.ba), " mIsSupportLoopRecommendFood ", Boolean.valueOf(this.av), " mCurrentPosition ", Integer.valueOf(this.u));
        if (this.ba) {
            w();
            p();
            c();
            ad();
            q();
            a(this.u);
            return;
        }
        r();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        a(i);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (this.au) {
            return;
        }
        this.au = i == 1;
    }

    static class e implements ResponseCallback<Object> {
        private final WeakReference<AiFatReductionPlanFragment> c;
        private final String d;

        e(AiFatReductionPlanFragment aiFatReductionPlanFragment, String str) {
            this.c = new WeakReference<>(aiFatReductionPlanFragment);
            this.d = str;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.huawei.hwbasemgr.ResponseCallback
        public void onResponse(int i, Object obj) {
            char c;
            AiFatReductionPlanFragment aiFatReductionPlanFragment = this.c.get();
            if (aiFatReductionPlanFragment == null) {
                ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "AiPlanCallback onResponse fragment is null status ", Integer.valueOf(i), " object ", obj);
            }
            ReleaseLogUtil.b("R_Suggestion_AiFatReductionPlanFragment", "AiPlanCallback mType ", this.d, " status ", Integer.valueOf(i), " object ", obj);
            String str = this.d;
            str.hashCode();
            switch (str.hashCode()) {
                case -2121184768:
                    if (str.equals("TYPE_MENSTRUAL")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1882615153:
                    if (str.equals("TYPE_REPLACE")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1011185289:
                    if (str.equals("TYPE_CANCEL_LEAVE")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 314273106:
                    if (str.equals("TYPE_LEAVE")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 644772376:
                    if (str.equals("TYPE_LEAVE_MENSTRUAL")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 923810384:
                    if (str.equals("TYPE_DELETE")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 1722476560:
                    if (str.equals("TYPE_UPDATE_PLAN_CALENDAR_REST")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 1859382572:
                    if (str.equals("TYPE_UPDATE_PLAN_CALENDAR_TRAIN")) {
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
                    aiFatReductionPlanFragment.aj.removeMessages(12);
                    Message obtain = Message.obtain();
                    obtain.what = 12;
                    obtain.obj = obj;
                    aiFatReductionPlanFragment.aj.sendMessage(obtain);
                    break;
                case 1:
                    if (i != 0) {
                        nrh.b(aiFatReductionPlanFragment.i, i == 15030326 ? R.string._2130848805_res_0x7f022c25 : R.string._2130848804_res_0x7f022c24);
                        ggr.d(false, aiFatReductionPlanFragment.bd, aiFatReductionPlanFragment.be);
                        break;
                    } else {
                        ggr.d(true, aiFatReductionPlanFragment.bd, aiFatReductionPlanFragment.be);
                        HandlerExecutor.e(new Runnable() { // from class: fvv
                            @Override // java.lang.Runnable
                            public final void run() {
                                fys.c();
                            }
                        });
                        break;
                    }
                case 2:
                    if (i != 0) {
                        nrh.b(aiFatReductionPlanFragment.i, R.string._2130848806_res_0x7f022c26);
                    }
                    ggr.d(1, 7, aiFatReductionPlanFragment.g);
                    break;
                case 3:
                    if (i != 0) {
                        nrh.b(aiFatReductionPlanFragment.i, R.string._2130848806_res_0x7f022c26);
                    }
                    ggr.d(1, 5, aiFatReductionPlanFragment.bh);
                    break;
                case 4:
                    if (i != 0) {
                        nrh.b(aiFatReductionPlanFragment.i, R.string._2130848806_res_0x7f022c26);
                    }
                    ggr.d(2, 5, aiFatReductionPlanFragment.bh);
                    break;
                case 5:
                    if (i == 0) {
                        HashMap hashMap = new HashMap(1);
                        hashMap.put("click", 1);
                        ixx.d().d(aiFatReductionPlanFragment.i, AnalyticsValue.INT_PLAN_1120037.value(), hashMap, 0);
                        break;
                    }
                    break;
                case 6:
                    if (i != 0) {
                        nrh.b(aiFatReductionPlanFragment.i, R.string._2130848806_res_0x7f022c26);
                    }
                    ggr.d(1, 2, 0);
                    break;
                case 7:
                    if (i != 0) {
                        nrh.b(aiFatReductionPlanFragment.i, R.string._2130848806_res_0x7f022c26);
                        break;
                    }
                    break;
                default:
                    ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "AiPlanCallback mType ", this.d, " status ", Integer.valueOf(i), " object ", obj);
                    break;
            }
        }
    }

    static class a implements HiSubscribeListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<AiFatReductionPlanFragment> f3258a;

        a(AiFatReductionPlanFragment aiFatReductionPlanFragment) {
            this.f3258a = new WeakReference<>(aiFatReductionPlanFragment);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            AiFatReductionPlanFragment aiFatReductionPlanFragment = this.f3258a.get();
            if (aiFatReductionPlanFragment != null && !koq.b(list)) {
                aiFatReductionPlanFragment.cb = list;
            } else {
                ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "InnerHiSubscribeListener onResult fragment ", aiFatReductionPlanFragment, " successList ", list, " failList ", list2);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            AiFatReductionPlanFragment aiFatReductionPlanFragment = this.f3258a.get();
            if (aiFatReductionPlanFragment == null) {
                ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "InnerHiSubscribeListener onChange fragment is null");
                return;
            }
            if ("setUserData".equals(str) || ArkUIXConstants.INSERT.equals(str) || ArkUIXConstants.DELETE.equals(str)) {
                if (i == 100) {
                    aiFatReductionPlanFragment.aj.removeMessages(10);
                    aiFatReductionPlanFragment.aj.sendEmptyMessageDelayed(10, 1000L);
                } else if (i == 23) {
                    aiFatReductionPlanFragment.aj.removeMessages(13);
                    aiFatReductionPlanFragment.aj.sendEmptyMessageDelayed(13, 1000L);
                } else if (i == HiSubscribeType.c) {
                    aiFatReductionPlanFragment.aj.sendEmptyMessage(11);
                } else {
                    ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "InnerHiSubscribeListener onChange type ", Integer.valueOf(i));
                }
            }
        }
    }

    static class d implements WeightCallback {

        /* renamed from: a, reason: collision with root package name */
        private final int f3260a;
        private final WeakReference<AiFatReductionPlanFragment> c;

        d(AiFatReductionPlanFragment aiFatReductionPlanFragment, int i) {
            this.c = new WeakReference<>(aiFatReductionPlanFragment);
            this.f3260a = i;
        }

        @Override // com.huawei.ui.main.stories.health.weight.callback.WeightCallback
        public void getWeight(ArrayList<qtm> arrayList) {
            AiFatReductionPlanFragment aiFatReductionPlanFragment = this.c.get();
            if (aiFatReductionPlanFragment != null) {
                aiFatReductionPlanFragment.aj.removeMessages(this.f3260a);
                Message obtain = Message.obtain();
                obtain.what = this.f3260a;
                obtain.obj = arrayList;
                aiFatReductionPlanFragment.aj.sendMessage(obtain);
                return;
            }
            ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "AiPlanWeightCallback getWeight fragment is null");
        }
    }

    static class b extends BaseHandler<AiFatReductionPlanFragment> {
        b(AiFatReductionPlanFragment aiFatReductionPlanFragment) {
            super(aiFatReductionPlanFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aHB_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AiFatReductionPlanFragment aiFatReductionPlanFragment, Message message) {
            if (aiFatReductionPlanFragment.getView() == null) {
                ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "AiPlanHandler handleMessageWhenReferenceNotNull object ", aiFatReductionPlanFragment, " message ", message);
            }
            int i = message.what;
            if (i == 20) {
                aiFatReductionPlanFragment.e(message.obj);
                return;
            }
            if (i == 21) {
                aiFatReductionPlanFragment.b(message.obj);
                return;
            }
            if (i == 30) {
                aiFatReductionPlanFragment.s();
                return;
            }
            switch (i) {
                case 10:
                    float e = fnz.e();
                    if (Float.compare(aiFatReductionPlanFragment.ca, e) != 0) {
                        aiFatReductionPlanFragment.ca = e;
                        aiFatReductionPlanFragment.c(aiFatReductionPlanFragment.f());
                        aiFatReductionPlanFragment.k();
                    }
                    int b = fnz.b();
                    if (aiFatReductionPlanFragment.bx != b) {
                        aiFatReductionPlanFragment.bx = b;
                        aiFatReductionPlanFragment.m();
                        break;
                    }
                    break;
                case 11:
                    aiFatReductionPlanFragment.y();
                    break;
                case 12:
                    aiFatReductionPlanFragment.a(message.obj);
                    break;
                case 13:
                    aiFatReductionPlanFragment.i();
                    break;
                default:
                    ReleaseLogUtil.a("R_Suggestion_AiFatReductionPlanFragment", "AiPlanHandler handleMessageWhenReferenceNotNull message ", message);
                    break;
            }
        }
    }
}
