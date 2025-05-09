package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;

/* loaded from: classes3.dex */
public class cpd {
    public static void Kb_(Context context, Uri uri) {
        LogUtil.a("PluginDevice_JumpUtil", "enter scaleUpdateJump");
        if (context == null) {
            LogUtil.a("PluginDevice_JumpUtil", "context is null");
            return;
        }
        String queryParameter = uri.getQueryParameter(MedalConstants.EVENT_KEY);
        String queryParameter2 = uri.getQueryParameter("uniqueId");
        LogUtil.a("PluginDevice_JumpUtil", "scaleUpdateJump productId=", queryParameter, ", uniqueId isEmpty ", Boolean.valueOf(TextUtils.isEmpty(queryParameter2)));
        if (ceo.d().e(queryParameter, false) == null) {
            LogUtil.h("PluginDevice_JumpUtil", "scaleUpdateJump bondedDevice is null");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("productId", queryParameter);
        ContentValues contentValues = new ContentValues();
        if (cpa.x(queryParameter)) {
            queryParameter2 = ceo.d().d(queryParameter, queryParameter2);
            if (cpa.c(queryParameter, queryParameter2)) {
                Kc_(context, intent, ceo.d().b(queryParameter, false));
            } else {
                Kc_(context, intent, true);
            }
        } else if (cpa.au(queryParameter)) {
            Kc_(context, intent, true);
        } else {
            Kc_(context, intent, false);
        }
        contentValues.put("productId", queryParameter);
        contentValues.put("uniqueId", queryParameter2);
        intent.putExtra("commonDeviceInfo", contentValues);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("PluginDevice_JumpUtil", "scaleUpdateJump ActivityNotFoundException");
        }
    }

    private static void Kc_(Context context, Intent intent, boolean z) {
        if (z) {
            intent.setClass(context, DeviceMainActivity.class);
            intent.putExtra("view", "WifiVersionDetails");
        } else {
            intent.setClassName(context, "com.huawei.ui.device.activity.update.WeightUpdateVersionActivity");
            intent.putExtra("isUpdateDialog", true);
            intent.putExtra("user_type", true);
            intent.putExtra("isFromNotify", true);
        }
    }
}
