package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.health.adapter.ClaimMeasureDataAdapter;
import com.huawei.ui.main.stories.health.interactors.healthdata.SelectUserInterface;
import defpackage.csh;
import defpackage.koq;
import defpackage.qsj;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class ClaimMeasureDataAdapter extends RecyclerView.Adapter<MyViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private SelectUserInterface f10120a;
    private int b;
    private Context c;
    private List<csh> e = new ArrayList(16);

    public ClaimMeasureDataAdapter(Context context, SelectUserInterface selectUserInterface) {
        this.f10120a = selectUserInterface;
        this.c = context;
    }

    public void e() {
        this.b = 0;
        for (csh cshVar : this.e) {
            if (cshVar.f()) {
                cshVar.e(false);
            }
        }
    }

    public void e(List<csh> list) {
        if (koq.b(list)) {
            LogUtil.h("ClaimMeasureDataAdapter", "setList beanList is empty");
            return;
        }
        if (this.e.size() <= 0) {
            this.e.addAll(list);
        } else {
            for (csh cshVar : list) {
                if (!this.e.contains(cshVar)) {
                    this.e.add(cshVar);
                }
            }
            Collections.sort(this.e, new Comparator() { // from class: qgg
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ClaimMeasureDataAdapter.a((csh) obj, (csh) obj2);
                }
            });
        }
        notifyDataSetChanged();
    }

    public static /* synthetic */ int a(csh cshVar, csh cshVar2) {
        return (int) (cshVar2.e() - cshVar.e());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dCy_, reason: merged with bridge method [inline-methods] */
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.c).inflate(R.layout.item_claim_measure_data, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        if (koq.b(this.e, i)) {
            LogUtil.h("ClaimMeasureDataAdapter", "onBindViewHolder mList is out of bounds");
            return;
        }
        final csh cshVar = this.e.get(i);
        if (cshVar == null) {
            LogUtil.h("ClaimMeasureDataAdapter", "onBindViewHolder dataBean is null");
            return;
        }
        HiHealthData c = cshVar.c();
        if (c == null) {
            LogUtil.h("ClaimMeasureDataAdapter", "onBindViewHolder hiHealthData is null");
            return;
        }
        int i2 = c.getInt("trackdata_deviceType");
        double a2 = UnitUtil.a(cshVar.d());
        myViewHolder.f10121a.setText(qsj.e(a2, qsj.c(a2, i2)));
        String a3 = cshVar.a();
        if (!TextUtils.isEmpty(a3)) {
            myViewHolder.c.setText(a3);
        }
        myViewHolder.e.setChecked(cshVar.f());
        myViewHolder.b.setVisibility(i == this.e.size() + (-1) ? 8 : 0);
        myViewHolder.e.setOnClickListener(new View.OnClickListener() { // from class: qgh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ClaimMeasureDataAdapter.this.dCx_(cshVar, view);
            }
        });
    }

    public /* synthetic */ void dCx_(csh cshVar, View view) {
        if (cshVar.f()) {
            this.b--;
        } else {
            this.b++;
        }
        LogUtil.a("ClaimMeasureDataAdapter", "mSelectStatus onClick ... dataBean.isChoose = ", Boolean.valueOf(cshVar.f()), ", mSize = ", Integer.valueOf(this.b));
        this.f10120a.selectItem(this.e.size(), this.b);
        cshVar.e(!cshVar.f());
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a(boolean z) {
        LogUtil.a("ClaimMeasureDataAdapter", "setAllChooseItem isAllSelect = ", Boolean.valueOf(z));
        Iterator<csh> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().e(z);
        }
        if (z) {
            this.b = this.e.size();
        } else {
            this.b = 0;
        }
        notifyDataSetChanged();
        this.f10120a.selectItem(this.e.size(), this.b);
    }

    public ArrayList<csh> b() {
        ArrayList<csh> arrayList = new ArrayList<>(16);
        for (csh cshVar : this.e) {
            if (cshVar.f()) {
                arrayList.add(cshVar);
            }
        }
        return arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    public void c() {
        ArrayList arrayList = new ArrayList(16);
        for (csh cshVar : this.e) {
            if (!cshVar.f()) {
                arrayList.add(cshVar);
            }
        }
        this.e = arrayList;
        this.b = 0;
        this.f10120a.selectItem(arrayList.size(), this.b);
        notifyDataSetChanged();
    }

    public int a() {
        return this.e.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f10121a;
        HealthDivider b;
        HealthTextView c;
        HealthCheckBox e;

        public MyViewHolder(View view) {
            super(view);
            this.f10121a = (HealthTextView) view.findViewById(R.id.tv_claim_weight_data_body_weight);
            this.c = (HealthTextView) view.findViewById(R.id.tv_claim_weight_data_measur_time);
            this.e = (HealthCheckBox) view.findViewById(R.id.iv_claim_weight_data_select_status);
            this.b = (HealthDivider) view.findViewById(R.id.view_claim_weight_data_line);
        }
    }
}
