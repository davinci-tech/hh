package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class qre {
    public static int c(int i) {
        if (i < 2) {
            return -1;
        }
        if (i <= 14) {
            return 1;
        }
        return i <= 59 ? 2 : 3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x00cc, code lost:
    
        r0.d("stand.db");
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00cf, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00be, code lost:
    
        if (r9 == null) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b(int r8, int r9, int r10, int r11) {
        /*
            java.lang.String r0 = "getHealthSuggestionInfo dataType = "
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)
            java.lang.String r2 = "; sex = "
            java.lang.Integer r3 = java.lang.Integer.valueOf(r9)
            java.lang.String r4 = "; ageType = "
            java.lang.Integer r5 = java.lang.Integer.valueOf(r10)
            java.lang.String r6 = "; indexLevel = "
            java.lang.Integer r7 = java.lang.Integer.valueOf(r11)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2, r3, r4, r5, r6, r7}
            java.lang.String r1 = "HealthSuggestionsUtils"
            com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
            cfd r0 = defpackage.cfd.e()
            java.lang.String r2 = ""
            if (r0 != 0) goto L33
            java.lang.String r8 = "getHealthSuggestionInfo assetsDatabaseManager is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r8)
            return r2
        L33:
            java.lang.String r3 = "stand.db"
            android.database.sqlite.SQLiteDatabase r4 = r0.Et_(r3)
            if (r4 != 0) goto L45
            java.lang.String r8 = "health suggestion db is null; and suggestions = "
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
            return r2
        L45:
            boolean r5 = r4.isOpen()
            if (r5 != 0) goto L4f
            android.database.sqlite.SQLiteDatabase r4 = r0.Eu_(r3)
        L4f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r8)
            r5.append(r2)
            java.lang.String r8 = r5.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r9)
            r5.append(r2)
            java.lang.String r9 = r5.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r10)
            r5.append(r2)
            java.lang.String r10 = r5.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r11)
            r5.append(r2)
            java.lang.String r11 = r5.toString()
            java.lang.String[] r8 = new java.lang.String[]{r8, r9, r10, r11}
            r9 = 0
            if (r4 == 0) goto Lc7
            java.lang.String r10 = "select * from data_suggestion_info where data_type = ? and sex = ? and age_level = ? and index_level = ?"
            android.database.Cursor r9 = r4.rawQuery(r10, r8)     // Catch: java.lang.Throwable -> La9 android.database.SQLException -> Lab
        L98:
            boolean r8 = r9.moveToNext()     // Catch: java.lang.Throwable -> La9 android.database.SQLException -> Lab
            if (r8 == 0) goto Lc7
            java.lang.String r8 = "suggestions"
            int r8 = r9.getColumnIndex(r8)     // Catch: java.lang.Throwable -> La9 android.database.SQLException -> Lab
            java.lang.String r2 = r9.getString(r8)     // Catch: java.lang.Throwable -> La9 android.database.SQLException -> Lab
            goto L98
        La9:
            r8 = move-exception
            goto Lc1
        Lab:
            r8 = move-exception
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch: java.lang.Throwable -> La9
            java.lang.String r11 = "SQLException is"
            r4 = 0
            r10[r4] = r11     // Catch: java.lang.Throwable -> La9
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> La9
            r11 = 1
            r10[r11] = r8     // Catch: java.lang.Throwable -> La9
            com.huawei.hwlogsmodel.LogUtil.a(r1, r10)     // Catch: java.lang.Throwable -> La9
            if (r9 == 0) goto Lcc
            goto Lc9
        Lc1:
            if (r9 == 0) goto Lc6
            r9.close()
        Lc6:
            throw r8
        Lc7:
            if (r9 == 0) goto Lcc
        Lc9:
            r9.close()
        Lcc:
            r0.d(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qre.b(int, int, int, int):java.lang.String");
    }

    public static String d(String str, long j) {
        if (!TextUtils.isEmpty(str) && j >= 0) {
            String[] split = str.split("\\$");
            int length = split.length;
            LogUtil.a("HealthSuggestionsUtils", "getSuggest suggestion length = ", Integer.valueOf(length));
            int i = (int) (j % length);
            return koq.b(split, i) ? "" : split[i];
        }
        LogUtil.a("HealthSuggestionsUtils", "getSuggest health suggestions is null");
        return "";
    }
}
