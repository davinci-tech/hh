package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amap.api.col.p0003sl.hz;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.BaseHoleOptions;
import com.amap.api.maps.model.CircleHoleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.PolygonHoleOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.autonavi.amap.mapcore.interfaces.IMapConfig;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.FileUtil;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hianalytics.core.transport.Utils;
import huawei.android.hwcolorpicker.HwColorPicker;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class dv {

    /* renamed from: a, reason: collision with root package name */
    private static FPoint[] f978a = {FPoint.obtain(), FPoint.obtain(), FPoint.obtain(), FPoint.obtain()};
    private static List<Float> b = new ArrayList(4);
    private static List<Float> c = new ArrayList(4);
    private static int d = 0;

    private static double a(double d2, double d3, double d4, double d5, double d6, double d7) {
        return ((d4 - d2) * (d7 - d3)) - ((d6 - d2) * (d5 - d3));
    }

    public static void a(Bitmap bitmap) {
    }

    private static boolean a(double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double d9 = d4 - d2;
        double d10 = d8 - d7;
        double d11 = d5 - d3;
        double d12 = 180.0d - d6;
        double d13 = (d9 * d10) - (d11 * d12);
        if (d13 != 0.0d) {
            double d14 = d3 - d7;
            double d15 = d2 - d6;
            double d16 = ((d12 * d14) - (d10 * d15)) / d13;
            double d17 = ((d14 * d9) - (d15 * d11)) / d13;
            if (d16 >= 0.0d && d16 <= 1.0d && d17 >= 0.0d && d17 <= 1.0d) {
                return true;
            }
        }
        return false;
    }

    public static Bitmap a(Context context, String str) {
        try {
            InputStream open = dp.a(context).open(str);
            Bitmap decodeStream = BitmapFactory.decodeStream(open);
            open.close();
            return decodeStream;
        } catch (Throwable th) {
            iv.c(th, "Util", "fromAsset");
            a(th);
            return null;
        }
    }

    public static void a(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public static String a(String str, Object obj) {
        return str + "=" + String.valueOf(obj);
    }

    public static float a(IMapConfig iMapConfig, float f, float f2) {
        boolean z;
        if (iMapConfig != null) {
            boolean isAbroadEnable = iMapConfig.isAbroadEnable();
            z = iMapConfig.getAbroadState() != 1;
            r0 = isAbroadEnable;
        } else {
            z = false;
        }
        float f3 = f >= 0.0f ? f : 0.0f;
        if (r0 && z) {
            if (f3 > 40.0f) {
                return 40.0f;
            }
            return f3;
        }
        if (iMapConfig != null && iMapConfig.isTerrainEnable()) {
            if (f3 > 80.0f) {
                return 80.0f;
            }
            return f3;
        }
        if (f <= 40.0f) {
            return f3;
        }
        float f4 = f2 <= 15.0f ? 40 : f2 <= 16.0f ? 56 : f2 <= 17.0f ? 66 : f2 <= 18.0f ? 74 : f2 <= 18.0f ? 78 : 80;
        return f3 > f4 ? f4 : f3;
    }

    public static float a(IMapConfig iMapConfig, float f) {
        if (iMapConfig != null) {
            if (f > iMapConfig.getMaxZoomLevel()) {
                return iMapConfig.getMaxZoomLevel();
            }
            return f < iMapConfig.getMinZoomLevel() ? iMapConfig.getMinZoomLevel() : f;
        }
        float f2 = 20.0f;
        if (f <= 20.0f) {
            f2 = 3.0f;
            if (f >= 3.0f) {
                return f;
            }
        }
        return f2;
    }

    public static String a(String... strArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            sb.append(str);
            if (i != strArr.length - 1) {
                sb.append(",");
            }
            i++;
        }
        return sb.toString();
    }

    public static int a(Object[] objArr) {
        return Arrays.hashCode(objArr);
    }

    public static Bitmap a(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * f), (int) (bitmap.getHeight() * f), true);
    }

    public static String a(Context context) {
        File file = new File(FileUtil.getMapBaseStorage(context), AeUtil.ROOT_DATA_PATH_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file.toString() + File.separator);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file.toString() + File.separator;
    }

    public static String b(Context context) {
        return FileUtil.getMapBaseStorage(context) + File.separator + "data" + File.separator;
    }

    public static String c(Context context) {
        String a2 = a(context);
        if (a2 == null) {
            return null;
        }
        File file = new File(a2, "VMAP2");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString() + File.separator;
    }

    public static String a(int i) {
        if (i < 1000) {
            return i + FitRunPlayAudio.OPPORTUNITY_M;
        }
        return (i / 1000) + "km";
    }

    public static boolean d(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        return (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || (state = activeNetworkInfo.getState()) == null || state == NetworkInfo.State.DISCONNECTED || state == NetworkInfo.State.DISCONNECTING) ? false : true;
    }

    public static String a(InputStream inputStream) {
        try {
            return new String(b(inputStream), "utf-8");
        } catch (Throwable th) {
            iv.c(th, "Util", "decodeAssetResData");
            th.printStackTrace();
            return null;
        }
    }

    private static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            int read = inputStream.read(bArr, 0, 2048);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a6, code lost:
    
        if (r7 != 0) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0098, code lost:
    
        if (r7 == 0) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x007e, code lost:
    
        if (r7 != 0) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0070, code lost:
    
        if (r7 == 0) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00d2, code lost:
    
        if (r0 != 0) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00d4, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00d8, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00d9, code lost:
    
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x00c4, code lost:
    
        if (r0 == 0) goto L106;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v13, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v16, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v25, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v30 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.io.File r7) {
        /*
            Method dump skipped, instructions count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.dv.a(java.io.File):java.lang.String");
    }

    public static boolean a(LatLng latLng, List<LatLng> list) {
        boolean z;
        List<LatLng> list2 = list;
        if (latLng == null || list2 == null) {
            return false;
        }
        double d2 = latLng.longitude;
        double d3 = latLng.latitude;
        double d4 = latLng.latitude;
        if (list.size() < 3) {
            return false;
        }
        if (list2.get(0).equals(list2.get(list.size() - 1))) {
            z = false;
        } else {
            list2.add(list2.get(0));
            z = true;
        }
        int i = 0;
        int i2 = 0;
        while (i < list.size() - 1) {
            try {
                double d5 = list2.get(i).longitude;
                double d6 = list2.get(i).latitude;
                int i3 = i + 1;
                double d7 = list2.get(i3).longitude;
                try {
                    double d8 = list2.get(i3).latitude;
                    double d9 = d4;
                    double d10 = d3;
                    double d11 = d2;
                    if (b(d2, d3, d5, d6, d7, d8)) {
                        if (z) {
                            list.remove(list.size() - 1);
                        }
                        return true;
                    }
                    list2 = list;
                    if (Math.abs(d8 - d6) >= 1.0E-9d) {
                        if (b(d5, d6, d11, d10, 180.0d, d9)) {
                            if (d6 > d8) {
                                i2++;
                            }
                        } else if (!b(d7, d8, d11, d10, 180.0d, d9)) {
                            if (!a(d5, d6, d7, d8, d11, d10, d9)) {
                            }
                            i2++;
                        } else if (d8 > d6) {
                            i2++;
                        }
                    }
                    i = i3;
                    d4 = d9;
                    d3 = d10;
                    d2 = d11;
                } catch (Throwable th) {
                    th = th;
                    list2 = list;
                    if (z) {
                        list2.remove(list.size() - 1);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        boolean z2 = i2 % 2 != 0;
        if (z) {
            list2.remove(list.size() - 1);
        }
        return z2;
    }

    private static boolean b(double d2, double d3, double d4, double d5, double d6, double d7) {
        return Math.abs(a(d2, d3, d4, d5, d6, d7)) < 1.0E-9d && (d2 - d4) * (d2 - d6) <= 0.0d && (d3 - d5) * (d3 - d7) <= 0.0d;
    }

    public static boolean a(BaseHoleOptions baseHoleOptions, LatLng latLng) {
        if (baseHoleOptions instanceof CircleHoleOptions) {
            CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
            LatLng center = circleHoleOptions.getCenter();
            return center != null && ((double) AMapUtils.calculateLineDistance(center, latLng)) <= circleHoleOptions.getRadius();
        }
        List<LatLng> points = ((PolygonHoleOptions) baseHoleOptions).getPoints();
        if (points == null || points.size() == 0) {
            return false;
        }
        return a(latLng, points);
    }

    public static hz a() {
        try {
            if (v.e == null) {
                v.e = new hz.a("3dmap", "9.3.0", v.c).a(new String[]{"com.amap.api.maps", "com.amap.api.mapcore", "com.autonavi.amap.mapcore", "com.autonavi.amap", "com.autonavi.ae", "com.autonavi.base", "com.autonavi.patch", "com.amap.api.3dmap.admic", "com.amap.api.trace", "com.amap.api.trace.core"}).a("9.3.0").a();
            }
            return v.e;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static void c(View view) {
        int i = 0;
        if (!(view instanceof ViewGroup)) {
            if (view instanceof TextView) {
                ((TextView) view).setHorizontallyScrolling(false);
            }
        } else {
            while (true) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (i >= viewGroup.getChildCount()) {
                    return;
                }
                c(viewGroup.getChildAt(i));
                i++;
            }
        }
    }

    public static Bitmap a(View view) {
        try {
            c(view);
            view.destroyDrawingCache();
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                return drawingCache.copy(Bitmap.Config.ARGB_8888, false);
            }
            return null;
        } catch (Throwable th) {
            iv.c(th, Utils.TAG, "getBitmapFromView");
            th.printStackTrace();
            return null;
        }
    }

    public static DPoint a(LatLng latLng) {
        double d2 = latLng.longitude / 360.0d;
        double sin = Math.sin(Math.toRadians(latLng.latitude));
        return DPoint.obtain((d2 + 0.5d) * 1.0d, (((Math.log((sin + 1.0d) / (1.0d - sin)) * 0.5d) / (-6.283185307179586d)) + 0.5d) * 1.0d);
    }

    public static boolean a(Rect rect, int i, int i2) {
        return rect.contains(i, i2);
    }

    public static Pair<Float, IPoint> a(AbstractCameraUpdateMessage abstractCameraUpdateMessage, IMapConfig iMapConfig) {
        return a(iMapConfig, Math.max(abstractCameraUpdateMessage.paddingLeft, 1), Math.max(abstractCameraUpdateMessage.paddingRight, 1), Math.max(abstractCameraUpdateMessage.paddingTop, 1), Math.max(abstractCameraUpdateMessage.paddingBottom, 1), abstractCameraUpdateMessage.bounds, abstractCameraUpdateMessage.width, abstractCameraUpdateMessage.height);
    }

    public static Pair<Float, IPoint> a(IMapConfig iMapConfig, int i, int i2, int i3, int i4, LatLngBounds latLngBounds, int i5, int i6) {
        int i7;
        float f;
        float f2;
        int i8;
        if (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null || iMapConfig == null) {
            return null;
        }
        Point latLongToPixels = VirtualEarthProjection.latLongToPixels(latLngBounds.northeast.latitude, latLngBounds.northeast.longitude, 20);
        Point latLongToPixels2 = VirtualEarthProjection.latLongToPixels(latLngBounds.southwest.latitude, latLngBounds.southwest.longitude, 20);
        int i9 = latLongToPixels.x - latLongToPixels2.x;
        int i10 = latLongToPixels2.y - latLongToPixels.y;
        int i11 = i5 - (i + i2);
        int i12 = i6 - (i3 + i4);
        if (i9 < 0 && i10 < 0) {
            return null;
        }
        int i13 = i9 <= 0 ? 1 : i9;
        int i14 = i10 <= 0 ? 1 : i10;
        Pair<Float, Boolean> b2 = b(iMapConfig, latLongToPixels.x, latLongToPixels.y, latLongToPixels2.x, latLongToPixels2.y, i11 <= 0 ? 1 : i11, i12 <= 0 ? 1 : i12);
        float floatValue = ((Float) b2.first).floatValue();
        boolean booleanValue = ((Boolean) b2.second).booleanValue();
        float a2 = a(iMapConfig.getMapZoomScale(), floatValue, i13);
        float a3 = a(iMapConfig.getMapZoomScale(), floatValue, i14);
        if (floatValue >= iMapConfig.getMaxZoomLevel()) {
            i7 = (int) (latLongToPixels2.x + ((((i2 - i) + a2) * i13) / (a2 * 2.0f)));
            i8 = latLongToPixels.y;
        } else if (booleanValue) {
            i7 = (int) (latLongToPixels2.x + ((((i5 / 2) - i) / a2) * i13));
            i8 = latLongToPixels.y;
        } else {
            i7 = (int) (latLongToPixels2.x + ((((i2 - i) + a2) * i13) / (a2 * 2.0f)));
            f = latLongToPixels.y;
            f2 = (((i6 / 2) - i3) / a3) * i14;
            return new Pair<>(Float.valueOf(floatValue), IPoint.obtain((int) (i7 + a(iMapConfig.getMapZoomScale(), floatValue, iMapConfig.getAnchorX() - (iMapConfig.getMapWidth() >> 1))), (int) (((int) (f + f2)) + a(iMapConfig.getMapZoomScale(), floatValue, iMapConfig.getAnchorY() - (iMapConfig.getMapHeight() >> 1)))));
        }
        f = i8;
        f2 = (((i4 - i3) + a3) * i14) / (a3 * 2.0f);
        return new Pair<>(Float.valueOf(floatValue), IPoint.obtain((int) (i7 + a(iMapConfig.getMapZoomScale(), floatValue, iMapConfig.getAnchorX() - (iMapConfig.getMapWidth() >> 1))), (int) (((int) (f + f2)) + a(iMapConfig.getMapZoomScale(), floatValue, iMapConfig.getAnchorY() - (iMapConfig.getMapHeight() >> 1)))));
    }

    private static double a(float f, double d2, double d3) {
        return 20.0d - (Math.log(d3 / (d2 * f)) / Math.log(2.0d));
    }

    private static float a(float f, float f2, double d2) {
        return (float) (d2 / (Math.pow(2.0d, 20.0f - f2) * f));
    }

    private static float a(float f, float f2, float f3) {
        return (float) (f3 * Math.pow(2.0d, 20.0f - f2) * f);
    }

    private static Pair<Float, Boolean> b(IMapConfig iMapConfig, int i, int i2, int i3, int i4, int i5, int i6) {
        float min;
        iMapConfig.getSZ();
        if (i == i3 && i2 == i4) {
            min = iMapConfig.getMaxZoomLevel();
        } else {
            float a2 = (float) a(iMapConfig.getMapZoomScale(), i6, Math.abs(i4 - i2));
            float a3 = (float) a(iMapConfig.getMapZoomScale(), i5, Math.abs(i3 - i));
            float min2 = Math.min(a3, a2);
            r0 = min2 == a3;
            min = Math.min(iMapConfig.getMaxZoomLevel(), Math.max(iMapConfig.getMinZoomLevel(), min2));
        }
        return new Pair<>(Float.valueOf(min), Boolean.valueOf(r0));
    }

    public static float a(IMapConfig iMapConfig, int i, int i2, int i3, int i4, int i5, int i6) {
        float sz = iMapConfig.getSZ();
        if (i == i3 || i2 == i4) {
            return sz;
        }
        return Math.max((float) a(iMapConfig.getMapZoomScale(), i5, Math.abs(i3 - i)), (float) a(iMapConfig.getMapZoomScale(), i6, Math.abs(i4 - i2)));
    }

    public static boolean a(int i, int i2) {
        if (i > 0 && i2 > 0) {
            return true;
        }
        Log.w("3dmap", "the map must have a size");
        return false;
    }

    public static float a(IGLMapState iGLMapState, int i, int i2, double d2, double d3, int i3) {
        IPoint obtain = IPoint.obtain();
        VirtualEarthProjection.latLongToPixels(d2, d3, 20, obtain);
        float a2 = a(iGLMapState, i, i2, obtain.x, obtain.y, i3);
        obtain.recycle();
        return a2;
    }

    private static float a(IGLMapState iGLMapState, int i, int i2, int i3, int i4, int i5) {
        if (iGLMapState != null) {
            return iGLMapState.calculateMapZoomer(i, i2, i3, i4, i5);
        }
        return 3.0f;
    }

    public static int[] a(int i, int i2, int i3, int i4, IMapConfig iMapConfig, IGLMapState iGLMapState, int i5, int i6) {
        int[] iArr;
        synchronized (dv.class) {
            int mapWidth = iMapConfig.getMapWidth();
            int mapHeight = iMapConfig.getMapHeight();
            iArr = new int[]{(int) Math.max(i3 + a(iMapConfig.getMapZoomScale(), iGLMapState.getMapZoomer(), iMapConfig.getAnchorX()), Math.min(i5, i - a(iMapConfig.getMapZoomScale(), iGLMapState.getMapZoomer(), mapWidth - r3))), (int) Math.max(i2 + a(iMapConfig.getMapZoomScale(), iGLMapState.getMapZoomer(), iMapConfig.getAnchorY()), Math.min(i6, i4 - a(iMapConfig.getMapZoomScale(), iGLMapState.getMapZoomer(), mapHeight - r4)))};
        }
        return iArr;
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, i, i, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0028, code lost:
    
        r2.setPixel(r5, r6, r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(byte[] r7, int r8, int r9, boolean r10) {
        /*
            int r0 = r7.length     // Catch: java.lang.Throwable -> L40
            r1 = 0
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeByteArray(r7, r1, r0)     // Catch: java.lang.Throwable -> L40
            android.graphics.Bitmap$Config r2 = r0.getConfig()     // Catch: java.lang.Throwable -> L40
            r3 = 1
            android.graphics.Bitmap r2 = r0.copy(r2, r3)     // Catch: java.lang.Throwable -> L40
            int r3 = r0.getWidth()     // Catch: java.lang.Throwable -> L40
            int r4 = r0.getHeight()     // Catch: java.lang.Throwable -> L40
            r5 = r1
        L18:
            if (r5 >= r3) goto L31
            r6 = r1
        L1b:
            if (r6 >= r4) goto L2e
            if (r5 == 0) goto L26
            if (r6 != 0) goto L22
            goto L26
        L22:
            r2.setPixel(r5, r6, r8)     // Catch: java.lang.Throwable -> L40
            goto L2b
        L26:
            if (r10 != 0) goto L2b
            r2.setPixel(r5, r6, r9)     // Catch: java.lang.Throwable -> L40
        L2b:
            int r6 = r6 + 1
            goto L1b
        L2e:
            int r5 = r5 + 1
            goto L18
        L31:
            byte[] r8 = b(r2)     // Catch: java.lang.Throwable -> L40
            if (r8 != 0) goto L38
            r8 = r7
        L38:
            a(r2)     // Catch: java.lang.Throwable -> L40
            a(r0)     // Catch: java.lang.Throwable -> L40
            r7 = r8
            goto L44
        L40:
            r8 = move-exception
            r8.printStackTrace()
        L44:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.dv.a(byte[], int, int, boolean):byte[]");
    }

    private static byte[] b(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable unused) {
            byteArrayOutputStream = null;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return byteArray;
        } catch (Throwable unused2) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
            return null;
        }
    }

    public static boolean a(List<BaseHoleOptions> list, PolygonHoleOptions polygonHoleOptions) {
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            BaseHoleOptions baseHoleOptions = list.get(i);
            if (baseHoleOptions instanceof PolygonHoleOptions) {
                z = a(((PolygonHoleOptions) baseHoleOptions).getPoints(), polygonHoleOptions.getPoints());
                if (z) {
                    return true;
                }
            } else if (baseHoleOptions instanceof CircleHoleOptions) {
                z = b(polygonHoleOptions.getPoints(), (CircleHoleOptions) baseHoleOptions);
                if (z) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return z;
    }

    public static boolean a(List<BaseHoleOptions> list, CircleHoleOptions circleHoleOptions) {
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            BaseHoleOptions baseHoleOptions = list.get(i);
            if (baseHoleOptions instanceof PolygonHoleOptions) {
                z = b(((PolygonHoleOptions) baseHoleOptions).getPoints(), circleHoleOptions);
                if (z) {
                    return true;
                }
            } else if ((baseHoleOptions instanceof CircleHoleOptions) && (z = a(circleHoleOptions, (CircleHoleOptions) baseHoleOptions))) {
                return true;
            }
        }
        return z;
    }

    private static boolean a(CircleHoleOptions circleHoleOptions, CircleHoleOptions circleHoleOptions2) {
        try {
            return ((double) AMapUtils.calculateLineDistance(circleHoleOptions2.getCenter(), circleHoleOptions.getCenter())) < circleHoleOptions.getRadius() + circleHoleOptions2.getRadius();
        } catch (Throwable th) {
            iv.c(th, "Util", "isPolygon2CircleIntersect");
            th.printStackTrace();
            return false;
        }
    }

    private static boolean b(List<LatLng> list, CircleHoleOptions circleHoleOptions) {
        int i;
        try {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < list.size(); i2++) {
                arrayList.add(list.get(i2));
            }
            arrayList.add(list.get(0));
            ArrayList arrayList2 = new ArrayList();
            int i3 = 0;
            while (i3 < arrayList.size() && (i = i3 + 1) < arrayList.size()) {
                if (circleHoleOptions.getRadius() < AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), (LatLng) arrayList.get(i3)) && circleHoleOptions.getRadius() < AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), (LatLng) arrayList.get(i))) {
                    arrayList2.clear();
                    arrayList2.add(arrayList.get(i3));
                    arrayList2.add(arrayList.get(i));
                    if (circleHoleOptions.getRadius() >= AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), (LatLng) SpatialRelationUtil.calShortestDistancePoint(arrayList2, circleHoleOptions.getCenter()).second)) {
                        return true;
                    }
                    i3 = i;
                }
                return true;
            }
        } catch (Throwable th) {
            iv.c(th, "Util", "isPolygon2CircleIntersect");
            th.printStackTrace();
        }
        return false;
    }

    private static boolean a(List<LatLng> list, List<LatLng> list2) {
        for (int i = 0; i < list2.size(); i++) {
            try {
                if (a(list2.get(i), list)) {
                    return true;
                }
            } catch (Throwable th) {
                iv.c(th, "Util", "isPolygon2PolygonIntersect");
                th.printStackTrace();
                return false;
            }
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (a(list.get(i2), list2)) {
                return true;
            }
        }
        return b(list, list2);
    }

    private static boolean b(List<LatLng> list, List<LatLng> list2) {
        int i;
        int i2;
        int i3 = 0;
        while (i3 < list.size() && (i = i3 + 1) < list.size()) {
            try {
                int i4 = 0;
                while (i4 < list2.size() && (i2 = i4 + 1) < list2.size()) {
                    boolean a2 = dq.a(list.get(i3), list.get(i), list2.get(i4), list2.get(i2));
                    if (a2) {
                        return a2;
                    }
                    i4 = i2;
                }
                i3 = i;
            } catch (Throwable th) {
                iv.c(th, "Util", "isSegmentsIntersect");
                th.printStackTrace();
            }
        }
        return false;
    }

    public static boolean e(Context context) {
        File file = new File(b(context));
        if (file.exists()) {
            return FileUtil.deleteFile(file);
        }
        return true;
    }

    public static float a(DPoint dPoint, DPoint dPoint2) {
        if (dPoint == null || dPoint2 == null) {
            return 0.0f;
        }
        double d2 = dPoint.x;
        double d3 = dPoint2.x;
        return (float) ((Math.atan2(dPoint2.y - dPoint.y, d3 - d2) / 3.141592653589793d) * 180.0d);
    }

    public static boolean b(List<LatLng> list, PolygonHoleOptions polygonHoleOptions) {
        boolean z = false;
        if (list != null && polygonHoleOptions != null) {
            try {
                List<LatLng> points = polygonHoleOptions.getPoints();
                for (int i = 0; i < points.size() && (z = a(points.get(i), list)); i++) {
                }
            } catch (Throwable th) {
                iv.c(th, "PolygonDelegateImp", "isPolygonInPolygon");
                th.printStackTrace();
            }
        }
        return z;
    }

    public static boolean a(List<LatLng> list, List<BaseHoleOptions> list2, CircleHoleOptions circleHoleOptions) {
        try {
            if (b(list, circleHoleOptions)) {
                return false;
            }
            return a(list, list2, circleHoleOptions.getCenter());
        } catch (Throwable th) {
            iv.c(th, "PolygonDelegateImp", "isCircleInPolygon");
            th.printStackTrace();
            return false;
        }
    }

    private static boolean a(List<LatLng> list, List<BaseHoleOptions> list2, LatLng latLng) throws RemoteException {
        if (latLng == null) {
            return false;
        }
        if (list2 != null) {
            try {
                if (list2.size() > 0) {
                    Iterator<BaseHoleOptions> it = list2.iterator();
                    while (it.hasNext()) {
                        if (a(it.next(), latLng)) {
                            return false;
                        }
                    }
                }
            } catch (Throwable th) {
                iv.c(th, "PolygonDelegateImp", "contains");
                th.printStackTrace();
                return false;
            }
        }
        return a(latLng, list);
    }

    public static boolean a(double d2, LatLng latLng, List<BaseHoleOptions> list, PolygonHoleOptions polygonHoleOptions) {
        boolean z = true;
        try {
            List<LatLng> points = polygonHoleOptions.getPoints();
            for (int i = 0; i < points.size() && (z = a(d2, latLng, list, points.get(i))); i++) {
            }
        } catch (Throwable th) {
            iv.c(th, "CircleDelegateImp", "isPolygonInCircle");
            th.printStackTrace();
        }
        return z;
    }

    public static boolean a(double d2, LatLng latLng, CircleHoleOptions circleHoleOptions) {
        try {
        } catch (Throwable th) {
            iv.c(th, "CircleDelegateImp", "isCircleInCircle");
            th.printStackTrace();
        }
        return ((double) AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), latLng)) <= d2 - circleHoleOptions.getRadius();
    }

    private static boolean a(double d2, LatLng latLng, List<BaseHoleOptions> list, LatLng latLng2) throws RemoteException {
        if (list != null && list.size() > 0) {
            Iterator<BaseHoleOptions> it = list.iterator();
            while (it.hasNext()) {
                if (a(it.next(), latLng2)) {
                    return false;
                }
            }
        }
        return d2 >= ((double) AMapUtils.calculateLineDistance(latLng, latLng2));
    }

    public static void a(Throwable th) {
        try {
            if (MapsInitializer.getExceptionLogger() != null) {
                MapsInitializer.getExceptionLogger().onException(th);
            }
        } catch (Throwable unused) {
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            Uri parse = Uri.parse(str);
            if (parse.getAuthority() != null && parse.getAuthority().startsWith("dualstack-")) {
                return str;
            }
            if (parse.getAuthority() != null && parse.getAuthority().startsWith("restsdk.amap.com")) {
                return parse.buildUpon().authority("dualstack-arestapi.amap.com").build().toString();
            }
            return parse.buildUpon().authority("dualstack-" + parse.getAuthority()).build().toString();
        } catch (Throwable unused) {
            return str;
        }
    }

    public static Bitmap a(int[] iArr, int i, int i2) {
        return a(iArr, i, i2, false);
    }

    public static Bitmap a(int[] iArr, int i, int i2, boolean z) {
        try {
            int[] iArr2 = new int[iArr.length];
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    int i5 = (i3 * i) + i4;
                    int i6 = iArr[i5];
                    int i7 = ((i6 >> 16) & 255) | ((-16711936) & i6) | ((i6 << 16) & HwColorPicker.MASK_RESULT_STATE);
                    if (z) {
                        iArr2[(((i2 - i3) - 1) * i) + i4] = i7;
                    } else {
                        iArr2[i5] = i7;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr2, 0, i, 0, 0, i, i2);
            return createBitmap;
        } catch (Throwable th) {
            iv.c(th, "Util", "rgbaToArgb");
            th.printStackTrace();
            return null;
        }
    }

    public static String b(View view) {
        StringBuilder sb = new StringBuilder();
        if (view != null) {
            try {
                if (view instanceof TextView) {
                    sb = new StringBuilder(((TextView) view).getText().toString());
                }
                if (view instanceof ViewGroup) {
                    int childCount = ((ViewGroup) view).getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        String b2 = b(((ViewGroup) view).getChildAt(i));
                        if (!TextUtils.isEmpty(b2)) {
                            sb.append("--");
                            sb.append(b2);
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static int b() {
        int i;
        synchronized (dv.class) {
            int i2 = d + 1;
            d = i2;
            if (i2 == Integer.MAX_VALUE) {
                d = 0;
            }
            i = d;
        }
        return i;
    }
}
