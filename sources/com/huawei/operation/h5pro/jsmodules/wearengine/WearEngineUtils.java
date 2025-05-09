package com.huawei.operation.h5pro.jsmodules.wearengine;

import android.text.TextUtils;
import com.huawei.wearengine.device.Device;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
class WearEngineUtils {
    private static final int BYTE_SIZE = 2;
    private static final int SHIFT_LENGTH = 4;
    private static final int TEMPLATE_NUMBER = 255;

    private WearEngineUtils() {
    }

    static Device getTargetConnDevice(List<Device> list, String str) {
        if (list != null && !list.isEmpty()) {
            for (Device device : list) {
                if (TextUtils.isEmpty(str) && device.isConnected()) {
                    return device;
                }
                if (!TextUtils.isEmpty(str) && str.equals(device.getUuid())) {
                    return device;
                }
            }
        }
        return null;
    }

    static JSONArray devicesToJsonArray(List<Device> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Device device : list) {
            if (device != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", device.getName());
                jSONObject.put("udid", device.getUuid());
                jSONObject.put("model", device.getModel());
                jSONObject.put("productType", device.getProductType());
                jSONObject.put("isConnected", device.isConnected());
                jSONObject.put("basicInfo", device.getBasicInfo());
                jSONObject.put("capability", device.getCapability());
                jSONObject.put("identify", device.getIdentify());
                jSONObject.put("reservedness", device.getReservedness());
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }

    static String bytes2HexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString.toUpperCase(Locale.ENGLISH));
        }
        return sb.toString();
    }

    static byte[] hexString2Bytes(String str) {
        if (TextUtils.isEmpty(str) || str.length() % 2 != 0) {
            return null;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        int length = upperCase.length() / 2;
        byte[] bArr = new byte[length];
        char[] charArray = upperCase.toCharArray();
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (charToByte(charArray[i2 + 1]) | (charToByte(charArray[i2]) << 4));
        }
        return bArr;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
