package com.huawei.hms.maps.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.maps.model.internal.IBitmapDescriptorDelegate;
import com.huawei.hms.maps.utils.LogM;
import com.huawei.hms.maps.utils.mab;
import com.huawei.hms.maps.utils.mac;
import com.huawei.hms.maps.utils.mae;
import com.huawei.hms.maps.utils.mag;
import com.huawei.hms.maps.utils.mah;

/* loaded from: classes4.dex */
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

    /* renamed from: a, reason: collision with root package name */
    private static IBitmapDescriptorDelegate f4979a;
    private static Context b;

    public static void setIBitmapDescriptorDelegate(IBitmapDescriptorDelegate iBitmapDescriptorDelegate) {
        f4979a = iBitmapDescriptorDelegate;
    }

    public static void setContext(Context context) {
        b = context == null ? null : context.getApplicationContext();
    }

    public static BitmapDescriptor fromResource(int i) {
        Bitmap bitmap;
        try {
            bitmap = new mah(i).a(b);
        } catch (NullPointerException unused) {
            LogM.e("BitmapDescriptorFactory", "the sContext is null");
            bitmap = null;
        }
        return new BitmapDescriptor(ObjectWrapper.wrap(bitmap));
    }

    public static BitmapDescriptor fromPath(String str) {
        Bitmap bitmap;
        try {
            bitmap = new mag(str).a(b);
        } catch (NullPointerException unused) {
            LogM.e("BitmapDescriptorFactory", "the sContext is null");
            bitmap = null;
        }
        return new BitmapDescriptor(ObjectWrapper.wrap(bitmap));
    }

    public static BitmapDescriptor fromFile(String str) {
        Bitmap bitmap;
        try {
            bitmap = new mae(str).a(b);
        } catch (NullPointerException unused) {
            LogM.e("BitmapDescriptorFactory", "the sContext is null");
            bitmap = null;
        }
        return new BitmapDescriptor(ObjectWrapper.wrap(bitmap));
    }

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        Bitmap bitmap2;
        try {
            bitmap2 = new mac(bitmap).a(b);
        } catch (NullPointerException unused) {
            LogM.e("BitmapDescriptorFactory", "the sContext is null");
            bitmap2 = null;
        }
        return new BitmapDescriptor(ObjectWrapper.wrap(bitmap2));
    }

    public static BitmapDescriptor fromAsset(String str) {
        Bitmap bitmap;
        try {
            bitmap = new mab(str).a(b);
        } catch (NullPointerException unused) {
            LogM.e("BitmapDescriptorFactory", "the sContext is null");
            bitmap = null;
        }
        return new BitmapDescriptor(ObjectWrapper.wrap(bitmap));
    }

    public static BitmapDescriptor defaultMarker(float f) {
        IBitmapDescriptorDelegate iBitmapDescriptorDelegate = f4979a;
        BitmapDescriptor bitmapDescriptor = null;
        if (iBitmapDescriptorDelegate == null) {
            LogM.w("BitmapDescriptorFactory", "defaultMarker hue null == IBitmapDescriptorDelegate");
            return null;
        }
        try {
            IObjectWrapper defaultMarkerWithHue = iBitmapDescriptorDelegate.defaultMarkerWithHue(f);
            if (defaultMarkerWithHue != null) {
                bitmapDescriptor = new BitmapDescriptor(defaultMarkerWithHue);
            } else {
                LogM.w("BitmapDescriptorFactory", "defaultMarker hue null == objectWrapper");
            }
        } catch (RemoteException unused) {
            LogM.e("BitmapDescriptorFactory", "defaultMarkerWithHue error");
        }
        return bitmapDescriptor;
    }

    public static BitmapDescriptor defaultMarker() {
        IBitmapDescriptorDelegate iBitmapDescriptorDelegate = f4979a;
        BitmapDescriptor bitmapDescriptor = null;
        if (iBitmapDescriptorDelegate == null) {
            LogM.w("BitmapDescriptorFactory", "defaultMarker null == IBitmapDescriptorDelegate");
            return null;
        }
        try {
            IObjectWrapper defaultMarker = iBitmapDescriptorDelegate.defaultMarker();
            if (defaultMarker != null) {
                bitmapDescriptor = new BitmapDescriptor(defaultMarker);
            } else {
                LogM.w("BitmapDescriptorFactory", "defaultMarker null == objectWrapper");
            }
        } catch (RemoteException unused) {
            LogM.e("BitmapDescriptorFactory", "defaultMarker error");
        }
        return bitmapDescriptor;
    }
}
