package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes3.dex */
public class bvd extends CloudCommonReponse {

    @SerializedName("activities")
    private List<ceb> e;

    public List<ceb> d() {
        return this.e;
    }
}
