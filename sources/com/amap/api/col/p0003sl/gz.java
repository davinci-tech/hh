package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.col.p0003sl.fo;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IRoutePOISearch;
import com.amap.api.services.routepoisearch.RoutePOISearch;
import com.amap.api.services.routepoisearch.RoutePOISearchQuery;
import com.amap.api.services.routepoisearch.RoutePOISearchResult;

/* loaded from: classes8.dex */
public final class gz implements IRoutePOISearch {

    /* renamed from: a, reason: collision with root package name */
    private RoutePOISearchQuery f1095a;
    private Context b;
    private RoutePOISearch.OnRoutePOISearchListener c;
    private Handler d;

    public gz(Context context, RoutePOISearchQuery routePOISearchQuery) throws AMapException {
        this.d = null;
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.b = context;
        this.f1095a = routePOISearchQuery;
        this.d = fo.a();
    }

    @Override // com.amap.api.services.interfaces.IRoutePOISearch
    public final void setRoutePOISearchListener(RoutePOISearch.OnRoutePOISearchListener onRoutePOISearchListener) {
        this.c = onRoutePOISearchListener;
    }

    @Override // com.amap.api.services.interfaces.IRoutePOISearch
    public final void searchRoutePOIAsyn() {
        gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gz.1
            @Override // java.lang.Runnable
            public final void run() {
                fo.j jVar;
                Message obtainMessage = gz.this.d.obtainMessage();
                obtainMessage.arg1 = 14;
                Bundle bundle = new Bundle();
                RoutePOISearchResult routePOISearchResult = null;
                try {
                    try {
                        routePOISearchResult = gz.this.searchRoutePOI();
                        bundle.putInt("errorCode", 1000);
                        jVar = new fo.j();
                    } catch (AMapException e) {
                        bundle.putInt("errorCode", e.getErrorCode());
                        jVar = new fo.j();
                    }
                    jVar.b = gz.this.c;
                    jVar.f1050a = routePOISearchResult;
                    obtainMessage.obj = jVar;
                    obtainMessage.setData(bundle);
                    gz.this.d.sendMessage(obtainMessage);
                } catch (Throwable th) {
                    fo.j jVar2 = new fo.j();
                    jVar2.b = gz.this.c;
                    jVar2.f1050a = routePOISearchResult;
                    obtainMessage.obj = jVar2;
                    obtainMessage.setData(bundle);
                    gz.this.d.sendMessage(obtainMessage);
                    throw th;
                }
            }
        });
    }

    @Override // com.amap.api.services.interfaces.IRoutePOISearch
    public final void setQuery(RoutePOISearchQuery routePOISearchQuery) {
        this.f1095a = routePOISearchQuery;
    }

    @Override // com.amap.api.services.interfaces.IRoutePOISearch
    public final RoutePOISearchQuery getQuery() {
        return this.f1095a;
    }

    @Override // com.amap.api.services.interfaces.IRoutePOISearch
    public final RoutePOISearchResult searchRoutePOI() throws AMapException {
        try {
            fm.a(this.b);
            if (!a()) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            return new gf(this.b, this.f1095a.m107clone()).d();
        } catch (AMapException e) {
            fd.a(e, "RoutePOISearchCore", "searchRoutePOI");
            throw e;
        }
    }

    private boolean a() {
        RoutePOISearchQuery routePOISearchQuery = this.f1095a;
        if (routePOISearchQuery == null || routePOISearchQuery.getSearchType() == null) {
            return false;
        }
        return (this.f1095a.getFrom() == null && this.f1095a.getTo() == null && this.f1095a.getPolylines() == null) ? false : true;
    }
}
