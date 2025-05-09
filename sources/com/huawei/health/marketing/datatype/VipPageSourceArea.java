package com.huawei.health.marketing.datatype;

import com.amap.api.services.district.DistrictSearchQuery;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class VipPageSourceArea {

    @SerializedName(DistrictSearchQuery.KEYWORDS_CITY)
    private String city;

    @SerializedName(DistrictSearchQuery.KEYWORDS_PROVINCE)
    private String province;

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }
}
