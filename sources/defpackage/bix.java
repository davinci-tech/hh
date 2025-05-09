package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.devicesdk.entity.ConnectMode;

/* loaded from: classes3.dex */
public class bix {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("deviceIdentity")
    private String f398a;

    @SerializedName("mIsHandshakeRunning")
    private boolean b;

    @SerializedName("connectmode")
    private ConnectMode d;

    public void d(String str) {
        this.f398a = str;
    }

    public ConnectMode b() {
        return this.d;
    }

    public void d(ConnectMode connectMode) {
        this.d = connectMode;
    }

    public boolean d() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }
}
