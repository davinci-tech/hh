package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.constant.ApiNames;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.listeners.PlacementAdListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class go implements gl {
    private boolean f;
    private Context g;
    private PlacementAdListener h;
    private String i;
    private long j;
    private long k;

    /* renamed from: a, reason: collision with root package name */
    private boolean f6884a = false;
    private boolean b = false;
    private Map<String, List<IPlacementAd>> d = new HashMap(4);
    private Map<String, List<IPlacementAd>> e = new HashMap(4);
    private dt c = dt.h();

    public void b(int i) {
        ho.b("PlacementAd", "startCache:" + i);
        dt dtVar = this.c;
        if (dtVar != null) {
            dtVar.a(Integer.valueOf(i));
            this.c.j();
        }
        if (this.d.isEmpty() && this.e.isEmpty()) {
            this.b = true;
            return;
        }
        com.huawei.openalliance.ad.utils.t.a(this.d, i, false, 1);
        if (com.huawei.openalliance.ad.utils.ce.a(this.g).c()) {
            com.huawei.openalliance.ad.utils.t.a(this.e, i, true, 1);
        } else {
            ho.c("PlacementAd", "in background, do not preload contents");
        }
    }

    public void b() {
        if (this.f || this.b) {
            b(dt.h().g());
            this.b = false;
        }
    }

    public void a(final Map<String, List<IPlacementAd>> map, final Map<String, List<IPlacementAd>> map2) {
        this.d.clear();
        if (map != null) {
            this.d.putAll(map);
        }
        this.e.clear();
        if (map2 != null) {
            this.e.putAll(map2);
        }
        if (this.h != null) {
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.go.1
                @Override // java.lang.Runnable
                public void run() {
                    int i;
                    go goVar;
                    Map map3;
                    go.this.k = System.currentTimeMillis();
                    if (go.this.f6884a) {
                        goVar = go.this;
                        map3 = map2;
                    } else {
                        Map map4 = map;
                        if (map4 == null || map4.isEmpty()) {
                            go.this.a(800);
                            i = 800;
                            com.huawei.openalliance.ad.analysis.d.a(go.this.g, ApiNames.API_LOAD_AD, go.this.i, 1, 60, go.this.j, go.this.k, i);
                        }
                        goVar = go.this;
                        map3 = map;
                    }
                    goVar.a(map3);
                    i = 200;
                    com.huawei.openalliance.ad.analysis.d.a(go.this.g, ApiNames.API_LOAD_AD, go.this.i, 1, 60, go.this.j, go.this.k, i);
                }
            });
        }
    }

    @Override // com.huawei.openalliance.ad.gl
    public void a(Map map) {
        StringBuilder sb = new StringBuilder("onAdsLoaded, size:");
        sb.append(map != null ? Integer.valueOf(map.size()) : null);
        sb.append(", listener:");
        sb.append(this.h);
        ho.b("PlacementAd", sb.toString());
        PlacementAdListener placementAdListener = this.h;
        if (placementAdListener != null) {
            placementAdListener.onAdsLoaded(map);
        }
    }

    public void a(String str) {
        this.i = str;
    }

    public void a(long j) {
        this.j = j;
    }

    @Override // com.huawei.openalliance.ad.gl
    public void a(int i) {
        ho.b("PlacementAd", "onAdFailed, errorCode:" + i);
        PlacementAdListener placementAdListener = this.h;
        if (placementAdListener != null) {
            placementAdListener.onAdFailed(i);
        }
    }

    public void a() {
        dt dtVar = this.c;
        if (dtVar != null) {
            dtVar.i();
        }
    }

    public go(Context context, PlacementAdListener placementAdListener, boolean z) {
        this.g = context;
        this.h = placementAdListener;
        this.f = z;
        this.d.clear();
        this.e.clear();
    }
}
