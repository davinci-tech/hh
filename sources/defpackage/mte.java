package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.network.embedded.k;

/* loaded from: classes6.dex */
public class mte {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("functionId")
    private String f15161a;

    @SerializedName(k.g)
    private int c;

    @SerializedName("functionName")
    private String d;

    public String b() {
        return this.f15161a;
    }

    public int c() {
        return this.c;
    }

    public String toString() {
        return "FunctionEntranceInfo{mFunctionId=" + this.f15161a + ", mFunctionName=" + this.d + ", mEnable=" + this.c + '}';
    }
}
