package com.huawei.watchface.mvp.model.datatype;

import java.util.List;

/* loaded from: classes7.dex */
public class TransFileInfo {
    private List<Integer> attentionTypes;
    private String fileName;
    private String filePath;
    private int fileType;
    private String packageName;
    private TransferFileReqType requestType;
    private long sourceId;

    public TransferFileReqType getRequestType() {
        return this.requestType;
    }

    public void setRequestType(TransferFileReqType transferFileReqType) {
        this.requestType = transferFileReqType;
    }

    public List<Integer> getAttentionTypes() {
        return this.attentionTypes;
    }

    public void setAttentionTypes(List<Integer> list) {
        this.attentionTypes = list;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public int getFileType() {
        return this.fileType;
    }

    public void setFileType(int i) {
        this.fileType = i;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(long j) {
        this.sourceId = j;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }
}
