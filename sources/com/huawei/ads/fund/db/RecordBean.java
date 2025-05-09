package com.huawei.ads.fund.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.ads.fund.anno.DbField;
import com.huawei.ads.fund.anno.DbFieldReadOnly;
import com.huawei.ads.fund.anno.NoDbField;
import com.huawei.ads.fund.e;
import com.huawei.ads.fund.util.JsonUtil;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.openalliance.ad.db.bean.a;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;

/* loaded from: classes2.dex */
public abstract class RecordBean implements Table, TableRecord, Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Class, String> f1690a;

    public ContentValues toRecord(Context context) {
        String jsonNoException;
        Field[] a2 = e.a(getClass());
        ContentValues contentValues = new ContentValues();
        int length = a2.length;
        for (int i = 0; i < length; i++) {
            try {
                Field a3 = e.a(a2[i], true);
                a2[i] = a3;
                if (!d(a3)) {
                    Object obj = a2[i].get(this);
                    String a4 = a(a2[i]);
                    if (obj instanceof String) {
                        jsonNoException = (String) obj;
                    } else if (obj instanceof Integer) {
                        contentValues.put(a4, (Integer) obj);
                    } else if (obj instanceof Long) {
                        contentValues.put(a4, (Long) obj);
                    } else if (obj instanceof Float) {
                        contentValues.put(a4, (Float) obj);
                    } else if (obj instanceof Double) {
                        contentValues.put(a4, (Double) obj);
                    } else if (obj instanceof Boolean) {
                        contentValues.put(a4, (Boolean) obj);
                    } else {
                        jsonNoException = JsonUtil.toJsonNoException(obj);
                    }
                    contentValues.put(a4, jsonNoException);
                }
            } catch (IllegalAccessException unused) {
                HiAdLog.w(a.TAG, "toRecord IllegalAccessException");
            }
        }
        return contentValues;
    }

    @Override // com.huawei.ads.fund.db.TableRecord
    public ContentValues toRecord() {
        return toRecord(null);
    }

    @Override // com.huawei.ads.fund.db.TableRecord
    public void toBean(Cursor cursor) {
        boolean z;
        String name;
        Field field;
        Object valueOf;
        Field field2;
        String string;
        Field[] a2 = e.a(getClass());
        int length = a2.length;
        for (int i = 0; i < length; i++) {
            try {
                z = true;
                Field a3 = e.a(a2[i], true);
                a2[i] = a3;
                name = a3.getName();
            } catch (Throwable th) {
                HiAdLog.w(a.TAG, "toBean " + th.getClass().getSimpleName());
            }
            if (Modifier.isStatic(a2[i].getModifiers()) || !"_id".equals(name)) {
                if (b(a2[i])) {
                    Class<?> type = a2[i].getType();
                    int columnIndex = cursor.getColumnIndex(a(a2[i]));
                    if (columnIndex != -1) {
                        if (String.class == type) {
                            field2 = a2[i];
                            string = cursor.getString(columnIndex);
                        } else {
                            if (Integer.TYPE == type) {
                                field = a2[i];
                                valueOf = Integer.valueOf(cursor.getInt(columnIndex));
                            } else if (Long.TYPE == type) {
                                field = a2[i];
                                valueOf = Long.valueOf(cursor.getLong(columnIndex));
                            } else if (Float.TYPE == type) {
                                field = a2[i];
                                valueOf = Float.valueOf(cursor.getFloat(columnIndex));
                            } else if (Double.TYPE == type) {
                                field = a2[i];
                                valueOf = Double.valueOf(cursor.getDouble(columnIndex));
                            } else if (Boolean.class == type) {
                                field = a2[i];
                                if (cursor.getInt(columnIndex) == 0) {
                                    z = false;
                                }
                                valueOf = Boolean.valueOf(z);
                            } else {
                                a(cursor.getString(columnIndex), a2[i], type);
                            }
                            field.set(this, valueOf);
                        }
                    }
                }
            } else {
                int columnIndex2 = cursor.getColumnIndex(name);
                if (columnIndex2 != -1) {
                    field2 = a2[i];
                    string = cursor.getString(columnIndex2);
                }
            }
            field2.set(this, string);
        }
    }

    public String getUnique() {
        return "";
    }

    @Override // com.huawei.ads.fund.db.Table
    public String getTableScheme() {
        return b(getTableName());
    }

    @Override // com.huawei.ads.fund.db.Table
    public String getTableName() {
        return getClass().getSimpleName();
    }

    @Override // com.huawei.ads.fund.db.Table
    public String[] getExpireCleanWhereArgs() {
        return new String[]{String.valueOf(System.currentTimeMillis() - getMaxStoreTime())};
    }

    public RecordBean copy() {
        try {
            return (RecordBean) super.clone();
        } catch (CloneNotSupportedException unused) {
            HiAdLog.e(a.TAG, "copy failed");
            return null;
        }
    }

    private boolean d(Field field) {
        return !b(field) || c(field);
    }

    private boolean c(Field field) {
        if (field != null) {
            String name = field.getName();
            if (!Modifier.isStatic(field.getModifiers()) && name != null && !name.contains(SampleEvent.SEPARATOR) && field.isAnnotationPresent(DbFieldReadOnly.class)) {
                return true;
            }
        }
        return false;
    }

    private boolean b(Field field) {
        if (field != null) {
            String name = field.getName();
            if (!Modifier.isStatic(field.getModifiers()) && name != null && !name.contains(SampleEvent.SEPARATOR) && !field.isAnnotationPresent(NoDbField.class)) {
                return true;
            }
        }
        return false;
    }

    private String b(String str) {
        Field[] a2 = e.a(getClass());
        StringBuilder sb = new StringBuilder("create table ");
        sb.append(str);
        sb.append(" ( _id INTEGER primary key autoincrement ");
        int length = a2.length;
        for (int i = 0; i < length; i++) {
            Field a3 = e.a(a2[i], true);
            a2[i] = a3;
            if (!d(a3)) {
                String str2 = f1690a.get(a2[i].getType());
                if (str2 == null) {
                    str2 = "TEXT";
                }
                String a4 = a(a2[i]);
                sb.append(" , ");
                sb.append(a4);
                sb.append(' ');
                sb.append(str2);
                if (getUnique() != null && getUnique().equals(a4)) {
                    sb.append(" unique");
                }
            }
        }
        sb.append(" ) ");
        return sb.toString();
    }

    private void a(String str, Field field, Class cls) {
        try {
            field.set(this, JsonUtil.toObject(str, cls, e.a(field)));
        } catch (JSONException unused) {
        } catch (Exception unused2) {
            HiAdLog.w(a.TAG, "toBean - other field injection Exception");
        }
    }

    private String a(Field field) {
        DbField dbField = (DbField) field.getAnnotation(DbField.class);
        return (dbField == null || TextUtils.isEmpty(dbField.value())) ? a(field.getName()) : dbField.value();
    }

    private String a(String str) {
        return str.endsWith("_") ? str.substring(0, str.length() - 1) : str;
    }

    static {
        HashMap hashMap = new HashMap(4);
        f1690a = hashMap;
        hashMap.put(String.class, "TEXT");
        hashMap.put(Long.TYPE, "INTEGER");
        hashMap.put(Integer.TYPE, "INTEGER");
        hashMap.put(Float.TYPE, "REAL");
        hashMap.put(Double.TYPE, "REAL");
        hashMap.put(Boolean.TYPE, "BOOLEAN");
    }
}
