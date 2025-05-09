package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes9.dex */
public class rjl {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(BleConstants.LIMIT)
    private int f16787a;

    @SerializedName("start")
    private String e;

    public rjl(int i) {
        this.f16787a = i;
    }

    public void c(int i) {
        this.f16787a = i;
    }

    public void a(String str) {
        this.e = str;
    }
}
