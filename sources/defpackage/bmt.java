package defpackage;

import android.util.Log;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* loaded from: classes3.dex */
public class bmt {
    public bmq b(byte[] bArr) throws bmk {
        return c(bArr, 0, 0);
    }

    public bmq c(byte[] bArr, int i) throws bmk {
        return c(bArr, i, 0);
    }

    private bmq c(byte[] bArr, int i, int i2) throws bmk {
        bmq bmqVar = new bmq();
        if (bArr == null || bArr.length == 0) {
            Log.w("TlvByteUtils", "hexString is null");
            return bmqVar;
        }
        if (i >= bArr.length) {
            LogUtil.a("TlvByteUtils", "input position or data is wrong, please check input.");
            return bmqVar;
        }
        if (i2 > 50) {
            throw new bmk();
        }
        while (i != bArr.length) {
            try {
                byte b2 = bArr[i];
                if (b2 == 0) {
                    throw new bmk();
                }
                int i3 = i + 1;
                byte[] d = d(bArr, i3);
                int length = i3 + d.length;
                int d2 = d(d);
                byte[] array = ByteBuffer.allocate(d2).put(bArr, length, d2).array();
                i = length + d2;
                if ((b2 & 128) == 128) {
                    bmqVar.a().add(c(array, 0, i2 + 1));
                } else {
                    bmqVar.d().add(new bmu(b2, d2, array));
                }
            } catch (IndexOutOfBoundsException e) {
                Log.e("TlvByteUtils", "parseTlv occur exception " + ExceptionUtils.d(e));
                throw new bmk();
            }
        }
        return bmqVar;
    }

    public bmq d(byte[] bArr, TreeSet<Byte> treeSet) throws bmk {
        bmq bmqVar = new bmq();
        if (bArr == null || bArr.length == 0 || treeSet == null || treeSet.size() == 0) {
            Log.w("TlvByteUtils", "hexString is null");
            return bmqVar;
        }
        int size = treeSet.size();
        int i = 0;
        int i2 = 0;
        while (i != bArr.length) {
            try {
                byte b2 = bArr[i];
                boolean contains = treeSet.contains(Byte.valueOf(b2));
                int i3 = i + 1;
                byte[] d = d(bArr, i3);
                int length = i3 + d.length;
                int d2 = d(d);
                if (contains) {
                    byte[] array = ByteBuffer.allocate(d2).put(bArr, length, d2).array();
                    i = length + d2;
                    if ((b2 & 128) == 128) {
                        Log.w("TlvByteUtils", "(tag & BIT_HIGHEST) == BIT_HIGHEST");
                    } else {
                        bmqVar.d().add(new bmu(b2, d2, array));
                        i2++;
                        if (i2 == size) {
                            return bmqVar;
                        }
                    }
                } else {
                    i = length + d2;
                }
            } catch (IndexOutOfBoundsException unused) {
                throw new bmk();
            }
        }
        return bmqVar;
    }

