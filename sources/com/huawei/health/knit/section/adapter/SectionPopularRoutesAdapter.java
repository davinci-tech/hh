package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ebw;
import defpackage.eet;
import defpackage.nrf;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionPopularRoutesAdapter extends RecyclerView.Adapter<b> {

    /* renamed from: a, reason: collision with root package name */
    private static final int f2579a = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);
    private List<Double> b;
    private List<String> c;
    private Context d;
    private List<String> e;
    private OnClickSectionListener h;
    private List<Long> j;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public SectionPopularRoutesAdapter(Context context, ebw ebwVar) {
        this.d = context;
        b(ebwVar);
    }

    public void a(ebw ebwVar) {
        b(ebwVar);
        notifyDataSetChanged();
    }

    private void b(ebw ebwVar) {
        this.e = ebwVar.d();
        this.c = ebwVar.c();
        this.b = ebwVar.b();
        this.j = ebwVar.e();
        this.h = ebwVar.i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: adT_, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(this.d).inflate(R.layout.section_popular_routes_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i) {
        d(bVar, i);
    }

    private void d(b bVar, final int i) {
        RecyclerView.LayoutParams c = c(bVar, i);
        if (c == null) {
            LogUtil.b("params is invalid!", new Object[0]);
            return;
        }
        if (bVar.f != null) {
            bVar.f.setLayoutParams(c);
            bVar.f.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionPopularRoutesAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (SectionPopularRoutesAdapter.this.h != null) {
                        LogUtil.a("SectionPopularRoutesAdapter", "ITEM IS CLICK!");
                        SectionPopularRoutesAdapter.this.h.onClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        b(bVar, i);
        a(bVar, i);
    }

    protected RecyclerView.LayoutParams c(b bVar, int i) {
        ViewGroup.LayoutParams layoutParams = bVar.f.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.b("layoutParams error", new Object[0]);
            return null;
        }
        int dimensionPixelSize = this.d.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.d.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int dimensionPixelSize3 = this.d.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        if (getItemCount() > 1) {
            layoutParams2.width = this.d.getResources().getDimensionPixelSize(R.dimen._2131362980_res_0x7f0a04a4);
            if (i == 0) {
                layoutParams2.setMarginStart(dimensionPixelSize);
            } else if (i == getItemCount() - 1) {
                layoutParams2.setMarginStart(dimensionPixelSize3);
                layoutParams2.setMarginEnd(dimensionPixelSize2);
            } else {
                layoutParams2.setMarginStart(dimensionPixelSize3);
            }
            return layoutParams2;
        }
        layoutParams2.width = -1;
        layoutParams2.setMarginStart(dimensionPixelSize3);
        layoutParams2.setMarginEnd(dimensionPixelSize2);
        return layoutParams2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return eet.d(this.c, this.e, this.b, this.j);
    }

    class b extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private LinearLayout f2580a;
        private ImageView b;
        private TextView d;
        private LinearLayout e;
        private RelativeLayout f;
        private TextView h;
        private TextView i;

        public b(View view) {
            super(view);
            this.f = (RelativeLayout) view.findViewById(R.id.recycle_item_layout);
            this.b = (ImageView) view.findViewById(R.id.item_picture);
            this.i = (TextView) view.findViewById(R.id.item_title);
            this.d = (TextView) view.findViewById(R.id.item_durationl_first);
            this.h = (TextView) view.findViewById(R.id.item_duration_second);
            this.f2580a = (LinearLayout) view.findViewById(R.id.date_line_layout);
            this.e = (LinearLayout) view.findViewById(R.id.flag_line_layout);
            this.f2580a.setVisibility(8);
            this.e.setVisibility(8);
        }

        public void c(String str, int i) {
            if (!TextUtils.isEmpty(str)) {
                if (str.startsWith("http")) {
                    nrf.cHI_(str, this.b, i);
                    return;
                } else {
                    nrf.cJy_(str, this.b);
                    return;
                }
            }
            LogUtil.h("SectionPopularRoutesAdapter", "setImageUrl imageUrl isEmpty");
        }
    }

    private void b(b bVar, int i) {
        if (!eet.b(this.c, i) || bVar.b == null) {
            return;
        }
        bVar.c(this.c.get(i), f2579a);
    }

    private void a(b bVar, int i) {
        if (eet.b(this.e, i) && bVar.i != null) {
            bVar.i.setText(this.e.get(i));
        }
        if (eet.b(this.b, i) && bVar.d != null) {
            bVar.d.setText(a(R$string.IDS_hw_show_sport_kms_string, R$string.IDS_hw_show_sport_kms_string_en, this.b.get(i).doubleValue()));
        }
        if (!eet.b(this.j, i) || bVar.h == null) {
            return;
        }
        bVar.h.setText(this.d.getResources().getQuantityString(R.plurals._2130903415_res_0x7f030177, UnitUtil.e(this.j.get(i).longValue())));
    }

    private String a(int i, int i2, double d) {
        boolean h = UnitUtil.h();
        double d2 = d / 1000.0d;
        double e = UnitUtil.e(d2, 3);
        if (h) {
            d2 = e;
        }
        if (h) {
            i = i2;
        }
        return this.d.getResources().getString(i, UnitUtil.e(d2, 1, 2));
    }
}
