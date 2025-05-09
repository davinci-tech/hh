package com.huawei.hms.support.hwid.common.e;

import android.content.Context;
import java.lang.reflect.Field;

/* loaded from: classes9.dex */
public class h {
    public static int a(Context context, String str, String str2) {
        try {
            int identifier = context.getResources().getIdentifier(str2, str, context.getPackageName());
            if (identifier == 0) {
                Field field = Class.forName(context.getPackageName() + ".R$" + str).getField(str2);
                identifier = Integer.parseInt(field.get(field.getName()).toString());
                if (identifier == 0) {
                    g.a("ResourceLoader", "resourceId == 0");
                }
            }
            return identifier;
        } catch (ClassNotFoundException unused) {
            g.b("ResourceLoader", "!!!! ResourceLoader: reflect resource ClassNotFoundException");
            return 0;
        } catch (IllegalAccessException unused2) {
            g.b("ResourceLoader", "!!!! ResourceLoader: reflect resource IllegalAccessException");
            return 0;
        } catch (IllegalArgumentException unused3) {
            g.b("ResourceLoader", "!!!! ResourceLoader: reflect resource IllegalArgumentException");
            return 0;
        } catch (Exception unused4) {
            g.b("ResourceLoader", "!!!! ResourceLoader: reflect resource Exception");
            return 0;
        } catch (LinkageError unused5) {
            g.b("ResourceLoader", "!!!! ResourceLoader: reflect resource LinkageError");
            return 0;
        } catch (NumberFormatException unused6) {
            g.b("ResourceLoader", "!!!! ResourceLoader: reflect resource NumberFormatException");
            return 0;
        } catch (RuntimeException unused7) {
            g.b("ResourceLoader", "!!!! ResourceLoader: reflect resource RuntimeException");
            return 0;
        }
    }

    public static int a(Context context, String str) {
        return a(context, "xml", str);
    }
}
