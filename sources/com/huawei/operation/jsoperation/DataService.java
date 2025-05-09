package com.huawei.operation.jsoperation;

import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiRegisterClientListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DataService implements JsCommand {
    private static final int DEVICE_TYPE = 10001;
    private static final String TAG = "PluginOperation_DataService";

    private <T> boolean isErrorCodeStatus(int i, T t) {
        return i == 1001 || i == 1999 || t == null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.operation.jsoperation.JsCommand
    public void execute(WebViewActivity webViewActivity, PluginOperationAdapter pluginOperationAdapter, JsCommandOption jsCommandOption) {
        char c;
        if (jsCommandOption == null || webViewActivity == null || pluginOperationAdapter == null) {
            LogUtil.b(TAG, "execute option or web or adapter is null");
            return;
        }
        LogUtil.a(TAG, "execute fun type is ", jsCommandOption.getFuncType());
        String funcType = jsCommandOption.getFuncType();
        funcType.hashCode();
        switch (funcType.hashCode()) {
            case -2099705049:
                if (funcType.equals(JsUtil.DataFunc.INSERT_HEALTH_DATA)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -2087230091:
                if (funcType.equals(JsUtil.DataFunc.ANNUAL_REPORT_DATA)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1749382556:
                if (funcType.equals(JsUtil.DataFunc.REGISTER_DATA_CLIENT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1643099199:
                if (funcType.equals(JsUtil.DataFunc.FITNESS_DATA)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1446242757:
                if (funcType.equals(JsUtil.DataFunc.MOTION_PATH_DATA)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1346380505:
                if (funcType.equals(JsUtil.DataFunc.USER_INFO_DATA)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 270156717:
                if (funcType.equals(JsUtil.DataFunc.HEALTH_DATA)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 270621271:
                if (funcType.equals(JsUtil.DataFunc.HEALTH_STAT)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 505406901:
                if (funcType.equals(JsUtil.DataFunc.SPORT_DATA)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                insertHealthData(webViewActivity, jsCommandOption);
                break;
            case 1:
                getAnnualReport(webViewActivity, pluginOperationAdapter, jsCommandOption);
                break;
            case 2:
                registerDataClient(webViewActivity, jsCommandOption);
                break;
            case 3:
                selectFitnessData(webViewActivity, pluginOperationAdapter, jsCommandOption);
                break;
            case 4:
                selectMotionPathData(webViewActivity, pluginOperationAdapter, jsCommandOption);
                break;
            case 5:
                selectUserInfoData(webViewActivity, pluginOperationAdapter, jsCommandOption);
                break;
            case 6:
                selectHealthData(webViewActivity, pluginOperationAdapter, jsCommandOption);
                break;
            case 7:
                selectHealthStat(webViewActivity, pluginOperationAdapter, jsCommandOption);
                break;
            case '\b':
                selectSportData(webViewActivity, pluginOperationAdapter, jsCommandOption);
                break;
        }
    }

    private void insertHealthData(WebViewActivity webViewActivity, JsCommandOption jsCommandOption) {
        if (JsUtil.checkAuth(webViewActivity, jsCommandOption.getFunctionRes(), jsCommandOption.isLegal())) {
            String param = jsCommandOption.getParam();
            String functionRes = jsCommandOption.getFunctionRes();
            ArrayList arrayList = new ArrayList(10);
            try {
                JSONObject jSONObject = new JSONObject(param);
                String string = jSONObject.getString("appID");
                int i = jSONObject.getInt("type");
                Object obj = jSONObject.get(JsUtil.DATA_LIST);
                if (obj instanceof JSONArray) {
                    PluginOperationDataStored.parseHealthData(i, toString((JSONArray) obj), string, arrayList);
                }
                if (arrayList.isEmpty()) {
                    LogUtil.a(TAG, "hiHealthDataList.isEmpty())");
                    JsUtil.runJsFunc(webViewActivity, functionRes, 1001, null);
                } else {
                    LogUtil.a(TAG, "begin insert HealthData");
                    HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
                    hiDataInsertOption.setDatas(arrayList);
                    HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new InnerHiDataOperateListener(webViewActivity, functionRes));
                }
            } catch (JSONException e) {
                LogUtil.b(TAG, "registerDataClient JSONException param fail exception = ", e.getMessage());
                JsUtil.runJsFunc(webViewActivity, functionRes, 1001, null);
            }
        }
    }

    private void registerDataClient(WebViewActivity webViewActivity, JsCommandOption jsCommandOption) {
        if (JsUtil.checkAuth(webViewActivity, jsCommandOption.getFunctionRes(), jsCommandOption.isLegal())) {
            String param = jsCommandOption.getParam();
            final String functionRes = jsCommandOption.getFunctionRes();
            try {
                JSONObject jSONObject = new JSONObject(param);
                String string = jSONObject.getString("appID");
                String string2 = jSONObject.getString(JsUtil.SERVICE_NAME);
                String string3 = jSONObject.getString("manufacturer");
                LogUtil.a(TAG, "registerDeviceToHiHealth enter");
                HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(10001);
                hiDeviceInfo.setDeviceUniqueCode(string);
                hiDeviceInfo.setDeviceName(string2);
                hiDeviceInfo.setManufacturer(string3);
                ArrayList arrayList = new ArrayList(10);
                arrayList.add(0);
                final WeakReference weakReference = new WeakReference(webViewActivity);
                HiHealthManager.d(BaseApplication.getContext()).registerDataClient(hiDeviceInfo, arrayList, new HiRegisterClientListener() { // from class: com.huawei.operation.jsoperation.DataService.1
                    @Override // com.huawei.hihealth.data.listener.HiRegisterClientListener
                    public void onResult(HiHealthClient hiHealthClient) {
                        WebViewActivity webViewActivity2 = (WebViewActivity) weakReference.get();
                        if (webViewActivity2 == null) {
                            LogUtil.h(DataService.TAG, "registerDataClient onResult, WebViewActivity is null");
                            return;
                        }
                        if (hiHealthClient != null) {
                            LogUtil.c(DataService.TAG, "registerDataClient onResult:", DataService.this.toString(hiHealthClient));
                            LogUtil.c(DataService.TAG, "client.getHiDeviceInfo():", DataService.this.toString(hiHealthClient.getHiDeviceInfo()));
                            JsUtil.runJsFunc(webViewActivity2, functionRes, 0, null);
                        } else {
                            LogUtil.a(DataService.TAG, "cannot registerDataClient to HiHealth");
                            JsUtil.runJsFunc(webViewActivity2, functionRes, 1001, null);
                        }
                    }
                });
            } catch (JSONException e) {
                LogUtil.b(TAG, "registerDataClient JSONException param fail exception = ", e.getMessage());
                JsUtil.runJsFunc(webViewActivity, functionRes, 1001, null);
            }
        }
    }

    private void getAnnualReport(WebViewActivity webViewActivity, PluginOperationAdapter pluginOperationAdapter, JsCommandOption jsCommandOption) {
        if (JsUtil.checkAuth(webViewActivity, jsCommandOption.getFunctionRes(), jsCommandOption.isLegal())) {
            String param = jsCommandOption.getParam();
            final String functionRes = jsCommandOption.getFunctionRes();
            try {
                int i = new JSONObject(param).getInt(JsUtil.ANNUAL_YEAR);
                final WeakReference weakReference = new WeakReference(webViewActivity);
                pluginOperationAdapter.getAnnualReport(i, new IBaseResponseCallback() { // from class: com.huawei.operation.jsoperation.DataService.2
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        LogUtil.c(DataService.TAG, "getAnnualReport errCode = ", Integer.valueOf(i2), ",objData = ", obj);
                        WebViewActivity webViewActivity2 = (WebViewActivity) weakReference.get();
                        if (webViewActivity2 == null) {
                            LogUtil.h(DataService.TAG, "getAnnualReport onResponse, WebViewActivity is null");
                        } else if (i2 == 0 && obj != null) {
                            JsUtil.runJsFunc(webViewActivity2, functionRes, i2, DataService.this.toString(obj));
                        } else {
                            JsUtil.runJsFunc(webViewActivity2, functionRes, i2, Constants.NULL);
                        }
                    }
                });
            } catch (JSONException unused) {
                LogUtil.b(TAG, "getAnnualReport parse param json fail!");
                JsUtil.runJsFunc(webViewActivity, functionRes, 1001, null);
            }
        }
    }

    private void selectHealthData(WebViewActivity webViewActivity, PluginOperationAdapter pluginOperationAdapter, JsCommandOption jsCommandOption) {
        if (JsUtil.checkAuth(webViewActivity, jsCommandOption.getFunctionRes(), jsCommandOption.isLegal())) {
            String param = jsCommandOption.getParam();
            final String functionRes = jsCommandOption.getFunctionRes();
            try {
                JSONObject jSONObject = new JSONObject(param);
                long j = jSONObject.getLong("startTime");
                long j2 = jSONObject.getLong("endTime");
                int i = jSONObject.getInt("type");
                final WeakReference weakReference = new WeakReference(webViewActivity);
                pluginOperationAdapter.getHealthData(j, j2, i, new IBaseResponseCallback() { // from class: com.huawei.operation.jsoperation.DataService.3
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        WebViewActivity webViewActivity2 = (WebViewActivity) weakReference.get();
                        if (webViewActivity2 != null) {
                            DataService dataService = DataService.this;
                            dataService.runJsFunc(webViewActivity2, functionRes, i2, dataService.toString(obj));
                        }
                    }
                });
            } catch (JSONException unused) {
                LogUtil.b(TAG, "selectHealthStat parse param json fail!");
                JsUtil.runJsFunc(webViewActivity, functionRes, 1001, null);
            }
        }
    }

    private void selectHealthStat(WebViewActivity webViewActivity, PluginOperationAdapter pluginOperationAdapter, JsCommandOption jsCommandOption) {
        if (JsUtil.checkAuth(webViewActivity, jsCommandOption.getFunctionRes(), jsCommandOption.isLegal())) {
            String param = jsCommandOption.getParam();
            final String functionRes = jsCommandOption.getFunctionRes();
            try {
                JSONObject jSONObject = new JSONObject(param);
                String string = jSONObject.getString(Constants.START_DATE);
                String string2 = jSONObject.getString("endDate");
                int i = jSONObject.getInt("type");
                final WeakReference weakReference = new WeakReference(webViewActivity);
                pluginOperationAdapter.getHealthStat(string, string2, i, new IBaseResponseCallback() { // from class: com.huawei.operation.jsoperation.DataService.4
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        WebViewActivity webViewActivity2 = (WebViewActivity) weakReference.get();
                        if (webViewActivity2 != null) {
                            DataService dataService = DataService.this;
                            dataService.runJsFunc(webViewActivity2, functionRes, i2, dataService.toString(obj));
                        }
                    }
                });
            } catch (JSONException unused) {
                LogUtil.b(TAG, "selectHealthStat parse param json fail!");
                JsUtil.runJsFunc(webViewActivity, functionRes, 1001, null);
            }
        }
    }

    private void selectFitnessData(WebViewActivity webViewActivity, PluginOperationAdapter pluginOperationAdapter, JsCommandOption jsCommandOption) {
        if (JsUtil.checkAuth(webViewActivity, jsCommandOption.getFunctionRes(), jsCommandOption.isLegal())) {
            String param = jsCommandOption.getParam();
            String functionRes = jsCommandOption.getFunctionRes();
            try {
                JSONObject jSONObject = new JSONObject(param);
                String string = jSONObject.getString(Constants.START_DATE);
                String string2 = jSONObject.getString("endDate");
                if (!JsUtil.checkParamIsLegal(string, string2)) {
                    JsUtil.runJsFunc(webViewActivity, functionRes, 1001, null);
                    return;
                }
                List<Map<String, Object>> recordsByDateRange = pluginOperationAdapter.getRecordsByDateRange(string, string2);
                if (recordsByDateRange == null || recordsByDateRange.isEmpty()) {
                    JsUtil.runJsFunc(webViewActivity, functionRes, 0, null);
                    return;
                }
                JSONObject jSONObject2 = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                if (getSuggestionData(webViewActivity, functionRes, recordsByDateRange, jSONArray)) {
                    return;
                }
                try {
                    jSONObject2.put("data", jSONArray);
                    JsUtil.runJsFunc(webViewActivity, functionRes, 0, toString(jSONObject2));
                } catch (JSONException unused) {
                    JsUtil.runJsFunc(webViewActivity, functionRes, 1001, null);
                }
            } catch (JSONException unused2) {
                JsUtil.runJsFunc(webViewActivity, functionRes, 1001, null);
            }
        }
    }

    private boolean getSuggestionData(WebViewActivity webViewActivity, String str, List<Map<String, Object>> list, JSONArray jSONArray) {
        for (Map<String, Object> map : list) {
            JSONObject jSONObject = new JSONObject();
            Object obj = map.get("date");
            Object obj2 = map.get(JsUtil.SUGGESTION_TOTAL_CALORIE);
            Object obj3 = map.get("totalduration");
            if ((obj instanceof String) && (obj2 instanceof String) && (obj3 instanceof String)) {
                try {
                    jSONObject.put("date", Integer.valueOf((String) obj));
                    jSONObject.put(JsUtil.SUGGESTION_TOTAL_CALORIE, Float.valueOf((String) obj2));
                    jSONObject.put("totalduration", Integer.valueOf((String) obj3));
                    jSONArray.put(jSONObject);
                } catch (NumberFormatException | JSONException unused) {
                    JsUtil.runJsFunc(webViewActivity, str, 1001, null);
                    return true;
                }
            }
        }
        return false;
    }

    private void selectSportData(WebViewActivity webViewActivity, PluginOperationAdapter pluginOperationAdapter, JsCommandOption jsCommandOption) {
        if (JsUtil.checkAuth(webViewActivity, jsCommandOption.getFunctionRes(), jsCommandOption.isLegal())) {
            String param = jsCommandOption.getParam();
            final String functionRes = jsCommandOption.getFunctionRes();
            try {
                JSONObject jSONObject = new JSONObject(param);
                String string = jSONObject.getString(Constants.START_DATE);
                String string2 = jSONObject.getString("endDate");
                LogUtil.a(TAG, "sport Data startDate = ", string, ", endDate = ", string2);
                final WeakReference weakReference = new WeakReference(webViewActivity);
                pluginOperationAdapter.getSportData(string, string2, new IBaseResponseCallback() { // from class: com.huawei.operation.jsoperation.DataService$$ExternalSyntheticLambda0
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        DataService.this.m756x70f25e2f(weakReference, functionRes, i, obj);
                    }
                });
            } catch (JSONException e) {
                LogUtil.b(TAG, "selectSportData parse param json fail! ONE exception = ", e.getMessage());
                JsUtil.runJsFunc(webViewActivity, functionRes, 1001, null);
            }
        }
    }

    /* renamed from: lambda$selectSportData$0$com-huawei-operation-jsoperation-DataService, reason: not valid java name */
    /* synthetic */ void m756x70f25e2f(WeakReference weakReference, String str, int i, Object obj) {
        WebViewActivity webViewActivity = (WebViewActivity) weakReference.get();
        if (webViewActivity == null) {
            LogUtil.h(TAG, "sport Data onResponse, WebViewActivity is null");
            return;
        }
        if (isErrorCodeStatus(i, obj)) {
            JsUtil.runJsFunc(webViewActivity, str, i, Constants.NULL);
            return;
        }
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            JSONObject jSONObject2 = new JSONObject();
            try {
                Object obj2 = jSONObject.get(JsUtil.SUMMARIES_KEY);
                if (obj2 instanceof JSONArray) {
                    LogUtil.c(TAG, "getSportData jsonArray");
                    jSONObject2.put("data", (JSONArray) obj2);
                }
                JsUtil.runJsFunc(webViewActivity, str, i, toString(jSONObject2));
            } catch (JSONException e) {
                LogUtil.b(TAG, "selectSportData parse param json fail! TWO exception = ", e.getMessage());
                JsUtil.runJsFunc(webViewActivity, str, 1001, null);
            }
        }
    }

    private void selectUserInfoData(WebViewActivity webViewActivity, PluginOperationAdapter pluginOperationAdapter, JsCommandOption jsCommandOption) {
        if (JsUtil.checkAuth(webViewActivity, jsCommandOption.getFunctionRes(), jsCommandOption.isLegal())) {
            final String functionRes = jsCommandOption.getFunctionRes();
            final WeakReference weakReference = new WeakReference(webViewActivity);
            pluginOperationAdapter.getUserInfo(new IBaseResponseCallback() { // from class: com.huawei.operation.jsoperation.DataService.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    WebViewActivity webViewActivity2 = (WebViewActivity) weakReference.get();
                    if (webViewActivity2 != null) {
                        DataService dataService = DataService.this;
                        dataService.runJsFunc(webViewActivity2, functionRes, i, dataService.toString(obj));
                    }
                }
            });
        }
    }

    private void selectMotionPathData(WebViewActivity webViewActivity, PluginOperationAdapter pluginOperationAdapter, JsCommandOption jsCommandOption) {
        if (JsUtil.checkAuth(webViewActivity, jsCommandOption.getFunctionRes(), jsCommandOption.isLegal())) {
            String param = jsCommandOption.getParam();
            final String functionRes = jsCommandOption.getFunctionRes();
            try {
                JSONObject jSONObject = new JSONObject(param);
                long j = jSONObject.getLong("startTime");
                long j2 = jSONObject.getLong("endTime");
                LogUtil.a(TAG, "motion path startTime = ", Long.valueOf(j), ", endTime = ", Long.valueOf(j2));
                final WeakReference weakReference = new WeakReference(webViewActivity);
                pluginOperationAdapter.getMotionPathData(j, j2, new IBaseResponseCallback() { // from class: com.huawei.operation.jsoperation.DataService.6
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        WebViewActivity webViewActivity2 = (WebViewActivity) weakReference.get();
                        if (webViewActivity2 != null) {
                            DataService dataService = DataService.this;
                            dataService.runJsFunc(webViewActivity2, functionRes, i, dataService.toString(obj));
                        }
                    }
                });
            } catch (JSONException e) {
                LogUtil.b(TAG, "parse param json fail! e : ", e.getMessage());
                JsUtil.runJsFunc(webViewActivity, functionRes, 1001, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void runJsFunc(WebViewActivity webViewActivity, String str, int i, T t) {
        if (isErrorCodeStatus(i, t)) {
            JsUtil.runJsFunc(webViewActivity, str, i, Constants.NULL);
        } else {
            JsUtil.runJsFunc(webViewActivity, str, i, toString(t));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> String toString(T t) {
        if (t == null) {
            return null;
        }
        return t.toString();
    }

    static class InnerHiDataOperateListener implements HiDataOperateListener {
        private final String functionRes;
        private final WeakReference<WebViewActivity> wrWebViewActivity;

        InnerHiDataOperateListener(WebViewActivity webViewActivity, String str) {
            this.wrWebViewActivity = new WeakReference<>(webViewActivity);
            this.functionRes = str;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            LogUtil.a(DataService.TAG, "insertHealthData onResult type=", Integer.valueOf(i), " obj=", obj);
            if (i == 0) {
                LogUtil.a(DataService.TAG, "insertThirdPartServiceDataToHiHealth success");
                JsUtil.runJsFunc(this.wrWebViewActivity.get(), this.functionRes, 0, null);
            } else {
                LogUtil.b(DataService.TAG, "insertThirdPartServiceDataToHiHealth not correct obj=", obj);
                JsUtil.runJsFunc(this.wrWebViewActivity.get(), this.functionRes, 1001, null);
            }
        }
    }
}
