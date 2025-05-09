package defpackage;

import android.text.TextUtils;
import com.huawei.basichealthmodel.cloud.bean.Record;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.LogAnonymous;
import java.util.List;

/* loaded from: classes3.dex */
public class aug {
    private static volatile aug e;

    private aug() {
    }

    public static aug d() {
        if (e == null) {
            synchronized (aug.class) {
                if (e == null) {
                    e = new aug();
                }
            }
        }
        return e;
    }

    public void e(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        aue.e().e(new ResultCallback<aur>() { // from class: aug.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(aur aurVar) {
                iBaseResponseCallback.d(0, aurVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a("HealthLife_HealthModelCloudHelper", "getHealthLife ", LogAnonymous.b(th));
                iBaseResponseCallback.d(-1, null);
            }
        });
    }

    public void d(boolean z, List<Record> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        aue.e().b(z, list, new ResultCallback<auh>() { // from class: aug.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(auh auhVar) {
                iBaseResponseCallback.d(0, auhVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a("HealthLife_HealthModelCloudHelper", "addHealthLifeConfig ", LogAnonymous.b(th));
                iBaseResponseCallback.d(-1, null);
            }
        });
    }

    public void c(List<Integer> list, List<Integer> list2, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        aue.e().a(list, list2, new ResultCallback<aun>() { // from class: aug.2
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(aun aunVar) {
                if (aunVar == null || aunVar.a() != 0) {
                    LogUtil.h("HealthLife_HealthModelCloudHelper", "getHealthLifeStat fail");
                    iBaseResponseCallback.d(-1, null);
                } else {
                    iBaseResponseCallback.d(0, aunVar);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a("HealthLife_HealthModelCloudHelper", "getHealthLifeStat ", LogAnonymous.b(th));
                iBaseResponseCallback.d(-1, null);
            }
        });
    }

    public void c(int i, int i2, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        aue.e().a(i, i2, new ResultCallback<auh>() { // from class: aug.6
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(auh auhVar) {
                if (auhVar == null || auhVar.a() != 0) {
                    LogUtil.h("HealthLife_HealthModelCloudHelper", "updateHealthLifeChallenge fail");
                    iBaseResponseCallback.d(-1, null);
                } else {
                    iBaseResponseCallback.d(0, auhVar);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a("HealthLife_HealthModelCloudHelper", "updateHealthLifeChallenge ", LogAnonymous.b(th));
                iBaseResponseCallback.d(-1, null);
            }
        });
    }

    public void d(long j, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HealthLife_HealthModelCloudHelper", "queryWeekHealthReport callback is null");
        } else {
            aue.e().d(j, new ResultCallback<dsn>() { // from class: aug.10
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(dsn dsnVar) {
                    if (dsnVar == null || dsnVar.c() != 0) {
                        LogUtil.h("HealthLife_HealthModelCloudHelper", "queryWeekHealthReport onSuccess response ", dsnVar);
                        iBaseResponseCallback.d(0, null);
                    } else {
                        iBaseResponseCallback.d(0, dsnVar);
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    LogUtil.h("HealthLife_HealthModelCloudHelper", "queryWeekHealthReport onFailure ", LogAnonymous.b(th));
                    iBaseResponseCallback.d(-1, null);
                }
            });
        }
    }

    public void a(final ResponseCallback<dsc> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_HealthModelCloudHelper", "getDoctorBasicInfo callback is null");
        } else {
            aue.e().c(new ResultCallback<dsc>() { // from class: aug.8
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(dsc dscVar) {
                    if (dscVar == null || dscVar.h() != 0) {
                        LogUtil.h("HealthLife_HealthModelCloudHelper", "getDoctorBasicInfo onSuccess response ", dscVar);
                        responseCallback.onResponse(-1, null);
                    } else {
                        responseCallback.onResponse(0, dscVar);
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    LogUtil.h("HealthLife_HealthModelCloudHelper", "getDoctorBasicInfo onFailure ", LogAnonymous.b(th));
                    responseCallback.onResponse(-1, null);
                }
            });
        }
    }

    public void d(final ResponseCallback<dsg> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_HealthModelCloudHelper", "getDoctorImInfo callback is null");
        } else {
            aue.e().a(new ResultCallback<dsg>() { // from class: aug.7
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(dsg dsgVar) {
                    if (dsgVar == null || dsgVar.c() != 0) {
                        LogUtil.h("HealthLife_HealthModelCloudHelper", "getDoctorImInfo onSuccess response ", dsgVar);
                        responseCallback.onResponse(-1, null);
                    } else {
                        responseCallback.onResponse(0, dsgVar);
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    LogUtil.h("HealthLife_HealthModelCloudHelper", "getDoctorImInfo onFailure ", LogAnonymous.b(th));
                    responseCallback.onResponse(-1, null);
                }
            });
        }
    }

    public void c(final ResponseCallback<dso> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_HealthModelCloudHelper", "queryInterventionPlanInfo callback is null");
        } else {
            aue.e().d(new ResultCallback<dso>() { // from class: aug.9
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(dso dsoVar) {
                    if (dsoVar == null || dsoVar.a() != 0) {
                        LogUtil.h("HealthLife_HealthModelCloudHelper", "queryInterventionPlanInfo onSuccess response ", dsoVar);
                        responseCallback.onResponse(-1, null);
                    } else {
                        responseCallback.onResponse(0, dsoVar);
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    LogUtil.h("HealthLife_HealthModelCloudHelper", "queryInterventionPlanInfo onFailure ", LogAnonymous.b(th));
                    responseCallback.onResponse(-1, null);
                }
            });
        }
    }

    public void c(String str, String str2, final IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || iBaseResponseCallback == null) {
            LogUtil.h("HealthLife_HealthModelCloudHelper", "getUserSampleConfigList type ", str, " id ", str2, " callback ", iBaseResponseCallback);
        } else {
            aue.e().b(str, str2, new ResultCallback<dsh>() { // from class: aug.5
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(dsh dshVar) {
                    if (dshVar == null || dshVar.b() != 0) {
                        LogUtil.h("HealthLife_HealthModelCloudHelper", "getUserSampleConfigList onSuccess response ", dshVar);
                        iBaseResponseCallback.d(-1, null);
                    } else {
                        iBaseResponseCallback.d(0, dshVar.e());
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    LogUtil.h("HealthLife_HealthModelCloudHelper", "getUserSampleConfigList onFailure ", LogAnonymous.b(th));
                    iBaseResponseCallback.d(-1, null);
                }
            });
        }
    }

    public void d(List<Integer> list, final ResponseCallback<dsa> responseCallback) {
        aue.e().e(list, new ResultCallback<auu>() { // from class: aug.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(auu auuVar) {
                if (auuVar == null || auuVar.a() != 0) {
                    LogUtil.h("HealthLife_HealthModelCloudHelper", "getHealthLifeConDays onSuccess response ", auuVar);
                    responseCallback.onResponse(-1, null);
                    return;
                }
                dsa dsaVar = new dsa();
                dsaVar.b(DateFormatUtil.b(System.currentTimeMillis()));
                dsaVar.c(auuVar.e());
                dsaVar.b(auuVar.b());
                bda.d(dsaVar);
                LogUtil.a("HealthLife_HealthModelCloudHelper", "HealthTaskConsResponse is ", auuVar.toString());
                responseCallback.onResponse(0, dsaVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HealthLife_HealthModelCloudHelper", "getHealthLifeConDays onFailure ", LogAnonymous.b(th));
                responseCallback.onResponse(-1, null);
            }
        });
    }
}
