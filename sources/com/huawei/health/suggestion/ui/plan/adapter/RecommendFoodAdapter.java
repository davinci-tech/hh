package com.huawei.health.suggestion.ui.plan.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.plan.adapter.RecommendFoodAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fiu;
import defpackage.fjb;
import defpackage.fyv;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qts;
import defpackage.quh;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class RecommendFoodAdapter extends BaseRecyclerAdapter<fiu> {

    /* renamed from: a, reason: collision with root package name */
    private IntPlan f3252a;
    private int b;
    private final Context c;
    private String d;
    private final boolean e;
    private int f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private List<fiu> k;
    private int l;
    private quh m;
    private final Resources n;
    private String o;
    private SparseIntArray q;
    private int r;
    private int t;

    public RecommendFoodAdapter(SparseArray<List<fiu>> sparseArray, quh quhVar, IntPlan intPlan, int i, long j) {
        super(new ArrayList(), R.layout.item_recycler_recommend_food);
        Context e = BaseApplication.e();
        this.c = e;
        Resources resources = e.getResources();
        this.n = resources;
        this.e = LanguageUtil.h(e);
        this.k = new ArrayList(3);
        this.q = new SparseIntArray();
        if (sparseArray == null) {
            ReleaseLogUtil.a("R_RecommendFoodAdapter", "RecommendFoodAdapter sparseArray is null");
            return;
        }
        this.r = i;
        List<fiu> list = sparseArray.get(i);
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_RecommendFoodAdapter", "RecommendFoodAdapter list ", list);
            return;
        }
        int size = list.size();
        this.t = size;
        this.f = size - 1;
        boolean z = size > 3;
        this.g = z;
        int i2 = R.dimen._2131363100_res_0x7f0a051c;
        this.b = resources.getDimensionPixelSize(z ? R.dimen._2131363074_res_0x7f0a0502 : R.dimen._2131363100_res_0x7f0a051c);
        int aGU_ = aGU_(sparseArray);
        int abs = Math.abs((resources.getDimensionPixelSize(aGU_ > 3 ? R.dimen._2131363074_res_0x7f0a0502 : i2) * aGU_) - (this.b * this.t));
        this.l = abs;
        if (abs <= resources.getDimensionPixelSize(R.dimen._2131362945_res_0x7f0a0481)) {
            ReleaseLogUtil.a("R_RecommendFoodAdapter", "RecommendFoodAdapter mOtherHeight ", Integer.valueOf(this.l), " mHeight ", Integer.valueOf(this.b), " mSize ", Integer.valueOf(this.t), " maxCount ", Integer.valueOf(aGU_));
            this.l = 0;
        }
        ArrayList arrayList = new ArrayList();
        for (fiu fiuVar : list) {
            if (fiuVar != null) {
                arrayList.add(fiuVar);
            }
        }
        if (arrayList.size() < aGU_) {
            arrayList.add(new fiu());
        }
        this.i = nsn.a(3.1f);
        this.k = arrayList;
        this.m = quhVar;
        this.f3252a = intPlan;
        this.d = DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD);
        boolean h = fyv.h(this.r);
        this.h = h;
        if (h) {
            this.o = DateFormatUtil.a(jdl.y(j), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD);
        }
        this.j = fyv.e(this.m, j);
        refreshDataChange(this.k);
    }

    public void aHa_(SparseIntArray sparseIntArray) {
        if (sparseIntArray != null) {
            this.q = sparseIntArray;
        }
    }

    private int aGU_(SparseArray<List<fiu>> sparseArray) {
        if (sparseArray == null) {
            ReleaseLogUtil.a("R_RecommendFoodAdapter", "getMaxFoodCount sparseArray is null");
            return 0;
        }
        int size = sparseArray.size();
        if (size <= 0) {
            ReleaseLogUtil.a("R_RecommendFoodAdapter", "getMaxFoodCount sparseArray ", sparseArray);
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            List<fiu> list = sparseArray.get(sparseArray.keyAt(i2));
            if (!koq.b(list)) {
                i = Math.max(i, list.size());
            }
        }
        return i;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.k)) {
            return 0;
        }
        return this.k.size();
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, fiu fiuVar) {
        if (recyclerHolder == null || fiuVar == null) {
            ReleaseLogUtil.a("R_RecommendFoodAdapter", "convert holder ", recyclerHolder, " itemData ", fiuVar, " position ", Integer.valueOf(i), " mList ", this.k);
            return;
        }
        boolean z = i >= this.t;
        ViewGroup.LayoutParams layoutParams = recyclerHolder.itemView.getLayoutParams();
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
            layoutParams2.height = z ? this.l : this.b;
            recyclerHolder.itemView.setLayoutParams(layoutParams2);
        }
        String a2 = fiuVar.a();
        boolean z2 = !this.h && this.j && fyv.e(this.m, this.r).contains(a2);
        a(recyclerHolder, fiuVar, z2);
        b(recyclerHolder, fiuVar, z2);
        c(recyclerHolder, fiuVar, z2);
        recyclerHolder.a(R.id.divider, (TextUtils.isEmpty(a2) || i == this.f || i == getItemCount() - 1) ? 8 : 0);
        d(recyclerHolder, z);
    }

    private void a(RecyclerHolder recyclerHolder, final fiu fiuVar, final boolean z) {
        ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.food_image);
        String i = fiuVar.i();
        if (TextUtils.isEmpty(i)) {
            imageView.setVisibility(8);
            return;
        }
        String trim = i.trim();
        if (TextUtils.isEmpty(trim)) {
            ReleaseLogUtil.a("R_RecommendFoodAdapter", "initFoodImage url ", trim);
            imageView.setVisibility(8);
            return;
        }
        imageView.setVisibility(0);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            int dimensionPixelSize = this.n.getDimensionPixelSize(this.g ? R.dimen._2131363029_res_0x7f0a04d5 : R.dimen._2131363060_res_0x7f0a04f4);
            layoutParams2.width = dimensionPixelSize;
            layoutParams2.height = dimensionPixelSize;
            imageView.setLayoutParams(layoutParams2);
        }
        nrf.cIS_(imageView, trim, nrf.c, 0, R.drawable._2131427609_res_0x7f0b0119);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: fuc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecommendFoodAdapter.this.aGY_(fiuVar, z, view);
            }
        });
    }

    public /* synthetic */ void aGY_(fiu fiuVar, boolean z, View view) {
        a(fiuVar, z);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(RecyclerHolder recyclerHolder, final fiu fiuVar, final boolean z) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.food_name);
        HealthTextView healthTextView2 = (HealthTextView) recyclerHolder.cwK_(R.id.food_content);
        HealthTextView healthTextView3 = (HealthTextView) recyclerHolder.cwK_(R.id.food_heat);
        HealthTextView healthTextView4 = (HealthTextView) recyclerHolder.cwK_(R.id.food_heat_large_mode);
        int dimensionPixelSize = this.n.getDimensionPixelSize(R.dimen._2131362906_res_0x7f0a045a);
        if (this.g) {
            int dimensionPixelSize2 = this.n.getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446);
            healthTextView.setTextSize(0, dimensionPixelSize);
            float f = dimensionPixelSize2;
            healthTextView2.setTextSize(0, f);
            healthTextView3.setTextSize(0, f);
            healthTextView4.setTextSize(0, f);
        } else {
            healthTextView.setTextSize(0, this.n.getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a));
            float f2 = dimensionPixelSize;
            healthTextView2.setTextSize(0, f2);
            healthTextView3.setTextSize(0, f2);
            healthTextView4.setTextSize(0, f2);
        }
        String b = fiuVar.b();
        healthTextView.setText(b);
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: fud
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecommendFoodAdapter.this.aGW_(fiuVar, z, view);
            }
        });
        healthTextView.setVisibility(TextUtils.isEmpty(b) ? 8 : 0);
        int g = (int) fiuVar.g();
        String a2 = nsf.a(R.plurals._2130903474_res_0x7f0301b2, g, UnitUtil.e(g, 1, 0));
        int i = g <= 0 ? 8 : 0;
        if (!this.g && this.i) {
            healthTextView4.setVisibility(i);
            healthTextView4.setText(a2);
            healthTextView3.setVisibility(8);
            healthTextView3.setText("");
        } else {
            healthTextView3.setVisibility(i);
            healthTextView3.setText(a2);
            healthTextView4.setVisibility(8);
            healthTextView4.setText("");
        }
        b(healthTextView2, fiuVar, z);
    }

    public /* synthetic */ void aGW_(fiu fiuVar, boolean z, View view) {
        a(fiuVar, z);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(HealthTextView healthTextView, final fiu fiuVar, final boolean z) {
        int o = fiuVar.o();
        if (o <= 0) {
            healthTextView.setVisibility(8);
            return;
        }
        healthTextView.setVisibility(0);
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: fua
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecommendFoodAdapter.this.aGX_(fiuVar, z, view);
            }
        });
        fjb c = fyv.c(fiuVar);
        double d = fyv.d(c, o);
        String e = UnitUtil.e(o, 1, 0);
        String l = fiuVar.l();
        if (Double.compare(d, 0.0d) != 1) {
            healthTextView.setText(nsf.b(R.string._2130848836_res_0x7f022c44, e, l));
            return;
        }
        Object[] objArr = new Object[4];
        objArr[0] = UnitUtil.e(d, 1, 1);
        objArr[1] = c == null ? "" : c.e();
        objArr[2] = e;
        objArr[3] = l;
        healthTextView.setText(nsf.b(R.string._2130848837_res_0x7f022c45, objArr));
    }

    public /* synthetic */ void aGX_(fiu fiuVar, boolean z, View view) {
        a(fiuVar, z);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(RecyclerHolder recyclerHolder, final fiu fiuVar, final boolean z) {
        Drawable drawable;
        ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.food_refresh);
        imageView.setVisibility(TextUtils.isEmpty(fiuVar.a()) ? 8 : 0);
        if (z) {
            drawable = ContextCompat.getDrawable(this.c, R.drawable._2131430245_res_0x7f0b0b65);
        } else {
            drawable = ContextCompat.getDrawable(this.c, R.drawable._2131430251_res_0x7f0b0b6b);
        }
        imageView.setImageDrawable(drawable);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: fue
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecommendFoodAdapter.this.aGZ_(z, fiuVar, view);
            }
        });
    }

    public /* synthetic */ void aGZ_(boolean z, fiu fiuVar, View view) {
        if (z) {
            a(fiuVar, true);
        } else {
            d(fiuVar);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        HandlerExecutor.d(new Runnable() { // from class: fug
            @Override // java.lang.Runnable
            public final void run() {
                ary.a().e("PLAN_UPDATE");
            }
        }, 1000L);
    }

    private void a(fiu fiuVar, boolean z) {
        fiuVar.c(fyv.c(this.r));
        fyv.e(this.c, fiuVar, z, this.h ? this.o : this.d, this.f3252a);
        b();
    }

    private void d(fiu fiuVar) {
        qts d;
        quh quhVar = this.m;
        int i = 1000;
        if (quhVar != null && (d = quhVar.d()) != null) {
            i = Math.min(Math.max(1000, Math.round(d.c())), 10000);
        }
        fiuVar.c(fyv.c(this.r));
        fyv.e(this.c, fiuVar, this.h ? this.o : this.d, this.f3252a, String.valueOf(i));
        b();
    }

    private void d(RecyclerHolder recyclerHolder, boolean z) {
        LinearLayout linearLayout = (LinearLayout) recyclerHolder.cwK_(R.id.food_tip);
        if (!z || this.l <= 0) {
            linearLayout.setVisibility(8);
            return;
        }
        CharSequence j = nsf.j(this.q.get(fyv.a(this.r)));
        ReleaseLogUtil.b("R_RecommendFoodAdapter", "setFoodTip mSize ", Integer.valueOf(this.t), " mHeight ", Integer.valueOf(this.b), " mOtherHeight ", Integer.valueOf(this.l), " mWhichMeal ", Integer.valueOf(this.r), " mList ", this.k, " text ", j);
        if (TextUtils.isEmpty(j)) {
            linearLayout.setVisibility(8);
            return;
        }
        linearLayout.setVisibility(0);
        ((HealthTextView) recyclerHolder.cwK_(R.id.food_tip_text)).setText(String.valueOf(j));
        if (!this.e) {
            recyclerHolder.a(R.id.food_tip_left, 8);
            recyclerHolder.a(R.id.food_tip_left_oval, 8);
            recyclerHolder.a(R.id.food_tip_right_oval, 8);
            recyclerHolder.a(R.id.food_tip_right, 8);
            return;
        }
        recyclerHolder.a(R.id.food_tip_left, 0);
        recyclerHolder.a(R.id.food_tip_left_oval, 0);
        recyclerHolder.a(R.id.food_tip_right_oval, 0);
        recyclerHolder.a(R.id.food_tip_right, 0);
        ViewTreeObserver viewTreeObserver = linearLayout.getViewTreeObserver();
        if (viewTreeObserver == null) {
            ReleaseLogUtil.a("R_RecommendFoodAdapter", "setFoodTip viewTreeObserver is null");
        } else {
            viewTreeObserver.addOnGlobalLayoutListener(new a(this, recyclerHolder));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aGV_(View view, float f) {
        if (view == null) {
            ReleaseLogUtil.a("R_RecommendFoodAdapter", "setFoodTipViewLayoutParams view is null weight ", Float.valueOf(f));
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.weight = f;
            view.setLayoutParams(layoutParams2);
        }
    }

    static class a implements ViewTreeObserver.OnGlobalLayoutListener {
        private final WeakReference<RecommendFoodAdapter> b;
        private final WeakReference<RecyclerHolder> e;

        a(RecommendFoodAdapter recommendFoodAdapter, RecyclerHolder recyclerHolder) {
            this.b = new WeakReference<>(recommendFoodAdapter);
            this.e = new WeakReference<>(recyclerHolder);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            RecommendFoodAdapter recommendFoodAdapter = this.b.get();
            if (recommendFoodAdapter == null) {
                ReleaseLogUtil.a("R_RecommendFoodAdapter", "InnerViewTreeObserver onGlobalLayout recommendFoodAdapter is null");
                return;
            }
            RecyclerHolder recyclerHolder = this.e.get();
            if (recyclerHolder == null) {
                ReleaseLogUtil.a("R_RecommendFoodAdapter", "InnerViewTreeObserver onGlobalLayout recyclerHolder is null");
                return;
            }
            LinearLayout linearLayout = (LinearLayout) recyclerHolder.cwK_(R.id.food_tip);
            if (linearLayout != null) {
                ViewTreeObserver viewTreeObserver = linearLayout.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
                HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.food_tip_text);
                r3 = (linearLayout.getWidth() - (healthTextView != null ? healthTextView.getTextWidth() : 0.0f)) - nsf.b(R.dimen._2131363052_res_0x7f0a04ec);
            }
            float f = r3 / 2.0f;
            recommendFoodAdapter.aGV_(recyclerHolder.cwK_(R.id.food_tip_left), f);
            recommendFoodAdapter.aGV_(recyclerHolder.cwK_(R.id.food_tip_right), f);
        }
    }
}
