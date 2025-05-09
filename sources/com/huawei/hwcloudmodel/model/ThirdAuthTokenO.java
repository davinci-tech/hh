package com.huawei.hwcloudmodel.model;

import java.util.List;

/* loaded from: classes7.dex */
public class ThirdAuthTokenO {
    private ThirdAuthToken thirdAuthToken;
    private List<ThirdAuthToken> thirdAuthTokenList;

    public ThirdAuthToken getThirdAuthToken() {
        return this.thirdAuthToken;
    }

    public List<ThirdAuthToken> getThirdAuthTokenList() {
        return this.thirdAuthTokenList;
    }

    public static class ThirdAuthToken {
        private int thirdAccountType = 0;

        public int getThirdAccountType() {
            return this.thirdAccountType;
        }
    }
}
