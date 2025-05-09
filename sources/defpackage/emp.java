package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.operation.utils.Constants;

/* loaded from: classes3.dex */
public class emp extends CloudCommonReponse {

    @SerializedName("cityInfo")
    private HotPathCityInfo c;

    @SerializedName("countryId")
    private String d;

    public HotPathCityInfo c() {
        return this.c;
    }

    public HotPathCityInfo d() {
        return this.c;
    }

    public String a() {
        return this.d;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder("GetCityInfoWithGpsRsp{cityInfo=");
        HotPathCityInfo hotPathCityInfo = this.c;
        sb.append(hotPathCityInfo == null ? Constants.NULL : hotPathCityInfo.toString());
        sb.append('}');
        return sb.toString();
    }
}
