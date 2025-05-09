package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.openalliance.ad.db.bean.AnalysisEventRecord;
import com.huawei.openalliance.ad.db.bean.AppDataCollectionRecord;
import com.huawei.openalliance.ad.db.bean.ClickEventRecord;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.ContentRecordV3;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import com.huawei.openalliance.ad.db.bean.DeletedContentRecord;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import com.huawei.openalliance.ad.db.bean.EventRecord;
import com.huawei.openalliance.ad.db.bean.ImpEventRecord;
import com.huawei.openalliance.ad.db.bean.PlacementRecord;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;
import com.huawei.openalliance.ad.db.bean.TemplateRecord;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.openalliance.ad.db.bean.TestContentRecord;
import com.huawei.openalliance.ad.db.bean.ThirdPartyEventRecord;
import com.huawei.openalliance.ad.db.bean.UserCloseRecord;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class dg {
    private static List<com.huawei.openalliance.ad.db.bean.a> b = new ArrayList(4);
    private static List<com.huawei.openalliance.ad.db.bean.a> c = new ArrayList(4);
    private static String d = "TestContentRecord";

    /* renamed from: a, reason: collision with root package name */
    private dd f6698a;

    public void b() {
        Iterator<com.huawei.openalliance.ad.db.bean.a> it = b.iterator();
        while (it.hasNext()) {
            String bq = it.next().bq();
            try {
                if (this.f6698a.e(bq)) {
                    this.f6698a.b(bq);
                }
            } catch (com.huawei.openalliance.ad.exception.a unused) {
                ho.c("DbUpdateHelper", "delete table fail");
            }
        }
        for (com.huawei.openalliance.ad.db.bean.a aVar : c) {
            try {
                this.f6698a.c(aVar.bp());
            } catch (com.huawei.openalliance.ad.exception.a unused2) {
                ho.c("DbUpdateHelper", "create table %s failed", aVar.bq());
            }
        }
    }

    public void a() {
        for (com.huawei.openalliance.ad.db.bean.a aVar : c) {
            String bq = aVar.bq();
            if (this.f6698a.e(bq)) {
                this.f6698a.f(bq);
                ho.b("DbUpdateHelper", "tableName exist moidfy table successfully.");
                try {
                    this.f6698a.c(aVar.bp());
                    b(bq);
                    ho.b("DbUpdateHelper", "insert data to table successfully.");
                    this.f6698a.a(bq);
                    ho.b("DbUpdateHelper", "drop table temp table successfully.");
                } catch (com.huawei.openalliance.ad.exception.a unused) {
                    throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "table exist, init table tableName: %s failed", bq.trim()));
                }
            } else {
                try {
                    this.f6698a.c(aVar.bp());
                } catch (com.huawei.openalliance.ad.exception.a unused2) {
                    throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "table is not exist, init table tableName: %s failed", bq.trim()));
                }
            }
        }
    }

    private void b(String str) {
        StringBuilder sb = new StringBuilder(" INSERT INTO ");
        sb.append(str);
        sb.append(" SELECT ");
        try {
            try {
                String a2 = a(this.f6698a.d(str), this.f6698a.d("_temp_" + str));
                if (a2 == null) {
                    throw new com.huawei.openalliance.ad.exception.a("insert data sql is null");
                }
                sb.append(a2);
                sb.append(" FROM _temp_");
                sb.append(str);
                try {
                    this.f6698a.c(sb.toString());
                } catch (com.huawei.openalliance.ad.exception.a unused) {
                    throw new com.huawei.openalliance.ad.exception.a("DbUpdateHelper insertData mDbHelper.executeSQL error");
                }
            } catch (com.huawei.openalliance.ad.exception.a unused2) {
                throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "get temp table %s column names failed", str.trim()));
            }
        } catch (com.huawei.openalliance.ad.exception.a unused3) {
            throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "get table %s column names failed", str.trim()));
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String replaceAll = str.replaceAll("_temp_", "");
        Iterator<com.huawei.openalliance.ad.db.bean.a> it = c.iterator();
        while (it.hasNext()) {
            if (replaceAll.equals(it.next().bq())) {
                return true;
            }
        }
        Iterator<com.huawei.openalliance.ad.db.bean.a> it2 = b.iterator();
        while (it2.hasNext()) {
            if (replaceAll.equals(it2.next().bq())) {
                return true;
            }
        }
        return false;
    }

    private String a(String[] strArr, String[] strArr2) {
        StringBuilder sb = new StringBuilder();
        List arrayList = new ArrayList(4);
        if (strArr2 != null) {
            arrayList = Arrays.asList(strArr2);
        }
        if (strArr == null || strArr.length <= 0 || strArr2 == null) {
            return null;
        }
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (!arrayList.contains(str)) {
                str = "\"\"";
            }
            sb.append(str);
            if (i != strArr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public dg(dd ddVar) {
        this.f6698a = ddVar;
    }

    static {
        b.add(new ContentRecord());
        b.add(new EventRecord());
        b.add(new TestContentRecord());
        b.add(new ThirdPartyEventRecord());
        b.add(new UserCloseRecord());
        b.add(new TemplateRecord());
        b.add(new PlacementRecord());
        b.add(new ImpEventRecord());
        b.add(new ClickEventRecord());
        b.add(new AnalysisEventRecord());
        b.add(new ContentResource());
        b.add(new EventMonitorRecord());
        b.add(new TemplateStyleRecord());
        b.add(new ContentTemplateRecord());
        b.add(new ContentRecordV3());
        b.add(new AppDataCollectionRecord());
        b.add(new DeletedContentRecord());
        b.add(new SdkCfgSha256Record());
        c.add(new ContentRecord());
        c.add(new EventRecord());
        c.add(new ThirdPartyEventRecord());
        c.add(new UserCloseRecord());
        c.add(new TemplateRecord());
        c.add(new PlacementRecord());
        c.add(new ImpEventRecord());
        c.add(new ClickEventRecord());
        c.add(new AnalysisEventRecord());
        c.add(new ContentResource());
        c.add(new EventMonitorRecord());
        c.add(new TemplateStyleRecord());
        c.add(new ContentTemplateRecord());
        c.add(new ContentRecordV3());
        c.add(new AppDataCollectionRecord());
        c.add(new DeletedContentRecord());
        c.add(new SdkCfgSha256Record());
    }
}
