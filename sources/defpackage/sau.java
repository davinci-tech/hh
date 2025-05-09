package defpackage;

import android.content.Context;
import android.os.Handler;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.main.api.WeChatApi;
import com.huawei.hmf.annotation.ApiDefine;

@ApiDefine(uri = WeChatApi.class)
/* loaded from: classes7.dex */
public class sau implements WeChatApi {
    @Override // com.huawei.health.main.api.WeChatApi
    public void showSportPrivacySettingDialog(Context context, int i) {
        sbk.d(context, i);
    }

    @Override // com.huawei.health.main.api.WeChatApi
    public boolean isUserBind(Context context) {
        return sbk.a(context).b(context);
    }

    @Override // com.huawei.health.main.api.WeChatApi
    public void jumpWechatHelp(Context context) {
        sbk.c(context);
    }

    @Override // com.huawei.health.main.api.WeChatApi
    public void showDeviceAlreadyBinded(Context context) {
        sbk.a(context).g(context);
    }

    @Override // com.huawei.health.main.api.WeChatApi
    public void setWeChatHandler(Handler handler) {
        sbk.a(BaseApplication.e()).dUY_(handler);
    }

    @Override // com.huawei.health.main.api.WeChatApi
    public void go2WeChatHandle(Context context) {
        sbk.a(BaseApplication.e()).e(context);
    }

    @Override // com.huawei.health.main.api.WeChatApi
    public String isUserBinded(Context context) {
        return sbk.a(BaseApplication.e()).i(context);
    }

    @Override // com.huawei.health.main.api.WeChatApi
    public void bindTitle(Context context, String str) {
        sbk.a(BaseApplication.e()).b(context, str);
    }

    @Override // com.huawei.health.main.api.WeChatApi
    public void jumpToHwPublic(String str) {
        sbk.a(BaseApplication.e()).a(str);
    }

    @Override // com.huawei.health.main.api.WeChatApi
    public void dismissJumpToHwPublicDialog() {
        sbk.a(BaseApplication.e()).c();
    }

    @Override // com.huawei.health.main.api.WeChatApi
    public void launchWxMiniProgram(String str, String str2, int i) {
        sbk.a(BaseApplication.e()).e(str, str2, i);
    }
}
