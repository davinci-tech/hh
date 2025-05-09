package com.huawei.operation.h5pro.jsmodules.healthengine.service;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.h5pro.utils.AppInfoUtil;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.hihealth.HealthKitDictQuery;
import com.huawei.hihealth.HiHealthDataQueryOption;
import com.huawei.hihealthkit.context.H5ProAppInfo;
import com.huawei.operation.h5pro.jsmodules.healthengine.service.QueryDataParamObj;
import defpackage.idr;
import defpackage.idx;
import defpackage.idz;
import defpackage.iea;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class HealthEngineService {
    public static final HealthEngineService INSTANCE = new HealthEngineService();
    private static final String TAG = "H5PRO_HealthEngineService";

    private HealthEngineService() {
    }

    public HealthKitDictQuery parseQueryParam(String str) {
        QueryDataParamObj queryDataParamObj = (QueryDataParamObj) GsonUtil.parseContainsMapJson(str, QueryDataParamObj.class);
        HiHealthDataQueryOption hiHealthDataQueryOption = null;
        if (queryDataParamObj == null) {
            return null;
        }
        QueryDataParamObj.QueryDataOption queryOption = queryDataParamObj.getQueryOption();
        if (queryOption != null) {
            hiHealthDataQueryOption = new HiHealthDataQueryOption();
            hiHealthDataQueryOption.setLimit(queryOption.getLimit());
            hiHealthDataQueryOption.setOffset(queryOption.getOffset());
            hiHealthDataQueryOption.setOrder(queryOption.getOrder());
        }
        HealthKitDictQuery healthKitDictQuery = new HealthKitDictQuery(queryDataParamObj.getType(), queryDataParamObj.getStartTime(), queryDataParamObj.getEndTime(), hiHealthDataQueryOption);
        if (queryDataParamObj.getValueFilterType() > 0) {
            healthKitDictQuery.setValueFilterType(queryDataParamObj.getValueFilterType());
        }
        if (queryDataParamObj.getAggregateType() > 0) {
            healthKitDictQuery.putAggregateType(queryDataParamObj.getAggregateType());
        }
        if (queryDataParamObj.getGroupUnitType() > 0) {
            healthKitDictQuery.putGroupUnitType(queryDataParamObj.getGroupUnitType());
        }
        if (queryDataParamObj.getSubTypes() != null) {
            healthKitDictQuery.putSubTypes(queryDataParamObj.getSubTypes());
        }
        if (!TextUtils.isEmpty(queryDataParamObj.getFilterKeys()) && !TextUtils.isEmpty(queryDataParamObj.getFilterValues())) {
            healthKitDictQuery.setFilterKeys(queryDataParamObj.getFilterKeys());
            healthKitDictQuery.setFilterValues(queryDataParamObj.getFilterValues());
        }
        Map<String, Object> bundle = queryDataParamObj.getBundle();
        if (bundle != null && !bundle.isEmpty()) {
            for (Map.Entry<String, Object> entry : bundle.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey())) {
                    if (entry.getValue() instanceof Integer) {
                        healthKitDictQuery.putValue(entry.getKey(), ((Integer) entry.getValue()).intValue());
                    } else if (entry.getValue() instanceof Double) {
                        healthKitDictQuery.putValue(entry.getKey(), ((Double) entry.getValue()).doubleValue());
                    } else if (entry.getValue() instanceof String) {
                        healthKitDictQuery.putValue(entry.getKey(), (String) entry.getValue());
                    } else if (entry.getValue() instanceof Boolean) {
                        healthKitDictQuery.putValue(entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
                    } else {
                        LogUtil.i(TAG, "bundle: Only Integer, Double, Boolean,and String are supported. " + entry.getKey());
                    }
                }
            }
        }
        return healthKitDictQuery;
    }

    public void onQueryResult(H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker, long j, Object obj) {
        if (h5ProJsCbkInvoker == null) {
            LogUtil.w(TAG, "onQueryResult: callbackInvoker is null");
            return;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            LogUtil.i(TAG, "onQueryResult: size: " + list.size());
            if (list.isEmpty()) {
                h5ProJsCbkInvoker.onSuccess(list, j);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : list) {
                if (obj2 instanceof iea) {
                    arrayList.add((iea) obj2);
                } else if (obj2 instanceof idx) {
                    arrayList.add((idx) obj2);
                } else if (obj2 instanceof idz) {
                    arrayList.add((idz) obj2);
                } else {
                    LogUtil.d(TAG, "onQueryResult: Unknown type of data -> " + obj2);
                }
            }
            h5ProJsCbkInvoker.onSuccess(arrayList, j);
            return;
        }
        h5ProJsCbkInvoker.onSuccess(obj, j);
    }

    public idr getH5AppContext(Context context, H5ProInstance h5ProInstance) {
        return new idr(context, buildH5ProAppInfo(h5ProInstance));
    }

    private H5ProAppInfo buildH5ProAppInfo(H5ProInstance h5ProInstance) {
        H5ProAppInfo h5ProAppInfo = new H5ProAppInfo();
        com.huawei.health.h5pro.vengine.H5ProAppInfo appInfo = h5ProInstance.getAppInfo();
        if (appInfo == null) {
            LogUtil.w(TAG, "buildH5ProAppInfo originInfo is null");
            return h5ProAppInfo;
        }
        h5ProAppInfo.setAppId(appInfo.getAppId());
        h5ProAppInfo.setAccessToken(h5ProInstance.getAccessToken());
        h5ProAppInfo.setAppName(appInfo.getAppName());
        if (!TextUtils.isEmpty(appInfo.getPkgName())) {
            h5ProAppInfo.setPkgName(appInfo.getPkgName());
        } else {
            String hostOrPkgNameFroUrl = AppInfoUtil.getInstance().getHostOrPkgNameFroUrl(h5ProInstance.getUrl(), appInfo);
            if (!TextUtils.isEmpty(hostOrPkgNameFroUrl)) {
                h5ProAppInfo.setPkgName(hostOrPkgNameFroUrl);
            } else {
                h5ProAppInfo.setPkgName("com.huawei.health.h5pro.lightapp.unknow");
            }
        }
        h5ProAppInfo.setCertPrint(appInfo.getCertPrint());
        h5ProAppInfo.setUrl("");
        return h5ProAppInfo;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
    
        com.huawei.health.h5pro.utils.LogUtil.w(com.huawei.operation.h5pro.jsmodules.healthengine.service.HealthEngineService.TAG, "toIntArray: value is not type Integer");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int[] toIntArray(org.json.JSONArray r7) {
        /*
            r6 = this;
            java.lang.String r0 = "H5PRO_HealthEngineService"
            if (r7 != 0) goto Lf
            java.lang.String r7 = "toIntArray: jsonArray is null"
            java.lang.String[] r7 = new java.lang.String[]{r7}
            com.huawei.health.h5pro.utils.LogUtil.w(r0, r7)
            r7 = 0
            return r7
        Lf:
            int r1 = r7.length()
            int[] r1 = new int[r1]
            r2 = 0
            r3 = r2
        L17:
            int r4 = r7.length()     // Catch: org.json.JSONException -> L3b
            if (r3 >= r4) goto L41
            java.lang.Object r4 = r7.get(r3)     // Catch: org.json.JSONException -> L3b
            boolean r5 = r4 instanceof java.lang.Integer     // Catch: org.json.JSONException -> L3b
            if (r5 != 0) goto L30
            r7 = 1
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch: org.json.JSONException -> L3b
            java.lang.String r3 = "toIntArray: value is not type Integer"
            r7[r2] = r3     // Catch: org.json.JSONException -> L3b
            com.huawei.health.h5pro.utils.LogUtil.w(r0, r7)     // Catch: org.json.JSONException -> L3b
            goto L41
        L30:
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch: org.json.JSONException -> L3b
            int r4 = r4.intValue()     // Catch: org.json.JSONException -> L3b
            r1[r3] = r4     // Catch: org.json.JSONException -> L3b
            int r3 = r3 + 1
            goto L17
        L3b:
            r7 = move-exception
            java.lang.String r2 = "toIntArray: exception -> "
            com.huawei.health.h5pro.utils.LogUtil.e(r0, r2, r7)
        L41:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.h5pro.jsmodules.healthengine.service.HealthEngineService.toIntArray(org.json.JSONArray):int[]");
    }
}
