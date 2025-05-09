package com.huawei.openalliance.ad.inter;

import android.location.Location;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.inter.listeners.RewardAdListener;

/* loaded from: classes5.dex */
public interface IRewardAdLoader {
    void loadAds(int i, boolean z);

    void setContentBundle(String str);

    void setListener(RewardAdListener rewardAdListener);

    void setLocation(Location location);

    void setRequestOptions(RequestOptions requestOptions);
}
