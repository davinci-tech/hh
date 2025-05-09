package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class dko {
    c b;

    private dko(c cVar) {
        this.b = cVar;
    }

    public static class c {
        private Map<String, Object> e = new HashMap(6);

        public c e(double d) {
            this.e.put("connected_time", String.valueOf(d));
            return this;
        }

        public c b(String str) {
            this.e.put("enter_type", str);
            return this;
        }

        public c c(String str) {
            this.e.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.a(str));
            return this;
        }

        public c d(String str) {
            this.e.put(DeviceCategoryFragment.DEVICE_TYPE, str);
            return this;
        }

        public c b(int i) {
            if (i == 2) {
                this.e.put("failed_status", "connect success");
            } else if (i == 12) {
                this.e.put("failed_status", "bond success");
            } else {
                this.e.put("failed_status", String.valueOf(i));
            }
            return this;
        }

        public c d(int i) {
            if (i == 1 || i == -11) {
                this.e.put("download_status", "download_success");
            } else {
                this.e.put("download_status", String.valueOf(i));
            }
            return this;
        }

        public c e(int i) {
            this.e.put("reconnect_time", String.valueOf(i));
            return this;
        }

        public Map<String, Object> d() {
            return this.e;
        }

        public c e(String str) {
            this.e.put("macAddress", dis.b(str));
            return this;
        }

        public c i(String str) {
            this.e.put("prodId", dij.e(str));
            return this;
        }

        public c a(String str) {
            this.e.put("page_id", str);
            return this;
        }

        public dko b() {
            return new dko(this);
        }
    }

    public void b(Context context) {
        if (context == null) {
            LogUtil.h("EcologyBiAnalyticsUtils", "context or touchingConnectMap is null");
            return;
        }
        Map<String, Object> d = this.b.d();
        LogUtil.c("EcologyBiAnalyticsUtils", "touchingConnectMap is ", d.toString());
        ixx.d().d(context, AnalyticsValue.HEALTH_MEASURE_NFC_BLOOD_PRESSURE_2060055.value(), d, 0);
    }

    public void e(Context context) {
        if (context == null) {
            LogUtil.b("EcologyBiAnalyticsUtils", "context = null");
            return;
        }
        Map<String, Object> d = this.b.d();
        LogUtil.c("EcologyBiAnalyticsUtils", "tickBiRopeLevel2PageBrowsing is ", d.toString());
        ixx.d().d(context, AnalyticsValue.HEALTH_ROPE_TWO_LEVEL_PAGE_2170040.value(), d, 0);
    }

    public static double c(long j) {
        return (System.currentTimeMillis() - j) / 1000.0f;
    }

    public static void b(String str, String str2, String str3) {
        LogUtil.a("EcologyBiAnalyticsUtils", "doDeviceBindBiEvent pair passed");
        HashMap hashMap = new HashMap(16);
        if (Utils.o()) {
            String b = dis.b(str2);
            if (b != null && !TextUtils.isEmpty(b) && b.length() > 24) {
                hashMap.put("macAddress", b.substring(0, 24));
            }
        } else {
            hashMap.put("macAddress", dis.b(str2));
        }
        dcz d = ResourceManager.e().d(str);
        if (d != null) {
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.e(str, d.n().b()));
            hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, d.l().name());
            hashMap.put("deviceId", d.g());
        }
        hashMap.put("prodId", dij.e(str));
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_SUCCEED_2060005.value(), hashMap, 0);
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_SUCCEED_WHITE_2060087.value(), hashMap, 0);
        hashMap.put("productId", str);
        if (str3 != null) {
            hashMap.put("uniqueId", str3);
        }
        bzw.e().setEvent(BaseApplication.getContext(), String.valueOf(1500), hashMap);
    }

    public static void b(String str, String str2) {
        String j = dds.c().j();
        String d = dds.c().d();
        HashMap hashMap = new HashMap(16);
        hashMap.put("prodId", dij.e(j));
        hashMap.put("macAddress", dis.b(d));
        hashMap.put("courseIdList", str);
        hashMap.put("action", str2);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.DELETE_AND_DISABLE_COURSE_EVENT_VALUE.value(), hashMap, 0);
    }
}
