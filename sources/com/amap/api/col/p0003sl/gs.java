package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.col.p0003sl.fo;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.cloud.CloudItemDetail;
import com.amap.api.services.cloud.CloudResult;
import com.amap.api.services.cloud.CloudSearch;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.ICloudSearch;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public final class gs implements ICloudSearch {

    /* renamed from: a, reason: collision with root package name */
    private Context f1075a;
    private CloudSearch.OnCloudSearchListener b;
    private CloudSearch.Query c;
    private int d;
    private HashMap<Integer, CloudResult> e;
    private Handler f;

    public gs(Context context) throws AMapException {
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.f1075a = context.getApplicationContext();
        this.f = fo.a();
    }

    @Override // com.amap.api.services.interfaces.ICloudSearch
    public final void setOnCloudSearchListener(CloudSearch.OnCloudSearchListener onCloudSearchListener) {
        this.b = onCloudSearchListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13, types: [com.amap.api.services.cloud.CloudResult] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    public CloudResult a(CloudSearch.Query query) throws AMapException {
        CloudResult cloudResult;
        try {
            if (!b(query)) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!query.queryEquals(this.c)) {
                this.d = 0;
                this.c = query.m92clone();
                HashMap<Integer, CloudResult> hashMap = this.e;
                if (hashMap != null) {
                    hashMap.clear();
                }
            }
            cloudResult = this.d;
            try {
                if (cloudResult == 0) {
                    CloudResult d = new fb(this.f1075a, query).d();
                    a(d, query);
                    cloudResult = d;
                } else {
                    CloudResult a2 = a(query.getPageNum());
                    if (a2 != null) {
                        return a2;
                    }
                    CloudResult d2 = new fb(this.f1075a, query).d();
                    this.e.put(Integer.valueOf(query.getPageNum()), d2);
                    cloudResult = d2;
                }
                return cloudResult;
            } catch (Throwable th) {
                th = th;
                fd.a(th, "CloudSearch", "searchCloud");
                if (th instanceof AMapException) {
                    throw th;
                }
                th.printStackTrace();
                return cloudResult;
            }
        } catch (Throwable th2) {
            th = th2;
            cloudResult = 0;
        }
    }

    @Override // com.amap.api.services.interfaces.ICloudSearch
    public final void searchCloudAsyn(final CloudSearch.Query query) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gs.1
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    try {
                        try {
                            obtainMessage.arg1 = 12;
                            obtainMessage.what = 700;
                            fo.d dVar = new fo.d();
                            dVar.b = gs.this.b;
                            obtainMessage.obj = dVar;
                            dVar.f1044a = gs.this.a(query);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e) {
                            obtainMessage.arg2 = e.getErrorCode();
                        }
                    } finally {
                        gs.this.f.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CloudItemDetail a(String str, String str2) throws AMapException {
        if (str == null || str.trim().equals("")) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        if (str2 == null || str2.trim().equals("")) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        try {
            return new fa(this.f1075a, new fw(str, str2)).d();
        } catch (Throwable th) {
            fd.a(th, "CloudSearch", "searchCloudDetail");
            if (th instanceof AMapException) {
                throw th;
            }
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.amap.api.services.interfaces.ICloudSearch
    public final void searchCloudDetailAsyn(final String str, final String str2) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gs.2
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    try {
                        try {
                            obtainMessage.arg1 = 12;
                            obtainMessage.what = 701;
                            fo.c cVar = new fo.c();
                            cVar.b = gs.this.b;
                            obtainMessage.obj = cVar;
                            cVar.f1043a = gs.this.a(str, str2);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e) {
                            obtainMessage.arg2 = e.getErrorCode();
                        }
                    } finally {
                        gs.this.f.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(CloudResult cloudResult, CloudSearch.Query query) {
        HashMap<Integer, CloudResult> hashMap = new HashMap<>();
        this.e = hashMap;
        if (this.d > 0) {
            hashMap.put(Integer.valueOf(query.getPageNum()), cloudResult);
        }
    }

    private CloudResult a(int i) {
        if (!b(i)) {
            throw new IllegalArgumentException("page out of range");
        }
        return this.e.get(Integer.valueOf(i));
    }

    private boolean b(int i) {
        return i <= this.d && i > 0;
    }

    private static boolean b(CloudSearch.Query query) {
        if (query == null || fd.a(query.getTableID()) || query.getBound() == null) {
            return false;
        }
        if (query.getBound() != null && query.getBound().getShape().equals("Bound") && query.getBound().getCenter() == null) {
            return false;
        }
        if (query.getBound() != null && query.getBound().getShape().equals("Rectangle")) {
            LatLonPoint lowerLeft = query.getBound().getLowerLeft();
            LatLonPoint upperRight = query.getBound().getUpperRight();
            if (lowerLeft == null || upperRight == null || lowerLeft.getLatitude() >= upperRight.getLatitude() || lowerLeft.getLongitude() >= upperRight.getLongitude()) {
                return false;
            }
        }
        if (query.getBound() == null || !query.getBound().getShape().equals("Polygon")) {
            return true;
        }
        List<LatLonPoint> polyGonList = query.getBound().getPolyGonList();
        for (int i = 0; i < polyGonList.size(); i++) {
            if (polyGonList.get(i) == null) {
                return false;
            }
        }
        return true;
    }
}
