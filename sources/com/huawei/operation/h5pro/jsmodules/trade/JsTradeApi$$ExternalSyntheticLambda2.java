package com.huawei.operation.h5pro.jsmodules.trade;

import android.view.View;
import android.view.ViewTreeObserver;

/* loaded from: classes9.dex */
public final /* synthetic */ class JsTradeApi$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ View f$0;
    public final /* synthetic */ ViewTreeObserver.OnDrawListener f$1;

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.getViewTreeObserver().addOnDrawListener(this.f$1);
    }

    public /* synthetic */ JsTradeApi$$ExternalSyntheticLambda2(View view, ViewTreeObserver.OnDrawListener onDrawListener) {
        this.f$0 = view;
        this.f$1 = onDrawListener;
    }
}
