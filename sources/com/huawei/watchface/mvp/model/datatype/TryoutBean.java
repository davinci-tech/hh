package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.util.Locale;

/* loaded from: classes7.dex */
public class TryoutBean implements Parcelable {
    public static final Parcelable.Creator<TryoutBean> CREATOR = new Parcelable.Creator<TryoutBean>() { // from class: com.huawei.watchface.mvp.model.datatype.TryoutBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TryoutBean createFromParcel(Parcel parcel) {
            return new TryoutBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TryoutBean[] newArray(int i) {
            return new TryoutBean[i];
        }
    };
    private String cnTitle;
    private String detailUrl;
    private String fileSize;
    private String hashCode;
    private boolean isZip;
    private String productId;
    private String resourceType;
    private String title;

    @SerializedName("hitopId")
    private String tryOutHitopId;
    private String version;
    private String versionCode;
    private String watchScreen;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TryoutBean() {
    }

    protected TryoutBean(Parcel parcel) {
        this.tryOutHitopId = parcel.readString();
        this.version = parcel.readString();
        this.productId = parcel.readString();
        this.fileSize = parcel.readString();
        this.hashCode = parcel.readString();
        this.title = parcel.readString();
        this.cnTitle = parcel.readString();
        this.detailUrl = parcel.readString();
        this.versionCode = parcel.readString();
        this.watchScreen = parcel.readString();
        this.resourceType = parcel.readString();
        this.isZip = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tryOutHitopId);
        parcel.writeString(this.version);
        parcel.writeString(this.productId);
        parcel.writeString(this.fileSize);
        parcel.writeString(this.hashCode);
        parcel.writeString(this.title);
        parcel.writeString(this.cnTitle);
        parcel.writeString(this.detailUrl);
        parcel.writeString(this.versionCode);
        parcel.writeString(this.watchScreen);
        parcel.writeString(this.resourceType);
        parcel.writeByte(this.isZip ? (byte) 1 : (byte) 0);
    }

    public String getTryOutHiTopId() {
        return this.tryOutHitopId;
    }

    public void setTryOutHiTopId(String str) {
        this.tryOutHitopId = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(String str) {
        this.fileSize = str;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String str) {
        this.hashCode = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getCnTitle() {
        return this.cnTitle;
    }

    public void setCnTitle(String str) {
        this.cnTitle = str;
    }

    public String getDetailUrl() {
        return this.detailUrl;
    }

    public void setDetailUrl(String str) {
        this.detailUrl = str;
    }

    public String getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(String str) {
        this.versionCode = str;
    }

    public String getWatchScreen() {
        return this.watchScreen;
    }

    public void setWatchScreen(String str) {
        this.watchScreen = str;
    }

    public boolean isZip() {
        return this.isZip;
    }

    public void setZip(boolean z) {
        this.isZip = z;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String str) {
        this.resourceType = str;
    }

    public String getShowTitle() {
        Locale locale = Locale.getDefault();
        if (TextUtils.isEmpty(this.cnTitle)) {
            return this.title;
        }
        if (locale.toString().contains("zh_") || locale.equals(Locale.CHINESE)) {
            return this.cnTitle;
        }
        return this.title;
    }
}
