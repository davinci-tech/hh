package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice;

import defpackage.nrv;
import java.util.List;

/* loaded from: classes9.dex */
public class StickerEventDTO {
    private List<FileInfo> fileInfo;
    private Integer request;
    private Integer result;

    public List<FileInfo> getFileInfo() {
        return this.fileInfo;
    }

    public void setFileInfo(List<FileInfo> list) {
        this.fileInfo = list;
    }

    public Integer getResult() {
        return this.result;
    }

    public void setResult(Integer num) {
        this.result = num;
    }

    public Integer getRequest() {
        return this.request;
    }

    public void setRequest(Integer num) {
        this.request = num;
    }

    public static String dumps(StickerEventDTO stickerEventDTO) {
        return nrv.a(stickerEventDTO);
    }

    public static StickerEventDTO loads(String str) {
        return (StickerEventDTO) nrv.b(str, StickerEventDTO.class);
    }

    public static class FileInfo {
        private String fileName;
        private int fileType;

        public int getFileType() {
            return this.fileType;
        }

        public void setFileType(int i) {
            this.fileType = i;
        }

        public String getFileName() {
            return this.fileName;
        }

        public void setFileName(String str) {
            this.fileName = str;
        }
    }
}
