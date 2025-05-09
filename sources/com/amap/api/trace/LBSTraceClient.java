package com.amap.api.trace;

import android.content.Context;
import com.amap.api.col.p0003sl.dv;
import com.amap.api.col.p0003sl.hj;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.col.p0003sl.hx;
import java.util.List;

/* loaded from: classes8.dex */
public class LBSTraceClient {
    public static final String LOCATE_TIMEOUT_ERROR = "定位超时";
    public static final String MIN_GRASP_POINT_ERROR = "轨迹点太少或距离太近,轨迹纠偏失败";
    public static final String TRACE_SUCCESS = "纠偏成功";
    public static final int TYPE_AMAP = 1;
    public static final int TYPE_BAIDU = 3;
    public static final int TYPE_GPS = 2;

    /* renamed from: a, reason: collision with root package name */
    private static LBSTraceBase f1606a;
    private static volatile LBSTraceClient b;

    public LBSTraceClient(Context context) throws Exception {
        a(context);
    }

    private LBSTraceClient() {
    }

    public static LBSTraceClient getInstance(Context context) throws Exception {
        if (b == null) {
            synchronized (LBSTraceClient.class) {
                if (b == null) {
                    a(context);
                    b = new LBSTraceClient();
                }
            }
        }
        return b;
    }

    private static void a(Context context) throws Exception {
        hx a2 = hw.a(context, dv.a());
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new Exception(a2.b);
        }
        if (context != null) {
            f1606a = new hj(context.getApplicationContext());
        }
    }

    public void queryProcessedTrace(int i, List<TraceLocation> list, int i2, TraceListener traceListener) {
        LBSTraceBase lBSTraceBase = f1606a;
        if (lBSTraceBase != null) {
            lBSTraceBase.queryProcessedTrace(i, list, i2, traceListener);
        }
    }

    public void startTrace(TraceStatusListener traceStatusListener) {
        LBSTraceBase lBSTraceBase = f1606a;
        if (lBSTraceBase != null) {
            lBSTraceBase.startTrace(traceStatusListener);
        }
    }

    public void stopTrace() {
        LBSTraceBase lBSTraceBase = f1606a;
        if (lBSTraceBase != null) {
            lBSTraceBase.stopTrace();
        }
    }

    public void destroy() {
        LBSTraceBase lBSTraceBase = f1606a;
        if (lBSTraceBase != null) {
            lBSTraceBase.destroy();
            a();
        }
    }

    private static void a() {
        f1606a = null;
        b = null;
    }
}
