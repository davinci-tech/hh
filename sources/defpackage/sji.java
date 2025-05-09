package defpackage;

import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hms.framework.network.restclient.hwhttp.MediaType;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.trans.MultipartBody;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.thirdpartservice.syncdata.SyncTask;
import com.huawei.ui.thirdpartservice.syncdata.constants.RuntasticOauthConstant;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import com.huawei.ui.thirdpartservice.syncdata.net.SyncDataToRuntasticApi;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class sji extends SyncTask {
    private final SyncDataToRuntasticApi d;

    sji(HiHealthData hiHealthData, String str, File file, SyncDataToRuntasticApi syncDataToRuntasticApi) {
        super(hiHealthData, str, file);
        this.d = syncDataToRuntasticApi;
        this.mUploadDomain = jah.c().e("domain_runtastic");
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public void retryTooManyTimes() {
        sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), this.mDescription, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_RUNTASTIC, "", "failed to retry too many times.");
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public void sportDataException() {
        sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), this.mDescription, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_RUNTASTIC, "", "sport data exception.");
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public void doUpload() {
        b(this.mGpxFile, this.mUploadDomain + RuntasticOauthConstant.RUNTASTIC_UPLOAD_PATH, this.mDescription, this.mHiHealthData.getStartTime());
    }

    private void b(File file, String str, final String str2, long j) {
        String accessToken = sjd.d().getAccessToken();
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", SyncDataConstant.SYNC_DATA_HEADER_UA);
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + accessToken);
        MediaType parse = MediaType.parse(FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM);
        this.d.uploadGpxToRuntastic(str, hashMap, new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("description", str2 + " " + nsj.e(j)).addFormDataPart("file", file.getName(), RequestBody.create(parse, file)).build()).enqueue(new ResultCallback<String>() { // from class: sji.3
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(Response<String> response) {
                String str3;
                LogUtil.a("RuntasticSyncTask", "upload success. code = ", Integer.valueOf(response.getCode()), " json = ", response.getBody());
                if (response.isSuccessful()) {
                    sji.this.callResult(true);
                    sji.this.recordUploadBiEvent(str2, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_RUNTASTIC);
                    return;
                }
                if (response.getCode() != 401) {
                    sji.this.callResult(false);
                    str3 = "sync data failed.";
                } else {
                    LogUtil.h("RuntasticSyncTask", "token invalidation.");
                    sji.this.tokenInvalidation();
                    str3 = "token invalid";
                }
                sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), str2, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_RUNTASTIC, String.valueOf(response.getCode()), str3);
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), str2, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_RUNTASTIC, "", "sync data failed.");
                LogUtil.h("RuntasticSyncTask", "upload failure：", th.getMessage());
                sji.this.callResult(false);
            }
        });
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public String getTag() {
        return "RuntasticSyncTask";
    }
}
