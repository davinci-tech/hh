package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes6.dex */
public class muk extends CloudCommonReponse {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("stickers")
    private List<fee> f15179a;

    @SerializedName("recommendedTpls")
    private List<fdy> b;

    @SerializedName("dataWatermarks")
    private List<fef> c;

    @SerializedName("backgroundImages")
    private List<fec> e;

    public List<fec> e() {
        return this.e;
    }

    public List<fdy> b() {
        return this.b;
    }

    public List<fef> a() {
        return this.c;
    }

    public List<fee> c() {
        return this.f15179a;
    }
}
