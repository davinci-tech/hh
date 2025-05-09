package com.huawei.hwcloudmodel.hwwear.hag.model.body;

/* loaded from: classes5.dex */
public class EndpointBean {
    private String countryCode;
    private DeviceBean device;
    private String locale;
    private String roamingCountryCode;

    public void setLocale(String str) {
        this.locale = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setRoamingCountryCode(String str) {
        this.roamingCountryCode = str;
    }

    public void setDevice(DeviceBean deviceBean) {
        this.device = deviceBean;
    }

    public String toString() {
        return "EndpointBean{locale='" + this.locale + "', countryCode='" + this.countryCode + "', roamingCountryCode='" + this.roamingCountryCode + "', device=" + this.device + '}';
    }

    public static class DeviceBean {
        private String deviceId;
        private String deviceType;
        private LocationBean location;
        private String phoneType;
        private String prdVer;
        private String sysVer;
        private String timezone;

        public void setDeviceId(String str) {
            this.deviceId = str;
        }

        public void setTimezone(String str) {
            this.timezone = str;
        }

        public void setLocation(LocationBean locationBean) {
            this.location = locationBean;
        }

        public void setPrdVer(String str) {
            this.prdVer = str;
        }

        public void setSysVer(String str) {
            this.sysVer = str;
        }

        public void setPhoneType(String str) {
            this.phoneType = str;
        }

        public void setDeviceType(String str) {
            this.deviceType = str;
        }

        public String toString() {
            return "DeviceBean{deviceId='" + this.deviceId + "', timezone='" + this.timezone + "', location=" + this.location + ", prdVer='" + this.prdVer + "', sysVer='" + this.sysVer + "', phoneType='" + this.phoneType + "', deviceType='" + this.deviceType + "'}";
        }

        public static class LocationBean {
            private String latitude;
            private String locationSystem;
            private String longitude;

            public void setLocationSystem(String str) {
                this.locationSystem = str;
            }

            public void setLatitude(String str) {
                this.latitude = str;
            }

            public void setLongitude(String str) {
                this.longitude = str;
            }

            public String toString() {
                return "LocationBean{locationSystem='" + this.locationSystem + "', latitude='" + this.latitude + "', longitude='" + this.longitude + "'}";
            }
        }
    }
}
