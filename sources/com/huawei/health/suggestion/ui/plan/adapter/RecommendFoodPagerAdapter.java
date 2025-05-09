package com.huawei.health.suggestion.ui.plan.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.plan.adapter.RecommendFoodPagerAdapter;
import com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.pluginoperation.util.DietKakaUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.fiu;
import defpackage.fjf;
import defpackage.fyv;
import defpackage.gge;
import defpackage.ggl;
import defpackage.ggr;
import defpackage.gib;
import defpackage.grz;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrt;
import defpackage.nsf;
import defpackage.quh;
import defpackage.quk;
import defpackage.qul;
import health.compact.a.HiDateUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class RecommendFoodPagerAdapter extends PagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final Context f3253a;
    private int b;
    private String c;
    private final SparseArray<List<fiu>> d;
    private int e;
    private String f;
    private final boolean g;
    private AiPlanWeekDetailViewHolder h;
    private boolean i;
    private IntPlan j;
    private long k;
    private quh n;
    private final SparseIntArray o;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public RecommendFoodPagerAdapter() {
        Context e = BaseApplication.e();
        this.f3253a = e;
        this.d = new SparseArray<>();
        this.o = new SparseIntArray();
        this.g = nrt.a(e);
    }

    public void d(List<List<fiu>> list, quh quhVar, IntPlan intPlan, AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder, long j) {
        if (koq.b(list)) {
            LogUtil.a("RecommendFoodPagerAdapter", "setData list ", list);
            return;
        }
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(Integer.valueOf(R.string._2130848791_res_0x7f022c17));
        arrayList.add(Integer.valueOf(R.string._2130848792_res_0x7f022c18));
        arrayList.add(Integer.valueOf(R.string._2130848793_res_0x7f022c19));
        Collections.shuffle(arrayList);
        int size = arrayList.size();
        this.o.clear();
        for (int i = 0; i < size; i++) {
            this.o.append(fyv.d(i), ((Integer) arrayList.get(i)).intValue());
        }
        this.d.clear();
        int size2 = list.size();
        for (int i2 = 0; i2 < size2; i2++) {
            this.d.append(fyv.d(i2), list.get(i2));
        }
        this.n = quhVar;
        this.j = intPlan;
        this.h = aiPlanWeekDetailViewHolder;
        this.k = j;
        this.b = HiDateUtil.c(j);
        this.c = gib.j(this.k);
        this.f = gib.j(HiDateUtil.g(this.k));
        this.e = HiDateUtil.c(System.currentTimeMillis());
        this.i = fyv.e(this.n, this.k);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.d.size() * 900;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        int size = this.d.size();
        if (size <= 0) {
            LogUtil.a("RecommendFoodPagerAdapter", "instantiateItem size ", Integer.valueOf(size), " index ", Integer.valueOf(i), " viewGroup ", viewGroup, " mArray ", this.d);
            return viewGroup;
        }
        View inflate = View.inflate(this.f3253a, R.layout.item_pager_recipe_recommend, null);
        int keyAt = this.d.keyAt(i % size);
        aHf_(inflate, keyAt);
        List<fiu> list = this.d.get(keyAt);
        aHb_(inflate, list);
        boolean b = b(list, keyAt);
        aHe_(inflate, b, keyAt);
        aHc_(inflate, b, list, keyAt);
        aHd_(inflate, keyAt);
        viewGroup.addView(inflate);
        return inflate;
    }

    private void aHf_(View view, int i) {
        int i2;
        if (i == 10) {
            i2 = R.string._2130848778_res_0x7f022c0a;
        } else if (i == 20) {
            i2 = R.string._2130848779_res_0x7f022c0b;
        } else if (i == 30) {
            i2 = R.string._2130848780_res_0x7f022c0c;
        } else if (i == 40) {
            i2 = R.string._2130848781_res_0x7f022c0d;
        } else if (i == 50) {
            i2 = R.string._2130848782_res_0x7f022c0e;
        } else if (i != 60) {
            LogUtil.a("RecommendFoodPagerAdapter", "setTitle default mealType ", Integer.valueOf(i));
            i2 = 0;
        } else {
            i2 = R.string._2130848783_res_0x7f022c0f;
        }
        String h = nsf.h(i2);
        if (TextUtils.isEmpty(h)) {
            ReleaseLogUtil.a("RecommendFoodPagerAdapter", "setTitle titleString is empty resourceId ", Integer.valueOf(i2));
        } else {
            ((HealthTextView) view.findViewById(R.id.title)).setText(h);
        }
    }

    private int c(List<fiu> list) {
        int i = 0;
        if (koq.b(list)) {
            LogUtil.a("RecommendFoodPagerAdapter", "getHeat list ", list);
            return 0;
        }
        for (fiu fiuVar : list) {
            if (fiuVar != null) {
                i = (int) (i + fiuVar.g());
            }
        }
        return i;
    }

    private void aHb_(View view, List<fiu> list) {
        int c = c(list);
        ((HealthTextView) view.findViewById(R.id.heat)).setText(nsf.a(R.plurals._2130903474_res_0x7f0301b2, c, UnitUtil.e(c, 1, 0)));
    }

    private boolean b(List<fiu> list, int i) {
        if (koq.b(list) || !this.i || fyv.h(i)) {
            LogUtil.a("RecommendFoodPagerAdapter", "isAllRecord list ", list, " mealType ", Integer.valueOf(i), " mIsSameDay ", Boolean.valueOf(this.i));
            return false;
        }
        List<String> e = fyv.e(this.n, i);
        for (fiu fiuVar : list) {
            if (fiuVar != null && !e.contains(fiuVar.a())) {
                return false;
            }
        }
        return true;
    }

    private void aHe_(View view, boolean z, final int i) {
        ImageView imageView = (ImageView) view.findViewById(R.id.do_change_button);
        if (z) {
            imageView.setVisibility(8);
            return;
        }
        imageView.setVisibility(0);
        Drawable drawable = ContextCompat.getDrawable(this.f3253a, R.drawable._2131430251_res_0x7f0b0b6b);
        if (drawable != null) {
            if (this.g) {
                drawable = nrf.cJH_(drawable, ContextCompat.getColor(this.f3253a, R.color._2131296934_res_0x7f0902a6));
            }
            imageView.setImageDrawable(drawable);
        }
        final String str = fyv.h(i) ? this.f : this.c;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: fuf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RecommendFoodPagerAdapter.this.aHh_(str, i, view2);
            }
        });
    }

    public /* synthetic */ void aHh_(final String str, final int i, View view) {
        new fjf().getReplaceList(str, fyv.c(i), fyv.e(this.n, i), new UiCallback<List<fiu>>() { // from class: com.huawei.health.suggestion.ui.plan.adapter.RecommendFoodPagerAdapter.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str2) {
                LogUtil.a("RecommendFoodPagerAdapter", "setRefresh onFailure errorCode ", Integer.valueOf(i2), " errorInfo ", str2);
                if (i2 == 15110) {
                    nrh.b(RecommendFoodPagerAdapter.this.f3253a, R.string._2130848845_res_0x7f022c4d);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<fiu> list) {
                if (koq.b(list)) {
                    LogUtil.a("RecommendFoodPagerAdapter", "setRefresh data ", list);
                    return;
                }
                ggr.c();
                if (RecommendFoodPagerAdapter.this.h != null) {
                    RecommendFoodPagerAdapter.this.h.c(list, fyv.b(i), str);
                } else {
                    LogUtil.a("RecommendFoodPagerAdapter", "setRefresh onSuccess mHolder is null");
                }
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    private void aHc_(View view, boolean z, final List<fiu> list, final int i) {
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.do_all_record_button);
        if (z || fyv.h(i) || this.b > this.e) {
            healthButton.setVisibility(8);
            return;
        }
        healthButton.setVisibility(0);
        if (this.b < this.e) {
            healthButton.setText(R.string._2130848657_res_0x7f022b91);
        }
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: fuj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RecommendFoodPagerAdapter.this.aHg_(i, list, view2);
            }
        });
    }

    public /* synthetic */ void aHg_(int i, List list, View view) {
        long time = ggl.e(this.c + fyv.e(i), "yyyy-MM-dd HH:mm:ss").getTime();
        final qul qulVar = new qul(fyv.a(i), time, c(list), fyv.b((List<fiu>) list));
        qulVar.c(HiDateUtil.d(""));
        int b = DateFormatUtil.b(time);
        ReleaseLogUtil.b("RecommendFoodPagerAdapter", "setRecord dayFormat ", Integer.valueOf(b), " meal ", qulVar);
        grz.e(b, b, (ResponseCallback<List<quh>>) new ResponseCallback() { // from class: fui
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                RecommendFoodPagerAdapter.this.b(qulVar, i2, (List) obj);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void b(qul qulVar, int i, List list) {
        LogUtil.c("RecommendFoodPagerAdapter", "setRecord errorCode ", Integer.valueOf(i), " dietRecordList ", list);
        a(list, qulVar, new ResponseCallback() { // from class: fuk
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                RecommendFoodPagerAdapter.this.b(i2, (quh) obj);
            }
        });
    }

    public /* synthetic */ void b(int i, quh quhVar) {
        LogUtil.c("RecommendFoodPagerAdapter", "setRecord resultCode ", Integer.valueOf(i), " dietRecord ", quhVar);
        if (quhVar == null) {
            return;
        }
        gge.e(AnalyticsValue.INT_PLAN_2040151.value());
        HandlerExecutor.a(new Runnable() { // from class: fuh
            @Override // java.lang.Runnable
            public final void run() {
                RecommendFoodPagerAdapter.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder = this.h;
        if (aiPlanWeekDetailViewHolder == null) {
            LogUtil.a("RecommendFoodPagerAdapter", "setRecord onSuccess mHolder is null");
        } else {
            aiPlanWeekDetailViewHolder.c(true, true);
        }
    }

    private void a(List<quh> list, qul qulVar, ResponseCallback<quh> responseCallback) {
        boolean z = this.b < this.e;
        if (koq.b(list)) {
            grz.a(qulVar, responseCallback);
            ggr.b(qulVar.h(), z ? 2 : 1);
            return;
        }
        qulVar.c(this.i ? this.n.j() : HiDateUtil.d(""));
        quh quhVar = list.get(0);
        qul qulVar2 = null;
        if (quhVar != null) {
            for (qul qulVar3 : quhVar.a()) {
                if (qulVar3 != null && qulVar3.h() == qulVar.h()) {
                    qulVar2 = qulVar3;
                }
            }
        }
        if (qulVar2 == null) {
            grz.a(qulVar, responseCallback);
            DietKakaUtil.completeMeal(this.f3253a, this.n, qulVar);
            ggr.b(qulVar.h(), z ? 2 : 1);
        } else {
            qulVar.c(c(qulVar2, qulVar) + qulVar.b());
            qulVar.e(qulVar2.d());
            qulVar.c().addAll(qulVar2.c());
            grz.b(qulVar, responseCallback);
            ggr.b(qulVar.h(), z ? 2 : 3);
        }
    }

    private int c(qul qulVar, qul qulVar2) {
        List<quk> c = qulVar2.c();
        List<quk> c2 = qulVar.c();
        int i = 0;
        if (!koq.b(c) && !koq.b(c2)) {
            Iterator<quk> it = c2.iterator();
            while (it.hasNext()) {
                quk next = it.next();
                if (next != null) {
                    Iterator<quk> it2 = c.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            quk next2 = it2.next();
                            if (next2 != null && next2.a().equals(next.a())) {
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
        }
        return i;
    }

    private void aHd_(View view, int i) {
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.recycle_view);
        healthRecycleView.setIsScroll(false);
        healthRecycleView.setIsConsumption(true);
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this.f3253a));
        RecommendFoodAdapter recommendFoodAdapter = new RecommendFoodAdapter(this.d, this.n, this.j, i, this.k);
        recommendFoodAdapter.aHa_(this.o);
        healthRecycleView.setAdapter(recommendFoodAdapter);
    }
}
