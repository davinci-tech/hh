package defpackage;

import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.wearengine.sensor.AsyncReadCallback;
import com.huawei.wearengine.sensor.AsyncStopCallback;
import com.huawei.wearengine.sensor.DataResult;
import com.huawei.wearengine.sensor.Sensor;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class mbq {
    private static final Object d = new Object();
    private static final Pattern e = Pattern.compile("[0-9]+:");

    private static boolean c(byte b) {
        return (b & Byte.MIN_VALUE) == -128;
    }

    public static byte[] d() {
        return mbg.a("00012400000003000000FFE800000800");
    }

    public static byte[] b(Sensor sensor, int i, String str) {
        if (sensor == null) {
            tos.e("CommandUtil", "controlSensorCmd sensor is null");
            return new byte[0];
        }
        if (TextUtils.isEmpty(str)) {
            tos.e("CommandUtil", "controlSensorCmd cmd is null");
            return new byte[0];
        }
        tos.b("CommandUtil", "controlSensorCmd sensorId " + sensor.getId() + " cmd " + str);
        StringBuilder sb = new StringBuilder("SET_PARAMETER * DMSDP/1.0\r\nServiceType: 2048\r\nDataSessionID: ");
        sb.append(sensor.getId());
        sb.append("\r\nCSeq: ");
        sb.append(i);
        sb.append("\r\nDataVersion: 2\r\nRate: ");
        sb.append(tsg.a(sensor.getExtendJson()));
        sb.append("\r\nContent-Type: text/parameters\r\nContent-Length: ");
        StringBuilder sb2 = new StringBuilder("msdp_trigger_method: ");
        sb2.append(str);
        sb.append(sb2.length());
        sb.append("\r\n\r\n");
        sb.append((CharSequence) sb2);
        sb.append((char) 0);
        String sb3 = sb.toString();
        tos.b("CommandUtil", "controlSensorCmd controlSensorCmd:");
        tos.b("CommandUtil", sb3);
        byte[] bArr = new byte[0];
        try {
            bArr = sb3.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            tos.e("CommandUtil", "controlSensorCmd UnsupportedEncodingException");
        }
        return e(2, bArr);
    }

    public static byte[] b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            tos.d("CommandUtil", "getSensorData value is empty");
            return new byte[0];
        }
        if (bArr.length > 65535) {
            tos.d("CommandUtil", "getSensorData value.length out of range");
            return new byte[0];
        }
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        c(bArr, 1, bArr2, 0, length);
        tos.b("CommandUtil", "getSensorData: " + Arrays.toString(bArr2));
        return bArr2;
    }

    public static float[] e(int i, byte[] bArr, boolean z) {
        if (i > 4) {
            tos.d("CommandUtil", "convertByteArrayToFloatArray size over 4");
            return new float[0];
        }
        if (i <= 0) {
            tos.d("CommandUtil", "convertByteArrayToFloatArray size is 0");
            return new float[0];
        }
        if (bArr == null) {
            tos.d("CommandUtil", "convertByteArrayToFloatArray data is null");
            return new float[0];
        }
        float[] fArr = new float[bArr.length / i];
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3 = 0;
            for (int i4 = 0; i4 < i; i4++) {
                i3 |= (bArr[i2 + i4] & 255) << (i4 * 8);
            }
            fArr[i2 / i] = z ? Float.intBitsToFloat(i3) : i3;
            i2 += i;
        }
        return fArr;
    }

    public static void a(byte[] bArr, Map<Integer, AsyncReadCallback> map, Map<Integer, AsyncStopCallback> map2) throws RemoteException {
        if (bArr == null || bArr.length <= 0) {
            tos.e("CommandUtil", "handleSensorEnable dataContents is invalids");
            return;
        }
        if (map == null) {
            tos.e("CommandUtil", "handleSensorEnable asyncReadErrorCallbackMap is null");
            return;
        }
        if (map2 == null) {
            tos.e("CommandUtil", "handleSensorEnable asyncStopCallbackMap is null");
            return;
        }
        byte[] c = c(bArr);
        tos.b("CommandUtil", "handleSensorEnable data: " + mbg.c(c));
        int length = c.length + (-1);
        byte[] bArr2 = new byte[length];
        c(c, 0, bArr2, 0, length);
        String[] split = new String(bArr2, Charset.defaultCharset()).split("\r\n");
        int a2 = a(split);
        int b = b(split);
        tos.a("CommandUtil", "handleSensorEnable code: " + a2 + " req: " + b);
        AsyncReadCallback asyncReadCallback = map.get(Integer.valueOf(b));
        map.remove(Integer.valueOf(b));
        if (asyncReadCallback != null) {
            if (a2 == 200) {
                return;
            }
            try {
                asyncReadCallback.onReadResult(a2, null);
                return;
            } catch (Exception unused) {
                tos.e("CommandUtil", "handleSensorEnable asyncReadCallback onReadResult Exception");
                return;
            }
        }
        AsyncStopCallback asyncStopCallback = map2.get(Integer.valueOf(b));
        map2.remove(Integer.valueOf(b));
        if (asyncStopCallback != null) {
            try {
                asyncStopCallback.onStopResult(a2 != 200 ? a2 : 0);
            } catch (Exception unused2) {
                tos.e("CommandUtil", "handleSensorEnable asyncStopCallback onStopResult Exception");
            }
        }
    }

    private static int a(String[] strArr) {
        String str;
        if (strArr == null) {
            tos.e("CommandUtil", "getCode retLines is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        String[] split = strArr[0].split(" ");
        if (split.length > 1) {
            str = split[1];
        } else {
            tos.d("CommandUtil", "code information is null.");
            str = null;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            tos.e("CommandUtil", "getCode NumberFormatException");
            throw new IllegalStateException(String.valueOf(12));
        }
    }

    private static int b(String[] strArr) {
        String str;
        if (strArr == null) {
            tos.e("CommandUtil", "getReq retLines is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        try {
            if (strArr.length > 2) {
                String[] split = strArr[2].split(": ");
                if (split.length > 1) {
                    str = split[1];
                    return Integer.parseInt(str);
                }
                tos.d("CommandUtil", "req information is null.");
            } else {
                tos.d("CommandUtil", "result does not contain req information.");
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            tos.e("CommandUtil", "getReq NumberFormatException");
            throw new IllegalStateException(String.valueOf(12));
        }
        str = null;
    }

    public static void e(byte[] bArr, List<Sensor> list, tou touVar) {
        if (bArr == null || bArr.length <= 0) {
            tos.e("CommandUtil", "handleSensorSync dataContents is invalids");
            return;
        }
        if (list == null) {
            tos.e("CommandUtil", "handleSensorSync sensors is null");
            return;
        }
        if (touVar == null) {
            tos.e("CommandUtil", "handleSensorSync asynToSyncListener is null");
            return;
        }
        tos.b("CommandUtil", "handleSensorSync dataContents " + mbg.c(bArr));
        byte[] j = j(c(bArr));
        e a2 = a(j, 0);
        byte[] bArr2 = new byte[(j.length - 1) - a2.e()];
        c(j, a2.e(), bArr2, 0, (j.length - 1) - a2.e());
        int d2 = d(bArr2);
        if (list.size() >= d2) {
            tos.a("CommandUtil", "handleSensorSync onFinish first");
            touVar.onFinish();
            return;
        }
        Sensor e2 = e(bArr2);
        synchronized (d) {
            list.add(e2);
        }
        tos.a("CommandUtil", "handleSensorSync sensor num is " + list.size());
        if (list.size() >= d2) {
            tos.a("CommandUtil", "handleSensorSync onFinish");
            touVar.onFinish();
        }
    }

    public static int a(byte[] bArr) {
        byte[] bArr2 = new byte[2];
        c(bArr, 0, bArr2, 0, 2);
        tos.b("CommandUtil", "parseControlModeCmdType controlModeCmd " + Arrays.toString(bArr2));
        return bArr2[1];
    }

    private static Sensor e(byte[] bArr) {
        try {
            JSONObject jSONObject = new JSONObject(e(new String(bArr, Charset.defaultCharset())));
            tos.b("CommandUtil", "getSensor CMD_SYNC_DEVICE_SERVICE resultJson: " + jSONObject);
            JSONObject jSONObject2 = jSONObject.getJSONObject(Event.EventConstants.PROPERTIES).getJSONObject("2017");
            int i = jSONObject2.getInt("sensorType");
            tos.b("CommandUtil", "getSensor CMD_SYNC_DEVICE_SERVICE innerSensorType: " + i);
            int a2 = tqp.a(i);
            tos.b("CommandUtil", "getSensor CMD_SYNC_DEVICE_SERVICE sensorType: " + a2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("FrequencyType", 2);
            if (jSONObject.has("isSupportUTC")) {
                jSONObject3.put("isSupportUTC", jSONObject.getBoolean("isSupportUTC"));
            }
            if (jSONObject.has("FrequencyList")) {
                jSONObject3.put("SupportFrequencyList", jSONObject.getString("FrequencyList"));
            }
            if (jSONObject.has("SensorChipType")) {
                jSONObject3.put("SensorChipType", jSONObject.getString("SensorChipType"));
            }
            return new Sensor(jSONObject2.getInt("sensorId"), a2, jSONObject2.getInt("accuracy"), (float) jSONObject2.getDouble("resolution"), jSONObject3.toString());
        } catch (JSONException e2) {
            tos.e("CommandUtil", "createSensorByJson resultJson formmat error");
            tos.b("CommandUtil", "createSensorByJson resultJson formmat error " + e2);
            return null;
        }
    }

    private static String e(String str) {
        Matcher matcher = e.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            str = str.replace(group, "\"" + group.substring(0, group.length() - 1) + "\":");
        }
        return str.replaceAll("\\\\", "").replace("\"{", "{").replace("}\"", "}");
    }

    private static int d(byte[] bArr) {
        String str;
        try {
            JSONObject jSONObject = new JSONObject(e(new String(bArr, Charset.defaultCharset()))).getJSONObject(Event.EventConstants.PROPERTIES);
            Iterator<String> keys = jSONObject.keys();
            int i = 0;
            while (keys.hasNext()) {
                String next = keys.next();
                if (!(next instanceof String)) {
                    str = "";
                } else {
                    str = next;
                }
                if (!str.equals("2017")) {
                    tos.a("CommandUtil", "parseSensorNum key: " + str + " value: " + jSONObject.getBoolean(str));
                    if (jSONObject.getBoolean(str)) {
                        i++;
                    }
                }
            }
            tos.a("CommandUtil", "parseSensorNum sensor num is " + i);
            return i;
        } catch (JSONException e2) {
            tos.e("CommandUtil", "createSensorByJson resultJson formmat error");
            tos.b("CommandUtil", "createSensorByJson resultJson formmat error " + e2);
            return -1;
        }
    }

    private static byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (bArr == null || bArr.length <= 2) {
            return bArr2;
        }
        byte[] bArr3 = new byte[bArr.length - 2];
        c(bArr, 2, bArr3, 0, bArr.length - 2);
        return bArr3;
    }

    private static byte[] j(byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (bArr == null || bArr.length <= 9) {
            return bArr2;
        }
        byte[] bArr3 = new byte[bArr.length - 9];
        c(bArr, 9, bArr3, 0, bArr.length - 9);
        return bArr3;
    }

    private static void c(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (i < 0) {
            tos.e("CommandUtil", "srcPos IndexOutOfBounds");
            return;
        }
        if (i2 < 0) {
            tos.e("CommandUtil", "destPos IndexOutOfBounds");
            return;
        }
        if (i3 < 0) {
            tos.e("CommandUtil", "length IndexOutOfBounds");
            return;
        }
        if (i + i3 > bArr.length) {
            tos.e("CommandUtil", "srcPos + length IndexOutOfBounds");
        } else if (i2 + i3 > bArr2.length) {
            tos.e("CommandUtil", "destPos + length IndexOutOfBounds");
        } else {
            System.arraycopy(bArr, i, bArr2, i2, i3);
        }
    }

    public static int e(int i, byte[] bArr, DataResult dataResult) {
        if (bArr == null || dataResult == null) {
            tos.e("CommandUtil", "getMultiChannelSensorData sensorData or dataResult is null");
            return 12;
        }
        int i2 = 0;
        try {
            long j = 0;
            byte[] bArr2 = null;
            int i3 = 0;
            for (mbn mbnVar : new mbr().a(mbg.c(bArr)).c()) {
                try {
                    tos.b("CommandUtil", "getNewSensorData tlv tag " + mbnVar.d() + " length " + mbnVar.e() + " value " + mbnVar.c());
                    int b = b(mbnVar.d());
                    if (b == 1) {
                        i3 = b(mbnVar.c());
                    } else if (b == 3) {
                        j = a(mbnVar.c());
                    } else if (b == 4) {
                        i2 = b(mbnVar.c());
                    } else if (b == 5) {
                        bArr2 = mbg.a(mbnVar.c());
                    }
                } catch (mbl unused) {
                    i2 = i3;
                    tos.e("CommandUtil", "getNewSensorData parse sensorData Exception");
                    return i2;
                }
            }
            dataResult.setTimestamp(j);
            e(i, bArr2, i2, dataResult);
            return i3;
        } catch (mbl unused2) {
        }
    }

    public static int c(int i, boolean z, byte[] bArr, DataResult dataResult) {
        byte[] bArr2;
        if (bArr == null || dataResult == null) {
            tos.e("CommandUtil", "getNoneChannelSensorData sensorData or dataResult is null");
            return 12;
        }
        if (bArr.length < 9) {
            dataResult.setTimestamp(0L);
            dataResult.setListValues(new LinkedList());
            if (dataResult.getSensor().getName().equals(Sensor.NAME_ACC)) {
                dataResult.setValues(new float[0]);
                return 13;
            }
            dataResult.setValues(e(tqp.b(i), bArr, i == 4));
            return 0;
        }
        int i2 = 8;
        byte[] bArr3 = new byte[8];
        c(bArr, 0, bArr3, 0, 8);
        dataResult.setTimestamp(a(mbg.c(bArr3)));
        if (z) {
            byte[] bArr4 = new byte[8];
            c(bArr, 8, bArr4, 0, 8);
            dataResult.setUtc(a(mbg.c(bArr4)));
            i2 = 16;
            bArr2 = new byte[bArr.length - 16];
        } else {
            bArr2 = new byte[bArr.length - 8];
        }
        c(bArr, i2, bArr2, 0, bArr2.length);
        dataResult.setValues(e(tqp.b(i), bArr2, i == 4));
        dataResult.setListValues(new LinkedList());
        return 0;
    }

    public static int b(int i, byte[] bArr, DataResult dataResult) {
        if (bArr == null || dataResult == null) {
            tos.e("CommandUtil", "getHeartRateSensorData sensorData or dataResult is null");
            return 12;
        }
        if (bArr.length < 9) {
            dataResult.setTimestamp(0L);
            dataResult.setListValues(new LinkedList());
            dataResult.setValues(e(tqp.b(i), bArr, false));
            return 0;
        }
        byte[] bArr2 = new byte[8];
        c(bArr, 0, bArr2, 0, 8);
        dataResult.setTimestamp(a(mbg.c(bArr2)));
        int length = bArr.length - 8;
        byte[] bArr3 = new byte[length];
        c(bArr, 8, bArr3, 0, length);
        float[] e2 = e(tqp.b(i), bArr3, false);
        dataResult.setValues(e2);
        dataResult.setListValues(new LinkedList());
        if (((int) e2[0]) <= 255) {
            return 0;
        }
        dataResult.setTimestamp(0L);
        dataResult.setValues(new float[0]);
        return (int) e2[0];
    }

    private static void e(int i, byte[] bArr, int i2, DataResult dataResult) {
        ArrayList arrayList = new ArrayList();
        dataResult.setListValues(arrayList);
        dataResult.setValues(new float[0]);
        float[] e2 = e(4, bArr, false);
        if (e2.length == 0) {
            tos.d("CommandUtil", "parseNewSensorData floatData is empty");
            return;
        }
        if (i2 == 0) {
            i2 = e2.length;
        }
        int length = e2.length / i2;
        for (int i3 = 0; i3 < length; i3++) {
            DataResult dataResult2 = new DataResult();
            dataResult2.setTimestamp(dataResult.getTimestamp());
            dataResult2.setChannel(a(i, i3));
            float[] fArr = new float[i2];
            for (int i4 = 0; i4 < e2.length; i4 += length) {
                fArr[i4 / length] = e2[i4 + i3];
            }
            dataResult2.setValues(fArr);
            dataResult2.setSensor(dataResult.getSensor());
            arrayList.add(dataResult2);
        }
        dataResult.setListValues(arrayList);
    }

    private static int a(int i, int i2) {
        if (i == 1) {
            return tqp.e(i2);
        }
        return 0;
    }

    public static int b(String str) {
        int c = c(str, 16);
        tos.b("CommandUtil", "parseIntByRadix value is: " + c);
        return c;
    }

    private static int c(String str, int i) {
        try {
            return Integer.parseInt(str, i);
        } catch (NumberFormatException unused) {
            tos.e("CommandUtil", "parseIntByRadix exception, input is : " + str);
            return -1;
        }
    }

    public static long a(String str) {
        try {
            return Long.parseLong(str, 16);
        } catch (NumberFormatException unused) {
            tos.e("CommandUtil", "parseLongByRadix exception");
            return -1L;
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private int f14873a;
        private int b;

        e(int i, int i2) {
            this.f14873a = i;
            this.b = i2;
        }

        public int e() {
            return this.b;
        }
    }

    private static e a(byte[] bArr, int i) {
        int i2 = i + 4;
        if (i2 > bArr.length) {
            i2 = bArr.length;
        }
        int i3 = 0;
        int i4 = i;
        while (i4 < i2) {
            byte b = bArr[i4];
            i3 |= (b & 127) << ((i4 - i) * 7);
            i4++;
            if (c(b)) {
                break;
            }
        }
        return new e(i3, i4);
    }

    public static byte[] e(Integer num, byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            tos.d("CommandUtil", "packageSendData data is empty");
            return new byte[0];
        }
        if (bArr.length > 65535) {
            tos.d("CommandUtil", "packageSendData data.length out of range");
            return new byte[0];
        }
        byte[] bArr2 = new byte[bArr.length + 2];
        bArr2[0] = 0;
        bArr2[1] = num.byteValue();
        c(bArr, 0, bArr2, 2, bArr.length);
        return bArr2;
    }

    public static byte[] a(byte b) {
        return new byte[]{b, 0};
    }
}
