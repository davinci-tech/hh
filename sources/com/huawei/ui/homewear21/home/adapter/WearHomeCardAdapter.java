package com.huawei.ui.homewear21.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homewear21.home.card.WearHomeBaseCard;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<WearHomeBaseCard> b;
    private Context c;
    private LayoutInflater d;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public WearHomeCardAdapter(Context context, ArrayList<WearHomeBaseCard> arrayList) {
        this.c = context;
        this.b = arrayList;
        this.d = LayoutInflater.from(context);
    }

    public void c(ArrayList<WearHomeBaseCard> arrayList) {
        this.b = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i >= this.b.size()) {
            return new a(new View(this.c));
        }
        return this.b.get(i).getCardViewHolder(viewGroup, this.d);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("WearHomeCardAdapter", "onBindViewHolder");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    static class a extends RecyclerView.ViewHolder {
        public a(View view) {
            super(view);
        }
    }
}
