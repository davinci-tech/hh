package com.huawei.health.marketing.views.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes3.dex */
public class ThreeGridSeriesViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f2917a;
    private ImageView b;
    private ImageView c;
    private HealthTextView d;
    private ImageView e;
    private HealthCardView f;
    private HealthTextView g;
    private HealthTextView h;
    private ImageView i;
    private RelativeLayout j;

    public ThreeGridSeriesViewHolder(View view) {
        super(view);
        this.f = (HealthCardView) view.findViewById(R.id.three_grid_tahiti);
        this.i = (ImageView) view.findViewById(R.id.three_grid_background);
        this.j = (RelativeLayout) view.findViewById(R.id.three_grid_rl);
        this.h = (HealthTextView) view.findViewById(R.id.three_grid_title);
        this.g = (HealthTextView) view.findViewById(R.id.three_grid_subtitle);
        this.f2917a = (ImageView) view.findViewById(R.id.card_view_image);
        this.b = (ImageView) view.findViewById(R.id.item_image_1);
        this.c = (ImageView) view.findViewById(R.id.item_image_2);
        this.e = (ImageView) view.findViewById(R.id.item_image_3);
        this.d = (HealthTextView) view.findViewById(R.id.text_tip);
    }

    public ImageView arn_() {
        return this.i;
    }

    public HealthTextView i() {
        return this.h;
    }

    public HealthTextView g() {
        return this.g;
    }

    public HealthTextView b() {
        return this.d;
    }

    public ImageView ark_() {
        return this.b;
    }

    public ImageView arl_() {
        return this.c;
    }

    public ImageView arm_() {
        return this.e;
    }
}
