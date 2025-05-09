package com.huawei.maps.offlinedata.handler.dto;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes5.dex */
public class DownloadRequest {

    @SerializedName("backDownloadUri")
    private final String backDownloadUri;

    @SerializedName("downloadUri")
    private final String downloadUri;

    @SerializedName("filePath")
    private final String filePath;

    @SerializedName("fileSize")
    private final long fileSize;

    @SerializedName("sha256")
    private final String sha256;

    private DownloadRequest(Builder builder) {
        this.downloadUri = builder.downloadUri;
        this.backDownloadUri = builder.backDownloadUri;
        this.filePath = builder.filePath;
        this.fileSize = builder.fileSize;
        this.sha256 = builder.sha256;
    }

    public String getDownloadUri() {
        return this.downloadUri;
    }

    public String getBackDownloadUri() {
        return this.backDownloadUri;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public String getSha256() {
        return this.sha256;
    }

    public static final class Builder {
        private String backDownloadUri;
        private String downloadUri;
        private String filePath;
        private long fileSize;
        private String sha256;

        public Builder downloadUri(String str) {
            this.downloadUri = str;
            return this;
        }

        public Builder backDownloadUri(String str) {
            this.backDownloadUri = str;
            return this;
        }

        public Builder filePath(String str) {
            this.filePath = str;
            return this;
        }

        public Builder fileSize(long j) {
            this.fileSize = j;
            return this;
        }

        public Builder sha256(String str) {
            this.sha256 = str;
            return this;
        }

        public DownloadRequest build() {
            return new DownloadRequest(this);
        }
    }
}
