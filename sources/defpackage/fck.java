package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: classes.dex */
public class fck {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("isUpdated")
    private boolean f12441a;

    @SerializedName("result")
    private Map<String, Object> d;

    public Map<String, Object> c() {
        return this.d;
    }

    public void c(Map<String, Object> map) {
        this.d = map;
    }

    public boolean e() {
        return this.f12441a;
    }

    public void b(boolean z) {
        this.f12441a = z;
    }
}
