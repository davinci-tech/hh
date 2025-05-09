package com.huawei.watchface.mvp.model.latona;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.watchface.mvp.model.datatype.VideoStruct;
import com.huawei.watchface.mvp.model.latona.provider.ElementsProvider;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class LatonaWatchFaceProvider {
    private static final String BACKGROUND_LABEL = "background";
    private static final String COMMA = ",";
    private static final int INT_BIT_SUPPORT = 1;
    private static final int INT_BIT_UNSUPPORT = 0;
    private static final int LIST_MAX_LENGTH = 20;
    private static final String TAG = "LatonaWatchFaceProvider";
    private static LatonaWatchFaceProvider sLatonaWatchFaceProvider;
    private String[] mBackgroundResActives;
    private String mPath;
    private int mWatchFaceHeight;
    private int mWatchFaceWidth;
    private List<String> mTempImageList = new ArrayList(20);
    private LatonaWatchFaceThemeAlbumInfo mWatchFaceThemeAlbumInfo = new LatonaWatchFaceThemeAlbumInfo();

    public LatonaWatchFaceProvider(Context context) {
    }

    public static LatonaWatchFaceProvider getInstance(Context context) {
        if (sLatonaWatchFaceProvider == null) {
            synchronized (LatonaWatchFaceProvider.class) {
                if (sLatonaWatchFaceProvider == null) {
                    sLatonaWatchFaceProvider = new LatonaWatchFaceProvider(context.getApplicationContext());
                }
            }
        }
        return sLatonaWatchFaceProvider;
    }

    public void parseLatonaWatchFaceInfo(ElementsProvider elementsProvider, boolean z) {
        parseLatonaBackGroundElement(elementsProvider);
        parseLatonaConfig(elementsProvider, z);
    }

    private void parseLatonaBackGroundElement(ElementsProvider elementsProvider) {
        LatonaElement latonaElement = elementsProvider.getLatonaElement("background");
        if (latonaElement == null) {
            HwLog.w(TAG, "parseLatonaBackGroundElement latonaBackgroundElement is null");
            return;
        }
        List<LatonaLayer> layers = latonaElement.getLayers();
        if (layers == null || layers.isEmpty()) {
            HwLog.w(TAG, "parseLatonaBackGroundElement layerList is null");
            return;
        }
        LatonaLayer latonaLayer = layers.get(0);
        setBackgroundResourceActive(latonaLayer.getResActive());
        HwLog.i(TAG, "parseLatonaBackGroundElement getResActive:" + latonaLayer.getResActive());
    }

    private void parseLatonaConfig(ElementsProvider elementsProvider, boolean z) {
        LatonaConfig latonaConfig = elementsProvider.getLatonaConfig();
        if (latonaConfig == null) {
            HwLog.w(TAG, "parseLatonaConfig latonaConfig is null");
            return;
        }
        setPositionConfig(latonaConfig.getLatonaPositions(), z);
        setStyleConfig(latonaConfig.getLatonaStyles(), z);
        setDatasConfig(latonaConfig.getLatonaDatas());
        setBackgroundsConfig(latonaConfig.getBackgrounds());
        setHandStyleConfig(latonaConfig.getHandStyles());
    }

    private void setPositionConfig(LatonaPositions latonaPositions, boolean z) {
        setPositionOption(latonaPositions != null);
        if (latonaPositions == null) {
            HwLog.w(TAG, "setPositionConfig failed latonaPositions is null");
            return;
        }
        if (this.mWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setPositionConfig mWatchFaceThemeAlbumInfo is null");
            return;
        }
        List<LatonaPosition> latonaPositionList = latonaPositions.getLatonaPositionList();
        ArrayList arrayList = new ArrayList(20);
        if (latonaPositionList != null && !latonaPositionList.isEmpty()) {
            for (LatonaPosition latonaPosition : latonaPositionList) {
                PositionOptionalConfig positionOptionalConfig = new PositionOptionalConfig();
                positionOptionalConfig.setPositionIndex(latonaPosition.getPositionLabel());
                positionOptionalConfig.setPositionRect(latonaPosition.getPositionRect());
                arrayList.add(positionOptionalConfig);
            }
            HwLog.i(TAG, "setPositionConfig positionOptionalConfigList:" + arrayList.toString());
        }
        if (!z) {
            this.mWatchFaceThemeAlbumInfo.setPositionIndex(latonaPositions.getSelectedIndex());
        }
        this.mWatchFaceThemeAlbumInfo.setLatonaPositionList(arrayList);
    }

    private void setStyleConfig(LatonaStyles latonaStyles, boolean z) {
        setStyleOption(latonaStyles != null);
        if (latonaStyles == null) {
            HwLog.w(TAG, "setStyleConfig failed latonaStyles is null");
            return;
        }
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setStyleConfig failed mWatchFaceThemeAlbumInfos is null");
            return;
        }
        if (!z) {
            latonaWatchFaceThemeAlbumInfo.setStyleIndex(latonaStyles.getSelectedIndex());
        }
        List<LatonaStyle> styles = latonaStyles.getStyles();
        ArrayList arrayList = new ArrayList(20);
        if (styles != null && !styles.isEmpty()) {
            for (LatonaStyle latonaStyle : styles) {
                StyleOptionalConfig styleOptionalConfig = new StyleOptionalConfig();
                styleOptionalConfig.setIndex(latonaStyle.getIndex());
                styleOptionalConfig.setResPreview(latonaStyle.getResPreview());
                arrayList.add(styleOptionalConfig);
            }
        }
        this.mWatchFaceThemeAlbumInfo.setStyleResPathList(arrayList);
    }

    private void setDatasConfig(LatonaDatas latonaDatas) {
        setDataOption(latonaDatas != null);
        if (latonaDatas == null) {
            HwLog.w(TAG, "setDatasConfig failed latonaDatas is null");
            return;
        }
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setDatasConfig failed mWatchFaceThemeAlbumInfos is null");
            return;
        }
        latonaWatchFaceThemeAlbumInfo.setDataIndex(latonaDatas.getSelectedIndex());
        List<LatonaData> latonaDataList = latonaDatas.getLatonaDataList();
        ArrayList arrayList = new ArrayList(20);
        if (latonaDataList != null && !latonaDataList.isEmpty()) {
            for (LatonaData latonaData : latonaDataList) {
                DataOptionalConfig dataOptionalConfig = new DataOptionalConfig();
                dataOptionalConfig.setIndex(latonaData.getDataLabel());
                dataOptionalConfig.setResPreview(latonaData.getResPreview());
                dataOptionalConfig.setDataType(latonaData.getDataType());
                arrayList.add(dataOptionalConfig);
            }
        }
        this.mWatchFaceThemeAlbumInfo.setDataResPathList(arrayList);
    }

    private void setBackgroundsConfig(Backgrounds backgrounds) {
        setBackgroundOption(backgrounds != null);
        if (backgrounds == null) {
            HwLog.w(TAG, "setBackgroundsConfig failed latonaDatas is null");
            return;
        }
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setBackgroundsConfig failed mWatchFaceThemeAlbumInfos is null");
            return;
        }
        latonaWatchFaceThemeAlbumInfo.setBackgroundIndex(backgrounds.getSelectedIndex());
        List<Background> backgroundList = backgrounds.getBackgroundList();
        ArrayList arrayList = new ArrayList(20);
        if (backgroundList != null && !backgroundList.isEmpty()) {
            for (Background background : backgroundList) {
                BackgroundOptionalConfig backgroundOptionalConfig = new BackgroundOptionalConfig();
                backgroundOptionalConfig.setIndex(background.getIndex());
                backgroundOptionalConfig.setResPreview(background.getResPreview());
                arrayList.add(backgroundOptionalConfig);
            }
        }
        setBackgroundResActives(arrayList);
        this.mWatchFaceThemeAlbumInfo.setDefaultBackgroundOptionalConfigList(arrayList);
    }

    public void setDefaultBackgroundOptionalConfigList(ArrayList<BackgroundOptionalConfig> arrayList) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setDefaultBackgroundOptionalConfigList mWatchFaceThemeAlbumInfo is null");
        } else {
            latonaWatchFaceThemeAlbumInfo.setDefaultBackgroundOptionalConfigList(arrayList);
        }
    }

    public List<BackgroundOptionalConfig> getDefaultBackgroundOptionalConfigList() {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "getDefaultBackgroundOptionalConfigList mWatchFaceThemeAlbumInfo is null");
            return null;
        }
        return latonaWatchFaceThemeAlbumInfo.getDefaultBackgroundOptionalConfigList();
    }

    public void setBackgroundOptionalConfigList(ArrayList<BackgroundOptionalConfig> arrayList) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setBackgroundOptionalConfigList mWatchFaceThemeAlbumInfo is null");
            return;
        }
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        latonaWatchFaceThemeAlbumInfo.setBackgroundOptionalConfigList(arrayList);
    }

    public List<BackgroundOptionalConfig> getBackgroundOptionalConfigList() {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "getBackgroundOptionalConfigList mWatchFaceThemeAlbumInfo is null");
            return null;
        }
        return latonaWatchFaceThemeAlbumInfo.getBackgroundOptionalConfigList();
    }

    private void setBackgroundResActives(List<BackgroundOptionalConfig> list) {
        if (ArrayUtils.isEmpty(list)) {
            HwLog.w(TAG, "setBackgroundResActives dataOptionalConfigList is null");
            return;
        }
        this.mBackgroundResActives = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.mBackgroundResActives[i] = list.get(i).getResPreview();
        }
    }

    private void setHandStyleConfig(HandStyles handStyles) {
        setHandStyleOption(handStyles != null);
        if (handStyles == null) {
            HwLog.w(TAG, "setHandStyleConfig failed latonaDatas is null");
            return;
        }
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setHandStyleConfig failed mWatchFaceThemeAlbumInfos is null");
            return;
        }
        latonaWatchFaceThemeAlbumInfo.setHandStyleIndex(handStyles.getSelectedIndex());
        List<HandStyle> backgroundList = handStyles.getBackgroundList();
        ArrayList arrayList = new ArrayList(20);
        if (backgroundList != null && !backgroundList.isEmpty()) {
            for (HandStyle handStyle : backgroundList) {
                HandStyleOptionalConfig handStyleOptionalConfig = new HandStyleOptionalConfig();
                handStyleOptionalConfig.setIndex(handStyle.getIndex());
                handStyleOptionalConfig.setResPreview(handStyle.getResPreview());
                arrayList.add(handStyleOptionalConfig);
            }
        }
        this.mWatchFaceThemeAlbumInfo.setHandStyleResPathList(arrayList);
    }

    public void combineStyleResourcePath(String str) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "combineStyleResourcePath failed mWatchFaceThemeAlbumInfo is null");
            return;
        }
        List<StyleOptionalConfig> styleResPathList = latonaWatchFaceThemeAlbumInfo.getStyleResPathList();
        for (int i = 0; i < styleResPathList.size(); i++) {
            StyleOptionalConfig styleOptionalConfig = styleResPathList.get(i);
            if (styleOptionalConfig != null) {
                styleOptionalConfig.setResPreview(str + "/" + styleOptionalConfig.getResPreview());
            }
            styleResPathList.set(i, styleOptionalConfig);
        }
    }

    public void combineHandsStyleResourcePath(String str) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "combineHandsStyleResourcePath failed mWatchFaceThemeAlbumInfo is null");
            return;
        }
        List<HandStyleOptionalConfig> handStyleResPathList = latonaWatchFaceThemeAlbumInfo.getHandStyleResPathList();
        for (int i = 0; i < handStyleResPathList.size(); i++) {
            HandStyleOptionalConfig handStyleOptionalConfig = handStyleResPathList.get(i);
            if (handStyleOptionalConfig != null) {
                handStyleOptionalConfig.setResPreview(str + "/" + handStyleOptionalConfig.getResPreview());
            }
            handStyleResPathList.set(i, handStyleOptionalConfig);
        }
    }

    public void combineDataResourcePath(String str) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "combineDataResourcePath failed mWatchFaceThemeAlbumInfo is null");
            return;
        }
        List<DataOptionalConfig> dataResPathList = latonaWatchFaceThemeAlbumInfo.getDataResPathList();
        for (int i = 0; i < dataResPathList.size(); i++) {
            DataOptionalConfig dataOptionalConfig = dataResPathList.get(i);
            if (dataOptionalConfig != null) {
                if (TextUtils.isEmpty(dataOptionalConfig.getResPreview())) {
                    dataOptionalConfig.setResPreview("");
                } else {
                    dataOptionalConfig.setResPreview(str + "/" + dataOptionalConfig.getResPreview());
                }
            }
            dataResPathList.set(i, dataOptionalConfig);
        }
    }

    public void setLatonaThemeAlbumInfo(String str, String str2, String str3, int i, String str4, boolean z, String str5, String str6) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setLatonaThemeAlbumInfo() mWatchFaceThemeAlbumInfo is null");
            return;
        }
        latonaWatchFaceThemeAlbumInfo.setPositionIndex(str);
        this.mWatchFaceThemeAlbumInfo.setStyleIndex(str2);
        this.mWatchFaceThemeAlbumInfo.setDataIndex(str3);
        this.mWatchFaceThemeAlbumInfo.setBackgroundType(i);
        this.mWatchFaceThemeAlbumInfo.setSupportMaxBgAmount(str4);
        this.mWatchFaceThemeAlbumInfo.setIsSupportIntellectColor(z);
        this.mWatchFaceThemeAlbumInfo.setPortraitMode(str5);
        this.mWatchFaceThemeAlbumInfo.setPortPositionIndex(str6);
    }

    public void setKaleidoscopeInfo(String str, String str2, String str3, int i, String str4) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setKaleidoscopeInfo() mWatchFaceThemeAlbumInfo is null");
            return;
        }
        latonaWatchFaceThemeAlbumInfo.setBackgroundIndex(str);
        this.mWatchFaceThemeAlbumInfo.setHandStyleIndex(str2);
        this.mWatchFaceThemeAlbumInfo.setStyleIndex(str3);
        this.mWatchFaceThemeAlbumInfo.setBackgroundType(i);
        this.mWatchFaceThemeAlbumInfo.setSupportMaxBgAmount(str4);
    }

    public void setWearInfo(int i, int i2, int i3, long j, long j2, long j3) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setWearInfo() mWatchFaceThemeAlbumInfo is null");
            return;
        }
        latonaWatchFaceThemeAlbumInfo.setStyleCount(i);
        this.mWatchFaceThemeAlbumInfo.setMaxStyleNum(i2);
        this.mWatchFaceThemeAlbumInfo.setWearStyleType(i3);
        this.mWatchFaceThemeAlbumInfo.setWearTypeCapability(j);
        this.mWatchFaceThemeAlbumInfo.setCurStyleIndex(j2);
        this.mWatchFaceThemeAlbumInfo.setClockTypeCapability(j3);
    }

    private void setBackgroundResourceActive(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.w(TAG, "setBackgroundResourceActive failed backgroundResourceActive is empty");
        } else {
            this.mBackgroundResActives = str.split(",");
        }
    }

    public String[] getBackgroundResourceActive() {
        if (this.mBackgroundResActives != null) {
            HwLog.i(TAG, "getBackgroundResActive mBackgroundResActives:" + Arrays.toString(this.mBackgroundResActives));
            return (String[]) this.mBackgroundResActives.clone();
        }
        HwLog.w(TAG, "getBackgroundResActive failed");
        return new String[0];
    }

    public List<String> getLatonaBackgroundPathList() {
        return this.mWatchFaceThemeAlbumInfo.getWatchFaceBgPathList();
    }

    public void setLatonaBackgroundPathList(List<String> list) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setLatonaBackgroundPathList mWatchFaceThemeAlbumInfo is null");
        } else {
            latonaWatchFaceThemeAlbumInfo.setWatchFaceBgPathList(list);
        }
    }

    public void setLatonaBackgroundNameList(ArrayList<String> arrayList) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setLatonaBackgroundNameList mWatchFaceThemeAlbumInfo is null");
        } else {
            latonaWatchFaceThemeAlbumInfo.setBackgroundList(arrayList);
        }
    }

    public void setPortModeList(ArrayList<String> arrayList) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setLatonaBackgroundNameList mWatchFaceThemeAlbumInfo is null");
        } else {
            latonaWatchFaceThemeAlbumInfo.setmPortraitModeList(arrayList);
        }
    }

    public void setPortPositionIndeList(ArrayList<String> arrayList) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setLatonaBackgroundNameList mWatchFaceThemeAlbumInfo is null");
        } else {
            latonaWatchFaceThemeAlbumInfo.setmPortPositionIndexList(arrayList);
        }
    }

    public ArrayList<String> getLatonaBackgroundNameList() {
        return this.mWatchFaceThemeAlbumInfo.getBackgroundList();
    }

    private void setStyleOption(boolean z) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setStyleOption failed mWatchFaceThemeAlbumInfo is null");
        } else {
            latonaWatchFaceThemeAlbumInfo.setSupportStyleOption(z);
        }
    }

    private void setDataOption(boolean z) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setDataOption failed mWatchFaceThemeAlbumInfo is null");
        } else {
            latonaWatchFaceThemeAlbumInfo.setIsSupportDataOption(z);
        }
    }

    private void setPositionOption(boolean z) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setPositionOption failed mWatchFaceThemeAlbumInfo is null");
        } else {
            latonaWatchFaceThemeAlbumInfo.setSupportPositionOption(z);
        }
    }

    private void setBackgroundOption(boolean z) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setBackgroundOption failed mWatchFaceThemeAlbumInfo is null");
        } else {
            latonaWatchFaceThemeAlbumInfo.setSupportBackgroundOption(z);
        }
    }

    private void setHandStyleOption(boolean z) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setHandStyleOption failed mWatchFaceThemeAlbumInfo is null");
        } else {
            latonaWatchFaceThemeAlbumInfo.setSupportHandStyleOption(z);
        }
    }

    public String transmitLatonaWatchFaceInfo() {
        if (this.mWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "transmitWatchFaceInfo failed mWatchFaceThemeAlbumInfo is null");
            return "";
        }
        return new Gson().toJson(this.mWatchFaceThemeAlbumInfo);
    }

    public void setLatonaBackgroundSavedPath(String str) {
        this.mPath = str;
    }

    public String getLatonaBackgroundSavedPath() {
        return this.mPath;
    }

    public void setWatchFaceSize(int i, int i2) {
        this.mWatchFaceWidth = i;
        this.mWatchFaceHeight = i2;
    }

    public int getWatchFaceWidth() {
        return this.mWatchFaceWidth;
    }

    public int getWatchFaceHeight() {
        return this.mWatchFaceHeight;
    }

    public void setTempImagePaths(String str) {
        this.mTempImageList.add(str);
    }

    public void deleteClippedFile() {
        if (this.mTempImageList.isEmpty()) {
            return;
        }
        Iterator<String> it = this.mTempImageList.iterator();
        while (it.hasNext()) {
            File file = new File(CommonUtils.filterFilePath(it.next()));
            if (file.exists()) {
                HwLog.d(TAG, "deleteClippedFile isClippedFileDelete:" + file.delete());
            }
        }
        this.mTempImageList.clear();
    }

    public int isSupportPositionOption() {
        return this.mWatchFaceThemeAlbumInfo.isSupportPositionOption() ? 1 : 0;
    }

    public int isSupportStyleOption() {
        return this.mWatchFaceThemeAlbumInfo.isSupportStyleOption() ? 1 : 0;
    }

    public int isSupportDataOption() {
        return this.mWatchFaceThemeAlbumInfo.isSupportDataOption() ? 1 : 0;
    }

    public int isSupportBackgroundOption() {
        return this.mWatchFaceThemeAlbumInfo.isSupportBackgroundOption() ? 1 : 0;
    }

    public int isSupportHandStyleOption() {
        return this.mWatchFaceThemeAlbumInfo.isSupportHandStyleOption() ? 1 : 0;
    }

    public void setDefaultBackground(String str, String str2) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setDefaultBackground but model is null");
        } else {
            latonaWatchFaceThemeAlbumInfo.setDefaultBackground(str);
            this.mWatchFaceThemeAlbumInfo.setDefaultBackgroundPath(str2);
        }
    }

    public void setVideoStructs(ArrayList<VideoStruct> arrayList) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setVideoStructs() mWatchFaceThemeAlbumInfo is null.");
            return;
        }
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        latonaWatchFaceThemeAlbumInfo.setVideoStructList(arrayList);
    }

    public ArrayList<VideoStruct> getVideoStructs() {
        return this.mWatchFaceThemeAlbumInfo.getVideoStructList();
    }

    public void setDefaultVideoStructs(ArrayList<VideoStruct> arrayList) {
        LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w(TAG, "setDefaultVideoStructs() mWatchFaceThemeAlbumInfo is null.");
        } else {
            latonaWatchFaceThemeAlbumInfo.setDefaultVideoStructList(arrayList);
        }
    }

    public ArrayList<VideoStruct> getDefaultVideoStructs() {
        return this.mWatchFaceThemeAlbumInfo.getDefaultVideoStructList();
    }
}
