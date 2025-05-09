package com.huawei.hms.support.hwid.service;

import android.content.Context;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.support.hwid.bean.AckQrLoginReq;
import com.huawei.hms.support.hwid.bean.CheckPasswordByUserIdReq;
import com.huawei.hms.support.hwid.bean.GetRealNameInfoReq;
import com.huawei.hms.support.hwid.bean.LoginInfoReq;
import com.huawei.hms.support.hwid.bean.SignInByQrReq;
import com.huawei.hms.support.hwid.bean.StartQrAuthReq;
import com.huawei.hms.support.hwid.bean.StartQrLoginReq;
import com.huawei.hms.support.hwid.common.cloudservice.CloudRequestHandler;
import com.huawei.hms.support.hwid.result.SignInQrInfo;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public interface HuaweiIdAdvancedService {
    Task<String> ackQrLogin(AckQrLoginReq ackQrLoginReq);

    void checkPasswordByUserId(Context context, CheckPasswordByUserIdReq checkPasswordByUserIdReq, CloudRequestHandler cloudRequestHandler);

    Task<String> getAccountInfo(List<String> list);

    Task<String> getDeviceInfo();

    Task<String> getRealNameInfo();

    Task<String> getRealNameInfo(GetRealNameInfoReq getRealNameInfoReq);

    Task<String> getVerifyTokenByQrcode(String str);

    Task<String> hasAccountChanged(String str, String str2);

    Task<Void> login(LoginInfoReq loginInfoReq);

    Task<Void> logout();

    Task<String> registerFilterForLogin(String str, HashMap<String, String> hashMap, List<String> list, int i, String str2);

    Task<Void> signInByQrCode(SignInByQrReq signInByQrReq);

    Task<String> startQrAuth(StartQrAuthReq startQrAuthReq);

    Task<SignInQrInfo> startQrLogin(StartQrLoginReq startQrLoginReq);
}
