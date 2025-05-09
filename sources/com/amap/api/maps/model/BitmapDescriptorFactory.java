package com.amap.api.maps.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.FrameLayout;
import com.amap.api.col.p0003sl.di;
import com.amap.api.col.p0003sl.dv;
import com.amap.api.col.p0003sl.dw;
import com.amap.api.col.p0003sl.dx;
import com.amap.api.col.p0003sl.z;
import java.io.FileInputStream;
import java.io.InputStream;

/* loaded from: classes2.dex */
public final class BitmapDescriptorFactory {
    public static final float HUE_AZURE = 210.0f;
    public static final float HUE_BLUE = 240.0f;
    public static final float HUE_CYAN = 180.0f;
    public static final float HUE_GREEN = 120.0f;
    public static final float HUE_MAGENTA = 300.0f;
    public static final float HUE_ORANGE = 30.0f;
    public static final float HUE_RED = 0.0f;
    public static final float HUE_ROSE = 330.0f;
    public static final float HUE_VIOLET = 270.0f;
    public static final float HUE_YELLOW = 60.0f;
    private static final String ICON_ID_PREFIX = "com.amap.api.icon_";

    public static BitmapDescriptor fromResource(int i) {
        try {
            Context context = getContext();
            if (context != null) {
                return fromBitmap(BitmapFactory.decodeStream(context.getResources().openRawResource(i)));
            }
            return null;
        } catch (Throwable th) {
            dv.a(th);
            dx.b(dw.f, "read bitmap from res failed " + th.getMessage());
            return null;
        }
    }

    public static BitmapDescriptor fromView(View view) {
        try {
            Context context = getContext();
            if (context == null) {
                return null;
            }
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.addView(view);
            frameLayout.setDrawingCacheEnabled(true);
            return fromBitmap(dv.a(frameLayout));
        } catch (Throwable th) {
            dv.a(th);
            dx.b(dw.f, "read bitmap from view failed " + th.getMessage());
            return null;
        }
    }

    public static BitmapDescriptor fromPath(String str) {
        try {
            return fromBitmap(BitmapFactory.decodeFile(str));
        } catch (Throwable th) {
            dv.a(th);
            dx.b(dw.f, "read bitmap from disk failed " + th.getMessage());
            return null;
        }
    }

    public static BitmapDescriptor fromAsset(String str) {
        try {
            Context context = getContext();
            if (context != null) {
                return fromBitmap(dv.a(context, str));
            }
            InputStream resourceAsStream = BitmapDescriptorFactory.class.getResourceAsStream("/assets/".concat(String.valueOf(str)));
            Bitmap decodeStream = BitmapFactory.decodeStream(resourceAsStream);
            resourceAsStream.close();
            return fromBitmap(decodeStream);
        } catch (Throwable th) {
            dv.a(th);
            dx.b(dw.f, "read bitmap from assets failed " + th.getMessage());
            return null;
        }
    }

    public static BitmapDescriptor fromFile(String str) {
        try {
            Context context = getContext();
            if (context == null) {
                return null;
            }
            FileInputStream openFileInput = context.openFileInput(str);
            Bitmap decodeStream = BitmapFactory.decodeStream(openFileInput);
            openFileInput.close();
            BitmapDescriptor fromBitmap = fromBitmap(decodeStream);
            dv.a(decodeStream);
            return fromBitmap;
        } catch (Throwable th) {
            dv.a(th);
            dx.b(dw.f, "read bitmap from disk failed " + th.getMessage());
            return null;
        }
    }

    public static BitmapDescriptor defaultMarker() {
        try {
            return fromAsset(di.a.marker_default.name() + ".png");
        } catch (Throwable th) {
            dv.a(th);
            return null;
        }
    }

    public static BitmapDescriptor defaultMarker(float f) {
        try {
            float f2 = (((int) (f + 15.0f)) / 30) * 30;
            if (f2 > 330.0f) {
                f2 = 330.0f;
            } else if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            return fromAsset((f2 == 0.0f ? "RED" : f2 == 30.0f ? "ORANGE" : f2 == 60.0f ? "YELLOW" : f2 == 120.0f ? "GREEN" : f2 == 180.0f ? "CYAN" : f2 == 210.0f ? "AZURE" : f2 == 240.0f ? "BLUE" : f2 == 270.0f ? "VIOLET" : f2 == 300.0f ? "MAGENTA" : f2 == 330.0f ? "ROSE" : "").concat(".png"));
        } catch (Throwable th) {
            dv.a(th);
            return null;
        }
    }

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            return new BitmapDescriptor(bitmap, ICON_ID_PREFIX + dv.b());
        } catch (Throwable th) {
            dv.a(th);
            dx.b(dw.f, "read bitmap from bitmap failed " + th.getMessage());
            return null;
        }
    }

    public static Context getContext() {
        return z.f1381a;
    }
}
