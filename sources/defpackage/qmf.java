package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.alipay.sdk.m.p.e;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.arkuix.IntentParams;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity;
import health.compact.a.LogUtil;

/* loaded from: classes6.dex */
public class qmf {
    public static void dFD_(final Context context, final Bundle bundle) {
        boolean z;
        if (!HandlerExecutor.b()) {
            HandlerExecutor.e(new Runnable() { // from class: qme
                @Override // java.lang.Runnable
                public final void run() {
                    qmf.dFD_(context, bundle);
                }
            });
            return;
        }
        Context e = BaseApplication.e();
        if (efb.e()) {
            Intent intent = new Intent();
            intent.setClassName(context.getPackageName(), "com.huawei.hwarkuix.EntryAbilityActivity");
            String string = (bundle == null || !bundle.containsKey("tab")) ? "stress" : bundle.getString("tab");
            String intentParams = IntentParams.builder().addPageId(ArkUIXConstants.KNIT_EMOTION).addPageType("8").addRecord("{'tab':'" + string + "'}").build().toString();
            intent.putExtra(e.n, intentParams);
            intent.putExtra(ArkUIXConstants.ARKUIX_MODULE_TYPE_KEY, 1);
            LogUtil.c("EmotionStressUtil", "gotoEmotionStress params ", intentParams);
            gnm.aPC_(context, intent);
            return;
        }
        Intent intent2 = new Intent(e, (Class<?>) KnitPressureActivity.class);
        if (bundle != null) {
            if (bundle.containsKey("type")) {
                intent2.putExtra("type", bundle.getInt("type"));
            }
            if (bundle.containsKey("pressure_is_have_datas")) {
                z = bundle.getBoolean("pressure_is_have_datas");
                intent2.putExtra("pressure_is_have_datas", z);
            } else {
                z = false;
            }
            if (bundle.containsKey("lastDataTime") && z) {
                KnitPressureActivity.b(context, bundle.getLong("lastDataTime"));
                return;
            }
        }
        gnm.aPB_(context, intent2);
    }
}
