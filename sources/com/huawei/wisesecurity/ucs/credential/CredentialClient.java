package com.huawei.wisesecurity.ucs.credential;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hidatamanager.util.Const;
import com.huawei.hms.network.embedded.y;
import com.huawei.wisesecurity.kfs.ha.message.BaseReportMsgBuilder;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotNull;
import com.huawei.wisesecurity.ucs.common.log.ILogUcs;
import com.huawei.wisesecurity.ucs.common.report.ReportOption;
import com.huawei.wisesecurity.ucs.credential.nativelib.UcsLib;
import com.huawei.wisesecurity.ucs.credential.outer.GrsCapability;
import com.huawei.wisesecurity.ucs.credential.outer.HACapability;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability;
import com.huawei.wisesecurity.ucs.credential.outer.Selector;
import com.huawei.wisesecurity.ucs_credential.o;
import defpackage.ttr;
import defpackage.tue;
import defpackage.tvz;
import defpackage.twb;
import defpackage.twc;
import defpackage.twf;
import defpackage.twl;
import defpackage.two;
import defpackage.twt;
import defpackage.twv;
import java.util.UUID;

/* loaded from: classes7.dex */
public class CredentialClient {
    private static final String TAG = "CredentialClient";
    private String appId;
    private Context context;
    private twt credentialManager;
    private HACapability haCapability;

    public Credential applyCredential(String str, String str2) throws twc {
        checkParams(str);
        checkThread();
        twl createReportMsgBuilder = createReportMsgBuilder(str, str2);
        twb.a(TAG, "start apply credential for {0} , appId is {1},", str, this.appId);
        try {
            try {
                Credential b = this.credentialManager.b(1, str, str2);
                twb.a(TAG, "finish apply credential for {0} , appId is {1}", str, this.appId);
                createReportMsgBuilder.e(this.credentialManager.h).setStatusCode(0);
                return b;
            } catch (twc e) {
                twb.e(TAG, "get Credential get UcsException : " + e.getMessage(), new Object[0]);
                createReportMsgBuilder.setStatusCode((int) e.b()).setErrorMsg(e.getMessage());
                throw e;
            } catch (Exception e2) {
                String str3 = "get Credential get exception : " + e2.getMessage();
                twb.e(TAG, str3, new Object[0]);
                createReportMsgBuilder.setStatusCode(2001).setErrorMsg(str3);
                throw new twc(2001L, str3);
            }
        } finally {
            reportLogs(createReportMsgBuilder);
        }
    }

    public Credential applyCredentialByEC(String str, String str2) throws twc {
        checkParams(str);
        checkThread();
        twl createReportMsgBuilder = createReportMsgBuilder(str, str2);
        twb.a(TAG, "start apply credential by EC for {0} , appId is {1}", str, this.appId);
        try {
            try {
                Credential b = this.credentialManager.b(2, str, str2);
                twb.a(TAG, "finish apply credential by EC for {0} , appId is {1}", str, this.appId);
                createReportMsgBuilder.e(this.credentialManager.h).setStatusCode(0);
                return b;
            } catch (twc e) {
                twb.e(TAG, "get Credential by EC get UcsException : " + e.getMessage(), new Object[0]);
                createReportMsgBuilder.setStatusCode((int) e.b()).setErrorMsg(e.getMessage());
                throw e;
            } catch (Exception e2) {
                String str3 = "get Credential by EC get exception : " + e2.getMessage();
                twb.e(TAG, str3, new Object[0]);
                createReportMsgBuilder.setStatusCode(2001).setErrorMsg(str3);
                throw new twc(2001L, str3);
            }
        } finally {
            reportLogs(createReportMsgBuilder);
        }
    }

    public void reportLogs(BaseReportMsgBuilder baseReportMsgBuilder) {
        baseReportMsgBuilder.setAppId(this.appId).setPackageName(this.context.getPackageName()).setVersion("1.0.4.315");
        Context context = this.context;
        try {
            this.haCapability.onEvent(context, baseReportMsgBuilder.getEventId(), baseReportMsgBuilder.setCostTime());
        } catch (Throwable th) {
            StringBuilder e = twf.e("onEvent get exception : ");
            e.append(th.getMessage());
            twb.a("ReportUtil", e.toString(), new Object[0]);
        }
    }

    public static class Builder {
        private String appId;

