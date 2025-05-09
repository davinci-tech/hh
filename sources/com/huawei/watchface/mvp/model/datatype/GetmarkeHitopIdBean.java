package com.huawei.watchface.mvp.model.datatype;

import java.util.List;

/* loaded from: classes9.dex */
public class GetmarkeHitopIdBean {
    private List<ListBean> list;
    private String resultCode;
    private String resultInfo;

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public String getResultInfo() {
        return this.resultInfo;
    }

    public void setResultInfo(String str) {
        this.resultInfo = str;
    }

    public List<ListBean> getList() {
        return this.list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String author;
        private String description;
        private String designer;
        private String downloadCount;
        private String hitopId;
        private String id;
        private String label;
        private String price;
        private String productId;
        private String recommendKeyword;
        private String type;
        private String version;

        public String getHitopId() {
            return this.hitopId;
        }

        public void setHitopId(String str) {
            this.hitopId = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getLabel() {
            return this.label;
        }

        public void setLabel(String str) {
            this.label = str;
        }

        public String getDownloadCount() {
            return this.downloadCount;
        }

        public void setDownloadCount(String str) {
            this.downloadCount = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getProductId() {
            return this.productId;
        }

        public void setProductId(String str) {
            this.productId = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getDesigner() {
            return this.designer;
        }

        public void setDesigner(String str) {
            this.designer = str;
        }

        public String getAuthor() {
            return this.author;
        }

        public void setAuthor(String str) {
            this.author = str;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public String getPrice() {
            return this.price;
        }

        public void setPrice(String str) {
            this.price = str;
        }

        public String getRecommendKeyword() {
            return this.recommendKeyword;
        }

        public void setRecommendKeyword(String str) {
            this.recommendKeyword = str;
        }
    }
}
