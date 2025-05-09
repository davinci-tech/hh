package com.huawei.basichealthmodel;

import android.os.RemoteException;
import android.util.SparseArray;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.IHealthDataOpenInterface;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.aus;
import defpackage.auz;
import defpackage.awq;
import defpackage.azi;
import defpackage.bax;
import defpackage.jdn;
import health.compact.a.LogAnonymous;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class DeveloperKitProxy extends IHealthDataOpenInterface.Stub {
    @Override // com.huawei.health.IHealthDataOpenInterface
    public void getHealthData(long j, long j2, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        LogUtil.a("HealthLife_DeveloperKitProxy", "getHealthData startTime ", Long.valueOf(j), " endTime ", Long.valueOf(j2), " callback ", iBaseCommonCallback);
        if (iBaseCommonCallback == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HealthLife_DeveloperKitProxy", "getHealthData currentTime ", Long.valueOf(currentTimeMillis));
        if (j < 0 || j2 <= 0 || j > j2 || j > currentTimeMillis) {
            iBaseCommonCallback.onResponse(2, "getHealthData param invalid");
            return;
        }
        int t = azi.t();
        LogUtil.a("HealthLife_DeveloperKitProxy", "getHealthData joinTime ", Integer.valueOf(t));
        if (t <= 0) {
            iBaseCommonCallback.onResponse(-2, "user not subscribed");
            return;
        }
        String b = b(Math.max(j, DateFormatUtil.c(t)), Math.min(j2, currentTimeMillis));
        LogUtil.a("HealthLife_DeveloperKitProxy", "getHealthData result ", b);
        iBaseCommonCallback.onResponse(0, b);
    }

    private String b(long j, long j2) {
        HashMap<String, HealthLifeBean> hashMap = new HashMap<>();
        HashMap hashMap2 = new HashMap();
        Set<Integer> c = c(j, j2);
        String p = azi.p();
        Iterator<Integer> it = c.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            SparseArray<HealthLifeBean> mv_ = bax.mv_(aus.jS_(p, intValue, awq.e().kD_(), true));
            HealthLifeBean healthLifeBean = (HealthLifeBean) jdn.a(mv_.get(1));
            if (healthLifeBean != null) {
                healthLifeBean.setId(100001);
                mv_.put(100001, healthLifeBean);
            }
            int size = mv_.size();
            for (int i = 0; i < size; i++) {
                int keyAt = mv_.keyAt(i);
                HealthLifeBean healthLifeBean2 = mv_.get(keyAt);
                if (healthLifeBean2 != null) {
                    healthLifeBean2.setRecordDay(intValue);
                    if (healthLifeBean2.getAddStatus() == 1) {
                        hashMap.put(intValue + "_" + keyAt, healthLifeBean2);
                    } else {
                        hashMap2.put(intValue + "_" + keyAt, healthLifeBean2);
                    }
                }
            }
        }
        return d(hashMap, hashMap2, j, j2).toString();
    }

    private JSONArray d(HashMap<String, HealthLifeBean> hashMap, Map<String, HealthLifeBean> map, long j, long j2) {
        JSONArray jSONArray = new JSONArray();
        if (hashMap != null && hashMap.size() > 0) {
            List<HealthLifeBean> d = auz.d(DateFormatUtil.b(j), DateFormatUtil.b(j2), azi.p());
            HashMap hashMap2 = new HashMap();
            for (HealthLifeBean healthLifeBean : d) {
                if (healthLifeBean != null) {
                    hashMap2.put(healthLifeBean.getRecordDay() + "_" + healthLifeBean.getId(), healthLifeBean);
                }
            }
            for (Map.Entry<String, HealthLifeBean> entry : hashMap.entrySet()) {
                if (entry != null) {
                    HealthLifeBean healthLifeBean2 = (HealthLifeBean) hashMap2.get(entry.getKey());
                    if (healthLifeBean2 == null) {
                        healthLifeBean2 = a(entry.getValue());
                    }
                    jSONArray.put(c(healthLifeBean2));
                }
            }
        }
        if (map != null && map.size() > 0) {
            for (HealthLifeBean healthLifeBean3 : map.values()) {
                if (healthLifeBean3 != null) {
                    jSONArray.put(b(healthLifeBean3));
                }
            }
        }
        return jSONArray;
    }

    private Set<Integer> c(long j, long j2) {
        LogUtil.a("HealthLife_DeveloperKitProxy", "getDaysFromTimeStampRange startTime ", Long.valueOf(j), " endTime", Long.valueOf(j2));
        HashSet hashSet = new HashSet();
        while (j < j2 + 86400000) {
            hashSet.add(Integer.valueOf(DateFormatUtil.b(Math.min(j, j2))));
            j += 86400000;
        }
        return hashSet;
    }

    private HealthLifeBean a(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_DeveloperKitProxy", "emptyTaskRecordDbBean bean is null");
            return null;
        }
        HealthLifeBean healthLifeBean2 = new HealthLifeBean();
        healthLifeBean2.setComplete(0);
        healthLifeBean2.setRest(0);
        healthLifeBean2.setRecordDay(healthLifeBean.getRecordDay());
        healthLifeBean2.setTimestamp(healthLifeBean.getTimestamp());
        healthLifeBean2.setTimeZone(healthLifeBean.getTimeZone());
        healthLifeBean2.setId(healthLifeBean.getId());
        return healthLifeBean2;
    }

    private JSONObject c(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_DeveloperKitProxy", "subscribedTask2Json bean is null");
            return new JSONObject();
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("addStatus", 1);
            jSONObject.put("status", healthLifeBean.getComplete());
            jSONObject.put("restStatus", healthLifeBean.getRest());
            jSONObject.put(ParsedFieldTag.RECORD_DAY, healthLifeBean.getRecordDay());
            jSONObject.put("timestamp", healthLifeBean.getTimestamp());
            jSONObject.put(DBSessionCommon.COLUMN_TIME_ZONE, healthLifeBean.getTimeZone());
            jSONObject.put("id", healthLifeBean.getId());
            return jSONObject;
        } catch (JSONException e) {
            LogUtil.b("HealthLife_DeveloperKitProxy", "subscribedTask2Json exception ", LogAnonymous.b((Throwable) e));
            return new JSONObject();
        }
    }

    private JSONObject b(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_DeveloperKitProxy", "unSubscribedTask2Json bean is null");
            return new JSONObject();
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("addStatus", 0);
            jSONObject.put("status", 0);
            jSONObject.put("restStatus", 0);
            jSONObject.put(ParsedFieldTag.RECORD_DAY, healthLifeBean.getRecordDay());
            jSONObject.put("timestamp", healthLifeBean.getTimestamp());
            jSONObject.put(DBSessionCommon.COLUMN_TIME_ZONE, healthLifeBean.getTimeZone());
            jSONObject.put("id", healthLifeBean.getId());
            return jSONObject;
        } catch (JSONException e) {
            LogUtil.b("HealthLife_DeveloperKitProxy", "unSubscribedTask2Json exception ", LogAnonymous.b((Throwable) e));
            return new JSONObject();
        }
    }
}
