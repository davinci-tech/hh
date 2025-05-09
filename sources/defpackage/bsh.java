package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class bsh implements UserInfoMedia.UserInfoWriter {
    private Context c;
    private boolean e = true;
    private int d = 0;

    bsh(Context context) {
        this.c = null;
        if (context == null) {
            this.c = BaseApplication.getContext();
        } else {
            this.c = context.getApplicationContext();
        }
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter
    public void write(UserInfomation userInfomation, UserInfoMedia.UserInfoWriter.Callback callback) {
        synchronized (this) {
            if (this.e) {
                LogUtil.h("HiHealthWriter", "block!!!");
            } else {
                e(userInfomation, callback);
            }
        }
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter
    public void block() {
        synchronized (this) {
            this.e = true;
        }
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter
    public void unBlock() {
        synchronized (this) {
            this.e = false;
        }
    }

    public void e(UserInfomation userInfomation, final UserInfoMedia.UserInfoWriter.Callback callback) {
        synchronized (this) {
            if (this.e) {
                LogUtil.h("HiHealthWriter", "block!!!");
                return;
            }
            UserInfomation copyFrom = userInfomation.copyFrom();
            copyFrom.transSelf2METRIC();
            if (this.d == 0) {
                LogUtil.a("HiHealthWriter", "setUserData start :DATA_ALL");
                LogUtil.h("HiHealthWriter", "all data set in HiUserInfo to DB,warning,not permitted");
                return;
            }
            HiUserInfo a2 = a(copyFrom);
            LogUtil.c("HiHealthWriter", " setUserData start, user=(1)", a2.getName(), " ", Integer.valueOf(a2.getBirthday()), " ", Integer.valueOf(a2.getGender()), " ", a2.getHeadImgUrl());
            LogUtil.c("HiHealthWriter", " setUserData start, user=(2)", Integer.valueOf(a2.getHeight()), " ", Float.valueOf(a2.getWeight()), " ", Integer.valueOf(a2.getUnitType()));
            HandlerExecutor.d(new Runnable() { // from class: bso
                @Override // java.lang.Runnable
                public final void run() {
                    OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("callSetUserdata", "HiHealthWriter");
                }
            }, PreConnectManager.CONNECT_INTERNAL);
            HiHealthNativeApi.a(this.c).setUserData(a2, new HiCommonListener() { // from class: bsh.1
                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onSuccess(int i, Object obj) {
                    LogUtil.a("HiHealthWriter", "setUserData onSuccess");
                    bsf.b(callback);
                }

                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onFailure(int i, Object obj) {
                    LogUtil.a("HiHealthWriter", "setUserData onFailure:", Integer.valueOf(i), " ", obj);
                    bsf.c(callback, -1);
                }
            });
        }
    }

    private HiUserInfo a(UserInfomation userInfomation) {
        HiUserInfo hiUserInfo = new HiUserInfo();
        hiUserInfo.setUser(1073741824);
        int i = this.d;
        if (i == 268435456) {
            LogUtil.a("HiHealthWriter", "setUserData start :UP_DATA_ONLY");
            hiUserInfo.setModifiedIntent(268435456);
        } else if (i == 536870912) {
            LogUtil.a("HiHealthWriter", "setUserData start :DP_DATA_ONLY");
            hiUserInfo.setModifiedIntent(536870912);
        } else {
            LogUtil.h("HiHealthWriter", "unknown data type in HiUserInfo to DB,warning");
        }
        if (!TextUtils.isEmpty(userInfomation.getName())) {
            hiUserInfo.setName(userInfomation.getName());
        }
        if (userInfomation.isGenderValid()) {
            int gender = userInfomation.getGender();
            if (gender == 0) {
                hiUserInfo.setGender(1);
            } else if (gender == 1) {
                hiUserInfo.setGender(0);
            } else {
                hiUserInfo.setGender(gender);
            }
        }
        if (userInfomation.isBirthdayValid()) {
            hiUserInfo.setBirthday(nsn.e(userInfomation.getBirthday()));
        }
        if (userInfomation.isHeightValid()) {
            hiUserInfo.setHeight(userInfomation.getHeight());
        }
        if (userInfomation.isWeightValid()) {
            hiUserInfo.setWeight(userInfomation.getWeight());
        }
        hiUserInfo.setUnitType(userInfomation.getClientSet());
        Object[] objArr = new Object[8];
        objArr[0] = "is reset to default height:";
        objArr[1] = Boolean.valueOf(hiUserInfo.getHeight() == 170);
        objArr[2] = " default weight:";
        objArr[3] = Boolean.valueOf(hiUserInfo.getWeight() == 60.0f);
        objArr[4] = " default birthday:";
        objArr[5] = Boolean.valueOf(hiUserInfo.getBirthday() == 19900801);
        objArr[6] = " default gender:";
        objArr[7] = Boolean.valueOf(hiUserInfo.getGender() == 1);
        ReleaseLogUtil.e("R_PersonalInfo_HiHealthWriter", objArr);
        hiUserInfo.setCreateTime(System.currentTimeMillis());
        return hiUserInfo;
    }

    public void d(int i) {
        synchronized (this) {
            this.d = i;
        }
    }
}
