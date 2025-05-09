package com.huawei.hms.network.file.download.api;

import com.huawei.hms.network.file.a.f;
import com.huawei.hms.network.file.api.Request;

/* loaded from: classes4.dex */
public final class GetRequest extends Request {
    private final boolean enableSlice;
    private final String filePath;
    private final long fileSize;
    private final long offset;
    private final String sha256;

    public static final class Builder extends Request.Builder<Builder> {
        private boolean enableSlice;
        private String filePath;
        private long fileSize;
        private long offset;
        private String sha256;

        public Builder sha256(String str) {
            this.sha256 = str;
            return this;
        }

        public Builder offset(long j) {
            this.offset = j;
            return this;
        }

        public Builder fileSize(long j) {
            this.fileSize = j;
            return this;
        }

        public Builder filePath(String str) {
            this.filePath = str;
            return this;
        }

        public Builder enableSlice(boolean z) {
            this.enableSlice = z;
            return this;
        }

        @Override // com.huawei.hms.network.file.api.Request.Builder
        public GetRequest build() {
            return new GetRequest(this);
        }

        public Builder(GetRequest getRequest) {
            super(getRequest);
            this.offset = getRequest.offset;
            this.fileSize = getRequest.fileSize;
            this.sha256 = getRequest.sha256;
            this.filePath = getRequest.filePath;
            this.enableSlice = getRequest.enableSlice;
        }

        public Builder() {
            this.converter = new f();
            this.enableSlice = true;
        }
    }

    @Override // com.huawei.hms.network.file.api.Request
    public String toString() {
        return "GetRequest{offset=" + this.offset + ", fileSize=" + this.fileSize + ", config=" + this.config + ", name='" + this.name + "', id=" + this.id + ", enableSlice=" + this.enableSlice + '}';
    }

    @Override // com.huawei.hms.network.file.api.Request
    public Builder newBuilder() {
        return new Builder(this);
    }

    public boolean isEnableSlice() {
        return this.enableSlice;
    }

    public String getSha256() {
        return this.sha256;
    }

    public long getOffset() {
        return this.offset;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public String getFilePath() {
        return this.filePath;
    }

    GetRequest(Builder builder) {
        super(builder);
        this.offset = builder.offset;
        this.fileSize = builder.fileSize;
        this.sha256 = builder.sha256;
        this.filePath = builder.filePath;
        this.enableSlice = builder.enableSlice;
    }
}
