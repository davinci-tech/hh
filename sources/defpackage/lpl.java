package defpackage;

import com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lpl {
    public static URL d(String str, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, int i, Map<String, String> map) {
        StringBuilder sb = new StringBuilder(str);
        HashMap<String, String> d = d(absPrimaryDevice, absPairedDevice, i, map);
        boolean z = true;
        for (Map.Entry<String, String> entry : d.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
            if (!"vers".equals(key) && !key.isEmpty() && !value.isEmpty()) {
                if (z) {
                    sb.append("?");
                    z = false;
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
        }
        sb.append("&vers=");
        sb.append(d.get("vers"));
        try {
            return new URL(sb.toString());
        } catch (MalformedURLException unused) {
            loq.b("OdsaResolveObjectToXml", "getRequestUrl MalformedURLException error.");
            return null;
        }
    }

    private static HashMap<String, String> d(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, int i, Map<String, String> map) {
        String imsi;
        HashMap<String, String> hashMap = new HashMap<>();
        if (absPrimaryDevice == null || absPairedDevice == null) {
            loq.b("OdsaResolveObjectToXml", "primaryDevice == null || pairedDevice == null.");
            return hashMap;
        }
        if (i == 0) {
            b(hashMap, absPrimaryDevice, absPairedDevice);
        } else if (i == 3 || i == 5) {
            e(hashMap, absPrimaryDevice, absPairedDevice);
        } else if (i == 1) {
            d(hashMap, absPrimaryDevice, absPairedDevice, map);
        } else if (i == 2) {
            a(hashMap, absPrimaryDevice, absPairedDevice, map);
        } else if (i == 4) {
            c(hashMap, absPrimaryDevice, map);
        } else {
            loq.d("OdsaResolveObjectToXml", "getRequestMap donothing");
        }
        AuthParam c = loy.e().c();
        if (c == null) {
            loq.b("OdsaResolveObjectToXml", "get from authParam is null.");
            imsi = null;
        } else {
            imsi = c.getImsi();
        }
        if (lop.e("ODSA_ADD_IMSI", (Boolean) false).booleanValue()) {
            hashMap.put("IMSI", imsi);
        }
        loq.b("OdsaResolveObjectToXml", "get from authParam imsi=", imsi);
        return hashMap;
    }

    private static void b(HashMap<String, String> hashMap, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice) {
        hashMap.put("terminal_id", absPrimaryDevice.getTerminalId());
        hashMap.put("token", lpu.b());
        hashMap.put("terminal_vendor", absPrimaryDevice.getTerminalVendor());
        hashMap.put("terminal_model", absPrimaryDevice.getTerminalModel());
        hashMap.put("terminal_sw_version", absPrimaryDevice.getTerminalSwVersion());
        hashMap.put("provisioning_version", "4.0");
        hashMap.put("entitlement_version", lop.a("ODSA_ES_VERSION", "1.0"));
        hashMap.put("app", "ap2006");
        hashMap.put("operation", lpr.b[0]);
        hashMap.put("companion_terminal_id", absPairedDevice.getTerminalId());
        if (lop.e("ODSA_ADD_COMPANION_INFO", (Boolean) false).booleanValue()) {
            hashMap.put("companion_terminal_vendor", "Huawei");
            hashMap.put("companion_terminal_model", absPairedDevice.getTerminalModel());
            hashMap.put("companion_terminal_sw_version", absPairedDevice.getTerminalSwVersion());
        }
        hashMap.put("vers", lop.e("ODSA_SIMPLE_VERS", (Boolean) true).booleanValue() ? "1" : "1 HTTP/1.1");
        hashMap.put("app_name", "Health");
        hashMap.put("app_version", "11.0.6.002");
    }

    private static void e(HashMap<String, String> hashMap, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice) {
        hashMap.put("terminal_id", absPrimaryDevice.getTerminalId());
        hashMap.put("token", lpu.b());
        hashMap.put("terminal_vendor", absPrimaryDevice.getTerminalVendor());
        hashMap.put("terminal_model", absPrimaryDevice.getTerminalModel());
        hashMap.put("provisioning_version", "4.0");
        hashMap.put("terminal_sw_version", absPrimaryDevice.getTerminalSwVersion());
        hashMap.put("app", "ap2006");
        hashMap.put("operation", lpr.b[3]);
        hashMap.put("companion_terminal_id", absPairedDevice.getTerminalId());
        hashMap.put("entitlement_version", lop.a("ODSA_ES_VERSION", "1.0"));
        hashMap.put("app_name", "Health");
        hashMap.put("vers", lop.e("ODSA_SIMPLE_VERS", (Boolean) true).booleanValue() ? "1" : "1 HTTP/1.1");
    }

    private static void d(HashMap<String, String> hashMap, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Map<String, String> map) {
        hashMap.put("terminal_id", absPrimaryDevice.getTerminalId());
        hashMap.put("token", lpu.b());
        hashMap.put("app", "ap2006");
        hashMap.put("operation", lpr.b[1]);
        hashMap.put("provisioning_version", "4.0");
        hashMap.put("companion_terminal_id", absPairedDevice.getTerminalId());
        hashMap.put("companion_terminal_eid", absPairedDevice.getTerminalEid());
        if (lop.e("ODSA_ADD_COMPANION_INFO", (Boolean) false).booleanValue()) {
            hashMap.put("companion_terminal_vendor", "Huawei");
            hashMap.put("companion_terminal_model", absPairedDevice.getTerminalModel());
            hashMap.put("companion_terminal_sw_version", absPairedDevice.getTerminalSwVersion());
            hashMap.put("companion_terminal_service", "SharedNumber");
            hashMap.put("companion_terminal_friendly_name", "Huawei");
        }
        hashMap.put("vers", lop.e("ODSA_SIMPLE_VERS", (Boolean) true).booleanValue() ? "1" : "1 HTTP/1.1");
        hashMap.put("app_name", "Health");
        hashMap.put("entitlement_version", lop.a("ODSA_ES_VERSION", "1.0"));
        hashMap.put("terminal_model", absPrimaryDevice.getTerminalModel());
        hashMap.put("terminal_sw_version", absPrimaryDevice.getTerminalSwVersion());
        hashMap.put("terminal_vendor", absPrimaryDevice.getTerminalVendor());
        if (map != null) {
            hashMap.putAll(map);
        }
    }

    private static void a(HashMap<String, String> hashMap, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Map<String, String> map) {
        hashMap.put("terminal_id", absPrimaryDevice.getTerminalId());
        hashMap.put("token", lpu.b());
        hashMap.put("terminal_vendor", absPrimaryDevice.getTerminalVendor());
        hashMap.put("terminal_model", absPrimaryDevice.getTerminalModel());
        hashMap.put("app_name", "Health");
        hashMap.put("terminal_sw_version", absPrimaryDevice.getTerminalSwVersion());
        hashMap.put("app", "ap2006");
        hashMap.put("operation", lpr.b[2]);
        hashMap.put("operation_type", map.get("operation_type"));
        hashMap.put("companion_terminal_id", absPairedDevice.getTerminalId());
        if (lop.e("ODSA_ADD_COMPANION_INFO", (Boolean) false).booleanValue()) {
            hashMap.put("companion_terminal_vendor", "Huawei");
            hashMap.put("companion_terminal_model", absPairedDevice.getTerminalModel());
            hashMap.put("companion_terminal_sw_version", absPairedDevice.getTerminalSwVersion());
        }
        hashMap.put("companion_terminal_service", "SharedNumber");
        hashMap.put("vers", lop.e("ODSA_SIMPLE_VERS", (Boolean) true).booleanValue() ? "1" : "1 HTTP/1.1");
    }

    private static void c(HashMap<String, String> hashMap, AbsPrimaryDevice absPrimaryDevice, Map<String, String> map) {
        loq.c("OdsaResolveObjectToXml", "initAcquireOdsaUrlParams");
        hashMap.put("terminal_id", absPrimaryDevice.getTerminalId());
        hashMap.put("entitlement_version", lop.a("ODSA_ES_VERSION", "1.0"));
        hashMap.put("provisioning_version", "4.0");
        hashMap.put("terminal_vendor", absPrimaryDevice.getTerminalVendor());
        hashMap.put("terminal_model", absPrimaryDevice.getTerminalModel());
        hashMap.put("terminal_sw_version", absPrimaryDevice.getTerminalSwVersion());
        hashMap.put("app", "ap2006");
        hashMap.put("app_name", "Health");
        hashMap.put("vers", lop.e("ODSA_SIMPLE_VERS", (Boolean) true).booleanValue() ? "1" : "1 HTTP/1.1");
        if (map != null) {
            hashMap.putAll(map);
        }
    }

    public static String a(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, int i, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : d(absPrimaryDevice, absPairedDevice, i, map).entrySet()) {
                String value = entry.getValue();
                String key = entry.getKey();
                if (!key.isEmpty() && !value.isEmpty()) {
                    if ("operation_type".equals(key)) {
                        jSONObject.put(key, Integer.valueOf(value));
                    } else {
                        jSONObject.put(key, value);
                    }
                }
            }
            return jSONObject.toString();
        } catch (JSONException unused) {
            loq.b("OdsaResolveObjectToXml", "generateJsonStr error");
            return "";
        }
    }
}
