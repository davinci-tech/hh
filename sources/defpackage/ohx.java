package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.Constants;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes6.dex */
public class ohx {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("cardIds")
    private List<String> f15686a;

    @SerializedName("groupType")
    private int b;

    public int d() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public List<String> c() {
        return this.f15686a;
    }

    public void c(List<String> list) {
        this.f15686a = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CardShowGroup{mGroupType=");
        sb.append(this.b);
        sb.append(", mCardIds=");
        List<String> list = this.f15686a;
        sb.append(list == null ? Constants.NULL : Arrays.toString(list.toArray()));
        sb.append('}');
        return sb.toString();
    }
}
