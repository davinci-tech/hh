package com.huawei.health.receiver;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.receiver.EcgReminderReceiver;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.cvs;
import defpackage.cwi;
import defpackage.jdh;
import defpackage.jpt;
import defpackage.nsn;
import defpackage.pry;
import defpackage.psa;
import defpackage.sqa;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.TimeZone;
import org.json.JSONException;

/* loaded from: classes8.dex */
public class EcgReminderReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("reminderHuid");
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        long longExtra = intent.getLongExtra("reminderTime", 0L);
        String stringExtra2 = intent.getStringExtra("remindKey");
        if (TextUtils.isEmpty(stringExtra2)) {
            LogUtil.b("EcgReminderReceiver", "Receiver received reminder with empty remindKey");
            return;
        }
        int ekr_ = sqa.ekr_(intent);
        LogUtil.a("EcgReminderReceiver", "registerReminder time ", Long.valueOf(longExtra), ", remindKey = ", stringExtra2, ", timeZone offset = ", Integer.valueOf(ekr_));
        int intExtra = intent.getIntExtra("requestCode", 0);
        if (ekr_ < 0) {
            e(intExtra, longExtra - ekr_, stringExtra, stringExtra2);
            return;
        }
        if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(accountInfo) && stringExtra.equals(accountInfo) && ekr_ == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            DeviceInfo a2 = jpt.a("EcgReminderReceiver");
            if (c(a2, stringExtra2) && Math.abs((currentTimeMillis % 86400000) - (longExtra % 86400000)) <= 900000 && !c(currentTimeMillis)) {
                d(stringExtra2, cwi.c(a2, OldToNewMotionPath.SPORT_TYPE_PILATES));
            }
        }
        if (intExtra <= 0) {
            LogUtil.h("EcgReminderReceiver", "registerReminder requestCode is invalid");
        } else {
            e(intExtra, (longExtra - ekr_) + 604800000, stringExtra, stringExtra2);
        }
    }

    private void e(int i, long j, String str, String str2) {
        LogUtil.a("EcgReminderReceiver", "registerReminder requestCode ", Integer.valueOf(i), ", time ", Long.valueOf(j));
        Context context = BaseApplication.getContext();
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.receiver.EcgReminderReceiver");
        intent.putExtra("reminderHuid", str);
        intent.putExtra("reminderTime", j);
        intent.putExtra("requestCode", i);
        intent.putExtra("remindKey", str2);
        intent.putExtra("timeZoneId", TimeZone.getDefault().getID());
        sqa.ekz_(i, intent, 201326592, 0, j);
    }

    private boolean c(DeviceInfo deviceInfo, String str) {
        char c;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("EcgReminderReceiver", "remindKey is empty");
            return false;
        }
        LogUtil.a("EcgReminderReceiver", "isDeviceSupport, remindKey = ", str);
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("EcgReminderReceiver", "deviceInfo is null or device is not connected");
            return false;
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -918899155) {
            if (str.equals("com.huawei.health.vascular-health.remind.measure.switch")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 155319248) {
            if (hashCode == 1407588978 && str.equals("com.huawei.health.ecgce.remind.measure.switch")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("com.huawei.health.ecg.remind.measure.switch")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            return cwi.c(deviceInfo, 75);
        }
        if (c != 1) {
            if (c != 2) {
                return false;
            }
            return cwi.c(deviceInfo, 106);
        }
        DeviceCapability d = cvs.d();
        if (d != null && d.isSupportEcgAuth()) {
            return true;
        }
        LogUtil.h("EcgReminderReceiver", "capability is null or not support ecg");
        return false;
    }

    private boolean c(long j) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "last_time_notice_measure_ecg");
        if (TextUtils.isEmpty(b) || Math.abs(j - nsn.h(b)) >= 1800000) {
            return false;
        }
        LogUtil.a("EcgReminderReceiver", "isRepeatedNotification lastRemindTime ", b);
        return true;
    }

    private void d(String str, boolean z) {
        char c;
        String string;
        String string2;
        jdh.c().a(20211202);
        Resources resources = BaseApplication.getContext().getResources();
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -918899155) {
            if (str.equals("com.huawei.health.vascular-health.remind.measure.switch")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 155319248) {
            if (hashCode == 1407588978 && str.equals("com.huawei.health.ecgce.remind.measure.switch")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("com.huawei.health.ecg.remind.measure.switch")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            string = resources.getString(R.string._2130846039_res_0x7f022157);
            string2 = resources.getString(R.string._2130846038_res_0x7f022156);
        } else if (c == 1) {
            string = resources.getString(R.string._2130844070_res_0x7f0219a6);
            string2 = resources.getString(R.string._2130846015_res_0x7f02213f);
        } else if (c == 2) {
            string = resources.getString(R.string.IDS_quick_app_ecg_analysis);
            string2 = resources.getString(R.string._2130844485_res_0x7f021b45);
        } else {
            LogUtil.b("EcgReminderReceiver", "setNotify Undefined remindKey");
            return;
        }
        Notification.Builder dsy_ = psa.dsy_(string, string2, str);
        if (CommonUtil.cf()) {
            dsy_.setGroupSummary(true);
            if (!z) {
                pry.e().pingComand(aui_(string, string2, resources, dsy_), "hw.watch.ecgrecorder.p2p");
            }
        }
        psa.dsA_(dsy_);
        LogUtil.a("EcgReminderReceiver", "sendMsgToNotification");
    }

    private PingCallback aui_(final String str, final String str2, final Resources resources, final Notification.Builder builder) {
        return new PingCallback() { // from class: ezm
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public final void onPingResult(int i) {
                EcgReminderReceiver.auh_(str, str2, resources, builder, i);
            }
        };
    }

    public static /* synthetic */ void auh_(String str, String str2, Resources resources, Notification.Builder builder, int i) {
        LogUtil.a("EcgReminderReceiver", "pingComand, errCode = ", Integer.valueOf(i));
        if (i == 202) {
            try {
                pry.e().b(pry.e().d(9001, str, str2, resources.getString(R.string._2130845530_res_0x7f021f5a), resources.getString(R.string._2130845531_res_0x7f021f5b)).toString());
                return;
            } catch (JSONException unused) {
                LogUtil.h("EcgReminderReceiver", "JSONException");
                builder.setGroupSummary(false);
                psa.dsA_(builder);
                return;
            }
        }
        builder.setGroupSummary(false);
        psa.dsA_(builder);
    }
}
