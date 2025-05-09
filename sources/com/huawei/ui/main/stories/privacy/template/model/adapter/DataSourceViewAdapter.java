package com.huawei.ui.main.stories.privacy.template.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class DataSourceViewAdapter extends RecyclerView.Adapter<e> {
    private List<PrivacyDataModel> c = new ArrayList(10);

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dQi_, reason: merged with bridge method [inline-methods] */
    public e onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new e(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_detail_item_content, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(e eVar, int i) {
        eVar.b(this.c.get(i));
        eVar.d.setVisibility(i == this.c.size() + (-1) ? 4 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    public void b(List<PrivacyDataModel> list) {
        this.c.clear();
        this.c.addAll(list);
        notifyDataSetChanged();
    }

    class e extends RecyclerView.ViewHolder {
        protected HealthTextView c;
        private HealthDivider d;
        protected HealthTextView e;

        e(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.privacy_detail_title);
            this.e = (HealthTextView) view.findViewById(R.id.privacy_detail_desc);
            this.d = (HealthDivider) view.findViewById(R.id.data_line);
        }

        void b(PrivacyDataModel privacyDataModel) {
            this.c.setText(privacyDataModel.getDataTitle());
            this.e.setText(privacyDataModel.getDataDesc());
        }
    }
}
