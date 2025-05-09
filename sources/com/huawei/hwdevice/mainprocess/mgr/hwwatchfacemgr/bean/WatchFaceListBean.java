package com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class WatchFaceListBean implements Parcelable {
    public static final Parcelable.Creator<WatchFaceListBean> CREATOR = new Parcelable.Creator<WatchFaceListBean>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.bean.WatchFaceListBean.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: bIa_, reason: merged with bridge method [inline-methods] */
        public WatchFaceListBean createFromParcel(Parcel parcel) {
            return new WatchFaceListBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public WatchFaceListBean[] newArray(int i) {
            return new WatchFaceListBean[i];
        }
    };
    private static final int DESCRIBE_CONTENT_DEFAULT = 0;
    private static final int DISCOUNT_LIST_DEFAULT_SIZE = 100;
    private String currency;
    private String fileHost;
    private String messageHashCode;
    private int recordCount;

    @SerializedName("resultcode")
    private String resultCode;

    @SerializedName("resultinfo")
    private String resultInfo;

    @SerializedName("recNum")
    private int resultNumber;

    @SerializedName("shareDESC")
    private String shareDescription;

    @SerializedName("shareURL")
    private String shareUrl;
    private String symbol;
    private int totalPage;

    @SerializedName("list")
    private List<WatchFaceBean> watchFaceBeanList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WatchFaceListBean() {
    }

    protected WatchFaceListBean(Parcel parcel) {
        this.messageHashCode = parcel.readString();
        this.symbol = parcel.readString();
        this.resultNumber = parcel.readInt();
        this.totalPage = parcel.readInt();
        this.recordCount = parcel.readInt();
        this.fileHost = parcel.readString();
        this.resultInfo = parcel.readString();
        this.currency = parcel.readString();
        this.shareUrl = parcel.readString();
        this.resultCode = parcel.readString();
        this.shareDescription = parcel.readString();
        this.watchFaceBeanList = parcel.createTypedArrayList(WatchFaceBean.CREATOR);
    }

    public String getMessageHashCode() {
        return this.messageHashCode;
    }

    public void setMessageHashCode(String str) {
        this.messageHashCode = str;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public int getResultNumber() {
        return this.resultNumber;
    }

    public void setResultNumber(int i) {
        this.resultNumber = i;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int i) {
        this.totalPage = i;
    }

    public int getRecordCount() {
        return this.recordCount;
    }

    public void setRecordCount(int i) {
        this.recordCount = i;
    }

    public String getFileHost() {
        return this.fileHost;
    }

    public void setFileHost(String str) {
        this.fileHost = str;
    }

    public String getResultInfo() {
        return this.resultInfo;
    }

    public void setResultInfo(String str) {
        this.resultInfo = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String str) {
        this.shareUrl = str;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public String getShareDescription() {
        return this.shareDescription;
    }

    public void setShareDescription(String str) {
        this.shareDescription = str;
    }

    public List<WatchFaceBean> getWatchFaceBeanList() {
        return this.watchFaceBeanList;
    }

    public void setWatchFaceBeanList(List<WatchFaceBean> list) {
        this.watchFaceBeanList = list;
    }

    public static class WatchFaceBean implements Parcelable {
        public static final Parcelable.Creator<WatchFaceBean> CREATOR = new Parcelable.Creator<WatchFaceBean>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.bean.WatchFaceListBean.WatchFaceBean.5
            @Override // android.os.Parcelable.Creator
            /* renamed from: bIb_, reason: merged with bridge method [inline-methods] */
            public WatchFaceBean createFromParcel(Parcel parcel) {
                return new WatchFaceBean(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public WatchFaceBean[] newArray(int i) {
                return new WatchFaceBean[i];
            }
        };

        @SerializedName("addtime")
        private String addTime;
        private String author;

        @SerializedName("briefinfo")
        private String briefInfo;

        @SerializedName("twcategory")
        private int category;
        private String collectCount;

        @SerializedName("commentNum")
        private int commentNumber;
        private String designer;

        @SerializedName("discountList")
        private List<DiscountListBean> discountList;

        @SerializedName("downloadcount")
        private int downloadCount;
        private String font;

        @SerializedName("hashcode")
        private List<HashCodeBean> hashCode;

        @SerializedName("hashcodelist")
        private List<HashCodeListBean> hashCodeList;
        private String hitopid;
        private int id;
        private String isoCode;

        @SerializedName("LABEL")
        private String label;

        @SerializedName("lastupdate")
        private String lastUpdate;
        private String log;
        private String logo;
        private String name;

        @SerializedName("needaccount")
        private String needAccount;

        @SerializedName("osversion")
        private String osVersion;

        @SerializedName("packagefile")
        private String packageFile;

        @SerializedName("packagename")
        private String packageName;
        private String praiseCount;

        @SerializedName("prerescount")
        private int preresCount;
        private String previews;

        @SerializedName("shareURL")
        private String shareUrl;
        private int size;
        private String stars;

        @SerializedName("title-cn")
        private String title;

        @SerializedName("title-local")
        private String titleLocal;
        private int type;
        private String version;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public WatchFaceBean() {
        }

        protected WatchFaceBean(Parcel parcel) {
            this.hitopid = parcel.readString();
            this.log = parcel.readString();
            this.praiseCount = parcel.readString();
            this.title = parcel.readString();
            this.type = parcel.readInt();
            this.preresCount = parcel.readInt();
            this.needAccount = parcel.readString();
            this.category = parcel.readInt();
            this.logo = parcel.readString();
            this.label = parcel.readString();
            this.downloadCount = parcel.readInt();
            this.id = parcel.readInt();
            this.author = parcel.readString();
            this.briefInfo = parcel.readString();
            this.packageName = parcel.readString();
            this.collectCount = parcel.readString();
            this.designer = parcel.readString();
            this.stars = parcel.readString();
            this.version = parcel.readString();
            this.titleLocal = parcel.readString();
            this.commentNumber = parcel.readInt();
            this.size = parcel.readInt();
            this.isoCode = parcel.readString();
            this.addTime = parcel.readString();
            this.packageFile = parcel.readString();
            this.name = parcel.readString();
            this.previews = parcel.readString();
            this.shareUrl = parcel.readString();
            this.lastUpdate = parcel.readString();
            this.osVersion = parcel.readString();
            this.font = parcel.readString();
            ArrayList arrayList = new ArrayList(100);
            this.discountList = arrayList;
            parcel.readList(arrayList, DiscountListBean.class.getClassLoader());
            this.hashCode = parcel.createTypedArrayList(HashCodeBean.CREATOR);
            this.hashCodeList = parcel.createTypedArrayList(HashCodeListBean.CREATOR);
        }

        public String getHitopid() {
            return this.hitopid;
        }

        public void setHitopid(String str) {
            this.hitopid = str;
        }

        public String getLog() {
            return this.log;
        }

        public void setLog(String str) {
            this.log = str;
        }

        public String getPraiseCount() {
            return this.praiseCount;
        }

        public void setPraiseCount(String str) {
            this.praiseCount = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public int getPreresCount() {
            return this.preresCount;
        }

        public void setPreresCount(int i) {
            this.preresCount = i;
        }

        public String getNeedAccount() {
            return this.needAccount;
        }

        public void setNeedAccount(String str) {
            this.needAccount = str;
        }

        public int getCategory() {
            return this.category;
        }

        public void setCategory(int i) {
            this.category = i;
        }

        public String getLogo() {
            return this.logo;
        }

        public void setLogo(String str) {
            this.logo = str;
        }

        public String getLabel() {
            return this.label;
        }

        public void setLabel(String str) {
            this.label = str;
        }

        public int getDownloadCount() {
            return this.downloadCount;
        }

        public void setDownloadCount(int i) {
            this.downloadCount = i;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getAuthor() {
            return this.author;
        }

        public void setAuthor(String str) {
            this.author = str;
        }

        public String getBriefInfo() {
            return this.briefInfo;
        }

        public void setBriefInfo(String str) {
            this.briefInfo = str;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public void setPackageName(String str) {
            this.packageName = str;
        }

        public String getCollectCount() {
            return this.collectCount;
        }

        public void setCollectCount(String str) {
            this.collectCount = str;
        }

        public String getDesigner() {
            return this.designer;
        }

        public void setDesigner(String str) {
            this.designer = str;
        }

        public String getStars() {
            return this.stars;
        }

        public void setStars(String str) {
            this.stars = str;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public String getTitleLocal() {
            return this.titleLocal;
        }

        public void setTitleLocal(String str) {
            this.titleLocal = str;
        }

        public int getCommentNumber() {
            return this.commentNumber;
        }

        public void setCommentNumber(int i) {
            this.commentNumber = i;
        }

        public int getSize() {
            return this.size;
        }

        public void setSize(int i) {
            this.size = i;
        }

        public String getIsoCode() {
            return this.isoCode;
        }

        public void setIsoCode(String str) {
            this.isoCode = str;
        }

        public String getAddTime() {
            return this.addTime;
        }

        public void setAddTime(String str) {
            this.addTime = str;
        }

        public String getPackageFile() {
            return this.packageFile;
        }

        public void setPackageFile(String str) {
            this.packageFile = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getPreviews() {
            return this.previews;
        }

        public void setPreviews(String str) {
            this.previews = str;
        }

        public String getShareUrl() {
            return this.shareUrl;
        }

        public void setShareUrl(String str) {
            this.shareUrl = str;
        }

        public String getLastUpdate() {
            return this.lastUpdate;
        }

        public void setLastUpdate(String str) {
            this.lastUpdate = str;
        }

        public String getOsVersion() {
            return this.osVersion;
        }

        public void setOsVersion(String str) {
            this.osVersion = str;
        }

        public String getFont() {
            return this.font;
        }

        public void setFont(String str) {
            this.font = str;
        }

        public List<DiscountListBean> getDiscountList() {
            return this.discountList;
        }

        public void setDiscountList(List<DiscountListBean> list) {
            this.discountList = list;
        }

        public List<HashCodeBean> getHashCode() {
            return this.hashCode;
        }

        public void setHashCode(List<HashCodeBean> list) {
            this.hashCode = list;
        }

        public List<HashCodeListBean> getHashCodeList() {
            return this.hashCodeList;
        }

        public void setHashCodeList(List<HashCodeListBean> list) {
            this.hashCodeList = list;
        }

        public static class DiscountListBean implements Parcelable {
            public static final Parcelable.Creator<DiscountListBean> CREATOR = new Parcelable.Creator<DiscountListBean>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.bean.WatchFaceListBean.WatchFaceBean.DiscountListBean.4
                @Override // android.os.Parcelable.Creator
                /* renamed from: bIc_, reason: merged with bridge method [inline-methods] */
                public DiscountListBean createFromParcel(Parcel parcel) {
                    return new DiscountListBean(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public DiscountListBean[] newArray(int i) {
                    return new DiscountListBean[i];
                }
            };
            private String discountCode;
            private String discountEndTime;
            private String discountId;
            private String discountPrice;
            private String discountType;
            private String markText;
            private String markUrl;
            private String startTime;

            @SerializedName("UPDATEDATE")
            private String updateDate;

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public DiscountListBean() {
            }

            protected DiscountListBean(Parcel parcel) {
                this.discountCode = parcel.readString();
                this.discountEndTime = parcel.readString();
                this.markUrl = parcel.readString();
                this.discountPrice = parcel.readString();
                this.markText = parcel.readString();
                this.startTime = parcel.readString();
                this.discountType = parcel.readString();
                this.updateDate = parcel.readString();
                this.discountId = parcel.readString();
            }

            public String getDiscountCode() {
                return this.discountCode;
            }

            public void setDiscountCode(String str) {
                this.discountCode = str;
            }

            public String getDiscountEndTime() {
                return this.discountEndTime;
            }

            public void setDiscountEndTime(String str) {
                this.discountEndTime = str;
            }

            public String getMarkUrl() {
                return this.markUrl;
            }

            public void setMarkUrl(String str) {
                this.markUrl = str;
            }

            public String getDiscountPrice() {
                return this.discountPrice;
            }

            public void setDiscountPrice(String str) {
                this.discountPrice = str;
            }

            public String getMarkText() {
                return this.markText;
            }

            public void setMarkText(String str) {
                this.markText = str;
            }

            public String getStartTime() {
                return this.startTime;
            }

            public void setStartTime(String str) {
                this.startTime = str;
            }

            public String getDiscountType() {
                return this.discountType;
            }

            public void setDiscountType(String str) {
                this.discountType = str;
            }

            public String getUpdateDate() {
                return this.updateDate;
            }

            public void setUpdateDate(String str) {
                this.updateDate = str;
            }

            public String getDiscountId() {
                return this.discountId;
            }

            public void setDiscountId(String str) {
                this.discountId = str;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.discountCode);
                parcel.writeString(this.discountEndTime);
                parcel.writeString(this.markUrl);
                parcel.writeString(this.discountPrice);
                parcel.writeString(this.markText);
                parcel.writeString(this.startTime);
                parcel.writeString(this.discountType);
                parcel.writeString(this.updateDate);
                parcel.writeString(this.discountId);
            }
        }

        public static class HashCodeBean implements Parcelable {
            public static final Parcelable.Creator<HashCodeBean> CREATOR = new Parcelable.Creator<HashCodeBean>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.bean.WatchFaceListBean.WatchFaceBean.HashCodeBean.2
                @Override // android.os.Parcelable.Creator
                /* renamed from: bId_, reason: merged with bridge method [inline-methods] */
                public HashCodeBean createFromParcel(Parcel parcel) {
                    return new HashCodeBean(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public HashCodeBean[] newArray(int i) {
                    return new HashCodeBean[i];
                }
            };

            @SerializedName("filehashcode")
            private String fileHashCode;

            @SerializedName("hashcode")
            private String hashCode;
            private String name;

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public HashCodeBean() {
            }

            protected HashCodeBean(Parcel parcel) {
                this.hashCode = parcel.readString();
                this.name = parcel.readString();
                this.fileHashCode = parcel.readString();
            }

            public String getHashCode() {
                return this.hashCode;
            }

            public void setHashCode(String str) {
                this.hashCode = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getFileHashCode() {
                return this.fileHashCode;
            }

            public void setFileHashCode(String str) {
                this.fileHashCode = str;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.hashCode);
                parcel.writeString(this.name);
                parcel.writeString(this.fileHashCode);
            }
        }

        public static class HashCodeListBean implements Parcelable {
            public static final Parcelable.Creator<HashCodeListBean> CREATOR = new Parcelable.Creator<HashCodeListBean>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.bean.WatchFaceListBean.WatchFaceBean.HashCodeListBean.4
                @Override // android.os.Parcelable.Creator
                /* renamed from: bIe_, reason: merged with bridge method [inline-methods] */
                public HashCodeListBean createFromParcel(Parcel parcel) {
                    return new HashCodeListBean(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public HashCodeListBean[] newArray(int i) {
                    return new HashCodeListBean[i];
                }
            };

            @SerializedName("filehashcode")
            private String fileHashCode;

            @SerializedName("hashcode")
            private String hashCode;
            private String name;

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public HashCodeListBean() {
            }

            protected HashCodeListBean(Parcel parcel) {
                this.hashCode = parcel.readString();
                this.name = parcel.readString();
                this.fileHashCode = parcel.readString();
            }

            public String getHashCode() {
                return this.hashCode;
            }

            public void setHashCode(String str) {
                this.hashCode = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getFileHashCode() {
                return this.fileHashCode;
            }

            public void setFileHashCode(String str) {
                this.fileHashCode = str;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.hashCode);
                parcel.writeString(this.name);
                parcel.writeString(this.fileHashCode);
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.hitopid);
            parcel.writeString(this.log);
            parcel.writeString(this.praiseCount);
            parcel.writeString(this.title);
            parcel.writeInt(this.type);
            parcel.writeInt(this.preresCount);
            parcel.writeString(this.needAccount);
            parcel.writeInt(this.category);
            parcel.writeString(this.logo);
            parcel.writeString(this.label);
            parcel.writeInt(this.downloadCount);
            parcel.writeInt(this.id);
            parcel.writeString(this.author);
            parcel.writeString(this.briefInfo);
            parcel.writeString(this.packageName);
            parcel.writeString(this.collectCount);
            parcel.writeString(this.designer);
            parcel.writeString(this.stars);
            parcel.writeString(this.version);
            parcel.writeString(this.titleLocal);
            parcel.writeInt(this.commentNumber);
            parcel.writeInt(this.size);
            parcel.writeString(this.isoCode);
            parcel.writeString(this.addTime);
            parcel.writeString(this.packageFile);
            parcel.writeString(this.name);
            parcel.writeString(this.previews);
            parcel.writeString(this.shareUrl);
            parcel.writeString(this.lastUpdate);
            parcel.writeString(this.osVersion);
            parcel.writeString(this.font);
            parcel.writeList(this.discountList);
            parcel.writeTypedList(this.hashCode);
            parcel.writeTypedList(this.hashCodeList);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.messageHashCode);
        parcel.writeString(this.symbol);
        parcel.writeInt(this.resultNumber);
        parcel.writeInt(this.totalPage);
        parcel.writeInt(this.recordCount);
        parcel.writeString(this.fileHost);
        parcel.writeString(this.resultInfo);
        parcel.writeString(this.currency);
        parcel.writeString(this.shareUrl);
        parcel.writeString(this.resultCode);
        parcel.writeString(this.shareDescription);
        parcel.writeTypedList(this.watchFaceBeanList);
    }
}
