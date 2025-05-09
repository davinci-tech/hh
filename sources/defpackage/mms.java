package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes8.dex */
public class mms extends CloudCommonReponse {

    @SerializedName("aiConfigInfos")
    private List<mmq> d;

    public List<mmq> e() {
        return this.d;
    }
}
