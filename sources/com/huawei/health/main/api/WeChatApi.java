package com.huawei.health.main.api;

import android.content.Context;
import android.os.Handler;

/* loaded from: classes3.dex */
public interface WeChatApi {
    void bindTitle(Context context, String str);

    void dismissJumpToHwPublicDialog();

    void go2WeChatHandle(Context context);

    boolean isUserBind(Context context);

    String isUserBinded(Context context);

    void jumpToHwPublic(String str);

    void jumpWechatHelp(Context context);

    void launchWxMiniProgram(String str, String str2, int i);

    void setWeChatHandler(Handler handler);

    void showDeviceAlreadyBinded(Context context);

    void showSportPrivacySettingDialog(Context context, int i);
}
