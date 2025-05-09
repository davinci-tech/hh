package defpackage;

import android.content.Context;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class bsi implements UserInfoMedia.UserInfoReader {

    /* renamed from: a, reason: collision with root package name */
    private boolean f492a = true;
    private Context b;

    bsi(Context context) {
        this.b = null;
        if (context == null) {
            this.b = BaseApplication.getContext();
        } else {
            this.b = context.getApplicationContext();
        }
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader
    public void read(UserInfoMedia.UserInfoReader.Callback callback) {
        synchronized (this) {
            if (this.f492a) {
                LogUtil.h("HiHealthReader", "read block!!!");
            } else {
                LogUtil.a("HiHealthReader", "sync(HiHealthReader) callback:", callback);
                b(callback);
            }
        }
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader
    public void block() {
        synchronized (this) {
            LogUtil.a("HiHealthReader", "block HiHealthReader");
            this.f492a = true;
        }
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader
    public void unBlock() {
        synchronized (this) {
            LogUtil.a("HiHealthReader", "unBlock HiHealthReader");
            this.f492a = false;
        }
    }

    public void b(UserInfoMedia.UserInfoReader.Callback callback) {
        synchronized (this) {
            if (this.f492a) {
                LogUtil.h("HiHealthReader", "sync block!!!");
            } else {
                LogUtil.a("TimeEat_HiHealthReader", "start to fetchUserData");
                c(callback);
            }
        }
    }

    private void c(final UserInfoMedia.UserInfoReader.Callback callback) {
        HiHealthNativeApi.a(this.b).fetchUserData(new HiCommonListener() { // from class: bsi.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                List list;
                LogUtil.a("TimeEat_HiHealthReader", "Enter fetchUserData onSuccess");
                if (obj != null) {
                    try {
                        list = (List) obj;
                    } catch (ClassCastException unused) {
                        LogUtil.b("HiHealthReader", "fetchUserData data ClassCastException");
                        list = null;
                    }
                    if (list == null || list.size() <= 0) {
                        ReleaseLogUtil.d("R_PersonalInfo_HiHealthReader", "fetchUserData data size = 0");
                        UserInfoMedia.UserInfoReader.Callback callback2 = callback;
                        if (callback2 != null) {
                            callback2.onFail(300099);
                            return;
                        }
                        return;
                    }
                    HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                    if (hiUserInfo == null) {
                        ReleaseLogUtil.d("R_PersonalInfo_HiHealthReader", "hiUserInfo = null");
                        UserInfoMedia.UserInfoReader.Callback callback3 = callback;
                        if (callback3 != null) {
                            callback3.onFail(300099);
                            return;
                        }
                        return;
                    }
                    ReleaseLogUtil.e("R_PersonalInfo_HiHealthReader", "verify userInfo, isGenderValid: ", Boolean.valueOf(hiUserInfo.isGenderValid()), " isBirthdayValid: ", Boolean.valueOf(hiUserInfo.isBirthdayValid()), " isHeightValid: ", Boolean.valueOf(hiUserInfo.isHeightValid()), " isWeightValid: ", Boolean.valueOf(hiUserInfo.isWeightValid()));
                    bsi.this.b(hiUserInfo.getOwnerId());
                    UserInfomation userInfomation = new UserInfomation();
                    userInfomation.loadAccountData(hiUserInfo);
                    userInfomation.loadAccountExtData(hiUserInfo);
                    userInfomation.setPicPath(bvx.a());
                    userInfomation.transSelf2METRIC();
                    bsf.d(callback, userInfomation);
                    LogUtil.a("TimeEat_HiHealthReader", "Leave fetchUserData onSuccess");
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                ReleaseLogUtil.d("R_PersonalInfo_HiHealthReader", "fetchUserData onFailure errCode: ", Integer.valueOf(i), " errMsg: ", obj);
                bsf.d(callback, -1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (!Utils.i() || nry.e()) {
            return;
        }
        boolean z = BaseApplication.getContext().getSharedPreferences("sync_record", 0).getBoolean("firstsynccompleteflag" + i, false);
        LogUtil.a("HiHealthReader", "updateSyncCompleteStatus isSyncComplete is ", Boolean.valueOf(z));
        nry.a(z);
    }
}
