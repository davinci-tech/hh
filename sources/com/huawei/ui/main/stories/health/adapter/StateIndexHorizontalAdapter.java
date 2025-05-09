package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.qkk;
import java.util.List;

/* loaded from: classes6.dex */
public class StateIndexHorizontalAdapter extends RecyclerView.Adapter<b> {
    private Context b;
    private int c = -1;
    private List<qkk> d;
    private OnItemClickListener e;

    public interface OnItemClickListener {
        void onItemClick(String str, int i);
    }

    public StateIndexHorizontalAdapter(List<qkk> list, Context context) {
        this.d = list;
        this.b = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dDi_, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(this.b).inflate(R.layout.item_state_index_horizontal, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    public void e(OnItemClickListener onItemClickListener) {
        this.e = onItemClickListener;
    }

    public void c(int i) {
        int i2 = 0;
        while (i2 < this.d.size()) {
            this.d.get(i2).a(i2 == i);
            i2++;
        }
        notifyDataSetChanged();
    }

    public int c() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, final int i) {
        if (koq.d(this.d, i)) {
            bVar.f10140a.setText(this.d.get(i).d());
            bVar.c.setText(this.d.get(i).a());
            if (this.d.get(i).c()) {
                this.c = i;
                bVar.itemView.setBackground(this.b.getResources().getDrawable(R.drawable._2131431315_res_0x7f0b0f93));
                bVar.c.setTextColor(ContextCompat.getColor(this.b, R.color._2131299238_res_0x7f090ba6));
                bVar.f10140a.setTextColor(ContextCompat.getColor(this.b, R.color._2131299238_res_0x7f090ba6));
            } else {
                bVar.itemView.setBackground(this.b.getResources().getDrawable(R.drawable._2131431314_res_0x7f0b0f92));
                bVar.c.setTextColor(ContextCompat.getColor(this.b, R.color._2131299241_res_0x7f090ba9));
                bVar.f10140a.setTextColor(ContextCompat.getColor(this.b, R.color._2131299236_res_0x7f090ba4));
            }
            bVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.adapter.StateIndexHorizontalAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (StateIndexHorizontalAdapter.this.e != null) {
                        StateIndexHorizontalAdapter.this.e.onItemClick(((qkk) StateIndexHorizontalAdapter.this.d.get(i)).a(), i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    class b extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f10140a;
        private HealthTextView c;

        public b(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.item_horizontal_index);
            this.f10140a = (HealthTextView) view.findViewById(R.id.item_horizontal_value);
        }
    }
}
