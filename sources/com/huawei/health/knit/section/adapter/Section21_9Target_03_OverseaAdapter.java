package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.ece;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class Section21_9Target_03_OverseaAdapter extends RecyclerView.Adapter<b> {

    /* renamed from: a, reason: collision with root package name */
    private List<ece> f2554a = new ArrayList();
    private int b;
    private int c;
    private Context e;

    public Section21_9Target_03_OverseaAdapter(Context context, int i, int i2) {
        this.e = context;
        this.c = i;
        this.b = i2;
    }

    public void e(List<ece> list) {
        this.f2554a.clear();
        if (!koq.b(list)) {
            this.f2554a.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: adg_, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.section21_9target_03_layout_oversea_item, viewGroup, false);
        adf_(inflate);
        return new b(inflate);
    }

    private void adf_(View view) {
        RecyclerView.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (!(layoutParams2 instanceof RecyclerView.LayoutParams)) {
            layoutParams = new RecyclerView.LayoutParams(-1, -2);
        } else {
            layoutParams = (RecyclerView.LayoutParams) layoutParams2;
        }
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        int dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446);
        int intValue = ((Integer) safeRegionWidth.first).intValue();
        int intValue2 = ((Integer) safeRegionWidth.second).intValue();
        int n = nsn.n();
        int i = this.b;
        int i2 = (((n - (intValue + dimensionPixelSize)) - (dimensionPixelSize + intValue2)) - ((i - 1) * this.c)) / i;
        layoutParams.width = i2;
        layoutParams.height = (int) (i2 / 2.3333333f);
        view.setLayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i) {
        ece eceVar = this.f2554a.get(i);
        if (eceVar == null) {
            return;
        }
        bVar.d(eceVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2554a.size();
    }

    static class b extends RecyclerView.ViewHolder {
        private static final int c = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);

        /* renamed from: a, reason: collision with root package name */
        HealthImageView f2555a;
        Context b;
        HealthTextView d;
        HealthTextView e;

        public b(View view) {
            super(view);
            this.f2555a = (HealthImageView) view.findViewById(R.id.bg_img);
            this.e = (HealthTextView) view.findViewById(R.id.title);
            this.d = (HealthTextView) view.findViewById(R.id.sub_title);
            this.b = view.getContext();
        }

        public void d(ece eceVar) {
            HealthTextView healthTextView = this.e;
            if (healthTextView != null) {
                healthTextView.setText(eceVar != null ? eceVar.a() : null);
            }
            HealthTextView healthTextView2 = this.d;
            if (healthTextView2 != null) {
                healthTextView2.setText(eceVar != null ? eceVar.c() : null);
            }
            HealthImageView healthImageView = this.f2555a;
            if (healthImageView != null) {
                if (eceVar != null) {
                    nrf.a(eceVar.e(), this.f2555a, c);
                } else {
                    healthImageView.setImageDrawable(null);
                }
            }
        }
    }
}
