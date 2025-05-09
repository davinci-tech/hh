package defpackage;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;

/* loaded from: classes3.dex */
public class bwa {
    public static void a(Context context, IBaseResponseCallback iBaseResponseCallback) {
        if (context == null || iBaseResponseCallback == null) {
            LogUtil.h("CheckUserInfoUtil", "context == null || callback == null");
            return;
        }
        bqi c = bqi.c(context);
        UserInfomation e = c.e();
        if (e == null) {
            LogUtil.h("CheckUserInfoUtil", "checkUserInfo userInfo == null");
            iBaseResponseCallback.d(-2, null);
            return;
        }
        boolean z = (e.isGenderValid() && e.isWeightValid() && e.isHeightValid() && e.isBirthdayValid()) ? false : true;
        LogUtil.a("CheckUserInfoUtil", "hwUserProfileMgr.isInitFinish() ", Boolean.valueOf(c.c()), "isUserInvalid ", Boolean.valueOf(!e.isGenderValid()), Boolean.valueOf(!e.isWeightValid()), Boolean.valueOf(!e.isHeightValid()), Boolean.valueOf(!e.isBirthdayValid()));
        if (z && c.c()) {
            nry.c(context, iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(-2, null);
        }
    }
}
