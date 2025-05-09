package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class iyb {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("CONTENT")
    private Map<String, Object> f13657a;

    @SerializedName("pageProperties")
    private List<Map<String, Object>> c;

    @SerializedName("scene")
    private Map<String, Object> e;

    public Map<String, Object> e() {
        return this.f13657a;
    }

    public void d(Map<String, Object> map) {
        this.f13657a = map;
    }

    public List<Map<String, Object>> d() {
        return this.c;
    }

    public void d(List<Map<String, Object>> list) {
        this.c = list;
    }

    public Map<String, Object> a() {
        return this.e;
    }

    public void e(Map<String, Object> map) {
        this.e = map;
    }

    public void e(Map<String, Object> map, String str) {
        map.put("sessionId", str);
    }

    public String toString() {
        return "HealthRootBiInfo{content=" + this.f13657a + ", pageProperties=" + this.c + ", scene=" + this.e + '}';
    }
}
