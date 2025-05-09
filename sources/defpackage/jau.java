package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes7.dex */
public class jau {

    @SerializedName("labelName")
    private String b;

    @SerializedName("labelValue")
    private List<String> e;

    public String a() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public List<String> e() {
        return this.e;
    }

    public void a(List<String> list) {
        this.e = list;
    }
}
