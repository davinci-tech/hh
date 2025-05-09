package defpackage;

import com.google.gson.annotations.SerializedName;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
class fhi {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(SocialConstants.PARAM_APP_DESC)
    private String f12513a;

    @SerializedName("sources")
    private List<fhg> b;

    @SerializedName("managers")
    private List<fhg> c;

    @SerializedName("producers")
    private List<fhg> d;

    @SerializedName("sportMode")
    private int e;

    @SerializedName("sportTypeId")
    private int h;

    @SerializedName("sportTypeName")
    private String i;

    @SerializedName("sportTypeRes")
    private String j;

    fhi() {
    }

    public List<fhg> a() {
        return this.c;
    }

    public List<fhg> b() {
        return this.d;
    }

    public List<fhg> c() {
        return this.b;
    }

    public boolean b(int i, int i2) {
        return this.h == i && this.e == i2;
    }

    public void d(fhi fhiVar) {
        if (fhiVar == null) {
            return;
        }
        this.c = b(fhiVar.c, this.c);
        this.d = b(fhiVar.d, this.d);
        this.b = b(fhiVar.b, this.b);
    }

    private <T> List<T> b(List<T> list, List<T> list2) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            arrayList.addAll(list);
        }
        if (list2 != null) {
            arrayList.addAll(list2);
        }
        return arrayList;
    }
}
