package com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.tencent.open.SocialConstants;
import java.util.List;

/* loaded from: classes4.dex */
public class HwSportTypeInfo {

    @SerializedName(SocialConstants.PARAM_APP_DESC)
    private String mDesc;

    @SerializedName(MessageConstant.GROUP_MEDAL_TYPE)
    private String mGroup;

    @SerializedName("heartPostureType")
    private int mHeartPostureType;

    @SerializedName("historyList")
    private HwHistoryListInfo mHistoryList;

    @SerializedName("homePage")
    private HwHomePageInfo mHomePage;

    @SerializedName("mets")
    private float mMet;

    @SerializedName("oversea")
    private int mOversea;

    @SerializedName("sportDataStatisticsDetail")
    private List<HwSportDataStaticsInfo> mSportDataStatics;

    @SerializedName("sportDetail")
    private HwSportDetailInfo mSportDetail;

    @SerializedName("sportTypeId")
    private int mSportTypeId;

    @SerializedName("sportTypeName")
    private String mSportTypeName;

    @SerializedName("sportTypeRes")
    private String mSportTypeRes;

    public int getSportTypeId() {
        return this.mSportTypeId;
    }

    public void setSportTypeId(int i) {
        this.mSportTypeId = i;
    }

    public String getSportTypeName() {
        return this.mSportTypeName;
    }

    public void setSportTypeName(String str) {
        this.mSportTypeName = str;
    }

    public String getSportTypeRes() {
        return this.mSportTypeRes;
    }

    public void setHeartPostureType(int i) {
        this.mHeartPostureType = i;
    }

    public int getHeartPostureType() {
        return this.mHeartPostureType;
    }

    public void setSportTypeRes(String str) {
        this.mSportTypeRes = str;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public void setDesc(String str) {
        this.mDesc = str;
    }

    public int getOversea() {
        return this.mOversea;
    }

    public void setOversea(int i) {
        this.mOversea = i;
    }

    public String getGroup() {
        return this.mGroup;
    }

    public void setGroup(String str) {
        this.mGroup = str;
    }

    public float getMet() {
        return this.mMet;
    }

    public void setMets(float f) {
        this.mMet = f;
    }

    public HwHomePageInfo getHomePage() {
        return this.mHomePage;
    }

    public void setHomePage(HwHomePageInfo hwHomePageInfo) {
        this.mHomePage = hwHomePageInfo;
    }

    public HwHistoryListInfo getHistoryList() {
        return this.mHistoryList;
    }

    public void setHistoryList(HwHistoryListInfo hwHistoryListInfo) {
        this.mHistoryList = hwHistoryListInfo;
    }

    public HwSportDetailInfo getSportDetail() {
        return this.mSportDetail;
    }

    public void setSportDetail(HwSportDetailInfo hwSportDetailInfo) {
        this.mSportDetail = hwSportDetailInfo;
    }

    public List<HwSportDataStaticsInfo> getSportDataStatics() {
        return this.mSportDataStatics;
    }
}
