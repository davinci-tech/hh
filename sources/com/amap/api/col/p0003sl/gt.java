package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IDistanceSearch;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;

/* loaded from: classes8.dex */
public class gt implements IDistanceSearch {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1078a = "gt";
    private Context b;
    private Handler c;
    private DistanceSearch.OnDistanceSearchListener d;

    public gt(Context context) throws AMapException {
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.b = context.getApplicationContext();
        this.c = fo.a();
    }

    @Override // com.amap.api.services.interfaces.IDistanceSearch
    public DistanceResult calculateRouteDistance(DistanceSearch.DistanceQuery distanceQuery) throws AMapException {
        try {
            fm.a(this.b);
            if (distanceQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (a(distanceQuery)) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            DistanceSearch.DistanceQuery m97clone = distanceQuery.m97clone();
            DistanceResult d = new fe(this.b, m97clone).d();
            if (d != null) {
                d.setDistanceQuery(m97clone);
            }
            return d;
        } catch (AMapException e) {
            fd.a(e, f1078a, "calculateWalkRoute");
            throw e;
        }
    }

    private static boolean a(DistanceSearch.DistanceQuery distanceQuery) {
        return distanceQuery.getDestination() == null || distanceQuery.getOrigins() == null || distanceQuery.getOrigins().size() <= 0;
    }

    @Override // com.amap.api.services.interfaces.IDistanceSearch
    public void calculateRouteDistanceAsyn(final DistanceSearch.DistanceQuery distanceQuery) {
        gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gt.1
            @Override // java.lang.Runnable
            public final void run() {
                Message obtainMessage = fo.a().obtainMessage();
                obtainMessage.what = 400;
                obtainMessage.arg1 = 16;
                Bundle bundle = new Bundle();
                DistanceResult distanceResult = null;
                try {
                    try {
                        distanceResult = gt.this.calculateRouteDistance(distanceQuery);
                        bundle.putInt("errorCode", 1000);
                    } catch (AMapException e) {
                        bundle.putInt("errorCode", e.getErrorCode());
                    }
                } finally {
                    obtainMessage.obj = gt.this.d;
                    bundle.putParcelable("result", distanceResult);
                    obtainMessage.setData(bundle);
                    gt.this.c.sendMessage(obtainMessage);
                }
            }
        });
    }

    @Override // com.amap.api.services.interfaces.IDistanceSearch
    public void setDistanceSearchListener(DistanceSearch.OnDistanceSearchListener onDistanceSearchListener) {
        this.d = onDistanceSearchListener;
    }
}
