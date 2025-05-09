package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.col.p0003sl.fo;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.busline.BusStationQuery;
import com.amap.api.services.busline.BusStationResult;
import com.amap.api.services.busline.BusStationSearch;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IBusStationSearch;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class gr implements IBusStationSearch {

    /* renamed from: a, reason: collision with root package name */
    private Context f1073a;
    private BusStationSearch.OnBusStationSearchListener b;
    private BusStationQuery c;
    private BusStationQuery d;
    private ArrayList<BusStationResult> e = new ArrayList<>();
    private int f;
    private Handler g;

    public gr(Context context, BusStationQuery busStationQuery) throws AMapException {
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.f1073a = context.getApplicationContext();
        this.c = busStationQuery;
        this.g = fo.a();
    }

    @Override // com.amap.api.services.interfaces.IBusStationSearch
    public final BusStationResult searchBusStation() throws AMapException {
        try {
            fm.a(this.f1073a);
            if (!a()) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!this.c.weakEquals(this.d)) {
                this.d = this.c.m91clone();
                this.f = 0;
                ArrayList<BusStationResult> arrayList = this.e;
                if (arrayList != null) {
                    arrayList.clear();
                }
            }
            if (this.f == 0) {
                BusStationResult busStationResult = (BusStationResult) new ey(this.f1073a, this.c).d();
                this.f = busStationResult.getPageCount();
                a(busStationResult);
                return busStationResult;
            }
            BusStationResult b = b(this.c.getPageNumber());
            if (b != null) {
                return b;
            }
            BusStationResult busStationResult2 = (BusStationResult) new ey(this.f1073a, this.c).d();
            this.e.set(this.c.getPageNumber(), busStationResult2);
            return busStationResult2;
        } catch (AMapException e) {
            fd.a(e, "BusStationSearch", "searchBusStation");
            throw new AMapException(e.getErrorMessage());
        } catch (Throwable th) {
            fd.a(th, "BusStationSearch", "searchBusStation");
            return null;
        }
    }

    private void a(BusStationResult busStationResult) {
        int i;
        this.e = new ArrayList<>();
        int i2 = 0;
        while (true) {
            i = this.f;
            if (i2 > i) {
                break;
            }
            this.e.add(null);
            i2++;
        }
        if (i > 0) {
            this.e.set(this.c.getPageNumber(), busStationResult);
        }
    }

    private boolean a(int i) {
        return i <= this.f && i >= 0;
    }

    private BusStationResult b(int i) {
        if (!a(i)) {
            throw new IllegalArgumentException("page out of range");
        }
        return this.e.get(i);
    }

    @Override // com.amap.api.services.interfaces.IBusStationSearch
    public final void setOnBusStationSearchListener(BusStationSearch.OnBusStationSearchListener onBusStationSearchListener) {
        this.b = onBusStationSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IBusStationSearch
    public final void searchBusStationAsyn() {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gr.1
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    try {
                        try {
                            obtainMessage.arg1 = 7;
                            fo.b bVar = new fo.b();
                            bVar.b = gr.this.b;
                            obtainMessage.obj = bVar;
                            BusStationResult searchBusStation = gr.this.searchBusStation();
                            obtainMessage.what = 1000;
                            bVar.f1042a = searchBusStation;
                        } catch (AMapException e) {
                            obtainMessage.what = e.getErrorCode();
                        }
                    } finally {
                        gr.this.g.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IBusStationSearch
    public final void setQuery(BusStationQuery busStationQuery) {
        if (busStationQuery.weakEquals(this.c)) {
            return;
        }
        this.c = busStationQuery;
    }

    @Override // com.amap.api.services.interfaces.IBusStationSearch
    public final BusStationQuery getQuery() {
        return this.c;
    }

    private boolean a() {
        BusStationQuery busStationQuery = this.c;
        return (busStationQuery == null || fd.a(busStationQuery.getQueryString())) ? false : true;
    }
}
