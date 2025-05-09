package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes6.dex */
public class mwv implements Serializable {
    private static final long serialVersionUID = 5896619171447527539L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("supportArV2")
    private int f15225a;

    @SerializedName("model")
    private List<String> c;

    @SerializedName("version")
    private String d;

    public String b() {
        return this.d;
    }

    public List<String> d() {
        return this.c;
    }

    public void e(int i) {
        this.f15225a = i;
    }
}
