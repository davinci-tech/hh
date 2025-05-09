package com.huawei.pluginachievement.manager.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.mff;

/* loaded from: classes6.dex */
public class LanguageResReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction() != null) {
                if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
                    LogUtil.a("PLGACHIEVE_LanguageResReceiver", "onReceive action:", intent.getAction());
                    mff a2 = mff.a(context);
                    Object[] objArr = new Object[2];
                    objArr[0] = "onReceive languageRes:";
                    objArr[1] = a2 == null ? Constants.NULL : a2.toString();
                    LogUtil.a("PLGACHIEVE_LanguageResReceiver", objArr);
                    if (a2 == null || !a2.c()) {
                        return;
                    }
                    a2.a();
                    return;
                }
                return;
            }
            LogUtil.a("PLGACHIEVE_LanguageResReceiver", "onReceive action ,intent.getAction() is null!");
            return;
        }
        LogUtil.a("PLGACHIEVE_LanguageResReceiver", "onReceive action is null!");
    }
}
