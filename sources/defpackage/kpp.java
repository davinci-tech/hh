package defpackage;

import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.IRequest;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kpp {
    private static final ParamsFactory e = new kpo();

    private static <T> void b(String str, Map<String, String> map, String str2, Class<T> cls, ResultCallback<T> resultCallback) {
        LogUtil.a("HealthWeight_FatLossCloudManager", "callHttpPost url ", str, " headers ", map, " body ", str2);
        try {
            lqi.d().b(str, map, str2, cls, resultCallback);
        } catch (OutOfMemoryError e2) {
            LogUtil.b("HealthWeight_FatLossCloudManager", "callHttpPost error ", LogAnonymous.b((Throwable) e2), " url ", str, " headers ", map, " body ", str2, " responseType ", cls, " callback ", resultCallback);
        }
    }

    private static boolean c() {
        boolean aa = CommonUtil.aa(BaseApplication.e());
        boolean i = Utils.i();
        ReleaseLogUtil.e("HealthWeight_FatLossCloudManager", "isVerify isNetworkConnected ", Boolean.valueOf(aa), " isAllowLogin ", Boolean.valueOf(i));
        return aa && i;
    }

    public static void d(IRequest iRequest, final UiCallback<gse> uiCallback) {
        if (c()) {
            ParamsFactory paramsFactory = e;
            b(iRequest.getUrl(), paramsFactory.getHeaders(), HiJsonUtil.e(paramsFactory.getBody(iRequest)), String.class, new ResultCallback<String>() { // from class: kpp.5
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        ReleaseLogUtil.e("HealthWeight_FatLossCloudManager", "calculateGoal jsonObject ", jSONObject);
                        int optInt = jSONObject.optInt("resultCode");
                        String optString = jSONObject.optString("resultDesc");
                        if (optInt != 0) {
                            ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "calculateGoal onFailure resultCode ", Integer.valueOf(optInt), " desc ", optString);
                            UiCallback.this.onFailure(optInt, optString);
                        } else {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("goalDetail");
                            ReleaseLogUtil.e("HealthWeight_FatLossCloudManager", "calculateGoal object ", jSONObject2);
                            UiCallback.this.onSuccess((gse) moj.e(jSONObject2.toString(), gse.class));
                        }
                    } catch (JSONException unused) {
                        ReleaseLogUtil.c("HealthWeight_FatLossCloudManager", "calculateGoal JSONException");
                        UiCallback.this.onFailure(-1, "calculateGoal JSONException");
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "calculateGoal getData onFailure");
                    UiCallback.this.onFailure(-1, "calculateGoal onFailure");
                }
            });
        } else if (uiCallback != null) {
            uiCallback.onFailure(-1, "");
        }
    }

    private static void d(IRequest iRequest, final ResponseCallback<JSONObject> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "getDietFilterAnon callback is null request ", iRequest);
        } else {
            if (!c()) {
                responseCallback.onResponse(-1, null);
                return;
            }
            String url = iRequest.getUrl();
            ParamsFactory paramsFactory = e;
            b(url, paramsFactory.getHeaders(), HiJsonUtil.e(paramsFactory.getBody(iRequest)), String.class, new ResultCallback<String>() { // from class: kpp.2
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    LogUtil.a("HealthWeight_FatLossCloudManager", "getDietFilterAnon onSuccess json ", str);
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        ResponseCallback.this.onResponse(jSONObject.optInt("resultCode"), jSONObject);
                    } catch (JSONException e2) {
                        ReleaseLogUtil.c("HealthWeight_FatLossCloudManager", "getDietFilterAnon exception ", ExceptionUtils.d(e2));
                        ResponseCallback.this.onResponse(-1, null);
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "getDietFilterAnon onFailure throwable ", ExceptionUtils.d(th));
                    ResponseCallback.this.onResponse(-1, null);
                }
            });
        }
    }

    public static void c(IRequest iRequest, final ResponseCallback<Boolean> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "getSupportDietSetting callback is null request ", iRequest);
        } else {
            d(iRequest, (ResponseCallback<JSONObject>) new ResponseCallback() { // from class: kpr
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    kpp.d(ResponseCallback.this, i, (JSONObject) obj);
                }
            });
        }
    }

    static /* synthetic */ void d(ResponseCallback responseCallback, int i, JSONObject jSONObject) {
        if (jSONObject == null || i != 0) {
            ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "getSupportDietSetting jsonObject is null errorCode ", Integer.valueOf(i));
            responseCallback.onResponse(i, false);
            return;
        }
        LogUtil.a("HealthWeight_FatLossCloudManager", "diet setting is ", jSONObject.toString());
        gsd.v();
        gsd.a(jSONObject.optInt("display") == 1, "THIRD_DIET_TIME_MILLIS", System.currentTimeMillis());
        gsd.a(jSONObject.optInt("simplifyDisplay") == 1, "SIMPLIFY_DIET_TIME_MILLIS", System.currentTimeMillis());
        gsd.a(jSONObject.optInt("dietAnalysis") == 1, "DIET_ANALYSIS_TIME_MILLIS", System.currentTimeMillis());
        gsd.a(jSONObject.optInt("recommendRecipe") == 1, "RECOMMEND_DIET_TIME_MILLIS", System.currentTimeMillis());
        responseCallback.onResponse(i, true);
    }

    private static void e(IRequest iRequest, final ResponseCallback<JSONObject> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "getDietAnalysis callback is null request ", iRequest);
        } else {
            if (!c()) {
                responseCallback.onResponse(-1, null);
                return;
            }
            String url = iRequest.getUrl();
            ParamsFactory paramsFactory = e;
            b(url, paramsFactory.getHeaders(), HiJsonUtil.e(paramsFactory.getBody(iRequest)), String.class, new ResultCallback<String>() { // from class: kpp.3
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    JSONObject jSONObject;
                    int i;
                    LogUtil.a("HealthWeight_FatLossCloudManager", "getDietAnalysis onSuccess json ", str);
                    try {
                        jSONObject = new JSONObject(str);
                        try {
                            i = jSONObject.optInt("resultCode");
                        } catch (JSONException e2) {
                            e = e2;
                            ReleaseLogUtil.c("HealthWeight_FatLossCloudManager", "getDietAnalysis onSuccess exception ", ExceptionUtils.d(e));
                            i = -1;
                            ResponseCallback.this.onResponse(i, jSONObject);
                        }
                    } catch (JSONException e3) {
                        e = e3;
                        jSONObject = null;
                    }
                    ResponseCallback.this.onResponse(i, jSONObject);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "getDietAnalysis onFailure throwable ", ExceptionUtils.d(th));
                    ResponseCallback.this.onResponse(-1, null);
                }
            });
        }
    }

    public static void a(IRequest iRequest, final ResponseCallback<Map<String, cwp>> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "getNutritionAnalysisItem callback is null request ", iRequest);
        } else {
            e(iRequest, new ResponseCallback() { // from class: kpv
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    kpp.b(ResponseCallback.this, i, (JSONObject) obj);
                }
            });
        }
    }

    static /* synthetic */ void b(ResponseCallback responseCallback, int i, JSONObject jSONObject) {
        if (jSONObject == null) {
            ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "getNutritionAnalysisItem jsonObject is null errorCode ", Integer.valueOf(i));
            responseCallback.onResponse(i, new HashMap());
            return;
        }
        HashMap hashMap = new HashMap();
        JSONObject optJSONObject = jSONObject.optJSONObject("fatAnalysis");
        if (optJSONObject != null) {
            hashMap.put("fatAnalysis", (cwp) HiJsonUtil.e(optJSONObject.toString(), cwp.class));
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("proteinAnalysis");
        if (optJSONObject2 != null) {
            hashMap.put("proteinAnalysis", (cwp) HiJsonUtil.e(optJSONObject2.toString(), cwp.class));
        }
        JSONObject optJSONObject3 = jSONObject.optJSONObject("carbohydrateAnalysis");
        if (optJSONObject3 != null) {
            hashMap.put("carbohydrateAnalysis", (cwp) HiJsonUtil.e(optJSONObject3.toString(), cwp.class));
        }
        responseCallback.onResponse(i, hashMap);
    }

    public static void b(IRequest iRequest, final ResponseCallback<List<cwk>> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "getFoodList callback is null request ", iRequest);
        } else {
            if (!c()) {
                responseCallback.onResponse(-1, null);
                return;
            }
            String url = iRequest.getUrl();
            ParamsFactory paramsFactory = e;
            b(url, paramsFactory.getHeaders(), HiJsonUtil.e(paramsFactory.getBody(iRequest)), String.class, new ResultCallback<String>() { // from class: kpp.1
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    JSONObject jSONObject;
                    int i;
                    List arrayList;
                    JSONArray optJSONArray;
                    LogUtil.a("HealthWeight_FatLossCloudManager", "getFoodList onSuccess json ", str);
                    try {
                        jSONObject = new JSONObject(str);
                        try {
                            i = jSONObject.optInt("resultCode");
                        } catch (JSONException e2) {
                            e = e2;
                            ReleaseLogUtil.c("HealthWeight_FatLossCloudManager", "getFoodList onSuccess exception ", ExceptionUtils.d(e));
                            i = -1;
                            arrayList = new ArrayList();
                            if (jSONObject != null) {
                                arrayList = (List) HiJsonUtil.b(optJSONArray.toString(), new TypeToken<List<cwk>>() { // from class: kpp.1.2
                                }.getType());
                            }
                            ResponseCallback.this.onResponse(i, arrayList);
                        }
                    } catch (JSONException e3) {
                        e = e3;
                        jSONObject = null;
                    }
                    arrayList = new ArrayList();
                    if (jSONObject != null && (optJSONArray = jSONObject.optJSONArray("foodList")) != null) {
                        arrayList = (List) HiJsonUtil.b(optJSONArray.toString(), new TypeToken<List<cwk>>() { // from class: kpp.1.2
                        }.getType());
                    }
                    ResponseCallback.this.onResponse(i, arrayList);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    ReleaseLogUtil.d("HealthWeight_FatLossCloudManager", "getFoodList onFailure throwable ", ExceptionUtils.d(th));
                    ResponseCallback.this.onResponse(-1, Collections.emptyList());
                }
            });
        }
    }
}
