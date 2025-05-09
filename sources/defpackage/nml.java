package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes6.dex */
class nml extends BitmapDrawable {

    /* renamed from: a, reason: collision with root package name */
    private float f15391a;
    private float c;
    private float d;
    private int e;

    nml(View view, float f, float f2) {
        super(view.getResources(), cAE_(view));
        nmk nmkVar = (nmk) view.getTag();
        this.c = nmkVar.cAD_().top;
        this.e = nmkVar.cAD_().left;
        this.f15391a = f;
        this.d = f2;
        setBounds(nmkVar.cAD_().left, nmkVar.cAD_().top, nmkVar.cAD_().right, nmkVar.cAD_().bottom);
    }

    void cAF_(MotionEvent motionEvent) {
        int y = (int) ((this.c - this.f15391a) + motionEvent.getY());
        int x = (int) ((this.e - this.d) + motionEvent.getX());
        setBounds(x, y, getIntrinsicWidth() + x, getIntrinsicHeight() + y);
    }

    static Bitmap cAE_(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }
}
