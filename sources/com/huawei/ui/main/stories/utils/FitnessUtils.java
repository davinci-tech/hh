package com.huawei.ui.main.stories.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gnm;
import defpackage.pvz;
import health.compact.a.LanguageUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes7.dex */
public class FitnessUtils {
    private static final Object c = new Object();
    private Context e;

    public FitnessUtils(Context context) {
        this.e = context;
    }

    public static String d(String str) {
        return "FitnessStepDetailActivity".equals(str) ? "Step" : "FitnessCalorieDetailActivity".equals(str) ? "Calorie" : "FitnessDistanceDetailActivity".equals(str) ? "Distance" : "FitnessClimbDetailActivity".equals(str) ? "Climb" : "";
    }

    public static boolean c(long j, long j2) {
        if (j2 == -1 || j == -1) {
            LogUtil.a("SCUI_FitnessUtils", "isSameDay return false");
            return false;
        }
        Date date = new Date(j);
        Date date2 = new Date(j2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat.format(date);
        String format2 = simpleDateFormat.format(date2);
        LogUtil.a("SCUI_FitnessUtils", "orgString=", format, "cmpString=", format2);
        return format.equals(format2);
    }

    public static List<Double> c(List<pvz> list) {
        synchronized (c) {
            ArrayList arrayList = new ArrayList();
            if (list != null && list.size() != 0) {
                arrayList.addAll(list);
                ArrayList arrayList2 = new ArrayList();
                LogUtil.a("SCUI_FitnessUtils", "parseToBarChartData fitness.size() = ", Integer.valueOf(arrayList.size()));
                LogUtil.a("SCUI_FitnessUtils", "parseToBarChartData fitness = ", arrayList);
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i) != null) {
                        arrayList2.add(Double.valueOf(((pvz) arrayList.get(i)).b()));
                    } else {
                        arrayList2.add(Double.valueOf(0.0d));
                    }
                }
                return arrayList2;
            }
            return null;
        }
    }

    public void dVX_(ImageView... imageViewArr) {
        LogUtil.c("SCUI_FitnessUtils", "setBackground");
        for (ImageView imageView : imageViewArr) {
            if (LanguageUtil.bc(this.e)) {
                LogUtil.c("SCUI_FitnessUtils", "-----------common_ui_arrow_left");
                imageView.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            } else {
                LogUtil.c("SCUI_FitnessUtils", "----------arrow_right_normal");
                imageView.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
            }
        }
    }

    public static int a(int i) {
        int i2;
        LogUtil.c("SCUI_FitnessUtils", "getRegularityScore");
        if (i < 30) {
            i2 = 100 - ((i * 2) / 3);
        } else {
            i2 = i < 190 ? 95 - (i / 2) : 0;
        }
        LogUtil.c("SCUI_FitnessUtils", "score = " + i2);
        return i2;
    }

    public static void c(Context context, Class<?> cls, long j) {
        if (context == null) {
            return;
        }
        if (j <= 0) {
            j = 0;
        }
        Intent intent = new Intent(context, cls);
        intent.putExtra("key_bundle_health_last_data_time", j);
        gnm.aPB_(context, intent);
    }
}
