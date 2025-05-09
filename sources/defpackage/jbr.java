package defpackage;

import android.os.Build;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hwcloudmodel.hwwear.hag.model.tide.HagTideDataBean;
import com.huawei.hwcloudmodel.hwwear.hag.model.tide.HagTideStationBean;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.CommonApi;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes5.dex */
public class jbr {
    private static final Object b = new Object();
    private static jbr c = null;
    private static String e = "";

    /* renamed from: a, reason: collision with root package name */
    private HttpClient f13714a;

    private jbr() {
        RestClientGlobalInstance.getInstance().init(BaseApplication.getContext());
        this.f13714a = new HttpClient.Builder().connectTimeout(30000).readTimeout(10000).writeTimeout(10000).build();
    }

    static String d() {
        if (!TextUtils.isEmpty(e) && e.length() > 16) {
            return e;
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(15), "hag_device_uuid");
        if (!TextUtils.isEmpty(b2)) {
            return b2;
        }
        String replace = UUID.randomUUID().toString().replace(Constants.LINK, "");
        String c2 = jbu.c(replace);
        if (TextUtils.isEmpty(c2) || c2.length() < 32) {
            return replace;
        }
        String substring = c2.substring(0, 32);
        int e2 = SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(15), "hag_device_uuid", substring, new StorageParams(0));
        if (e2 == 0) {
            e = substring;
        }
        LogUtil.a("HagHttpUtils", "deviceUdid is saved:", Integer.valueOf(e2));
        return substring;
    }

    public static jbr e() {
        jbr jbrVar;
        synchronized (b) {
            if (c == null) {
                c = new jbr();
            }
            jbrVar = c;
        }
        return jbrVar;
    }

    private String c(String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            String a2 = a();
            LogUtil.a("HagHttpUtils", "x-client-version:", "com.huawei.health.app_" + CommonUtil.e(BaseApplication.getContext()), "x-hag-trace-id:", a2, CloudParamKeys.X_DEVICE_ID, d(), "url:", str);
            hashMap.put(CloudParamKeys.X_CLIENT_VERSION, "com.huawei.health.app_" + CommonUtil.e(BaseApplication.getContext()));
            hashMap.put("Content-Type", "application/json");
            hashMap.put("x-hag-trace-id", a2);
            hashMap.put("x-prd-pkg-name", "com.huawei.health.app");
            hashMap.put(CloudParamKeys.X_DEVICE_ID, d());
            hashMap.put("x-udid", b());
            CommonApi commonApi = (CommonApi) lqi.a(this.f13714a).b(CommonApi.class);
            if (commonApi == null) {
                LogUtil.h("HagHttpUtils", "post, hagInfoApi is null");
                return String.valueOf(TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR);
            }
            Response<String> execute = commonApi.commonPost(str, hashMap, str2).execute();
            if (execute == null) {
                LogUtil.h("HagHttpUtils", "post() result is empty");
                return String.valueOf(TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR);
            }
            if (execute.isOK()) {
                String body = execute.getBody();
                LogUtil.a("HagHttpUtils", "post():", body);
                if (!TextUtils.isEmpty(body)) {
                    return body;
                }
                LogUtil.h("HagHttpUtils", "post() result is empty");
                return String.valueOf(TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR);
            }
            LogUtil.h("HagHttpUtils", "post() code:", Integer.valueOf(execute.getCode()), ",message:", execute.getMessage());
            return String.valueOf(execute.getCode());
        } catch (IOException unused) {
            LogUtil.b("HagHttpUtils", "post() IOException");
            return String.valueOf(600);
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("HagHttpUtils", "post() IllegalArgumentException");
            return String.valueOf(601);
        }
    }

    private String b() {
        String h;
        return (CommonUtil.bh() && (h = CommonUtil.h()) != null) ? h : "";
    }

    private String a() {
        return d() + Constants.LINK + System.currentTimeMillis();
    }

    public jbz c(boolean z, double d, double d2) {
        LogUtil.a("HagHttpUtils", "hagWeatherInfo");
        jbz jbzVar = new jbz();
        jbzVar.b(-99);
        jbzVar.c(-99);
        jbzVar.e(-99);
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainHagHicloud", jbu.e());
        String b2 = jbv.b(d, d2, false);
        if (!TextUtils.isEmpty(b2) && !TextUtils.isEmpty(noCheckUrl)) {
            long currentTimeMillis = System.currentTimeMillis();
            jbzVar = jbn.c(c(c(noCheckUrl) + "/user-inquiry/v1/dist-abilities/inquiry", b2));
            jbzVar.c(a(System.currentTimeMillis() - currentTimeMillis));
            if (z) {
                jbzVar.d(d(d, d2));
            }
            LogUtil.a("HagHttpUtils", "hagWeatherInfo:", jbzVar.toString());
        } else {
            LogUtil.h("HagHttpUtils", "assemblyBodyJson is empty or url is null");
        }
        return jbzVar;
    }

    private HagTideDataBean d(double d, double d2) {
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainHagHicloud", jbu.e());
        String b2 = jbv.b(d, d2, true);
        HagTideDataBean hagTideDataBean = new HagTideDataBean();
        if (!TextUtils.isEmpty(b2) && !TextUtils.isEmpty(noCheckUrl)) {
            long currentTimeMillis = System.currentTimeMillis();
            String c2 = c(d(noCheckUrl) + "/user-inquiry/v1/dist-abilities/inquiry", b2);
            hagTideDataBean.setHagReportBiMap(a(System.currentTimeMillis() - currentTimeMillis));
            List<HagTideStationBean> a2 = jbq.a(c2);
            if (a2 != null) {
                hagTideDataBean.setTideStations(a2);
                LogUtil.a("HagHttpUtils", "hagTideInfo:", a2.toString());
            } else {
                LogUtil.h("HagHttpUtils", "hagTideInfo tideStations is null");
            }
        } else {
            LogUtil.h("HagHttpUtils", "hagTideInfo is empty or url is null");
        }
        return hagTideDataBean;
    }

    public jca b(double d, double d2) {
        jca jcaVar = new jca();
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainHagHicloud", jbu.e());
        String b2 = jbv.b(d, d2, false);
        if (!TextUtils.isEmpty(b2) && !TextUtils.isEmpty(noCheckUrl)) {
            long currentTimeMillis = System.currentTimeMillis();
            jcaVar = jbp.e(c(noCheckUrl + "/user-inquiry/v1/dist-abilities/inquiry", b2));
            jcaVar.e(a(System.currentTimeMillis() - currentTimeMillis));
        }
        LogUtil.a("HagHttpUtils", "getHagAtmosphereInfo() atmosphereInfo:", jcaVar.toString());
        return jcaVar;
    }

    private Map<String, Object> a(long j) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("event_time", Long.valueOf(System.currentTimeMillis()));
        hashMap.put("device_version", Build.BRAND + "_" + Build.VERSION.RELEASE);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, Build.MODEL);
        hashMap.put("device_id", d());
        hashMap.put("product", "com.huawei.health.app");
        hashMap.put("product_version", "com.huawei.health.app_" + CommonUtil.e(BaseApplication.getContext()));
        hashMap.put("service_type", "HAG");
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, 0);
        hashMap.put("ability_id", "BUILTIN_WEATHER");
        hashMap.put("trace_id", a());
        hashMap.put("service_delay", Long.valueOf(j));
        return hashMap;
    }

    private String c(String str) {
        if (!jbo.h()) {
            LogUtil.h("HagHttpUtils", "convertWeatherHttpUrl() is not developer");
            return str;
        }
        String c2 = jbo.c();
        if (!TextUtils.isEmpty(c2)) {
            return c2;
        }
        LogUtil.h("HagHttpUtils", "convertWeatherHttpUrl() developerWeatherUrl is empty");
        return str;
    }

    private String d(String str) {
        if (!jbo.h()) {
            LogUtil.h("HagHttpUtils", "convertTideHttpUrl() is not developer");
            return str;
        }
        String e2 = jbo.e();
        if (!TextUtils.isEmpty(e2)) {
            return e2;
        }
        LogUtil.h("HagHttpUtils", "convertTideHttpUrl() developerTideUrl is empty");
        return str;
    }
}
