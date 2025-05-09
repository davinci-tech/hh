package com.huawei.health.health.utils.functionsetcard.manager.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.tencent.open.SocialConstants;
import health.compact.a.CommonUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class CardConfig {

    @SerializedName("appVersionType")
    private int mAppVersionType;

    @SerializedName("biKey")
    private String mBiKey;

    @SerializedName("cardConstructorCls")
    private String mCardConstructorCls;

    @SerializedName("cardIconRes")
    private String mCardIconRes;

    @SerializedName("cardId")
    private String mCardId;

    @SerializedName("cardName")
    private String mCardName;

    @SerializedName("cardNameRes")
    private String mCardNameRes;

    @SerializedName("dataTypes")
    private List<Integer> mDataTypes;

    @SerializedName("defaultBgRes")
    private String mDefaultBgRes;

    @SerializedName("defaultMarketingDescRes")
    private String mDefaultMarketingDescRes;

    @SerializedName("defaultMarketingImageRes")
    private String mDefaultMarketingImageRes;

    @SerializedName("defaultOrder")
    private int mDefaultOrder;

    @SerializedName(SocialConstants.PARAM_APP_DESC)
    private String mDesc;

    @SerializedName("deviceCapability")
    private List<String> mDeviceCapability;

    @SerializedName("editFlag")
    private int mEditFlag;

    @SerializedName("extendConfig")
    private Map<String, String> mExtendConfig;

    @SerializedName(MessageConstant.GROUP_MEDAL_TYPE)
    private String mGroup;

    @SerializedName("isSupportOversea")
    private boolean mIsSupportOversea;

    @SerializedName("noDataBgRes")
    private String mNoDataBgRes;

    @SerializedName("noDataDescRes")
    private String mNoDataDescRes;

    @SerializedName("showFlag")
    private int mShowFlag;

    /* loaded from: classes8.dex */
    public static class EditFlagConstant {
        public static final int EDIT_BY_AUTOMATIC = 0;
        public static final int EDIT_BY_INIT = -1;
        public static final int EDIT_BY_USER = 1;
    }

    /* loaded from: classes8.dex */
    public static class ShowStatusConstant {
        public static final int HIDE = 2;
        public static final int SHOW = 1;
        public static final int UNKNOWN = 0;
    }

    /* loaded from: classes8.dex */
    public static class VersionFlagConstant {
        public static final int ALL_VERSION = 65535;
        public static final int BETA_VERSION = 2;
        public static final int DEBUG_VERSION = 4;
        public static final int GP_BETA_VERSION = 16;
        public static final int GP_DEBUG_VERSION = 32;
        public static final int GP_RELEASE_VERSION = 8;
        public static final int RELEASE_VERSION = 1;
        public static final int STORE_DEMO_VERSION = 32768;
    }

    public CardConfig() {
        this.mEditFlag = -1;
    }

    public CardConfig(CardConfig cardConfig) {
        this.mEditFlag = -1;
        if (cardConfig == null) {
            return;
        }
        this.mCardId = cardConfig.mCardId;
        this.mCardName = cardConfig.mCardName;
        this.mCardNameRes = cardConfig.mCardNameRes;
        this.mDesc = cardConfig.mDesc;
        this.mGroup = cardConfig.mGroup;
        this.mDeviceCapability = cardConfig.mDeviceCapability;
        this.mDataTypes = cardConfig.mDataTypes;
        this.mIsSupportOversea = cardConfig.mIsSupportOversea;
        this.mAppVersionType = cardConfig.mAppVersionType;
        this.mEditFlag = cardConfig.mEditFlag;
        this.mShowFlag = cardConfig.mShowFlag;
        this.mNoDataDescRes = cardConfig.mNoDataDescRes;
        this.mDefaultMarketingDescRes = cardConfig.mDefaultMarketingDescRes;
        this.mNoDataBgRes = cardConfig.mNoDataBgRes;
        this.mDefaultBgRes = cardConfig.mDefaultBgRes;
        this.mDefaultMarketingImageRes = cardConfig.mDefaultMarketingImageRes;
        this.mCardIconRes = cardConfig.mCardIconRes;
        this.mDefaultOrder = cardConfig.mDefaultOrder;
        this.mBiKey = cardConfig.mBiKey;
        this.mCardConstructorCls = cardConfig.mCardConstructorCls;
        this.mExtendConfig = cardConfig.mExtendConfig;
    }

    public void update(CardConfig cardConfig) {
        String str;
        if (cardConfig == null || (str = this.mCardId) == null || !str.equals(cardConfig.mCardId)) {
            return;
        }
        this.mCardName = cardConfig.mCardName;
        this.mCardNameRes = cardConfig.mCardNameRes;
        this.mDesc = cardConfig.mDesc;
        this.mGroup = cardConfig.mGroup;
        this.mDeviceCapability = cardConfig.mDeviceCapability;
        this.mDataTypes = cardConfig.mDataTypes;
        this.mIsSupportOversea = cardConfig.mIsSupportOversea;
        this.mAppVersionType = cardConfig.mAppVersionType;
        this.mNoDataDescRes = cardConfig.mNoDataDescRes;
        this.mDefaultMarketingDescRes = cardConfig.mDefaultMarketingDescRes;
        this.mNoDataBgRes = cardConfig.mNoDataBgRes;
        this.mDefaultBgRes = cardConfig.mDefaultBgRes;
        this.mDefaultMarketingImageRes = cardConfig.mDefaultMarketingImageRes;
        this.mCardIconRes = cardConfig.mCardIconRes;
        this.mDefaultOrder = cardConfig.mDefaultOrder;
        this.mBiKey = cardConfig.mBiKey;
        this.mCardConstructorCls = cardConfig.mCardConstructorCls;
        this.mExtendConfig = cardConfig.mExtendConfig;
    }

    public boolean isInitialized() {
        return this.mShowFlag != 0;
    }

    public boolean isShow() {
        return this.mShowFlag == 1;
    }

    public void setShow(boolean z) {
        this.mShowFlag = z ? 1 : 2;
    }

    public boolean isSupport(boolean z, int i) {
        if (CommonUtil.cg()) {
            return true;
        }
        if (!z || isSupportOversea()) {
            return isSupportVersion(i);
        }
        return false;
    }

    public String getCardId() {
        return this.mCardId;
    }

    public void setCardId(String str) {
        this.mCardId = str;
    }

    public String getCardName() {
        return this.mCardName;
    }

    public void setCardName(String str) {
        this.mCardName = str;
    }

    public String getCardNameRes() {
        return this.mCardNameRes;
    }

    public void setCardNameRes(String str) {
        this.mCardNameRes = str;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public void setDesc(String str) {
        this.mDesc = str;
    }

    public String getGroup() {
        return this.mGroup;
    }

    public void setGroup(String str) {
        this.mGroup = str;
    }

    public List<String> getDeviceCapability() {
        return this.mDeviceCapability;
    }

    public void setDeviceCapability(List<String> list) {
        this.mDeviceCapability = list;
    }

    public List<Integer> getDataTypes() {
        return this.mDataTypes;
    }

    public void setDataTypes(List<Integer> list) {
        this.mDataTypes = list;
    }

    public void setSupportOversea(boolean z) {
        this.mIsSupportOversea = z;
    }

    public int getAppVersionType() {
        return this.mAppVersionType;
    }

    public void setAppVersionType(int i) {
        this.mAppVersionType = i;
    }

    public boolean isSupportVersion(int i) {
        int i2 = this.mAppVersionType;
        return i2 == 0 || (i2 & i) == i;
    }

    public int getEditFlag() {
        return this.mEditFlag;
    }

    public void setEditFlag(int i) {
        this.mEditFlag = i;
    }

    public int getShowFlag() {
        return this.mShowFlag;
    }

    public void setShowFlag(int i) {
        this.mShowFlag = i;
    }

    public String getNoDataDescRes() {
        return this.mNoDataDescRes;
    }

    public void setNoDataDescRes(String str) {
        this.mNoDataDescRes = str;
    }

    public String getDefaultMarketingDescRes() {
        return this.mDefaultMarketingDescRes;
    }

    public void setDefaultMarketingDescRes(String str) {
        this.mDefaultMarketingDescRes = str;
    }

    public String getNoDataBgRes() {
        return this.mNoDataBgRes;
    }

    public void setNoDataBgRes(String str) {
        this.mNoDataBgRes = str;
    }

    public String getDefaultBgRes() {
        return this.mDefaultBgRes;
    }

    public void setDefaultBgRes(String str) {
        this.mDefaultBgRes = str;
    }

    public String getDefaultMarketingImageRes() {
        return this.mDefaultMarketingImageRes;
    }

    public void setDefaultMarketingImageRes(String str) {
        this.mDefaultMarketingImageRes = str;
    }

    public String getCardIconRes() {
        return this.mCardIconRes;
    }

    public void setCardIconRes(String str) {
        this.mCardIconRes = str;
    }

    public int getDefaultOrder() {
        return this.mDefaultOrder;
    }

    public void setDefaultOrder(int i) {
        this.mDefaultOrder = i;
    }

    public String getBiKey() {
        return this.mBiKey;
    }

    public void setBiKey(String str) {
        this.mBiKey = str;
    }

    public String getCardConstructorCls() {
        return this.mCardConstructorCls;
    }

    public void setCardConstructorCls(String str) {
        this.mCardConstructorCls = str;
    }

    public Map<String, String> getExtendConfig() {
        return this.mExtendConfig;
    }

    public void setExtendConfig(Map<String, String> map) {
        this.mExtendConfig = map;
    }

    public boolean isSupportOversea() {
        return this.mIsSupportOversea;
    }

    public String toString() {
        return "cardID = " + getCardId() + ", showFlag = " + getShowFlag() + ", cardName = " + getCardName();
    }
}
