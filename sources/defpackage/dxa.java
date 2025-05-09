package defpackage;

import android.util.Log;
import com.huawei.health.hwhealthtrackalgo.c;
import com.huawei.health.hwhealthtrackalgo.stat.FilterResultListener;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public final class dxa {
    public FilterResultListener i;
    public Map<String, Integer> b = new HashMap(16);
    public int c = 0;
    public int e = 0;

    /* renamed from: a, reason: collision with root package name */
    public int f11872a = 0;
    public int d = 0;
    public int f = 0;
    public int h = 0;

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("Total = ");
        stringBuffer.append(this.c);
        stringBuffer.append(" Filted = ");
        stringBuffer.append(this.h);
        stringBuffer.append(" ByAccuracy = ");
        stringBuffer.append(this.f);
        stringBuffer.append(" ByTime = ");
        stringBuffer.append(this.e);
        stringBuffer.append(" ByShortDis = ");
        stringBuffer.append(this.f11872a);
        stringBuffer.append(" ByOverSpeed = ");
        stringBuffer.append(this.d);
        return stringBuffer.toString();
    }

    public final void c(int i, c cVar, long j) {
        int i2 = 1;
        if (i != 1 || cVar == null) {
            i2 = 0;
        } else {
            this.h++;
            if (cVar instanceof dwr) {
                this.f++;
                i2 = 2;
            } else if (cVar instanceof dww) {
                this.e++;
                i2 = 4;
            } else if (cVar instanceof dwx) {
                this.f11872a++;
                i2 = 3;
            } else if (cVar instanceof dws) {
                this.d++;
            } else {
                Log.e("Track_StatFilterPointsMgr", "stat else");
                i2 = 199;
            }
        }
        FilterResultListener filterResultListener = this.i;
        if (filterResultListener != null) {
            filterResultListener.onFilterResult(i2, j);
        }
    }
}
