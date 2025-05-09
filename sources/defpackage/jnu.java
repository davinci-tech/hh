package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.huawei.health.BuildConfig;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jnu {
    public static String e() {
        int nextInt = new SecureRandom().nextInt(ExceptionCode.CRASH_EXCEPTION);
        Date date = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(date) + (((nextInt + ExceptionCode.CRASH_EXCEPTION) % ExceptionCode.CRASH_EXCEPTION) + ExceptionCode.CRASH_EXCEPTION);
    }

    public static String h() {
        PackageInfo packageInfo;
        try {
            PackageManager packageManager = BaseApplication.getContext().getPackageManager();
            if (packageManager == null || (packageInfo = packageManager.getPackageInfo(BaseApplication.getAppPackage(), 0)) == null) {
                return "";
            }
            return packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("TouchUtil", "getAppVersionName() Exception", e.getMessage());
            return "";
        }
    }

    public static List<jmu> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(16);
        try {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                jmu c = jmu.c(jSONArray.getJSONObject(i));
                if (c != null) {
                    arrayList.add(c);
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("TouchUtil", "parseApduList,JSONException");
        }
        return arrayList;
    }

    public static String f() {
        DeviceInfo a2 = jpt.a("TouchUtil");
        return a2 != null ? a2.getDeviceModel() : "";
    }

    public static String g() {
        DeviceInfo a2 = jpt.a("TouchUtil");
        return a2 != null ? a2.getSecurityDeviceId() : "";
    }

    public static String c() {
        DeviceInfo a2 = jpt.a("TouchUtil");
        return a2 != null ? a2.getDeviceIdentify() : "";
    }

    public static String i() {
        DeviceInfo a2 = jpt.a("TouchUtil");
        return a2 != null ? a2.getDeviceName() : "";
    }

    public static String a() {
        String e;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Data_File_E103", "000F20010000FF0406E10401000000");
            String c = c(c().replace(":", ""));
            StringBuilder sb = new StringBuilder(16);
            sb.append("D20C");
            StringBuilder sb2 = new StringBuilder(16);
            sb2.append("100000000901");
            String str = cvx.e(3) + cvx.e(c.length() / 2) + c;
            sb2.append(str);
            sb.append(cvx.e(sb2.toString().length() / 2));
            sb.append("6170702F68776F6E65686F70100000000901");
            sb.append(str);
            if (sb.toString().length() / 2 <= 255) {
                e = cvx.e(0) + cvx.e(sb.toString().length() / 2);
            } else {
                e = cvx.e(sb.toString().length() / 2);
            }
            LogUtil.a("TouchUtil", "length:", e);
            jSONObject.put("Data_File_E104", e + sb.toString());
            return jSONObject.toString();
        } catch (JSONException unused) {
            LogUtil.b("TouchUtil", "createPersonalizeMacDeviceModel,JSONException");
            return null;
        }
    }

    public static String c(String str) {
        if (str == null || str.length() == 0 || str.length() < 3) {
            return "";
        }
        String str2 = "000000000" + str.substring(str.length() - 3, str.length());
        byte[] bArr = new byte[6];
        for (int i = 0; i < 6; i++) {
            int i2 = i * 2;
            bArr[i] = b(str2.charAt(i2), str2.charAt(i2 + 1));
        }
        return b(bArr, 6);
    }

    public static String b(byte[] bArr, int i) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(16);
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                sb.append("0");
                sb.append(hexString);
                sb.append("");
            } else {
                sb.append(hexString);
                sb.append("");
            }
        }
        return sb.toString().toUpperCase(Locale.ENGLISH).trim();
    }

    private static byte b(char c, char c2) {
        return (byte) ((b(c) << 4) | b(c2));
    }

    private static int b(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'A';
        if (c < 'A' || c > 'F') {
            c2 = 'a';
            if (c < 'a' || c > 'f') {
                LogUtil.h("TouchUtil", "hexCharToInt error");
                return 0;
            }
        }
        return (c - c2) + 10;
    }

    public static int b() {
        DeviceInfo a2 = jpt.a("TouchUtil");
        if (a2 != null) {
            return a2.getDeviceConnectState();
        }
        return 0;
    }

    public static String d() {
        return CommonUtil.ag(BaseApplication.getContext()) ? BuildConfig.WALLET_MERCHANT_ID : "900086000000010204";
    }
}
