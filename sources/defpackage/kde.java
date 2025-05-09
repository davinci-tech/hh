package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes5.dex */
public class kde {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mAccDataList")
    List<Integer> f14300a;

    @SerializedName("mPpgDataList")
    List<Integer> b;

    @SerializedName("mSysTick")
    long d;

    public void b(long j) {
        this.d = j;
    }

    public void b(List<Integer> list) {
        this.f14300a = list;
    }

    public void e(List<Integer> list) {
        this.b = list;
    }
}
