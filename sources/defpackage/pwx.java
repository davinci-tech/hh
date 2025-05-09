package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.ResultUtils;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.pluginsleeplabel.SleepLabelApi;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* loaded from: classes6.dex */
public class pwx {
    private int c;
    private Context d;
    private static final int[] e = {44101, 44102, 44103, 44104, 44105, 44106, 44201, 44205, 44202, 44207, 44204, 44203, 44208, 44107, 44108, 44110};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f16315a = {"core_sleep_dream_key", "core_sleep_deep_key", "core_sleep_shallow_key", "core_sleep_wake_key", "core_sleep_sum_key", "core_sleep_deep_count_key", "core_sleep_fall_time_key", "core_sleep_go_bed_time_key", "core_sleep_wake_up_time_key", "core_sleep_efficiency_key", "core_sleep_latency_key", "core_sleep_score_key", "core_sleep_snore_freq_key", "core_sleep_wake_count_key", "core_sleep_noon_sleep_count_key", "sleep_device_category_key"};

    /* JADX INFO: Access modifiers changed from: private */
    public int e(int i) {
        if (i != 13) {
            return i != 59 ? 0 : 2;
        }
        return 1;
    }

    private pwx(Context context) {
        if (context == null) {
            LogUtil.h("SleepAdviceMgr", "SleepAdviceMgr context null");
        } else {
            this.d = context.getApplicationContext();
        }
    }

    public static pwx d(Context context) {
        return new pwx(context);
    }

    public void a(Date date, int i, CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("SleepAdviceMgr", "enter getSleepAdvice() ");
        a();
        e(date, i, commonUiBaseResponse);
        LogUtil.a("SleepAdviceMgr", "leave getSleepAdvice()");
    }

    public void c(Date date, Date date2, CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("SleepAdviceMgr", "enter getSleepAdvice() ");
        a();
        b(date, date2, commonUiBaseResponse);
        LogUtil.a("SleepAdviceMgr", "leave getSleepAdvice()");
    }

