package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class rbg extends CloudCommonReponse {

    @SerializedName("memberPostIds")
    private List<rbl> c;

    public List<rbl> b() {
        return this.c;
    }
}
