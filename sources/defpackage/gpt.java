package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.vip.datatypes.MemberTypeInfo;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes4.dex */
public class gpt extends CloudCommonReponse {

    @SerializedName("memberTypeInfoList")
    private List<MemberTypeInfo> d;

    public List<MemberTypeInfo> a() {
        return this.d;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("memberTypeInfoList=");
        sb.append(this.d);
        return sb.toString();
    }
}
