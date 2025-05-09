package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionGridAdapter extends RecyclerView.Adapter<SectionGridHolder> {

    /* renamed from: a, reason: collision with root package name */
    private OnClickSectionListener f2568a;
    private List<SpannableString> b;
    private List<Integer> c;
    private List<String> d;
    private Context e;

    public SectionGridAdapter(Context context) {
        this.e = context;
    }

    public void e(List<String> list, List<Integer> list2, List<SpannableString> list3, OnClickSectionListener onClickSectionListener) {
        this.d = list;
        this.c = list2;
        this.b = list3;
        this.f2568a = onClickSectionListener;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ady_, reason: merged with bridge method [inline-methods] */
    public SectionGridHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SectionGridHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_grid_record_layout_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SectionGridHolder sectionGridHolder, final int i) {
        if (b(this.d, i)) {
            if (LanguageUtil.p(this.e)) {
                sectionGridHolder.b.setTextSize(0, this.e.getResources().getDimension(R.dimen._2131362704_res_0x7f0a0390));
                sectionGridHolder.b.setMaxLines(2);
                sectionGridHolder.b.setEllipsize(TextUtils.TruncateAt.END);
            }
            sectionGridHolder.b.setText(String.valueOf(this.d.get(i)));
        }
        List<Integer> list = this.c;
        if (list != null && i < list.size()) {
            sectionGridHolder.b.setTextColor(this.c.get(i).intValue());
        }
        if (b(this.b, i)) {
            sectionGridHolder.e.setText(this.b.get(i));
        }
        if (this.f2568a != null) {
            sectionGridHolder.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionGridAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SectionGridAdapter.this.f2568a.onClick(i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        d(sectionGridHolder.d);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.d;
        if (list == null) {
            return 6;
        }
        return list.size();
    }

    private boolean b(List list, int i) {
        return list != null && i < list.size();
    }

    private void d(HealthCardView healthCardView) {
        int i;
        ViewGroup.LayoutParams layoutParams = healthCardView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : new ViewGroup.MarginLayoutParams(layoutParams);
        marginLayoutParams.setMargins(0, 0, 0, 0);
        int b = nrr.b(this.e);
        int i2 = this.e.getResources().getDisplayMetrics().widthPixels;
        if (nsn.ag(this.e)) {
            i = (i2 - (b * 5)) / 4;
        } else {
            i = (i2 - (b * 4)) / 3;
        }
        marginLayoutParams.width = i;
        healthCardView.setLayoutParams(marginLayoutParams);
        if (BaseApplication.getContext().getResources().getConfiguration().fontScale >= 1.2d) {
            ViewGroup.LayoutParams layoutParams2 = healthCardView.getLayoutParams();
            layoutParams2.height = -2;
            healthCardView.setLayoutParams(layoutParams2);
        }
    }

    public static class SectionGridHolder extends RecyclerView.ViewHolder {
        private HealthTextView b;
        private HealthCardView d;
        private HealthTextView e;

        public SectionGridHolder(View view) {
            super(view);
            this.d = (HealthCardView) view.findViewById(R.id.section_card_view);
            this.b = (HealthTextView) view.findViewById(R.id.top_text);
            this.e = (HealthTextView) view.findViewById(R.id.bottom_text);
        }
    }
}
