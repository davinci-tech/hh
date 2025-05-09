package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class gdu {
    public static List<FitWorkout> e(int i) {
        String b;
        String str;
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            str = ash.b("RECOMMEND_WORKOUT_CREATE_TIME_COURSE");
            b = ash.b("RECOMMEND_WORKOUT_KEY_COURSE");
        } else {
            String b2 = ash.b("RECOMMEND_WORKOUT_CREATE_TIME_COURSE" + i);
            b = ash.b("RECOMMEND_WORKOUT_KEY_COURSE" + i);
            str = b2;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(b)) {
            return arrayList;
        }
        long i2 = jec.i() - CommonUtil.g(str);
        if (i2 <= 0 || i2 >= 86400000) {
            return arrayList;
        }
        try {
            return (List) new Gson().fromJson(b, new TypeToken<List<FitWorkout>>() { // from class: gdu.4
            }.getType());
        } catch (JsonSyntaxException e) {
            LogUtil.h("LocalDataUtil", "parse RecommendData error ", e.getMessage());
            return arrayList;
        }
    }

    public static void c(final List<FitWorkout> list, final int i) {
        if (HandlerExecutor.b()) {
            jdx.b(new Runnable() { // from class: gdu.2
                @Override // java.lang.Runnable
                public void run() {
                    gdu.c(list, i);
                }
            });
            return;
        }
        long i2 = jec.i();
        if (i == 0) {
            ash.a("RECOMMEND_WORKOUT_KEY_COURSE", new Gson().toJson(list));
            ash.a("RECOMMEND_WORKOUT_CREATE_TIME_COURSE", Long.toString(i2));
            return;
        }
        ash.a("RECOMMEND_WORKOUT_KEY_COURSE" + i, new Gson().toJson(list));
        ash.a("RECOMMEND_WORKOUT_CREATE_TIME_COURSE" + i, Long.toString(i2));
    }
}
