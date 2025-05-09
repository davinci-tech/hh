package com.huawei.ui.main.stories.health.interactors.healthdata;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.main.R$string;
import defpackage.cfe;
import defpackage.doj;
import defpackage.qsj;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;

/* loaded from: classes6.dex */
public class BodyReportRecycleItem {
    private c[] e;
    private cfe i;
    private BodyReportType j;

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f10202a = {2, 1, 10, 26};
    private static final int[] c = {5, 25, 4, 11};
    private static final int[] d = {5, 7, 4, 26};
    private static final int[] b = {5, 4, 11};

    public enum BodyReportType {
        BODY_ANALYSIS,
        PEER_COMPARISON,
        MUSCLE_ANALYSIS,
        FAT_ANALYSIS,
        PHYSIQUE_PREDICTION,
        IMPORTANT_INDICATORS,
        OTHER_INDICATORS
    }

    private int b(int i) {
        return (i == 94 || i == 95 || i == 106 || i == 385) ? 1 : 0;
    }

    public BodyReportRecycleItem(BodyReportType bodyReportType, cfe cfeVar) {
        this.j = bodyReportType;
        this.i = cfeVar;
        if (bodyReportType == BodyReportType.IMPORTANT_INDICATORS || this.j == BodyReportType.OTHER_INDICATORS) {
            this.e = a(this.j, this.i);
        }
    }

    private c[] a(BodyReportType bodyReportType, cfe cfeVar) {
        int[] iArr;
        if (this.j != BodyReportType.IMPORTANT_INDICATORS && this.j != BodyReportType.OTHER_INDICATORS) {
            LogUtil.h("BodyReportRecycleItem", "buildIndexTableItems illegal argument: itemType", bodyReportType);
        }
        if (bodyReportType == BodyReportType.IMPORTANT_INDICATORS) {
            iArr = f10202a;
        } else if (Utils.o()) {
            iArr = b;
        } else if ("b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(cfeVar.getWeightScaleProductId()) && cfeVar.e() < 18) {
            iArr = d;
        } else {
            iArr = c;
        }
        c[] cVarArr = new c[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            cVarArr[i] = c(iArr[i], cfeVar);
        }
        return cVarArr;
    }

    private c c(int i, cfe cfeVar) {
        c cVar = new c(i);
        cVar.e(b(i, cfeVar));
        cVar.b(a(i));
        cVar.c(e(i, cfeVar));
        cVar.a(a(i, cfeVar));
        return cVar;
    }

    private String b(int i, cfe cfeVar) {
        Context e = BaseApplication.e();
        if (i == 1) {
            return e.getResources().getString(R$string.IDS_hw_health_show_healthdata_bodyfat_rate);
        }
        if (i == 2) {
            return e.getResources().getString(R$string.IDS_hw_show_BMI);
        }
        if (i == 4) {
            return e.getResources().getString(R$string.IDS_hw_show_metabolism);
        }
        if (i == 5) {
            return e.getResources().getString(R$string.IDS_hw_show_haslet);
        }
        if (i == 7) {
            return e.getResources().getString(R$string.IDS_hw_show_bone);
        }
        if (i == 10) {
            return e.getResources().getString(R$string.IDS_hw_show_skeletal_muscle_mass);
        }
        if (i == 11) {
            return e.getResources().getString(R$string.IDS_main_watch_heart_rate_string);
        }
        if (i == 25) {
            if (cfeVar.ao() > 0.0d) {
                return e.getResources().getString(R$string.IDS_weight_waist_to_hip_ratio);
            }
            return e.getResources().getString(R$string.IDS_weight_spectral_waist_to_hip_ratio);
        }
        if (i == 26) {
            return e.getResources().getString(R$string.IDS_weight_limb_skeletal_muscle_index);
        }
        LogUtil.h("BodyReportRecycleItem", "getWeightIndexName unknow weightIndex");
        return "";
    }

    private String a(int i, cfe cfeVar) {
        if (cfeVar == null) {
            LogUtil.h("BodyReportRecycleItem", "getWeightIndexValueFormat: weightBean is null");
            return "";
        }
        double d2 = d(i, cfeVar);
        if (i != 1 && i != 2) {
            if (i != 4) {
                if (i != 5) {
                    if (i == 7) {
                        return UnitUtil.e(d2, 1, cfeVar.getFractionDigitByType(7));
                    }
                    if (i != 10) {
                        if (i != 11) {
                            if (i == 25) {
                                return UnitUtil.e(d2, 1, 2);
                            }
                            if (i != 26) {
                                LogUtil.h("BodyReportRecycleItem", "getWeightIndexValueFormat: unknown itemType");
                                return "";
                            }
                        }
                    }
                }
            }
            return UnitUtil.e(d2, 1, 0);
        }
        return UnitUtil.e(d2, 1, 1);
    }

