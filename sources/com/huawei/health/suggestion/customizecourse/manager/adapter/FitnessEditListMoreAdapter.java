package com.huawei.health.suggestion.customizecourse.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.uikit.hwsubheader.widget.HwSubHeader;

/* loaded from: classes4.dex */
public class FitnessEditListMoreAdapter extends HwSubHeader.SubHeaderRecyclerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private String f3036a;
    private final String c;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 2;
    }

    @Override // com.huawei.uikit.hwsubheader.widget.HwSubHeader.SubHeaderRecyclerAdapter
    public int getItemType(int i) {
        return i == 0 ? 1 : 0;
    }

    public FitnessEditListMoreAdapter(String str, String str2) {
        this.c = str;
        this.f3036a = str2;
    }

    public void c(String str) {
        this.f3036a = str;
        notifyDataSetChanged();
    }

    @Override // com.huawei.uikit.hwsubheader.widget.HwSubHeader.SubHeaderRecyclerAdapter
    public View getHeaderViewAsPos(int i, Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_dialog_edit_title, (ViewGroup) null, false);
        onBindViewHolder(new a(inflate), i);
        return inflate;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_edit_title, viewGroup, false));
        }
        return new d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_edit_list_more, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof a) {
            ((a) viewHolder).c.setText(this.c);
        } else if (viewHolder instanceof d) {
            ((d) viewHolder).c.setText(this.f3036a);
        } else {
            LogUtil.h("Suggestion_FitnessEditListMoreAdapter", "onBindViewHolder, position = ", Integer.valueOf(i));
        }
    }

    static class a extends RecyclerView.ViewHolder {
        HealthTextView c;

        private a(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.custom_dialog_edit_title);
        }
    }

    static class d extends RecyclerView.ViewHolder {
        HealthTextView c;

        private d(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.custom_courses_selected_value);
        }
    }
}
