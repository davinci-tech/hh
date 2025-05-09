package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.google.gson.Gson;
import com.huawei.haf.store.SharedStoreManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.LogUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class tno {
    private static String a(String str) {
        if (str == null) {
            LogUtil.a("ReconnectDeviceUtil", "getValue, key is null");
            return "[]";
        }
        return SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").getString(str, "[]");
    }

    private static Map<String, Map<String, String>> e(String str) {
        return (Map) new Gson().fromJson(a(str), HashMap.class);
    }

    public static Map<String, Map<String, String>> d() {
        Map<String, Map<String, String>> e = e();
        e.putAll(b());
        return e;
    }

    public static Map<String, Map<String, String>> e() {
        Map<String, Map<String, String>> e = e("ble_reconnect_devices");
        e.putAll(e("old_ble_reconnect_devices"));
        return e;
    }

    public static Map<String, Map<String, String>> b() {
        Map<String, Map<String, String>> e = e("br_reconnect_devices");
        e.putAll(e("old_br_reconnect_devices"));
        return e;
    }

    public static boolean c() {
        HashSet<String> h = h();
        if (h == null || h.isEmpty()) {
            LogUtil.c("ReconnectDeviceUtil", "hasBondedDevices devices isEmpty");
            return false;
        }
        Set<BluetoothDevice> a2 = a();
        if (a2 == null || a2.isEmpty()) {
            LogUtil.c("ReconnectDeviceUtil", "hasBondedDevices bondedDevices isEmpty");
            return false;
        }
        Iterator<BluetoothDevice> it = a2.iterator();
        while (it.hasNext()) {
            if (h.contains(it.next().getAddress())) {
                LogUtil.c("ReconnectDeviceUtil", "hasBondedDevices true");
                return true;
            }
        }
        LogUtil.c("ReconnectDeviceUtil", "hasBondedDevices false");
        return false;
    }

    private static HashSet<String> h() {
        try {
            HashSet<String> hashSet = (HashSet) new Gson().fromJson(a("identitys"), HashSet.class);
            Map<String, String> a2 = SharedPreferenceManager.a(BaseApplication.getContext(), String.valueOf(1000));
            if (a2 == null) {
                LogUtil.c("ReconnectDeviceUtil", "maps is null.");
                return hashSet;
            }
            Iterator<Map.Entry<String, String>> it = a2.entrySet().iterator();
            while (it.hasNext()) {
                Map map = (Map) new Gson().fromJson(it.next().getValue(), HashMap.class);
                if (map != null) {
                    hashSet.add((String) map.get("mDeviceIdentify"));
                }
            }
            LogUtil.c("ReconnectDeviceUtil", "getDevices devices size : ", Integer.valueOf(hashSet.size()));
            return hashSet;
        } catch (Exception unused) {
            LogUtil.e("ReconnectDeviceUtil", "getDevices gson internal error.");
            return null;
        }
    }

    private static Set<BluetoothDevice> a() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return Collections.EMPTY_SET;
        }
        try {
            return defaultAdapter.getBondedDevices();
        } catch (SecurityException unused) {
            LogUtil.e("ReconnectDeviceUtil", "getBondedDevices error.");
            return null;
        }
    }
}
