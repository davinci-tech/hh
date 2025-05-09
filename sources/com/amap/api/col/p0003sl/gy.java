package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.col.p0003sl.fo;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.interfaces.IPoiSearch;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public final class gy implements IPoiSearch {
    private static HashMap<Integer, PoiResult> i;

    /* renamed from: a, reason: collision with root package name */
    private PoiSearch.SearchBound f1092a;
    private PoiSearch.Query b;
    private Context c;
    private PoiSearch.OnPoiSearchListener d;
    private String e = "zh-CN";
    private PoiSearch.Query f;
    private PoiSearch.SearchBound g;
    private int h;
    private Handler j;

    public gy(Context context, PoiSearch.Query query) throws AMapException {
        this.j = null;
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.c = context.getApplicationContext();
        setQuery(query);
        this.j = fo.a();
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public final void setOnPoiSearchListener(PoiSearch.OnPoiSearchListener onPoiSearchListener) {
        this.d = onPoiSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public final void setLanguage(String str) {
        if ("en".equals(str)) {
            this.e = "en";
        } else {
            this.e = "zh-CN";
        }
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public final String getLanguage() {
        return this.e;
    }

    private boolean a() {
        PoiSearch.Query query = this.b;
        if (query == null) {
            return false;
        }
        return (fd.a(query.getQueryString()) && fd.a(this.b.getCategory())) ? false : true;
    }

    private boolean b() {
        PoiSearch.SearchBound bound = getBound();
        return bound != null && bound.getShape().equals("Bound");
    }

    private boolean c() {
        PoiSearch.SearchBound bound = getBound();
        if (bound == null) {
            return true;
        }
        if (bound.getShape().equals("Bound")) {
            return bound.getCenter() != null;
        }
        if (bound.getShape().equals("Polygon")) {
            List<LatLonPoint> polyGonList = bound.getPolyGonList();
            if (polyGonList == null || polyGonList.size() == 0) {
                return false;
            }
            for (int i2 = 0; i2 < polyGonList.size(); i2++) {
                if (polyGonList.get(i2) == null) {
                    return false;
                }
            }
            return true;
        }
        if (!bound.getShape().equals("Rectangle")) {
            return true;
        }
        LatLonPoint lowerLeft = bound.getLowerLeft();
        LatLonPoint upperRight = bound.getUpperRight();
        return lowerLeft != null && upperRight != null && lowerLeft.getLatitude() < upperRight.getLatitude() && lowerLeft.getLongitude() < upperRight.getLongitude();
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public final PoiResult searchPOI() throws AMapException {
        try {
            fm.a(this.c);
            if (!b() && !a()) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!c()) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            PoiSearch.Query query = this.b;
            if (query == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if ((!query.queryEquals(this.f) && this.f1092a == null) || (!this.b.queryEquals(this.f) && !this.f1092a.equals(this.g))) {
                this.h = 0;
                this.f = this.b.m95clone();
                PoiSearch.SearchBound searchBound = this.f1092a;
                if (searchBound != null) {
                    this.g = searchBound.m96clone();
                }
                HashMap<Integer, PoiResult> hashMap = i;
                if (hashMap != null) {
                    hashMap.clear();
                }
            }
            PoiSearch.SearchBound searchBound2 = this.f1092a;
            PoiSearch.SearchBound m96clone = searchBound2 != null ? searchBound2.m96clone() : null;
            gc.a().a(this.b.getQueryString());
            this.b.setPageNum(gc.a().k(this.b.getPageNum()));
            this.b.setPageSize(gc.a().l(this.b.getPageSize()));
            if (this.h == 0) {
                PoiResult d = new fu(this.c, new fx(this.b.m95clone(), m96clone)).d();
                a(d);
                return d;
            }
            PoiResult a2 = a(this.b.getPageNum());
            if (a2 != null) {
                return a2;
            }
            PoiResult d2 = new fu(this.c, new fx(this.b.m95clone(), m96clone)).d();
            i.put(Integer.valueOf(this.b.getPageNum()), d2);
            return d2;
        } catch (AMapException e) {
            fd.a(e, "PoiSearch", "searchPOI");
            throw new AMapException(e.getErrorMessage());
        }
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public final void searchPOIAsyn() {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gy.1
                @Override // java.lang.Runnable
                public final void run() {
                    fo.h hVar;
                    Message obtainMessage = gy.this.j.obtainMessage();
                    obtainMessage.arg1 = 6;
                    obtainMessage.what = 600;
                    Bundle bundle = new Bundle();
                    PoiResult poiResult = null;
                    try {
                        try {
                            poiResult = gy.this.searchPOI();
                            bundle.putInt("errorCode", 1000);
                            hVar = new fo.h();
                        } catch (AMapException e) {
                            bundle.putInt("errorCode", e.getErrorCode());
                            hVar = new fo.h();
                        }
                        hVar.b = gy.this.d;
                        hVar.f1048a = poiResult;
                        obtainMessage.obj = hVar;
                        obtainMessage.setData(bundle);
                        gy.this.j.sendMessage(obtainMessage);
                    } catch (Throwable th) {
                        fo.h hVar2 = new fo.h();
                        hVar2.b = gy.this.d;
                        hVar2.f1048a = poiResult;
                        obtainMessage.obj = hVar2;
                        obtainMessage.setData(bundle);
                        gy.this.j.sendMessage(obtainMessage);
                        throw th;
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public final PoiItem searchPOIId(String str) throws AMapException {
        fm.a(this.c);
        PoiSearch.Query query = this.b;
        return new ft(this.c, str, query != null ? query.m95clone() : null).d();
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public final void searchPOIIdAsyn(final String str) {
        gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gy.2
            @Override // java.lang.Runnable
            public final void run() {
                fo.g gVar;
                Message obtainMessage = fo.a().obtainMessage();
                obtainMessage.arg1 = 6;
                obtainMessage.what = 602;
                Bundle bundle = new Bundle();
                PoiItem poiItem = null;
                try {
                    try {
                        poiItem = gy.this.searchPOIId(str);
                        bundle.putInt("errorCode", 1000);
                        gVar = new fo.g();
                    } catch (AMapException e) {
                        fd.a(e, "PoiSearch", "searchPOIIdAsyn");
                        bundle.putInt("errorCode", e.getErrorCode());
                        gVar = new fo.g();
                    }
                    gVar.b = gy.this.d;
                    gVar.f1047a = poiItem;
                    obtainMessage.obj = gVar;
                    obtainMessage.setData(bundle);
                    gy.this.j.sendMessage(obtainMessage);
                } catch (Throwable th) {
                    fo.g gVar2 = new fo.g();
                    gVar2.b = gy.this.d;
                    gVar2.f1047a = poiItem;
                    obtainMessage.obj = gVar2;
                    obtainMessage.setData(bundle);
                    gy.this.j.sendMessage(obtainMessage);
                    throw th;
                }
            }
        });
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public final void setQuery(PoiSearch.Query query) {
        this.b = query;
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public final void setBound(PoiSearch.SearchBound searchBound) {
        this.f1092a = searchBound;
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public final PoiSearch.Query getQuery() {
        return this.b;
    }

    @Override // com.amap.api.services.interfaces.IPoiSearch
    public final PoiSearch.SearchBound getBound() {
        return this.f1092a;
    }

    private void a(PoiResult poiResult) {
        int i2;
        i = new HashMap<>();
        PoiSearch.Query query = this.b;
        if (query == null || poiResult == null || (i2 = this.h) <= 0 || i2 <= query.getPageNum()) {
            return;
        }
        i.put(Integer.valueOf(this.b.getPageNum()), poiResult);
    }

    private PoiResult a(int i2) {
        if (!b(i2)) {
            throw new IllegalArgumentException("page out of range");
        }
        return i.get(Integer.valueOf(i2));
    }

    private boolean b(int i2) {
        return i2 <= this.h && i2 >= 0;
    }
}
