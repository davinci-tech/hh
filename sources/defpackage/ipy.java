package defpackage;

import java.util.Set;

/* loaded from: classes4.dex */
public class ipy {
    private a appAuditInfo;
    private c redirectInfo;

    public a getAppAuditInfo() {
        return this.appAuditInfo;
    }

    public c getRedirectInfo() {
        return this.redirectInfo;
    }

    /* loaded from: classes8.dex */
    public static class a {
        private String betaScopes;
        private String passedScopes;

        public String getPassedScopes() {
            if (this.passedScopes == null) {
                this.passedScopes = "";
            }
            return this.passedScopes;
        }

        public String getBetaScopes() {
            if (this.betaScopes == null) {
                this.betaScopes = "";
            }
            return this.betaScopes;
        }
    }

    public static class c {
        private String appId;
        private long expiredTimestamp;
        private String secret;
        private String signature;
        private Set<String> urlSchemes;

        public Set<String> getUrlSchemes() {
            return this.urlSchemes;
        }

        public String getSecret() {
            return this.secret;
        }

        public void setSecret(String str) {
            this.secret = str;
        }

        public void setExpiredTimestamp(long j) {
            this.expiredTimestamp = j;
        }

        public boolean isExpired() {
            return this.expiredTimestamp - System.currentTimeMillis() < 0;
        }

        public String getSignature() {
            return this.signature;
        }

        public void setSignature(String str) {
            this.signature = str;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String str) {
            this.appId = str;
        }
    }
}
