package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class emt extends CloudCommonReponse {

    @SerializedName("cityInfos")
    private List<HotPathCityInfo> b = new ArrayList();

    public List<HotPathCityInfo> a() {
        return this.b;
    }
}
