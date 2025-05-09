package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessSessionManager;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.stories.history.SportHistoryActivity;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class opi {
    private static opi b;

    public static opi d() {
        opi opiVar;
        synchronized (opi.class) {
            if (b == null) {
                b = new opi();
            }
            opiVar = b;
        }
        return opiVar;
    }

    public FitnessSessionManager.SessionActivityAction b() {
        return new FitnessSessionManager.SessionActivityAction() { // from class: opi.2
            @Override // com.huawei.health.suggestion.ui.fitness.viewholder.FitnessSessionManager.SessionActivityAction
            public void startSportHistoryActivity(Context context) {
                Intent intent = new Intent(context, (Class<?>) SportHistoryActivity.class);
                intent.setFlags(268435456);
                intent.putExtra(BleConstants.SPORT_TYPE, 10001);
                gnm.aPB_(context, intent);
                if (context instanceof Activity) {
                    ((Activity) context).overridePendingTransition(0, R.anim._2130771981_res_0x7f01000d);
                }
                opi.this.b(context, 10001, 0);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.viewholder.FitnessSessionManager.SessionActivityAction
            public void startSportHistoryActivity(Context context, int i, int i2) {
                Intent intent = new Intent(context, (Class<?>) SportHistoryActivity.class);
                intent.setFlags(268435456);
                intent.putExtra(BleConstants.SPORT_TYPE, i);
                intent.putExtra("subSportType", i2);
                gnm.aPB_(context, intent);
                if (context instanceof Activity) {
                    ((Activity) context).overridePendingTransition(0, R.anim._2130771981_res_0x7f01000d);
                }
                opi.this.b(context, i, i2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final Context context, final int i, final int i2) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: oph
            @Override // java.lang.Runnable
            public final void run() {
                opi.e(i, i2, context);
            }
        });
    }

    static /* synthetic */ void e(int i, int i2, Context context) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(i));
        hashMap.put("category", Integer.valueOf(i2));
        ixx.d().d(context, AnalyticsValue.HEALTH_HOME_GPS_HISTORY_2010015.value(), hashMap, 0);
    }
}
