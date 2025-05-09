package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class mtd {

    @SerializedName("resultCode")
    private int c;

    @SerializedName("resultDesc")
    private String d;

    @SerializedName("functionList")
    private List<mte> e;

    public int c() {
        return this.c;
    }

    public List<mte> b() {
        return this.e;
    }

    public String toString() {
        return "FunctionEntranceResponse{mResultCode=" + this.c + ", mResultDesc=" + this.d + ", mFunctionList=" + this.e + '}';
    }
}
