package com.huawei.watchface.mvp.model.latona.provider;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.mvp.model.datatype.TimePositionRect;
import com.huawei.watchface.mvp.model.datatype.WatchFaceThemeAlbumInfo;
import com.huawei.watchface.mvp.model.latona.WatchFaceThemeElement;
import com.huawei.watchface.mvp.model.latona.WatchFaceThemeElementConfig;
import com.huawei.watchface.mvp.model.latona.WatchFaceThemeLayer;
import com.huawei.watchface.mvp.model.latona.WatchFaceThemePosition;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class WatchFaceProvider {
    public static final String BACKGROUND_LABEL = "background";
    private static final String COMMA = ",";
    public static final String DATE_LABEL = "date";
    private static final int FONTNAME_SPLIT_INDEX = 4;
    private static final Object LOCK = new Object();
    public static final String POSITION_LABEL_U = "U";
    private static final int POSITION_OPTIONS_LENGTH = 4;
    public static final String SVG_SUFFIX = ".svg";
    public static final String SVG_TTF = ".ttf";
    public static final String TAG = "WatchFaceProvider";
    public static final String TIME_LABEL = "time";
    private static WatchFaceProvider sWatchFaceProvider;
    private String mAmbientRes;
    private String mWatchFacePackageBgPath;
    private List<String> mTempImageList = new ArrayList(10);
    private List<TimePositionRect> mTimePositionRectList = new ArrayList(10);
    private WatchFaceThemeAlbumInfo mWatchFaceThemeAlbumInfo = new WatchFaceThemeAlbumInfo();

    public static WatchFaceProvider getInstance(Context context) {
        WatchFaceProvider watchFaceProvider;
        synchronized (LOCK) {
            if (sWatchFaceProvider == null) {
                sWatchFaceProvider = new WatchFaceProvider();
            }
            watchFaceProvider = sWatchFaceProvider;
        }
        return watchFaceProvider;
    }

    public void resetWatchFaceConfig(ElementsProvider elementsProvider) {
        if (elementsProvider == null) {
            HwLog.i(TAG, "resetWatchFaceConfig failed, elementsProvider is null");
            return;
        }
        resetWatchFaceBgFiles();
        List<WatchFaceThemeElement> resetElements = resetElements(elementsProvider);
        elementsProvider.setConfig(resetConfig(elementsProvider));
        elementsProvider.setElements(resetElements);
        elementsProvider.saveProviders();
    }

    public void resetWatchFaceBgFiles() {
        if (this.mWatchFaceThemeAlbumInfo == null) {
            HwLog.i(TAG, "resetWatchFaceBgFiles failed, watchFaceThemeAlbumInfo is null");
            return;
        }
        File file = new File(getWatchFacePackageBgPath());
        if (!file.exists()) {
            HwLog.i(TAG, "resetWatchFaceBgFiles failed, watchFacePackageBgPath doesn't exist");
        }
        compareWatchFaceBgFiles(this.mWatchFaceThemeAlbumInfo.getBgPathList(), file.list());
    }

    private void compareWatchFaceBgFiles(List<String> list, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            HwLog.i(TAG, "compareWatchFaceBgFiles failed, watchFacePackageBgPathArray is null or not enough length");
            return;
        }
        if (list == null || list.size() == 0) {
            HwLog.i(TAG, "compareWatchFaceBgFiles failed, bgPathList is null or not enough length");
            return;
        }
        HashMap hashMap = new HashMap(10);
        for (String str : strArr) {
            if (!str.contains(getAmbientRes())) {
                hashMap.put(getWatchFacePackageBgPath() + "/" + str, str);
            }
        }
        for (String str2 : list) {
            if (hashMap.containsKey(str2)) {
                hashMap.remove(str2);
            }
        }
        HwLog.i(TAG, "compareWatchFaceBgFiles oldPathMap.size():" + hashMap.size());
        if (hashMap.size() > 0) {
            Iterator it = hashMap.keySet().iterator();
            while (it.hasNext()) {
                File file = new File((String) it.next());
                if (file.exists()) {
                    HwLog.i(TAG, "isFileDeleteOrNot:" + file.delete());
                }
            }
        }
    }

    public List<WatchFaceThemeElement> resetElements(ElementsProvider elementsProvider) {
        if (elementsProvider == null) {
            HwLog.i(TAG, "resetElements failed, elementsProvider is null");
            return null;
        }
        List<WatchFaceThemeElement> elements = elementsProvider.getElements();
        if (elements == null || elements.size() == 0) {
            HwLog.i(TAG, "resetElements failed, watchFaceThemeElements is null or not enough length");
            return null;
        }
        int i = 0;
        for (WatchFaceThemeElement watchFaceThemeElement : elements) {
            List<WatchFaceThemeLayer> watchFaceThemeLayerList = watchFaceThemeElement.getWatchFaceThemeLayerList();
            resetLayers(watchFaceThemeLayerList, watchFaceThemeElement.getLabel());
            watchFaceThemeElement.setWatchFaceThemeLayerList(watchFaceThemeLayerList);
            elements.set(i, watchFaceThemeElement);
            i++;
        }
        return elements;
    }

    public WatchFaceThemeElementConfig resetConfig(ElementsProvider elementsProvider) {
        WatchFaceThemeElementConfig config = elementsProvider.getConfig();
        if (config == null) {
            HwLog.i(TAG, "resetConfig, watchFaceThemeElementConfig is null");
            return null;
        }
        List<WatchFaceThemePosition> watchFaceThemePositionList = config.getWatchFaceThemePositionList();
        if (watchFaceThemePositionList == null || watchFaceThemePositionList.size() == 0) {
            HwLog.i(TAG, "resetConfig, watchFaceThemePositions is null or not enough length");
            return null;
        }
        List<TimePositionRect> timePositionList = this.mWatchFaceThemeAlbumInfo.getTimePositionList();
        if (timePositionList == null || timePositionList.size() == 0) {
            HwLog.i(TAG, "resetConfig, timePositionList is null or not enough length");
            return null;
        }
        int i = 0;
        for (WatchFaceThemePosition watchFaceThemePosition : watchFaceThemePositionList) {
            watchFaceThemePosition.setPositionLabel(timePositionList.get(i).getPositionLabel());
            watchFaceThemePosition.setPositionRect(timePositionList.get(i).getPositionRect());
            watchFaceThemePositionList.set(i, watchFaceThemePosition);
            i++;
        }
        config.setWatchFaceThemePositionList(watchFaceThemePositionList);
        return config;
    }

    public void resetLayers(List<WatchFaceThemeLayer> list, String str) {
        if (this.mWatchFaceThemeAlbumInfo == null) {
            HwLog.i(TAG, "resetLayers failed, watchFaceThemeAlbumInfo is null");
            return;
        }
        if (list == null || list.size() == 0) {
            HwLog.i(TAG, "resetLayers failed, watchFaceThemeLayers is null or not enough length");
        } else if (isTimeLabel(str)) {
            resetTimeLayers(list);
        } else {
            resetOtherLayers(list, str);
        }
    }

    private void resetOtherLayers(List<WatchFaceThemeLayer> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            WatchFaceThemeLayer watchFaceThemeLayer = list.get(i);
            if (isDateLabel(str)) {
                HwLog.i(TAG, "dateLabel，isHasPositionOptionsProperty:" + watchFaceThemeLayer.isHasPositionOptionsProperty());
                if (watchFaceThemeLayer.isHasPositionOptionsProperty()) {
                    watchFaceThemeLayer.setTextPositionLabel(this.mWatchFaceThemeAlbumInfo.getDefaultTimePosLabel());
                    watchFaceThemeLayer.setTextPosition(getLayerPosition(watchFaceThemeLayer.getTextPositionOptions(), this.mWatchFaceThemeAlbumInfo.getDefaultTimePosLabel()));
                    HwLog.i(TAG, "dateLabel，textPosition:" + watchFaceThemeLayer.getTextPosition());
                }
            } else {
                List<String> bgPathList = this.mWatchFaceThemeAlbumInfo.getBgPathList();
                if (bgPathList == null || bgPathList.size() == 0) {
                    HwLog.i(TAG, "resetLayers failed, bgPathList is null or not enough length");
                    return;
                }
                StringBuilder sb = new StringBuilder(10);
                for (String str2 : bgPathList) {
                    sb.append(SafeString.substring(str2, str2.lastIndexOf("/") + 1, str2.length()) + ",");
                }
                sb.deleteCharAt(sb.length() - 1);
                watchFaceThemeLayer.setResActive(sb.toString());
                HwLog.i(TAG, "resetResActive:" + watchFaceThemeLayer.getResActive());
            }
            list.set(i, watchFaceThemeLayer);
        }
    }

    private void resetTimeLayers(List<WatchFaceThemeLayer> list) {
        if (list == null || list.size() == 0) {
            HwLog.i(TAG, "watchFaceThemeLayers is null or not enough length");
            return;
        }
        WatchFaceThemeLayer watchFaceThemeLayer = list.get(0);
        if (watchFaceThemeLayer == null) {
            HwLog.i(TAG, "resetTimeLayers parentWatchFaceThemeLayer is null");
            return;
        }
        if (watchFaceThemeLayer.getWatchFaceThemeLayerList() == null || watchFaceThemeLayer.getWatchFaceThemeLayerList().size() == 0) {
            HwLog.i(TAG, "resetTimeLayers parentWatchFaceThemeLayer.getWatchFaceThemeLayerList is null or not enough length");
            return;
        }
        List<WatchFaceThemeLayer> watchFaceThemeLayerList = watchFaceThemeLayer.getWatchFaceThemeLayerList();
        if (watchFaceThemeLayerList == null || watchFaceThemeLayerList.size() == 0) {
            return;
        }
        int i = 0;
        for (WatchFaceThemeLayer watchFaceThemeLayer2 : watchFaceThemeLayerList) {
            if (watchFaceThemeLayer2.isHasPositionOptionsProperty()) {
                watchFaceThemeLayer2.setTextPositionLabel(this.mWatchFaceThemeAlbumInfo.getDefaultTimePosLabel());
                watchFaceThemeLayer2.setTextPositionRelative(getLayerPosition(watchFaceThemeLayer2.getTextPositionOptions(), this.mWatchFaceThemeAlbumInfo.getDefaultTimePosLabel()));
                HwLog.i(TAG, "timeLabel, isHasPositionOptionsProperty:" + watchFaceThemeLayer2.getTextPositionRelative());
            }
            if (watchFaceThemeLayer2.isHasFontOptionsProperty()) {
                watchFaceThemeLayer2.setTextFont(getFontBySvgPath(this.mWatchFaceThemeAlbumInfo.getDefaultTimeTextFont()));
            }
            watchFaceThemeLayerList.set(i, watchFaceThemeLayer2);
            i++;
        }
        watchFaceThemeLayer.setWatchFaceThemeLayerList(watchFaceThemeLayerList);
        list.set(0, watchFaceThemeLayer);
    }

    private String getFontBySvgPath(String str) {
        int lastIndexOf;
        return (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf("/")) == -1) ? "" : SafeString.replace(SafeString.substring(str, lastIndexOf + 1, str.length()), SVG_SUFFIX, SVG_TTF);
    }

    private String getLayerPosition(String str, String str2) {
        String[] split = str.split(",");
        if (split.length == 4) {
            HwLog.i(TAG, "getLayerPosition failed, positionOption is null or not enough length");
            return "";
        }
        if (TextUtils.equals(str2, POSITION_LABEL_U)) {
            return split[0] + "," + split[1];
        }
        return split[2] + "," + split[3];
    }

    private boolean isTimeLabel(String str) {
        return TextUtils.equals(str, "time");
    }

    private boolean isDateLabel(String str) {
        return TextUtils.equals(str, "date");
    }

    public void parseWatchFaceConfig(ElementsProvider elementsProvider) {
        setWatchFaceDpi(elementsProvider.getDpi());
        parseTimeElement(elementsProvider);
        parseBackGroundElement(elementsProvider);
        parseConfig(elementsProvider);
    }

    private void parseBackGroundElement(ElementsProvider elementsProvider) {
        WatchFaceThemeElement element = elementsProvider.getElement(BACKGROUND_LABEL);
        if (element == null) {
            HwLog.i(TAG, "backgroundWatchFaceThemeElement is null, backgroundLabel");
            return;
        }
        List<WatchFaceThemeLayer> watchFaceThemeLayerList = element.getWatchFaceThemeLayerList();
        if (watchFaceThemeLayerList == null || watchFaceThemeLayerList.size() == 0) {
            HwLog.i(TAG, "parseBackGroundElement, watchFaceThemeLayerLists is null, backgroundLabel");
            return;
        }
        WatchFaceThemeLayer watchFaceThemeLayer = watchFaceThemeLayerList.get(0);
        HwLog.i(TAG, "parseBackGroundElement, getResAmbient:" + watchFaceThemeLayer.getResAmbient());
        setAmbientRes(watchFaceThemeLayer.getResAmbient());
    }

    private void parseConfig(ElementsProvider elementsProvider) {
        WatchFaceThemeElementConfig config = elementsProvider.getConfig();
        if (config == null) {
            HwLog.i(TAG, "parseConfig option, watchFaceThemeElementConfig is null");
            return;
        }
        List<WatchFaceThemePosition> watchFaceThemePositionList = config.getWatchFaceThemePositionList();
        if (watchFaceThemePositionList == null || watchFaceThemePositionList.size() == 0) {
            HwLog.i(TAG, "watchFaceThemePositions is null or not enough length");
            return;
        }
        this.mTimePositionRectList.clear();
        for (WatchFaceThemePosition watchFaceThemePosition : watchFaceThemePositionList) {
            TimePositionRect timePositionRect = new TimePositionRect();
            timePositionRect.setPositionLabel(watchFaceThemePosition.getPositionLabel());
            timePositionRect.setPositionRect(watchFaceThemePosition.getPositionRect());
            this.mTimePositionRectList.add(timePositionRect);
        }
        HwLog.i(TAG, "positionSize:" + this.mTimePositionRectList.size());
        setTimePosInfo(this.mTimePositionRectList);
    }

    private void parseTimeElement(ElementsProvider elementsProvider) {
        WatchFaceThemeElement element = elementsProvider.getElement("time");
        if (element == null) {
            HwLog.i(TAG, "timeWatchFaceThemeElement is null, timeLabel");
            return;
        }
        List<WatchFaceThemeLayer> watchFaceThemeLayerList = element.getWatchFaceThemeLayerList();
        if (watchFaceThemeLayerList == null || watchFaceThemeLayerList.size() == 0) {
            HwLog.i(TAG, "parentWatchFaceThemeLayerLists is null, timeLabel");
            return;
        }
        List<WatchFaceThemeLayer> watchFaceThemeLayerList2 = watchFaceThemeLayerList.get(0).getWatchFaceThemeLayerList();
        if (watchFaceThemeLayerList2 == null || watchFaceThemeLayerList2.size() == 0) {
            HwLog.i(TAG, "watchFaceThemeLayerLists is null, timeLabel");
            return;
        }
        WatchFaceThemeLayer watchFaceThemeLayer = watchFaceThemeLayerList2.get(0);
        setDefaultTimePosLabel(watchFaceThemeLayer.getTextPositionLabel());
        setTextAmbientColor(watchFaceThemeLayer.getTextAmbientColor());
        setTextFont(watchFaceThemeLayer.getTextFont());
    }

    public void setTimePosInfo(List<TimePositionRect> list) {
        WatchFaceThemeAlbumInfo watchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (watchFaceThemeAlbumInfo == null) {
            HwLog.i(TAG, "setTimePosInfo failed, watchFaceThemeAlbumInfo is null");
        } else {
            watchFaceThemeAlbumInfo.setTimePositionList(list);
        }
    }

    public void setDefaultTimePosLabel(String str) {
        WatchFaceThemeAlbumInfo watchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (watchFaceThemeAlbumInfo == null) {
            HwLog.i(TAG, "setDefaultTimePosLabel failed, watchFaceThemeAlbumInfo is null");
        } else {
            watchFaceThemeAlbumInfo.setDefaultTimePosLabel(str);
        }
    }

    public String getDpi() {
        return this.mWatchFaceThemeAlbumInfo.getDpi();
    }

    public void setSupportMaxBgAmount(String str) {
        WatchFaceThemeAlbumInfo watchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (watchFaceThemeAlbumInfo == null) {
            HwLog.i(TAG, "setSupportMaxBgAmount failed, watchFaceThemeAlbumInfo is null");
        } else {
            watchFaceThemeAlbumInfo.setSupportMaxBgAmount(str);
        }
    }

    public void setTextAmbientColor(String str) {
        WatchFaceThemeAlbumInfo watchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (watchFaceThemeAlbumInfo == null) {
            HwLog.i(TAG, "setTextAmbientColor failed, watchFaceThemeAlbumInfo is null");
        } else {
            watchFaceThemeAlbumInfo.setTextAmbientColor(str);
        }
    }

    public void setTextFont(String str) {
        WatchFaceThemeAlbumInfo watchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (watchFaceThemeAlbumInfo == null) {
            HwLog.i(TAG, "setTextFont failed, watchFaceThemeAlbumInfo is null");
        } else {
            watchFaceThemeAlbumInfo.setDefaultTimeTextFont(str);
        }
    }

    public void setWatchFaceBgPath(List<String> list) {
        WatchFaceThemeAlbumInfo watchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (watchFaceThemeAlbumInfo == null) {
            HwLog.i(TAG, "setWatchFaceBgPath failed, watchFaceThemeAlbumInfo is null");
        } else {
            watchFaceThemeAlbumInfo.setBgPathList(list);
        }
    }

    public void setWatchFaceSvgPath(List<String> list) {
        if (this.mWatchFaceThemeAlbumInfo == null) {
            HwLog.i(TAG, "setWatchFaceSvgPath failed, watchFaceThemeAlbumInfo is null");
        } else {
            filterSvgPath(list);
            this.mWatchFaceThemeAlbumInfo.setSvgPathList(list);
        }
    }

    public void filterSvgPath(List<String> list) {
        for (String str : list) {
            if (str.contains(getSvgPathByFont(this.mWatchFaceThemeAlbumInfo.getDefaultTimeTextFont()))) {
                this.mWatchFaceThemeAlbumInfo.setDefaultTimeTextFont(str);
            }
        }
    }

    private String getSvgPathByFont(String str) {
        return SafeString.substring(str, 0, str.length() - 4);
    }

    public void setWatchFaceDpi(String str) {
        WatchFaceThemeAlbumInfo watchFaceThemeAlbumInfo = this.mWatchFaceThemeAlbumInfo;
        if (watchFaceThemeAlbumInfo == null) {
            HwLog.i(TAG, "setWatchFaceDpi failed, watchFaceThemeAlbumInfo is null");
        } else {
            watchFaceThemeAlbumInfo.setDpi(str);
        }
    }

    public void setAmbientRes(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mAmbientRes = str;
    }

    public String getAmbientRes() {
        return this.mAmbientRes;
    }

    public String transmitWatchFaceInfo() {
        if (this.mWatchFaceThemeAlbumInfo == null) {
            HwLog.i(TAG, "transmitWatchFaceInfo failed, watchFaceThemeAlbumInfo is null");
            return "";
        }
        return new Gson().toJson(this.mWatchFaceThemeAlbumInfo);
    }

    public void saveWatchFaceThemeAlbumInfo(WatchFaceThemeAlbumInfo watchFaceThemeAlbumInfo) {
        this.mWatchFaceThemeAlbumInfo = watchFaceThemeAlbumInfo;
    }

    public void setWatchFacePackageBgPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mWatchFacePackageBgPath = str;
    }

    public String getWatchFacePackageBgPath() {
        return this.mWatchFacePackageBgPath;
    }

    public void setTempImagePaths(String str) {
        this.mTempImageList.add(str);
    }

    public void deleteClippedFile() {
        if (this.mTempImageList.size() > 0) {
            Iterator<String> it = this.mTempImageList.iterator();
            while (it.hasNext()) {
                File file = new File(CommonUtils.filterFilePath(it.next()));
                if (file.exists()) {
                    HwLog.i(TAG, "isClippedFileDelete:" + file.delete());
                }
            }
            this.mTempImageList.clear();
        }
    }
}
