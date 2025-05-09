package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.health.pluginhealthzone.UserInfoCallback;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginhealthzone.cloud.HttpDataCallback;
import com.huawei.pluginhealthzone.jsmodule.PluginHealthH5InteractionUtil;
import defpackage.exh;
import defpackage.rbf;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

@ApiDefine(uri = FamilyHealthZoneApi.class)
@Singleton
/* loaded from: classes6.dex */
public class mpi implements FamilyHealthZoneApi {
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private volatile int f15096a;
    private volatile long d = 0;
    private volatile String e = "";

    @Override // com.huawei.health.pluginhealthzone.FamilyHealthZoneApi
    public Class<? extends JsModuleBase> getCommonJsModule(String str) {
        return PluginHealthH5InteractionUtil.class;
    }

    @Override // com.huawei.health.pluginhealthzone.FamilyHealthZoneApi
    public void getCommonUsedDevices(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("FamilyHealthZoneImpl", "getCommonUsedDevices callback is null!");
        } else {
            mpq.d().getCommonUsedDevices("FAMILY_SPACE", new HttpDataCallback() { // from class: mpi.2
                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onFailure(int i, String str) {
                    iBaseResponseCallback.d(i, str);
                }

                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onSuccess(JSONObject jSONObject) {
                    iBaseResponseCallback.d(0, jSONObject);
                }
            });
        }
    }

    @Override // com.huawei.health.pluginhealthzone.FamilyHealthZoneApi
    public void setCommonUsedDevices(List<exf> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("FamilyHealthZoneImpl", "setCommonUsedDevices callback is null!");
        } else {
            mpq.d().setCommonUsedDevices(list, new HttpDataCallback() { // from class: mpi.3
                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onFailure(int i, String str) {
                    iBaseResponseCallback.d(i, str);
                }

                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onSuccess(JSONObject jSONObject) {
                    iBaseResponseCallback.d(0, jSONObject);
                }
            });
        }
    }

    @Override // com.huawei.health.pluginhealthzone.FamilyHealthZoneApi
    public void notifyMemberCheckHealth(String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("FamilyHealthZoneImpl", "notifyMemberCheckHealth callback is null!");
        } else {
            mpq.d().notifyMemberToCheckHealth(str, new HttpDataCallback() { // from class: mpi.1
                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onFailure(int i, String str2) {
                    iBaseResponseCallback.d(i, str2);
                }

                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onSuccess(JSONObject jSONObject) {
                    iBaseResponseCallback.d(0, jSONObject);
                }
            });
        }
    }

    @Override // com.huawei.health.pluginhealthzone.FamilyHealthZoneApi
    public void gotoFamilyHealth(Context context, String str) {
        if (context == null) {
            LogUtil.h("FamilyHealthZoneImpl", "gotoFamilyHealth context is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("family_health_temp_type", "family_health_temp_type_guide");
        bundle.putString("encryptData", str);
        AppRouter.b("/PluginHealthZone/FamilyHealthTempActivity").zF_(bundle).c(context);
    }

    @Override // com.huawei.health.pluginhealthzone.FamilyHealthZoneApi
    public void startFamilySpaceH5(Context context, dst dstVar) {
        String d = d("sIsFirstHealth");
        bzs.e().initH5Pro();
        H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.familyspace", new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("JsInteraction", bzs.e().getCommonJsModule("JsInteraction")).addCustomizeJsModule("healthZoneApi", getCommonJsModule("healthZoneApi")).addCustomizeJsModule("achievement", bzw.e().getCommonJsModule("achievement")).addCustomizeJsModule("device", bzs.e().getCommonJsModule("device")).addCustomizeArg("from", "3").addCustomizeArg("data", new GsonBuilder().disableHtmlEscaping().create().toJson(dstVar)).addCustomizeArg("isFirstLoad", d).addCustomizeArg("isGuestMode", String.valueOf(!LoginInit.getInstance(context).getIsLogined())).setImmerse().showStatusBar().setNeedSoftInputAdapter().setStatusBarTextBlack(true).setForceDarkMode(1).build());
        d();
    }

    @Override // com.huawei.health.pluginhealthzone.FamilyHealthZoneApi
    public void requestFindUserInfo(final int i, final String str, final UserInfoCallback<exh.b> userInfoCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mpp
            @Override // java.lang.Runnable
            public final void run() {
                mpi.this.b(i, str, userInfoCallback);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001b, code lost:
    
        if (b() != false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    /* synthetic */ void b(int r3, java.lang.String r4, com.huawei.health.pluginhealthzone.UserInfoCallback r5) {
        /*
            r2 = this;
            boolean r0 = r2.c()
            java.lang.String r1 = "FamilyHealthZoneImpl"
            if (r0 == 0) goto L48
            boolean r0 = health.compact.a.Utils.o()
            if (r0 != 0) goto L1e
            java.lang.String r0 = "not oversea, request LoginStatus"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            boolean r0 = r2.b()
            if (r0 == 0) goto L3a
            goto L2d
        L1e:
            boolean r0 = defpackage.efb.l()
            if (r0 == 0) goto L31
            java.lang.String r0 = "oversea support family data"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
        L2d:
            r2.c(r3, r4, r5)
            goto L54
        L31:
            java.lang.String r3 = "oversea not support family data"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r3)
        L3a:
            java.lang.String r3 = "requestFindUserInfo onLoginError"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r3)
            r3 = -1
            r5.errorCallback(r3)
            goto L54
        L48:
            java.lang.String r0 = "requestFindUserInfo login success"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r2.c(r3, r4, r5)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mpi.b(int, java.lang.String, com.huawei.health.pluginhealthzone.UserInfoCallback):void");
    }

    @Override // com.huawei.health.pluginhealthzone.FamilyHealthZoneApi
    public void getOtherUserInfo(final String str, final UserInfoCallback<exg> userInfoCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mpm
            @Override // java.lang.Runnable
            public final void run() {
                mpi.this.a(str, userInfoCallback);
            }
        });
    }

    /* synthetic */ void a(String str, UserInfoCallback userInfoCallback) {
        if (TextUtils.isEmpty(str)) {
            userInfoCallback.errorCallback(-1);
        } else {
            LogUtil.a("FamilyHealthZoneImpl", "findUserInfo");
            c(str, userInfoCallback);
        }
    }

    private exi b(String str) {
        exi exiVar = new exi();
        exiVar.e(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        exiVar.a(str);
        exiVar.e(0);
        exiVar.b(0);
        exiVar.c(new ArrayList());
        return exiVar;
    }

    private void c(String str, UserInfoCallback<exg> userInfoCallback) {
        exi b2 = b(str);
        LogUtil.c("FamilyHealthZoneImpl", " getOtherUserAllInfo request is ", b2.toString());
        exg e = rbq.d().e(b2);
        if (e.c() == 1002) {
            LogUtil.c("FamilyHealthZoneImpl", "retryOnceOtherUserInfo");
            e(str, userInfoCallback);
            return;
        }
        if (e.b() == null) {
            LogUtil.h("FamilyHealthZoneImpl", "response.getResultCode() == null");
            userInfoCallback.errorCallback(-1);
            return;
        }
        LogUtil.a("FamilyHealthZoneImpl", "getOtherUserAllInfo, resultCode: ", Integer.valueOf(e.c()));
        if (e.c() == 0) {
            userInfoCallback.infoCallback(e);
            return;
        }
        LogUtil.h("FamilyHealthZoneImpl", "getOtherUserAllInfo error! resultCode: ", Integer.valueOf(e.c()));
        if (userInfoCallback != null) {
            userInfoCallback.errorCallback(e.c());
        }
    }

    private void c(int i, String str, UserInfoCallback<exh.b> userInfoCallback) {
        rba rbaVar = new rba();
        try {
            rbaVar.c(Long.parseLong(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)));
            rba a2 = a(rbaVar, i, str);
            LogUtil.c("FamilyHealthZoneImpl", " FindUserV2Request request is ", a2.toString());
            exh a3 = rbq.d().a(a2);
            if (a3.getResultCode() != null) {
                LogUtil.a("FamilyHealthZoneImpl", "requestSocialUserInfo, resultCode: ", a3.getResultCode());
                if (a3.getResultCode().intValue() == 1002) {
                    LogUtil.c("FamilyHealthZoneImpl", "retryOnceSocialUserInfo");
                    a(i, str, userInfoCallback);
                    return;
                }
                if (a3.getResultCode().intValue() != 0) {
                    LogUtil.h("FamilyHealthZoneImpl", "requestSocialUserInfo error! resultCode: ", a3.getResultCode());
                    if (userInfoCallback != null) {
                        userInfoCallback.errorCallback(a3.getResultCode().intValue());
                        return;
                    }
                    return;
                }
                List<exh.b> d = a3.b().d();
                if (d != null && d.size() != 0) {
                    LogUtil.a("FamilyHealthZoneImpl", "requestSocialUserInfo findUserRspList size is ", Integer.valueOf(d.size()));
                    if (userInfoCallback != null) {
                        userInfoCallback.infoCallback(d.get(0));
                        return;
                    }
                    return;
                }
                userInfoCallback.errorCallback(1005);
                LogUtil.h("FamilyHealthZoneImpl", "requestSocialUserInfo user is not exist");
                return;
            }
            LogUtil.h("FamilyHealthZoneImpl", "response.getResultCode() == null");
            userInfoCallback.errorCallback(-1);
        } catch (NumberFormatException unused) {
            LogUtil.b("FamilyHealthZoneImpl", "requestUserInfo numberFormatException");
            userInfoCallback.errorCallback(-1);
        }
    }

    private rba a(rba rbaVar, int i, String str) {
        rbaVar.c(i);
        rbaVar.a(str);
        if (i == 0) {
            if (rbu.a(str)) {
                rbaVar.a(1);
            } else {
                rbaVar.a(0);
                rbaVar.a(a(str));
            }
        }
        return rbaVar;
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("FamilyHealthZoneImpl", "getCodePhone phone is null");
            return str;
        }
        if (Utils.o()) {
            return Marker.ANY_NON_NULL_MARKER + str;
        }
        if (str.startsWith("+86")) {
            return str;
        }
        if (str.startsWith("86")) {
            return Marker.ANY_NON_NULL_MARKER + str;
        }
        return "+86" + str;
    }

    public boolean b() {
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        rbc rbcVar = new rbc(BaseApplication.getAppPackage(), "", String.valueOf(45000001), BaseApplication.getAppPackage());
        try {
            rbcVar.d(Long.parseLong(loginInit.getAccountInfo(1011)));
            rbcVar.b(loginInit.getDeviceId());
            rbcVar.e(loginInit.getAccountInfo(1008));
            rbf a2 = rbq.d().a(rbcVar);
            if (a2.getResultCode() == null) {
                LogUtil.h("FamilyHealthZoneImpl", "response.getResultCode() == null");
                return false;
            }
            if (a2.getResultCode().intValue() != 0) {
                LogUtil.h("FamilyHealthZoneImpl", "requestLoginStatus error! responseCode: ", a2.getResultCode());
                return false;
            }
            this.d = System.currentTimeMillis();
            this.e = loginInit.getAccountInfo(1011);
            rbf.e b2 = a2.b();
            this.f15096a = b2.b();
            LogUtil.a("FamilyHealthZoneImpl", "requestLoginStatus is true, user is ", Long.valueOf(b2.a()), " mSessionValidTime ", Integer.valueOf(this.f15096a));
            return true;
        } catch (NumberFormatException unused) {
            LogUtil.b("FamilyHealthZoneImpl", "requestLoginStatus numberFormatException");
            return false;
        }
    }

    private boolean c() {
        if (!this.e.equals(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011))) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.d;
        long j = (this.f15096a * 1000) - 360000;
        LogUtil.a("FamilyHealthZoneImpl", "interval is", Long.valueOf(currentTimeMillis), ", validTime: ", Long.valueOf(j));
        return currentTimeMillis > j;
    }

    private void e(String str, UserInfoCallback<exg> userInfoCallback) {
        LogUtil.a("FamilyHealthZoneImpl", "retryOnce");
        if (b()) {
            c(str, userInfoCallback);
        } else {
            LogUtil.h("FamilyHealthZoneImpl", "requestFindUserInfo onLoginError");
        }
    }

    private void a(int i, String str, UserInfoCallback<exh.b> userInfoCallback) {
        LogUtil.a("FamilyHealthZoneImpl", "retryOnce");
        if (b()) {
            c(i, str, userInfoCallback);
        } else {
            LogUtil.h("FamilyHealthZoneImpl", "requestFindUserInfo onLoginError");
        }
    }

    private String d(String str) {
        return SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(CapabilityStatus.AWA_CAP_CODE_WIFI), str);
    }

    private void d() {
        a("sIsFirstHealth", "1");
    }

    private void a(String str, String str2) {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(CapabilityStatus.AWA_CAP_CODE_WIFI), str, str2, (StorageParams) null);
    }

    @Override // com.huawei.health.pluginhealthzone.FamilyHealthZoneApi
    public void getUserGrpList(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("FamilyHealthZoneImpl", "getUserGrpList callback is null!");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("timeZone", HiDateUtil.d((String) null));
            mpo.a("socialCloudRankUrl", "/v2/getUserGrpList", jSONObject, null, new HttpDataCallback() { // from class: mpi.4
                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onFailure(int i, String str) {
                    iBaseResponseCallback.d(i, str);
                }

                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onSuccess(JSONObject jSONObject2) {
                    iBaseResponseCallback.d(0, jSONObject2);
                }
            });
        } catch (JSONException e) {
            LogUtil.b("FamilyHealthZoneImpl", LogAnonymous.b((Throwable) e));
            iBaseResponseCallback.d(-1, e.getMessage());
        }
    }
}
