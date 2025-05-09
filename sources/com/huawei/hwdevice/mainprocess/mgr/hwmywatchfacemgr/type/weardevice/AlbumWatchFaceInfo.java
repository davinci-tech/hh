package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class AlbumWatchFaceInfo {

    @SerializedName("backgroundImageNum")
    private int backgroundImageNum;

    @SerializedName("backgroundImageOption")
    private int backgroundImageOption;

    @SerializedName("backgroundImageType")
    private int backgroundImageType;

    @SerializedName("canIntellectColor")
    public int canIntellectColor;

    @SerializedName("carouselMode")
    private int carouselMode;
    private int isUseGaussColor;

    @SerializedName("maxBackgroundImages")
    private int maxBackgroundImages;

    @SerializedName("optionSelectedList")
    private List<Integer> optionSelectedList;

    @SerializedName("positionIndex")
    private int positionIndex;

    @SerializedName("styleApplyAll")
    private int styleApplyAll;

    @SerializedName("styleIndex")
    private int styleIndex;

    @SerializedName("valueTypeIndex")
    private int valueTypeIndex;

    @SerializedName("wristSwitch")
    private int wristSwitch;

    @SerializedName("backgroundList")
    private ArrayList<BackgroundBean> backgroundList = new ArrayList<>(20);

    @SerializedName("dataList")
    private ArrayList<WatchFacePhotoCustom> dataList = new ArrayList<>(20);

    public String toString() {
        return "AlbumWatchFaceInfo{backgroundList=" + this.backgroundList + ", optionSelectedList=" + this.optionSelectedList + '}';
    }

    public List<Integer> getOptionSelectedList() {
        return this.optionSelectedList;
    }

    public void setOptionSelectedList(List<Integer> list) {
        this.optionSelectedList = list;
    }

    public int getIsUseGaussColor() {
        return this.isUseGaussColor;
    }

    public void setIsUseGaussColor(int i) {
        this.isUseGaussColor = i;
    }

    public int getCanIntellectColor() {
        return this.canIntellectColor;
    }

    public void setCanIntellectColor(int i) {
        this.canIntellectColor = i;
    }

    public int getBackgroundImageNum() {
        return this.backgroundImageNum;
    }

    public void setBackgroundImageNum(int i) {
        this.backgroundImageNum = i;
    }

    public int getMaxBackgroundImages() {
        return this.maxBackgroundImages;
    }

    public void setMaxBackgroundImages(int i) {
        this.maxBackgroundImages = i;
    }

    public int getBackgroundImageType() {
        return this.backgroundImageType;
    }

    public void setBackgroundImageType(int i) {
        this.backgroundImageType = i;
    }

    public ArrayList<BackgroundBean> getBackgroundList() {
        return this.backgroundList;
    }

    public void setBackgroundList(ArrayList<BackgroundBean> arrayList) {
        this.backgroundList = arrayList;
    }

    public int getPositionIndex() {
        return this.positionIndex;
    }

    public void setPositionIndex(int i) {
        this.positionIndex = i;
    }

    public int getStyleIndex() {
        return this.styleIndex;
    }

    public void setStyleIndex(int i) {
        this.styleIndex = i;
    }

    public int getValueTypeIndex() {
        return this.valueTypeIndex;
    }

    public void setValueTypeIndex(int i) {
        this.valueTypeIndex = i;
    }

    public int getBackgroundImageOption() {
        return this.backgroundImageOption;
    }

    public void setBackgroundImageOption(int i) {
        this.backgroundImageOption = i;
    }

    public int getWristSwitch() {
        return this.wristSwitch;
    }

    public void setWristSwitch(int i) {
        this.wristSwitch = i;
    }

    public int getStyleApplyAll() {
        return this.styleApplyAll;
    }

    public void setStyleApplyAll(int i) {
        this.styleApplyAll = i;
    }

    public ArrayList<WatchFacePhotoCustom> getDataList() {
        return this.dataList;
    }

    public void setDataList(ArrayList<WatchFacePhotoCustom> arrayList) {
        this.dataList = arrayList;
    }

    public int getCarouselMode() {
        return this.carouselMode;
    }

    public void setCarouselMode(int i) {
        this.carouselMode = i;
    }

    public static class BackgroundBean {

        @SerializedName("backgroundColor")
        private String backgroundColor;

        @SerializedName("backgroundIndex")
        private int backgroundIndex;

        @SerializedName("backgroundName")
        private String backgroundName;

        @SerializedName("fontOpa")
        private int fontOpa;

        @SerializedName("gaussBlurRadius")
        private int gaussBlurRadius;

        @SerializedName("gaussColorBrightness")
        private int gaussColorBrightness;

        @SerializedName("gaussColorSaturation")
        private int gaussColorSaturation;

        @SerializedName("portPositionIndex")
        private int portPositionIndex;

        @SerializedName("portraitMode")
        private int portraitMode;

        @SerializedName("smartFontIndex")
        private int smartFontIndex;

        @SerializedName("smartPosIndex")
        private int smartPosIndex;

        @SerializedName("smartTimeIndex")
        private int smartTimeIndex;

        @SerializedName("timeColor")
        private String timeColor;

        @SerializedName("timeStyleIndex")
        private int timeStyleIndex;

        public String toString() {
            return "BackgroundBean{smartFontIndex=" + this.smartFontIndex + ", gaussColorSaturation=" + this.gaussColorSaturation + ", gaussColorBrightness=" + this.gaussColorBrightness + ", gaussBlurRadius=" + this.gaussBlurRadius + ", fontOpa=" + this.fontOpa + '}';
        }

        public int getSmartPosIndex() {
            return this.smartPosIndex;
        }

        public void setSmartPosIndex(int i) {
            this.smartPosIndex = i;
        }

        public int getSmartTimeIndex() {
            return this.smartTimeIndex;
        }

        public void setSmartTimeIndex(int i) {
            this.smartTimeIndex = i;
        }

        public int getSmartFontIndex() {
            return this.smartFontIndex;
        }

        public void setSmartFontIndex(int i) {
            this.smartFontIndex = i;
        }

        public int getGaussColorSaturation() {
            return this.gaussColorSaturation;
        }

        public void setGaussColorSaturation(int i) {
            this.gaussColorSaturation = i;
        }

        public int getGaussColorBrightness() {
            return this.gaussColorBrightness;
        }

        public void setGaussColorBrightness(int i) {
            this.gaussColorBrightness = i;
        }

        public int getGaussBlurRadius() {
            return this.gaussBlurRadius;
        }

        public void setGaussBlurRadius(int i) {
            this.gaussBlurRadius = i;
        }

        public int getFontOpa() {
            return this.fontOpa;
        }

        public void setFontOpa(int i) {
            this.fontOpa = i;
        }

        public int getBackgroundIndex() {
            return this.backgroundIndex;
        }

        public void setBackgroundIndex(int i) {
            this.backgroundIndex = i;
        }

        public String getBackgroundName() {
            return this.backgroundName;
        }

        public void setBackgroundName(String str) {
            this.backgroundName = str;
        }

        public int getPortPositionIndex() {
            return this.portPositionIndex;
        }

        public void setPortPositionIndex(int i) {
            this.portPositionIndex = i;
        }

        public int getPortraitMode() {
            return this.portraitMode;
        }

        public void setPortraitMode(int i) {
            this.portraitMode = i;
        }

        public int getTimeStyleIndex() {
            return this.timeStyleIndex;
        }

        public void setTimeStyleIndex(int i) {
            this.timeStyleIndex = i;
        }

        public String getTimeColor() {
            return this.timeColor;
        }

        public void setTimeColor(String str) {
            this.timeColor = str;
        }

        public String getBackgroundColor() {
            return this.backgroundColor;
        }

        public void setBackgroundColor(String str) {
            this.backgroundColor = str;
        }
    }

    public static class WatchFacePhotoCustom {

        @SerializedName("containerIndex")
        private int containerIndex;

        @SerializedName("optionIndex")
        private int optionIndex;

        public int getContainerIndex() {
            return this.containerIndex;
        }

        public void setContainerIndex(int i) {
            this.containerIndex = i;
        }

        public int getOptionIndex() {
            return this.optionIndex;
        }

        public void setOptionIndex(int i) {
            this.optionIndex = i;
        }
    }
}
