package com.amap.api.col.p0003sl;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.trace.LBSTraceBase;
import com.amap.api.trace.LBSTraceClient;
import com.amap.api.trace.TraceListener;
import com.amap.api.trace.TraceLocation;
import com.amap.api.trace.TraceStatusListener;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes8.dex */
public final class hj implements LocationSource.OnLocationChangedListener, LBSTraceBase {
    private Context b;
    private CoordinateConverter c;
    private la d;
    private la e;
    private TraceStatusListener h;
    private au i;
    private long f = 2000;
    private int g = 5;
    private List<TraceLocation> j = new ArrayList();
    private int k = 0;
    private int l = 0;
    private long m = 0;
    private TraceLocation o = null;
    private List<LatLng> p = new ArrayList();
    private List<LatLng> q = new ArrayList();
    private List<LatLng> r = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    int f1122a = Runtime.getRuntime().availableProcessors();
    private BlockingQueue<Runnable> s = new LinkedBlockingQueue();
    private BlockingQueue<Runnable> t = new LinkedBlockingQueue();
    private c n = new c(Looper.getMainLooper());

    public hj(Context context) {
        this.b = context.getApplicationContext();
        this.c = new CoordinateConverter(this.b);
        ht.a().a(this.b);
        this.d = du.a(this.f1122a * 2, this.s, "AMapTraceManagerProcess");
        this.e = du.a(this.f1122a * 2, this.t, "AMapTraceManagerRequest");
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public final void queryProcessedTrace(int i, List<TraceLocation> list, int i2, TraceListener traceListener) {
        try {
            this.d.a(new a(i, list, i2, traceListener));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public final void setLocationInterval(long j) {
        this.f = j;
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public final void setTraceStatusInterval(int i) {
        this.g = Math.max(i, 2);
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public final void startTrace(TraceStatusListener traceStatusListener) {
        if (this.b == null) {
            Log.w("LBSTraceClient", "Context need to be initialized");
            return;
        }
        this.m = System.currentTimeMillis();
        this.h = traceStatusListener;
        if (this.i == null) {
            au auVar = new au(this.b);
            this.i = auVar;
            auVar.a(this.f);
            this.i.activate(this);
        }
    }

    @Override // com.amap.api.maps.LocationSource.OnLocationChangedListener
    public final void onLocationChanged(Location location) {
        TraceStatusListener traceStatusListener;
        if (this.h != null) {
            try {
                if (System.currentTimeMillis() - this.m >= OpAnalyticsConstants.H5_LOADING_DELAY && (traceStatusListener = this.h) != null) {
                    traceStatusListener.onTraceStatus(null, null, LBSTraceClient.LOCATE_TIMEOUT_ERROR);
                }
                this.m = System.currentTimeMillis();
                Bundle extras = location.getExtras();
                int i = extras.getInt("errorCode");
                if (i != 0) {
                    Log.w("LBSTraceClient", "Locate failed [errorCode:\"" + i + "\"  errorInfo:" + extras.getString(MyLocationStyle.ERROR_INFO) + "\"]");
                    return;
                }
                synchronized (this.j) {
                    TraceLocation traceLocation = new TraceLocation(location.getLatitude(), location.getLongitude(), location.getSpeed(), location.getBearing(), location.getTime());
                    if (a(this.o, traceLocation)) {
                        return;
                    }
                    this.j.add(traceLocation);
                    this.o = traceLocation;
                    int i2 = this.k + 1;
                    this.k = i2;
                    if (i2 == this.g) {
                        this.l += i2;
                        a();
                        this.k = 0;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static boolean a(TraceLocation traceLocation, TraceLocation traceLocation2) {
        return traceLocation != null && traceLocation.getLatitude() == traceLocation2.getLatitude() && traceLocation.getLongitude() == traceLocation2.getLongitude();
    }

    private void a() {
        int size = this.j.size();
        if (size < this.g) {
            return;
        }
        if (size <= 50) {
            ArrayList arrayList = new ArrayList(this.j);
            queryProcessedTrace(0, arrayList, 1, new b(arrayList));
            return;
        }
        int i = size - 50;
        if (i < 0) {
            return;
        }
        a(new ArrayList(this.j.subList(i - this.g, i)));
        ArrayList arrayList2 = new ArrayList(this.j.subList(i, size));
        queryProcessedTrace(i, arrayList2, 1, new b(arrayList2));
    }

    private void a(List<TraceLocation> list) {
        Iterator<LatLng> it;
        hj hjVar = this;
        synchronized (hjVar.r) {
            try {
                if (list.size() <= 0) {
                    return;
                }
                if (hjVar.r.size() <= 0) {
                    return;
                }
                double d = 0.0d;
                LatLng latLng = null;
                double d2 = 0.0d;
                TraceLocation traceLocation = null;
                for (TraceLocation traceLocation2 : list) {
                    if (traceLocation2 != null) {
                        if (traceLocation != null) {
                            double a2 = a(traceLocation.getLatitude(), traceLocation.getLongitude(), traceLocation2.getLatitude(), traceLocation2.getLongitude());
                            if (a2 <= 100.0d) {
                                d2 += a2;
                            }
                        }
                        traceLocation = traceLocation2;
                    }
                }
                Iterator<LatLng> it2 = hjVar.r.iterator();
                while (it2.hasNext()) {
                    LatLng next = it2.next();
                    if (next == null) {
                        it2.remove();
                    } else {
                        if (latLng == null) {
                            hjVar.p.add(next);
                            it2.remove();
                            it = it2;
                        } else {
                            it = it2;
                            try {
                                d += a(latLng.latitude, latLng.longitude, next.latitude, next.longitude);
                                if (d >= d2) {
                                    break;
                                }
                                hjVar = this;
                                hjVar.p.add(next);
                                it.remove();
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        }
                        it2 = it;
                        latLng = next;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private static double a(double d, double d2, double d3, double d4) {
        double d5 = d > d3 ? d - d3 : d3 - d;
        double d6 = d2 > d4 ? d2 - d4 : d4 - d2;
        return Math.sqrt((d5 * d5) + (d6 * d6));
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public final void stopTrace() {
        b();
        c();
    }

    private void b() {
        au auVar = this.i;
        if (auVar != null) {
            auVar.deactivate();
            this.i = null;
        }
    }

    private void c() {
        this.s.clear();
        this.t.clear();
        List<TraceLocation> list = this.j;
        if (list != null) {
            synchronized (list) {
                List<TraceLocation> list2 = this.j;
                if (list2 != null) {
                    list2.clear();
                }
                this.l = 0;
                this.k = 0;
                this.m = 0L;
                this.o = null;
            }
        }
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public final void destroy() {
        try {
            stopTrace();
            la laVar = this.d;
            if (laVar != null) {
                laVar.e();
                this.d = null;
            }
            la laVar2 = this.e;
            if (laVar2 != null) {
                laVar2.e();
                this.e = null;
            }
            this.j = null;
            this.h = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.b = null;
        this.c = null;
    }

    final class b implements TraceListener {
        private final List<TraceLocation> b;

        @Override // com.amap.api.trace.TraceListener
        public final void onTraceProcessing(int i, int i2, List<LatLng> list) {
        }

        public b(List<TraceLocation> list) {
            this.b = list;
        }

        @Override // com.amap.api.trace.TraceListener
        public final void onRequestFailed(int i, String str) {
            ArrayList arrayList = new ArrayList();
            if (hj.this.r != null) {
                arrayList.addAll(hj.this.r);
            }
            List<TraceLocation> list = this.b;
            if (list != null) {
                int size = list.size();
                if (this.b.size() > hj.this.g) {
                    for (int i2 = size - hj.this.g; i2 < size; i2++) {
                        TraceLocation traceLocation = this.b.get(i2);
                        if (traceLocation != null) {
                            arrayList.add(new LatLng(traceLocation.getLatitude(), traceLocation.getLongitude()));
                        }
                    }
                }
            }
            a(i, arrayList);
        }

        @Override // com.amap.api.trace.TraceListener
        public final void onFinished(int i, List<LatLng> list, int i2, int i3) {
            a(i, list);
        }

        private void a(int i, List<LatLng> list) {
            try {
                synchronized (hj.this.r) {
                    hj.this.r.clear();
                    hj.this.r.addAll(list);
                }
                hj.this.q.clear();
                if (i == 0) {
                    hj.this.q.addAll(hj.this.r);
                } else {
                    hj.this.q.addAll(hj.this.p);
                    hj.this.q.addAll(hj.this.r);
                }
                hj.this.h.onTraceStatus(hj.this.j, hj.this.q, LBSTraceClient.TRACE_SUCCESS);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    final class a extends lb {
        private int c;
        private int d;
        private List<TraceLocation> e;
        private TraceListener h;
        private List<TraceLocation> b = new ArrayList();
        private String g = dn.a();

        public a(int i, List<TraceLocation> list, int i2, TraceListener traceListener) {
            this.c = i2;
            this.d = i;
            this.e = list;
            this.h = traceListener;
        }

        @Override // com.amap.api.col.p0003sl.lb
        public final void runTask() {
            try {
                hj.this.n.a(this.h);
                int a2 = a();
                List<TraceLocation> list = this.e;
                if (list != null && list.size() >= 2) {
                    Iterator<TraceLocation> it = this.e.iterator();
                    while (it.hasNext()) {
                        TraceLocation copy = it.next().copy();
                        if (copy != null && copy.getLatitude() > 0.0d && copy.getLongitude() > 0.0d) {
                            this.b.add(copy);
                        }
                    }
                    int size = (this.b.size() - 2) / 500;
                    hk.a().a(this.g, this.d, size, a2);
                    int i = 0;
                    int i2 = 500;
                    while (i <= size) {
                        if (i == size) {
                            i2 = this.b.size();
                        }
                        int i3 = i2;
                        ArrayList arrayList = new ArrayList();
                        for (int i4 = 0; i4 < i3; i4++) {
                            TraceLocation remove = this.b.remove(0);
                            if (remove != null) {
                                int i5 = this.c;
                                if (i5 != 1) {
                                    if (i5 == 3) {
                                        hj.this.c.from(CoordinateConverter.CoordType.BAIDU);
                                    } else if (i5 == 2) {
                                        hj.this.c.from(CoordinateConverter.CoordType.GPS);
                                    }
                                    hj.this.c.coord(new LatLng(remove.getLatitude(), remove.getLongitude()));
                                    LatLng convert = hj.this.c.convert();
                                    if (convert != null) {
                                        remove.setLatitude(convert.latitude);
                                        remove.setLongitude(convert.longitude);
                                    }
                                }
                                arrayList.add(remove);
                            }
                        }
                        if (arrayList.size() >= 2 && arrayList.size() <= 500) {
                            final hi hiVar = new hi(hj.this.b, hj.this.n, arrayList, this.g, this.d, i);
                            hj.this.e.a(new lb() { // from class: com.amap.api.col.3sl.hj.a.1
                                @Override // com.amap.api.col.p0003sl.lb
                                public final void runTask() {
                                    hiVar.run();
                                }
                            });
                            i++;
                            try {
                                Thread.sleep(50L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        i2 = i3;
                    }
                    return;
                }
                hk.a();
                hk.a(hj.this.n, this.d, LBSTraceClient.MIN_GRASP_POINT_ERROR);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        private int a() {
            List<TraceLocation> list = this.e;
            int i = 0;
            if (list != null && list.size() != 0) {
                ArrayList arrayList = new ArrayList();
                for (TraceLocation traceLocation : this.e) {
                    if (traceLocation != null) {
                        if (traceLocation.getSpeed() < 0.01d) {
                            arrayList.add(traceLocation);
                        } else {
                            i += a(arrayList);
                            arrayList.clear();
                        }
                    }
                }
            }
            return i;
        }

        private static int a(List<TraceLocation> list) {
            int size = list.size();
            if (size <= 1) {
                return 0;
            }
            TraceLocation traceLocation = list.get(0);
            TraceLocation traceLocation2 = list.get(size - 1);
            if (traceLocation == null || traceLocation2 == null || traceLocation == null || traceLocation2 == null) {
                return 0;
            }
            return (int) ((traceLocation2.getTime() - traceLocation.getTime()) / 1000);
        }
    }

    static final class c extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private TraceListener f1126a;

        public c(Looper looper) {
            super(looper);
        }

        public final void a(TraceListener traceListener) {
            this.f1126a = traceListener;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Bundle data;
            try {
                if (this.f1126a == null || (data = message.getData()) == null) {
                    return;
                }
                int i = data.getInt("lineID");
                switch (message.what) {
                    case 100:
                        this.f1126a.onTraceProcessing(i, message.arg1, (List) message.obj);
                        break;
                    case 101:
                        this.f1126a.onFinished(i, (List) message.obj, message.arg1, message.arg2);
                        break;
                    case 102:
                        this.f1126a.onRequestFailed(i, (String) message.obj);
                        break;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