    private double d(int i, cfe cfeVar) {
        if (cfeVar == null) {
            LogUtil.h("BodyReportRecycleItem", "getWeightIndexValueFromWeightBean illegal weightBean");
            return -1.0d;
        }
        if (i == 1) {
            return cfeVar.a();
        }
        if (i == 2) {
            return cfeVar.j();
        }
        if (i == 4) {
            return cfeVar.d();
        }
        if (i == 5) {
            return cfeVar.s();
        }
        if (i == 7) {
            return cfeVar.i();
        }
        if (i == 10) {
            return cfeVar.aj();
        }
        if (i == 11) {
            return cfeVar.p();
        }
        if (i == 25) {
            if (cfeVar.ao() > 0.0d) {
                return cfeVar.ao();
            }
            return cfeVar.as();
        }
        if (i == 26) {
            return cfeVar.af();
        }
        LogUtil.h("BodyReportRecycleItem", "getWeightIndexValueFromWeightBean unknow weightIndex");
        return -1.0d;
    }

    private String a(int i) {
        Context e = BaseApplication.e();
        String string = e.getResources().getString(R$string.IDS_hw_health_empty_unit);
        if (i == 1) {
            return "%";
        }
        if (i == 2) {
            return string;
        }
        if (i == 4) {
            return e.getResources().getString(R$string.IDS_hw_show_sport_cal_unit_new);
        }
        if (i == 5) {
            return e.getResources().getString(R$string.IDS_hw_show_haslet_unit);
        }
        if (i == 7 || i == 10) {
            return qsj.e();
        }
        if (i == 11) {
            return e.getResources().getString(R$string.IDS_device_measure_pressure_result_heart_rate_unit);
        }
        if (i == 25) {
            return string;
        }
        if (i == 26) {
            return e.getResources().getString(R$string.IDS_weight_kg_square);
        }
        LogUtil.h("BodyReportRecycleItem", "getWeightIndexUnit unknow weightIndex");
        return string;
    }

    private String e(int i, cfe cfeVar) {
        byte an = cfeVar.an();
        int b2 = b(cfeVar.l());
        if (i == 1) {
            return b(doj.b(cfeVar.an(), cfeVar.e(), b2), HiDataFilter.DataFilterExpression.LESS_THAN);
        }
        if (i == 2) {
            return c(doj.d(Utils.o()));
        }
        if (i == 4) {
            return c(doj.b(cfeVar.an(), cfeVar.e(), cfeVar.ax(), b2));
        }
        if (i == 5) {
            return c(new double[]{doj.v()[0], doj.aa()[1] - 1.0d});
        }
        if (i == 7) {
            return e(doj.e(cfeVar.an(), cfeVar.t(), cfeVar.getFractionDigitByType(7), cfeVar.e()));
        }
        if (i == 10) {
            return c(doj.m());
        }
        if (i == 11) {
            return c(new double[]{doj.j()[0], doj.j()[1]});
        }
        if (i == 25) {
            return b(doj.d(cfeVar.an())[0], HiDataFilter.DataFilterExpression.LESS_THAN);
        }
        if (i == 26) {
            return c(doj.a(an, cfeVar.e()));
        }
        LogUtil.h("BodyReportRecycleItem", "getWeightIndexStandardRange unknow weightIndex");
        return "";
    }

    private String e(String[] strArr) {
        if (strArr == null || strArr.length != 2) {
            LogUtil.h("BodyReportRecycleItem", "getCloseRangeString illegal range");
            return "";
        }
        return strArr[0] + Constants.LINK + strArr[1];
    }

    private String b(double d2, String str) {
        return str + UnitUtil.e(d2, 1, 2);
    }

    private static String c(double[] dArr) {
        if (dArr == null || dArr.length != 2) {
            LogUtil.h("BodyReportRecycleItem", "getCloseRangeString illegal range");
            return "";
        }
        return UnitUtil.e(dArr[0], 1, 0) + Constants.LINK + UnitUtil.e(dArr[1], 1, 0);
    }

    public BodyReportType a() {
        return this.j;
    }

    public c[] e() {
        return (c[]) this.e.clone();
    }

    public cfe b() {
        return this.i;
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private String f10203a;
        private String b;
        private String c;
        private int d;
        private String e;

        public c(int i) {
            this.d = i;
        }

        public String e() {
            return this.e;
        }

        public String c() {
            return this.c;
        }

        public String a() {
            return this.f10203a;
        }

        public String d() {
            return this.b;
        }

        public void e(String str) {
            this.e = str;
        }

        public void b(String str) {
            this.c = str;
        }

        public void a(String str) {
            this.f10203a = str;
        }

        public void c(String str) {
            this.b = str;
        }
    }
}
