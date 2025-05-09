package defpackage;

import com.huawei.health.threeCircle.remind.model.ThreeCircleRemindData;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class gjr implements ResponseCallback<ThreeCircleRemindData> {

    /* renamed from: a, reason: collision with root package name */
    private gjs f12828a;
    private String b;
    private boolean c;
    private ResponseCallback<ThreeCircleRemindData> e;

    public gjr(gjs gjsVar, String str, boolean z, ResponseCallback<ThreeCircleRemindData> responseCallback) {
        this.f12828a = gjsVar;
        this.b = str;
        this.c = z;
        this.e = responseCallback;
    }

    public gjr(gjs gjsVar, String str, ResponseCallback<ThreeCircleRemindData> responseCallback) {
        this.f12828a = gjsVar;
        this.b = str;
        this.c = false;
        this.e = responseCallback;
    }

    public boolean d() {
        return this.c;
    }

    @Override // com.huawei.hwbasemgr.ResponseCallback
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onResponse(int i, ThreeCircleRemindData threeCircleRemindData) {
        LogUtil.a("RemindDataCallback", "onResponse:", Integer.valueOf(i), "remindType:", this.b);
        if (threeCircleRemindData == null) {
            LogUtil.h("RemindDataCallback", "onResponse data == null");
            return;
        }
        if (this.f12828a == null || this.e == null) {
            LogUtil.b("RemindDataCallback", "onResponse: mRemindDataQueue == null or mResponseCallback == null");
            return;
        }
        ReleaseLogUtil.e("R_RemindDataCallback", "remindType:", this.b, " data:", threeCircleRemindData.getRemindText(), " ", threeCircleRemindData.getRemindType(), " isFromDevice:", Boolean.valueOf(this.c));
        if (this.c) {
            this.e.onResponse(0, threeCircleRemindData);
            return;
        }
        this.b = threeCircleRemindData.getRemindType();
        this.f12828a.d(threeCircleRemindData);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            ReleaseLogUtil.c("R_RemindDataCallback", "sleep error.", LogAnonymous.b((Throwable) e));
        }
        a(threeCircleRemindData);
    }

    private void a(ThreeCircleRemindData threeCircleRemindData) {
        ThreeCircleRemindData e = this.f12828a.e();
        if (e == null) {
            ReleaseLogUtil.c("R_RemindDataCallback", "remindData == null.");
            this.e.onResponse(-1, null);
            return;
        }
        String str = this.b;
        if (str == null || str.equals(e.getRemindType())) {
            this.e.onResponse(0, e);
            this.f12828a.d();
        } else {
            ReleaseLogUtil.d("R_RemindDataCallback", "priority too low, has other remind.no need response.", Integer.valueOf(threeCircleRemindData.getPriority()), threeCircleRemindData.getRemindType());
        }
    }
}
