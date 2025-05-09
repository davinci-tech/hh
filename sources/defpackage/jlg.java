package defpackage;

import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jlg {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13928a = new Object();
    private static jlg b;

    private jlg() {
    }

    public static jlg d() {
        jlg jlgVar;
        synchronized (f13928a) {
            if (b == null) {
                b = new jlg();
            }
            jlgVar = b;
        }
        return jlgVar;
    }

    public void d(int i) {
        ReleaseLogUtil.e("DEVMGR_RriHeartRateSendCommandUtil", "pushPressAutoMonitor openOrClose:", Integer.valueOf(i));
        if (i != 1 && i != 2) {
            LogUtil.h("RriHeartRateSendCommandUtil", "openOrClose is ", Integer.valueOf(i));
            return;
        }
        StringBuilder sb = new StringBuilder(0);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(i));
        e(sb, (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class));
    }

    private void e(final StringBuilder sb, HealthDataMgrApi healthDataMgrApi) {
        healthDataMgrApi.getUserPressureAdjustDatas(new IBaseResponseCallback() { // from class: jlg.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_RriHeartRateSendCommandUtil", "getUserAdjustData errorCode:", Integer.valueOf(i));
                if (i == 0 && obj != null) {
                    HiStressMetaData c = jlg.c(obj instanceof String ? (String) obj : "");
                    if (c != null) {
                        StringBuilder c2 = jlg.this.c(c);
                        List<Float> fetchStressFeature = c.fetchStressFeature();
                        StringBuilder sb2 = new StringBuilder(0);
                        sb2.append(cvx.e(3));
                        sb2.append(cvx.e(48));
                        if (fetchStressFeature != null && fetchStressFeature.size() == 12) {
                            for (int i2 = 0; i2 < fetchStressFeature.size(); i2++) {
                                sb2.append(cvx.a(fetchStressFeature.get(i2).floatValue()));
                            }
                            StringBuilder e = jlg.this.e(c);
                            StringBuilder sb3 = new StringBuilder(0);
                            sb3.append(sb.toString());
                            sb3.append(c2.toString());
                            sb3.append(sb2.toString());
                            sb3.append(e.toString());
                            LogUtil.a("RriHeartRateSendCommandUtil", "5.32.9 send cmd1 : ", sb3.toString());
                            jqi.a().sendSetSwitchSettingCmd(cvx.a(sb3.toString()), "", 32, 9);
                            return;
                        }
                    } else {
                        ReleaseLogUtil.d("DEVMGR_RriHeartRateSendCommandUtil", "getUserAdjustData data is null");
                        return;
                    }
                } else {
                    ReleaseLogUtil.d("DEVMGR_RriHeartRateSendCommandUtil", "getUserAdjustData error or objectData is null");
                }
                jlg.this.c(sb);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(StringBuilder sb) {
        LogUtil.a("RriHeartRateSendCommandUtil", "5.32.9 send cmd2 : ", sb.toString());
        jqi.a().sendSetSwitchSettingCmd(cvx.a(sb.toString()), "", 32, 9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StringBuilder c(HiStressMetaData hiStressMetaData) {
        int fetchStressScore = hiStressMetaData.fetchStressScore();
        StringBuilder sb = new StringBuilder(0);
        sb.append(cvx.e(2));
        sb.append(cvx.e(1));
        sb.append(cvx.e(fetchStressScore));
        return sb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StringBuilder e(HiStressMetaData hiStressMetaData) {
        StringBuilder sb = new StringBuilder(0);
        sb.append(cvx.e(4));
        sb.append(cvx.e(4));
        sb.append(cvx.b(hiStressMetaData.fetchStressStartTime() / 1000));
        return sb;
    }

    public void d(HiStressMetaData hiStressMetaData) {
        if (hiStressMetaData == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(0);
        int fetchStressScore = hiStressMetaData.fetchStressScore();
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(fetchStressScore));
        long fetchStressStartTime = hiStressMetaData.fetchStressStartTime();
        sb.append(cvx.e(2));
        sb.append(cvx.e(4));
        sb.append(cvx.b(fetchStressStartTime / 1000));
        long fetchStressEndTime = hiStressMetaData.fetchStressEndTime();
        sb.append(cvx.e(3));
        sb.append(cvx.e(4));
        sb.append(cvx.b(fetchStressEndTime / 1000));
        int fetchStressGrade = hiStressMetaData.fetchStressGrade();
        sb.append(cvx.e(4));
        sb.append(cvx.e(1));
        sb.append(cvx.e(fetchStressGrade));
        List<Float> fetchStressFeature = hiStressMetaData.fetchStressFeature();
        if (fetchStressFeature != null && fetchStressFeature.size() == 12) {
            sb.append(cvx.e(5));
            sb.append(cvx.e(48));
            for (int i = 0; i < fetchStressFeature.size(); i++) {
                sb.append(cvx.a(fetchStressFeature.get(i).floatValue()));
            }
        }
        LogUtil.a("RriHeartRateSendCommandUtil", "5.32.10 send cmd: ", sb.toString());
        jqi.a().sendSetSwitchSettingCmd(cvx.a(sb.toString()), "", 32, 10);
    }

    public static HiStressMetaData c(String str) {
        return eeo.d(str);
    }
}
