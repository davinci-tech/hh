package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section16_9Series_01Adapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.eat;

/* loaded from: classes3.dex */
public class Section16_9Series_02Adapter extends Section16_9Series_01Adapter {
    private Context b;

    public Section16_9Series_02Adapter(Context context, eat eatVar) {
        super(context, eatVar);
        this.b = context;
    }

    @Override // com.huawei.health.knit.section.adapter.Section16_9Series_01Adapter, androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acm_ */
    public Section16_9Series_01Adapter.Section16_9Series_01ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Section16_9Series_01Adapter.Section16_9Series_01ViewHolder(LayoutInflater.from(this.b).inflate(R.layout.section16_9series_02_item, viewGroup, false));
    }

    @Override // com.huawei.health.knit.section.adapter.Section16_9Series_01Adapter
    protected RecyclerView.LayoutParams c(Section16_9Series_01Adapter.Section16_9Series_01ViewHolder section16_9Series_01ViewHolder, int i) {
        ViewGroup.LayoutParams layoutParams = section16_9Series_01ViewHolder.acl_().getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.b("layoutParams error", new Object[0]);
            return null;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int dimensionPixelSize3 = this.b.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        if (i == 0) {
            layoutParams2.setMarginStart(dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue());
        } else if (i == getItemCount() - 1) {
            layoutParams2.setMarginStart(dimensionPixelSize3);
            layoutParams2.setMarginEnd(dimensionPixelSize2 + ((Integer) safeRegionWidth.second).intValue());
        } else {
            layoutParams2.setMarginStart(dimensionPixelSize3);
        }
        return layoutParams2;
    }
}
