package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.phoneprocess.SampleSequenceAfterProcess;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.sleep.SleepApi;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.SleepDailyProcessResultCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class nhs implements SampleSequenceAfterProcess {
    @Override // com.huawei.health.devicemgr.api.phoneprocess.SampleSequenceAfterProcess
    public HiHealthData onSamplSequenceAfterProcess(HiHealthData hiHealthData, String str) {
        if (!d(hiHealthData)) {
            b(str);
            return hiHealthData;
        }
        try {
            JSONObject jSONObject = new JSONObject(hiHealthData.getMetaData());
            if (!e(jSONObject)) {
                b(str);
                return hiHealthData;
            }
            nhv nhvVar = new nhv(jSONObject, hiHealthData.getDeviceUuid());
            a(jSONObject, nhvVar);
            d(hiHealthData.getSequenceData(), nhvVar, jSONObject, str);
            c(hiHealthData, jSONObject, nhvVar);
            return hiHealthData;
        } catch (JSONException unused) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "metadata jsonException");
            b(str);
            return hiHealthData;
        }
    }

    private void c(HiHealthData hiHealthData, JSONObject jSONObject, nhv nhvVar) {
        if (nhvVar.b() == -1) {
            try {
                jSONObject.put("validData", 65535L);
                hiHealthData.setMetaData(jSONObject.toString());
            } catch (JSONException e) {
                ReleaseLogUtil.c("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", " trans validdata error: ", ExceptionUtils.d(e));
            }
        }
    }

    private boolean d(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "checkParameters hiHealthData is null");
            return false;
        }
        if (TextUtils.isEmpty(hiHealthData.getDeviceUuid())) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "checkParameters deviceUuid isEmpty");
            return false;
        }
        if (TextUtils.isEmpty(hiHealthData.getMetaData())) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "checkParameters metaData isEmpty");
            return false;
        }
        LogUtil.a("CoreSleepSequenceAfterProcess", "metaData is ", hiHealthData.getMetaData());
        LogUtil.a("CoreSleepSequenceAfterProcess", "detailData is ", hiHealthData.getSequenceData());
        return true;
    }

    private boolean e(JSONObject jSONObject) {
        if (jSONObject.has("wakeupTime") && jSONObject.has("fallAsleepTime")) {
            return true;
        }
        ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", " no fallAsleepTime or wakeupTime");
        return false;
    }

    private void a(JSONObject jSONObject, nhv nhvVar) {
        if (nhvVar.b() == -1 || nhvVar.b() == 65535) {
            ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "validData is: ", Integer.valueOf(nhvVar.b()));
            return;
        }
        List<HiHealthData> e = nib.e(jSONObject, nhvVar);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(e);
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "insertSleepStatData start");
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: nhs.5
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "insertSleepStatData errorCode ", Integer.valueOf(i));
            }
        });
    }

    private void d(String str, final nhv nhvVar, JSONObject jSONObject, final String str2) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "checkParameters detailData isEmpty");
            b(str2);
            return;
        }
        int length = str.length();
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "detailDataLength is ", Integer.valueOf(length));
        if (length % 8 != 0) {
            b(str2);
            ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "detailData length error");
            return;
        }
        List<HiHealthData> d = nhx.d(str, nhvVar, jSONObject);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(d);
        LogUtil.a("CoreSleepSequenceAfterProcess", "sleepDetailList size ", Integer.valueOf(d.size()));
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "insertSleepDetailData start");
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: nhs.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "insertSleepDetailData errorCode ", Integer.valueOf(i));
                if (nhs.this.b(str2)) {
                    if (i == 0) {
                        fch.a(new Date());
                        nhs.this.e();
                        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_SLEEP_DATA_REPORT_85070001.value(), 0);
                        nhs.this.b();
                    }
                    if (VersionControlUtil.isSupportSleepManagement()) {
                        nhs.this.d(i, nhvVar);
                    } else if (nhy.d(i, 0, nhvVar)) {
                        nhy.e(nhvVar);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) {
        DeviceInfo b = nib.b();
        if (b == null) {
            LogUtil.h("CoreSleepSequenceAfterProcess", "saveCoreSleepProcessSuccess() deviceInfo is null");
            return false;
        }
        if ("true".equals(str)) {
            nib.e(b.getDeviceIdentify(), true, 4000000, "CoreSleepSequenceAfterProcess");
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        nhu.a().startSynBasicData(new IBaseResponseCallback() { // from class: nhs.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "insertSleepDetailData to syncFitnessDetailData, errorCode :", Integer.valueOf(i));
                if (i == 0) {
                    nhs.this.e();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("CoreSleepSequenceAfterProcess", "start syncSleepAfterInsert");
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i, final nhv nhvVar) {
        SleepApi sleepApi = (SleepApi) Services.a("Main", SleepApi.class);
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "requestAndUpdateDailyResult start");
        sleepApi.requestAndUpdateDailyResult(ggl.g(System.currentTimeMillis()), new SleepDailyProcessResultCallback<fda>() { // from class: nhs.3
            @Override // com.huawei.hwbasemgr.SleepDailyProcessResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i2, fda fdaVar) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSequenceAfterProcess", "requestAndUpdateDailyResult algorithmCode is ", Integer.valueOf(i2));
                if (nhy.d(i, i2, nhvVar)) {
                    nhy.e(nhvVar);
                }
            }
        });
    }
}
