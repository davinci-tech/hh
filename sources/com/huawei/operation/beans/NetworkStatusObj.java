package com.huawei.operation.beans;

import com.google.gson.annotations.SerializedName;
import com.huawei.watchface.mvp.model.webview.JsNetwork;

/* loaded from: classes9.dex */
public class NetworkStatusObj {

    @SerializedName("isAirplaneMode")
    private boolean isAirplaneMode;

    @SerializedName("isRoaming")
    private boolean isRoaming;

    @SerializedName("networkCountryIso")
    private String networkCountryIso;

    @SerializedName("operator")
    private String operator;

    @SerializedName("simInfo")
    private SimInfoObj simInfo;

    @SerializedName("type")
    private String type = NetworkType.UNCONNECTED.getType();

    @SerializedName("metered")
    private int metered = NetworkMeteredType.UNKNOWN.getMetered();

    public void setType(NetworkType networkType) {
        this.type = networkType.getType();
    }

    public void setMetered(NetworkMeteredType networkMeteredType) {
        this.metered = networkMeteredType.getMetered();
    }

    public void setOperator(String str) {
        this.operator = str;
    }

    public void setNetworkCountryIso(String str) {
        this.networkCountryIso = str;
    }

    public void setRoaming(boolean z) {
        this.isRoaming = z;
    }

    public void setAirplaneMode(boolean z) {
        this.isAirplaneMode = z;
    }

    public void setSimInfo(SimInfoObj simInfoObj) {
        this.simInfo = simInfoObj;
    }

    public String getType() {
        return this.type;
    }

    public int getMetered() {
        return this.metered;
    }

    public String getOperator() {
        return this.operator;
    }

    public String getNetworkCountryIso() {
        return this.networkCountryIso;
    }

    public boolean isRoaming() {
        return this.isRoaming;
    }

    public boolean isAirplaneMode() {
        return this.isAirplaneMode;
    }

    public SimInfoObj getSimInfo() {
        return this.simInfo;
    }

    public enum NetworkType {
        UNCONNECTED("unConnected"),
        WIFI("wifi"),
        MOBILE(JsNetwork.NetworkType.MOBILE),
        VPN("vpn"),
        OTHER("other");

        private final String type;

        NetworkType(String str) {
            this.type = str;
        }

        public String getType() {
            return this.type;
        }
    }

    public enum NetworkMeteredType {
        UNKNOWN(0),
        METERED(1),
        UN_METERED(2);

        private final int metered;

        NetworkMeteredType(int i) {
            this.metered = i;
        }

        public int getMetered() {
            return this.metered;
        }
    }

    public static class SimInfoObj {

        @SerializedName("countryIso")
        private String countryIso;

        @SerializedName("operator")
        private String operator;

        @SerializedName("operatorName")
        private String operatorName;

        @SerializedName("state")
        private int state;

        public int getState() {
            return this.state;
        }

        public String getOperator() {
            return this.operator;
        }

        public String getOperatorName() {
            return this.operatorName;
        }

        public String getCountryIso() {
            return this.countryIso;
        }

        public void setState(int i) {
            this.state = i;
        }

        public void setOperator(String str) {
            this.operator = str;
        }

        public void setOperatorName(String str) {
            this.operatorName = str;
        }

        public void setCountryIso(String str) {
            this.countryIso = str;
        }
    }
}
