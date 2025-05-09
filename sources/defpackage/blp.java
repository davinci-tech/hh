package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import health.compact.a.LogUtil;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes3.dex */
public class blp {
    public static String d(String str, String str2) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        LogUtil.c("HonorHideUtil", "adapterCloudHide enter : ", str, "device Model : ", str2);
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceModel(str2);
        if (!bld.c(deviceInfo)) {
            return str;
        }
        Object a2 = a("hide_capability_on_honor_device");
        JSONArray jSONArray = a2 instanceof JSONArray ? (JSONArray) a2 : null;
        if (jSONArray == null || jSONArray.length() == 0) {
            return str;
        }
        byte[] a3 = blq.a(str);
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                i = jSONArray.getInt(i2);
            } catch (JSONException e) {
                LogUtil.e("HonorHideUtil", "adapterCloudHide exception : ", bll.a(e));
                i = 0;
            }
            LogUtil.c("HonorHideUtil", "hide index : ", Integer.valueOf(i));
            a(a3, i);
        }
        blt.d("R_HonorHideUtil", a3, "before adapter : ", str, " after adapter : ");
        return blq.d(a3);
    }

    private static void a(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0 || i >= bArr.length * 8) {
            return;
        }
        if (i < 0) {
            LogUtil.a("HonorHideUtil", "isSupport target : ", Integer.valueOf(i));
        } else {
            int i2 = i / 8;
            bArr[i2] = (byte) ((~(1 << (i % 8))) & bArr[i2]);
        }
    }

    private static Object a(String str) {
        Object obj = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (bky.i()) {
            String d = blz.d("app_info_device_cloud", "");
            LogUtil.c("HonorHideUtil", "adapterCloudHide json : ", d);
            if (TextUtils.isEmpty(d)) {
                return null;
            }
            try {
                JSONArray jSONArray = new JSONArray(d);
                for (int i = 0; i < jSONArray.length(); i++) {
                    obj = jSONArray.getJSONObject(i).opt(str);
                    if (obj != null) {
                        return obj;
                    }
                    LogUtil.a("HonorHideUtil", "cloud no info.");
                }
            } catch (JSONException e) {
                LogUtil.e("HonorHideUtil", "isHonorShielded exception : ", bll.a(e));
            }
        }
        return obj;
    }

    public static boolean a(int i, String str) {
        if (i < 10000) {
            LogUtil.a("HonorHideUtil", "wrong 5.1.55 capability please use normal device info.");
            return false;
        }
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceModel(str);
        if (bld.c(deviceInfo)) {
            Object a2 = a("hide_capability_on_honor_device");
            JSONArray jSONArray = a2 instanceof JSONArray ? (JSONArray) a2 : null;
            if (jSONArray == null || jSONArray.length() == 0) {
                return false;
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                } catch (JSONException e) {
                    LogUtil.e("HonorHideUtil", "isHonorHide exception : ", bll.a(e));
                }
                if (i == jSONArray.getInt(i2)) {
                    LogUtil.c("HonorHideUtil", "cloud shield : ", Integer.valueOf(i));
                    return true;
                }
                continue;
            }
        }
        LogUtil.c("HonorHideUtil", "isHonorHide index is not hide : ", Integer.valueOf(i));
        return false;
    }
}
