package defpackage;

import android.graphics.Outline;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewOutlineProvider;

/* loaded from: classes4.dex */
public class gfm extends ViewOutlineProvider {
    private float c;

    public gfm(float f) {
        this.c = f;
    }

    @Override // android.view.ViewOutlineProvider
    public void getOutline(View view, Outline outline) {
        if (view == null || outline == null) {
            return;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        outline.setRoundRect(new Rect(0, 0, rect.right - rect.left, rect.bottom - rect.top), this.c);
    }
}
