package com.huawei.ui.main.stories.recommendcloud.data;

import java.util.List;

/* loaded from: classes9.dex */
public class SleepServiceData {
    private SleepZoneConfigBean sleepZoneConfig;

    public SleepZoneConfigBean getSleepZoneConfig() {
        return this.sleepZoneConfig;
    }

    public static class SleepZoneConfigBean {
        private List<InfoListBean> infoList;
        private List<ServiceListBean> serviceList;

        public static class ServiceListBean {
            private String description;
            private String img;
            private String model;
            private String name;
            private String style;
            private String type;
            private String url;
        }

        public List<InfoListBean> getInfoList() {
            return this.infoList;
        }

        public static class InfoListBean {
            private String img;
            private String name;
            private String time;
            private String url;

            public String getUrl() {
                return this.url;
            }
        }
    }
}
