package com.huawei.openalliance.ad.inter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.inter.listeners.IExSplashCallback;
import com.huawei.openalliance.ad.inter.listeners.LandingPageAction;
import com.huawei.openalliance.ad.inter.listeners.LinkedAdListener;
import com.huawei.openalliance.ad.media.IMultiMediaPlayingManager;

/* loaded from: classes5.dex */
public interface f {
    void a(BroadcastReceiver broadcastReceiver);

    void a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);

    void a(VideoConfiguration videoConfiguration, boolean z, boolean z2, int i);

    void a(String str);

    void a(boolean z);

    LandingPageAction b();

    void b(String str);

    IMultiMediaPlayingManager c();

    void c(String str);

    Context d();

    boolean e();

    IExSplashCallback f();

    LinkedAdListener g();

    int h();

    int i();

    Integer j();

    boolean k();

    AppStatusQuery l();

    boolean m();

    boolean n();

    Boolean o();
}
