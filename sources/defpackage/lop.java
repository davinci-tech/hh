package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Message;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice;
import com.huawei.multisimsdk.multidevicemanager.common.InProgressData;
import defpackage.log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lop {
    private static final String c = "Utils";
    private static ConnectivityManager d;
    private static final Character e = '{';

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Map> f14814a = new ConcurrentHashMap();

    public static boolean bYo_(Message message) {
        return true;
    }

    public static int c() {
        return ((int) (Math.random() * 100000.0d)) + 1;
    }

    public static String a(Context context, String str, String str2) {
        String j = j(str);
        StringBuffer stringBuffer = new StringBuffer("");
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences("MultiSIM_info", 0) : null;
        if (sharedPreferences == null || str2 == null || str2.isEmpty()) {
            return "";
        }
        String string = sharedPreferences.getString(stringBuffer.append(j).append("_").append(str2).toString(), "");
        return "".equals(string) ? "" : lon.b(context, string);
    }

    public static void e(Context context, String str, String str2) {
        d(context, str, "Tag", str2);
        d(context, str2, "Tag", str);
    }

    public static boolean d(Context context, String str, String str2, String str3) {
        String j = j(str);
        StringBuffer stringBuffer = new StringBuffer("");
        String a2 = lon.a(context, str3);
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences("MultiSIM_info", 0) : null;
        if (sharedPreferences == null || str2 == null || str2.isEmpty()) {
            return false;
        }
        String stringBuffer2 = stringBuffer.append(j).append("_").append(str2).toString();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (!"".equals(sharedPreferences.getString(stringBuffer2, ""))) {
            edit.remove(stringBuffer2);
        }
        edit.putString(stringBuffer2, a2);
        edit.commit();
        return true;
    }

    public static boolean b(Context context, String str, String str2) {
        String j = j(str);
        StringBuffer stringBuffer = new StringBuffer("");
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences("MultiSIM_info", 0) : null;
        if (sharedPreferences == null || str2 == null || str2.isEmpty()) {
            return false;
        }
        String stringBuffer2 = stringBuffer.append(j).append("_").append(str2).toString();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (!"".equals(sharedPreferences.getString(stringBuffer2, ""))) {
            edit.remove(stringBuffer2);
        }
        edit.commit();
        return true;
    }

    private static String j(String str) {
        int length;
        if (str == null || (length = str.length()) <= 5) {
            return null;
        }
        return str.substring(length - 5, length);
    }

    public static lnq d(Context context, String str) {
        lnq lnqVar = new lnq();
        lnqVar.c(c());
        lnqVar.a("DevAuth");
        lnqVar.e("Token");
        lnqVar.c(Base64.encodeToString(String.format("0%1$s@nai.epc.mnc%2$s.mcc%3$s.3gppnetwork.org", str, b(str), a(str)).getBytes(), 0).trim());
        lnqVar.b(a(context, str, "authen_Token"));
        return lnqVar;
    }

    public static void b(int i, InProgressData inProgressData) {
        log.c h = log.a().h();
        if (h != null) {
            h.sendMessage(h.obtainMessage(i, inProgressData));
        }
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        if (d == null) {
            d = (ConnectivityManager) context.getSystemService("connectivity");
        }
        NetworkInfo activeNetworkInfo = d.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }

    public static void bYq_(Message message) {
        if (message == null || message.getTarget() == null) {
            return;
        }
        try {
            message.sendToTarget();
        } catch (IllegalStateException unused) {
            loq.b(c, "don't send again!!");
        }
    }

    public static boolean i(String str) {
        return (str == null || str.length() <= 1 || e.charValue() == str.charAt(0)) ? false : true;
    }

    public static String a(InProgressData inProgressData) {
        String str;
        String str2;
        if (inProgressData != null) {
            str = inProgressData.getPrimary();
            str2 = inProgressData.getPrimaryIDtype();
        } else {
            str = null;
            str2 = null;
        }
        return lod.c(str2, str);
    }

    public static void bYp_(Message message, int i) {
        if (message != null) {
            loq.c(c, "sendMessagewitharg1 arg1 :" + i);
            message.arg1 = i;
            bYq_(message);
        }
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str) || !str.matches("[0-9]{1,4}")) {
            return false;
        }
        loq.c(c, "http connect result is match digital, result:" + str);
        return true;
    }

    public static void b(HttpURLConnection httpURLConnection, String str) {
        JSONArray jSONArray;
        String headerField = httpURLConnection != null ? httpURLConnection.getHeaderField("set-cookie") : null;
        try {
            if (TextUtils.isEmpty(str)) {
                jSONArray = new JSONArray();
            } else {
                jSONArray = new JSONArray(str);
            }
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            if (jSONObject == null || !"DevAuth".equals(jSONObject.optString("ReqName"))) {
                return;
            }
            String valueOf = String.valueOf(jSONObject.optInt("ReqSN"));
            SharedPreferences sharedPreferences = log.a().c().getSharedPreferences("MultiCookie", 0);
            if (sharedPreferences == null || valueOf == null) {
                return;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (!"".equals(sharedPreferences.getString(valueOf, ""))) {
                edit.remove(valueOf);
            }
            edit.putString(valueOf, headerField);
            edit.commit();
            sharedPreferences.getString(valueOf, "");
        } catch (JSONException unused) {
            loq.c(c, "ResponseAuthFirstInfo-parseResponseAuthInfoArray JSONException");
        }
    }

    public static String e(String str) {
        SharedPreferences sharedPreferences = log.a().c().getSharedPreferences("MultiCookie", 0);
        if (sharedPreferences == null || str == null) {
            return null;
        }
        String string = sharedPreferences.getString(str, "");
        if (!"".equals(string)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove(str);
            edit.commit();
        }
        return string;
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return String.format("%03d", Integer.valueOf(Integer.parseInt(str.substring(0, 3))));
        } catch (NumberFormatException unused) {
            loq.c(c, "getMcc NumberFormatException");
            return null;
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return String.format("%03d", Integer.valueOf(Integer.parseInt(str.substring(3, 5))));
        } catch (NumberFormatException unused) {
            loq.c(c, "getMnc NumberFormatException");
            return null;
        }
    }

    public static int c(Context context, int i) {
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        int i2 = -1;
        if (subscriptionManager == null) {
            loq.b(c, "subscriptionManager is null");
            return -1;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            int[] subscriptionIds = subscriptionManager.getSubscriptionIds(i);
            if (subscriptionIds != null && subscriptionIds.length > 0) {
                i2 = subscriptionIds[0];
            }
        } else if (Build.VERSION.SDK_INT >= 28) {
            List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
            if (activeSubscriptionInfoList == null) {
                loq.b(c, "P not obtained subId");
                return -1;
            }
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                if (subscriptionInfo.getSimSlotIndex() == i) {
                    loq.c(c, "P get subId");
                    i2 = subscriptionInfo.getSubscriptionId();
                }
            }
        } else {
            SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = subscriptionManager.getActiveSubscriptionInfoForSimSlotIndex(i);
            if (activeSubscriptionInfoForSimSlotIndex == null) {
                loq.b(c, "no subscriptionInfo");
                return -1;
            }
            i2 = activeSubscriptionInfoForSimSlotIndex.getSubscriptionId();
        }
        loq.c(c, "Utils getSubIdFromSlotId() subId=" + i2);
        return i2;
    }

    public static boolean d(int i, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Map<String, String> map) {
        if (absPrimaryDevice == null || absPairedDevice == null) {
            loq.b(c, "primaryDevice is null or pairedDevice is null");
            return false;
        }
        if (i == 0) {
            return c(absPrimaryDevice, absPairedDevice);
        }
        if (i == 1) {
            return d(absPrimaryDevice, absPairedDevice);
        }
        if (i == 2) {
            return d(absPrimaryDevice, absPairedDevice, map);
        }
        if (i == 3 || i == 5) {
            return e(absPrimaryDevice, absPairedDevice);
        }
        return true;
    }

    private static boolean d(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice) {
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalId())) {
            loq.b(c, "isContainParamsOperationManageSubscription: TerminalId is null");
            return false;
        }
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalVendor())) {
            loq.b(c, "isContainParamsOperationManageSubscription: TerminalVendor is null");
            return false;
        }
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalModel())) {
            loq.b(c, "isContainParamsOperationManageSubscription: TerminalModel is null");
            return false;
        }
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalSwVersion())) {
            loq.b(c, "isContainParamsOperationManageSubscription: TerminalSwVersion is null");
            return false;
        }
        if (TextUtils.isEmpty(absPairedDevice.getTerminalEid())) {
            loq.b(c, "isContainParamsOperationManageSubscription: pairedDevice TerminalEid is null");
            return false;
        }
        if (!TextUtils.isEmpty(absPairedDevice.getTerminalId())) {
            return true;
        }
        loq.b(c, "isContainParamsOperationManageSubscription: pairedDevice TerminalId is null");
        return false;
    }

    private static boolean e(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice) {
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalId())) {
            loq.b(c, "isContainParamsOperationAcquireConfiguration: TerminalId is null");
            return false;
        }
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalVendor())) {
            loq.b(c, "isContainParamsOperationAcquireConfiguration: TerminalVendor is null");
            return false;
        }
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalModel())) {
            loq.b(c, "isContainParamsOperationAcquireConfiguration: TerminalModel is null");
            return false;
        }
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalSwVersion())) {
            loq.b(c, "isContainParamsOperationAcquireConfiguration: TerminalSwVersion is null");
            return false;
        }
        if (!TextUtils.isEmpty(absPairedDevice.getTerminalId())) {
            return true;
        }
        loq.b(c, "isContainParamsOperationAcquireConfiguration: pairedDevice TerminalId is null");
        return false;
    }

    private static boolean c(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice) {
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalId())) {
            loq.b(c, "isContainParamsOperationCheckCheckEligibility: TerminalId is null");
            return false;
        }
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalVendor())) {
            loq.b(c, "isContainParamsOperationCheckCheckEligibility: TerminalVendor is null");
            return false;
        }
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalModel())) {
            loq.b(c, "isContainParamsOperationCheckCheckEligibility: TerminalModel is null");
            return false;
        }
        if (TextUtils.isEmpty(absPrimaryDevice.getTerminalSwVersion())) {
            loq.b(c, "isContainParamsOperationCheckCheckEligibility: TerminalSwVersion is null");
            return false;
        }
        if (!TextUtils.isEmpty(absPairedDevice.getTerminalId())) {
            return true;
        }
        loq.b(c, "isContainParamsOperationCheckCheckEligibility: pairedDevice TerminalId is null");
        return false;
    }

    private static boolean d(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Map<String, String> map) {
        try {
            int intValue = Integer.valueOf(map.get("operation_type")).intValue();
            if (intValue != 10 && intValue != 11) {
                loq.b(c, "isContainParamsOperationManageService: typeResultCode is can only be 10 or 11");
                return false;
            }
            if (TextUtils.isEmpty(absPrimaryDevice.getTerminalId())) {
                loq.b(c, "isContainParamsOperationManageService: TerminalId is null");
                return false;
            }
            if (TextUtils.isEmpty(absPrimaryDevice.getTerminalVendor())) {
                loq.b(c, "isContainParamsOperationManageService: TerminalVendor is null");
                return false;
            }
            if (TextUtils.isEmpty(absPrimaryDevice.getTerminalModel())) {
                loq.b(c, "isContainParamsOperationManageService: TerminalModel is null");
                return false;
            }
            if (TextUtils.isEmpty(absPrimaryDevice.getTerminalSwVersion())) {
                loq.b(c, "isContainParamsOperationManageService: TerminalSwVersion is null");
                return false;
            }
            if (!TextUtils.isEmpty(absPairedDevice.getTerminalId())) {
                return true;
            }
            loq.b(c, "isContainParamsOperationManageService: pairedDevice TerminalId is null");
            return false;
        } catch (NumberFormatException unused) {
            loq.b(c, "isContainParamsOperationManageService: typeResultCode is not number");
            return false;
        }
    }

    public static boolean bYn_(Context context, Message message, int i) {
        int e2;
        String str = c;
        loq.e(str, "HwES check Android version, slotID = %s", Integer.valueOf(i));
        if (Build.VERSION.SDK_INT > 29 || (e2 = e(context, i)) == 1000) {
            return true;
        }
        loq.b(str, "HwES check Android Version is false");
        message.arg1 = e2;
        bYq_(message);
        return false;
    }

    private static int e(Context context, int i) {
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        if (subscriptionManager == null) {
            loq.b(c, "subscriptionManager is null");
            return -1007;
        }
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList == null || activeSubscriptionInfoList.size() <= 0) {
            loq.b(c, "subscriptionInfoList is zero or null");
            return -1007;
        }
        for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
            if (subscriptionInfo.getSubscriptionId() == defaultDataSubscriptionId && i != subscriptionInfo.getSimSlotIndex()) {
                loq.c(c, "The slotId is not primary card");
                return -1006;
            }
        }
        return 1000;
    }

    public static String b(Context context, int i) {
        String str = c;
        loq.c(str, "getSimOperator slotId=" + i);
        if (context != null) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (i == -1) {
                if (telephonyManager.getSimState() == 5) {
                    return telephonyManager.getSimOperator();
                }
            } else if (Build.VERSION.SDK_INT >= 28) {
                if (telephonyManager.getSimState(i) == 5) {
                    return telephonyManager.createForSubscriptionId(c(context, i)).getSimOperator();
                }
            } else if (Build.VERSION.SDK_INT == 26) {
                try {
                    int c2 = c(context, i);
                    return Class.forName("android.telephony.TelephonyManager").getMethod("getSimOperator", Integer.TYPE).invoke(telephonyManager, Integer.valueOf(c2)) + "";
                } catch (ClassNotFoundException unused) {
                    loq.b(c, "Unable to find class android.telephony.TelephonyManager");
                } catch (IllegalAccessException | InvocationTargetException unused2) {
                    loq.b(c, "invoke exception");
                } catch (NoSuchMethodException unused3) {
                    loq.b(c, "getSimOperator method not found");
                } catch (Exception unused4) {
                    loq.b(c, "other exception");
                }
            } else {
                loq.b(str, "SDK_INT less than O");
            }
        }
        return "";
    }

    private static Map a(Context context, int i) {
        String b = b(context, i);
        loq.c(c, "getCarrierConfigHashMap slotId = " + i);
        Map map = f14814a.get(b);
        if (map != null) {
            return map;
        }
        Map a2 = lnf.a(context, b);
        f14814a.put(b, a2);
        return a2;
    }

    public static String a(String str, String str2) {
        return e(loy.e().b(), str, loy.e().d(), str2);
    }

    public static String e(Context context, String str, int i, String str2) {
        Object obj;
        Map a2 = a(context, i);
        return (a2 == null || (obj = a2.get(str)) == null || !String.class.isInstance(obj)) ? str2 : (String) String.class.cast(obj);
    }

    public static Boolean e(String str, Boolean bool) {
        Object obj;
        Map a2 = a(loy.e().b(), loy.e().d());
        return (a2 == null || (obj = a2.get(str)) == null || !Boolean.class.isInstance(obj)) ? bool : (Boolean) Boolean.class.cast(obj);
    }

    public static Boolean a(Context context, String str, int i, Boolean bool) {
        Object obj;
        Map a2 = a(context, i);
        return (a2 == null || (obj = a2.get(str)) == null || !Boolean.class.isInstance(obj)) ? bool : (Boolean) Boolean.class.cast(obj);
    }

    public static String c(HttpURLConnection httpURLConnection) {
        loq.c(c, "dealGzipContent");
        try {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(httpURLConnection.getInputStream());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = gZIPInputStream.read(bArr);
                        if (read != -1) {
                            byteArrayOutputStream.write(bArr, 0, read);
                        } else {
                            String byteArrayOutputStream2 = byteArrayOutputStream.toString("utf-8");
                            byteArrayOutputStream.close();
                            gZIPInputStream.close();
                            return byteArrayOutputStream2;
                        }
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException unused) {
            loq.b(c, "dealGzipContent IOException");
            return "";
        }
    }

    public static byte[] d(String str) {
        loq.c(c, "compressToGzip");
        if (str == null || str.isEmpty()) {
            return new byte[0];
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(str.getBytes("utf-8"));
                    gZIPOutputStream.finish();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    gZIPOutputStream.close();
                    byteArrayOutputStream.close();
                    return byteArray;
                } finally {
                }
            } finally {
            }
        } catch (IOException unused) {
            loq.b(c, "compress: fail");
            return new byte[0];
        }
    }

    public static String d() {
        if (loy.e().b() == null) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) loy.e().b().getSystemService("phone");
        return loy.e().d() == -1 ? telephonyManager.getSimState() == 5 ? telephonyManager.getSimSerialNumber() : "" : telephonyManager.getSimState(loy.e().d()) == 5 ? telephonyManager.createForSubscriptionId(c(loy.e().b(), loy.e().d())).getSimSerialNumber() : "";
    }

    public static String b() {
        String language = Locale.getDefault().getLanguage();
        return (language == "en" || language == "tr") ? language : "tr";
    }
}
