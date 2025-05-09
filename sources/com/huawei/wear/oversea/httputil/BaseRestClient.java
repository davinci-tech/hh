package com.huawei.wear.oversea.httputil;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.wear.oversea.httputil.restclient.StringDeleteRestClientService;
import com.huawei.wear.oversea.httputil.restclient.StringGetRestClientService;
import com.huawei.wear.oversea.httputil.restclient.StringRestClientService;
import defpackage.stg;
import defpackage.sti;
import defpackage.stj;
import defpackage.stl;
import defpackage.stm;
import defpackage.stq;
import defpackage.swa;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public abstract class BaseRestClient<Result, RequestParams> extends BaseHttpParams {
    private static final int DELETE = 3;
    private static final int GET = 1;
    private static final int POST = 2;
    private Map<String, String> headMaps;
    private Context mContext;
    protected int mRequestType = 2;
    private String mUrl;
    private Map<String, String> requestMaps;

    protected abstract Result handleFailResult(int i, String str, Object obj);

    protected abstract Result handleSucceedResult(String str);

    public BaseRestClient(Context context, String str, Map<String, String> map, Map<String, String> map2) {
        this.headMaps = new LinkedHashMap();
        this.requestMaps = new LinkedHashMap();
        this.mUrl = str;
        this.mContext = context;
        if (map != null) {
            this.headMaps = map;
            addAgent(map);
        }
        if (map2 != null) {
            this.requestMaps = map2;
        }
    }

    public static Map<String, String> getDefaultMaps() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Content-Type", "application/json");
        linkedHashMap.put("Charset", "UTF-8");
        return linkedHashMap;
    }

    private Map<String, String> getHeadMaps() {
        Map<String, String> defaultMaps = getDefaultMaps();
        addAgent(defaultMaps);
        return defaultMaps;
    }

    private Map<String, String> addAgent(Map map) {
        if (map == null) {
            return null;
        }
        if (map.containsKey("User-Agent")) {
            return map;
        }
        map.put("User-Agent", swa.a(this.mContext));
        return map;
    }

    public Result doRestClientProcessTask(stg stgVar, String str) throws stl, stm, stj, IllegalArgumentException, IOException {
        RestClient c = sti.c(this.mUrl, this.mContext);
        if (c == null) {
            stq.e("doRestClientProcessTask", "restClient is null : ", false);
            throw new stl("restClient is null");
        }
        stgVar.a(" |before open https connection:" + System.currentTimeMillis());
        Submit submit = getSubmit(stgVar, c, str);
        if (submit == null) {
            stq.e("doRestClientProcessTask", "submit is null : ", false);
            throw new stj();
        }
        Response execute = submit.execute();
        if (execute == null) {
            throw new stm();
        }
        stgVar.a(" |after open https connection:" + System.currentTimeMillis());
        if (execute.isOK()) {
            stgVar.a(" |before read Response data:" + System.currentTimeMillis());
            Result handleSucceedResult = handleSucceedResult((String) execute.getBody());
            stgVar.a(" |after handle Response data:" + System.currentTimeMillis());
            return handleSucceedResult;
        }
        return handleFailResult(execute.getCode(), execute.getMessage(), execute);
    }

    protected Submit getSubmit(stg stgVar, RestClient restClient, String str) {
        int i = this.mRequestType;
        if (i == 1) {
            stgVar.a(" |prepare BaseRestClient.GET:" + System.currentTimeMillis());
            Submit<String> executeRestClientRequest = ((StringGetRestClientService) restClient.create(StringGetRestClientService.class)).executeRestClientRequest(this.headMaps, this.mUrl, this.requestMaps);
            stgVar.a(" |end BaseRestClient.GET: and submit " + System.currentTimeMillis());
            return executeRestClientRequest;
        }
        if (i == 3) {
            stgVar.a(" |prepare BaseRestClient.DELETE:" + System.currentTimeMillis());
            Submit<String> executeRestClientRequest2 = ((StringDeleteRestClientService) restClient.create(StringDeleteRestClientService.class)).executeRestClientRequest(this.headMaps, this.mUrl, this.requestMaps);
            stgVar.a(" |end BaseRestClient.DELETE: and submit " + System.currentTimeMillis());
            return executeRestClientRequest2;
        }
        stgVar.a(" |prepare post:" + System.currentTimeMillis());
        StringRestClientService stringRestClientService = (StringRestClientService) restClient.create(StringRestClientService.class);
        if (str == null) {
            str = "";
        }
        Map<String, String> map = this.headMaps;
        Submit<String> executeRestClientRequest3 = stringRestClientService.executeRestClientRequest((map == null || map.isEmpty()) ? getHeadMaps() : this.headMaps, this.mUrl, str);
        stgVar.a(" | post get submit:" + System.currentTimeMillis());
        return executeRestClientRequest3;
    }
}
