package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes7.dex */
public class sap extends CloudCommonReponse {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("totalCount")
    private int f16989a;

    public int e() {
        return this.f16989a;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetUserGrpListRsp{totalCount=" + this.f16989a + '}';
    }
}
