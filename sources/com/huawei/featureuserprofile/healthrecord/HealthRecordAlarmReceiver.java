package com.huawei.featureuserprofile.healthrecord;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import defpackage.jdh;
import defpackage.nsn;
import defpackage.sqa;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;

/* loaded from: classes.dex */
public class HealthRecordAlarmReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("HealthRecordAlarmReceiver", "Receive alarm broadcast-----------");
        if (intent == null) {
            LogUtil.h("HealthRecordAlarmReceiver", "onReceive intent == null");
            return;
        }
        int intExtra = intent.getIntExtra("physicalExaminationId", 0);
        String c = StringUtils.c((Object) intent.getStringExtra(JsbMapKeyNames.H5_USER_ID));
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        LogUtil.c("HealthRecordAlarmReceiver", "onReceive userId= ", c, " currentUserId = ", accountInfo);
        if (TextUtils.isEmpty(c) || TextUtils.isEmpty(accountInfo) || !c.equals(accountInfo)) {
            return;
        }
        LogUtil.a("HealthRecordAlarmReceiver", "onReceive examinationId = ", Integer.valueOf(intExtra));
        if (sqa.ekw_(intent)) {
            int ekr_ = sqa.ekr_(intent);
            long ekq_ = sqa.ekq_(intent);
            if (ekr_ < 0) {
                sqa.ekn_(intExtra, intent, 201326592);
                sqa.ekx_(intExtra, intent, 201326592, 0, ekq_);
            }
            ReleaseLogUtil.b("HealthRecordAlarmReceiver", "onReceive offset ", Integer.valueOf(ekr_), " timeMillis ", Long.valueOf(ekq_));
            return;
        }
        a(context, intExtra);
    }

    private void a(Context context, int i) {
        jdh.c().a(i);
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        Resources resources = BaseApplication.getContext().getResources();
        xf_.setContentTitle(resources.getString(R.string.IDS_health_record)).setContentText(resources.getString(R.string._2130845836_res_0x7f02208c));
        xf_.setContentIntent(PendingIntent.getActivity(context, 0, new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.health-record&from=healthRecord&type=1&examinationId=" + i)), AppRouterExtras.COLDSTART));
        xf_.setAutoCancel(true);
        jdh.c().xh_(i, xf_.build());
        LogUtil.a("HealthRecordAlarmReceiver", "notifyHealthRecordMessage success");
    }
}
