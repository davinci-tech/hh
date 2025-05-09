package com.huawei.openalliance.ad.views.viewpager;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* loaded from: classes9.dex */
class b extends View.AccessibilityDelegate {

    /* renamed from: a, reason: collision with root package name */
    private e f8167a;

    @Override // android.view.View.AccessibilityDelegate
    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        e eVar;
        int i2;
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        if (i != 4096) {
            if (i != 8192 || !this.f8167a.canScrollHorizontally(-1)) {
                return false;
            }
            eVar = this.f8167a;
            i2 = eVar.f8169a - 1;
        } else {
            if (!this.f8167a.canScrollHorizontally(1)) {
                return false;
            }
            eVar = this.f8167a;
            i2 = eVar.f8169a + 1;
        }
        eVar.setCurrentItem(i2);
        return true;
    }

    @Override // android.view.View.AccessibilityDelegate
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(e.class.getName());
        accessibilityNodeInfo.setScrollable(a());
        if (this.f8167a.canScrollHorizontally(1)) {
            accessibilityNodeInfo.addAction(4096);
        }
        if (this.f8167a.canScrollHorizontally(-1)) {
            accessibilityNodeInfo.addAction(8192);
        }
    }

    @Override // android.view.View.AccessibilityDelegate
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName(e.class.getName());
        accessibilityEvent.setScrollable(a());
        if (accessibilityEvent.getEventType() != 4096 || this.f8167a.b == null) {
            return;
        }
        accessibilityEvent.setItemCount(this.f8167a.b.a());
        accessibilityEvent.setFromIndex(this.f8167a.f8169a);
        accessibilityEvent.setToIndex(this.f8167a.f8169a);
    }

    private boolean a() {
        return this.f8167a.b != null && this.f8167a.b.a() > 1;
    }

    public b(e eVar) {
        this.f8167a = eVar;
    }
}
