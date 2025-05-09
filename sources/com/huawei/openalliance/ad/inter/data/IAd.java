package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.PromoteInfo;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public interface IAd extends Serializable {
    String getAbilityDetailInfo();

    String getAdChoiceIcon();

    String getAdChoiceUrl();

    String getAdSign();

    AppInfo getAppInfo();

    BiddingInfo getBiddingInfo();

    List<AdvertiserInfo> getCompliance();

    String getContentId();

    int getCreativeType();

    String getCta();

    String getCtrlSwitchs();

    String getDspLogo();

    String getDspName();

    long getEndTime();

    String getHwChannelId();

    int getInterfaceDownloadConfig();

    String getLabel();

    int getMinEffectiveShowRatio();

    long getMinEffectiveShowTime();

    PromoteInfo getPromoteInfo();

    RewardVerifyConfig getRewardVerifyConfig();

    String getShowId();

    long getStartTime();

    String getTaskId();

    String getTransparencyTplUrl();

    String getUniqueId();

    String getWhyThisAd();

    void gotoWhyThisAdPage(Context context);

    boolean isAdIdInWhiteList();

    boolean isExpired();

    boolean isShowAppElement();

    boolean isTransparencyOpen();

    void setRewardVerifyConfig(RewardVerifyConfig rewardVerifyConfig);

    void showAppDetailPage(Context context);
}
