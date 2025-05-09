package defpackage;

import com.huawei.datatype.PeriodRriEntity;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes5.dex */
public class keq {
    public static PeriodRriEntity c(FileInputStream fileInputStream) {
        PeriodRriEntity periodRriEntity;
        byte[] bArr;
        PeriodRriEntity periodRriEntity2 = null;
        if (fileInputStream == null) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_ParsePeriodRriFileUtil", "inputStream is null");
            return null;
        }
        try {
            try {
                LogUtil.a("ParsePeriodRriFileUtil", "parse file Header.");
                periodRriEntity = new PeriodRriEntity();
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException unused) {
                    LogUtil.b("ParsePeriodRriFileUtil", "IOException");
                }
            }
        } catch (FileNotFoundException unused2) {
        } catch (IOException unused3) {
        }
        try {
            bArr = new byte[48];
        } catch (FileNotFoundException unused4) {
            periodRriEntity2 = periodRriEntity;
            ReleaseLogUtil.c("BTSYNC_PeriodRri_ParsePeriodRriFileUtil", "FileNotFoundException");
            try {
                fileInputStream.close();
            } catch (IOException unused5) {
                LogUtil.b("ParsePeriodRriFileUtil", "IOException");
            }
            periodRriEntity = periodRriEntity2;
            LogUtil.a("ParsePeriodRriFileUtil", "Period_RRI parse end,entity is:", periodRriEntity.toString());
            return periodRriEntity;
        } catch (IOException unused6) {
            periodRriEntity2 = periodRriEntity;
            ReleaseLogUtil.c("BTSYNC_PeriodRri_ParsePeriodRriFileUtil", "IOException");
            try {
                fileInputStream.close();
            } catch (IOException unused7) {
                LogUtil.b("ParsePeriodRriFileUtil", "IOException");
            }
            periodRriEntity = periodRriEntity2;
            LogUtil.a("ParsePeriodRriFileUtil", "Period_RRI parse end,entity is:", periodRriEntity.toString());
            return periodRriEntity;
        }
        if (fileInputStream.read(bArr) == -1) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_ParsePeriodRriFileUtil", "isReadHeader is -1");
            return periodRriEntity;
        }
        int c = c(bArr, periodRriEntity);
        int fetchVersion = periodRriEntity.fetchVersion();
        if (fetchVersion == 0 || fetchVersion == 1 || fetchVersion == 2) {
            c(bArr, c, periodRriEntity);
            b(fileInputStream, periodRriEntity);
            a(periodRriEntity.fetchRriDataList());
        } else if (fetchVersion != 3) {
            LogUtil.h("ParsePeriodRriFileUtil", "no support version.");
        } else {
            a(bArr, c, periodRriEntity);
            e(fileInputStream, periodRriEntity);
            e(periodRriEntity.fetchPressDataList());
        }
        try {
            fileInputStream.close();
        } catch (IOException unused8) {
            LogUtil.b("ParsePeriodRriFileUtil", "IOException");
        }
        LogUtil.a("ParsePeriodRriFileUtil", "Period_RRI parse end,entity is:", periodRriEntity.toString());
        return periodRriEntity;
    }

    private static int c(byte[] bArr, PeriodRriEntity periodRriEntity) {
        LogUtil.a("ParsePeriodRriFileUtil", "parse RRI header file com");
        if (bArr != null && bArr.length == 48) {
            LogUtil.a("ParsePeriodRriFileUtil", "header :", cvx.d(bArr));
            int e = e(64);
            periodRriEntity.configFileSize(d(a(bArr, 0, e)));
            int e2 = e(16) + e;
            String a2 = a(bArr, e, e2);
            LogUtil.a("ParsePeriodRriFileUtil", "bitmap :", a2);
            periodRriEntity.configBitmap((int) d(a2));
            int e3 = e(16) + e2;
            periodRriEntity.configVersion((int) d(a(bArr, e2, e3)));
            return e3;
        }
        ReleaseLogUtil.d("BTSYNC_PeriodRri_ParsePeriodRriFileUtil", "header is error argument");
        return -1;
    }

    private static void a(byte[] bArr, int i, PeriodRriEntity periodRriEntity) {
        periodRriEntity.configHeaderReserved(a(bArr, i, i + 36));
    }

    private static void c(byte[] bArr, int i, PeriodRriEntity periodRriEntity) {
        int i2 = i + 1;
        periodRriEntity.configSize((int) d(a(bArr, i, i2)));
        periodRriEntity.configRriDataList(new ArrayList(0));
        periodRriEntity.configHeaderReserved(a(bArr, i2, i + 36));
    }

    private static void b(FileInputStream fileInputStream, PeriodRriEntity periodRriEntity) throws IOException {
        byte[] bArr = new byte[(periodRriEntity.fetchSize() * 3) + 6];
        while (fileInputStream.read(bArr) != -1) {
            a(bArr, periodRriEntity);
        }
    }

    private static void a(byte[] bArr, PeriodRriEntity periodRriEntity) {
        PeriodRriEntity.RriDataEntity rriDataEntity = new PeriodRriEntity.RriDataEntity();
        int i = 0;
        int i2 = 4;
        String a2 = a(bArr, 0, 4);
        if (d(a2) == 0) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_ParsePeriodRriFileUtil", "remove startTime is 0");
            return;
        }
        rriDataEntity.configStartTime(d(a2));
        int fetchSize = periodRriEntity.fetchSize();
        rriDataEntity.configRriList(new ArrayList(fetchSize));
        int i3 = 0;
        while (i3 < fetchSize) {
            int i4 = i2 + 2;
            rriDataEntity.addRri((int) d(a(bArr, i2, i4)));
            i3++;
            i2 = i4;
        }
        rriDataEntity.configSqiList(new ArrayList(fetchSize));
        while (i < fetchSize) {
            int i5 = i2 + 1;
            rriDataEntity.addSqi((int) d(a(bArr, i2, i5)));
            i++;
            i2 = i5;
        }
        int i6 = i2 + 1;
        rriDataEntity.configIntensity((int) d(a(bArr, i2, i6)));
        rriDataEntity.configReserved(a(bArr, i6, i2 + 2));
        periodRriEntity.fetchRriDataList().add(rriDataEntity);
    }

    private static void e(FileInputStream fileInputStream, PeriodRriEntity periodRriEntity) throws IOException {
        byte[] bArr = new byte[66];
        periodRriEntity.configPressDataList(new ArrayList(0));
        while (fileInputStream.read(bArr) != -1) {
            b(bArr, periodRriEntity);
        }
    }

    private static void b(byte[] bArr, PeriodRriEntity periodRriEntity) {
        HiStressMetaData hiStressMetaData = new HiStressMetaData();
        String a2 = a(bArr, 0, 4);
        if (d(a2) == 0) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_ParsePeriodRriFileUtil", "remove startTime is 0");
            return;
        }
        hiStressMetaData.configStressStartTime(d(a2));
        String a3 = a(bArr, 4, 8);
        if (d(a3) == 0) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_ParsePeriodRriFileUtil", "remove endTime is 0");
            return;
        }
        hiStressMetaData.configStressEndTime(d(a3));
        hiStressMetaData.configStressAlgorithmVer((int) d(a(bArr, 8, 12)));
        hiStressMetaData.configStressScore((int) d(a(bArr, 12, 13)));
        hiStressMetaData.configStressGrade((int) d(a(bArr, 13, 14)));
        hiStressMetaData.configStressCalibFlag((int) d(a(bArr, 14, 15)));
        d(bArr, hiStressMetaData, 15);
        periodRriEntity.fetchPressDataList().add(hiStressMetaData);
    }

    private static void d(byte[] bArr, HiStressMetaData hiStressMetaData, int i) {
        int i2 = i + 1;
        hiStressMetaData.configStressMeasureType((int) d(a(bArr, i, i2)));
        int i3 = i + 2;
        hiStressMetaData.configStressAccFlag((int) d(a(bArr, i2, i3)));
        int i4 = i + 3;
        hiStressMetaData.configStressPpgFlag((int) d(a(bArr, i3, i4)));
        int i5 = 0;
        ArrayList arrayList = new ArrayList(0);
        while (i5 < 12) {
            int i6 = i4 + 4;
            arrayList.add(Float.valueOf(cvx.i(a(bArr, i4, i6))));
            i5++;
            i4 = i6;
        }
        hiStressMetaData.configStressFeature(arrayList);
    }

    private static String a(byte[] bArr, int i, int i2) {
        if (bArr == null || i > i2) {
            LogUtil.h("ParsePeriodRriFileUtil", "byteToString bytes is null");
            return "";
        }
        byte[] bArr2 = new byte[i2 - i];
        int i3 = 0;
        while (i < i2) {
            bArr2[i3] = bArr[i];
            i++;
            i3++;
        }
        return cvx.d(bArr2);
    }

    private static int e(int i) {
        if (i >= 0) {
            return i >> 3;
        }
        throw new IllegalArgumentException("error argument");
    }

    private static void a(List<PeriodRriEntity.RriDataEntity> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Collections.sort(list, new Comparator<PeriodRriEntity.RriDataEntity>() { // from class: keq.4
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(PeriodRriEntity.RriDataEntity rriDataEntity, PeriodRriEntity.RriDataEntity rriDataEntity2) {
                if (rriDataEntity == null || rriDataEntity2 == null) {
                    LogUtil.h("ParsePeriodRriFileUtil", "sortRriData is null");
                    return 0;
                }
                return Long.compare(rriDataEntity.fetchStartTime(), rriDataEntity2.fetchStartTime());
            }
        });
    }

    private static void e(List<HiStressMetaData> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Collections.sort(list, new Comparator<HiStressMetaData>() { // from class: keq.1
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(HiStressMetaData hiStressMetaData, HiStressMetaData hiStressMetaData2) {
                if (hiStressMetaData == null || hiStressMetaData2 == null) {
                    LogUtil.h("ParsePeriodRriFileUtil", "sortPressData is null");
                    return 0;
                }
                return Long.compare(hiStressMetaData.fetchStressEndTime(), hiStressMetaData2.fetchStressEndTime());
            }
        });
    }

    private static long d(String str) {
        try {
            return Long.parseLong(str, 16);
        } catch (NumberFormatException unused) {
            LogUtil.b("ParsePeriodRriFileUtil", "parse fail:", str);
            return 0L;
        }
    }

    public static boolean d(List<HiStressMetaData> list) {
        for (HiStressMetaData hiStressMetaData : list) {
            if (hiStressMetaData == null || !b(hiStressMetaData)) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(HiStressMetaData hiStressMetaData) {
        long fetchStressStartTime = hiStressMetaData.fetchStressStartTime();
        long fetchStressEndTime = hiStressMetaData.fetchStressEndTime();
        long t = HiDateUtil.t(0L);
        if (fetchStressStartTime > fetchStressEndTime || fetchStressEndTime <= 0 || fetchStressStartTime <= 0) {
            LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressStartTime: ", Long.valueOf(fetchStressStartTime));
            return false;
        }
        if (fetchStressStartTime < t) {
            LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressStartTime: ", Long.valueOf(fetchStressStartTime));
            return false;
        }
        int fetchStressAlgorithmVer = hiStressMetaData.fetchStressAlgorithmVer();
        if (fetchStressAlgorithmVer <= 0) {
            LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressAlgorithmVer: ", Integer.valueOf(fetchStressAlgorithmVer));
            return false;
        }
        int fetchStressDevNo = hiStressMetaData.fetchStressDevNo();
        if (fetchStressDevNo < 0) {
            LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressDeviceNo: ", Integer.valueOf(fetchStressDevNo));
            return false;
        }
        int fetchStressScore = hiStressMetaData.fetchStressScore();
        if (fetchStressScore <= 0 || fetchStressScore >= 100) {
            LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressScore: ", Integer.valueOf(fetchStressScore));
            return false;
        }
        int fetchStressGrade = hiStressMetaData.fetchStressGrade();
        if (fetchStressGrade <= 0 || fetchStressGrade > 4) {
            LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressGrade: ", Integer.valueOf(fetchStressGrade));
            return false;
        }
        int fetchStressCalibFlag = hiStressMetaData.fetchStressCalibFlag();
        if (fetchStressCalibFlag != 1 && fetchStressCalibFlag != 0) {
            LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressCalibFlag: ", Integer.valueOf(fetchStressCalibFlag));
            return false;
        }
        return c(hiStressMetaData);
    }

    private static boolean c(HiStressMetaData hiStressMetaData) {
        int fetchStressMeasureType = hiStressMetaData.fetchStressMeasureType();
        if (fetchStressMeasureType < 0 || fetchStressMeasureType > 2) {
            LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressMeasureType: ", Integer.valueOf(fetchStressMeasureType));
            return false;
        }
        int fetchStressAccFlag = hiStressMetaData.fetchStressAccFlag();
        if (fetchStressAccFlag < 0) {
            LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressAccFlag: ", Integer.valueOf(fetchStressAccFlag));
            return false;
        }
        int fetchStressPpgFlag = hiStressMetaData.fetchStressPpgFlag();
        if (fetchStressPpgFlag < 0) {
            LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressPpgFlag: ", Integer.valueOf(fetchStressPpgFlag));
            return false;
        }
        List<Float> fetchStressFeature = hiStressMetaData.fetchStressFeature();
        if (fetchStressFeature != null) {
            int size = fetchStressFeature.size();
            if (size != 12) {
                LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressFeatureSize: ", Integer.valueOf(size));
                return false;
            }
            List<String> fetchStressFeatureAtts = hiStressMetaData.fetchStressFeatureAtts();
            if (fetchStressFeatureAtts != null) {
                int size2 = fetchStressFeatureAtts.size();
                if (size2 == 12) {
                    return true;
                }
                LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressFeatureAttsSize: ", Integer.valueOf(size2));
                return false;
            }
            LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressFeatureAtts is null");
            return false;
        }
        LogUtil.a("ParsePeriodRriFileUtil", "isStressData is false, stressFeature is null");
        return false;
    }
}
