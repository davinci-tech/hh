package com.huawei.featureuserprofile.common;

import android.os.Message;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.up.model.UserInfomation;

/* loaded from: classes3.dex */
public interface IUserInfomationHandler {
    void processDestroy();

    void processInit(Message message);

    void processModifyUserInfo(Message message, UserInfomation userInfomation, UserInfoMedia.UserInfoWriter.Callback callback);
}
