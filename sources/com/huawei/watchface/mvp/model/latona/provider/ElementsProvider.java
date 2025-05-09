package com.huawei.watchface.mvp.model.latona.provider;

import android.text.TextUtils;
import com.huawei.watchface.mvp.model.latona.LatonaConfig;
import com.huawei.watchface.mvp.model.latona.LatonaElement;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFace;
import com.huawei.watchface.mvp.model.latona.WatchFaceThemeElement;
import com.huawei.watchface.mvp.model.latona.WatchFaceThemeElementConfig;
import com.huawei.watchface.utils.HwLog;
import java.util.List;

/* loaded from: classes9.dex */
public class ElementsProvider {
    private static final String TAG = "ElementsProvider";
    private LatonaWatchFace mLatonaWatchFace;
    private ResourceResolver mResolver;
    private WatchFaceThemeProvider mWatchFaceThemeProvider;
    private WatchFaceThemeProviders mWatchFaceThemeProviders;

    public ElementsProvider(ResourceResolver resourceResolver, boolean z) {
        List<WatchFaceThemeProvider> watchFaceThemeProviders;
        this.mResolver = resourceResolver;
        if (z) {
            WatchFaceThemeProviders parseConfigFile = resourceResolver.parseConfigFile();
            this.mWatchFaceThemeProviders = parseConfigFile;
            if (parseConfigFile == null || (watchFaceThemeProviders = parseConfigFile.getWatchFaceThemeProviders()) == null || watchFaceThemeProviders.size() == 0) {
                return;
            }
            for (WatchFaceThemeProvider watchFaceThemeProvider : watchFaceThemeProviders) {
                if (watchFaceThemeProvider != null) {
                    this.mWatchFaceThemeProvider = watchFaceThemeProvider;
                }
            }
            return;
        }
        this.mLatonaWatchFace = resourceResolver.parserLetonaConfigFile();
    }

    public WatchFaceThemeElement getElement(String str) {
        WatchFaceThemeProvider watchFaceThemeProvider = this.mWatchFaceThemeProvider;
        if (watchFaceThemeProvider == null) {
            HwLog.i(TAG, "getElement failed, mWatchFaceThemeProvider is null");
            return null;
        }
        List<WatchFaceThemeElement> watchFaceThemeElements = watchFaceThemeProvider.getWatchFaceThemeElements();
        if (watchFaceThemeElements == null || watchFaceThemeElements.size() == 0) {
            HwLog.i(TAG, "getElement failed, watchFaceThemeElements is null");
            return null;
        }
        for (WatchFaceThemeElement watchFaceThemeElement : watchFaceThemeElements) {
            if (TextUtils.equals(watchFaceThemeElement.getLabel(), str)) {
                return watchFaceThemeElement;
            }
        }
        return null;
    }

    public String getDpi() {
        WatchFaceThemeProvider watchFaceThemeProvider = this.mWatchFaceThemeProvider;
        if (watchFaceThemeProvider == null) {
            return null;
        }
        return watchFaceThemeProvider.getDpi();
    }

    public List<WatchFaceThemeElement> getElements() {
        List<WatchFaceThemeElement> watchFaceThemeElements;
        WatchFaceThemeProvider watchFaceThemeProvider = this.mWatchFaceThemeProvider;
        if (watchFaceThemeProvider == null || (watchFaceThemeElements = watchFaceThemeProvider.getWatchFaceThemeElements()) == null || watchFaceThemeElements.size() == 0) {
            return null;
        }
        return watchFaceThemeElements;
    }

    public void setElements(List<WatchFaceThemeElement> list) {
        if (list == null || list.size() == 0) {
            HwLog.i(TAG, "setWatchFaceThemeElements failed, watchFaceThemeElements is null");
            return;
        }
        WatchFaceThemeProvider watchFaceThemeProvider = this.mWatchFaceThemeProvider;
        if (watchFaceThemeProvider == null) {
            HwLog.i(TAG, "setWatchFaceThemeElements failed ");
        } else {
            watchFaceThemeProvider.setWatchFaceThemeElements(list);
        }
    }

    public void setConfig(WatchFaceThemeElementConfig watchFaceThemeElementConfig) {
        WatchFaceThemeProvider watchFaceThemeProvider = this.mWatchFaceThemeProvider;
        if (watchFaceThemeProvider == null || watchFaceThemeElementConfig == null) {
            HwLog.i(TAG, "setWatchFaceThemeElementConfig failed ");
        } else {
            watchFaceThemeProvider.setWatchFaceThemeElementConfig(watchFaceThemeElementConfig);
        }
    }

    public WatchFaceThemeElementConfig getConfig() {
        WatchFaceThemeProvider watchFaceThemeProvider = this.mWatchFaceThemeProvider;
        if (watchFaceThemeProvider == null) {
            HwLog.i(TAG, "getWatchFaceThemeElementConfig failed, mWatchFaceThemeProvider is null");
            return null;
        }
        return watchFaceThemeProvider.getWatchFaceThemeElementConfig();
    }

    public boolean saveProviders() {
        return this.mResolver.writeBackConfigFile(this.mWatchFaceThemeProviders);
    }

    public List<LatonaElement> getLatonaElements() {
        LatonaWatchFace latonaWatchFace = this.mLatonaWatchFace;
        if (latonaWatchFace == null) {
            HwLog.i(TAG, "getLatonaElements failed, mLetonaWatchface is null");
            return null;
        }
        return latonaWatchFace.getLatonaElements();
    }

    public LatonaElement getLatonaElement(String str) {
        List<LatonaElement> latonaElements = getLatonaElements();
        if (latonaElements == null || latonaElements.size() == 0) {
            HwLog.i(TAG, "latonaElements is null or is not enough length");
            return null;
        }
        for (LatonaElement latonaElement : latonaElements) {
            if (TextUtils.equals(latonaElement.getLabel(), str)) {
                return latonaElement;
            }
        }
        return null;
    }

    public LatonaConfig getLatonaConfig() {
        LatonaWatchFace latonaWatchFace = this.mLatonaWatchFace;
        if (latonaWatchFace == null) {
            HwLog.i(TAG, "getLatonaConfig failed, mLetonaWatchface is null");
            return null;
        }
        return latonaWatchFace.getLatonaConfig();
    }
}
