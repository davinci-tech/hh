package com.huawei.profile.account;

import com.huawei.profile.utils.logger.DsLog;

/* loaded from: classes6.dex */
public class Account {
    private static final String QUOTATION_MARK = "\"";
    private static final String TAG = "Account";
    private String accessToken;
    private long timestamp;
    private String userId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        if (str == null) {
            DsLog.et(TAG, "userId is null", new Object[0]);
        } else {
            this.userId = str.replace(QUOTATION_MARK, "");
        }
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        if (str == null) {
            DsLog.et(TAG, "accessToken is null", new Object[0]);
        } else {
            this.accessToken = str.replace(QUOTATION_MARK, "");
        }
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}
