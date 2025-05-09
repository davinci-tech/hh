package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class feh implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mDataMap")
    private Map<String, String> f12468a = new HashMap();

    @SerializedName("mThumbnail")
    private String b;

    @SerializedName("mPoints")
    private List<hjd> d;

    public void d(Map<String, String> map) {
        this.f12468a = map;
    }

    public Map<String, String> b() {
        return this.f12468a;
    }

    public void a(List<hjd> list) {
        this.d = list;
    }

    public List<hjd> a() {
        return this.d;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }
}
