package com.huawei.ui.main.stories.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import defpackage.izx;
import defpackage.moh;
import health.compact.a.Services;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes7.dex */
public class UserInfoMockInteractor {
    private Context c;

    public UserInfoMockInteractor(Context context) {
        this.c = context;
    }

    public void e() {
        LogUtil.a("UserInfoMockUtil", "insertUserInfoDataToDB");
        ThreadPoolManager.e(1, 1, "UserInfoMockUtil").execute(new Runnable() { // from class: com.huawei.ui.main.stories.utils.UserInfoMockInteractor.2
            @Override // java.lang.Runnable
            public void run() {
                UserInfomation userInfomation = new UserInfomation();
                userInfomation.setWeight(68.0f);
                userInfomation.setHeight(175);
                userInfomation.setGender(0);
                userInfomation.setPortraitUrl("https://");
                userInfomation.setName("小明");
                userInfomation.setBirthday("19950808");
                userInfomation.setPicPath(izx.bEe_(UserInfoMockInteractor.this.c, "1", UserInfoMockInteractor.this.dWn_()));
                userInfomation.setBirthdayStatus(UserInfomation.BIRTHDAY_SETED);
                userInfomation.setSetTime(System.currentTimeMillis());
                ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).setUserInfo(userInfomation, new ICloudOperationResult<Boolean>() { // from class: com.huawei.ui.main.stories.utils.UserInfoMockInteractor.2.3
                    @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void operationResult(Boolean bool, String str, boolean z) {
                        LogUtil.a("UserInfoMockUtil", "setUserInfo result = ", Boolean.valueOf(z));
                    }
                });
            }
        });
    }

    public Bitmap dWn_() {
        Bitmap bitmap = null;
        try {
            InputStream e = moh.e("healthgroup", "pic_6.png");
            try {
                bitmap = BitmapFactory.decodeStream(e);
                if (e != null) {
                    e.close();
                }
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.b("UserInfoMockUtil", "readProfileBitmapFromStoreDemo error");
        }
        return bitmap;
    }
}
