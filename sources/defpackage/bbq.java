package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class bbq {
    public static void c(final String str, final ResponseCallback<SparseArray<List<mdf>>> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("HealthLife_KakaUtils", "getTaskInfoList callback is null");
            return;
        }
        Context e = BaseApplication.e();
        if (!CommonUtil.aa(e)) {
            ReleaseLogUtil.a("HealthLife_KakaUtils", "getTaskInfoList not connected to the network");
            responseCallback.onResponse(-1, new SparseArray<>(16));
            return;
        }
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelKakaList_" + str, new Runnable() { // from class: bbo
                @Override // java.lang.Runnable
                public final void run() {
                    bbq.c(str, (ResponseCallback<SparseArray<List<mdf>>>) responseCallback);
                }
            });
            return;
        }
        Task<List<mdf>> taskInfoList = bzw.e().getTaskInfoList(e, 1);
        if (taskInfoList == null) {
            ReleaseLogUtil.a("HealthLife_KakaUtils", "getTaskInfoList task is null");
            responseCallback.onResponse(-1, new SparseArray<>(16));
        } else {
            taskInfoList.addOnSuccessListener(new OnSuccessListener() { // from class: bbp
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    bbq.e(ResponseCallback.this, str, (List) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: bbn
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    bbq.b(ResponseCallback.this, exc);
                }
            });
        }
    }

    static /* synthetic */ void e(ResponseCallback responseCallback, String str, List list) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("HealthLife_KakaUtils", "getTaskInfoList kakaTaskInfoList ", list);
            responseCallback.onResponse(-1, new SparseArray(16));
        } else {
            LogUtil.a("HealthLife_KakaUtils", "getTaskInfoList kakaTaskInfoList ", list);
            d("HealthModelKakaList_" + str, (List<mdf>) list, (ResponseCallback<SparseArray<List<mdf>>>) responseCallback);
        }
    }

    static /* synthetic */ void b(ResponseCallback responseCallback, Exception exc) {
        ReleaseLogUtil.a("HealthLife_KakaUtils", "getTaskInfoList addOnFailureListener consecutiveDays ", Integer.valueOf(azi.a(false)));
        responseCallback.onResponse(-1, new SparseArray(16));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final String str, final List<mdf> list, final ResponseCallback<SparseArray<List<mdf>>> responseCallback) {
        String h;
        int d;
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), str, new Runnable() { // from class: bbv
                @Override // java.lang.Runnable
                public final void run() {
                    bbq.d(str, (List<mdf>) list, (ResponseCallback<SparseArray<List<mdf>>>) responseCallback);
                }
            });
            return;
        }
        List<mdf> b = b(list);
        String p = azi.p();
        List<HealthLifeBean> c = c(1, p);
        List<HealthLifeBean> c2 = c(2, p);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (mdf mdfVar : b) {
            if (mdfVar != null && !"2".equals(mdfVar.p()) && (d = d((h = mdfVar.h()), c, c2)) >= 0) {
                mdfVar.d(d);
                if ("4414136".equals(h) || "4587804".equals(h)) {
                    if (!arrayList2.contains(mdfVar)) {
                        arrayList2.add(mdfVar);
                    }
                } else if (!arrayList.contains(mdfVar)) {
                    arrayList.add(mdfVar);
                }
            }
        }
        if (koq.c(arrayList)) {
            mdf mdfVar2 = new mdf();
            mdfVar2.e("2332598");
            arrayList.add(0, mdfVar2);
        }
        SparseArray<List<mdf>> sparseArray = new SparseArray<>();
        sparseArray.append(0, arrayList);
        sparseArray.append(1, arrayList2);
        responseCallback.onResponse(0, sparseArray);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static List<mdf> b(List<mdf> list) {
        char c;
        ArrayList arrayList = new ArrayList();
        for (mdf mdfVar : list) {
            if (mdfVar == null) {
                ReleaseLogUtil.a("HealthLife_KakaUtils", "getKakaTaskInfoListForGrowthValue kakaTaskInfo is null");
            } else {
                String h = mdfVar.h();
                if (TextUtils.isEmpty(h)) {
                    ReleaseLogUtil.a("HealthLife_KakaUtils", "getKakaTaskInfoListForGrowthValue taskId ", h);
                } else {
                    h.hashCode();
                    switch (h.hashCode()) {
                        case 326722737:
                            if (h.equals("4018400")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 357944950:
                            if (h.equals("4142595")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 388571989:
                            if (h.equals("4267785")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 411564870:
                            if (h.equals("4304180")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 441117393:
                            if (h.equals("4414136")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 476307196:
                            if (h.equals("4587804")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    if (c == 0 || c == 1 || c == 2 || c == 3 || c == 4 || c == 5) {
                        arrayList.add(mdfVar);
                        bao.b("health_model_kaka_task_reward_" + h, String.valueOf(mdfVar.n()));
                        bao.b("health_model_kaka_continuous_" + h, String.valueOf(mdfVar.y()));
                    }
                }
            }
        }
        return arrayList;
    }

    private static int d(String str, List<HealthLifeBean> list, List<HealthLifeBean> list2) {
        char c = 65535;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode != 326722737) {
            if (hashCode != 441117393) {
                if (hashCode == 476307196 && str.equals("4587804")) {
                    c = 2;
                }
            } else if (str.equals("4414136")) {
                c = 1;
            }
        } else if (str.equals("4018400")) {
            c = 0;
        }
        if (c == 0) {
            int a2 = azi.a(true);
            LogUtil.a("HealthLife_KakaUtils", "getTaskStatus consecutiveDays ", Integer.valueOf(a2));
            return Integer.compare(a2, 2);
        }
        if (c == 1 || c == 2) {
            return 0;
        }
        return c(str, list, list2);
    }

    private static List<HealthLifeBean> c(int i, String str) {
        int e = awq.e().e(DateFormatUtil.b(System.currentTimeMillis()));
        int b = DateFormatUtil.b(jdl.d(DateFormatUtil.c(e), -i));
        LogUtil.a("HealthLife_KakaUtils", "getTaskRecordBeanList day ", Integer.valueOf(b), ",recordDay=", Integer.valueOf(e));
        return auz.d(b, b, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int c(String str, List<HealthLifeBean> list, List<HealthLifeBean> list2) {
        int b = b(str);
        int i = -1;
        int i2 = 1;
        while (true) {
            if (i2 <= 2) {
                boolean z = i2 == 1 ? 1 : 0;
                boolean b2 = azi.b(z != 0 ? list : list2, b);
                LogUtil.a("HealthLife_KakaUtils", "getTaskStatus isYesterday ", Boolean.valueOf(z), " isCompleted ", Boolean.valueOf(b2), " type ", Integer.valueOf(b));
                if (!b2) {
                    LogUtil.h("HealthLife_KakaUtils", "getTaskStatus taskStatus ", Integer.valueOf(i));
                    break;
                }
                i = !z;
                i2++;
            } else {
                break;
            }
        }
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int b(String str) {
        char c;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_KakaUtils", "getTaskType taskId ", str);
            return -1;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -497947691:
                if (str.equals("3279864")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -470273800:
                if (str.equals("3368659")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -445336695:
                if (str.equals("3428891")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 357944950:
                if (str.equals("4142595")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 388571989:
                if (str.equals("4267785")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 411564870:
                if (str.equals("4304180")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c != 3) {
                        if (c != 4) {
                            if (c != 5) {
                                return -1;
                            }
                        }
                    }
                }
                return 2;
            }
            return 3;
        }
        return 1;
    }
}
