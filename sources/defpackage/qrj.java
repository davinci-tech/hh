package defpackage;

import android.app.Activity;
import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.views.HealthBodyBarData;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class qrj {
    public static void a(final Context context, final cfe cfeVar, final HealthBodyBarData healthBodyBarData) {
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser.p()) {
            e(cfeVar, healthBodyBarData, currentUser.g());
        } else {
            MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback<cfi>() { // from class: qrj.5
                @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onResponse(final int i, final cfi cfiVar) {
                    Context context2 = context;
                    if (context2 == null || !(context2 instanceof Activity)) {
                        LogUtil.h("HealthWeight_PeerComparisionUtils", "mContext is null or mContext is not Activity");
                    } else {
                        ((Activity) context2).runOnUiThread(new Runnable() { // from class: qrj.5.2
                            @Override // java.lang.Runnable
                            public void run() {
                                cfi cfiVar2 = cfiVar;
                                if (cfiVar2 == null || i != 0) {
                                    LogUtil.h("HealthWeight_PeerComparisionUtils", "loadDataSuccess getCurrentUser: currentUser is null return");
                                    cfiVar2 = MultiUsersManager.INSTANCE.getCurrentUser();
                                }
                                qrj.e(cfeVar, healthBodyBarData, cfiVar2.g());
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(cfe cfeVar, HealthBodyBarData healthBodyBarData, int i) {
        if (cfeVar == null || healthBodyBarData == null) {
            LogUtil.h("HealthWeight_PeerComparisionUtils", "setPeerBarView weightBean ", cfeVar, "healthBodyBarData ", healthBodyBarData);
            return;
        }
        sdb c = c(cfeVar, i);
        d(cfeVar, c, healthBodyBarData);
        e(cfeVar, c, healthBodyBarData);
        a(cfeVar, c, healthBodyBarData);
        b(cfeVar, c, healthBodyBarData);
        c(cfeVar, c, healthBodyBarData);
    }

    private static void d(cfe cfeVar, sdb sdbVar, HealthBodyBarData healthBodyBarData) {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(1);
        double j = cfeVar.j();
        boolean o = Utils.o();
        if (!dph.e(j, o)) {
            LogUtil.h("HealthWeight_PeerComparisionUtils", "setPeerBmiView Bmi is out of range");
            healthBodyBarData.setBmiBar(null);
            return;
        }
        float s = sdbVar.s();
        String format = percentInstance.format(sdbVar.m());
        double[] d = doj.d(o);
        double[] e = doj.e(o, cfeVar.an(), cfeVar.e());
        healthBodyBarData.setBmiBar(c(qrd.b(0), String.format(BaseApplication.getContext().getResources().getString(R$string.IDS_weight_better_than_of_peers), format), "", new float[]{(float) j, s, (float) d[0], (float) e[0], (float) e[1], (float) d[1]}));
    }

    private static void e(cfe cfeVar, sdb sdbVar, HealthBodyBarData healthBodyBarData) {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(1);
        float a2 = (float) cfeVar.a();
        double[] doubleArrayLevelByType = cfeVar.getDoubleArrayLevelByType(1);
        if (!dph.j(a2)) {
            LogUtil.h("HealthWeight_PeerComparisionUtils", "setPeerBodyFatView BodyFat is out of range");
            healthBodyBarData.setFatBar(null);
        } else {
            if (qsj.e(doubleArrayLevelByType, 1)) {
                float l = sdbVar.l();
                String format = percentInstance.format(sdbVar.k());
                double[] i = doj.i();
                healthBodyBarData.setFatBar(c(qrd.j(0), String.format(BaseApplication.getContext().getResources().getString(R$string.IDS_weight_better_than_of_peers), format), BaseApplication.getContext().getResources().getString(R$string.IDS_motiontrack_show_chart_unit_string, BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_show_healthdata_weight_percent)), new float[]{a2, l * 100.0f, (float) i[0], (float) doubleArrayLevelByType[0], (float) doubleArrayLevelByType[1], (float) i[1]}));
                return;
            }
            healthBodyBarData.setFatBar(null);
        }
    }

    private static void a(cfe cfeVar, sdb sdbVar, HealthBodyBarData healthBodyBarData) {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(1);
        float s = (float) cfeVar.s();
        if (!dph.y(s)) {
            LogUtil.h("HealthWeight_PeerComparisionUtils", "setPeerVisceralLevelView VisceralLevel is out of range");
            healthBodyBarData.setVisceralBar(null);
            return;
        }
        float v = sdbVar.v();
        String format = percentInstance.format(sdbVar.x());
        double[] v2 = doj.v();
        double[] doubleArrayLevelByType = cfeVar.getDoubleArrayLevelByType(5);
        if (qsj.e(doubleArrayLevelByType, 1)) {
            healthBodyBarData.setVisceralBar(c(qrd.q(0), String.format(BaseApplication.getContext().getResources().getString(R$string.IDS_weight_better_than_of_peers), format), BaseApplication.getContext().getResources().getString(R$string.IDS_weight_brackets_grade), new float[]{s, v, 0.0f, (float) v2[0], ((float) doubleArrayLevelByType[1]) - 1.0f, (float) v2[1]}));
        } else {
            healthBodyBarData.setVisceralBar(null);
        }
    }

    private static void b(cfe cfeVar, sdb sdbVar, HealthBodyBarData healthBodyBarData) {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(1);
        if (!dph.p((float) cfeVar.aj())) {
            LogUtil.h("HealthWeight_PeerComparisionUtils", "setPeerSkeletalMuscleView skeletalMuscle is out of range");
            healthBodyBarData.setMuscleBar(null);
            return;
        }
        float q = sdbVar.q();
        double[] r = doj.r();
        double d = r[0];
        double d2 = r[1];
        double[] doubleArrayLevelByType = cfeVar.getDoubleArrayLevelByType(10);
        if (qsj.e(doubleArrayLevelByType, 1)) {
            float floatValue = BigDecimal.valueOf((float) UnitUtil.a(r3)).setScale(1, 4).floatValue();
            float floatValue2 = BigDecimal.valueOf((float) UnitUtil.a(q)).setScale(1, 4).floatValue();
            double floatValue3 = BigDecimal.valueOf((float) UnitUtil.a(d)).setScale(1, 4).floatValue();
            double floatValue4 = BigDecimal.valueOf((float) UnitUtil.a(d2)).setScale(1, 4).floatValue();
            double d3 = doubleArrayLevelByType[0];
            double d4 = doubleArrayLevelByType[1];
            healthBodyBarData.setMuscleBar(c(qrd.l(0), String.format(BaseApplication.getContext().getResources().getString(R$string.IDS_weight_better_than_of_peers), percentInstance.format(sdbVar.p())), BaseApplication.getContext().getResources().getString(R$string.IDS_motiontrack_show_chart_unit_string, qsj.e()), new float[]{floatValue, floatValue2, (float) floatValue3, BigDecimal.valueOf((float) UnitUtil.a(d3)).setScale(1, 4).floatValue(), BigDecimal.valueOf((float) UnitUtil.a(d4)).setScale(1, 4).floatValue(), (float) floatValue4}));
            return;
        }
        healthBodyBarData.setMuscleBar(null);
    }

    private static void c(cfe cfeVar, sdb sdbVar, HealthBodyBarData healthBodyBarData) {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(1);
        float af = (float) cfeVar.af();
        if (!dph.r(af)) {
            LogUtil.h("HealthWeight_PeerComparisionUtils", "setRasmView Rasm is out of range");
            healthBodyBarData.setLimbBar(null);
            return;
        }
        float t = sdbVar.t();
        byte an = cfeVar.an();
        String format = percentInstance.format(sdbVar.r());
        double[] s = doj.s();
        double d = s[0];
        double d2 = s[1];
        double[] a2 = doj.a(an, cfeVar.e());
        healthBodyBarData.setLimbBar(c(qrd.o(0), String.format(BaseApplication.getContext().getResources().getString(R$string.IDS_weight_better_than_of_peers), format), BaseApplication.getContext().getResources().getString(R$string.IDS_weight_brackets_kg_square), new float[]{af, t, (float) d, (float) a2[0], (float) a2[1], (float) d2}));
    }

    private static ntg c(String str, String str2, String str3, float[] fArr) {
        ntg ntgVar = new ntg();
        ntgVar.e(str);
        ntgVar.d(str2);
        ntgVar.b(str3);
        if (fArr != null && fArr.length >= 5) {
            ntgVar.c((float) doj.d(fArr[0]));
            ntgVar.d((float) doj.d(fArr[1]));
            ntgVar.i((float) doj.d(fArr[2]));
            ntgVar.a((float) doj.d(fArr[3]));
            ntgVar.e((float) doj.d(fArr[4]));
            ntgVar.b((float) doj.d(fArr[5]));
        }
        return ntgVar;
    }

    public static sdb c(cfe cfeVar, int i) {
        int e = cfeVar.e();
        byte an = cfeVar.an();
        int e2 = cgs.e(i, e);
        int a2 = cgs.a(i);
        float t = cfeVar.t();
        float j = (float) cfeVar.j();
        float a3 = (float) cfeVar.a();
        int s = (int) cfeVar.s();
        float aj = (float) cfeVar.aj();
        float af = (float) cfeVar.af();
        LogUtil.a("HealthWeight_PeerComparisionUtils", "getPeerAnalysis age=", Integer.valueOf(e), ",sex=", Integer.valueOf(an), ",realityAge=", Integer.valueOf(e2), "realityMonth=", Integer.valueOf(a2), ",height=", Float.valueOf(t), ",bmi=", Float.valueOf(j), "bodyFat=", Float.valueOf(a3), ",visceralLevel=", Integer.valueOf(s), ",skeletalMuscle=", Float.valueOf(aj), ",rasm=", Float.valueOf(af));
        return new sdb(e2, a2, an, t / 100.0f, j, a3 / 100.0f, s, aj, af);
    }

    public static String c(cfe cfeVar) {
        if (cfeVar == null) {
            LogUtil.h("HealthWeight_PeerComparisionUtils", "getPeerComparisionSuggestion WeightBean is null");
            return "";
        }
        int e = cfeVar.e();
        int c = c(doj.b(cfeVar.j(), Utils.o(), cfeVar.an(), e));
        int d = d((int) cfeVar.getDoubleOrIntLevelByType(1));
        int d2 = d((int) cfeVar.getDoubleOrIntLevelByType(5));
        int e2 = e((int) cfeVar.getDoubleOrIntLevelByType(10));
        int e3 = e(doj.e(cfeVar.an(), cfeVar.af(), e));
        int i = c + d + d2 + e2 + e3;
        String[] stringArray = BaseApplication.getContext().getResources().getStringArray(R.array._2130968638_res_0x7f04003e);
        boolean z = false;
        if (cfeVar.aa() != 2) {
            boolean z2 = d2 > 0 && e2 > 0;
            if (i >= 6 && c > 0 && d > 0 && z2) {
                return stringArray[3];
            }
            if (i >= 4) {
                return stringArray[2];
            }
            if (i >= 2) {
                return stringArray[1];
            }
            return stringArray[0];
        }
        if (d2 > 0 && e2 > 0 && e3 > 0) {
            z = true;
        }
        if (i >= 8 && c > 0 && d > 0 && z) {
            return stringArray[3];
        }
        if (i >= 5) {
            return stringArray[2];
        }
        if (i >= 2) {
            return stringArray[1];
        }
        return stringArray[3];
    }

    private static int c(int i) {
        if (i < 0 || i > 4) {
            LogUtil.h("HealthWeight_PeerComparisionUtils", "getLevelToScoreOne level is out of range");
            return 0;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(0);
        arrayList.add(0);
        return ((Integer) arrayList.get(i - 1)).intValue();
    }

    private static int d(int i) {
        if (i < 0 || i > 4) {
            LogUtil.h("HealthWeight_PeerComparisionUtils", "getLevelToScoreTwo level is out of range");
            return 0;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(0);
        arrayList.add(0);
        return ((Integer) arrayList.get(i - 1)).intValue();
    }

    private static int e(int i) {
        if (i < 0 || i > 4) {
            LogUtil.h("HealthWeight_PeerComparisionUtils", "getLevelToScoreThree level is out of range");
            return 0;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(2);
        return ((Integer) arrayList.get(i - 1)).intValue();
    }
}
