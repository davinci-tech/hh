package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class qvn {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mUserBirthDay")
    private int f16614a;

    @SerializedName("mTypeList")
    private List<Integer> c;

    @SerializedName("mWeightBean")
    private cfe e;

    public List<Integer> c() {
        return this.c;
    }

    public cfe d() {
        return this.e;
    }

    public int e() {
        return this.f16614a;
    }

    public String toString() {
        return "PeerComparisionParam{mWeightBean=" + this.e + ", userBirthDay=" + this.f16614a + '}';
    }
}
