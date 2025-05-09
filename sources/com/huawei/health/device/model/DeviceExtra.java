package com.huawei.health.device.model;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class DeviceExtra {
    private List<DeviceData> fittings = new ArrayList();
    private List<DeviceData> attached = new ArrayList();

    public List<DeviceData> getFittings() {
        return this.fittings;
    }

    public void setFittings(List<DeviceData> list) {
        this.fittings = list;
    }

    public List<DeviceData> getAttached() {
        return this.attached;
    }

    public void setAttached(List<DeviceData> list) {
        this.attached = list;
    }

    public static class DeviceData {
        private String kind;
        private String productId;
        private String uniqueId;

        public DeviceData(String str, String str2, String str3) {
            this.kind = str;
            this.productId = str2;
            this.uniqueId = str3;
        }

        public String getKind() {
            return this.kind;
        }

        public void setKind(String str) {
            this.kind = str;
        }

        public String getProductId() {
            return this.productId;
        }

        public void setProductId(String str) {
            this.productId = str;
        }

        public String getUniqueId() {
            return this.uniqueId;
        }

        public void setUniqueId(String str) {
            this.uniqueId = str;
        }
    }
}
