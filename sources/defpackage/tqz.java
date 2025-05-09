package defpackage;

import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.wearengine.device.Device;
import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class tqz extends JSONObject {
    public tqz(String str) throws JSONException {
        super(str);
    }

    private tqz() throws JSONException {
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
            LogUtil.b("DeviceJson", "getJsonFromDevice catch IllegalAccessException");
            return "";
        } catch (NoSuchFieldException unused2) {
            LogUtil.b("DeviceJson", "getJsonFromDevice catch NoSuchFieldException");
            return "";
        }
    }

    public void b(String str) {
        try {
            put("device_wear_engine_device_id", str);
        } catch (JSONException unused) {
            LogUtil.h("DeviceJson", "setWearEngineDeviceId catch JSONException");
        }
    }

    public void c(int i) {
        try {
            put("device_p2p_capability", i);
        } catch (JSONException unused) {
            LogUtil.h("DeviceJson", "setP2pCapability catch JSONException");
        }
    }

    public void e(int i) {
        try {
            put("device_monitor_capability", i);
        } catch (JSONException unused) {
            LogUtil.h("DeviceJson", "setMonitorCapability catch JSONException");
        }
    }

    public void b(int i) {
        try {
            put("device_notify_capability", i);
        } catch (JSONException unused) {
            LogUtil.h("DeviceJson", "setNotifyCapability catch JSONException");
        }
    }

    public void d(int i) {
        try {
            put("device_sensor_capability", i);
        } catch (JSONException unused) {
            LogUtil.h("DeviceJson", "setSensorCapability catch JSONException");
        }
    }

    public void d(String str) {
        try {
            put(PluginPayAdapter.DEVICE_CATEGORY, str);
        } catch (JSONException unused) {
            LogUtil.h("DeviceJson", "setDeviceCategory catch JSONException");
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
            LogUtil.h("DeviceJson", "setExtra catch JSONException");
        }
    }

    public static class c {
        private String g = "";

        /* renamed from: a, reason: collision with root package name */
        private String f17354a = "";
        private String b = "";
        private String f = "";
        private String m = "";
        private String k = "";
        private int i = 2;
        private int j = 2;
        private int h = 2;
        private int l = 2;
        private String e = "";
        private String d = "";
        private int c = -1;

        public c a(String str) {
            this.g = str;
            return this;
        }

        public c d(String str) {
            this.m = str;
            return this;
        }

        public c b(String str) {
            this.k = str;
            return this;
        }

        public c b(int i) {
            this.c = i;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public tqz c() {
            AnonymousClass5 anonymousClass5 = null;
            try {
                tqz tqzVar = new tqz();
                try {
                    tqzVar.put("device_reservedness", this.g);
                    tqzVar.put("device_capability", this.f17354a);
                    tqzVar.put("device_basic_info", this.b);
                    tqzVar.put("device_identify", this.f);
                    tqzVar.put("device_wear_engine_device_id", this.m);
                    tqzVar.put("device_soft_version", this.k);
                    tqzVar.put("device_p2p_capability", this.i);
                    tqzVar.put("device_monitor_capability", this.j);
                    tqzVar.put("device_notify_capability", this.h);
                    tqzVar.put("device_sensor_capability", this.l);
                    tqzVar.put(PluginPayAdapter.DEVICE_CATEGORY, this.e);
                    tqzVar.put("device_extra", this.d);
                    tqzVar.put(DeviceCategoryFragment.DEVICE_TYPE, this.c);
                    return tqzVar;
                } catch (JSONException unused) {
                    anonymousClass5 = tqzVar;
                    LogUtil.h("DeviceJson", "build catch JSONException");
                    return anonymousClass5;
                }
            } catch (JSONException unused2) {
            }
        }
    }
}
