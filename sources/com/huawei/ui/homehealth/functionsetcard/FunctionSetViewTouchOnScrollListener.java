package com.huawei.ui.homehealth.functionsetcard;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.functionsetcard.FunctionSetPagerSnapHelper;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes9.dex */
public class FunctionSetViewTouchOnScrollListener extends RecyclerView.OnScrollListener {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9431a;
    private boolean b;
    private final FunctionSetPagerSnapHelper c;
    private Context d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private int j;

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        if (recyclerView == null) {
            LogUtil.h("FunctionSetViewTouchOnScrollListener", "recyclerView is null");
            return;
        }
        if (i == 0) {
            int findFirstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            LogUtil.a("FunctionSetViewTouchOnScrollListener", "FirstVisibleItemPosition: ", Integer.valueOf(findFirstVisibleItemPosition), ", findLastVisibleItemPosition", Integer.valueOf(findLastVisibleItemPosition));
            d(findFirstVisibleItemPosition, findLastVisibleItemPosition);
            return;
        }
        if (i == 1) {
            int findFirstVisibleItemPosition2 = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            int findLastVisibleItemPosition2 = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            LogUtil.a("FunctionSetViewTouchOnScrollListener", "FirstVisibleItemPosition: ", Integer.valueOf(findFirstVisibleItemPosition2), ", findLastVisibleItemPosition", Integer.valueOf(findLastVisibleItemPosition2));
            this.c.a(findFirstVisibleItemPosition2);
            this.c.e(findLastVisibleItemPosition2);
            return;
        }
        LogUtil.a("FunctionSetViewTouchOnScrollListener", "newState : ", Integer.valueOf(i));
    }

    private void d(int i, int i2) {
        float f = this.d.getResources().getDisplayMetrics().density;
        LogUtil.a("FunctionSetViewTouchOnScrollListener", "start: ", Integer.valueOf(i), ", end:", Integer.valueOf(i2), ", istSize:", Integer.valueOf(this.j), ", mIsNavigationGone", Boolean.valueOf(this.f9431a));
        if (this.f9431a) {
            return;
        }
        if (this.b) {
            if (i == 0 && i2 <= i + 3) {
                e(f);
                return;
            } else if (this.j - 1 == i2 && i >= i2 - 3) {
                b(f);
                return;
            } else {
                a(f);
                return;
            }
        }
        if (i == 0 && i2 <= i + 3) {
            int i3 = (int) ((6.0f * f) + 0.5d);
            this.e.setLayoutParams(new LinearLayout.LayoutParams(i3, i3));
            this.e.setBackground(this.d.getResources().getDrawable(R.drawable._2131430224_res_0x7f0b0b50));
            int i4 = (int) ((4.0f * f) + 0.5d);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i4, i4);
            layoutParams.setMarginStart((int) ((f * 8.0f) + 0.5d));
            this.g.setLayoutParams(layoutParams);
            this.g.setBackground(this.d.getResources().getDrawable(R.drawable._2131430225_res_0x7f0b0b51));
            return;
        }
        if (this.j - 1 == i2 && i >= i2 - 3) {
            int i5 = (int) ((4.0f * f) + 0.5d);
            this.e.setLayoutParams(new LinearLayout.LayoutParams(i5, i5));
            this.e.setBackground(this.d.getResources().getDrawable(R.drawable._2131430225_res_0x7f0b0b51));
            int i6 = (int) ((6.0f * f) + 0.5d);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i6, i6);
            layoutParams2.setMarginStart((int) ((f * 8.0f) + 0.5d));
            this.g.setLayoutParams(layoutParams2);
            this.g.setBackground(this.d.getResources().getDrawable(R.drawable._2131430224_res_0x7f0b0b50));
            return;
        }
        LogUtil.a("FunctionSetViewTouchOnScrollListener", "wrong red dot");
    }

    private void e(float f) {
        int i = (int) ((6.0f * f) + 0.5d);
        this.e.setLayoutParams(new LinearLayout.LayoutParams(i, i));
        this.e.setBackground(this.d.getResources().getDrawable(R.drawable._2131430224_res_0x7f0b0b50));
        int i2 = (int) ((4.0f * f) + 0.5d);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
        int i3 = (int) ((f * 8.0f) + 0.5d);
        layoutParams.setMarginStart(i3);
        this.f.setLayoutParams(layoutParams);
        this.f.setBackground(this.d.getResources().getDrawable(R.drawable._2131430225_res_0x7f0b0b51));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i2, i2);
        layoutParams2.setMarginStart(i3);
        this.g.setLayoutParams(layoutParams2);
        this.g.setBackground(this.d.getResources().getDrawable(R.drawable._2131430225_res_0x7f0b0b51));
    }

    private void b(float f) {
        int i = (int) ((4.0f * f) + 0.5d);
        this.e.setLayoutParams(new LinearLayout.LayoutParams(i, i));
        this.e.setBackground(this.d.getResources().getDrawable(R.drawable._2131430225_res_0x7f0b0b51));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        int i2 = (int) ((8.0f * f) + 0.5d);
        layoutParams.setMarginStart(i2);
        this.f.setLayoutParams(layoutParams);
        this.f.setBackground(this.d.getResources().getDrawable(R.drawable._2131430225_res_0x7f0b0b51));
        int i3 = (int) ((f * 6.0f) + 0.5d);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i3, i3);
        layoutParams2.setMarginStart(i2);
        this.g.setLayoutParams(layoutParams2);
        this.g.setBackground(this.d.getResources().getDrawable(R.drawable._2131430224_res_0x7f0b0b50));
    }

    private void a(float f) {
        int i = (int) ((4.0f * f) + 0.5d);
        this.e.setLayoutParams(new LinearLayout.LayoutParams(i, i));
        this.e.setBackground(this.d.getResources().getDrawable(R.drawable._2131430225_res_0x7f0b0b51));
        int i2 = (int) ((6.0f * f) + 0.5d);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
        int i3 = (int) ((f * 8.0f) + 0.5d);
        layoutParams.setMarginStart(i3);
        this.f.setLayoutParams(layoutParams);
        this.f.setBackground(this.d.getResources().getDrawable(R.drawable._2131430224_res_0x7f0b0b50));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i, i);
        layoutParams2.setMarginStart(i3);
        this.g.setLayoutParams(layoutParams2);
        this.g.setBackground(this.d.getResources().getDrawable(R.drawable._2131430225_res_0x7f0b0b51));
    }
}
