package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes3.dex */
public class SleepAudio implements Parcelable {
    public static final Parcelable.Creator<SleepAudio> CREATOR = new Parcelable.Creator<SleepAudio>() { // from class: com.huawei.health.marketing.request.SleepAudio.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SleepAudio createFromParcel(Parcel parcel) {
            return new SleepAudio(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SleepAudio[] newArray(int i) {
            return new SleepAudio[i];
        }
    };
    private static final String TAG = "SleepAudio";
    private String audioDescription;
    private long audioDuration;
    private String audioIcon;
    private int audioType;
    private String audioUrl;
    private String auditionUrl;
    private String backGroundImg;
    private String bigBackGroundImg;
    private List<BootPageTxt> bootPageTxts;
    private String chapter;
    private int decompressType;
    private int displayStyle;
    private String firstTitle;
    private int heatCount;
    private int iconType;
    private int id;
    private String imgIcon;
    private int infoType;
    private List<String> introductionImgs;
    private int isShow;
    private int isVip;
    private List<SleepAudioLabel> labelList;
    private int labelType;
    private String memberBenefitsBigUrl;
    private String memberBenefitsUrl;
    private String name;
    private int parentAudioId;
    private int placeHolder;
    private PlayRecordInfo playRecord;
    private int positionId;
    private String positionName;
    private int purchasedStatus;
    private ResPurchaseInfo resPurchaseInfo;
    private String secondTitle;
    private int seriesAudioSize;
    private int seriesAudioUseSize;
    private String serviceProviderLogo;
    private String serviceProviderName;
    private String shareDescription;
    private String shareIcon;
    private String shareTitle;
    private String smallImage;
    private String supportAppVersion;
    private List<Tab> tabList;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SleepAudio(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.audioUrl = parcel.readString();
        this.imgIcon = parcel.readString();
        this.backGroundImg = parcel.readString();
        this.labelType = parcel.readInt();
        this.shareTitle = parcel.readString();
        this.shareDescription = parcel.readString();
        this.shareIcon = parcel.readString();
        this.heatCount = parcel.readInt();
        this.audioType = parcel.readInt();
        this.audioIcon = parcel.readString();
        this.iconType = parcel.readInt();
        this.smallImage = parcel.readString();
        this.title = parcel.readString();
        this.bootPageTxts = parcel.createTypedArrayList(BootPageTxt.CREATOR);
        this.audioDescription = parcel.readString();
        this.parentAudioId = parcel.readInt();
        this.infoType = parcel.readInt();
        this.audioDuration = parcel.readInt();
        this.placeHolder = parcel.readInt();
        this.seriesAudioSize = parcel.readInt();
        this.labelList = parcel.createTypedArrayList(SleepAudioLabel.CREATOR);
        this.chapter = parcel.readString();
        this.isShow = parcel.readInt();
        this.tabList = parcel.createTypedArrayList(Tab.CREATOR);
        this.playRecord = (PlayRecordInfo) parcel.readParcelable(PlayRecordInfo.class.getClassLoader());
        this.resPurchaseInfo = (ResPurchaseInfo) parcel.readParcelable(RespBreathExerciseInfo.class.getClassLoader());
        this.isVip = parcel.readInt();
        this.seriesAudioUseSize = parcel.readInt();
        this.introductionImgs = parcel.createStringArrayList();
        this.positionId = parcel.readInt();
        this.positionName = parcel.readString();
        this.decompressType = parcel.readInt();
        this.bigBackGroundImg = parcel.readString();
        this.memberBenefitsBigUrl = parcel.readString();
        this.memberBenefitsUrl = parcel.readString();
        this.supportAppVersion = parcel.readString();
        this.serviceProviderName = parcel.readString();
        this.serviceProviderLogo = parcel.readString();
        this.purchasedStatus = parcel.readInt();
        this.firstTitle = parcel.readString();
        this.secondTitle = parcel.readString();
        this.displayStyle = parcel.readInt();
        this.audioUrl = parcel.readString();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAudioUrl() {
        return this.audioUrl;
    }

    public void setAudioUrl(String str) {
        this.audioUrl = str;
    }

    public String getImgIcon() {
        return this.imgIcon;
    }

    public void setImgIcon(String str) {
        this.imgIcon = str;
    }

    public String getBackGroundImg() {
        return this.backGroundImg;
    }

    public void setBackGroundImg(String str) {
        this.backGroundImg = str;
    }

    public int getLabelType() {
        return this.labelType;
    }

    public void setLabelType(int i) {
        this.labelType = i;
    }

    public String getShareTitle() {
        return this.shareTitle;
    }

    public void setShareTitle(String str) {
        this.shareTitle = str;
    }

    public String getShareDescription() {
        return this.shareDescription;
    }

    public void setShareDescription(String str) {
        this.shareDescription = str;
    }

    public String getShareIcon() {
        return this.shareIcon;
    }

    public void setShareIcon(String str) {
        this.shareIcon = str;
    }

    public int getHeatCount() {
        return this.heatCount;
    }

    public void setHeatCount(int i) {
        this.heatCount = i;
    }

    public int getAudioType() {
        return this.audioType;
    }

    public void setAudioType(int i) {
        this.audioType = i;
    }

    public String getAudioIcon() {
        return this.audioIcon;
    }

    public void setAudioIcon(String str) {
        this.audioIcon = str;
    }

    public int getIconType() {
        return this.iconType;
    }

    public void setIconType(int i) {
        this.iconType = i;
    }

    public String getSmallImage() {
        return this.smallImage;
    }

    public void setSmallImage(String str) {
        this.smallImage = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public List<BootPageTxt> getBootPageTxts() {
        return this.bootPageTxts;
    }

    public void setBootPageTxts(List<BootPageTxt> list) {
        this.bootPageTxts = list;
    }

    public String getAudioDescription() {
        return this.audioDescription;
    }

    public void setAudioDescription(String str) {
        this.audioDescription = str;
    }

    public int getParentAudioId() {
        return this.parentAudioId;
    }

    public void setParentAudioId(int i) {
        this.parentAudioId = i;
    }

    public int getInfoType() {
        return this.infoType;
    }

    public void setInfoType(int i) {
        this.infoType = i;
    }

    public long getAudioDuration() {
        return this.audioDuration;
    }

    public void setAudioDuration(long j) {
        this.audioDuration = j;
    }

    public int getPlaceHolder() {
        return this.placeHolder;
    }

    public void setPlaceHolder(int i) {
        this.placeHolder = i;
    }

    public int getSeriesAudioSize() {
        return this.seriesAudioSize;
    }

    public void setSeriesAudioSize(int i) {
        this.seriesAudioSize = i;
    }

    public List<SleepAudioLabel> getLabelList() {
        return this.labelList;
    }

    public void setLabelList(List<SleepAudioLabel> list) {
        this.labelList = list;
    }

    public String getChapter() {
        return this.chapter;
    }

    public void setChapter(String str) {
        this.chapter = str;
    }

    public int getIsShow() {
        return this.isShow;
    }

    public void setIsShow(int i) {
        this.isShow = i;
    }

    public List<Tab> getTabList() {
        return this.tabList;
    }

    public void setTabList(List<Tab> list) {
        this.tabList = list;
    }

    public PlayRecordInfo getPlayRecord() {
        return this.playRecord;
    }

    public void setPlayRecord(PlayRecordInfo playRecordInfo) {
        this.playRecord = playRecordInfo;
    }

    public ResPurchaseInfo getResPurchaseInfo() {
        return this.resPurchaseInfo;
    }

    public void setResPurchaseInfo(ResPurchaseInfo resPurchaseInfo) {
        this.resPurchaseInfo = resPurchaseInfo;
    }

    public int getIsVip() {
        return this.isVip;
    }

    public void setIsVip(int i) {
        this.isVip = i;
    }

    public int getSeriesAudioUseSize() {
        return this.seriesAudioUseSize;
    }

    public void setSeriesAudioUseSize(int i) {
        this.seriesAudioUseSize = i;
    }

    public List<String> getIntroductionImgs() {
        return this.introductionImgs;
    }

    public void setIntroductionImgs(List<String> list) {
        this.introductionImgs = list;
    }

    public int getPositionId() {
        return this.positionId;
    }

    public void setPositionId(int i) {
        this.positionId = i;
    }

    public String getPositionName() {
        return this.positionName;
    }

    public void setPositionName(String str) {
        this.positionName = str;
    }

    public int getDecompressType() {
        return this.decompressType;
    }

    public void setDecompressType(int i) {
        this.decompressType = i;
    }

    public String getBigBackGroundImg() {
        return this.bigBackGroundImg;
    }

    public void setBigBackGroundImg(String str) {
        this.bigBackGroundImg = str;
    }

    public String getMemberBenefitsBigUrl() {
        return this.memberBenefitsBigUrl;
    }

    public void setMemberBenefitsBigUrl(String str) {
        this.memberBenefitsBigUrl = str;
    }

    public String getMemberBenefitsUrl() {
        return this.memberBenefitsUrl;
    }

    public void setMemberBenefitsUrl(String str) {
        this.memberBenefitsUrl = str;
    }

    public String getSupportAppVersion() {
        return this.supportAppVersion;
    }

    public void setSupportAppVersion(String str) {
        this.supportAppVersion = str;
    }

    public String getServiceProviderName() {
        return this.serviceProviderName;
    }

    public void setServiceProviderName(String str) {
        this.serviceProviderName = str;
    }

    public String getServiceProviderLogo() {
        return this.serviceProviderLogo;
    }

    public void setServiceProviderLogo(String str) {
        this.serviceProviderLogo = str;
    }

    public int getPurchasedStatus() {
        return this.purchasedStatus;
    }

    public void setPurchasedStatus(int i) {
        this.purchasedStatus = i;
    }

    public String getFirstTitle() {
        return this.firstTitle;
    }

    public void setFirstTitle(String str) {
        this.firstTitle = str;
    }

    public String getSecondTitle() {
        return this.secondTitle;
    }

    public void setSecondTitle(String str) {
        this.secondTitle = str;
    }

    public int getDisplayStyle() {
        return this.displayStyle;
    }

    public void setDisplayStyle(int i) {
        this.displayStyle = i;
    }

    public String getAuditionUrl() {
        return this.auditionUrl;
    }

    public void setAuditionUrl(String str) {
        this.auditionUrl = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.audioUrl);
        parcel.writeString(this.imgIcon);
        parcel.writeString(this.backGroundImg);
        parcel.writeInt(this.labelType);
        parcel.writeString(this.shareTitle);
        parcel.writeString(this.shareDescription);
        parcel.writeString(this.shareIcon);
        parcel.writeInt(this.heatCount);
        parcel.writeInt(this.audioType);
        parcel.writeString(this.audioIcon);
        parcel.writeInt(this.iconType);
        parcel.writeString(this.smallImage);
        parcel.writeString(this.title);
        parcel.writeTypedList(this.bootPageTxts);
        parcel.writeString(this.audioDescription);
        parcel.writeInt(this.parentAudioId);
        parcel.writeInt(this.infoType);
        parcel.writeLong(this.audioDuration);
        parcel.writeInt(this.placeHolder);
        parcel.writeInt(this.seriesAudioSize);
        parcel.writeTypedList(this.labelList);
        parcel.writeString(this.chapter);
        parcel.writeInt(this.isShow);
        parcel.writeTypedList(this.tabList);
        parcel.writeParcelable(this.playRecord, i);
        parcel.writeParcelable(this.resPurchaseInfo, i);
        parcel.writeInt(this.isVip);
        parcel.writeInt(this.seriesAudioUseSize);
        parcel.writeStringList(this.introductionImgs);
        parcel.writeInt(this.positionId);
        parcel.writeString(this.positionName);
        parcel.writeInt(this.decompressType);
        parcel.writeString(this.bigBackGroundImg);
        parcel.writeString(this.memberBenefitsBigUrl);
        parcel.writeString(this.memberBenefitsUrl);
        parcel.writeString(this.supportAppVersion);
        parcel.writeString(this.serviceProviderName);
        parcel.writeString(this.serviceProviderLogo);
        parcel.writeInt(this.purchasedStatus);
        parcel.writeString(this.firstTitle);
        parcel.writeString(this.secondTitle);
        parcel.writeInt(this.displayStyle);
        parcel.writeString(this.auditionUrl);
    }

    public String toString() {
        return "SleepAudio{id=" + this.id + ", name='" + this.name + "', audioUrl='" + this.audioUrl + "', imgIcon='" + this.imgIcon + "', backGroundImg='" + this.backGroundImg + "', labelType=" + this.labelType + ", shareTitle='" + this.shareTitle + "', shareDescription='" + this.shareDescription + "', shareIcon='" + this.shareIcon + "', heatCount=" + this.heatCount + ", audioType=" + this.audioType + ", audioIcon='" + this.audioIcon + "', iconType=" + this.iconType + ", smallImage='" + this.smallImage + "', title='" + this.title + "', bootPageTxts=" + this.bootPageTxts + ", audioDescription='" + this.audioDescription + "', parentAudioId=" + this.parentAudioId + ", infoType=" + this.infoType + ", audioDuration=" + this.audioDuration + ", placeHolder=" + this.placeHolder + ", seriesAudioSize=" + this.seriesAudioSize + ", labelList=" + this.labelList + ", chapter='" + this.chapter + "', isShow=" + this.isShow + ", tabList=" + this.tabList + ", playRecord=" + this.playRecord + ", resPurchaseInfo=" + this.resPurchaseInfo + ", isVip=" + this.isVip + ", seriesAudioUseSize=" + this.seriesAudioUseSize + ", introductionImgs=" + this.introductionImgs + ", positionId=" + this.positionId + ", positionName='" + this.positionName + "', decompressType=" + this.decompressType + ", bigBackGroundImg='" + this.bigBackGroundImg + "', memberBenefitsBigUrl='" + this.memberBenefitsBigUrl + "', memberBenefitsUrl='" + this.memberBenefitsUrl + "', supportAppVersion='" + this.supportAppVersion + "', serviceProviderName='" + this.serviceProviderName + "', serviceProviderLogo='" + this.serviceProviderLogo + "', purchasedStatus=" + this.purchasedStatus + ", firstTitle='" + this.firstTitle + "', secondTitle='" + this.secondTitle + "', displayStyle=" + this.displayStyle + ", auditionUrl='" + this.auditionUrl + "'}";
    }
}
