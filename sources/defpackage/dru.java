package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class dru {

    @SerializedName("timestamp")
    private String b;

    @SerializedName("msgContent")
    private String c;

    @SerializedName("msgType")
    private String e;

    public String d() {
        return this.b;
    }

    public String a() {
        return this.e;
    }

    public String b() {
        return this.c;
    }

    public String toString() {
        return "DoctorImInfoMap{mTimestamp=" + this.b + ", mMessageType=" + this.e + ", mMessageContent=" + this.c + "}";
    }
}
