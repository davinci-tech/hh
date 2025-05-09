package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public class CustomConfig {
    private Integer colorSelectedIndex;
    private Integer containerCount;
    private String customColorValue;
    private CustomStyle customStyle;
    private String marketId;
    private List<Integer> widgetSelectedOptions;

    /* loaded from: classes5.dex */
    public static class CustomStyle {
        private Integer bkgSelectedIndex;
        private Integer handSelectedIndex;
        private Integer scaleSelectedIndex;
        private Integer styleSelectedIndex;
        private Integer symbolSelectedIndex;
        private Integer timeSelectedIndex;
        private Integer typefaceSelectedIndex;

        public String toString() {
            return "CustomStyle{styleSelectedIndex=" + this.styleSelectedIndex + ", bkgSelectedIndex=" + this.bkgSelectedIndex + ", scaleSelectedIndex=" + this.scaleSelectedIndex + ", symbolSelectedIndex=" + this.symbolSelectedIndex + ", typefaceSelectedIndex=" + this.typefaceSelectedIndex + ", timeSelectedIndex=" + this.timeSelectedIndex + ", handSelectedIndex=" + this.handSelectedIndex + '}';
        }

        public Integer getStyleSelectedIndex() {
            return this.styleSelectedIndex;
        }

        public void setStyleSelectedIndex(Integer num) {
            this.styleSelectedIndex = num;
        }

        public Integer getBkgSelectedIndex() {
            return this.bkgSelectedIndex;
        }

        public void setBkgSelectedIndex(Integer num) {
            this.bkgSelectedIndex = num;
        }

        public Integer getScaleSelectedIndex() {
            return this.scaleSelectedIndex;
        }

        public void setScaleSelectedIndex(Integer num) {
            this.scaleSelectedIndex = num;
        }

        public Integer getSymbolSelectedIndex() {
            return this.symbolSelectedIndex;
        }

        public void setSymbolSelectedIndex(Integer num) {
            this.symbolSelectedIndex = num;
        }

        public Integer getTypefaceSelectedIndex() {
            return this.typefaceSelectedIndex;
        }

        public void setTypefaceSelectedIndex(Integer num) {
            this.typefaceSelectedIndex = num;
        }

        public Integer getTimeSelectedIndex() {
            return this.timeSelectedIndex;
        }

        public void setTimeSelectedIndex(Integer num) {
            this.timeSelectedIndex = num;
        }

        public Integer getHandSelectedIndex() {
            return this.handSelectedIndex;
        }

        public void setHandSelectedIndex(Integer num) {
            this.handSelectedIndex = num;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CustomStyle)) {
                return false;
            }
            CustomStyle customStyle = (CustomStyle) obj;
            return Objects.equals(this.styleSelectedIndex, customStyle.styleSelectedIndex) && Objects.equals(this.bkgSelectedIndex, customStyle.bkgSelectedIndex) && Objects.equals(this.scaleSelectedIndex, customStyle.scaleSelectedIndex) && Objects.equals(this.symbolSelectedIndex, customStyle.symbolSelectedIndex) && Objects.equals(this.typefaceSelectedIndex, customStyle.typefaceSelectedIndex) && Objects.equals(this.timeSelectedIndex, customStyle.timeSelectedIndex) && Objects.equals(this.handSelectedIndex, customStyle.handSelectedIndex);
        }

        public int hashCode() {
            return Objects.hash(this.styleSelectedIndex, this.bkgSelectedIndex, this.scaleSelectedIndex, this.symbolSelectedIndex, this.typefaceSelectedIndex, this.timeSelectedIndex, this.handSelectedIndex);
        }
    }

    public String toString() {
        return "CustomConfig{marketId='" + this.marketId + "', customStyle=" + this.customStyle + ", colorSelectedIndex=" + this.colorSelectedIndex + ", customColorValue='" + this.customColorValue + "', containerCount=" + this.containerCount + ", widgetSelectedOptions=" + this.widgetSelectedOptions + '}';
    }

    public String getMarketId() {
        return this.marketId;
    }

    public void setMarketId(String str) {
        this.marketId = str;
    }

    public CustomStyle getCustomStyle() {
        return this.customStyle;
    }

    public void setCustomStyle(CustomStyle customStyle) {
        this.customStyle = customStyle;
    }

    public Integer getColorSelectedIndex() {
        return this.colorSelectedIndex;
    }

    public void setColorSelectedIndex(Integer num) {
        this.colorSelectedIndex = num;
    }

    public String getCustomColorValue() {
        return this.customColorValue;
    }

    public void setCustomColorValue(String str) {
        this.customColorValue = str;
    }

    public Integer getContainerCount() {
        return this.containerCount;
    }

    public void setContainerCount(Integer num) {
        this.containerCount = num;
    }

    public List<Integer> getWidgetSelectedOptions() {
        return this.widgetSelectedOptions;
    }

    public void setWidgetSelectedOptions(List<Integer> list) {
        this.widgetSelectedOptions = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomConfig)) {
            return false;
        }
        CustomConfig customConfig = (CustomConfig) obj;
        return Objects.equals(this.marketId, customConfig.marketId) && Objects.equals(this.customStyle, customConfig.customStyle) && Objects.equals(this.colorSelectedIndex, customConfig.colorSelectedIndex) && Objects.equals(this.customColorValue, customConfig.customColorValue) && Objects.equals(this.containerCount, customConfig.containerCount) && Objects.equals(this.widgetSelectedOptions, customConfig.widgetSelectedOptions);
    }

    public int hashCode() {
        return Objects.hash(this.marketId, this.customStyle, this.colorSelectedIndex, this.customColorValue, this.containerCount, this.widgetSelectedOptions);
    }
}
