package com.huawei.picture.security.account.bean;

import android.text.TextUtils;
import java.util.List;

/* loaded from: classes6.dex */
public class UserAccountInfo extends UserInfoBean {
    private int siteId;
    List<UserTokenBean> userTokens;

    public int getSiteId() {
        return this.siteId;
    }

    public void setSiteId(int i) {
        this.siteId = i;
    }

    public List<UserTokenBean> getUserTokens() {
        return this.userTokens;
    }

    public void setUserTokens(List<UserTokenBean> list) {
        this.userTokens = list;
    }

    public String getToken(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        for (UserTokenBean userTokenBean : this.userTokens) {
            if (str.equals(userTokenBean.getAppId())) {
                return userTokenBean.getToken();
            }
        }
        return "";
    }
}
