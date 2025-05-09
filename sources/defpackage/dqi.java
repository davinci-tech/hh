package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class dqi {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("resultCode")
    private int f11781a;

    @SerializedName("resultDesc")
    private String c;

    @SerializedName("duration")
    private int e;

    @SerializedName("resultType")
    private int g;

    @SerializedName("isFromCloud")
    private boolean d = false;

    @SerializedName("fileList")
    private List<dqh> b = new ArrayList(0);

    public int d() {
        return this.f11781a;
    }

    public boolean c() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public List<dqh> e() {
        return this.b;
    }
}
