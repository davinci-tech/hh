package com.huawei.hms.network.embedded;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.CreateFileUtil;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.network.embedded.k;
import com.huawei.hms.network.httpclient.excutor.PolicyExecutor;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.chromium.net.ExperimentalCronetEngine;
import org.chromium.net.impl.ImplVersion;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class d2 {
    public static final String CONGESTION_CONTROL_PCC_MULTIPATH = "pcc_multipath";
    public static final String c = "CronetEngineManager";
    public static final String d = "CronetListener";
    public static final String e = "core_textTransmission";
    public static final String f = "core_smallPkt-fec";
    public static final String g = "SCC";
    public static final String h = "HW01,AKDU";
    public static final String i = "RACK,ARC3";
    public static final String j = "HW02,PCCH,IFWd";
    public static final String k = "PRIO";
    public static final int l = 0;
    public static final int m = 5;
    public static final int n = 1;
    public static final int o = 2;
    public static final int p = 3;
    public static final int q = 4;
    public static final String r = "MPTH,MMAB";
    public static final String s = "MPTH,MRTT";
    public static final String t = "MPTH,MMIM";
    public static final String u = "MPTH";
    public static final String v = "STMP";
    public static final int w = 16;
    public static final int x = 300;
    public static final int y = 1000;
    public static final String z = ",";

    /* renamed from: a, reason: collision with root package name */
    public e2 f5210a;
    public ConcurrentHashMap<String, c2> b;

    public c2 getCronetEngineFeature(Context context, PolicyExecutor policyExecutor) {
        String str = policyExecutor.getString("", PolicyNetworkService.ClientConstants.CONGESTION_CONTROL_TYPE).equals(k.a.b) ? policyExecutor.getBoolean("", PolicyNetworkService.ClientConstants.ENABLE_MULTIPATH) ? CONGESTION_CONTROL_PCC_MULTIPATH : k.a.b : "bbrv1";
        Logger.v(c, "congestionType is ".concat(str));
        synchronized (d2.class) {
            c2 c2Var = this.b.get(str);
            if (c2Var != null && c2Var.getCronetEngine() != null) {
                Logger.v(c, "use already cronet engine, congestion type is ".concat(str));
                return c2Var;
            }
            c2 a2 = a(context, policyExecutor);
            getInstance().addCronetEngineFeature(str, a2);
            g2.getInstance().maybeRecordCongestionTypeInfo(str, policyExecutor.getBoolean("", PolicyNetworkService.ClientConstants.QUIC_ENABLE_BANDWIDTH));
            return a2;
        }
    }

    public void addCronetEngineFeature(String str, c2 c2Var) {
        this.b.put(str, c2Var);
    }

    public static d2 getInstance() {
        return b.f5211a;
    }

    private boolean a(String str) {
        File newFile = CreateFileUtil.newFile(str);
        return newFile != null && newFile.exists() && newFile.canWrite();
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public static final d2 f5211a = new d2();
    }

    private String a(String str, String str2) {
        String[] split = str.split(",");
        String[] split2 = str2.split(",");
        HashSet<String> hashSet = new HashSet();
        hashSet.add(v);
        hashSet.addAll(Arrays.asList(split));
        hashSet.addAll(Arrays.asList(split2));
        StringBuilder sb = new StringBuilder();
        for (String str3 : hashSet) {
            if (!TextUtils.isEmpty(str3)) {
                sb.append("," + str3);
            }
        }
        return sb.toString().replaceFirst(",", "");
    }

    private c2 a(Context context, PolicyExecutor policyExecutor) {
        String str;
        int i2;
        int stringToInteger;
        StringBuilder sb;
        String str2;
        c2 c2Var = new c2();
        try {
            ExperimentalCronetEngine.Builder enableNetworkQualityEstimator = new ExperimentalCronetEngine.Builder(context).enableQuic(true).enableHttp2(true).enableNetworkQualityEstimator(true);
            if (ImplVersion.getApiLevel() >= 16) {
                enableNetworkQualityEstimator.enableTextTransmission(Boolean.parseBoolean(policyExecutor.getValue("", e)));
            }
            for (Map.Entry<String, m2> entry : g2.getInstance().getQuicHints().entrySet()) {
                Logger.i(c, "the host of using quic is %s", entry.getKey());
                enableNetworkQualityEstimator.addQuicHint(entry.getKey(), entry.getValue().getPort(), entry.getValue().getAlternatePort());
            }
            if (policyExecutor.getUserConfigValue(f).isEmpty() ? policyExecutor.getBoolean("", PolicyNetworkService.ClientConstants.SMALLPKT_FEC) : StringUtils.stringToBoolean(policyExecutor.getUserConfigValue(f), false)) {
                long j2 = policyExecutor.getLong("", PolicyNetworkService.ClientConstants.SMALLPKT_FEC_INITIALLEVEL, 0L);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(",HW01,AKDU");
                sb2.append(",SCC");
                if (j2 < 0 || j2 > 5) {
                    j2 = 0;
                }
                sb2.append(j2);
                str = sb2.toString();
            } else {
                str = "";
            }
            if (policyExecutor.getBoolean("", PolicyNetworkService.ClientConstants.ENABLE_MULTIPATH)) {
                int i3 = policyExecutor.getInt("", PolicyNetworkService.ClientConstants.MULTIPATH_TYPE);
                if (i3 == 4) {
                    sb = new StringBuilder();
                    sb.append(str);
                    str2 = ",MPTH";
                } else if (i3 == 3) {
                    sb = new StringBuilder();
                    sb.append(str);
                    str2 = ",MPTH,MMIM";
                } else if (i3 == 2) {
                    sb = new StringBuilder();
                    sb.append(str);
                    str2 = ",MPTH,MRTT";
                } else {
                    sb = new StringBuilder();
                    sb.append(str);
                    str2 = ",MPTH,MMAB";
                }
                sb.append(str2);
                str = sb.toString();
            }
            if (k.a.b.equals(policyExecutor.getString("", PolicyNetworkService.ClientConstants.CONGESTION_CONTROL_TYPE))) {
                str = str + ",HW02,PCCH,IFWd";
            }
            if (policyExecutor.getBoolean("", PolicyNetworkService.ClientConstants.REDUNDANT_ACK)) {
                str = str + ",RACK,ARC3";
            }
            if (k.f.f5336a.equals(policyExecutor.getString("", PolicyNetworkService.ClientConstants.PRIORITY_CONTROL_TYPE))) {
                str = str + ",PRIO";
            }
            if (HianalyticsHelper.getInstance().isQuicEnableReport(ContextHolder.getAppContext()) && g4.getInstance().addQuicTrace()) {
                str = str + ",RPTD";
            }
            String string = policyExecutor.getString("", PolicyNetworkService.ClientConstants.QUIC_CONNECTION_OPTIONS);
            String a2 = TextUtils.isEmpty(string) ? v + str : a(str, string);
            String str3 = (k.c.f5333a.equals(policyExecutor.getString("", PolicyNetworkService.QuicConstants.MODULE_NAME)) && g2.getInstance().isHquicProviderSupportSelectQuic()) ? g2.H3 : "QUIC_VERSION_43";
            try {
                JSONObject put = new JSONObject().put(i.f, a2).put("idle_connection_timeout_seconds", 300).put("quic_disable_tls_0rtt", !policyExecutor.getBoolean("", PolicyNetworkService.ClientConstants.TLS_ZERO_RTT));
                put.put("quic_version", str3);
                if (policyExecutor.getBoolean("", PolicyNetworkService.ClientConstants.HWHTTP_ENABLE_CONNECTION_MIGRATION)) {
                    put.put("migrate_sessions_on_network_change_v2", true).put("migrate_sessions_early_v2", true);
                    c2Var.setEnableConnectionMigrated(true);
                    long j3 = policyExecutor.getLong("", PolicyNetworkService.ClientConstants.WAIT_FOR_NEW_NETWORK_TIME_MS, -1L);
                    if (j3 >= 0 && j3 <= 20000) {
                        put.put("wait_for_new_network_time_ms", j3);
                    }
                }
                JSONObject put2 = new JSONObject().put("QUIC", put);
                if (policyExecutor.getBoolean("", PolicyNetworkService.ClientConstants.QUIC_ENABLE_GSO)) {
                    put2.put("quic_enable_gso", true);
                }
                if (policyExecutor.getBoolean("", PolicyNetworkService.ClientConstants.QUIC_ENABLE_BANDWIDTH)) {
                    put2.put("quic_enable_bandwidth", true);
                }
                String value = policyExecutor.getValue("", PolicyNetworkService.ClientConstants.TRAFFIC_CLASS);
                if (!TextUtils.isEmpty(value) && (stringToInteger = StringUtils.stringToInteger(value, -1)) >= 0 && stringToInteger <= 255) {
                    put2.put(x2.TRAFFIC_CLASS, stringToInteger);
                }
                String string2 = policyExecutor.getString("", PolicyNetworkService.ClientConstants.STORAGE_PATH);
                if (a(string2)) {
                    File file = new File(CreateFileUtil.getCanonicalPath(string2) + "/cronet_cache");
                    if (file.exists() || file.mkdirs()) {
                        enableNetworkQualityEstimator.setStoragePath(file.getCanonicalPath());
                        long j4 = policyExecutor.getLong("", PolicyNetworkService.ClientConstants.MAX_SERVER_CONFIGS_STORED_PROPERTIES, 0L);
                        if (j4 > 0 && j4 <= 1000) {
                            put.put("max_server_configs_stored_in_properties", policyExecutor.getLong("", PolicyNetworkService.ClientConstants.MAX_SERVER_CONFIGS_STORED_PROPERTIES, 0L));
                        }
                    }
                } else {
                    Logger.i(c, "Storage file path is invalid.");
                }
                put2.put("server_config_persist_delay", policyExecutor.getInt("", PolicyNetworkService.ClientConstants.SERVER_CONFIG_PERSIST_DELAY)).put("tls13_session_timeout", policyExecutor.getInt("", PolicyNetworkService.ClientConstants.TLS13_SESSION_TIMEOUT)).put("commit_file_delay", policyExecutor.getInt("", PolicyNetworkService.ClientConstants.COMMIT_FILE_DELAY)).put("quic_broken_mode", policyExecutor.getInt("", PolicyNetworkService.ClientConstants.QUIC_BROKEN_MDOE));
                Logger.i(c, "QUIC options:" + put2.toString());
                enableNetworkQualityEstimator.setExperimentalOptions(put2.toString());
                j.e().b(a2);
                i2 = 0;
            } catch (JSONException e2) {
                i2 = 0;
                Logger.e(c, "set QUIC options failed, exception:", e2.getClass().getSimpleName());
            }
            enableNetworkQualityEstimator.setThreadPriority(i2);
            ExperimentalCronetEngine build = enableNetworkQualityEstimator.build();
            c2Var.setCronetEngine(build);
            e2 e2Var = new e2(ExecutorsUtils.newSingleThreadExecutor(d));
            this.f5210a = e2Var;
            if (build instanceof ExperimentalCronetEngine) {
                build.addRequestFinishedListener(e2Var);
            }
        } catch (Throwable th) {
            Logger.i(c, "build CronetEngine failed, the reason:" + StringUtils.anonymizeMessage(th.getMessage()));
        }
        return c2Var;
    }

    public d2() {
        this.b = new ConcurrentHashMap<>(3);
    }
}
