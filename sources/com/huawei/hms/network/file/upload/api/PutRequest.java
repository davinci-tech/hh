package com.huawei.hms.network.file.upload.api;

import com.huawei.hms.network.file.upload.api.BodyRequest;
import java.util.List;

/* loaded from: classes4.dex */
public final class PutRequest extends BodyRequest {

    /* loaded from: classes9.dex */
    public static final class Builder extends BodyRequest.Builder<Builder> {
        @Override // com.huawei.hms.network.file.upload.api.BodyRequest.Builder
        /* renamed from: fileParams, reason: avoid collision after fix types in other method */
        public Builder fileParams2(List<FileEntity> list) {
            return (Builder) super.fileParams(list);
        }

        @Override // com.huawei.hms.network.file.upload.api.BodyRequest.Builder
        public /* bridge */ /* synthetic */ Builder fileParams(List list) {
            return fileParams2((List<FileEntity>) list);
        }

        @Override // com.huawei.hms.network.file.api.Request.Builder
        public PutRequest build() {
            return new PutRequest(this);
        }

        public Builder(BodyRequest bodyRequest) {
            super(bodyRequest);
        }

        public Builder() {
        }
    }

    @Override // com.huawei.hms.network.file.api.Request
    public Builder newBuilder() {
        return new Builder(this);
    }

    @Override // com.huawei.hms.network.file.upload.api.BodyRequest
    public List<FileEntity> getFileEntityList() {
        return super.getFileEntityList();
    }

    PutRequest(Builder builder) {
        super(builder);
    }
}
