package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.nrf;
import defpackage.qlb;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class SugarFoodReferenceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context c;
    private List<qlb> d = new ArrayList();

    public SugarFoodReferenceAdapter(Context context) {
        this.c = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SugarFoodReferenceViewHolder(LayoutInflater.from(this.c).inflate(R.layout.item_sugar_food_reference, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (!(viewHolder instanceof SugarFoodReferenceViewHolder) || koq.b(this.d, i)) {
            LogUtil.b("Suggestion_SugarFoodReferenceAdapter", "onBindViewHolder error!");
        } else {
            qlb qlbVar = this.d.get(i);
            ((SugarFoodReferenceViewHolder) viewHolder).c(qlbVar.a(), qlbVar.c());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<qlb> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void e(List<qlb> list) {
        List<qlb> list2 = this.d;
        if (list2 != null) {
            list2.addAll(list);
            notifyDataSetChanged();
        }
    }

    public class SugarFoodReferenceViewHolder extends RecyclerView.ViewHolder {
        ImageView d;
        HealthTextView e;

        SugarFoodReferenceViewHolder(View view) {
            super(view);
            this.d = (ImageView) view.findViewById(R.id.sugar_food_reference_picture);
            this.e = (HealthTextView) view.findViewById(R.id.sugar_food_reference_name);
        }

        void c(String str, String str2) {
            if (this.e == null || TextUtils.isEmpty(str) || this.d == null || TextUtils.isEmpty(str2)) {
                return;
            }
            this.e.setText(str);
            nrf.cIU_(str2, this.d, R.dimen._2131364629_res_0x7f0a0b15);
        }
    }
}
