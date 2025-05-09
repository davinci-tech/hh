package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes3.dex */
public class GlobalSearchContent implements Parcelable {
    public static final Parcelable.Creator<GlobalSearchContent> CREATOR = new Parcelable.Creator<GlobalSearchContent>() { // from class: com.huawei.health.marketing.request.GlobalSearchContent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalSearchContent createFromParcel(Parcel parcel) {
            return new GlobalSearchContent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalSearchContent[] newArray(int i) {
            return new GlobalSearchContent[i];
        }
    };
    public static final int FUNCTION_PLAN_TYPE = 4;
    private static final String HTML_SUFFIX = ".html";
    public static final int MEMBER_FLAG = 1;
    public static final int PAID_COURSE_FLAG = 1;
    private static final String TAG = "GlobalSearchContent";
    public static final int VIP_COURSE_FLAG = 2;
    private static final String VMALL_DETAIL_URL_PREFIX = "/product/";
    private static final String VMALL_IMAGE_FILENAME_PREFIX = "428_428_";
    private static final String VMALL_IMAGE_URL_PREFIX = "/pimages";

    @SerializedName("activityName")
    private String activityName;

    @SerializedName("appVersionList")
    private List<AppVersionInfo> appVersionList;

    @SerializedName("audioType")
    private int audioType;

    @SerializedName("auditionUrl")
    private String auditionUrl;

    @SerializedName("author")
    private String author;

    @SerializedName("calorie")
    private int calorie;

    @SerializedName("commodityFlag")
    private int commodityFlag;

    @SerializedName("currencyUnit")
    private String currencyUnit;

    @SerializedName("date")
    private String date;

    @SerializedName("deepLink")
    private String deepLink;

    @SerializedName("description")
    private String description;

    @SerializedName("difficulty")
    private int difficulty;

    @SerializedName("disdepart")
    private List<String> disdepart;

    @SerializedName("duration")
    private int duration;

    @SerializedName("enableNewUrl")
    private int enableNewUrl;

    @SerializedName("goodRate")
    private String goodRate;

    @SerializedName("gotourl")
    private String gotourl;

    @SerializedName("heatCount")
    private long heatCount;

    @SerializedName("highLights")
    private List<HighLight> highLights;

    @SerializedName("iconUrl")
    private String iconUrl;

    @SerializedName("id")
    private String id;

    @SerializedName("infoType")
    private int infoType;

    @SerializedName("isInv")
    private int isInv;

    @SerializedName("labels")
    private List<String> labels;

    @SerializedName("lessonName")
    private String lessonName;

    @SerializedName("lessonType")
    private int lessonType;

    @SerializedName("locale")
    private String locale;

    @SerializedName("memberShip")
    private int memberShip;

    @SerializedName("name")
    private String name;

    @SerializedName("operationGroupId")
    private int operationGroupId;

    @SerializedName("photoName")
    private String photoName;

    @SerializedName("photoPath")
    private String photoPath;

    @SerializedName("picUrl")
    private String picUrl;

    @SerializedName(ParsedFieldTag.PRICE)
    private float price;

    @SerializedName("priceAccurate")
    private String priceAccurate;

    @SerializedName("priceMode")
    private int priceMode;

    @SerializedName("productId")
    private long productId;

    @SerializedName("promoLabels")
    private List<String> promoLabels;

    @SerializedName("promoPrice")
    private float promoPrice;

    @SerializedName("promoPriceAccurate")
    private String promoPriceAccurate;

    @SerializedName("promotionInfo")
    private String promotionInfo;

    @SerializedName("rateCount")
    private int rateCount;

    @SerializedName("seriesCourseId")
    private String seriesCourseId;

    @SerializedName("seriesCourseName")
    private String seriesCourseName;

    @SerializedName("status")
    private int status;

    @SerializedName("subCourse")
    private boolean subCourse;

    @SerializedName("tabs")
    private List<HealthDictSearchContent> tabs;

    @SerializedName("target")
    private String target;

    @SerializedName("targetPage")
    private String targetPage;

    @SerializedName("targetPageType")
    private int targetPageType;

