package com.huawei.hms.iapfull.network.model;

import java.util.List;

/* loaded from: classes9.dex */
public class DeveloperSignResponse extends BaseResponse {
    private List<MyDics> dics;
    private String huaweiSDKKey;
    private List<MyPayType> payTypeList;

    public void setPayTypeList(List<MyPayType> list) {
        this.payTypeList = list;
    }

    public static class MyDics {
        String name;
        String value;

        public void setValue(String str) {
            this.value = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }
    }

    public void setHuaweiSDKKey(String str) {
        this.huaweiSDKKey = str;
    }

    public void setDics(List<MyDics> list) {
        this.dics = list;
    }

    public static class MyPayType {
        int payType;

        public void setPayType(int i) {
            this.payType = i;
        }

        public int getPayType() {
            return this.payType;
        }
    }

    public List<MyPayType> getPayTypeList() {
        return this.payTypeList;
    }

    public String getHuaweiSDKKey() {
        return this.huaweiSDKKey;
    }

    public List<MyDics> getDics() {
        return this.dics;
    }
}
