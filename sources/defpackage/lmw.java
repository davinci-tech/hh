package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes5.dex */
public class lmw extends lmy {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("resourceType")
    private int f14771a;

    @SerializedName(ParsedFieldTag.MEDAL_ID)
    private String b;

    @SerializedName("capabilities")
    private int c;

    @SerializedName("action")
    private int e;

    public int d() {
        return this.c;
    }

    public String j() {
        return this.b;
    }
}
