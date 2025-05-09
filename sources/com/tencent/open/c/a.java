package com.tencent.open.c;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.RelativeLayout;

/* loaded from: classes10.dex */
public class a extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11349a = "com.tencent.open.c.a";
    private Rect b;
    private boolean c;
    private InterfaceC0291a d;

    /* renamed from: com.tencent.open.c.a$a, reason: collision with other inner class name */
    public interface InterfaceC0291a {
        void a();

        void a(int i);
    }

    public a(Context context) {
        super(context);
        this.b = null;
        this.c = false;
        this.d = null;
        this.b = new Rect();
    }

    public void a(InterfaceC0291a interfaceC0291a) {
        this.d = interfaceC0291a;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        Activity activity = (Activity) getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.b);
        int i3 = this.b.top;
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        InterfaceC0291a interfaceC0291a = this.d;
        if (interfaceC0291a != null && size != 0) {
            if ((height - i3) - size > 100) {
                interfaceC0291a.a((Math.abs(this.b.height()) - getPaddingBottom()) - getPaddingTop());
            } else {
                interfaceC0291a.a();
            }
        }
        super.onMeasure(i, i2);
    }
}
