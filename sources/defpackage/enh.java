package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class enh implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("hotPathAutoParticipateSwitch")
    private String f12109a;

    @SerializedName("hotPathAutoParticipatePushSwitch")
    private String b;

    public String c() {
        return this.f12109a;
    }

    public void a(String str) {
        this.f12109a = str;
    }

    public void c(boolean z) {
        this.f12109a = z ? "1" : "0";
    }

    public String a() {
        return this.b;
    }

    public void e(boolean z) {
        this.b = z ? "1" : "0";
    }

    public String toString() {
        return "PathAutoSwitchInfo{mHotPathAutoParticipateSwitch='" + this.f12109a + "', mHotPathAutoParticipatePushSwitch=" + this.b + '}';
    }
}
