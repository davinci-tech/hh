package com.huawei.hms.hwid;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.ResolvableApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.account.result.AccountIcon;
import com.huawei.hms.support.account.result.GetChannelResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.tencent.open.SocialConstants;
import org.json.JSONException;

/* loaded from: classes9.dex */
public class i extends TaskApiCall<c, AccountIcon> {

    /* renamed from: a, reason: collision with root package name */
    private Context f4648a;

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        return 10;
    }

    public i(String str, String str2, String str3, Context context) {
        super(str, str2, str3);
        this.f4648a = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void doExecute(c cVar, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<AccountIcon> taskCompletionSource) {
        if (responseErrorCode == null) {
            as.b("[AccountSDK]AccountGetChannelTaskApiCall", "response is null.", true);
            taskCompletionSource.setException(new ApiException(new Status(2003, "response is null.")));
            return;
        }
        int errorCode = responseErrorCode.getErrorCode();
        if (errorCode != 0 && CommonCode.Resolution.HAS_RESOLUTION.equals(responseErrorCode.getResolution())) {
            as.b("[AccountSDK]AccountGetChannelTaskApiCall", "apk version is low or is not exist.", true);
            Status status = new Status(errorCode, responseErrorCode.getErrorReason());
            ao.a(responseErrorCode, status);
            taskCompletionSource.setException(new ResolvableApiException(status));
            return;
        }
        as.b("[AccountSDK]AccountGetChannelTaskApiCall", "ResponseErrorCode.status:" + responseErrorCode.getErrorCode(), true);
        AccountIcon accountIcon = new AccountIcon();
        if (!TextUtils.isEmpty(str)) {
            if ("{}".equals(str)) {
                as.b("[AccountSDK]AccountGetChannelTaskApiCall", "getChannel complete, body is null", true);
                taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
                return;
            }
            try {
                GetChannelResult fromJson = new GetChannelResult().fromJson(str);
                if (fromJson.isSuccess()) {
                    as.b("[AccountSDK]AccountGetChannelTaskApiCall", "getChannel success", true);
                    accountIcon.setDescription(fromJson.getDescription());
                    accountIcon.setIcon(a(fromJson.getIcon()));
                    taskCompletionSource.setResult(accountIcon);
                    long a2 = ao.a();
                    SharedPreferences.Editor edit = this.f4648a.getSharedPreferences("ACCOUNT_CHANNEL_CACHE", 0).edit();
                    edit.putLong("cache_time", a2);
                    edit.putString(SocialConstants.PARAM_APP_DESC, fromJson.getDescription());
                    edit.putString("icon", fromJson.getIcon());
                    edit.apply();
                } else {
                    as.b("[AccountSDK]AccountGetChannelTaskApiCall", "getChannel failed", true);
                    taskCompletionSource.setException(new ApiException(fromJson.getStatus()));
                }
                return;
            } catch (JSONException unused) {
                as.c("[AccountSDK]AccountGetChannelTaskApiCall", "getChannel complete, but parser json exception", true);
                taskCompletionSource.setException(new ApiException(new Status(errorCode, responseErrorCode.getErrorReason())));
                return;
            }
        }
        as.b("[AccountSDK]AccountGetChannelTaskApiCall", "getChannel complete, response is null, failed", true);
        taskCompletionSource.setException(new ApiException(new Status(errorCode, responseErrorCode.getErrorReason())));
    }

    private Bitmap a(String str) {
        try {
            byte[] decode = Base64.decode(str, 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            as.d("[AccountSDK]AccountGetChannelTaskApiCall", "stringToBitmap Exception is " + e.getClass().getSimpleName(), true);
            return null;
        } catch (OutOfMemoryError unused) {
            as.d("[AccountSDK]AccountGetChannelTaskApiCall", "out of memory error ", true);
            return null;
        }
    }
}
