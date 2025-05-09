package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kid {
    public static khw b(byte[] bArr) throws cwg {
        String d = cvx.d(bArr);
        if (d == null || d.length() <= 4) {
            return new khw();
        }
        return d(new cwl().a(d.substring(4, d.length())));
    }

    private static khw d(cwe cweVar) {
        if (cweVar == null) {
            LogUtil.h("OtaCommandUnPackageUtil", "dealDeviceApply tlvFather is null");
            return null;
        }
        khw khwVar = new khw();
        List<cwd> e = cweVar.e();
        if (e != null && !e.isEmpty()) {
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                String c = cwdVar.c();
                if (w == 1) {
                    khwVar.d(CommonUtil.y(c));
                } else if (w == 2) {
                    khwVar.b(CommonUtil.y(c));
                } else if (w == 3) {
                    khwVar.a(d(c));
                } else if (w == 4) {
                    khwVar.c(CommonUtil.y(c));
                } else {
                    LogUtil.h("OtaCommandUnPackageUtil", "dealDeviceApply dataOtaApplyReport default");
                }
            }
        }
        return khwVar;
    }

    public static List<Integer> d(String str) {
        ArrayList arrayList = new ArrayList(10);
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("OtaCommandUnPackageUtil", "parseAck , bitmapHex is empty");
            return arrayList;
        }
        byte[] a2 = cvx.a(str);
        StringBuilder sb = new StringBuilder(16);
        for (byte b : a2) {
            sb.append(new StringBuffer(d(b)).reverse().toString());
        }
        String sb2 = sb.toString();
        LogUtil.a("OtaCommandUnPackageUtil", "parseAck, bufferStr = ", sb2);
        for (int i = 0; i < sb2.length(); i++) {
            if ("0".equalsIgnoreCase(sb2.charAt(i) + "")) {
                arrayList.add(0);
                LogUtil.a("OtaCommandUnPackageUtil", "errorPackages, error package index = ", Integer.valueOf(i));
            } else {
                arrayList.add(1);
            }
        }
        return arrayList;
    }

    private static String d(byte b) {
        return "" + ((int) ((byte) ((b >> 7) & 1))) + ((int) ((byte) ((b >> 6) & 1))) + ((int) ((byte) ((b >> 5) & 1))) + ((int) ((byte) ((b >> 4) & 1))) + ((int) ((byte) ((b >> 3) & 1))) + ((int) ((byte) ((b >> 2) & 1))) + ((int) ((byte) ((b >> 1) & 1))) + ((int) ((byte) (b & 1)));
    }

    public static kib a(byte[] bArr) throws cwg {
        blt.d("OtaCommandUnPackageUtil", bArr, "5.9.5 Enter dataInfos: ");
        String d = cvx.d(bArr);
        if (d == null || d.length() <= 4) {
            return new kib();
        }
        return b(new cwl().a(d.substring(4, d.length())));
    }

    private static kib b(cwe cweVar) {
        List<cwd> e;
        LogUtil.a("OtaCommandUnPackageUtil", "5.9.5 unPackassgeSizeReport enter");
        kib kibVar = new kib();
        if (cweVar != null && (e = cweVar.e()) != null && e.size() > 0) {
            for (cwd cwdVar : e) {
                if (cwdVar != null) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c = cwdVar.c();
                    if (a2 == 1) {
                        kibVar.b(CommonUtil.y(c));
                    } else if (a2 == 2) {
                        kibVar.d(CommonUtil.y(c));
                    } else {
                        LogUtil.c("OtaCommandUnPackageUtil", "unPackassgeSizeReport: no such case");
                    }
                }
            }
        }
        return kibVar;
    }

    public static kic c(byte[] bArr) throws cwg {
        blt.d("OtaCommandUnPackageUtil", bArr, "5.9.6 Enter dataInfos: ");
        String d = cvx.d(bArr);
        if (d == null || d.length() <= 4) {
            return new kic();
        }
        return c(new cwl().a(d.substring(4, d.length())));
    }

    private static kic c(cwe cweVar) {
        List<cwd> e;
        kic kicVar = new kic();
        kicVar.c(0);
        if (cweVar == null) {
            return kicVar;
        }
        try {
            e = cweVar.e();
        } catch (NumberFormatException unused) {
            LogUtil.b("OtaCommandUnPackageUtil", "unCheckResultsReport: NumberFormatException");
        }
        if (e != null && e.size() != 0) {
            for (cwd cwdVar : e) {
                if (cwdVar != null) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c = cwdVar.c();
                    if (a2 == 1) {
                        kicVar.c(CommonUtil.w(c));
                    } else if (a2 == 4) {
                        kicVar.a(CommonUtil.y(c));
                    } else {
                        LogUtil.c("OtaCommandUnPackageUtil", "unPackassgeSizeReport: no such case");
                    }
                }
            }
            return kicVar;
        }
        return kicVar;
    }

    public static boolean e(DeviceInfo deviceInfo, byte[] bArr) throws cwg {
        cwd b = b(bArr, "5.9.15");
        if (b == null) {
            LogUtil.h("OtaCommandUnPackageUtil", "isRequestNewVersionCheck tlv is null");
            return false;
        }
        if (CommonUtil.a(b.e(), 16) != 1) {
            LogUtil.h("OtaCommandUnPackageUtil", "isRequestNewVersionCheck type not equals 1");
            return false;
        }
        int a2 = CommonUtil.a(b.c(), 16);
        LogUtil.a("OtaCommandUnPackageUtil", "isRequestNewVersionCheck value:", Integer.valueOf(a2));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x01", a2);
        } catch (JSONException unused) {
            LogUtil.b("OtaCommandUnPackageUtil", "isRequestNewVersionCheck JSONException");
        }
        jrd.b(deviceInfo, "050915", "0", "", jSONObject.toString());
        return a2 == 0;
    }

    private static cwd b(byte[] bArr, String str) throws cwg {
        String d = cvx.d(bArr);
        if (d == null || d.length() <= 4) {
            LogUtil.h("OtaCommandUnPackageUtil", "parseTlvByDataInfos resultDataHex is null ", str);
            return null;
        }
        List<cwd> e = new cwl().a(d.substring(4, d.length())).e();
        if (e == null || e.isEmpty()) {
            LogUtil.h("OtaCommandUnPackageUtil", "parseTlvByDataInfos tlvList is empty ", str);
            return null;
        }
        return e.get(0);
    }

    public static int d(DeviceInfo deviceInfo, byte[] bArr) throws cwg {
        cwd b = b(bArr, "5.9.16");
        if (b == null) {
            LogUtil.h("OtaCommandUnPackageUtil", "deviceRequestDownloadOta tlv is null");
            return 255;
        }
        if (CommonUtil.a(b.e(), 16) != 1) {
            LogUtil.h("OtaCommandUnPackageUtil", "deviceRequestDownloadOta type not equals 1");
            return 255;
        }
        int a2 = CommonUtil.a(b.c(), 16);
        LogUtil.a("OtaCommandUnPackageUtil", "isRequestNewVersionCheck value:", Integer.valueOf(a2));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x01", a2);
        } catch (JSONException unused) {
            LogUtil.b("OtaCommandUnPackageUtil", "deviceRequestDownloadOta JSONException");
        }
        jrd.b(deviceInfo, "050916", "0", "", jSONObject.toString());
        return a2;
    }

    public static boolean a(DeviceInfo deviceInfo, byte[] bArr) throws cwg {
        cwd b = b(bArr, "5.9.17");
        if (b == null) {
            LogUtil.h("OtaCommandUnPackageUtil", "isUserAgreeDownload tlv is null");
            return false;
        }
        if (CommonUtil.a(b.e(), 16) != 1) {
            LogUtil.h("OtaCommandUnPackageUtil", "isUserAgreeDownload type not equals 1");
            return false;
        }
        int a2 = CommonUtil.a(b.c(), 16);
        LogUtil.a("OtaCommandUnPackageUtil", "isUserAgreeDownload value:", Integer.valueOf(a2));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x01", a2);
        } catch (JSONException unused) {
            LogUtil.b("OtaCommandUnPackageUtil", "isUserAgreeDownload JSONException");
        }
        jrd.b(deviceInfo, "050917", "0", "", jSONObject.toString());
        return a2 == 1;
    }
}
