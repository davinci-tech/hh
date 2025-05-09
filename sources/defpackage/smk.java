package defpackage;

import android.graphics.Canvas;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchUIUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;

/* loaded from: classes9.dex */
public class smk implements ItemTouchUIUtil {
    public static final ItemTouchUIUtil e = new smk();

    smk() {
    }

    private static float egt_(RecyclerView recyclerView, View view) {
        float f = 0.0f;
        if (recyclerView != null && view != null) {
            int childCount = recyclerView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                if (childAt != view) {
                    float elevation = ViewCompat.getElevation(childAt);
                    if (elevation > f) {
                        f = elevation;
                    }
                }
            }
        }
        return f;
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onSelected(View view) {
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void clearView(View view) {
        if (view == null) {
            return;
        }
        Object tag = view.getTag(R.id.item_touch_helper_previous_elevation);
        if (tag != null && (tag instanceof Float)) {
            ViewCompat.setElevation(view, ((Float) tag).floatValue());
        }
        view.setTag(R.id.item_touch_helper_previous_elevation, null);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onDraw(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
        if (recyclerView == null || view == null) {
            return;
        }
        if (z && view.getTag(R.id.item_touch_helper_previous_elevation) == null) {
            float elevation = ViewCompat.getElevation(view);
            ViewCompat.setElevation(view, egt_(recyclerView, view) + 1.0f);
            view.setTag(R.id.item_touch_helper_previous_elevation, Float.valueOf(elevation));
        }
        view.setTranslationX(f);
        view.setTranslationY(f2);
    }
}
