package defpackage;

import android.content.Context;
import com.huawei.featureuserprofile.account.data.IAccountDataMgr;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class bqt implements IAccountDataMgr {
    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void init(Context context) {
        LogUtil.h("NullAccountDataMgr", "NullAccountDataMgr init,error");
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void destroy() {
        LogUtil.h("NullAccountDataMgr", "NullAccountDataMgr destroy,error");
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public UserInfomation getUserInfo() {
        LogUtil.h("NullAccountDataMgr", "NullAccountDataMgr getUserInfo,error");
        return null;
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void setUserInfo(UserInfomation userInfomation, UserInfoMedia.UserInfoWriter.Callback callback) {
        LogUtil.h("NullAccountDataMgr", "NullAccountDataMgr setUserInfo,error");
        bsf.c(callback, -1);
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void sync() {
        LogUtil.h("NullAccountDataMgr", "NullAccountDataMgr sync,error");
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void sync(UserInfoMedia.UserInfoReader.Callback callback) {
        LogUtil.h("NullAccountDataMgr", "NullAccountDataMgr sync,error");
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public boolean checkInit() {
        ReleaseLogUtil.e("R_PersonalInfo_NullAccountDataMgr", "NullAccountDataMgr checkInit isInit = false");
        return false;
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void refreshAccountDataCache(UserInfomation userInfomation) {
        LogUtil.h("NullAccountDataMgr", "NullAccountDataMgr refreshAccountDataCache");
    }
}
