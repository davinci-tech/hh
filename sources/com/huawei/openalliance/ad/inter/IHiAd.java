package com.huawei.openalliance.ad.inter;

import android.app.Activity;
import android.content.Context;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.download.app.interfaces.AutoOpenListener;
import com.huawei.openalliance.ad.dynamictemplate.IImageLoader;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1;
import com.huawei.openalliance.ad.inter.listeners.ExtensionActionListener;
import com.huawei.openalliance.ad.inter.listeners.IAskForUnlockScreen;
import com.huawei.openalliance.ad.inter.listeners.IExSplashCallback;
import com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback;
import com.huawei.openalliance.ad.inter.listeners.LandingPageAction;
import com.huawei.openalliance.ad.inter.listeners.LinkedAdListener;
import com.huawei.openalliance.ad.magazine.inter.listener.IAdInvalidHandler;
import com.huawei.openalliance.ad.magazine.inter.listener.IPPSDownloadService;
import com.huawei.openalliance.ad.media.IMultiMediaPlayingManager;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes5.dex */
public interface IHiAd {
    void enableSharePd(boolean z);

    void enableUserInfo(boolean z);

    void enableVideoCacheWhenPlay(int i, boolean z);

    IAdInvalidHandler getAdInvalidHandler();

    int getAppActivateStyle();

    IAppDownloadManager getAppDownloadManager();

    IAskForUnlockScreen getAskForUnlockScreen();

    Activity getCurActivity();

    String getEngineVer();

    ExtensionActionListener getExtensionActionListener();

    IPPSDownloadService getPPSDownloadService();

    RequestOptions getRequestConfiguration();

    Map<String, List<String>> getTemplates(List<String> list);

    void informReady();

    void initGrs(String str);

    void initGrs(String str, String str2);

    void initLog(boolean z, int i);

    void initLog(boolean z, int i, String str);

    void initOptions(Context context);

    boolean isAppAutoOpenForbidden();

    boolean isAppInstalledNotify();

    boolean isEnableUserInfo();

    boolean isVideoCacheWhenPlay(int i);

    void notifyUiModeChange(int i);

    void onBackground();

    void onForeground();

    void placementAdPreCfgReq();

    void requestConfig(String str);

    void setAdInvalidHandler(IAdInvalidHandler iAdInvalidHandler);

    void setAppActivateStyle(int i);

    void setAppAutoOpenForbidden(boolean z);

    void setAppDownloadListener(AppDownloadListener appDownloadListener);

    void setAppInstalledNotify(boolean z);

    void setAppStatusQuery(AppStatusQuery appStatusQuery);

    void setAskForUnlockScreen(IAskForUnlockScreen iAskForUnlockScreen);

    void setAutoOpenListener(AutoOpenListener autoOpenListener);

    void setBrand(int i);

    void setBrowserAppDownloadListener(AppDownloadListenerV1 appDownloadListenerV1);

    void setConsent(String str);

    void setCountryCode(String str);

    void setCurActivity(Activity activity);

    void setCustomSSLSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager);

    void setExLinkedAdListener(LinkedAdListener linkedAdListener);

    void setExSplashCallback(IExSplashCallback iExSplashCallback, int i, int i2);

    void setExtensionActionListener(ExtensionActionListener extensionActionListener);

    void setImageLoader(IImageLoader iImageLoader);

    void setIsPreloadWebView(boolean z);

    void setLandingPageAction(LandingPageAction landingPageAction);

    void setMultiMediaPlayingManager(IMultiMediaPlayingManager iMultiMediaPlayingManager);

    void setOpenLinkStatus(boolean z);

    void setOpenWebPageByBrowser(boolean z);

    void setPPSDownloadService(IPPSDownloadService iPPSDownloadService);

    void setPPSWebListener(IPPSWebEventCallback iPPSWebEventCallback);

    void setRequestConfiguration(RequestOptions requestOptions);

    @Deprecated
    void setVideoAutoPlayNet(int i);

    void syncMediaGrs(String str, String str2);
}
