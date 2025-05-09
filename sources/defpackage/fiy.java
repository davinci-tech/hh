package defpackage;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes4.dex */
public class fiy {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("breakfast")
    private List<fiu> f12535a;

    @SerializedName("dinner")
    private List<fiu> c;

    @SerializedName("lunch")
    private List<fiu> e;

    public String toString() {
        return new Gson().toJson(this);
    }

    public List<fiu> e() {
        return this.f12535a;
    }

    public void d(List<fiu> list) {
        this.f12535a = list;
    }

    public List<fiu> c() {
        return this.e;
    }

    public void b(List<fiu> list) {
        this.e = list;
    }

    public List<fiu> a() {
        return this.c;
    }

    public void c(List<fiu> list) {
        this.c = list;
    }
}
