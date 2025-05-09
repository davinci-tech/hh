package com.huawei.hianalytics;

import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.framework.config.EvtHeaderAttributeCollector;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class c implements EvtHeaderAttributeCollector {

    /* renamed from: a, reason: collision with root package name */
    public final JSONObject f3838a;

    @Override // com.huawei.hianalytics.framework.config.EvtHeaderAttributeCollector
    public JSONObject doCollector(JSONObject jSONObject, int i) {
        if (this.f3838a == null) {
            return null;
        }
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    this.f3838a.put(next, jSONObject.getString(next));
                }
            } catch (Exception unused) {
                HiLog.e("HAHC", "json exception");
            }
        }
        this.f3838a.put("hmac", "");
        return this.f3838a;
    }

    public c(JSONObject jSONObject) {
        this.f3838a = jSONObject;
    }
}
