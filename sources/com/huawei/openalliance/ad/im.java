package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.views.interfaces.INativeVideoView;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class im implements iq {

    /* renamed from: a, reason: collision with root package name */
    WeakReference<INativeVideoView> f6947a;

    @Override // com.huawei.openalliance.ad.iq
    public void a(final int i) {
        ho.b("NativeVideoStreamListener", "stream error, code: %s", Integer.valueOf(i));
        final INativeVideoView iNativeVideoView = this.f6947a.get();
        if (iNativeVideoView != null) {
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.im.1
                @Override // java.lang.Runnable
                public void run() {
                    iNativeVideoView.notifyStreamError(i);
                    if (i == -2) {
                        iNativeVideoView.stop();
                    }
                }
            });
        }
    }

    public im(INativeVideoView iNativeVideoView) {
        this.f6947a = new WeakReference<>(iNativeVideoView);
    }
}
