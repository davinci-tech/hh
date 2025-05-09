package com.huawei.openalliance.ad.beans.parameter;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.ads.data.SearchInfo;
import com.huawei.openalliance.ad.annotations.f;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.beans.metadata.ImpEXs;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.cz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class RequestOptions {
    public static final String AD_CONTENT_CLASSIFICATION_A = "A";
    public static final String AD_CONTENT_CLASSIFICATION_J = "J";
    public static final String AD_CONTENT_CLASSIFICATION_PI = "PI";
    public static final String AD_CONTENT_CLASSIFICATION_UNSPECIFIED = "";
    public static final String AD_CONTENT_CLASSIFICATION_W = "W";
    private static final String TAG = "RequestOptions";
    public static final int TAG_FOR_CHILD_PROTECTION_FALSE = 0;
    public static final int TAG_FOR_CHILD_PROTECTION_TRUE = 1;
    public static final int TAG_FOR_CHILD_PROTECTION_UNSPECIFIED = -1;
    public static final int TAG_FOR_UNDER_AGE_OF_PROMISE_FALSE = 0;
    public static final int TAG_FOR_UNDER_AGE_OF_PROMISE_TRUE = 1;
    public static final int TAG_FOR_UNDER_AGE_OF_PROMISE_UNSPECIFIED = -1;
    private String adContentClassification;
    private App app;
    private String appCountry;
    private String appLang;
    private Map<String, BiddingParam> biddingParamMap;
    private String consent;

    @f
    private Map<String, Bundle> extras;
    private Integer hwNonPersonalizedAd;
    private Map<String, ImpEXs> impEXs;
    private Integer isQueryUseEnabled;
    private Integer nonPersonalizedAd;
    private Boolean requestLocation;
    private SearchInfo searchInfo;
    private String searchTerm;
    private Boolean supportFa;
    private Integer tMax;
    private Integer tagForChildProtection;
    private Integer tagForUnderAgeOfPromise;
    private Integer thirdNonPersonalizedAd;
    private boolean useConsentNpa;

    public static class Builder {
        private App app;
        private String appCountry;
        private String appLang;
        private String consent;
        private Map<String, Bundle> extras;
        private Boolean requestLocation;
        private SearchInfo searchInfo;
        private String searchTerm;
        private Boolean supportFa;
        private Integer tMax;
        private Integer tagForChildProtection;
        private Integer tagForUnderAgeOfPromise;
        private String adContentClassification = null;
        private Integer nonPersonalizedAd = null;
        private Integer isQueryUseEnabled = null;
        private Integer hwNonPersonalizedAd = null;
        private Integer thirdNonPersonalizedAd = null;
        private Map<String, BiddingParam> biddingParamMap = new HashMap();
        private boolean useConsentNpa = true;

        public Builder setUseConsentNpa(boolean z) {
            this.useConsentNpa = z;
            return this;
        }

        public Builder setThirdNonPersonalizedAd(Integer num) {
            if (num == null || 1 == num.intValue() || num.intValue() == 0) {
                this.thirdNonPersonalizedAd = num;
            } else {
                ho.d(RequestOptions.TAG, "Invalid value passed to setThirdNonPersonalizedAd: " + num);
            }
            return this;
        }

        public Builder setTagForUnderAgeOfPromise(Integer num) {
            if (num == null || num.intValue() == -1 || num.intValue() == 0 || num.intValue() == 1) {
                this.tagForUnderAgeOfPromise = num;
            } else {
                ho.a(RequestOptions.TAG, "Invalid value for setTagForUnderAgeOfPromise: %d", num);
            }
            return this;
        }

        public Builder setTagForChildProtection(Integer num) {
            if (num == null || num.intValue() == -1 || num.intValue() == 0 || num.intValue() == 1) {
                this.tagForChildProtection = num;
            } else {
                ho.a(RequestOptions.TAG, "Invalid value for setTagForChildProtection: %d", num);
            }
            return this;
        }

        public Builder setTMax(Integer num) {
            this.tMax = num;
            return this;
        }

        public Builder setSupportFa(Boolean bool) {
            this.supportFa = bool;
            return this;
        }

        public Builder setSearchTerm(String str) {
            if (TextUtils.isEmpty(str)) {
                ho.d(RequestOptions.TAG, "Invalid value setSearchTerm");
            } else {
                this.searchTerm = str;
            }
            return this;
        }

        public Builder setSearchInfo(SearchInfo searchInfo) {
            this.searchInfo = searchInfo;
            return this;
        }

        public Builder setRequestLocation(Boolean bool) {
            this.requestLocation = bool;
            return this;
        }

        public Builder setNonPersonalizedAd(Integer num) {
            if (num == null || 1 == num.intValue() || num.intValue() == 0) {
                this.nonPersonalizedAd = num;
            } else {
                ho.d(RequestOptions.TAG, "Invalid value passed to setNonPersonalizedAd: " + num);
            }
            return this;
        }

        public Builder setIsQueryUseEnabled(Integer num) {
            if (num == null || num.intValue() == 0 || 1 == num.intValue()) {
                this.isQueryUseEnabled = num;
            } else {
                ho.d(RequestOptions.TAG, "Invalid value passed to setIsQueryUseEnabled: " + num);
            }
            return this;
        }

        public Builder setHwNonPersonalizedAd(Integer num) {
            if (num == null || 1 == num.intValue() || num.intValue() == 0) {
                this.hwNonPersonalizedAd = num;
            } else {
                ho.d(RequestOptions.TAG, "Invalid value passed to setHwNonPersonalizedAd: " + num);
            }
            return this;
        }

        public Builder setExtras(Map<String, Bundle> map) {
            this.extras = map;
            return this;
        }

        public Builder setConsent(String str) {
            this.consent = str;
            return this;
        }

        public Builder setBiddingParamMap(Map<String, BiddingParam> map) {
            if (map == null) {
                return null;
            }
            this.biddingParamMap = map;
            return this;
        }

        public Builder setAppLang(String str) {
            if (TextUtils.isEmpty(str)) {
                ho.d(RequestOptions.TAG, "Invalid value setAppLang");
            } else {
                this.appLang = str;
            }
            return this;
        }

        public Builder setAppCountry(String str) {
            if (TextUtils.isEmpty(str)) {
                ho.d(RequestOptions.TAG, "Invalid value setAppLang");
            } else {
                this.appCountry = str;
            }
            return this;
        }

        public Builder setApp(App app) {
            this.app = app;
            return this;
        }

        public Builder setAdContentClassification(String str) {
            if (str == null || "".equals(str)) {
                str = null;
            } else if (!"W".equals(str) && !RequestOptions.AD_CONTENT_CLASSIFICATION_PI.equals(str) && !RequestOptions.AD_CONTENT_CLASSIFICATION_J.equals(str) && !"A".equals(str)) {
                ho.a(RequestOptions.TAG, "Invalid value for setAdContentClassification: %s", str);
                return this;
            }
            this.adContentClassification = str;
            return this;
        }

        public RequestOptions build() {
            return new RequestOptions(this);
        }

        public Builder addBiddingParamMap(String str, BiddingParam biddingParam) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            this.biddingParamMap.put(str, biddingParam);
            return this;
        }
    }

    public Builder toBuilder() {
        return new Builder().setTagForChildProtection(this.tagForChildProtection).setTagForUnderAgeOfPromise(this.tagForUnderAgeOfPromise).setAdContentClassification(this.adContentClassification).setAppLang(this.appLang).setAppCountry(this.appCountry).setNonPersonalizedAd(this.nonPersonalizedAd).setHwNonPersonalizedAd(this.hwNonPersonalizedAd).setThirdNonPersonalizedAd(this.thirdNonPersonalizedAd).setIsQueryUseEnabled(this.isQueryUseEnabled).setRequestLocation(this.requestLocation).setSearchTerm(this.searchTerm).setExtras(this.extras).setConsent(this.consent).setApp(this.app).setBiddingParamMap(this.biddingParamMap).setTMax(this.tMax).setSearchInfo(this.searchInfo).setSupportFa(this.supportFa).setUseConsentNpa(this.useConsentNpa);
    }

    public boolean isRequestLocation() {
        Boolean bool = this.requestLocation;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    public Integer getTagForUnderAgeOfPromise() {
        return this.tagForUnderAgeOfPromise;
    }

    public Integer getTagForChildProtection() {
        return this.tagForChildProtection;
    }

    public Integer getTMax() {
        return this.tMax;
    }

    public String getSearchTerm() {
        return this.searchTerm;
    }

    public Integer getNonPersonalizedAd() {
        return this.nonPersonalizedAd;
    }

    public Integer getIsQueryUseEnabled() {
        return this.isQueryUseEnabled;
    }

    public Map<String, Bundle> getExtras() {
        return this.extras;
    }

    public String getConsent() {
        return this.consent;
    }

    public BiddingParam getBiddingParam(String str) {
        if (this.biddingParamMap == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return this.biddingParamMap.get(str);
    }

    public String getAppLang() {
        return this.appLang;
    }

    public String getAppCountry() {
        return this.appCountry;
    }

    public App getApp() {
        return this.app;
    }

    public String getAdContentClassification() {
        String str = this.adContentClassification;
        return str == null ? "" : str;
    }

    public boolean g() {
        return this.useConsentNpa;
    }

    public Boolean f() {
        return this.supportFa;
    }

    public SearchInfo e() {
        return this.searchInfo;
    }

    public Boolean d() {
        return this.requestLocation;
    }

    public Integer c() {
        return this.thirdNonPersonalizedAd;
    }

    public Integer b() {
        return this.hwNonPersonalizedAd;
    }

    public String a() {
        return this.adContentClassification;
    }

    private Map<String, ImpEXs> a(Map<String, Bundle> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        try {
            for (Map.Entry<String, Bundle> entry : map.entrySet()) {
                String key = entry.getKey();
                Bundle value = entry.getValue();
                if (value != null) {
                    ArrayList arrayList = new ArrayList();
                    for (String str : value.keySet()) {
                        arrayList.add(new ImpEX(str, cz.d(value.getString(str))));
                    }
                    hashMap.put(key, new ImpEXs(arrayList));
                }
            }
            return hashMap;
        } catch (Throwable th) {
            ho.c(TAG, "toImpEXs err: %s", th.getClass().getSimpleName());
            return hashMap;
        }
    }

    private RequestOptions(Builder builder) {
        this.nonPersonalizedAd = null;
        this.isQueryUseEnabled = null;
        this.hwNonPersonalizedAd = null;
        this.thirdNonPersonalizedAd = null;
        this.biddingParamMap = new HashMap();
        this.useConsentNpa = true;
        this.tagForChildProtection = builder.tagForChildProtection;
        this.tagForUnderAgeOfPromise = builder.tagForUnderAgeOfPromise;
        this.adContentClassification = builder.adContentClassification;
        this.isQueryUseEnabled = builder.isQueryUseEnabled;
        this.nonPersonalizedAd = builder.nonPersonalizedAd;
        this.hwNonPersonalizedAd = builder.hwNonPersonalizedAd;
        this.thirdNonPersonalizedAd = builder.thirdNonPersonalizedAd;
        this.appLang = builder.appLang;
        this.appCountry = builder.appCountry;
        this.requestLocation = builder.requestLocation;
        this.searchTerm = builder.searchTerm;
        Map<String, Bundle> map = builder.extras;
        this.extras = map;
        this.impEXs = a(map);
        this.consent = builder.consent;
        this.app = builder.app;
        this.biddingParamMap = builder.biddingParamMap;
        this.tMax = builder.tMax;
        this.searchInfo = builder.searchInfo;
        this.supportFa = builder.supportFa;
        this.useConsentNpa = builder.useConsentNpa;
    }

    public RequestOptions() {
        this.nonPersonalizedAd = null;
        this.isQueryUseEnabled = null;
        this.hwNonPersonalizedAd = null;
        this.thirdNonPersonalizedAd = null;
        this.biddingParamMap = new HashMap();
        this.useConsentNpa = true;
    }
}
