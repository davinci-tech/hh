package com.huawei.openalliance.ad.db.bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.openalliance.ad.annotations.b;
import com.huawei.openalliance.ad.annotations.c;
import com.huawei.openalliance.ad.annotations.e;
import com.huawei.openalliance.ad.annotations.f;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.ck;
import com.huawei.openalliance.ad.utils.cp;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class a {
    private static final boolean DEBUG = false;
    public static final String ID = "_id";
    public static final String TAG = "RecordBean";
    private static Map<Class, String> dbTypeMap;

    @f
    @e
    private byte[] key;

    public ContentValues d(Context context) {
        String b;
        Field[] a2 = ck.a((Class) getClass());
        ContentValues contentValues = new ContentValues();
        int length = a2.length;
        for (int i = 0; i < length; i++) {
            try {
                Field a3 = ck.a(a2[i], true);
                a2[i] = a3;
                if (!a(a3)) {
                    Object obj = a2[i].get(this);
                    String d = d(a2[i]);
                    if (obj instanceof String) {
                        b = (String) obj;
                    } else if (obj instanceof Integer) {
                        contentValues.put(d, (Integer) obj);
                    } else if (obj instanceof Long) {
                        contentValues.put(d, (Long) obj);
                    } else if (obj instanceof Float) {
                        contentValues.put(d, (Float) obj);
                    } else if (obj instanceof EncryptionField) {
                        EncryptionField encryptionField = (EncryptionField) obj;
                        if (this.key == null) {
                            this.key = cp.b(context);
                        }
                        b = encryptionField.b(this.key);
                    } else {
                        b = be.b(obj);
                    }
                    contentValues.put(d, b);
                }
            } catch (IllegalAccessException unused) {
                ho.c(TAG, "toRecord IllegalAccessException");
            }
        }
        return contentValues;
    }

    public String bq() {
        return getClass().getSimpleName();
    }

    public String bp() {
        return aj(bq());
    }

    public List<String> bo() {
        Field[] a2 = ck.a((Class) getClass());
        ArrayList arrayList = new ArrayList();
        int length = a2.length;
        for (int i = 0; i < length; i++) {
            Field a3 = ck.a(a2[i], true);
            a2[i] = a3;
            if (!a(a3)) {
                arrayList.add(d(a2[i]));
            }
        }
        return arrayList;
    }

    public byte[] bn() {
        return this.key;
    }

    public String aj(String str) {
        Field[] a2 = ck.a((Class) getClass());
        StringBuilder sb = new StringBuilder("create table ");
        sb.append(str);
        sb.append(" ( _id INTEGER primary key autoincrement ");
        int length = a2.length;
        for (int i = 0; i < length; i++) {
            Field a3 = ck.a(a2[i], true);
            a2[i] = a3;
            if (!a(a3)) {
                String str2 = dbTypeMap.get(a2[i].getType());
                if (str2 == null) {
                    str2 = "TEXT";
                }
                String d = d(a2[i]);
                sb.append(" , ");
                sb.append(d);
                sb.append(' ');
                sb.append(str2);
                if (a_() != null && a_().equals(d)) {
                    sb.append(" unique");
                }
            }
        }
        sb.append(" ) ");
        return sb.toString();
    }

    public String a_() {
        return "";
    }

    public void a(byte[] bArr) {
        if (bArr == null) {
            this.key = null;
            return;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        this.key = bArr2;
    }

    public void a(Cursor cursor) {
        String str;
        Field field;
        Object valueOf;
        Field[] a2 = ck.a((Class) getClass());
        int length = a2.length;
        for (int i = 0; i < length; i++) {
            try {
                Field a3 = ck.a(a2[i], true);
                a2[i] = a3;
                String name = a3.getName();
                if (!Modifier.isStatic(a2[i].getModifiers()) && "_id".equals(name)) {
                    int columnIndex = cursor.getColumnIndex(name);
                    if (columnIndex != -1) {
                        a2[i].set(this, cursor.getString(columnIndex));
                    }
                } else if (b(a2[i])) {
                    Class<?> type = a2[i].getType();
                    int columnIndex2 = cursor.getColumnIndex(d(a2[i]));
                    if (columnIndex2 != -1) {
                        if (String.class == type) {
                            field = a2[i];
                            valueOf = cursor.getString(columnIndex2);
                        } else if (Integer.TYPE == type) {
                            field = a2[i];
                            valueOf = Integer.valueOf(cursor.getInt(columnIndex2));
                        } else if (Long.TYPE == type) {
                            field = a2[i];
                            valueOf = Long.valueOf(cursor.getLong(columnIndex2));
                        } else if (Float.TYPE == type) {
                            field = a2[i];
                            valueOf = Float.valueOf(cursor.getFloat(columnIndex2));
                        } else if (EncryptionField.class == type) {
                            a(cursor.getString(columnIndex2), a2[i]);
                        } else {
                            a(cursor.getString(columnIndex2), a2[i], type);
                        }
                        field.set(this, valueOf);
                    }
                }
            } catch (IllegalAccessException unused) {
                str = "toBean IllegalAccessException";
                ho.c(TAG, str);
            } catch (Exception e) {
                str = "toBean " + e.getClass().getSimpleName();
                ho.c(TAG, str);
            }
        }
    }

    private String d(Field field) {
        b bVar = (b) field.getAnnotation(b.class);
        return (bVar == null || TextUtils.isEmpty(bVar.a())) ? a(field.getName()) : bVar.a();
    }

    private boolean c(Field field) {
        if (field != null) {
            String name = field.getName();
            if (!Modifier.isStatic(field.getModifiers()) && name != null && !name.contains(SampleEvent.SEPARATOR) && field.isAnnotationPresent(c.class)) {
                return true;
            }
        }
        return false;
    }

    private boolean b(Field field) {
        if (field != null) {
            String name = field.getName();
            if (!Modifier.isStatic(field.getModifiers()) && name != null && !name.contains(SampleEvent.SEPARATOR) && !field.isAnnotationPresent(e.class)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Field field) {
        return !b(field) || c(field);
    }

    private void a(String str, Field field, Class cls) {
        try {
            field.set(this, be.a(str, cls, ck.a(field)));
        } catch (JSONException unused) {
        } catch (Exception unused2) {
            ho.c(TAG, "toBean - other field injection Exception");
        }
    }

    private void a(String str, Field field) {
        Class<?> cls;
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            Type a2 = ck.a(0, (ParameterizedType) genericType);
            Class<?> a3 = ck.a(a2);
            cls = a2 instanceof ParameterizedType ? ck.a(ck.a(0, (ParameterizedType) a2)) : null;
            r2 = a3;
        } else {
            cls = null;
        }
        if (r2 != null) {
            EncryptionField encryptionField = new EncryptionField(r2, cls);
            encryptionField.a(str);
            field.set(this, encryptionField);
        } else {
            ho.c(TAG, "Cannot find member class from " + field);
        }
    }

    private String a(String str) {
        return str.endsWith("_") ? str.substring(0, str.length() - 1) : str;
    }

    static {
        HashMap hashMap = new HashMap(4);
        dbTypeMap = hashMap;
        hashMap.put(String.class, "TEXT");
        dbTypeMap.put(Long.TYPE, "INTEGER");
        dbTypeMap.put(Integer.TYPE, "INTEGER");
        dbTypeMap.put(Float.TYPE, "REAL");
        dbTypeMap.put(EncryptionField.class, "TEXT");
    }
}
