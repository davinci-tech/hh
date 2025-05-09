package com.huawei.health.suggestion.ui.plan.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.plan.adapter.DietDetailsMutipleAdapter;
import com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginoperation.util.DietKakaUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fiu;
import defpackage.fjf;
import defpackage.fyv;
import defpackage.fyw;
import defpackage.grz;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.qts;
import defpackage.quh;
import defpackage.quk;
import defpackage.qul;
import health.compact.a.ReleaseLogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class DietDetailsMutipleAdapter extends BaseExpandableListAdapter {
    private AiPlanWeekDetailViewHolder b;
    private int d;
    private quh e;
    private final Context g;
    private int h;
    private int i;
    private IntPlan k;
    private String m;
    private List<String> f = new ArrayList();
    private List<List<fiu>> j = new ArrayList();
    private List<Integer> o = Arrays.asList(10, 20, 30);
    private List<String> n = Arrays.asList(" 08:00:00", " 12:00:00", " 18:00:00");
    private HashMap<Integer, List<String>> l = new HashMap<>();
    private HashMap<Integer, Boolean> c = new HashMap<>(16);

    /* renamed from: a, reason: collision with root package name */
    private Handler f3243a = new Handler();

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        View f3245a;
        LinearLayout b;
        HealthTextView c;
        ImageView d;
        HealthTextView e;
        HealthTextView g;
        ImageView h;
        HealthTextView i;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.HeterogeneousExpandableList
    public int getChildTypeCount() {
        return 2;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public DietDetailsMutipleAdapter(Context context) {
        this.g = context;
        i();
    }

    private void i() {
        Resources resources = this.g.getResources();
        this.d = resources.getDimensionPixelSize(R.dimen._2131363770_res_0x7f0a07ba);
        this.h = resources.getDimensionPixelSize(R.dimen._2131363591_res_0x7f0a0707);
        this.i = resources.getDimensionPixelSize(R.dimen._2131363780_res_0x7f0a07c4);
    }

    public void a(IntPlan intPlan, List<String> list, List<List<fiu>> list2, quh quhVar, AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder, String str) {
        if ((list instanceof ArrayList) && (list2 instanceof ArrayList)) {
            Object clone = ((ArrayList) list).clone();
            if (clone instanceof ArrayList) {
                this.f = (List) clone;
            }
            Object clone2 = ((ArrayList) list2).clone();
            if (clone2 instanceof ArrayList) {
                this.j = (List) clone2;
            }
        }
        this.b = aiPlanWeekDetailViewHolder;
        this.m = str;
        this.k = intPlan;
        d(quhVar);
    }

    private void d(quh quhVar) {
        this.l.clear();
        this.c.clear();
        this.e = quhVar;
        if (quhVar != null) {
            for (qul qulVar : quhVar.a()) {
                for (quk qukVar : qulVar.c()) {
                    List<String> list = this.l.get(Integer.valueOf(qulVar.h()));
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(qukVar.a());
                    this.l.put(Integer.valueOf(qulVar.h()), list);
                }
            }
            for (int i = 0; i < this.j.size(); i++) {
                List<fiu> list2 = this.j.get(i);
                if (this.l.get(this.o.get(i)) == null || list2.isEmpty()) {
                    this.c.put(Integer.valueOf(i), false);
                } else {
                    int i2 = 0;
                    for (int i3 = 0; i3 < list2.size(); i3++) {
                        if (this.l.get(this.o.get(i)).contains(list2.get(i3).a())) {
                            i2++;
                        }
                    }
                    if (i2 == list2.size()) {
                        this.c.put(Integer.valueOf(i), true);
                    }
                }
            }
        }
        LogUtil.c("DietDetailsAdapter", "recordFoodMap " + this.l.toString());
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public fiu getChild(int i, int i2) {
        if (koq.b(this.j, i) || koq.b(this.j.get(i), i2)) {
            return null;
        }
        return this.j.get(i).get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        List<List<fiu>> list = this.j;
        if (list != null && koq.d(list, i)) {
            return this.j.get(i).size() + 1;
        }
        return 0;
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.HeterogeneousExpandableList
    public int getChildType(int i, int i2) {
        List<List<fiu>> list = this.j;
        return (list == null || list.get(i) == null || i2 != this.j.get(i).size()) ? 1 : 0;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        LogUtil.a("DietDetailsAdapter", "groupPosition", Integer.valueOf(i), " getChildView childPosition ", Integer.valueOf(i2));
        if (i2 == this.j.get(i).size()) {
            if (view == null) {
                view = aGB_();
            }
            Object tag = view.getTag();
            if (!(tag instanceof c)) {
                LogUtil.a("DietDetailsAdapter", "buttonHolder is not ChildButtonViewHolder " + view);
                return view;
            }
            e(i, (c) tag);
            return view;
        }
        View aGC_ = view == null ? aGC_() : view;
        Object tag2 = aGC_.getTag();
        if (tag2 instanceof d) {
            d dVar = (d) tag2;
            if (koq.b(this.j, i) || koq.b(this.j.get(i), i2)) {
                LogUtil.b("groupPosition or childPosition out of range.", new Object[0]);
            } else {
                d(dVar, z);
                fiu child = getChild(i, i2);
                if (child == null) {
                    return aGC_;
                }
                aGF_(dVar, view, child, i, i2);
            }
        }
        return aGC_;
    }

    private void aGF_(d dVar, final View view, fiu fiuVar, final int i, final int i2) {
        nrf.cIS_(dVar.d, fiuVar.i().trim(), nrf.c, 0, R.drawable._2131427609_res_0x7f0b0119);
        dVar.e.setText(fiuVar.b());
        dVar.i.setText(String.valueOf(fiuVar.o()));
        dVar.g.setText(fiuVar.l());
        dVar.c.setText(String.valueOf((int) fiuVar.g()));
        if (fyw.b(this.m)) {
            dVar.b.setVisibility(8);
            return;
        }
        dVar.b.setVisibility(0);
        List<String> list = this.l.get(this.o.get(i));
        LogUtil.c("DietDetailsAdapter", "recordFoods " + list);
        if (list != null && list.contains(fiuVar.a())) {
            dVar.h.setBackgroundResource(R.drawable._2131430245_res_0x7f0b0b65);
            dVar.b.setOnClickListener(new View.OnClickListener() { // from class: fub
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    DietDetailsMutipleAdapter.this.aGJ_(i, i2, view2);
                }
            });
        } else {
            dVar.h.setBackgroundResource(R.drawable._2131430251_res_0x7f0b0b6b);
            dVar.b.setOnClickListener(new View.OnClickListener() { // from class: ftz
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    DietDetailsMutipleAdapter.this.aGK_(i, i2, view, view2);
                }
            });
        }
    }

    public /* synthetic */ void aGJ_(int i, int i2, View view) {
        b(i, i2);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aGK_(int i, int i2, View view, View view2) {
        aGE_(i, i2, view);
        ViewClickInstrumentation.clickOnView(view2);
    }

    private void e(final int i, c cVar) {
        cVar.f3244a.setOnClickListener(new View.OnClickListener() { // from class: fts
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DietDetailsMutipleAdapter.this.aGG_(i, view);
            }
        });
        cVar.e.setOnClickListener(new View.OnClickListener() { // from class: fty
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DietDetailsMutipleAdapter.this.aGH_(view);
            }
        });
        cVar.c.setOnClickListener(new View.OnClickListener() { // from class: ftx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DietDetailsMutipleAdapter.this.aGI_(i, view);
            }
        });
        if (fyw.b(this.m)) {
            cVar.f3244a.setVisibility(8);
            cVar.c.setVisibility(0);
            cVar.e.setVisibility(8);
            return;
        }
        cVar.f3244a.setVisibility(0);
        cVar.c.setVisibility(0);
        cVar.e.setVisibility(0);
        if (f()) {
            cVar.f3244a.setText(BaseApplication.getContext().getString(R.string._2130848657_res_0x7f022b91).toUpperCase());
        } else {
            cVar.f3244a.setText(BaseApplication.getContext().getString(R.string._2130848648_res_0x7f022b88).toUpperCase());
        }
    }

    public /* synthetic */ void aGG_(int i, View view) {
        c(i);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aGH_(View view) {
        JumpUtil.d(this.m, new String[0]);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aGI_(int i, View view) {
        a(i);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(int i) {
        if (nsn.o()) {
            LogUtil.h("DietDetailsAdapter", "FastClick on replacing food");
        } else {
            b(i);
        }
    }

    private View aGC_() {
        d dVar = new d();
        View inflate = LayoutInflater.from(this.g).inflate(R.layout.layout_diet_detail_child_item, (ViewGroup) null);
        dVar.f3245a = inflate.findViewById(R.id.hw_show_diet_detail_child_bg);
        dVar.d = (ImageView) inflate.findViewById(R.id.hw_show_diet_detail_child_food_img);
        dVar.e = (HealthTextView) inflate.findViewById(R.id.hw_show_diet_detail_child_food_name);
        dVar.i = (HealthTextView) inflate.findViewById(R.id.hw_show_diet_detail_child_weight);
        dVar.g = (HealthTextView) inflate.findViewById(R.id.hw_show_diet_detail_child_unit);
        dVar.c = (HealthTextView) inflate.findViewById(R.id.hw_show_diet_detail_heat);
        dVar.h = (ImageView) inflate.findViewById(R.id.hw_show_diet_child_right_image);
        dVar.b = (LinearLayout) inflate.findViewById(R.id.replace_click_layout);
        inflate.setTag(dVar);
        return inflate;
    }

    private View aGB_() {
        LogUtil.a("DietDetailsAdapter", "getChildButtonView");
        c cVar = new c();
        View inflate = LayoutInflater.from(this.g).inflate(R.layout.layout_diet_detail_child_button, (ViewGroup) null);
        cVar.f3244a = (HealthButton) inflate.findViewById(R.id.do_all_record_button);
        cVar.c = (HealthButton) inflate.findViewById(R.id.do_change_button);
        cVar.e = (HealthButton) inflate.findViewById(R.id.record_other_diet_btn);
        cVar.d = inflate.findViewById(R.id.hw_show_diet_detail_button_bar);
        cVar.d.setBackground(this.g.getResources().getDrawable(R.drawable._2131427636_res_0x7f0b0134));
        inflate.setTag(cVar);
        return inflate;
    }

    private void b(final int i) {
        LogUtil.a("DietDetailsAdapter", "click replaceAllFood: ", Integer.valueOf(i));
        ArrayList arrayList = new ArrayList();
        if (koq.d(this.o, i) && this.l.containsKey(this.o.get(i)) && koq.d(this.j, i)) {
            List<String> list = this.l.get(this.o.get(i));
            for (fiu fiuVar : this.j.get(i)) {
                if (list.contains(fiuVar.a())) {
                    arrayList.add(fiuVar.a());
                }
            }
        }
        new fjf().getReplaceList(this.m, i + 1, arrayList, new UiCallback<List<fiu>>() { // from class: com.huawei.health.suggestion.ui.plan.adapter.DietDetailsMutipleAdapter.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                LogUtil.a("DietDetailsAdapter", "getReplaceList errorCode: ", Integer.valueOf(i2), ", info: ", str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<fiu> list2) {
                LogUtil.a("DietDetailsAdapter", "getReplaceList onSuccess.");
                if (!koq.b(list2)) {
                    DietDetailsMutipleAdapter.this.b.c(list2, i, DietDetailsMutipleAdapter.this.m);
                } else {
                    LogUtil.h("DietDetailsAdapter", "getReplaceList data is empty");
                }
            }
        });
    }

    private void d(d dVar, boolean z) {
        ViewGroup.LayoutParams layoutParams = dVar.f3245a.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            if (z) {
                dVar.f3245a.setBackground(this.g.getResources().getDrawable(R.drawable._2131427636_res_0x7f0b0134));
                layoutParams2.height = this.h + this.d;
            } else {
                dVar.f3245a.setBackground(this.g.getResources().getDrawable(R.color._2131296666_res_0x7f09019a));
                layoutParams2.height = this.h;
            }
            dVar.f3245a.setLayoutParams(layoutParams2);
        }
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String getGroup(int i) {
        if (koq.d(this.f, i)) {
            return this.f.get(i);
        }
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        List<String> list = this.f;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = aGD_();
        }
        Object tag = view.getTag();
        if (tag instanceof e) {
            e eVar = (e) tag;
            b(eVar, i, z);
            String group = getGroup(i);
            if (group == null) {
                return view;
            }
            eVar.b.setText(group);
        }
        return view;
    }

    private View aGD_() {
        e eVar = new e();
        View inflate = LayoutInflater.from(this.g).inflate(R.layout.layout_diet_detail_father_item, (ViewGroup) null);
        eVar.f3246a = inflate.findViewById(R.id.hw_show_diet_detail_father_bg);
        eVar.b = (HealthTextView) inflate.findViewById(R.id.hw_show_diet_detail_father_heat);
        eVar.d = (ImageView) inflate.findViewById(R.id.diet_record_finished);
        eVar.e = inflate.findViewById(R.id.hw_show_diet_detail_father_line);
        eVar.c = (HealthTextView) inflate.findViewById(R.id.has_recorded);
        inflate.setTag(eVar);
        return inflate;
    }

    private void c(int i) {
        Date date;
        List<List<fiu>> list = this.j;
        if (list == null || i >= list.size()) {
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.g, AnalyticsValue.INT_PLAN_2040151.value(), hashMap, 0);
        List<fiu> list2 = this.j.get(i);
        Iterator<fiu> it = list2.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 = (int) (i2 + it.next().g());
        }
        List<quk> b = fyv.b(list2);
        try {
            date = DateFormatUtil.d(this.m + this.n.get(i), DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT);
        } catch (ParseException e2) {
            ReleaseLogUtil.c("DietDetailsAdapter", "addAllRecord exception ", ExceptionUtils.d(e2));
            date = null;
        }
        if (date == null) {
            date = new Date();
        }
        qul qulVar = new qul(this.o.get(i).intValue(), date.getTime(), i2, b);
        qulVar.c(jdl.q(System.currentTimeMillis()));
        e(qulVar, date);
    }

    private void e(final qul qulVar, Date date) {
        int b = DateFormatUtil.b(date.getTime());
        ReleaseLogUtil.b("DietDetailsAdapter", "getDietRecordLocal dayFormat ", Integer.valueOf(b));
        final ResponseCallback responseCallback = new ResponseCallback() { // from class: ftt
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                DietDetailsMutipleAdapter.this.b(i, (quh) obj);
            }
        };
        grz.e(b, b, (ResponseCallback<List<quh>>) new ResponseCallback() { // from class: ftu
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                DietDetailsMutipleAdapter.this.d(qulVar, responseCallback, i, (List) obj);
            }
        });
    }

    public /* synthetic */ void b(int i, quh quhVar) {
        LogUtil.a("DietDetailsAdapter", "getDietRecordLocal errorCode ", Integer.valueOf(i), " dietRecord ", quhVar);
        if (quhVar == null) {
            return;
        }
        this.f3243a.post(new Runnable() { // from class: ftw
            @Override // java.lang.Runnable
            public final void run() {
                DietDetailsMutipleAdapter.this.a();
            }
        });
    }

    public /* synthetic */ void a() {
        this.b.c(true, true);
    }

    public /* synthetic */ void d(qul qulVar, ResponseCallback responseCallback, int i, List list) {
        LogUtil.a("DietDetailsAdapter", "getDietRecordLocal errorCode ", Integer.valueOf(i), " list ", list);
        b((List<quh>) list, qulVar, (ResponseCallback<quh>) responseCallback);
    }

    private void b(List<quh> list, qul qulVar, ResponseCallback<quh> responseCallback) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("DietDetailsAdapter", "processDietRecordData list ", list);
            grz.a(qulVar, responseCallback);
            return;
        }
        qul qulVar2 = null;
        for (qul qulVar3 : list.get(0).a()) {
            if (qulVar3.h() == qulVar.h()) {
                qulVar2 = qulVar3;
            }
        }
        qulVar.c(this.e.j());
        if (qulVar2 == null) {
            grz.a(qulVar, responseCallback);
            DietKakaUtil.completeMeal(this.g, this.e, qulVar);
        } else {
            c(qulVar2, qulVar);
            grz.b(qulVar, responseCallback);
        }
    }

    private void aGE_(int i, int i2, View view) {
        List<List<fiu>> list = this.j;
        if (list == null || koq.b(list, i) || koq.b(this.j.get(i), i2)) {
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.g, AnalyticsValue.INT_PLAN_2040152.value(), hashMap, 0);
        fiu fiuVar = this.j.get(i).get(i2);
        fiuVar.c(i + 1);
        qts d2 = this.e.d();
        String valueOf = String.valueOf(1000);
        if (d2 != null) {
            valueOf = String.valueOf(Math.min(Math.max(1000, Math.round(d2.c())), 10000));
        }
        fyv.e(this.g, fiuVar, this.m, this.k, valueOf);
        this.f3243a.postDelayed(new Runnable() { // from class: fto
            @Override // java.lang.Runnable
            public final void run() {
                ary.a().e("PLAN_UPDATE");
            }
        }, 1000L);
    }

    private boolean f() {
        return new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(System.currentTimeMillis())).compareToIgnoreCase(this.m) > 0;
    }

    public boolean b(int i, int i2) {
        boolean z = false;
        if (koq.b(this.j, i) || koq.b(this.j.get(i), i2)) {
            return false;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.g, AnalyticsValue.INT_PLAN_2040154.value(), hashMap, 0);
        if (fyw.b(this.m)) {
            return true;
        }
        quh quhVar = this.e;
        if (quhVar != null && !quhVar.a().isEmpty()) {
            fiu child = getChild(i, i2);
            if (child != null) {
                List<String> list = this.l.get(this.o.get(i));
                if (list != null && list.contains(child.a())) {
                    z = true;
                }
            } else {
                LogUtil.b("foodData is null.", new Object[0]);
            }
        }
        fiu fiuVar = this.j.get(i).get(i2);
        fiuVar.c(i + 1);
        fyv.e(BaseApplication.getContext(), fiuVar, z, this.m, this.k);
        this.f3243a.postDelayed(new Runnable() { // from class: ftv
            @Override // java.lang.Runnable
            public final void run() {
                ary.a().e("PLAN_UPDATE");
            }
        }, 1000L);
        return true;
    }

    private void b(e eVar, int i, boolean z) {
        ViewGroup.LayoutParams layoutParams = eVar.e.getLayoutParams();
        if (i == 0) {
            layoutParams.height = 0;
        } else {
            layoutParams.height = this.g.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        }
        eVar.e.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = eVar.f3246a.getLayoutParams();
        if (layoutParams2 instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) layoutParams2;
            Boolean bool = this.c.get(Integer.valueOf(i));
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            int intValue = (((this.g.getResources().getDisplayMetrics().widthPixels - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue()) - (((int) this.g.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e)) * 2)) - (((int) this.g.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d)) * 2);
            float f = intValue;
            eVar.b.setWidth((int) (0.65f * f));
            if (bool != null && bool.booleanValue()) {
                eVar.c.setWidth((int) (f * 0.35f));
                eVar.c.setVisibility(0);
                eVar.d.setVisibility(0);
            } else {
                eVar.c.setVisibility(8);
                eVar.d.setVisibility(8);
            }
            if (fyw.b(this.m)) {
                eVar.b.setWidth(intValue);
                eVar.c.setVisibility(8);
                eVar.d.setVisibility(8);
            }
            if (!z) {
                eVar.d.setImageResource(R.drawable._2131430240_res_0x7f0b0b60);
                int i2 = this.i;
                int i3 = this.d;
                layoutParams3.height = i2 + i3 + i3;
                eVar.f3246a.setBackground(this.g.getResources().getDrawable(R.drawable._2131427635_res_0x7f0b0133));
            } else {
                eVar.d.setImageResource(R.drawable.ic_health_list_drop_down_arrow_sel);
                eVar.f3246a.setBackground(this.g.getResources().getDrawable(R.drawable._2131427637_res_0x7f0b0135));
                layoutParams3.height = this.i + this.d;
            }
            eVar.f3246a.setLayoutParams(layoutParams3);
        }
    }

    private void c(qul qulVar, qul qulVar2) {
        int i = 0;
        if (qulVar2.c() != null && qulVar.c() != null) {
            Iterator<quk> it = qulVar.c().iterator();
            while (it.hasNext()) {
                quk next = it.next();
                Iterator<quk> it2 = qulVar2.c().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (it2.next().a().equals(next.a())) {
                            it.remove();
                            break;
                        }
                    } else {
                        i = (int) (i + next.g());
                        break;
                    }
                }
            }
        }
        qulVar2.c(i + qulVar2.b());
        qulVar2.e(qulVar.d());
        qulVar2.c().addAll(qulVar.c());
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        View f3246a;
        HealthTextView b;
        HealthTextView c;
        ImageView d;
        View e;

        private e() {
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        HealthButton f3244a;
        HealthButton c;
        View d;
        HealthButton e;

        private c() {
        }
    }
}
