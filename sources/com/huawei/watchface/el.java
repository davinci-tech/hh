package com.huawei.watchface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface el {
    ek[] a() default {};

    ek[] b() default {};

    ek[] c() default {};

    String[] d() default {"userToken", "usertoken", "reservedInfor", "licenseReq", "authCode", "deviceId", "userRefreshToken", "sign", "x-sign", "deviceid", "phoneId", "publicKey"};

    String[] e() default {"userToken", "reservedInfor", "licenseReq", "authCode", "deviceId", "userRefreshToken", "sign", "phoneId", "publicKey"};

    boolean f() default false;

    String[] g() default {"resultCode", "resultcode", "retCode"};

    String[] h() default {"resultInfo", "resultinfo", "retDesc"};
}
