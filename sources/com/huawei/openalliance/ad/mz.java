package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.INativeAdLoader;
import com.huawei.openalliance.ad.inter.NativeAdLoader;
import com.huawei.openalliance.ad.inter.data.BannerSize;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.listeners.ContentIdListener;
import com.huawei.openalliance.ad.inter.listeners.NativeAdListener;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.views.PPSBannerView;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class mz extends jj<com.huawei.openalliance.ad.views.interfaces.h> implements nw<com.huawei.openalliance.ad.views.interfaces.h> {
    private INativeAdLoader d;
    private Context e;
    private qq f;
    private ContentRecord g;
    private INativeAd h;
    private RequestOptions i;
    private String j;
    private Location k;
    private Integer l;
    private Integer m;
    private int n;

    @Override // com.huawei.openalliance.ad.nw
    public void b(String str) {
        this.j = str;
    }

    @Override // com.huawei.openalliance.ad.nw
    public void b(Integer num) {
        this.m = num;
    }

    @Override // com.huawei.openalliance.ad.nw
    public boolean a(BannerSize bannerSize, float f) {
        boolean z = false;
        if (!(d() instanceof PPSBannerView)) {
            return false;
        }
        PPSBannerView pPSBannerView = (PPSBannerView) d();
        Context applicationContext = pPSBannerView.getContext().getApplicationContext();
        int width = pPSBannerView.getWidth();
        int height = pPSBannerView.getHeight();
        if (ho.a()) {
            ho.a("BannerPresenter", "banner view width: %s, height: %s", Integer.valueOf(width), Integer.valueOf(height));
        }
        DisplayMetrics f2 = com.huawei.openalliance.ad.utils.d.f(this.e);
        if (width > f2.widthPixels || height > f2.heightPixels) {
            ho.c("BannerPresenter", "Ad view is off screen");
            return false;
        }
        int a2 = bannerSize.a();
        int b = bannerSize.b();
        float f3 = a2 - width;
        float f4 = a2;
        float f5 = b - height;
        float f6 = b;
        float f7 = f5 / f6;
        if (f3 / f4 < f && f7 < f) {
            z = true;
        }
        if (!z) {
            float j = com.huawei.openalliance.ad.utils.d.j(applicationContext);
            if (j > 0.0f) {
                ho.c("BannerPresenter", "Not enough space to show ad. Needs %s×%s dp, but only has %s×%s dp", Integer.valueOf(Math.round(f4 / j)), Integer.valueOf(Math.round(f6 / j)), Integer.valueOf(Math.round(width / j)), Integer.valueOf(Math.round(height / j)));
            }
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.nw
    public boolean a() {
        return com.huawei.openalliance.ad.utils.ao.b(this.e);
    }

    @Override // com.huawei.openalliance.ad.nw
    public void a(String str, int i, List<String> list, int i2) {
        if (str == null || str.isEmpty()) {
            ho.c("BannerPresenter", "adId is null or empty when load ad");
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.mz.1
                @Override // java.lang.Runnable
                public void run() {
                    mz.this.d().a(702);
                }
            });
            return;
        }
        ho.a("BannerPresenter", "loadAd ,adId:%s", str);
        this.n = i2;
        NativeAdLoader nativeAdLoader = new NativeAdLoader(this.e, new String[]{str}, i, list);
        this.d = nativeAdLoader;
        if (nativeAdLoader instanceof NativeAdLoader) {
            nativeAdLoader.setLocation(this.k);
            ((NativeAdLoader) this.d).a(Integer.valueOf(this.n));
            ((NativeAdLoader) this.d).setContentBundle(this.j);
        }
        this.d.setAdWidth(this.l);
        this.d.setAdHeight(this.m);
        this.d.setRequestOptions(this.i);
        this.d.setListener(new NativeAdListener() { // from class: com.huawei.openalliance.ad.mz.2
            @Override // com.huawei.openalliance.ad.inter.listeners.NativeAdListener
            public void onAdsLoaded(Map<String, List<INativeAd>> map) {
                ho.a("BannerPresenter", "loadAd onAdsLoaded");
                mz mzVar = mz.this;
                mzVar.h = mzVar.a(map);
                com.huawei.openalliance.ad.utils.m.b(new Runnable() { // from class: com.huawei.openalliance.ad.mz.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        mz.this.c();
                    }
                });
                mz.this.b();
            }

            @Override // com.huawei.openalliance.ad.inter.listeners.NativeAdListener
            public void onAdFailed(final int i3) {
                ho.a("BannerPresenter", "loadAd onAdFailed");
                com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.mz.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        mz.this.d().a(i3);
                    }
                });
            }
        });
        this.d.setContentIdListener(new a(this));
        this.d.loadAds(com.huawei.openalliance.ad.utils.x.k(this.e), null, false);
    }

    @Override // com.huawei.openalliance.ad.nw
    public void a(Integer num) {
        this.l = num;
    }

    @Override // com.huawei.openalliance.ad.nw
    public void a(com.huawei.openalliance.ad.inter.data.e eVar) {
        this.h = eVar;
        this.g = pd.a(eVar);
        Context context = this.e;
        this.f = new ou(context, new ry(context, eVar == null ? 8 : eVar.e()), this.g);
    }

    @Override // com.huawei.openalliance.ad.nw
    public void a(RequestOptions requestOptions) {
        this.i = requestOptions;
    }

    @Override // com.huawei.openalliance.ad.nw
    public void a(Location location) {
        this.k = location;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        INativeAd iNativeAd = this.h;
        if (iNativeAd == null) {
            ho.c("BannerPresenter", "downLoadBitmap nativeAd is null");
            a(ErrorCode.ERROR_CODE_OTHER);
            return;
        }
        List<ImageInfo> imageInfos = iNativeAd.getImageInfos();
        if (com.huawei.openalliance.ad.utils.bg.a(imageInfos)) {
            ho.c("BannerPresenter", "downLoadBitmap imageInfo is null");
            a(ErrorCode.ERROR_CODE_OTHER);
            return;
        }
        String a2 = a(imageInfos.get(0), System.currentTimeMillis());
        if (a2 != null) {
            com.huawei.openalliance.ad.utils.az.a(this.e, a2, new az.a() { // from class: com.huawei.openalliance.ad.mz.4
                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a(final Drawable drawable) {
                    com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.mz.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            mz.this.d().a(drawable, mz.this.h);
                        }
                    });
                }

                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a() {
                    ho.c("BannerPresenter", "loadImage onFail");
                    mz.this.a(ErrorCode.ERROR_CODE_OTHER);
                }
            }, this.g);
        } else {
            ho.c("BannerPresenter", "downLoadBitmap filePath is null");
            a(ErrorCode.ERROR_CODE_OTHER);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        INativeAd iNativeAd;
        final long parseLong;
        if (this.n == 1 || (iNativeAd = this.h) == null) {
            return;
        }
        String ai = iNativeAd instanceof com.huawei.openalliance.ad.inter.data.e ? ((com.huawei.openalliance.ad.inter.data.e) iNativeAd).ai() : null;
        ho.b("BannerPresenter", "setBannerRefresh: %s", ai);
        if (TextUtils.isEmpty(ai)) {
            return;
        }
        if ("N".equalsIgnoreCase(ai)) {
            parseLong = 0;
        } else if ("Y".equalsIgnoreCase(ai)) {
            parseLong = fh.b(this.e).bq();
        } else {
            try {
                parseLong = Long.parseLong(ai);
            } catch (NumberFormatException e) {
                ho.c("BannerPresenter", "parseIntOrDefault exception: " + e.getClass().getSimpleName());
                return;
            }
        }
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.mz.3
            @Override // java.lang.Runnable
            public void run() {
                mz.this.d().a(parseLong);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i) {
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.mz.5
            @Override // java.lang.Runnable
            public void run() {
                mz.this.d().a(i);
                if (i == 499) {
                    mz.this.d().a();
                }
            }
        });
    }

    private String a(ImageInfo imageInfo, long j) {
        return new oo(this.e, this.h).a(imageInfo, j);
    }

    static class a implements ContentIdListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<mz> f7292a;

        @Override // com.huawei.openalliance.ad.inter.listeners.ContentIdListener
        public void a(final List<String> list) {
            ho.b("BannerPresenter", "loadAd onInValidContentIdsGot");
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.mz.a.1
                @Override // java.lang.Runnable
                public void run() {
                    mz mzVar = (mz) a.this.f7292a.get();
                    if (mzVar == null) {
                        ho.c("BannerPresenter", "onInValidContentIdsGot presenter is null");
                    } else {
                        mzVar.d().a(list);
                    }
                }
            });
        }

        public a(mz mzVar) {
            this.f7292a = new WeakReference<>(mzVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public INativeAd a(Map<String, List<INativeAd>> map) {
        if (map == null) {
            return null;
        }
        Iterator<Map.Entry<String, List<INativeAd>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Iterator<INativeAd> it2 = it.next().getValue().iterator();
            if (it2.hasNext()) {
                return it2.next();
            }
        }
        return null;
    }

    public mz(Context context, com.huawei.openalliance.ad.views.interfaces.h hVar) {
        a((mz) hVar);
        this.e = context != null ? context.getApplicationContext() : context;
    }
}
