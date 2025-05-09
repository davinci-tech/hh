package defpackage;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class csp {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11446a = new Object();
    private static volatile csp d;
    private List<ctn> b;
    private String[] c = {"G", "H", RequestOptions.AD_CONTENT_CLASSIFICATION_J, "I"};
    private Context e;
    private List<ScanResult> g;

    private csp(Context context) {
        if (context != null) {
            this.e = context.getApplicationContext();
        } else {
            cpw.c(false, "DeviceWifiAp", "DeviceWifiAp context is null ");
        }
    }

    public static csp c(Context context) {
        if (d == null) {
            synchronized (f11446a) {
                if (d == null) {
                    d = new csp(context);
                }
            }
        }
        return d;
    }

    public String c(ctn ctnVar) {
        String str = "";
        if (ctnVar != null) {
            cpw.c(false, "DeviceWifiAp", "scanSelectWifiAp info ", ctnVar.toString());
            Context context = this.e;
            if (context != null) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                wifiManager.startScan();
                this.g = ctm.b(wifiManager.getScanResults());
            } else {
                cpw.c(false, "DeviceWifiAp", "scanSelectWifiAp mContext is null ");
            }
            List<ctn> e = e(this.g);
            this.b = e;
            cpw.a(false, "DeviceWifiAp", "scanSelectWifiAp device size:", Integer.valueOf(e.size()));
            for (ctn ctnVar2 : this.b) {
                if (ctnVar.b() != null && ctnVar.b().equals(ctnVar2.b())) {
                    str = d(ctnVar2.f());
                }
            }
        }
        return str;
    }

    public String d(String str) {
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            cpw.a(false, "DeviceWifiAp", "fetchDeviceWorkStatusMsg ssid is empty.");
            return "";
        }
        int length = str.length();
        if (e(str) && length > 19) {
            Matcher matcher = Pattern.compile("^Hi(\\w{1})(\\w{1})([\\w^_]{4})(\\w{1})([\\w^_]{1})([\\w^_]{10,31})").matcher(str);
            while (matcher.find()) {
                str2 = matcher.group(1);
            }
        }
        cpw.a(false, "DeviceWifiAp", "fetchDeviceWorkStatusMsg: ", str2);
        return str2;
    }

    public List<ctn> e(dcz dczVar) {
        cpw.a(false, "DeviceWifiAp", "------scanWifiAp----");
        List<ScanResult> b = ctm.b(((WifiManager) this.e.getSystemService("wifi")).getScanResults());
        this.g = b;
        List<ctn> c = c(dczVar, b);
        this.b = c;
        cpw.a(false, "DeviceWifiAp", "scanWifiAp mAddDeviceInfos size:", Integer.valueOf(c.size()));
        return this.b;
    }

    private List<ctn> e(List<ScanResult> list) {
        ArrayList arrayList = new ArrayList(16);
        if (list == null) {
            return arrayList;
        }
        cpw.a(false, "DeviceWifiAp", "getAddDeviceInfosByResult enter");
        for (ScanResult scanResult : list) {
            String str = scanResult.SSID;
            if (TextUtils.isEmpty(str)) {
                cpw.c(true, "DeviceWifiAp", "ssid is null.");
            } else if (str.length() == 32) {
                if (!str.startsWith("Hi- ")) {
                    cpw.c(true, "DeviceWifiAp", cpw.d(str), "  len:", Integer.valueOf(str.length()));
                    if (e(str) && a(str)) {
                        arrayList.add(LP_(str, scanResult));
                    }
                } else {
                    cpw.a(false, "DeviceWifiAp", "Hi- , SoftAp branch");
                    String[] split = str.split(Constants.LINK);
                    if (!a(split)) {
                        String substring = split[3].substring(1, 5);
                        String substring2 = split[3].substring(5, 6);
                        if ("0".equals(split[3].substring(0, 1))) {
                            int a2 = new ctt("B").a();
                            cpw.c(true, "DeviceWifiAp", "mode: ", Integer.valueOf(a2));
                            ctn ctnVar = new ctn(str, substring2, a2);
                            ctnVar.j("softap");
                            ctnVar.d(scanResult.BSSID);
                            ctnVar.c(substring);
                            arrayList.add(ctnVar);
                            cpw.c(true, "DeviceWifiAp", ctnVar.toString());
                        } else {
                            cpw.c(true, "DeviceWifiAp", "mode is not 0");
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private boolean a(String[] strArr) {
        if (strArr.length < 4) {
            cpw.c(true, "DeviceWifiAp", "buffers.length < 4 ssid split faild:", Integer.valueOf(strArr.length));
            return true;
        }
        if (strArr.length != 4 || strArr[3].length() < 6) {
            cpw.c(true, "DeviceWifiAp", "ssid split faild:", Integer.valueOf(strArr.length));
            return true;
        }
        if (Pattern.matches("[0-9A-Za-z]+", strArr[3].substring(0, 6))) {
            return false;
        }
        cpw.c(true, "DeviceWifiAp", "ssid[3] is not avalid：", strArr[3]);
        return true;
    }

    private ctn LP_(String str, ScanResult scanResult) {
        cpw.a(false, "DeviceWifiAp", "broadcast branch", cpw.d(str));
        Matcher matcher = Pattern.compile("^Hi(\\w{1})(\\w{1})([\\w^_]{4})(\\w{1})([\\w^_]{1})([\\w^_]{10,31})").matcher(str);
        String str2 = "";
        int i = -1;
        String str3 = "";
        while (matcher.find()) {
            str3 = matcher.group(3);
            str2 = matcher.group(4);
            i = new ctt(matcher.group(5)).a();
            cpw.c(true, "DeviceWifiAp", "deviceSn :", str2);
        }
        ctn ctnVar = new ctn(str, str2, i);
        ctnVar.j("wifiap");
        ctnVar.d(scanResult.BSSID);
        ctnVar.c(str3);
        return ctnVar;
    }

    public boolean a(String str) {
        if (e(str) && str.length() > 19) {
            cpw.c(true, "DeviceWifiAp", "CheckSpecialStatusSsid ", str);
            Matcher matcher = Pattern.compile("^Hi(\\w{1})(\\w{1})([\\w^_]{4})(\\w{1})([\\w^_]{1})([\\w^_]{10,31})").matcher(str);
            String str2 = "";
            while (matcher.find()) {
                str2 = matcher.group(3);
            }
            for (String str3 : this.c) {
                if (str3.equals(str2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<ctn> c(dcz dczVar, List<ScanResult> list) {
        List<ctn> e = e(list);
        if (e.size() == 0 || dczVar == null) {
            return e;
        }
        ArrayList arrayList = new ArrayList(16);
        for (ctn ctnVar : e) {
            if (dczVar.w().a(ctnVar.g())) {
                ctnVar.i(dczVar.t());
                arrayList.add(ctnVar);
            }
        }
        return arrayList;
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str) || str.endsWith("_5G")) {
            return false;
        }
        return Pattern.compile("^Hi(\\w{1})(\\w{1})([\\w^_]{4})(\\w{1})([\\w^_]{1})([\\w^_]{10,31})").matcher(str).matches();
    }

    public ctn a(cth cthVar) {
        ctn ctnVar = new ctn();
        if (cthVar != null && cthVar.a() != null) {
            cpw.c(true, "DeviceWifiAp", "model is :", cthVar);
            String d2 = cthVar.a().d();
            boolean z = d2 == null || cthVar.a().c() == null;
            String e = cthVar.a().e();
            if (z || e == null) {
                return ctnVar;
            }
            if (d2.length() > 1) {
                d2 = d2.substring(d2.length() - 1);
            }
            ctnVar.a(d2);
            String b = cthVar.a().b();
            StringBuilder sb = new StringBuilder(16);
            sb.append("Hi00");
            sb.append(b);
            StringBuilder sb2 = new StringBuilder(16);
            sb2.append("000000000000000000000000");
            sb.append(sb2.toString());
            String sb3 = sb.toString();
            cpw.c(true, "DeviceWifiAp", "parseCoapEntityToDeviceInfo: ssid is ", cpw.d(sb3));
            ctnVar.c(b);
            ctnVar.f(sb3);
            ctnVar.e(cthVar.c());
            ctnVar.d(cthVar.a().a());
            ctnVar.b(cthVar.d());
            ctnVar.j("coap");
        } else {
            cpw.d(false, "DeviceWifiAp", "COAP Scan devInfo is null");
        }
        return ctnVar;
    }

    public int b(String str) {
        int i = -1;
        if (e(str) && a(str)) {
            Matcher matcher = Pattern.compile("^Hi(\\w{1})(\\w{1})([\\w^_]{4})(\\w{1})([\\w^_]{1})([\\w^_]{10,31})").matcher(str);
            while (matcher.find()) {
                i = new ctt(matcher.group(5)).a();
                cpw.a(false, "DeviceWifiAp", "EncryptMode :", Integer.valueOf(i));
            }
        }
        return i;
    }
}
