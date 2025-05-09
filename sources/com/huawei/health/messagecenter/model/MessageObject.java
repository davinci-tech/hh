package com.huawei.health.messagecenter.model;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.Guidepost;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.notification.NotificationDeviceLinkage;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import defpackage.bzs;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes3.dex */
public class MessageObject {
    private static final long MILLISECOND_OF_YEAR = 31536000000L;
    private static final String TAG = "serviceApi_MessageObject";
    private int mActivityJoinNum;
    private String mCategory;
    private long mCreateTime;
    private String mDetailUri;
    private String mDetailUriExt;
    private List<NotificationDeviceLinkage> mDeviceLinkageList;
    private String mDynamicDataId;
    private long mExpireTime;
    private ContentValues mExtra;
    private int mFlag;
    private boolean mGroupSummary;
    private String mH5PackageName;
    private String mHarmonyImageSize;
    private String mHarmonyImgUrl;
    private String mHeatMapCity;
    private String mHuid;
    private int mImageTextSeparateSwitch;
    private String mImei;
    private String mImgBigUri;
    private String mImgUri;
    private String mInfoClassify;
    private int mInfoRecommend;
    private int mJumpType;
    private String mLatestGetMsgTimestamp;
    private int mLayout;
    private List<MessageExt> mMessageExtList;
    private String mMetadata;
    private String mModule;
    private String mMsgContent;
    private String mMsgFrom;
    private String mMsgId;
    private int mMsgPosition;
    private String mMsgTitle;
    private int mMsgType;
    private String mMsgUserLabel;
    private INativeAd mNativeAd;
    private int mNewMsg;
    private int mNotified;
    private int mPageType;
    private int mPosition;
    private int mReadFlag;
    private long mReceiveTime;
    private ResourceBriefInfo mResBriefInfo;
    private String mType;
    private int mWeight;

    /* loaded from: classes8.dex */
    public static class Builder {
        private String mHuid;
        private String mImei;
        private String mModule;
        private String mMsgContent;
        private String mMsgTitle;
        private String mType;

        public Builder(String str, String str2) {
            this.mHuid = str;
            this.mImei = str2;
        }

        public Builder module(String str) {
            this.mModule = str;
            return this;
        }

        public Builder type(String str) {
            this.mType = str;
            return this;
        }

        public Builder title(String str) {
            this.mMsgTitle = str;
            return this;
        }

        public Builder content(String str) {
            this.mMsgContent = str;
            return this;
        }

        public MessageObject build() {
            return new MessageObject(this);
        }
    }

    private MessageObject(Builder builder) {
        this.mHuid = builder.mHuid;
        this.mImei = builder.mImei;
        this.mModule = builder.mModule;
        this.mType = builder.mType;
        this.mMsgTitle = builder.mMsgTitle;
        this.mMsgContent = builder.mMsgContent;
    }

    public MessageObject() {
        this.mMsgId = "";
        this.mModule = "";
        this.mType = "";
        this.mMetadata = "";
        this.mMsgType = 1;
        this.mFlag = 0;
        this.mWeight = 0;
        this.mMsgTitle = "";
        this.mMsgContent = "";
        this.mCreateTime = 0L;
        this.mExpireTime = new Date().getTime() + MILLISECOND_OF_YEAR;
        this.mImgUri = "";
        this.mImgBigUri = "";
        this.mDetailUri = "";
        this.mDetailUriExt = "";
        this.mMsgFrom = "";
        this.mPosition = 1;
        this.mMsgPosition = 0;
        this.mHuid = "";
        this.mImei = "";
        this.mNotified = 0;
        this.mInfoClassify = "";
        this.mHeatMapCity = "";
        this.mInfoRecommend = 0;
        this.mMsgUserLabel = "";
        this.mPageType = 0;
        this.mImageTextSeparateSwitch = 0;
        this.mHarmonyImgUrl = "";
        this.mHarmonyImageSize = "";
        this.mGroupSummary = false;
    }

    public boolean isGroupSummary() {
        return this.mGroupSummary;
    }

