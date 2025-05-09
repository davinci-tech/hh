package com.huawei.hianalytics.visual.view.model.config;

import java.util.List;

/* loaded from: classes4.dex */
public class VisualConfig {
    public VisualEvent event;
    public boolean isH5;
    public String os;
    public String packageName;
    public List<VisualProperty> property;
    public String version;

    public static class VisualEvent {
        public String eventName;
        public String eventType;
        public boolean isCollect;
        public boolean limitViewContent;
        public boolean limitViewPosition;
        public String pageName;
        public String url;
        public String viewContent;
        public String viewId;
        public String viewPath;
        public String viewPosition;
    }

    public static class VisualProperty {
        public String name;
        public String pageName;
        public String propertyType;
        public String regular;
        public String type;
        public String viewContent;
        public String viewId;
        public String viewPath;
        public String viewPosition;

        public String getPageName() {
            String str = this.pageName;
            if (str == null || !str.contains("_")) {
                return this.pageName;
            }
            String str2 = this.pageName;
            return str2.substring(0, str2.indexOf("_"));
        }

        public void setPageName(String str) {
            this.pageName = str;
        }
    }
}
