package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class qko {
    public static void a(List<cfe> list) {
        d(list, "CACHE_LAST_WEIGHT");
    }

    public static <T> void d(List<T> list, String str) {
        Context e = BaseApplication.e();
        String b = b(e);
        SharedPreferenceManager.e(e, "day_weight_cache", str + b, HiJsonUtil.e(list), (StorageParams) null);
    }

    public static ArrayList<cfe> d() {
        return c(d("CACHE_LAST_WEIGHT", new TypeToken<List<cfe>>() { // from class: qko.3
        }.getType()), MultiUsersManager.INSTANCE.getCurrentUser());
    }

    private static <T> ArrayList<T> d(String str, Type type) {
        ArrayList<T> arrayList;
        Context e = BaseApplication.e();
        String b = SharedPreferenceManager.b(e, "day_weight_cache", str + b(e));
        if (TextUtils.isEmpty(b)) {
            LogUtil.a("WeightDataCache", "start load data animal");
            return new ArrayList<>();
        }
        try {
            arrayList = (ArrayList) HiJsonUtil.b(b, type);
        } catch (JsonIOException | JsonSyntaxException | IllegalStateException | NumberFormatException e2) {
            LogUtil.b("WeightDataCache", "getCacheData catch all Exception ", LogAnonymous.b(e2));
            arrayList = null;
        }
        if (!koq.b(arrayList)) {
            return arrayList;
        }
        LogUtil.a("WeightDataCache", "getCacheData error");
        return new ArrayList<>();
    }

    private static ArrayList<cfe> c(ArrayList<cfe> arrayList, cfi cfiVar) {
        ArrayList<cfe> arrayList2 = new ArrayList<>();
        Iterator<cfe> it = arrayList.iterator();
        while (it.hasNext()) {
            cfe c = qsj.c(it.next(), Utils.o(), cfiVar);
            if (c != null) {
                arrayList2.add(c);
            }
        }
        return arrayList2;
    }

    public static <T> boolean b(List<T> list, List<T> list2) {
        if (list.size() == 0) {
            return true;
        }
        return !HiJsonUtil.e(list).equals(HiJsonUtil.e(list2));
    }

    private static String b(Context context) {
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        return !TextUtils.isEmpty(accountInfo) ? SecurityUtils.d(accountInfo) : "";
    }
}
