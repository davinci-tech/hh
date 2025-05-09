package com.huawei.health.marketing.views.holders;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;

/* loaded from: classes3.dex */
public class DietAnalysisSeriesViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f2911a;
    private HealthTextView b;
    private FrameLayout c;
    private ImageView d;
    private HealthImageView e;
    private HealthTextView j;

    public DietAnalysisSeriesViewHolder(View view) {
        super(view);
        this.c = (FrameLayout) view.findViewById(R.id.course_item_tahiti);
        this.d = (ImageView) view.findViewById(R.id.course_item_tahiti_background);
        this.j = (HealthTextView) view.findViewById(R.id.series_course_tahiti_title);
        this.b = (HealthTextView) view.findViewById(R.id.series_course_tahiti_subtitle);
        this.f2911a = (ImageView) view.findViewById(R.id.card_view_image);
        this.e = (HealthImageView) view.findViewById(R.id.content_background);
    }

    public FrameLayout arg_() {
        return this.c;
    }

    public ImageView arf_() {
        return this.d;
    }

    public HealthTextView c() {
        return this.j;
    }

    public HealthTextView e() {
        return this.b;
    }

    public HealthImageView b() {
        return this.e;
    }
}
