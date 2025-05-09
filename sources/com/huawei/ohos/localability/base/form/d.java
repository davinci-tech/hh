package com.huawei.ohos.localability.base.form;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> b = new b();

    /* renamed from: a, reason: collision with root package name */
    private Map<String, Object> f6549a;

    public d(Parcel parcel) {
        int readInt;
        Object obj;
        HashMap hashMap = new HashMap();
        this.f6549a = hashMap;
        hashMap.clear();
        if (parcel != null && (readInt = parcel.readInt()) <= 512000) {
            for (int i = 0; i < readInt; i++) {
                String readString = parcel.readString();
                int readInt2 = parcel.readInt();
                switch (readInt2) {
                    case -1:
                        obj = null;
                        break;
                    case 0:
                    default:
                        throw new IllegalArgumentException("read IntentParams: unsupported value type " + readInt2);
                    case 1:
                        obj = Boolean.valueOf(parcel.readInt() != 0);
                        break;
                    case 2:
                        obj = Byte.valueOf(parcel.readByte());
                        break;
                    case 3:
                        obj = Character.valueOf((char) parcel.readInt());
                        break;
                    case 4:
                        obj = Short.valueOf((short) parcel.readInt());
                        break;
                    case 5:
                        obj = Integer.valueOf(parcel.readInt());
                        break;
                    case 6:
                        obj = Long.valueOf(parcel.readLong());
                        break;
                    case 7:
                        obj = Float.valueOf(parcel.readFloat());
                        break;
                    case 8:
                        obj = Double.valueOf(parcel.readDouble());
                        break;
                    case 9:
                        obj = parcel.readString();
                        break;
                }
                this.f6549a.put(readString, obj);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <E> void a(Parcel parcel, E e) {
        String obj;
        int i;
        if (e == 0) {
            i = -1;
        } else if (e instanceof Boolean) {
            parcel.writeInt(1);
            i = ((Boolean) e).booleanValue();
        } else {
            if (e instanceof Byte) {
                parcel.writeInt(2);
                parcel.writeByte(((Byte) e).byteValue());
                return;
            }
            if (e instanceof Character) {
                parcel.writeInt(3);
                i = ((Character) e).charValue();
            } else if (e instanceof Short) {
                parcel.writeInt(4);
                i = ((Short) e).shortValue();
            } else {
                if (!(e instanceof Integer)) {
                    if (e instanceof Long) {
                        parcel.writeInt(6);
                        parcel.writeLong(((Long) e).longValue());
                        return;
                    }
                    if (e instanceof Float) {
                        parcel.writeInt(7);
                        parcel.writeFloat(((Float) e).floatValue());
                        return;
                    }
                    if (e instanceof Double) {
                        parcel.writeInt(8);
                        parcel.writeDouble(((Double) e).doubleValue());
                        return;
                    }
                    if (e instanceof String) {
                        parcel.writeInt(9);
                        obj = (String) e;
                    } else {
                        if (!(e instanceof CharSequence)) {
                            boolean z = e instanceof List;
                            if (z) {
                                parcel.writeInt(50);
                                Parcel obtain = Parcel.obtain();
                                if (!z) {
                                    throw new IllegalArgumentException("write IntentParams: unsupported type " + e.getClass());
                                }
                                List list = (List) e;
                                obtain.writeInt(list.size());
                                Iterator<E> it = list.iterator();
                                while (it.hasNext()) {
                                    a(obtain, (Parcel) it.next());
                                }
                                byte[] marshall = obtain.marshall();
                                parcel.writeInt(marshall.length);
                                parcel.writeByteArray(marshall);
                                return;
                            }
                            if (e instanceof boolean[]) {
                                parcel.writeInt(11);
                                parcel.writeBooleanArray((boolean[]) e);
                                return;
                            }
                            if (e instanceof byte[]) {
                                parcel.writeInt(12);
                                parcel.writeByteArray((byte[]) e);
                                return;
                            }
                            if (e instanceof char[]) {
                                parcel.writeInt(13);
                                parcel.writeCharArray((char[]) e);
                                return;
                            }
                            int i2 = 0;
                            if (e instanceof short[]) {
                                parcel.writeInt(14);
                                short[] sArr = (short[]) e;
                                int[] iArr = new int[sArr.length];
                                while (i2 < sArr.length) {
                                    iArr[i2] = sArr[i2];
                                    i2++;
                                }
                                parcel.writeIntArray(iArr);
                                return;
                            }
                            if (e instanceof int[]) {
                                parcel.writeInt(15);
                                parcel.writeIntArray((int[]) e);
                                return;
                            }
                            if (e instanceof long[]) {
                                parcel.writeInt(16);
                                parcel.writeLongArray((long[]) e);
                                return;
                            }
                            if (e instanceof float[]) {
                                parcel.writeInt(17);
                                parcel.writeFloatArray((float[]) e);
                                return;
                            }
                            if (e instanceof double[]) {
                                parcel.writeInt(18);
                                parcel.writeDoubleArray((double[]) e);
                                return;
                            }
                            if (e instanceof String[]) {
                                parcel.writeInt(19);
                                parcel.writeStringArray((String[]) e);
                                return;
                            }
                            if (!(e instanceof CharSequence[])) {
                                throw new IllegalArgumentException("the type or contained type is not support to transport when acquireForm : " + e.getClass());
                            }
                            parcel.writeInt(20);
                            CharSequence[] charSequenceArr = (CharSequence[]) e;
                            String[] strArr = new String[charSequenceArr.length];
                            while (i2 < charSequenceArr.length) {
                                strArr[i2] = charSequenceArr[i2].toString();
                                i2++;
                            }
                            parcel.writeStringArray(strArr);
                            return;
                        }
                        parcel.writeInt(10);
                        obj = e.toString();
                    }
                    parcel.writeString(obj);
                    return;
                }
                parcel.writeInt(5);
                i = ((Integer) e).intValue();
            }
        }
        parcel.writeInt(i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(this.f6549a.size());
        for (Map.Entry<String, Object> entry : this.f6549a.entrySet()) {
            obtain.writeString(entry.getKey());
            a(obtain, (Parcel) entry.getValue());
        }
        byte[] marshall = obtain.marshall();
        parcel.writeInt(marshall.length);
        parcel.writeByteArray(marshall);
    }

    public <T> void a(String str, T t) {
        this.f6549a.put(str, t);
    }

    static final class b implements Parcelable.Creator<d> {
        @Override // android.os.Parcelable.Creator
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public d[] newArray(int i) {
            if (i >= 0) {
                return new d[i];
            }
            return null;
        }

        b() {
        }
    }

    public d() {
        this(null);
    }
}
