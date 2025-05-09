package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.service.AchieveMedalResDownloadObserver;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class mei {
    public static void e(final meh mehVar, Context context) {
        if (mehVar == null) {
            return;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        List<mcz> b = mehVar.b(9, new HashMap(2));
        mehVar.b(new AchieveMedalResDownloadObserver(context));
        if (b != null) {
            for (mcz mczVar : b) {
                if (mczVar instanceof MedalConfigInfo) {
                    MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                    if (mlb.o(medalConfigInfo.acquireMedalType()) && TextUtils.isEmpty(medalConfigInfo.acquireEndTime())) {
                        final String valueOf = String.valueOf(medalConfigInfo.acquireActivityId());
                        newSingleThreadExecutor.execute(new Runnable() { // from class: mel
                            @Override // java.lang.Runnable
                            public final void run() {
                                mei.e(meh.this, valueOf);
                            }
                        });
                    }
                }
            }
            newSingleThreadExecutor.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(meh mehVar, String str) {
        LogUtil.a("PLGACHIEVE_AchieveActivityMedalService", "processActivityMedal acquireActivityId=", str);
        HashMap hashMap = new HashMap(1);
        hashMap.put("activityId", str);
        mehVar.a(10, hashMap);
    }
}
