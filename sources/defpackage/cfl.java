package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.hwlogsmodel.LogUtil;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public class cfl {
    private static cfl b;

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<MeasureKit> f681a;
    private Map<String, com.huawei.hihealth.device.open.MeasureKit> d = new HashMap();
    private HashMap<String, String> e;

    private cfl() {
        ArrayList<MeasureKit> arrayList = new ArrayList<>();
        this.f681a = arrayList;
        arrayList.add(new cjm());
        this.f681a.add(new dam());
        this.f681a.add(new dag());
        this.f681a.add(new daf());
        this.f681a.add(new dai());
        this.f681a.add(new dat());
        this.f681a.add(new czw());
        this.f681a.add(new czy());
        this.f681a.add(new cjp());
        this.f681a.add(new cgj());
        this.f681a.add(new cgw());
        this.f681a.add(new cgr());
        this.f681a.add(new dar());
        this.f681a.add(new cfo());
        this.f681a.add(new dbk());
        this.f681a.add(new dbg());
        this.f681a.add(new dbi());
        this.f681a.add(new dbf());
        this.f681a.add(new dbq());
    }

    public static cfl a() {
        if (b == null) {
            b = new cfl();
        }
        return b;
    }

    public void e(String str, String str2) {
        LogUtil.a("MeasureKitManager", "Enter registerExternalKit:", str);
        if (str == null || str2 == null) {
            return;
        }
        if (this.e == null) {
            this.e = new HashMap<>();
        }
        this.e.put(str, str2);
    }

    private String b(String str) {
        int indexOf;
        if (TextUtils.isEmpty(str) || (indexOf = str.indexOf(":")) == -1) {
            return null;
        }
        return str.substring(0, indexOf);
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf(":");
        return indexOf == -1 ? str : str.substring(indexOf + 1);
    }

    private com.huawei.hihealth.device.open.MeasureKit i(String str) {
        if (str == null) {
            return null;
        }
        HashMap<String, String> hashMap = this.e;
        String str2 = hashMap != null ? hashMap.get(str) : null;
        LogUtil.a("MeasureKitManager", "loadExternalKit kitUuid:", str, ",externalKits:", this.e, ",measureEntry:", str2);
        if (str2 == null) {
            return null;
        }
        if (!this.d.containsKey(str)) {
            Context a2 = cpp.a();
            try {
                String b2 = b(str2);
                LogUtil.a("MeasureKitManager", "kit uuid is:", str);
                if (str.equals("54C9739F-CA5C-4347-9F00-75B9DDF2C649") && !TextUtils.isEmpty(b2)) {
                    int indexOf = b2.indexOf(".dex");
                    if (indexOf == -1) {
                        indexOf = 0;
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(b2.substring(0, indexOf));
                    stringBuffer.append("_androidX.dex");
                    b2 = stringBuffer.toString();
                    LogUtil.a("MeasureKitManager", "kit path:", b2);
                }
                if (TextUtils.isEmpty(b2)) {
                    LogUtil.h("MeasureKitManager", "loadExternalKit externalKitPath is empty");
                    return null;
                }
                this.d.put(str, (com.huawei.hihealth.device.open.MeasureKit) new DexClassLoader(new File(b2).getCanonicalPath(), a2.getDir("runtime", 0).getCanonicalPath(), a2.getApplicationInfo().nativeLibraryDir, a2.getClassLoader()).loadClass(a(str2)).getConstructor(Context.class).newInstance(a2));
                LogUtil.a("MeasureKitManager", "loadExternalKit kit:", this.d.get(str));
                return this.d.get(str);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
                LogUtil.b("MeasureKitManager", "loadExternalKit exception");
                return null;
            }
        }
        return this.d.get(str);
    }

    private com.huawei.hihealth.device.open.MeasureKit j(String str) {
        this.d.remove(str);
        return i(str);
    }

    public com.huawei.hihealth.device.open.MeasureKit c(String str) {
        return i(str);
    }

    public com.huawei.hihealth.device.open.MeasureKit d(String str) {
        return j(str);
    }

    public MeasureKit e(String str) {
        if (str != null) {
            Iterator<MeasureKit> it = this.f681a.iterator();
            while (it.hasNext()) {
                MeasureKit next = it.next();
                if (str.equals(next.getUuid())) {
                    return next;
                }
            }
        }
        return null;
    }
}
