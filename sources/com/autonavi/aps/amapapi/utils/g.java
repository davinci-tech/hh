package com.autonavi.aps.amapapi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import com.amap.api.col.p0003sl.hm;
import com.amap.api.col.p0003sl.iv;
import com.amap.api.col.p0003sl.kg;
import com.amap.api.col.p0003sl.kh;
import com.amap.api.col.p0003sl.ki;
import com.amap.api.col.p0003sl.kj;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.hms.network.embedded.c0;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public SparseArray<Long> f1650a = new SparseArray<>();
    public int b = -1;
    public long c = 0;
    String[] d = {"ol", "cl", "gl", "ha", "bs", Constants.RELATION_COL_B_KEY};
    public int e = -1;
    public long f = -1;
    private static List<ki> i = new ArrayList();
    private static JSONArray j = null;
    static AMapLocation g = null;
    static boolean h = false;

    private static boolean a(AMapLocation aMapLocation) {
        return i.a(aMapLocation) ? !b.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()) : "http://abroad.apilocate.amap.com/mobile/binary".equals(b.c);
    }

    public static void a(Context context, AMapLocation aMapLocation, com.autonavi.aps.amapapi.a aVar) {
        if (aMapLocation == null) {
            return;
        }
        try {
            if (GeocodeSearch.GPS.equalsIgnoreCase(aMapLocation.getProvider())) {
                return;
            }
            int i2 = 1;
            if (aMapLocation.getLocationType() == 1) {
                return;
            }
            String str = a(aMapLocation) ? "abroad" : "domestic";
            String str2 = "net";
            if (aMapLocation.getErrorCode() != 0) {
                int errorCode = aMapLocation.getErrorCode();
                if (errorCode != 4 && errorCode != 5 && errorCode != 6 && errorCode != 11) {
                    str2 = "cache";
                }
                i2 = 0;
            } else {
                int locationType = aMapLocation.getLocationType();
                if (locationType != 5 && locationType != 6) {
                    str2 = "cache";
                }
            }
            a(context, "O016", str2, str, i2, aMapLocation.getErrorCode(), aVar);
        } catch (Throwable th) {
            b.a(th, "ReportUtil", "reportBatting");
        }
    }

    public static void a(Context context, long j2, boolean z) {
        if (context != null) {
            try {
                if (a.a()) {
                    a(context, j2, z, "O015");
                }
            } catch (Throwable th) {
                b.a(th, "ReportUtil", "reportGPSLocUseTime");
            }
        }
    }

    public static void b(Context context, long j2, boolean z) {
        if (context != null) {
            try {
                if (a.a()) {
                    a(context, j2, z, "O024");
                }
            } catch (Throwable th) {
                b.a(th, "ReportUtil", "reportCoarseLocUseTime");
            }
        }
    }

    private static void a(Context context, long j2, boolean z, String str) {
        a(context, str, !z ? "abroad" : "domestic", Long.valueOf(j2).intValue());
    }

    private static void a(Context context, String str, String str2, String str3, int i2, int i3, com.autonavi.aps.amapapi.a aVar) {
        if (context != null) {
            try {
                if (a.a()) {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put("param_string_first", str2);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        jSONObject.put("param_string_second", str3);
                    }
                    if (i2 != Integer.MAX_VALUE) {
                        jSONObject.put("param_int_first", i2);
                    }
                    if (i3 != Integer.MAX_VALUE) {
                        jSONObject.put("param_int_second", i3);
                    }
                    if (aVar != null) {
                        if (!TextUtils.isEmpty(aVar.d())) {
                            jSONObject.put("dns", aVar.d());
                        }
                        if (!TextUtils.isEmpty(aVar.e())) {
                            jSONObject.put("domain", aVar.e());
                        }
                        if (!TextUtils.isEmpty(aVar.f())) {
                            jSONObject.put("type", aVar.f());
                        }
                        if (!TextUtils.isEmpty(aVar.g())) {
                            jSONObject.put("reason", aVar.g());
                        }
                        if (!TextUtils.isEmpty(aVar.c())) {
                            jSONObject.put(c0.h, aVar.c());
                        }
                        if (!TextUtils.isEmpty(aVar.b())) {
                            jSONObject.put("stack", aVar.b());
                        }
                        if (aVar.h() > 0) {
                            jSONObject.put("ctime", String.valueOf(aVar.h()));
                        }
                        if (aVar.a() > 0) {
                            jSONObject.put("ntime", String.valueOf(aVar.a()));
                        }
                    }
                    a(context, str, jSONObject);
                }
            } catch (Throwable th) {
                b.a(th, "ReportUtil", "applyStatisticsEx");
            }
        }
    }

    private static void a(Context context, String str, String str2, int i2) {
        if (context != null) {
            try {
                if (a.a()) {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put("param_string_first", str2);
                    }
                    if (!TextUtils.isEmpty(null)) {
                        jSONObject.put("param_string_second", (Object) null);
                    }
                    if (i2 != Integer.MAX_VALUE) {
                        jSONObject.put("param_int_first", i2);
                    }
                    a(context, str, jSONObject);
                }
            } catch (Throwable th) {
                b.a(th, "ReportUtil", "applyStatisticsEx");
            }
        }
    }

    public static void a(Context context, String str, JSONObject jSONObject) {
        synchronized (g.class) {
            if (context != null) {
                try {
                    if (a.a()) {
                        ki kiVar = new ki(context, "loc", "6.1.0", str);
                        if (jSONObject != null) {
                            kiVar.a(jSONObject.toString());
                        }
                        i.add(kiVar);
                        if (i.size() >= 30) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.addAll(i);
                            kj.b(arrayList, context);
                            i.clear();
                        }
                    }
                } catch (Throwable th) {
                    b.a(th, "ReportUtil", "applyStatistics");
                }
            }
        }
    }

    public static void a(Context context) {
        synchronized (g.class) {
            if (context != null) {
                try {
                    if (a.a()) {
                        List<ki> list = i;
                        if (list != null && list.size() > 0) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.addAll(i);
                            kj.b(arrayList, context);
                            i.clear();
                        }
                        f(context);
                    }
                } catch (Throwable th) {
                    b.a(th, "ReportUtil", "destroy");
                }
            }
        }
    }

    public static void a(String str, String str2) {
        try {
            iv.b(b.c(), str2, str);
        } catch (Throwable th) {
            b.a(th, "ReportUtil", "reportLog");
        }
    }

    public final void a(Context context, int i2) {
        try {
            int i3 = this.b;
            if (i3 == i2) {
                return;
            }
            if (i3 != -1 && i3 != i2) {
                long b = i.b();
                long j2 = this.c;
                this.f1650a.append(this.b, Long.valueOf((b - j2) + this.f1650a.get(this.b, 0L).longValue()));
            }
            this.c = i.b() - h.a(context, "pref1", this.d[i2], 0L);
            this.b = i2;
        } catch (Throwable th) {
            b.a(th, "ReportUtil", "setLocationType");
        }
    }

    public final void b(Context context) {
        try {
            long b = i.b();
            long j2 = this.c;
            int i2 = this.b;
            if (i2 != -1) {
                this.f1650a.append(this.b, Long.valueOf((b - j2) + this.f1650a.get(i2, 0L).longValue()));
            }
            long b2 = i.b();
            long j3 = this.f;
            int i3 = this.e;
            if (i3 != -1) {
                this.f1650a.append(this.e, Long.valueOf((b2 - j3) + this.f1650a.get(i3, 0L).longValue()));
            }
            SharedPreferences.Editor a2 = h.a(context, "pref1");
            for (int i4 = 0; i4 < this.d.length; i4++) {
                long longValue = this.f1650a.get(i4, 0L).longValue();
                if (longValue > 0 && longValue > h.a(context, "pref1", this.d[i4], 0L)) {
                    h.a(a2, this.d[i4], longValue);
                }
            }
            h.a(a2);
        } catch (Throwable th) {
            b.a(th, "ReportUtil", "saveLocationTypeAndMode");
        }
    }

    /* renamed from: com.autonavi.aps.amapapi.utils.g$1, reason: invalid class name */
    static final /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1651a;

        static {
            int[] iArr = new int[AMapLocationClientOption.AMapLocationMode.values().length];
            f1651a = iArr;
            try {
                iArr[AMapLocationClientOption.AMapLocationMode.Battery_Saving.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1651a[AMapLocationClientOption.AMapLocationMode.Device_Sensors.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1651a[AMapLocationClientOption.AMapLocationMode.Hight_Accuracy.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public final void a(Context context, AMapLocationClientOption aMapLocationClientOption) {
        int i2;
        try {
            int i3 = AnonymousClass1.f1651a[aMapLocationClientOption.getLocationMode().ordinal()];
            if (i3 == 1) {
                i2 = 4;
            } else if (i3 != 2) {
                i2 = 3;
                if (i3 != 3) {
                    i2 = -1;
                }
            } else {
                i2 = 5;
            }
            int i4 = this.e;
            if (i4 == i2) {
                return;
            }
            if (i4 != -1 && i4 != i2) {
                this.f1650a.append(this.e, Long.valueOf((i.b() - this.f) + this.f1650a.get(this.e, 0L).longValue()));
            }
            this.f = i.b() - h.a(context, "pref1", this.d[i2], 0L);
            this.e = i2;
        } catch (Throwable th) {
            b.a(th, "ReportUtil", "setLocationMode");
        }
    }

    public final int c(Context context) {
        try {
            long a2 = h.a(context, "pref1", this.d[2], 0L);
            long a3 = h.a(context, "pref1", this.d[0], 0L);
            long a4 = h.a(context, "pref1", this.d[1], 0L);
            if (a2 == 0 && a3 == 0 && a4 == 0) {
                return -1;
            }
            long j2 = a3 - a2;
            long j3 = a4 - a2;
            return a2 > j2 ? a2 > j3 ? 2 : 1 : j2 > j3 ? 0 : 1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public final int d(Context context) {
        try {
            long a2 = h.a(context, "pref1", this.d[3], 0L);
            long a3 = h.a(context, "pref1", this.d[4], 0L);
            long a4 = h.a(context, "pref1", this.d[5], 0L);
            if (a2 == 0 && a3 == 0 && a4 == 0) {
                return -1;
            }
            return a2 > a3 ? a2 > a4 ? 3 : 5 : a3 > a4 ? 4 : 5;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public final void e(Context context) {
        try {
            SharedPreferences.Editor a2 = h.a(context, "pref1");
            int i2 = 0;
            while (true) {
                String[] strArr = this.d;
                if (i2 < strArr.length) {
                    h.a(a2, strArr[i2], 0L);
                    i2++;
                } else {
                    h.a(a2);
                    return;
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context, int i2, int i3, long j2, long j3) {
        if (i2 == -1 || i3 == -1) {
            return;
        }
        try {
            a(context, "O012", i2, i3, j2, j3);
        } catch (Throwable th) {
            b.a(th, "ReportUtil", "reportServiceAliveTime");
        }
    }

    private static void a(Context context, String str, int i2, int i3, long j2, long j3) {
        if (context != null) {
            try {
                if (a.a()) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("param_int_first", i2);
                    jSONObject.put("param_int_second", i3);
                    jSONObject.put("param_long_first", j2);
                    jSONObject.put("param_long_second", j3);
                    a(context, str, jSONObject);
                }
            } catch (Throwable th) {
                b.a(th, "ReportUtil", "applyStatisticsEx");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0034 A[Catch: all -> 0x00f0, TRY_LEAVE, TryCatch #0 {all -> 0x00f0, blocks: (B:4:0x0003, B:9:0x000b, B:21:0x0034, B:31:0x0047, B:33:0x004b, B:34:0x0052, B:36:0x008b, B:38:0x0098, B:39:0x00d9, B:41:0x00eb, B:43:0x0091), top: B:3:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r8, com.amap.api.location.AMapLocation r9) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.utils.g.a(android.content.Context, com.amap.api.location.AMapLocation):void");
    }

    private static void f(Context context) {
        try {
            JSONArray jSONArray = j;
            if (jSONArray == null || jSONArray.length() <= 0) {
                return;
            }
            kh.a(new kg(context, b.c(), j.toString()), context);
            j = null;
        } catch (Throwable th) {
            b.a(th, "ReportUtil", "writeOfflineLocLog");
        }
    }

    public static void a(String str, int i2) {
        a(str, String.valueOf(i2), a(i2));
    }

    public static void a(String str, String str2, String str3) {
        try {
            iv.a(b.c(), "/mobile/binary", str3, str, str2);
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, Throwable th) {
        try {
            if (th instanceof hm) {
                iv.a(b.c(), str, (hm) th);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        try {
            if (g == null) {
                if (!i.a(aMapLocation)) {
                    g = aMapLocation2;
                    return;
                }
                g = aMapLocation.m77clone();
            }
            if (i.a(g) && i.a(aMapLocation2)) {
                AMapLocation m77clone = aMapLocation2.m77clone();
                if (g.getLocationType() != 1 && g.getLocationType() != 9 && !GeocodeSearch.GPS.equalsIgnoreCase(g.getProvider()) && g.getLocationType() != 7 && m77clone.getLocationType() != 1 && m77clone.getLocationType() != 9 && !GeocodeSearch.GPS.equalsIgnoreCase(m77clone.getProvider()) && m77clone.getLocationType() != 7) {
                    long abs = Math.abs(m77clone.getTime() - g.getTime()) / 1000;
                    if (abs <= 0) {
                        abs = 1;
                    }
                    if (abs <= 1800) {
                        float a2 = i.a(g, m77clone);
                        float f = a2 / abs;
                        if (a2 > 30000.0f && f > 1000.0f) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(g.getLatitude());
                            sb.append(",");
                            sb.append(g.getLongitude());
                            sb.append(",");
                            sb.append(g.getAccuracy());
                            sb.append(",");
                            sb.append(g.getLocationType());
                            sb.append(",");
                            if (aMapLocation.getTime() != 0) {
                                sb.append(i.a(g.getTime(), "yyyyMMdd_HH:mm:ss:SS"));
                            } else {
                                sb.append(g.getTime());
                            }
                            sb.append("#");
                            sb.append(m77clone.getLatitude());
                            sb.append(",");
                            sb.append(m77clone.getLongitude());
                            sb.append(",");
                            sb.append(m77clone.getAccuracy());
                            sb.append(",");
                            sb.append(m77clone.getLocationType());
                            sb.append(",");
                            if (m77clone.getTime() != 0) {
                                sb.append(i.a(m77clone.getTime(), "yyyyMMdd_HH:mm:ss:SS"));
                            } else {
                                sb.append(m77clone.getTime());
                            }
                            a("bigshiftstatistics", sb.toString());
                            sb.delete(0, sb.length());
                        }
                    }
                }
                g = m77clone;
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(long j2, long j3) {
        String str;
        try {
            if (h) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("gpsTime:").append(i.a(j2, "yyyy-MM-dd HH:mm:ss.SSS")).append(",sysTime:");
            stringBuffer.append(i.a(j3, "yyyy-MM-dd HH:mm:ss.SSS")).append(",serverTime:");
            long t = a.t();
            if (0 == t) {
                str = "0";
            } else {
                str = i.a(t, "yyyy-MM-dd HH:mm:ss.SSS");
            }
            stringBuffer.append(str);
            a("checkgpstime", stringBuffer.toString());
            if (0 != t && Math.abs(j2 - t) < 31536000000L) {
                stringBuffer.append(", correctError");
                a("checkgpstimeerror", stringBuffer.toString());
            }
            stringBuffer.delete(0, stringBuffer.length());
            h = true;
        } catch (Throwable unused) {
        }
    }

    private static String a(int i2) {
        if (i2 == 2011) {
            return "ContextIsNull";
        }
        if (i2 == 2031) {
            return "CreateApsReqException";
        }
        if (i2 == 2041) {
            return "ResponseResultIsNull";
        }
        if (i2 == 2081) {
            return "LocalLocException";
        }
        if (i2 == 2091) {
            return "InitException";
        }
        if (i2 == 2111) {
            return "ErrorCgiInfo";
        }
        if (i2 == 2121) {
            return "NotLocPermission";
        }
        if (i2 == 2141) {
            return "NoEnoughStatellites";
        }
        if (i2 == 2021) {
            return "OnlyMainWifi";
        }
        if (i2 == 2022) {
            return "OnlyOneWifiButNotMain";
        }
        if (i2 == 2061) {
            return "ServerRetypeError";
        }
        if (i2 == 2062) {
            return "ServerLocFail";
        }
        switch (i2) {
            case 2051:
                return "NeedLoginNetWork\t";
            case 2052:
                return "MaybeIntercepted";
            case 2053:
                return "DecryptResponseException";
            case 2054:
                return "ParserDataException";
            default:
                switch (i2) {
                    case 2101:
                        return "BindAPSServiceException";
                    case 2102:
                        return "AuthClientScodeFail";
                    case 2103:
                        return "NotConfigAPSService";
                    default:
                        switch (i2) {
                            case 2131:
                                return "NoCgiOAndWifiInfo";
                            case 2132:
                                return "AirPlaneModeAndWifiOff";
                            case 2133:
                                return "NoCgiAndWifiOff";
                            default:
                                switch (i2) {
                                    case 2151:
                                        return "MaybeMockNetLoc";
                                    case 2152:
                                        return "MaybeMockGPSLoc";
                                    case 2153:
                                        return "UNSUPPORT_COARSE_LBSLOC";
                                    case 2154:
                                        return "UNSUPPORT_CONTINUE_LOC";
                                    default:
                                        return "";
                                }
                        }
                }
        }
    }
}
