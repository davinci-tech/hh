package com.huawei.wear.oversea.httputil;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.stg;
import defpackage.stj;
import defpackage.stk;
import defpackage.stl;
import defpackage.stm;
import defpackage.stq;
import defpackage.svx;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public abstract class HttpConnTask<Result, RequestParams> extends BaseRestClient<Result, RequestParams> {
    private static final String TAG = "HttpConnTask";
    private String commander;
    protected Context mContext;
    private String mUrl;
    private String srcTranID;
    private stg timeExecuteBuilder;

    protected abstract void onReportEvent(String str, String str2, String str3, String str4, String str5, String str6);

    protected abstract String prepareRequestStr(RequestParams requestparams);

    protected abstract Result readErrorResponse(int i);

    protected abstract Result readSuccessResponse(String str);

    public HttpConnTask(Context context, String str) {
        super(context, str, null, null);
        this.mContext = context;
        this.mUrl = str;
    }

    public HttpConnTask(Context context, String str, int i, int i2) {
        super(context, str, null, null);
        this.mContext = context;
        this.mUrl = str;
    }

    public HttpConnTask(Context context, String str, int i, Map<String, String> map, Map<String, String> map2) {
        super(context, str, map, map2);
        this.mContext = context;
        this.mUrl = str;
        this.mRequestType = i;
    }

    public HttpConnTask(Context context, String str, int i, Map<String, String> map, Map<String, String> map2, String str2) {
        super(context, str, map, map2);
        this.mContext = context;
        this.mUrl = str;
        this.mRequestType = i;
        this.commander = str2;
    }

    public Result processTask(RequestParams requestparams) {
        String str;
        this.timeExecuteBuilder = new stg();
        if (!svx.d()) {
            stq.c(TAG, "processTask  no network ", false);
            logDebugHttpConnTask("processTask, no network." + this.mUrl);
            return readErrorResponse(-1);
        }
        this.timeExecuteBuilder.a(" prepareRequestStr " + System.currentTimeMillis());
        if (requestparams != null) {
            str = prepareRequestStr(requestparams);
            if (str == null) {
                stq.c(TAG, "processTask, prepareRequestStr is null", false);
                return readErrorResponse(-3);
            }
        } else {
            str = null;
        }
        try {
            preGetWalletReportStr(str);
            this.timeExecuteBuilder.a(" |before open https connection:" + System.currentTimeMillis());
            stq.d(TAG, getSubProcessPrefix() + "processTask request string   " + str, false);
            return excuteRestClient(str);
        } catch (stk e) {
            onReportEvent("TokenErrorException, " + e.d() + " -7000", "SERVICE_TOKEN_INVALID", this.commander, this.sendMsg, this.srcTranID, "");
            return readErrorResponse(-2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0073, code lost:
    
        if ((r6 instanceof java.lang.Integer) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007b, code lost:
    
        if (((java.lang.Integer) r6).intValue() == 1048832) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0085, code lost:
    
        throw new defpackage.stk("AccessToken");
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void preGetWalletReportStr(java.lang.String r6) throws defpackage.stk {
        /*
            r5 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = 0
            java.lang.String r2 = "HttpConnTask"
            if (r0 == 0) goto Lf
            java.lang.String r6 = "preGetWalletReportStr requestStr is null"
            defpackage.stq.c(r2, r6, r1)
            return
        Lf:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L86
            r0.<init>(r6)     // Catch: org.json.JSONException -> L86
            java.lang.String r6 = "data"
            java.lang.String r6 = r0.optString(r6)     // Catch: org.json.JSONException -> L86
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: org.json.JSONException -> L86
            if (r0 == 0) goto L26
            java.lang.String r6 = "dataString is null"
            defpackage.stq.c(r2, r6, r1)     // Catch: org.json.JSONException -> L86
            return
        L26:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L86
            r0.<init>(r6)     // Catch: org.json.JSONException -> L86
            java.lang.String r6 = "header"
            org.json.JSONObject r6 = r0.optJSONObject(r6)     // Catch: org.json.JSONException -> L86
            if (r6 != 0) goto L39
            java.lang.String r6 = "preGetWalletReportStr null == head"
            defpackage.stq.c(r2, r6, r1)     // Catch: org.json.JSONException -> L86
            return
        L39:
            java.lang.String r0 = "srcTranID"
            java.lang.String r0 = r6.optString(r0)     // Catch: org.json.JSONException -> L86
            r5.srcTranID = r0     // Catch: org.json.JSONException -> L86
            java.lang.String r0 = "commander"
            java.lang.String r0 = r6.optString(r0)     // Catch: org.json.JSONException -> L86
            r5.commander = r0     // Catch: org.json.JSONException -> L86
            java.lang.String r0 = "serviceTokenAuth"
            java.lang.Object r0 = r6.opt(r0)     // Catch: org.json.JSONException -> L86
            java.lang.String r3 = "accessTokenAuth"
            java.lang.Object r6 = r6.opt(r3)     // Catch: org.json.JSONException -> L86
            r3 = 1048832(0x100100, float:1.469727E-39)
            if (r0 == 0) goto L6f
            boolean r4 = r0 instanceof java.lang.Integer     // Catch: org.json.JSONException -> L86
            if (r4 == 0) goto L6f
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch: org.json.JSONException -> L86
            int r0 = r0.intValue()     // Catch: org.json.JSONException -> L86
            if (r0 == r3) goto L67
            goto L6f
        L67:
            stk r6 = new stk     // Catch: org.json.JSONException -> L86
            java.lang.String r0 = "ServiceToken"
            r6.<init>(r0)     // Catch: org.json.JSONException -> L86
            throw r6     // Catch: org.json.JSONException -> L86
        L6f:
            if (r6 == 0) goto L9e
            boolean r0 = r6 instanceof java.lang.Integer     // Catch: org.json.JSONException -> L86
            if (r0 == 0) goto L9e
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch: org.json.JSONException -> L86
            int r6 = r6.intValue()     // Catch: org.json.JSONException -> L86
            if (r6 == r3) goto L7e
            goto L9e
        L7e:
            stk r6 = new stk     // Catch: org.json.JSONException -> L86
            java.lang.String r0 = "AccessToken"
            r6.<init>(r0)     // Catch: org.json.JSONException -> L86
            throw r6     // Catch: org.json.JSONException -> L86
        L86:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = r5.getSubProcessPrefix()
            r6.append(r0)
            java.lang.String r0 = "Something wrong when get srcTranID and commander"
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            defpackage.stq.e(r2, r6, r1)
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.wear.oversea.httputil.HttpConnTask.preGetWalletReportStr(java.lang.String):void");
    }

    private Result excuteRestClient(String str) {
        try {
            return doRestClientProcessTask(this.timeExecuteBuilder, str);
        } catch (IllegalArgumentException e) {
            stq.e(TAG, "processTask IOException : ", true);
            logDebugHttpConnTask("logDebugHttpConnTask(MalformedURLException+mUrl);" + this.mUrl);
            onReportEvent("Send request IllegalArgumentException, RESPONSE_MESSAGE_CONNECTION_FAILED,uEx error ", "RESPONSE_MESSAGE_CONNECTION_FAILED_NO_SUCH_ALGORITHM_EXCEPTION", this.commander, this.sendMsg, this.srcTranID, e.getMessage());
            return readErrorResponse(-3);
        } catch (MalformedURLException e2) {
            logDebugHttpConnTask("MalformedURLException" + this.mUrl);
            onReportEvent("Send request failed MalformedURLException, RESPONSE_MESSAGE_PARAMS_ERROR_MALFORMED_URL_EXCEPTION,urlEx error ", String.valueOf(1), this.commander, this.sendMsg, this.srcTranID, e2.getMessage());
            return readErrorResponse(-3);
        } catch (IOException e3) {
            stq.e(TAG, "processTask IOException : ", true);
            logDebugHttpConnTask("IOException ioEx" + this.mUrl);
            onReportEvent("Send request failed, IOException  RESPONSE_CONNECTION_FAILED_MESSAGE,ioEx error ", String.valueOf(-2), this.commander, this.sendMsg, this.srcTranID, e3.getMessage());
            return readErrorResponse(-2);
        } catch (stj unused) {
            stq.e(TAG, "submit is null : ", true);
            logDebugHttpConnTask("submit is null :" + this.mUrl);
            Result readErrorResponse = readErrorResponse(-3);
            onReportEvent("submit is null, -10040,ioEx error ", "RESTCLIENT_RESPONSE_SUBMIT_IS_NULL", this.commander, this.sendMsg, this.srcTranID, "");
            return readErrorResponse;
        } catch (stl unused2) {
            stq.e(TAG, "restClient is null : ", true);
            logDebugHttpConnTask("restClient is null :" + this.mUrl);
            onReportEvent("restClient is null, -10042", "REQUEST_REST_CLIENT_IS_NULL", this.commander, this.sendMsg, this.srcTranID, "");
            return readErrorResponse(-2);
        } catch (stm unused3) {
            stq.e(TAG, "response is null ", true);
            logDebugHttpConnTask("response is null " + this.mUrl);
            onReportEvent("submit is null, -10041,ioEx error ", "RESPONSE_REST_CLIENT_RESPONSE_NULL", this.commander, this.sendMsg, this.srcTranID, "");
            return readErrorResponse(-2);
        } catch (Exception e4) {
            stq.e(TAG, "processTask Exception : ", true);
            logDebugHttpConnTask("Exception ex" + this.mUrl);
            onReportEvent("Send request failed, Exception  RESPONSE_CONNECTION_FAILED_MESSAGE,Exception error ", String.valueOf(-2), this.commander, this.sendMsg, this.srcTranID, e4.getMessage());
            return readErrorResponse(-2);
        }
    }

    @Override // com.huawei.wear.oversea.httputil.BaseRestClient
    protected Result handleSucceedResult(String str) {
        Result readSuccessResponse = readSuccessResponse(str);
        uploadWalletMsg(str);
        return readSuccessResponse;
    }

    private void uploadWalletMsg(String str) {
        if (TextUtils.isEmpty(str)) {
            stq.c(TAG, "responseStr is null", false);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(TrackConstants$Opers.RESPONSE);
            if (!TextUtils.isEmpty(optString)) {
                String optString2 = new JSONObject(optString).optString("returnCode");
                if (!TextUtils.isEmpty(optString2) && this.timeExecuteBuilder != null && optString2.equals("0")) {
                    return;
                }
            }
            String optString3 = jSONObject.optString("errorCode");
            String optString4 = jSONObject.optString("errorMsg");
            String optString5 = jSONObject.optString(TrackConstants$Opers.RESPONSE);
            if (TextUtils.isEmpty(optString3)) {
                return;
            }
            stq.d(TAG, getSubProcessPrefix() + "handleResponse, return code : " + optString3 + ", return msg : " + optString4, false);
            StringBuilder sb = new StringBuilder("handleResponse, return code : ");
            sb.append(optString3);
            onReportEvent(sb.toString(), optString3, this.commander, this.receiveMsg, this.srcTranID, "errorMsg: " + optString4 + "responseDataStr:" + optString5);
        } catch (JSONException unused) {
            stq.c(TAG, "uploadWalletMsg JSONException", false);
        }
    }

    @Override // com.huawei.wear.oversea.httputil.BaseRestClient
    protected Result handleFailResult(int i, String str, Object obj) {
        String str2 = "url:" + this.mUrl + " rc:" + i;
        stq.c(TAG, "Service err. handleFailResult resultCode :" + i, false);
        onReportEvent("RESPONSE_MESSAGE_CONNECTION_FAILED", String.valueOf(i), this.commander, this.receiveMsg, this.srcTranID, str2);
        if (i == 410) {
            return readErrorResponse(410);
        }
        return readErrorResponse(-2);
    }

    private void logDebugHttpConnTask(String str) {
        stq.c(TAG, str);
    }
}
