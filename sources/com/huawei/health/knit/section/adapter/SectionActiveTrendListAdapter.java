package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.edz;
import defpackage.eej;
import defpackage.koq;
import defpackage.nrz;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionActiveTrendListAdapter extends RecyclerView.Adapter<SectionActiveTrendListViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f2559a;
    private OnClickSectionListener b;
    private List<edz> e;

    public SectionActiveTrendListAdapter(Context context, List<edz> list) {
        this.f2559a = context;
        this.e = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: adn_, reason: merged with bridge method [inline-methods] */
    public SectionActiveTrendListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SectionActiveTrendListViewHolder(LayoutInflater.from(this.f2559a).inflate(R.layout.section_active_trend_recyclerview_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SectionActiveTrendListViewHolder sectionActiveTrendListViewHolder, final int i) {
        if (koq.b(this.e, i)) {
            LogUtil.h("SectionActiveTrendListAdapter", "onBindViewHolder position is out of bounds");
            return;
        }
        List<edz> list = this.e;
        if (list == null) {
            LogUtil.h("SectionActiveTrendListAdapter", "onBindViewHolder mSectionActiveTrendList is null");
            return;
        }
        edz edzVar = list.get(i);
        if (edzVar == null) {
            LogUtil.h("SectionActiveTrendListAdapter", "onBindViewHolder bean is null");
        } else {
            b(sectionActiveTrendListViewHolder, edzVar);
            sectionActiveTrendListViewHolder.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionActiveTrendListAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (SectionActiveTrendListAdapter.this.b != null) {
                        SectionActiveTrendListAdapter.this.b.onClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<edz> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    private void b(SectionActiveTrendListViewHolder sectionActiveTrendListViewHolder, edz edzVar) {
        CharSequence agZ_ = eej.agZ_(this.f2559a, eej.d(edzVar.b()), edzVar.c(), R.dimen._2131365076_res_0x7f0a0cd4);
        CharSequence agZ_2 = eej.agZ_(this.f2559a, eej.d(edzVar.a()), edzVar.e(), R.dimen._2131365076_res_0x7f0a0cd4);
        int i = edzVar.i();
        if (i == 0) {
            a(sectionActiveTrendListViewHolder, edzVar);
        } else if (i == 1) {
            d(sectionActiveTrendListViewHolder, edzVar);
        } else if (i == 2) {
            c(sectionActiveTrendListViewHolder, edzVar);
        } else {
            LogUtil.h("SectionActiveTrendListAdapter", "onBindViewHolder switch default");
        }
        HealthTextView healthTextView = sectionActiveTrendListViewHolder.f2560a;
        if (agZ_ == null) {
            agZ_ = "";
        }
        healthTextView.setText(agZ_);
        HealthTextView healthTextView2 = sectionActiveTrendListViewHolder.b;
        if (agZ_2 == null) {
            agZ_2 = "";
        }
        healthTextView2.setText(agZ_2);
        e(sectionActiveTrendListViewHolder, edzVar);
    }

    private void a(SectionActiveTrendListViewHolder sectionActiveTrendListViewHolder, edz edzVar) {
        a(sectionActiveTrendListViewHolder, R.drawable._2131431722_res_0x7f0b112a);
        sectionActiveTrendListViewHolder.k.setText(this.f2559a.getString(R$string.IDS_settings_steps));
        if (edzVar.b() == 0) {
            sectionActiveTrendListViewHolder.e.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427502_res_0x7f0b00ae));
        } else {
            sectionActiveTrendListViewHolder.e.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427501_res_0x7f0b00ad));
        }
        if (edzVar.a() == 0) {
            sectionActiveTrendListViewHolder.c.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427497_res_0x7f0b00a9));
            sectionActiveTrendListViewHolder.c.setAlpha(1.0f);
        } else {
            sectionActiveTrendListViewHolder.c.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427501_res_0x7f0b00ad));
            sectionActiveTrendListViewHolder.c.setAlpha(0.2f);
        }
    }

    private void d(SectionActiveTrendListViewHolder sectionActiveTrendListViewHolder, edz edzVar) {
        a(sectionActiveTrendListViewHolder, R.drawable._2131431721_res_0x7f0b1129);
        sectionActiveTrendListViewHolder.k.setText(this.f2559a.getString(R$string.IDS_active_workout));
        if (edzVar.b() == 0) {
            sectionActiveTrendListViewHolder.e.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427504_res_0x7f0b00b0));
        } else {
            sectionActiveTrendListViewHolder.e.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427503_res_0x7f0b00af));
        }
        if (edzVar.a() == 0) {
            sectionActiveTrendListViewHolder.c.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427497_res_0x7f0b00a9));
            sectionActiveTrendListViewHolder.c.setAlpha(1.0f);
        } else {
            sectionActiveTrendListViewHolder.c.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427503_res_0x7f0b00af));
            sectionActiveTrendListViewHolder.c.setAlpha(0.2f);
        }
    }

    private void c(SectionActiveTrendListViewHolder sectionActiveTrendListViewHolder, edz edzVar) {
        a(sectionActiveTrendListViewHolder, R.drawable._2131431719_res_0x7f0b1127);
        sectionActiveTrendListViewHolder.k.setText(this.f2559a.getString(R$string.IDS_three_circle_card_activity_hours));
        if (edzVar.b() == 0) {
            sectionActiveTrendListViewHolder.e.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427500_res_0x7f0b00ac));
        } else {
            sectionActiveTrendListViewHolder.e.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427499_res_0x7f0b00ab));
        }
        if (edzVar.a() == 0) {
            sectionActiveTrendListViewHolder.c.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427497_res_0x7f0b00a9));
            sectionActiveTrendListViewHolder.c.setAlpha(1.0f);
        } else {
            sectionActiveTrendListViewHolder.c.setProgressDrawable(this.f2559a.getResources().getDrawable(R.drawable._2131427499_res_0x7f0b00ab));
            sectionActiveTrendListViewHolder.c.setAlpha(0.2f);
        }
    }

    private void a(SectionActiveTrendListViewHolder sectionActiveTrendListViewHolder, int i) {
        if (LanguageUtil.bc(this.f2559a)) {
            HealthImageView healthImageView = sectionActiveTrendListViewHolder.g;
            Context context = this.f2559a;
            healthImageView.setImageDrawable(nrz.cKm_(context, ContextCompat.getDrawable(context, i)));
            return;
        }
        sectionActiveTrendListViewHolder.g.setImageResource(i);
    }

    private void e(SectionActiveTrendListViewHolder sectionActiveTrendListViewHolder, edz edzVar) {
        if (LanguageUtil.bc(this.f2559a)) {
            sectionActiveTrendListViewHolder.f.setBackground(this.f2559a.getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            sectionActiveTrendListViewHolder.f.setBackground(this.f2559a.getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        if (LanguageUtil.h(this.f2559a)) {
            sectionActiveTrendListViewHolder.d.setMaxLines(2);
        } else {
            sectionActiveTrendListViewHolder.d.setMaxLines(3);
        }
        sectionActiveTrendListViewHolder.d.setText(edzVar.agL_());
        if (edzVar.b() != 0 || !edzVar.j()) {
            sectionActiveTrendListViewHolder.e.setMax(edzVar.a() == 0 ? 1 : edzVar.a());
            sectionActiveTrendListViewHolder.e.setProgress(edzVar.b() == 0 ? 0 : (int) Math.max(edzVar.b(), Math.round(sectionActiveTrendListViewHolder.i * sectionActiveTrendListViewHolder.e.getMax())));
        } else {
            sectionActiveTrendListViewHolder.e.setMax(100);
            sectionActiveTrendListViewHolder.e.setProgress(6);
        }
        sectionActiveTrendListViewHolder.c.setMax(edzVar.b() != 0 ? edzVar.b() : 1);
        sectionActiveTrendListViewHolder.c.setProgress(edzVar.a() != 0 ? (int) Math.max(edzVar.a(), Math.round(sectionActiveTrendListViewHolder.i * sectionActiveTrendListViewHolder.c.getMax())) : 0);
    }

    public static class SectionActiveTrendListViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2560a;
        private HealthTextView b;
        private HealthProgressBar c;
        private HealthTextView d;
        private HealthProgressBar e;
        private HealthImageView f;
        private HealthImageView g;
        private ConstraintLayout h;
        private double i;
        private int j;
        private HealthTextView k;

        public SectionActiveTrendListViewHolder(View view) {
            super(view);
            this.j = -1;
            this.i = 0.0d;
            this.h = (ConstraintLayout) view.findViewById(R.id.cl_type_container);
            this.g = (HealthImageView) view.findViewById(R.id.hiv_type_icon);
            this.k = (HealthTextView) view.findViewById(R.id.htv_type_title);
            this.f = (HealthImageView) view.findViewById(R.id.hiv_type_right_arrow);
            this.d = (HealthTextView) view.findViewById(R.id.htv_compared_description);
            this.f2560a = (HealthTextView) view.findViewById(R.id.htv_current_value);
            this.e = (HealthProgressBar) view.findViewById(R.id.hpb_current_value_progress);
            this.b = (HealthTextView) view.findViewById(R.id.htv_compared_value);
            this.c = (HealthProgressBar) view.findViewById(R.id.hpb_compared_value_progress);
            this.e.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.knit.section.adapter.SectionActiveTrendListAdapter.SectionActiveTrendListViewHolder.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (SectionActiveTrendListViewHolder.this.j == -1) {
                        SectionActiveTrendListViewHolder sectionActiveTrendListViewHolder = SectionActiveTrendListViewHolder.this;
                        sectionActiveTrendListViewHolder.j = sectionActiveTrendListViewHolder.e.getWidth();
                        if (SectionActiveTrendListViewHolder.this.j != 0) {
                            SectionActiveTrendListViewHolder.this.i = Math.round((r0.e.getHeight() * 100.0d) / SectionActiveTrendListViewHolder.this.j) / 100.0d;
                            SectionActiveTrendListViewHolder.this.e.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                }
            });
        }
    }

    public void c(OnClickSectionListener onClickSectionListener) {
        this.b = onClickSectionListener;
    }
}
