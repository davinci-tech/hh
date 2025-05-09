package defpackage;

import android.os.Bundle;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gxx {

    /* renamed from: a, reason: collision with root package name */
    private long f12999a;
    private ISportDataCallback d;
    private long e;

    public gxx(ISportDataCallback iSportDataCallback, long j) {
        this.d = iSportDataCallback;
        d(j);
        this.f12999a = 1L;
    }

    public void aVL_(Bundle bundle) {
        long j = this.f12999a;
        if (j >= this.e) {
            aVK_(bundle);
            this.f12999a = 1L;
        } else {
            this.f12999a = j + 1;
        }
    }

    public void aVK_(Bundle bundle) {
        ISportDataCallback iSportDataCallback = this.d;
        if (iSportDataCallback != null) {
            iSportDataCallback.getSportInfo(bundle);
        } else {
            LogUtil.h("Track_SportDataCallbackProxy", "report mCallback is null");
        }
    }

    public void c(MotionPathSimplify motionPathSimplify) {
        ISportDataCallback iSportDataCallback = this.d;
        if (iSportDataCallback != null) {
            iSportDataCallback.onSummary(motionPathSimplify);
        } else {
            LogUtil.h("Track_SportDataCallbackProxy", "onSummary mCallback is null");
        }
    }

    private void d(long j) {
        if (j <= 0) {
            this.e = 1L;
        } else {
            this.e = j;
        }
    }

    public ISportDataCallback e() {
        return this.d;
    }
}
