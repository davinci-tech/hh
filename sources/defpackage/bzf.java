package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes8.dex */
public class bzf {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("isNonsense")
    boolean f558a;

    @SerializedName("audioBeginTime")
    long b;

    @SerializedName(BleConstants.KEY_PATH)
    String d;

    public String b() {
        return this.d;
    }

    public boolean c() {
        return this.f558a;
    }

    public long d() {
        return this.b;
    }

    public String toString() {
        return "SleepMonitorShare{path='" + this.d + "', isNonsense=" + this.f558a + ", audioBeginTime=" + this.b + '}';
    }
}
