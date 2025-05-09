package com.huawei.featureuserprofile.account.data;

import android.content.Context;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.up.model.UserInfomation;

/* loaded from: classes3.dex */
public interface IAccountDataMgr {
    boolean checkInit();

    void destroy();

    UserInfomation getUserInfo();

    void init(Context context);

    void refreshAccountDataCache(UserInfomation userInfomation);

    void setUserInfo(UserInfomation userInfomation, UserInfoMedia.UserInfoWriter.Callback callback);

    void sync();

    void sync(UserInfoMedia.UserInfoReader.Callback callback);
}
