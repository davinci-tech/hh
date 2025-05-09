package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes7.dex */
public class quu {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("needSync")
    private boolean f16599a;

    @SerializedName("version")
    private int c;

    @SerializedName("biMetaDataList")
    private List<qur> e;

    public int d() {
        return this.c;
    }

    public boolean c() {
        return this.f16599a;
    }

    public List<qur> e() {
        return this.e;
    }

    public String toString() {
        return "FastingLiteDeviceMessage{mVersion=" + this.c + ", mNeedSync=" + this.f16599a + ", mBiMetaDataList=" + this.e + '}';
    }
}
