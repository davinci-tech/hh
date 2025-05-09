package com.huawei.openalliance.ad.inter;

import android.location.Location;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.inter.listeners.ContentIdListener;
import com.huawei.openalliance.ad.inter.listeners.NativeAdListener;
import java.util.List;

/* loaded from: classes5.dex */
public interface INativeAdLoader {
    void enableDirectCacheVideo(boolean z);

    void enableDirectReturnVideoAd(boolean z);

    void loadAds(int i, String str, boolean z);

    void loadAds(int i, boolean z);

    void setAdHeight(Integer num);

    void setAdWidth(Integer num);

    void setAdsReturnedFromThread(boolean z);

    void setAudioTotalDuration(Integer num);

    void setContentBundle(String str);

    void setContentIdListener(ContentIdListener contentIdListener);

    void setDetailedCreativeType(List<Integer> list);

    void setExtraInfo(String str);

    void setIsSmart(Integer num);

    void setJssdkVersion(String str);

    void setListener(NativeAdListener nativeAdListener);

    void setLocation(Location location);

    void setMaxAdNumbers(int i);

    void setRequestOptions(RequestOptions requestOptions);

    void setSupportTptAd(boolean z);
}
