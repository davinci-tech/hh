package com.huawei.featureuserprofile.account.ext;

import android.content.Context;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.up.model.UserInfomation;

/* loaded from: classes3.dex */
public interface IAccountDataExtMgr {
    boolean checkInit();

    void destroy(Context context);

    UserInfomation getUserInfo();

    void init(Context context);

    void setUserInfo(UserInfomation userInfomation, UserInfoMedia.UserInfoWriter.Callback callback);
}
