package com.huawei.health.suggestion.ui;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.RunPlanReportAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.barlist.HealthBarListView;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarView;
import com.huawei.ui.commonui.calendarview.HealthMark;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ffy;
import defpackage.gib;
import defpackage.koq;
import defpackage.mnu;
import defpackage.mnx;
import defpackage.mny;
import defpackage.moa;
import defpackage.moe;
import defpackage.nkq;
import defpackage.nkz;
import defpackage.nld;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class RunPlanReportAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private mny f3058a;
    private Context b;
    private UiCallback c;
    private final int e;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 4;
    }

    public RunPlanReportAdapter(int i, Context context) {
        this.b = context;
        this.e = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2 || this.e != 0) {
                i2 = 3;
                if (i != 3) {
                    LogUtil.b("sug_RunPlanReportAdapter", "position is out of index");
                    return -1;
                }
            }
        }
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ayD_, reason: merged with bridge method [inline-methods] */
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (viewGroup == null) {
            LogUtil.b("sug_RunPlanReportAdapter", "parent is null");
            return new RecyclerViewHolder(this.b, new View(this.b), i);
        }
        if (i == 0 && this.f3058a.j()) {
            return new RecyclerViewHolder(this.b, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_report_coach_comments_item, viewGroup, false), 0);
        }
        if (1 == i) {
            return new RecyclerViewHolder(this.b, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_report_training_completion_detail_item, viewGroup, false), 1);
        }
        if (2 == i && this.e == 0 && this.f3058a.j()) {
            return new RecyclerViewHolder(this.b, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_report_last_week_summary_item, viewGroup, false), 2);
        }
        if (3 == i && a() && this.f3058a.j()) {
            return new RecyclerViewHolder(this.b, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_report_performance_prediction_item, viewGroup, false), 3);
        }
        return new RecyclerViewHolder(this.b, new View(this.b), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int i) {
        if (recyclerViewHolder == null || this.f3058a == null) {
            return;
        }
        if (recyclerViewHolder.y == 0) {
            String c = this.f3058a.c();
            if (TextUtils.isEmpty(c) || !this.f3058a.j()) {
                return;
            }
            recyclerViewHolder.b.setText(c);
            return;
        }
        if (1 == recyclerViewHolder.y) {
            a(recyclerViewHolder);
            return;
        }
        if (2 == recyclerViewHolder.y && this.e == 0 && this.f3058a.j()) {
            e(recyclerViewHolder);
        } else if (3 == recyclerViewHolder.y && this.f3058a.j()) {
            d(recyclerViewHolder);
        } else {
            LogUtil.b("sug_RunPlanReportAdapter", "Holder mType ==", Integer.valueOf(recyclerViewHolder.y));
        }
    }

    private void d(RecyclerViewHolder recyclerViewHolder) {
        if (this.f3058a.i() == 0 || this.f3058a.m() == 0 || this.f3058a.f() == 0) {
            LogUtil.b("sug_RunPlanReportAdapter", "getTargetTime  =", Integer.valueOf(this.f3058a.m()), "before = ", Integer.valueOf(this.f3058a.i()), "current  =", Integer.valueOf(this.f3058a.f()));
            UiCallback uiCallback = this.c;
            if (uiCallback != null) {
                uiCallback.onSuccess("");
                return;
            }
            return;
        }
        f(recyclerViewHolder);
        c(recyclerViewHolder);
        recyclerViewHolder.f3060a.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.huawei.health.suggestion.ui.RunPlanReportAdapter.4
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                LogUtil.a("sug_RunPlanReportAdapter", "addOnLayoutChangeListener");
                if (RunPlanReportAdapter.this.c == null || !RunPlanReportAdapter.this.f3058a.t()) {
                    return;
                }
                RunPlanReportAdapter.this.c.onSuccess("");
            }
        });
    }

    private void c(RecyclerViewHolder recyclerViewHolder) {
        LogUtil.a("sug_RunPlanReportAdapter", "getTargetTime  =", Integer.valueOf(this.f3058a.m()), "before = ", Integer.valueOf(this.f3058a.i()), "current  =", Integer.valueOf(this.f3058a.f()));
        recyclerViewHolder.l.setText(b(this.f3058a.m()));
        recyclerViewHolder.k.setText(b(this.f3058a.f()));
        b(recyclerViewHolder);
        if (this.f3058a.m() - this.f3058a.f() >= 0) {
            recyclerViewHolder.f3060a.setText(this.b.getResources().getString(R.string._2130845568_res_0x7f021f80));
        } else {
            recyclerViewHolder.f3060a.setText(this.b.getResources().getString(R.string._2130844473_res_0x7f021b39));
        }
    }

    private void f(RecyclerViewHolder recyclerViewHolder) {
        if (this.f3058a.t()) {
            recyclerViewHolder.g.setRightArrayVisibility(8);
        } else {
            recyclerViewHolder.g.setRightArrayVisibility(0);
        }
        recyclerViewHolder.g.setRightDrawable(R.drawable._2131430067_res_0x7f0b0ab3);
        recyclerViewHolder.g.setSubHeaderBackgroundColor(ContextCompat.getColor(this.b, R.color._2131296971_res_0x7f0902cb));
        recyclerViewHolder.g.setMoreTextVisibility(4);
        recyclerViewHolder.g.setMoreViewClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.RunPlanReportAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RunPlanReportAdapter.this.c(1);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void e(RecyclerViewHolder recyclerViewHolder) {
        String[] a2 = a(this.f3058a.h().e());
        recyclerViewHolder.v.setText(a2[0]);
        recyclerViewHolder.w.setText(a2[1]);
        String[] d = d(this.f3058a.h().c());
        recyclerViewHolder.ad.setText(d[0]);
        recyclerViewHolder.z.setText(d[1]);
        recyclerViewHolder.ac.setText(UnitUtil.e(this.f3058a.h().a(), 1, 0));
        recyclerViewHolder.aa.setText(BaseApplication.getContext().getResources().getString(R.string._2130837534_res_0x7f02001e, ""));
        recyclerViewHolder.f.setVisibility(8);
        recyclerViewHolder.i.setVisibility(8);
        e(recyclerViewHolder, this.f3058a);
        if (this.f3058a.t()) {
            recyclerViewHolder.i.setVisibility(8);
        }
        recyclerViewHolder.i.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.RunPlanReportAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RunPlanReportAdapter.this.c(0);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        recyclerViewHolder.h.setSubHeaderBackgroundColor(ContextCompat.getColor(this.b, R.color._2131296971_res_0x7f0902cb));
    }

    private void a(RecyclerViewHolder recyclerViewHolder) {
        if (this.f3058a.s() == null) {
            LogUtil.b("sug_RunPlanReportAdapter", "mTrainReportBean.getTotalStatisticsData() == null");
            return;
        }
        d(recyclerViewHolder, c(this.f3058a.s().a()));
        List<Integer> a2 = this.f3058a.s().a();
        List<Integer> b = this.f3058a.s().b();
        if (koq.b(a2) || koq.b(b)) {
            LogUtil.h("sug_RunPlanReportAdapter", "realClockInDayList || targetClockInDayList is null");
            return;
        }
        e(recyclerViewHolder, recyclerViewHolder.j, c(a2), c(b));
    }

    private List<Integer> c(List<Integer> list) {
        if (this.e == 1) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i < this.f3058a.q()) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    private void e(RecyclerViewHolder recyclerViewHolder, HealthBarListView healthBarListView, List<Integer> list, List<Integer> list2) {
        long a2;
        if (list2.size() >= list.size()) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < list2.size()) {
                int intValue = koq.d(list, i) ? list.get(i).intValue() : 0;
                int intValue2 = list2.get(i).intValue();
                i++;
                arrayList.add(new nkq(intValue, intValue2, this.b.getResources().getString(R.string._2130845622_res_0x7f021fb6, UnitUtil.e(intValue, 1, 0), UnitUtil.e(intValue2, 1, 0)), this.b.getResources().getQuantityString(R.plurals._2130903313_res_0x7f030111, i, Integer.valueOf(i))));
                i3 += intValue2;
                i2 += intValue;
            }
            if (this.f3058a.o() > 0) {
                a2 = this.f3058a.o() * 1000;
            } else {
                a2 = gib.a(this.f3058a.n());
            }
            int e = gib.e(a2, System.currentTimeMillis());
            healthBarListView.setBarColor(this.b.getResources().getColor(R.color._2131296927_res_0x7f09029f));
            healthBarListView.setData(arrayList, e + 1);
            a(recyclerViewHolder, list2, i2, i3);
        }
    }

    private boolean a(List<Integer> list) {
        mny mnyVar = this.f3058a;
        if (mnyVar == null || mnyVar.s() == null) {
            LogUtil.b("sug_RunPlanReportAdapter", "isShowCalendar data null");
            return false;
        }
        if (!koq.b(list)) {
            return list.size() <= 4 && koq.c(this.f3058a.k());
        }
        LogUtil.b("sug_RunPlanReportAdapter", "isShowCalendar targetClockInDayList is 0");
        return false;
    }

    private void a(RecyclerViewHolder recyclerViewHolder, List<Integer> list, int i, int i2) {
        String quantityString = this.b.getResources().getQuantityString(R.plurals._2130903481_res_0x7f0301b9, i2, UnitUtil.e(i, 1, 0), UnitUtil.e(i2, 1, 0));
        if (a(list)) {
            recyclerViewHolder.c.setVisibility(0);
            recyclerViewHolder.e.setVisibility(0);
            recyclerViewHolder.j.setVisibility(8);
            recyclerViewHolder.c.setText(ffy.d(this.b, R.string._2130845469_res_0x7f021f1d, quantityString));
            a(recyclerViewHolder, list);
            return;
        }
        recyclerViewHolder.c.setVisibility(8);
        recyclerViewHolder.e.setVisibility(8);
        recyclerViewHolder.j.setVisibility(0);
        recyclerViewHolder.j.setTitle(ffy.d(this.b, R.string._2130845469_res_0x7f021f1d, quantityString));
    }

    private void d(RecyclerViewHolder recyclerViewHolder, List<Integer> list) {
        float a2 = moe.a(this.f3058a.s().g());
        recyclerViewHolder.o.setText(UnitUtil.e(a2, 2, 1));
        recyclerViewHolder.m.setProgress(Math.round(a2));
        if (this.f3058a.j()) {
            String[] d = d(this.f3058a.s().e());
            recyclerViewHolder.r.setText(d[0]);
            recyclerViewHolder.t.setText(d[1]);
            recyclerViewHolder.u.setText(R.string._2130844187_res_0x7f021a1b);
        } else {
            recyclerViewHolder.u.setText(R.string._2130848530_res_0x7f022b12);
            int e = e(list);
            recyclerViewHolder.r.setText(UnitUtil.e(e, 1, 0));
            recyclerViewHolder.t.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903243_res_0x7f0300cb, e));
        }
        String[] a3 = a(this.f3058a.s().d());
        recyclerViewHolder.p.setText(a3[1]);
        recyclerViewHolder.n.setText(a3[0]);
        recyclerViewHolder.s.setText(BaseApplication.getContext().getResources().getString(R.string._2130837534_res_0x7f02001e, ""));
        recyclerViewHolder.q.setText(UnitUtil.e(this.f3058a.s().c(), 1, 0));
    }

    private int e(List<Integer> list) {
        int i = 0;
        if (koq.c(list)) {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                i += it.next().intValue();
            }
        }
        return i;
    }

    private String b(int i) {
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "Hms")).format(new Date(gib.g(i)));
    }

    private void b(RecyclerViewHolder recyclerViewHolder) {
        mny mnyVar = this.f3058a;
        if (mnyVar == null) {
            LogUtil.b("sug_RunPlanReportAdapter", "hideForGoalScoreView mTrainReportBean == null");
        } else if (mnyVar.f() == 0) {
            recyclerViewHolder.f3060a.setVisibility(8);
        }
    }

    private void e(RecyclerViewHolder recyclerViewHolder, mny mnyVar) {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(this.b.getString(R.string._2130842942_res_0x7f02153e, 5));
        arrayList.add(this.b.getString(R.string._2130842942_res_0x7f02153e, 4));
        arrayList.add(this.b.getString(R.string._2130842942_res_0x7f02153e, 3));
        arrayList.add(this.b.getString(R.string._2130842942_res_0x7f02153e, 2));
        arrayList.add(this.b.getString(R.string._2130842942_res_0x7f02153e, 1));
        List<nkz> b = b(arrayList, mnyVar);
        mnx h = mnyVar.h();
        List<Integer> d = h.d();
        final List<Integer> b2 = h.b();
        if (koq.b(d) || koq.b(b2)) {
            LogUtil.b("sug_RunPlanReportAdapter", "initIntensityStatistics realIntensityZone or targetIntensityZone is null");
            return;
        }
        HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.b, new nld().c(false).b(true), b);
        healthRingChartAdapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: fjw
            @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
            public final String format(nkz nkzVar) {
                return RunPlanReportAdapter.this.b(b2, nkzVar);
            }
        });
        recyclerViewHolder.f.setAdapter(healthRingChartAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String b(List<Integer> list, nkz nkzVar) {
        double i = nkzVar.i() / 60.0d;
        Context context = this.b;
        return context.getString(R.string._2130837534_res_0x7f02001e, context.getResources().getString(R.string._2130845622_res_0x7f021fb6, UnitUtil.e(i, 1, 0), UnitUtil.e(0.0d, 1, 0)));
    }

    private List<nkz> b(List<String> list, mny mnyVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298929_res_0x7f090a71)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298927_res_0x7f090a6f)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298925_res_0x7f090a6d)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298931_res_0x7f090a73)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298933_res_0x7f090a75)));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298928_res_0x7f090a70)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298926_res_0x7f090a6e)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298924_res_0x7f090a6c)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298930_res_0x7f090a72)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298932_res_0x7f090a74)));
        int size = list.size();
        List<Integer> d = mnyVar.h().d();
        ArrayList arrayList3 = new ArrayList(size);
        if (koq.b(d)) {
            LogUtil.b("sug_RunPlanReportAdapter", "realIntensityZone is empty");
            return arrayList3;
        }
        int i = 0;
        while (i < size) {
            arrayList3.add(new nkz(i < list.size() ? list.get(i) : "", koq.d(d, i) ? d.get(i).intValue() : 0, i < arrayList.size() ? ((Integer) arrayList.get(i)).intValue() : 0, i < arrayList2.size() ? ((Integer) arrayList2.get(i)).intValue() : 0));
            i++;
        }
        return arrayList3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        String string;
        String string2;
        if (i == 0) {
            string = this.b.getResources().getString(R.string._2130844805_res_0x7f021c85);
            string2 = this.b.getResources().getString(R.string._2130844465_res_0x7f021b31);
        } else if (i == 1) {
            string = this.b.getResources().getString(R.string._2130844804_res_0x7f021c84);
            string2 = this.b.getResources().getString(R.string._2130842098_res_0x7f0211f2);
        } else {
            LogUtil.b("sug_RunPlanReportAdapter", "explainType = ", Integer.valueOf(i));
            string = null;
            string2 = null;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.b).e(string).b(string2).cyU_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.RunPlanReportAdapter.2
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

    private String[] d(double d) {
        String string;
        String[] strArr = new String[2];
        if (UnitUtil.h()) {
            d = UnitUtil.e(d, 3);
        }
        String e = UnitUtil.e(d, 1, 2);
        if (UnitUtil.h()) {
            string = this.b.getResources().getString(R.string._2130841422_res_0x7f020f4e, "");
        } else {
            string = this.b.getResources().getString(R.string._2130841421_res_0x7f020f4d, "");
        }
        strArr[0] = e;
        strArr[1] = string;
        return strArr;
    }

    private String[] a(float f) {
        String string;
        String e;
        String[] strArr = new String[2];
        float b = moe.b(f);
        if (b >= 100000.0f) {
            string = BaseApplication.getContext().getResources().getString(R.string._2130850156_res_0x7f02316c);
            e = UnitUtil.e(b / 10000.0f, 1, 2);
        } else {
            string = BaseApplication.getContext().getResources().getString(R.string._2130837535_res_0x7f02001f, "");
            e = UnitUtil.e(b, 1, 0);
        }
        strArr[0] = e;
        strArr[1] = string;
        return strArr;
    }

    public void a(mny mnyVar) {
        this.f3058a = mnyVar;
        if (a()) {
            this.f3058a.a(false);
            notifyItemChanged(3);
        }
    }

    public void a(UiCallback uiCallback) {
        this.c = uiCallback;
        if (a()) {
            this.f3058a.a(true);
            notifyItemChanged(3);
        } else {
            this.c.onSuccess("");
        }
    }

    private boolean a() {
        mny mnyVar = this.f3058a;
        if (mnyVar != null) {
            return (mnyVar.i() == 0 || this.f3058a.m() == 0 || this.f3058a.f() == 0) ? false : true;
        }
        LogUtil.b("sug_RunPlanReportAdapter", "isValidValue mTrainReportBean == null");
        return false;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f3060a;
        private HealthTextView aa;
        private HealthTextView ac;
        private HealthTextView ad;
        private HealthTextView b;
        private HealthTextView c;
        private HealthCalendarView e;
        private HealthRingChart f;
        private HealthSubHeader g;
        private HealthSubHeader h;
        private ImageView i;
        private HealthBarListView j;
        private HealthTextView k;
        private HealthTextView l;
        private HealthProgressBar m;
        private HealthTextView n;
        private HealthTextView o;
        private HealthTextView p;
        private HealthTextView q;
        private HealthTextView r;
        private HealthTextView s;
        private HealthTextView t;
        private HealthTextView u;
        private HealthTextView v;
        private HealthTextView w;
        private View x;
        private int y;
        private HealthTextView z;

        public RecyclerViewHolder(Context context, View view, int i) {
            super(view);
            this.y = i;
            this.x = view;
            RunPlanReportAdapter.this.b = context;
            e();
        }

        private void e() {
            int i = this.y;
            if (i == 0) {
                this.b = (HealthTextView) this.x.findViewById(R.id.coach_comments);
                return;
            }
            if (i == 1) {
                this.o = (HealthTextView) this.x.findViewById(R.id.plan_finish_percent);
                this.m = (HealthProgressBar) this.x.findViewById(R.id.plan_report_progressbar);
                this.q = (HealthTextView) this.x.findViewById(R.id.sug_tv_total_plan_trains);
                this.s = (HealthTextView) this.x.findViewById(R.id.sug_tv_total_plan_unit);
                this.r = (HealthTextView) this.x.findViewById(R.id.sug_tv_plan_distance);
                this.t = (HealthTextView) this.x.findViewById(R.id.sug_tv_plan_distance_unit);
                this.n = (HealthTextView) this.x.findViewById(R.id.sug_tv_plan_calorie);
                this.p = (HealthTextView) this.x.findViewById(R.id.sug_tv_plan_calorie_unit);
                this.j = (HealthBarListView) this.x.findViewById(R.id.finish_record);
                this.u = (HealthTextView) this.x.findViewById(R.id.sug_tv_his_name1);
                b();
                return;
            }
            if (i != 2) {
                if (i == 3) {
                    this.g = (HealthSubHeader) this.x.findViewById(R.id.sub_header_performance_prediction);
                    this.l = (HealthTextView) this.x.findViewById(R.id.plan_target_time);
                    this.k = (HealthTextView) this.x.findViewById(R.id.plan_prediction_time);
                    this.f3060a = (HealthTextView) this.x.findViewById(R.id.plan_achieve_comment);
                    return;
                }
                LogUtil.b("sug_RunPlanReportAdapter", "initView type error");
                return;
            }
            this.ac = (HealthTextView) this.x.findViewById(R.id.sug_tv_week_plan_trains);
            this.aa = (HealthTextView) this.x.findViewById(R.id.sug_tv_week_plan_unit);
            this.ad = (HealthTextView) this.x.findViewById(R.id.sug_week_plan_distance);
            this.z = (HealthTextView) this.x.findViewById(R.id.sug_week_plan_distance_unit);
            this.v = (HealthTextView) this.x.findViewById(R.id.sug_week_plan_calorie);
            this.w = (HealthTextView) this.x.findViewById(R.id.sug_week_plan_calorie_unit);
            this.f = (HealthRingChart) this.x.findViewById(R.id.intensity_statistics_layout);
            this.i = (ImageView) this.x.findViewById(R.id.intensity_statistics_explain_icon);
            this.h = (HealthSubHeader) this.x.findViewById(R.id.last_week_summary_header);
        }

        private void b() {
            this.e = (HealthCalendarView) this.x.findViewById(R.id.report_calendarView);
            this.c = (HealthTextView) this.x.findViewById(R.id.calendar_title);
            this.e.setIsDrawSelected(false);
            this.e.b(true);
            this.e.setWeekViewScrollable(false);
            this.e.d(false);
            this.e.setMonthViewIsScroll(false);
            this.e.setOnCalendarSelectListener(null);
        }
    }

    private void a(RecyclerViewHolder recyclerViewHolder, List<Integer> list) {
        if (recyclerViewHolder != null) {
            recyclerViewHolder.e.a();
            e(recyclerViewHolder, list);
            List<moa> k = this.f3058a.k();
            HashMap hashMap = new HashMap();
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    break;
                }
                if (koq.b(k, i)) {
                    LogUtil.b("sug_RunPlanReportAdapter", "updateCalendarMark planWeekDataList isOutOfBounds");
                    break;
                }
                moa moaVar = k.get(i);
                if (moaVar == null) {
                    LogUtil.b("sug_RunPlanReportAdapter", "updateCalendarMark planWeekDataBean == null");
                } else {
                    List<mnu> j = moaVar.j();
                    if (koq.b(j)) {
                        LogUtil.h("sug_RunPlanReportAdapter", "updateCalendarMark trainingDateWeek is rest");
                    } else {
                        a(hashMap, moaVar, j);
                    }
                }
                i++;
            }
            recyclerViewHolder.e.b(hashMap);
            return;
        }
        LogUtil.b("sug_RunPlanReportAdapter", "updateCalendarMark intPlan == null");
    }

    private void a(Map<String, HealthCalendar> map, moa moaVar, List<mnu> list) {
        for (mnu mnuVar : list) {
            if (mnuVar == null || koq.b(mnuVar.a())) {
                LogUtil.h("sug_RunPlanReportAdapter", "updateCalendarMark planDayDataBean is rest");
            } else {
                Calendar calendar = Calendar.getInstance();
                long o = this.f3058a.o();
                calendar.setTimeInMillis(gib.b(o * 1000, moaVar.f() - 1, mnuVar.c(), gib.e(this.f3058a.l())));
                int i = calendar.get(1);
                int i2 = calendar.get(2) + 1;
                int i3 = calendar.get(5);
                HealthCalendar d = d(i, i2, i3);
                HealthMark healthMark = new HealthMark(HealthMark.MarkType.COLOR);
                healthMark.b(this.b.getResources().getColor(R.color._2131296560_res_0x7f090130));
                d.addMark(healthMark);
                if (mnuVar.e()) {
                    HealthMark healthMark2 = new HealthMark(HealthMark.MarkType.DRAWABLE);
                    healthMark2.cxz_(this.b.getDrawable(R.drawable._2131427734_res_0x7f0b0196));
                    healthMark2.d(nsn.c(this.b, 4.0f));
                    d.addMark(healthMark2);
                    LogUtil.a("sug_RunPlanReportAdapter", "mark checkIn month ", Integer.valueOf(i2), " day ", Integer.valueOf(i3));
                }
                map.put(d.toString(), d);
            }
        }
    }

    private HealthCalendar d(int i, int i2, int i3) {
        HealthCalendar healthCalendar = new HealthCalendar();
        healthCalendar.setYear(i);
        healthCalendar.setMonth(i2);
        healthCalendar.setDay(i3);
        return healthCalendar;
    }

    private void e(RecyclerViewHolder recyclerViewHolder, List<Integer> list) {
        long o = this.f3058a.o() * 1000;
        Date date = new Date();
        date.setTime(o);
        HealthCalendar healthCalendar = new HealthCalendar();
        healthCalendar.setTime(date);
        int d = gib.d(gib.d(o, gib.e(this.f3058a.l())).get(7));
        long size = ((7 - d) * 86400000) + o + ((list.size() - 1) * 7 * 86400000);
        if (size > this.f3058a.a() * 1000) {
            size = this.f3058a.a() * 1000;
        }
        Date date2 = new Date();
        date2.setTime(size);
        HealthCalendar healthCalendar2 = new HealthCalendar();
        healthCalendar2.setTime(date2);
        recyclerViewHolder.e.setRange(healthCalendar, healthCalendar2, false);
        LogUtil.a("sug_RunPlanReportAdapter", "setRangeTime startPlanTime: ", Long.valueOf(o), "planStartDayIndex: ", Integer.valueOf(d), "endTime: ", Long.valueOf(size));
    }
}
