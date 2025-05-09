package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.LanguageUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class WatchFaceListBean implements Parcelable {
    public static final Parcelable.Creator<WatchFaceListBean> CREATOR = new Parcelable.Creator<WatchFaceListBean>() { // from class: com.huawei.watchface.mvp.model.datatype.WatchFaceListBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchFaceListBean createFromParcel(Parcel parcel) {
            return new WatchFaceListBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchFaceListBean[] newArray(int i) {
            return new WatchFaceListBean[i];
        }
    };
    private static final int DESCRIBE_CONTENT_DEFAULT = 0;
    private static final int DISCOUNT_LIST_DEFAULT_SIZE = 100;
    private String acUrl;
    private String algId;
    private String categoryBackPicUrl;
    private String categoryDesc;
    private String categoryName;
    private String categoryPicUrl;
    private String categorySubtitle;
    private String correctKeyWord;
    private String cursor;
    private String messageHashCode;
    private String resultCode;
    private String resultInfo;
    private String sourceFlag;
    private String totalCount;

    @SerializedName("list")
    private List<WatchFaceBean> watchFaceBeanList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WatchFaceListBean() {
    }

    protected WatchFaceListBean(Parcel parcel) {
        this.resultCode = parcel.readString();
        this.resultInfo = parcel.readString();
        this.messageHashCode = parcel.readString();
        this.watchFaceBeanList = CommonUtils.a(WatchFaceBean.CREATOR, parcel);
        this.sourceFlag = parcel.readString();
        this.algId = parcel.readString();
        this.categorySubtitle = parcel.readString();
        this.categoryDesc = parcel.readString();
        this.acUrl = parcel.readString();
        this.categoryBackPicUrl = parcel.readString();
        this.categoryName = parcel.readString();
        this.categoryPicUrl = parcel.readString();
        this.cursor = parcel.readString();
        this.correctKeyWord = parcel.readString();
        this.totalCount = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.resultCode);
        parcel.writeString(this.resultInfo);
        parcel.writeString(this.messageHashCode);
        CommonUtils.a(parcel, this.watchFaceBeanList);
        parcel.writeString(this.sourceFlag);
        parcel.writeString(this.algId);
        parcel.writeString(this.categorySubtitle);
        parcel.writeString(this.categoryDesc);
        parcel.writeString(this.acUrl);
        parcel.writeString(this.categoryBackPicUrl);
        parcel.writeString(this.categoryName);
        parcel.writeString(this.categoryPicUrl);
        parcel.writeString(this.cursor);
        parcel.writeString(this.correctKeyWord);
        parcel.writeString(this.totalCount);
    }

    public String getMessageHashCode() {
        return this.messageHashCode;
    }

    public void setMessageHashCode(String str) {
        this.messageHashCode = str;
    }

    public String getResultInfo() {
        return this.resultInfo;
    }

    public void setResultInfo(String str) {
        this.resultInfo = str;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public List<WatchFaceBean> getWatchFaceBeanList() {
        return this.watchFaceBeanList;
    }

    public void setWatchFaceBeanList(List<WatchFaceBean> list) {
        this.watchFaceBeanList = list;
    }

    public String getSourceFlag() {
        return this.sourceFlag;
    }

    public void setSourceFlag(String str) {
        this.sourceFlag = str;
    }

    public String getAlgId() {
        return this.algId;
    }

    public void setAlgId(String str) {
        this.algId = str;
    }

    public String getCategorySubtitle() {
        return this.categorySubtitle;
    }

    public void setCategorySubtitle(String str) {
        this.categorySubtitle = str;
    }

    public String getCategoryDesc() {
        return this.categoryDesc;
    }

    public void setCategoryDesc(String str) {
        this.categoryDesc = str;
    }

    public String getAcUrl() {
        return this.acUrl;
    }

    public void setAcUrl(String str) {
        this.acUrl = str;
    }

    public String getCategoryBackPicUrl() {
        return this.categoryBackPicUrl;
    }

    public void setCategoryBackPicUrl(String str) {
        this.categoryBackPicUrl = str;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String str) {
        this.categoryName = str;
    }

    public String getCategoryPicUrl() {
        return this.categoryPicUrl;
    }

    public void setCategoryPicUrl(String str) {
        this.categoryPicUrl = str;
    }

    public String getCursor() {
        return this.cursor;
    }

    public void setCursor(String str) {
        this.cursor = str;
    }

    public String getCorrectKeyWord() {
        return this.correctKeyWord;
    }

    public void setCorrectKeyWord(String str) {
        this.correctKeyWord = str;
    }

    public String getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(String str) {
        this.totalCount = str;
    }

    public static class WatchFaceBean implements Parcelable {
        public static final Parcelable.Creator<WatchFaceBean> CREATOR = new Parcelable.Creator<WatchFaceBean>() { // from class: com.huawei.watchface.mvp.model.datatype.WatchFaceListBean.WatchFaceBean.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WatchFaceBean createFromParcel(Parcel parcel) {
                return new WatchFaceBean(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WatchFaceBean[] newArray(int i) {
                return new WatchFaceBean[i];
            }
        };
        private String addTime;
        private String author;

        @SerializedName("description")
        private String briefInfo;
        private String categoryId;
        private String collectCount;

        @SerializedName("commentNum")
        private int commentNumber;
        private String contentPrivType;
        private String currency;
        private String designer;
        private List<DiscountListBean> discountList;
        private int downloadCount;
        private FileInfoBean fileInfo;
        private String followerStatus;
        private String hitopId;
        private int id;
        private String isPraised;

        @SerializedName("countryCode")
        private String isoCode;
        private String label;

        @SerializedName("lastUpdateTime")
        private String lastUpdate;
        private String mLiveMinversion;
        private String mLivePackageName;
        private String mLiveTitle;
        private String mLiveTitleCN;
        private String praiseCount;
        private List<PreviewBean> previews;
        private String price;
        private String processCover;
        private String productId;
        private String recommendKeyword;
        private String stars;
        private String subType;
        private String symbol;
        private List<TitleListBean> title;
        private String titleCn;
        private String titleEn;
        private int type;
        private String userID;
        private String version;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public WatchFaceBean() {
        }

        protected WatchFaceBean(Parcel parcel) {
            this.hitopId = parcel.readString();
            this.praiseCount = parcel.readString();
            this.title = CommonUtils.a(TitleListBean.CREATOR, parcel);
            this.type = parcel.readInt();
            this.label = parcel.readString();
            this.downloadCount = parcel.readInt();
            this.id = parcel.readInt();
            this.briefInfo = parcel.readString();
            this.collectCount = parcel.readString();
            this.designer = parcel.readString();
            this.stars = parcel.readString();
            this.version = parcel.readString();
            this.commentNumber = parcel.readInt();
            this.isoCode = parcel.readString();
            this.addTime = parcel.readString();
            this.lastUpdate = parcel.readString();
            ArrayList arrayList = new ArrayList(100);
            this.discountList = arrayList;
            parcel.readList(arrayList, DiscountListBean.class.getClassLoader());
            this.productId = parcel.readString();
            this.fileInfo = (FileInfoBean) parcel.readParcelable(FileInfoBean.class.getClassLoader());
            this.previews = CommonUtils.a(PreviewBean.CREATOR, parcel);
            this.processCover = parcel.readString();
            this.subType = parcel.readString();
            this.price = parcel.readString();
            this.isPraised = parcel.readString();
            this.categoryId = parcel.readString();
            this.recommendKeyword = parcel.readString();
            this.symbol = parcel.readString();
            this.currency = parcel.readString();
            this.mLivePackageName = parcel.readString();
            this.mLiveMinversion = parcel.readString();
            this.mLiveTitleCN = parcel.readString();
            this.mLiveTitle = parcel.readString();
            this.followerStatus = parcel.readString();
            this.contentPrivType = parcel.readString();
            this.userID = parcel.readString();
            this.author = parcel.readString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.hitopId);
            parcel.writeString(this.praiseCount);
            CommonUtils.a(parcel, this.title);
            parcel.writeInt(this.type);
            parcel.writeString(this.label);
            parcel.writeInt(this.downloadCount);
            parcel.writeInt(this.id);
            parcel.writeString(this.briefInfo);
            parcel.writeString(this.collectCount);
            parcel.writeString(this.designer);
            parcel.writeString(this.stars);
            parcel.writeString(this.version);
            parcel.writeInt(this.commentNumber);
            parcel.writeString(this.isoCode);
            parcel.writeString(this.addTime);
            parcel.writeString(this.lastUpdate);
            parcel.writeList(this.discountList);
            parcel.writeString(this.productId);
            parcel.writeParcelable(this.fileInfo, i);
            CommonUtils.a(parcel, this.previews);
            parcel.writeString(this.processCover);
            parcel.writeString(this.subType);
            parcel.writeString(this.price);
            parcel.writeString(this.isPraised);
            parcel.writeString(this.categoryId);
            parcel.writeString(this.recommendKeyword);
            parcel.writeString(this.symbol);
            parcel.writeString(this.currency);
            parcel.writeString(this.mLivePackageName);
            parcel.writeString(this.mLiveMinversion);
            parcel.writeString(this.mLiveTitleCN);
            parcel.writeString(this.mLiveTitle);
            parcel.writeString(this.followerStatus);
            parcel.writeString(this.contentPrivType);
            parcel.writeString(this.userID);
            parcel.writeString(this.author);
        }

        public String getTitleLocal() {
            List<TitleListBean> title = getTitle();
            if (ArrayUtils.isEmpty(title)) {
                return "";
            }
            for (TitleListBean titleListBean : title) {
                if ("64".equals(titleListBean.getLanguage())) {
                    this.titleCn = titleListBean.getTitle();
                } else {
                    this.titleEn = titleListBean.getTitle();
                }
            }
            return LanguageUtils.b() ? this.titleCn : this.titleEn;
        }

        public String getProductId() {
            return this.productId;
        }

        public void setProductId(String str) {
            this.productId = str;
        }

        public FileInfoBean getFileInfo() {
            return this.fileInfo;
        }

        public void setFileInfo(FileInfoBean fileInfoBean) {
            this.fileInfo = fileInfoBean;
        }

        public List<PreviewBean> getPreviews() {
            return this.previews;
        }

        public void setPreviews(List<PreviewBean> list) {
            this.previews = list;
        }

        public String getProcessCover() {
            return this.processCover;
        }

        public void setProcessCover(String str) {
            this.processCover = str;
        }

        public String getSubType() {
            return this.subType;
        }

        public void setSubType(String str) {
            this.subType = str;
        }

        public String getPrice() {
            return this.price;
        }

        public void setPrice(String str) {
            this.price = str;
        }

        public String getIsPraised() {
            return this.isPraised;
        }

        public void setIsPraised(String str) {
            this.isPraised = str;
        }

        public String getCategoryId() {
            return this.categoryId;
        }

        public void setCategoryId(String str) {
            this.categoryId = str;
        }

        public String getRecommendKeyword() {
            return this.recommendKeyword;
        }

        public void setRecommendKeyword(String str) {
            this.recommendKeyword = str;
        }

        public String getSymbol() {
            return this.symbol;
        }

        public void setSymbol(String str) {
            this.symbol = str;
        }

        public String getCurrency() {
            return this.currency;
        }

        public void setCurrency(String str) {
            this.currency = str;
        }

        public String getmLivePackageName() {
            return this.mLivePackageName;
        }

        public void setmLivePackageName(String str) {
            this.mLivePackageName = str;
        }

        public String getmLiveMinversion() {
            return this.mLiveMinversion;
        }

        public void setmLiveMinversion(String str) {
            this.mLiveMinversion = str;
        }

        public String getmLiveTitleCN() {
            return this.mLiveTitleCN;
        }

        public void setmLiveTitleCN(String str) {
            this.mLiveTitleCN = str;
        }

        public String getmLiveTitle() {
            return this.mLiveTitle;
        }

        public void setmLiveTitle(String str) {
            this.mLiveTitle = str;
        }

        public String getFollowerStatus() {
            return this.followerStatus;
        }

        public void setFollowerStatus(String str) {
            this.followerStatus = str;
        }

        public String getContentPrivType() {
            return this.contentPrivType;
        }

        public void setContentPrivType(String str) {
            this.contentPrivType = str;
        }

        public String getUserID() {
            return this.userID;
        }

        public void setUserID(String str) {
            this.userID = str;
        }

        public String getHitopId() {
            return this.hitopId;
        }

        public void setHitopId(String str) {
            this.hitopId = str;
        }

        public String getPraiseCount() {
            return this.praiseCount;
        }

        public void setPraiseCount(String str) {
            this.praiseCount = str;
        }

        public List<TitleListBean> getTitle() {
            return this.title;
        }

        public void setTitle(List<TitleListBean> list) {
            this.title = list;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
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

        public String getBriefInfo() {
            return this.briefInfo;
        }

        public void setBriefInfo(String str) {
            this.briefInfo = str;
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

        public int getCommentNumber() {
            return this.commentNumber;
        }

        public void setCommentNumber(int i) {
            this.commentNumber = i;
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

        public String getLastUpdate() {
            return this.lastUpdate;
        }

        public void setLastUpdate(String str) {
            this.lastUpdate = str;
        }

        public List<DiscountListBean> getDiscountList() {
            return this.discountList;
        }

        public void setDiscountList(List<DiscountListBean> list) {
            this.discountList = list;
        }

        public String getAuthor() {
            return this.author;
        }

        public void setAuthor(String str) {
            this.author = str;
        }

        public static class DiscountListBean implements Parcelable {
            public static final Parcelable.Creator<DiscountListBean> CREATOR = new Parcelable.Creator<DiscountListBean>() { // from class: com.huawei.watchface.mvp.model.datatype.WatchFaceListBean.WatchFaceBean.DiscountListBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public DiscountListBean createFromParcel(Parcel parcel) {
                    return new DiscountListBean(parcel);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public DiscountListBean[] newArray(int i) {
                    return new DiscountListBean[i];
                }
            };
            private String discountCode;
            private String discountEndTime;
            private String discountId;
            private String discountPrice;
            private String discountType;
            private String givePrice;
            private String isActiveDiscount;
            private String markText;
            private String markUrl;
            private String startTime;

            @SerializedName("updateTime")
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
                this.givePrice = parcel.readString();
                this.isActiveDiscount = parcel.readString();
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

            public String getGivePrice() {
                return this.givePrice;
            }

            public void setGivePrice(String str) {
                this.givePrice = str;
            }

            public String getIsActiveDiscount() {
                return this.isActiveDiscount;
            }

            public void setIsActiveDiscount(String str) {
                this.isActiveDiscount = str;
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
                parcel.writeString(this.givePrice);
                parcel.writeString(this.isActiveDiscount);
            }
        }

        public static class FileInfoBean implements Parcelable {
            public static final Parcelable.Creator<FileInfoBean> CREATOR = new Parcelable.Creator<FileInfoBean>() { // from class: com.huawei.watchface.mvp.model.datatype.WatchFaceListBean.WatchFaceBean.FileInfoBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FileInfoBean createFromParcel(Parcel parcel) {
                    return new FileInfoBean(parcel);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FileInfoBean[] newArray(int i) {
                    return new FileInfoBean[i];
                }
            };
            private String algorithmName;
            private String downloadUrl;
            private String fileName;
            private String fileType;
            private String hashCode;
            private String size;

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            protected FileInfoBean(Parcel parcel) {
                this.hashCode = parcel.readString();
                this.algorithmName = parcel.readString();
                this.fileName = parcel.readString();
                this.size = parcel.readString();
                this.fileType = parcel.readString();
                this.downloadUrl = parcel.readString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.hashCode);
                parcel.writeString(this.algorithmName);
                parcel.writeString(this.fileName);
                parcel.writeString(this.size);
                parcel.writeString(this.fileType);
                parcel.writeString(this.downloadUrl);
            }

            public String getHashCode() {
                return this.hashCode;
            }

            public void setHashCode(String str) {
                this.hashCode = str;
            }

            public String getAlgorithmName() {
                return this.algorithmName;
            }

            public void setAlgorithmName(String str) {
                this.algorithmName = str;
            }

            public String getFileName() {
                return this.fileName;
            }

            public void setFileName(String str) {
                this.fileName = str;
            }

            public String getSize() {
                return this.size;
            }

            public void setSize(String str) {
                this.size = str;
            }

            public String getFileType() {
                return this.fileType;
            }

            public void setFileType(String str) {
                this.fileType = str;
            }

            public String getDownloadUrl() {
                return this.downloadUrl;
            }

            public void setDownloadUrl(String str) {
                this.downloadUrl = str;
            }
        }

        public static class PreviewBean implements Parcelable {
            public static final Parcelable.Creator<PreviewBean> CREATOR = new Parcelable.Creator<PreviewBean>() { // from class: com.huawei.watchface.mvp.model.datatype.WatchFaceListBean.WatchFaceBean.PreviewBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PreviewBean createFromParcel(Parcel parcel) {
                    return new PreviewBean(parcel);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PreviewBean[] newArray(int i) {
                    return new PreviewBean[i];
                }
            };
            private String chname;
            private String designer;
            private String enname;
            private String language;
            private String magazineWebp;
            private String previewName;
            private String previewUrl;

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            protected PreviewBean(Parcel parcel) {
                this.previewName = parcel.readString();
                this.previewUrl = parcel.readString();
                this.magazineWebp = parcel.readString();
                this.designer = parcel.readString();
                this.chname = parcel.readString();
                this.enname = parcel.readString();
                this.language = parcel.readString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.previewName);
                parcel.writeString(this.previewUrl);
                parcel.writeString(this.magazineWebp);
                parcel.writeString(this.designer);
                parcel.writeString(this.chname);
                parcel.writeString(this.enname);
                parcel.writeString(this.language);
            }

            public String getPreviewName() {
                return this.previewName;
            }

            public void setPreviewName(String str) {
                this.previewName = str;
            }

            public String getPreviewUrl() {
                return this.previewUrl;
            }

            public void setPreviewUrl(String str) {
                this.previewUrl = str;
            }

            public String getMagazineWebp() {
                return this.magazineWebp;
            }

            public void setMagazineWebp(String str) {
                this.magazineWebp = str;
            }

            public String getDesigner() {
                return this.designer;
            }

            public void setDesigner(String str) {
                this.designer = str;
            }

            public String getChname() {
                return this.chname;
            }

            public void setChname(String str) {
                this.chname = str;
            }

            public String getEnname() {
                return this.enname;
            }

            public void setEnname(String str) {
                this.enname = str;
            }

            public String getLanguage() {
                return this.language;
            }

            public void setLanguage(String str) {
                this.language = str;
            }
        }

        public static class TitleListBean implements Parcelable {
            public static final Parcelable.Creator<TitleListBean> CREATOR = new Parcelable.Creator<TitleListBean>() { // from class: com.huawei.watchface.mvp.model.datatype.WatchFaceListBean.WatchFaceBean.TitleListBean.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public TitleListBean createFromParcel(Parcel parcel) {
                    return new TitleListBean(parcel);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public TitleListBean[] newArray(int i) {
                    return new TitleListBean[i];
                }
            };
            private String language;
            private String title;

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            protected TitleListBean(Parcel parcel) {
                this.language = parcel.readString();
                this.title = parcel.readString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.language);
                parcel.writeString(this.title);
            }

            public String getLanguage() {
                return this.language;
            }

            public void setLanguage(String str) {
                this.language = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }
        }
    }
}
