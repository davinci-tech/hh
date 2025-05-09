package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.inter.HiAd;

/* loaded from: classes5.dex */
public class cn {
    public static RequestOptions a(Context context, RequestOptions requestOptions) {
        RequestOptions requestConfiguration = HiAd.getInstance(context).getRequestConfiguration();
        if (requestOptions == null) {
            return requestConfiguration;
        }
        RequestOptions.Builder builder = requestOptions.toBuilder();
        if (requestOptions.a() == null) {
            builder.setAdContentClassification(requestConfiguration.getAdContentClassification());
        }
        if (requestOptions.getTagForUnderAgeOfPromise() == null) {
            builder.setTagForUnderAgeOfPromise(requestConfiguration.getTagForUnderAgeOfPromise());
        }
        if (requestOptions.getTagForChildProtection() == null) {
            builder.setTagForChildProtection(requestConfiguration.getTagForChildProtection());
        }
        if (requestOptions.getNonPersonalizedAd() == null) {
            builder.setNonPersonalizedAd(requestConfiguration.getNonPersonalizedAd());
        }
        if (requestOptions.getIsQueryUseEnabled() == null) {
            builder.setIsQueryUseEnabled(requestConfiguration.getIsQueryUseEnabled());
        }
        if (requestOptions.b() == null) {
            builder.setHwNonPersonalizedAd(requestConfiguration.b());
        }
        if (requestOptions.c() == null) {
            builder.setThirdNonPersonalizedAd(requestConfiguration.c());
        }
        if (requestOptions.getAppLang() == null) {
            builder.setAppLang(requestConfiguration.getAppLang());
        }
        if (requestOptions.getAppCountry() == null) {
            builder.setAppCountry(requestConfiguration.getAppCountry());
        }
        if (TextUtils.isEmpty(requestOptions.getConsent())) {
            builder.setConsent(requestConfiguration.getConsent());
        }
        if (requestOptions.getApp() == null) {
            builder.setApp(requestConfiguration.getApp());
        }
        if (requestOptions.e() == null) {
            builder.setSearchInfo(requestConfiguration.e());
        }
        if (requestOptions.f() == null) {
            builder.setSupportFa(requestConfiguration.f());
        }
        return builder.build();
    }
}
