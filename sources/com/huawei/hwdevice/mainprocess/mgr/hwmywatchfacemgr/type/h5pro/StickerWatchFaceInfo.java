package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

import defpackage.nrv;
import java.util.List;

/* loaded from: classes9.dex */
public class StickerWatchFaceInfo {
    private List<StickerTPLInfo> stickerTplInfo;

    public static class CustomResource {
        private String customStickerName;

        public String getCustomStickerName() {
            return this.customStickerName;
        }

        public void setCustomStickerName(String str) {
            this.customStickerName = str;
        }
    }

    public static class StickerTPLInfo {
        private CustomResource customResource;
        private Integer tplType;

        public CustomResource getCustomResource() {
            return this.customResource;
        }

        public void setCustomResource(CustomResource customResource) {
            this.customResource = customResource;
        }

        public Integer getTplType() {
            return this.tplType;
        }

        public void setTplType(Integer num) {
            this.tplType = num;
        }
    }

    public List<StickerTPLInfo> getStickerTplInfo() {
        return this.stickerTplInfo;
    }

    public void setStickerTplInfo(List<StickerTPLInfo> list) {
        this.stickerTplInfo = list;
    }

    public static StickerWatchFaceInfo loads(String str) {
        return (StickerWatchFaceInfo) nrv.b(str, StickerWatchFaceInfo.class);
    }

    public static String dumps(StickerWatchFaceInfo stickerWatchFaceInfo) {
        return nrv.a(stickerWatchFaceInfo);
    }
}
