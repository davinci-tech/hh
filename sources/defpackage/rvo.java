package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.userprofile.DeleteAllUserProfileReq;
import com.huawei.hwcloudmodel.model.userprofile.DeleteAllUserProfileRsp;
import com.huawei.hwcloudmodel.model.userprofile.LastClearCloudDataTimeRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class rvo {
    private static rvo b;
    private gmz e = gmz.d();

    private rvo(Context context) {
    }

    public static rvo e(Context context) {
        if (b == null) {
            b = new rvo(context);
        }
        return b;
    }

    public String a(int i) {
        if (i == 6) {
            return this.e.c(7);
        }
        return this.e.c(i);
    }

    public void e(int i, boolean z) {
        LogUtil.a("PersonalPrivacySettingsInteractors", "setPersonalPrivacySettingValue... privacyId = ", Integer.valueOf(i), ", isOpen = ", Boolean.valueOf(z));
        this.e.c(i, z, String.valueOf(i), new IBaseResponseCallback() { // from class: rvo.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 0) {
                    LogUtil.a("PersonalPrivacySettingsInteractors", "onResponse setUserPrivacy success");
                } else {
                    LogUtil.b("PersonalPrivacySettingsInteractors", "onResponse setUserPrivacy failure");
                }
            }
        });
    }

    public void b(final IBaseResponseCallback iBaseResponseCallback) {
        jbs.a(BaseApplication.e()).b(new DeleteAllUserProfileReq(), new ResultCallback<DeleteAllUserProfileRsp>() { // from class: rvo.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(DeleteAllUserProfileRsp deleteAllUserProfileRsp) {
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(0, null);
                }
                if (deleteAllUserProfileRsp.getResultCode().intValue() == 0) {
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_CLEAN_CLOUD_DATA_85070028.value(), 0);
                } else {
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_CLEAN_CLOUD_DATA_85070028.value(), deleteAllUserProfileRsp.getResultCode().intValue());
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("PersonalPrivacySettingsInteractors", "deleteAllData fail:", th.getMessage());
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(300099, null);
                }
            }
        });
    }

    public void e(final IBaseResponseCallback iBaseResponseCallback) {
        jbs.a(BaseApplication.e()).e(new ResultCallback<LastClearCloudDataTimeRsp>() { // from class: rvo.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(LastClearCloudDataTimeRsp lastClearCloudDataTimeRsp) {
                if (iBaseResponseCallback != null) {
                    if (lastClearCloudDataTimeRsp.getResultCode().intValue() == 0) {
                        iBaseResponseCallback.d(0, lastClearCloudDataTimeRsp.getLastRecord());
                    } else {
                        iBaseResponseCallback.d(300099, null);
                    }
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("PersonalPrivacySettingsInteractors", "getLastClearCloudDataTime fail:", th.getMessage());
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(300099, null);
                }
            }
        });
    }

    public void e() {
        if (!Utils.i()) {
            LogUtil.h("PersonalPrivacySettingsInteractors", "reUploadUserPrivacy not, no cloud version.");
        } else {
            this.e.c();
        }
    }
}
