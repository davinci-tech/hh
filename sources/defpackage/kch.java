package defpackage;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.datatype.GpsParameter;
import com.huawei.datatype.GpsStruct;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.List;

/* loaded from: classes5.dex */
public class kch {
    public static boolean d(Context context) {
        boolean z;
        boolean z2;
        if (context == null) {
            LogUtil.h("HwGpsUtil", "context is null");
            return false;
        }
        Object systemService = context.getSystemService("location");
        if (systemService instanceof LocationManager) {
            LocationManager locationManager = (LocationManager) systemService;
            z2 = locationManager.isProviderEnabled(GeocodeSearch.GPS);
            z = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
            LogUtil.a("HwGpsUtil", "isGPSLocationEnable:", Boolean.valueOf(z2), "isNetWorkLocationEnable:", Boolean.valueOf(z));
        } else {
            z = false;
            z2 = false;
        }
        return z2 || z;
    }

    public static void bNd_(int i, long j, Location location, float f, GpsStruct gpsStruct) {
        if (location == null || gpsStruct == null) {
            LogUtil.h("HwGpsUtil", "location or gpsStruct is null");
            return;
        }
        switch (i) {
            case 0:
                gpsStruct.setGpsSpeed((int) (location.getSpeed() * 10.0f));
                return;
            case 1:
                gpsStruct.setGpsDistance((int) (f * 10.0f));
                return;
            case 2:
                gpsStruct.setGpsAltitude((int) location.getAltitude());
                return;
            case 3:
                gpsStruct.setGpsTotalDistance(j);
                return;
            case 4:
                gpsStruct.setGpsLongitude(location.getLongitude());
                gpsStruct.setGpsLatitude(location.getLatitude());
                return;
            case 5:
                Location bQV_ = ktr.bQV_(BaseApplication.getContext(), location);
                gpsStruct.setGpsMarsLongitude(bQV_.getLongitude());
                gpsStruct.setGpsMarsLatitude(bQV_.getLatitude());
                return;
            case 6:
                gpsStruct.setGpsDirection(location.getBearing());
                return;
            case 7:
                gpsStruct.setGpsPrecision(location.getAccuracy());
                return;
            case 8:
                int i2 = 0;
                try {
                    int i3 = location.getExtras().getInt("gpsQuality");
                    LogUtil.a("HwGpsUtil", "gpsQuality: ", Integer.valueOf(i3));
                    i2 = i3;
                } catch (Exception unused) {
                    LogUtil.b("HwGpsUtil", "gpsQuality Exception.");
                }
                gpsStruct.setGpsQuality(i2);
                break;
        }
        LogUtil.h("HwGpsUtil", "type does not match");
    }

    public static void a(byte[] bArr, DeviceInfo deviceInfo) {
        if (bArr == null) {
            LogUtil.h("HwGpsUtil", "dataInfos is null");
            return;
        }
        blt.d("HwGpsUtil", bArr, "getResult(): ");
        if (deviceInfo != null) {
            c(juu.b(deviceInfo.getProductType()));
        }
        if (cwl.b(bArr)) {
            return;
        }
        String d = cvx.d(bArr);
        if (d.length() < 4) {
            LogUtil.b("HwGpsUtil", "Receive command error");
            return;
        }
        try {
            cwe a2 = new cwl().a(d.substring(4, d.length()));
            List<cwe> a3 = a2.a();
            List<cwd> e = a2.e();
            byte b = bArr[1];
            if (b != 1) {
                if (b == 2) {
                    d(e);
                } else if (b == 3) {
                    c(e);
                } else if (b == 4) {
                    b(e);
                } else if (b == 8) {
                    b(bArr, deviceInfo);
                } else {
                    LogUtil.h("HwGpsUtil", "type does not match");
                }
            } else if (!e(e)) {
                a(a3);
            }
        } catch (cwg unused) {
            LogUtil.b("HwGpsUtil", "Receive command error");
            sqo.i("Receive command error");
        }
    }

    private static void b(byte[] bArr, DeviceInfo deviceInfo) {
        kce.b().d(deviceInfo);
        if (bArr.length != 5) {
            blt.d("HwGpsUtil", bArr, "arKitSwitch() status: ");
            return;
        }
        byte b = bArr[4];
        LogUtil.a("HwGpsUtil", "arKitSwitch() status:", Byte.valueOf(b));
        kcg.b().e(b);
    }

