package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.MotionData;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.pe;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class qz {

    /* renamed from: a, reason: collision with root package name */
    private Context f7477a;
    private gc b;
    private fx c;
    private String d;
    private boolean e;
    private boolean f;
    private boolean g;
    private int h = 1;
    private int i = 0;

    public boolean a(final long j, String str, List<INativeAd> list, final com.huawei.openalliance.ad.inter.data.e eVar, pe.a aVar) {
        String str2;
        eVar.a(99);
        boolean z = true;
        if (this.e) {
            eVar.i(true);
        }
        if (this.e) {
            ho.b("NativeAdParser3", "parser, add nativeAd");
            list.add(eVar);
            z = false;
            if (com.huawei.openalliance.ad.utils.bd.a(Integer.valueOf(eVar.g())) && !this.f) {
                str2 = "no cache";
            } else if (com.huawei.openalliance.ad.utils.c.a()) {
                str2 = "use engine down";
            } else {
                com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.qz.1
                    @Override // java.lang.Runnable
                    public void run() {
                        qz.this.a(eVar);
                        qz.this.a(eVar, j);
                    }
                });
            }
            ho.b("NativeAdParser3", str2);
            return false;
        }
        a(str, eVar, j, aVar);
        return z;
    }

    private boolean b() {
        int i;
        if (this.g || this.f || (i = this.i) == 1) {
            return true;
        }
        return i == 0 && com.huawei.openalliance.ad.utils.bv.c(this.f7477a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(ContentRecord contentRecord, rt rtVar) {
        if (rtVar != null) {
            rtVar.a(contentRecord);
            rtVar.c(true);
            rtVar.d(Constants.TPLATE_CACHE);
            ru a2 = this.c.a(rtVar);
            if (a2 != null) {
                return a2.a();
            }
        }
        return null;
    }

    private rt b(Asset asset, long j) {
        if (asset == null || asset.e() == null) {
            return null;
        }
        rt rtVar = new rt();
        rtVar.c(asset.e().a());
        rtVar.b(asset.e().f());
        rtVar.b(asset.e().h() == 0);
        rtVar.c(true);
        rtVar.a(Long.valueOf(j));
        rtVar.a(Constants.TEMPLATE_VIDEO_SUB_DIR);
        rtVar.a(true);
        return rtVar;
    }

    private boolean a(String str) {
        try {
            return new JSONObject(str).optInt("optional", 0) == 1;
        } catch (Throwable th) {
            ho.b("NativeAdParser3", "isOptional err: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(com.huawei.openalliance.ad.inter.data.e eVar, long j) {
        if (this.b.aA() + 86400000 < com.huawei.openalliance.ad.utils.ao.c()) {
            this.b.b(com.huawei.openalliance.ad.utils.ao.c());
            com.huawei.openalliance.ad.utils.ae.a(this.d, 604800000L);
        }
        ContentRecord a2 = pd.a(eVar);
        if (a2 == null || com.huawei.openalliance.ad.utils.bg.a(a2.aV())) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (Asset asset : a2.aV()) {
            if (asset != null) {
                if (asset.d() != null) {
                    String a3 = a(a2, a(asset, j));
                    if (TextUtils.isEmpty(a3)) {
                        ho.c("NativeAdParser3", "download img: %s failed", asset.b());
                        if (!a(asset.h())) {
                            z = false;
                        }
                    } else {
                        asset.d().b(a3);
                        a(asset, a3);
                    }
                }
                if (asset.e() != null) {
                    if (!a()) {
                        z = false;
                    } else if (1 == this.h || this.f) {
                        ho.b("NativeAdParser3", "cacheVideo");
                        String a4 = a(a2, b(asset, j));
                        if (TextUtils.isEmpty(a4)) {
                            ho.c("NativeAdParser3", "dealVideo, download video failed!");
                            if (!a(asset.h())) {
                                z = false;
                            }
                        } else {
                            asset.e().d(a4);
                        }
                    }
                }
                arrayList.add(asset);
            }
        }
        if (z) {
            eVar.j(arrayList);
        }
        a(a2, j);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        if (1 != this.h || b()) {
            return true;
        }
        ho.c("NativeAdParser3", "cache mode video is not allowed to download in network %d", Integer.valueOf(this.i));
        return false;
    }

    private void a(final String str, final com.huawei.openalliance.ad.inter.data.e eVar, final long j, final pe.a aVar) {
        ho.b("NativeAdParser3", "dealVideo, adId: %s, directCacheVideo: %s.", str, Boolean.valueOf(this.f));
        com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.qz.2
            @Override // java.lang.Runnable
            public void run() {
                qz.this.a(eVar);
                if (qz.this.a(eVar, j)) {
                    eVar.b(true);
                    HashMap hashMap = new HashMap();
                    ArrayList arrayList = new ArrayList(1);
                    arrayList.add(eVar);
                    com.huawei.openalliance.ad.utils.c.a(hashMap, str, arrayList);
                    aVar.a(hashMap);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.huawei.openalliance.ad.inter.data.e eVar) {
        try {
            JSONArray jSONArray = new JSONArray(eVar.Y().a());
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (!jSONObject.isNull("videoDwnNetwork")) {
                    this.i = jSONObject.optInt("videoDwnNetwork");
                } else if (!jSONObject.isNull("videoPlayMode")) {
                    this.h = jSONObject.optInt("videoPlayMode");
                }
            }
        } catch (Throwable th) {
            ho.b("NativeAdParser3", "getTemplateContext err: %s", th.getClass().getSimpleName());
        }
    }

    private void a(final ContentRecord contentRecord, final long j) {
        com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.qz.3
            @Override // java.lang.Runnable
            public void run() {
                ContentRecord contentRecord2 = contentRecord;
                if (contentRecord2 == null || contentRecord2.aT() == null || !qz.this.a()) {
                    return;
                }
                if ((1 == qz.this.h || qz.this.f) && !com.huawei.openalliance.ad.utils.bg.a(contentRecord.aT().c())) {
                    Iterator<MotionData> it = contentRecord.aT().c().iterator();
                    while (it.hasNext()) {
                        rt a2 = qz.this.a(it.next(), j);
                        a2.a(contentRecord);
                        qz.this.b(contentRecord, a2);
                    }
                }
            }
        });
    }

    private void a(Asset asset, String str) {
        if (asset.d().b() <= 0 || asset.d().c() <= 0) {
            Rect a2 = com.huawei.openalliance.ad.utils.az.a(str);
            int width = a2.width();
            int height = a2.height();
            if (width <= 0 || height <= 0) {
                return;
            }
            asset.d().a(width);
            asset.d().b(height);
        }
    }

    private String a(ContentRecord contentRecord, rt rtVar) {
        String b = b(contentRecord, rtVar);
        dk a2 = dh.a(this.f7477a, Constants.TPLATE_CACHE);
        if (!a2.g(b)) {
            a2 = dh.a(this.f7477a, "normal");
        }
        String c = a2.c(b);
        if (com.huawei.openalliance.ad.utils.cz.b(c)) {
            return null;
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public rt a(MotionData motionData, long j) {
        if (motionData == null) {
            return null;
        }
        rt rtVar = new rt();
        rtVar.c(motionData.g());
        rtVar.b(motionData.h());
        rtVar.b(true);
        rtVar.c(true);
        rtVar.d(Constants.TPLATE_CACHE);
        rtVar.a(Long.valueOf(j));
        return rtVar;
    }

    private rt a(Asset asset, long j) {
        if (asset == null || asset.d() == null) {
            return null;
        }
        rt rtVar = new rt();
        rtVar.c(asset.d().a());
        rtVar.b(asset.d().d() != null ? asset.d().d().b() : null);
        rtVar.b(asset.d().d() == null || asset.d().d().d() == 0);
        rtVar.c(true);
        rtVar.a(Long.valueOf(j));
        rtVar.a(Constants.TEMPLATE_VIDEO_SUB_DIR);
        return rtVar;
    }

    public qz(Context context, boolean z, boolean z2, boolean z3) {
        this.f7477a = context;
        this.e = z;
        this.f = z2;
        this.g = z3;
        this.b = fh.b(context);
        this.c = fb.a(context);
        this.d = com.huawei.openalliance.ad.utils.cw.c(context) + File.separator + Constants.PPS_ROOT_PATH + File.separator + Constants.TEMPLATE_VIDEO_SUB_DIR + File.separator;
    }
}
