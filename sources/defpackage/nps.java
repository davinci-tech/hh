package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hianalytics.core.transport.Utils;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/* loaded from: classes6.dex */
public class nps {
    public static Field d(Class<?> cls, String str, boolean z) {
        if (cls == null || TextUtils.isEmpty(str)) {
            LogUtil.b(Utils.TAG, "cls is null or fieldName is Empty");
            return null;
        }
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (!a(declaredField)) {
                if (!z) {
                    return null;
                }
                declaredField.setAccessible(true);
            }
            return declaredField;
        } catch (NoSuchFieldException e) {
            LogUtil.b(Utils.TAG, "NoSuchFieldException: ", ExceptionUtils.d(e));
            return null;
        }
    }

    private static boolean a(Member member) {
        return (member == null || !Modifier.isPublic(member.getModifiers()) || member.isSynthetic()) ? false : true;
    }
}
