package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class fcs {

    @SerializedName("monthlyDailyAdvices")
    private List<fcp> b;

    @SerializedName("monthlyDailyTasks")
    private List<List<fcx>> d;

    public List<fcp> d() {
        return this.b;
    }

    public List<List<fcx>> c() {
        return this.d;
    }
}
