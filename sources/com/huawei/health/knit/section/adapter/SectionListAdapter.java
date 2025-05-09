package com.huawei.health.knit.section.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ebq;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionListAdapter extends RecyclerView.Adapter<SectionListHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f2569a;
    private List<String> b;
    private List<String> c;
    private OnClickSectionListener d;
    private List<Drawable> e;
    private List<String> h;
    private List<Integer> j;

    public void d(ebq ebqVar) {
        if (ebqVar != null) {
            this.e = ebqVar.b();
            this.b = ebqVar.e();
            this.c = ebqVar.c();
            this.f2569a = ebqVar.d();
            this.h = ebqVar.g();
            this.j = ebqVar.i();
            this.d = ebqVar.a();
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: adz_, reason: merged with bridge method [inline-methods] */
    public SectionListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("SectionListAdapter", "SectionListAdapter onCreateViewHolder");
        if (nsn.s()) {
            return new SectionListHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_line_record_layout_item_large, viewGroup, false));
        }
        return new SectionListHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_line_record_layout_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SectionListHolder sectionListHolder, final int i) {
        LogUtil.a("SectionListAdapter", "SectionListAdapter onBindViewHolder");
        List<Drawable> list = this.e;
        if (list != null && i < list.size()) {
            if (LanguageUtil.bc(BaseApplication.e())) {
                nsy.cMm_(sectionListHolder.b, nrz.cKm_(BaseApplication.e(), this.e.get(i)));
            } else {
                nsy.cMm_(sectionListHolder.b, this.e.get(i));
            }
        }
        if (c(this.b, i)) {
            sectionListHolder.f2570a.setText(String.valueOf(this.b.get(i)));
            if (nsn.k()) {
                sectionListHolder.f2570a.setTextSize(1, 20.0f);
            }
        }
        if (c(this.c, i)) {
            sectionListHolder.e.setText(String.valueOf(this.c.get(i)));
        }
        List<Integer> list2 = this.f2569a;
        if (list2 != null && i < list2.size()) {
            sectionListHolder.e.setTextColor(this.f2569a.get(i).intValue());
        }
        if (c(this.h, i)) {
            sectionListHolder.f.setText(String.valueOf(this.h.get(i)));
        }
        List<Integer> list3 = this.j;
        if (list3 != null && i < list3.size()) {
            sectionListHolder.h.setImageResource(this.j.get(i).intValue());
        }
        if (i != getItemCount() - 1 || sectionListHolder.d == null) {
            sectionListHolder.d.setVisibility(0);
        } else {
            sectionListHolder.d.setVisibility(8);
        }
        if (sectionListHolder.c == null || this.d == null) {
            return;
        }
        sectionListHolder.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SectionListAdapter.this.d.onClick(i);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<Drawable> list = this.e;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    private boolean c(List list, int i) {
        return list != null && i < list.size();
    }

    public static class SectionListHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2570a;
        private ImageView b;
        private LinearLayout c;
        private HealthDivider d;
        private HealthTextView e;
        private HealthTextView f;
        private ImageView h;

        public SectionListHolder(View view) {
            super(view);
            this.b = (ImageView) view.findViewById(R.id.left_image);
            this.f2570a = (HealthTextView) view.findViewById(R.id.left_top_value);
            this.e = (HealthTextView) view.findViewById(R.id.left_top_state);
            this.f = (HealthTextView) view.findViewById(R.id.right_time);
            this.h = (ImageView) view.findViewById(R.id.right_icon);
            this.d = (HealthDivider) view.findViewById(R.id.section_divider);
            this.c = (LinearLayout) view.findViewById(R.id.abnormal_blood_pressure_recyclerview_item_ll);
        }
    }
}
