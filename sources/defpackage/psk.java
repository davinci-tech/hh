package defpackage;

import com.huawei.ui.commonui.linechart.icommon.ISupportRangePresentation;
import com.huawei.ui.main.stories.fitness.activity.heartrate.helper.HeartRateDetailData;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class psk implements ISupportRangePresentation {

    /* renamed from: a, reason: collision with root package name */
    private long f16247a;
    private int b;
    private int c;
    private ArrayList<HeartRateDetailData> d;
    private long e;

    public void d(long j, long j2) {
        this.e = j;
        this.f16247a = j2;
    }

    public void c(int i, int i2) {
        this.c = i;
        this.b = i2;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.ISupportRangePresentation
    public long getStartX() {
        return this.e;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.ISupportRangePresentation
    public long getEndX() {
        return this.f16247a;
    }

    public int e() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public ArrayList<HeartRateDetailData> b() {
        return this.d;
    }

    public void b(ArrayList<HeartRateDetailData> arrayList) {
        this.d = arrayList;
    }
}
