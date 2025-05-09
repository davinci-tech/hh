package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class emo extends CloudCommonReponse {

    @SerializedName("cityInfoMap")
    private Map<String, List<HotPathCityInfo>> e = new HashMap();

    public Map<String, List<HotPathCityInfo>> a() {
        return this.e;
    }
}
