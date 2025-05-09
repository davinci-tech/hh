package com.huawei.hwcommonmodel.accessibility;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import defpackage.jcm;
import health.compact.a.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class ChartTouchHelper extends ExploreByTouchHelper {

    /* renamed from: a, reason: collision with root package name */
    private jcm f6267a;
    private final String e;

    public ChartTouchHelper(View view, Class<? extends AbstractTouchNode> cls) {
        super(view);
        this.e = "ChartTouchHelper";
        this.f6267a = new jcm(cls);
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void getVisibleVirtualViews(List<Integer> list) {
        for (int i = 0; i < this.f6267a.c(); i++) {
            list.add(Integer.valueOf(i));
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public int getVirtualViewAt(float f, float f2) {
        return this.f6267a.b((int) f, (int) f2);
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (i == -1) {
            ReleaseLogUtil.a("ChartTouchHelper", "virtualViewId is invalid_index");
        } else {
            this.f6267a.a(i).onPopulateNodeForVirtualView(accessibilityNodeInfoCompat);
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
        if (i == -1) {
            ReleaseLogUtil.a("ChartTouchHelper", "virtualViewId is invalid_index");
            return false;
        }
        return this.f6267a.a(i).onPerformActionForVirtualView(i2, bundle);
    }

    public jcm e() {
        return this.f6267a;
    }
}
