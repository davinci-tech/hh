package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes5.dex */
public class lmv extends lmy {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("capabilities")
    private List<Integer> f14770a;

    @SerializedName("deviceId")
    private String b;

    @SerializedName("action")
    private int c;

    @SerializedName("language")
    private String d;

    @SerializedName("country")
    private String e;

    @SerializedName("timestamp")
    private long f;

    @SerializedName(ParsedFieldTag.MEDAL_ID)
    private String i;

    public int d() {
        return this.c;
    }

    public long h() {
        return this.f;
    }

    public List<Integer> j() {
        return this.f14770a;
    }

    public String i() {
        return this.i;
    }
}
