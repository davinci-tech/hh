package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class muo {

    @SerializedName("language")
    private String c;

    @SerializedName("previewUrl")
    private String d;

    @SerializedName("previewName")
    private String e;

    public String c() {
        return this.d;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PreviewInfo{previewUrl=");
        stringBuffer.append(this.d);
        stringBuffer.append(", previewName=").append(this.e);
        stringBuffer.append(", language=").append(this.c);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
