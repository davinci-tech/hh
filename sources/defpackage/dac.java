package defpackage;

import com.huawei.health.device.open.data.HealthDataParser;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hwlogsmodel.LogUtil;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class dac implements HealthDataParser {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [dac$1] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.huawei.health.device.open.data.model.HealthData] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    @Override // com.huawei.health.device.open.data.HealthDataParser
    public HealthData parseData(byte[] bArr) {
        LogUtil.a("GlucoseDataParser", "parseData");
        ?? r0 = 0;
        r0 = 0;
        if (bArr == null) {
            LogUtil.h("GlucoseDataParser", "parseData data is null");
            return null;
        }
        List<Object> c = new d().c(bArr);
        if (c != null && c.size() > 0) {
            deb debVar = new deb();
            if (c.get(0) instanceof Float) {
                debVar.setBloodSugar(((Float) c.get(0)).floatValue());
            }
            if (c.get(1) instanceof Long) {
                debVar.setStartTime(((Long) c.get(1)).longValue());
                debVar.setEndTime(((Long) c.get(1)).longValue());
            }
            r0 = debVar;
            if (c.get(2) instanceof Integer) {
                debVar.setSequenceNumber(((Integer) c.get(2)).intValue());
                r0 = debVar;
            }
        }
        return r0;
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private int f11566a;
        private byte[] b;
        private byte[] c;

        private d() {
            this.c = new byte[2];
            this.b = new byte[1];
            this.f11566a = 0;
        }

        public String e(byte[] bArr) {
            StringBuilder sb = new StringBuilder("");
            if (bArr != null && bArr.length > 0) {
                for (byte b : bArr) {
                    String hexString = Integer.toHexString(b & 255);
                    if (hexString.length() < 2) {
                        sb.append(0);
                    }
                    sb.append(hexString);
                }
                return sb.toString().toUpperCase(Locale.ENGLISH);
            }
            return sb.toString();
        }

        private int a(byte[] bArr) {
            ByteBuffer wrap = ByteBuffer.wrap(Arrays.copyOf(bArr, 4));
            wrap.order(ByteOrder.LITTLE_ENDIAN);
            return wrap.asIntBuffer().get();
        }

        private String a(int i) {
            if (i >= 10) {
                return String.valueOf(i);
            }
            return "0" + i;
        }

        public List<Object> c(byte[] bArr) {
            LogUtil.a("GlucoseDataParser", "getSugarData return data:", d(bArr));
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.get(this.b, 0, 1);
            String substring = e(this.b).substring(1, 2);
            LogUtil.a("GlucoseDataParser", "getSugarData Flag:", substring);
            try {
                int parseInt = Integer.parseInt(substring);
                wrap.get(this.c, 0, 2);
                int a2 = a(this.c);
                this.f11566a = a2;
                LogUtil.a("GlucoseDataParser", "getSugarData sequenceNumber:", Integer.valueOf(a2));
                String e = e(wrap);
                if ((parseInt & 1) == 1) {
                    wrap.get(this.c, 0, 2);
                }
                wrap.get(this.c, 0, 2);
                String binaryString = Integer.toBinaryString(a(this.c));
                try {
                    double b = dac.b((parseInt & 4) >> 2, binaryString);
                    if (c((parseInt & 2) >> 1, wrap)) {
                        return Collections.emptyList();
                    }
                    return d(new BigDecimal(b).setScale(1, 4).floatValue(), e, this.f11566a);
                } catch (IllegalArgumentException e2) {
                    LogUtil.b("GlucoseDataParser", "getSugarData glpValue rawGlucose:", binaryString, " e = ", e2.getMessage());
                    return Collections.emptyList();
                }
            } catch (NumberFormatException e3) {
                LogUtil.b("GlucoseDataParser", "getSugarData e = ", e3.getMessage());
                return Collections.emptyList();
            }
        }

        private List<Object> d(float f, String str, int i) {
            ArrayList arrayList = new ArrayList(3);
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                arrayList.add(Float.valueOf(f));
                arrayList.add(Long.valueOf(simpleDateFormat.parse(str).getTime()));
                arrayList.add(Integer.valueOf(i));
                return arrayList;
            } catch (ParseException e) {
                LogUtil.b("GlucoseDataParser", "getSugarData ParseException e = ", e.getMessage());
                return Collections.emptyList();
            }
        }

        private boolean c(int i, ByteBuffer byteBuffer) {
            if (i == 1) {
                byteBuffer.get(this.b, 0, 1);
                String binaryString = Integer.toBinaryString(a(this.b));
                try {
                    if (Integer.parseInt(("00000000".substring(0, 8 - binaryString.length()) + binaryString).substring(0, 4), 2) == 4) {
                        return true;
                    }
                } catch (NumberFormatException e) {
                    LogUtil.b("GlucoseDataParser", "isParseOneSuccess", e.getMessage());
                    return true;
                }
            }
            return false;
        }

        private String e(ByteBuffer byteBuffer) {
            byteBuffer.get(this.c, 0, 2);
            String a2 = a(a(this.c));
            byteBuffer.get(this.b, 0, 1);
            String a3 = a(a(this.b));
            byteBuffer.get(this.b, 0, 1);
            String a4 = a(a(this.b));
            byteBuffer.get(this.b, 0, 1);
            String a5 = a(a(this.b));
            byteBuffer.get(this.b, 0, 1);
            String a6 = a(a(this.b));
            byteBuffer.get(this.b, 0, 1);
            return String.format(Locale.ENGLISH, "%s-%s-%s %s:%s:%s", a2, a3, a4, a5, a6, a(a(this.b)));
        }

        public String d(byte[] bArr) {
            StringBuilder sb = new StringBuilder(bArr.length * 2);
            for (byte b : bArr) {
                sb.append(String.format(Locale.ENGLISH, "%02x", Integer.valueOf(b & 255)));
            }
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double b(int i, String str) throws IllegalArgumentException {
        double pow;
        double d2;
        try {
            int parseInt = str.length() >= 4 ? Integer.parseInt(str.substring(0, 4), 2) : 0;
            int i2 = parseInt > 8 ? parseInt - 16 : -parseInt;
            try {
                int parseInt2 = Integer.parseInt(str.substring(4, 16), 2);
                if (i == 1) {
                    pow = parseInt2;
                    d2 = Math.pow(10.0d, i2);
                } else {
                    pow = parseInt2 * Math.pow(10.0d, i2);
                    d2 = 5.55000555000555d;
                }
                return pow * d2 * 1000.0d;
            } catch (NumberFormatException e) {
                LogUtil.b("GlucoseDataParser", "getGlpValue mantissa", e.getMessage());
                throw new IllegalArgumentException("mantissa parse error");
            }
        } catch (NumberFormatException e2) {
            LogUtil.b("GlucoseDataParser", "getGlpValue exponent", e2.getMessage());
            throw new IllegalArgumentException("exponent parse error");
        }
    }
}
