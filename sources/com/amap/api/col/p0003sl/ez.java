package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.cloud.CloudItem;
import com.amap.api.services.cloud.CloudItemDetail;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.CloudParamKeys;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public abstract class ez<T, V> extends ew<T, V> {
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ka
    public byte[] getEntityBytes() {
        return null;
    }

    public ez(Context context, T t) {
        super(context, t);
        this.f1031a = false;
    }

    protected static JSONArray a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject != null) {
            return optJSONObject.optJSONArray("list");
        }
        return null;
    }

    protected static int b(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2 = jSONObject.optJSONObject("data");
        if (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject(CloudParamKeys.INFO)) == null) {
            return 0;
        }
        return optJSONObject.optInt("count");
    }

    protected static CloudItemDetail c(JSONObject jSONObject) throws JSONException {
        CloudItemDetail cloudItemDetail = new CloudItemDetail(fl.a(jSONObject, "id"), new LatLonPoint(jSONObject.optDouble("point_y"), jSONObject.optDouble("point_x")), fl.a(jSONObject, "title"), fl.a(jSONObject, "address"));
        cloudItemDetail.setCreatetime(fl.a(jSONObject, "gmt_create"));
        cloudItemDetail.setUpdatetime(fl.a(jSONObject, "gmt_modified"));
        if (jSONObject.has("_distance")) {
            String optString = jSONObject.optString("_distance");
            if (!c(optString)) {
                cloudItemDetail.setDistance(Integer.parseInt(optString));
            }
        }
        return cloudItemDetail;
    }

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev, com.amap.api.col.p0003sl.ka
    public Map<String, String> getRequestHead() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);
        hashMap.put(j2.v, Constants.GZIP);
        hashMap.put("User-Agent", "AMAP SDK Android Search 9.2.0");
        hashMap.put("X-INFO", hq.b(this.i));
        hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", "9.2.0", "cloud"));
        hashMap.put("logversion", "2.1");
        return hashMap;
    }

    protected static void a(CloudItem cloudItem, JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        HashMap<String, String> hashMap = new HashMap<>();
        if (keys == null) {
            return;
        }
        while (keys.hasNext()) {
            String next = keys.next();
            if (next != null) {
                hashMap.put(next.toString(), jSONObject.optString(next.toString()));
            }
        }
        cloudItem.setCustomfield(hashMap);
    }

    private static boolean c(String str) {
        return str == null || str.equals("") || str.equals("[]");
    }

    @Override // com.amap.api.col.p0003sl.ev
    protected final V a(byte[] bArr) throws AMapException {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Exception e) {
            fd.a(e, "ProtocalHandler", "loadData");
            str = null;
        }
        if (str == null || str.equals("")) {
            return null;
        }
        fd.c(str);
        return a(str);
    }
}
