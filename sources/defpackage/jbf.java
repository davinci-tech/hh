package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes7.dex */
public class jbf extends jay {

    @SerializedName("appInfos")
    private List<jbb> e;

    public List<jbb> e() {
        return this.e;
    }

    @Override // defpackage.jay
    public String toString() {
        return "ThirdAuthPermissionRsp{appInfos=" + this.e + '}';
    }
}
