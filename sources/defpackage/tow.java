package defpackage;

import com.huawei.wearengine.device.Device;
import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class tow extends JSONObject {
    public tow(String str) throws JSONException {
        super(str);
    }

    private tow() throws JSONException {
        this("{}");
    }

    public static String b(Device device) {
        if (device == null) {
            return "";
        }
        try {
            Field declaredField = device.getClass().getDeclaredField("mReservedness");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(device);
            return obj instanceof String ? (String) obj : "";
        } catch (IllegalAccessException unused) {
            tov.d("DeviceJson", "getJsonFromDevice catch IllegalAccessException");
            return "";
        } catch (NoSuchFieldException unused2) {
            tov.d("DeviceJson", "getJsonFromDevice catch NoSuchFieldException");
            return "";
        }
    }

    public String a() {
        Object opt = opt("device_extra");
        return opt instanceof String ? (String) opt : "";
    }

    public void a(String str) {
        try {
            put("device_extra", str);
        } catch (JSONException unused) {
            tos.d("DeviceJson", "setExtra catch JSONException");
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f17285a = "";
        private String c = "";
        private boolean d = false;
        private String e = "";

        public b e(String str) {
            this.f17285a = str;
            return this;
        }

        public b b(String str) {
            this.c = str;
            return this;
        }

        public b a(boolean z) {
            this.d = z;
            return this;
        }

        public b a(String str) {
            this.e = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public tow d() {
            AnonymousClass2 anonymousClass2 = null;
            try {
                tow towVar = new tow();
                try {
                    towVar.put("device_reservedness", this.f17285a);
                    towVar.put("device_soft_version", this.c);
                    towVar.put("device_is_support_ota", this.d);
                    towVar.put("device_extra", this.e);
                    return towVar;
                } catch (JSONException unused) {
                    anonymousClass2 = towVar;
                    tov.c("DeviceJson", "build catch JSONException");
                    return anonymousClass2;
                }
            } catch (JSONException unused2) {
            }
        }
    }
}
