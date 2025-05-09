package defpackage;

import android.text.TextUtils;
import com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ipy;
import health.compact.a.Base64;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes4.dex */
public class iru {
    public boolean b(String str, String str2, String str3) {
        LogUtil.a("ThirdPartySignatureAuthUtil", "enter isValidRedirectUrl", " appId = ", str, " redirectUrl = ", str3);
        boolean z = e(str, str2, str3) && a(str3);
        ReleaseLogUtil.e("HiH_ThirdPartySignatureAuthUtil", "isValidRedirectUrl result: ", Boolean.valueOf(z));
        return z;
    }

    public boolean e(String str, String str2, String str3) {
        ipy.c e = irt.c().e(str3);
        if (e != null && c(e.getAppId(), str) && d(e.getSignature(), str2)) {
            ReleaseLogUtil.e("HiH_ThirdPartySignatureAuthUtil", "check appId and signature is right by cache");
            return true;
        }
        return a(str, str3, str2);
    }

    private boolean a(final String str, final String str2, final String str3) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("HiH_ThirdPartySignatureAuthUtil", "App id is empty");
            return false;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = {false};
        ipb.d().c("ThirdPartySignatureAuthUtil", new ipe(str).getRequestParamsBuilder().e(true).b(new OnRequestCallBack<ipv>() { // from class: iru.1
            @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ipv ipvVar) {
                ReleaseLogUtil.e("HiH_ThirdPartySignatureAuthUtil", "getAppInfoByAppId success");
                if (ipvVar == null || ipvVar.getAppInfos() == null || ipvVar.getAppInfos().size() == 0) {
                    ReleaseLogUtil.c("HiH_ThirdPartySignatureAuthUtil", "AppInfo is empty, appId = ", str);
                    countDownLatch.countDown();
                    return;
                }
                ipy.c redirectInfo = ipvVar.getAppInfos().get(0).getRedirectInfo();
                if (redirectInfo != null) {
                    boolean d = iru.this.d(iru.this.a(str, redirectInfo.getSecret()), str3);
                    ReleaseLogUtil.e("HiH_ThirdPartySignatureAuthUtil", "Signature comparison result: ", Boolean.valueOf(d));
                    if (d) {
                        zArr[0] = true;
                        redirectInfo.setSignature(str3);
                        redirectInfo.setAppId(str);
                        irt.c().c(str2, redirectInfo);
                    }
                } else {
                    ReleaseLogUtil.c("HiH_ThirdPartySignatureAuthUtil", "RedirectInfo is null");
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
            public void onFail(int i, Throwable th) {
                ReleaseLogUtil.c("HiH_ThirdPartySignatureAuthUtil", "getAppInfoByAppId errCode: ", Integer.valueOf(i), ", msg = ", th.getMessage());
                countDownLatch.countDown();
            }
        }).a());
        try {
            ReleaseLogUtil.e("HiH_ThirdPartySignatureAuthUtil", "requestRedirectInfo on time = ", Boolean.valueOf(countDownLatch.await(2000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e) {
            ReleaseLogUtil.c("HiH_ThirdPartySignatureAuthUtil", "requestRedirectInfo InterruptedException e:", e.getMessage());
        }
        return zArr[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2) {
        return b(new StringBuffer().append(str).append("_").append(str2).toString(), str2);
    }

    private String b(String str, String str2) {
        try {
            return Base64.a(a(Base64.e(str2), str.getBytes("utf-8")));
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            ReleaseLogUtil.c("HiH_ThirdPartySignatureAuthUtil", "getHmacSha256 error, msg = ", e.getMessage());
            return "";
        }
    }

    private byte[] a(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, InvalidKeyException {
        if (bArr == null || bArr2 == null) {
            ReleaseLogUtil.c("HiH_ThirdPartySignatureAuthUtil", "input param is invalid.");
            return new byte[0];
        }
        ReleaseLogUtil.c("HiH_ThirdPartySignatureAuthUtil", "hmacSha256 start");
        byte[] bArr3 = (byte[]) bArr.clone();
        byte[] bArr4 = (byte[]) bArr2.clone();
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr3, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(bArr4);
    }

    private boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.c("HiH_ThirdPartySignatureAuthUtil", "App id is null");
            return false;
        }
        boolean equals = str2.equals(str);
        ReleaseLogUtil.e("HiH_ThirdPartySignatureAuthUtil", "checkAppId result = ", Boolean.valueOf(equals));
        return equals;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str, String str2) {
        boolean z = !TextUtils.isEmpty(str) && str.equals(str2);
        ReleaseLogUtil.e("HiH_ThirdPartySignatureAuthUtil", "checkSignature result = ", Boolean.valueOf(z));
        return z;
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("HiH_ThirdPartySignatureAuthUtil", "redirectUrlFromUri is null");
            return false;
        }
        ipy.c e = irt.c().e(str);
        if (e == null) {
            ReleaseLogUtil.c("HiH_ThirdPartySignatureAuthUtil", "RedirectUrl cannot be confirmed");
            return false;
        }
        String str2 = str.split("\\?")[0];
        Iterator<String> it = e.getUrlSchemes().iterator();
        while (it.hasNext()) {
            if (str2.equals(it.next())) {
                return true;
            }
        }
        return false;
    }
}
