package com.huawei.ui.main.stories.history.adapter;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.koq;
import defpackage.nsn;
import defpackage.rdi;
import defpackage.rdy;
import java.util.List;

/* loaded from: classes7.dex */
public class RightListSportAdapter extends BaseRecyclerAdapter<rdi> {
    private MotionTypeAdapter c;
    private Context d;
    private SportOnSelectPositionListener e;

    public interface SportOnSelectPositionListener {
        void selectedPosition(int i, int i2);
    }

    public RightListSportAdapter(List<rdi> list, Context context) {
        super(list, R.layout.item_sport_type_right);
        this.e = null;
        this.d = context;
        LogUtil.a("Track_RightListAdapter", "RightListAdapter init");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return super.getItemId(i);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, rdi rdiVar) {
        LogUtil.a("Track_RightListAdapter", "start convert position: ", Integer.valueOf(i));
        if (rdiVar != null) {
            recyclerHolder.b(R.id.right_name, rdiVar.a());
            HealthRecycleView healthRecycleView = (HealthRecycleView) recyclerHolder.cwK_(R.id.sport_item_type);
            if (koq.b(rdiVar.c())) {
                LogUtil.h("Track_RightListAdapter", "MotionTypeAdapter convert  itemData.getTypeItemlist() is null");
                healthRecycleView.setVisibility(8);
            } else {
                a(healthRecycleView, i, rdiVar.c());
                recyclerHolder.setIsRecyclable(false);
            }
        }
    }

    private void a(HealthRecycleView healthRecycleView, final int i, final List<rdy> list) {
        LogUtil.a("Track_RightListAdapter", "enter initItemRecyclerView");
        healthRecycleView.setLayoutManager(new GridLayoutManager(this.d, 3, 1, false) { // from class: com.huawei.ui.main.stories.history.adapter.RightListSportAdapter.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        RecyclerView.ItemAnimator itemAnimator = healthRecycleView.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        LogUtil.a("Track_RightListAdapter", "ItemList.size ", Integer.valueOf(list.size()));
        MotionTypeAdapter motionTypeAdapter = new MotionTypeAdapter(list);
        this.c = motionTypeAdapter;
        healthRecycleView.setAdapter(motionTypeAdapter);
        this.c.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: com.huawei.ui.main.stories.history.adapter.RightListSportAdapter.3
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public void onItemClicked(RecyclerHolder recyclerHolder, int i2, Object obj) {
                if (!nsn.o()) {
                    RightListSportAdapter.this.b(i2, list);
                    RightListSportAdapter.this.e.selectedPosition(i, i2);
                } else {
                    LogUtil.h("Track_RightListAdapter", "onClick isFastDoubleClick");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, List<rdy> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 == i) {
                list.get(i2).d(true);
            } else {
                list.get(i2).d(false);
            }
        }
        this.c.notifyDataSetChanged();
        notifyDataSetChanged();
    }

    public void c(SportOnSelectPositionListener sportOnSelectPositionListener) {
        this.e = sportOnSelectPositionListener;
    }
}
