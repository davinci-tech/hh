package defpackage;

import android.graphics.Bitmap;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.Base64;
import health.compact.a.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class nzy {
    public static String cTv_(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            try {
                try {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    String str = "data:image/png;base64," + Base64.a(byteArrayOutputStream.toByteArray());
                    try {
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                        return str;
                    } catch (IOException e) {
                        LogUtil.e("DeviceConnectManagerUtils", "bitmapToBase64Encode exception2: ", ExceptionUtils.d(e));
                        return str;
                    }
                } finally {
                    try {
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        LogUtil.e("DeviceConnectManagerUtils", "bitmapToBase64Encode exception2: ", ExceptionUtils.d(e2));
                    }
                }
            } catch (IOException e3) {
                LogUtil.e("DeviceConnectManagerUtils", "bitmapToBase64Encode exception1: ", ExceptionUtils.d(e3));
            }
        } else {
            LogUtil.e("DeviceConnectManagerUtils", "bitmapToBase64Encode bitmap is null");
        }
        return null;
    }

    public static boolean c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("isCloudDevice")) {
                return jSONObject.optBoolean("isCloudDevice");
            }
        } catch (JSONException unused) {
            LogUtil.e("DeviceConnectManagerUtils", "isCloudDevice is error");
        }
        return false;
    }

    public static int a(int i) {
        int d = jfu.c(i).d();
        return (d != -1 || cup.a().get(Integer.valueOf(i)) == null) ? d : cup.a().get(Integer.valueOf(i)).intValue();
    }

    public static ArrayList<String> b(int i) {
        return jfu.b(i);
    }

    public static String d(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("name")) {
                return jSONObject.optString("name");
            }
        } catch (JSONException unused) {
            LogUtil.e("DeviceConnectManagerUtils", "getDeviceName is error");
        }
        return "";
    }
}
