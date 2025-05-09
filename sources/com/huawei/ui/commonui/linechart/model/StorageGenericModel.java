package com.huawei.ui.commonui.linechart.model;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import defpackage.koq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class StorageGenericModel implements IStorageModel {
    private PresentStyle b;
    private Map<String, Float> d = new HashMap();
    private Map<String, Object> e = new HashMap();

    public interface PresentStyle {
        boolean validate();
    }

    public void c(String str, float f) {
        this.d.put(str, Float.valueOf(f));
    }

    public void e(String str, Object obj) {
        List list;
        if (this.e.get(str) == null) {
            list = new ArrayList();
            this.e.put(str, list);
        } else {
            Object obj2 = this.e.get(str);
            if (!koq.e(obj2, Object.class)) {
                throw new RuntimeException("addGenericObject old not list");
            }
            list = (List) obj2;
        }
        list.add(obj);
    }

    public List<Object> e(String str) {
        if (str != null) {
            Object obj = this.e.get(str);
            if (obj != null) {
                if (!koq.e(obj, Object.class)) {
                    throw new RuntimeException("queryGenericObjects old not list");
                }
                return (List) obj;
            }
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }

    public void d(PresentStyle presentStyle) {
        this.b = presentStyle;
    }

    public PresentStyle a() {
        return this.b;
    }

    public boolean b() {
        PresentStyle presentStyle = this.b;
        return presentStyle != null && presentStyle.validate();
    }

    public class a implements PresentStyle {
        private String c;
        private String[] e;

        public a(String str, String... strArr) {
            this.c = str;
            if (strArr == null) {
                this.e = null;
            } else {
                this.e = (String[]) strArr.clone();
            }
        }

        public float c() {
            Float f = (Float) StorageGenericModel.this.d.get(this.c);
            if (f != null) {
                return f.floatValue();
            }
            String[] strArr = this.e;
            if (strArr == null || strArr.length == 0) {
                return 0.0f;
            }
            for (String str : strArr) {
                Float f2 = (Float) StorageGenericModel.this.d.get(str);
                if (f2 != null) {
                    return f2.floatValue();
                }
            }
            return 0.0f;
        }

        @Override // com.huawei.ui.commonui.linechart.model.StorageGenericModel.PresentStyle
        public boolean validate() {
            return !TextUtils.isEmpty(this.c);
        }
    }

    public class e implements PresentStyle {

        /* renamed from: a, reason: collision with root package name */
        private String f8885a;
        private String d;

        public e(String str, String str2) {
            this.f8885a = str;
            this.d = str2;
            if ("HR_WARNING_MAX".equals(str2) && "HR_WARNING_MIN".equals(this.f8885a) && StorageGenericModel.this.e.size() == 0) {
                LogUtil.b("BarDataPresentation", "not has detail");
            }
        }

        public float b() {
            Float f = (Float) StorageGenericModel.this.d.get(this.d);
            if (f == null) {
                return 0.0f;
            }
            return f.floatValue();
        }

        public float e() {
            Float f = (Float) StorageGenericModel.this.d.get(this.f8885a);
            if (f == null) {
                return 0.0f;
            }
            return f.floatValue();
        }

        @Override // com.huawei.ui.commonui.linechart.model.StorageGenericModel.PresentStyle
        public boolean validate() {
            return !TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(this.f8885a) && StorageGenericModel.this.d.containsKey(this.d) && StorageGenericModel.this.d.containsKey(this.f8885a);
        }
    }

    public static class d implements PresentStyle {
        @Override // com.huawei.ui.commonui.linechart.model.StorageGenericModel.PresentStyle
        public boolean validate() {
            return true;
        }

        public d(String str) {
        }
    }
}
