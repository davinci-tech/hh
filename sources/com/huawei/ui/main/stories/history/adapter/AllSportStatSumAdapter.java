package com.huawei.ui.main.stories.history.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import defpackage.cat;
import defpackage.koq;
import defpackage.rdw;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class AllSportStatSumAdapter extends RecyclerView.Adapter<d> {
    private int b;
    private Context c;
    private OnItemClickListener g;
    private List<rdw> j;
    private String e = "Track_AllSportStatSumAdapter";
    private boolean f = LanguageUtil.m(BaseApplication.getContext());
    private double d = 60.0d;

    /* renamed from: a, reason: collision with root package name */
    private double f10292a = 100000.0d;

    public interface OnItemClickListener {
        void onItemClick(String str, int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public AllSportStatSumAdapter(Context context, List<rdw> list) {
        this.c = context;
        this.j = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dJU_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.item_all_sport_data_sum, viewGroup, false);
        if (i == 0 && (inflate.getLayoutParams() instanceof RecyclerView.LayoutParams)) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) inflate.getLayoutParams();
            layoutParams.setMarginStart(inflate.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e));
            inflate.setLayoutParams(layoutParams);
        }
        return new d(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.j.size();
    }

    public void b(OnItemClickListener onItemClickListener) {
        this.g = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, final int i) {
        if (koq.d(this.j, i)) {
            dVar.e.setText(this.j.get(i).b());
            b(dVar, i);
            d(dVar, i);
            dVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.adapter.AllSportStatSumAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (AllSportStatSumAdapter.this.g != null) {
                        AllSportStatSumAdapter.this.g.onItemClick(AllSportStatSumAdapter.this.c(i), i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void b(d dVar, int i) {
        double d2 = this.j.get(i).d();
        if (i == 0) {
            dVar.c.setText(e(d2));
            dVar.f10294a.setText(a(d2));
        } else if (i == 1) {
            dVar.c.setText(UnitUtil.e(d2, 1, 0));
            dVar.f10294a.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, 0, ""));
        } else {
            dVar.c.setText(b(d2));
            dVar.f10294a.setText(c(d2));
        }
    }

    private void d(d dVar, int i) {
        if (this.j.get(i).e()) {
            LogUtil.a(this.e, "init color position = ", Integer.valueOf(i), ", color = ", Integer.valueOf(this.j.get(i).c()));
            this.b = i;
            dVar.e.setTextColor(ContextCompat.getColor(this.c, R.color._2131299238_res_0x7f090ba6));
            dVar.c.setTextColor(ContextCompat.getColor(this.c, R.color._2131299238_res_0x7f090ba6));
            dVar.f10294a.setTextColor(ContextCompat.getColor(this.c, R.color._2131299238_res_0x7f090ba6));
            dVar.b.setCardBackgroundColor(this.c.getColor(this.j.get(i).c()));
            return;
        }
        dVar.e.setTextColor(ContextCompat.getColor(this.c, R.color._2131299236_res_0x7f090ba4));
        dVar.c.setTextColor(ContextCompat.getColor(this.c, R.color._2131299236_res_0x7f090ba4));
        dVar.f10294a.setTextColor(ContextCompat.getColor(this.c, R.color._2131299236_res_0x7f090ba4));
        dVar.b.setCardBackgroundColor(this.c.getColor(R.color._2131296666_res_0x7f09019a));
    }

    public String e(double d2) {
        return cat.b(d2 / 1000.0d);
    }

    public String a(double d2) {
        return cat.a(d2);
    }

    public String b(double d2) {
        if (this.f && d2 / 1000.0d >= this.f10292a) {
            return UnitUtil.e(d2 / 1.0E7d, 1, d(d2));
        }
        return UnitUtil.e(d2 / 1000.0d, 1, d(d2));
    }

    public String c(double d2) {
        if (this.f && d2 / 1000.0d >= this.f10292a) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_skiing_ten_thousand_kcal, "");
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_plugindameon_hw_show_sport_kcal_string, "");
    }

    private int d(double d2) {
        return (!this.f || d2 / 1000.0d < this.f10292a) ? 0 : 2;
    }

    public void b(int i) {
        if (koq.b(this.j, this.b) || koq.b(this.j, i)) {
            LogUtil.h(this.e, "mIndex or cardPosition is out of bound");
            return;
        }
        int i2 = this.b;
        if (i != i2) {
            this.j.get(i2).b(false);
            this.j.get(i).b(true);
        } else {
            this.j.get(i).b(true);
        }
        notifyDataSetChanged();
    }

    class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f10294a;
        private HealthCardView b;
        private HealthTextView c;
        private HealthTextView e;

        d(View view) {
            super(view);
            this.b = (HealthCardView) view.findViewById(R.id.all_sport_item_card);
            this.e = (HealthTextView) view.findViewById(R.id.card_item_string);
            this.c = (HealthTextView) view.findViewById(R.id.card_item_value);
            this.f10294a = (HealthTextView) view.findViewById(R.id.card_item_unit);
        }
    }

    public String c(int i) {
        if (i == 0) {
            return "Track_Duration_Sum";
        }
        return i == 1 ? "Track_Count_Sum" : i == 2 ? "Track_Calorie_Sum" : "";
    }
}