        @KfsNotNull
        private Context context;
        private GrsCapability grsCapability;
        private HACapability haCapability;
        private NetworkCapability networkCapability;
        private String serCountry;
        private int networkTimeOut = y.c;
        private int networkRetryTime = 2;
        private ReportOption reportOption = ReportOption.REPORT_NORMAL;

        public Builder serCountry(String str) {
            this.serCountry = str;
            return this;
        }

        public Builder reportOption(ReportOption reportOption) {
            this.reportOption = reportOption;
            return this;
        }

        public Builder networkTimeOut(int i) {
            this.networkTimeOut = i;
            return this;
        }

        public Builder networkRetryTime(int i) {
            this.networkRetryTime = i;
            return this;
        }

        public Builder networkCapability(NetworkCapability networkCapability) {
            this.networkCapability = networkCapability;
            return this;
        }

        public Builder logInstance(ILogUcs iLogUcs) {
            twb.d(iLogUcs);
            return this;
        }

        public Builder haCapability(HACapability hACapability) {
            this.haCapability = hACapability;
            return this;
        }

        public Builder grsCapability(GrsCapability grsCapability) {
            this.grsCapability = grsCapability;
            return this;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public CredentialClient build() throws twc {
            try {
                String str = this.appId;
                if (str != null && str.length() > 30) {
                    throw new tvz("appId length is too long");
                }
                tue.d(this);
                o selectGrsCapability = Selector.selectGrsCapability(this.grsCapability, this.context, this.serCountry);
                return new CredentialClient(this.context, this.appId, selectGrsCapability, Selector.selectNetWorkCapability(this.networkCapability, this.context, this.networkTimeOut, this.networkRetryTime), Selector.selectHACapability(this.haCapability, selectGrsCapability, this.reportOption));
            } catch (ttr e) {
                StringBuilder e2 = twf.e("CredentialClient check param error : ");
                e2.append(e.getMessage());
                throw new tvz(e2.toString());
            } catch (twc e3) {
                twb.e(CredentialClient.TAG, "CredentialClient build get UCS exception : errorCode : {0} errorMsg : {1}", Long.valueOf(e3.b()), e3.getMessage());
                throw e3;
            } catch (Throwable th) {
                StringBuilder e4 = twf.e("CredentialClient build get exception : ");
                e4.append(th.getMessage());
                String sb = e4.toString();
                throw two.e(CredentialClient.TAG, sb, new Object[0], 2001L, sb);
            }
        }

        public Builder appId(String str) {
            this.appId = str;
            return this;
        }
    }

    public Credential genCredentialFromString(String str) throws twc {
        twv twvVar = (twv) new twv().d().setApiName("appAuth.credentialFromString").setCallTime();
        try {
            try {
                Credential fromString = Credential.fromString(this.context, str, twvVar);
                twvVar.setStatusCode(0);
                return fromString;
            } catch (twc e) {
                twb.e(TAG, "credential from string get UcsException : {0}", e.getMessage());
                twvVar.setStatusCode((int) e.b()).setErrorMsg(e.getMessage());
                throw e;
            } catch (Exception e2) {
                String str2 = "credential from string get exception : " + e2.getMessage();
                twb.e(TAG, "{0}", str2);
                twvVar.setStatusCode(2001).setErrorMsg(str2);
                throw new twc(2001L, str2);
            }
        } finally {
            reportLogs(twvVar);
        }
    }

    public Credential applyCredentialByEC(String str) throws twc {
        return applyCredentialByEC(str, UUID.randomUUID().toString());
    }

    public Credential applyCredential(String str) throws twc {
        return applyCredential(str, UUID.randomUUID().toString());
    }

    private twl createReportMsgBuilder(String str, String str2) {
        return (twl) new twl().b().a(str).setTransId(str2).setApiName("appAuth.applyCredential").setCallTime();
    }

    private void checkThread() throws twc {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new twc(1015L, "can not apply in main looper...");
        }
    }

    private void checkParams(String str) throws twc {
        if (TextUtils.isEmpty(str)) {
            throw new twc(Const.RawDataType.HEALTH_EVENT_RECORD, "serviceName illegal...");
        }
    }

    private CredentialClient(Context context, String str, o oVar, NetworkCapability networkCapability, HACapability hACapability) throws twc {
        this.context = context;
        this.appId = str;
        this.haCapability = hACapability;
        this.credentialManager = new twt(this, context, networkCapability, oVar, str);
        UcsLib.checkNativeLibrary();
    }
}
