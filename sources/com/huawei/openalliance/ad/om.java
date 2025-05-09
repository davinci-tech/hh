package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.inter.AdParseConfig;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import com.huawei.openalliance.ad.oy;
import com.huawei.openalliance.ad.pe;
import com.huawei.openalliance.ad.po;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class om implements qm {

    /* renamed from: a, reason: collision with root package name */
    private a f7373a;
    private Context b;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;

    public interface a<T extends IAd> {
        void a(int i, int i2);

        void a(int i, Map<String, List<T>> map);

        void a(Map<String, List<IPlacementAd>> map, Map<String, List<IPlacementAd>> map2);
    }

    @Override // com.huawei.openalliance.ad.qm
    public void c(boolean z) {
        this.e = z;
    }

    @Override // com.huawei.openalliance.ad.qm
    public void b(boolean z) {
        this.d = z;
    }

    @Override // com.huawei.openalliance.ad.qm
    public void a(boolean z) {
        this.c = z;
    }

    @Override // com.huawei.openalliance.ad.qm
    public void a(Map<Integer, AdContentRsp> map, long j, AdParseConfig adParseConfig) {
        ho.b("ApiProcessor", "api parser");
        if (map.isEmpty()) {
            return;
        }
        for (Map.Entry<Integer, AdContentRsp> entry : map.entrySet()) {
            int intValue = entry.getKey().intValue();
            ho.a("ApiProcessor", "adType: %d", Integer.valueOf(intValue));
            a(intValue, entry.getValue(), j, adParseConfig);
        }
    }

    private void c(final int i, AdContentRsp adContentRsp) {
        ho.a("ApiProcessor", "parseRewardAds");
        new po(this.b, new po.a() { // from class: com.huawei.openalliance.ad.om.3
            @Override // com.huawei.openalliance.ad.po.a
            public void a(Map<String, List<IRewardAd>> map) {
                if (map == null || map.isEmpty()) {
                    om.this.f7373a.a(i, 900);
                } else {
                    om.this.f7373a.a(i, map);
                }
            }

            @Override // com.huawei.openalliance.ad.po.a
            public void a(int i2) {
                om.this.f7373a.a(i, i2);
            }
        }).a(adContentRsp);
    }

    private void b(final int i, AdContentRsp adContentRsp, long j, AdParseConfig adParseConfig) {
        ho.a("ApiProcessor", "parseNativeAds");
        pe peVar = new pe(this.b, new pe.a() { // from class: com.huawei.openalliance.ad.om.4
            @Override // com.huawei.openalliance.ad.pe.a
            public void a(Map<String, List<INativeAd>> map) {
                om.this.f7373a.a(i, map);
            }

            @Override // com.huawei.openalliance.ad.pe.a
            public void a(int i2) {
                om.this.f7373a.a(i, i2);
            }
        }, i, adParseConfig.a());
        peVar.a(this.c);
        peVar.b(this.d);
        peVar.c(this.e);
        peVar.a(i);
        peVar.a(adContentRsp, j, adParseConfig);
    }

    private void b(final int i, AdContentRsp adContentRsp) {
        ho.a("ApiProcessor", "parseInterstitialAds");
        new oy(this.b, new oy.a() { // from class: com.huawei.openalliance.ad.om.2
            @Override // com.huawei.openalliance.ad.oy.a
            public void a(Map<String, List<com.huawei.openalliance.ad.inter.data.b>> map) {
                om.this.f7373a.a(i, map);
            }

            @Override // com.huawei.openalliance.ad.oy.a
            public void a(int i2) {
                om.this.f7373a.a(i, i2);
            }
        }).a(adContentRsp);
    }

    private void a(int i, AdContentRsp adContentRsp, long j, AdParseConfig adParseConfig) {
        if (i != 3) {
            if (i == 7) {
                c(i, adContentRsp);
                return;
            }
            if (i != 9) {
                if (i == 60) {
                    a(i, adContentRsp);
                    return;
                } else if (i == 12) {
                    b(i, adContentRsp);
                    return;
                } else if (i != 13) {
                    return;
                }
            }
        }
        b(i, adContentRsp, j, adParseConfig);
    }

    private void a(final int i, AdContentRsp adContentRsp) {
        ho.a("ApiProcessor", "parsePlacementAds");
        op.a(this.b, new qx() { // from class: com.huawei.openalliance.ad.om.1
            @Override // com.huawei.openalliance.ad.qx
            public void a(Map<String, List<IPlacementAd>> map, Map<String, List<IPlacementAd>> map2) {
                om.this.f7373a.a(map, map2);
            }

            @Override // com.huawei.openalliance.ad.qx
            public void a(int i2) {
                om.this.f7373a.a(i, i2);
            }
        }, false).a(adContentRsp);
    }

    public om(Context context, a aVar) {
        this.b = context;
        this.f7373a = aVar;
    }
}
