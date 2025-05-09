package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.os.Looper;
import android.os.MessageQueue;
import android.text.TextUtils;
import com.huawei.android.bundlecore.load.listener.OnModuleLoadListener;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.bundle.extension.BundleClassNotFoundInterceptor;
import com.huawei.haf.bundle.extension.BundleExtension;
import com.huawei.haf.bundle.extension.BundleLoadManager;
import com.huawei.haf.bundle.extension.BundleLoadMode;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.dynamic.DynamicApplicationLoaders;
import com.huawei.haf.dynamic.DynamicLoaderException;
import dalvik.system.PathClassLoader;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes8.dex */
public final class zh implements BundleLoadManager {
    private static final zh e = new zh();
    private final Map<String, String> i = new ConcurrentHashMap();
    private final Map<String, String> f = new ConcurrentHashMap();
    private final Context d = AppBundleBuildConfig.c();

    /* renamed from: a, reason: collision with root package name */
    private final BundleLoadMode f17773a = BundleLoadMode.valueOf(AppBundleBuildConfig.j());
    private final boolean c = AppBundleBuildConfig.o();
    private final String[] h = AppBundleBuildConfig.i();
    private final String[] b = AppBundleBuildConfig.g();

    private zh() {
        BundleExtension.d(this);
    }

    public static zh a() {
        return e;
    }

    public void e() {
        if (j() && h()) {
            d(b().getClassLoader());
        }
        ClassLoader classLoader = b().getClassLoader();
        if (DynamicApplicationLoaders.d(classLoader)) {
            DynamicApplicationLoaders.d(classLoader, new BundleClassNotFoundInterceptor(getClass().getClassLoader(), this, this.f17773a));
        }
    }

    public void d() {
        if (this.c && h()) {
            g();
        }
    }

    public Runnable c(List<Intent> list, OnModuleLoadListener onModuleLoadListener) {
        return new ze(this, list, onModuleLoadListener);
    }

    @Override // com.huawei.haf.bundle.extension.BundleLoadManager
    public Set<String> getLoadedModuleNames() {
        return this.i.keySet();
    }

    @Override // com.huawei.haf.bundle.extension.BundleLoadManager
    public Set<String> getLoadedModuleApkPaths() {
        return this.f.keySet();
    }

    @Override // com.huawei.haf.bundle.extension.BundleLoadManager
    public boolean checkModuleLoaded(String str) {
        return this.i.containsKey(str);
    }

    @Override // com.huawei.haf.bundle.extension.BundleLoadManager
    public boolean checkModuleExisted(String str, boolean z) {
        yi e2;
        return checkModuleLoaded(str) || (e2 = yg.a().e(b(), str)) == null || a(e2, z);
    }

    @Override // com.huawei.haf.bundle.extension.BundleLoadManager
    public boolean checkModuleUninstalled(String str, boolean z) {
        yi e2 = yg.a().e(b(), str);
        return e2 != null && d(e2, z);
    }

    @Override // com.huawei.haf.bundle.extension.BundleLoadManager
    public boolean loadInstalledModules(List<String> list) {
        return d(d(list), true);
    }

    public BundleLoadMode c() {
        return this.f17773a;
    }

    Context b() {
        return this.d;
    }

    void e(String str, String str2) {
        this.i.put(str, str2);
        this.f.put(str2, str);
    }

    private boolean j() {
        if (Build.VERSION.SDK_INT < 29) {
            return this.c;
        }
        return this.c && !DynamicApplicationLoaders.d(b().getClassLoader());
    }

    private List<Intent> e(Collection<yi> collection, boolean z) {
        String replace = !z ? ProcessUtil.b().replace(b().getPackageName(), "") : null;
        ArrayList arrayList = new ArrayList(collection.size());
        for (yi yiVar : collection) {
            if (checkModuleLoaded(yiVar.f())) {
                if (!z) {
                    LogUtil.c("Bundle_LoadManager", yiVar.f(), " has been loaded, ignore it!");
                }
            } else if (!z && !d(yiVar, replace)) {
                LogUtil.c("Bundle_LoadManager", yiVar.f(), " do not need work in process ", ProcessUtil.b());
            } else {
                Intent eG_ = eG_(yiVar, z);
                if (eG_ != null) {
                    arrayList.add(eG_);
                }
                if (!z) {
                    Object[] objArr = new Object[7];
                    objArr[0] = yiVar.f();
                    objArr[1] = " will work in process ";
                    objArr[2] = ProcessUtil.b();
                    objArr[3] = ", ";
                    objArr[4] = eG_ == null ? "but" : "and";
                    objArr[5] = " it is ";
                    objArr[6] = eG_ == null ? "not installed" : "installed";
                    LogUtil.c("Bundle_LoadManager", objArr);
                }
            }
        }
        return arrayList;
    }

