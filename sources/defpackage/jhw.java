package defpackage;

import com.huawei.hwcommonmodel.fitnessdatatype.DataTotalMotion;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateDetect;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jhw {
    private int d;
    private List<DataTotalMotion> b = new ArrayList(16);
    private HeartRateDetect e = new HeartRateDetect();

    public int c() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public List<DataTotalMotion> d() {
        return this.b;
    }

    public void d(HeartRateDetect heartRateDetect) {
        this.e = heartRateDetect;
    }
}
