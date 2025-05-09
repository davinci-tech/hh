package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.hquic.HQUICProvider;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.chromium.net.UrlResponseInfo;

/* loaded from: classes9.dex */
public class n2 {
    public static final String SERVER_IP = "server_ip";

    /* renamed from: a, reason: collision with root package name */
    public static final String f5387a = "QuicStats";
    public static final String d = ",";
    public static final String e = ":";
    public static final String CONGESTION_CONTROL_TYPE = "congestion_control_type";
    public static final String MULTIPATH_ALGORITHM = "multipath_scheduler";
    public static final Set<String> b = new HashSet(Arrays.asList(JsbMapKeyNames.H5_CLIENT_ID, "bytes_sent", "packets_sent", "packets_discarded", "bytes_received", "packets_received", "packets_processed", "bytes_retransmitted", "packets_retransmitted", "bytes_spuriously_retransmitted", "packets_spuriously_retransmitted", "packets_lost", "srtt_us", "estimated_bandwidth_bps", "is_fec_enabled", "current_fec_policy", "packet_loss_rate", "is_text_transmission_used", "tlp_count", "rto_count", "pto_count", "zero_rtt_mode", "zero_rtt_early_data_reason", "is_rack_enabled", "rack_count", "is_pcch_enabled", "sending_rate", "migration_result", "migration_status", "alternativejob_start_status", "alternativejob_race_res", "inner_quic_error", "inner_net_error", "mark_broken", "is_ript_enabled", "ript_count", CONGESTION_CONTROL_TYPE, MULTIPATH_ALGORITHM, "gso_status"));
    public static final Set<String> c = new HashSet(Arrays.asList("fec_restored", "fec_actual_bytes_sent", "fec_actual_packets_sent", "fec_actual_bytes_received", "fec_actual_packets_received", "fec_bytes_sent", "fec_packets_send", "fec_packet_lost", "fec_frames_received", "fec_protected_packets_sent"));

    public static LinkedHashMap<String, String> getQuicStatsMap(UrlResponseInfo urlResponseInfo) {
        String str;
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        if (urlResponseInfo == null) {
            return linkedHashMap;
        }
        try {
            str = urlResponseInfo.getQuicStats();
        } catch (Throwable unused) {
            Logger.e(f5387a, "getQuicStats meet error");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return linkedHashMap;
        }
        Logger.v(f5387a, "Cronet urlResponseInfo.getQuicStats(): " + str);
        for (String str2 : str.split(",")) {
            String[] split = str2.split(":");
            if (split.length == 2) {
                linkedHashMap.put(split[0], split[1]);
            }
        }
        return linkedHashMap;
    }

    public static void collectQuicStats(x2 x2Var, j2 j2Var) {
        LinkedHashMap<String, String> quicStatsMap = getQuicStatsMap(j2Var.getResponseInfo());
        if (quicStatsMap.isEmpty()) {
            return;
        }
        x2Var.put("hquic_version", new HQUICProvider(ContextHolder.getResourceContext()).getVersion());
        boolean z = false;
        for (Map.Entry<String, String> entry : quicStatsMap.entrySet()) {
            if (b.contains(entry.getKey())) {
                if ("is_fec_enabled".equals(entry.getKey()) && "1".equals(entry.getValue())) {
                    z = true;
                }
            } else if (z && c.contains(entry.getKey())) {
            }
            x2Var.put(entry.getKey(), entry.getValue());
        }
    }
}
