package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes3.dex */
public class emv extends CloudCommonReponse {

    @SerializedName("hotPathDetailInfo")
    private enc c;

    @SerializedName("citySupportPathAttribute")
    private emj e;

    public enc b() {
        return this.c;
    }

    public emj d() {
        return this.e;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetHotPathDetailRsp{hotPathDetailInfo=" + this.c + ", citySupportPathAttribute=" + this.e + '}';
    }
}
