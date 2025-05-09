package com.huawei.health.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import defpackage.jad;
import defpackage.jfo;
import health.compact.a.CommonUtil;

/* loaded from: classes8.dex */
public class AccountLoginReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        LogUtil.a("AccountLoginReceiver", " == AccountLoginReceiver enter ");
        if (CommonUtil.ce() && jad.d(53) && intent != null) {
            DeviceCapability e = DeviceSettingsInteractors.d(context).e();
            if (e == null) {
                LogUtil.h("AccountLoginReceiver", "deviceCapability is null!");
                return;
            }
            if (!e.isSupportPay() && !e.isSupportWalletOpenCard()) {
                LogUtil.h("AccountLoginReceiver", "AccountLoginReceiver is not SupportPay : ");
                return;
            }
            try {
                str = intent.getAction();
            } catch (Exception unused) {
                LogUtil.b("AccountLoginReceiver", "AccountLoginReceiver getAction Exception");
                str = null;
            }
            String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
            if (TextUtils.isEmpty(accountInfo)) {
                LogUtil.h("AccountLoginReceiver", "AccountLoginReceiver userId is empty");
                return;
            }
            if (str != null) {
                if ("com.huawei.plugin.account.login".equals(str)) {
                    LogUtil.a("AccountLoginReceiver", "AccountLoginReceiver sendAccount ");
                    jfo.e().a(accountInfo, (IBaseResponseCallback) null);
                } else if ("com.huawei.plugin.account.logout".equals(str)) {
                    LogUtil.a("AccountLoginReceiver", "AccountLoginReceiver logout sendAccount ");
                } else {
                    LogUtil.a("AccountLoginReceiver", "AccountLoginReceiver receive unknown localBroadCast action = ", str);
                }
            }
        }
    }
}
