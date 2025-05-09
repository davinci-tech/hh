package com.huawei.pluginachievement.manager.service;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.ActivityInfo;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import defpackage.mct;
import defpackage.mcz;
import defpackage.meh;
import defpackage.mlb;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class AchieveMedalResDownloadObserver implements AchieveObserver {
    private Context b;

    public AchieveMedalResDownloadObserver(Context context) {
        this.b = context;
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        c(i, userAchieveWrapper, meh.c(BaseApplication.getContext()));
    }

    private void c(int i, UserAchieveWrapper userAchieveWrapper, meh mehVar) {
        if (mehVar == null) {
            return;
        }
        if (i == -1 || userAchieveWrapper == null) {
            LogUtil.a("PLGACHIEVE_AchieveMedalResDownloadObserver", "HttpErrCode or wrapper is error");
            return;
        }
        int contentType = userAchieveWrapper.getContentType();
        LogUtil.a("PLGACHIEVE_AchieveMedalResDownloadObserver", "AchieveMedalResDownloadObserver|onDataChanged contentType = ", Integer.valueOf(contentType));
        if (contentType == 8) {
            mehVar.y();
            HashMap hashMap = new HashMap(8);
            hashMap.put("countryCode", LoginInit.getInstance(this.b).getAccountInfo(1010));
            mehVar.a(0, hashMap);
            e(userAchieveWrapper, mehVar);
        }
        if (contentType == 10) {
            c(userAchieveWrapper, mehVar);
        }
    }

    private void e(UserAchieveWrapper userAchieveWrapper, meh mehVar) {
        LogUtil.a("PLGACHIEVE_AchieveMedalResDownloadObserver", "enter processActivityMedal");
        if (mehVar == null) {
            return;
        }
        if (TextUtils.isEmpty(mct.b(this.b, "FlorenceMedal"))) {
            LogUtil.a("PLGACHIEVE_AchieveMedalResDownloadObserver", "first cover install");
            mct.b(this.b, "FlorenceMedal", "done");
            mehVar.e();
            e();
            return;
        }
        Iterator<mcz> it = userAchieveWrapper.toList().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            mcz next = it.next();
            if (next instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) next;
                if (mlb.o(medalConfigInfo.acquireMedalType()) && TextUtils.isEmpty(medalConfigInfo.acquireEndTime())) {
                    LogUtil.a("PLGACHIEVE_AchieveMedalResDownloadObserver", "processActivityMedal acquireActivityId=", Integer.valueOf(medalConfigInfo.acquireActivityId()));
                    mehVar.e();
                    break;
                }
            }
        }
        e();
    }

    private void c(UserAchieveWrapper userAchieveWrapper, final meh mehVar) {
        LogUtil.a("PLGACHIEVE_AchieveMedalResDownloadObserver", "updateActivityMedalInfo ");
        final ActivityInfo acquireActivityInfo = userAchieveWrapper.acquireActivityInfo();
        if (acquireActivityInfo == null || TextUtils.isEmpty(acquireActivityInfo.acquireActivityId())) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.manager.service.AchieveMedalResDownloadObserver.5
            @Override // java.lang.Runnable
            public void run() {
                AchieveMedalResDownloadObserver.this.e(acquireActivityInfo, mehVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ActivityInfo activityInfo, meh mehVar) {
        if (mehVar == null) {
            LogUtil.a("PLGACHIEVE_AchieveMedalResDownloadObserver", "achieveDataManager is null");
            return;
        }
        if (activityInfo == null) {
            LogUtil.a("PLGACHIEVE_AchieveMedalResDownloadObserver", "activityInfo is null");
            return;
        }
        List<mcz> b = mehVar.b(9, new HashMap<>(2));
        if (b != null) {
            for (mcz mczVar : b) {
                if (mczVar instanceof MedalConfigInfo) {
                    MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                    if (activityInfo.acquireActivityId().equals(String.valueOf(medalConfigInfo.acquireActivityId()))) {
                        LogUtil.a("PLGACHIEVE_AchieveMedalResDownloadObserver", "updateActivityMedal medalId=", medalConfigInfo.acquireMedalID(), activityInfo.acquireEndTime());
                        medalConfigInfo.saveEndTime(d(activityInfo.acquireEndTime()));
                        medalConfigInfo.saveStartTime(d(activityInfo.acquireBeginTime()));
                        mehVar.e((mcz) medalConfigInfo);
                    }
                }
            }
        }
    }

    private void e() {
        LogUtil.a("PLGACHIEVE_AchieveMedalResDownloadObserver", "enter releaseManager");
        meh.c(BaseApplication.getContext()).c(this);
    }

    private static String d(String str) {
        String valueOf = String.valueOf(mlb.e(str, "yyyy-MM-dd HH:mm:ss"));
        LogUtil.a("PLGACHIEVE_AchieveMedalResDownloadObserver", "getActivityMedalTimestamp time", valueOf, "date=", str);
        return valueOf;
    }
}
