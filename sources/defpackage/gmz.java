package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.userprofile.AddPrivacyRecordReq;
import com.huawei.hwcloudmodel.model.userprofile.AddPrivacyRecordRsp;
import com.huawei.hwcloudmodel.model.userprofile.GetPrivacyRecordReq;
import com.huawei.hwcloudmodel.model.userprofile.GetPrivacyRecordRsp;
import com.huawei.hwcloudmodel.model.userprofile.PrivacyRecord;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.up.callback.CommonCallback;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes.dex */
public class gmz {
    private static gmz c;
    private Context b;
    private jbx d;

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f12873a = {"", "true", "", "", "true", "true", "", ""};
    private static final Object e = new Object();

    private static boolean d(int i) {
        return (i == 601 || i == 602 || i == 603 || i == 604 || i == 402 || i == 403 || i == 12 || i == 13 || i == 14 || i == 15 || i == 203 || i == 405 || i == 701) ? false : true;
    }

    private static boolean e(int i) {
        return i == 601 || i == 602 || i == 603 || i == 604 || i == 403 || i == 405;
    }

    private gmz() {
        this.b = null;
        this.d = null;
        this.b = BaseApplication.getContext();
        if (this.d == null) {
            this.d = jbx.d();
        }
    }

    public static gmz d() {
        gmz gmzVar;
        synchronized (e) {
            if (c == null) {
                c = new gmz();
            }
            gmzVar = c;
        }
        return gmzVar;
    }

