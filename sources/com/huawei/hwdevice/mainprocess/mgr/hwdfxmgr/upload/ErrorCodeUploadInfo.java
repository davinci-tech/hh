package com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.upload;

/* loaded from: classes5.dex */
public class ErrorCodeUploadInfo {
    private Object headers;
    private String method;
    private String objectId;
    private String uploadUrl;

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public String getUploadUrl() {
        return this.uploadUrl;
    }

    public void setUploadUrl(String str) {
        this.uploadUrl = str;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public Object getHeaders() {
        return this.headers;
    }

    public void setHeaders(Object obj) {
        this.headers = obj;
    }
}
