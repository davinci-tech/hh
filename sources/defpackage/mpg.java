package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.health.devicemgr.api.phoneprocess.SampleSequenceAfterProcess;
import com.huawei.hihealth.HiHealthData;
import health.compact.a.util.LogUtil;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mpg implements SampleSequenceAfterProcess {
    @Override // com.huawei.health.devicemgr.api.phoneprocess.SampleSequenceAfterProcess
    public HiHealthData onSamplSequenceAfterProcess(HiHealthData hiHealthData, String str) {
        if (hiHealthData == null) {
            LogUtil.c("PpgSampleSequenceAfterProcess", "hiHealthData==null");
            return hiHealthData;
        }
        try {
        } catch (InterruptedException unused) {
            LogUtil.e("PpgSampleSequenceAfterProcess", "interrupted while loadPlugin");
        } catch (JSONException unused2) {
            LogUtil.e("PpgSampleSequenceAfterProcess", "jsonException");
            return hiHealthData;
        }
        if (hiHealthData.getSequenceData() == null) {
            LogUtil.c("PpgSampleSequenceAfterProcess", "no ppgData");
            return hiHealthData;
        }
        String metaData = hiHealthData.getMetaData();
        if (TextUtils.isEmpty(metaData)) {
            return hiHealthData;
        }
        JSONObject jSONObject = new JSONObject(metaData);
        if (jSONObject.has("firstValley") || !jSONObject.has("accStatus")) {
            return hiHealthData;
        }
        mpj a2 = mpj.a();
        if (a2.isPluginAvaiable()) {
            return a2.getPeriodMeasureResult(hiHealthData);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        a2.loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mpg.5
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public boolean call(Context context, Intent intent) {
                LogUtil.d("PpgSampleSequenceAfterProcess", "loadPlugin call");
                countDownLatch.countDown();
                return false;
            }

            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public void onFailed(Context context, Intent intent) {
                LogUtil.d("PpgSampleSequenceAfterProcess", "loadPlugin failed");
                countDownLatch.countDown();
            }
        });
        if (countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
            return a2.getPeriodMeasureResult(hiHealthData);
        }
        LogUtil.c("PpgSampleSequenceAfterProcess", "loadPlugin wait timeout");
        return hiHealthData;
    }
}
