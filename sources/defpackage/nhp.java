package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nhp {

    @SerializedName("coreCardIds")
    private List<Integer> d = new ArrayList();

    @SerializedName("topCardIds")
    private List<Integer> b = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("domainTopCardIds")
    private List<nhn> f15287a = new ArrayList();

    @SerializedName("domainCards")
    private List<nhr> e = new ArrayList();

    public List<nhn> b() {
        return this.f15287a;
    }

    public List<nhr> a() {
        return this.e;
    }

    public String toString() {
        return "Highlights{mCoreCardIdList=" + this.d + ", mTopCardIdList=" + this.b + ", mDomainTopCardIdList=" + this.f15287a + ", mDomainCardList=" + this.e + '}';
    }
}
