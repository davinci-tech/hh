package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: classes9.dex */
public class kup extends kuq {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("dataSourceId")
    private String f14610a;

    @SerializedName("dataType")
    private kun b;

    @SerializedName("fields")
    private Map<String, Object> d;

    public kun l() {
        return this.b;
    }

    public String g() {
        return this.f14610a;
    }

    public Map<String, Object> n() {
        return this.d;
    }

    @Override // defpackage.kuq, defpackage.kul
    public String toString() {
        return super.toString() + "SampleStatReadRequest{mType=" + this.b + "mDataSourceId=" + this.f14610a + ", mFields=" + this.d + '}';
    }
}
