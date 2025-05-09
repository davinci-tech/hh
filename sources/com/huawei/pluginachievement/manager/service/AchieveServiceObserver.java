package com.huawei.pluginachievement.manager.service;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.MessageObject;
import com.huawei.pluginachievement.manager.model.RecentMonthRecord;
import com.huawei.pluginachievement.manager.model.RecentWeekRecord;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import defpackage.mct;
import defpackage.mcv;
import defpackage.meh;
import defpackage.mes;
import defpackage.met;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.Calendar;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class AchieveServiceObserver implements AchieveObserver {
    private static AchieveMedalDialogScenario b;
    private int d = 0;
    private Context e;

    public AchieveServiceObserver(Context context) {
        this.e = context;
    }

    public static void d(int i) {
        b = AchieveMedalDialogScenario.getMedalDialogScenario(i);
    }

    private boolean a(String str) {
        return !"0".equals(str);
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        a(i, userAchieveWrapper, meh.c(BaseApplication.getContext()));
    }

    private void a(int i, UserAchieveWrapper userAchieveWrapper, meh mehVar) {
        if (mehVar == null || userAchieveWrapper == null) {
            return;
        }
        int contentType = userAchieveWrapper.getContentType();
        if (contentType == 2 || contentType == 3 || contentType == 4 || contentType == 7) {
            LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "dealDataChanged need process result");
            if (i == -1) {
                if (contentType == 4) {
                    b();
                    return;
                }
                return;
            }
            LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "AchieveServiceObserver|onDataChanged contentType = ", Integer.valueOf(contentType));
            if (contentType == 2) {
                c(userAchieveWrapper, mehVar);
                return;
            }
            if (contentType == 3) {
                b(userAchieveWrapper, mehVar);
                return;
            }
            if (contentType == 4) {
                mehVar.d(userAchieveWrapper);
                mes.c(this.e).d(userAchieveWrapper);
                b();
                return;
            } else {
                if (contentType == 7) {
                    a(userAchieveWrapper);
                    return;
                }
                return;
            }
        }
        LogUtil.h("PLGACHIEVE_AchieveServiceObserver", "dealDataChanged do not need process result");
    }

    private void b() {
        AchieveMedalDialogScenario achieveMedalDialogScenario = b;
        if (achieveMedalDialogScenario != null) {
            met.a(achieveMedalDialogScenario);
            b = null;
        }
    }

    private void a(UserAchieveWrapper userAchieveWrapper) {
        String resultCode = userAchieveWrapper.getResultCode();
        LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "processTakeMedal resultCode=", resultCode);
        if ("0".equals(resultCode)) {
            mct.e(this.e, "_uploadMedal");
        }
    }

    private void b(UserAchieveWrapper userAchieveWrapper, meh mehVar) {
        RecentMonthRecord acquireRecentMonthRecord;
        if (mehVar == null || (acquireRecentMonthRecord = userAchieveWrapper.acquireRecentMonthRecord()) == null) {
            return;
        }
        try {
            this.d = Integer.parseInt(mct.b(this.e, "_monthReportNo"));
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveServiceObserver", "processMonthly NumberFormatException");
        }
        if (e(acquireRecentMonthRecord.getEndUtcDate(), 3)) {
            LogUtil.h("PLGACHIEVE_AchieveServiceObserver", "processMonthly error month report");
            return;
        }
        int acquireReportNo = acquireRecentMonthRecord.acquireReportNo();
        int acquireMinReportNo = acquireRecentMonthRecord.acquireMinReportNo();
        LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "processMonthly reportNo=", Integer.valueOf(acquireReportNo));
        LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "processMonthly minReportNo=", Integer.valueOf(acquireMinReportNo));
        LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "processMonthly localMonthReportNo=", Integer.valueOf(this.d));
        LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "processMonthly wrapper.getResultCode()=", userAchieveWrapper.getResultCode());
        c(String.valueOf(acquireReportNo), String.valueOf(acquireMinReportNo));
        int i = this.d;
        if (-1 == i || acquireReportNo <= i || a(userAchieveWrapper.getResultCode())) {
            return;
        }
        this.d = acquireReportNo;
        MessageObject e = mehVar.e(acquireMinReportNo, acquireReportNo, acquireRecentMonthRecord.acquireFirstDate());
        e.setModule("16");
        e.setType("monthReportMessage");
        e.setImgUri("assets://localMessageIcon/messagecenter_achieve_ic_report.png");
        e.setMsgPosition(11);
        e.setMetaData(e.getMsgTitle());
        e.setExpireTime(0L);
        e(e, 3);
        String d = d();
        mct.b(this.e, "_month_report_no_genera_flag", d);
        LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "reportPop processMonthly monthReport general! currentMonth ", d);
    }

    private String d() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(1) + Constants.LINK + (calendar.get(2) + 1);
    }

    private boolean e(long j, int i) {
        if (j <= 0) {
            return true;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        int i2 = calendar.get(1);
        int i3 = calendar.get(2);
        int i4 = calendar.get(5);
        int i5 = calendar2.get(1);
        int i6 = calendar2.get(2);
        int i7 = calendar2.get(5);
        if (i == 3) {
            if (i2 > i5) {
                return true;
            }
            return i2 == i5 && i3 > i6;
        }
        if (i2 > i5) {
            return true;
        }
        if (i2 != i5 || i3 <= i6) {
            return i2 == i5 && i3 == i6 && i4 > i7;
        }
        return true;
    }

    private void c(UserAchieveWrapper userAchieveWrapper, meh mehVar) {
        int i;
        if (mehVar == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "AchieveServiceObserver, enter processWeekly");
        RecentWeekRecord acquireRecentWeekRecord = userAchieveWrapper.acquireRecentWeekRecord();
        if (acquireRecentWeekRecord == null) {
            LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "weekRecord is null");
            return;
        }
        if (e(acquireRecentWeekRecord.getEndUtcDate(), 2)) {
            LogUtil.h("PLGACHIEVE_AchieveServiceObserver", "processWeekly error weekly report");
            HashMap hashMap = new HashMap(8);
            hashMap.put(DBSessionCommon.COLUMN_TIME_ZONE, CommonUtil.am());
            mehVar.a(3, hashMap);
            return;
        }
        int acquireReportNo = acquireRecentWeekRecord.acquireReportNo();
        int acquireMinReportNo = acquireRecentWeekRecord.acquireMinReportNo();
        String b2 = mct.b(this.e, "_weekReportNo");
        a(String.valueOf(acquireReportNo), String.valueOf(acquireMinReportNo));
        if (mcv.e()) {
            int acquireMinReportNo2 = acquireRecentWeekRecord.acquireMinReportNo();
            int acquireReportNo2 = acquireRecentWeekRecord.acquireReportNo();
            try {
                i = Integer.parseInt(b2);
            } catch (NumberFormatException unused) {
                LogUtil.b("PLGACHIEVE_AchieveServiceObserver", "processWeekly NumberFormatException");
                i = 0;
            }
            int i2 = Calendar.getInstance().get(7);
            if (i != -1 && i2 - 1 == 0 && acquireReportNo2 > i && !a(userAchieveWrapper.getResultCode())) {
                MessageObject c = mehVar.c(acquireMinReportNo2, acquireReportNo2);
                c.setModule("16");
                c.setType(com.huawei.health.messagecenter.model.CommonUtil.WEEK_REPORT_MESSAGE);
                c.setImgUri("assets://localMessageIcon/messagecenter_achieve_ic_report.png");
                c.setMetaData(c.getMsgTitle());
                c.setMsgPosition(11);
                e(c, 2);
            }
        }
        HashMap hashMap2 = new HashMap(8);
        hashMap2.put(DBSessionCommon.COLUMN_TIME_ZONE, CommonUtil.am());
        mehVar.a(3, hashMap2);
    }

    private void e(MessageObject messageObject, int i) {
        String b2;
        if (messageObject == null) {
            LogUtil.h("PLGACHIEVE_AchieveServiceObserver", "generateMessage messageObject is null");
            return;
        }
        if (i == 2) {
            b2 = mct.b(this.e, "_achieve_msg_id_week");
        } else if (i == 3) {
            b2 = mct.b(this.e, "_achieve_msg_id_month");
        } else {
            LogUtil.h("PLGACHIEVE_AchieveServiceObserver", "generateMessage error contentType = ", Integer.valueOf(i));
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "generateMessage msgId=", b2, " contentType=", Integer.valueOf(i));
        if (mcv.d(this.e).getAdapter() != null) {
            if (!TextUtils.isEmpty(b2)) {
                messageObject.setMsgId(b2);
            }
            if (i == 3) {
                mcv.d(this.e).b();
            }
            String generateMessage = mcv.d(this.e).getAdapter().generateMessage(messageObject);
            if (i == 3) {
                if (TextUtils.isEmpty(b2)) {
                    b2 = generateMessage;
                }
                mct.b(this.e, "_achieve_msg_id_month", b2);
                SharedPreferenceManager.e(String.valueOf(20003), "new_report", true);
                LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "processMonthly msgId.=", b2);
                return;
            }
            if (!TextUtils.isEmpty(generateMessage)) {
                mct.b(this.e, "_achieve_msg_id_week", generateMessage);
                LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "processWeekly msgIdRes.=", generateMessage);
            } else {
                mct.b(this.e, "_achieve_msg_id_week", b2);
                LogUtil.a("PLGACHIEVE_AchieveServiceObserver", "processWeekly msgId.=", b2);
            }
        }
    }

    private void a(String str, String str2) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("_weekReportNo", str);
        hashMap.put("_weekMinReportNo", str2);
        mct.b(this.e, hashMap);
    }

    private void c(String str, String str2) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("_monthReportNo", str);
        hashMap.put("_monthMinReportNo", str2);
        mct.b(this.e, hashMap);
    }
}
