package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveObserver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class meu implements AchieveObserver {

    /* renamed from: a, reason: collision with root package name */
    private int[] f14938a;
    private int b;
    private Object c;
    private CountDownLatch d = new CountDownLatch(1);
    private Context e;

    meu(Context context, int i) {
        this.e = context;
        this.b = i;
    }

    public void e(int[] iArr) {
        if (iArr != null) {
            this.f14938a = (int[]) iArr.clone();
        }
    }

    boolean a(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.d.await(j, timeUnit);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0058, code lost:
    
        if (r1 != 18) goto L26;
     */
    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDataChanged(int r4, com.huawei.pluginachievement.manager.model.UserAchieveWrapper r5) {
        /*
            r3 = this;
            java.lang.String r0 = "PLGACHIEVE_AchieveKakaObserver"
            if (r5 != 0) goto Le
            java.lang.String r4 = "AchieveKakaObserver wrapper is null"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r4)
            return
        Le:
            int r1 = r5.getContentType()
            int r2 = r3.b
            if (r1 == r2) goto L20
            java.lang.String r4 = "AchieveKakaObserver do not need process result"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r4)
            return
        L20:
            r2 = -1
            if (r4 != r2) goto L3d
            java.lang.String r4 = "HttpErrCode or userAchieveWrapper is error"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r4)
            java.util.concurrent.CountDownLatch r4 = r3.d
            r4.countDown()
            android.content.Context r4 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            meh r4 = defpackage.meh.c(r4)
            r4.c(r3)
            return
        L3d:
            java.lang.String r4 = "AchieveKakaObserver|onDataChanged contentType = "
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r4)
            r4 = 11
            if (r1 == r4) goto L70
            r4 = 12
            if (r1 == r4) goto L62
            r4 = 17
            if (r1 == r4) goto L5b
            r4 = 18
            if (r1 == r4) goto L62
            goto L7c
        L5b:
            java.util.List r4 = r5.getHealthLifeKakaTaskInfoList()
            r3.c = r4
            goto L7c
        L62:
            mde r4 = r5.acquireKakaUpdateReturnBody()
            r3.c = r4
            mde r4 = r5.acquireKakaUpdateReturnBody()
            r3.e(r1, r4)
            goto L7c
        L70:
            int[] r4 = r3.f14938a
            java.util.List r5 = r5.getHealthLifeKakaTaskInfoList()
            java.util.List r4 = defpackage.mle.b(r4, r5)
            r3.c = r4
        L7c:
            java.util.concurrent.CountDownLatch r4 = r3.d
            r4.countDown()
            android.content.Context r4 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            meh r4 = defpackage.meh.c(r4)
            r4.c(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.meu.onDataChanged(int, com.huawei.pluginachievement.manager.model.UserAchieveWrapper):void");
    }

    public Object b() {
        return this.c;
    }

    private void e(int i, mde mdeVar) {
        if (mdeVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaObserver", "updateTaskInfoToDb kakaUpdateReturnBody is null");
            return;
        }
        String e = mdeVar.e();
        int c = mdeVar.c();
        LogUtil.a("PLGACHIEVE_AchieveKakaObserver", "updateTaskInfoToDb UPDATE_TASK_STATUS RewardKaka=", Integer.valueOf(c));
        if (c > 0) {
            int b = mdeVar.b();
            LogUtil.c("PLGACHIEVE_AchieveKakaObserver", "onDataChange UPDATE_TASK_STATUS taskIdTemp =", e);
            mer.b(this.e.getApplicationContext()).b(b);
            LogUtil.a("PLGACHIEVE_AchieveKakaObserver", "updateTaskInfoToDb getResultCode =", mdeVar.d());
            if (i == 12 && "0".equals(mdeVar.d())) {
                LogUtil.a("PLGACHIEVE_AchieveKakaObserver", "onDataChange task picked ");
                mer.b(this.e.getApplicationContext()).b(e, 2);
            }
        }
    }
}
