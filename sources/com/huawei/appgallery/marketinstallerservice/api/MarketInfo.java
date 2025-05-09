package com.huawei.appgallery.marketinstallerservice.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.InstallerNetTransmission;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.JsonBean;

/* loaded from: classes3.dex */
public class MarketInfo extends JsonBean implements Parcelable {
    public static final Parcelable.Creator<MarketInfo> CREATOR = new Parcelable.Creator<MarketInfo>() { // from class: com.huawei.appgallery.marketinstallerservice.api.MarketInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MarketInfo[] newArray(int i) {
            return new MarketInfo[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MarketInfo createFromParcel(Parcel parcel) {
            return new MarketInfo(parcel);
        }
    };

    @InstallerNetTransmission
    private String appName;

    @InstallerNetTransmission
    private String downUrl;

    @InstallerNetTransmission
    private long fileSize;

    @InstallerNetTransmission
    private String pkgName;

    @InstallerNetTransmission
    private String sha256;

    @InstallerNetTransmission
    private int versionCode;

    @InstallerNetTransmission
    private String versionName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.pkgName);
        parcel.writeString(this.downUrl);
        parcel.writeString(this.appName);
        parcel.writeInt(this.versionCode);
        parcel.writeString(this.versionName);
        parcel.writeLong(this.fileSize);
        parcel.writeString(this.sha256);
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }

    public void setSha256(String str) {
        this.sha256 = str;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public void setDownUrl(String str) {
        this.downUrl = str;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public String getSha256() {
        return this.sha256;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public String getDownUrl() {
        return this.downUrl;
    }

    public String getAppName() {
        return this.appName;
    }

    public MarketInfo(Parcel parcel) {
        this.pkgName = parcel.readString();
        this.downUrl = parcel.readString();
        this.appName = parcel.readString();
        this.versionCode = parcel.readInt();
        this.versionName = parcel.readString();
        this.fileSize = parcel.readLong();
        this.sha256 = parcel.readString();
    }

    public MarketInfo() {
    }
}
