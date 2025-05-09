package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.nsn;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionPieChartBasicAdapter extends RecyclerView.Adapter<TitleViewHolder> {
    private final Context b;
    private final List<b> e = new ArrayList();

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i != 0) {
            return i == 3 ? 2 : 1;
        }
        return 0;
    }

    public SectionPieChartBasicAdapter(Context context) {
        this.b = context;
    }

    public void d(List<b> list) {
        this.e.clear();
        this.e.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ajv_, reason: merged with bridge method [inline-methods] */
    public TitleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.b).inflate(i != 0 ? R.layout.section_pie_basic_situation_value_row : R.layout.section_pie_basic_situation_title_row, viewGroup, false);
        if (i == 2) {
            aju_(inflate);
        }
        return i != 0 ? new ValueViewHolder(inflate) : new TitleViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(TitleViewHolder titleViewHolder, int i) {
        if (!koq.b(this.e, i)) {
            titleViewHolder.e(this.e.get(i));
        }
        if (i == getItemCount() - 1) {
            nsy.cMA_(titleViewHolder.f2713a, 8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    private void aju_(View view) {
        if (view.getLayoutParams() instanceof RecyclerView.LayoutParams) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            layoutParams.height = (int) Utils.convertDpToPixel(42.0f);
            view.setLayoutParams(layoutParams);
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private CharSequence f2714a;
        private CharSequence b;
        private CharSequence c;
        private CharSequence d;
        private CharSequence e;
        private Drawable f;
        private Drawable g;
        private Drawable h;
        private CharSequence i;
        private CharSequence j;

        public b() {
        }

        public b(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
            this(charSequence, charSequence2, null, null, charSequence3, null, null, charSequence4, null, null);
        }

        public b(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Drawable drawable, CharSequence charSequence4, CharSequence charSequence5, Drawable drawable2, CharSequence charSequence6, CharSequence charSequence7, Drawable drawable3) {
            this.c = charSequence;
            this.b = charSequence2;
            this.d = charSequence3;
            this.f2714a = charSequence4;
            this.i = charSequence5;
            this.e = charSequence6;
            this.j = charSequence7;
            this.h = drawable;
            this.f = drawable2;
            this.g = drawable3;
        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private final HealthDivider f2713a;
        private final HealthTextView b;
        private final HealthTextView c;
        private final HealthTextView d;
        private final HealthTextView e;

        public TitleViewHolder(View view) {
            super(view);
            this.e = (HealthTextView) view.findViewById(R.id.text1);
            this.d = (HealthTextView) view.findViewById(R.id.text2);
            this.c = (HealthTextView) view.findViewById(R.id.text3);
            this.b = (HealthTextView) view.findViewById(R.id.text4);
            this.f2713a = (HealthDivider) view.findViewById(R.id.basic_situation_value_divider);
        }

        public void e(b bVar) {
            nsy.cMr_(this.e, bVar.c);
            nsy.cMr_(this.d, bVar.b);
            nsy.cMr_(this.c, bVar.f2714a);
            nsy.cMr_(this.b, bVar.e);
            if (nsn.t() && nsn.c() < 1.45f) {
                this.b.setTextSize(1, 14.0f);
                return;
            }
            if (nsn.c() >= 1.45f && nsn.c() < 1.55f) {
                this.b.setTextSize(1, 17.0f);
            } else if (nsn.c() > 1.55f) {
                this.b.setTextSize(1, 22.0f);
            } else {
                this.b.setTextSize(1, 14.0f);
            }
        }
    }

    public static class ValueViewHolder extends TitleViewHolder {
        private final HealthTextView b;
        private final HealthTextView c;
        private final HealthTextView d;

        public ValueViewHolder(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.textSub2);
            this.d = (HealthTextView) view.findViewById(R.id.textSub3);
            this.b = (HealthTextView) view.findViewById(R.id.textSub4);
        }

        @Override // com.huawei.health.knit.section.view.SectionPieChartBasicAdapter.TitleViewHolder
        public void e(b bVar) {
            super.e(bVar);
            nsy.cMr_(this.c, bVar.d);
            HealthTextView healthTextView = this.c;
            if (healthTextView != null) {
                healthTextView.setVisibility(TextUtils.isEmpty(bVar.d) ? 8 : 0);
            }
            nsy.cMt_(this.c, bVar.h);
            nsy.cMr_(this.d, bVar.i);
            HealthTextView healthTextView2 = this.d;
            if (healthTextView2 != null) {
                healthTextView2.setVisibility(TextUtils.isEmpty(bVar.i) ? 8 : 0);
            }
            nsy.cMt_(this.d, bVar.f);
            nsy.cMr_(this.b, bVar.j);
            HealthTextView healthTextView3 = this.b;
            if (healthTextView3 != null) {
                healthTextView3.setVisibility(TextUtils.isEmpty(bVar.j) ? 8 : 0);
            }
            nsy.cMt_(this.b, bVar.g);
        }
    }
}
