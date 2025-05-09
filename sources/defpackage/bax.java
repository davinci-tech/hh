package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.basichealthmodel.cloud.bean.Record;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class bax {
    public static String c(ArrayList<HealthLifeBean> arrayList) {
        if (koq.b(arrayList)) {
            return "";
        }
        Iterator<HealthLifeBean> it = arrayList.iterator();
        while (it.hasNext()) {
            HealthLifeBean next = it.next();
            if (next != null && next.getId() == 1) {
                return next.getTarget();
            }
        }
        return "";
    }

    public static HealthLifeBean mx_(SparseArray<HealthLifeBean> sparseArray) {
        if (sparseArray == null || sparseArray.size() == 0) {
            LogUtil.h("HealthLife_HealthTaskUtil", "getCloverData list is empty");
            return null;
        }
        Iterator<HealthLifeBean> it = mw_(sparseArray).iterator();
        while (it.hasNext()) {
            HealthLifeBean next = it.next();
            if (next != null && next.getId() == 1) {
                return next;
            }
        }
        return null;
    }

    public static SparseArray<HealthLifeBean> mv_(List<HealthLifeBean> list) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_HealthTaskUtil", "convertHealthLifeArray beanList is empty");
            return new SparseArray<>();
        }
        SparseArray<HealthLifeBean> sparseArray = new SparseArray<>();
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null) {
                sparseArray.put(healthLifeBean.getId(), healthLifeBean);
            }
        }
        LogUtil.a("HealthLife_HealthTaskUtil", "convertHealthLifeArray beanList: ", Integer.valueOf(list.size()));
        return sparseArray;
    }

    public static ArrayList<HealthLifeBean> mw_(SparseArray<HealthLifeBean> sparseArray) {
        if (sparseArray == null || sparseArray.size() == 0) {
            LogUtil.h("HealthLife_HealthTaskUtil", "convertHealthLifeList list is null.");
            return new ArrayList<>();
        }
        return (ArrayList) new Gson().fromJson(my_(sparseArray), new TypeToken<List<HealthLifeBean>>() { // from class: bax.1
        }.getType());
    }

    public static String my_(SparseArray<HealthLifeBean> sparseArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < sparseArray.size(); i++) {
            int keyAt = sparseArray.keyAt(i);
            HealthLifeBean healthLifeBean = sparseArray.get(keyAt);
            if (healthLifeBean != null) {
                if (keyAt == 5) {
                    healthLifeBean.setTarget("1");
                }
                if (keyAt != 4) {
                    arrayList.add(healthLifeBean);
                }
            }
        }
        return new Gson().toJson(arrayList);
    }

    public static Record b(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_HealthTaskUtil", "convertSubscriptionToRecordBean healthTaskBean is null.");
            return null;
        }
        int id = healthLifeBean.getId();
        String target = healthLifeBean.getTarget();
        if ((id == 11 || id == 12) && TextUtils.isEmpty(target)) {
            target = String.valueOf(0);
        }
        Record record = new Record();
        record.setId(id);
        record.setLastTarget(healthLifeBean.getLastTarget());
        record.setTarget(target);
        record.setRecordDay(healthLifeBean.getRecordDay());
        record.setTimeZone(healthLifeBean.getTimeZone());
        record.setTimestamp(healthLifeBean.getTimestamp());
        record.setDataSource(Integer.valueOf(healthLifeBean.getDataSource()));
        int taskType = healthLifeBean.getTaskType();
        record.setTaskType(taskType == 0 ? null : Integer.valueOf(taskType));
        int challengeId = healthLifeBean.getChallengeId();
        record.setChallengeId(challengeId >= 200001 ? Integer.valueOf(challengeId) : null);
        return record;
    }
}
