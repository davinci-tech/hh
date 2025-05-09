package com.huawei.health.suggestion.ui.run.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.holder.RadioViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gdg;
import defpackage.koq;
import java.util.List;

/* loaded from: classes4.dex */
public class ChoiceDataAdapter extends RecyclerView.Adapter<RadioViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f3349a;
    private RadioViewHolder.OnItemClickListener c;
    private List<gdg> d;

    public ChoiceDataAdapter(Context context, List<gdg> list) {
        this.d = list;
        this.f3349a = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aKT_, reason: merged with bridge method [inline-methods] */
    public RadioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RadioViewHolder(this.f3349a.inflate(R.layout.item_single_choice, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(RadioViewHolder radioViewHolder, int i) {
        if (koq.b(this.d)) {
            LogUtil.h("Suggestion__ChoiceDataAdapter", "onBindViewHolder holder mData null");
            return;
        }
        boolean z = i != this.d.size() - 1;
        gdg gdgVar = this.d.get(i);
        radioViewHolder.d(gdgVar.b().getName(), gdgVar.d(), z);
        radioViewHolder.d(this.c, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<gdg> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void b(RadioViewHolder.OnItemClickListener onItemClickListener) {
        this.c = onItemClickListener;
    }
}
