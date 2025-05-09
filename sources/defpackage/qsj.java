package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.core.util.Supplier;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.embedded.r3;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.indicator.HealthLevelIndicator;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightDetailActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity;
import com.huawei.ui.main.stories.health.util.TrendFragmentCallback;
import com.huawei.ui.main.stories.health.util.WeightAndAiBodyShapeCallback;
import com.huawei.ui.main.stories.health.views.HealthBodyDetailData;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.GRSManager;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class qsj {
    private static final float[][] d = {new float[]{0.0051f, 0.01f}, new float[]{0.0101f, 0.015f}, new float[]{0.0151f, 0.02f}};
    private static final int[][] e = {new int[]{24, 1}, new int[]{12, 1}, new int[]{6, 1}};

    /* loaded from: classes7.dex */
    static class e {
        public static final qsj b = new qsj();
    }

    static /* synthetic */ boolean dIe_(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }

    private qsj() {
    }

    public static qsj a() {
        return e.b;
    }

    public static boolean d(cfe cfeVar) {
        return e(cfeVar, Utils.o());
    }

    public static boolean e(cfe cfeVar, boolean z) {
        if (cfeVar == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "checkWeightDataValidation WeightBean is null");
            return false;
        }
        if (cfeVar.isVisible(3, z) || cfeVar.isVisible(5, z) || cfeVar.isVisible(7, z) || cfeVar.isVisible(4, z) || cfeVar.isVisible(8, z) || cfeVar.isVisible(10, z)) {
            return true;
        }
        return cfeVar.isVisible(11, z);
    }

    public static void e(Context context, cfe cfeVar, cfi cfiVar) {
        if (cfeVar == null || context == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "bean or context is null");
            return;
        }
        if (nsn.o()) {
            return;
        }
        double a2 = cfeVar.a();
        Intent intent = new Intent();
        intent.setFlags(536870912);
        dIc_(cfeVar, cfiVar, a2, intent);
        int l = cfeVar.l();
        LogUtil.c("HealthWeight_WeightViewUtils", "dataType is : ", Integer.valueOf(l));
        if (cpa.b(l)) {
            LogUtil.a("HealthWeight_WeightViewUtils", "checkWeightDataValidation : true");
            b(context, AnalyticsValue.HEALTH_HEALTH_HEALTHDATA_WEIGHT_HISTORY_DATA_2030056.value());
            intent.putExtra("currentUser", MultiUsersManager.INSTANCE.getCurrentUser());
            intent.putExtra("mainUser", MultiUsersManager.INSTANCE.getMainUser());
            intent.setClass(context, WeightDetailActivity.class);
        } else if (d(cfeVar) && a2 > 0.0d) {
            LogUtil.a("HealthWeight_WeightViewUtils", "checkWeightDataValidation : true");
            intent.putExtra("currentUser", MultiUsersManager.INSTANCE.getCurrentUser());
            intent.putExtra("mainUser", MultiUsersManager.INSTANCE.getMainUser());
            intent.setClass(context, WeightDetailActivity.class);
        } else {
            intent.setClass(context, InputWeightActivity.class);
        }
        gnm.aPB_(context, intent);
    }

    private static void dIc_(cfe cfeVar, cfi cfiVar, double d2, Intent intent) {
        intent.putExtra("weightBean", cfeVar);
        intent.putExtra("weight", String.valueOf(cfeVar.ax()));
        intent.putExtra("bodyFat", String.valueOf(d2));
        intent.putExtra("weightTime", cfeVar.au());
        intent.putExtra("deleteTime", cfeVar.au());
        intent.putExtra("clientId", cfeVar.k());
        intent.putExtra(ParsedFieldTag.TASK_MODIFY_TIME, cfeVar.w());
        if (cfeVar.ap() > 0.0d && cfeVar.ap() <= 100.0d) {
            intent.putExtra("water", cfeVar.ap());
        } else {
            intent.putExtra("water", cfeVar.al());
        }
        intent.putExtra("deleteEndTime", cfeVar.av());
        intent.putExtra("BITag", 1);
        intent.putExtra("resistance", cfeVar.ae());
        if (cfeVar.t() > 0) {
            intent.putExtra("userHeight", cfeVar.t());
        } else {
            intent.putExtra("userHeight", cfiVar.d());
        }
        intent.putExtra("isShowBodyFat", d2 >= 0.5d);
        intent.putExtra("isShowInput", false);
        intent.putExtra("dataType", cfeVar.l());
    }

    public static void e(String str, Context context) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(context, str, hashMap, 0);
    }

    public static void dIj_(cfe cfeVar, ImageView imageView) {
        if (cfeVar == null || imageView == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "bean or imageView is null");
            return;
        }
        if (!cfeVar.r()) {
            if (h(cfeVar)) {
                LogUtil.a("HealthWeight_WeightViewUtils", "setWeightAdapterImage image is eight electrode icon");
                imageView.setImageResource(R.drawable._2131430080_res_0x7f0b0ac0);
                return;
            } else {
                LogUtil.a("HealthWeight_WeightViewUtils", "setWeightAdapterImage image is measure icon");
                imageView.setImageResource(R.drawable._2131430034_res_0x7f0b0a92);
                return;
            }
        }
        LogUtil.a("HealthWeight_WeightViewUtils", "setWeightAdapterImage image is edit icon");
        Context context = imageView.getContext();
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable._2131428392_res_0x7f0b0428);
        if (LanguageUtil.bc(context)) {
            drawable = nrz.cKm_(context, drawable);
        }
        imageView.setImageDrawable(drawable);
    }

    public static boolean e(Context context) {
        if (context == null) {
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        return ("it".equalsIgnoreCase(language) || "de".equalsIgnoreCase(language) || "es".equalsIgnoreCase(language)) || ("fr".equalsIgnoreCase(language) || "en".equalsIgnoreCase(language));
    }

    public static void a(Context context) {
        CustomProgressDialog e2 = new CustomProgressDialog.Builder(context).e();
        e2.setCanceledOnTouchOutside(false);
        e2.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: qsk
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return qsj.dIe_(dialogInterface, i, keyEvent);
            }
        });
    }

    public static ArrayList<cfe> b(String str, long j, cfi cfiVar, boolean z) {
        ArrayList arrayList = new ArrayList(31);
        ArrayList<cfe> arrayList2 = new ArrayList<>(31);
        if (!TextUtils.isEmpty(str)) {
            d(str, (ArrayList<cfe>) arrayList, cfiVar);
            long n = jec.n(new Date(j));
            int n2 = dpg.n(j);
            if (koq.c(arrayList)) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    cfe cfeVar = (cfe) it.next();
                    if ((1000 * n) - ((n2 - 1) * 86400000) <= cfeVar.au() && cfeVar.au() <= j && e(cfeVar, z)) {
                        arrayList2.add(cfeVar);
                    }
                }
            }
            return arrayList2;
        }
        LogUtil.a("HealthWeight_WeightViewUtils", "getUserWeightTwoWeekData uuid is null");
        return arrayList2;
    }

    private static void d(String str, ArrayList<cfe> arrayList, cfi cfiVar) {
        if (arrayList == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "constructWeightListByUuid weightList = null");
            return;
        }
        List<cfe> singleUserWeightData = WeightDataManager.INSTANCE.getSingleUserWeightData(str, true);
        boolean o = Utils.o();
        if (koq.c(singleUserWeightData)) {
            Iterator<cfe> it = singleUserWeightData.iterator();
            while (it.hasNext()) {
                cfe c2 = c(it.next(), o, cfiVar);
                if (c2 != null) {
                    arrayList.add(c2);
                }
            }
        }
    }

    public static void b(Context context, String str) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(context, str, hashMap, 0);
    }

    public static void c(cfi cfiVar, HiAggregateOption hiAggregateOption, TrendFragmentCallback trendFragmentCallback) {
        if (cfiVar == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "getWeightData user is null");
        } else {
            if (hiAggregateOption == null) {
                LogUtil.h("HealthWeight_WeightViewUtils", "getWeightData aggregateOption is null");
                return;
            }
            e(cfiVar, hiAggregateOption);
            LogUtil.a("HealthWeight_WeightViewUtils", "getWeightData, aggregateHiHealthData");
            HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new c(trendFragmentCallback, hiAggregateOption, cfiVar));
        }
    }

    public static void d(long j, long j2, WeightAndAiBodyShapeCallback weightAndAiBodyShapeCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(j, j2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setCount(1);
        hiAggregateOption.setSortOrder(1);
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(b(hiAggregateOption), new d(weightAndAiBodyShapeCallback));
    }

    /* loaded from: classes8.dex */
    static class d implements HiAggregateListener {
        private WeightAndAiBodyShapeCallback d;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        d(WeightAndAiBodyShapeCallback weightAndAiBodyShapeCallback) {
            this.d = weightAndAiBodyShapeCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ArrayList<cfe> arrayList = new ArrayList<>(16);
            ArrayList<qku> arrayList2 = new ArrayList<>(16);
            if (koq.b(list)) {
                WeightAndAiBodyShapeCallback weightAndAiBodyShapeCallback = this.d;
                if (weightAndAiBodyShapeCallback != null) {
                    weightAndAiBodyShapeCallback.getWeightAndAIBodyShape(arrayList, arrayList2);
                    return;
                }
                return;
            }
            arrayList2.add(new qku(list.get(0)));
            WeightAndAiBodyShapeCallback weightAndAiBodyShapeCallback2 = this.d;
            if (weightAndAiBodyShapeCallback2 != null) {
                weightAndAiBodyShapeCallback2.getWeightAndAIBodyShape(arrayList, arrayList2);
            }
        }
    }

    private static void e(cfi cfiVar, HiAggregateOption hiAggregateOption) {
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter(a(cfiVar));
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
    }

    public static void e(cfi cfiVar, HiAggregateOption hiAggregateOption, HiAggregateOption hiAggregateOption2, WeightAndAiBodyShapeCallback weightAndAiBodyShapeCallback) {
        if (cfiVar == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "getWeightData user is null");
            return;
        }
        if (hiAggregateOption == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "getWeightData aggregateOption is null");
            return;
        }
        e(cfiVar, hiAggregateOption);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hiAggregateOption);
        if (hiAggregateOption2 != null) {
            arrayList.add(b(hiAggregateOption2));
        }
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthDataEx(arrayList, new b(weightAndAiBodyShapeCallback, arrayList, cfiVar));
    }

    public static HiAggregateOption b(HiAggregateOption hiAggregateOption) {
        hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.BODY_SHAPE_BUST_GIRTH.value(), DicDataTypeUtil.DataType.BODY_SHAPE_WAIST_GIRTH.value(), DicDataTypeUtil.DataType.BODY_SHAPE_HIPLINE.value(), DicDataTypeUtil.DataType.BODY_SHAPE_THIGH_GIRTH.value(), DicDataTypeUtil.DataType.BODY_SHAPE_CALVES.value(), DicDataTypeUtil.DataType.BODY_SHAPE_ARM_CIRCUMFERENCE.value(), DicDataTypeUtil.DataType.BODY_SHAPE_HEAD_CIRCUMFERENCE.value(), DicDataTypeUtil.DataType.BODY_SHAPE_LEG_LENGTH.value(), DicDataTypeUtil.DataType.BODY_SHAPE_ARM_LENGTH.value(), DicDataTypeUtil.DataType.BODY_SHAPE_SHOULDER_WIDTH.value(), DicDataTypeUtil.DataType.BODY_SHAPE_WAIST_HIP_RATIO.value(), DicDataTypeUtil.DataType.BODY_SHAPE_BODY_FORM.value()});
        hiAggregateOption.setConstantsKey(new String[]{"bustGirth", "waistGirth", "hipline", "thighGirth", "calves", "armCircumference", "headCircumference", "legLength", "armLength", "shoulderWidth", "waistHipRatio", "bodyForm"});
        return hiAggregateOption;
    }

    public static void e(cfe cfeVar, qku qkuVar) {
        if (cfeVar == null || qkuVar == null || HiDateUtil.c(cfeVar.au()) != HiDateUtil.c(qkuVar.g()) || !cfeVar.isVisible(27, !qrp.d())) {
            return;
        }
        cfeVar.ag(qkuVar.h());
        cfeVar.h(qkuVar.c());
    }

    public static ArrayList<qtm> b(ArrayList<cfe> arrayList) {
        if (koq.b(arrayList)) {
            return new ArrayList<>();
        }
        ArrayList<qtm> arrayList2 = new ArrayList<>();
        Iterator<cfe> it = arrayList.iterator();
        while (it.hasNext()) {
            cfe next = it.next();
            if (next != null) {
                arrayList2.add(new qtm(next.ax(), next.a(), next.au(), next.getFractionDigitByType(0)));
            }
        }
        return arrayList2;
    }

    public static String a(cfi cfiVar) {
        return cfiVar == null ? "NULL" : grz.d(cfiVar.i());
    }

    public static cfe d(cfe cfeVar, boolean z) {
        return c(cfeVar, z, MultiUsersManager.INSTANCE.getCurrentUser());
    }

    public static cfe c(cfe cfeVar, boolean z, cfi cfiVar) {
        if (cfeVar == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "convertWeightBean weightBean = null");
            return null;
        }
        if (Double.compare(cfeVar.ax(), 0.0d) <= 0) {
            return null;
        }
        cfe b2 = b(cfeVar.l());
        e(cfeVar, b2, z, cfiVar);
        b2.y(cfeVar.ae());
        b2.aa(cfeVar.al());
        b2.ai(cfeVar.ap());
        b2.l(cfeVar.s());
        b2.j(cfeVar.i());
        b2.d(cfeVar.d());
        b2.q(cfeVar.z());
        b2.t(cfeVar.ab());
        b2.g(cfeVar.h());
        b2.b(cfeVar.b());
        b2.n(cfeVar.p());
        b2.s(cfeVar.ad());
        b2.ad(cfeVar.aj());
        b2.j(cfeVar.aa());
        b2.k(cfeVar.v());
        b2.r(cfeVar.y());
        b2.o(cfeVar.u());
        b2.m(cfeVar.x());
        b2.u(cfeVar.am());
        b2.ab(cfeVar.ak());
        b2.w(cfeVar.ai());
        b2.v(cfeVar.ah());
        b2.ac(cfeVar.ar());
        b2.z(cfeVar.aq());
        b2.ag(cfeVar.as());
        b2.x(cfeVar.af());
        b2.f(cfeVar.g());
        b2.h(cfeVar.f());
        b2.i(cfeVar.o());
        b2.p(cfeVar.ac());
        b2.e(cfeVar.m());
        b2.b(cfeVar.k());
        b2.e(cfeVar.w());
        b2.c(cfeVar.n());
        a(cfeVar, b2);
        return b2;
    }

    private static cfe b(int i) {
        cfe qgqVar;
        if (cpa.e(i)) {
            qgqVar = new qgo();
        } else if (i == 133) {
            qgqVar = new qgn();
        } else {
            qgqVar = new qgq();
        }
        qgqVar.a(i);
        return qgqVar;
    }

    private static void e(cfe cfeVar, cfe cfeVar2, boolean z, cfi cfiVar) {
        if (cfeVar == null || cfeVar2 == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "setBaseDataByWeightData originData = null or weightBean = null");
            return;
        }
        int t = cfeVar.t();
        if (t <= 0 && cfiVar != null) {
            t = cfiVar.d();
        }
        cfeVar2.c(t);
        double j = cfeVar.j();
        if (!dph.e(j, z)) {
            j = doj.a(cfeVar.ax(), t);
        }
        cfeVar2.c(j);
        cfeVar2.a(cfeVar.an());
        int e2 = cfeVar.e();
        if (e2 <= 0) {
            e2 = MultiUsersManager.INSTANCE.getCurrentUser().a();
        }
        cfeVar2.d(e2);
        cfeVar2.ae(cfeVar.ax());
        cfeVar2.e(cfeVar.a());
        cfeVar2.a(cfeVar.c());
    }

    private static void a(cfe cfeVar, cfe cfeVar2) {
        if (cpa.c(cfeVar)) {
            cfeVar2.e(cfeVar.q());
            cfeVar2.e(2);
        }
        cfeVar2.c(cfeVar.ag());
        cfeVar2.b(cfeVar.au());
        cfeVar2.d(cfeVar.av());
        cfeVar2.ah(cfeVar.ao());
    }

    public static void d(HiHealthData hiHealthData, cfe cfeVar) {
        hiHealthData.putDouble("bodyWeight", cfeVar.ax());
        hiHealthData.putDouble(BleConstants.BODY_FAT_RATE, cfeVar.a());
        hiHealthData.putDouble("bodyFat", cfeVar.c());
        hiHealthData.putDouble(BleConstants.IMPEDANCE, cfeVar.ae());
        hiHealthData.putDouble(BleConstants.MOISTURE, cfeVar.al());
        hiHealthData.putDouble(BleConstants.MOISTURE_RATE, cfeVar.ap());
        hiHealthData.putDouble(BleConstants.VISCERAL_FAT_LEVEL, cfeVar.s());
        hiHealthData.putDouble(BleConstants.BONE_SALT, cfeVar.i());
        hiHealthData.putDouble(BleConstants.BMI, cfeVar.j());
        hiHealthData.putDouble(BleConstants.BASAL_METABOLISM, cfeVar.d());
        hiHealthData.putDouble(BleConstants.MUSCLE_MASS, cfeVar.z());
        hiHealthData.putDouble("protein", cfeVar.ab());
        hiHealthData.putDouble(BleConstants.BODY_SCORE, cfeVar.h());
        hiHealthData.putDouble(BleConstants.BODY_AGE, cfeVar.b());
        hiHealthData.putDouble(IndoorEquipManagerApi.KEY_HEART_RATE, cfeVar.p());
        hiHealthData.putDouble("pressure", cfeVar.ad());
        hiHealthData.putDouble("skeletalMusclelMass", cfeVar.aj());
        hiHealthData.putDouble("age", cfeVar.e());
        hiHealthData.putInt("height", cfeVar.t());
        hiHealthData.putInt(CommonConstant.KEY_GENDER, cfeVar.an());
        hiHealthData.putInt("trackdata_deviceType", cfeVar.l());
        hiHealthData.putInt("pole", cfeVar.aa());
        hiHealthData.putDouble("rightLegMuscleMass", cfeVar.ah());
        hiHealthData.putDouble("leftLegMuscleMass", cfeVar.x());
        hiHealthData.putDouble("rightArmMuscleMass", cfeVar.ak());
        hiHealthData.putDouble("leftArmMuscleMass", cfeVar.y());
        hiHealthData.putDouble("trunkMuscleMass", cfeVar.aq());
        hiHealthData.putDouble("rightLegFatMass", cfeVar.ai());
        hiHealthData.putDouble("leftLegFatMass", cfeVar.u());
        hiHealthData.putDouble("rightArmFatMass", cfeVar.am());
        hiHealthData.putDouble("leftArmFatMass", cfeVar.v());
        hiHealthData.putDouble("trunkFatMass", cfeVar.ar());
        hiHealthData.putDouble("waistHipRatio", cfeVar.as());
        hiHealthData.putDouble("waistHipRatioUser", cfeVar.ao());
        hiHealthData.putDouble("rasm", cfeVar.af());
        hiHealthData.putDouble("bodySize", cfeVar.g());
        hiHealthData.putDouble("bodyShape", cfeVar.f());
        hiHealthData.putDouble("fatBalance", cfeVar.o());
        hiHealthData.putDouble("muscleBalance", cfeVar.ac());
        b(hiHealthData, cfeVar);
        c(hiHealthData, cfeVar);
        hiHealthData.setEndTime(cfeVar.av());
        hiHealthData.setStartTime(cfeVar.au());
    }

    private static void c(HiHealthData hiHealthData, cfe cfeVar) {
        if (cpa.c(cfeVar)) {
            LogUtil.a("HealthWeight_WeightViewUtils", "putHfResistArray hiData put six hf hfResist");
            String[] strArr = {"lfrfHfImpedance", "lhrhHfImpedance", "lhlfHfImpedance", "lhrfHfImpedance", "rhlfHfImpedance", "rhrfHfImpedance"};
            double[] q = cfeVar.q();
            for (int i = 0; i < 6; i++) {
                hiHealthData.putDouble(strArr[i], q[i]);
            }
            return;
        }
        LogUtil.a("HealthWeight_WeightViewUtils", "puthfResistArray WeightBean is null or not support multi measure");
    }

    private static void b(HiHealthData hiHealthData, cfe cfeVar) {
        String[] strArr = {"lfrfImpedance", "lhrhImpedance", "lhlfImpedance", "lhrfImpedance", "rhlfImpedance", "rhrfImpedance"};
        double[] ag = cfeVar.ag();
        for (int i = 0; i < 6; i++) {
            hiHealthData.putDouble(strArr[i], ag[i]);
        }
    }

    public static long[] a(Date date, int i) {
        long[] jArr = new long[2];
        switch (i) {
            case 7:
                jArr[0] = System.currentTimeMillis();
                jArr[1] = 0;
                return jArr;
            case 8:
                e(date, jArr);
                return jArr;
            case 9:
                a(date, jArr);
                return jArr;
            case 10:
                b(date, jArr);
                return jArr;
            case 11:
                d(date, jArr);
                return jArr;
            case 12:
                jArr[0] = System.currentTimeMillis();
                jArr[1] = Math.min(gsd.h(), System.currentTimeMillis());
                return jArr;
            case 13:
                c(date, jArr);
                return jArr;
            default:
                d(date, jArr, i);
                return jArr;
        }
    }

    private static void e(Date date, long[] jArr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        jArr[1] = calendar.getTimeInMillis();
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        jArr[0] = calendar.getTimeInMillis();
        if (jArr[1] >= System.currentTimeMillis() || System.currentTimeMillis() >= jArr[0]) {
            return;
        }
        jArr[0] = System.currentTimeMillis();
    }

    private static void a(Date date, long[] jArr) {
        jArr[1] = date.getTime() - ((dpg.n(date.getTime()) - 1) * 86400000);
        long time = date.getTime() + 86400000;
        jArr[0] = time;
        if (time > System.currentTimeMillis()) {
            jArr[0] = System.currentTimeMillis();
        }
    }

    private static void b(Date date, long[] jArr) {
        jArr[1] = date.getTime() - ((dpg.n(date.getTime()) - 1) * 86400000);
        jArr[0] = System.currentTimeMillis();
    }

    private static void d(Date date, long[] jArr) {
        jArr[0] = date.getTime();
        jArr[1] = date.getTime() - 2592000000L;
    }

    private static void c(Date date, long[] jArr) {
        jArr[0] = date.getTime() - 604800000;
        jArr[1] = date.getTime() - 2592000000L;
    }

    private static void d(Date date, long[] jArr, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        if (i == 6) {
            calendar.add(2, 0);
            calendar.add(5, 0);
        }
        long timeInMillis = calendar.getTimeInMillis();
        LogUtil.c("HealthWeight_WeightViewUtils", "last is : ", Long.valueOf(timeInMillis));
        calendar.set(10, 0);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        if (i == 3) {
            calendar.add(5, 0);
        } else if (i == 4) {
            calendar.add(5, -6);
        } else if (i == 5) {
            calendar.add(5, -(d(date) - 1));
        } else if (i == 6) {
            calendar.set(5, 1);
            calendar.add(2, -11);
        } else {
            LogUtil.h("HealthWeight_WeightViewUtils", "unkonw type");
        }
        long timeInMillis2 = calendar.getTimeInMillis();
        jArr[0] = timeInMillis;
        if (i == 5) {
            timeInMillis2 += (d(new Date(timeInMillis)) - d(new Date(timeInMillis2))) * 86400000;
        }
        jArr[1] = timeInMillis2;
    }

    private static int d(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(5);
    }

    public static void d(int i) {
        sdk c2 = sdk.c();
        MultiUsersManager.INSTANCE.getMainUser().d(i);
        c2.a(false);
    }

    public static int c(float f) {
        return new BigDecimal(f).setScale(0, 4).intValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
    
        if (r4 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0064, code lost:
    
        return r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0061, code lost:
    
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005f, code lost:
    
        if (r4 != null) goto L22;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006d  */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.database.Cursor, android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int b(java.lang.String r15) {
        /*
            java.lang.String r0 = "HealthWeight_WeightViewUtils"
            r1 = 1
            r2 = 0
            r3 = 0
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L4c android.database.SQLException -> L4f
            java.lang.String r5 = "device.db"
            android.database.sqlite.SQLiteDatabase r4 = r4.openOrCreateDatabase(r5, r2, r3)     // Catch: java.lang.Throwable -> L4c android.database.SQLException -> L4f
            java.lang.String[] r8 = new java.lang.String[r1]     // Catch: android.database.SQLException -> L4a java.lang.Throwable -> L65
            java.lang.String r5 = "productId"
            r8[r2] = r5     // Catch: android.database.SQLException -> L4a java.lang.Throwable -> L65
            java.lang.String[] r10 = new java.lang.String[]{r15}     // Catch: android.database.SQLException -> L4a java.lang.Throwable -> L65
            java.lang.String r7 = "device"
            java.lang.String r9 = "kind = ? "
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r6 = r4
            android.database.Cursor r3 = r6.query(r7, r8, r9, r10, r11, r12, r13, r14)     // Catch: android.database.SQLException -> L4a java.lang.Throwable -> L65
            r15 = r2
        L29:
            boolean r5 = r3.moveToNext()     // Catch: android.database.SQLException -> L51 java.lang.Throwable -> L65
            if (r5 == 0) goto L32
            int r15 = r15 + 1
            goto L29
        L32:
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: android.database.SQLException -> L51 java.lang.Throwable -> L65
            java.lang.String r6 = "getBondedProducts count "
            r5[r2] = r6     // Catch: android.database.SQLException -> L51 java.lang.Throwable -> L65
            java.lang.Integer r6 = java.lang.Integer.valueOf(r15)     // Catch: android.database.SQLException -> L51 java.lang.Throwable -> L65
            r5[r1] = r6     // Catch: android.database.SQLException -> L51 java.lang.Throwable -> L65
            com.huawei.hwlogsmodel.LogUtil.a(r0, r5)     // Catch: android.database.SQLException -> L51 java.lang.Throwable -> L65
            if (r3 == 0) goto L47
            r3.close()
        L47:
            if (r4 == 0) goto L64
            goto L61
        L4a:
            r15 = r2
            goto L51
        L4c:
            r15 = move-exception
            r4 = r3
            goto L66
        L4f:
            r15 = r2
            r4 = r3
        L51:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L65
            java.lang.String r5 = "getBondedProducts SQLException"
            r1[r2] = r5     // Catch: java.lang.Throwable -> L65
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L65
            if (r3 == 0) goto L5f
            r3.close()
        L5f:
            if (r4 == 0) goto L64
        L61:
            r4.close()
        L64:
            return r15
        L65:
            r15 = move-exception
        L66:
            if (r3 == 0) goto L6b
            r3.close()
        L6b:
            if (r4 == 0) goto L70
            r4.close()
        L70:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qsj.b(java.lang.String):int");
    }

    public static int e(cfe cfeVar) {
        if (cfeVar == null) {
            return 0;
        }
        double a2 = cfeVar.a();
        double aj = cfeVar.aj();
        if (!dph.j(a2) || !dph.p(aj)) {
            return 0;
        }
        int c2 = doj.c((int) cfeVar.getDoubleOrIntLevelByType(100), (int) cfeVar.getDoubleOrIntLevelByType(10));
        if (dph.d(c2)) {
            return c2;
        }
        return 0;
    }

    public static int[] b(int i, int i2, double d2, double d3) {
        if (d2 >= d3) {
            if (i <= 1 && i2 == 2) {
                return new int[]{2, 2};
            }
            if (i == 2 && i2 >= 3) {
                return new int[]{3, 3};
            }
        }
        if (d2 <= d3) {
            if (i == 2 && i2 <= 1) {
                return new int[]{2, 2};
            }
            if (i >= 3 && i2 == 2) {
                return new int[]{3, 3};
            }
        }
        return new int[]{i, i2};
    }

    public static boolean a(cfe cfeVar) {
        if (cfeVar == null) {
            return false;
        }
        return dph.q(cfeVar.v()) && dph.i(cfeVar.u()) && dph.q(cfeVar.am()) && dph.i(cfeVar.ai());
    }

    public static boolean b(cfe cfeVar) {
        if (cfeVar == null) {
            return false;
        }
        return dph.v(cfeVar.y()) && dph.o(cfeVar.x()) && dph.v(cfeVar.ak()) && dph.o(cfeVar.ah());
    }

    public static int c(double d2, int i) {
        return (cff.a(i) != 2 || (((UnitUtil.a(d2, 2) - doj.d(d2)) > 0.0d ? 1 : ((UnitUtil.a(d2, 2) - doj.d(d2)) == 0.0d ? 0 : -1)) == 0)) ? 1 : 2;
    }

    public static String e() {
        return d(1.0d);
    }

    public static String d(double d2) {
        return e(d2, true);
    }

    public static String e(double d2, boolean z) {
        String a2;
        int a3 = UnitUtil.a();
        if (a3 == 1) {
            a2 = nsf.a(R.plurals._2130903105_res_0x7f030041, UnitUtil.e(d2), "");
        } else if (a3 == 3) {
            a2 = nsf.a(R.plurals._2130903216_res_0x7f0300b0, UnitUtil.e(d2), "");
        } else {
            a2 = nsf.a(R.plurals._2130903215_res_0x7f0300af, UnitUtil.e(d2), "");
        }
        return z ? a2.trim() : a2;
    }

    public static String e(double d2, int i) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            return e(0.0d, i);
        }
        Resources resources = BaseApplication.e().getResources();
        int a2 = UnitUtil.a();
        if (a2 == 1) {
            return resources.getQuantityString(R.plurals._2130903105_res_0x7f030041, UnitUtil.e(d2), d(d2, 1, i));
        }
        if (a2 == 3) {
            return resources.getQuantityString(R.plurals._2130903216_res_0x7f0300b0, UnitUtil.e(d2), d(d2, 1, i));
        }
        return resources.getQuantityString(R.plurals._2130903089_res_0x7f030031, UnitUtil.e(d2), d(d2, 1, i));
    }

    public static String c() {
        Resources resources = BaseApplication.e().getResources();
        int a2 = UnitUtil.a();
        if (a2 == 1) {
            return resources.getQuantityString(R.plurals._2130903105_res_0x7f030041, UnitUtil.e(0.0d), "-/-");
        }
        if (a2 == 3) {
            return resources.getQuantityString(R.plurals._2130903216_res_0x7f0300b0, UnitUtil.e(0.0d), "-/-");
        }
        return resources.getQuantityString(R.plurals._2130903089_res_0x7f030031, UnitUtil.e(0.0d), "-/-");
    }

    public static void dIi_(Activity activity, boolean z) {
        if (activity == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "setResultToH5 activity is null");
            return;
        }
        LogUtil.a("HealthWeight_WeightViewUtils", "setResultToH5 isChange ", Boolean.valueOf(z));
        Intent intent = new Intent();
        HashMap hashMap = new HashMap();
        hashMap.put("isChange", Boolean.valueOf(z));
        intent.putExtra("result", HiJsonUtil.e(hashMap));
        activity.setResult(-1, intent);
    }

    public static String d(double d2, int i, int i2) {
        return UnitUtil.e(d2, i, i2, new Supplier() { // from class: com.huawei.ui.main.stories.health.util.WeightViewUtils$$ExternalSyntheticLambda1
            @Override // androidx.core.util.Supplier
            public final Object get() {
                Boolean valueOf;
                valueOf = Boolean.valueOf(LanguageUtil.ac(BaseApplication.e()));
                return valueOf;
            }
        });
    }

    public static boolean h(cfe cfeVar) {
        double[] ag;
        if (cfeVar == null || (ag = cfeVar.ag()) == null || ag.length < 6 || cfeVar.aa() != 2) {
            return false;
        }
        for (double d2 : ag) {
            if (d2 <= 0.0d) {
                return false;
            }
        }
        return true;
    }

    public static String d() {
        String url = GRSManager.a(BaseApplication.e()).getUrl("domainTipsResDbankcdn");
        if (!Utils.o()) {
            if (LanguageUtil.j(BaseApplication.e())) {
                return url + "/SmartWear/Scale-fat/EMUI8.0/C001B001/zh-CN/index.html";
            }
            return url + "/SmartWear/Scale-fat/EMUI8.0/C001B001/en-US/index.html";
        }
        return url + String.format("/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%1$s&devicetype=Scale-fat", CommonUtil.u());
    }

    public void dIk_(Context context, ImageView imageView) {
        if (context == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "setAboutImageLayout() context is null.");
            return;
        }
        if (imageView == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "setAboutImageLayout() imageView is null.");
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        if (nsn.ag(context)) {
            layoutParams.width = (int) new HealthColumnSystem(context, 0).d(4);
        } else {
            layoutParams.width = -1;
        }
        imageView.setLayoutParams(layoutParams);
    }

    public static ArrayList<Integer> d(cps cpsVar, int[] iArr, int[] iArr2, cfe cfeVar) {
        ArrayList<Integer> arrayList = new ArrayList<>(5);
        if (cpsVar == null || cfeVar == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "getTrunkFatRateGrades: hwWeightAlgorithm or weightBean is null");
            return arrayList;
        }
        if (Utils.o() && !Utils.a(BaseApplication.e())) {
            LogUtil.h("HealthWeight_WeightViewUtils", "getTrunkFatRateGrades in oversea context, return default value");
            for (int i = 0; i < 5; i++) {
                arrayList.add(1);
            }
            return arrayList;
        }
        if (iArr == null || iArr.length < 2 || iArr2 == null || iArr2.length < 2) {
            LogUtil.h("HealthWeight_WeightViewUtils", "getTrunkFatRateGrades: upperLimbGrades or lowerLimbGrades is out bounds");
            return arrayList;
        }
        arrayList.add(Integer.valueOf(doj.j(cfeVar.an(), cpsVar.am(), cfeVar.e())));
        arrayList.add(Integer.valueOf(iArr[0]));
        arrayList.add(Integer.valueOf(iArr[1]));
        arrayList.add(Integer.valueOf(iArr2[0]));
        arrayList.add(Integer.valueOf(iArr2[1]));
        return e((int) cfeVar.getDoubleOrIntLevelByType(1), arrayList);
    }

    public static ArrayList<Integer> c(cfe cfeVar) {
        ArrayList<Integer> arrayList = new ArrayList<>(5);
        if (cfeVar == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "getSkeletalMuscleGrades return defualt value, is oversea: ", Boolean.valueOf(Utils.o()));
            return arrayList;
        }
        if (Utils.o() && !Utils.a(BaseApplication.e())) {
            LogUtil.h("HealthWeight_WeightViewUtils", "getSkeletalMuscleGrades oversea context, return default value");
            for (int i = 0; i < 5; i++) {
                arrayList.add(1);
            }
            return arrayList;
        }
        byte an = cfeVar.an();
        int e2 = cfeVar.e();
        int t = cfeVar.t();
        arrayList.add(Integer.valueOf(doj.d(an, e2, t, cfeVar.aq())));
        arrayList.add(Integer.valueOf(doj.e(an, e2, t, cfeVar.y())));
        arrayList.add(Integer.valueOf(doj.e(an, e2, t, cfeVar.ak())));
        arrayList.add(Integer.valueOf(doj.b(an, e2, t, cfeVar.x())));
        arrayList.add(Integer.valueOf(doj.b(an, e2, t, cfeVar.ah())));
        return e((int) cfeVar.getDoubleOrIntLevelByType(10), arrayList);
    }

    private static ArrayList<Integer> e(int i, ArrayList<Integer> arrayList) {
        if (i != 1) {
            if (i == 2) {
                if (arrayList.contains(2) || (arrayList.contains(1) && arrayList.contains(3))) {
                    return arrayList;
                }
            } else if ((i != 3 && i != 4) || arrayList.contains(3)) {
                return arrayList;
            }
        } else if (arrayList.contains(1)) {
            return arrayList;
        }
        if (i == 4) {
            arrayList.set(0, 3);
        } else {
            arrayList.set(0, Integer.valueOf(i));
        }
        return arrayList;
    }

    public static void dIh_(View view, int... iArr) {
        if (view == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "setVisibilityInOversea view is null");
            return;
        }
        for (int i : iArr) {
            view.findViewById(i).setVisibility(8);
        }
    }

    public static void a(HealthBodyDetailData healthBodyDetailData, ArrayList<Double> arrayList, ArrayList<Integer> arrayList2) {
        if (healthBodyDetailData == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "setBodyDetailDataContent: bodyDetailData is null");
            return;
        }
        if (koq.d(arrayList, 4) && koq.d(arrayList2, 4)) {
            healthBodyDetailData.setContent(1, arrayList2.get(0).intValue(), UnitUtil.e(arrayList.get(0).doubleValue(), 1, 1));
            healthBodyDetailData.setContent(3, arrayList2.get(1).intValue(), UnitUtil.e(arrayList.get(1).doubleValue(), 1, 1));
            healthBodyDetailData.setContent(2, arrayList2.get(2).intValue(), UnitUtil.e(arrayList.get(2).doubleValue(), 1, 1));
            healthBodyDetailData.setContent(5, arrayList2.get(3).intValue(), UnitUtil.e(arrayList.get(3).doubleValue(), 1, 1));
            healthBodyDetailData.setContent(4, arrayList2.get(4).intValue(), UnitUtil.e(arrayList.get(4).doubleValue(), 1, 1));
            return;
        }
        LogUtil.h("HealthWeight_WeightViewUtils", "setBodyDetailDataContent: bodyTorsoElementContentList or bodyTorsoElementGradleList is out bounds");
    }

    public static boolean e(double[] dArr, int i) {
        return dArr != null && dArr.length != 0 && i >= 0 && i < dArr.length;
    }

    public static List<nkz> b(Context context, float[] fArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(context.getResources().getString(R$string.IDS_weight_moisture));
        arrayList.add(context.getResources().getString(R$string.IDS_hw_show_protein));
        arrayList.add(context.getResources().getString(R$string.IDS_weight_fat_mass));
        arrayList.add(context.getResources().getString(R$string.IDS_hw_show_bone));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131299350_res_0x7f090c16)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131299348_res_0x7f090c14)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131299346_res_0x7f090c12)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131299344_res_0x7f090c10)));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131299349_res_0x7f090c15)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131299347_res_0x7f090c13)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131299345_res_0x7f090c11)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131299343_res_0x7f090c0f)));
        int length = fArr != null ? fArr.length : 0;
        ArrayList arrayList4 = new ArrayList(length);
        int i = 0;
        while (i < length) {
            arrayList4.add(new nkz(i < arrayList.size() ? (String) arrayList.get(i) : "", fArr[i], i < arrayList2.size() ? ((Integer) arrayList2.get(i)).intValue() : 0, i < arrayList3.size() ? ((Integer) arrayList3.get(i)).intValue() : 0));
            i++;
        }
        return arrayList4;
    }

    /* loaded from: classes7.dex */
    static class c implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private HiAggregateOption f16571a;
        private TrendFragmentCallback c;
        private cfi d;
        private int e;

        c(TrendFragmentCallback trendFragmentCallback, HiAggregateOption hiAggregateOption, cfi cfiVar) {
            this.c = trendFragmentCallback;
            this.f16571a = hiAggregateOption;
            this.e = hiAggregateOption.getCount();
            this.d = cfiVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            boolean b = qsj.b(i, this.f16571a);
            ArrayList<cfe> arrayList = new ArrayList<>(16);
            if (koq.b(list)) {
                TrendFragmentCallback trendFragmentCallback = this.c;
                if (trendFragmentCallback != null) {
                    trendFragmentCallback.getWeight(arrayList, false);
                    return;
                }
                return;
            }
            rag.a(list, arrayList, this.d);
            TrendFragmentCallback trendFragmentCallback2 = this.c;
            if (trendFragmentCallback2 != null) {
                trendFragmentCallback2.getWeight(arrayList, this.e == list.size());
            }
            qsj.a(list, b);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("HealthWeight_WeightViewUtils", "onResultIntent intentType ", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2));
            if (i2 != 0) {
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_QUERY_85070010.value(), i2);
            }
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_QUERY_85070010.value(), nsn.e("0"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(int i, HiAggregateOption hiAggregateOption) {
        boolean z = hiAggregateOption != null && hiAggregateOption.getCount() == 50;
        if (!z) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(r3.A, true);
            EventBus.d(new EventBus.b("weight_measure_cloud_down_complete", bundle));
        }
        if (i != 0) {
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_QUERY_85070010.value(), i);
            LogUtil.h("HealthWeight_WeightViewUtils", "getAggregateHealthData not success");
        }
        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_QUERY_85070010.value(), nsn.e("0"));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(List<HiHealthData> list, boolean z) {
        if (z) {
            LogUtil.a("HealthWeight_WeightViewUtils", "getAggregateHealthData aggregateOption count REQUEST_MAX_NUM");
            WeightDataManager.INSTANCE.setDataList(false, list);
        } else {
            WeightDataManager.INSTANCE.setDataList(true, list);
            SharedPreferenceManager.e(BaseApplication.e(), "wifi_weight_device", "wifi_push_weight_download", "1", (StorageParams) null);
        }
    }

    /* loaded from: classes7.dex */
    static class b implements HiAggregateListenerEx {

        /* renamed from: a, reason: collision with root package name */
        private final List<HiAggregateOption> f16570a;
        private final WeightAndAiBodyShapeCallback b;
        private final cfi d;

        b(WeightAndAiBodyShapeCallback weightAndAiBodyShapeCallback, List<HiAggregateOption> list, cfi cfiVar) {
            this.b = weightAndAiBodyShapeCallback;
            this.f16570a = list;
            this.d = cfiVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
        public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
            LogUtil.b("HealthWeight_WeightViewUtils", "error code is ", Integer.valueOf(i), " anchor is ", Integer.valueOf(i2));
            if (sparseArray == null) {
                LogUtil.b("HealthWeight_WeightViewUtils", "dataList is null, callback empty array");
                WeightAndAiBodyShapeCallback weightAndAiBodyShapeCallback = this.b;
                if (weightAndAiBodyShapeCallback != null) {
                    weightAndAiBodyShapeCallback.getWeightAndAIBodyShape(new ArrayList<>(), new ArrayList<>());
                    return;
                }
                return;
            }
            HiAggregateOption hiAggregateOption = koq.d(this.f16570a, 0) ? this.f16570a.get(0) : null;
            List<HiHealthData> list = sparseArray.get(0, new ArrayList());
            boolean b = qsj.b(i, hiAggregateOption);
            ArrayList<cfe> arrayList = new ArrayList<>(16);
            ArrayList<qku> dId_ = qsj.dId_(sparseArray);
            if (koq.b(list)) {
                WeightAndAiBodyShapeCallback weightAndAiBodyShapeCallback2 = this.b;
                if (weightAndAiBodyShapeCallback2 != null) {
                    weightAndAiBodyShapeCallback2.getWeightAndAIBodyShape(arrayList, dId_);
                    return;
                }
                return;
            }
            rag.a(list, arrayList, this.d);
            WeightAndAiBodyShapeCallback weightAndAiBodyShapeCallback3 = this.b;
            if (weightAndAiBodyShapeCallback3 != null) {
                weightAndAiBodyShapeCallback3.getWeightAndAIBodyShape(arrayList, dId_);
            }
            qsj.a(list, b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<qku> dId_(SparseArray<List<HiHealthData>> sparseArray) {
        List<HiHealthData> list = sparseArray.get(1, new ArrayList());
        ArrayList<qku> arrayList = new ArrayList<>();
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new qku(it.next()));
        }
        return arrayList;
    }

    public static void b(final String str, final Context context, final boolean z) {
        if (context == null || !(context instanceof Activity)) {
            LogUtil.h("HealthWeight_WeightViewUtils", "showSetWeightGoalTip context is null");
            return;
        }
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(context).e(R$string.IDS_set_weight_goal_tip).czC_(R$string.IDS_finish_plan, new View.OnClickListener() { // from class: qsi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qsj.dIf_(str, context, z, view);
            }
        }).czz_(R$string.IDS_plugin_fitnessadvice_cancal, new View.OnClickListener() { // from class: qsp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qsj.dIg_(context, z, view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
        c(BaseApplication.e(), AnalyticsValue.WEIGHT_INT_PLAN_2160125.value(), 0);
    }

    static /* synthetic */ void dIf_(String str, final Context context, final boolean z, View view) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthWeight_WeightViewUtils", "finishPlan planId is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "showSetWeightGoalTip intPlanApi == null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            planApi.finishPlan(3, str, new UiCallback<String>() { // from class: qsj.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    nrh.b(context, R$string.sug_haveno_network);
                    LogUtil.b("HealthWeight_WeightViewUtils", "finishPlan onFailure");
                    Context context2 = context;
                    if (!(context2 instanceof Activity) || z) {
                        return;
                    }
                    ((Activity) context2).finish();
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str2) {
                    nrh.b(context, R$string.IDS_hwh_home_program_terminated);
                    LogUtil.a("HealthWeight_WeightViewUtils", "finishPlan OK !! ", str2);
                    Context context2 = context;
                    if ((context2 instanceof Activity) && z) {
                        gnm.aPB_(context2, new Intent(context, (Class<?>) WeightGoalActivity.class));
                        ((Activity) context).finish();
                    }
                }
            });
            c(BaseApplication.e(), AnalyticsValue.WEIGHT_INT_PLAN_2160125.value(), 1);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    static /* synthetic */ void dIg_(Context context, boolean z, View view) {
        if ((context instanceof Activity) && !z) {
            ((Activity) context).finish();
        }
        c(BaseApplication.e(), AnalyticsValue.WEIGHT_INT_PLAN_2160125.value(), -1);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void c(Context context, String str, int i) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(context, str, hashMap, 0);
    }

    public static void a(HealthLevelIndicator healthLevelIndicator, List<nmm> list, double[] dArr, double[] dArr2) {
        if (koq.b(list) || healthLevelIndicator == null) {
            LogUtil.h("HealthWeight_WeightViewUtils", "setIndicatorAllLevelsData, levelsData is empty or mIndicator is null.");
            return;
        }
        int i = 0;
        if (!e(dArr, 0) || !e(dArr2, 1)) {
            LogUtil.h("HealthWeight_WeightViewUtils", "setIndicatorAllLevelsData, valuesArray is empty or rangArray.length < 2.");
            return;
        }
        int length = dArr.length + dArr2.length;
        float[] fArr = new float[length];
        fArr[0] = (float) dArr2[0];
        fArr[length - 1] = (float) dArr2[1];
        while (i < dArr.length) {
            int i2 = i + 1;
            fArr[i2] = (float) dArr[i];
            i = i2;
        }
        for (nmm nmmVar : list) {
            nmmVar.d(nmmVar.d());
            nmmVar.b(nmmVar.b());
        }
        healthLevelIndicator.setAllLevelsData(list, c(fArr));
    }

    public static int a(double d2, double d3) {
        double[] c2 = c(1, d3);
        double[] c3 = c(2, d3);
        if (d2 < c2[1]) {
            return 1;
        }
        return d2 < c3[1] ? 2 : 3;
    }

    public static gsi b(gse gseVar, int i, int i2) {
        gsi gsiVar = new gsi();
        if (i == 0) {
            return c(gseVar, i2);
        }
        if (i == 1 || i == 2) {
            return a(i != 1 ? 0 : 2);
        }
        return gsiVar;
    }

    private static gsi a(int i) {
        gsi gsiVar = new gsi();
        gsiVar.e(i);
        gsiVar.c(0);
        gsiVar.b(0);
        gsf gsfVar = new gsf();
        gsfVar.c(0);
        gsfVar.a(0);
        gsfVar.b(0);
        gsfVar.e(0);
        gsiVar.b(gsfVar);
        gsb gsbVar = new gsb();
        gsbVar.b(1);
        gsbVar.e(0.0f);
        gsbVar.a(0.0f);
        gsbVar.a(0);
        gsiVar.b(gsbVar);
        return gsiVar;
    }

    private static gsi c(gse gseVar, int i) {
        gsi gsiVar = new gsi();
        gsiVar.e(1);
        if (gseVar.b() != null) {
            gsiVar.b(gseVar.b().a());
            gsiVar.c(gseVar.b().d());
        }
        gsb gsbVar = new gsb();
        gsbVar.b(i);
        float[][] fArr = d;
        int i2 = i - 1;
        gsbVar.e(fArr[i2][0]);
        gsbVar.a(fArr[i2][1]);
        gsbVar.a(e[i2][0]);
        gsiVar.b(gsbVar);
        gsf gsfVar = new gsf();
        gsfVar.c(30);
        gsfVar.a(40);
        gsfVar.b(25);
        gsfVar.e(5);
        gsiVar.b(gsfVar);
        return gsiVar;
    }

    public static double[] c(int i, double d2) {
        if (i <= 0 || i > d.length) {
            i = 1;
        }
        float[][] fArr = d;
        int i2 = i - 1;
        return new double[]{UnitUtil.c(UnitUtil.a(fArr[i2][0] * d2, 1), 1), UnitUtil.c(UnitUtil.a(fArr[i2][1] * d2, 1), 1)};
    }

    public static int e(int i, double d2, double d3) {
        if (i <= 0 || i > d.length) {
            i = 1;
        }
        float[] fArr = d[i - 1];
        double d4 = 1.0f - ((fArr[0] + fArr[1]) / 2.0f);
        return ((int) Math.ceil((Math.log(d3) / Math.log(d4)) - (Math.log(d2) / Math.log(d4)))) * 7;
    }

    private static int[] c(float[] fArr) {
        if (fArr == null || fArr.length <= 1) {
            return new int[]{(int) 270.0f};
        }
        int length = fArr.length - 1;
        int[] iArr = new int[length];
        int intValue = BigDecimal.valueOf(270.0f).divide(BigDecimal.valueOf(length)).intValue();
        for (int i = 0; i < length; i++) {
            iArr[i] = intValue;
        }
        return iArr;
    }

    public static void d(ResponseCallback<Object> responseCallback, String str) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("HealthWeight_WeightViewUtils", "saveWeightManagerAndGoal callback is null ");
        } else {
            HiHealthNativeApi.a(BaseApplication.e()).fetchGoalInfo(0, 5, new AnonymousClass2(responseCallback, str));
        }
    }

    /* renamed from: qsj$2, reason: invalid class name */
    class AnonymousClass2 implements HiCommonListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ResponseCallback f16567a;
        final /* synthetic */ String c;

        AnonymousClass2(ResponseCallback responseCallback, String str) {
            this.f16567a = responseCallback;
            this.c = str;
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            if (obj == null) {
                ReleaseLogUtil.a("HealthWeight_WeightViewUtils", "saveWeightManagerAndGoal data is null");
                this.f16567a.onResponse(-1, null);
                return;
            }
            if (!koq.e(obj, HiGoalInfo.class)) {
                ReleaseLogUtil.a("HealthWeight_WeightViewUtils", "saveWeightManagerAndGoal is not list<HiGoalInfo> type");
                this.f16567a.onResponse(-1, null);
                return;
            }
            List list = (List) obj;
            if (koq.b(list) || list.get(0) == null) {
                ReleaseLogUtil.a("HealthWeight_WeightViewUtils", "saveWeightManagerAndGoal goalInformationList is null");
                this.f16567a.onResponse(-1, null);
                return;
            }
            final float goalValue = (float) ((HiGoalInfo) list.get(0)).getGoalValue();
            ReleaseLogUtil.b("HealthWeight_WeightViewUtils", "saveWeightManagerAndGoal fetchGoalInfo weightGoal ", Float.valueOf(goalValue));
            if (goalValue <= 0.0f) {
                this.f16567a.onResponse(-1, null);
                return;
            }
            ThreadPoolManager d = ThreadPoolManager.d();
            final String str = this.c;
            final ResponseCallback responseCallback = this.f16567a;
            d.execute(new Runnable() { // from class: qsn
                @Override // java.lang.Runnable
                public final void run() {
                    qsj.a(goalValue, str, (ResponseCallback<Object>) responseCallback);
                }
            });
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            ReleaseLogUtil.a("HealthWeight_WeightViewUtils", "saveWeightManagerAndGoal onFailure errorCode ", Integer.valueOf(i), " errorMessage ", obj);
            this.f16567a.onResponse(-1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(float f, String str, ResponseCallback<Object> responseCallback) {
        LogUtil.a("HealthWeight_WeightViewUtils", "saveWeightManagerAndGoal weightGoal: ", Float.valueOf(f), " configId: ", str);
        WeightTargetDifferences e2 = rag.e(rag.b());
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("custom.start_weight_base");
        float j = userPreference != null ? CommonUtils.j(userPreference.getValue()) : 0.0f;
        if (e2 == null || j <= 0.0f) {
            ReleaseLogUtil.b("HealthWeight_WeightViewUtils", "getWeightManagerAndGoal weightTargetDifferences: ", e2, " startWeight: ", Float.valueOf(j));
            responseCallback.onResponse(-1, null);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        a(f, currentTimeMillis, responseCallback, str);
        WeightTargetDifferences.WeightTargetType d2 = e2.d();
        ReleaseLogUtil.b("HealthWeight_WeightViewUtils", "saveWeightManagerAndGoal type ", d2, " startWeight ", Float.valueOf(j));
        int i = AnonymousClass5.f16569a[d2.ordinal()];
        if (i == 1) {
            d(currentTimeMillis, f, j, e2, str, responseCallback);
            return;
        }
        if (i == 2) {
            gsi b2 = b(new gse(), 1, 1);
            b2.c(j);
            a(b2, currentTimeMillis, responseCallback, str);
        } else {
            if (i != 3) {
                return;
            }
            gsi b3 = b(new gse(), 2, 1);
            b3.c(j);
            a(b3, currentTimeMillis, responseCallback, str);
        }
    }

    /* renamed from: qsj$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f16569a;

        static {
            int[] iArr = new int[WeightTargetDifferences.WeightTargetType.values().length];
            f16569a = iArr;
            try {
                iArr[WeightTargetDifferences.WeightTargetType.WEIGHT_LOSS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f16569a[WeightTargetDifferences.WeightTargetType.WEIGHT_GAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f16569a[WeightTargetDifferences.WeightTargetType.WEIGHT_KEE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(gsi gsiVar, long j, ResponseCallback<Object> responseCallback, String str) {
        LogUtil.a("HealthWeight_WeightViewUtils", "saveWeightManager weightManager ", gsiVar);
        kpu.c(gsiVar, j, false, (ResponseCallback<gsi>) null);
        if ("900300023".equals(str)) {
            responseCallback.onResponse(2, gsiVar);
        }
    }

    private static void a(float f, long j, ResponseCallback<Object> responseCallback, String str) {
        kpu.e(f, j, null);
        if ("900300022".equals(str)) {
            responseCallback.onResponse(2, Float.valueOf(f));
        }
    }

    private static int a(double d2) {
        float[][] fArr = d;
        if (d2 <= fArr[0][1]) {
            return 1;
        }
        return d2 > ((double) fArr[2][0]) ? 3 : 2;
    }

    public static void d(long j, float f, float f2, WeightTargetDifferences weightTargetDifferences, String str, ResponseCallback<Object> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("HealthWeight_WeightViewUtils", "buildAndSaveWeight callback is null ");
        } else if (weightTargetDifferences == null) {
            ReleaseLogUtil.a("HealthWeight_WeightViewUtils", "buildAndSaveWeight weightTargetDifferences is null");
            responseCallback.onResponse(-1, null);
        } else {
            c(j, f, f2, weightTargetDifferences, str, responseCallback);
        }
    }

    private static void c(final long j, float f, final float f2, WeightTargetDifferences weightTargetDifferences, final String str, final ResponseCallback<Object> responseCallback) {
        UserInfomation c2 = gni.c();
        if (c2 == null) {
            ReleaseLogUtil.a("HealthWeight_WeightViewUtils", "saveWeightManagerLose userInfomation is null");
            responseCallback.onResponse(-1, null);
            return;
        }
        qvr qvrVar = new qvr();
        qvrVar.e(c2.getAge());
        qvrVar.c(c2.getGender());
        qvrVar.b(c2.getHeight() == 0 ? 170 : c2.getHeight());
        qvrVar.c(c2.getWeight() < 10.0f ? 65.0d : c2.getWeight());
        float d2 = jdl.d(DateFormatUtil.b(weightTargetDifferences.e()), DateFormatUtil.b(weightTargetDifferences.b()));
        int ceil = (int) Math.ceil(d2 / 7.0f);
        if (f2 <= 0.0f) {
            ReleaseLogUtil.a("HealthWeight_WeightViewUtils", "getWeightManagerAndGoal startWeight ", Float.valueOf(f2));
            return;
        }
        double pow = 1.0d - Math.pow(Math.abs(f / f2), 1.0f / ceil);
        final int a2 = a(pow);
        LogUtil.a("HealthWeight_WeightViewUtils", "getWeightManagerAndGoal rate ", Double.valueOf(pow), " loseRate ", Integer.valueOf(a2), " days ", Float.valueOf(d2));
        HashMap hashMap = new HashMap();
        hashMap.put("userInfo", qvrVar);
        hashMap.put("goalWeight", Float.valueOf(f));
        hashMap.put("choice", Integer.valueOf(a2));
        hashMap.put("targetSettingChanged", true);
        hashMap.put("getIntPlan", false);
        qzw.a(hashMap, new UiCallback<gse>() { // from class: qsj.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.a("HealthWeight_WeightViewUtils", "getWeightManagerAndGoal failed errorCode ", Integer.valueOf(i), " errorInfo ", str2);
                ResponseCallback.this.onResponse(-1, null);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(gse gseVar) {
                ReleaseLogUtil.b("HealthWeight_WeightViewUtils", "getWeightManagerAndGoal data ", gseVar);
                if (gseVar == null) {
                    return;
                }
                gsi b2 = qsj.b(gseVar, 0, a2);
                b2.c(f2);
                qsj.a(b2, j, (ResponseCallback<Object>) ResponseCallback.this, str);
            }
        });
    }

    public static double c(double d2, int i, int i2) {
        LogUtil.a("HealthWeight_WeightViewUtils", "getWeightGoalLowLimit currentWeight: ", Double.valueOf(d2), " loseRate: ", Integer.valueOf(i), " height: ", Integer.valueOf(i2));
        if (i <= 0 || i > d.length) {
            i = 1;
        }
        double a2 = UnitUtil.a(Math.pow(i2 / 100.0d, 2.0d) * 16.0d, 1);
        float[] fArr = d[i - 1];
        float f = (fArr[0] + fArr[1]) / 2.0f;
        double a3 = UnitUtil.a(Math.pow(1.0f - f, e[r14][0]) * d2, 1);
        LogUtil.a("HealthWeight_WeightViewUtils", "getWeightGoalLowLimit weightBmi: ", Double.valueOf(a2), " midRate: ", Float.valueOf(f), " weight: ", Double.valueOf(a3));
        if (Double.compare(a2, d2) >= 0) {
            return 10.0d;
        }
        return Math.max(Math.max(10.0d, a3), a2);
    }
}
