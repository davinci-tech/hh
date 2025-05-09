package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class rjg extends CloudCommonReponse {

    @SerializedName("shoppingInfoItems")
    private List<rjq> d = new ArrayList();

    @SerializedName("pagination")
    private rjm c = new rjm();

    public List<rjq> e() {
        return this.d;
    }

    public rjm d() {
        return this.c;
    }
}
