package defpackage;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;

/* loaded from: classes3.dex */
public class cuz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mEarPhoneClose")
    private int f11491a;

    @SerializedName("mDeviceName")
    private String c;

    @SerializedName("mDeviceIdentify")
    private String d;

    @SerializedName("mEarphoneIgnore")
    private int i;

    @SerializedName("mDeviceStatus")
    private int e = -1;

    @SerializedName("mEarphoneConnectState")
    private int b = 255;

    public String c() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String b() {
        return this.c;
    }

    public void e(String str) {
        this.c = str;
    }

    public int a() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public int e() {
        return this.f11491a;
    }

    public void d(int i) {
        this.f11491a = i;
    }

    public int i() {
        return this.i;
    }

    public void a(int i) {
        this.i = i;
    }

    public int d() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof cuz)) {
            return false;
        }
        cuz cuzVar = (cuz) obj;
        return TextUtils.equals(this.d, cuzVar.d) && TextUtils.equals(this.c, cuzVar.c);
    }

    public int hashCode() {
        return Objects.hash(this.d, this.c);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("EarPhoneInfo{mDeviceIdentify='");
        sb.append(this.d.substring(r1.length() - 3));
        sb.append("', mDeviceName='");
        sb.append(this.c);
        sb.append("', mDeviceStatus=");
        sb.append(this.e);
        sb.append(", mEarPhoneClose=");
        sb.append(this.f11491a);
        sb.append(", mEarphoneIgnore=");
        sb.append(this.i);
        sb.append(", mEarphoneConnectState=");
        sb.append(this.b);
        sb.append('}');
        return sb.toString();
    }
}
