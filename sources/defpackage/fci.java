package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class fci implements Serializable {

    @SerializedName("advice_code")
    private String b;

    @SerializedName("advice_type")
    private int c;

    @SerializedName("advice_id")
    private int d;

    public void c(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }

    public void e(int i) {
        this.c = i;
    }

    public int e() {
        return this.c;
    }

    public void d(String str) {
        this.b = str;
    }

    public String c() {
        return this.b;
    }

    public String toString() {
        return "{\"DailyAdvice\": {\"adviceId\":" + this.d + ", \"adviceType\":" + this.c + ", \"adviceCode\": \"" + this.b + "\"}}";
    }
}
