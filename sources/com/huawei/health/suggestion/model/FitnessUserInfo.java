package com.huawei.health.suggestion.model;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.suggestion.UserinfoAdapter;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class FitnessUserInfo implements UserinfoAdapter {
    private static final String TAG = "Suggestion_" + FitnessUserInfo.class.getName();
    private Context mContext;
    private UserInfomation mUserInformation;

    public FitnessUserInfo(Context context, UserInfomation userInfomation) {
        this.mContext = context == null ? BaseApplication.getContext() : context;
        this.mUserInformation = userInfomation;
    }

    @Override // com.huawei.health.suggestion.UserinfoAdapter
    public int getGender() {
        UserInfomation userInfomation = this.mUserInformation;
        if (userInfomation != null) {
            return userInfomation.getGenderOrDefaultValue() == 0 ? 0 : 1;
        }
        LogUtil.c(TAG, "---getGender: ", 1);
        return 1;
    }

    @Override // com.huawei.health.suggestion.UserinfoAdapter
    public int getHeight() {
        UserInfomation userInfomation = this.mUserInformation;
        if (userInfomation != null) {
            return userInfomation.getHeightOrDefaultValue();
        }
        if (UnitUtil.h()) {
            return new UserInfomation(1).getHeightOrDefaultValue();
        }
        return new UserInfomation(0).getHeightOrDefaultValue();
    }

    @Override // com.huawei.health.suggestion.UserinfoAdapter
    public float getWeight() {
        UserInfomation userInfomation = this.mUserInformation;
        if (userInfomation != null) {
            return userInfomation.getWeightOrDefaultValue();
        }
        if (UnitUtil.h()) {
            return new UserInfomation(1).getWeightOrDefaultValue();
        }
        return new UserInfomation(0).getWeightOrDefaultValue();
    }

    @Override // com.huawei.health.suggestion.UserinfoAdapter
    public int getAge() {
        UserInfomation userInfomation = this.mUserInformation;
        if (userInfomation != null) {
            return userInfomation.getAgeOrDefaultValue();
        }
        if (UnitUtil.h()) {
            return new UserInfomation(1).getAgeOrDefaultValue();
        }
        return new UserInfomation(0).getAgeOrDefaultValue();
    }

    @Override // com.huawei.health.suggestion.UserinfoAdapter
    public Uri getPortrait() {
        if (this.mContext != null) {
            UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
            if (userProfileMgrApi == null) {
                LogUtil.h(TAG, "getUserInfo : userProfileMgrApi is null.");
                return null;
            }
            UserInfomation userInfo = userProfileMgrApi.getUserInfo();
            if (userInfo != null) {
                String portraitUrl = userInfo.getPortraitUrl();
                if (!TextUtils.isEmpty(portraitUrl)) {
                    return Uri.parse(portraitUrl);
                }
            }
        }
        return null;
    }
}
