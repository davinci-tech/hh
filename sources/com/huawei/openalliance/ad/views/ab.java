package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dd;

/* loaded from: classes5.dex */
public class ab extends ProgressButton {
    @Override // com.huawei.openalliance.ad.views.ProgressButton
    protected int getTextStart() {
        if (dd.h()) {
            return this.k;
        }
        int width = ((getWidth() - this.f7999a.width()) - this.n) - ao.a(getContext(), 8.0f);
        if (width < this.j) {
            width = this.j;
        }
        ho.b("ProgressButtonNew", "safeTextStart: %s", Integer.valueOf(width));
        return width;
    }

    @Override // com.huawei.openalliance.ad.views.ProgressButton
    protected void a(Canvas canvas) {
        int width;
        int height;
        synchronized (this.h) {
            if (this.c != null && this.c.length() > 0) {
                String intern = this.c.toString().intern();
                if (this.m) {
                    width = (getWidth() / 2) - this.f7999a.centerX();
                    if (this.l && width < this.i) {
                        width = getTextStart();
                    }
                    height = getHeight() / 2;
                } else {
                    width = (getWidth() - this.f7999a.width()) - ao.a(getContext(), 1.0f);
                    if (this.l && width < this.i) {
                        width = getTextStart();
                    }
                    height = getHeight() / 2;
                }
                canvas.drawText((CharSequence) intern, 0, intern.length(), width, height - this.f7999a.centerY(), this.b);
                a(getWidth(), getHeight());
            }
        }
    }

    @Override // com.huawei.openalliance.ad.views.ProgressButton
    protected void a(int i, int i2) {
        int width;
        int width2;
        synchronized (this.h) {
            if (this.m) {
                width = (getWidth() / 2) - this.f7999a.centerX();
                if (this.l && width < this.i) {
                    width = getTextStart();
                }
                width2 = this.f7999a.width() + width;
            } else {
                width = (getWidth() - this.f7999a.width()) - ao.a(getContext(), 1.0f);
                if (this.l && width < this.i) {
                    width = getTextStart();
                }
                width2 = getWidth();
            }
            if (this.g != null) {
                this.g.setBounds(width, 0, width2, i2);
            }
        }
    }

    public ab(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
