package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;

/* loaded from: classes6.dex */
public class php {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(CloudParamKeys.CLIENT_TYPE)
    private int f16135a;

    @SerializedName("timeZone")
    private String b;

    @SerializedName("country")
    private String c;

    @SerializedName("timestamp")
    private long e;

    public php() {
        b(jdl.q(System.currentTimeMillis()));
        a(System.currentTimeMillis());
        c(nsn.b());
        e(LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1010));
    }

    public void a(long j) {
        this.e = j;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(int i) {
        this.f16135a = i;
    }

    public void e(String str) {
        this.c = str;
    }
}
