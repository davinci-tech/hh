package com.huawei.android.bundlecore.download;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.haf.bundle.AppBundleDownloader;

/* loaded from: classes8.dex */
public final class DownloadRequest implements AppBundleDownloader.DownloadRequest, Parcelable {
    public static final Parcelable.Creator<DownloadRequest> CREATOR = new Parcelable.Creator<DownloadRequest>() { // from class: com.huawei.android.bundlecore.download.DownloadRequest.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: eq_, reason: merged with bridge method [inline-methods] */
        public DownloadRequest createFromParcel(Parcel parcel) {
            return new DownloadRequest(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DownloadRequest[] newArray(int i) {
            return new DownloadRequest[i];
        }
    };
    private final String mFileDir;
    private final String mFileName;
    private final int mFileNum;
    private final String mModuleName;
    private final String mUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private DownloadRequest(Parcel parcel) {
        this.mUrl = parcel.readString();
        this.mFileDir = parcel.readString();
        this.mFileName = parcel.readString();
        this.mModuleName = parcel.readString();
        this.mFileNum = parcel.readInt();
    }

    private DownloadRequest(c cVar) {
        this.mUrl = cVar.c;
        this.mFileDir = cVar.d;
        this.mFileName = cVar.f1826a;
        this.mModuleName = cVar.e;
        this.mFileNum = cVar.b;
    }

    public static c newBuilder() {
        return new c();
    }

    @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadRequest
    public String getUrl() {
        return this.mUrl;
    }

    @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadRequest
    public String getFileDir() {
        return this.mFileDir;
    }

    @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadRequest
    public String getFileName() {
        return this.mFileName;
    }

    @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadRequest
    public String getModuleName() {
        return this.mModuleName;
    }

    @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadRequest
    public int getFileNum() {
        return this.mFileNum;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mFileDir);
        parcel.writeString(this.mFileName);
        parcel.writeString(this.mModuleName);
        parcel.writeInt(this.mFileNum);
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private String f1826a;
        private int b;
        private String c;
        private String d;
        private String e;

        public c c(String str) {
            this.c = str;
            return this;
        }

        public c e(String str) {
            this.d = str;
            return this;
        }

        public c b(String str) {
            this.f1826a = str;
            return this;
        }

        public c d(String str) {
            this.e = str;
            return this;
        }

        public c c(int i) {
            this.b = i;
            return this;
        }

        public DownloadRequest e() {
            return new DownloadRequest(this);
        }
    }
}
