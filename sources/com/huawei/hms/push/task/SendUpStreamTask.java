package com.huawei.hms.push.task;

import android.content.Intent;
import android.os.Bundle;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.task.PushClient;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.push.r;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.openalliance.ad.constant.VastAttribute;

/* loaded from: classes9.dex */
public class SendUpStreamTask extends TaskApiCall<PushClient, BaseVoidTask> {

    /* renamed from: a, reason: collision with root package name */
    private String f5688a;
    private String b;

    public SendUpStreamTask(String str, String str2, String str3, String str4, String str5) {
        super(str, str2, str3);
        this.f5688a = str4;
        this.b = str5;
    }

    private void a(PushClient pushClient, ResponseErrorCode responseErrorCode) {
        HMSLog.i("SendUpStreamTask", "receive upstream, msgId :" + this.b + " , packageName = " + this.f5688a + " , errorCode = " + responseErrorCode.getErrorCode());
        Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
        intent.setPackage(this.f5688a);
        Bundle bundle = new Bundle();
        bundle.putString("message_id", this.b);
        bundle.putInt(VastAttribute.ERROR, responseErrorCode.getErrorCode());
        if (ErrorEnum.SUCCESS.getInternalCode() == responseErrorCode.getErrorCode()) {
            bundle.putString(RemoteMessageConst.MSGTYPE, "sent_message");
        } else {
            bundle.putString(RemoteMessageConst.MSGTYPE, "send_error");
        }
        if (new r().a(pushClient.getContext(), bundle, intent)) {
            HMSLog.i("SendUpStreamTask", "receive upstream, start service success");
            PushBiUtil.reportExit(pushClient.getContext(), getUri(), responseErrorCode);
        } else {
            HMSLog.w("SendUpStreamTask", "receive upstream, start service failed");
            PushBiUtil.reportExit(pushClient.getContext(), getUri(), responseErrorCode.getTransactionId(), ErrorEnum.ERROR_BIND_SERVICE_SELF_MAPPING);
        }
    }

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return 40003000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public void doExecute(PushClient pushClient, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<BaseVoidTask> taskCompletionSource) {
        if (responseErrorCode.getErrorCode() == 0) {
            HMSLog.i("SendUpStreamTask", "send up stream task,Operate succeed");
            taskCompletionSource.setResult(null);
        } else {
            HMSLog.e("SendUpStreamTask", "send up stream task,Operate failed with ret=" + responseErrorCode.getErrorCode());
            ErrorEnum fromCode = ErrorEnum.fromCode(responseErrorCode.getErrorCode());
            if (fromCode != ErrorEnum.ERROR_UNKNOWN) {
                taskCompletionSource.setException(fromCode.toApiException());
            } else {
                taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
            }
        }
        a(pushClient, responseErrorCode);
    }
}
