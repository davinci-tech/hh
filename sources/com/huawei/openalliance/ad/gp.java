package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.inter.listeners.RewardAdListener;
import java.util.Map;

/* loaded from: classes5.dex */
public class gp implements gl {

    /* renamed from: a, reason: collision with root package name */
    private Context f6886a;
    private RewardAdListener b;

    @Override // com.huawei.openalliance.ad.gl
    public void a(Map map) {
        StringBuilder sb = new StringBuilder("onAdsLoaded, size:");
        sb.append(map != null ? Integer.valueOf(map.size()) : null);
        sb.append(", listener:");
        sb.append(this.b);
        ho.b("RewardCallbackProc", sb.toString());
        RewardAdListener rewardAdListener = this.b;
        if (rewardAdListener != null) {
            rewardAdListener.onAdsLoaded(map);
        }
        com.huawei.openalliance.ad.utils.aa.a(this.f6886a, map);
    }

    @Override // com.huawei.openalliance.ad.gl
    public void a(int i) {
        ho.b("RewardCallbackProc", "onAdFailed, errorCode:" + i);
        RewardAdListener rewardAdListener = this.b;
        if (rewardAdListener != null) {
            rewardAdListener.onAdFailed(i);
        }
    }

    public gp(Context context, RewardAdListener rewardAdListener) {
        this.f6886a = context.getApplicationContext();
        this.b = rewardAdListener;
    }
}
