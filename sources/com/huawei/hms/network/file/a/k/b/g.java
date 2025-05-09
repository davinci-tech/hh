package com.huawei.hms.network.file.a.k.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.file.api.RequestConfig;
import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.file.download.api.GetRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public class g extends c<com.huawei.hms.network.file.core.e<GetRequest>> {
    List<com.huawei.hms.network.file.core.e<GetRequest>> c(String str) {
        return b("manager=?", new String[]{str});
    }

    String b(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            FLogger.i("DBDao", "encodeUrlString UnsupportedEncodingException", new Object[0]);
            return str;
        }
    }

    @Override // com.huawei.hms.network.file.a.k.b.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ContentValues a(com.huawei.hms.network.file.core.e<GetRequest> eVar, String str) {
        return a2(eVar, str);
    }

    public void a(GetRequest getRequest, e.a aVar, String str) {
        a(a2(new com.huawei.hms.network.file.core.e<>(getRequest, aVar.ordinal()), str), "requestId=? and manager=?", new String[]{String.valueOf(getRequest.getId()), str});
    }

    void a(ContentValues contentValues, String str, String str2, List<String> list) {
        byte[] bArr;
        if (Utils.isBlank(str2)) {
            bArr = null;
        } else {
            if (!Utils.isEmpty(list)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str2);
                arrayList.addAll(list);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i != 0) {
                        sb.append(";");
                    }
                    sb.append(b((String) arrayList.get(i)));
                }
                str2 = sb.toString();
            }
            bArr = StringUtils.str2Byte(str2);
        }
        if (bArr != null) {
            c.a(contentValues, str, bArr);
        }
    }

    void a(ContentValues contentValues, String str, Object obj) {
        if (obj != null) {
            c.a(contentValues, str, f.a(obj));
        }
    }

    public void a(long j, e.a aVar, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Integer.valueOf(aVar.ordinal()));
        a(contentValues, "requestId=? and manager=?", new String[]{String.valueOf(j), str});
    }

    public Set<Long> a(String str, int i) {
        String str2;
        if (i >= 0) {
            str2 = "0," + i;
        } else {
            str2 = null;
        }
        return a("requestId", "manager=?", new String[]{str}, str2);
    }

    String a(String str) {
        if (Utils.isBlank(str)) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            FLogger.i("DBDao", "decodeUrlString UnsupportedEncodingException", new Object[0]);
            return str;
        }
    }

    public String a(long j, String str) {
        com.huawei.hms.network.file.core.e<GetRequest> a2 = a(j);
        if (a2 == null) {
            return null;
        }
        a("requestId=? and manager=?", new String[]{String.valueOf(j), str});
        return a2.a().getFilePath();
    }

    @Override // com.huawei.hms.network.file.a.k.b.c
    public String a() {
        return "download_request";
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hms.network.file.a.k.b.c
    public com.huawei.hms.network.file.core.e<GetRequest> a(Cursor cursor) {
        GetRequest.Builder builder = new GetRequest.Builder();
        return new com.huawei.hms.network.file.core.e<>(builder.build(), a(cursor, builder));
    }

    com.huawei.hms.network.file.core.e<GetRequest> a(long j) {
        return c("requestId=?", new String[]{String.valueOf(j)});
    }

    /* renamed from: a, reason: avoid collision after fix types in other method */
    ContentValues a2(com.huawei.hms.network.file.core.e<GetRequest> eVar, String str) {
        ContentValues contentValues = new ContentValues();
        GetRequest a2 = eVar.a();
        c.a(contentValues, "filePath", StringUtils.str2Byte(Utils.nullStrToEmpty(a2.getFilePath())));
        contentValues.put("fileSha256", Utils.nullStrToEmpty(a2.getSha256()));
        contentValues.put("fileSize", Long.valueOf(a2.getFileSize()));
        contentValues.put("manager", str);
        contentValues.put("name", Utils.nullStrToEmpty(a2.getName()));
        contentValues.put("requestId", Long.valueOf(a2.getId()));
        contentValues.put("startPosition", Long.valueOf(a2.getOffset()));
        contentValues.put("status", Integer.valueOf(eVar.b()));
        a(contentValues, "headers", a2.getHeaders());
        a(contentValues, com.alipay.sdk.m.p.e.n, a2.getParams());
        a(contentValues, "logInfos", a2.getReportInfos());
        a(contentValues, "configs", a2.getConfig());
        a(contentValues, com.huawei.hms.kit.awareness.b.a.a.b, a2.getUrl(), a2.getBackupUrls());
        return contentValues;
    }

    public int a(Cursor cursor, GetRequest.Builder builder) {
        Object a2;
        Object a3;
        Object a4;
        Object a5;
        builder.name(cursor.getString(cursor.getColumnIndex("name")));
        builder.fileSize(cursor.getLong(cursor.getColumnIndex("fileSize")));
        byte[] a6 = a(cursor, "filePath");
        builder.filePath(a6 != null ? StringUtils.byte2Str(a6) : "");
        builder.sha256(cursor.getString(cursor.getColumnIndex("fileSha256")));
        builder.offset(cursor.getLong(cursor.getColumnIndex("startPosition")));
        builder.id(cursor.getLong(cursor.getColumnIndex("requestId")));
        byte[] a7 = a(cursor, "headers");
        if (a7 != null && (a5 = f.a(a7)) != null) {
            builder.headers((Map) a5);
        }
        byte[] a8 = a(cursor, com.alipay.sdk.m.p.e.n);
        if (a8 != null && (a4 = f.a(a8)) != null) {
            builder.params((Map) a4);
        }
        byte[] a9 = a(cursor, "logInfos");
        if (a9 != null && (a3 = f.a(a9)) != null) {
            builder.reportInfos((Map) a3);
        }
        byte[] a10 = a(cursor, "configs");
        if (a10 != null && (a2 = f.a(a10)) != null) {
            builder.config((RequestConfig) a2);
        }
        byte[] a11 = a(cursor, com.huawei.hms.kit.awareness.b.a.a.b);
        if (a11 != null) {
            String byte2Str = StringUtils.byte2Str(a11);
            if (!Utils.isBlank(byte2Str)) {
                if (byte2Str.contains(";")) {
                    String[] split = byte2Str.split(";");
                    if (split.length > 0) {
                        builder.url(a(split[0]));
                        ArrayList arrayList = new ArrayList();
                        if (split.length > 1) {
                            for (int i = 1; i < split.length; i++) {
                                arrayList.add(a(split[i]));
                            }
                        }
                        builder.backupUrls(arrayList);
                    }
                } else {
                    builder.url(byte2Str);
                }
            }
        }
        return cursor.getInt(cursor.getColumnIndex("status"));
    }

    public g(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }
}
