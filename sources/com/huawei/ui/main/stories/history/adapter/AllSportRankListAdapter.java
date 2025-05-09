package com.huawei.ui.main.stories.history.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cat;
import defpackage.hji;
import defpackage.koq;
import defpackage.rci;
import defpackage.rdu;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class AllSportRankListAdapter extends RecyclerView.Adapter<b> {
    private Context b;
    private List<rci> c;
    private int e;

    public AllSportRankListAdapter(Context context, List<rci> list) {
        this.b = context;
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dJS_, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_all_sport_rank_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<rci> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i) {
        if (koq.b(this.c, i)) {
            return;
        }
        b(bVar, i);
        j(bVar, i);
        e(bVar, i);
        a(bVar, i);
        c(bVar, i);
    }

    private void b(b bVar, int i) {
        bVar.c.setVisibility(0);
        bVar.f.setVisibility(8);
        if (i == 0) {
            bVar.c.setImageResource(R.drawable._2131430304_res_0x7f0b0ba0);
            return;
        }
        if (i == 1) {
            bVar.c.setImageResource(R.drawable._2131430305_res_0x7f0b0ba1);
        } else {
            if (i == 2) {
                bVar.c.setImageResource(R.drawable._2131430306_res_0x7f0b0ba2);
                return;
            }
            bVar.c.setVisibility(8);
            bVar.f.setVisibility(0);
            bVar.f.setText(UnitUtil.e(i + 1, 1, 0));
        }
    }

    private void j(b bVar, int i) {
        bVar.h.setText(rdu.c(this.c.get(i).d(), this.b));
    }

    private void e(b bVar, int i) {
        int i2 = this.e;
        if (i2 == 0) {
            bVar.f10291a.setText(a(this.c.get(i).c()));
        } else if (i2 == 1) {
            double e = this.c.get(i).e();
            bVar.f10291a.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, (int) e, UnitUtil.e(e, 1, 0)));
        } else {
            bVar.f10291a.setText(b(this.c.get(i).a()));
        }
    }

    private void a(b bVar, int i) {
        double b2 = this.c.get(i).b();
        if (b2 >= 0.1d) {
            bVar.b.setText(UnitUtil.e(b2, 2, 1));
            return;
        }
        bVar.b.setText(HiDataFilter.DataFilterExpression.LESS_THAN + UnitUtil.e(0.1d, 2, 1));
    }

    private String a(double d) {
        return cat.c(d / 1000.0d);
    }

    private String b(double d) {
        return hji.b(d / 1000.0d);
    }

    private void c(b bVar, int i) {
        if (i == this.c.size() - 1) {
            bVar.d.setVisibility(8);
        } else {
            bVar.d.setVisibility(0);
        }
    }

    public void c(int i) {
        this.e = i;
        notifyDataSetChanged();
    }

    class b extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f10291a;
        private HealthTextView b;
        private ImageView c;
        private HealthDivider d;
        private HealthTextView f;
        private HealthTextView h;

        public b(View view) {
            super(view);
            this.c = (ImageView) view.findViewById(R.id.rank_image);
            this.f = (HealthTextView) view.findViewById(R.id.rank_id);
            this.h = (HealthTextView) view.findViewById(R.id.rank_sport_type);
            this.f10291a = (HealthTextView) view.findViewById(R.id.rank_sport_value);
            this.b = (HealthTextView) view.findViewById(R.id.rank_sport_percent);
            this.d = (HealthDivider) view.findViewById(R.id.rank_item_divider);
        }
    }
}
