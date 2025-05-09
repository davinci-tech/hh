package com.huawei.ui.commonui.barlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nkq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class BarViewAdapter extends RecyclerView.Adapter<d> {

    /* renamed from: a, reason: collision with root package name */
    private List<nkq> f8766a = new ArrayList();
    private int b;
    private Context c;
    private int d;

    public BarViewAdapter(Context context, int i, int i2) {
        this.c = context;
        this.d = i;
        d(i2);
    }

    void d(int i) {
        this.b = i;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cwT_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.health_bar_item_layout, viewGroup, false);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) inflate.getLayoutParams();
        if (layoutParams == null) {
            inflate.setLayoutParams(new RecyclerView.LayoutParams(this.d, -2));
        } else {
            layoutParams.width = this.d;
        }
        return new d(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, int i) {
        nkq nkqVar;
        List<nkq> list = this.f8766a;
        if (list == null || i >= list.size() || (nkqVar = this.f8766a.get(i)) == null) {
            return;
        }
        dVar.c.setData(nkqVar.e(), nkqVar.a(), this.b);
        dVar.e.setText(nkqVar.d());
        dVar.d.setText(nkqVar.b());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f8766a.size();
    }

    public void e(List<nkq> list) {
        this.f8766a = list;
        notifyDataSetChanged();
    }

    public void d(List<nkq> list) {
        int size = this.f8766a.size();
        this.f8766a.addAll(list);
        notifyItemRangeInserted(size, list.size());
    }

    static final class d extends RecyclerView.ViewHolder {
        BarView c;
        HealthTextView d;
        HealthTextView e;

        public d(View view) {
            super(view);
            this.e = (HealthTextView) view.findViewById(R.id.header_text);
            this.c = (BarView) view.findViewById(R.id.bar_view);
            this.d = (HealthTextView) view.findViewById(R.id.bottom_text);
        }
    }
}
