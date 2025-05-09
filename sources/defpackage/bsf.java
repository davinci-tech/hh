package defpackage;

import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.up.model.UserInfomation;

/* loaded from: classes3.dex */
public class bsf {
    public static void d(UserInfoMedia.UserInfoReader.Callback callback, UserInfomation userInfomation) {
        if (callback != null) {
            callback.onSuccess(userInfomation);
        }
    }

    public static void b(UserInfoMedia.UserInfoWriter.Callback callback) {
        if (callback != null) {
            callback.onSuccess();
        }
    }

    public static void d(UserInfoMedia.UserInfoReader.Callback callback, int i) {
        if (callback != null) {
            callback.onFail(i);
        }
    }

    public static void c(UserInfoMedia.UserInfoWriter.Callback callback, int i) {
        if (callback != null) {
            callback.onFail(i);
        }
    }

    public static void a(BaseResponseCallback<UserInfomation> baseResponseCallback, int i, UserInfomation userInfomation) {
        if (baseResponseCallback != null) {
            baseResponseCallback.onResponse(i, userInfomation);
        }
    }
}
