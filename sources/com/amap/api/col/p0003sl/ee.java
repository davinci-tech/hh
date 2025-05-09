package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.maps.model.LatLng;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* loaded from: classes2.dex */
public final class ee extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    Bitmap f995a;
    Bitmap b;
    Bitmap c;
    Bitmap d;
    Bitmap e;
    Bitmap f;
    ImageView g;
    IAMapDelegate h;
    boolean i;

    public final void a() {
        try {
            removeAllViews();
            Bitmap bitmap = this.f995a;
            if (bitmap != null) {
                dv.a(bitmap);
            }
            Bitmap bitmap2 = this.b;
            if (bitmap2 != null) {
                dv.a(bitmap2);
            }
            if (this.b != null) {
                dv.a(this.c);
            }
            this.f995a = null;
            this.b = null;
            this.c = null;
            Bitmap bitmap3 = this.d;
            if (bitmap3 != null) {
                dv.a(bitmap3);
                this.d = null;
            }
            Bitmap bitmap4 = this.e;
            if (bitmap4 != null) {
                dv.a(bitmap4);
                this.e = null;
            }
            Bitmap bitmap5 = this.f;
            if (bitmap5 != null) {
                dv.a(bitmap5);
                this.f = null;
            }
        } catch (Throwable th) {
            iv.c(th, "LocationView", "destroy");
            th.printStackTrace();
        }
    }

    public ee(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.i = false;
        this.h = iAMapDelegate;
        try {
            Bitmap a2 = dv.a(context, "location_selected.png");
            this.d = a2;
            this.f995a = dv.a(a2, v.f1366a);
            Bitmap a3 = dv.a(context, "location_pressed.png");
            this.e = a3;
            this.b = dv.a(a3, v.f1366a);
            Bitmap a4 = dv.a(context, "location_unselected.png");
            this.f = a4;
            this.c = dv.a(a4, v.f1366a);
            ImageView imageView = new ImageView(context);
            this.g = imageView;
            imageView.setImageBitmap(this.f995a);
            this.g.setClickable(true);
            this.g.setPadding(0, 20, 20, 0);
            this.g.setOnTouchListener(new View.OnTouchListener() { // from class: com.amap.api.col.3sl.ee.1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!ee.this.i) {
                        return false;
                    }
                    if (motionEvent.getAction() == 0) {
                        ee.this.g.setImageBitmap(ee.this.b);
                    } else if (motionEvent.getAction() == 1) {
                        try {
                            ee.this.g.setImageBitmap(ee.this.f995a);
                            ee.this.h.setMyLocationEnabled(true);
                            Location myLocation = ee.this.h.getMyLocation();
                            if (myLocation == null) {
                                return false;
                            }
                            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                            ee.this.h.showMyLocationOverlay(myLocation);
                            ee.this.h.moveCamera(aj.a(latLng, ee.this.h.getZoomLevel()));
                        } catch (Throwable th) {
                            iv.c(th, "LocationView", "onTouch");
                            th.printStackTrace();
                        }
                    }
                    return false;
                }
            });
            addView(this.g);
        } catch (Throwable th) {
            iv.c(th, "LocationView", "create");
            th.printStackTrace();
        }
    }

    public final void a(boolean z) {
        this.i = z;
        try {
            if (z) {
                this.g.setImageBitmap(this.f995a);
            } else {
                this.g.setImageBitmap(this.c);
            }
            this.g.invalidate();
        } catch (Throwable th) {
            iv.c(th, "LocationView", "showSelect");
            th.printStackTrace();
        }
    }
}
