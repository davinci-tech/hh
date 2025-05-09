package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import defpackage.spn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.Services;
import java.io.File;
import java.io.UnsupportedEncodingException;

/* loaded from: classes6.dex */
public class nid implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    protected String f15300a;
    private lmy c;

    public boolean d(lmy lmyVar) {
        return true;
    }

    public nid(lmy lmyVar) {
        this.c = lmyVar;
        d();
    }

    public String e() {
        return this.c.b();
    }

    protected void a(lmy lmyVar) {
        if (lmyVar == null) {
            LogUtil.h("BaseTask", "onTaskSuccess basicMessage is null ");
        } else {
            LogUtil.a("BaseTask", "onTaskSuccess state == ", Integer.valueOf(lmyVar.a()));
        }
    }

    protected void e(lmy lmyVar) {
        LogUtil.b("BaseTask", "onTaskFailed");
    }

    @Override // java.lang.Runnable
    public void run() {
        if (d(this.c)) {
            a(this.c);
        } else {
            e(this.c);
        }
    }

    lmz b(lmy lmyVar) {
        if (!(lmyVar instanceof lmz)) {
            return null;
        }
        lmz lmzVar = (lmz) lmyVar;
        lmzVar.e(CommonUtil.e(BaseApplication.getContext()));
        if (TextUtils.isEmpty(this.f15300a)) {
            d();
        }
        lmzVar.b(this.f15300a);
        lmzVar.d(200);
        return lmzVar;
    }

    lmz a(int i) {
        return new lmz(e(), i, 2);
    }

    spn a(lmz lmzVar) {
        byte[] bArr;
        spn.b bVar = new spn.b();
        String json = new Gson().toJson(lmzVar);
        LogUtil.a("BaseTask", "SendDeviceMsg == ", json);
        try {
            bArr = json.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("BaseTask", "getRequestMsg UnsupportedEncodingException");
            bArr = null;
        }
        bVar.c(bArr);
        return bVar.e();
    }

    spn c(File file) {
        spn.b bVar = new spn.b();
        bVar.a(file);
        return bVar.e();
    }

    private void d() {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        if (EnvironmentUtils.c()) {
            String a2 = a(userProfileMgrApi.getUserInfo());
            this.f15300a = a2;
            LogUtil.c("BaseTask", "Main process get userinfo success mUserName = ", a2);
            return;
        }
        userProfileMgrApi.getUserInfo(new BaseResponseCallback<UserInfomation>() { // from class: nid.3
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, UserInfomation userInfomation) {
                if (i != 0) {
                    LogUtil.h("BaseTask", "get userinfo failed errCode = ", Integer.valueOf(i));
                } else {
                    if (userInfomation == null) {
                        LogUtil.h("BaseTask", "get userinfo success but obtain null objData");
                        return;
                    }
                    nid nidVar = nid.this;
                    nidVar.f15300a = nidVar.a(userInfomation);
                    LogUtil.c("BaseTask", "not Main process get mUserName = ", nid.this.f15300a);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(UserInfomation userInfomation) {
        if (userInfomation == null) {
            return "";
        }
        String name = userInfomation.getName();
        return TextUtils.isEmpty(name) ? new UpApi(BaseApplication.getContext()).getAccountName() : name;
    }

    public static class e implements SendCallback {
        private ResponseCallback b;

        e(ResponseCallback responseCallback) {
            this.b = responseCallback;
        }

        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onSendResult(int i) {
            if (i == 500000) {
                return;
            }
            if (i == 207) {
                LogUtil.a("BaseTask", "SendEndMessage success, code == ", Integer.valueOf(i));
            } else {
                LogUtil.a("BaseTask", "SendEndMessage fail! code ", Integer.valueOf(i));
            }
            ResponseCallback responseCallback = this.b;
            if (responseCallback != null) {
                responseCallback.onResult(i, null);
            }
        }

        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onSendProgress(long j) {
            LogUtil.c("BaseTask", "SendEndMessage onSendProgress count == ", Long.valueOf(j));
        }

        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onFileTransferReport(String str) {
            LogUtil.c("BaseTask", "SendEndMessageCallback onFileTransferReport transferWay:", str);
        }
    }

    protected boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return spz.e(str);
    }

    spn d(lmx lmxVar) {
        byte[] bArr;
        spn.b bVar = new spn.b();
        String json = new Gson().toJson(lmxVar);
        LogUtil.a("BaseTask", "generatorMedalMessage SendDeviceMsg == ", json);
        try {
            bArr = json.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("BaseTask", "getRequestMsg UnsupportedEncodingException");
            bArr = null;
        }
        bVar.c(bArr);
        return bVar.e();
    }
}
