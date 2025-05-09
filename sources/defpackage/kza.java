package defpackage;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipConnectedActivity;
import com.huawei.indoorequip.activity.IndoorEquipDisplayActivity;
import com.huawei.indoorequip.activity.IndoorEquipLandDisplayActivity;
import health.compact.a.HuaweiHealth;

/* loaded from: classes5.dex */
public class kza {

    /* renamed from: a, reason: collision with root package name */
    private Handler f14711a;
    private Context b;
    private final IndoorEquipConnectedActivity c;
    private final IndoorEquipLandDisplayActivity d;
    private final IndoorEquipDisplayActivity e;
    private law i;

    public kza(IndoorEquipConnectedActivity indoorEquipConnectedActivity, IndoorEquipDisplayActivity indoorEquipDisplayActivity, IndoorEquipLandDisplayActivity indoorEquipLandDisplayActivity) {
        this.c = indoorEquipConnectedActivity;
        this.e = indoorEquipDisplayActivity;
        this.d = indoorEquipLandDisplayActivity;
    }

    public void b() {
        if (this.c != null) {
            Toast.makeText(HuaweiHealth.a(), this.b.getString(R.string._2130840308_res_0x7f020af4), 0).show();
            String m = kzc.n().m();
            if (AbstractFitnessClient.ACTION_GATT_STATE_CONNECTING.equals(m)) {
                this.f14711a.sendEmptyMessage(311);
                return;
            }
            if (AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING.equals(m)) {
                this.f14711a.sendEmptyMessage(307);
                return;
            } else if (AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED.equals(m)) {
                this.f14711a.sendEmptyMessage(302);
                return;
            } else {
                LogUtil.a("IDEQ_CommonMethodsForActivities", "private void disposeCaseOfShowTipsWhenStartHasConnectedAlready");
                return;
            }
        }
        if (this.d != null) {
            Toast.makeText(HuaweiHealth.a(), this.b.getString(R.string._2130840308_res_0x7f020af4), 0).show();
        } else if (this.e != null) {
            Toast.makeText(HuaweiHealth.a(), this.b.getString(R.string._2130840308_res_0x7f020af4), 0).show();
        } else {
            LogUtil.b("IDEQ_CommonMethodsForActivities", "Activity is null!");
        }
    }

    public void a(String str) {
        law lawVar = this.i;
        if (lawVar != null) {
            lawVar.b(str);
        }
    }

    public void bTd_(Handler handler) {
        this.f14711a = handler;
    }

    public void b(law lawVar) {
        this.i = lawVar;
    }

    public void a(Context context) {
        this.b = context;
    }
}
