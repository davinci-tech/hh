package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder;
import com.huawei.health.weight.WeightApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.stories.health.weight.callback.WeightCallback;
import defpackage.ffl;
import defpackage.fzr;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class fzr {
    private static volatile int b;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f12712a;
    private IntPlan aa;
    private HealthRecycleView ab;
    private final ViewGroup ac;
    private f ad;
    private List<j> ae;
    private final Resources af;
    private List<RecordData> ag;
    private LinearLayout ah;
    private final List<RecordData> ai;
    private int aj;
    private AiPlanWeekDetailViewHolder ak;
    private int al;
    private LinearLayout am;
    private d an;
    private ImageView ap;
    private TextView aq;
    private long c;
    private b e;
    private double f;
    private ArrayList<String> g;
    private int h;
    private final Context i;
    private double j;
    private int k;
    private ImageView l;
    private Map<String, FitWorkout> m = new ConcurrentHashMap();
    private List<IntAction> n;
    private final List<IntAction> o;
    private List<e> p;
    private HealthRecycleView q;
    private Handler r;
    private a s;
    private int t;
    private HealthTextView u;
    private a v;
    private List<e> w;
    private final List<IntAction> x;
    private List<IntAction> y;
    private ViewGroup z;

    static class j {
        String e;

        j(RecordData recordData) {
            this.e = recordData.getRecordId();
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        int f12718a;
        String b;
        int c;
        IntAction.ActionType d;
        RecordData e;

        e(IntAction intAction, RecordData recordData, int i) {
            this.f12718a = intAction.getPunchFlag();
            this.b = intAction.getActionId();
            this.d = intAction.getActionType();
            this.e = recordData;
            this.c = i;
        }
    }

    class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        FrameLayout f12717a;
        HealthTextView b;
        HealthTextView c;
        View d;
        ImageView e;
        LinearLayout f;
        ImageView g;
        HealthCardView h;

        public c(View view) {
            super(view);
            int intValue;
            this.f12717a = (FrameLayout) view.findViewById(R.id.plan_course_item);
            this.g = (ImageView) view.findViewById(R.id.plan_course_item_background);
            this.c = (HealthTextView) view.findViewById(R.id.course_name);
            this.b = (HealthTextView) view.findViewById(R.id.course_duration);
            this.d = view.findViewById(R.id.course_item_interval);
            this.e = (ImageView) view.findViewById(R.id.plan_trained_state);
            this.f = (LinearLayout) view.findViewById(R.id.plan_trained_layout);
            this.h = (HealthCardView) view.findViewById(R.id.view_record);
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            if (nsn.ag(fzr.this.i)) {
                intValue = ((((fzr.this.af.getDisplayMetrics().widthPixels - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue()) - (((int) fzr.this.af.getDimension(R.dimen._2131362366_res_0x7f0a023e)) * 3)) - (((int) fzr.this.af.getDimension(R.dimen._2131362365_res_0x7f0a023d)) * 2)) / 2;
            } else {
                int i = fzr.this.af.getDisplayMetrics().widthPixels;
                int intValue2 = ((Integer) safeRegionWidth.first).intValue();
                intValue = ((((i - intValue2) - ((Integer) safeRegionWidth.second).intValue()) - ((int) fzr.this.af.getDimension(R.dimen._2131362579_res_0x7f0a0313))) - (((int) fzr.this.af.getDimension(R.dimen._2131362366_res_0x7f0a023e)) * 2)) - (((int) fzr.this.af.getDimension(R.dimen._2131362365_res_0x7f0a023d)) * 2);
            }
            int i2 = (int) ((intValue * 9.0f) / 21.0f);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(intValue, i2);
            this.g.setLayoutParams(layoutParams);
            this.f12717a.setLayoutParams(new RelativeLayout.LayoutParams(intValue, i2));
            this.f.setLayoutParams(layoutParams);
            if (nsn.s()) {
                nsn.b(this.c);
                nsn.b(this.b);
            }
        }
    }

    class a extends RecyclerView.Adapter<c> {

        /* renamed from: a, reason: collision with root package name */
        List<IntAction> f12716a;
        boolean b;
        Context d;

        public a(Context context, List<IntAction> list, boolean z) {
            this.d = context;
            this.f12716a = list;
            this.b = z;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: aJr_, reason: merged with bridge method [inline-methods] */
        public c onCreateViewHolder(ViewGroup viewGroup, int i) {
            return fzr.this.new c(LayoutInflater.from(this.d).inflate(R.layout.sug_layout_intplan_course_card_view, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(c cVar, int i) {
            String str;
            long j;
            String str2;
            if (this.f12716a.get(i) != null) {
                if (i == 0) {
                    if (fzr.this.aa.getPlanType() != IntPlan.PlanType.AI_FITNESS_PLAN) {
                        cVar.d.setVisibility(8);
                    } else {
                        cVar.d.setVisibility(0);
                    }
                }
                String actionId = this.f12716a.get(i).getActionId();
                FitWorkout fitWorkout = (FitWorkout) fzr.this.m.get(actionId);
                mnt actionInfo = this.f12716a.get(i).getActionInfo();
                if (actionInfo != null) {
                    ReleaseLogUtil.e("IntPlanDayCourseView", "onBindViewHolder : add course card view ", actionId, " punchFlag:", Integer.valueOf(this.f12716a.get(i).getPunchFlag()));
                    str = actionInfo.d();
                    j = actionInfo.c();
                    str2 = actionInfo.e();
                } else if (fitWorkout != null) {
                    ReleaseLogUtil.e("IntPlanDayCourseView", "onBindViewHolder : add fit workout card view ", actionId, " punchFlag:", Integer.valueOf(this.f12716a.get(i).getPunchFlag()));
                    str = fitWorkout.acquireName();
                    j = fitWorkout.acquireDuration();
                    str2 = fitWorkout.acquirePicture();
                } else {
                    LogUtil.b("IntPlanDayCourseView", "onBindViewHolder : missing course info");
                    str = "";
                    j = 0;
                    str2 = null;
                }
                cVar.c.setText(str);
                if (j >= 60) {
                    cVar.b.setVisibility(0);
                    cVar.b.setText(ffy.b(R.plurals._2130903305_res_0x7f030109, (int) (j / 60), gic.e(j)));
                } else {
                    cVar.b.setVisibility(8);
                }
                cVar.e.setImageResource(R.drawable._2131430246_res_0x7f0b0b66);
                cVar.f12717a.setAlpha(1.0f);
                if (fzr.this.aj != 3) {
                    if (fzr.this.aj == 0) {
                        fzr.this.aJg_(cVar.f12717a, actionId, i, fitWorkout);
                    } else {
                        fzr.this.aJg_(cVar.f12717a, actionId, i, fitWorkout);
                        fzr.this.aJh_(cVar.f12717a, this.b, this.f12716a.get(i));
                    }
                    cVar.h.setVisibility(8);
                    if (this.f12716a.get(i).getPunchFlag() == 1) {
                        cVar.e.setImageResource(R.drawable._2131430243_res_0x7f0b0b63);
                        if (fzr.this.aa.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN || (fzr.this.aa.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN && this.b)) {
                            fzr.this.aJj_(cVar.h, this.f12716a.get(i), this.d);
                        }
                    }
                } else {
                    cVar.e.setImageResource(R.drawable._2131430249_res_0x7f0b0b69);
                    cVar.h.setVisibility(8);
                    fzr.this.aJg_(cVar.f12717a, actionId, i, fitWorkout);
                }
                int dimension = (int) fzr.this.af.getDimension(R.dimen._2131362360_res_0x7f0a0238);
                if (str2 == null) {
                    nrf.cIP_(cVar.g, R.drawable.plan_course_background_error, dimension, 1, 0);
                } else {
                    nrf.cIS_(cVar.g, str2, dimension, 1, R.drawable.plan_course_background_error);
                }
                if (cVar.h.getVisibility() == 0 && nsn.t()) {
                    cVar.c.setMaxLines(1);
                } else {
                    cVar.c.setMaxLines(2);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.f12716a.size();
        }
    }

    class h extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f12720a;
        ImageView b;
        HealthTextView c;
        LinearLayout d;
        HealthTextView e;
        ImageView g;
        HealthTextView j;

        public h(View view) {
            super(view);
            this.d = (LinearLayout) view.findViewById(R.id.run_record_layout);
            this.g = (ImageView) view.findViewById(R.id.run_type_icon);
            this.j = (HealthTextView) view.findViewById(R.id.run_type);
            this.c = (HealthTextView) view.findViewById(R.id.run_distance);
            this.f12720a = (HealthTextView) view.findViewById(R.id.run_time);
            ImageView imageView = (ImageView) view.findViewById(R.id.sug_out_record_arrow);
            this.b = imageView;
            imageView.setImageResource(LanguageUtil.bc(fzr.this.i) ? R.drawable._2131427841_res_0x7f0b0201 : R.drawable._2131427842_res_0x7f0b0202);
            this.e = (HealthTextView) view.findViewById(R.id.run_distance_unit);
        }
    }

    class f extends RecyclerView.Adapter<h> {

        /* renamed from: a, reason: collision with root package name */
        List<RecordData> f12719a;
        Context d;

        public f(Context context, List<RecordData> list) {
            this.d = context;
            this.f12719a = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: aJt_, reason: merged with bridge method [inline-methods] */
        public h onCreateViewHolder(ViewGroup viewGroup, int i) {
            return fzr.this.new h(LayoutInflater.from(this.d).inflate(R.layout.sug_layout_out_plan_run_card_view, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(h hVar, int i) {
            RecordData recordData;
            if (!koq.c(this.f12719a) || i >= this.f12719a.size() || (recordData = this.f12719a.get(i)) == null) {
                return;
            }
            hVar.g.setVisibility(0);
            hVar.c.setText("");
            try {
                hVar.c.setText(fzr.this.c(Integer.parseInt(recordData.getExtraInfo())));
            } catch (NumberFormatException unused) {
                LogUtil.b("IntPlanDayCourseView", "onBindViewHolder NumberFormatException");
            }
            if (recordData.getSportType() == 264) {
                hVar.j.setText(R.string.IDS_start_track_sport_type_indoor_run);
                fzr.this.aJi_(R.drawable._2131430405_res_0x7f0b0c05, hVar.g);
                hVar.c.setTextColor(fzr.this.af.getColor(R$color.sug_ai_run_plan_indoor_text_color));
            } else if (recordData.getSportType() == 258) {
                hVar.j.setText(R.string.IDS_start_track_sport_type_outdoor_run);
                fzr.this.aJi_(R.drawable._2131430378_res_0x7f0b0bea, hVar.g);
                hVar.c.setTextColor(fzr.this.af.getColor(R$color.colorAccent));
            } else {
                hVar.j.setText(R.string.IDS_motiontrack_cross_country_race);
                fzr.this.aJi_(R.drawable._2131430404_res_0x7f0b0c04, hVar.g);
                hVar.c.setTextColor(fzr.this.af.getColor(R$color.sug_ai_run_plan_cross_text_color));
            }
            hVar.e.setText(UnitUtil.h() ? R.string._2130841383_res_0x7f020f27 : R.string._2130841382_res_0x7f020f26);
            hVar.f12720a.setText(UnitUtil.d((int) recordData.getDuration()));
            fzr.this.aJk_(hVar.d, recordData, this.d);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.f12719a.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(double d2) {
        double d3 = d2 / 1000.0d;
        return UnitUtil.h() ? UnitUtil.e(UnitUtil.e(d3, 3), 1, 2) : UnitUtil.e(d3, 1, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aJi_(int i, ImageView imageView) {
        if (LanguageUtil.bc(this.i)) {
            imageView.setImageDrawable(nrz.cKn_(this.i, i));
        } else {
            imageView.setImageResource(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.i, AnalyticsValue.INT_PLAN_1120037.value(), hashMap, 0);
    }

    public Context j() {
        return this.i;
    }

    public fzr(Context context, ViewGroup viewGroup) {
        ArrayList arrayList = new ArrayList();
        this.o = arrayList;
        this.x = new ArrayList();
        this.ai = new ArrayList();
        this.af = BaseApplication.e().getResources();
        this.n = new ArrayList();
        this.y = new ArrayList();
        this.ag = new ArrayList();
        this.t = 0;
        this.r = new Handler(Looper.getMainLooper());
        this.an = new d(0);
        this.e = new b(this);
        this.f = 65.0d;
        this.j = 20.0d;
        this.h = 0;
        this.p = new ArrayList();
        this.w = new ArrayList();
        this.ae = new ArrayList();
        this.g = new ArrayList<>();
        this.i = context;
        this.ac = viewGroup;
        if (viewGroup != null) {
            this.am = (LinearLayout) viewGroup.findViewById(R.id.start_day_view);
            this.ah = (LinearLayout) viewGroup.findViewById(R.id.rest_day_view);
            this.q = (HealthRecycleView) viewGroup.findViewById(R.id.plan_sport_recyclerView);
            a aVar = new a(context, arrayList, true);
            this.s = aVar;
            HealthRecycleView healthRecycleView = this.q;
            if (healthRecycleView != null) {
                healthRecycleView.setAdapter(aVar);
            }
            ggs.a(context, this.q);
            HealthRecycleView healthRecycleView2 = (HealthRecycleView) viewGroup.findViewById(R.id.out_plan_sport_recyclerView);
            this.ab = healthRecycleView2;
            ggs.a(context, healthRecycleView2);
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.out_plan_moudle);
            this.z = viewGroup2;
            if (viewGroup2 != null) {
                viewGroup2.setVisibility(8);
            }
            this.l = (ImageView) viewGroup.findViewById(R.id.explain_out_plan);
            HealthTextView healthTextView = (HealthTextView) viewGroup.findViewById(R.id.out_plan_training_helps_punch);
            this.u = healthTextView;
            if (healthTextView != null) {
                healthTextView.setVisibility(8);
            }
            ImageView imageView = this.l;
            if (imageView != null) {
                imageView.setOnClickListener(new View.OnClickListener() { // from class: fzr.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        fzr.this.u();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (this.i == null) {
            LogUtil.h("IntPlanDayCourseView", "showExplainDialog mContext == null");
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.i).d(R.string._2130848745_res_0x7f022be9).b(R.string._2130848469_res_0x7f022ad5).cyU_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: fzr.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        if (a2.isShowing()) {
            return;
        }
        a2.show();
    }

    public void e(final int i, final int i2, AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder, int i3) {
        this.ak = aiPlanWeekDetailViewHolder;
        this.t = i3;
        this.al = i;
        this.k = i2;
        final ArrayList arrayList = new ArrayList();
        if (this.aa != null) {
            ad();
            arrayList.addAll(fys.b(this.aa, this.al, this.k));
            ase.e(this.aa, this.al, this.k, new IBaseResponseCallback() { // from class: fzu
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i4, Object obj) {
                    fzr.this.c(arrayList, i, i2, i4, obj);
                }
            });
        }
    }

    /* synthetic */ void c(List list, int i, int i2, int i3, Object obj) {
        List<RecordData> list2 = (List) obj;
        final boolean d2 = d(this.p, (List<IntAction>) list, list2);
        this.n = list;
        this.p.clear();
        for (IntAction intAction : this.n) {
            this.p.add(new e(intAction, fys.a(list2, intAction.getActionId()), this.aj));
        }
        final boolean b2 = ase.b(this.aa, i, i2);
        LogUtil.a("IntPlanDayCourseView", "setCourseData week:", Integer.valueOf(i), " day:", Integer.valueOf(i2), " mInPlanCourseList = ", Integer.valueOf(this.n.size()), "mOutPlanCourseList = ", Integer.valueOf(this.y.size()), " changed:", Boolean.valueOf(d2), "isLastDay:", Boolean.valueOf(b2));
        HandlerExecutor.e(new Runnable() { // from class: fzr.2
            @Override // java.lang.Runnable
            public void run() {
                if (fzr.this.c(b2)) {
                    fzr.this.v();
                } else if (koq.b(fzr.this.n)) {
                    fzr.this.y();
                    fzr.this.r();
                    fzr.this.m();
                } else if (d2) {
                    fzr.this.y();
                    fzr.this.l();
                } else {
                    LogUtil.c("IntPlanDayCourseView", "update nothing");
                }
                fzr.this.n();
            }
        });
    }

    private void ad() {
        if (IntPlan.PlanType.AI_FITNESS_PLAN.equals(this.aa.getPlanType())) {
            a(q().size());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        y();
        r();
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(boolean z) {
        if (koq.c(this.n) && this.n.size() == 1 && "Race".equals(this.n.get(0).getActionId())) {
            return true;
        }
        if (this.aa != null) {
            return IntPlan.PlanType.AI_RUN_PLAN.equals(this.aa.getPlanType()) && z && koq.b(this.n);
        }
        LogUtil.h("IntPlanDayCourseView", "isShowCompletionView mPlan == null");
        return false;
    }

    private boolean a(IntDayPlan intDayPlan) {
        for (int i = 0; i < intDayPlan.getInPlanActionCnt(); i++) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i);
            if (inPlanAction != null && inPlanAction.getPunchFlag() == 0) {
                return false;
            }
        }
        return true;
    }

    private void p() {
        IntWeekPlan weekInfoByOrder;
        IntDayPlan dayByOrder;
        if (this.y.size() != 0) {
            this.z.setVisibility(0);
            IntPlan intPlan = this.aa;
            if (intPlan == null || (weekInfoByOrder = intPlan.getWeekInfoByOrder(this.al)) == null || (dayByOrder = weekInfoByOrder.getDayByOrder(this.k)) == null) {
                return;
            }
            if (dayByOrder.getPunchFlag() == 1 && !a(dayByOrder)) {
                this.u.setVisibility(0);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(gib.e(this.aa.getPlanTimeInfo().getBeginDate() * 1000, this.al, this.k));
                int i = calendar.get(2);
                int i2 = calendar.get(5);
                this.u.setText(this.af.getString(R.string._2130848698_res_0x7f022bba, String.valueOf(i + 1) + "/" + String.valueOf(i2)));
                return;
            }
        } else {
            this.z.setVisibility(8);
        }
        this.u.setVisibility(8);
    }

    private void s() {
        IntDayPlan dayByOrder;
        if (this.ag.size() != 0) {
            this.z.setVisibility(0);
            IntWeekPlan weekInfoByOrder = this.aa.getWeekInfoByOrder(this.al);
            if (weekInfoByOrder == null || (dayByOrder = weekInfoByOrder.getDayByOrder(this.k)) == null) {
                return;
            }
            if (dayByOrder.getPunchFlag() == 1 && !a(dayByOrder)) {
                this.u.setVisibility(0);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(gib.e(this.aa.getPlanTimeInfo().getBeginDate() * 1000, this.al, this.k));
                this.u.setText(this.af.getString(R.string._2130848699_res_0x7f022bbb, new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "M/d")).format(calendar.getTime())));
                return;
            }
        } else {
            this.z.setVisibility(8);
        }
        this.u.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        IntPlan intPlan = this.aa;
        if (intPlan == null) {
            x();
            return;
        }
        if (intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            final List<IntAction> q = q();
            this.g.clear();
            Iterator<IntAction> it = q.iterator();
            while (it.hasNext()) {
                this.g.add(it.next().getActionId());
            }
            ase.e(this.aa, this.al, this.k, new IBaseResponseCallback() { // from class: fzz
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    fzr.this.c(q, i, obj);
                }
            });
            return;
        }
        if (this.aa.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            ase.e(this.aa, this.al, this.k, new IBaseResponseCallback() { // from class: gaa
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    fzr.this.b(i, obj);
                }
            });
        } else {
            LogUtil.a("IntPlanDayCourseView", "checkCreateOutPlanRecordView doing nothing for fit plan");
        }
    }

    /* synthetic */ void c(List list, int i, Object obj) {
        List<RecordData> list2 = (List) obj;
        boolean d2 = d(this.w, (List<IntAction>) list, list2);
        this.w.clear();
        for (IntAction intAction : this.y) {
            this.w.add(new e(intAction, fys.a(list2, intAction.getActionId()), this.aj));
        }
        LogUtil.a("IntPlanDayCourseView", "checkCreateOutPlanRecordView ai fit plan mOutPlanCourseList = ", Integer.valueOf(this.y.size()), " changed:", Boolean.valueOf(d2));
        if (d2) {
            HandlerExecutor.e(new Runnable() { // from class: fzt
                @Override // java.lang.Runnable
                public final void run() {
                    fzr.this.i();
                }
            });
        }
    }

    /* synthetic */ void i() {
        p();
        this.x.clear();
        this.x.addAll(this.y);
        this.v.notifyDataSetChanged();
        t();
    }

    /* synthetic */ void b(int i, Object obj) {
        ArrayList arrayList = new ArrayList();
        List list = (List) obj;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (((RecordData) list.get(i2)).getIsInPlan() == 0) {
                arrayList.add((RecordData) list.get(i2));
            }
        }
        final boolean d2 = d(this.ae, arrayList);
        this.ag = arrayList;
        this.ae.clear();
        Iterator<RecordData> it = this.ag.iterator();
        while (it.hasNext()) {
            this.ae.add(new j(it.next()));
        }
        LogUtil.a("IntPlanDayCourseView", "checkCreateOutPlanRecordView ai run plan mRecordList = ", Integer.valueOf(this.ag.size()), " changed:", Boolean.valueOf(d2));
        HandlerExecutor.e(new Runnable() { // from class: fzx
            @Override // java.lang.Runnable
            public final void run() {
                fzr.this.a(d2);
            }
        });
    }

    /* synthetic */ void a(boolean z) {
        if (z) {
            s();
            this.ai.clear();
            this.ai.addAll(this.ag);
            this.ad.notifyDataSetChanged();
            t();
        }
    }

    private List<IntAction> q() {
        ArrayList arrayList = new ArrayList(fys.e(this.aa, this.al, this.k));
        this.y = arrayList;
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        final int i;
        synchronized (d) {
            b++;
            i = b;
        }
        if (this.aa.getPlanType() == IntPlan.PlanType.FIT_PLAN) {
            ArrayList arrayList = new ArrayList();
            for (IntAction intAction : this.n) {
                if (!this.m.containsKey(intAction.getActionId())) {
                    arrayList.add(new ffl.d(intAction.getActionId()).e(this.aa.getPlanId()).b());
                }
            }
            LogUtil.a("IntPlanDayCourseView", "createCourseView requestList size:", Integer.valueOf(arrayList.size()));
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (arrayList.size() > 0 && courseApi != null) {
                courseApi.getCourseByIds(arrayList, false, new UiCallback<List<FitWorkout>>() { // from class: fzr.1
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i2, String str) {
                        LogUtil.b("IntPlanDayCourseView", "createCourseView getCourseByIds error:", str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(List<FitWorkout> list) {
                        if (list == null) {
                            return;
                        }
                        LogUtil.a("IntPlanDayCourseView", "createCourseView getCourseByIds success:", Integer.valueOf(list.size()));
                        for (FitWorkout fitWorkout : list) {
                            LogUtil.a("IntPlanDayCourseView", "createCourseView fitWorkout : ", fitWorkout.acquireId());
                            fzr.this.m.put(fitWorkout.acquireId(), fitWorkout);
                        }
                        synchronized (fzr.d) {
                            if (i == fzr.b) {
                                fzr.this.w();
                            }
                        }
                    }
                });
                return;
            }
        }
        w();
    }

    private boolean d(List<e> list, List<IntAction> list2, List<RecordData> list3) {
        if (list.size() != list2.size()) {
            return true;
        }
        for (int i = 0; i < list.size(); i++) {
            e eVar = list.get(i);
            IntAction intAction = list2.get(i);
            if (eVar.b != null) {
                if (eVar.b.equals(intAction.getActionId()) && eVar.f12718a == intAction.getPunchFlag() && eVar.d == intAction.getActionType() && eVar.c == this.aj) {
                    RecordData a2 = fys.a(list3, intAction.getActionId());
                    if (eVar.e == null) {
                        if (a2 != null) {
                            return true;
                        }
                    } else if (a2 != null && eVar.e.getRecordId() != null && eVar.e.getRecordId().equals(a2.getRecordId())) {
                    }
                }
                return true;
            }
            if (intAction.getActionId() != null) {
                return true;
            }
        }
        return false;
    }

    private boolean d(List<j> list, List<RecordData> list2) {
        if (list.size() != list2.size()) {
            return true;
        }
        for (int i = 0; i < list.size(); i++) {
            j jVar = list.get(i);
            RecordData recordData = list2.get(i);
            if (jVar.e != null && !jVar.e.equals(recordData.getRecordId())) {
                return true;
            }
        }
        return true;
    }

    public void g() {
        long j2;
        IntPlan intPlan = this.aa;
        if (intPlan != null) {
            if (IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType())) {
                int h2 = ase.h(this.aa);
                j2 = DateFormatUtil.c(h2);
                ReleaseLogUtil.e("IntPlanDayCourseView", "showStartDay startDate ", Integer.valueOf(h2));
            } else {
                j2 = ase.j(this.aa);
            }
            ReleaseLogUtil.e("IntPlanDayCourseView", "showStartDay startTime ", Long.valueOf(j2));
            String formatDateTime = DateUtils.formatDateTime(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), j2, 24);
            if (LanguageUtil.bt(this.i)) {
                Date date = new Date(j2);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int i = calendar.get(2);
                int i2 = calendar.get(5);
                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setGroupingUsed(false);
                numberFormat.setMinimumIntegerDigits(2);
                formatDateTime = numberFormat.format(i2) + Constants.LINK + numberFormat.format(i + 1);
            }
            if (LanguageUtil.bt(this.i) || LanguageUtil.bj(this.i) || LanguageUtil.ar(this.i)) {
                a(nsf.b(R.string._2130846123_res_0x7f0221ab, formatDateTime));
                return;
            }
            a(nsf.b(R.string._2130846123_res_0x7f0221ab, formatDateTime + " "));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (this.ak != null) {
            this.r.post(new Runnable() { // from class: fzr.4
                @Override // java.lang.Runnable
                public void run() {
                    fzr.this.ac.measure(View.MeasureSpec.makeMeasureSpec(fzr.this.ak.itemView.getMeasuredWidth(), Integer.MIN_VALUE), 0);
                    fzr.this.ak.e(0, fzr.this.ac.getMeasuredHeight());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aJj_(final View view, final IntAction intAction, final Context context) {
        ase.e(this.aa, this.al, this.k, new IBaseResponseCallback() { // from class: fzw
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                fzr.this.aJn_(intAction, view, context, i, obj);
            }
        });
    }

    /* synthetic */ void aJn_(final IntAction intAction, final View view, final Context context, int i, Object obj) {
        final RecordData a2 = fys.a((List) obj, intAction.getActionId());
        if (a2 == null || TextUtils.isEmpty(a2.getRecordId()) || TextUtils.isEmpty(a2.getWorkoutId())) {
            return;
        }
        LogUtil.h("IntPlanDayCourseView", "show record click workoutId:", intAction.getActionId());
        HandlerExecutor.e(new Runnable() { // from class: fzv
            @Override // java.lang.Runnable
            public final void run() {
                fzr.this.aJm_(view, intAction, a2, context);
            }
        });
    }

    /* synthetic */ void aJm_(View view, final IntAction intAction, final RecordData recordData, final Context context) {
        view.setVisibility(0);
        view.setOnClickListener(new View.OnClickListener() { // from class: gac
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                fzr.this.aJl_(intAction, recordData, context, view2);
            }
        });
    }

    /* synthetic */ void aJl_(IntAction intAction, RecordData recordData, Context context, View view) {
        if (intAction.getActionType() == IntAction.ActionType.RUN) {
            c(recordData);
        } else {
            c(recordData, context);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aJk_(View view, final RecordData recordData, Context context) {
        view.setVisibility(0);
        view.setOnClickListener(new View.OnClickListener() { // from class: fzy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                fzr.this.aJo_(recordData, view2);
            }
        });
    }

    /* synthetic */ void aJo_(RecordData recordData, View view) {
        c(recordData);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(RecordData recordData) {
        String recordId = recordData.getRecordId();
        if (recordId == null) {
            LogUtil.h("IntPlanDayCourseView", "button_show trajectoryId == null ");
        } else {
            fis.d().a(recordId);
        }
    }

    private void c(RecordData recordData, Context context) {
        String workoutId = recordData.getWorkoutId();
        List<WorkoutRecord> arrayList = new ArrayList<>();
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("IntPlanDayCourseView", "recordView courseApi is null");
        } else {
            arrayList = courseApi.getWorkoutRecords(this.aa.getPlanId(), new UiCallback<List<WorkoutRecord>>() { // from class: fzr.7
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("IntPlanDayCourseView", "recordView getWorkoutRecords error");
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<WorkoutRecord> list) {
                    LogUtil.a("IntPlanDayCourseView", "recordView getWorkoutRecords success:", Integer.valueOf(list.size()));
                }
            });
        }
        for (WorkoutRecord workoutRecord : arrayList) {
            if (workoutId.equals(workoutRecord.acquireWorkoutId()) && (workoutRecord.acquireExerciseTime() / 1000) * 1000 == (recordData.getEndTime() / 1000) * 1000) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("workout_record", workoutRecord);
                AppRouter.b("/PluginFitnessAdvice/FitnessResultActivity").zF_(bundle).c(context);
                return;
            }
        }
        AppRouter.b("/PluginFitnessAdvice/FitnessResultActivity").zF_(new Bundle()).c(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aJg_(FrameLayout frameLayout, final String str, final int i, final FitWorkout fitWorkout) {
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: fzr.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HashMap hashMap = new HashMap(1);
                hashMap.put("click", 1);
                ixx.d().d(fzr.this.i, AnalyticsValue.INT_PLAN_1120029.value(), hashMap, 0);
                FitWorkout fitWorkout2 = fitWorkout;
                if (fitWorkout2 != null) {
                    if (fitWorkout2.isRunModelCourse()) {
                        fzr.this.b(fitWorkout, i + 1);
                    } else {
                        fzr.this.a(fitWorkout, i + 1);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
                if (courseApi == null) {
                    LogUtil.h("IntPlanDayCourseView", "requestCourseList : courseApi is null.");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    courseApi.getCourseById(new ffl.d(str).c(false).b(), new UiCallback<Workout>() { // from class: fzr.6.2
                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        public void onFailure(int i2, String str2) {
                            LogUtil.h("IntPlanDayCourseView", "getCourseById fail,", str2, " workoutId:", str);
                        }

                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        /* renamed from: d, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(Workout workout) {
                            LogUtil.c("IntPlanDayCourseView", "getCourseById onSuccess id:", str);
                            FitWorkout a2 = mod.a(workout);
                            if (a2.isRunModelCourse()) {
                                fzr.this.b(a2, i + 1);
                            } else {
                                fzr.this.a(a2, i + 1);
                            }
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    /* renamed from: fzr$9, reason: invalid class name */
    class AnonymousClass9 implements View.OnLongClickListener {
        final /* synthetic */ IntAction d;
        final /* synthetic */ boolean e;

        AnonymousClass9(boolean z, IntAction intAction) {
            this.e = z;
            this.d = intAction;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (this.e) {
                return true;
            }
            NoTitleCustomAlertDialog.Builder e = new NoTitleCustomAlertDialog.Builder(fzr.this.i).e(R.string._2130848644_res_0x7f022b84);
            final IntAction intAction = this.d;
            e.czC_(R.string._2130848357_res_0x7f022a65, new View.OnClickListener() { // from class: gad
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    fzr.AnonymousClass9.this.aJq_(intAction, view2);
                }
            }).czz_(R.string._2130839509_res_0x7f0207d5, new View.OnClickListener() { // from class: gab
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ViewClickInstrumentation.clickOnView(view2);
                }
            }).e().show();
            return true;
        }

        /* synthetic */ void aJq_(IntAction intAction, View view) {
            moc mocVar = new moc();
            mocVar.b(fzr.this.aa.getPlanId());
            mocVar.a(fzr.this.k);
            mocVar.b(fzr.this.al);
            mocVar.d(1);
            ArrayList arrayList = new ArrayList();
            mnr mnrVar = new mnr();
            mnrVar.a(intAction.getActionId());
            mnrVar.b(IntAction.ActionType.WORKOUT.getType());
            arrayList.add(mnrVar);
            mocVar.d(arrayList);
            ((PlanApi) Services.a("CoursePlanService", PlanApi.class)).updateAction(mocVar, new UiCallback<String>() { // from class: fzr.9.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("IntPlanDayCourseView", "updateAction fail");
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    LogUtil.c("IntPlanDayCourseView", "updateAction success");
                    fzr.this.t();
                    fzr.this.o();
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aJh_(FrameLayout frameLayout, boolean z, IntAction intAction) {
        frameLayout.setOnLongClickListener(new AnonymousClass9(z, intAction));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        LogUtil.a("IntPlanDayCourseView", "requestCourseList notifyDataSetChanged:", Integer.valueOf(this.n.size()));
        this.o.clear();
        this.o.addAll(this.n);
        this.s.notifyDataSetChanged();
        t();
    }

    public void e(IntPlan intPlan) {
        IntPlan intPlan2 = this.aa;
        if ((intPlan2 == null || intPlan2.getPlanType() != intPlan.getPlanType()) && intPlan != null) {
            if (intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
                this.ab.setVisibility(0);
                this.x.clear();
                a aVar = new a(this.i, this.x, false);
                this.v = aVar;
                this.ab.setAdapter(aVar);
            } else if (intPlan.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
                this.ab.setVisibility(0);
                this.ad = new f(this.i, this.ai);
                this.ai.clear();
                this.ab.setAdapter(this.ad);
            } else {
                this.ab.setVisibility(8);
                this.u.setVisibility(8);
            }
        }
        this.aa = intPlan;
    }

    public void e(int i) {
        this.aj = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(FitWorkout fitWorkout, int i) {
        long beginDate = this.aa.getPlanTimeInfo().getBeginDate() * 1000;
        long e2 = gib.e(beginDate, this.al, this.k);
        ReleaseLogUtil.e("IntPlanDayCourseView", "startRunCourse time:", Long.valueOf(e2), " week:", Integer.valueOf(this.al), " day:", Integer.valueOf(this.k));
        int i2 = this.aj;
        if (i2 == 0) {
            e2 = c(i);
        } else if (i2 == 3 && a(beginDate)) {
            e2 = -1;
        }
        gds.e(this.i, fitWorkout, this.aa, e2, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(FitWorkout fitWorkout, int i) {
        long beginDate = this.aa.getPlanTimeInfo().getBeginDate() * 1000;
        long e2 = gib.e(beginDate, this.al, this.k);
        ReleaseLogUtil.e("IntPlanDayCourseView", "startFitnessCourse time:", Long.valueOf(e2), " week:", Integer.valueOf(this.al), "  day:", Integer.valueOf(this.k));
        int i2 = this.aj;
        if (i2 == 0) {
            e2 = c(i);
        } else if (i2 == 3 && a(beginDate)) {
            e2 = -1;
        }
        gds.d(this.i, fitWorkout, this.aa, i, e2);
    }

    private long c(int i) {
        int i2 = i - 1;
        IntDayPlan dayInfo = this.aa.getDayInfo(this.al, this.k);
        if (dayInfo == null || i2 < 0) {
            return 0L;
        }
        IntAction inPlanAction = dayInfo.getInPlanAction(i2);
        return (inPlanAction == null || inPlanAction.getPunchFlag() != 1) ? -2L : -3L;
    }

    private boolean a(long j2) {
        return gib.e(j2, System.currentTimeMillis()) + 1 == this.al;
    }

    private void a(String str) {
        Context context;
        f();
        if (this.am == null || (context = this.i) == null) {
            return;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.sug_layout_intplan_course_card_view, (ViewGroup) this.am, true);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.plan_trained_state);
        this.ap = imageView;
        imageView.setVisibility(0);
        this.ap.setImageResource(R.drawable._2131430559_res_0x7f0b0c9f);
        ((ImageView) inflate.findViewById(R.id.plan_course_item_background)).setBackgroundResource(R.drawable.ic_pic_course_fitness_background);
        inflate.findViewById(R.id.course_information_layout).setVisibility(8);
        inflate.findViewById(R.id.rest_day_information_layout).setVisibility(0);
        TextView textView = (TextView) inflate.findViewById(R.id.weight_left);
        this.aq = textView;
        textView.setText(str);
        aJf_(inflate);
        t();
    }

    private void k() {
        Context context;
        if (this.ah == null || (context = this.i) == null) {
            return;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.sug_layout_intplan_course_card_view, (ViewGroup) this.ah, true);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.plan_trained_state);
        this.ap = imageView;
        imageView.setVisibility(8);
        ((ImageView) inflate.findViewById(R.id.plan_course_item_background)).setBackgroundResource(R.drawable.ic_pic_course_fitness_background);
        inflate.findViewById(R.id.course_information_layout).setVisibility(8);
        inflate.findViewById(R.id.rest_day_information_layout).setVisibility(0);
        TextView textView = (TextView) inflate.findViewById(R.id.weight_left);
        this.aq = textView;
        textView.setText(R.string._2130848765_res_0x7f022bfd);
        aJf_(inflate);
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        Context context;
        if (this.ah == null || (context = this.i) == null) {
            return;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.sug_layout_intplan_course_card_view, (ViewGroup) this.ah, true);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.plan_trained_state);
        this.ap = imageView;
        imageView.setVisibility(8);
        ((ImageView) inflate.findViewById(R.id.plan_course_item_background)).setBackgroundResource(R.drawable.ic_pic_course_fitness_background);
        inflate.findViewById(R.id.course_information_layout).setVisibility(8);
        inflate.findViewById(R.id.rest_day_information_layout).setVisibility(0);
        TextView textView = (TextView) inflate.findViewById(R.id.weight_left);
        this.aq = textView;
        textView.setText(R.string._2130846124_res_0x7f0221ac);
        IntPlan intPlan = this.aa;
        if (intPlan == null) {
            return;
        }
        if (intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            this.ap.setVisibility(0);
            this.f12712a = (HealthButton) inflate.findViewById(R.id.record_button);
            this.c = gib.e(this.aa.getPlanTimeInfo().getBeginDate() * 1000, this.al, this.k);
            this.an.a(0);
            HiAggregateOption hiAggregateOption = new HiAggregateOption();
            hiAggregateOption.setTimeRange(gib.b(this.c), gib.d(this.c));
            hiAggregateOption.setAggregateType(0);
            hiAggregateOption.setGroupUnitType(0);
            hiAggregateOption.setCount(1);
            WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
            if (weightApi != null) {
                weightApi.getWeightData(hiAggregateOption, this.an);
            } else {
                LogUtil.h("IntPlanDayCourseView", "weightApi is null.");
            }
        }
        aJf_(inflate);
        t();
    }

    private void aJf_(View view) {
        int i;
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        int intValue = (((this.af.getDisplayMetrics().widthPixels - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue()) - (((int) this.af.getDimension(R.dimen._2131362366_res_0x7f0a023e)) * 2)) - (((int) this.af.getDimension(R.dimen._2131362365_res_0x7f0a023d)) * 2);
        if (nsn.ag(this.i)) {
            int i2 = this.af.getDisplayMetrics().widthPixels;
            int intValue2 = ((Integer) safeRegionWidth.first).intValue();
            i = (int) (((((((i2 - intValue2) - ((Integer) safeRegionWidth.second).intValue()) - (((int) this.af.getDimension(R.dimen._2131362366_res_0x7f0a023e)) * 3)) - (((int) this.af.getDimension(R.dimen._2131362365_res_0x7f0a023d)) * 2)) / 2) * 9.0f) / 21.0f);
            intValue = (int) ((i * 21.0d) / 9.0d);
        } else {
            i = (int) ((intValue * 9.0f) / 21.0f);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(intValue, i);
        ((ImageView) view.findViewById(R.id.plan_course_item_background)).setLayoutParams(layoutParams);
        view.findViewById(R.id.plan_trained_layout).setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(intValue, i);
        if (this.aa.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN && fys.a(this.aa, this.al, this.k)) {
            layoutParams2.topMargin = nsn.c(this.i, 12.0f);
        }
        ((FrameLayout) view.findViewById(R.id.plan_course_item)).setLayoutParams(layoutParams2);
    }

    static class d implements WeightCallback {
        private int b;
        private WeakReference<fzr> c;

        public void a(int i) {
            this.b = i;
        }

        private d(fzr fzrVar, int i) {
            this.c = new WeakReference<>(fzrVar);
            this.b = i;
        }

        @Override // com.huawei.ui.main.stories.health.weight.callback.WeightCallback
        public void getWeight(ArrayList<qtm> arrayList) {
            WeakReference<fzr> weakReference = this.c;
            if (weakReference == null) {
                Log.w("IntPlanDayCourseView", "LocalReceiver onReceive mWeakReference is null");
                return;
            }
            fzr fzrVar = weakReference.get();
            if (fzrVar == null) {
                Log.w("IntPlanDayCourseView", "weightBeforeInteractor is not null");
                return;
            }
            if (fzrVar.j() != null) {
                if (fzrVar.an != null) {
                    Message obtain = Message.obtain();
                    obtain.what = this.b;
                    obtain.obj = arrayList;
                    fzrVar.e.sendMessage(obtain);
                    return;
                }
                return;
            }
            Log.w("IntPlanDayCourseView", "weightBeforeInteractor mContext is not exist");
        }
    }

    public static class b extends BaseHandler<fzr> {
        b(fzr fzrVar) {
            super(fzrVar);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aJs_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(fzr fzrVar, Message message) {
            if (message == null || fzrVar == null) {
                Log.w("IntPlanDayCourseView", "handleMessageWhenReferenceNotNull obj = null or msg = null !");
                return;
            }
            int i = message.what;
            if (i == 0) {
                fzrVar.a((ArrayList<qtm>) message.obj);
            } else if (i == 1) {
                fzrVar.b((ArrayList<qtm>) message.obj);
            } else {
                Log.i("IntPlanDayCourseView", "handleMessageWhenReferenceNotNull default branch !");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ArrayList<qtm> arrayList) {
        boolean z = this.al == ase.g(this.aa) && this.k == gib.d(Calendar.getInstance().get(7));
        LogUtil.c("IntPlanDayCourseView", "isToday", Boolean.valueOf(z), Integer.valueOf(arrayList.size()));
        if (koq.c(arrayList)) {
            this.aq.setText(R.string._2130848654_res_0x7f022b8e);
            this.aq.setVisibility(0);
            double e2 = arrayList.get(0).e();
            if (UnitUtil.h()) {
                e2 = UnitUtil.h(e2);
            }
            DecimalFormat decimalFormat = new DecimalFormat("#.0");
            Context context = com.huawei.hwcommonmodel.application.BaseApplication.getContext();
            if (UnitUtil.h()) {
                this.aq.setText(context.getString(R.string._2130848654_res_0x7f022b8e, context.getResources().getQuantityString(R.plurals._2130903216_res_0x7f0300b0, (int) e2, decimalFormat.format(e2))));
            } else {
                this.aq.setText(context.getString(R.string._2130848654_res_0x7f022b8e, context.getResources().getQuantityString(R.plurals._2130903215_res_0x7f0300af, (int) e2, decimalFormat.format(e2))));
            }
            this.ap.setImageResource(R.drawable._2131430244_res_0x7f0b0b64);
            return;
        }
        if (arrayList.size() == 0 && z) {
            this.f12712a.setVisibility(0);
            this.aq.setText(R.string._2130846124_res_0x7f0221ac);
            this.aq.setVisibility(0);
            this.ap.setImageResource(R.drawable._2131430247_res_0x7f0b0b67);
            HiAggregateOption hiAggregateOption = new HiAggregateOption();
            hiAggregateOption.setTimeRange(0L, gib.d(this.c));
            hiAggregateOption.setAggregateType(0);
            hiAggregateOption.setGroupUnitType(0);
            hiAggregateOption.setCount(1);
            this.an.a(1);
            WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
            if (weightApi != null) {
                weightApi.getWeightData(hiAggregateOption, this.an);
                return;
            } else {
                LogUtil.h("IntPlanDayCourseView", "weightApi is null.");
                return;
            }
        }
        this.aq.setText(R.string._2130846124_res_0x7f0221ac);
        this.aq.setVisibility(0);
        this.ap.setImageResource(R.drawable._2131430247_res_0x7f0b0b67);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ArrayList<qtm> arrayList) {
        qtm qtmVar;
        if (koq.c(arrayList) && (qtmVar = arrayList.get(0)) != null) {
            this.f = qtmVar.e();
            this.j = qtmVar.b();
        }
        this.f12712a.setOnClickListener(new AnonymousClass10());
    }

    /* renamed from: fzr$10, reason: invalid class name */
    class AnonymousClass10 implements View.OnClickListener {
        AnonymousClass10() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ggu.d(fzr.this.i, fzr.this.f, fzr.this.j);
            fzr.this.r.postDelayed(new Runnable() { // from class: gaf
                @Override // java.lang.Runnable
                public final void run() {
                    ary.a().e("PLAN_UPDATE");
                }
            }, 1000L);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public int d() {
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public ArrayList<String> a() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        LinearLayout linearLayout = this.am;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        LinearLayout linearLayout2 = this.ah;
        if (linearLayout2 != null) {
            linearLayout2.removeAllViews();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.o.clear();
        this.n.clear();
        this.p.clear();
        a aVar = this.s;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    private void x() {
        this.z.setVisibility(8);
        this.u.setVisibility(8);
        this.x.clear();
        this.ai.clear();
        this.y.clear();
        this.w.clear();
        a aVar = this.v;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public void f() {
        y();
        r();
        x();
    }

    public void e() {
        int i;
        int punchFlag;
        ArrayList arrayList = new ArrayList();
        List<IntAction> list = this.n;
        if (list != null) {
            i = 0;
            for (IntAction intAction : list) {
                arrayList.add(intAction);
                i += intAction.getPunchFlag();
            }
        } else {
            i = 0;
        }
        List<IntAction> list2 = this.y;
        if (list2 != null) {
            for (IntAction intAction2 : list2) {
                List<IntAction> list3 = this.n;
                if (list3 == null) {
                    arrayList.add(intAction2);
                    punchFlag = intAction2.getPunchFlag();
                } else if (!list3.contains(intAction2)) {
                    arrayList.add(intAction2);
                    punchFlag = intAction2.getPunchFlag();
                }
                i += punchFlag;
            }
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("addedNum", Integer.valueOf(this.h));
        hashMap.put("goalNum", Integer.valueOf(arrayList.size()));
        hashMap.put("finishNum", Integer.valueOf(i));
        ixx.d().d(this.i, AnalyticsValue.INT_PLAN_1120038.value(), hashMap, 0);
    }

    public boolean h() {
        return this.n.size() == 0;
    }
}
