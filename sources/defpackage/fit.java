package defpackage;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes4.dex */
public class fit {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("energy")
    private String f12531a;

    @SerializedName("management")
    private String b;

    @SerializedName("notEatFoodList")
    private List<fiu> d;

    public void e(String str) {
        this.f12531a = str;
    }

    public void c(String str) {
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
