package com.huawei.health.marketing.views.holders;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;

/* loaded from: classes3.dex */
public class CourseSeriesViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private FrameLayout f2910a;
    private ImageView b;
    private HealthButton c;
    private View d;
    private ImageView e;
    private HealthTextView f;
    private HealthTextView h;
    private HealthRecycleView j;

    public CourseSeriesViewHolder(View view) {
        super(view);
        this.f2910a = (FrameLayout) view.findViewById(R.id.course_item_tahiti);
        this.e = (ImageView) view.findViewById(R.id.course_item_tahiti_background);
        this.f = (HealthTextView) view.findViewById(R.id.series_course_tahiti_title);
        this.h = (HealthTextView) view.findViewById(R.id.series_course_tahiti_subtitle);
        this.b = (ImageView) view.findViewById(R.id.card_view_image);
        this.j = (HealthRecycleView) view.findViewById(R.id.course_item_recyclerview);
        this.c = (HealthButton) view.findViewById(R.id.bt_show_more);
        this.d = view.findViewById(R.id.bottom_divider);
    }

    public FrameLayout are_() {
        return this.f2910a;
    }

    public ImageView arb_() {
        return this.e;
    }

    public HealthTextView j() {
        return this.f;
    }

    public HealthTextView h() {
        return this.h;
    }

    public ImageView arc_() {
        return this.b;
    }

    public HealthRecycleView i() {
        return this.j;
    }

    public HealthButton b() {
        return this.c;
    }

    public View ard_() {
        return this.d;
    }
}