    public String c(String str) {
        return SharedPreferenceManager.b(this.b, String.valueOf(1004), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, StorageParams storageParams) {
        SharedPreferenceManager.e(this.b, String.valueOf(1004), str, str2, storageParams);
    }

    public void a() {
        a("custome_define_init_flag", "false", null);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10016), "health_first_login");
        LogUtil.a("HWUserProfileMgrUserPrivacy", "firstLogin = ", b);
        if ("false".equals(b)) {
            return;
        }
        SharedPreferenceManager.e(this.b, String.valueOf(10016), "health_first_login", "false", (StorageParams) null);
        if (Utils.g()) {
            return;
        }
        c((CommonCallback) null);
    }

    public void b() {
        dsl.q();
        a("custome_define_init_flag", "true", null);
        for (int i = 1; i < 8; i++) {
            String[] strArr = f12873a;
            a("cloud_user_privacy" + i, strArr[i], null);
            a("cloud_user_privacy_reupload" + i, "", null);
            a("cloud_user_privacy_reupload_desp" + i, "", null);
            KeyValDbManager.b(this.b).e("cloud_user_privacy" + i, strArr[i]);
        }
        knx.d();
        a("cloud_user_privacy_reupload11", "", null);
        a("cloud_user_privacy_reupload_desp11", "", null);
        a("cloud_user_privacy402", "", null);
        a("cloud_user_privacy13", "", null);
        a("cloud_user_privacy14", "", null);
        a("cloud_user_privacy15", "", null);
        i();
    }

    private void i() {
        SharedPreferenceManager.e(this.b, Integer.toString(10000), "health_product_recommend", "", new StorageParams());
        SharedPreferenceManager.e(this.b, Integer.toString(10000), "personalized_recommend", "", new StorageParams());
    }

    public void c(final CommonCallback commonCallback) {
        LogUtil.a("HWUserProfileMgrUserPrivacy", " downloadUserPrivacy Entry");
        final GetPrivacyRecordReq getPrivacyRecordReq = new GetPrivacyRecordReq();
        getPrivacyRecordReq.setPrivacyId(0);
        ThreadPoolManager.d().execute(new Runnable() { // from class: gnf
            @Override // java.lang.Runnable
            public final void run() {
                gmz.this.c(getPrivacyRecordReq, commonCallback);
            }
        });
    }

    /* synthetic */ void c(GetPrivacyRecordReq getPrivacyRecordReq, final CommonCallback commonCallback) {
        jbs.a(this.b).a(getPrivacyRecordReq, new ICloudOperationResult() { // from class: gnd
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                gmz.this.c(commonCallback, (GetPrivacyRecordRsp) obj, str, z);
            }
        });
    }

    /* synthetic */ void c(CommonCallback commonCallback, GetPrivacyRecordRsp getPrivacyRecordRsp, String str, boolean z) {
        LogUtil.a("HWUserProfileMgrUserPrivacy", "downloadUserPrivacy result is " + z);
        if (z) {
            b(getPrivacyRecordRsp, commonCallback);
            return;
        }
        LogUtil.a("HWUserProfileMgrUserPrivacy", "getPrivacyRecord fail " + str);
        if (commonCallback != null) {
            commonCallback.onFail(-1);
        }
    }

    private void b(GetPrivacyRecordRsp getPrivacyRecordRsp, CommonCallback commonCallback) {
        try {
            List<PrivacyRecord> privacyRecords = getPrivacyRecordRsp.getPrivacyRecords();
            if (privacyRecords != null) {
                boolean z = false;
                boolean z2 = false;
                for (PrivacyRecord privacyRecord : privacyRecords) {
                    if (privacyRecord.getPrivacyId().intValue() != 1) {
                        int intValue = privacyRecord.getPrivacyId().intValue();
                        String str = "cloud_user_privacy" + intValue;
                        if (intValue == 10) {
                            SharedPreferenceManager.e(this.b, Integer.toString(10000), "health_product_recommend", privacyRecord.getOpinion().intValue() == 2 ? "0" : "1", new StorageParams());
                        } else if (intValue == 12) {
                            SharedPreferenceManager.e(this.b, Integer.toString(10000), "personalized_recommend", privacyRecord.getOpinion().intValue() == 2 ? "0" : "1", new StorageParams());
                        } else if (intValue == 11) {
                            LogUtil.a("HWUserProfileMgrUserPrivacy", "setShareStatus opinion = ", privacyRecord.getOpinion());
                            knx.b(privacyRecord.getOpinion().intValue() == 1);
                            z2 = true;
                        } else {
                            if (intValue == 402) {
                                z = true;
                            }
                            if (LoginInit.getInstance(BaseApplication.getContext()).isMinorAccount() && (intValue == 13 || intValue == 14 || intValue == 15)) {
                                a(str, "false", null);
                            } else {
                                a(str, String.valueOf(privacyRecord.getOpinion().intValue() == 1), null);
                            }
                        }
                        KeyValDbManager.b(this.b).e(str, String.valueOf(privacyRecord.getOpinion().intValue() == 1));
                        c(true);
                    }
                }
                a(z);
                f();
                b(z2);
            }
            if (commonCallback != null) {
                commonCallback.onSuccess(new Bundle());
            }
        } catch (ClassCastException e2) {
            LogUtil.c("HWUserProfileMgrUserPrivacy", "operationReultRsp fail ", e2.getMessage());
        }
    }

    private void b(boolean z) {
        LogUtil.a("HWUserProfileMgrUserPrivacy", "updateDefaultShareStatus, hasShareStatus is ", Boolean.valueOf(z));
        if (z) {
            return;
        }
        knx.b((Utils.o() || LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) ? false : true);
    }

    private void a(boolean z) {
        LogUtil.a("HWUserProfileMgrUserPrivacy", "setDefaultHealthKitStatus ");
        if (z || Utils.o()) {
            return;
        }
        c(402, true, String.valueOf(402), new IBaseResponseCallback() { // from class: gnh
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                gmz.a(i, obj);
            }
        });
    }

    static /* synthetic */ void a(int i, Object obj) {
        Object[] objArr = new Object[1];
        objArr[0] = i == 0 ? "onResponse setUserPrivacy success" : "onResponse setUserPrivacy failure";
        LogUtil.a("HWUserProfileMgrUserPrivacy", objArr);
    }

    private void f() {
        LogUtil.a("HWUserProfileMgrUserPrivacy", "setDefaultPersonalRecommendStatus.");
        if (!TextUtils.isEmpty(SharedPreferenceManager.b(this.b, Integer.toString(10000), "personalized_recommend")) || Utils.o()) {
            return;
        }
        LogUtil.a("HWUserProfileMgrUserPrivacy", "PersonalRecommendStatus is empty. Set default value.");
        c(12, !LoginInit.getInstance(BaseApplication.getContext()).isKidAccount(), String.valueOf(12), new IBaseResponseCallback() { // from class: gna
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                gmz.d(i, obj);
            }
        });
    }

    static /* synthetic */ void d(int i, Object obj) {
        Object[] objArr = new Object[1];
        objArr[0] = i == 0 ? "onResponse setDefaultPersonalRecommendStatus success" : "onResponse setDefaultPersonalRecommendStatus failure";
        LogUtil.a("HWUserProfileMgrUserPrivacy", objArr);
    }

    public void c(int i, boolean z, String str, IBaseResponseCallback iBaseResponseCallback) {
        if (i == 10) {
            SharedPreferenceManager.e(this.b, Integer.toString(10000), "health_product_recommend", z ? "1" : "0", new StorageParams());
        } else if (i == 12) {
            SharedPreferenceManager.e(this.b, Integer.toString(10000), "personalized_recommend", z ? "1" : "0", new StorageParams());
        } else if (i == 11) {
            knx.b(z);
            ixx.d().a(LoginInit.getInstance(this.b).getAccountInfo(1011));
        } else {
            LogUtil.a("HWUserProfileMgrUserPrivacy", "privacyId is not privacy recommendation switch");
        }
        a("cloud_user_privacy" + i, String.valueOf(z), null);
        KeyValDbManager.b(this.b).e("cloud_user_privacy" + i, String.valueOf(z));
        if (Utils.i()) {
            AddPrivacyRecordReq addPrivacyRecordReq = new AddPrivacyRecordReq();
            addPrivacyRecordReq.setPrivacyId(Integer.valueOf(i));
            addPrivacyRecordReq.setOpinion(Integer.valueOf(z ? 1 : 2));
            addPrivacyRecordReq.setDescription(str);
            a(addPrivacyRecordReq, iBaseResponseCallback, i, z, str);
        }
    }

    private void a(final AddPrivacyRecordReq addPrivacyRecordReq, final IBaseResponseCallback iBaseResponseCallback, final int i, final boolean z, final String str) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gne
            @Override // java.lang.Runnable
            public final void run() {
                gmz.this.e(addPrivacyRecordReq, iBaseResponseCallback, i, z, str);
            }
        });
    }

    /* synthetic */ void e(AddPrivacyRecordReq addPrivacyRecordReq, final IBaseResponseCallback iBaseResponseCallback, final int i, final boolean z, final String str) {
        jbs.a(this.b).c(addPrivacyRecordReq, new ICloudOperationResult() { // from class: gmy
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z2) {
                gmz.this.a(iBaseResponseCallback, i, z, str, (AddPrivacyRecordRsp) obj, str2, z2);
            }
        });
    }

    /* synthetic */ void a(IBaseResponseCallback iBaseResponseCallback, int i, boolean z, String str, AddPrivacyRecordRsp addPrivacyRecordRsp, String str2, boolean z2) {
        if (z2) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, null);
            }
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_PRIVACY_SWITCH_85070029.value(), 0);
            a("cloud_user_privacy_reupload" + i, "", null);
            a("cloud_user_privacy_reupload_desp" + i, "", null);
            return;
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(300099, null);
        }
        a("cloud_user_privacy_reupload" + i, String.valueOf(z), null);
        a("cloud_user_privacy_reupload_desp" + i, str, null);
        if (addPrivacyRecordRsp != null) {
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_PRIVACY_SWITCH_85070029.value(), addPrivacyRecordRsp.getResultCode().intValue());
        }
    }

    public String c(int i) {
        if ((i < 1 || (i >= 8 && i != 11 && i != 202)) && i != 401 && d(i)) {
            LogUtil.h("HWUserProfileMgrUserPrivacy", "getUserPrivacy invalid privacyId");
            return null;
        }
        String c2 = c("cloud_user_privacy" + i);
        boolean isKidAccount = LoginInit.getInstance(BaseApplication.getContext()).isKidAccount();
        if (!TextUtils.isEmpty(c2)) {
            return c2;
        }
        if (i == 401 || e(i)) {
            return String.valueOf(false);
        }
        if (i == 11) {
            return isKidAccount ? String.valueOf(false) : knx.a();
        }
        if (i == 402) {
            return String.valueOf(!Utils.o());
        }
        if (i == 12) {
            return String.valueOf((Utils.o() || isKidAccount) ? false : true);
        }
        if (i != 202 && i != 701) {
            if (i == 203) {
                return "false";
            }
            if (i == 13 || i == 14 || i == 15) {
                if (LoginInit.getInstance(BaseApplication.getContext()).isMinorAccount()) {
                    return "false";
                }
            } else {
                return f12873a[i];
            }
        }
        return "true";
    }

    private void b(final int i) {
        String c2 = c("cloud_user_privacy_reupload" + i);
        String c3 = c("cloud_user_privacy_reupload_desp" + i);
        if (TextUtils.isEmpty(c2) || TextUtils.isEmpty(c3)) {
            return;
        }
        AddPrivacyRecordReq addPrivacyRecordReq = new AddPrivacyRecordReq();
        addPrivacyRecordReq.setPrivacyId(Integer.valueOf(i));
        addPrivacyRecordReq.setOpinion(Integer.valueOf(c2.equals("true") ? 1 : 2));
        addPrivacyRecordReq.setDescription(c3);
        jbs.a(this.b).c(addPrivacyRecordReq, new ICloudOperationResult<AddPrivacyRecordRsp>() { // from class: gmz.4
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void operationResult(AddPrivacyRecordRsp addPrivacyRecordRsp, String str, boolean z) {
                if (z && addPrivacyRecordRsp.getResultCode().equals(0)) {
                    gmz.this.a("cloud_user_privacy_reupload" + i, "", null);
                    gmz.this.a("cloud_user_privacy_reupload_desp" + i, "", null);
                }
            }
        });
    }

    public void c() {
        LogUtil.a("HWUserProfileMgrUserPrivacy", "reuploadUserPrivacy Enter");
        ThreadPoolManager.d().execute(new Runnable() { // from class: gng
            @Override // java.lang.Runnable
            public final void run() {
                gmz.this.e();
            }
        });
    }

    /* synthetic */ void e() {
        for (int i = 1; i < 8; i++) {
            b(i);
        }
    }

    public void c(boolean z) {
        LogUtil.a("HWUserProfileMgrUserPrivacy", "setUserPrivacyUpgraded, flag = " + z);
        a("KEY_PERSONAL_PRIVACY_SETTINGS_UPGRADED_FLAG", String.valueOf(z), null);
    }
}
