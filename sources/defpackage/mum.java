package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class mum {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("resultInfo")
    private String f15181a;

    @SerializedName("cursor")
    private String b;

    @SerializedName("resultCode")
    private String c;

    @SerializedName("list")
    private List<mun> d;

    public String c() {
        return this.c;
    }

    public List<mun> b() {
        return this.d;
    }

    public String d() {
        return this.b;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetThemeListRsp{resultCode=");
        stringBuffer.append(this.c);
        stringBuffer.append("resultInfo=").append(this.f15181a);
        stringBuffer.append(", list=").append(this.d);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
