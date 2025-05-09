package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.hms.network.embedded.w9;

/* loaded from: classes8.dex */
public class bgh {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("status")
    private int f356a;

    @SerializedName(Wpt.MODE)
    private int b;

    @SerializedName(w9.o)
    private boolean d;

    public void b(int i) {
        this.b = i;
    }

    public void a(int i) {
        this.f356a = i;
    }

    public void d(boolean z) {
        this.d = z;
    }
}
