package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public interface IPlacementAd extends IAd, Serializable {
    int getAdinteractiontype();

    List<AdvertiserInfo> getAdvertiserInfo();

    String getAppMarketAppId();

    List<Integer> getClickActionList();

    String getEncodedParamFromServer();

    String getEncodedeMonitors();

    String getIntent();

    int getInterActionType();

    String getJumpText(Context context);

    String getLandWebUrl();

    PlacementMediaFile getMediaFile();

    String getPromotionChannel();

    int getSequence();

    int getShowLandingPageTitleFlag();

    String getWebConfig();

    boolean hasAdvertiserInfo();

    boolean isClicked();

    boolean isImageAd();

    boolean isShown();

    boolean isVideoAd();
}