    public bmq e(byte[] bArr, TreeSet<Byte> treeSet) throws bmk {
        bmq bmqVar = new bmq();
        if (bArr == null || bArr.length == 0 || treeSet == null || treeSet.size() == 0) {
            Log.w("TlvByteUtils", "hexString is null");
            return bmqVar;
        }
        int size = treeSet.size();
        int i = 0;
        int i2 = 0;
        do {
            try {
                if (i == bArr.length) {
                    return bmqVar;
                }
                byte b2 = bArr[i];
                if (!treeSet.contains(Byte.valueOf(b2))) {
                    throw new bmk();
                }
                int i3 = i + 1;
                byte[] d = d(bArr, i3);
                int length = i3 + d.length;
                int d2 = d(d);
                byte[] array = ByteBuffer.allocate(d2).put(bArr, length, d2).array();
                i = length + d2;
                if ((b2 & 128) == 128) {
                    throw new bmk();
                }
                bmqVar.d().add(new bmu(b2, d2, array));
                i2++;
            } catch (IndexOutOfBoundsException unused) {
                throw new bmk();
            }
        } while (i2 != size);
        if (i >= bArr.length) {
            return bmqVar;
        }
        throw new bmk();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] d(byte[] bArr, int i) throws bmk {
        int i2 = 0;
        int i3 = i;
        while ((bArr[i3] & 128) == 128) {
            i3++;
            i2++;
            if (i2 == 3) {
                throw new bmk();
            }
        }
        int i4 = i2 + 1;
        ByteBuffer allocate = ByteBuffer.allocate(i4);
        allocate.put(bArr, i, i4);
        return allocate.array();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int d(byte[] bArr) throws bmk {
        byte b2;
        int i;
        int length = bArr.length;
        if (length == 1) {
            byte b3 = bArr[0];
            if ((b3 & 128) != 128) {
                return b3;
            }
            throw new bmk();
        }
        if (length == 2) {
            byte b4 = bArr[0];
            if ((b4 & 128) != 128) {
                throw new bmk();
            }
            b2 = bArr[1];
            if ((b2 & 128) == 128) {
                throw new bmk();
            }
            i = (b4 & Byte.MAX_VALUE) << 7;
        } else if (length == 3) {
            byte b5 = bArr[0];
            if ((b5 & 128) != 128) {
                throw new bmk();
            }
            byte b6 = bArr[1];
            if ((b6 & 128) != 128) {
                throw new bmk();
            }
            b2 = bArr[2];
            if ((b2 & 128) == 128) {
                throw new bmk();
            }
            i = ((b5 & Byte.MAX_VALUE) << 14) | ((b6 & Byte.MAX_VALUE) << 7);
        } else {
            throw new bmk();
        }
        return (b2 & Byte.MAX_VALUE) | i;
    }

    public static class b {
        private Map<Byte, byte[]> d = new HashMap();

        /* JADX INFO: Access modifiers changed from: private */
        public void e(byte b, byte[] bArr) {
            this.d.put(Byte.valueOf(b), bArr);
        }

        public int a(byte b, int i) {
            return blq.d(this.d.get(Byte.valueOf(b)), i);
        }

        public long b(byte b, long j) {
            return blq.e(this.d.get(Byte.valueOf(b)), j);
        }

        public String b(byte b, String str) {
            byte[] bArr = this.d.get(Byte.valueOf(b));
            if (bArr == null || bArr.length == 0) {
                LogUtil.a("TlvByteUtils", "tlv response target type: ", Byte.valueOf(b), ", but no value");
                return str;
            }
            return new String(bArr, StandardCharsets.UTF_8);
        }

        public byte[] c(byte b, byte[] bArr) {
            return this.d.containsKey(Byte.valueOf(b)) ? this.d.get(Byte.valueOf(b)) : bArr;
        }

        public List<b> a(byte b) throws bmk {
            if (!this.d.containsKey(Byte.valueOf(b))) {
                return null;
            }
            byte[] bArr = this.d.get(Byte.valueOf(b));
            if (bArr == null || bArr.length == 0) {
                LogUtil.a("TlvByteUtils", "tlv response target type: ", Byte.valueOf(b), ", but no value");
                return null;
            }
            LinkedList linkedList = new LinkedList();
            int i = 0;
            while (i != bArr.length) {
                try {
                    int i2 = i + 1;
                    byte[] d = bmt.d(bArr, i2);
                    int length = i2 + d.length;
                    int d2 = bmt.d(d);
                    byte[] array = ByteBuffer.allocate(d2).put(bArr, length, d2).array();
                    i = length + d2;
                    linkedList.add(new bmt().b(array, 0));
                } catch (IndexOutOfBoundsException unused) {
                    throw new bmk();
                }
            }
            return linkedList;
        }
    }

    public b b(byte[] bArr, int i) throws bmk {
        b bVar = new b();
        if (bArr == null || bArr.length == 0) {
            Log.w("TlvByteUtils", "hexString is null");
            return bVar;
        }
        if (i >= bArr.length) {
            LogUtil.a("TlvByteUtils", "input position or data is wrong, please check input.");
            return bVar;
        }
        while (i != bArr.length) {
            try {
                byte b2 = bArr[i];
                if (b2 == 0) {
                    throw new bmk();
                }
                int i2 = i + 1;
                byte[] d = d(bArr, i2);
                int length = i2 + d.length;
                int d2 = d(d);
                byte[] array = ByteBuffer.allocate(d2).put(bArr, length, d2).array();
                i = length + d2;
                if ((b2 & 128) == 128) {
                    bVar.e((byte) (b2 & Byte.MAX_VALUE), array);
                } else {
                    bVar.e(b2, array);
                }
            } catch (IndexOutOfBoundsException unused) {
                throw new bmk();
            }
        }
        return bVar;
    }
}
