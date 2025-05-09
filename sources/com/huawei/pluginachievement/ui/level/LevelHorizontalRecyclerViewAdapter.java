package com.huawei.pluginachievement.ui.level;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.pluginachievement.manager.model.LevelInfoDesc;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.koq;
import defpackage.mlu;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes9.dex */
public class LevelHorizontalRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Pair<Integer, Integer> f8448a = BaseActivity.getSafeRegionWidth();
    private Context c;
    private List<LevelInfoDesc> d;

    public LevelHorizontalRecyclerViewAdapter(Context context, List<LevelInfoDesc> list) {
        this.d = list;
        this.c = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cjd_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.c).inflate(R.layout.level_horizontal_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        cjb_(viewHolder.itemView, i, getItemCount());
        if (koq.b(this.d, i)) {
            return;
        }
        viewHolder.f8449a.setImageResource(this.d.get(i).getBadgeImage());
    }

    private void cjb_(View view, int i, int i2) {
        int e = mlu.e(view.getContext());
        view.setPadding(e - ((Integer) this.f8448a.first).intValue(), 0, e - ((Integer) this.f8448a.second).intValue(), 0);
        int a2 = e + (mlu.a(view.getContext()) / 2);
        int i3 = i == 0 ? a2 : 0;
        int i4 = i == i2 + (-1) ? a2 : 0;
        if (LanguageUtil.bc(view.getContext())) {
            cjc_(view, i4, 0, i3, 0);
        } else {
            cjc_(view, i3, 0, i4, 0);
        }
    }

    private void cjc_(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams.leftMargin == i && marginLayoutParams.topMargin == i2 && marginLayoutParams.rightMargin == i3 && marginLayoutParams.bottomMargin == i4) {
            return;
        }
        marginLayoutParams.setMargins(i, i2, i3, i4);
        view.setLayoutParams(marginLayoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.d)) {
            return 0;
        }
        return this.d.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f8449a;

        public ViewHolder(View view) {
            super(view);
            this.f8449a = (ImageView) view.findViewById(R.id.iv_big);
        }
    }
}
