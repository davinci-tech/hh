package com.huawei.health.ecologydevice.ui.measure.adapter;

import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.dhe;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class SecondRopeItemAdapter extends BaseRecyclerAdapter<dhe> {
    private List<dhe> c;

    @Deprecated
    public SecondRopeItemAdapter(List<dhe> list) {
        super(list, R.layout.second_rope_item);
        this.c = new ArrayList(10);
    }

    public SecondRopeItemAdapter() {
        super(new ArrayList(0), R.layout.second_rope_item);
        ArrayList arrayList = new ArrayList(10);
        this.c = arrayList;
        refreshDataChange(arrayList);
    }

    public void c(List<dhe> list) {
        this.c.clear();
        this.c.addAll(list);
        notifyDataSetChanged();
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, dhe dheVar) {
        if (recyclerHolder == null || dheVar == null) {
            LogUtil.h("SecondRopeItemAdapter", "convert holder or itemData is null");
            return;
        }
        RelativeLayout relativeLayout = (RelativeLayout) recyclerHolder.cwK_(R.id.second_rope_item_layout);
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.second_rope_item_title);
        HealthTextView healthTextView2 = (HealthTextView) recyclerHolder.cwK_(R.id.second_rope_item_description);
        HealthImageView healthImageView = (HealthImageView) recyclerHolder.cwK_(R.id.second_rope_item_img);
        relativeLayout.setAlpha(dheVar.a() ? 1.0f : 0.5f);
        healthTextView.setText(dheVar.c());
        if (TextUtils.isEmpty(dheVar.e())) {
            healthTextView2.setVisibility(8);
        } else {
            healthTextView2.setText(dheVar.e());
            healthTextView2.setVisibility(0);
        }
        healthImageView.setBackgroundResource(dheVar.d());
    }
}
