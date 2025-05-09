package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class feb implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mWatermarkId")
    private int f12466a;

    @SerializedName("mBitmapId")
    private int d;

    public void d(int i) {
        this.f12466a = i;
    }

    public int a() {
        return this.f12466a;
    }

    public void e(int i) {
        this.d = i;
    }

    public int d() {
        return this.d;
    }
}
