package com.huawei.hwcommonmodel.accessibility;

import android.graphics.Rect;
import android.os.Bundle;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* loaded from: classes5.dex */
public abstract class AbstractTouchNode {
    private String description;
    private Rect rect;

    public abstract boolean onPerformActionForVirtualView(int i, Bundle bundle);

    public abstract void onPopulateNodeForVirtualView(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    public AbstractTouchNode() {
        this.rect = new Rect();
        this.description = "";
    }

    public AbstractTouchNode(Rect rect, String str) {
        this.rect = rect;
        this.description = str;
    }

    public Rect getRect() {
        return this.rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }
}
