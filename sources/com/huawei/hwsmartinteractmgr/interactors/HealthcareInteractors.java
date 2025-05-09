package com.huawei.hwsmartinteractmgr.interactors;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.ActivitySimple;
import com.huawei.hwsmartinteractmgr.data.ContentOrdinaryBgText;
import com.huawei.hwsmartinteractmgr.data.ContentRemindSteps;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.login.ui.login.LoginInit;
import com.tencent.open.apireq.BaseResp;
import defpackage.jec;
import defpackage.kvs;
import defpackage.kwn;
import health.compact.a.HiDateUtil;
import java.util.Date;

/* loaded from: classes5.dex */
public class HealthcareInteractors {
    private kvs c;
    private Context d;

    public HealthcareInteractors(Context context) {
        if (context == null) {
            this.d = BaseApplication.getContext();
        } else {
            this.d = context;
        }
        this.c = kvs.b(this.d);
    }

    public void b(ActivitySimple activitySimple) {
        LogUtil.a("SMART_HealthcareInteractors", "createRemindReachStepsGoalSmartMsg");
        if (activitySimple == null) {
            return;
        }
        SmartMsgDbObject b = b("ai-walk-002", 10001);
        b.setMsgContentType(3);
        b.setMsgContent(HiJsonUtil.d(new ContentRemindSteps(activitySimple.getActivityName(), activitySimple.getTargetValue(), activitySimple.getActivityId(), activitySimple.getActivityStatus(), activitySimple.getLastModifyTime()), ContentRemindSteps.class));
        e(activitySimple, b);
        SmartMsgDbObject a2 = this.c.a(10001);
        if (a2 == null) {
            boolean a3 = this.c.a(b);
            long c = jec.c();
            b("reach_steps_goal_check_interval", String.valueOf(c), this.c);
            LogUtil.a("SMART_HealthcareInteractors", "createRemindReachStepsGoalSmartMsg, oldSmartMsg is null, isInserted =", Boolean.valueOf(a3));
            return;
        }
        if (activitySimple.getActivityId() != null) {
            ContentRemindSteps contentRemindSteps = (ContentRemindSteps) HiJsonUtil.e(a2.getMsgContent(), ContentRemindSteps.class);
            if (activitySimple.getActivityId().equals(contentRemindSteps.getActivityId())) {
                LogUtil.a("SMART_HealthcareInteractors", "createRemindReachStepsGoalSmartMsg, same activity");
                if (a2.getStatus() == 3 || !contentRemindSteps.getLastModifyTime().equals(activitySimple.getLastModifyTime()) || d()) {
                    boolean e = this.c.e(a2.getId(), b);
                    long c2 = jec.c();
                    b("reach_steps_goal_check_interval", String.valueOf(c2), this.c);
                    LogUtil.a("SMART_HealthcareInteractors", "createRemindReachStepsGoalSmartMsg, MSG_STATUS_EXPIRED, isUpdated=", Boolean.valueOf(e));
                    return;
                }
                return;
            }
            int d = this.c.d(10001);
            boolean a4 = this.c.a(b);
            long c3 = jec.c();
            b("reach_steps_goal_check_interval", String.valueOf(c3), this.c);
            LogUtil.a("SMART_HealthcareInteractors", "createRemindReachStepsGoalSmartMsg, delResult=", Integer.valueOf(d), ", isInserted=", Boolean.valueOf(a4));
        }
    }

    private SmartMsgDbObject b(String str, int i) {
        int d = kwn.d(30000, str);
        String a2 = kwn.a(30000, str);
        SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
        smartMsgDbObject.setMsgType(i);
        smartMsgDbObject.setMsgSrc(1);
        smartMsgDbObject.setShowMethod(SmartMsgConstant.SHOW_METHOD_SMART_CARD_HI_BOARD);
        smartMsgDbObject.setMessagePriority(d);
        smartMsgDbObject.setShowTime(a2);
        smartMsgDbObject.setStatus(1);
        return smartMsgDbObject;
    }

    public void d(ActivitySimple activitySimple) {
        LogUtil.a("SMART_HealthcareInteractors", "createReachGoalActivitySmartMsg");
        if (activitySimple == null || activitySimple.getActivityPosition() == 5) {
            LogUtil.a("SMART_HealthcareInteractors", "companyActivity no recommend");
            return;
        }
        SmartMsgDbObject b = b("ai-walk-001", 10000);
        b.setMsgContentType(2);
        b.setMsgContent(HiJsonUtil.d(e(activitySimple), ContentOrdinaryBgText.class));
        e(activitySimple, b);
        SmartMsgDbObject a2 = this.c.a(10000);
        if (a2 == null) {
            LogUtil.a("SMART_HealthcareInteractors", "createReachGoalActivitySmartMsg, oldSmartMsg is null, isInserted =", Boolean.valueOf(this.c.a(b)));
            return;
        }
        ContentOrdinaryBgText contentOrdinaryBgText = (ContentOrdinaryBgText) HiJsonUtil.e(a2.getMsgContent(), ContentOrdinaryBgText.class);
        if (activitySimple.getActivityId() != null) {
            if (activitySimple.getActivityId().equals(contentOrdinaryBgText.getSubContent())) {
                if (a2.getStatus() == 3 || !contentOrdinaryBgText.getLastModifyTime().equals(activitySimple.getLastModifyTime())) {
                    this.c.e(a2.getId(), b);
                    return;
                }
                return;
            }
            LogUtil.a("SMART_HealthcareInteractors", "createReachGoalActivitySmartMsg, delResult=", Integer.valueOf(this.c.d(10000)), ", isInserted=", Boolean.valueOf(this.c.a(b)));
        }
    }

    private ContentOrdinaryBgText e(ActivitySimple activitySimple) {
        ContentOrdinaryBgText contentOrdinaryBgText = new ContentOrdinaryBgText();
        contentOrdinaryBgText.setContent(activitySimple.getWordDesc());
        contentOrdinaryBgText.setSubContent(activitySimple.getActivityId());
        contentOrdinaryBgText.setMinContent(Integer.toString(activitySimple.getActivityStatus()));
        contentOrdinaryBgText.setLastModifyTime(activitySimple.getLastModifyTime());
        return contentOrdinaryBgText;
    }

    private void e(ActivitySimple activitySimple, SmartMsgDbObject smartMsgDbObject) {
        long a2 = HiDateUtil.a(activitySimple.getEndDate());
        if (a2 != -1) {
            smartMsgDbObject.setExpireTime(a2);
        }
    }

    private int b(String str, String str2, HwBaseManager hwBaseManager) {
        String accountInfo = LoginInit.getInstance(this.d).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h("SMART_HealthcareInteractors", "get user failed!");
            return BaseResp.CODE_UNSUPPORTED_BRANCH;
        }
        return hwBaseManager.setSharedPreference(accountInfo + str, str2, null);
    }

    private String c(String str, HwBaseManager hwBaseManager) {
        String accountInfo = LoginInit.getInstance(this.d).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h("SMART_HealthcareInteractors", "get user failed!");
            return null;
        }
        return hwBaseManager.getSharedPreference(accountInfo + str);
    }

    private boolean d() {
        long j;
        String c = c("reach_steps_goal_check_interval", this.c);
        if (!TextUtils.isEmpty(c)) {
            try {
                j = Long.parseLong(c);
            } catch (NumberFormatException e) {
                LogUtil.b("SMART_HealthcareInteractors", "NumberFormatException:", e.getMessage());
                j = 0;
            }
            if (jec.ab(new Date(j * 1000))) {
                return false;
            }
        }
        return true;
    }
}
