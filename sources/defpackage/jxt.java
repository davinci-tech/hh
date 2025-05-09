package defpackage;

import com.huawei.hwcommonmodel.fitnessdatatype.DataTotalMotion;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateDetect;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jxt {
    private int e;
    private List<DataTotalMotion> d = new ArrayList(16);
    private HeartRateDetect b = new HeartRateDetect();

    public int a() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public List<DataTotalMotion> c() {
        return this.d;
    }

    public void a(HeartRateDetect heartRateDetect) {
        this.b = heartRateDetect;
    }
}
