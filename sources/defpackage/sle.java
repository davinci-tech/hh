package defpackage;

import android.view.ViewGroup;
import android.view.ViewParent;

/* loaded from: classes7.dex */
public class sle {
    public static void ebA_(ViewParent viewParent) {
        if (viewParent != null && (viewParent instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
        }
    }
}
