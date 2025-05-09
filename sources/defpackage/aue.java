package defpackage;

import android.text.TextUtils;
import com.huawei.basichealthmodel.cloud.HealthModelParamsFactory;
import com.huawei.basichealthmodel.cloud.bean.Record;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class aue extends HwBaseManager {
    private static volatile aue c;
    private final ParamsFactory d;

    private aue() {
        super(BaseApplication.e());
        this.d = new HealthModelParamsFactory(BaseApplication.e());
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return Integer.valueOf(CapabilityStatus.AWA_CAP_CODE_APPLICATION);
    }

    public static aue e() {
        if (c == null) {
            synchronized (aue.class) {
                if (c == null) {
                    c = new aue();
                }
            }
        }
        return c;
    }

    private <T> T a(String str, Map<String, String> map, String str2, Class<T> cls) {
        try {
            return (T) lqi.d().d(str, map, str2, cls);
        } catch (OutOfMemoryError e) {
            LogUtil.b("HealthLife_HealthModelCloudManager", "callHttpPost error ", LogAnonymous.b((Throwable) e), " url ", str, " headers ", map, " body ", str2, " responseType ", cls);
            return null;
        }
    }

    private <T> void d(String str, Map<String, String> map, String str2, Class<T> cls, ResultCallback<T> resultCallback) {
        try {
            lqi.d().b(str, map, str2, cls, resultCallback);
        } catch (OutOfMemoryError e) {
            LogUtil.b("HealthLife_HealthModelCloudManager", "callHttpPost error ", LogAnonymous.b((Throwable) e), " url ", str, " headers ", map, " body ", str2, " responseType ", cls, " callback ", resultCallback);
        }
    }

    public void e(ResultCallback<aur> resultCallback) {
        auk aukVar = new auk();
        String b = lql.b(this.d.getBody(aukVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "getHealthLife body ", b);
        d(aukVar.getUrl(), this.d.getHeaders(), b, aur.class, resultCallback);
    }

    public aur a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "getHealthLifeSync host ", str);
            return null;
        }
        String b = lql.b(this.d.getBody(new auf()));
        LogUtil.a("HealthLife_HealthModelCloudManager", "getHealthLifeSync body ", b);
        return (aur) a(str + "/achievement/getHealthLife", this.d.getHeaders(), b, aur.class);
    }

    public void b(boolean z, List<Record> list, ResultCallback<auh> resultCallback) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "addHealthLifeConfig records ", list, " isUpgrade ", Boolean.valueOf(z));
            return;
        }
        aum aumVar = new aum();
        aumVar.e(list);
        aumVar.b(System.currentTimeMillis());
        if (z) {
            aumVar.a(1);
        }
        String b = lql.b(this.d.getBody(aumVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "addHealthLifeConfig body ", b);
        d(aumVar.getUrl(), this.d.getHeaders(), b, auh.class, resultCallback);
    }

    public auo c(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "getHealthLifeConfigSync host ", str, " startTime ", Integer.valueOf(i), " endTime ", Integer.valueOf(i2));
            return null;
        }
        aui auiVar = new aui();
        auiVar.d(i);
        auiVar.c(i2);
        String b = lql.b(this.d.getBody(auiVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "getHealthLifeConfigSync body ", b);
        return (auo) a(str + "/achievement/getHealthLifeConfig", this.d.getHeaders(), b, auo.class);
    }

    public auh e(String str, List<Record> list) {
        if (TextUtils.isEmpty(str) || koq.b(list)) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "addHealthLifeRecordSync host ", str, " records ", list);
            return null;
        }
        aup aupVar = new aup();
        aupVar.e(list);
        String b = lql.b(this.d.getBody(aupVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "addHealthLifeRecordSync body ", b);
        return (auh) a(str + "/achievement/addHealthLifeRecord", this.d.getHeaders(), b, auh.class);
    }

    public auj e(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "getHealthLifeRecordSync host ", str, " startTime ", Integer.valueOf(i), " endTime ", Integer.valueOf(i2));
            return null;
        }
        aui auiVar = new aui();
        auiVar.d(i);
        auiVar.c(i2);
        String b = lql.b(this.d.getBody(auiVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "getHealthLifeRecordSync body ", b);
        return (auj) a(str + "/achievement/getHealthLifeRecord", this.d.getHeaders(), b, auj.class);
    }

    public auj c(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "getHealthLifeRecordByVersionSync host ", str, " version ", Long.valueOf(j));
            return null;
        }
        aui auiVar = new aui();
        auiVar.b(j);
        String b = lql.b(this.d.getBody(auiVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "getHealthLifeRecordByVersionSync body ", b);
        return (auj) a(str + "/achievement/getHealthLifeRecordByVersion", this.d.getHeaders(), b, auj.class);
    }

    public void a(List<Integer> list, List<Integer> list2, ResultCallback<aun> resultCallback) {
        auq auqVar = new auq();
        if (koq.c(list)) {
            auqVar.d(list);
        }
        if (koq.c(list2)) {
            auqVar.c(list2);
        }
        String b = lql.b(this.d.getBody(auqVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "getHealthLifeStat body ", b);
        d(auqVar.getUrl(), this.d.getHeaders(), b, aun.class, resultCallback);
    }

    public auh e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "getHealthLifeLastVersionSync host ", str);
            return null;
        }
        String b = lql.b(this.d.getBody(new auf()));
        LogUtil.a("HealthLife_HealthModelCloudManager", "getHealthLifeLastVersionSync body ", b);
        return (auh) a(str + "/achievement/getHealthLifeLastVersion", this.d.getHeaders(), b, auh.class);
    }

    public void a(int i, int i2, ResultCallback<auh> resultCallback) {
        auv auvVar = new auv();
        auvVar.d(i);
        auvVar.a(i2);
        String b = lql.b(this.d.getBody(auvVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "updateHealthLifeChallenge body ", b);
        d(auvVar.getUrl(), this.d.getHeaders(), b, auh.class, resultCallback);
    }

    public void d(int i, int i2, ResultCallback<aul> resultCallback) {
        aud audVar = new aud();
        audVar.d(i);
        audVar.b(i2);
        String b = lql.b(this.d.getBody(audVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "getHealthLifeChallenges body ", b);
        d(audVar.getUrl(), this.d.getHeaders(), b, aul.class, resultCallback);
    }

    public void d(long j, ResultCallback<dsn> resultCallback) {
        if (resultCallback == null) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "queryWeekHealthReport callback is null");
            return;
        }
        dsj dsjVar = new dsj();
        dsjVar.c("week");
        dsjVar.b(j);
        String b = lql.b(this.d.getBody(dsjVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "queryWeekHealthReport body ", b);
        d(dsjVar.getUrl(), this.d.getHeaders(), b, dsn.class, resultCallback);
    }

    public void c(ResultCallback<dsc> resultCallback) {
        if (resultCallback == null) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "getDoctorBasicInfo callback is null");
            return;
        }
        dsd dsdVar = new dsd();
        String b = lql.b(this.d.getBody(dsdVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "getDoctorBasicInfo body ", b);
        d(dsdVar.getUrl(), this.d.getHeaders(), b, dsc.class, resultCallback);
    }

    public void a(ResultCallback<dsg> resultCallback) {
        if (resultCallback == null) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "getDoctorImInfo callback is null");
            return;
        }
        dsi dsiVar = new dsi();
        String b = lql.b(this.d.getBody(dsiVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "getDoctorImInfo body ", b);
        d(dsiVar.getUrl(), this.d.getHeaders(), b, dsg.class, resultCallback);
    }

    public void d(ResultCallback<dso> resultCallback) {
        if (resultCallback == null) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "queryInterventionPlanInfo callback is null");
            return;
        }
        dsk dskVar = new dsk();
        String b = lql.b(this.d.getBody(dskVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "queryInterventionPlanInfo body ", b);
        d(dskVar.getUrl(), this.d.getHeaders(), b, dso.class, resultCallback);
    }

    public void b(String str, String str2, ResultCallback<dsh> resultCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || resultCallback == null) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "getUserSampleConfigList type ", str, " id ", str2, " callback ", resultCallback);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new drz(str, str2));
        dsf dsfVar = new dsf(arrayList);
        try {
            String b = lql.b(this.d.getBody(dsfVar));
            LogUtil.a("HealthLife_HealthModelCloudManager", "getUserSampleConfigList body ", b);
            d(dsfVar.getUrl(), this.d.getHeaders(), b, dsh.class, resultCallback);
        } catch (NullPointerException e) {
            LogUtil.b("HealthLife_HealthModelCloudManager", "getUserSampleConfigList NullPointerException");
            resultCallback.onFailure(e);
        }
    }

    public void e(List<Integer> list, ResultCallback<auu> resultCallback) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_HealthModelCloudManager", "getHealthLifeConDays ids ", list, " callback ", resultCallback);
            return;
        }
        aut autVar = new aut();
        autVar.c(list);
        String b = lql.b(this.d.getBody(autVar));
        LogUtil.a("HealthLife_HealthModelCloudManager", "getHealthLifeConDays body ", b);
        d(autVar.getUrl(), this.d.getHeaders(), b, auu.class, resultCallback);
    }
}
