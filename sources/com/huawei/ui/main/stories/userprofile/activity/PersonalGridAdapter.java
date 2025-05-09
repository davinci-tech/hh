package com.huawei.ui.main.stories.userprofile.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.userprofile.card.PersonalGridCardViewHolder;
import defpackage.koq;
import defpackage.rzv;
import java.util.List;

/* loaded from: classes7.dex */
public class PersonalGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<rzv> c;
    private LayoutInflater e;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public PersonalGridAdapter(Context context, List<rzv> list) {
        this.e = LayoutInflater.from(context);
        c(list);
    }

    public void c(List<rzv> list) {
        if (list == null) {
            return;
        }
        if (koq.c(this.c)) {
            this.c.clear();
        }
        this.c = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.c.get(i).getCardViewHolder(viewGroup, this.e);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if ((i < this.c.size() ? this.c.get(i) : null) == null || viewHolder == null) {
            LogUtil.h("PersonalGridAdapter", "onBindViewHolder model or holder is null");
        } else if (viewHolder instanceof PersonalGridCardViewHolder) {
            ((PersonalGridCardViewHolder) viewHolder).b();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.c)) {
            return 0;
        }
        return this.c.size();
    }
}
