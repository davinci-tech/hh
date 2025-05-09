package com.huawei.openalliance.ad.inter;

import android.content.Context;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.PPSNativeView;
import com.huawei.openalliance.ad.views.PPSPlacementView;

/* loaded from: classes5.dex */
public interface IAppDownloadManager {
    public static final int MAX_PROGRESS = 100;
    public static final int MIN_PROGRESS = 0;

    void addOnResolutionRequiredListener(AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener);

    void cancelDownload(Context context, INativeAd iNativeAd);

    void cancelDownload(Context context, IPlacementAd iPlacementAd);

    AppStatus getAppStatus(Context context, INativeAd iNativeAd);

    AppStatus getAppStatus(Context context, IPlacementAd iPlacementAd);

    int getDownloadProgress(Context context, INativeAd iNativeAd);

    int getDownloadProgress(Context context, IPlacementAd iPlacementAd);

    boolean openApp(Context context, INativeAd iNativeAd);

    boolean openApp(Context context, IPlacementAd iPlacementAd);

    void pauseDownload(Context context, INativeAd iNativeAd);

    void pauseDownload(Context context, IPlacementAd iPlacementAd);

    void removeAllOnResolutionRequiredListener();

    void removeOnResolutionRequiredListener();

    int resumeDownload(Context context, INativeAd iNativeAd);

    int resumeDownload(Context context, IPlacementAd iPlacementAd);

    void setAgdDownloadSource(int i);

    int startDownload(Context context, INativeAd iNativeAd);

    int startDownload(Context context, IPlacementAd iPlacementAd);

    int startDownload(Context context, PPSNativeView pPSNativeView, INativeAd iNativeAd);

    int startDownload(Context context, PPSPlacementView pPSPlacementView, IPlacementAd iPlacementAd);
}
