package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.hwhealthlinkage.linkage.parsedata.LinkageDataHandler;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dux implements LinkageDataHandler {
    @Override // com.huawei.health.hwhealthlinkage.linkage.parsedata.LinkageDataHandler
    public void handle(double d, ldo ldoVar) {
        ldoVar.c((int) d);
    }

    @Override // com.huawei.health.hwhealthlinkage.linkage.parsedata.LinkageDataHandler
    public cvv packageSamplePointInfo(JSONObject jSONObject, String str, int i) {
        try {
            int i2 = jSONObject.getInt(str);
            cvv cvvVar = new cvv();
            cvvVar.d(i);
            cvvVar.b(blq.e(i2));
            return cvvVar;
        } catch (JSONException e) {
            ReleaseLogUtil.c("R_LINKAGE_AveragePace", "packageSamplePointInfo JSONException: ", ExceptionUtils.d(e));
            return new cvv();
        }
    }
}
