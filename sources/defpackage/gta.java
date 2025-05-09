package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes8.dex */
public class gta {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(BleConstants.SPORT_TYPE)
    private int f12920a;

    @SerializedName("simpleContent")
    private String b;

    @SerializedName("contentPath")
    private String c;

    public int e() {
        return this.f12920a;
    }

    public String b() {
        return this.c;
    }

    public String a() {
        return this.b;
    }

    public String toString() {
        return "DivingModel{contentPath='" + this.c + "', sportType=" + this.f12920a + ", simpleContent='" + this.b + "'}";
    }
}
