package com.huawei.hms.network.file.upload.api;

import com.huawei.hms.network.file.upload.api.BodyRequest;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class PostRequest extends BodyRequest {

    /* loaded from: classes9.dex */
    public static final class Builder extends BodyRequest.Builder<Builder> {
        @Override // com.huawei.hms.network.file.upload.api.BodyRequest.Builder
        public Builder fileParams(String str, FileEntity... fileEntityArr) {
            return (Builder) super.fileParams(str, fileEntityArr);
        }

        @Override // com.huawei.hms.network.file.upload.api.BodyRequest.Builder
        /* renamed from: fileParams, reason: avoid collision after fix types in other method */
        public Builder fileParams2(String str, List<FileEntity> list) {
            return (Builder) super.fileParams(str, list);
        }

        @Override // com.huawei.hms.network.file.upload.api.BodyRequest.Builder
        public /* bridge */ /* synthetic */ Builder fileParams(String str, List list) {
            return fileParams2(str, (List<FileEntity>) list);
        }

        @Override // com.huawei.hms.network.file.api.Request.Builder
        public PostRequest build() {
            return new PostRequest(this);
        }

        public Builder(PostRequest postRequest) {
            super(postRequest);
        }

        public Builder() {
        }
    }

    @Override // com.huawei.hms.network.file.api.Request
    public Builder newBuilder() {
        return new Builder(this);
    }

    @Override // com.huawei.hms.network.file.upload.api.BodyRequest
    public Map<String, List<FileEntity>> getFileEntityMap() {
        return super.getFileEntityMap();
    }

    PostRequest(Builder builder) {
        super(builder);
    }
}
