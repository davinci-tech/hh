package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class drz {

    @SerializedName("type")
    private String d;

    @SerializedName("id")
    private String e;

    public drz(String str, String str2) {
        this.d = str;
        this.e = str2;
    }

    public String toString() {
        return "GetUserSampleConfig{mType=" + this.d + ", mId=" + this.e + "}";
    }
}
