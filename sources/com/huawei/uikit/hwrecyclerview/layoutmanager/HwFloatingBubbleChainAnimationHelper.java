package com.huawei.uikit.hwrecyclerview.layoutmanager;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationHelper;
import com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView;

/* loaded from: classes9.dex */
public class HwFloatingBubbleChainAnimationHelper extends HwChainAnimationHelper {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10688a;
    private int b;
    private HwRecyclerView c;
    private boolean d;
    private HwFloatingBubblesLayoutManager e;
    private boolean f;

    private void d(int i) {
        HwFloatingBubblesLayoutManager hwFloatingBubblesLayoutManager = this.e;
        if (hwFloatingBubblesLayoutManager != null) {
            boolean e = e();
            if (e()) {
                i = 0;
            }
            hwFloatingBubblesLayoutManager.d(e, i);
            this.e.e(false);
            this.e.b();
        }
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationHelper
    public boolean a() {
        return !e();
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationHelper
    public void b() {
        super.b();
        this.d = true;
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationHelper
    public View eeQ_(float f, float f2) {
        HwFloatingBubblesLayoutManager hwFloatingBubblesLayoutManager = this.e;
        if (hwFloatingBubblesLayoutManager == null || hwFloatingBubblesLayoutManager.getChildCount() <= 0) {
            return null;
        }
        int childCount = this.e.getChildCount() - 1;
        int i = 0;
        double d = 0.0d;
        for (int i2 = childCount; i2 >= 0; i2--) {
            View childAt = this.e.getChildAt(i2);
            if (childAt != null) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.LayoutParams) {
                    float translationX = childAt.getTranslationX();
                    float translationY = childAt.getTranslationY();
                    float scaleX = childAt.getScaleX();
                    float decoratedLeft = (this.e.getDecoratedLeft(childAt) + translationX) - r7.leftMargin;
                    float decoratedRight = this.e.getDecoratedRight(childAt);
                    float f3 = ((RecyclerView.LayoutParams) layoutParams).rightMargin;
                    float decoratedTop = (this.e.getDecoratedTop(childAt) + translationY) - r7.topMargin;
                    double sqrt = Math.sqrt(Math.pow(f - (decoratedLeft + ((((decoratedRight + translationX) + f3) - decoratedLeft) / 2.0f)), 2.0d) + Math.pow(f2 - (decoratedTop + ((((this.e.getDecoratedBottom(childAt) + translationY) + r7.bottomMargin) - decoratedTop) / 2.0f)), 2.0d)) / scaleX;
                    if (i2 != childCount) {
                        if (sqrt < d) {
                            i = i2;
                        }
                    }
                    d = sqrt;
                }
            }
        }
        return this.e.getChildAt(i);
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationHelper, android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        c();
        if (this.e == null) {
            return false;
        }
        if ((!e() || !d()) && this.c.f()) {
            i();
        }
        if (this.d && !this.f10688a) {
            d(0);
            return true;
        }
        if (!this.f) {
            d(0);
            this.f = false;
            return true;
        }
        d(this.c.getScrollState() == 0 ? 0 : -this.b);
        this.f10688a = !this.d;
        this.f = false;
        return true;
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationHelper, androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        this.d = false;
        this.b = i2;
        this.f = true;
    }
}
