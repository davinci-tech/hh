package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.subheader.HealthSubHeader;

/* loaded from: classes4.dex */
public class FitnessSubTitleViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private HealthSubHeader f3219a;

    public FitnessSubTitleViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sug_fitness_item_subtitle, viewGroup, false));
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.itemView.findViewById(R.id.item_sub_header);
        this.f3219a = healthSubHeader;
        healthSubHeader.setMoreText("");
        this.f3219a.setMoreLayoutVisibility(4);
        this.f3219a.setSubHeaderBackgroundColor(ContextCompat.getColor(this.itemView.getContext(), R.color._2131296971_res_0x7f0902cb));
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f3219a.setVisibility(8);
        } else {
            this.f3219a.setVisibility(0);
            this.f3219a.setHeadTitleText(str);
        }
    }
}
