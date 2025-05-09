package defpackage;

import android.text.TextUtils;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hms.framework.network.restclient.hwhttp.MediaType;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.ui.thirdpartservice.syncdata.SyncTask;
import com.huawei.ui.thirdpartservice.syncdata.constants.KomootOauthConstant;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import com.huawei.ui.thirdpartservice.syncdata.net.SyncDataToKomootApi;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes7.dex */
public class siu extends SyncTask {
    private final SyncDataToKomootApi b;

    siu(HiHealthData hiHealthData, String str, File file, SyncDataToKomootApi syncDataToKomootApi) {
        super(hiHealthData, str, file);
        this.b = syncDataToKomootApi;
        this.mUploadDomain = jah.c().e("domain_komoot");
    }

    public siu(SyncDataToKomootApi syncDataToKomootApi) {
        super(new HiHealthData(), "", null);
        this.b = syncDataToKomootApi;
        this.mUploadDomain = jah.c().e("domain_komoot");
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public void retryTooManyTimes() {
        sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), this.mDescription, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_KOMMOT, "", "failed to retry too many times.");
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public void sportDataException() {
        sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), this.mDescription, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_KOMMOT, "", "sport data exception.");
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public void doUpload() {
        e(this.mGpxFile, String.format(Locale.ENGLISH, this.mUploadDomain + KomootOauthConstant.KOMOOT_UPLOAD_PATH, this.mDescription, this.mDescription + " " + nsj.e(this.mHiHealthData.getStartTime())), this.mDescription);
    }

    private void e(File file, String str, final String str2) {
        String accessToken = sir.c().getAccessToken();
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", SyncDataConstant.SYNC_DATA_HEADER_UA);
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + accessToken);
        this.b.uploadGpxToKomoot(str, hashMap, RequestBody.create(MediaType.parse(FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM), file)).enqueue(new ResultCallback<String>() { // from class: siu.1
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(Response<String> response) {
                String str3;
                LogUtil.a("KomootSyncTask", "upload success. code = ", Integer.valueOf(response.getCode()), " json = ", response.getBody());
                if (response.isSuccessful()) {
                    siu.this.callResult(true);
                    siu.this.recordUploadBiEvent(str2, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_KOMMOT);
                    return;
                }
                if (response.getCode() != 401) {
                    siu.this.callResult(false);
                    str3 = "sync data failed.";
                } else {
                    LogUtil.h("KomootSyncTask", "token invalidation.");
                    siu.this.tokenInvalidation();
                    str3 = "token invalid";
                }
                sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), str2, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_KOMMOT, String.valueOf(response.getCode()), str3);
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), str2, SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_KOMMOT, "", "sync data failed.");
                LogUtil.h("KomootSyncTask", "upload failure：", th.getMessage());
                siu.this.callResult(false);
            }
        });
    }

    public void b(String str, ResultCallback<String> resultCallback) {
        String format = String.format(Locale.ENGLISH, this.mUploadDomain + KomootOauthConstant.KOMOOT_ROAD, str);
        String accessToken = sir.c().getAccessToken();
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("User-Agent", SyncDataConstant.SYNC_DATA_HEADER_UA);
        hashMap2.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + accessToken);
        this.b.getRoad(format, hashMap2, hashMap).enqueue(resultCallback);
    }

    public void d(String str, String str2, int i, ResultCallback<String> resultCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put(BleConstants.LIMIT, HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY);
        hashMap.put("page", String.valueOf(i));
        hashMap.put("only_unlocked", "true");
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("sport_types", str2);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("User-Agent", SyncDataConstant.SYNC_DATA_HEADER_UA);
        hashMap2.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + sir.c().getAccessToken());
        this.b.getRoadList(String.format(Locale.ENGLISH, this.mUploadDomain + KomootOauthConstant.KOMOOT_ROAD_LIST, str), hashMap2, hashMap).enqueue(resultCallback);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask
    public String getTag() {
        return "KomootSyncTask";
    }
}
