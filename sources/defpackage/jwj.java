package defpackage;

import com.google.json.JsonSanitizer;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.phoneprocess.mgr.httpproxy.factory.HttpProxyParamsFactory;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.utils.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class jwj implements HttpProxyParamsFactory {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.httpproxy.factory.HttpProxyParamsFactory
    public Map<String, String> getCommonHeaders() {
        return new HashMap();
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.httpproxy.factory.HttpProxyParamsFactory
    public Map<String, String> getHeaders(String str) {
        HashMap hashMap = new HashMap();
        if (getCommonHeaders() != null && !getCommonHeaders().isEmpty()) {
            hashMap.putAll(getCommonHeaders());
        }
        if (StringUtils.g(str)) {
            LogUtil.h("BaseHttpProxyParamsFactory", "headers is empty in getHeaders");
            return hashMap;
        }
        try {
            JSONObject jSONObject = new JSONObject(JsonSanitizer.sanitize(str));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
            return hashMap;
        } catch (JSONException unused) {
            LogUtil.b("BaseHttpProxyParamsFactory", "JSONException in getHeaders");
            return hashMap;
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.httpproxy.factory.HttpProxyParamsFactory
    public Map<String, String> getCommonBody(DeviceInfo deviceInfo) {
        return new HashMap();
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.httpproxy.factory.HttpProxyParamsFactory
    public Map<String, Object> getBody(String str, DeviceInfo deviceInfo) {
        HashMap hashMap = new HashMap();
        if (getCommonBody(deviceInfo) != null && !getCommonBody(deviceInfo).isEmpty()) {
            hashMap.putAll(getCommonBody(deviceInfo));
        }
        if (StringUtils.g(str)) {
            LogUtil.h("BaseHttpProxyParamsFactory", "body is empty in getBody");
            return hashMap;
        }
        try {
            JSONObject jSONObject = new JSONObject(JsonSanitizer.sanitize(str));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.get(next));
            }
            return hashMap;
        } catch (JSONException unused) {
            LogUtil.b("BaseHttpProxyParamsFactory", "JSONException in getBody");
            return hashMap;
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.httpproxy.factory.HttpProxyParamsFactory
    public Map<String, String> getParams(String str) {
        HashMap hashMap = new HashMap();
        if (StringUtils.g(str)) {
            LogUtil.h("BaseHttpProxyParamsFactory", "params is empty in getParams");
            return hashMap;
        }
        try {
            JSONObject jSONObject = new JSONObject(JsonSanitizer.sanitize(str));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
            return hashMap;
        } catch (JSONException unused) {
            LogUtil.b("BaseHttpProxyParamsFactory", "JSONException in getParams");
            return hashMap;
        }
    }
}