    @SerializedName("title")
    private String title;

    @SerializedName("totalChapters")
    private int totalChapters;

    @SerializedName("type")
    private int type;

    @SerializedName("version")
    private String version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GlobalSearchContent() {
    }

    public GlobalSearchContent(Parcel parcel) {
        this.id = parcel.readString();
        this.activityName = parcel.readString();
        this.lessonName = parcel.readString();
        this.lessonType = parcel.readInt();
        this.infoType = parcel.readInt();
        this.audioType = parcel.readInt();
        this.auditionUrl = parcel.readString();
        this.labels = parcel.createStringArrayList();
        this.date = parcel.readString();
        this.title = parcel.readString();
        this.author = parcel.readString();
        this.description = parcel.readString();
        this.picUrl = parcel.readString();
        this.deepLink = parcel.readString();
        this.difficulty = parcel.readInt();
        this.duration = parcel.readInt();
        this.calorie = parcel.readInt();
        this.commodityFlag = parcel.readInt();
        this.productId = parcel.readLong();
        this.name = parcel.readString();
        this.photoPath = parcel.readString();
        this.photoName = parcel.readString();
        this.price = parcel.readFloat();
        this.priceAccurate = parcel.readString();
        this.promoPrice = parcel.readFloat();
        this.promoPriceAccurate = parcel.readString();
        this.promotionInfo = parcel.readString();
        this.priceMode = parcel.readInt();
        this.rateCount = parcel.readInt();
        this.goodRate = parcel.readString();
        this.promoLabels = parcel.createStringArrayList();
        this.isInv = parcel.readInt();
        this.currencyUnit = parcel.readString();
        this.type = parcel.readInt();
        this.target = parcel.readString();
        this.operationGroupId = parcel.readInt();
        this.locale = parcel.readString();
        this.appVersionList = parcel.createTypedArrayList(AppVersionInfo.CREATOR);
        this.iconUrl = parcel.readString();
        this.targetPageType = parcel.readInt();
        this.targetPage = parcel.readString();
        this.status = parcel.readInt();
        this.memberShip = parcel.readInt();
        this.tabs = parcel.createTypedArrayList(HealthDictSearchContent.CREATOR);
        this.gotourl = parcel.readString();
        this.disdepart = parcel.createStringArrayList();
        this.totalChapters = parcel.readInt();
        this.heatCount = parcel.readLong();
        this.seriesCourseId = parcel.readString();
        this.seriesCourseName = parcel.readString();
        this.version = parcel.readString();
        this.subCourse = parcel.readInt() != 0;
        this.highLights = parcel.createTypedArrayList(HighLight.CREATOR);
        this.enableNewUrl = parcel.readInt();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getActivityName() {
        return TextUtils.isEmpty(this.activityName) ? "" : this.activityName;
    }

    public void setActivityName(String str) {
        this.activityName = str;
    }

    public String getLessonName() {
        return TextUtils.isEmpty(this.lessonName) ? "" : this.lessonName;
    }

    public void setLessonName(String str) {
        this.lessonName = str;
    }

    public int getLessonType() {
        return this.lessonType;
    }

    public void setLessonType(int i) {
        this.lessonType = i;
    }

    public int getInfoType() {
        return this.infoType;
    }

    public void setInfoType(int i) {
        this.infoType = i;
    }

    public String getAuditionUrl() {
        return TextUtils.isEmpty(this.auditionUrl) ? "" : this.auditionUrl;
    }

    public void setAuditionUrl(String str) {
        this.auditionUrl = str;
    }

    public List<String> getLabels() {
        return this.labels;
    }

    public void setLabels(List<String> list) {
        this.labels = list;
    }

    public String getDate() {
        return TextUtils.isEmpty(this.date) ? "" : this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getTitle() {
        return TextUtils.isEmpty(this.title) ? "" : this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getAuthor() {
        return TextUtils.isEmpty(this.author) ? "" : this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public String getDescription() {
        return TextUtils.isEmpty(this.description) ? "" : this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getPicUrl() {
        return TextUtils.isEmpty(this.picUrl) ? "" : this.picUrl;
    }

    public void setPicUrl(String str) {
        this.picUrl = str;
    }

    public String getDeepLink() {
        return TextUtils.isEmpty(this.deepLink) ? "" : this.deepLink;
    }

    public void setDeepLink(String str) {
        this.deepLink = str;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int i) {
        this.difficulty = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public int getCalorie() {
        return this.calorie;
    }

    public void setCalorie(int i) {
        this.calorie = i;
    }

    public int getCommodityFlag() {
        return this.commodityFlag;
    }

    public void setCommodityFlag(int i) {
        this.commodityFlag = i;
    }

    public long getProductId() {
        return this.productId;
    }

    public void setProductId(long j) {
        this.productId = j;
    }

    public String getName() {
        return TextUtils.isEmpty(this.name) ? "" : this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPhotoPath() {
        return this.photoPath;
    }

    public void setPhotoPath(String str) {
        this.photoPath = str;
    }

    public String getPhotoName() {
        return this.photoName;
    }

    public void setPhotoName(String str) {
        this.photoName = str;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float f) {
        this.price = f;
    }

    public String getPriceAccurate() {
        return this.priceAccurate;
    }

    public void setPriceAccurate(String str) {
        this.priceAccurate = str;
    }

    public float getPromoPrice() {
        return this.promoPrice;
    }

    public void setPromoPrice(float f) {
        this.promoPrice = f;
    }

    public String getPromoPriceAccurate() {
        return this.promoPriceAccurate;
    }

    public void setPromoPriceAccurate(String str) {
        this.promoPriceAccurate = str;
    }

    public String getPromotionInfo() {
        return this.promotionInfo;
    }

    public void setPromotionInfo(String str) {
        this.promotionInfo = str;
    }

    public int getPriceMode() {
        return this.priceMode;
    }

    public void setPriceMode(int i) {
        this.priceMode = i;
    }

    public int getRateCount() {
        return this.rateCount;
    }

    public void setRateCount(int i) {
        this.rateCount = i;
    }

    public String getGoodRate() {
        return this.goodRate;
    }

    public void setGoodRate(String str) {
        this.goodRate = str;
    }

    public List<String> getPromoLabels() {
        return this.promoLabels;
    }

    public void setPromoLabels(List<String> list) {
        this.promoLabels = list;
    }

    public int getIsInv() {
        return this.isInv;
    }

    public void setIsInv(int i) {
        this.isInv = i;
    }

    public String getCurrencyUnit() {
        return this.currencyUnit;
    }

    public void setCurrencyUnit(String str) {
        this.currencyUnit = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public int getOperationGroupId() {
        return this.operationGroupId;
    }

    public void setOperationGroupId(int i) {
        this.operationGroupId = i;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public List<AppVersionInfo> getAppVersionList() {
        return this.appVersionList;
    }

    public void setAppVersionList(List<AppVersionInfo> list) {
        this.appVersionList = list;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public int getTargetPageType() {
        return this.targetPageType;
    }

    public void setTargetPageType(int i) {
        this.targetPageType = i;
    }

    public String getTargetPage() {
        return this.targetPage;
    }

    public void setTargetPage(String str) {
        this.targetPage = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getMemberShip() {
        return this.memberShip;
    }

    public void setMemberShip(int i) {
        this.memberShip = i;
    }

    public List<HealthDictSearchContent> getTabs() {
        return this.tabs;
    }

    public void setTabs(List<HealthDictSearchContent> list) {
        this.tabs = list;
    }

    public String getGotourl() {
        return this.gotourl;
    }

    public void setGotourl(String str) {
        this.gotourl = str;
    }

    public List<String> getDisdepart() {
        return this.disdepart;
    }

    public void setDisdepart(List<String> list) {
        this.disdepart = list;
    }

    public int getTotalChapters() {
        return this.totalChapters;
    }

    public void setTotalChapters(int i) {
        this.totalChapters = i;
    }

    public String getSeriesCourseName() {
        return TextUtils.isEmpty(this.seriesCourseName) ? "" : this.seriesCourseName;
    }

    public void setSeriesCourseName(String str) {
        this.seriesCourseName = str;
    }

    public boolean isSubCourse() {
        return this.subCourse;
    }

    public void setSubCourse(boolean z) {
        this.subCourse = z;
    }

    public long getHeatCount() {
        return this.heatCount;
    }

    public void setHeatCount(long j) {
        this.heatCount = j;
    }

    public int getAudioType() {
        return this.audioType;
    }

    public void setAudioType(int i) {
        this.audioType = i;
    }

    public String getVersion() {
        return TextUtils.isEmpty(this.version) ? "" : this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getSeriesCourseId() {
        return TextUtils.isEmpty(this.seriesCourseId) ? "" : this.seriesCourseId;
    }

    public void setSeriesCourseId(String str) {
        this.seriesCourseId = str;
    }

    public List<HighLight> getHighLights() {
        return this.highLights;
    }

    public void setHighLights(List<HighLight> list) {
        this.highLights = list;
    }

    public int getEnableNewUrl() {
        return this.enableNewUrl;
    }

    public void setEnableNewUrl(int i) {
        this.enableNewUrl = i;
    }

    public String getProductDetailUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("domainSearchVmallDetail") + VMALL_DETAIL_URL_PREFIX + String.valueOf(this.productId) + HTML_SUFFIX;
    }

    public String getProductImageUrl() {
        if (this.photoName == null || this.photoPath == null) {
            LogUtil.b(TAG, "getProductImageUrl photoName or photoPath is null");
            return "";
        }
        return GRSManager.a(BaseApplication.getContext()).getUrl("domainSearchVmallImg") + VMALL_IMAGE_URL_PREFIX + this.photoPath + VMALL_IMAGE_FILENAME_PREFIX + this.photoName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.activityName);
        parcel.writeString(this.lessonName);
        parcel.writeInt(this.lessonType);
        parcel.writeInt(this.infoType);
        parcel.writeInt(this.audioType);
        parcel.writeString(this.auditionUrl);
        parcel.writeStringList(this.labels);
        parcel.writeString(this.date);
        parcel.writeString(this.title);
        parcel.writeString(this.author);
        parcel.writeString(this.description);
        parcel.writeString(this.picUrl);
        parcel.writeString(this.deepLink);
        parcel.writeInt(this.difficulty);
        parcel.writeInt(this.duration);
        parcel.writeInt(this.calorie);
        parcel.writeInt(this.commodityFlag);
        parcel.writeLong(this.productId);
        parcel.writeString(this.name);
        parcel.writeString(this.photoPath);
        parcel.writeString(this.photoName);
        parcel.writeFloat(this.price);
        parcel.writeString(this.priceAccurate);
        parcel.writeFloat(this.promoPrice);
        parcel.writeString(this.promoPriceAccurate);
        parcel.writeString(this.promotionInfo);
        parcel.writeInt(this.priceMode);
        parcel.writeInt(this.rateCount);
        parcel.writeString(this.goodRate);
        parcel.writeStringList(this.promoLabels);
        parcel.writeInt(this.isInv);
        parcel.writeString(this.currencyUnit);
        parcel.writeInt(this.type);
        parcel.writeString(this.target);
        parcel.writeInt(this.operationGroupId);
        parcel.writeString(this.locale);
        parcel.writeTypedList(this.appVersionList);
        parcel.writeString(this.iconUrl);
        parcel.writeInt(this.targetPageType);
        parcel.writeString(this.targetPage);
        parcel.writeInt(this.status);
        parcel.writeInt(this.memberShip);
        parcel.writeTypedList(this.tabs);
        parcel.writeString(this.gotourl);
        parcel.writeStringList(this.disdepart);
        parcel.writeInt(this.totalChapters);
        parcel.writeLong(this.heatCount);
        parcel.writeString(this.seriesCourseId);
        parcel.writeString(this.seriesCourseName);
        parcel.writeString(this.version);
        parcel.writeInt(this.subCourse ? 1 : 0);
        parcel.writeTypedList(this.highLights);
        parcel.writeInt(this.enableNewUrl);
    }
}
