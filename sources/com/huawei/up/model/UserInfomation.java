package com.huawei.up.model;

import android.text.TextUtils;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jed;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
public class UserInfomation implements Serializable {
    public static final String BIRTHDAY_DEFAULT = "19900801";
    public static final String BIRTHDAY_SETED = "SETED";
    public static final String BIRTHDAY_UNKNOWN = "UNKNOWN";
    public static final String BIRTHDAY_UNSETED = "UNSETED";
    public static final String BIRTHDAY_UNSETED_USER_SEE = "UNSETED_USER_SEE";
    public static final int DATA_NOT_CHANGED = -1000;
    public static final int GENDER_FEMALE = 1;
    public static final int GENDER_MALE = 0;
    public static final int GENDER_SECRET = 2;
    public static final int GENDER_UNKNOWN = -1;
    public static final int HEIGHT_DEFAULT_ENGLISH = 67;
    public static final int HEIGHT_DEFAULT_METRIC = 170;
    public static final String KEY_USER_INFO = "userinfo_key";
    private static final float PARAMETERS_RUN = 0.83f;
    private static final float PARAMETERS_WALK = 0.42f;
    private static final String TAG = "UserInfomation";
    public static final int UNIT_TYPE_ENGLISH = 1;
    public static final int UNIT_TYPE_METRIC = 0;
    public static final int WEIGHT_DEFAULT_ENGLISH = 132;
    public static final int WEIGHT_DEFAULT_METRIC = 60;
    private static final long serialVersionUID = -318540945237567052L;
    private transient String birthdayStatus;
    private transient int clientSet;
    private transient int maxVo2;
    private transient String name;
    private transient String picPath;
    private transient String portraitUrl;
    private transient long setTime;
    private transient long vo2Time;
    private transient String birthday = Integer.toString(-100);
    private transient int gender = -100;
    private transient float weight = -100.0f;
    private transient int height = 0;
    private String languageCode = MLAsrConstants.LAN_ZH;

    @Deprecated
    public UserInfomation() {
        if (UnitUtil.h()) {
            this.clientSet = 1;
        } else {
            this.clientSet = 0;
        }
    }

    public UserInfomation(int i) {
        this.clientSet = i;
    }

    public String getBirthdayStatus() {
        return this.birthdayStatus;
    }

