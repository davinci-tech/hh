package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.ads.fund.util.StringUtils;
import com.huawei.openalliance.ad.constant.AdConfigMapKey;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class wc {

    /* renamed from: a, reason: collision with root package name */
    private static wc f17726a;
    private static final byte[] d = new byte[0];
    private Context b;

    public int i(String str) {
        return dn_(str).getInt(AdConfigMapKey.DUPLICATE_TIME, 1);
    }

    public int j(String str) {
        return dn_(str).getInt(AdConfigMapKey.RECOMMEND_STRATEGY, 0);
    }

    public int d(String str) {
        return dn_(str).getInt(AdConfigMapKey.RECALL_STRATEGY, 0);
    }

    public int e(String str) {
        return dn_(str).getInt("rankStrategy", 4);
    }

    public int b(String str) {
        return dn_(str).getInt(AdConfigMapKey.FEEDBACK_ENABLED, 1);
    }

    public int a(String str) {
        return dn_(str).getInt(AdConfigMapKey.RECALL_RANK_ENABLED, 0);
    }

    public void e(String str, JSONObject jSONObject) {
        SharedPreferences.Editor edit = dn_(str).edit();
        Integer integer = StringUtils.toInteger(jSONObject.optString(AdConfigMapKey.RECALL_STRATEGY));
        edit.putInt(AdConfigMapKey.RECALL_STRATEGY, integer != null ? integer.intValue() : 0);
        Integer integer2 = StringUtils.toInteger(jSONObject.optString(AdConfigMapKey.RECOMMEND_STRATEGY));
        edit.putInt(AdConfigMapKey.RECOMMEND_STRATEGY, integer2 != null ? integer2.intValue() : 0);
        Integer integer3 = StringUtils.toInteger(jSONObject.optString(AdConfigMapKey.DUPLICATE_TIME));
        edit.putInt(AdConfigMapKey.DUPLICATE_TIME, integer3 != null ? integer3.intValue() : 1);
        Integer integer4 = StringUtils.toInteger(jSONObject.optString(AdConfigMapKey.IMP_MAX_COUNT));
        edit.putInt(AdConfigMapKey.IMP_MAX_COUNT, integer4 != null ? integer4.intValue() : 2);
        Integer integer5 = StringUtils.toInteger(jSONObject.optString(AdConfigMapKey.FEEDBACK_ENABLED));
        edit.putInt(AdConfigMapKey.FEEDBACK_ENABLED, integer5 != null ? integer5.intValue() : 1);
        edit.putString(AdConfigMapKey.RECOMMEND_TASK_LIST, jSONObject.optString(AdConfigMapKey.RECOMMEND_TASK_LIST, "2,3"));
        Integer integer6 = StringUtils.toInteger(jSONObject.optString(AdConfigMapKey.RECALL_RANK_ENABLED));
        edit.putInt(AdConfigMapKey.RECALL_RANK_ENABLED, integer6 != null ? integer6.intValue() : 0);
        Integer integer7 = StringUtils.toInteger(jSONObject.optString("rankStrategy"));
        edit.putInt("rankStrategy", integer7 != null ? integer7.intValue() : 4);
        edit.apply();
    }

    public int c(String str) {
        return dn_(str).getInt(AdConfigMapKey.IMP_MAX_COUNT, 2);
    }

    private SharedPreferences dn_(String str) {
        return this.b.getSharedPreferences("hiad_rec_slot_cfg_" + str, 0);
    }

    private static wc e(Context context) {
        wc wcVar;
        synchronized (d) {
            if (f17726a == null) {
                f17726a = new wc(context);
            }
            wcVar = f17726a;
        }
        return wcVar;
    }

    public static wc d(Context context) {
        return e(context);
    }

    private wc(Context context) {
        this.b = wm.d(context);
    }
}
