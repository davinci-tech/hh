package com.huawei.haf.bundle.extension;

import android.content.Context;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.bundle.extension.fakecomponents.FakeActivity;
import com.huawei.haf.bundle.extension.fakecomponents.FakeReceiver;
import com.huawei.haf.bundle.extension.fakecomponents.FakeService;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class ComponentInfoProvider {
    private static ComponentInfoProvider d;

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, String> f2066a = new HashMap();
    private final List<String> b = new ArrayList();
    private final List<String> e = new ArrayList();
    private final List<String> c = new ArrayList();

    private ComponentInfoProvider() {
        Set<String> d2 = AppBundleBuildConfig.d();
        if (d2.isEmpty()) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer(64);
        Iterator<String> it = d2.iterator();
        while (it.hasNext()) {
            c(it.next(), stringBuffer);
        }
    }

    public static ComponentInfoProvider e() {
        if (d == null) {
            d = new ComponentInfoProvider();
        }
        return d;
    }

    public Class<?> e(String str) {
        if (this.b.contains(str)) {
            return FakeActivity.class;
        }
        if (this.e.contains(str)) {
            return FakeService.class;
        }
        if (this.c.contains(str)) {
            return FakeReceiver.class;
        }
        return null;
    }

    public String d(String str) {
        return this.f2066a.get(str);
    }

    public String d(Context context, String str) {
        return ComponentInfoReader.e(context, str);
    }

    public String b(Context context, String str) {
        return ComponentInfoReader.c(context, str);
    }

    private void c(String str, StringBuffer stringBuffer) {
        stringBuffer.delete(0, stringBuffer.length());
        d(ComponentInfoReader.a(str), this.b, str, "Activities", stringBuffer);
        d(ComponentInfoReader.d(str), this.e, str, "Services", stringBuffer);
        d(ComponentInfoReader.c(str), this.c, str, "Receivers", stringBuffer);
        if (stringBuffer.length() > 0) {
            LogUtil.c("Bundle_ComponentInfo", str, " {", stringBuffer.toString(), "}");
        }
    }

    private void d(String[] strArr, List<String> list, String str, String str2, StringBuffer stringBuffer) {
        if (strArr.length == 0) {
            return;
        }
        list.addAll(Arrays.asList(strArr));
        for (String str3 : strArr) {
            this.f2066a.put(str3, str);
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.append(", ");
        }
        stringBuffer.append(str2).append('=').append(strArr.length);
    }
}