    private boolean d(yi yiVar, String str) {
        List<String> n = yiVar.n();
        if (CollectionUtils.d(n)) {
            return true;
        }
        return n.contains(str);
    }

    private void g() {
        final Collection<yi> a2 = yg.a().a(b());
        if (Looper.myLooper() == null) {
            d(a2, false);
        } else {
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: zh.2
                @Override // android.os.MessageQueue.IdleHandler
                public boolean queueIdle() {
                    zh.this.d((Collection<yi>) a2, false);
                    return false;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(Collection<yi> collection, boolean z) {
        if (collection.isEmpty()) {
            return false;
        }
        List<Intent> e2 = e(collection, z);
        if (e2.isEmpty()) {
            return false;
        }
        c(e2, null).run();
        return true;
    }

    private boolean d(yi yiVar, boolean z) {
        if (yw.a().i(yiVar).exists()) {
            return true;
        }
        List<String> e2 = yiVar.e();
        if (!z || e2.isEmpty()) {
            return false;
        }
        for (String str : e2) {
            yi e3 = yg.a().e(b(), str);
            if (e3 != null && yw.a().i(e3).exists()) {
                LogUtil.c("Bundle_LoadManager", "Dependency ", str, " is uninstalled!");
                return true;
            }
        }
        return false;
    }

    private boolean a(yi yiVar, boolean z) {
        if (!zg.e(yiVar)) {
            return false;
        }
        List<String> e2 = yiVar.e();
        if (!z || e2.isEmpty()) {
            return true;
        }
        LogUtil.c("Bundle_LoadManager", "Module ", yiVar.f(), " has dependencies ", e2);
        for (String str : e2) {
            yi e3 = yg.a().e(b(), str);
            if (e3 != null && !checkModuleLoaded(str) && !zg.e(e3)) {
                LogUtil.c("Bundle_LoadManager", "Dependency module ", str, " mark file is not existed!");
                return false;
            }
        }
        return true;
    }

    private Intent eG_(yi yiVar, boolean z) {
        ArrayList<String> arrayList = null;
        if (!a(yiVar, true)) {
            return null;
        }
        if (!z && d(yiVar, true)) {
            return null;
        }
        File a2 = yw.a().a(yiVar);
        if (yiVar.l()) {
            arrayList = new ArrayList<>();
            arrayList.add(a2.getAbsolutePath());
        }
        Intent intent = new Intent();
        intent.putExtra("moduleName", yiVar.f());
        intent.putExtra("apk", a2.getAbsolutePath());
        if (arrayList != null) {
            intent.putStringArrayListExtra("added-dex", arrayList);
        }
        return intent;
    }

    private Collection<yi> d(List<String> list) {
        if (CollectionUtils.d(list)) {
            return Collections.emptyList();
        }
        Collection<yi> a2 = yg.a().a(b());
        ArrayList arrayList = new ArrayList(list.size());
        e(arrayList, a2, list);
        Set<String> c = c(arrayList);
        if (!c.isEmpty()) {
            c.removeAll(list);
            LogUtil.c("Bundle_LoadManager", "Automatically add dependencies ", c.toString(), " for load module ", list.toString());
            e(arrayList, a2, c);
        }
        return arrayList;
    }

    private void e(List<yi> list, Collection<yi> collection, Collection<String> collection2) {
        for (yi yiVar : collection) {
            if (collection2.contains(yiVar.f())) {
                list.add(yiVar);
            }
        }
    }

    private Set<String> c(List<yi> list) {
        HashSet hashSet = new HashSet();
        Iterator<yi> it = list.iterator();
        while (it.hasNext()) {
            hashSet.addAll(it.next().e());
        }
        return hashSet;
    }

    private Context f() {
        Context b = b();
        while (b instanceof ContextWrapper) {
            b = ((ContextWrapper) b).getBaseContext();
        }
        return b;
    }

    private void d(ClassLoader classLoader) {
        if (classLoader instanceof PathClassLoader) {
            try {
                DynamicApplicationLoaders.a(classLoader, f());
            } catch (DynamicLoaderException e2) {
                LogUtil.e("Bundle_LoadManager", "Failed to hook PathClassloader, ex=", LogUtil.a(e2));
            }
        }
    }

    private boolean h() {
        if ((this.h.length == 0 && this.b.length == 0) || ProcessUtil.d()) {
            return true;
        }
        String b = ProcessUtil.b();
        for (String str : this.b) {
            if (a(str).equals(b)) {
                return false;
            }
        }
        String[] strArr = this.h;
        int length = strArr.length;
        for (int i = 0; i < length && !a(strArr[i]).equals(b); i++) {
        }
        return true;
    }

    private String a(String str) {
        String packageName = b().getPackageName();
        if (TextUtils.isEmpty(str)) {
            return packageName;
        }
        if (str.startsWith(packageName)) {
            return str;
        }
        return packageName + str;
    }
}
