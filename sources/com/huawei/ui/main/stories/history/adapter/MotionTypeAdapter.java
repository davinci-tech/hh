package com.huawei.ui.main.stories.history.adapter;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrt;
import defpackage.rds;
import defpackage.rdy;
import java.util.List;

/* loaded from: classes7.dex */
public class MotionTypeAdapter extends BaseRecyclerAdapter<rdy> {
    private int d;

    public MotionTypeAdapter(List<rdy> list) {
        super(list, R.layout.item_right_motion_type_info);
        this.d = BaseApplication.getContext().getResources().getColor(R.color._2131299386_res_0x7f090c3a);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, rdy rdyVar) {
        if (rdyVar != null) {
            d(recyclerHolder, rdyVar);
        }
    }

    private void d(RecyclerHolder recyclerHolder, rdy rdyVar) {
        int i;
        LogUtil.a("Track_MotionTypeAdapter", "ItemNameId :", Integer.valueOf(rdyVar.c()));
        if (rdyVar.c() == 0) {
            recyclerHolder.b(R.id.sport_item_name, "");
        } else {
            recyclerHolder.b(R.id.sport_item_name, rdyVar.c());
        }
        View cwK_ = recyclerHolder.cwK_(R.id.sport_item_name);
        if (cwK_ instanceof HealthTextView) {
            ((HealthTextView) cwK_).setAutoTextInfo(9, 1, 2);
        }
        if (rdyVar.g()) {
            i = R.drawable._2131430888_res_0x7f0b0de8;
        } else {
            i = nrt.a(BaseApplication.getContext()) ? R.drawable._2131430886_res_0x7f0b0de6 : R.drawable._2131430887_res_0x7f0b0de7;
        }
        View cwK_2 = recyclerHolder.cwK_(R.id.sport_motion_item_layout);
        if (cwK_2 instanceof LinearLayout) {
            ((LinearLayout) cwK_2).setBackgroundResource(i);
        }
        a(recyclerHolder, rdyVar);
    }

    private void a(RecyclerHolder recyclerHolder, rdy rdyVar) {
        Resources resources = recyclerHolder.itemView.getResources();
        View cwK_ = recyclerHolder.cwK_(R.id.sport_item_iv);
        if (cwK_ == null || resources == null) {
            LogUtil.h("Track_MotionTypeAdapter", "view or resource is null");
            return;
        }
        if (!(cwK_ instanceof ImageView)) {
            LogUtil.h("Track_MotionTypeAdapter", "view is not instanceof ImageView");
            return;
        }
        ((ImageView) cwK_).setPadding(4, 4, 4, 4);
        if (rdyVar.g()) {
            recyclerHolder.cwN_(R.id.sport_item_iv, rds.dMx_(rdyVar.dMA_(), this.d));
        } else {
            recyclerHolder.cwN_(R.id.sport_item_iv, rds.dMx_(rdyVar.dMA_(), rds.d()));
        }
    }
}
