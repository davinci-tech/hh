package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.interfaces.IDistrictSearch;
import java.util.HashMap;

/* loaded from: classes8.dex */
public final class gu implements IDistrictSearch {
    private static HashMap<Integer, DistrictResult> f;

    /* renamed from: a, reason: collision with root package name */
    private Context f1080a;
    private DistrictSearchQuery b;
    private DistrictSearch.OnDistrictSearchListener c;
    private DistrictSearchQuery d;
    private int e;
    private Handler g;

    public gu(Context context) throws AMapException {
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.f1080a = context.getApplicationContext();
        this.g = fo.a();
    }

    private void a(DistrictResult districtResult) {
        int i;
        f = new HashMap<>();
        DistrictSearchQuery districtSearchQuery = this.b;
        if (districtSearchQuery == null || districtResult == null || (i = this.e) <= 0 || i <= districtSearchQuery.getPageNum()) {
            return;
        }
        f.put(Integer.valueOf(this.b.getPageNum()), districtResult);
    }

    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public final DistrictSearchQuery getQuery() {
        return this.b;
    }

    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public final void setQuery(DistrictSearchQuery districtSearchQuery) {
        this.b = districtSearchQuery;
    }

    private boolean a() {
        return this.b != null;
    }

    private DistrictResult a(int i) throws AMapException {
        if (!b(i)) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        return f.get(Integer.valueOf(i));
    }

    private boolean b(int i) {
        return i < this.e && i >= 0;
    }

    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public final DistrictResult searchDistrict() throws AMapException {
        DistrictResult a2;
        int i;
        try {
            DistrictResult districtResult = new DistrictResult();
            fm.a(this.f1080a);
            if (!a()) {
                this.b = new DistrictSearchQuery();
            }
            districtResult.setQuery(this.b.m94clone());
            if (!this.b.weakEquals(this.d)) {
                this.e = 0;
                this.d = this.b.m94clone();
                HashMap<Integer, DistrictResult> hashMap = f;
                if (hashMap != null) {
                    hashMap.clear();
                }
            }
            if (this.e == 0) {
                a2 = new ff(this.f1080a, this.b.m94clone()).d();
                if (a2 == null) {
                    return a2;
                }
                this.e = a2.getPageCount();
                a(a2);
            } else {
                a2 = a(this.b.getPageNum());
                if (a2 == null) {
                    a2 = new ff(this.f1080a, this.b.m94clone()).d();
                    DistrictSearchQuery districtSearchQuery = this.b;
                    if (districtSearchQuery != null && a2 != null && (i = this.e) > 0 && i > districtSearchQuery.getPageNum()) {
                        f.put(Integer.valueOf(this.b.getPageNum()), a2);
                    }
                }
            }
            return a2;
        } catch (AMapException e) {
            fd.a(e, "DistrictSearch", "searchDistrict");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public final void searchDistrictAsyn() {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gu.1
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    DistrictResult districtResult = new DistrictResult();
                    districtResult.setQuery(gu.this.b);
                    try {
                        try {
                            districtResult = gu.this.searchDistrict();
                            if (districtResult != null) {
                                districtResult.setAMapException(new AMapException());
                            }
                        } finally {
                            obtainMessage.arg1 = 4;
                            obtainMessage.obj = gu.this.c;
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("result", districtResult);
                            obtainMessage.setData(bundle);
                            if (gu.this.g != null) {
                                gu.this.g.sendMessage(obtainMessage);
                            }
                        }
                    } catch (AMapException e) {
                        districtResult.setAMapException(e);
                        obtainMessage.arg1 = 4;
                        obtainMessage.obj = gu.this.c;
                        Bundle bundle2 = new Bundle();
                        bundle2.putParcelable("result", districtResult);
                        obtainMessage.setData(bundle2);
                        if (gu.this.g != null) {
                            gu.this.g.sendMessage(obtainMessage);
                        }
                    } catch (Throwable th) {
                        fd.a(th, "DistrictSearch", "searchDistrictAnsyThrowable");
                        obtainMessage.arg1 = 4;
                        obtainMessage.obj = gu.this.c;
                        Bundle bundle3 = new Bundle();
                        bundle3.putParcelable("result", districtResult);
                        obtainMessage.setData(bundle3);
                        if (gu.this.g != null) {
                            gu.this.g.sendMessage(obtainMessage);
                        }
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public final void searchDistrictAnsy() {
        searchDistrictAsyn();
    }

    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public final void setOnDistrictSearchListener(DistrictSearch.OnDistrictSearchListener onDistrictSearchListener) {
        this.c = onDistrictSearchListener;
    }
}
