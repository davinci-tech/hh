package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes7.dex */
public class fha {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("language")
    private String f12504a;

    @SerializedName("moduleName")
    private String b;

    @SerializedName("timbre")
    private String c;

    @SerializedName("version")
    private List<Integer> d;

    public void a(List<Integer> list) {
        this.d = list;
    }

    public void d(String str) {
        this.f12504a = str;
    }
}
