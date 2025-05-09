package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.inter.listeners.INonwifiActionListener;
import com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener;
import java.io.Serializable;

/* loaded from: classes5.dex */
public interface IRewardAd extends IAd, Serializable {
    RewardItem getRewardItem();

    boolean hasShown();

    boolean isValid();

    void setAudioFocusType(int i);

    void setCustomData(String str);

    void setMobileDataAlertSwitch(boolean z);

    void setMute(boolean z);

    void setNonwifiActionListener(INonwifiActionListener iNonwifiActionListener);

    void setUserId(String str);

    void setVideoConfiguration(VideoConfiguration videoConfiguration);

    void show(Context context, IRewardAdStatusListener iRewardAdStatusListener);
}
