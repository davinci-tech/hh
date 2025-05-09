package com.huawei.health.manager.util;

import android.content.Context;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import health.compact.a.SharedPerferenceUtils;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class UserInfo {
    private static Context b;
    private static UserInfo e;
    private int c;
    private int i;
    private float j;
    private String l;
    private float n;
    private String g = BaseApplication.getAppPackage();

    /* renamed from: a, reason: collision with root package name */
    private int f2814a = 0;
    private float f = 60.0f;
    private float d = 170.0f;
    private int h = -1;

    private UserInfo() {
        Context context = b;
        if (context == null) {
            LogUtil.a("Step_UserInfo", "UserInfo mContext is null");
            return;
        }
        String[] v = SharedPerferenceUtils.v(context);
        if (v == null || v.length < 6) {
            LogUtil.a("Step_UserInfo", "UserInfo is invalid");
            return;
        }
        LogUtil.a("Step_UserInfo", "===userInfoStrings ", Arrays.toString(v));
        try {
            if (!"0".equals(v[0]) && Float.parseFloat(v[1]) != 0.0f && Float.parseFloat(v[2]) != 0.0f) {
                c(Float.parseFloat(v[1]), Float.parseFloat(v[2]), Integer.parseInt(v[3]), Integer.parseInt(v[4]), Integer.parseInt(v[5]));
                LogUtil.a("Step_UserInfo", "UserInfo from sp");
            } else {
                LogUtil.a("Step_UserInfo", "UserInfo from default");
                c(this.f, this.d, this.f2814a, 1, new HiUserInfo().getAgeOrDefaultValue());
                g();
            }
        } catch (NumberFormatException e2) {
            LogUtil.b("Step_UserInfo", "NumberFormatException exception", e2.getMessage());
        }
    }

    private void g() {
        HiHealthManager.d(b).fetchUserData(new HiCommonListener() { // from class: com.huawei.health.manager.util.UserInfo.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.c("Step_UserInfo", "getUserInfoFromDb");
                if (obj == null) {
                    LogUtil.h("Step_UserInfo", "getUserInfoFromDb, data is null.");
                    return;
                }
                if (!koq.e(obj, HiUserInfo.class)) {
                    LogUtil.h("Step_UserInfo", "getUserInfoFromDb userInfoLists not isListTypeMatch");
                    return;
                }
                List<HiUserInfo> list = (List) obj;
                if (koq.b(list)) {
                    LogUtil.h("Step_UserInfo", "getUserInfoFromDb userInfoLists is null or userInfoList.size() == 0");
                    return;
                }
                for (HiUserInfo hiUserInfo : list) {
                    if (hiUserInfo != null && hiUserInfo.getRelateType() == 0) {
                        if (hiUserInfo.isBirthdayValid() && hiUserInfo.isGenderValid() && hiUserInfo.isHeightValid() && hiUserInfo.isWeightValid()) {
                            UserInfo.this.c(hiUserInfo.getWeight(), hiUserInfo.getHeight(), hiUserInfo.getOwnerId(), hiUserInfo.getGender(), hiUserInfo.getAge());
                            LogUtil.a("Step_UserInfo", "setUserInfo sync");
                        } else {
                            LogUtil.a("Step_UserInfo", "setUserInfo sync isInValid");
                        }
                    }
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.b("Step_UserInfo", "getUserInfo onFailure = ", Integer.valueOf(i));
            }
        });
    }

    public static void c(Context context) {
        if (context != null) {
            b = context;
        }
    }

    public static UserInfo d() {
        UserInfo userInfo;
        synchronized (UserInfo.class) {
            if (e == null) {
                e = new UserInfo();
            }
            userInfo = e;
        }
        return userInfo;
    }

    public final void c(float f, float f2, int i, int i2, int i3) {
        if (f != this.n || f2 != this.j || i2 != this.h || i3 != this.c) {
            LogUtil.a("Step_UserInfo", "userInfo changed detail");
        }
        String str = this.g;
        this.l = str;
        this.n = f;
        this.j = f2;
        this.i = i;
        this.h = i2;
        this.c = i3;
        Context context = b;
        if (context != null) {
            SharedPerferenceUtils.c(context, str, f, f2, i, i2, i3);
        }
    }

    public int a() {
        return this.i;
    }

    public float c() {
        return this.j;
    }

    public float j() {
        return this.n;
    }

    public int e() {
        return this.h;
    }

    public int b() {
        return this.c;
    }
}
