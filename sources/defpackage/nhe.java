package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.huawei.health.algorithm.api.StressGameApi;
import com.huawei.health.algorithm.callback.StressGameBindCallback;
import com.huawei.health.algorithm.callback.StressGameNoticeInterface;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.stressgame.services.StressGameBindService;

@ApiDefine(uri = StressGameApi.class)
/* loaded from: classes6.dex */
public class nhe implements StressGameApi {
    private StressGameBindService c;

    @Override // com.huawei.health.algorithm.api.StressGameApi
    public void openMeasure() {
        StressGameBindService stressGameBindService = this.c;
        if (stressGameBindService != null) {
            stressGameBindService.e();
        }
    }

    @Override // com.huawei.health.algorithm.api.StressGameApi
    public void closeMeasure() {
        StressGameBindService stressGameBindService = this.c;
        if (stressGameBindService != null) {
            stressGameBindService.b();
        }
    }

    @Override // com.huawei.health.algorithm.api.StressGameApi
    public void startTimer() {
        StressGameBindService stressGameBindService = this.c;
        if (stressGameBindService != null) {
            stressGameBindService.a();
        }
    }

    @Override // com.huawei.health.algorithm.api.StressGameApi
    public void stopTimer() {
        StressGameBindService stressGameBindService = this.c;
        if (stressGameBindService != null) {
            stressGameBindService.d();
        }
    }

    @Override // com.huawei.health.algorithm.api.StressGameApi
    public void bindService(Context context, ServiceConnection serviceConnection) {
        LogUtil.a("StressGameApiImpl", "bindStressGameService");
        context.bindService(new Intent(context, (Class<?>) StressGameBindService.class), serviceConnection, 1);
    }

    @Override // com.huawei.health.algorithm.api.StressGameApi
    public void unbindService(Context context, ServiceConnection serviceConnection) {
        LogUtil.a("StressGameApiImpl", "unbindService");
        context.unbindService(serviceConnection);
        if (this.c != null) {
            this.c = null;
        }
    }

    @Override // com.huawei.health.algorithm.api.StressGameApi
    public void setStressGameServiceCallBack(IBinder iBinder, StressGameNoticeInterface stressGameNoticeInterface, StressGameBindCallback stressGameBindCallback) {
        if (iBinder instanceof StressGameBindService.StressGameBinder) {
            this.c = ((StressGameBindService.StressGameBinder) iBinder).getService();
            LogUtil.a("StressGameApiImpl", "setStressGameBindCallback");
            this.c.b(stressGameBindCallback);
            this.c.d(stressGameNoticeInterface);
        }
    }
}
