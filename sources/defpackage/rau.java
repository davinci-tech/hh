package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class rau extends CloudCommonReponse {

    @SerializedName("dataList")
    private List<ras> b;

    public List<ras> b() {
        return this.b;
    }
}
