package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes3.dex */
public class emw extends CloudCommonReponse {

    @SerializedName("history")
    private enj e;

    public enj d() {
        return this.e;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetHotPathParticipateInfoRsp{" + super.toString() + ", HotPathParticipateHistory='" + this.e + "'}";
    }
}
