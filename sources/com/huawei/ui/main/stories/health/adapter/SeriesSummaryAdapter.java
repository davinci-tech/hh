package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class SeriesSummaryAdapter extends RecyclerView.Adapter<SummaryViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f10125a;
    private SeriesCourseListActivity d;
    private Map<String, Drawable> b = new HashMap();
    private List<String> e = new ArrayList();

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 1;
    }

    public SeriesSummaryAdapter(Context context) {
        this.f10125a = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dCF_, reason: merged with bridge method [inline-methods] */
    public SummaryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SummaryViewHolder(LayoutInflater.from(this.f10125a).inflate(R.layout.series_summary_item_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SummaryViewHolder summaryViewHolder, int i) {
        if (summaryViewHolder.c == null) {
            LogUtil.d("SeriesSummaryAdapter", "holder.imageContainer == null");
            return;
        }
        summaryViewHolder.c.removeAllViews();
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            Drawable drawable = this.b.get(this.e.get(i2));
            if (drawable != null) {
                ImageView imageView = new ImageView(this.d);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setAdjustViewBounds(true);
                imageView.setImageDrawable(drawable);
                summaryViewHolder.c.addView(imageView, -1, -2);
            }
        }
    }

    public void b(Map<String, Drawable> map, SeriesCourseListActivity seriesCourseListActivity, List<String> list) {
        this.d = seriesCourseListActivity;
        this.b.clear();
        this.b.putAll(map);
        this.e.addAll(list);
        notifyDataSetChanged();
    }

    public static class SummaryViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout c;

        public SummaryViewHolder(View view) {
            super(view);
            this.c = (LinearLayout) view.findViewById(R.id.img_container);
        }
    }
}
