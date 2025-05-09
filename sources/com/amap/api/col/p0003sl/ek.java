package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.col.p0003sl.ef;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* loaded from: classes2.dex */
final class ek extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f1009a;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;
    private Bitmap g;
    private Bitmap h;
    private Bitmap i;
    private Bitmap j;
    private Bitmap k;
    private Bitmap l;
    private ImageView m;
    private ImageView n;
    private IAMapDelegate o;

    public final void a() {
        try {
            removeAllViews();
            dv.a(this.f1009a);
            dv.a(this.b);
            dv.a(this.c);
            dv.a(this.d);
            dv.a(this.e);
            dv.a(this.f);
            this.f1009a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            Bitmap bitmap = this.g;
            if (bitmap != null) {
                dv.a(bitmap);
                this.g = null;
            }
            Bitmap bitmap2 = this.h;
            if (bitmap2 != null) {
                dv.a(bitmap2);
                this.h = null;
            }
            Bitmap bitmap3 = this.i;
            if (bitmap3 != null) {
                dv.a(bitmap3);
                this.i = null;
            }
            Bitmap bitmap4 = this.j;
            if (bitmap4 != null) {
                dv.a(bitmap4);
                this.g = null;
            }
            Bitmap bitmap5 = this.k;
            if (bitmap5 != null) {
                dv.a(bitmap5);
                this.k = null;
            }
            Bitmap bitmap6 = this.l;
            if (bitmap6 != null) {
                dv.a(bitmap6);
                this.l = null;
            }
            this.m = null;
            this.n = null;
        } catch (Throwable th) {
            iv.c(th, "ZoomControllerView", "destory");
            th.printStackTrace();
        }
    }

    public ek(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.o = iAMapDelegate;
        try {
            Bitmap a2 = dv.a(context, "zoomin_selected.png");
            this.g = a2;
            this.f1009a = dv.a(a2, v.f1366a);
            Bitmap a3 = dv.a(context, "zoomin_unselected.png");
            this.h = a3;
            this.b = dv.a(a3, v.f1366a);
            Bitmap a4 = dv.a(context, "zoomout_selected.png");
            this.i = a4;
            this.c = dv.a(a4, v.f1366a);
            Bitmap a5 = dv.a(context, "zoomout_unselected.png");
            this.j = a5;
            this.d = dv.a(a5, v.f1366a);
            Bitmap a6 = dv.a(context, "zoomin_pressed.png");
            this.k = a6;
            this.e = dv.a(a6, v.f1366a);
            Bitmap a7 = dv.a(context, "zoomout_pressed.png");
            this.l = a7;
            this.f = dv.a(a7, v.f1366a);
            ImageView imageView = new ImageView(context);
            this.m = imageView;
            imageView.setImageBitmap(this.f1009a);
            this.m.setClickable(true);
            ImageView imageView2 = new ImageView(context);
            this.n = imageView2;
            imageView2.setImageBitmap(this.c);
            this.n.setClickable(true);
            this.m.setOnTouchListener(new View.OnTouchListener() { // from class: com.amap.api.col.3sl.ek.1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    try {
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    if (ek.this.o.getZoomLevel() < ek.this.o.getMaxZoomLevel() && ek.this.o.isMaploaded()) {
                        if (motionEvent.getAction() == 0) {
                            ek.this.m.setImageBitmap(ek.this.e);
                        } else if (motionEvent.getAction() == 1) {
                            ek.this.m.setImageBitmap(ek.this.f1009a);
                            try {
                                ek.this.o.animateCamera(aj.a());
                            } catch (RemoteException e) {
                                iv.c(e, "ZoomControllerView", "zoomin ontouch");
                                e.printStackTrace();
                            }
                        }
                        return false;
                    }
                    return false;
                }
            });
            this.n.setOnTouchListener(new View.OnTouchListener() { // from class: com.amap.api.col.3sl.ek.2
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    try {
                    } catch (Throwable th) {
                        iv.c(th, "ZoomControllerView", "zoomout ontouch");
                        th.printStackTrace();
                    }
                    if (ek.this.o.getZoomLevel() > ek.this.o.getMinZoomLevel() && ek.this.o.isMaploaded()) {
                        if (motionEvent.getAction() == 0) {
                            ek.this.n.setImageBitmap(ek.this.f);
                        } else if (motionEvent.getAction() == 1) {
                            ek.this.n.setImageBitmap(ek.this.c);
                            ek.this.o.animateCamera(aj.b());
                        }
                        return false;
                    }
                    return false;
                }
            });
            this.m.setPadding(0, 0, 20, -2);
            this.n.setPadding(0, 0, 20, 20);
            setOrientation(1);
            addView(this.m);
            addView(this.n);
        } catch (Throwable th) {
            iv.c(th, "ZoomControllerView", "create");
            th.printStackTrace();
        }
    }

    public final void a(float f) {
        try {
            if (f < this.o.getMaxZoomLevel() && f > this.o.getMinZoomLevel()) {
                this.m.setImageBitmap(this.f1009a);
                this.n.setImageBitmap(this.c);
            } else if (f == this.o.getMinZoomLevel()) {
                this.n.setImageBitmap(this.d);
                this.m.setImageBitmap(this.f1009a);
            } else if (f == this.o.getMaxZoomLevel()) {
                this.m.setImageBitmap(this.b);
                this.n.setImageBitmap(this.c);
            }
        } catch (Throwable th) {
            iv.c(th, "ZoomControllerView", "setZoomBitmap");
            th.printStackTrace();
        }
    }

    public final void a(int i) {
        try {
            ef.a aVar = (ef.a) getLayoutParams();
            if (i == 1) {
                aVar.e = 16;
            } else if (i == 2) {
                aVar.e = 80;
            }
            setLayoutParams(aVar);
        } catch (Throwable th) {
            iv.c(th, "ZoomControllerView", "setZoomPosition");
            th.printStackTrace();
        }
    }

    public final void a(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}
