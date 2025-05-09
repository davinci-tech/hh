package com.huawei.profile.coordinator.bean;

import java.util.List;

/* loaded from: classes6.dex */
public class CloudProfileBean {
    private String devId;
    private DevInfoBean devInfo;
    private String gatewayId;
    private String modifyTime;
    private List<ServicesBean> services;
    private String status;
    private String thirdPartyId;
    private String userId;

    public String getDevId() {
        return this.devId;
    }

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getGatewayId() {
        return this.gatewayId;
    }

    public void setGatewayId(String str) {
        this.gatewayId = str;
    }

    public String getThirdPartyId() {
        return this.thirdPartyId;
    }

    public void setThirdPartyId(String str) {
        this.thirdPartyId = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public DevInfoBean getDevInfo() {
        return this.devInfo;
    }

    public void setDevInfo(DevInfoBean devInfoBean) {
        this.devInfo = devInfoBean;
    }

    public List<ServicesBean> getServices() {
        return this.services;
    }

    public void setServices(List<ServicesBean> list) {
        this.services = list;
    }

    public String getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(String str) {
        this.modifyTime = str;
    }

    public static class DevInfoBean {
        private String devType;
        private String deviceName;
        private String deviceSn;
        private String fwv;
        private String hiv;
        private String hwv;
        private String internalModel;
        private String mac;
        private String manu;
        private String model;
        private String prodId;
        private int protType;
        private String sn;
        private String subProdId;
        private String swv;
        private String udid;

        public String getSn() {
            return this.sn;
        }

        public void setSn(String str) {
            this.sn = str;
        }

        public String getDeviceSn() {
            return this.deviceSn;
        }

        public void setDeviceSn(String str) {
            this.deviceSn = str;
        }

        public String getUdid() {
            return this.udid;
        }

        public void setUdid(String str) {
            this.udid = str;
        }

        public String getModel() {
            return this.model;
        }

        public void setModel(String str) {
            this.model = str;
        }

        public String getDevType() {
            return this.devType;
        }

        public void setDevType(String str) {
            this.devType = str;
        }

        public String getDeviceName() {
            return this.deviceName;
        }

        public void setDeviceName(String str) {
            this.deviceName = str;
        }

        public String getManu() {
            return this.manu;
        }

        public void setManu(String str) {
            this.manu = str;
        }

        public String getProdId() {
            return this.prodId;
        }

        public void setProdId(String str) {
            this.prodId = str;
        }

        public String getHiv() {
            return this.hiv;
        }

        public void setHiv(String str) {
            this.hiv = str;
        }

        public String getMac() {
            return this.mac;
        }

        public void setMac(String str) {
            this.mac = str;
        }

        public String getFwv() {
            return this.fwv;
        }

        public void setFwv(String str) {
            this.fwv = str;
        }

        public String getHwv() {
            return this.hwv;
        }

        public void setHwv(String str) {
            this.hwv = str;
        }

        public String getSwv() {
            return this.swv;
        }

        public void setSwv(String str) {
            this.swv = str;
        }

        public int getProtType() {
            return this.protType;
        }

        public void setProtType(int i) {
            this.protType = i;
        }

        public String getSubProdId() {
            return this.subProdId;
        }

        public void setSubProdId(String str) {
            this.subProdId = str;
        }

        public String getInternalModel() {
            return this.internalModel;
        }

        public void setInternalModel(String str) {
            this.internalModel = str;
        }
    }

    public static class ServicesBean {
        private List<CharacteristicBean> characteristic;
        private String sid;
        private String st;

        public String getSt() {
            return this.st;
        }

        public void setSt(String str) {
            this.st = str;
        }

        public String getSid() {
            return this.sid;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public List<CharacteristicBean> getCharacteristic() {
            return this.characteristic;
        }

        public void setCharacteristic(List<CharacteristicBean> list) {
            this.characteristic = list;
        }

        public static class CharacteristicBean {
            private String character;
            private String ts;
            private Object value;

            public String getTs() {
                return this.ts;
            }

            public void setTs(String str) {
                this.ts = str;
            }

            public String getCharacter() {
                return this.character;
            }

            public void setCharacter(String str) {
                this.character = str;
            }

            public Object getValue() {
                return this.value;
            }

            public void setValue(Object obj) {
                this.value = obj;
            }
        }
    }
}
