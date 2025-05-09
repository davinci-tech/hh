package com.huawei.wear.oversea.impl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.model.MyLocationStyle;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.wear.oversea.restclient.BaseRestClient;
import defpackage.stg;
import defpackage.stj;
import defpackage.stk;
import defpackage.stl;
import defpackage.stm;
import defpackage.stq;
import defpackage.sug;
import defpackage.suk;
import defpackage.sul;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public abstract class HttpConnTask<Result, RequestParams> extends BaseRestClient<Result, RequestParams> {
    private static final int SERVER_OVERLOAD_ERRORCODE = 503;
    private static final String TAG = "HttpConnTask";
    String commander;
    private boolean isSignatureError;
    protected Context mContext;
    private String receiveMsg;
    private String sendMsg;
    String srcTranID;
    private String standardCode;
    private stg timeExecuteBuilder;

    protected abstract String prepareRequestStr(RequestParams requestparams);

    protected abstract Result readErrorResponse(int i, String str);

    protected abstract Result readSuccessResponse(int i, String str, JSONObject jSONObject);

    public HttpConnTask(Context context, String str) {
        super(context, str, null, null);
        this.receiveMsg = "Recv_http_msg";
        this.sendMsg = "Send_http_msg";
        this.srcTranID = "";
        this.commander = "";
        this.mContext = context;
        this.mUrl = str;
    }

    public Result processTask(RequestParams requestparams) {
        return processTask(requestparams, false);
    }

    public Result processTask(RequestParams requestparams, boolean z) {
        String str;
        Result readErrorResponse;
        Result readErrorResponse2;
        String str2 = TAG;
        stq.b(str2, "processTask isRetry=" + z);
        this.isSignatureError = false;
        this.timeExecuteBuilder = new stg();
        this.standardCode = "00";
        Result preCheckNetWork = preCheckNetWork();
        if (preCheckNetWork != null) {
            return preCheckNetWork;
        }
        this.timeExecuteBuilder.a(" |prepareRequestStr:" + System.currentTimeMillis());
        if (requestparams != null) {
            str = prepareRequestStr(requestparams);
            if (TextUtils.isEmpty(str) && this.mRequestType == 2) {
                stq.b(str2, "processTask, invalid request params.");
                this.standardCode = "11";
                return readErrorResponse(1, "RESPONSE_MESSAGE_PARAMS_ERROR");
            }
        } else {
            str = null;
        }
        try {
            preGetWalletReportStr(str);
            stq.d(str2, "http request start, start connect", false);
            this.timeExecuteBuilder.a(" |before open https connection:" + System.currentTimeMillis());
            stq.d(str2, "processTask request string : " + str, false);
            try {
                readErrorResponse2 = doRestClientProcessTask(this.timeExecuteBuilder, str);
            } catch (IllegalArgumentException e) {
                stq.e(TAG, "Request Http IllegalArgumentException", true);
                this.standardCode = "18";
                readErrorResponse = readErrorResponse(-13, "IllegalArgumentException.");
                onReportEvent("Send request IllegalArgumentException, RESPONSE_MESSAGE_CONNECTION_FAILED,uEx error ", "RESPONSE_MESSAGE_CONNECTION_FAILED_NO_SUCH_ALGORITHM_EXCEPTION", this.commander, this.sendMsg, this.srcTranID, !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : "");
                readErrorResponse2 = readErrorResponse;
            } catch (MalformedURLException e2) {
                stq.e(TAG, "processTask url invalid.", false);
                this.standardCode = "11";
                StringBuilder sb = new StringBuilder("RESPONSE_MESSAGE_PARAMS_ERROR_MALFORMED_URL_EXCEPTION,urlEx = ");
                sb.append(!TextUtils.isEmpty(e2.getMessage()) ? e2.getMessage() : "");
                readErrorResponse = readErrorResponse(1, sb.toString());
                onReportEvent("Send request failed MalformedURLException, RESPONSE_MESSAGE_PARAMS_ERROR_MALFORMED_URL_EXCEPTION,urlEx error ", String.valueOf(1), this.commander, this.sendMsg, this.srcTranID, !TextUtils.isEmpty(e2.getMessage()) ? e2.getMessage() : "");
                readErrorResponse2 = readErrorResponse;
            } catch (SocketTimeoutException e3) {
                stq.e(TAG, "Request Http RestClient timeout.", e3, true);
                onReportEvent("Send request timeout, RESPONSE_CONNECTION_TIMEOUT_MESSAGE,tEx error ", String.valueOf(-11), this.commander, this.sendMsg, this.srcTranID, !TextUtils.isEmpty(e3.getMessage()) ? e3.getMessage() : "");
                this.standardCode = "16";
                readErrorResponse2 = readErrorResponse(-11, "RESPONSE_CONNECTION_TIMEOUT_MESSAGESocketTimeoutException.");
            } catch (UnknownHostException e4) {
                stq.e(TAG, "Request Http RestClient UnknownHost.", e4, true);
                this.standardCode = "17";
                readErrorResponse = readErrorResponse(-12, "UnknownHostException.");
                onReportEvent("Send request UnknownHost, RESPONSE_CONNECTION_UNKNOWNHOST_MESSAGE,uEx error ", String.valueOf(-12), this.commander, this.sendMsg, this.srcTranID, !TextUtils.isEmpty(e4.getMessage()) ? e4.getMessage() : "");
                readErrorResponse2 = readErrorResponse;
            } catch (IOException e5) {
                stq.e(TAG, "processTask IOException : ", e5, true);
                this.standardCode = "19";
                StringBuilder sb2 = new StringBuilder("RESPONSE_CONNECTION_FAILED_MESSAGE,ioEx = ");
                sb2.append(TextUtils.isEmpty(e5.getMessage()) ? "" : e5.getMessage());
                readErrorResponse2 = readErrorResponse(-2, sb2.toString());
                onReportEvent("Send request failed, IOException  RESPONSE_CONNECTION_FAILED_MESSAGE,ioEx error ", String.valueOf(-2), this.commander, this.sendMsg, this.srcTranID, "IOException in processTask");
            } catch (stj unused) {
                onReportEvent("submit is null, -10040,ioEx error ", "RESTCLIENT_RESPONSE_SUBMIT_IS_NULL", this.commander, this.sendMsg, this.srcTranID, "");
                stq.e(TAG, "submit is null", false);
                this.standardCode = "13";
                return readErrorResponse(-4, "submit is null");
            } catch (stl unused2) {
                stq.e(TAG, "restClient is null : ", false);
                onReportEvent("restClient is null, -10042", "REQUEST_REST_CLIENT_IS_NULL", this.commander, this.sendMsg, this.srcTranID, "");
                this.standardCode = "13";
                return readErrorResponse(-10042, "restClient is null.");
            } catch (stm unused3) {
                onReportEvent("submit is null, -10041,ioEx error ", "RESPONSE_REST_CLIENT_RESPONSE_NULL", this.commander, this.sendMsg, this.srcTranID, "");
                this.standardCode = "14";
                return readErrorResponse(-4, "RESPONSE_MESSAGE_SERVER_OVERLOAD_ERROR");
            } catch (Exception e6) {
                stq.e(TAG, "processTask IOException : ", true);
                this.standardCode = "19";
                StringBuilder sb3 = new StringBuilder("RESPONSE_CONNECTION_FAILED_MESSAGE,Exception = ");
                sb3.append(!TextUtils.isEmpty(e6.getMessage()) ? e6.getMessage() : "");
                readErrorResponse = readErrorResponse(-2, sb3.toString());
                onReportEvent("Send request failed, Exception  RESPONSE_CONNECTION_FAILED_MESSAGE,Exception error ", String.valueOf(-2), this.commander, this.sendMsg, this.srcTranID, !TextUtils.isEmpty(e6.getMessage()) ? e6.getMessage() : "");
                readErrorResponse2 = readErrorResponse;
            }
            if (!this.isSignatureError || z) {
                return readErrorResponse2;
            }
            stq.b(TAG, "commander:" + this.commander + " try again");
            return processTask(requestparams, true);
        } catch (stk e7) {
            onReportEvent("TokenErrorException, " + e7.d() + " -7000", "SERVICE_TOKEN_INVALID", this.commander, this.sendMsg, this.srcTranID, "");
            this.standardCode = "12";
            return readErrorResponse(-7000, "SERVICE_TOKEN_INVALID");
        }
    }

    private Result preCheckNetWork() {
        if (NetworkUtil.isNetworkAvailable(this.mContext)) {
            return null;
        }
        stq.e(TAG, "processTask, no network.", false);
        this.standardCode = "15";
        return readErrorResponse(-1, "RESPONSE_MESSAGE_NO_NETWORK_FAILED");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0079, code lost:
    
        if ((r0 instanceof java.lang.Integer) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0081, code lost:
    
        if (((java.lang.Integer) r0).intValue() == 1048832) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0084, code lost:
    
        defpackage.stq.c(com.huawei.wear.oversea.impl.HttpConnTask.TAG, "not login,accessTokenValue=null", false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0092, code lost:
    
        throw new defpackage.stk("AccessToken");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void preGetWalletReportStr(java.lang.String r11) throws defpackage.stk {
        /*
            r10 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r11)
            r1 = 0
            if (r0 == 0) goto Lf
            java.lang.String r11 = com.huawei.wear.oversea.impl.HttpConnTask.TAG
            java.lang.String r0 = "preGetWalletReportStr requestStr is null"
            defpackage.stq.c(r11, r0, r1)
            return
        Lf:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L93
            r0.<init>(r11)     // Catch: org.json.JSONException -> L93
            java.lang.String r11 = "data"
            java.lang.String r11 = defpackage.swd.c(r0, r11)     // Catch: org.json.JSONException -> L93
            boolean r0 = android.text.TextUtils.isEmpty(r11)     // Catch: org.json.JSONException -> L93
            if (r0 == 0) goto L21
            return
        L21:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L93
            r0.<init>(r11)     // Catch: org.json.JSONException -> L93
            java.lang.String r11 = "header"
            java.lang.String r11 = defpackage.swd.c(r0, r11)     // Catch: org.json.JSONException -> L93
            boolean r0 = android.text.TextUtils.isEmpty(r11)     // Catch: org.json.JSONException -> L93
            if (r0 == 0) goto L33
            return
        L33:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L93
            r0.<init>(r11)     // Catch: org.json.JSONException -> L93
            java.lang.String r11 = "srcTranID"
            java.lang.String r11 = defpackage.swd.c(r0, r11)     // Catch: org.json.JSONException -> L93
            r10.srcTranID = r11     // Catch: org.json.JSONException -> L93
            java.lang.String r11 = "commander"
            java.lang.String r11 = defpackage.swd.c(r0, r11)     // Catch: org.json.JSONException -> L93
            r10.commander = r11     // Catch: org.json.JSONException -> L93
            java.lang.String r11 = "serviceTokenAuth"
            java.lang.Object r11 = r0.opt(r11)     // Catch: org.json.JSONException -> L93
            java.lang.String r2 = "accessTokenAuth"
            java.lang.Object r0 = r0.opt(r2)     // Catch: org.json.JSONException -> L93
            r2 = 1048832(0x100100, float:1.469727E-39)
            if (r11 == 0) goto L75
            boolean r3 = r11 instanceof java.lang.Integer     // Catch: org.json.JSONException -> L93
            if (r3 == 0) goto L75
            java.lang.Integer r11 = (java.lang.Integer) r11     // Catch: org.json.JSONException -> L93
            int r11 = r11.intValue()     // Catch: org.json.JSONException -> L93
            if (r11 == r2) goto L66
            goto L75
        L66:
            java.lang.String r11 = com.huawei.wear.oversea.impl.HttpConnTask.TAG     // Catch: org.json.JSONException -> L93
            java.lang.String r0 = "not login,serviceTokenValue=null"
            defpackage.stq.c(r11, r0, r1)     // Catch: org.json.JSONException -> L93
            stk r11 = new stk     // Catch: org.json.JSONException -> L93
            java.lang.String r0 = "ServiceToken"
            r11.<init>(r0)     // Catch: org.json.JSONException -> L93
            throw r11     // Catch: org.json.JSONException -> L93
        L75:
            if (r0 == 0) goto Lad
            boolean r11 = r0 instanceof java.lang.Integer     // Catch: org.json.JSONException -> L93
            if (r11 == 0) goto Lad
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch: org.json.JSONException -> L93
            int r11 = r0.intValue()     // Catch: org.json.JSONException -> L93
            if (r11 == r2) goto L84
            goto Lad
        L84:
            java.lang.String r11 = com.huawei.wear.oversea.impl.HttpConnTask.TAG     // Catch: org.json.JSONException -> L93
            java.lang.String r0 = "not login,accessTokenValue=null"
            defpackage.stq.c(r11, r0, r1)     // Catch: org.json.JSONException -> L93
            stk r11 = new stk     // Catch: org.json.JSONException -> L93
            java.lang.String r0 = "AccessToken"
            r11.<init>(r0)     // Catch: org.json.JSONException -> L93
            throw r11     // Catch: org.json.JSONException -> L93
        L93:
            r11 = move-exception
            java.lang.String r0 = com.huawei.wear.oversea.impl.HttpConnTask.TAG
            java.lang.String r2 = "Something wrong when get srcTranID and commander"
            defpackage.stq.e(r0, r2, r1)
            java.lang.String r4 = "Something wrong when get srcTranID"
            java.lang.String r5 = "-1"
            java.lang.String r6 = r10.commander
            java.lang.String r7 = r10.sendMsg
            java.lang.String r8 = r10.srcTranID
            java.lang.String r9 = r11.getMessage()
            r3 = r10
            r3.onReportEvent(r4, r5, r6, r7, r8, r9)
        Lad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.wear.oversea.impl.HttpConnTask.preGetWalletReportStr(java.lang.String):void");
    }

    @Override // com.huawei.wear.oversea.restclient.BaseRestClient
    public Result handleSucceedResult(String str) {
        Result handleResponse = handleResponse(str, this.srcTranID, this.commander);
        stq.d(TAG, "HttpConnTaskprocessTask json string : " + str, false);
        return handleResponse;
    }

    @Override // com.huawei.wear.oversea.restclient.BaseRestClient
    public Result handleFailResult(int i, String str, Object obj) {
        if (503 == i) {
            stq.e(TAG, "processTask resultCode=SERVER_OVERLOAD_ERRORCODE.", false);
            this.standardCode = "14";
            Result readErrorResponse = readErrorResponse(-4, "RESPONSE_MESSAGE_SERVER_OVERLOAD_ERROR,code: 503,msg: " + str);
            onReportEvent("RESPONSE_MESSAGE_SERVER_OVERLOAD_ERROR", String.valueOf(i), this.commander, this.sendMsg, this.srcTranID, "");
            return readErrorResponse;
        }
        stq.e(TAG, "processTask resultCode=" + i, false);
        this.standardCode = "19";
        Result readErrorResponse2 = readErrorResponse(-2, "RESPONSE_MESSAGE_CONNECTION_FAILED,code: " + i + ",msg: " + str);
        onReportEvent("RESPONSE_MESSAGE_CONNECTION_FAILED", String.valueOf(i), this.commander, this.sendMsg, this.srcTranID, "");
        return readErrorResponse2;
    }

    protected String getStandardCode() {
        return this.standardCode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x021b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected Result handleResponse(java.lang.String r17, java.lang.String r18, java.lang.String r19) {
        /*
            Method dump skipped, instructions count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.wear.oversea.impl.HttpConnTask.handleResponse(java.lang.String, java.lang.String, java.lang.String):java.lang.Object");
    }

    public boolean isNumber(String str) {
        if (str != null && !"".equals(str)) {
            try {
                Long valueOf = Long.valueOf(Long.parseLong(str));
                if (valueOf.longValue() <= 2147483647L) {
                    if (valueOf.longValue() >= -2147483648L) {
                        return true;
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }

    protected void setErrorInfo(JSONObject jSONObject, sul sulVar) {
        sug sugVar;
        if (jSONObject != null && jSONObject.has(MyLocationStyle.ERROR_INFO)) {
            try {
                sugVar = sug.a(jSONObject.getJSONObject(MyLocationStyle.ERROR_INFO));
            } catch (JSONException unused) {
                stq.e(TAG, "setErrorInfo, JSONException", false);
                sulVar.g = -99;
                sugVar = null;
            }
            sulVar.d(sugVar);
        }
    }

    private void onReportEvent(String str, String str2, String str3, String str4, String str5, String str6) {
        String str7 = "commander:" + str3 + "; srcTranID:" + str5 + ";  ; result:" + str + "; returnCode:" + str2 + "; ";
        stq.c(TAG, "HttpConnTask onReportEvent, action:" + str4 + ", Report data: " + str7, false);
        suk.e(str4, str7 + " exception:" + str6);
    }
}
