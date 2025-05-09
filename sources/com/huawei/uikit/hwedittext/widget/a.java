package com.huawei.uikit.hwedittext.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/* loaded from: classes9.dex */
class a extends TextView {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ HwCounterTextLayout f10670a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    a(HwCounterTextLayout hwCounterTextLayout, Context context) {
        super(context);
        this.f10670a = hwCounterTextLayout;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onVisibilityChanged(View view, int i) {
        HwShapeMode hwShapeMode;
        HwShapeMode hwShapeMode2;
        super.onVisibilityChanged(view, i);
        hwShapeMode = this.f10670a.b;
        if (hwShapeMode != HwShapeMode.BUBBLE) {
            hwShapeMode2 = this.f10670a.b;
            if (hwShapeMode2 != HwShapeMode.WHITE) {
                return;
            }
        }
        this.f10670a.c();
    }
}
