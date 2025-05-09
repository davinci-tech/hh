package defpackage;

import com.huawei.health.suggestion.RunCourseRecommendCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.fitnessdatatype.Vo2maxDetail;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class euu implements IBaseResponseCallback {
    private int b = 0;
    private RunCourseRecommendCallback c;

    euu(RunCourseRecommendCallback runCourseRecommendCallback) {
        this.c = runCourseRecommendCallback;
    }

    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
    /* renamed from: onResponse */
    public void d(int i, Object obj) {
        fij fijVar = new fij();
        if (obj instanceof Vo2maxDetail) {
            Vo2maxDetail vo2maxDetail = (Vo2maxDetail) obj;
            LogUtil.a("Suggest_MyBaseResponseCallback", "System.currentTimeMillis()= ", Long.valueOf(System.currentTimeMillis()), " detail.getTimestamp() = ", Long.valueOf(vo2maxDetail.getTimestamp()));
            if (System.currentTimeMillis() - vo2maxDetail.getTimestamp() < 2592000000L) {
                this.b = vo2maxDetail.getVo2maxValue();
                fijVar.d(true);
            }
        } else {
            fijVar.d(false);
            LogUtil.a("Suggest_MyBaseResponseCallback", "! objData instanceof Vo2maxDetail= ");
        }
        fijVar.b(this.b);
        LogUtil.a("Suggest_MyBaseResponseCallback", "errcode= ", Integer.valueOf(i), "mVo2max= ", Integer.valueOf(this.b));
        this.c.onSuccess(eva.c(fijVar));
    }
}