    private void b(Date date, Date date2, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("SleepAdviceMgr", "enter getSleepAdviceFromLib() ");
        long n = jec.n(date2) * 1000;
        long n2 = jec.n(date) * 1000;
        LogUtil.a("SleepAdviceMgr", "startTime = ", Long.valueOf(n2), " endTime = ", Long.valueOf(n));
        d(n2, n, 30, new IBaseResponseCallback() { // from class: pwy
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                pwx.this.b(commonUiBaseResponse, i, obj);
            }
        });
    }

    private void e(Date date, int i, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("SleepAdviceMgr", "enter getSleepAdviceFromLib() ");
        long n = jec.n(date) * 1000;
        long j = n - (i * 86400000);
        LogUtil.a("SleepAdviceMgr", "startTime = ", Long.valueOf(j), " endTime = ", Long.valueOf(n));
        c(j, n, i, new IBaseResponseCallback() { // from class: pwz
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                pwx.this.c(commonUiBaseResponse, i2, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void c(int i, Object obj, CommonUiBaseResponse commonUiBaseResponse) {
        if (i == 0 && (obj instanceof String)) {
            String str = (String) obj;
            ReleaseLogUtil.e("R_SleepAdviceMgr", "suggestResponseOnLib to enter");
            LogUtil.a("SleepAdviceMgr", "getSleepAdviceFromLib contentValue = ", str);
            String sleepAdviceOriginalInterface = ((SleepLabelApi) Services.c("SleepLabelAlgorithm", SleepLabelApi.class)).getSleepAdviceOriginalInterface(null, str);
            LogUtil.a("SleepAdviceMgr", "getSleepAdviceFromLib ret = ", sleepAdviceOriginalInterface);
            if (TextUtils.isEmpty(sleepAdviceOriginalInterface)) {
                LogUtil.h("SleepAdviceMgr", "strAdvice is Empty");
                commonUiBaseResponse.onResponse(100001, null);
                return;
            }
            CoreSleepTotalData coreSleepTotalData = (CoreSleepTotalData) new Gson().fromJson(sleepAdviceOriginalInterface, CoreSleepTotalData.class);
            if (coreSleepTotalData == null || coreSleepTotalData.getAdNum0() == 0 || coreSleepTotalData.getAdNum1() == 0) {
                LogUtil.h("SleepAdviceMgr", "strAdvice is Empty");
                commonUiBaseResponse.onResponse(100001, null);
                return;
            } else {
                commonUiBaseResponse.onResponse(0, coreSleepTotalData);
                return;
            }
        }
        commonUiBaseResponse.onResponse(i, null);
    }

    private void d(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        long j3 = j - (i * 86400000);
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j3);
        hiAggregateOption.setEndTime(j2);
        LogUtil.a("SleepAdviceMgr", "getCoreSleepDataFromStorage optionStartTime time =  ", Long.valueOf(j3), " end time = ", Long.valueOf(j2));
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(f16315a);
        hiAggregateOption.setType(e);
        hiAggregateOption.setReadType(0);
        c(j, i, iBaseResponseCallback, hiAggregateOption);
    }

    private void c(final long j, final int i, final IBaseResponseCallback iBaseResponseCallback, HiAggregateOption hiAggregateOption) {
        HiHealthManager.d(this.d).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: pwx.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (!pwx.this.d(list, i, j)) {
                    JSONObject d = pwx.this.d(list, 2, j, iBaseResponseCallback);
                    if (d == null) {
                        return;
                    }
                    iBaseResponseCallback.d(0, d.toString());
                    return;
                }
                iBaseResponseCallback.d(-1, null);
            }
        });
    }

    private void c(long j, final long j2, final int i, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("SleepAdviceMgr", "getCoreSleepDataFromStorage start time =  ", Long.valueOf(j), " end time = ", Long.valueOf(j2));
        HiHealthManager.d(this.d).aggregateHiHealthData(b(j, j2), new HiAggregateListener() { // from class: pwx.4
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (pwx.this.d(list, i, j2)) {
                    iBaseResponseCallback.d(-1, null);
                    return;
                }
                pwx pwxVar = pwx.this;
                JSONObject d = pwxVar.d(list, pwxVar.e(i), j2, iBaseResponseCallback);
                if (d == null) {
                    return;
                }
                iBaseResponseCallback.d(0, d.toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(List<HiHealthData> list, int i, long j) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("SleepAdviceMgr", "on success() sumList = null");
            return true;
        }
        if (i != 7 || e(list, j)) {
            return false;
        }
        LogUtil.h("SleepAdviceMgr", "currentDay score = 0, return");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject d(List<HiHealthData> list, int i, long j, IBaseResponseCallback iBaseResponseCallback) {
        int i2;
        int i3;
        int i4;
        Object[] objArr;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        int i5 = 2;
        char c = 1;
        int i6 = 0;
        try {
            jSONObject.put("type", i);
            jSONObject.put("currTime", e(j));
            int i7 = 0;
            while (i7 < list.size()) {
                HiHealthData hiHealthData = list.get(i7);
                if (hiHealthData == null) {
                    Object[] objArr2 = new Object[i5];
                    objArr2[i6] = "getJsonObject data is null, i = ";
                    objArr2[c] = Integer.valueOf(i7);
                    LogUtil.h("SleepAdviceMgr", objArr2);
                    i2 = i6;
                    i4 = i7;
                } else {
                    long j2 = (long) hiHealthData.getDouble("core_sleep_fall_time_key");
                    int i8 = i7;
                    try {
                        long j3 = (long) hiHealthData.getDouble("core_sleep_wake_up_time_key");
                        int i9 = hiHealthData.getInt("core_sleep_shallow_key");
                        int i10 = hiHealthData.getInt("core_sleep_deep_key");
                        int i11 = hiHealthData.getInt("core_sleep_dream_key");
                        float f = hiHealthData.getFloat("core_sleep_sum_key");
                        if (fcj.e(hiHealthData, (List<HiHealthData>) null) != SleepViewConstants.SleepTypeEnum.SCIENCE) {
                            i4 = i8;
                            i2 = 0;
                        } else {
                            if (f <= 0.0f) {
                                try {
                                    objArr = new Object[1];
                                    i3 = 0;
                                } catch (JSONException e2) {
                                    e = e2;
                                    i3 = 0;
                                }
                                try {
                                    objArr[0] = "getJsonObject sleepSumTime <= 0";
                                    LogUtil.h("SleepAdviceMgr", objArr);
                                } catch (JSONException e3) {
                                    e = e3;
                                    i2 = i3;
                                    Object[] objArr3 = new Object[2];
                                    objArr3[i2] = "getCoreSleepDataFromStorage JSONException ";
                                    objArr3[1] = e.getMessage();
                                    LogUtil.b("SleepAdviceMgr", objArr3);
                                    return jSONObject;
                                }
                            } else {
                                i3 = 0;
                                if ((i9 > 0 || i10 > 0 || i11 > 0) && (i == 2 || i9 <= 0 || i10 != 0 || i11 != 0)) {
                                    int b = b(i10, i9, f);
                                    i4 = i8;
                                    i2 = 0;
                                    try {
                                        jSONArray.put(d(list, i8, b, j2, j3));
                                    } catch (JSONException e4) {
                                        e = e4;
                                        Object[] objArr32 = new Object[2];
                                        objArr32[i2] = "getCoreSleepDataFromStorage JSONException ";
                                        objArr32[1] = e.getMessage();
                                        LogUtil.b("SleepAdviceMgr", objArr32);
                                        return jSONObject;
                                    }
                                }
                            }
                            i4 = i8;
                            i2 = i3;
                        }
                    } catch (JSONException e5) {
                        e = e5;
                        i2 = 0;
                    }
                }
                i7 = i4 + 1;
                i6 = i2;
                i5 = 2;
                c = 1;
            }
        } catch (JSONException e6) {
            e = e6;
            i2 = i6;
        }
        if (jSONArray.isNull(i6)) {
            iBaseResponseCallback.d(-1, null);
            return null;
        }
        jSONObject.put("sleepInfoArr", jSONArray);
        jSONObject2.put("sex", this.c);
        jSONObject.put("registerInfo", jSONObject2);
        return jSONObject;
    }

    private int b(int i, int i2, float f) {
        return ((Integer) ResultUtils.a(Integer.valueOf((100 - Math.round((i2 / f) * 100.0f)) - Math.round((i / f) * 100.0f)))).intValue();
    }

    private JSONObject d(List<HiHealthData> list, int i, int i2, long j, long j2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String timeZone = list.get(i).getTimeZone();
        jSONObject.put("rdi", list.get(i).getInt("core_sleep_snore_freq_key"));
        if (i2 != 0) {
            jSONObject.put("remScale", i2);
        }
        jSONObject.put("allSleepTime", list.get(i).getInt("core_sleep_sum_key"));
        jSONObject.put("awakeTime", list.get(i).getDouble("core_sleep_wake_key"));
        jSONObject.put("deepSleepPartCnt", list.get(i).getInt("core_sleep_deep_count_key"));
        jSONObject.put("deepSleepTime", list.get(i).getInt("core_sleep_deep_key"));
        jSONObject.put("efficiency", list.get(i).getInt("core_sleep_efficiency_key"));
        jSONObject.put("fallAsleepTime", c(j, timeZone));
        jSONObject.put("latency", list.get(i).getInt("core_sleep_latency_key"));
        jSONObject.put(JsUtil.SCORE, list.get(i).getInt("core_sleep_score_key"));
        jSONObject.put("time", c(list.get(i).getStartTime(), timeZone));
        jSONObject.put("wakeUpTime", c(j2, timeZone));
        jSONObject.put("awakeCnt", list.get(i).getInt("core_sleep_wake_count_key"));
        jSONObject.put("snoreCnt", list.get(i).getInt("core_sleep_snore_freq_key"));
        return jSONObject;
    }

    private HiAggregateOption b(long j, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(f16315a);
        hiAggregateOption.setType(e);
        hiAggregateOption.setReadType(0);
        return hiAggregateOption;
    }

    private boolean e(List<HiHealthData> list, long j) {
        for (HiHealthData hiHealthData : list) {
            if (HiDateUtil.c(hiHealthData.getStartTime()) == HiDateUtil.c(j) && hiHealthData.getInt("core_sleep_score_key") > 0) {
                return true;
            }
        }
        return false;
    }

    private void a() {
        LogUtil.a("SleepAdviceMgr", "enter getUserInfo");
        HiHealthNativeApi.a(this.d).fetchUserData(new HiCommonListener() { // from class: pwx.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (obj == null) {
                    LogUtil.h("SleepAdviceMgr", "getUserInfo data is null");
                    return;
                }
                List list = (List) obj;
                if (list.size() > 0) {
                    HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                    pwx.this.c = hiUserInfo.getGender();
                    if (pwx.this.c == 1) {
                        pwx.this.c = 0;
                    } else {
                        pwx.this.c = 1;
                    }
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.a("SleepAdviceMgr", "get user info errCode = ", Integer.valueOf(i), " errMsg = ", obj);
            }
        });
    }

    private long e(long j) {
        return j + ((long) (b(HiDateUtil.d((String) null)) * 60.0f * 60.0f * 1000.0f));
    }

    private long c(long j, String str) {
        return j + ((long) (b(str) * 60.0f * 60.0f * 1000.0f));
    }

    private float b(String str) {
        int i = 1;
        if (str.contains(Marker.ANY_NON_NULL_MARKER)) {
            str = str.substring(1);
        } else if (str.contains(Constants.LINK)) {
            str = str.substring(1);
            i = -1;
        } else {
            LogUtil.h("SleepAdviceMgr", "time format is not ok. ", str);
        }
        return ((CommonUtil.m(this.d, str) / 100.0f) + ((r4 % 100) / 60.0f)) * i;
    }
}
