package com.huawei.hwcloudmodel.model;

/* loaded from: classes5.dex */
public class ThirdOauthTokenI {
    private int resultCode;
    private String resultDesc;
    private ThirdOauthToken thirdAuthToken;

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String str) {
        this.resultDesc = str;
    }

    public ThirdOauthToken getThirdAuthToken() {
        return this.thirdAuthToken;
    }

    public void setThirdAuthToken(ThirdOauthToken thirdOauthToken) {
        this.thirdAuthToken = thirdOauthToken;
    }

    public static class ThirdOauthToken {
        private long createTime;
        private long expireTime;
        private long huid;
        private long lastModifyTime;
        private String openId;
        private int thirdAccountType;
        private String thirdToken;
        private int thirdTokenType;
        private boolean valid;

        public long getHuid() {
            return this.huid;
        }

        public void setHuid(long j) {
            this.huid = j;
        }

        public long getLastModifyTime() {
            return this.lastModifyTime;
        }

        public void setLastModifyTime(long j) {
            this.lastModifyTime = j;
        }

        public boolean isValid() {
            return this.valid;
        }

        public void setValid(boolean z) {
            this.valid = z;
        }

        public int getThirdAccountType() {
            return this.thirdAccountType;
        }

        public void setThirdAccountType(int i) {
            this.thirdAccountType = i;
        }

        public int getThirdTokenType() {
            return this.thirdTokenType;
        }

        public void setThirdTokenType(int i) {
            this.thirdTokenType = i;
        }

        public String getThirdToken() {
            return this.thirdToken;
        }

        public void setThirdToken(String str) {
            this.thirdToken = str;
        }

        public long getExpireTime() {
            return this.expireTime;
        }

        public void setExpireTime(long j) {
            this.expireTime = j;
        }

        public long getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(long j) {
            this.createTime = j;
        }

        public String getOpenId() {
            return this.openId;
        }

        public void setOpenId(String str) {
            this.openId = str;
        }
    }
}
