package com.huawei.health.h5pro.vengine;

import android.content.Intent;
import android.text.TextUtils;
import android.webkit.WebView;
import com.huawei.health.h5pro.utils.AppInfoUtil;
import com.huawei.health.h5pro.vengine.load.H5ProInstanceStatus;
import com.huawei.health.h5pro.vengine.load.H5ProInstanceStatusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class H5ProInstance {
    public static final String TAG = "H5PRO_H5ProInstance";
    public H5ProAppInfo mH5ProAppInfo;
    public H5ProJsCbkInvoker<Object> mH5ProJsCbkInvoker;
    public H5ProViewControls mH5ProViewControls;
    public boolean mIsEnableSelfProtection;
    public String mPath;
    public String[] mSafeUrls;
    public String mTitleName;
    public boolean mIsEmbedded = false;
    public boolean mIsLoaded = false;
    public boolean mIsEnableImageCache = false;
    public boolean mIsDisableImageProxy = false;
    public boolean mIsInterceptWebViewPause = false;
    public final List<JsBridgeCleaner> mListCleaner = new ArrayList();
    public final Map<String, String> mAccessTokenMap = new HashMap();
    public long mLaunchTime = System.currentTimeMillis();

    public interface JsBridgeCleaner {
        void destroy();
    }

    public abstract boolean canGoBack();

    public abstract H5ProAppLoadListener getAppLoadListener();

    public abstract H5ProAppLoader getAppLoader();

    public abstract String getUrl();

    public abstract WebView getWebView();

    public abstract void goBack(H5ResultCallback h5ResultCallback);

    public abstract void onActivityResult(int i, int i2, Intent intent);

    public abstract void onMultiWindowModeChange(int i);

    public void onPause() {
    }

    public abstract void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    public void onResume() {
    }

    public abstract void onStatusChanged(H5ProInstanceStatus h5ProInstanceStatus);

    public abstract String readPreRequestsJson();

    public abstract void registerInstanceStatusListener(H5ProInstanceStatusListener h5ProInstanceStatusListener);

    public abstract void registerJsModule(String str, Object obj);

    public abstract void setBackGroundColor(int i);

    public abstract void setIsEnableOnDestroyCallback(boolean z);

    public abstract void setIsEnableOnPauseCallback(boolean z);

    public abstract void setIsEnableOnResumeCallback(boolean z);

    public void updateTitle(String str) {
        H5ProAppInfo appInfo;
        if (!TextUtils.isEmpty(str) || (appInfo = getAppInfo()) == null || TextUtils.isEmpty(appInfo.getAppName())) {
            this.mTitleName = str;
        } else {
            this.mTitleName = appInfo.getAppName();
        }
    }

    public void setViewControls(H5ProViewControls h5ProViewControls) {
        this.mH5ProViewControls = h5ProViewControls;
    }

    public void setSafeUrls(String[] strArr) {
        this.mSafeUrls = strArr;
    }

    public void setPath(String str) {
        this.mPath = str;
    }

    public void setLoaded() {
        this.mIsLoaded = true;
    }

    public void setJsCbkInvoker(H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker) {
        this.mH5ProJsCbkInvoker = h5ProJsCbkInvoker;
    }

    public void setIsEnableSelfProtection(boolean z) {
        this.mIsEnableSelfProtection = z;
    }

    public void setIsEnableImageCache(boolean z) {
        this.mIsEnableImageCache = z;
    }

    public void setIsDisableImageProxy(boolean z) {
        this.mIsDisableImageProxy = z;
    }

    public void setEmbedded() {
        this.mIsEmbedded = true;
    }

    public void setAppInfo(H5ProAppInfo h5ProAppInfo) {
        this.mH5ProAppInfo = h5ProAppInfo;
    }

    public void setAccessToken(String str) {
        String hostOrPkgNameFroUrl = AppInfoUtil.getInstance().getHostOrPkgNameFroUrl(getUrl(), this.mH5ProAppInfo);
        if (hostOrPkgNameFroUrl == null) {
            return;
        }
        this.mAccessTokenMap.put(hostOrPkgNameFroUrl, str);
    }

    public void removeAccessToken() {
        String hostOrPkgNameFroUrl = AppInfoUtil.getInstance().getHostOrPkgNameFroUrl(getUrl(), this.mH5ProAppInfo);
        if (TextUtils.isEmpty(hostOrPkgNameFroUrl)) {
            return;
        }
        this.mAccessTokenMap.remove(hostOrPkgNameFroUrl);
    }

    public boolean registerCleaner(JsBridgeCleaner jsBridgeCleaner) {
        if (this.mListCleaner.contains(jsBridgeCleaner)) {
            return false;
        }
        this.mListCleaner.add(jsBridgeCleaner);
        return true;
    }

    public boolean isLoaded() {
        return this.mIsLoaded;
    }

    public boolean isInterceptWebViewPause() {
        return this.mIsInterceptWebViewPause;
    }

    public boolean isEnableSelfProtection() {
        return this.mIsEnableSelfProtection;
    }

    public boolean isEnableImageCache() {
        return this.mIsEnableImageCache;
    }

    public boolean isEmbedded() {
        return this.mIsEmbedded;
    }

    public boolean isDisableImageProxy() {
        return this.mIsDisableImageProxy;
    }

    public void interceptWebViewPause(boolean z) {
        this.mIsInterceptWebViewPause = z;
    }

    public H5ProViewControls getViewControls() {
        return this.mH5ProViewControls;
    }

    public String getTitle() {
        return this.mTitleName;
    }

    public String[] getSafeUrls() {
        return this.mSafeUrls;
    }

    public String getPath() {
        return this.mPath;
    }

    public long getLaunchTime() {
        return this.mLaunchTime;
    }

    public H5ProJsCbkInvoker<Object> getJsCbkInvoker() {
        return this.mH5ProJsCbkInvoker;
    }

    public H5ProAppInfo getAppInfo() {
        return this.mH5ProAppInfo;
    }

    public String getAppFlag() {
        H5ProAppInfo h5ProAppInfo = this.mH5ProAppInfo;
        return h5ProAppInfo != null ? TextUtils.isEmpty(h5ProAppInfo.getPkgName()) ? "H5ProLight" : TextUtils.isEmpty(this.mH5ProAppInfo.getAppName()) ? this.mH5ProAppInfo.getPkgName() : this.mH5ProAppInfo.getAppName() : "";
    }

    public String getAccessToken() {
        String hostOrPkgNameFroUrl = AppInfoUtil.getInstance().getHostOrPkgNameFroUrl(getUrl(), this.mH5ProAppInfo);
        return TextUtils.isEmpty(hostOrPkgNameFroUrl) ? hostOrPkgNameFroUrl : this.mAccessTokenMap.get(hostOrPkgNameFroUrl);
    }

    public void destroy() {
        Iterator<JsBridgeCleaner> it = this.mListCleaner.iterator();
        while (it.hasNext()) {
            it.next().destroy();
        }
        this.mSafeUrls = null;
        this.mH5ProViewControls = null;
    }
}
