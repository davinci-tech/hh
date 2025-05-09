package com.huawei.hwcloudjs.d.a;

import com.huawei.hwcloudjs.service.auth.bean.AuthBean;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public final class e extends ObjectInputStream {

    /* renamed from: a, reason: collision with root package name */
    private static List<String> f6207a;

    @Override // java.io.ObjectInputStream
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        if (f6207a.contains(objectStreamClass.getName())) {
            return super.resolveClass(objectStreamClass);
        }
        throw new ClassNotFoundException(objectStreamClass.getName() + " not find");
    }

    public e(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    public e() throws SecurityException, IOException {
    }

    static {
        ArrayList arrayList = new ArrayList();
        f6207a = arrayList;
        arrayList.add(AuthBean.class.getName());
    }
}
