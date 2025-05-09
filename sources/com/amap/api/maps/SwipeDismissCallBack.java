package com.amap.api.maps;

import android.view.View;
import com.amap.api.maps.SwipeDismissTouchListener;

/* loaded from: classes8.dex */
public class SwipeDismissCallBack implements SwipeDismissTouchListener.DismissCallbacks {

    /* renamed from: a, reason: collision with root package name */
    SwipeDismissView f1416a;

    @Override // com.amap.api.maps.SwipeDismissTouchListener.DismissCallbacks
    public boolean canDismiss(Object obj) {
        return true;
    }

    public SwipeDismissCallBack(SwipeDismissView swipeDismissView) {
        this.f1416a = swipeDismissView;
    }

    @Override // com.amap.api.maps.SwipeDismissTouchListener.DismissCallbacks
    public void onDismiss(View view, Object obj) {
        if (this.f1416a.onDismissCallback != null) {
            this.f1416a.onDismissCallback.onDismiss();
        }
    }

    @Override // com.amap.api.maps.SwipeDismissTouchListener.DismissCallbacks
    public void onNotifySwipe() {
        if (this.f1416a.onDismissCallback != null) {
            this.f1416a.onDismissCallback.onNotifySwipe();
        }
    }
}
