package com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.restclienthttp.StringDeleteRestClientService;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.restclienthttp.StringGetRestClientService;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.restclienthttp.StringRestClientService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.jnj;
import defpackage.jnn;
import defpackage.jnu;
import health.compact.a.CommonUtil;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class HttpConnTask<Result, RequestParams> {
    private static final int MAP_INIT_SIZE = 1;
    private static final String TAG = "HttpConnTask";
    protected Context mContext;
    private String mUrl;
    private int mRequestType = 2;
    private Map<String, String> mHeaderMap = new LinkedHashMap(1);

    protected abstract String prepareRequestString(RequestParams requestparams);

    protected abstract Result readErrorResponse(int i);

    protected abstract Result readSuccessResponse(JSONObject jSONObject);

    public HttpConnTask(Context context, String str) {
        this.mContext = context;
        this.mUrl = str;
    }

    public Result processTask(RequestParams requestparams) {
        if (!CommonUtil.aa(this.mContext)) {
            LogUtil.h(TAG, "processTask, no network");
            return readErrorResponse(-2);
        }
        return executeRequest(requestparams);
    }

    private Result executeRequest(RequestParams requestparams) {
        try {
            RestClient e = jnj.e(this.mUrl, this.mContext);
            if (e == null) {
                LogUtil.h(TAG, "restClient is null");
                return readErrorResponse(-3);
            }
            Submit submit = getSubmit(e, requestparams);
            if (submit == null) {
                LogUtil.h(TAG, "submit is null");
                return readErrorResponse(-4);
            }
            return processResponse(submit.execute());
        } catch (IllegalArgumentException unused) {
            LogUtil.b(TAG, "processTask illegalArgumentException");
            return readErrorResponse(-4);
        } catch (MalformedURLException unused2) {
            LogUtil.b(TAG, "MalformedURLException url :", this.mUrl);
            return readErrorResponse(-4);
        } catch (IOException unused3) {
            LogUtil.b(TAG, "processTask IOException");
            return readErrorResponse(-3);
        }
    }

    private Result processResponse(Response response) {
        String e;
        String e2;
        String e3;
        int d;
        if (response == null) {
            LogUtil.h(TAG, "response is null");
            return readErrorResponse(-3);
        }
        if (response.isOK()) {
            String str = response.getBody() instanceof String ? (String) response.getBody() : "";
            LogUtil.a(TAG, "response is :", str);
            JSONObject jSONObject = null;
            try {
                JSONObject jSONObject2 = new JSONObject(str);
                e = jnn.e(jSONObject2, "errorCode");
                e2 = jnn.e(jSONObject2, "merchantID");
                e3 = jnn.e(jSONObject2, TrackConstants$Opers.RESPONSE);
                d = jnn.d(jSONObject2, "keyIndex");
            } catch (NumberFormatException unused) {
                LogUtil.b(TAG, "processResponse NumberFormatException");
            } catch (JSONException unused2) {
                LogUtil.b(TAG, "processResponse JSONException exception");
            }
            if (e != null) {
                LogUtil.h(TAG, "handleResponse, return code :", e);
                return readSuccessResponse(null);
            }
            if (jnu.d().equals(e2) && d == -1 && !TextUtils.isEmpty(e3)) {
                JSONObject jSONObject3 = new JSONObject(e3);
                if (jnn.e(jSONObject3, "returnCode") == null) {
                    LogUtil.h(TAG, "handleResponse, returnCode is invalid");
                    return readSuccessResponse(null);
                }
                jSONObject = jSONObject3;
                return readSuccessResponse(jSONObject);
            }
            LogUtil.h(TAG, "handleResponse, unexpected error from server");
            return readSuccessResponse(null);
        }
        LogUtil.h(TAG, "error message :", "url :", this.mUrl, "rc :", Integer.valueOf(response.getCode()));
        return readErrorResponse(-3);
    }

    private Submit getSubmit(RestClient restClient, RequestParams requestparams) {
        Map<String, String> defaultMaps;
        LinkedHashMap linkedHashMap = new LinkedHashMap(1);
        int i = this.mRequestType;
        if (i == 1) {
            return ((StringGetRestClientService) restClient.create(StringGetRestClientService.class)).executeRestClientRequest(this.mHeaderMap, this.mUrl, linkedHashMap);
        }
        if (i == 3) {
            return ((StringDeleteRestClientService) restClient.create(StringDeleteRestClientService.class)).executeRestClientRequest(this.mHeaderMap, this.mUrl, linkedHashMap);
        }
        String prepareRequestString = prepareRequestString(requestparams);
        if (prepareRequestString == null) {
            LogUtil.h(TAG, " processTask, invalid request params");
            return null;
        }
        LogUtil.a(TAG, "prepareRequestString :", prepareRequestString);
        StringRestClientService stringRestClientService = (StringRestClientService) restClient.create(StringRestClientService.class);
        Map<String, String> map = this.mHeaderMap;
        if (map == null || map.isEmpty()) {
            defaultMaps = getDefaultMaps();
        } else {
            defaultMaps = this.mHeaderMap;
        }
        return stringRestClientService.executeRestClientRequest(defaultMaps, this.mUrl, prepareRequestString);
    }

    private Map<String, String> getDefaultMaps() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(1);
        linkedHashMap.put("Content-Type", "application/json");
        linkedHashMap.put("Charset", "UTF-8");
        return linkedHashMap;
    }
}