    public void setGroupSummary(boolean z) {
        this.mGroupSummary = z;
    }

    public String getMsgId() {
        return this.mMsgId;
    }

    public void setMsgId(String str) {
        this.mMsgId = str;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public String getModule() {
        return this.mModule;
    }

    public void setModule(String str) {
        this.mModule = str;
    }

    public String getMetadata() {
        return this.mMetadata;
    }

    public void setMetadata(String str) {
        this.mMetadata = str;
    }

    public int getFlag() {
        return this.mFlag;
    }

    public void setFlag(int i) {
        this.mFlag = i;
    }

    public int getMsgType() {
        return this.mMsgType;
    }

    public void setMsgType(int i) {
        this.mMsgType = i;
    }

    public String getMsgTitle() {
        return this.mMsgTitle;
    }

    public void setMsgTitle(String str) {
        this.mMsgTitle = str;
    }

    public int getWeight() {
        return this.mWeight;
    }

    public void setWeight(int i) {
        this.mWeight = i;
    }

    public String getMsgContent() {
        return this.mMsgContent;
    }

    public void setMsgContent(String str) {
        this.mMsgContent = str;
    }

    public long getCreateTime() {
        return this.mCreateTime;
    }

    public void setCreateTime(long j) {
        this.mCreateTime = j;
    }

    public long getExpireTime() {
        return this.mExpireTime;
    }

    public void setExpireTime(long j) {
        this.mExpireTime = j;
    }

    public long getReceiveTime() {
        return this.mReceiveTime;
    }

    public void setReceiveTime(long j) {
        this.mReceiveTime = j;
    }

    public String getImgUri() {
        return this.mImgUri;
    }

    public void setImgUri(String str) {
        this.mImgUri = str;
    }

    public String getImgBigUri() {
        return this.mImgBigUri;
    }

    public void setImgBigUri(String str) {
        this.mImgBigUri = str;
    }

    public String getDetailUri() {
        return this.mDetailUri;
    }

    public void setDetailUri(String str) {
        this.mDetailUri = str;
    }

    public String getDetailUriExt() {
        return this.mDetailUriExt;
    }

    public void setDetailUriExt(String str) {
        this.mDetailUriExt = str;
    }

    public String getMsgFrom() {
        return this.mMsgFrom;
    }

    public void setMsgFrom(String str) {
        this.mMsgFrom = str;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public void setPosition(int i) {
        this.mPosition = i;
    }

    public int getMsgPosition() {
        return this.mMsgPosition;
    }

    public void setMsgPosition(int i) {
        this.mMsgPosition = i;
    }

    public String getHuid() {
        return this.mHuid;
    }

    public void setHuid(String str) {
        this.mHuid = str;
    }

    public String getImei() {
        return this.mImei;
    }

    public void setImei(String str) {
        this.mImei = str;
    }

    public int getReadFlag() {
        return this.mReadFlag;
    }

    public void setReadFlag(int i) {
        this.mReadFlag = i;
    }

    public int getNotified() {
        return this.mNotified;
    }

    public void setNotified(int i) {
        this.mNotified = i;
    }

    public int getNewMsg() {
        return this.mNewMsg;
    }

    public void setNewMsg(int i) {
        this.mNewMsg = i;
    }

    public int getJumpType() {
        return this.mJumpType;
    }

    public void setJumpType(int i) {
        this.mJumpType = i;
    }

    public ContentValues getExtra() {
        return this.mExtra;
    }

    public void setExtra(ContentValues contentValues) {
        this.mExtra = contentValues;
    }

    public String getH5PackageName() {
        return this.mH5PackageName;
    }

    public void setH5PackageName(String str) {
        this.mH5PackageName = str;
    }

    public String getInfoClassify() {
        return this.mInfoClassify;
    }

    public void setInfoClassify(String str) {
        this.mInfoClassify = str;
    }

    public String getHeatMapCity() {
        return this.mHeatMapCity;
    }

    public void setHeatMapCity(String str) {
        this.mHeatMapCity = str;
    }

    public String getMsgUserLabel() {
        return this.mMsgUserLabel;
    }

    public void setMsgUserLabel(String str) {
        this.mMsgUserLabel = str;
    }

    public int getInfoRecommend() {
        return this.mInfoRecommend;
    }

    public void setInfoRecommend(int i) {
        this.mInfoRecommend = i;
    }

    public int getLayout() {
        return this.mLayout;
    }

    public void setLayout(int i) {
        this.mLayout = i;
    }

    public List<MessageExt> getMessageExtList() {
        return this.mMessageExtList;
    }

    public void setMessageExtList(List<MessageExt> list) {
        this.mMessageExtList = list;
    }

    public int getPageType() {
        return this.mPageType;
    }

    public void setPageType(int i) {
        this.mPageType = i;
    }

    public int getImageTextSeparateSwitch() {
        return this.mImageTextSeparateSwitch;
    }

    public void setImageTextSeparateSwitch(int i) {
        this.mImageTextSeparateSwitch = i;
    }

    public String getHarmonyImgUrl() {
        return this.mHarmonyImgUrl;
    }

    public void setHarmonyImgUrl(String str) {
        this.mHarmonyImgUrl = str;
    }

    public String getHarmonyImageSize() {
        return this.mHarmonyImageSize;
    }

    public void setHarmonyImageSize(String str) {
        this.mHarmonyImageSize = str;
    }

    public void setNativeAd(INativeAd iNativeAd) {
        this.mNativeAd = iNativeAd;
    }

    public INativeAd getNativeAd() {
        return this.mNativeAd;
    }

    public void setLatestGetMsgTimestamp(String str) {
        this.mLatestGetMsgTimestamp = str;
    }

    public String getLatestGetMsgTimestamp() {
        return this.mLatestGetMsgTimestamp;
    }

    public void setCategory(String str) {
        this.mCategory = str;
    }

    public String getCategory() {
        return this.mCategory;
    }

    public ResourceBriefInfo getResBriefInfo() {
        return this.mResBriefInfo;
    }

    public void setResBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        this.mResBriefInfo = resourceBriefInfo;
    }

    public int getActivityJoinNum() {
        return this.mActivityJoinNum;
    }

    public void setActivityJoinNum(int i) {
        this.mActivityJoinNum = i;
    }

    public String getDynamicDataId() {
        return this.mDynamicDataId;
    }

    public void setDynamicDataId(String str) {
        this.mDynamicDataId = str;
    }

    public List<NotificationDeviceLinkage> getDeviceLinkageList() {
        return this.mDeviceLinkageList;
    }

    public void setDeviceLinkageList(List<NotificationDeviceLinkage> list) {
        this.mDeviceLinkageList = list;
    }

    public void jumpToTargetPage(Context context) {
        if (this.mNewMsg != 1 || context == null) {
            LogUtil.a(TAG, "Not new msg or context is null");
            return;
        }
        int i = this.mJumpType;
        if (i != 1) {
            if (i == 2) {
                if (TextUtils.isEmpty(this.mH5PackageName) || TextUtils.isEmpty(this.mDetailUri)) {
                    LogUtil.h(TAG, "jumpToTargetPage, h5url or h5PackageName is null/Empty.");
                    return;
                }
                H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
                builder.addPath(this.mDetailUri);
                bzs.e().loadH5ProApp(context, this.mH5PackageName, builder);
                return;
            }
            LogUtil.h(TAG, "Invalid jumpType, jumpType is: ", Integer.valueOf(i));
            return;
        }
        if (TextUtils.isEmpty(this.mDetailUri)) {
            LogUtil.a(TAG, "jumpToTargetPage, mDetailUri is null or empty");
            return;
        }
        Guidepost b = AppRouter.b(this.mDetailUri);
        if (this.mExtra != null) {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, Object> entry : this.mExtra.valueSet()) {
                bundle.putString(entry.getKey(), String.valueOf(entry.getValue()));
            }
            b = b.zF_(bundle);
        }
        b.c(context);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageObject messageObject = (MessageObject) obj;
        return Objects.equals(this.mModule, messageObject.mModule) && Objects.equals(this.mType, messageObject.mType) && Objects.equals(this.mHuid, messageObject.mHuid) && Objects.equals(this.mMsgFrom, messageObject.mMsgFrom) && Objects.equals(this.mMsgTitle, messageObject.mMsgTitle) && Objects.equals(this.mMsgContent, messageObject.mMsgContent);
    }

