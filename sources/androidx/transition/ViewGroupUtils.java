package androidx.transition;

import android.view.ViewGroup;

/* loaded from: classes2.dex */
class ViewGroupUtils {
    static ViewGroupOverlayImpl getOverlay(ViewGroup viewGroup) {
        return new ViewGroupOverlayApi18(viewGroup);
    }

    static void suppressLayout(ViewGroup viewGroup, boolean z) {
        ViewGroupUtilsApi18.suppressLayout(viewGroup, z);
    }

    private ViewGroupUtils() {
    }
}
