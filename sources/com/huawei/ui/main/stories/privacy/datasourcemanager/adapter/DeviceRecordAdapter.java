package com.huawei.ui.main.stories.privacy.datasourcemanager.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import com.huawei.ui.main.stories.privacy.datasourcemanager.adapter.DeviceRecordAdapter;
import com.huawei.ui.main.stories.privacy.datasourcemanager.listener.DataSourceItemClickListener;
import defpackage.koq;
import defpackage.nrz;
import defpackage.qrp;
import defpackage.rkc;
import defpackage.rrb;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class DeviceRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private DataSourceItemClickListener f10405a;
    private final int b;
    private final int c;
    private Context d;
    private int e;
    private List<rkc> f = new ArrayList(10);
    private Drawable g;
    private Drawable h;
    private Drawable i;
    private Drawable j;
    private int k;
    private int l;
    private LayoutInflater m;

    public DeviceRecordAdapter(Context context, DataSourceItemClickListener dataSourceItemClickListener) {
        this.m = LayoutInflater.from(context);
        this.d = context;
        this.f10405a = dataSourceItemClickListener;
        this.i = this.d.getResources().getDrawable(R.drawable._2131431108_res_0x7f0b0ec4);
        this.h = this.d.getResources().getDrawable(R.drawable._2131431106_res_0x7f0b0ec2);
        this.j = this.d.getResources().getDrawable(R.drawable._2131431107_res_0x7f0b0ec3);
        this.g = this.d.getResources().getDrawable(R.color._2131296665_res_0x7f090199);
        Resources resources = this.d.getResources();
        this.b = resources.getDimensionPixelSize(R.dimen._2131363844_res_0x7f0a0804);
        this.c = resources.getDimensionPixelSize(R.dimen._2131363706_res_0x7f0a077a);
    }

    public void b(List<rkc> list) {
        if (list == null) {
            return;
        }
        this.f = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new e(this.m.inflate(R.layout.activity_manager_data_source_item_divider, viewGroup, false));
        }
        if (i == 3) {
            return new b(this.m.inflate(R.layout.item_device_record_content, viewGroup, false));
        }
        if (i == 2) {
            return new a(this.m.inflate(R.layout.activity_manager_data_source_item_title, viewGroup, false));
        }
        LogUtil.a("DeviceRecordAdapter", "onCreateViewHolder error type");
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        rkc rkcVar = i < this.f.size() ? this.f.get(i) : null;
        if (rkcVar == null) {
            LogUtil.a("DeviceRecordAdapter", "onBindViewHolder model is null");
            return;
        }
        if (viewHolder instanceof b) {
            b bVar = (b) viewHolder;
            d(i, rkcVar, bVar);
            if (rkcVar.i()) {
                a(bVar, i);
            }
            if (rkcVar.h()) {
                c(bVar, i);
            }
            if (rkcVar.g()) {
                b(bVar, i);
                return;
            }
            return;
        }
        if (viewHolder instanceof a) {
            a aVar = (a) viewHolder;
            aVar.c.setText(rkcVar.b());
            if (rkcVar.g()) {
                aVar.c.setVisibility(8);
                return;
            }
            return;
        }
        if (viewHolder instanceof e) {
            ((e) viewHolder).e.setBackgroundColor(this.d.getResources().getColor(R.color._2131297799_res_0x7f090607));
        } else {
            LogUtil.a("DeviceRecordAdapter", "onBindViewHolder Holder is null");
        }
    }

    private void d(final int i, rkc rkcVar, b bVar) {
        bVar.c.setText(rkcVar.c());
        bVar.f10406a.setBackgroundResource(rkcVar.d());
        if (LanguageUtil.bc(this.d)) {
            bVar.b.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            int d = rkcVar.d();
            if (d == R.mipmap._2131821013_res_0x7f1101d5 || d == R.mipmap._2131821025_res_0x7f1101e1) {
                bVar.f10406a.setBackground(nrz.cKn_(this.d, d));
            }
        }
        bVar.d.setOnClickListener(new View.OnClickListener() { // from class: rjw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceRecordAdapter.this.dPD_(i, view);
            }
        });
    }

    public /* synthetic */ void dPD_(int i, View view) {
        if (this.f10405a == null) {
            LogUtil.b("DeviceRecordAdapter", "mDataSourceItemClickListener is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (!qrp.e()) {
                this.f10405a.onItemClickListener(i);
            } else {
                LogUtil.a("DeviceRecordAdapter", "click fast");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void b(b bVar, int i) {
        int i2 = this.k;
        if (i2 == 1) {
            bVar.d.setBackground(this.h);
            bVar.e.setVisibility(8);
            ConstraintLayout constraintLayout = bVar.d;
            int i3 = this.b;
            int i4 = this.c;
            constraintLayout.setPadding(i3, i4, i3, i4);
            return;
        }
        if (i2 <= 1) {
            LogUtil.a("DeviceRecordAdapter", "mActivityStatisticsSize size is 0");
            return;
        }
        if (i == 1) {
            bVar.e.setVisibility(0);
            bVar.d.setBackground(this.i);
            ConstraintLayout constraintLayout2 = bVar.d;
            int i5 = this.b;
            constraintLayout2.setPadding(i5, this.c, i5, 0);
            return;
        }
        if (i2 == i) {
            bVar.e.setVisibility(8);
            bVar.d.setBackground(this.j);
            ConstraintLayout constraintLayout3 = bVar.d;
            int i6 = this.b;
            constraintLayout3.setPadding(i6, 0, i6, this.c);
            return;
        }
        bVar.e.setVisibility(0);
        ConstraintLayout constraintLayout4 = bVar.d;
        int i7 = this.b;
        constraintLayout4.setPadding(i7, 0, i7, 0);
        bVar.d.setBackground(this.g);
    }

    private void c(b bVar, int i) {
        int i2 = this.l;
        if (i2 == 1) {
            bVar.d.setBackground(this.h);
            bVar.e.setVisibility(8);
            ConstraintLayout constraintLayout = bVar.d;
            int i3 = this.b;
            int i4 = this.c;
            constraintLayout.setPadding(i3, i4, i3, i4);
            return;
        }
        if (i2 > 1) {
            if (rrb.b(i, this.e)) {
                bVar.d.setBackground(this.i);
                bVar.e.setVisibility(0);
                ConstraintLayout constraintLayout2 = bVar.d;
                int i5 = this.b;
                constraintLayout2.setPadding(i5, this.c, i5, 0);
                return;
            }
            if (rrb.e(i, this.e, this.l)) {
                bVar.e.setVisibility(8);
                bVar.d.setBackground(this.j);
                ConstraintLayout constraintLayout3 = bVar.d;
                int i6 = this.b;
                constraintLayout3.setPadding(i6, 0, i6, this.c);
                return;
            }
            bVar.e.setVisibility(0);
            bVar.d.setBackground(this.g);
            ConstraintLayout constraintLayout4 = bVar.d;
            int i7 = this.b;
            constraintLayout4.setPadding(i7, 0, i7, 0);
            return;
        }
        LogUtil.a("DeviceRecordAdapter", "mHealthStateSize size is 0");
    }

    private void a(b bVar, int i) {
        int i2 = this.e;
        if (i2 == 1) {
            bVar.d.setBackground(this.h);
            bVar.e.setVisibility(8);
            ConstraintLayout constraintLayout = bVar.d;
            int i3 = this.b;
            int i4 = this.c;
            constraintLayout.setPadding(i3, i4, i3, i4);
            return;
        }
        if (i2 <= 1) {
            LogUtil.a("DeviceRecordAdapter", "mActivityStatisticsSize size is 0");
            return;
        }
        if (i == 1) {
            bVar.e.setVisibility(0);
            bVar.d.setBackground(this.i);
            ConstraintLayout constraintLayout2 = bVar.d;
            int i5 = this.b;
            constraintLayout2.setPadding(i5, this.c, i5, 0);
            return;
        }
        if (i2 == i) {
            bVar.e.setVisibility(8);
            bVar.d.setBackground(this.j);
            ConstraintLayout constraintLayout3 = bVar.d;
            int i6 = this.b;
            constraintLayout3.setPadding(i6, 0, i6, this.c);
            return;
        }
        bVar.d.setBackground(this.g);
        bVar.e.setVisibility(0);
        ConstraintLayout constraintLayout4 = bVar.d;
        int i7 = this.b;
        constraintLayout4.setPadding(i7, 0, i7, 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<rkc> list = this.f;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.f, i)) {
            return 0;
        }
        return this.f.get(i).e();
    }

    static class b extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        ImageView f10406a;
        ImageView b;
        HealthTextView c;
        ConstraintLayout d;
        HealthDivider e;

        b(View view) {
            super(view);
            this.d = (ConstraintLayout) view.findViewById(R.id.item_content_cl);
            this.f10406a = (ImageView) view.findViewById(R.id.content_icon);
            this.c = (HealthTextView) view.findViewById(R.id.content_name);
            this.b = (ImageView) view.findViewById(R.id.record_arrow);
            this.e = (HealthDivider) view.findViewById(R.id.data_line);
        }
    }

    static class a extends RecyclerView.ViewHolder {
        HealthTextView c;

        a(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.item_title);
        }
    }

    static class e extends RecyclerView.ViewHolder {
        View e;

        e(View view) {
            super(view);
            this.e = view.findViewById(R.id.health_divider_line);
        }
    }

    public void d(int i) {
        this.k = i;
    }

    public void b(int i) {
        this.e = i;
    }

    public void e(int i) {
        this.l = i;
    }
}