    public int hashCode() {
        return Objects.hash(this.mHuid, this.mModule, this.mType, this.mImei, this.mMsgTitle, this.mMsgContent);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("MessageObject{mMsgId='");
        stringBuffer.append(this.mMsgId).append("', mModule='");
        stringBuffer.append(this.mModule).append("', mType='");
        stringBuffer.append(this.mType).append("', mMetadata='");
        stringBuffer.append(this.mMetadata).append("', mMsgType=");
        stringBuffer.append(this.mMsgType);
        stringBuffer.append(", mFlag=").append(this.mFlag);
        stringBuffer.append(", mWeight=").append(this.mWeight);
        stringBuffer.append(", mMsgTitle='").append(this.mMsgTitle).append("', mMsgContent='");
        stringBuffer.append(this.mMsgContent).append("', mCreateTime=");
        stringBuffer.append(this.mCreateTime);
        stringBuffer.append(", mExpireTime=").append(this.mExpireTime);
        stringBuffer.append(", mReceiveTime=").append(this.mReceiveTime);
        stringBuffer.append(", mImgUri='").append(this.mImgUri).append("', mImgBigUri='");
        stringBuffer.append(this.mImgBigUri).append("', mDetailUri='");
        stringBuffer.append(this.mDetailUri).append("', mDetailUriExt='");
        stringBuffer.append(this.mDetailUriExt).append("', mMsgFrom='");
        stringBuffer.append(this.mMsgFrom).append("', mPosition=");
        stringBuffer.append(this.mPosition);
        stringBuffer.append(", mMsgPosition=").append(this.mMsgPosition);
        stringBuffer.append(", mHuid='").append(this.mHuid).append("', mImei='");
        stringBuffer.append(this.mImei).append("', mReadFlag=");
        stringBuffer.append(this.mReadFlag);
        stringBuffer.append(", mNotified=").append(this.mNotified);
        stringBuffer.append(", mInfoClassify='").append(this.mInfoClassify).append("', mHeatMapCity='");
        stringBuffer.append(this.mHeatMapCity).append("', mInfoRecommend=");
        stringBuffer.append(this.mInfoRecommend);
        stringBuffer.append(", mMsgUserLabel='").append(this.mMsgUserLabel).append("', mLayout=");
        stringBuffer.append(this.mLayout);
        stringBuffer.append(", mPageType=").append(this.mPageType);
        stringBuffer.append(", mMessageExtList=").append(this.mMessageExtList);
        stringBuffer.append(", mImageTextSeparateSwitch=").append(this.mImageTextSeparateSwitch);
        stringBuffer.append(", mHarmonyImgUrl='").append(this.mHarmonyImgUrl).append("', mHarmonyImageSize='");
        stringBuffer.append(this.mHarmonyImageSize).append("', mNativeAd=");
        stringBuffer.append(this.mNativeAd);
        stringBuffer.append(", mLatestGetMsgTimestamp='").append(this.mLatestGetMsgTimestamp).append("', mCategory='");
        stringBuffer.append(this.mCategory).append("', mResBriefInfo=");
        stringBuffer.append(this.mResBriefInfo);
        stringBuffer.append(", mGroupSummary=").append(this.mGroupSummary);
        stringBuffer.append(", mNewType=").append(this.mNewMsg);
        stringBuffer.append(", mJumpType=").append(this.mJumpType);
        stringBuffer.append(", mExtra=").append(this.mExtra);
        stringBuffer.append(", mActivityJoinNum=").append(this.mActivityJoinNum);
        stringBuffer.append(", mDynamicDataId=").append(this.mDynamicDataId);
        stringBuffer.append(", mDeviceLinkageList=").append(this.mDeviceLinkageList);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
