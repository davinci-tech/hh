package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes7.dex */
public class iud implements IRequest {

    @SerializedName("types")
    private List<Integer> b;

    public void c(List<Integer> list) {
        this.b = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("types=");
        sb.append(this.b);
        return sb.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/healthExpansion/extended/getCurrentVersion";
    }
}
