package com.huawei.openalliance.ad.views.interfaces;

import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.AppDownloadButtonStyle;
import com.huawei.openalliance.ad.views.PPSAppDetailView;

/* loaded from: classes5.dex */
public interface IAppDownloadButton extends jk {
    void cancel();

    AppStatus refreshStatus();

    void refreshStatusAsync(AppDownloadButton.OnStatusRefreshedListener onStatusRefreshedListener);

    void setAdLandingPageData(AdLandingPageData adLandingPageData);

    void setAppDetailView(PPSAppDetailView pPSAppDetailView);

    void setAppDownloadButtonStyle(AppDownloadButtonStyle appDownloadButtonStyle);

    boolean setNativeAd(INativeAd iNativeAd);

    boolean setPlacementAd(IPlacementAd iPlacementAd);

    void setPpsLinkedView(IPPSLinkedView iPPSLinkedView);

    void setPpsNativeView(IPPSNativeView iPPSNativeView);

    void setPpsPlacementView(IPPSPlacementView iPPSPlacementView);

    void updateContent(String str);

    void updateStartShowTime(long j);
}
