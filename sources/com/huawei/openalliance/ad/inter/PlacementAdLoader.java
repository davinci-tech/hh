package com.huawei.openalliance.ad.inter;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.beans.metadata.DelayInfo;
import com.huawei.openalliance.ad.beans.metadata.Video;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dt;
import com.huawei.openalliance.ad.ek;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.listeners.PlacementAdListener;
import com.huawei.openalliance.ad.ol;
import com.huawei.openalliance.ad.op;
import com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.pg;
import com.huawei.openalliance.ad.qx;
import com.huawei.openalliance.ad.sc;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bi;
import com.huawei.openalliance.ad.utils.ce;
import com.huawei.openalliance.ad.utils.cp;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.openalliance.ad.utils.t;
import com.huawei.openalliance.ad.utils.x;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class PlacementAdLoader implements IPlacementAdLoader, qx {

    /* renamed from: a, reason: collision with root package name */
    private b f7012a;
    private Context b;
    private final String[] c;
    private PlacementAdListener d;
    private op e;
    private String f;
    private Boolean g;
    private int h;
    private String i;
    private int j;
    private boolean k;
    private boolean l;
    private dt m;
    private Map<String, List<IPlacementAd>> n;
    private Map<String, List<IPlacementAd>> o;
    private boolean p;
    private boolean q;
    private RequestOptions r;
    private Location s;
    private DelayInfo t;
    private String u;
    private int v;
    private int w;
    private Integer x;
    private String y;

    enum b {
        IDLE,
        LOADING
    }

    @Override // com.huawei.openalliance.ad.inter.IPlacementAdLoader
    public void stopCache() {
        dt dtVar = this.m;
        if (dtVar != null) {
            dtVar.i();
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IPlacementAdLoader
    public void startCache(int i) {
        ho.b("PlacementAdLoader", "startCache:" + i);
        dt dtVar = this.m;
        if (dtVar != null) {
            dtVar.a(Integer.valueOf(i));
            this.m.j();
        }
        if (this.n.isEmpty() && this.o.isEmpty()) {
            this.q = true;
            return;
        }
        t.a(this.n, i, false, 0);
        if (ce.a(this.b).c()) {
            t.a(this.o, i, true, 0);
        } else {
            ho.c("PlacementAdLoader", "in background, do not preload contents");
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IPlacementAdLoader
    public void preLoadAds() {
        a(300, 1);
    }

    @Override // com.huawei.openalliance.ad.inter.IPlacementAdLoader
    public void loadAds(PlacementAdListener placementAdListener, int i, int i2) {
        this.d = placementAdListener;
        a(false, i, i2);
    }

    @Override // com.huawei.openalliance.ad.inter.IPlacementAdLoader
    public void loadAds(PlacementAdListener placementAdListener, int i) {
        loadAds(placementAdListener, i, 0);
    }

    @Override // com.huawei.openalliance.ad.inter.IPlacementAdLoader
    public void loadAds(PlacementAdListener placementAdListener) {
        loadAds(placementAdListener, 300, 1);
    }

    @Override // com.huawei.openalliance.ad.qx
    public void a(final Map<String, List<IPlacementAd>> map, final Map<String, List<IPlacementAd>> map2) {
        StringBuilder sb = new StringBuilder("onAdsLoaded, size:");
        sb.append(map == null ? 0 : map.size());
        ho.b("PlacementAdLoader", sb.toString());
        this.t.u().i(System.currentTimeMillis());
        a(map);
        this.n.clear();
        if (map != null) {
            this.n.putAll(map);
        }
        this.o.clear();
        if (map2 != null) {
            this.o.putAll(map2);
        }
        if (this.d != null) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.PlacementAdLoader.2
                @Override // java.lang.Runnable
                public void run() {
                    PlacementAdListener placementAdListener = PlacementAdLoader.this.d;
                    PlacementAdLoader.this.t.b(System.currentTimeMillis());
                    int i = 200;
                    Map<String, List<IPlacementAd>> map3 = null;
                    if (placementAdListener != null) {
                        if (PlacementAdLoader.this.p) {
                            map3 = map2;
                        } else {
                            Map map4 = map;
                            if (map4 == null || map4.isEmpty()) {
                                i = 800;
                                placementAdListener.onAdFailed(800);
                            } else {
                                map3 = map;
                            }
                        }
                        placementAdListener.onAdsLoaded(map3);
                    }
                    com.huawei.openalliance.ad.analysis.d.a(PlacementAdLoader.this.b, i, PlacementAdLoader.this.u, 60, map3, PlacementAdLoader.this.t);
                }
            });
        }
        if (this.l || this.q) {
            startCache(dt.h().g());
            this.q = false;
        }
    }

    @Override // com.huawei.openalliance.ad.qx
    public void a(final int i) {
        ho.b("PlacementAdLoader", "onAdFailed, errorCode:" + i);
        this.t.u().i(System.currentTimeMillis());
        if (this.d != null) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.PlacementAdLoader.3
                @Override // java.lang.Runnable
                public void run() {
                    PlacementAdListener placementAdListener = PlacementAdLoader.this.d;
                    PlacementAdLoader.this.t.b(System.currentTimeMillis());
                    if (placementAdListener != null) {
                        placementAdListener.onAdFailed(i);
                    }
                    com.huawei.openalliance.ad.analysis.d.a(PlacementAdLoader.this.b, i, PlacementAdLoader.this.u, 60, (Map) null, PlacementAdLoader.this.t);
                }
            });
        }
    }

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Context f7017a;
        private String[] b;
        private int c = 4;
        private String d;
        private String e;
        private int f;
        private boolean g;
        private boolean h;
        private RequestOptions i;
        private Location j;
        private Integer k;

        public Builder setTest(boolean z) {
            this.g = z;
            return this;
        }

        public Builder setRequestOptions(RequestOptions requestOptions) {
            this.i = requestOptions;
            return this;
        }

        public Builder setMaxLength(int i) {
            this.f = i;
            return this;
        }

        public Builder setLocation(Location location) {
            this.j = location;
            return this;
        }

        public Builder setExtraInfo(String str) {
            this.d = str;
            return this;
        }

        public Builder setDeviceType(int i) {
            this.c = i;
            return this;
        }

        public Builder setContentBundle(String str) {
            this.e = str;
            return this;
        }

        public Builder setAutoCache(boolean z) {
            this.h = z;
            return this;
        }

        public Builder setAdIds(String[] strArr) {
            if (strArr != null) {
                this.b = (String[]) Arrays.copyOf(strArr, strArr.length);
                a(strArr);
            } else {
                this.b = null;
            }
            return this;
        }

        public Context g() {
            return this.f7017a;
        }

        public boolean f() {
            return this.h;
        }

        public boolean e() {
            return this.g;
        }

        public int d() {
            return this.f;
        }

        public String c() {
            return this.d;
        }

        public PlacementAdLoader build() {
            return new PlacementAdLoader(this);
        }

        public int b() {
            return this.c;
        }

        public String[] a() {
            String[] strArr = this.b;
            return strArr != null ? (String[]) Arrays.copyOf(strArr, strArr.length) : new String[0];
        }

        public Builder a(Integer num) {
            this.k = num;
            return this;
        }

        private void a(final String[] strArr) {
            if (bg.a(strArr)) {
                return;
            }
            m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.PlacementAdLoader.Builder.1
                @Override // java.lang.Runnable
                public void run() {
                    fh.b(Builder.this.f7017a).s(strArr[0]);
                }
            });
        }

        public Builder(Context context) {
            this.f7017a = context.getApplicationContext();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        this.f = str;
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            new JSONObject(str);
            return true;
        } catch (JSONException unused) {
            ho.c("PlacementAdLoader", "extra info is not json string");
            return false;
        }
    }

    private void a(boolean z, int i, int i2) {
        this.t.a(System.currentTimeMillis());
        ho.b("PlacementAdLoader", "loadAds");
        this.p = z;
        this.v = i;
        this.w = i2;
        this.n.clear();
        this.o.clear();
        if (!ao.b(this.b)) {
            ho.c("PlacementAdLoader", "api level too low");
            a(1001);
            return;
        }
        if (!a(this.i)) {
            ho.c("PlacementAdLoader", "extra info is invalid");
            a(ErrorCode.ERROR_PLACEMENT_INVALID_PARAM);
            return;
        }
        if (b.LOADING == this.f7012a) {
            ho.b("PlacementAdLoader", "waiting for request finish");
            a(801);
            return;
        }
        String[] strArr = this.c;
        if (strArr == null || strArr.length == 0) {
            ho.c("PlacementAdLoader", "empty ad ids");
            a(ErrorCode.ERROR_PLACEMENT_EMPTY_AD_IDS);
            return;
        }
        if (i <= 0) {
            ho.c("PlacementAdLoader", "invalid totalDuration.");
            a(ErrorCode.ERROR_PLACEMENT_INVALID_PARAM);
            return;
        }
        if (i2 < 0) {
            ho.c("PlacementAdLoader", "invalid maxCount");
            a(ErrorCode.ERROR_PLACEMENT_INVALID_PARAM);
            return;
        }
        bi.b(this.b, this.r);
        ek.a(this.b).e();
        this.f7012a = b.LOADING;
        Pair<String, Boolean> b2 = com.huawei.openalliance.ad.utils.d.b(this.b, true);
        if (b2 == null && bz.h(this.b)) {
            ho.b("PlacementAdLoader", "start to request oaid " + System.currentTimeMillis());
            OAIDServiceManager.getInstance(this.b).requireOaid(new a(z));
            return;
        }
        if (b2 != null) {
            ho.b("PlacementAdLoader", "use cached oaid " + System.currentTimeMillis());
            b((String) b2.first);
            a((Boolean) b2.second);
        }
        a(this.h, z, this.i, this.j, this.k);
    }

    private void a(final Map<String, List<IPlacementAd>> map) {
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.PlacementAdLoader.4
            @Override // java.lang.Runnable
            public void run() {
                List<ContentRecord> a2 = pg.a(map);
                byte[] b2 = cp.b(PlacementAdLoader.this.b);
                for (ContentRecord contentRecord : a2) {
                    if (contentRecord != null) {
                        contentRecord.a(b2);
                        ou ouVar = new ou(PlacementAdLoader.this.b, sc.a(PlacementAdLoader.this.b, contentRecord.e()));
                        ouVar.a(contentRecord);
                        ouVar.o();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Boolean bool) {
        this.g = bool;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final boolean z, final String str, final int i2, final boolean z2) {
        final long currentTimeMillis = System.currentTimeMillis();
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.PlacementAdLoader.1
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PlacementAdLoader", "doRequestAd " + System.currentTimeMillis());
                PlacementAdLoader.this.t.d(System.currentTimeMillis() - currentTimeMillis);
                Video video = new Video(i2);
                int d = com.huawei.openalliance.ad.utils.d.d(PlacementAdLoader.this.b);
                int e = com.huawei.openalliance.ad.utils.d.e(PlacementAdLoader.this.b);
                boolean j = x.j(PlacementAdLoader.this.b);
                int i3 = j ? e : d;
                if (!j) {
                    d = e;
                }
                AdSlotParam.Builder builder = new AdSlotParam.Builder();
                builder.setAdIds(Arrays.asList(PlacementAdLoader.this.c)).setDeviceType(i).setIsPreload(Boolean.valueOf(z)).setOrientation(1).setWidth(i3).setHeight(d).setOaid(PlacementAdLoader.this.f).setTrackLimited(PlacementAdLoader.this.g).setTest(z2).setRequestOptions(PlacementAdLoader.this.r).setLocation(PlacementAdLoader.this.s).setMaxCount(PlacementAdLoader.this.w).setContentBundle(PlacementAdLoader.this.y).setTotalDuration(PlacementAdLoader.this.v).a(video);
                if (PlacementAdLoader.this.x != null) {
                    builder.c(PlacementAdLoader.this.x);
                }
                ol olVar = new ol(PlacementAdLoader.this.b);
                olVar.a(PlacementAdLoader.this.t);
                AdContentRsp a2 = olVar.a(builder.build(), str);
                if (a2 != null) {
                    PlacementAdLoader.this.u = a2.k();
                }
                PlacementAdLoader.this.t.u().h(System.currentTimeMillis());
                PlacementAdLoader placementAdLoader = PlacementAdLoader.this;
                placementAdLoader.e = op.a(placementAdLoader.b, PlacementAdLoader.this, z);
                PlacementAdLoader.this.e.a(a2);
                PlacementAdLoader.this.f7012a = b.IDLE;
            }
        }, m.a.NETWORK, false);
    }

    private void a(int i, int i2) {
        a(true, i, i2);
        ce.a(this.b).a(this.h);
        ce.a(this.b).a(this.c);
    }

    class a extends OAIDServiceManager.OaidResultCallback {
        private final boolean b;

        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
        public int b() {
            return 60;
        }

        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
        public void a(String str, boolean z) {
            ho.b("PlacementAdLoader", "onOaidAcquired " + System.currentTimeMillis());
            PlacementAdLoader.this.b(str);
            PlacementAdLoader.this.a(Boolean.valueOf(z));
            PlacementAdLoader placementAdLoader = PlacementAdLoader.this;
            placementAdLoader.a(placementAdLoader.h, this.b, PlacementAdLoader.this.i, PlacementAdLoader.this.j, PlacementAdLoader.this.k);
            com.huawei.openalliance.ad.utils.d.a(PlacementAdLoader.this.b, str, z);
        }

        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
        public void a() {
            ho.b("PlacementAdLoader", "onOaidAcquireFailed " + System.currentTimeMillis());
            PlacementAdLoader placementAdLoader = PlacementAdLoader.this;
            placementAdLoader.a(placementAdLoader.h, this.b, PlacementAdLoader.this.i, PlacementAdLoader.this.j, PlacementAdLoader.this.k);
        }

        public a(boolean z) {
            this.b = z;
        }
    }

    private PlacementAdLoader(Builder builder) {
        this.f7012a = b.IDLE;
        this.n = new HashMap(4);
        this.o = new HashMap(4);
        this.p = false;
        this.q = false;
        this.t = new DelayInfo();
        if (builder == null || builder.g() == null || !ao.b(builder.g())) {
            this.c = new String[0];
            return;
        }
        this.b = builder.g();
        String[] a2 = builder.a();
        if (bg.a(a2)) {
            this.c = new String[0];
        } else {
            String[] strArr = new String[a2.length];
            this.c = strArr;
            System.arraycopy(a2, 0, strArr, 0, a2.length);
        }
        this.h = builder.b();
        this.i = builder.c();
        this.j = builder.d();
        this.k = builder.e();
        this.l = builder.f();
        this.m = dt.h();
        this.s = builder.j;
        this.r = builder.i;
        this.x = builder.k;
        this.y = builder.e;
    }
}
