package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.receiver.GprsBloodSugarPushReceiver;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ezq implements IpushBase {
    private Context d;

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList("3001");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            ReleaseLogUtil.c("DEVMGR_GprsAdapterPushReceiver", "processPushMsg message is null return.");
            return;
        }
        ReleaseLogUtil.e("DEVMGR_GprsAdapterPushReceiver", "processPushMsg message:", str);
        this.d = context;
        a(str);
    }

    private void a(String str) {
        try {
            e eVar = new e();
            JSONObject jSONObject = new JSONObject(new JSONObject(str).getString(CommonUtil.MSG_CONTENT));
            eVar.c = jSONObject.getInt("pointCount");
            eVar.d = jSONObject.getString("dataType");
            JSONArray jSONArray = jSONObject.getJSONArray("endTimeList");
            eVar.e = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                eVar.e.add(Long.valueOf(jSONArray.getLong(i)));
            }
            e(eVar);
        } catch (NumberFormatException | JSONException unused) {
            LogUtil.b("GprsAdapterPushReceiver", "messageJson parse exception.");
        }
    }

    private void e(e eVar) {
        int parseInt = Integer.parseInt(eVar.d);
        if (parseInt == 4) {
            LogUtil.a("GprsAdapterPushReceiver", "Start processing blood sugar data");
            new GprsBloodSugarPushReceiver().processPushMsg(this.d, eVar);
        } else if (parseInt == 5) {
            LogUtil.a("GprsAdapterPushReceiver", "Start processing blood pressure data");
            new ezp().processPushMsg(this.d, eVar);
        } else if (parseInt == 700012) {
            LogUtil.a("GprsAdapterPushReceiver", "Start processing ventilator data");
            new ezr().processPushMsg(this.d, eVar);
        } else {
            LogUtil.a("GprsAdapterPushReceiver", "Other data, dataType = ", Integer.valueOf(parseInt));
        }
    }

    /* loaded from: classes3.dex */
    public static class e {
        int c;
        public String d;
        public List<Long> e;

        e() {
        }
    }
}
