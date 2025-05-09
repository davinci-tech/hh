package defpackage;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class kuq extends kul {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("sortOrder")
    private int f14611a;

    @SerializedName(TypedValues.CycleType.S_WAVE_OFFSET)
    private int b;

    @SerializedName("count")
    private int c;

    public int i() {
        return this.c;
    }

    public int h() {
        return this.f14611a;
    }

    public Map j() {
        return new HashMap();
    }

    @Override // defpackage.kul
    public String toString() {
        return "ReadDataRequest{mCount=" + this.c + ", mOffset=" + this.b + ", mSortOrder=" + this.f14611a + '}';
    }
}
