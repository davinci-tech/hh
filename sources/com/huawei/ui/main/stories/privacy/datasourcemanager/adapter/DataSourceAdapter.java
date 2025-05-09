package com.huawei.ui.main.stories.privacy.datasourcemanager.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.privacy.datasourcemanager.adapter.DataSourceAdapter;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import com.huawei.ui.main.stories.privacy.datasourcemanager.listener.DataSourceItemClickListener;
import defpackage.koq;
import defpackage.nrf;
import defpackage.qrp;
import defpackage.rrb;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class DataSourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private int f10403a;
    private final int b;
    private final int c;
    private Context d;
    private int e;
    private DataSourceItemClickListener f;
    private int g;
    private Drawable h;
    private Drawable i;
    private List<SourceInfo> j = new ArrayList(10);
    private Drawable k;
    private LayoutInflater o;

    public DataSourceAdapter(Context context, DataSourceItemClickListener dataSourceItemClickListener) {
        this.o = LayoutInflater.from(context);
        this.d = context;
        this.f = dataSourceItemClickListener;
        this.k = this.d.getResources().getDrawable(R.drawable._2131431108_res_0x7f0b0ec4);
        this.i = this.d.getResources().getDrawable(R.drawable._2131431106_res_0x7f0b0ec2);
        this.h = this.d.getResources().getDrawable(R.drawable._2131431107_res_0x7f0b0ec3);
        this.f10403a = this.d.getColor(R.color._2131296665_res_0x7f090199);
        Resources resources = this.d.getResources();
        this.c = resources.getDimensionPixelSize(R.dimen._2131363844_res_0x7f0a0804);
        this.b = resources.getDimensionPixelSize(R.dimen._2131363706_res_0x7f0a077a);
    }

    public void d(List<SourceInfo> list) {
        if (list == null) {
            return;
        }
        if (koq.c(this.j)) {
            this.j.clear();
        }
        this.j = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new e(this.o.inflate(R.layout.activity_manager_data_source_item_divider, viewGroup, false));
        }
        if (i == 3) {
            return new c(this.o.inflate(R.layout.activity_manager_data_source_item, viewGroup, false));
        }
        if (i == 2) {
            return new d(this.o.inflate(R.layout.activity_manager_data_source_item_title, viewGroup, false));
        }
        LogUtil.a("DataSourceAdapter", "onCreateViewHolder error type");
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        SourceInfo sourceInfo = i < this.j.size() ? this.j.get(i) : null;
        if (sourceInfo == null) {
            LogUtil.h("DataSourceAdapter", "onBindViewHolder model is null");
            return;
        }
        if (viewHolder instanceof c) {
            a((c) viewHolder, i, sourceInfo);
            return;
        }
        if (viewHolder instanceof d) {
            d dVar = (d) viewHolder;
            dVar.b.setText(sourceInfo.getTitle());
            if (sourceInfo.isHealthKit()) {
                dVar.b.setVisibility(8);
                return;
            }
            return;
        }
        if (viewHolder instanceof e) {
            ((e) viewHolder).d.setBackgroundColor(this.d.getResources().getColor(R.color._2131297799_res_0x7f090607));
        } else {
            LogUtil.a("DataSourceAdapter", "onBindViewHolder Holder is null");
        }
    }

    private void a(c cVar, final int i, SourceInfo sourceInfo) {
        cVar.e.setText(sourceInfo.getDeviceName());
        if (!TextUtils.isEmpty(sourceInfo.getDeviceIcon())) {
            cVar.b.setVisibility(0);
            nrf.cIu_(sourceInfo.getDeviceIcon(), cVar.b);
        } else {
            cVar.b.setVisibility(8);
        }
        if (LanguageUtil.bc(this.d)) {
            cVar.c.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        cVar.f10404a.setOnClickListener(new View.OnClickListener() { // from class: rjv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DataSourceAdapter.this.dPC_(i, view);
            }
        });
        int sourceType = sourceInfo.getSourceType();
        if (sourceType == 2) {
            c(cVar, i);
        }
        if (sourceType == 1) {
            e(cVar, i);
        }
        if (sourceType == 3) {
            c(cVar);
        }
    }

    public /* synthetic */ void dPC_(int i, View view) {
        if (this.f == null) {
            LogUtil.b("DataSourceAdapter", "mDataSourceItemClickListener is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (!qrp.e()) {
                this.f.onItemClickListener(i);
            } else {
                LogUtil.a("DataSourceAdapter", "click fast");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void c(c cVar) {
        cVar.f10404a.setBackground(this.i);
        cVar.d.setVisibility(8);
        ConstraintLayout constraintLayout = cVar.f10404a;
        int i = this.c;
        int i2 = this.b;
        constraintLayout.setPadding(i, i2, i, i2);
    }

    private void c(c cVar, int i) {
        int i2 = this.g;
        if (i2 == 1) {
            cVar.f10404a.setBackground(this.i);
            cVar.d.setVisibility(8);
            ConstraintLayout constraintLayout = cVar.f10404a;
            int i3 = this.c;
            int i4 = this.b;
            constraintLayout.setPadding(i3, i4, i3, i4);
            return;
        }
        if (i2 > 1) {
            if (rrb.b(i, this.e)) {
                cVar.d.setVisibility(0);
                cVar.f10404a.setBackground(this.k);
                ConstraintLayout constraintLayout2 = cVar.f10404a;
                int i5 = this.c;
                constraintLayout2.setPadding(i5, this.b, i5, 0);
                return;
            }
            if (rrb.e(i, this.e, this.g)) {
                cVar.d.setVisibility(8);
                cVar.f10404a.setBackground(this.h);
                ConstraintLayout constraintLayout3 = cVar.f10404a;
                int i6 = this.c;
                constraintLayout3.setPadding(i6, 0, i6, this.b);
                return;
            }
            ConstraintLayout constraintLayout4 = cVar.f10404a;
            int i7 = this.c;
            constraintLayout4.setPadding(i7, 0, i7, 0);
            cVar.d.setVisibility(0);
            cVar.f10404a.setBackgroundColor(this.f10403a);
            return;
        }
        LogUtil.a("DataSourceAdapter", "mDeviceSize size is 0");
    }

    private void e(c cVar, int i) {
        int i2 = this.e;
        if (i2 == 1) {
            cVar.f10404a.setBackground(this.i);
            cVar.d.setVisibility(8);
            ConstraintLayout constraintLayout = cVar.f10404a;
            int i3 = this.c;
            int i4 = this.b;
            constraintLayout.setPadding(i3, i4, i3, i4);
            return;
        }
        if (i2 <= 1) {
            LogUtil.a("DataSourceAdapter", "mActivityStatisticsSize size is 0");
            return;
        }
        if (i == 1) {
            cVar.d.setVisibility(0);
            cVar.f10404a.setBackground(this.k);
            ConstraintLayout constraintLayout2 = cVar.f10404a;
            int i5 = this.c;
            constraintLayout2.setPadding(i5, this.b, i5, 0);
            return;
        }
        if (i2 == i) {
            cVar.d.setVisibility(8);
            cVar.f10404a.setBackground(this.h);
            ConstraintLayout constraintLayout3 = cVar.f10404a;
            int i6 = this.c;
            constraintLayout3.setPadding(i6, 0, i6, this.b);
            return;
        }
        ConstraintLayout constraintLayout4 = cVar.f10404a;
        int i7 = this.c;
        constraintLayout4.setPadding(i7, 0, i7, 0);
        cVar.f10404a.setBackgroundColor(this.f10403a);
    }

    public void a(int i) {
        this.g = i;
    }

    public void c(int i) {
        this.e = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SourceInfo> list = this.j;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.j, i)) {
            return 0;
        }
        return this.j.get(i).getmItemType();
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        ConstraintLayout f10404a;
        ImageView b;
        ImageView c;
        HealthDivider d;
        HealthTextView e;

        c(View view) {
            super(view);
            this.f10404a = (ConstraintLayout) view.findViewById(R.id.item_content_rl);
            this.b = (ImageView) view.findViewById(R.id.content_icon);
            this.e = (HealthTextView) view.findViewById(R.id.content_name);
            this.c = (ImageView) view.findViewById(R.id.list_content_arrow_right);
            this.d = (HealthDivider) view.findViewById(R.id.data_line);
        }
    }

    static class d extends RecyclerView.ViewHolder {
        HealthTextView b;

        d(View view) {
            super(view);
            this.b = (HealthTextView) view.findViewById(R.id.item_title);
        }
    }

    static class e extends RecyclerView.ViewHolder {
        View d;

        e(View view) {
            super(view);
            this.d = view.findViewById(R.id.health_divider_line);
        }
    }
}