    public void setBirthdayStatus(String str) {
        this.birthdayStatus = str;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public int getAge() {
        if (!isBirthdayValid()) {
            return 0;
        }
        try {
            return countAge(new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(this.birthday));
        } catch (ParseException unused) {
            LogUtil.h(TAG, "birthday str parse error");
            return 0;
        }
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float f) {
        this.weight = f;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    private int transHeightUnit(int i, int i2, int i3) {
        if (i <= 0) {
            return i;
        }
        if (i2 == 1 && i3 == 0) {
            return jed.a(i);
        }
        if (i2 == 0 && i3 == 1) {
            return jed.d(i);
        }
        return 0;
    }

    private float transWeightUnit(float f, int i, int i2) {
        double h;
        if (f <= 0.0f) {
            return f;
        }
        if (i == 1 && i2 == 0) {
            h = UnitUtil.i(f);
        } else {
            if (i != 0 || i2 != 1) {
                return 0.0f;
            }
            h = UnitUtil.h(f);
        }
        return (float) h;
    }

    public int getMetricHeight() {
        int i = this.clientSet;
        if (i == 0) {
            return this.height;
        }
        return transHeightUnit(this.height, i, 0);
    }

    public float getMetricWeight() {
        int i = this.clientSet;
        if (i == 0) {
            return this.weight;
        }
        return transWeightUnit(this.weight, i, 0);
    }

    public int getGenderOrDefaultValue() {
        if (isGenderValid()) {
            return this.gender;
        }
        return 0;
    }

    public int getHeightOrDefaultValue() {
        if (isHeightValid()) {
            return this.height;
        }
        if (this.clientSet == 0) {
            return 170;
        }
        return jed.d(170);
    }

    public float getWeightOrDefaultValue() {
        if (isWeightValid()) {
            return this.weight;
        }
        if (this.clientSet == 0) {
            return 60.0f;
        }
        return (float) UnitUtil.h(60.0d);
    }

    public int getAgeOrDefaultValue() {
        try {
            return countAge(new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(isBirthdayValid() ? this.birthday : BIRTHDAY_DEFAULT));
        } catch (ParseException unused) {
            LogUtil.h(TAG, "birthday str parse error");
            return 0;
        }
    }

    public int getWalkStepLen() {
        return Math.round(getMetricHeight() * PARAMETERS_WALK);
    }

    public int getRunStepLen() {
        return Math.round(getMetricHeight() * PARAMETERS_RUN);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPortraitUrl() {
        return this.portraitUrl;
    }

    public void setPortraitUrl(String str) {
        this.portraitUrl = str;
    }

    public String getLanguageCode() {
        return this.languageCode;
    }

    public void setLanguageCode(String str) {
        this.languageCode = str;
    }

    public int getClientSet() {
        return this.clientSet;
    }

    public void setClientSet(Integer num) {
        if (num == null) {
            return;
        }
        this.clientSet = num.intValue();
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(Integer num) {
        if (num == null) {
            return;
        }
        this.gender = num.intValue();
    }

    public String getPicPath() {
        return this.picPath;
    }

    public void setPicPath(String str) {
        this.picPath = str;
    }

    public long getSetTime() {
        return this.setTime;
    }

    public void setSetTime(long j) {
        this.setTime = j;
    }

    public int getMaxVo2() {
        return this.maxVo2;
    }

    public void setMaxVo2(int i) {
        this.maxVo2 = i;
    }

    public long getVo2Time() {
        return this.vo2Time;
    }

    public void setVo2Time(long j) {
        this.vo2Time = j;
    }

    public boolean isGenderValid() {
        int i = this.gender;
        return i == 0 || i == 1 || i == 2;
    }

    public boolean isBirthdayValid() {
        if (TextUtils.isEmpty(this.birthday) || !TextUtils.isDigitsOnly(this.birthday)) {
            return false;
        }
        try {
            return Integer.parseInt(this.birthday) > 0;
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "isBirthdayValid NumberFormatException");
            return false;
        }
    }

    public boolean isHeightValid() {
        return this.height > 0;
    }

    public boolean isWeightValid() {
        return this.weight > 0.0f;
    }

    public String toString() {
        return "birthdayStatus:" + this.birthdayStatus + ":birthday:" + this.birthday + ";gender:" + this.gender + ";weight:" + this.weight + ";height:" + this.height + ";name:" + this.name + ";languageCode:" + this.languageCode + ";portraitUrl:" + this.portraitUrl + ";clientSet:" + this.clientSet + ";picPath:" + this.picPath;
    }

    public UserInfomation copyFrom() {
        UserInfomation userInfomation = new UserInfomation();
        userInfomation.birthdayStatus = this.birthdayStatus;
        userInfomation.birthday = this.birthday;
        userInfomation.gender = this.gender;
        userInfomation.weight = this.weight;
        userInfomation.height = this.height;
        userInfomation.name = this.name;
        userInfomation.languageCode = this.languageCode;
        userInfomation.portraitUrl = this.portraitUrl;
        userInfomation.clientSet = this.clientSet;
        userInfomation.picPath = this.picPath;
        userInfomation.setTime = this.setTime;
        return userInfomation;
    }

    public void transSelf2METRIC() {
        if (getClientSet() == 1) {
            LogUtil.a(TAG, "UNIT_TYPE_ENGLISH trans to UNIT_TYPE_METRIC");
            if (isHeightValid()) {
                setHeight(getMetricHeight());
            }
            if (isWeightValid()) {
                setWeight(getMetricWeight());
            }
            setClientSet(0);
        }
    }

    public void loadAccountExtData(HiUserInfo hiUserInfo) {
        if (hiUserInfo.isWeightValid()) {
            setWeight(hiUserInfo.getWeight());
        }
        if (hiUserInfo.isHeightValid()) {
            setHeight(hiUserInfo.getHeight());
        }
        setSetTime(hiUserInfo.getCreateTime());
        if (hiUserInfo.getUnitType() == 0) {
            setClientSet(0);
        } else {
            LogUtil.h(TAG, "UNIT_FT_LB not stand");
            setClientSet(1);
        }
    }

    public void loadAccountExtData(UserInfomation userInfomation) {
        if (userInfomation.isHeightValid()) {
            setHeight(userInfomation.getHeight());
        }
        if (userInfomation.isWeightValid()) {
            setWeight(userInfomation.getWeight());
        }
        setSetTime(userInfomation.getSetTime());
        setClientSet(Integer.valueOf(userInfomation.getClientSet()));
    }

    public void loadAccountData(HiUserInfo hiUserInfo) {
        if (!TextUtils.isEmpty(hiUserInfo.getName())) {
            setName(hiUserInfo.getName());
        }
        setLanguageCode(MLAsrConstants.LAN_ZH);
        if (hiUserInfo.isBirthdayValid()) {
            setBirthday(String.valueOf(hiUserInfo.getBirthday()));
        }
        if (hiUserInfo.isGenderValid()) {
            int gender = hiUserInfo.getGender();
            if (gender == 1) {
                setGender(0);
            } else if (gender == 0) {
                setGender(1);
            } else {
                setGender(Integer.valueOf(gender));
            }
        }
        if (!TextUtils.isEmpty(hiUserInfo.getHeadImgUrl())) {
            LogUtil.a(TAG, "modify headImg url not empty,modify image useful");
            setPortraitUrl(hiUserInfo.getHeadImgUrl());
        }
        if (TextUtils.isEmpty(getPicPath())) {
            setPicPath("default");
        }
    }

    public void loadAccountData(UserInfomation userInfomation) {
        if (!TextUtils.isEmpty(userInfomation.getName())) {
            setName(userInfomation.getName());
        }
        if (!TextUtils.isEmpty(userInfomation.getLanguageCode())) {
            setLanguageCode(userInfomation.getLanguageCode());
        }
        if (userInfomation.isBirthdayValid()) {
            setBirthday(userInfomation.getBirthday());
        }
        if (userInfomation.isGenderValid()) {
            setGender(Integer.valueOf(userInfomation.getGender()));
        }
        if (!TextUtils.isEmpty(userInfomation.getPortraitUrl())) {
            setPortraitUrl(userInfomation.getPortraitUrl());
        }
        if (!TextUtils.isEmpty(userInfomation.getPicPath())) {
            setPicPath(userInfomation.getPicPath());
        }
        if (TextUtils.isEmpty(getPicPath())) {
            setPicPath("default");
        }
    }

    public int countAge(Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar2.setTime(date);
        if (calendar2.after(calendar)) {
            LogUtil.h(TAG, "are you born in the future?");
            return 0;
        }
        int i = calendar.get(1) - calendar2.get(1);
        return calendar.get(6) < calendar2.get(6) ? i - 1 : i;
    }
}
