package com.huawei.hms.aaid;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.agconnect.credential.obs.c;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.entity.AAIDResult;
import com.huawei.hms.aaid.entity.DeleteTokenReq;
import com.huawei.hms.aaid.entity.TokenReq;
import com.huawei.hms.aaid.entity.TokenResult;
import com.huawei.hms.aaid.plugin.ProxyCenter;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.aaid.utils.BaseUtils;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.opendevice.a;
import com.huawei.hms.opendevice.b;
import com.huawei.hms.opendevice.e;
import com.huawei.hms.opendevice.f;
import com.huawei.hms.opendevice.g;
import com.huawei.hms.opendevice.h;
import com.huawei.hms.opendevice.i;
import com.huawei.hms.opendevice.l;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes4.dex */
public class HmsInstanceId {
    public static final String TAG = "HmsInstanceId";

    /* renamed from: a, reason: collision with root package name */
    private Context f4246a;
    private PushPreferences b;
    private HuaweiApi<Api.ApiOptions.NoOptions> c;

    private HmsInstanceId(Context context) {
        this.f4246a = context.getApplicationContext();
        this.b = new PushPreferences(context, c.f1765a);
        Api api = new Api(HuaweiApiAvailability.HMS_API_NAME_PUSH);
        if (context instanceof Activity) {
            this.c = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new PushClientBuilder());
        } else {
            this.c = new HuaweiApi<>(context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, new PushClientBuilder());
        }
        this.c.setKitSdkVersion(61200300);
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!e.e(this.f4246a)) {
            i.a(this.f4246a).removeKey("subjectId");
            return;
        }
        String string = i.a(this.f4246a).getString("subjectId");
        if (TextUtils.isEmpty(string)) {
            i.a(this.f4246a).saveString("subjectId", str);
            return;
        }
        if (string.contains(str)) {
            return;
        }
        i.a(this.f4246a).saveString("subjectId", string + "," + str);
    }

    private void b() throws ApiException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw ErrorEnum.ERROR_MAIN_THREAD.toApiException();
        }
    }

    public static HmsInstanceId getInstance(Context context) {
        Preconditions.checkNotNull(context);
        l.c(context);
        return new HmsInstanceId(context);
    }

    public void deleteAAID() throws ApiException {
        b();
        try {
            if (this.b.containsKey(c.f1765a)) {
                this.b.removeKey(c.f1765a);
                this.b.removeKey(c.b);
                if (b.d(this.f4246a)) {
                    if (ProxyCenter.getProxy() != null) {
                        HMSLog.i(TAG, "use proxy delete all token after delete AaId.");
                        ProxyCenter.getProxy().deleteAllToken(this.f4246a);
                        return;
                    }
                    DeleteTokenReq a2 = b.a(this.f4246a);
                    a2.setDeleteType(1);
                    a2.setMultiSender(false);
                    a(a2, 1);
                    BaseUtils.deleteAllTokenCache(this.f4246a);
                }
            }
        } catch (ApiException e) {
            throw e;
        } catch (Exception unused) {
            throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
        }
    }

    public void deleteToken(String str, String str2) throws ApiException {
        b();
        a();
        DeleteTokenReq a2 = b.a(this.f4246a, str, str2);
        a2.setMultiSender(false);
        a(a2, 1);
    }

    public Task<AAIDResult> getAAID() {
        try {
            return Tasks.callInBackground(new a(this.f4246a.getApplicationContext()));
        } catch (Exception unused) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(ErrorEnum.ERROR_INTERNAL_ERROR.toApiException());
            return taskCompletionSource.getTask();
        }
    }

    public long getCreationTime() {
        try {
            if (!this.b.containsKey(c.b)) {
                getAAID();
            }
            return this.b.getLong(c.b);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public String getId() {
        return b.b(this.f4246a);
    }

    @Deprecated
    public String getToken() {
        try {
            return getToken(null, null);
        } catch (Exception unused) {
            return null;
        }
    }

    public String getToken(String str, String str2) throws ApiException {
        b();
        a();
        TokenReq b = b.b(this.f4246a, null, str2);
        b.setAaid(getId());
        b.setMultiSender(false);
        i.a(this.f4246a).saveString(this.f4246a.getPackageName(), "1");
        return a(b, 1);
    }

    public void deleteToken(String str) throws ApiException {
        b();
        a();
        if (!TextUtils.isEmpty(str)) {
            String c = b.c(this.f4246a);
            if (!TextUtils.isEmpty(c)) {
                if (str.equals(c)) {
                    deleteToken(null, null);
                    return;
                }
                DeleteTokenReq a2 = b.a(this.f4246a, str);
                a2.setMultiSender(true);
                a(a2, 2);
                return;
            }
            throw ErrorEnum.ERROR_MISSING_PROJECT_ID.toApiException();
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }

    public String getToken(String str) throws ApiException {
        b();
        a();
        if (!TextUtils.isEmpty(str)) {
            String c = b.c(this.f4246a);
            if (!TextUtils.isEmpty(c)) {
                if (str.equals(c)) {
                    return getToken(null, null);
                }
                TokenReq b = b.b(this.f4246a, str);
                b.setAaid(getId());
                b.setMultiSender(true);
                return a(b, 2);
            }
            throw ErrorEnum.ERROR_MISSING_PROJECT_ID.toApiException();
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }

    private String a(TokenReq tokenReq, int i) throws ApiException {
        if (ProxyCenter.getProxy() != null) {
            HMSLog.i(TAG, "use proxy get token, please check HmsMessageService.onNewToken receive result.");
            ProxyCenter.getProxy().getToken(this.f4246a, tokenReq.getSubjectId(), null);
            return null;
        }
        a(tokenReq.getSubjectId());
        String a2 = h.a(this.f4246a, "push.gettoken");
        try {
            HMSLog.d(TAG, "getToken req :" + tokenReq.toString());
            g gVar = new g("push.gettoken", tokenReq, this.f4246a, a2);
            gVar.setApiLevel(i);
            return ((TokenResult) Tasks.await(this.c.doWrite(gVar))).getToken();
        } catch (Exception e) {
            if (e.getCause() instanceof ApiException) {
                ApiException apiException = (ApiException) e.getCause();
                h.a(this.f4246a, "push.gettoken", a2, apiException.getStatusCode());
                throw apiException;
            }
            Context context = this.f4246a;
            ErrorEnum errorEnum = ErrorEnum.ERROR_INTERNAL_ERROR;
            h.a(context, "push.gettoken", a2, errorEnum);
            throw errorEnum.toApiException();
        }
    }

    private void a(DeleteTokenReq deleteTokenReq, int i) throws ApiException {
        String subjectId = deleteTokenReq.getSubjectId();
        if (ProxyCenter.getProxy() != null) {
            HMSLog.i(TAG, "use proxy delete token");
            ProxyCenter.getProxy().deleteToken(this.f4246a, subjectId, null);
            return;
        }
        String a2 = h.a(this.f4246a, "push.deletetoken");
        try {
            String b = i.a(this.f4246a).b(subjectId);
            if (deleteTokenReq.isMultiSender() && (TextUtils.isEmpty(b) || b.equals(i.a(this.f4246a).b(null)))) {
                i.a(this.f4246a).removeKey(subjectId);
                HMSLog.i(TAG, "The local subject token is null");
                return;
            }
            deleteTokenReq.setToken(b);
            f fVar = new f("push.deletetoken", deleteTokenReq, a2);
            fVar.setApiLevel(i);
            Tasks.await(this.c.doWrite(fVar));
            i.a(this.f4246a).c(subjectId);
        } catch (Exception e) {
            if (e.getCause() instanceof ApiException) {
                ApiException apiException = (ApiException) e.getCause();
                h.a(this.f4246a, "push.deletetoken", a2, apiException.getStatusCode());
                throw apiException;
            }
            Context context = this.f4246a;
            ErrorEnum errorEnum = ErrorEnum.ERROR_INTERNAL_ERROR;
            h.a(context, "push.deletetoken", a2, errorEnum);
            throw errorEnum.toApiException();
        }
    }

    private void a() throws ApiException {
        if (BaseUtils.getProxyInit(this.f4246a) && ProxyCenter.getProxy() == null && !BaseUtils.isMainProc(this.f4246a)) {
            HMSLog.e(TAG, "Operations in child processes are not supported.");
            throw ErrorEnum.ERROR_OPER_IN_CHILD_PROCESS.toApiException();
        }
    }
}
