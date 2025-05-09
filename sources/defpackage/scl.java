package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.EzPluginManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class scl {

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f17021a = new ArrayList<String>() { // from class: scl.4
        {
            add("e570b133-357b-4b49-b894-600a27a0e826");
            add("9684a253-0fb5-4560-8fa3-b925163f8b67");
            add("825c82bd-84fe-44a0-9884-6a764bd73183");
            add("578d0675-cece-4426-bf28-43ce31eb7b5d");
            add("54af062d-b049-4880-beda-f0cbe64e9205");
            add("9bf158ba-49b0-46aa-9fdf-ed75da1569cf");
            add("6ab08ad8-753b-4dd9-bf3a-798a0a1d81bf");
            add("e4b0b1d5-2003-4d88-8b5f-c4f64542040b");
            add("a8ba095d-4123-43c4-a30a-0240011c58de");
            add("34fa0346-d46c-439d-9cb0-2f696618846b");
            add("7a1063dd-0e0f-4a72-9939-461476ff0259");
            add("ccd1f0f8-8c57-4bd7-a884-0ef38482f15f");
            add("33123f39-7fc1-420b-9882-a4b0d6c61100");
        }
    };

    public static long e() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        long courseUseCacheSize = courseApi != null ? courseApi.getCourseUseCacheSize() : 0L;
        LogUtil.a("CacheUtils", "getFitnessCacheSize useCacheSize is ", Long.valueOf(courseUseCacheSize));
        return courseUseCacheSize;
    }

    public static long b(Context context) {
        Iterator<File> it = d(context).iterator();
        long j = 0;
        while (it.hasNext()) {
            j += FileUtils.j(it.next());
        }
        LogUtil.a("CacheUtils", "OtherCacheSize:", Long.valueOf(j));
        return j;
    }

    public static List<File> d(Context context) {
        ArrayList arrayList = new ArrayList();
        String m = LogConfig.i() ? LogConfig.m() : CommonUtils.i(null);
        arrayList.add(new File(m + BaseApplication.d()));
        arrayList.add(new File(m + "BetaClub"));
        arrayList.add(new File(m + "SmartWearableDFX"));
        arrayList.add(new File(m + "SmartWearableDFXHonor"));
        arrayList.add(new File(m + "com.huawei.health.otalog"));
        arrayList.add(new File(m + "tracktest"));
        arrayList.add(new File(m + "CoreSleepLog"));
        arrayList.add(AppInfoUtils.f("Log"));
        arrayList.add(BaseApplication.e().getCacheDir());
        File a2 = AppInfoUtils.a("huaweisystem");
        if (a2 != null) {
            arrayList.add(a2);
            arrayList.add(AppInfoUtils.a("Log"));
            arrayList.add(BaseApplication.e().getExternalCacheDir());
            arrayList.add(AppInfoUtils.c("nanotraceFiles"));
        }
        return arrayList;
    }

    public static long e(Context context) {
        String str;
        if (context == null) {
            return 0L;
        }
        try {
            str = context.getFilesDir().getCanonicalPath() + File.separator + "achievemedal";
        } catch (IOException e) {
            LogUtil.b("CacheUtils", "getMedalCacheSize(),", e.getMessage());
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CacheUtils", "getMedalCacheSize(), medalPath is null");
            return 0L;
        }
        long j = FileUtils.j(new File(str));
        LogUtil.c("CacheUtils", "getMedalCacheSize medalSize is ", Long.valueOf(j));
        return j;
    }

    public static HashSet<String> j(Context context) {
        HashSet<String> hashSet = new HashSet<>(16);
        if (Utils.o() && !CommonUtil.cc()) {
            List<msa> b = EzPluginManager.a().b();
            if (koq.b(b)) {
                return hashSet;
            }
            for (msa msaVar : b) {
                if (msaVar != null && !TextUtils.equals(msaVar.k(), "SMART_WATCH") && !TextUtils.equals(msaVar.k(), "SMART_BAND") && !TextUtils.equals(msaVar.k(), "SMART_HEADPHONES") && !TextUtils.isEmpty(b(msaVar.b()))) {
                    hashSet.add(b(msaVar.b()));
                }
            }
            LogUtil.a("CacheUtils", "Oversea thirdDevicePathSet = ", hashSet.toString());
            return hashSet;
        }
        Iterator<Map.Entry<String, String>> it = msr.d.entrySet().iterator();
        List<msa> list = null;
        while (it.hasNext()) {
            String key = it.next().getKey();
            if ((!Utils.o() || CommonUtil.cc()) && msr.c.containsKey(key)) {
                list = EzPluginManager.a().e(key);
            }
            if (list != null && !list.isEmpty()) {
                c(hashSet, list);
            }
        }
        e(context, hashSet);
        for (String str : f17021a) {
            if (!TextUtils.isEmpty(b(str))) {
                hashSet.add(b(str));
            }
        }
        LogUtil.a("CacheUtils", "thirdDevicePathSet = ", hashSet.toString());
        return hashSet;
    }

    private static void e(Context context, HashSet<String> hashSet) {
        List<msa> d;
        String b = SharedPreferenceManager.b(context, String.valueOf(20010), "key_ezplugin_healthcloud_mgr");
        if (TextUtils.isEmpty(b)) {
            return;
        }
        Iterator it = ((HashSet) new Gson().fromJson(b, new TypeToken<HashSet<String>>() { // from class: scl.3
        }.getType())).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!TextUtils.isEmpty(str) && (d = cxl.b().d(str)) != null && !d.isEmpty()) {
                c(hashSet, d);
            }
        }
    }

    private static void c(HashSet<String> hashSet, List<msa> list) {
        for (msa msaVar : list) {
            if (msaVar != null && !TextUtils.isEmpty(b(msaVar.b()))) {
                hashSet.add(b(msaVar.b()));
            }
        }
    }

    public static String b(String str) {
        if (!TextUtils.isEmpty(str)) {
            String str2 = mrv.d + str;
            if (new File(str2).exists() && !dks.d(str, (String) null)) {
                return str2;
            }
        }
        return "";
    }

    public static long b(HashSet<String> hashSet) {
        long j = 0;
        if (hashSet != null && hashSet.size() != 0) {
            Iterator<String> it = hashSet.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!TextUtils.isEmpty(next)) {
                    j += FileUtils.j(new File(next));
                }
            }
            LogUtil.c("CacheUtils", "getDeviceCacheSize deviceSize is ", Long.valueOf(j));
        }
        return j;
    }

    public static long c(Context context) {
        if (context == null || hab.j() || !LanguageUtil.m(context) || Utils.o()) {
            return 0L;
        }
        long j = FileUtils.j(new File(gzv.e));
        LogUtil.a("CacheUtils", "getSmartCoachCacheSize smartCoachCacheSize is ", Long.valueOf(j));
        return j;
    }

    public static long d(Context context, String str) {
        if (context == null) {
            return 0L;
        }
        return FileUtils.j(new File(context.getFilesDir() + File.separator + str + File.separator));
    }

    public static long a(Context context) {
        if (context == null) {
            LogUtil.h("CacheUtils", "getSleepMusicCachePath context is null");
            return 0L;
        }
        return FileUtils.j(new File(StorageUtil.getNativeFilePath(context, "h5hosting-drcn.dbankcdn.cn/operationmusic"))) + FileUtils.j(new File(StorageUtil.getNativeFilePath(context, "operationmusic"))) + FileUtils.j(new File(StorageUtil.getNativeFilePath(context, "com.huawei.health.h5.sleeping-music/sleeping-music")));
    }
}
