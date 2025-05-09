package defpackage;

import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hms.framework.network.restclient.hwhttp.MediaType;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.trans.MultipartBody;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.thirdpartservice.syncdata.SyncTask;
import com.huawei.ui.thirdpartservice.syncdata.constants.StravaInfo;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import com.huawei.ui.thirdpartservice.syncdata.net.SyncDataToStravaApi;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class sjp extends SyncTask {
    private final SyncDataToStravaApi d;

    sjp(HiHealthData hiHealthData, String str, File file, SyncDataToStravaApi syncDataToStravaApi) {
        super(hiHealthData, str, file);
        this.d = syncDataToStravaApi;
        this.mUploadDomain = jah.c().e("domain_strava");
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public void retryTooManyTimes() {
        sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), this.mDescription, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_STRAVA, "", "failed to retry too many times.");
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public void sportDataException() {
        sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), this.mDescription, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_STRAVA, "", "sport data exception.");
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public void doUpload() {
        d(this.mGpxFile, this.mUploadDomain + StravaInfo.STRAVA_UPLOAD_PATH, this.mDescription, this.mHiHealthData.getStartTime());
    }

    private void d(File file, String str, String str2, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + sjo.d().getAccessToken());
        this.d.uploadGpxToStrava(str, hashMap, c(file, str2, j)).enqueue(e(file, str2));
    }

    private MultipartBody c(File file, String str, long j) {
        MediaType parse = MediaType.parse("text/*");
        return new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("name", "Huawei Health activity").addFormDataPart("description", str + " " + nsj.e(j)).addFormDataPart("data_type", NavigationFileParser.GPX).addFormDataPart("trainer", "0").addFormDataPart("commute", "0").addFormDataPart("external_id", System.currentTimeMillis() + "").addFormDataPart("file", file.getName(), RequestBody.create(parse, file)).build();
    }

    private ResultCallback<String> e(File file, final String str) {
        return new ResultCallback<String>() { // from class: sjp.2
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(Response<String> response) {
                String str2;
                LogUtil.a("StravaSyncTask", "upload success. code = ", Integer.valueOf(response.getCode()), " json = ", response.getBody());
                if (response.isSuccessful()) {
                    sjp.this.callResult(true);
                    sjp.this.recordUploadBiEvent(str, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_STRAVA);
                    return;
                }
                if (response.getCode() != 401) {
                    sjp.this.callResult(false);
                    str2 = "sync data failed.";
                } else {
                    LogUtil.h("StravaSyncTask", "token invalidation.");
                    sjp.this.tokenInvalidation();
                    str2 = "token invalid";
                }
                sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), str, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_STRAVA, String.valueOf(response.getCode()), str2);
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), str, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_STRAVA, "", "sync data failed.");
                LogUtil.h("StravaSyncTask", "upload failure：", th.getMessage());
                sjp.this.callResult(false);
            }
        };
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public String getTag() {
        return "StravaSyncTask";
    }
}
