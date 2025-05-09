package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.Map;

/* loaded from: classes7.dex */
public class iug extends CloudCommonReponse {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("versions")
    private Map<Integer, Long> f13613a;

    public Map<Integer, Long> a() {
        return this.f13613a;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("versions=");
        sb.append(this.f13613a);
        return sb.toString();
    }
}
