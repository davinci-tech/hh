package com.huawei.health.routeradapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.sport.model.WorkoutRecord;
import defpackage.mmp;
import defpackage.mod;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class TradePretreatmentService implements PretreatmentService {
    private static Map<String, String> c = new HashMap<String, String>() { // from class: com.huawei.health.routeradapter.TradePretreatmentService.1
        {
            put("1", "/PluginFitnessAdvice/TrainDetail");
            put("12", "/Main/SeriesCourseListActivity");
        }
    };

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            LogUtil.d("TradePretreatmentService", "uri == null");
            return false;
        }
        LogUtil.d("TradePretreatmentService", zN_.toString());
        if (TextUtils.isEmpty(zN_.getQuery())) {
            return false;
        }
        avB_(context, zN_);
        return false;
    }

    private void avB_(Context context, Uri uri) {
        String queryParameter = uri.getQueryParameter("type");
        if (!TextUtils.isEmpty(queryParameter) && c.containsKey(queryParameter)) {
            avC_(context, queryParameter, uri);
        } else {
            LogUtil.a("TradePretreatmentService", "not support ", queryParameter);
        }
    }

    private void avC_(Context context, String str, Uri uri) {
        String queryParameter = uri.getQueryParameter("id");
        Bundle bundle = new Bundle();
        if ("1".equals(str)) {
            WorkoutRecord workoutRecord = new WorkoutRecord();
            workoutRecord.saveExerciseTime(new Date().getTime());
            workoutRecord.saveWorkoutId(queryParameter);
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(workoutRecord);
            mod.c(context, new mmp(queryParameter), arrayList);
            return;
        }
        bundle.putString("id", queryParameter);
        AppRouter.b(c.get(str)).a(268435456).zF_(bundle).c(context);
    }
}
