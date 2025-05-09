package defpackage;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class kuw extends kuq {

    @SerializedName("sampleStatDataType")
    private kun d;

    @SerializedName("fields")
    private Map<String, Object> e;

    public kun n() {
        return this.d;
    }

    public Map<String, Object> g() {
        return this.e;
    }

    @Override // defpackage.kuq
    public Map<Integer, JsonObject> j() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Object> entry : this.e.entrySet()) {
            if (entry.getValue() instanceof Map) {
                for (String str : ((Map) entry.getValue()).keySet()) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty(kuh.f14600a, str);
                    jsonObject.addProperty(kuh.h, entry.getKey());
                    jsonObject.addProperty(kuh.d, Integer.valueOf(this.d.a()));
                    hashMap.put(Integer.valueOf(kuf.a().b(this.d.a(), str)), jsonObject);
                }
            }
        }
        return hashMap;
    }

    @Override // defpackage.kuq, defpackage.kul
    public String toString() {
        return super.toString() + "SampleStatReadRequest{mType=" + this.d + ", mFields=" + this.e + '}';
    }
}
