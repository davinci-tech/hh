package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class fdd {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("user_info")
    private fdf f12455a;

    @SerializedName("sleep_info")
    private List<fdb> b;

    @SerializedName("questionnaire")
    private List<fcy> e;

    public void e(List<fdb> list) {
        this.b = list;
    }

    public void e(fdf fdfVar) {
        this.f12455a = fdfVar;
    }

    public void c(List<fcy> list) {
        this.e = list;
    }
}
