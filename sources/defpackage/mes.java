package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.AchieveLevelEventInfo;
import com.huawei.pluginachievement.manager.model.AchieveMessage;
import com.huawei.pluginachievement.manager.model.AchieveUserLevelInfo;
import com.huawei.pluginachievement.manager.model.MessageReminder;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.manager.service.AchieveLevelInfoObserver;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mes {
    private static volatile mes b;
    private static long c;
    private static volatile meh d;

    /* renamed from: a, reason: collision with root package name */
    private Context f14936a;
    private mcz e;

    private mes(Context context) {
        this.f14936a = context.getApplicationContext();
    }

    public static mes c(Context context) {
        if (context == null) {
            return null;
        }
        d = meh.c(context.getApplicationContext());
        if (b == null) {
            synchronized (mes.class) {
                if (b == null) {
                    b = new mes(context);
                }
            }
        }
        return b;
    }

    public boolean d(AchieveUserLevelInfo achieveUserLevelInfo) {
        if (achieveUserLevelInfo == null) {
            LogUtil.a("PLGACHIEVE_AchieveLevelManager", "userLevelInfo is null");
            return false;
        }
        return d.c(achieveUserLevelInfo);
    }

    public void b(AchieveLevelEventInfo achieveLevelEventInfo) {
        if (achieveLevelEventInfo == null) {
            LogUtil.a("PLGACHIEVE_AchieveLevelManager", "achieveLevelEventInfo is null");
        } else {
            d.e(achieveLevelEventInfo);
        }
    }

    public void c(int i) {
        if (Utils.o()) {
            return;
        }
        if (c()) {
            LogUtil.b("PLGACHIEVE_AchieveLevelManager", "showLevelDialog is fast!");
            return;
        }
        if (!BaseApplication.isRunningForeground()) {
            LogUtil.h("PLGACHIEVE_AchieveLevelManager", "app is isRunningBackground!");
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("level", i);
        intent.setClassName(this.f14936a, PersonalData.CLASS_NAME_PERSONAL_LEVEL_DIALOG);
        intent.putExtra("tag", bundle);
        intent.addFlags(268435456);
        this.f14936a.startActivity(intent);
    }

    public void d(UserAchieveWrapper userAchieveWrapper) {
        LogUtil.a("PLGACHIEVE_AchieveLevelManager", "enter processLevelMessageRemind messages");
        MessageReminder msgReminder = userAchieveWrapper.getMsgReminder();
        if (msgReminder == null) {
            return;
        }
        List<AchieveMessage> messages = msgReminder.getMessages();
        LogUtil.a("PLGACHIEVE_AchieveLevelManager", "processLevelMessageRemind messages:", messages);
        if (messages == null) {
            return;
        }
        for (AchieveMessage achieveMessage : messages) {
            if (achieveMessage.getMsgType() == 5) {
                String content = achieveMessage.getContent();
                LogUtil.a("PLGACHIEVE_AchieveLevelManager", "processLevelMessageRemind，content = ", content);
                try {
                    int optInt = new JSONObject(content).optInt("level");
                    LogUtil.a("PLGACHIEVE_AchieveLevelManager", "processLevelMessageRemind，level = ", Integer.valueOf(optInt));
                    if (optInt > 0) {
                        c(optInt);
                    }
                } catch (JSONException e) {
                    LogUtil.b("PLGACHIEVE_AchieveLevelManager", "processLevelMessageRemind Exception:", e.getMessage());
                }
            }
        }
    }

    public static boolean c() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - c < 60000) {
            return true;
        }
        c = elapsedRealtime;
        return false;
    }

    public void d(final String str, final int i) {
        if (!mle.m(str)) {
            LogUtil.a("PLGACHIEVE_AchieveLevelManager", "key is Invalid");
        } else {
            mct.b(this.f14936a, "levelEventKey", "");
            ThreadPoolManager.d().execute(new Runnable() { // from class: mev
                @Override // java.lang.Runnable
                public final void run() {
                    mes.this.b(str, i);
                }
            });
        }
    }

    /* synthetic */ void b(String str, int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("taskId", str);
        mcz d2 = d.d(16, hashMap);
        mcz d3 = d.d(14, hashMap);
        int d4 = mlc.d(str);
        if (d2 instanceof AchieveLevelEventInfo) {
            AchieveLevelEventInfo achieveLevelEventInfo = (AchieveLevelEventInfo) d2;
            if (mle.a(achieveLevelEventInfo.acquireLastModifyTime(), System.currentTimeMillis())) {
                achieveLevelEventInfo.saveLastModifyTime(System.currentTimeMillis());
                achieveLevelEventInfo.saveTaskSumCount(achieveLevelEventInfo.acquireTaskSumCount() + 1);
                achieveLevelEventInfo.saveSyncTime(0L);
                b(achieveLevelEventInfo);
            } else {
                LogUtil.a("PLGACHIEVE_AchieveLevelManager", "level event is completed");
            }
        } else {
            AchieveLevelEventInfo achieveLevelEventInfo2 = new AchieveLevelEventInfo();
            achieveLevelEventInfo2.saveTaskSumCount(1);
            achieveLevelEventInfo2.saveTaskCount(1);
            achieveLevelEventInfo2.saveLastModifyTime(System.currentTimeMillis());
            achieveLevelEventInfo2.saveRewardExperience(d4);
            achieveLevelEventInfo2.saveTaskId(str);
            achieveLevelEventInfo2.saveSyncTime(0L);
            b(achieveLevelEventInfo2);
        }
        if (d3 instanceof AchieveUserLevelInfo) {
            AchieveUserLevelInfo achieveUserLevelInfo = (AchieveUserLevelInfo) d3;
            achieveUserLevelInfo.saveUserExperience(i);
            e(achieveUserLevelInfo, achieveUserLevelInfo);
            LogUtil.a("PLGACHIEVE_AchieveLevelManager", "dealLevelEvent userLevel totalGrowth ", Integer.valueOf(i));
            return;
        }
        AchieveUserLevelInfo achieveUserLevelInfo2 = new AchieveUserLevelInfo();
        achieveUserLevelInfo2.saveUserLevel(1);
        achieveUserLevelInfo2.saveUserExperience(0);
        achieveUserLevelInfo2.saveUserReachDays(0.0d);
        achieveUserLevelInfo2.saveSyncTimestamp(System.currentTimeMillis());
        achieveUserLevelInfo2.saveUserExperience(d4);
        e(achieveUserLevelInfo2, achieveUserLevelInfo2);
        LogUtil.a("PLGACHIEVE_AchieveLevelManager", "dealLevelEvent init userLevel one.");
    }

    public void b(final AchieveUserLevelInfo achieveUserLevelInfo) {
        ThreadPoolManager.d().c("PLGACHIEVE_AchieveLevelManager", new Runnable() { // from class: mes.2
            @Override // java.lang.Runnable
            public void run() {
                mcz d2;
                HashMap hashMap = new HashMap(2);
                if (mes.this.e == null) {
                    d2 = mes.d.d(14, hashMap);
                    LogUtil.a("PLGACHIEVE_AchieveLevelManager", "dealLevelStepsEvent userLevel getData.");
                } else {
                    LogUtil.a("PLGACHIEVE_AchieveLevelManager", "startUserLevel enter.");
                    d2 = mes.this.e;
                    mes.this.e = null;
                }
                if (d2 instanceof AchieveUserLevelInfo) {
                    mes.this.e(achieveUserLevelInfo, (AchieveUserLevelInfo) d2);
                    LogUtil.a("PLGACHIEVE_AchieveLevelManager", "dealLevelStepsEvent ex", Integer.valueOf(achieveUserLevelInfo.acquireUserExperience()));
                    return;
                }
                AchieveUserLevelInfo achieveUserLevelInfo2 = new AchieveUserLevelInfo();
                achieveUserLevelInfo2.saveUserLevel(1);
                achieveUserLevelInfo2.saveUserExperience(0);
                achieveUserLevelInfo2.saveUserReachDays(0.0d);
                achieveUserLevelInfo2.saveSyncTimestamp(System.currentTimeMillis());
                mes.this.e(achieveUserLevelInfo, achieveUserLevelInfo2);
                LogUtil.a("PLGACHIEVE_AchieveLevelManager", "dealLevelStepsEvent init userLevel one.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(AchieveUserLevelInfo achieveUserLevelInfo, AchieveUserLevelInfo achieveUserLevelInfo2) {
        if (achieveUserLevelInfo2 == null || achieveUserLevelInfo2.acquireSyncTimestamp() == 0) {
            LogUtil.h("PLGACHIEVE_AchieveLevelManager", "dealLevelMessage userLevelInfo.acquireSyncTimestamp() == 0!");
            return;
        }
        if (achieveUserLevelInfo == null) {
            LogUtil.h("PLGACHIEVE_AchieveLevelManager", "dealLevelMessage newUserLevelInfo == null");
            return;
        }
        int e = mlc.e(achieveUserLevelInfo.acquireUserExperience());
        int acquireUserLevel = achieveUserLevelInfo2.acquireUserLevel();
        LogUtil.a("PLGACHIEVE_AchieveLevelManager", "dealLevelMessage newLevel ", Integer.valueOf(e), " oldLevel ", Integer.valueOf(acquireUserLevel));
        achieveUserLevelInfo2.saveUserExperience(achieveUserLevelInfo.acquireUserExperience());
        achieveUserLevelInfo2.saveUserLevel(e);
        achieveUserLevelInfo2.saveUserReachDays(achieveUserLevelInfo.acquireUserReachDays());
        achieveUserLevelInfo2.saveSyncTimestamp(achieveUserLevelInfo.acquireSyncTimestamp());
        if (!d(achieveUserLevelInfo2) || e <= acquireUserLevel) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveLevelManager", "dealLevelMessage showLevelDialog!.");
    }

    public void a() {
        this.e = d.d(14, new HashMap(2));
        LogUtil.a("PLGACHIEVE_AchieveLevelManager", "startUserLevel getData()");
        String b2 = mct.b(this.f14936a, "levelEventKey");
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveLevelManager", "dealNotDealEvent key=", b2);
        c(b2);
    }

    public void c(String str) {
        LogUtil.a("PLGACHIEVE_AchieveLevelManager", "updateUserExperience is unUsed! ", str);
    }

    public void b() {
        HashMap hashMap = new HashMap(8);
        ThreadPoolManager.d().execute(new Runnable() { // from class: mes.3
            @Override // java.lang.Runnable
            public void run() {
                mes.this.a();
            }
        });
        d.b(new AchieveLevelInfoObserver(this.f14936a));
        d.a(13, hashMap);
    }
}
