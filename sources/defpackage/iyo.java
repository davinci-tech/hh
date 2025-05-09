package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.iconnect.IWearConnectService;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.LogUtil;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class iyo {
    private static boolean j;

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13669a = new Object();
    private static ConcurrentHashMap<String, Long> d = new ConcurrentHashMap<>(16);
    private static int e = 0;
    private static IWearConnectService h = null;
    private static int b = 0;
    private static boolean g = false;
    private static List<Integer> c = new ArrayList(16);

    private static izb c(izb izbVar, boolean z) {
        if (z) {
            return null;
        }
        return izbVar;
    }

    public static List<Integer> a() {
        List<Integer> list;
        synchronized (f13669a) {
            list = c;
        }
        return list;
    }

    public static izf e(int i, DeviceInfo deviceInfo) {
        String str;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "Start to get phone mac address.");
        String str2 = "";
        if (!bld.d()) {
            str = "";
        } else if (b()) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "iconnect action exist.");
            str = b("");
        } else {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "iconnect action do not exist so start to get by settings.");
            try {
                str = Settings.Secure.getString(BaseApplication.e().getContentResolver(), "bluetooth_address");
            } catch (SecurityException unused) {
                LogUtil.e("BTHandshakeManager", "btMacAddress exception");
                str = "";
            }
            try {
                if (TextUtils.isEmpty(str)) {
                    str = f();
                }
            } catch (SocketException unused2) {
                LogUtil.e("BTHandshakeManager", "btMacAddress SocketException");
            }
        }
        String c2 = iym.c(str, "FF:FF:FF:FF:FF:CC");
        String str3 = blq.b(7) + blq.b(c2.length()) + blq.b(c2);
        if (2 == i) {
            str2 = blq.b(9) + blq.b(0);
        }
        return iym.c(blq.a(blq.b(1) + blq.b(15) + (blq.b(1) + blq.b(0)) + (blq.b(3) + blq.b(6) + blq.b(e(deviceInfo, bml.d()))) + (blq.b(4) + blq.b(1) + blq.b(2)) + (blq.b(5) + blq.b(0)) + str3 + str2));
    }

    public static String f() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        String str = null;
        if (networkInterfaces == null) {
            com.huawei.hwlogsmodel.LogUtil.h("BTHandshakeManager", "can not get network interface !");
            return null;
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface nextElement = networkInterfaces.nextElement();
            if ("wlan0".equals(nextElement.getName())) {
                byte[] hardwareAddress = nextElement.getHardwareAddress();
                if (hardwareAddress == null || hardwareAddress.length == 0) {
                    com.huawei.hwlogsmodel.LogUtil.h("BTHandshakeManager", "addressByte is empty");
                } else {
                    StringBuilder sb = new StringBuilder(10);
                    for (byte b2 : hardwareAddress) {
                        sb.append(String.format(Locale.ENGLISH, "%02X:", Byte.valueOf(b2)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    str = sb.toString();
                }
            }
        }
        return str;
    }

    private static String e(DeviceInfo deviceInfo, String str) {
        if (deviceInfo == null) {
            return str;
        }
        String b2 = iyq.b(BaseApplication.e()).b(deviceInfo.getDeviceIdentify(), BaseApplication.e());
        if (!"".equals(b2)) {
            return blq.d(b2);
        }
        iyq.b(BaseApplication.e()).d(deviceInfo.getDeviceIdentify(), blq.b(str), "btsdk_sharedpreferences_bindid", BaseApplication.e());
        return str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static String b(String str) {
        String str2 = "finally";
        IWearConnectService iWearConnectService = h;
        if (iWearConnectService != null) {
            try {
                str = iWearConnectService.getHuaweiPhoneIndex();
            } catch (RemoteException e2) {
                LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "RemoteException: ", ExceptionUtils.d(e2));
            } catch (SecurityException e3) {
                LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "SecurityException: ", ExceptionUtils.d(e3));
            } finally {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", str2);
            }
        } else {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "mIConnectService is null.");
        }
        return str;
    }

    public static izb d(String str, Context context, byte[] bArr) {
        izb c2;
        if (TextUtils.isEmpty(str) || context == null || bArr == null || (c2 = c(str, context, bArr)) == null) {
            return null;
        }
        String d2 = blq.d(bArr);
        try {
            boolean z = false;
            for (bmi bmiVar : new bmn().c(d2.substring(4, d2.length())).b()) {
                int e2 = bli.e(bmiVar.e());
                if (e2 == 1) {
                    c2.d(bli.e(bmiVar.c()));
                } else if (e2 == 2) {
                    c2.a(bli.e(bmiVar.c()));
                } else if (e2 == 4) {
                    c2.c(bli.e(bmiVar.c()));
                } else if (e2 == 5) {
                    c2.e(bli.e(bmiVar.c()));
                } else if (e2 == 9) {
                    try {
                        d.put(str, Long.valueOf(Long.parseLong(bmiVar.c(), 16)));
                    } catch (NumberFormatException unused) {
                        LogUtil.e("BTHandshakeManager", "resolveBTDeviceBondStatus NumberFormatException");
                    }
                } else if (e2 == 127) {
                    z = true;
                }
            }
            return c(c2, z);
        } catch (bmk unused2) {
            return new izb();
        }
    }

    private static izb c(String str, Context context, byte[] bArr) {
        if (bArr == null || context == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBindStatusResponse with parameter is not correct.");
            return null;
        }
        izb izbVar = new izb();
        if (d.get(str) != null) {
            d.put(str, 0L);
        }
        return izbVar;
    }

    public static izf bDm_(Context context, BluetoothDevice bluetoothDevice, DeviceInfo deviceInfo, boolean z) {
        String c2;
        String str;
        if (context == null) {
            return null;
        }
        String a2 = a(deviceInfo, z, context);
        String bDl_ = bDl_(bluetoothDevice, deviceInfo, z);
        iyq.b(BaseApplication.e()).d(bDl_, blq.b(a2), "btsdk_sharedpreferences_bindid", BaseApplication.e());
        String str2 = "";
        if (deviceInfo != null) {
            if (2 == deviceInfo.getDeviceProtocol()) {
                byte[] n = iym.n();
                if (n != null) {
                    String b2 = iyq.b(context).b(b, bDl_, context, n, deviceInfo);
                    if (b2 != null && b2.length() > 64) {
                        str2 = iym.e(b2);
                        String substring = b2.substring(0, 32);
                        str = blq.b(7) + blq.b(substring.length() / 2) + substring;
                    } else {
                        LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "key:null.");
                        str = "";
                    }
                    return e(deviceInfo, a2, str2, str);
                }
                String b3 = iyq.b(context).b(b, bDl_, context, null, deviceInfo);
                c2 = blq.b(6) + blq.b(b3.length() / 2) + b3;
            } else {
                c2 = b(context, deviceInfo, bDl_);
            }
        } else {
            c2 = c(context, bDl_);
        }
        str2 = c2;
        str = "";
        return e(deviceInfo, a2, str2, str);
    }

    private static String bDl_(BluetoothDevice bluetoothDevice, DeviceInfo deviceInfo, boolean z) {
        String address = bluetoothDevice == null ? "" : bluetoothDevice.getAddress();
        return (!z || deviceInfo == null) ? address : deviceInfo.getDeviceIdentify();
    }

    private static izf e(DeviceInfo deviceInfo, String str, String str2, String str3) {
        String c2 = c(deviceInfo, m());
        String str4 = blq.b(1) + blq.b(14) + s() + t() + (blq.b(5) + blq.b(6) + blq.b(str)) + str2 + str3 + c2;
        LogUtil.c("BTHandshakeManager", "commandHex: ", str4);
        return iym.e(blq.a(str4));
    }

    private static String m() {
        try {
            iym.d();
            String b2 = blq.b(BluetoothAdapter.getDefaultAdapter().getName());
            String a2 = blq.a(b2.length() / 2);
            return blq.b(9) + a2 + b2;
        } catch (SecurityException e2) {
            LogUtil.e("BTHandshakeManager", "dealPhoneMode SecurityException:", ExceptionUtils.d(e2));
            return "";
        }
    }

    private static String b(Context context, DeviceInfo deviceInfo, String str) {
        String b2 = iyq.b(context).b(b, str, context, null, deviceInfo);
        return blq.b(6) + blq.b(b2.length() / 2) + b2;
    }

    private static String c(Context context, String str) {
        LogUtil.a("BTHandshakeManager", "requestBindBTDevice deviceInfo is null");
        return c(context, str, (DeviceInfo) null);
    }

    private static String c(DeviceInfo deviceInfo, String str) {
        if (deviceInfo == null) {
            return str;
        }
        int c2 = iym.c(deviceInfo.getDeviceName());
        LogUtil.c("BTHandshakeManager", "onlyAw70ProductType : ", Integer.valueOf(c2));
        return bku.e(c2) ? "" : str;
    }

    private static String a(DeviceInfo deviceInfo, boolean z, Context context) {
        String d2 = bml.d();
        if (deviceInfo != null) {
            String b2 = iyq.b(BaseApplication.e()).b(deviceInfo.getDeviceIdentify(), BaseApplication.e());
            if (!"".equals(b2)) {
                d2 = blq.d(b2);
            }
        }
        if (!z) {
            return d2;
        }
        String c2 = bkx.c();
        return (c2 == null || c2.length() < 6) ? c2 : c2.substring(c2.length() - 6);
    }

    private static String t() {
        return blq.b(3) + blq.b(1) + blq.b(0);
    }

    private static String s() {
        return blq.b(1) + blq.b(0);
    }

    private static String c(Context context, String str, DeviceInfo deviceInfo) {
        String b2 = iyq.b(context).b(b, str, context, null, deviceInfo);
        if (TextUtils.isEmpty(b2)) {
            return "";
        }
        return blq.b(6) + blq.b(b2.length() / 2) + b2;
    }

    public static int f(Context context, byte[] bArr) {
        if (context == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "context is null.");
        }
        if (bArr == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveRequestPairResponse with parameter is not correct.");
            return 0;
        }
        String d2 = blq.d(bArr);
        try {
            List<bmi> b2 = new bmn().c(d2.substring(4, d2.length())).b();
            if (b2.size() > 0) {
                return bli.e(b2.get(0).c());
            }
            return 0;
        } catch (bmk unused) {
            LogUtil.e("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "resolveBTDeviceLinkParameter tlv resolve exception");
            return 0;
        }
    }

    public static izf a(int i) {
        LogUtil.c("BTHandshakeManager", "Enter getBTDeviceLinkParameter with mBtType:" + i);
        return iym.b(i);
    }

    public static izh a(Context context, byte[] bArr) {
        if (bArr == null || context == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceLinkParameter with parameter is not correct.");
            return null;
        }
        izh izhVar = new izh();
        String d2 = blq.d(bArr);
        try {
            boolean z = false;
            for (bmi bmiVar : new bmn().c(d2.substring(4, d2.length())).b()) {
                int e2 = bli.e(bmiVar.e());
                if (e2 == 1) {
                    izhVar.e(bli.e(bmiVar.c()));
                } else if (e2 == 2) {
                    izhVar.b(bli.e(bmiVar.c()));
                } else if (e2 == 3) {
                    izhVar.h(bli.e(bmiVar.c()));
                } else if (e2 == 127) {
                    z = true;
                }
                izhVar = d(bmiVar, izhVar);
            }
            if (z) {
                return null;
            }
            return izhVar;
        } catch (bmk unused) {
            LogUtil.e("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceLinkParameter tlv resolve exception");
            return null;
        }
    }

    private static izh d(bmi bmiVar, izh izhVar) {
        switch (bli.e(bmiVar.e())) {
            case 4:
                izhVar.d(bli.e(bmiVar.c()));
                return izhVar;
            case 5:
                String c2 = bmiVar.c();
                if (36 == c2.length()) {
                    String substring = c2.substring(0, 4);
                    int e2 = bli.e(substring);
                    b = e2;
                    izhVar.a(e2);
                    String substring2 = c2.substring(4, 36);
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceLinkParameter Authentic version : ", substring, " resolveBTDeviceLinkParameter Authentic randA info : ", substring2);
                    izhVar.d(substring2);
                } else {
                    LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceLinkParameter error with handshake parameter is incorrect.");
                }
                return izhVar;
            case 6:
                izhVar.i(bli.e(bmiVar.c()));
                return izhVar;
            case 7:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceLinkParameter COMMAND_ID_SUPPORT_AUTH_TYPE:", bmiVar.c());
                izhVar.f(bli.e(bmiVar.c()));
                return izhVar;
            case 8:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceLinkParameter COMMAND_ID_AUTH_ALGORITHM:", bmiVar.c());
                izhVar.c(bli.e(bmiVar.c()));
                return izhVar;
            default:
                LogUtil.a("BTHandshakeManager", "resolveBTDeviceLinkParameterElse default:");
                return izhVar;
        }
    }

    public static izf a(int i, int i2) {
        e = i;
        return iym.e(i, i2);
    }

    public static izf e(boolean z) {
        izf a2 = iym.a(c());
        LogUtil.c("BTHandshakeManager", "setBTDeviceGATTTime isRetry:", Boolean.valueOf(z));
        if (z) {
            j = true;
        }
        return a2;
    }

    public static byte[] c() {
        int offset = bhh.b().getOffset(System.currentTimeMillis()) / 3600;
        int i = offset / 1000;
        int abs = (Math.abs(offset % 1000) * 60) / 1000;
        if (i < 0) {
            i = Math.abs(i) + 128;
        }
        int i2 = (i << 8) + abs;
        String str = blq.b(2) + blq.b(2) + blq.b(i2 >> 8) + blq.b(i2 & 255);
        String b2 = blq.b(1);
        String b3 = blq.b(5);
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        if (((int) (System.currentTimeMillis() % 1000)) >= 500) {
            currentTimeMillis++;
        }
        return blq.a(b2 + b3 + (blq.b(1) + blq.b(4) + blq.b(currentTimeMillis >> 24) + blq.b((currentTimeMillis >> 16) & 255) + blq.b((currentTimeMillis >> 8) & 255) + blq.b(currentTimeMillis & 255)) + str);
    }

    public static boolean[] e(Context context, byte[] bArr) {
        boolean[] zArr = {false, false};
        if (context == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "context is null.");
        }
        if (bArr == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceGATTTime with parameter is not correct.");
            return zArr;
        }
        String d2 = blq.d(bArr);
        try {
            a(new bmn().c(d2.substring(4, d2.length())), zArr);
            if (j) {
                j = false;
            }
            return zArr;
        } catch (bmk unused) {
            LogUtil.e("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceLinkParameter tlv resolve exception");
            return zArr;
        }
    }

    private static void a(bmj bmjVar, boolean[] zArr) {
        List<bmi> b2 = bmjVar.b();
        LogUtil.c("BTHandshakeManager", "parseGattTimeData");
        for (bmi bmiVar : b2) {
            int e2 = bli.e(bmiVar.e());
            if (e2 == 1) {
                int e3 = bli.e(bmiVar.c());
                int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
                int abs = Math.abs(currentTimeMillis - e3);
                LogUtil.c("BTHandshakeManager", "parseGattTimeData currentTime:", Integer.valueOf(currentTimeMillis), ",replyTime:", Integer.valueOf(e3), " parseGattTimeData offset:", Integer.valueOf(abs));
                if (abs >= 1 && !j) {
                    zArr[1] = true;
                }
            } else if (e2 == 2) {
                LogUtil.c("BTHandshakeManager", "parseGattTimeData ZONE");
            } else if (e2 == 127) {
                if (bli.e(bmiVar.c()) == 100000) {
                    zArr[0] = true;
                }
            } else {
                LogUtil.a("BTHandshakeManager", "parseGattTimeData default");
            }
        }
    }

    public static izf d(Context context, int i, String str, String str2, DeviceInfo deviceInfo) {
        if (context == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "context is null.");
        }
        if (str == null || str2 == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "requestAuthenticBTDevice with parameter is not correct.");
            return null;
        }
        return iym.c(i, str, str2, deviceInfo);
    }

    public static String d(byte[] bArr) {
        String str = "";
        if (bArr == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveAuthenticBTDevice with parameter is not correct.");
            return "";
        }
        String d2 = blq.d(bArr);
        try {
            for (bmi bmiVar : new bmn().c(d2.substring(4, d2.length())).b()) {
                if (bli.e(bmiVar.e()) == 1) {
                    str = bmiVar.c();
                }
            }
            return str;
        } catch (bmk unused) {
            LogUtil.e("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveAuthenticBTDevice tlv resolve exception");
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0094, code lost:
    
        if (r11[2] == 'y') goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static defpackage.izf a(android.content.Context r11) {
        /*
            if (r11 != 0) goto L4
            r11 = 0
            return r11
        L4:
            r0 = 13
            byte[] r0 = new byte[r0]
            r1 = 0
            r2 = 1
            r0[r1] = r2
            r3 = 5
            r0[r2] = r3
            long r3 = java.lang.System.currentTimeMillis()
            java.util.Date r5 = new java.util.Date
            r5.<init>(r3)
            java.text.SimpleDateFormat r3 = new java.text.SimpleDateFormat
            java.lang.String r4 = "yy MM dd HH mm ss"
            java.util.Locale r6 = java.util.Locale.US
            r3.<init>(r4, r6)
            java.lang.String r3 = r3.format(r5)
            java.lang.String r4 = " "
            java.lang.String[] r3 = r3.split(r4)
            r4 = 2
            r6 = 10
            r0[r4] = r6
            r7 = 9
            r8 = 3
            r0[r8] = r7
            r7 = r1
        L37:
            r9 = 6
            if (r7 >= r9) goto L4e
            r9 = r3[r7]
            r10 = 16
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9, r10)
            int r9 = r9.intValue()
            byte r9 = (byte) r9
            int r10 = r7 + 4
            r0[r10] = r9
            int r7 = r7 + 1
            goto L37
        L4e:
            java.util.Calendar r3 = java.util.Calendar.getInstance()
            r3.setTime(r5)
            r5 = 7
            int r3 = r3.get(r5)
            if (r3 != r2) goto L5d
            goto L5f
        L5d:
            int r9 = r3 + (-2)
        L5f:
            byte r3 = (byte) r9
            r0[r6] = r3
            boolean r3 = android.text.format.DateFormat.is24HourFormat(r11)
            r5 = 11
            if (r3 == 0) goto L6d
            r0[r5] = r2
            goto L6f
        L6d:
            r0[r5] = r4
        L6f:
            char[] r11 = android.text.format.DateFormat.getDateFormatOrder(r11)
            if (r11 == 0) goto L97
            int r3 = r11.length
            if (r3 != r8) goto L97
            char r1 = r11[r1]
            r3 = 121(0x79, float:1.7E-43)
            r5 = 77
            r6 = 100
            if (r1 != r6) goto L8c
            char r7 = r11[r2]
            if (r7 != r5) goto L8c
            char r7 = r11[r4]
            if (r7 != r3) goto L8c
            r2 = r4
            goto L98
        L8c:
            if (r1 != r5) goto L97
            char r1 = r11[r2]
            if (r1 != r6) goto L97
            char r11 = r11[r4]
            if (r11 != r3) goto L97
            goto L98
        L97:
            r2 = r8
        L98:
            r11 = 12
            byte r1 = (byte) r2
            r0[r11] = r1
            izf r11 = defpackage.iym.b(r0)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iyo.a(android.content.Context):izf");
    }

    public static byte[] d(Context context, byte[] bArr, String str, DeviceInfo deviceInfo) {
        if (context == null || bArr == null || str == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "encryptTLVsV1 with parameter is not correct.");
            return (byte[]) new byte[0].clone();
        }
        byte[] bArr2 = new byte[3];
        byte[] bArr3 = new byte[bArr.length - 3];
        blt.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, bArr, 0, "BTHandshakeManager", "encryptTLVsV1 before encrypt commands:");
        System.arraycopy(bArr, 0, bArr2, 0, 3);
        System.arraycopy(bArr, 3, bArr3, 0, bArr.length - 3);
        String str2 = blq.d(bArr2) + blq.d(iym.c(context, bArr3, deviceInfo, (byte[]) null));
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "encryptTLVsV1 After encrypt result =", str2);
        return blq.a(str2);
    }

    public static byte[] c(Context context, byte[] bArr, String str, boolean z, DeviceInfo deviceInfo) {
        if (context == null || bArr == null || str == null) {
            return (byte[]) new byte[0].clone();
        }
        byte[] bArr2 = new byte[2];
        byte[] bArr3 = new byte[bArr.length - 2];
        byte[] a2 = a(bArr, bArr2, bArr3);
        if (a2 == null) {
            return (byte[]) new byte[0].clone();
        }
        if (d.get(str) != null) {
            long longValue = d.get(str).longValue();
            if (longValue != 0) {
                if (longValue == 4294967295L) {
                    d.put(str, 1L);
                } else if (z) {
                    longValue++;
                    d.put(str, Long.valueOf(longValue));
                } else {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "encryptTLVsV2 send isNeedEncrypt: ", Boolean.valueOf(z));
                }
                byte[] a3 = blq.a(blq.d(longValue));
                if (a3 != null && a3.length == 4 && 16 == a2.length) {
                    a2[12] = a3[0];
                    a2[13] = a3[1];
                    a2[14] = a3[2];
                    a2[15] = a3[3];
                }
            }
        }
        byte[] c2 = iym.c(context, bArr3, deviceInfo, a2);
        if (c2 == null) {
            return (byte[]) new byte[0].clone();
        }
        return blq.a(blq.d(bArr2) + r() + a(blq.d(a2)) + e(blq.d(c2)));
    }

    private static String e(String str) {
        return blq.b(126) + blq.a(str.length() / 2) + str;
    }

    private static String a(String str) {
        return blq.b(125) + blq.b(str.length() / 2) + str;
    }

    private static String r() {
        return blq.b(124) + blq.b(1) + blq.b(1);
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        System.arraycopy(bArr, 0, bArr2, 0, 2);
        System.arraycopy(bArr, 2, bArr3, 0, bArr.length - 2);
        byte[] n = iym.n();
        if (n != null) {
            return n;
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "randIv is null");
        return null;
    }

    public static String c(int i, int i2, int i3, int i4, int i5) {
        String str = blq.b(127) + blq.b(4) + blq.d(100009L);
        if (2 != i3 && 1 != i3 && i3 != 0) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "The link layer version is not match.");
            return "";
        }
        if (2 == i3) {
            return blq.b(i) + blq.b(i2) + str;
        }
        if (1 != i3) {
            return str;
        }
        return blq.b(i4) + blq.b(i5) + blq.b(5) + str;
    }

    public static boolean c(Context context, int i, byte[] bArr) {
        int i2;
        if (bArr != null && context != null) {
            if (i != 1 && i != 2) {
                if (i == 0) {
                    if (8 == bArr.length && Byte.MAX_VALUE == bArr[2]) {
                        String d2 = blq.d(bArr);
                        if (100009 == bli.e(d2.substring(d2.length() - 8, d2.length()))) {
                            return true;
                        }
                    }
                    return false;
                }
                LogUtil.a("BTHandshakeManager", "linkVersion is other");
                return false;
            }
            String d3 = blq.d(bArr);
            try {
                List<bmi> b2 = new bmn().c(d3.substring(4, d3.length())).b();
                if (b2.size() > 0) {
                    try {
                        i2 = bli.e(b2.get(0).e());
                    } catch (NumberFormatException unused) {
                        LogUtil.e("BTHandshakeManager", "checkTimeoutInfo occur NumberFormatException");
                        i2 = 0;
                    }
                    if (b(b2, i2)) {
                        return true;
                    }
                }
                return false;
            } catch (bmk unused2) {
                LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "checkTimeOutInfo tlv resolve exception");
            }
        }
        return true;
    }

    private static boolean b(List<bmi> list, int i) {
        int i2;
        if (127 == i) {
            try {
                i2 = bli.e(list.get(0).c());
            } catch (NumberFormatException unused) {
                LogUtil.e("BTHandshakeManager", "NumberFormatException");
                i2 = 0;
            }
            if (100000 != i2) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "error code = " + i2);
                return true;
            }
        }
        return false;
    }

    public static JSONObject b(Context context, byte[] bArr) {
        if (context == null || bArr == null) {
            return new JSONObject();
        }
        JSONObject jSONObject = new JSONObject();
        try {
        } catch (JSONException unused) {
            p();
        }
        if (c(context, bArr, e == 0 ? 15 : 5)) {
            return jSONObject.put("type", -1);
        }
        String d2 = blq.d(bArr);
        try {
            for (bmi bmiVar : new bmn().c(d2.substring(4, d2.length())).b()) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "the case is ", Integer.valueOf(bli.e(bmiVar.e())));
                int e2 = bli.e(bmiVar.e());
                if (e2 == 1) {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "has HandshakeConstant.BT_VERSION");
                    jSONObject.put(PluginPayAdapter.KEY_DEVICE_INFO_BT_VERSION, blq.d(bmiVar.c()));
                } else if (e2 == 2) {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "has HandshakeConstant.DEVICE_PRODUCT_TYPE_VALUE");
                    jSONObject.put("type", bli.e(bmiVar.c()));
                }
                jSONObject = a(bmiVar, jSONObject);
            }
            return jSONObject;
        } catch (bmk unused2) {
            return jSONObject.put("type", -1);
        }
    }

    private static void p() {
        LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "put params error");
    }

    private static boolean c(Context context, byte[] bArr, int i) throws JSONException {
        if (bArr == null || context == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "parameter is incorrect.");
            return true;
        }
        if (i == bArr.length) {
            return false;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "command length is incorrect.", Integer.valueOf(bArr.length));
        return false;
    }

    private static JSONObject a(bmi bmiVar, JSONObject jSONObject) {
        try {
            int e2 = bli.e(bmiVar.e());
            if (e2 == 3) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "has DEVICE_VERSION: ", bmiVar.c());
                jSONObject.put("device_version", blq.d(bmiVar.c()));
            } else if (e2 == 7) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "has DEVICE_SOFT_VERSION: ", bmiVar.c());
                jSONObject.put(PluginPayAdapter.KEY_DEVICE_INFO_SOFT_VERSION, blq.d(bmiVar.c()));
            } else if (e2 == 12) {
                jSONObject.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, blq.d(bmiVar.c()));
            } else if (e2 == 9) {
                jSONObject.put("UUID", bmiVar.c());
            } else if (e2 == 10) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "has DEVICE_MODEL:", bmiVar.c());
                jSONObject.put(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, blq.d(bmiVar.c()));
            } else if (e2 == 14) {
                jSONObject.put("force_sn", bli.e(bmiVar.c()));
            } else if (e2 == 15) {
                LogUtil.c("BTHandshakeManager", "ota_package_name:", blq.d(bmiVar.c()));
                jSONObject.put("device_ota_package_name", blq.d(bmiVar.c()));
            } else if (e2 == 17) {
                LogUtil.c("BTHandshakeManager", "COMMAND_ID_CERT_MODEL not null: ", blq.d(bmiVar.c()));
                jSONObject.put("cert_model", blq.d(bmiVar.c()));
            } else if (e2 == 18) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "COMMAND_ID_POWER_SAVE not null: ", Integer.valueOf(bli.e(bmiVar.c())));
                jSONObject.put("power_save", bli.e(bmiVar.c()));
            } else {
                b(bmiVar, jSONObject);
            }
        } catch (JSONException unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "createJsonObject fail");
        }
        return jSONObject;
    }

    private static void b(bmi bmiVar, JSONObject jSONObject) throws JSONException {
        int e2 = bli.e(bmiVar.e());
        if (e2 == 21) {
            LogUtil.c("BTHandshakeManager", "COMMAND_ID_PRODUCT_ID not null: ", blq.d(bmiVar.c()));
            jSONObject.put("hilink_device_id", blq.d(bmiVar.c()));
        }
        if (e2 == 22) {
            LogUtil.c("BTHandshakeManager", "COMMAND_ID_CERT_MODEL not null: ", blq.d(bmiVar.c()));
            jSONObject.put("device_manufacture", blq.d(bmiVar.c()));
            return;
        }
        if (e2 == 26) {
            LogUtil.c("BTHandshakeManager", "COMMAND_ID_VERSION_TYPE not null: ", Integer.valueOf(bli.e(bmiVar.c())));
            jSONObject.put("device_version_type", bli.e(bmiVar.c()));
            return;
        }
        switch (e2) {
            case 29:
                jSONObject.put("udid_from_device", blq.d(bmiVar.c()));
                break;
            case 30:
                jSONObject.put("unconverted_udid", blq.d(bmiVar.c()));
                break;
            case 31:
                jSONObject.put("device_factory_reset", bli.e(bmiVar.c()));
                break;
            case 32:
                jSONObject.put("device_country_code", blq.d(bmiVar.c()));
                break;
            case 33:
                jSONObject.put("device_emui_version", blq.d(bmiVar.c()));
                break;
            case 34:
                jSONObject.put("device_multi_link_ble_mac", blq.d(bmiVar.c()));
                break;
        }
    }

    public static izf b(Context context) {
        if (context == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "context is null.");
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "Enter requestBTDeviceServiceCapability().");
        String b2 = blq.b(1);
        String b3 = blq.b(2);
        StringBuffer stringBuffer = new StringBuffer(16);
        synchronized (f13669a) {
            List<Integer> b4 = ble.b();
            for (int i = 1; i < b4.size(); i++) {
                stringBuffer.append(blq.b(b4.get(i).intValue()));
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        return iym.d(blq.a(b2 + b3 + (blq.b(1) + blq.b(stringBuffer2.length() / 2) + stringBuffer2)));
    }

    public static List<Integer> d(Context context, byte[] bArr) {
        bmj bmjVar;
        if (context == null || bArr == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceServiceCapability with parameter is not correct.");
            return new ArrayList(16);
        }
        String h2 = h(context, bArr);
        if (h2 == null) {
            return new ArrayList(16);
        }
        try {
            bmjVar = new bmn().c(h2.substring(4, h2.length()));
        } catch (bmk unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceLinkParameter tlv resolve exception");
            bmjVar = null;
        }
        if (bmjVar != null) {
            String str = "";
            List<bmi> b2 = bmjVar.b();
            if (b2.size() > 0) {
                str = b2.get(0).c();
            } else {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "TLV info is incorrect.");
            }
            synchronized (f13669a) {
                List<Integer> b3 = ble.b();
                if (str.length() > 0) {
                    return a(str, b3);
                }
                return new ArrayList(16);
            }
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceServiceCapability tlvFather is null.");
        return new ArrayList(16);
    }

    private static List<Integer> a(String str, List<Integer> list) {
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(1);
        c.clear();
        for (int i = 0; i < str.length() / 2; i++) {
            int e2 = e(str, i);
            if (i < list.size() - 1) {
                if (1 == e2) {
                    arrayList.add(list.get(i + 1));
                } else {
                    c.add(list.get(i + 1));
                }
            }
        }
        return arrayList;
    }

    private static int e(String str, int i) {
        int i2 = i * 2;
        return bli.e(str.substring(i2, i2 + 2));
    }

    private static String h(Context context, byte[] bArr) {
        if (bArr == null || context == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceServiceCapability with parameter is not correct.");
        }
        String j2 = j(bArr);
        if (j2 == null) {
            return null;
        }
        return j2;
    }

    private static String j(byte[] bArr) {
        String d2 = blq.d(bArr);
        if (d2 != null && d2.length() != 0) {
            return d2;
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "dataStrInfo is incorrect.");
        return null;
    }

    public static izf b(Context context, List<Integer> list) {
        if (context == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "context is null.");
        }
        if (e(list)) {
            return null;
        }
        boolean b2 = bld.b();
        g = b2;
        Map<Integer, List<Integer>> d2 = ble.d(b2);
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            List<Integer> list2 = d2.get(Integer.valueOf(intValue));
            if (list2 != null && list2.size() > 0) {
                String str = blq.b(2) + blq.b(1) + blq.b(intValue);
                String str2 = blq.b(3) + blq.b(list2.size());
                StringBuffer stringBuffer2 = new StringBuffer(16);
                Iterator<Integer> it2 = list2.iterator();
                while (it2.hasNext()) {
                    stringBuffer2.append(blq.b(it2.next().intValue()));
                }
                String str3 = str2 + stringBuffer2.toString();
                stringBuffer.append(str);
                stringBuffer.append(str3);
            }
        }
        String stringBuffer3 = stringBuffer.toString();
        return iym.b(blq.b(1) + blq.b(3) + (blq.b(129) + blq.a(stringBuffer3.length() / 2) + stringBuffer3));
    }

    private static boolean e(List<Integer> list) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "Enter requestBTDeviceCommandCapability().");
        if (list != null) {
            return false;
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "serviceIdList is null.");
        return true;
    }

    public static izf k() {
        return iym.f();
    }

    public static izf o() {
        return iym.m();
    }

    public static izf e() {
        return iym.b();
    }

    public static izf b(byte[] bArr) {
        return iym.j(bArr);
    }

    public static izf i() {
        return iym.g();
    }

    public static izf g() {
        return iym.j();
    }

    public static izf l() {
        return iym.h();
    }

    public static boolean e(Context context, byte[] bArr, DeviceCapability deviceCapability) {
        bmj bmjVar;
        int i;
        c(context, bArr, deviceCapability);
        String d2 = blq.d(bArr);
        if (d2 == null || d2.length() == 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "dataStrInfo is incorrect for resolveBTDeviceCommandCapability.");
            return false;
        }
        try {
            bmjVar = new bmn().c(d2.substring(4, d2.length()));
        } catch (bmk unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceCommandCapability tlv resolve exception");
            bmjVar = null;
        }
        if (bmjVar == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "tlvFather is null.");
            return false;
        }
        List<bmj> e2 = bmjVar.e();
        g = bld.b();
        Iterator<bmj> it = e2.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            for (bmi bmiVar : it.next().b()) {
                try {
                    i = bli.e(bmiVar.e());
                } catch (NumberFormatException unused2) {
                    LogUtil.e("BTHandshakeManager", "tlv.getTag() NumberFormatException");
                    i = 0;
                }
                if (i == 2) {
                    i2 = bli.e(bmiVar.c());
                } else if (i == 4) {
                    iym.a(context, i2, bmiVar.c(), deviceCapability, g);
                } else {
                    LogUtil.a("BTHandshakeManager", "other command type");
                }
            }
        }
        return true;
    }

    private static void c(Context context, byte[] bArr, DeviceCapability deviceCapability) {
        if (bArr == null || context == null || deviceCapability == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "resolveBTDeviceCommandCapability with parameter is not correct.");
        }
    }

    public static boolean b(byte[] bArr, DeviceCapability deviceCapability) {
        bmj bmjVar;
        if (bArr == null || deviceCapability == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceSupportedActivityType with parameter is not correct.");
            return false;
        }
        String d2 = blq.d(bArr);
        if (d2 == null || d2.length() == 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "dataStrInfo is incorrect.");
            return false;
        }
        try {
            bmjVar = new bmn().c(d2.substring(4, d2.length()));
        } catch (bmk unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceSupportedActivityType tlv resolve exception");
            bmjVar = null;
        }
        if (bmjVar == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "tlvFather is null.");
            return false;
        }
        Iterator<bmj> it = bmjVar.e().iterator();
        while (it.hasNext()) {
            for (bmi bmiVar : it.next().b()) {
                int e2 = bli.e(bmiVar.e());
                if (e2 == 2) {
                    iyz.a(bli.e(bmiVar.c()), deviceCapability);
                } else if (e2 == 3) {
                    iyz.e(bli.e(bmiVar.c()), deviceCapability);
                } else {
                    LogUtil.a("BTHandshakeManager", "other tlv command type");
                }
            }
        }
        return true;
    }

    public static boolean a(byte[] bArr, DeviceCapability deviceCapability) {
        bmj bmjVar;
        if (bArr == null || deviceCapability == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceSupportedNotificationType() with parameter is not correct.");
            return false;
        }
        String d2 = blq.d(bArr);
        if (d2 == null || d2.length() == 0) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceSupportedNotificationType() dataStrInfo is incorrect.");
            return false;
        }
        try {
            bmjVar = new bmn().c(d2.substring(4, d2.length()));
        } catch (bmk unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceSupportedNotificationType tlv resolve exception");
            bmjVar = null;
        }
        if (bmjVar != null) {
            for (bmi bmiVar : bmjVar.b()) {
                if (bli.e(bmiVar.e()) == 1) {
                    iyz.c(bli.e(bmiVar.c()), deviceCapability);
                }
            }
            return true;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveBTDeviceSupportedNotificationType() tlvFather is null.");
        return false;
    }

    public static boolean e(int i, int i2, byte[] bArr) {
        bmj bmjVar;
        String d2 = blq.d(bArr);
        if (d2 == null || d2.length() == 0) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "isSupportedCommand() dataStrInfo is incorrect.");
            return false;
        }
        String substring = d2.substring(4, d2.length());
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "isSupportedCommand() tlvStrInfo:", substring);
        try {
            bmjVar = new bmn().c(substring);
        } catch (bmk unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "isSupportedCommand() tlv resolve exception");
            bmjVar = null;
        }
        if (bmjVar != null) {
            return a(i, i2, false, 0, bmjVar.e());
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "isSupportedCommand() tlvFather is null.");
        return false;
    }

    private static boolean a(int i, int i2, boolean z, int i3, List<bmj> list) {
        g = bld.b();
        Iterator<bmj> it = list.iterator();
        boolean z2 = z;
        int i4 = i3;
        while (it.hasNext()) {
            for (bmi bmiVar : it.next().b()) {
                int e2 = bli.e(bmiVar.e());
                if (e2 != 2) {
                    if (e2 == 4 && i4 != 0) {
                        String c2 = bmiVar.c();
                        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "isSupportedCommand() strCommandIDValueInfo : ", c2);
                        List<Integer> list2 = ble.d(g).get(Integer.valueOf(i4));
                        if (list2 != null && list2.size() > 0) {
                            for (int i5 = 0; i5 < c2.length(); i5 += 2) {
                                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "isSupportedCommand() index : ", Integer.valueOf(i5));
                                boolean a2 = a(c2, i5);
                                if (i2 == list2.get(i5 / 2).intValue()) {
                                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "isSupportedCommand() mFlag : ", Boolean.valueOf(a2));
                                    i4 = 0;
                                    z2 = a2;
                                }
                            }
                        }
                    }
                } else if (i == bli.e(bmiVar.c())) {
                    i4 = i;
                }
            }
        }
        return z2;
    }

    private static boolean a(String str, int i) {
        return bli.e(str.substring(i, i + 2)) == 1;
    }

    public static byte[] b(Context context, byte[] bArr, DeviceInfo deviceInfo) {
        String o;
        if (context == null || deviceInfo == null || i(bArr) || (o = o(bArr)) == null) {
            return null;
        }
        try {
            String str = "";
            String str2 = "";
            int i = 0;
            for (bmi bmiVar : new bmn().c(o).b()) {
                switch (bli.e(bmiVar.e())) {
                    case 124:
                        i = bli.e(bmiVar.c());
                        break;
                    case 125:
                        str = bmiVar.c();
                        break;
                    case 126:
                        str2 = bmiVar.c();
                        break;
                }
            }
            if (1 == i) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "Need desEncrypt.");
                return c(context, bArr, deviceInfo, str, str2);
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "Do not need desEncrypt.");
            return bArr;
        } catch (bmk unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "checkDesEncryptCommand tlv resolve exception");
            return null;
        } catch (NumberFormatException unused2) {
            return n();
        }
    }

    private static byte[] n() {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "checkDesEncryptCommand tlv resolve NumberFormatException.");
        return null;
    }

    private static String o(byte[] bArr) {
        String d2 = blq.d(bArr);
        if (d2.length() < 4) {
            return null;
        }
        return d2.substring(4, d2.length());
    }

    private static boolean i(byte[] bArr) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "Enter checkEncryptCommand().");
        if (bArr != null) {
            return false;
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "dataContent is null.");
        return true;
    }

    private static byte[] c(Context context, byte[] bArr, DeviceInfo deviceInfo, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String a2 = a(context, deviceInfo);
            if (!TextUtils.isEmpty(a2)) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "Start to desEncrypt data.encryptData ", str2, "encryptIv ", str);
                byte[] a3 = iyx.a(1, blq.a(str2), blq.a(a2), blq.a(str));
                if (a3 == null) {
                    return null;
                }
                byte[] bArr2 = new byte[a3.length + 2];
                bArr2[0] = bArr[0];
                bArr2[1] = bArr[1];
                System.arraycopy(a3, 0, bArr2, 2, a3.length);
                return bArr2;
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "key is not incorrect.");
            return null;
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "Encrypt data is incorrect.");
        return null;
    }

    private static String a(Context context, DeviceInfo deviceInfo) {
        return iyq.b(context).e(context, deviceInfo);
    }

    public static izf d() {
        return iym.e();
    }

    public static izf e(int i) {
        return iym.d(i);
    }

    public static int c(Context context, byte[] bArr) {
        if (bArr == null || context == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "getBTDeviceAvailableStatusParameter with parameter is not correct.");
            return -1;
        }
        String d2 = blq.d(bArr);
        try {
            return b(-1, new bmn().c(d2.substring(4, d2.length())));
        } catch (bmk unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "getBTDeviceAvailableStatusParameter tlv resolve exception");
            return -1;
        }
    }

    private static int b(int i, bmj bmjVar) {
        for (bmi bmiVar : bmjVar.b()) {
            try {
                i = Integer.parseInt(bmiVar.c(), 16);
            } catch (NumberFormatException unused) {
                LogUtil.e("BTHandshakeManager", "tlv.getValue() NumberFormatException");
            }
            int e2 = bli.e(bmiVar.e());
            if (e2 == 0) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", " Not support dual phone connection.");
            } else if (e2 == 1) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "current connected.");
            } else if (e2 == 2) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "Has been connected.");
            } else if (e2 == 3) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "Double phone situation.");
            } else if (e2 == 127) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "notify device operation.");
            }
        }
        return i;
    }

    public static izf h() {
        return iym.i();
    }

    public static izf c(String str) {
        return iym.a(str);
    }

    public static izf j() {
        return iym.c();
    }

    public static boolean h(byte[] bArr) {
        if (bArr == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveGoldCard with parameter is not correct.");
            return false;
        }
        String d2 = blq.d(bArr);
        try {
            List<bmi> b2 = new bmn().c(d2.substring(4, d2.length())).b();
            if (b2.size() <= 0) {
                return false;
            }
            int e2 = bli.e(b2.get(0).c());
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "resolveGoldCard result :", Integer.valueOf(e2));
            String binaryString = Integer.toBinaryString(e2);
            int length = binaryString.length();
            if (8 > length) {
                for (int i = 0; i < 8 - length; i++) {
                    binaryString = "0" + binaryString;
                }
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "resolveGoldCard result :", Integer.valueOf(e2), CloudParamKeys.INFO, binaryString);
            return binaryString.substring(4, 5).equals("1");
        } catch (bmk unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveGoldCard tlv resolve exception");
            return false;
        }
    }

    public static boolean[] c(DeviceInfo deviceInfo, byte[] bArr) {
        boolean[] zArr = {false, false};
        if (bArr == null || deviceInfo == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "AutoDetectSwitchStatusAndWorkMode with parameter is not correct.");
            return zArr;
        }
        String d2 = blq.d(bArr);
        try {
            for (bmi bmiVar : new bmn().c(d2.substring(4, d2.length())).b()) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "the case is ", Integer.valueOf(bli.e(bmiVar.e())));
                int e2 = bli.e(bmiVar.e());
                if (e2 == 1) {
                    int e3 = bli.e(bmiVar.c());
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "AutoDetectSwitchStatusAndWorkMode AutoDetectSwitchStatus mode:", Integer.valueOf(e3));
                    zArr[0] = true;
                    deviceInfo.setAutoDetectSwitchStatus(e3);
                } else if (e2 == 2) {
                    int e4 = bli.e(bmiVar.c());
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "AutoDetectSwitchStatusAndWorkMode AutoDetectSwitchStatus status: ", Integer.valueOf(e4));
                    zArr[1] = true;
                    deviceInfo.setFootWearPosition(e4);
                }
            }
            return zArr;
        } catch (bmk unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "AutoDetectSwitchStatusAndWorkMode tlv resolve exception");
            return zArr;
        }
    }

    public static void a(IWearConnectService iWearConnectService) {
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "Enter setIConnectService.");
        if (iWearConnectService == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "setIConnectService with mIConnectService is null.");
        }
        h = iWearConnectService;
    }

    public static boolean b() {
        PackageManager packageManager = BaseApplication.e().getPackageManager();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(new Intent("com.huawei.iconnect.action.WEAR_CONNECT_SERVICE"), 0);
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "getIConnectServiceAction: listInfo is empty.");
            return false;
        }
        ApplicationInfo applicationInfo = null;
        for (ResolveInfo resolveInfo : queryIntentServices) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "pkgName : ", resolveInfo.serviceInfo.packageName, ", service : ", resolveInfo.serviceInfo.name);
            try {
                applicationInfo = packageManager.getApplicationInfo(resolveInfo.serviceInfo.packageName, 0);
            } catch (PackageManager.NameNotFoundException e2) {
                LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "e:", ExceptionUtils.d(e2));
            }
            if (applicationInfo == null) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "getIConnectServiceAction:null:applicationInfo");
            } else {
                boolean z = (applicationInfo.flags & 1) != 0;
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "getIConnectServiceAction:isSystemApp:", Boolean.valueOf(z));
                if (z) {
                    return true;
                }
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "getIConnectServiceAction: not system app");
            }
        }
        return false;
    }

    public static boolean d(byte[] bArr, DeviceCapability deviceCapability) {
        if (bArr == null || deviceCapability == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveSettingRelated with parameter is not correct.");
            return false;
        }
        try {
            for (bmi bmiVar : new bmn().c(blq.d(bArr).substring(4)).b()) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "the case is ", Integer.valueOf(bli.e(bmiVar.e())));
                switch (bli.e(bmiVar.e())) {
                    case 1:
                        d(deviceCapability, bmiVar);
                        break;
                    case 2:
                        a(deviceCapability, bmiVar);
                        break;
                    case 3:
                        i(deviceCapability, bmiVar);
                        break;
                    case 4:
                        b(deviceCapability, bmiVar);
                        break;
                    case 5:
                        e(deviceCapability, bmiVar);
                        break;
                    case 6:
                        c(deviceCapability, bmiVar);
                        break;
                    default:
                        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "resolveSettingRelated default");
                        break;
                }
            }
            return true;
        } catch (bmk unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "resolveSettingRelated tlv resolve exception");
            return false;
        }
    }

    private static void c(DeviceCapability deviceCapability, bmi bmiVar) {
        if (deviceCapability == null || bmiVar == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "params is null");
        } else {
            deviceCapability.configureHmsNotifyUpdate(bli.e(bmiVar.c()) == 1);
        }
    }

    private static void e(DeviceCapability deviceCapability, bmi bmiVar) {
        if (deviceCapability == null || bmiVar == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "capability or tlv is null");
        } else {
            deviceCapability.configureHmsAutoUpdateWifi(bli.e(bmiVar.c()) == 1);
        }
    }

    private static void b(DeviceCapability deviceCapability, bmi bmiVar) {
        if (deviceCapability == null || bmiVar == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "capability or tlv is null");
        } else {
            deviceCapability.configureHmsAutoUpdate(bli.e(bmiVar.c()) == 1);
        }
    }

    private static void i(DeviceCapability deviceCapability, bmi bmiVar) {
        if (deviceCapability == null || bmiVar == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "capability tlv is null");
        } else {
            deviceCapability.setSupportSmartWatchVersionStatus(true);
            deviceCapability.configureSmartWatchVersionTypeValue(bli.e(bmiVar.c()));
        }
    }

    private static void d(DeviceCapability deviceCapability, bmi bmiVar) {
        if (deviceCapability == null || bmiVar == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "capability tlv is null");
            return;
        }
        int e2 = bli.e(bmiVar.c());
        if ((e2 & 1) == 1) {
            deviceCapability.configureSupportLegalPrivacy(true);
        }
        if (((e2 >> 1) & 1) == 1) {
            deviceCapability.configureSupportLegalUserAgreement(true);
        }
        if (((e2 >> 2) & 1) == 1) {
            deviceCapability.configureSupportOpenSourceOpen(true);
        }
        if (((e2 >> 3) & 1) == 1) {
            deviceCapability.configureSupportLegalServiceStatement(true);
        }
        if (((e2 >> 4) & 1) == 1) {
            deviceCapability.configureSupportLegalSourceStatement(true);
        }
        if (((e2 >> 5) & 1) == 1) {
            deviceCapability.configureSupportLegalSystemWebView(true);
        }
    }

    private static int c(byte[] bArr, int i) {
        if (bArr == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveTlvUniqueValue with parameter is not correct.");
            return -1;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "start resolveTlvUniqueValue");
        String d2 = blq.d(bArr);
        try {
            for (bmi bmiVar : new bmn().c(d2.substring(4, d2.length())).b()) {
                if (bli.e(bmiVar.e()) == i) {
                    return bli.e(bmiVar.c());
                }
            }
            return -1;
        } catch (bmk unused) {
            LogUtil.e("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveTlvUniqueValue tlv resolve exception");
            return -1;
        }
    }

    public static boolean a(byte[] bArr) {
        int c2 = c(bArr, 2);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "start resolveAccountJudgmentResults", Integer.valueOf(c2));
        return c2 != 1;
    }

    public static boolean g(byte[] bArr) {
        int c2 = c(bArr, 1);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "start resolveUserSelectRestoreFactory", Integer.valueOf(c2));
        return c2 == 0;
    }

    private static void a(DeviceCapability deviceCapability, bmi bmiVar) {
        if (deviceCapability == null || bmiVar == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "parseSelfUploadDeviceLogCapability capability tlv is null");
            return;
        }
        byte[] a2 = blq.a(bmiVar.c());
        LogUtil.c("BTHandshakeManager", "string bitmap : ", bmiVar.c());
        if (bli.c(a2, 0)) {
            LogUtil.c("BTHandshakeManager", "isSupportSelfUploadDeviceLog : ", true);
            deviceCapability.setSupportSelfUploadDeviceLog(true);
        }
        if (bli.c(a2, 1)) {
            LogUtil.c("BTHandshakeManager", "isSupportCoreSleepNewFile : ", true);
            deviceCapability.configureSupportCoreSleepNewFile(true);
        }
        if (bli.c(a2, 2)) {
            LogUtil.c("BTHandshakeManager", "isSupportRriNewFile : ", true);
            deviceCapability.configureSupportRriNewFile(true);
        }
        if (bli.c(a2, 3)) {
            LogUtil.c("BTHandshakeManager", "isSupportUploadGpsAndPdrFile : ", true);
            deviceCapability.configureIsSupportUploadGpsAndPdrFile(true);
        }
    }

    public static void f(byte[] bArr) {
        if (bArr == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "capability tlv is null");
            return;
        }
        try {
            for (bmi bmiVar : new bmn().c(blq.d(bArr).substring(4)).b()) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "the case is ", Integer.valueOf(bli.e(bmiVar.e())));
                if (bli.e(bmiVar.e()) == 127) {
                    if (bli.e(bmiVar.c()) == 100000) {
                        LogUtil.c("BTHandshakeManager", "zoneId success");
                    }
                } else {
                    LogUtil.a("BTHandshakeManager", "zoneId default");
                }
            }
        } catch (bmk unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "resolveSetTimeZoneId tlv resolve exception");
        }
    }

    public static String e(byte[] bArr) {
        String str = "";
        if (bArr == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "capability tlv is null");
            return "";
        }
        List<bmi> c2 = c(bArr);
        if (c2 == null) {
            return "";
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "resolveExpandCapability info:", c2.toString());
        for (bmi bmiVar : c2) {
            if (bli.e(bmiVar.e()) == 1) {
                str = bmiVar.c();
            } else {
                LogUtil.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTHandshakeManager", "resolveExpandCapability default");
            }
        }
        return str;
    }

    public static List<bmi> c(byte[] bArr) {
        bmj bmjVar;
        if (bArr == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveDataGetTlvFather parameter is not correct.");
            return null;
        }
        String d2 = blq.d(bArr);
        if (d2.length() < 4) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManager", "resolveDataGetTlvFather dataStrInfo error");
            return null;
        }
        try {
            bmjVar = new bmn().c(d2.substring(4));
        } catch (bmk unused) {
            LogUtil.e("0xA0200008", a.t, 0, "BTHandshakeManager", "resolve tlv exception:");
            bmjVar = null;
        }
        if (bmjVar == null) {
            return null;
        }
        return bmjVar.b();
    }
}
