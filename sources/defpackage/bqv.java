package defpackage;

import android.content.Context;
import com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class bqv implements IAccountDataExtMgr {
    @Override // com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr
    public void init(Context context) {
        LogUtil.h("NullAccountDataExtMgr", "NullAccountDataExtMgr init,error");
    }

    @Override // com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr
    public void destroy(Context context) {
        LogUtil.h("NullAccountDataExtMgr", "NullAccountDataExtMgr destroy,error");
    }

    @Override // com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr
    public void setUserInfo(UserInfomation userInfomation, UserInfoMedia.UserInfoWriter.Callback callback) {
        LogUtil.h("NullAccountDataExtMgr", "NullAccountDataExtMgr setUserInfo,error");
        bsf.c(callback, -1);
    }

    @Override // com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr
    public UserInfomation getUserInfo() {
        LogUtil.h("NullAccountDataExtMgr", "NullAccountDataExtMgr getUserInfo,error");
        return null;
    }

    @Override // com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr
    public boolean checkInit() {
        ReleaseLogUtil.e("R_PersonalInfo_NullAccountDataExtMgr", "NullAccountDataExtMgr checkInit isInit = false");
        return false;
    }
}
