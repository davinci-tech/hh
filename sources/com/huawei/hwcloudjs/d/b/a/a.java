package com.huawei.hwcloudjs.d.b.a;

import android.text.TextUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hwcloudjs.d.b.a.c;
import com.huawei.hwcloudjs.f.d;
import com.huawei.hwcloudjs.service.http.annotation.RequestField;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;

/* loaded from: classes9.dex */
public abstract class a<T extends c> extends b<T> {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6210a = "FormBean";

    @Override // com.huawei.hwcloudjs.d.b.a.b
    public String f() {
        return "POST";
    }

    @Override // com.huawei.hwcloudjs.d.b.a.b
    public String b() {
        return RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED;
    }

    @Override // com.huawei.hwcloudjs.d.b.a.b
    public String a() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        Field[] declaredFields = getClass().getDeclaredFields();
        d.c(f6210a, "genBody fs:" + declaredFields, false);
        int length = declaredFields.length;
        for (int i = 0; i < length; i++) {
            Field field = declaredFields[i];
            d.c(f6210a, "genBody isAnnotationPresent:" + field.isAnnotationPresent(RequestField.class), false);
            if (field.isAnnotationPresent(RequestField.class)) {
                try {
                    a(field, stringBuffer);
                } catch (UnsupportedEncodingException unused) {
                    str = "setField UnsupportedEncodingException";
                    d.b(f6210a, str, true);
                } catch (IllegalAccessException unused2) {
                    str = "setField IllegalAccessException";
                    d.b(f6210a, str, true);
                }
            }
        }
        return stringBuffer.toString();
    }

    private void a(Field field, StringBuffer stringBuffer) throws IllegalAccessException, UnsupportedEncodingException {
        d.c(f6210a, "setField field:" + field, false);
        String value = ((RequestField) field.getAnnotation(RequestField.class)).value();
        if (TextUtils.isEmpty(value)) {
            value = field.getName();
        }
        d.c(f6210a, "setField key:" + value, false);
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        Object obj = field.get(this);
        if (obj != null) {
            String str = value + "=" + URLEncoder.encode(obj.toString(), "UTF-8");
            if (stringBuffer.length() != 0) {
                str = "&" + str;
            }
            stringBuffer.append(str);
        }
        d.c(f6210a, "setField sb:" + ((Object) stringBuffer), false);
        field.setAccessible(isAccessible);
    }
}
