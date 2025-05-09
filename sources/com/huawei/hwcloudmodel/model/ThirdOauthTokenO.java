package com.huawei.hwcloudmodel.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes5.dex */
public class ThirdOauthTokenO {
    public static final int ACCOUNT_TYPE_KOMOOT = 26;
    public static final int ACCOUNT_TYPE_RUNTASTIC = 25;
    public static final int ACCOUNT_TYPE_STRAVA = 27;
    public static final int TOKEN_TYPE_ACCESS = 1;
    public static final int TOKEN_TYPE_CODE = 3;
    public static final int TOKEN_TYPE_REFRESH = 2;
    private int thirdAccountType;
    private String thirdToken;
    private int thirdTokenType;

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface AccountType {
    }

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface TokenType {
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

    public String toString() {
        return "ThirdOauthTokenO{thirdAccountType=" + this.thirdAccountType + ", thirdTokenType=" + this.thirdTokenType + ", thirdToken='" + this.thirdToken + '}';
    }
}
