package com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth;

/* loaded from: classes8.dex */
public class QqAuthorizeTokenResult {
    private String resultCode;
    private ThirdUserToken thirdUserToken;

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public ThirdUserToken getThirdUserToken() {
        return this.thirdUserToken;
    }

    public void setThirdUserToken(ThirdUserToken thirdUserToken) {
        this.thirdUserToken = thirdUserToken;
    }

    public static class ThirdUserToken {
        private int expireTime;
        private String sessionId;
        private String thirdAccessToken;
        private String thirdAccount;
        private int thirdAccountType;
        private long huid = 0;
        private String lastModifyTime = "";
        private boolean valid = false;

        public int getExpireTime() {
            return this.expireTime;
        }

        public void setExpireTime(int i) {
            this.expireTime = i;
        }

        public long getHuid() {
            return this.huid;
        }

        public void setHuid(long j) {
            this.huid = j;
        }

        public String getLastModifyTime() {
            return this.lastModifyTime;
        }

        public void setLastModifyTime(String str) {
            this.lastModifyTime = str;
        }

        public String getSessionId() {
            return this.sessionId;
        }

        public void setSessionId(String str) {
            this.sessionId = str;
        }

        public String getThirdAccessToken() {
            return this.thirdAccessToken;
        }

        public void setThirdAccessToken(String str) {
            this.thirdAccessToken = str;
        }

        public String getThirdAccount() {
            return this.thirdAccount;
        }

        public void setThirdAccount(String str) {
            this.thirdAccount = str;
        }

        public int getThirdAccountType() {
            return this.thirdAccountType;
        }

        public void setThirdAccountType(int i) {
            this.thirdAccountType = i;
        }

        public boolean isValid() {
            return this.valid;
        }

        public void setValid(boolean z) {
            this.valid = z;
        }
    }
}
