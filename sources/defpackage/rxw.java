package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.listeners.NativeAdListener;
import com.huawei.ui.main.stories.soical.NewSocialFragment;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes7.dex */
public class rxw implements NativeAdListener {
    private static final String d = "com.huawei.ui.main.stories.soical.views.SocialAdListener";

    /* renamed from: a, reason: collision with root package name */
    private final WeakReference<NewSocialFragment> f16959a;
    private final WeakReference<CountDownLatch> c;

    public rxw(NewSocialFragment newSocialFragment, CountDownLatch countDownLatch) {
        this.f16959a = new WeakReference<>(newSocialFragment);
        this.c = new WeakReference<>(countDownLatch);
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.NativeAdListener
    public void onAdsLoaded(Map<String, List<INativeAd>> map) {
        String str = d;
        LogUtil.a(str, "onAdsLoaded, ad.size:" + map.size());
        NewSocialFragment newSocialFragment = this.f16959a.get();
        if (newSocialFragment == null) {
            LogUtil.h(str, "fragment = null");
            return;
        }
        newSocialFragment.e(sdi.d(map));
        CountDownLatch countDownLatch = this.c.get();
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.NativeAdListener
    public void onAdFailed(int i) {
        LogUtil.b(d, "fail to load ad, errorCode is:" + i);
        CountDownLatch countDownLatch = this.c.get();
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }
}
