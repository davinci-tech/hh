package defpackage;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.huawei.hwcommonmodel.accessibility.AbstractTouchNode;

/* loaded from: classes5.dex */
public class jco extends AbstractTouchNode {
    @Override // com.huawei.hwcommonmodel.accessibility.AbstractTouchNode
    public boolean onPerformActionForVirtualView(int i, Bundle bundle) {
        return false;
    }

    @Override // com.huawei.hwcommonmodel.accessibility.AbstractTouchNode
    public void onPopulateNodeForVirtualView(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Rect rect = getRect() == null ? new Rect() : getRect();
        if (rect.isEmpty() || TextUtils.isEmpty(getDescription())) {
            accessibilityNodeInfoCompat.setFocusable(false);
        }
        int abs = Math.abs(rect.left - rect.right);
        int abs2 = Math.abs(rect.top - rect.bottom);
        rect.right = Math.abs(rect.left - rect.right) < 15 ? rect.right + (15 - abs) : rect.right;
        rect.bottom = Math.abs(rect.top - rect.bottom) < 15 ? rect.bottom + (15 - abs2) : rect.bottom;
        accessibilityNodeInfoCompat.setBoundsInParent(rect);
        accessibilityNodeInfoCompat.setContentDescription(getDescription() == null ? "" : getDescription());
    }
}
