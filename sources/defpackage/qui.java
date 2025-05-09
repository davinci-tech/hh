package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class qui {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("currentFastingLiteMode")
    private quv f16594a;

    @SerializedName("isPrivacyAccepted")
    private boolean b;

    @SerializedName("isAppEnabled")
    private boolean c;

    @SerializedName("isNotifyEnabled")
    private boolean e;

    public boolean c() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public quv d() {
        return this.f16594a;
    }

    public boolean a() {
        return this.e;
    }

    public String toString() {
        return "FastingLiteAppSetting{isAppEnabled=" + this.c + ", currentFastingLiteMode=" + this.f16594a + ", isPrivacyAccepted=" + this.b + ", isNotifyEnabled=" + this.e + '}';
    }
}
