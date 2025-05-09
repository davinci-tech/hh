package com.huawei.featureuserprofile;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import defpackage.bqi;
import health.compact.a.LocalBroadcast;

/* loaded from: classes8.dex */
public class UserInfoModifyService extends IntentService {
    public UserInfoModifyService() {
        super("UserInfoModifyService");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        Bundle extras = intent.getExtras();
        if (!"modifyUserData".equals(intent.getAction()) || extras == null) {
            return;
        }
        HiUserInfo hiUserInfo = (HiUserInfo) extras.getParcelable("userInfo");
        String string = extras.getString("uuid");
        if (hiUserInfo == null || TextUtils.isEmpty(string)) {
            return;
        }
        c(hiUserInfo, string);
    }

    private void c(HiUserInfo hiUserInfo, final String str) {
        UserInfomation userInfomation = new UserInfomation(0);
        userInfomation.loadAccountData(hiUserInfo);
        userInfomation.loadAccountExtData(hiUserInfo);
        bqi.c(this).c(userInfomation, new ICloudOperationResult<Boolean>() { // from class: com.huawei.featureuserprofile.UserInfoModifyService.1
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void operationResult(Boolean bool, String str2, boolean z) {
                LogUtil.a("UserInfoModifyService", "modifyUserInfo operationResult is ", Boolean.valueOf(z));
                if (!z) {
                    LogUtil.a("UserInfoModifyService", "modifyUserInfo Fail");
                    Intent intent = new Intent("action_transcation_end");
                    intent.putExtra("uuid", str);
                    intent.putExtra("result", -1);
                    intent.setPackage(BaseApplication.d());
                    UserInfoModifyService.this.sendBroadcast(intent, LocalBroadcast.c);
                    return;
                }
                Intent intent2 = new Intent("action_transcation_end");
                intent2.putExtra("uuid", str);
                intent2.putExtra("result", 0);
                intent2.setPackage(BaseApplication.d());
                UserInfoModifyService.this.sendBroadcast(intent2, LocalBroadcast.c);
            }
        });
    }
}
