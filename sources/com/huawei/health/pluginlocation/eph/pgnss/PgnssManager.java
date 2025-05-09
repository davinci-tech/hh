package com.huawei.health.pluginlocation.eph.pgnss;

import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.network.embedded.b6;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.y;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.exz;
import defpackage.eyc;
import defpackage.eyf;
import defpackage.eyi;
import defpackage.eyk;
import defpackage.eyl;
import defpackage.eym;
import defpackage.eyn;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class PgnssManager {

    /* renamed from: a, reason: collision with root package name */
    private static double f2945a;
    private static double c;
    private static boolean e;
    private static volatile PgnssManager f;
    private static final String[] d = {"HW_PGNSS_GPS", "HW_PGNSS_GLONASS", "HW_PGNSS_GALILEO", "HW_PGNSS_BDS", "HW_PGNSS_QZS", "HW_PGNSS_EXTRA"};
    private static final Object b = new Object();

    private native boolean decodeEphSeeds();

    private native boolean decodeIonSeeds(double d2, double d3);

    private native boolean decodeRsmcEphSeeds();

    private native boolean initEnv(long j, String str, String str2);

    static {
        e = false;
        try {
            eym.b("PgnssManager", "start load PgnssJni library");
            System.loadLibrary("PgnssJni");
            e = true;
        } catch (UnsatisfiedLinkError unused) {
            eym.c("PgnssManager", "PgnssJni load fail");
            e = false;
        }
    }

    public static PgnssManager c() {
        if (f == null) {
            synchronized (b) {
                if (f == null) {
                    f = new PgnssManager();
                }
            }
        }
        return f;
    }

    public eyk c(List<String> list) {
        eyk eykVar;
        synchronized (b) {
            eym.b("PgnssManager", "getPredictRequest start");
            eykVar = new eyk();
            eykVar.d(true);
            eykVar.a(true);
            eykVar.c(2);
            eykVar.d(1);
            eykVar.e("getBatchPluginUrl");
            List<String> a2 = eyf.a(list);
            HashMap hashMap = new HashMap();
            Iterator<String> it = a2.iterator();
            while (it.hasNext()) {
                hashMap.put(it.next(), "HW_PGNSS");
            }
            eykVar.c(hashMap);
            HashMap hashMap2 = new HashMap();
            hashMap2.put(b6.a.b, "HealthApp");
            hashMap2.put("traceId", UUID.randomUUID().toString());
            eykVar.b(hashMap2);
            eym.b("PgnssManager", "getPredictRequest end");
        }
        return eykVar;
    }

    public Map<String, String> a(eyl eylVar) {
        HashMap hashMap;
        synchronized (b) {
            eym.b("PgnssManager", "getPredictResponse start");
            eym.b("PgnssManager", "originPath: " + eylVar.e());
            String str = eylVar.b() + File.separator;
            eym.b("PgnssManager", "storageDir: " + str);
            String d2 = eylVar.d();
            eym.b("PgnssManager", "deviceIdentify: " + d2);
            int i = 0;
            while (true) {
                String[] strArr = d;
                if (i >= strArr.length) {
                    break;
                }
                c(strArr[i], str, d2);
                i++;
            }
            d(str, d2, 2);
            hashMap = new HashMap();
            int i2 = 0;
            while (true) {
                String[] strArr2 = d;
                if (i2 < strArr2.length) {
                    String e2 = e(strArr2[i2], str, d2);
                    if (e2 != null && !"".equals(e2)) {
                        eym.b("PgnssManager", strArr2[i2] + " find");
                        hashMap.put(e2, strArr2[i2]);
                    } else {
                        eym.b("PgnssManager", strArr2[i2] + " is not exists");
                    }
                    i2++;
                } else {
                    eym.b("PgnssManager", "getPredictResponse end");
                }
            }
        }
        return hashMap;
    }

    private void c(String str, String str2, String str3) {
        eym.b("PgnssManager", "clearOldFiles start");
        File file = new File(str2, eyi.d(str3 + str));
        if (file.exists()) {
            eym.b("PgnssManager", "clearOldFiles delete file: " + str + ", isDeleted: " + file.delete());
        }
    }

    private String e(String str, String str2, String str3) {
        eym.b("PgnssManager", "checkDecodeResult start");
        File file = new File(str2, eyi.d(str3 + str));
        return file.exists() ? file.getPath() : "";
    }

    private void d(String str, String str2, int i) {
        boolean z;
        eym.b("PgnssManager", "decodePgnssSeed start");
        if (e) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            long j = currentTimeMillis - 315964782;
            eym.b("PgnssManager", "utcTime: " + currentTimeMillis + ", currentGpsTime: " + j);
            boolean initEnv = initEnv(j, str, str2);
            StringBuilder sb = new StringBuilder("initEnv, isInitSucceed: ");
            sb.append(initEnv);
            eym.b("PgnssManager", sb.toString());
            if (i == 2) {
                z = decodeEphSeeds();
            } else if (i == 5) {
                z = decodeRsmcEphSeeds();
            } else if (i == 8) {
                z = decodeIonSeeds(f2945a, c);
            } else {
                eym.e("PgnssManager", "decodePgnssSeed not supported: " + i);
                z = false;
            }
            eym.b("PgnssManager", "decodePgnssSeed, isDecodeSucceed: " + z + ", ephType: " + i);
            return;
        }
        eym.b("PgnssManager", "decodePgnssSeed PgnssJni load fail");
    }

    public eyk a(List<String> list) {
        eym.b("PgnssManager", "getTransRequest start");
        return new eyk();
    }

    public Map<String, String> c(eyl eylVar) {
        eym.b("PgnssManager", "getTransResponse start");
        return new HashMap();
    }

    public eyk d(List<String> list, int i) {
        eyk eykVar;
        synchronized (b) {
            eym.b("PgnssManager", "getRsmcRequest start, type: " + i);
            eykVar = new eyk();
            eykVar.d(true);
            if (i == 7) {
                eykVar.a(false);
            } else {
                eykVar.a(true);
            }
            eykVar.c(i);
            eykVar.d(2);
            eykVar.e("getAgnssPluginUrl");
            eykVar.c("POST");
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
            hashMap.put(j2.v, Constants.GZIP);
            eykVar.b(hashMap);
            eykVar.c(b(list, i));
            String str = "";
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("version", "v1");
                jSONObject.put("type", "BDS");
                str = jSONObject.toString();
            } catch (JSONException unused) {
                eykVar.d(false);
                eym.c("PgnssManager", "getRsmcBody JSON Exception");
            }
            eykVar.d(str);
            eym.b("PgnssManager", "getRsmcRequest end");
        }
        return eykVar;
    }

    private Map<String, String> b(List<String> list, int i) {
        String str;
        if (i == 5) {
            str = "HiBdsEE.dat";
        } else if (i == 6) {
            str = "rsmc_bds.json";
        } else if (i == 7) {
            str = "RSMC_ORBITS_CONFIG";
        } else {
            eym.b("PgnssManager", "getRsmcConfigMap type not supported");
            str = "";
        }
        if (str.isEmpty()) {
            return new HashMap();
        }
        List<String> a2 = eyf.a(list);
        HashMap hashMap = new HashMap();
        Iterator<String> it = a2.iterator();
        while (it.hasNext()) {
            hashMap.put(it.next(), str);
        }
        return hashMap;
    }

    public Map<String, String> d(eyl eylVar) {
        synchronized (b) {
            eym.b("PgnssManager", "getRsmcResponse start");
            String str = eylVar.b() + File.separator;
            String d2 = eylVar.d();
            String d3 = d(eylVar.c());
            c(d3, str, d2);
            int c2 = eylVar.c();
            if (c2 == 5) {
                if (!e(eylVar)) {
                    return new HashMap();
                }
                d(str, d2, 5);
            } else if (c2 == 6) {
                String str2 = str + eylVar.d() + d3;
                eyi.a(eylVar.e());
                BdsSysCfgConverter.a(eylVar.e(), str2);
            } else {
                eym.e("PgnssManager", "getRsmcResponse not supported, type: " + c2);
            }
            HashMap hashMap = new HashMap();
            String e2 = e(d3, str, d2);
            if (!e2.isEmpty()) {
                eym.b("PgnssManager", d3 + " is find");
                hashMap.put(e2, d3);
            } else {
                eym.e("PgnssManager", d3 + " is not exists");
            }
            return hashMap;
        }
    }

    private String d(int i) {
        String str;
        if (i == 5) {
            str = "RSMC_PGNSS_BDS";
        } else if (i == 6) {
            str = "RSMC_SYSTEM_COINFIG";
        } else {
            eym.e("PgnssManager", "getFileIdByType not supported, fileType: " + i);
            str = "";
        }
        eym.b("PgnssManager", "getFileIdByType, fileType: " + i);
        return str;
    }

    private boolean e(eyl eylVar) {
        String str = "";
        try {
            str = new JSONObject(new String(eyi.a(eylVar.e()), StandardCharsets.UTF_8)).getString("ephmerisData");
            eym.b("PgnssManager", "getBdsEphmerisSeed, ephDataStr length: " + str.length());
        } catch (JSONException unused) {
            eym.c("PgnssManager", "getBdsEphmerisSeed JSONException");
        }
        if (str.isEmpty()) {
            eym.e("PgnssManager", "getBdsEphmerisSeed ephDataStr is empty");
            return false;
        }
        String str2 = eylVar.b() + File.separator;
        String str3 = eylVar.d() + "RSMC_SEED_EPHEMERIS";
        c("RSMC_SEED_EPHEMERIS", str2, eylVar.d());
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        boolean d2 = eyi.d(str2 + str3, bytes, bytes.length);
        StringBuilder sb = new StringBuilder("getBdsEphmerisSeed end, isWriteSuccess: ");
        sb.append(d2);
        eym.b("PgnssManager", sb.toString());
        return d2;
    }

    public eyk e(List<String> list, int i) {
        eym.b("PgnssManager", "getIonRequest start");
        eyk eykVar = new eyk();
        eykVar.d(true);
        eykVar.a(true);
        eykVar.c(i);
        eykVar.d(1);
        eykVar.e("getAgnssPluginUrl");
        List<String> d2 = eyf.d(list);
        String str = d2.size() == 2 ? d2.get(0) : "";
        String[] split = (d2.size() == 2 ? d2.get(1) : "").split(",");
        try {
            if (split.length == 2) {
                f2945a = Double.valueOf(split[0]).doubleValue();
                c = Double.valueOf(split[1]).doubleValue();
            } else {
                eym.c("PgnssManager", " getIonRequest lat and lon OutOfBounds");
                eykVar.d(false);
            }
        } catch (NumberFormatException unused) {
            eym.c("PgnssManager", " getIonRequest lat and lon numberFormat");
            eykVar.d(false);
        }
        if (str == null || str.length() <= 0) {
            eym.e("PgnssManager", "getIonRequest standardUrl is null");
            eykVar.d(false);
        }
        HashMap hashMap = new HashMap();
        hashMap.put(str, "ION.dat");
        eykVar.c(hashMap);
        String e2 = e();
        if (e2 == null || e2.length() <= 0) {
            eykVar.d(false);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("X-Request-ID", UUID.randomUUID().toString());
        hashMap2.put("X-Device-Type", "SportsHealth");
        hashMap2.put("Authorization", e2);
        eykVar.b(hashMap2);
        eym.b("PgnssManager", "getIonRequest end");
        return eykVar;
    }

    private String e() {
        String str;
        eym.b("PgnssManager", "getIonAuth start");
        try {
            str = exz.b(b()).b();
        } catch (Exception unused) {
            eym.c("PgnssManager", "getIonAuth Exception");
            str = "";
        }
        if (str == null || str.length() <= 0) {
            eym.e("PgnssManager", "getIonAuth authorization is null");
        }
        return str;
    }

    private eyn b() {
        eym.b("PgnssManager", "getIonSdkAuth start");
        eyn eynVar = new eyn();
        eynVar.c("/higeo/v1/gnssIonInfo");
        eynVar.a("GET");
        eynVar.b("CLOUDSOA-HMAC-SHA256");
        eynVar.f("com.huawei.hms.location");
        eynVar.i(eyc.e());
        eynVar.e("precisionIonKey");
        eynVar.g("");
        eynVar.d("");
        eynVar.c(1);
        eynVar.d(y.c);
        eynVar.d(86400000L);
        return eynVar;
    }

    public Map<String, String> b(eyl eylVar) {
        HashMap hashMap;
        synchronized (b) {
            eym.b("PgnssManager", "getIonResponse start");
            eym.b("PgnssManager", "originPath: " + eylVar.e());
            String str = eylVar.b() + File.separator;
            eym.b("PgnssManager", "storageDir: " + str);
            String d2 = eylVar.d();
            eym.b("PgnssManager", "deviceIdentify: " + d2);
            c("SubION.dat", str, d2);
            d(str, d2, 8);
            hashMap = new HashMap();
            String e2 = e("SubION.dat", str, d2);
            if (e2 != null && !"".equals(e2)) {
                eym.b("PgnssManager", "SubION.dat find");
                hashMap.put(e2, "SubION.dat");
            } else {
                eym.b("PgnssManager", "SubION.dat is not exists");
            }
            eym.b("PgnssManager", "getIonResponse end");
        }
        return hashMap;
    }
}
