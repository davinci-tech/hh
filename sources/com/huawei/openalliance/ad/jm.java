package com.huawei.openalliance.ad;

import com.huawei.hms.ads.uiengine.common.MediaStateApi;

/* loaded from: classes5.dex */
public class jm implements MediaStateApi {

    /* renamed from: a, reason: collision with root package name */
    private jr f7132a;

    @Override // com.huawei.hms.ads.uiengine.common.MediaStateApi
    public void onMediaPause(int i) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.MediaStateApi
    public void onMediaStop(int i) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.MediaStateApi
    public void onProgress(int i, int i2) {
        ho.a("MediaStateImpl", "onProgress, percentage is %s, playTime is %s", Integer.valueOf(i), Integer.valueOf(i2));
        jr jrVar = this.f7132a;
        if (jrVar != null) {
            jrVar.a(i, i2);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.MediaStateApi
    public void onMediaStart(int i) {
        ho.a("MediaStateImpl", "onVideoStart");
        jr jrVar = this.f7132a;
        if (jrVar != null) {
            jrVar.c();
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.MediaStateApi
    public void onMediaCompletion(int i) {
        ho.a("MediaStateImpl", "onVideoEnd, playTime is %s", Integer.valueOf(i));
    }

    public jm(jr jrVar) {
        this.f7132a = jrVar;
    }
}
