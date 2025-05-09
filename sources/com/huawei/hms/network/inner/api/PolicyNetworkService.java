package com.huawei.hms.network.inner.api;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public abstract class PolicyNetworkService extends NetworkService {
    public static final String NETWORK_CONFIG_PRE = "core_";
    public static final String NETWORK_QUIC_PRE = "quic_";
    public static final String NETWORK_SWITCH_CONFIG_PRE = "core_switch_";
    public static final String TAG = "PolicyNetworkService";

    public static final class ClientConstants {
        public static final String COMMIT_FILE_DELAY = "core_commit_file_delay";
        public static final String CONGESTION_CONTROL_TYPE = "core_congestion_control_type";
        public static final String DISABLE_WEAKNETWORK_RETRY = "core_disable_weaknetwork_retry";
        public static final String ENABLE_MULTIPATH = "core_enable_multipath";
        public static final String ENABLE_PLAINTEXT_URL_PATH = "core_enable_plaintext_url_path";
        public static final String FOLLOW_REDIRECTS = "core_follow_redirects";
        public static final String FOLLOW_SSL_REDIRECTS = "core_follow_ssl_redirects";
        public static final String HWHTTP_ENABLE_CONNECTION_MIGRATION = "core_hwhttp_enable_connection_migration";
        public static final String MAX_SERVER_CONFIGS_STORED_PROPERTIES = "core_max_server_configs_stored_properties";
        public static final String MULTIPATH_TYPE = "core_multipath_type";
        public static final String NI_NAME = "core_netif_name";
        public static final String PRIORITY_CONTROL_TYPE = "core_priority_control_type";
        public static final String QUIC_BROKEN_MDOE = "core_quic_broken_mode";
        public static final String QUIC_CONNECTION_OPTIONS = "core_hquic_connection_options";
        public static final String QUIC_ENABLE_BANDWIDTH = "core_quic_enable_bandwidth";
        public static final String QUIC_ENABLE_GSO = "core_quic_enable_gso";
        public static final String REDUNDANT_ACK = "core_redundant_ack";
        public static final String SERVER_CONFIG_PERSIST_DELAY = "core_server_config_persist_delay";
        public static final String SMALLPKT_FEC = "core_smallpkt_fec";
        public static final String SMALLPKT_FEC_INITIALLEVEL = "core_smallpkt_fec_initiallevel";
        public static final String STORAGE_PATH = "core_storage_path";
        public static final String SUPPORT_PROTOCOLS = "core_protocols";
        public static final String TLS13_SESSION_TIMEOUT = "core_tls13_session_timeout";
        public static final String TLS_ZERO_RTT = "core_tls_zero_rtt";
        public static final String TRAFFIC_CLASS = "core_traffic_class";
        public static final String WAIT_FOR_NEW_NETWORK_TIME_MS = "core_wait_for_new_network_time_ms";
    }

    public static final class GlobalConstants {
        public static final String AB_ALLOWED_LIST = "core_ab_allowed_list";
        public static final String AB_EXPIRED_TIME = "core_ab_expired_time";
        public static final String CONFIG_EXPIRED_TIME = "core_config_expired_time";
        public static final String CONNECT_KEEP_ALIVE_DURATION = "core_connect_keep_alive_duration";
        public static final String CONNECT_POOL_SIZE = "core_connect_pool_size";
        public static final String ENABLE_ALLLINK_DELAY_ANALYSIS = "core_enable_alllink_delay_analysis";
        public static final String ENABLE_IPV6 = "core_enable_ipv6";
        public static final String ENABLE_PRIVACY_POLICY = "core_enable_privacy_policy";
        public static final String ENABLE_SITE_DETECT = "core_enable_site_detect";
        public static final String HA_TAG = "core_ha_tag";
        public static final String INDEPENDENT_CONFIGS = "independent_configs";
        public static final String PREFERRED = "_preferred";
        public static final String PROFILE_CONFIGS = "profile_configs";
        public static final String QUIC_REPORT_RATE = "core_quic_report_rate";
        public static final String REPORT_RATE = "core_report_rate";
        public static final String ENABLE_HTTPDNS = "core_enable_httpdns";
        public static final String DNS_RESULT_TTL = "core_dns_result_ttl";
        public static final String ENABLE_DETECT_WITH_HTTP = "core_enable_detect_with_http";
        public static final String SITE_DETECT_THRESHOLD = "core_site_detect_threshold";
        public static final List<String> GLOBAL_CONSTANTS = Collections.unmodifiableList(Arrays.asList(ENABLE_HTTPDNS, DNS_RESULT_TTL, ENABLE_DETECT_WITH_HTTP, "core_enable_ipv6_preferred", "core_enable_site_detect", SITE_DETECT_THRESHOLD));
    }

    public static final class NetworkServiceConstants {
        public static final String IS_CLOUD_SERVICE_ENABLED = "is_cloud_service_enabled";
        public static final String IS_DYNAMIC = "is_dynamic";
    }

    public static final class ProfileConstants {
        public static final String DEFAULT = "default";
        public static final String FILE_MANAGER = "file_manager";
        public static final String RESTFUL = "restful";
        public static final String SCENE_TYPE = "core_scene_type";
        public static final String SCENE_TYPE_OPTION_KEY = "scene_type";
        public static final String SEPARATOR = "|";
    }

    public static final class QuicConstants {
        public static final String MODULE_NAME = "quic_module_name";
        public static final String QUICHINT = "quic_quichint";
    }

    public static final class RequestConstants {
        public static final String CALL_TIMEOUT = "core_call_timeout";
        public static final String CONCURRENT_CONNECT_DELAY = "core_concurrent_connect_delay";
        public static final String CONNECT_EMPTY_BODY = "core_connect_empty_body";
        public static final String CONNECT_TIMEOUT = "core_connect_timeout";
        public static final String ENABLE_CONCURRENT_CONNECT = "core_enable_concurrent_connect";
        public static final String ENABLE_DYNAMIC_PING = "core_enable_dynamic_ping";
        public static final String ENABLE_SITE_DETECT = "core_enable_site_detect";
        public static final String INNER_CONNECT_EMPTY_BODY = "core_inner_connect_empty_body";
        public static final String METRICS_DATA = "core_metrics_data";
        public static final String PING_INTERVAL = "core_ping_interval";
        public static final String READ_TIMEOUT = "core_read_timeout";
        public static final String RETRY_TIME = "core_retry_time";
        public static final String WRITE_TIMEOUT = "core_write_timeout";
        public static final String ENABLE_TRAFFIC_CONTROL_WITH_429 = "core_enable_traffic_control_with_429";
        public static final String MAX_REQUEST_DISCRETE_TIME = "core_max_request_discrete_time";
        public static final String MAX_RETRY_WAITING_TIME = "core_max_retry_waiting_time";
        public static final String METRIC_POLICY = "core_metric_policy";
        public static final List<String> REQUEST_CONSTANTS = Collections.unmodifiableList(Arrays.asList("core_connect_timeout", "core_read_timeout", "core_write_timeout", "core_call_timeout", "core_ping_interval", "core_concurrent_connect_delay", "core_retry_time", "core_enable_concurrent_connect", "core_connect_empty_body", "core_metrics_data", ENABLE_TRAFFIC_CONTROL_WITH_429, MAX_REQUEST_DISCRETE_TIME, MAX_RETRY_WAITING_TIME, METRIC_POLICY));
    }

    public abstract void beginRequest(RequestContext requestContext);

    public abstract void endRequest(RequestContext requestContext);

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public abstract String getServiceType();

    public abstract String getValue(String str, String str2);

    public abstract Map<String, String> getValues(String str, String... strArr);

    public String getString(String str, String str2) {
        return getValue(str, str2);
    }

    public String getPolicyNetworkServiceParams() {
        return "";
    }

    public Map<String, String> getMap(String str, String str2) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(getValue(str, str2));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
        } catch (JSONException unused) {
            Logger.i(TAG, "getMap error " + str2);
        }
        return hashMap;
    }

    public long getLong(String str, String str2, long j) {
        return StringUtils.stringToLong(getValue(str, str2), -1L);
    }

    public int getInt(String str, String str2) {
        return StringUtils.stringToInteger(getValue(str, str2), -1);
    }

    public boolean getBoolean(String str, String str2) {
        return StringUtils.stringToBoolean(getValue(str, str2), false);
    }
}
