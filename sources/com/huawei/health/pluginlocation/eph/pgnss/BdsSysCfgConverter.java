package com.huawei.health.pluginlocation.eph.pgnss;

import com.amap.api.services.district.DistrictSearchQuery;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import defpackage.eym;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class BdsSysCfgConverter {

    interface BdsSysCfgItem {
        public static final String EMPTY = "";
        public static final int FACTOR = 100000;
        public static final int ZERO = 0;

        default void inputFromJson(JsonArray jsonArray) {
        }

        default void inputFromJson(JsonObject jsonObject) {
        }

        default int outPutToByteBuffer(ByteBuffer byteBuffer) {
            return 0;
        }
    }

    static class i implements BdsSysCfgItem {
        private int c;
        private int d;
        private int e;

        i() {
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public void inputFromJson(JsonObject jsonObject) {
            if (jsonObject == null) {
                return;
            }
            this.c = jsonObject.get("freqType") != null ? jsonObject.get("freqType").getAsInt() : 0;
            this.d = jsonObject.get("syncSeq") != null ? jsonObject.get("syncSeq").getAsInt() : 0;
            this.e = jsonObject.get("dataSeq") != null ? jsonObject.get("dataSeq").getAsInt() : 0;
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public int outPutToByteBuffer(ByteBuffer byteBuffer) {
            int position = byteBuffer.position();
            byteBuffer.putInt(this.c);
            byteBuffer.putInt(this.d);
            byteBuffer.putInt(this.e);
            return byteBuffer.position() - position;
        }
    }

    static class f implements BdsSysCfgItem {

        /* renamed from: a, reason: collision with root package name */
        private int f2941a;
        private int b;
        private int c;
        private int d;
        private String h = "";
        private i[] e = new i[5];

        f() {
            int i = 0;
            while (true) {
                i[] iVarArr = this.e;
                if (i >= iVarArr.length) {
                    return;
                }
                iVarArr[i] = new i();
                i++;
            }
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public void inputFromJson(JsonObject jsonObject) {
            JsonElement jsonElement;
            if (jsonObject == null || (jsonElement = jsonObject.get("inChanCommInfo")) == null || !jsonElement.isJsonObject()) {
                return;
            }
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            this.f2941a = asJsonObject.get("inFeqTypeSupp") != null ? asJsonObject.get("inFeqTypeSupp").getAsInt() : 0;
            this.c = asJsonObject.get("inPilotRatio") != null ? asJsonObject.get("inPilotRatio").getAsInt() : 0;
            this.d = asJsonObject.get("inPilotSeq") != null ? asJsonObject.get("inPilotSeq").getAsInt() : 0;
            this.h = asJsonObject.get("inSyncSymbol") != null ? asJsonObject.get("inSyncSymbol").getAsString() : "";
            JsonElement jsonElement2 = asJsonObject.get("inSpreadSpecSeq");
            if (jsonElement2 == null || !jsonElement2.isJsonArray()) {
                return;
            }
            Iterator<JsonElement> it = jsonElement2.getAsJsonArray().iterator();
            while (it.hasNext()) {
                this.e[this.b].inputFromJson(it.next().getAsJsonObject());
                int i = this.b + 1;
                this.b = i;
                if (i >= this.e.length) {
                    return;
                }
            }
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public int outPutToByteBuffer(ByteBuffer byteBuffer) {
            int position = byteBuffer.position();
            byteBuffer.putInt(this.f2941a);
            byteBuffer.putInt(this.c);
            byteBuffer.putInt(this.d);
            byteBuffer.put(Arrays.copyOf(this.h.getBytes(StandardCharsets.UTF_8), 56));
            byteBuffer.putInt(this.b);
            int i = 0;
            while (true) {
                i[] iVarArr = this.e;
                if (i < iVarArr.length) {
                    iVarArr[i].outPutToByteBuffer(byteBuffer);
                    i++;
                } else {
                    return byteBuffer.position() - position;
                }
            }
        }
    }

    static class h implements BdsSysCfgItem {
        private int b;

        h() {
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public void inputFromJson(JsonObject jsonObject) {
            JsonElement jsonElement = jsonObject.get("outChanCommInfo");
            if (jsonElement == null || !jsonElement.isJsonObject()) {
                return;
            }
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            this.b = asJsonObject.get("outCodeMode") != null ? asJsonObject.get("outCodeMode").getAsInt() : 0;
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public int outPutToByteBuffer(ByteBuffer byteBuffer) {
            int position = byteBuffer.position();
            byteBuffer.putInt(this.b);
            return byteBuffer.position() - position;
        }
    }

    static class c implements BdsSysCfgItem {

        /* renamed from: a, reason: collision with root package name */
        private int f2938a;
        private double c;
        private int d;

        c() {
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public void inputFromJson(JsonObject jsonObject) {
            if (jsonObject == null) {
                return;
            }
            this.f2938a = jsonObject.get("svid") != null ? jsonObject.get("svid").getAsInt() : 0;
            this.d = jsonObject.get("prn") != null ? jsonObject.get("prn").getAsInt() : 0;
            this.c = jsonObject.get("fixDelayOffset") != null ? jsonObject.get("fixDelayOffset").getAsDouble() : 0.0d;
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public int outPutToByteBuffer(ByteBuffer byteBuffer) {
            int position = byteBuffer.position();
            byteBuffer.putInt(this.f2938a);
            byteBuffer.putInt(this.d);
            byteBuffer.putInt((int) (this.c * 100000.0d));
            return byteBuffer.position() - position;
        }
    }

    static class e implements BdsSysCfgItem {

        /* renamed from: a, reason: collision with root package name */
        private c[] f2940a = new c[20];
        private int e;

        e() {
            int i = 0;
            while (true) {
                c[] cVarArr = this.f2940a;
                if (i >= cVarArr.length) {
                    return;
                }
                cVarArr[i] = new c();
                i++;
            }
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public void inputFromJson(JsonObject jsonObject) {
            JsonElement jsonElement = jsonObject.get("beamChanInfo");
            if (jsonElement == null || !jsonElement.isJsonArray()) {
                return;
            }
            Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
            while (it.hasNext()) {
                this.f2940a[this.e].inputFromJson(it.next().getAsJsonObject());
                int i = this.e + 1;
                this.e = i;
                if (i >= this.f2940a.length) {
                    return;
                }
            }
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public int outPutToByteBuffer(ByteBuffer byteBuffer) {
            int position = byteBuffer.position();
            byteBuffer.putInt(this.e);
            int i = 0;
            while (true) {
                c[] cVarArr = this.f2940a;
                if (i < cVarArr.length) {
                    cVarArr[i].outPutToByteBuffer(byteBuffer);
                    i++;
                } else {
                    return byteBuffer.position() - position;
                }
            }
        }
    }

    static class j implements BdsSysCfgItem {

        /* renamed from: a, reason: collision with root package name */
        private int[] f2943a = new int[3];
        private String b;
        private double c;
        private int d;
        private double e;

        j() {
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public void inputFromJson(JsonObject jsonObject) {
            if (jsonObject == null) {
                return;
            }
            this.b = jsonObject.get(DistrictSearchQuery.KEYWORDS_CITY) != null ? jsonObject.get(DistrictSearchQuery.KEYWORDS_CITY).getAsString() : "";
            this.e = jsonObject.get(JsbMapKeyNames.H5_LOC_LON) != null ? jsonObject.get(JsbMapKeyNames.H5_LOC_LON).getAsDouble() : 0.0d;
            this.c = jsonObject.get(JsbMapKeyNames.H5_LOC_LAT) != null ? jsonObject.get(JsbMapKeyNames.H5_LOC_LAT).getAsDouble() : 0.0d;
            JsonElement jsonElement = jsonObject.get("beamInfo");
            if (jsonElement.isJsonArray()) {
                Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
                while (it.hasNext()) {
                    this.f2943a[this.d] = it.next().getAsInt();
                    int i = this.d + 1;
                    this.d = i;
                    if (i >= this.f2943a.length) {
                        return;
                    }
                }
            }
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public int outPutToByteBuffer(ByteBuffer byteBuffer) {
            int position = byteBuffer.position();
            byteBuffer.put(Arrays.copyOf(this.b.getBytes(StandardCharsets.UTF_8), 24));
            byteBuffer.putInt((int) (this.e * 100000.0d));
            byteBuffer.putInt((int) (this.c * 100000.0d));
            int i = 0;
            while (true) {
                int[] iArr = this.f2943a;
                if (i < iArr.length) {
                    byteBuffer.putInt(iArr[i]);
                    i++;
                } else {
                    return byteBuffer.position() - position;
                }
            }
        }
    }

    static class g implements BdsSysCfgItem {

        /* renamed from: a, reason: collision with root package name */
        private List<j> f2942a = new ArrayList();
        private double c;

        g() {
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public void inputFromJson(JsonObject jsonObject) {
            JsonObject asJsonObject;
            JsonElement jsonElement = jsonObject.get("cityCoverInfo");
            if (jsonElement == null || !jsonElement.isJsonArray()) {
                return;
            }
            JsonArray asJsonArray = jsonElement.getAsJsonArray();
            if (asJsonArray.size() >= 1 && (asJsonObject = asJsonArray.get(0).getAsJsonObject()) != null) {
                this.c = asJsonObject.get("distanceThreshold") != null ? asJsonObject.get("distanceThreshold").getAsDouble() : 0.0d;
                JsonElement jsonElement2 = asJsonObject.get("cityList");
                if (jsonElement2.isJsonArray()) {
                    Iterator<JsonElement> it = jsonElement2.getAsJsonArray().iterator();
                    while (it.hasNext()) {
                        JsonElement next = it.next();
                        j jVar = new j();
                        jVar.inputFromJson(next.getAsJsonObject());
                        this.f2942a.add(jVar);
                    }
                }
            }
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public int outPutToByteBuffer(ByteBuffer byteBuffer) {
            int position = byteBuffer.position();
            byteBuffer.putInt((int) this.c);
            byteBuffer.putInt(this.f2942a.size());
            Iterator<j> it = this.f2942a.iterator();
            while (it.hasNext()) {
                it.next().outPutToByteBuffer(byteBuffer);
            }
            return byteBuffer.position() - position;
        }
    }

    static class k implements BdsSysCfgItem {

        /* renamed from: a, reason: collision with root package name */
        private double f2944a;
        private double d;

        k() {
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public void inputFromJson(JsonArray jsonArray) {
            JsonArray asJsonArray = jsonArray.getAsJsonArray();
            if (asJsonArray.size() >= 2) {
                this.f2944a = asJsonArray.get(0).getAsDouble();
                this.d = asJsonArray.get(1).getAsDouble();
            }
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public int outPutToByteBuffer(ByteBuffer byteBuffer) {
            int position = byteBuffer.position();
            byteBuffer.putInt((int) (this.f2944a * 100000.0d));
            byteBuffer.putInt((int) (this.d * 100000.0d));
            return byteBuffer.position() - position;
        }
    }

    static class d implements BdsSysCfgItem {

        /* renamed from: a, reason: collision with root package name */
        private int f2939a;
        private int b;
        private int c;
        private List<k> d = new ArrayList();
        private int e;

        d() {
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public void inputFromJson(JsonObject jsonObject) {
            if (jsonObject == null) {
                return;
            }
            this.b = jsonObject.get("svid") != null ? jsonObject.get("svid").getAsInt() : 0;
            this.c = jsonObject.get("prn") != null ? jsonObject.get("prn").getAsInt() : 0;
            JsonElement jsonElement = jsonObject.get("position");
            if (jsonElement.isJsonArray()) {
                Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
                while (it.hasNext()) {
                    JsonElement next = it.next();
                    k kVar = new k();
                    kVar.inputFromJson(next.getAsJsonArray());
                    this.d.add(kVar);
                }
            }
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public int outPutToByteBuffer(ByteBuffer byteBuffer) {
            int position = byteBuffer.position();
            byteBuffer.putInt(this.b);
            byteBuffer.putInt(this.c);
            byteBuffer.putInt(this.e);
            byteBuffer.putInt(this.f2939a);
            return byteBuffer.position() - position;
        }

        public void b(List<k> list) {
            this.e = list.size();
            list.addAll(this.d);
            this.f2939a = this.d.size();
        }
    }

    static class b implements BdsSysCfgItem {

        /* renamed from: a, reason: collision with root package name */
        private List<k> f2937a;
        private d[] b = new d[20];
        private int e;

        b() {
            int i = 0;
            while (true) {
                d[] dVarArr = this.b;
                if (i < dVarArr.length) {
                    dVarArr[i] = new d();
                    i++;
                } else {
                    this.f2937a = new ArrayList();
                    return;
                }
            }
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public void inputFromJson(JsonObject jsonObject) {
            JsonElement jsonElement = jsonObject.get("serviceAreaSuggest");
            if (jsonElement == null || !jsonElement.isJsonArray()) {
                return;
            }
            Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
            while (it.hasNext()) {
                this.b[this.e].inputFromJson(it.next().getAsJsonObject());
                this.e++;
            }
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public int outPutToByteBuffer(ByteBuffer byteBuffer) {
            int i;
            int position = byteBuffer.position();
            int i2 = 0;
            while (true) {
                i = this.e;
                if (i2 >= i) {
                    break;
                }
                this.b[i2].b(this.f2937a);
                i2++;
            }
            byteBuffer.putInt(i);
            int i3 = 0;
            while (true) {
                d[] dVarArr = this.b;
                if (i3 >= dVarArr.length) {
                    break;
                }
                dVarArr[i3].outPutToByteBuffer(byteBuffer);
                i3++;
            }
            byteBuffer.putInt(this.f2937a.size());
            for (int i4 = 0; i4 < this.f2937a.size(); i4++) {
                this.f2937a.get(i4).outPutToByteBuffer(byteBuffer);
            }
            return byteBuffer.position() - position;
        }
    }

    static class a implements BdsSysCfgItem {
        private long b;
        private long h;
        private String i;
        private f c = new f();
        private h f = new h();
        private e d = new e();
        private g e = new g();

        /* renamed from: a, reason: collision with root package name */
        private b f2936a = new b();

        a() {
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public void inputFromJson(JsonObject jsonObject) {
            if (jsonObject == null) {
                return;
            }
            this.i = jsonObject.get("version") != null ? jsonObject.get("version").getAsString() : "";
            this.h = jsonObject.get("releaseTime") != null ? jsonObject.get("releaseTime").getAsLong() : 0L;
            this.b = jsonObject.get("effectiveTime") != null ? jsonObject.get("effectiveTime").getAsLong() : 0L;
            this.c.inputFromJson(jsonObject);
            this.f.inputFromJson(jsonObject);
            this.d.inputFromJson(jsonObject);
            this.e.inputFromJson(jsonObject);
            this.f2936a.inputFromJson(jsonObject);
        }

        @Override // com.huawei.health.pluginlocation.eph.pgnss.BdsSysCfgConverter.BdsSysCfgItem
        public int outPutToByteBuffer(ByteBuffer byteBuffer) {
            byteBuffer.putInt(-1111638595);
            byteBuffer.mark();
            byteBuffer.putInt(0);
            byteBuffer.put(Arrays.copyOf(this.i.getBytes(StandardCharsets.UTF_8), 8));
            byteBuffer.putLong(this.h);
            byteBuffer.putLong(this.b);
            int position = byteBuffer.position();
            int outPutToByteBuffer = this.c.outPutToByteBuffer(byteBuffer);
            int outPutToByteBuffer2 = this.f.outPutToByteBuffer(byteBuffer);
            int outPutToByteBuffer3 = position + outPutToByteBuffer + outPutToByteBuffer2 + this.d.outPutToByteBuffer(byteBuffer) + this.e.outPutToByteBuffer(byteBuffer) + this.f2936a.outPutToByteBuffer(byteBuffer);
            byteBuffer.reset();
            byteBuffer.putInt(outPutToByteBuffer3);
            return outPutToByteBuffer3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(String str, String str2) {
        JsonElement parse;
        FileOutputStream fileOutputStream;
        File file;
        eym.b("BdsSysCfgConverter", "convertBdsSysCfgJsonToBin start");
        try {
            JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(new FileInputStream(str), StandardCharsets.UTF_8)).getAsJsonObject().get("bdsPara");
            if (jsonElement == null || !jsonElement.isJsonPrimitive() || (parse = new JsonParser().parse(jsonElement.getAsString())) == null || !parse.isJsonObject()) {
                return;
            }
            a aVar = new a();
            aVar.inputFromJson(parse.getAsJsonObject());
            ByteBuffer allocate = ByteBuffer.allocate(32768);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            int outPutToByteBuffer = aVar.outPutToByteBuffer(allocate);
            FileOutputStream fileOutputStream2 = null;
            FileOutputStream fileOutputStream3 = null;
            try {
                try {
                    file = new File(str2);
                    fileOutputStream = new FileOutputStream(file);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                }
            } catch (IOException unused) {
            }
            try {
                boolean exists = file.exists();
                if (exists == 0) {
                    file.createNewFile();
                }
                fileOutputStream.write(allocate.array(), 0, outPutToByteBuffer);
                fileOutputStream.flush();
                fileOutputStream.close();
                try {
                    fileOutputStream.close();
                    fileOutputStream2 = exists;
                } catch (IOException unused2) {
                    eym.c("BdsSysCfgConverter", "convertBdsSysCfgJsonToBin IOException 2");
                    fileOutputStream2 = exists;
                }
            } catch (IOException unused3) {
                fileOutputStream3 = fileOutputStream;
                eym.c("BdsSysCfgConverter", "convertBdsSysCfgJsonToBin IOException 1");
                fileOutputStream2 = fileOutputStream3;
                if (fileOutputStream3 != null) {
                    try {
                        fileOutputStream3.close();
                        fileOutputStream2 = fileOutputStream3;
                    } catch (IOException unused4) {
                        eym.c("BdsSysCfgConverter", "convertBdsSysCfgJsonToBin IOException 2");
                        fileOutputStream2 = fileOutputStream3;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused5) {
                        eym.c("BdsSysCfgConverter", "convertBdsSysCfgJsonToBin IOException 2");
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused6) {
            eym.c("BdsSysCfgConverter", "convertBdsSysCfgJsonToBin FileNotFoundException");
        }
    }
}
