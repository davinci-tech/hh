package com.huawei.uikit.hwrecyclerview.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.uikit.hwrecyclerview.widget.HwLinearLayoutManager;

/* loaded from: classes9.dex */
public class HwScaleLayoutCallback extends HwLinearLayoutManager.LayoutCallback {

    /* renamed from: a, reason: collision with root package name */
    private float f10731a = 0.25f;
    private float b;

    class d implements Runnable {
        final /* synthetic */ RecyclerView b;

        d(RecyclerView recyclerView) {
            this.b = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b.requestLayout();
        }
    }

    public HwScaleLayoutCallback(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        float f2 = (f <= 0.0f ? 3.0f : f) * 85.0f;
        this.b = (-0.34f) / (f2 * f2);
    }

    private void b(View view, float f) {
        int layerType = view.getLayerType();
        if (Float.compare(f, 0.0f) == 0 && layerType != 0) {
            view.setLayerType(0, null);
        } else {
            if (Float.compare(f, 0.0f) <= 0 || layerType == 2) {
                return;
            }
            view.setLayerType(2, null);
        }
    }

    private float d(float f) {
        return (float) (1.0d - Math.pow(1.0f - f, 3.0d));
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwLinearLayoutManager.LayoutCallback
    public void onLayoutFinished(View view, RecyclerView recyclerView) {
        int height = recyclerView.getHeight();
        if (height == 0) {
            recyclerView.post(new d(recyclerView));
            return;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return;
        }
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if ((adapter.getItemViewType(childAdapterPosition) & 268435456) != 0) {
            return;
        }
        float y = (view.getY() + (view.getHeight() / 2.0f)) - (height / 2.0f);
        float abs = Math.abs(y);
        float max = Math.max((this.b * abs * abs) + 1.08f, 0.38f);
        egs_(view, max);
        if ((adapter.getItemViewType(childAdapterPosition) & 16777216) != 0) {
            view.setPivotX(view.getWidth() / 2.0f);
            float min = Math.min(abs / (recyclerView.getHeight() / 2.0f), 1.0f);
            view.setPivotY((view.getHeight() / 2.0f) - (((Math.signum(y) * view.getHeight()) * d(min)) * this.f10731a));
        }
        view.setScaleX(max);
        view.setScaleY(max);
    }

    private void egs_(View view, float f) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    egs_(childAt, f);
                } else if (childAt instanceof TextView) {
                    b(childAt, f);
                }
            }
        }
    }
}