    private static void a(List<cwe> list) {
        int i;
        if (list == null || list.size() == 0) {
            LogUtil.h("HwGpsUtil", "processGetGpsLocationParameter tlvFatherList is null");
            return;
        }
        GpsParameter gpsParameter = new GpsParameter();
        for (cwd cwdVar : list.get(0).e()) {
            try {
                i = Integer.parseInt(cwdVar.e(), 16);
            } catch (NumberFormatException unused) {
                LogUtil.b("HwGpsUtil", "tlv.getTag() NumberFormatException");
                i = 0;
            }
            if (i == 2) {
                gpsParameter.setGpsInfoBitmap(CommonUtil.w(cwdVar.c()));
            } else if (i == 3) {
                gpsParameter.setGpsParaFormat(CommonUtil.w(cwdVar.c()));
            } else if (i == 4) {
                gpsParameter.setGpsParaElementNum(CommonUtil.w(cwdVar.c()));
            } else if (i == 5) {
                gpsParameter.setGpsThreshold(CommonUtil.w(cwdVar.c()));
            } else {
                LogUtil.h("HwGpsUtil", "domainTag does not match");
            }
        }
        synchronized (kcc.e()) {
            if (kcc.e().size() != 0) {
                kcc.e().get(0).d(100000, gpsParameter);
                kcc.e().remove(0);
            }
        }
    }

    private static boolean e(List<cwd> list) {
        if (list.size() == 0) {
            LogUtil.h("HwGpsUtil", "tlvs is empty");
            return false;
        }
        if (d(list.get(0).e(), 127, 16) != 127) {
            return false;
        }
        synchronized (kcc.e()) {
            if (kcc.e().size() != 0) {
                try {
                    kcc.e().get(0).d(Integer.parseInt(list.get(0).c(), 16), null);
                    kcc.e().remove(0);
                } catch (NumberFormatException unused) {
                    LogUtil.b("HwGpsUtil", "processGetGpsLocationParameter NumberFormatException");
                    sqo.i("processGetGpsLocationParameter NumberFormatException");
                }
            }
        }
        return true;
    }

    private static int d(String str, int i, int i2) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str, i2);
        } catch (NumberFormatException unused) {
            LogUtil.b("HwGpsUtil", "parseInt errorcode fail");
            return i;
        }
    }

    private static void d(List<cwd> list) {
        boolean z;
        if (list.size() <= 0) {
            z = false;
        } else {
            if (CommonUtil.w(list.get(0).e()) == 127) {
                return;
            }
            loop0: while (true) {
                z = false;
                for (cwd cwdVar : list) {
                    if (CommonUtil.w(cwdVar.e()) == 1) {
                        if (CommonUtil.w(cwdVar.c()) == 1) {
                            z = true;
                        }
                    } else {
                        LogUtil.h("HwGpsUtil", "processNotifiactionGpsParaEnable default");
                    }
                }
            }
        }
        synchronized (kcc.d()) {
            if (kcc.d().size() != 0) {
                kcc.d().get(0).d(100000, Boolean.valueOf(z));
            }
        }
    }

    private static void c(List<cwd> list) {
        int i = 0;
        for (cwd cwdVar : list) {
            if (CommonUtil.w(cwdVar.e()) == 127) {
                i = CommonUtil.w(cwdVar.c());
            } else {
                LogUtil.h("HwGpsUtil", "processSetGpsParameter default");
            }
        }
        synchronized (kcc.b()) {
            if (kcc.b().size() != 0 && kcc.b().get(0) != null) {
                kcc.b().get(0).d(i, null);
                kcc.b().remove(0);
            }
        }
    }

    private static void b(List<cwd> list) {
        int i = 0;
        for (cwd cwdVar : list) {
            try {
                if (Integer.parseInt(cwdVar.e(), 16) == 127) {
                    i = Integer.parseInt(cwdVar.c(), 16);
                } else {
                    LogUtil.h("HwGpsUtil", "processSetSupportGpsParameter default");
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("HwGpsUtil", "processSetSupportGpsParameter NumberFormatException");
            }
        }
        synchronized (kcc.a()) {
            if (kcc.a().size() != 0) {
                kcc.a().get(0).d(i, null);
                kcc.a().remove(0);
            }
        }
    }

    public static int d() {
        return SharedPreferenceManager.a("connect_gps_module_id", "key_connect_gps_device_type", 0);
    }

    public static void c(int i) {
        LogUtil.a("HwGpsUtil", "saveDeviceTypeToStorage isDeviceBand: ", Integer.valueOf(i));
        SharedPreferenceManager.b("connect_gps_module_id", "key_connect_gps_device_type", i);
    }
}
