package com.huawei.ui.main.stories.configuredpage.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.configuredpage.views.ConfiguredServerContentHolder;
import defpackage.cdu;
import java.util.List;

/* loaded from: classes6.dex */
public class ConfiguredServerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f9690a;
    private List<cdu> b;

    public ConfiguredServerAdapter(Context context, List<cdu> list) {
        this.f9690a = context;
        this.b = list;
    }

    public void d(List<cdu> list) {
        this.b = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ConfiguredServerContentHolder(LayoutInflater.from(this.f9690a).inflate(R.layout.hw_configure_server_detail_item, (ViewGroup) null));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder == null) {
            LogUtil.h("ConfiguredServerAdapter", "onBindViewHolder holder is null.");
            return;
        }
        if (i < 0 || i >= this.b.size()) {
            return;
        }
        viewHolder.setIsRecyclable(false);
        cdu cduVar = this.b.get(i);
        if (cduVar != null) {
            if (viewHolder instanceof ConfiguredServerContentHolder) {
                ((ConfiguredServerContentHolder) viewHolder).d(this.b, cduVar, i);
            } else {
                LogUtil.h("ConfiguredServerAdapter", "onBindViewHolder holder is not matching");
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }
}
