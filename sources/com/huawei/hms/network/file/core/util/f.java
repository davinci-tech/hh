package com.huawei.hms.network.file.core.util;

import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.framework.common.PackageManagerCompat;
import com.huawei.hms.framework.common.hianalytics.HianalyticsBaseData;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.framework.common.hianalytics.LinkedHashMapPack;
import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.huawei.hms.network.Version;
import com.huawei.hms.network.embedded.g2;
import com.huawei.hms.network.embedded.n2;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hms.network.file.api.Request;
import com.huawei.hms.network.file.api.RequestConfig;
import com.huawei.hms.network.file.api.RequestManager;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.task.k;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    int f5641a = 0;
    int b = 0;
    long c = 0;
    long d = 0;
    int e;
    String f;

    private long a(double d, double d2) {
        return (long) ((d * 0.2d) + (d2 * 0.8d));
    }

    public void b() {
        this.b = NetworkUtil.getMobileRsrp(RequestManager.getAppContext());
        this.f5641a = NetworkUtil.getWifiRssi(RequestManager.getAppContext());
    }

    public void a(List<k> list, FileManagerException fileManagerException, com.huawei.hms.network.file.core.task.d dVar, LinkedHashMap<String, String> linkedHashMap) {
        String str;
        if (!HianalyticsHelper.getInstance().isEnableReport(RequestManager.getAppContext())) {
            FLogger.i("Reporter", "hiAnalyticEnable is false, no need to report!", new Object[0]);
            return;
        }
        LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>();
        linkedHashMap2.putAll(linkedHashMap);
        linkedHashMap2.put("sdk_type", "UxPP");
        linkedHashMap2.put(HianalyticsBaseData.SDK_NAME, "networkkit");
        linkedHashMap2.put("sdk_version", "8.0.1.307");
        linkedHashMap2.put("total_time", String.valueOf(dVar.a()));
        linkedHashMap2.put("net_total_time", String.valueOf(dVar.b()));
        linkedHashMap2.put("restclient_version_code", Version.getVersion());
        linkedHashMap2.put("network_type", String.valueOf(NetworkUtil.netWork(RequestManager.getAppContext())));
        linkedHashMap2.put("wifi_signal_strength", String.valueOf(this.f5641a));
        linkedHashMap2.put("mobile_signal_strength", String.valueOf(this.b));
        linkedHashMap2.put("old_interface_flag", String.valueOf(this.e));
        int errorCode = Constants.ErrorCode.SUCCESS.getErrorCode();
        if (fileManagerException != null) {
            errorCode = fileManagerException.getErrorCode();
            str = fileManagerException.getMessage();
        } else {
            str = "success";
        }
        linkedHashMap2.put("error_code", String.valueOf(errorCode));
        linkedHashMap2.put("message", Utils.anonymizeMessage(str));
        a(list, dVar, linkedHashMap2);
        a(linkedHashMap2, list);
        FLogger.v("Reporter", "uploadCollection:" + linkedHashMap2);
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                linkedHashMap3.put("protocol_impl", list.get(i).o());
                linkedHashMap3.put(x2.PROTOCOL, list.get(i).n());
                linkedHashMap3.put(n2.CONGESTION_CONTROL_TYPE, list.get(i).i());
                linkedHashMap3.put("multipath_algorithm", String.valueOf(list.get(i).l()));
                sb.append(linkedHashMap3);
                if (i != list.size() - 1) {
                    sb.append(",");
                }
            }
        }
        FLogger.i("Reporter", "protocol:" + ((Object) sb), new Object[0]);
        HianalyticsHelper.getInstance().onEvent(linkedHashMap2, "file_request");
        a(errorCode);
        linkedHashMap2.clear();
    }

    public void a() {
        this.c = System.currentTimeMillis();
        this.d = SystemClock.elapsedRealtime();
    }

    private void b(LinkedHashMap<String, String> linkedHashMap, List<k> list) {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (k kVar : list) {
            if (kVar.m() != null) {
                if (kVar.m().b() != null) {
                    j += kVar.m().b().longValue();
                }
                if (kVar.m().a() != null) {
                    j2 += kVar.m().a().longValue();
                }
                j3 += kVar.m().d();
                j4 += kVar.m().c();
                FLogger.v("Reporter", "DownloadReportCollectInfo:" + kVar.m());
            }
        }
        linkedHashMap.put("available_ram", String.valueOf(a(j, j2) / list.size()));
        linkedHashMap.put("current_cpu_freq", String.valueOf(a(j3, j4) / list.size()));
    }

    private void a(Map<String, String> map, String str, Object obj) {
        if (map == null || TextUtils.isEmpty(str) || obj == null) {
            return;
        }
        if ((obj instanceof String) && TextUtils.isEmpty((String) obj)) {
            return;
        }
        map.put(str, String.valueOf(obj));
    }

    private void a(List<k> list, com.huawei.hms.network.file.core.task.d dVar, LinkedHashMap<String, String> linkedHashMap) {
        k a2 = k.a(list);
        String str = "unknown";
        if (a2 == null) {
            linkedHashMap.put("trans_type", "unknown");
            return;
        }
        Request p = a2.p();
        String valueOf = String.valueOf(p.getId());
        linkedHashMap.put(MapKeyNames.REQUEST_ID, valueOf);
        a(p, linkedHashMap);
        if (!TextUtils.isEmpty(this.f)) {
            linkedHashMap.put("scene", this.f);
        }
        linkedHashMap.put("retry_time", String.valueOf((p.getConfig() == null || p.getConfig().getRetryTimes() == -100) ? 0 : p.getConfig().getRetryTimes()));
        if (a2.d() == e.b.UPLOAD) {
            str = "upload";
        } else if (a2.d() == e.b.DOWNLOAD) {
            str = "download";
        }
        linkedHashMap.put("trans_type", str);
        String n = a2.n();
        linkedHashMap.put(x2.PROTOCOL, n);
        if (g2.H3.equals(n)) {
            linkedHashMap.put(n2.CONGESTION_CONTROL_TYPE, a2.i());
            linkedHashMap.put("multipath_algorithm", String.valueOf(a2.l()));
        }
        linkedHashMap.put("protocol_impl", a2.o());
        linkedHashMap.put("request_file_size", String.valueOf(a2.q()));
        linkedHashMap.put(x2.CONNECT_RETRY, String.valueOf(a2.j()));
        linkedHashMap.put("server_ip", a(list));
        linkedHashMap.put("function_flag", String.valueOf(a2.k()));
        if (p.getSpeedLimit() > 0) {
            a(linkedHashMap, "speed_limit", Integer.valueOf(p.getSpeedLimit()));
        }
        Utils.addNonEmptyMapItem(linkedHashMap, p.getReportInfos());
        if (!linkedHashMap.containsKey("trace_id")) {
            linkedHashMap.put("trace_id", valueOf);
        }
        a(linkedHashMap, "file_size", Long.valueOf(dVar.b(list)));
        a(linkedHashMap, "rate", Long.valueOf(dVar.a(list)));
        a(linkedHashMap, "ABTest_dyFrag_groupid", com.huawei.hms.network.file.core.b.a());
        a(linkedHashMap, "config_slice_num", Integer.valueOf(com.huawei.hms.network.file.core.b.b()));
        a(linkedHashMap, "actual_slice_num", Integer.valueOf(a2.t()));
    }

    private void a(LinkedHashMap<String, String> linkedHashMap, List<k> list) {
        linkedHashMap.put("api_level", String.valueOf(Build.VERSION.SDK_INT));
        if (list == null || list.size() <= 1) {
            return;
        }
        linkedHashMap.put("hardware", Build.HARDWARE);
        b(linkedHashMap, list);
    }

    private void a(Request request, LinkedHashMap<String, String> linkedHashMap) {
        RequestConfig config = request.getConfig();
        if (config == null || TextUtils.isEmpty(config.getOptions())) {
            return;
        }
        String valueFromOptions = Utils.getValueFromOptions("complete_file_size", config.getOptions());
        if (TextUtils.isEmpty(valueFromOptions)) {
            return;
        }
        linkedHashMap.put("complete_file_size", valueFromOptions);
    }

    private <T> void a(int i) {
        if (i == Constants.ErrorCode.SUCCESS.getErrorCode()) {
            i = 0;
        }
        long elapsedRealtime = this.d != 0 ? SystemClock.elapsedRealtime() - this.d : 0L;
        LinkedHashMapPack linkedHashMapPack = new LinkedHashMapPack();
        linkedHashMapPack.put("package", RequestManager.getAppContext().getPackageName()).put("version", "8.0.1.307").put("service", "networkkit").put("apiName", "file_request").put("result", i).put(WiseOpenHianalyticsData.UNION_COSTTIME, elapsedRealtime).put("callTime", this.c).put(WiseOpenHianalyticsData.UNION_APP_VERSION, PackageManagerCompat.getAppVersion(RequestManager.getAppContext()));
        FLogger.v("Reporter", "collectWiseOpenData:" + linkedHashMapPack.getAll());
        HianalyticsHelper.getInstance().onEvent(linkedHashMapPack.getAll(), "60000", 0);
    }

    private static String a(List<k> list) {
        if (Utils.isEmpty(list)) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        for (k kVar : list) {
            if (kVar != null && (kVar instanceof k)) {
                String s = kVar.s();
                if (!Utils.isBlank(s) && !arrayList.contains(s)) {
                    arrayList.add(s);
                }
            }
        }
        if (Utils.isEmpty(arrayList)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            if (i != 0) {
                sb.append(";");
            }
            sb.append((String) arrayList.get(i));
        }
        return sb.toString();
    }

    public f(com.huawei.hms.network.file.core.d dVar) {
        this.e = 0;
        if (dVar == null || dVar.a() == null) {
            return;
        }
        if (dVar.a().getOldInterfaceFlag()) {
            this.e = 1;
        }
        this.f = Utils.getValueFromOptions("scene", dVar.a().getOptions());
    }
}
