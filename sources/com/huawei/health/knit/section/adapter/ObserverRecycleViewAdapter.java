package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.eau;
import defpackage.eet;
import defpackage.nsn;
import defpackage.nsy;
import java.util.List;

/* loaded from: classes3.dex */
public class ObserverRecycleViewAdapter extends RecyclerView.Adapter<ObserverRecycleViewHolder> {
    private static int b = 120;

    /* renamed from: a, reason: collision with root package name */
    private OnClickSectionListener f2523a;
    private List<String> c;
    private Context d;
    private List<String> e;
    private int f;
    private int g = -1;
    private int h;
    private Typeface i;
    private int j;
    private int k;
    private Typeface l;
    private List<String> m;
    private List<String> n;
    private int o;

    public ObserverRecycleViewAdapter(Context context, eau eauVar) {
        this.d = context;
        b(eauVar);
    }

    public void b(eau eauVar) {
        LogUtil.a("ObserverRecycleViewAdapter", "setDataInternal");
        this.m = eauVar.i();
        this.c = eauVar.a();
        this.n = eauVar.o();
        this.e = eauVar.d();
        this.h = eauVar.c();
        this.k = eauVar.j();
        this.j = eauVar.h();
        this.o = eauVar.g();
        this.i = eauVar.acc_();
        this.l = eauVar.acd_();
        this.f2523a = eauVar.b();
        this.g = eauVar.e();
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acb_, reason: merged with bridge method [inline-methods] */
    public ObserverRecycleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("ObserverRecycleViewAdapter", "onCreateViewHolder");
        return new ObserverRecycleViewHolder(LayoutInflater.from(this.d).inflate(R.layout.observer_recycleview_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ObserverRecycleViewHolder observerRecycleViewHolder, final int i) {
        LogUtil.a("ObserverRecycleViewAdapter", "onBindViewHolder: " + i);
        if (eet.b(this.m, i)) {
            nsy.cMr_(observerRecycleViewHolder.f, this.m.get(i));
            observerRecycleViewHolder.f.setAutoTextInfo(10, 1, 1);
        }
        if (eet.b(this.c, i)) {
            nsy.cMr_(observerRecycleViewHolder.f2524a, this.c.get(i));
        }
        if (eet.b(this.n, i)) {
            nsy.cMr_(observerRecycleViewHolder.c, this.n.get(i));
        }
        if (eet.b(this.e, i)) {
            nsy.cMr_(observerRecycleViewHolder.d, this.e.get(i));
        }
        observerRecycleViewHolder.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.ObserverRecycleViewAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ObserverRecycleViewAdapter.this.f2523a != null) {
                    ObserverRecycleViewAdapter.this.f2523a.onClick(i);
                }
                ObserverRecycleViewAdapter.this.d(i);
                ObserverRecycleViewAdapter.this.notifyDataSetChanged();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        e(observerRecycleViewHolder, i);
        if (this.g == 1) {
            observerRecycleViewHolder.e.setMinimumHeight(nsn.a(this.d, b));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        LogUtil.a("ObserverRecycleViewAdapter", "getMaxValue: " + eet.d(this.n, this.c, this.m));
        return eet.d(this.n, this.c, this.m, this.e);
    }

    private int a() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        this.f = i;
    }

    public class ObserverRecycleViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2524a;
        private HealthTextView c;
        private HealthTextView d;
        private HealthCardView e;
        private HealthTextView f;

        public ObserverRecycleViewHolder(View view) {
            super(view);
            this.e = (HealthCardView) view.findViewById(R.id.observer_recyclerview_root_view);
            this.f = (HealthTextView) view.findViewById(R.id.observer_item_top_text);
            this.f2524a = (HealthTextView) view.findViewById(R.id.observer_item_bottom_value);
            this.c = (HealthTextView) view.findViewById(R.id.observer_item_bottom_unit);
            this.d = (HealthTextView) view.findViewById(R.id.observer_left_middle_text);
        }
    }

    public void e(ObserverRecycleViewHolder observerRecycleViewHolder, int i) {
        if (i == a()) {
            nsy.cMz_(observerRecycleViewHolder.e, this.h);
            nsy.cMu_(observerRecycleViewHolder.f, this.j);
            nsy.cMy_(observerRecycleViewHolder.f, this.i);
            nsy.cMu_(observerRecycleViewHolder.f2524a, this.j);
            nsy.cMy_(observerRecycleViewHolder.f2524a, this.i);
            nsy.cMu_(observerRecycleViewHolder.c, this.j);
            nsy.cMy_(observerRecycleViewHolder.c, this.i);
            nsy.cMu_(observerRecycleViewHolder.d, this.j);
            nsy.cMy_(observerRecycleViewHolder.d, this.i);
            return;
        }
        nsy.cMz_(observerRecycleViewHolder.e, this.k);
        nsy.cMu_(observerRecycleViewHolder.f, this.o);
        nsy.cMy_(observerRecycleViewHolder.f, this.l);
        nsy.cMu_(observerRecycleViewHolder.f2524a, this.o);
        nsy.cMy_(observerRecycleViewHolder.f2524a, this.l);
        nsy.cMu_(observerRecycleViewHolder.c, this.o);
        nsy.cMy_(observerRecycleViewHolder.c, this.l);
        nsy.cMu_(observerRecycleViewHolder.d, this.o);
        nsy.cMy_(observerRecycleViewHolder.d, this.l);
    }
}
