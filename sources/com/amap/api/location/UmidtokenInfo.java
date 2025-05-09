package com.amap.api.location;

import android.content.Context;
import android.os.Handler;
import com.amap.api.col.p0003sl.hr;
import com.autonavi.aps.amapapi.utils.b;
import com.huawei.operation.OpAnalyticsConstants;

/* loaded from: classes2.dex */
public class UmidtokenInfo {
    private static AMapLocationClient d;

    /* renamed from: a, reason: collision with root package name */
    static Handler f1401a = new Handler();
    static String b = null;
    private static long e = OpAnalyticsConstants.H5_LOADING_DELAY;
    static boolean c = true;

    public static String getUmidtoken() {
        return b;
    }

    public static void setLocAble(boolean z) {
        c = z;
    }

    public static void setUmidtoken(Context context, String str) {
        synchronized (UmidtokenInfo.class) {
            try {
                b = str;
                hr.a(str);
                if (d == null && c) {
                    a aVar = new a();
                    d = new AMapLocationClient(context);
                    AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
                    aMapLocationClientOption.setOnceLocation(true);
                    aMapLocationClientOption.setNeedAddress(false);
                    d.setLocationOption(aMapLocationClientOption);
                    d.setLocationListener(aVar);
                    d.startLocation();
                    f1401a.postDelayed(new Runnable() { // from class: com.amap.api.location.UmidtokenInfo.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                if (UmidtokenInfo.d != null) {
                                    UmidtokenInfo.d.onDestroy();
                                }
                            } catch (Throwable th) {
                                b.a(th, "UmidListener", "postDelayed");
                            }
                        }
                    }, OpAnalyticsConstants.H5_LOADING_DELAY);
                }
            } catch (Throwable th) {
                b.a(th, "UmidListener", "setUmidtoken");
            }
        }
    }

    /* loaded from: classes8.dex */
    static final class a implements AMapLocationListener {
        a() {
        }

        @Override // com.amap.api.location.AMapLocationListener
        public final void onLocationChanged(AMapLocation aMapLocation) {
            try {
                if (UmidtokenInfo.d != null) {
                    UmidtokenInfo.f1401a.removeCallbacksAndMessages(null);
                    UmidtokenInfo.d.onDestroy();
                }
            } catch (Throwable th) {
                b.a(th, "UmidListener", "onLocationChanged");
            }
        }
    }
}
