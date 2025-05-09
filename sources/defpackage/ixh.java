package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.common.dfx.storage.DefaultStorageHandler;
import com.huawei.haf.common.os.FileFilterUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class ixh extends DefaultStorageHandler {
    private static final String e = "files/" + BaseApplication.d();
    private static final String b = "files/huaweisystem/" + BaseApplication.d();

    public ixh() {
        super("TimeEat_StorageHandler");
    }

    @Override // com.huawei.haf.common.dfx.storage.DefaultStorageHandler
    public boolean e() {
        long g;
        if (!LogUtil.a() && !LogUtil.c()) {
            return false;
        }
        Context context = getContext();
        String e2 = KeyValDbManager.b(context).e("app_last_storage_check_time");
        if (TextUtils.isEmpty(e2)) {
            g = AppInfoUtils.e();
            KeyValDbManager.b(context).e("app_last_storage_check_time", String.valueOf(g));
        } else {
            g = CommonUtil.g(e2);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - g < 604800000) {
            return false;
        }
        KeyValDbManager.b(context).e("app_last_storage_check_time", String.valueOf(currentTimeMillis));
        return true;
    }

    @Override // com.huawei.haf.common.dfx.storage.DefaultStorageHandler
    public Map<String, Long> b() {
        HashMap hashMap = new HashMap();
        hashMap.put("cache", 0L);
        hashMap.put("files", 0L);
        hashMap.put("files/h5pro", 0L);
        hashMap.put(e, 0L);
        hashMap.put("files/mmkv", 0L);
        hashMap.put("files/achievemedal", 0L);
        hashMap.put("files/achievemedalpng", 0L);
        hashMap.put("files/fitness", 0L);
        hashMap.put("files/sleepMonitor", 0L);
        hashMap.put("files/plugins", 0L);
        hashMap.put("databases", 0L);
        hashMap.put("app_bundle", 0L);
        hashMap.put("app_webview", 0L);
        hashMap.put("app_hws_webview", 0L);
        return hashMap;
    }

    @Override // com.huawei.haf.common.dfx.storage.DefaultStorageHandler
    public Map<String, Long> d() {
        HashMap hashMap = new HashMap();
        hashMap.put("cache", 0L);
        hashMap.put("files", 0L);
        hashMap.put("files/h5pro", 0L);
        hashMap.put("files/huaweisystem", 0L);
        hashMap.put(b, 0L);
        hashMap.put("files/huaweisystem/BetaClub", 0L);
        hashMap.put("files/Huawei/Health", 0L);
        hashMap.put("files/amap", 0L);
        hashMap.put("files/RunwayRoutes", 0L);
        return hashMap;
    }

    @Override // com.huawei.haf.common.dfx.storage.DefaultStorageHandler
    public void b(String str, long j, long j2, Map<String, Long> map, Map<String, Long> map2) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(OpAnalyticsConstants.MONITOR_INFO, "storage_total_size(M)=" + (((j + j2) / 1024.0f) / 1024.0f));
        linkedHashMap.put("internal_total_size", String.valueOf(j));
        if (!map.isEmpty()) {
            linkedHashMap.put("internal_cache", String.valueOf(map.get("cache")));
            linkedHashMap.put("internal_files", String.valueOf(map.get("files")));
            linkedHashMap.put("internal_h5pro", String.valueOf(map.get("files/h5pro")));
            linkedHashMap.put("internal_healthlog", String.valueOf(map.get(e)));
            linkedHashMap.put("internal_mmkv", String.valueOf(map.get("files/mmkv")));
            linkedHashMap.put("internal_plugins", String.valueOf(map.get("files/plugins")));
            linkedHashMap.put("internal_chievemedalpng", String.valueOf(map.get("files/achievemedalpng")));
            linkedHashMap.put("internal_achievemedal", String.valueOf(map.get("files/achievemedal")));
            linkedHashMap.put("internal_fitness", String.valueOf(map.get("files/fitness")));
            linkedHashMap.put("internal_sleepmonitor", String.valueOf(map.get("files/sleepMonitor")));
            linkedHashMap.put("internal_databases", String.valueOf(map.get("databases")));
            linkedHashMap.put("internal_app_bundle", String.valueOf(map.get("app_bundle")));
            linkedHashMap.put("internal_app_webview", String.valueOf(map.get("app_webview")));
            linkedHashMap.put("internal_app_hws_weview", String.valueOf(map.get("app_hws_webview")));
        }
        linkedHashMap.put("external_total_size", String.valueOf(j2));
        if (!map2.isEmpty()) {
            linkedHashMap.put("external_cache", String.valueOf(map2.get("cache")));
            linkedHashMap.put("external_files", String.valueOf(map2.get("files")));
            linkedHashMap.put("external_h5pro", String.valueOf(map2.get("files/h5pro")));
            linkedHashMap.put("external_huaweisystem", String.valueOf(map2.get("files/huaweisystem")));
            linkedHashMap.put("external_healthlog", String.valueOf(map2.get(b)));
            linkedHashMap.put("external_betaclub", String.valueOf(map2.get("files/huaweisystem/BetaClub")));
            linkedHashMap.put("external_huaweihealth", String.valueOf(map2.get("files/Huawei/Health")));
            linkedHashMap.put("external_amap", String.valueOf(map2.get("files/amap")));
            linkedHashMap.put("external_runwayroutes", String.valueOf(map2.get("files/RunwayRoutes")));
        }
        if (!LogUtil.a()) {
            for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                LogUtil.c("TimeEat_StorageHandler", entry.getKey(), " = ", entry.getValue());
            }
        } else {
            super.b(str, j, j2, map, map2);
        }
        linkedHashMap.put(OpAnalyticsConstants.MONITOR_KEY, str);
        linkedHashMap.put("deviceType", Build.MANUFACTURER);
        linkedHashMap.put(DeviceCategoryFragment.DEVICE_TYPE, Build.BOARD);
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_STORAGE_90030006.value(), linkedHashMap);
    }

    @Override // com.huawei.haf.common.dfx.storage.DefaultStorageHandler
    public void c() {
        long currentTimeMillis = System.currentTimeMillis();
        h();
        a();
        LogUtil.c("TimeEat_StorageHandler", "pre auto clean storage, cost time=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    @Override // com.huawei.haf.common.dfx.storage.DefaultStorageHandler
    public void e(Map<String, Long> map, Map<String, Long> map2) {
        LogUtil.c("TimeEat_StorageHandler", "post auto clean storage, cost time=", Long.valueOf(System.currentTimeMillis() - System.currentTimeMillis()));
    }

    private void h() {
        FileUtils.d(AppInfoUtils.f(null), new FileFilterUtils.FilePrefixCollectFilter("HiTrack_", 86400000L));
        Glide.get(getContext()).clearDiskCache();
        FileUtils.b(AppInfoUtils.e(Constants.PPS_ROOT_PATH), false);
        FileUtils.b(AppInfoUtils.f("Log"), false);
        if (LogUtil.c()) {
            FileUtils.b(AppInfoUtils.f(getContext().getPackageName()), false);
        }
    }

    private void a() {
        FileUtils.b(AppInfoUtils.c("nanotraceFiles"), false);
        FileUtils.b(AppInfoUtils.d(null), false);
        FileUtils.b(AppInfoUtils.a("Log"), false);
        DfxBaseHandler.deleteHprofData();
        if (LogUtil.a()) {
            FileUtils.b(AppInfoUtils.a("huaweisystem"), false);
        }
    }
}
