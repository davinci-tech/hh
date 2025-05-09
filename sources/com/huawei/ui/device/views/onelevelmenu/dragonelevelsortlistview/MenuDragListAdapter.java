package com.huawei.ui.device.views.onelevelmenu.dragonelevelsortlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.oai;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class MenuDragListAdapter extends RecyclerView.Adapter<SlideViewHolder> implements ItemTouchHelperAdapter {
    private Context b;
    private final LayoutInflater c;
    List<Integer> d;
    private RefreshUiCallback e;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public MenuDragListAdapter(Context context, List<Integer> list, RefreshUiCallback refreshUiCallback) {
        this.e = null;
        this.b = context;
        this.d = list;
        this.c = LayoutInflater.from(context);
        this.e = refreshUiCallback;
    }

    public void d(List<Integer> list) {
        this.d = list;
    }

    public static class SlideViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout b;
        private HealthTextView c;

        public SlideViewHolder(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.itemText);
            this.b = (RelativeLayout) view.findViewById(R.id.item_menu_close);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cVK_, reason: merged with bridge method [inline-methods] */
    public SlideViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SlideViewHolder(this.c.inflate(R.layout.activity_one_level_menu_item_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SlideViewHolder slideViewHolder, final int i) {
        slideViewHolder.c.setText(oai.a().a(this.b, this.d.get(i).intValue()));
        slideViewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.views.onelevelmenu.dragonelevelsortlistview.MenuDragListAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MenuDragListAdapter.this.d != null && i >= 0 && MenuDragListAdapter.this.d.size() > i) {
                    MenuDragListAdapter.this.d.remove(i);
                }
                MenuDragListAdapter.this.notifyDataSetChanged();
                MenuDragListAdapter.this.e.refreshUi();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    @Override // com.huawei.ui.device.views.onelevelmenu.dragonelevelsortlistview.ItemTouchHelperAdapter
    public void onItemMove(int i, int i2) {
        List<Integer> list = this.d;
        if (list == null) {
            LogUtil.h("MenuDragListAdapter", "onItemMove mDataList is null");
            return;
        }
        if (i < 0 || i >= list.size()) {
            LogUtil.h("MenuDragListAdapter", "onItemMove fromPosition IndexOutOfBoundsException");
        } else if (i2 < 0 || i2 >= this.d.size()) {
            LogUtil.h("MenuDragListAdapter", "onItemMove toPosition IndexOutOfBoundsException");
        } else {
            Collections.swap(this.d, i, i2);
            notifyItemMoved(i, i2);
        }
    }
}
