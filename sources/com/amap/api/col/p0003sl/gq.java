package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.col.p0003sl.fo;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.busline.BusLineQuery;
import com.amap.api.services.busline.BusLineResult;
import com.amap.api.services.busline.BusLineSearch;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IBusLineSearch;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class gq implements IBusLineSearch {

    /* renamed from: a, reason: collision with root package name */
    private Context f1071a;
    private BusLineSearch.OnBusLineSearchListener b;
    private BusLineQuery c;
    private BusLineQuery d;
    private int e;
    private ArrayList<BusLineResult> f = new ArrayList<>();
    private Handler g;

    public gq(Context context, BusLineQuery busLineQuery) throws AMapException {
        this.g = null;
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.f1071a = context.getApplicationContext();
        this.c = busLineQuery;
        if (busLineQuery != null) {
            this.d = busLineQuery.m90clone();
        }
        this.g = fo.a();
    }

    @Override // com.amap.api.services.interfaces.IBusLineSearch
    public final BusLineResult searchBusLine() throws AMapException {
        try {
            fm.a(this.f1071a);
            if (this.d == null || !a()) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!this.c.weakEquals(this.d)) {
                this.d = this.c.m90clone();
                this.e = 0;
                ArrayList<BusLineResult> arrayList = this.f;
                if (arrayList != null) {
                    arrayList.clear();
                }
            }
            if (this.e == 0) {
                BusLineResult busLineResult = (BusLineResult) new ey(this.f1071a, this.c.m90clone()).d();
                a(busLineResult);
                return busLineResult;
            }
            BusLineResult b = b(this.c.getPageNumber());
            if (b != null) {
                return b;
            }
            BusLineResult busLineResult2 = (BusLineResult) new ey(this.f1071a, this.c).d();
            this.f.set(this.c.getPageNumber(), busLineResult2);
            return busLineResult2;
        } catch (AMapException e) {
            fd.a(e, "BusLineSearch", "searchBusLine");
            throw new AMapException(e.getErrorMessage());
        }
    }

    private void a(BusLineResult busLineResult) {
        int i;
        this.f = new ArrayList<>();
        int i2 = 0;
        while (true) {
            i = this.e;
            if (i2 >= i) {
                break;
            }
            this.f.add(null);
            i2++;
        }
        if (i < 0 || !a(this.c.getPageNumber())) {
            return;
        }
        this.f.set(this.c.getPageNumber(), busLineResult);
    }

    private boolean a(int i) {
        return i < this.e && i >= 0;
    }

    private BusLineResult b(int i) {
        if (!a(i)) {
            throw new IllegalArgumentException("page out of range");
        }
        return this.f.get(i);
    }

    @Override // com.amap.api.services.interfaces.IBusLineSearch
    public final void setOnBusLineSearchListener(BusLineSearch.OnBusLineSearchListener onBusLineSearchListener) {
        this.b = onBusLineSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IBusLineSearch
    public final void searchBusLineAsyn() {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gq.1
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    try {
                        try {
                            obtainMessage.arg1 = 3;
                            obtainMessage.what = 1000;
                            fo.a aVar = new fo.a();
                            obtainMessage.obj = aVar;
                            aVar.b = gq.this.b;
                            aVar.f1041a = gq.this.searchBusLine();
                        } catch (AMapException e) {
                            obtainMessage.what = e.getErrorCode();
                        }
                    } finally {
                        gq.this.g.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IBusLineSearch
    public final void setQuery(BusLineQuery busLineQuery) {
        if (this.c.weakEquals(busLineQuery)) {
            return;
        }
        this.c = busLineQuery;
        this.d = busLineQuery.m90clone();
    }

    @Override // com.amap.api.services.interfaces.IBusLineSearch
    public final BusLineQuery getQuery() {
        return this.c;
    }

    private boolean a() {
        BusLineQuery busLineQuery = this.c;
        return (busLineQuery == null || fd.a(busLineQuery.getQueryString())) ? false : true;
    }
}
