package com.huawei.health.marketing.request;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.jbj;
import defpackage.koq;
import defpackage.lqi;
import defpackage.lql;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ColumnRequestUtils {
    private static final String BROWSE_URL_SUFFIX = "Anon";
    private static final String KEY_MENU = "MarketingFunctionMenu";
    public static final int RESULT_CODE_SUCCESS = 0;
    private static final String TAG = "ColumnRequestUtils";

    public static void reportInfoReadNumber(final int i) {
        if (i <= 0) {
            LogUtil.h(TAG, "reportInfoReadNumber() informationId is empty.");
            return;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.request.ColumnRequestUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    ColumnRequestUtils.reportInfoReadNumber(i);
                }
            });
            return;
        }
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("messageCenterUrl");
        if (TextUtils.isEmpty(url)) {
            LogUtil.h(TAG, "reportInfoReadNumber() sMessageCenterDomainUrl is empty.");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        AutoCloseable autoCloseable = null;
        try {
            try {
                jSONObject.put("id", i);
                Response d = jbj.d(url + "/messageCenter/information/readCountPlusOne", jSONObject);
                if (d == null) {
                    LogUtil.h(TAG, "response is null in reportInfoReadNumber.");
                    if (d != null) {
                        try {
                            d.close();
                            return;
                        } catch (IOException e) {
                            LogUtil.b(TAG, "IOException: ", e);
                            return;
                        }
                    }
                    return;
                }
                int code = d.getCode();
                if (code != 200 || d.getBody() == null) {
                    LogUtil.h(TAG, "getRequestResult responseCode = ", Integer.valueOf(code));
                } else {
                    LogUtil.a(TAG, "getRequestResult success");
                }
                if (d != null) {
                    try {
                        d.close();
                    } catch (IOException e2) {
                        LogUtil.b(TAG, "IOException: ", e2);
                    }
                }
            } catch (IOException | JSONException unused) {
                LogUtil.b(TAG, "reportInfoReadNumber meet json exception or ioexception.");
                if (0 != 0) {
                    try {
                        autoCloseable.close();
                    } catch (IOException e3) {
                        LogUtil.b(TAG, "IOException: ", e3);
                    }
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    autoCloseable.close();
                } catch (IOException e4) {
                    LogUtil.b(TAG, "IOException: ", e4);
                }
            }
            throw th;
        }
    }

    public static void updateFavoriteData(final List<CustomConfigValue> list) {
        SetCustomConfigReq setCustomConfigReq = new SetCustomConfigReq();
        CustomConfigInfo customConfigInfo = new CustomConfigInfo();
        String b = lql.b(list);
        LogUtil.c(TAG, "updateFavoriteData Str: ", b);
        customConfigInfo.setContent(b);
        setCustomConfigReq.setCustomConfig(customConfigInfo);
        SetCustomConfigApi setCustomConfigApi = (SetCustomConfigApi) lqi.d().b(SetCustomConfigApi.class);
        CustomConfigFactory customConfigFactory = new CustomConfigFactory(BaseApplication.getContext());
        setCustomConfigApi.setCustomConfig(setCustomConfigReq.getUrl(), customConfigFactory.getHeaders(), lql.b(customConfigFactory.getBody(setCustomConfigReq))).enqueue(new ResultCallback<RespSetCustomConfig>() { // from class: com.huawei.health.marketing.request.ColumnRequestUtils.2
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(com.huawei.hms.framework.network.restclient.Response<RespSetCustomConfig> response) {
                if (response.isOK()) {
                    RespSetCustomConfig body = response.getBody();
                    if (body == null) {
                        LogUtil.h(ColumnRequestUtils.TAG, "updateFavoriteData result is null");
                        return;
                    }
                    String b2 = lql.b(body);
                    ColumnProvider.getInstance(BaseApplication.getContext()).setFavoriteList(list);
                    LogUtil.a(ColumnRequestUtils.TAG, "updateFavoriteData result: ", b2);
                }
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h(ColumnRequestUtils.TAG, "updateFavoriteData response is error.");
            }
        });
    }

    public static void getFavoriteData(final IFavoriteCallBack iFavoriteCallBack) {
        final ArrayList arrayList = new ArrayList(10);
        GetCustomConfigReq getCustomConfigReq = new GetCustomConfigReq();
        ArrayList<String> arrayList2 = new ArrayList<>(10);
        arrayList2.add(KEY_MENU);
        getCustomConfigReq.setCustomConfig(arrayList2);
        GetCustomConfigApi getCustomConfigApi = (GetCustomConfigApi) lqi.d().b(GetCustomConfigApi.class);
        CustomConfigFactory customConfigFactory = new CustomConfigFactory(BaseApplication.getContext());
        getCustomConfigApi.getCustomConfig(getCustomConfigReq.getUrl(), customConfigFactory.getHeaders(), lql.b(customConfigFactory.getBody(getCustomConfigReq))).enqueue(new ResultCallback<RespGetCustomConfig>() { // from class: com.huawei.health.marketing.request.ColumnRequestUtils.3
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(com.huawei.hms.framework.network.restclient.Response<RespGetCustomConfig> response) {
                if (response.isOK()) {
                    RespGetCustomConfig body = response.getBody();
                    if (body == null) {
                        LogUtil.h(ColumnRequestUtils.TAG, "getFavorite result is null");
                        IFavoriteCallBack iFavoriteCallBack2 = IFavoriteCallBack.this;
                        if (iFavoriteCallBack2 != null) {
                            iFavoriteCallBack2.onResult(arrayList);
                            return;
                        }
                        return;
                    }
                    if (body.getResultCode() != 0) {
                        LogUtil.h(ColumnRequestUtils.TAG, "getFavorite result is error.");
                        IFavoriteCallBack iFavoriteCallBack3 = IFavoriteCallBack.this;
                        if (iFavoriteCallBack3 != null) {
                            iFavoriteCallBack3.onResult(arrayList);
                        }
                    }
                    CustomConfigInfo customConfig = body.getCustomConfig();
                    LogUtil.c(ColumnRequestUtils.TAG, "getFavorite result: ", customConfig.getContent());
                    try {
                        List<CustomConfigValue> list = (List) new Gson().fromJson(customConfig.getContent(), new TypeToken<List<CustomConfigValue>>() { // from class: com.huawei.health.marketing.request.ColumnRequestUtils.3.1
                        }.getType());
                        ColumnProvider.getInstance(BaseApplication.getContext()).setFavoriteList(list);
                        IFavoriteCallBack iFavoriteCallBack4 = IFavoriteCallBack.this;
                        if (iFavoriteCallBack4 != null) {
                            iFavoriteCallBack4.onResult(list);
                        }
                    } catch (JsonSyntaxException unused) {
                        LogUtil.h(ColumnRequestUtils.TAG, "getFavorite Exception.");
                        IFavoriteCallBack iFavoriteCallBack5 = IFavoriteCallBack.this;
                        if (iFavoriteCallBack5 != null) {
                            iFavoriteCallBack5.onResult(arrayList);
                        }
                    }
                }
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                IFavoriteCallBack iFavoriteCallBack2 = IFavoriteCallBack.this;
                if (iFavoriteCallBack2 != null) {
                    iFavoriteCallBack2.onResult(arrayList);
                }
            }
        });
    }

    public static List<SingleGridContent> getFunctionMenuFinallyData(List<SingleGridContent> list, List<CustomConfigValue> list2) {
        ArrayList arrayList = new ArrayList(10);
        final ArrayList arrayList2 = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h(TAG, "setData() singleGridContents is null.");
            return arrayList;
        }
        if (koq.c(list2)) {
            for (SingleGridContent singleGridContent : list) {
                if (TextUtils.isEmpty(singleGridContent.getItemId())) {
                    break;
                }
                Iterator<CustomConfigValue> it = list2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        CustomConfigValue next = it.next();
                        if (!TextUtils.isEmpty(next.getId()) && TextUtils.equals(singleGridContent.getItemId(), next.getId())) {
                            singleGridContent.setFavoriteTime(next.getFavoriteTime());
                            arrayList.add(singleGridContent);
                            arrayList2.add(next);
                            break;
                        }
                    }
                }
            }
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.request.ColumnRequestUtils$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ColumnRequestUtils.updateFavoriteData(arrayList2);
            }
        });
        if (koq.b(arrayList)) {
            return list;
        }
        Collections.sort(arrayList, new Comparator<SingleGridContent>() { // from class: com.huawei.health.marketing.request.ColumnRequestUtils.4
            @Override // java.util.Comparator
            public int compare(SingleGridContent singleGridContent2, SingleGridContent singleGridContent3) {
                return Long.compare(singleGridContent3.getFavoriteTime(), singleGridContent2.getFavoriteTime());
            }
        });
        ArrayList arrayList3 = new ArrayList(10);
        for (SingleGridContent singleGridContent2 : list) {
            if (TextUtils.isEmpty(singleGridContent2.getItemId())) {
                break;
            }
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (it2.hasNext()) {
                    SingleGridContent singleGridContent3 = (SingleGridContent) it2.next();
                    if (!TextUtils.isEmpty(singleGridContent3.getItemId()) && TextUtils.equals(singleGridContent2.getItemId(), singleGridContent3.getItemId())) {
                        break;
                    }
                } else {
                    arrayList3.add(singleGridContent2);
                    break;
                }
            }
        }
        if (koq.c(arrayList3)) {
            arrayList.addAll(arrayList3);
        }
        return arrayList;
    }

    public static String getUrlSuffix() {
        return LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode() ? BROWSE_URL_SUFFIX : "";
    }

    public static List<InformationInfo> requestInformationInfo(String str) {
        ArrayList arrayList = new ArrayList(10);
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        InformationInfoReq informationInfoReq = new InformationInfoReq();
        informationInfoReq.setIds(str);
        informationInfoReq.setPageNo("1");
        informationInfoReq.setPageSize("100");
        InformationInfoApi informationInfoApi = (InformationInfoApi) lqi.d().b(InformationInfoApi.class);
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.getContext());
        try {
            com.huawei.hms.framework.network.restclient.Response<RespInformationInfo> execute = informationInfoApi.getInformationInfoList(informationInfoReq.getUrl(), activityInfoListFactory.getHeaders(), lql.b(activityInfoListFactory.getBody(informationInfoReq))).execute();
            if (execute.isOK()) {
                RespInformationInfo body = execute.getBody();
                if (body == null) {
                    LogUtil.h(TAG, "requestInformationInfo failed, response is null");
                    return arrayList;
                }
                if (body.getResultCode() != 0) {
                    LogUtil.h(TAG, "requestInformationInfo failed, resultCode error: " + body.getResultCode());
                    return arrayList;
                }
                if (koq.b(body.getInformations())) {
                    LogUtil.h(TAG, "requestInformationInfo failed, response body is null");
                    return arrayList;
                }
                List<InformationInfo> informations = body.getInformations();
                LogUtil.a(TAG, "requestInformationInfo success, info list size: " + informations.size());
                return informations;
            }
            ReleaseLogUtil.d(TAG, "requestInformationInfo failed, response is not ok");
            return arrayList;
        } catch (IOException unused) {
            LogUtil.h(TAG, "requestInformationInfo exception.");
            return arrayList;
        }
    }

    public static List<ActivityIdInfo> requestActivityInfo(Context context, int i, List<String> list) {
        ArrayList arrayList = new ArrayList(10);
        ActivityInfoReq activityInfoReq = new ActivityInfoReq();
        activityInfoReq.setActivityIdList(list);
        ActivityInfoApi activityInfoApi = (ActivityInfoApi) lqi.d().b(ActivityInfoApi.class);
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.getContext());
        try {
            com.huawei.hms.framework.network.restclient.Response<RespActivityList> execute = activityInfoApi.getActivityInfoList(activityInfoReq.getUrl(), activityInfoListFactory.getHeaders(), lql.b(activityInfoListFactory.getBody(activityInfoReq))).execute();
            if (!execute.isOK()) {
                return arrayList;
            }
            LogUtil.a(TAG, "requestActivityInfo response is OK.");
            RespActivityList body = execute.getBody();
            if (body == null) {
                LogUtil.h(TAG, "requestActivityInfo result is null");
                return arrayList;
            }
            if (body.getResultCode() == 0 && !koq.b(body.getPageActivityList())) {
                SharedPreferenceManager.e(context, Integer.toString(PrebakedEffectId.RT_ICE), "marketingServerCurrentTime" + i, body.getCurrentTime(), new StorageParams());
                return body.getPageActivityList();
            }
            LogUtil.h(TAG, "requestActivityInfo result is error.");
            return arrayList;
        } catch (IOException unused) {
            LogUtil.h(TAG, "requestActivityInfo exception.");
            return arrayList;
        }
    }

    public static void submitAnswer(QuestionOptionReq questionOptionReq, com.huawei.networkclient.ResultCallback<QuestionResultRsp> resultCallback) {
        if (questionOptionReq == null) {
            LogUtil.h(TAG, "submitAnswer questionOptionReq is null.");
            return;
        }
        LogUtil.a(TAG, "submitAnswer enter");
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.getContext());
        lqi.d().b(questionOptionReq.getUrl(), activityInfoListFactory.getHeaders(), lql.b(activityInfoListFactory.getBody(questionOptionReq)), QuestionResultRsp.class, resultCallback);
    }

    public static void requestAnswer(QuestionResultReq questionResultReq, com.huawei.networkclient.ResultCallback<QuestionResultRsp> resultCallback) {
        if (questionResultReq == null) {
            LogUtil.h(TAG, "questionOptionReq is null.");
            return;
        }
        LogUtil.a(TAG, "requestAnswer enter");
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.getContext());
        lqi.d().b(questionResultReq.getUrl(), activityInfoListFactory.getHeaders(), lql.b(activityInfoListFactory.getBody(questionResultReq)), QuestionResultRsp.class, resultCallback);
    }
}
