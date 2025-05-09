package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.phoneprocess.SampleSequenceAfterProcess;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionarySequenceInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kbw implements DictionarySequenceInterface {
    private boolean a(long j) {
        return j >= 32;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionarySequenceInterface
    public void readHiHealthData(int i, DeviceInfo deviceInfo, HiDataReadResultListener hiDataReadResultListener) {
        HiDataReadOption c = c(new int[]{i}, deviceInfo, 0L, System.currentTimeMillis(), 1);
        LogUtil.a("DictionarySequenceImpl", "readHiHealthData ", c);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(c, hiDataReadResultListener);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionarySequenceInterface
    public void insertToHiHealth(kbu kbuVar, HiDataOperateListener hiDataOperateListener) {
        if (kbuVar == null || hiDataOperateListener == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        LogUtil.a("DictionarySequenceImpl", "insertToHiHealth sampleSequenceFileData: ", kbuVar.toString());
        d(arrayList, kbuVar);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, hiDataOperateListener);
        if (kbuVar.b() == DicDataTypeUtil.DataType.SLEEP_DETAILS.value()) {
            d(kbuVar);
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionarySequenceInterface
    public kbs genCacheDicInfo(int i, int i2, int i3) {
        kbs kbsVar = new kbs();
        kbsVar.b(i);
        kbsVar.e(i2);
        kbsVar.c(i3);
        return kbsVar;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionarySequenceInterface
    public kbu parseSequenceData(String str) {
        Throwable th;
        FileInputStream fileInputStream;
        kbu kbuVar;
        kbu kbuVar2;
        File file;
        byte[] bArr;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                LogUtil.h("DictionarySequenceImpl", "parseSequenceData path:", str);
                file = new File(str);
            } catch (IOException unused) {
                kbuVar = null;
            }
            if (!CommonUtil.c(file)) {
                LogUtil.h("DictionarySequenceImpl", "Invalid file path");
                IoUtils.e((Closeable) null);
                return null;
            }
            long length = file.length();
            if (!a(length)) {
                LogUtil.h("DictionarySequenceImpl", "Sequence file is wrong. check file header wrong.:", Long.valueOf(length));
                IoUtils.e((Closeable) null);
                return null;
            }
            fileInputStream = FileUtils.openInputStream(file);
            try {
                try {
                    kbuVar2 = new kbu();
                } catch (Throwable th2) {
                    th = th2;
                    IoUtils.e(fileInputStream);
                    throw th;
                }
            } catch (IOException unused2) {
            }
            try {
                bArr = new byte[32];
            } catch (IOException unused3) {
                fileInputStream2 = kbuVar2;
                kbu kbuVar3 = fileInputStream2;
                fileInputStream2 = fileInputStream;
                kbuVar = kbuVar3;
                LogUtil.b("DictionarySequenceImpl", "File processing IOException");
                IoUtils.e(fileInputStream2);
                kbuVar2 = kbuVar;
                LogUtil.a("DictionarySequenceImpl", "parseSequenceData sequenceFileData:", kbuVar2);
                return kbuVar2;
            }
            if (fileInputStream.read(bArr) != 32) {
                LogUtil.h("DictionarySequenceImpl", "Unable to read header data");
                IoUtils.e(fileInputStream);
                return kbuVar2;
            }
            b(bArr, kbuVar2);
            b(fileInputStream, kbuVar2);
            IoUtils.e(fileInputStream);
            LogUtil.a("DictionarySequenceImpl", "parseSequenceData sequenceFileData:", kbuVar2);
            return kbuVar2;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionarySequenceInterface
    public boolean syncNextInfo(kbu kbuVar, kbs kbsVar) {
        int size;
        kbt kbtVar;
        if (kbuVar == null || kbsVar == null) {
            return false;
        }
        if (kbuVar.b() != kbsVar.c()) {
            LogUtil.h("DictionarySequenceImpl", "syncNextInfo dictionaryInfo:", Integer.valueOf(kbuVar.b()), ",cache id:", Integer.valueOf(kbsVar.c()));
            return true;
        }
        if (kbuVar.e() == null || (size = kbuVar.e().size()) <= 0 || (kbtVar = kbuVar.e().get(size - 1)) == null) {
            return false;
        }
        int a2 = kbtVar.a();
        int c = kbtVar.c();
        if (c != 0) {
            a2 = c;
        }
        int b = kbsVar.b();
        LogUtil.a("DictionarySequenceImpl", "endTime:", Integer.valueOf(a2), ",request endTime:", Integer.valueOf(b));
        return a2 >= b;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionarySequenceInterface
    public RequestFileInfo buildSendMessage(kbs kbsVar) {
        RequestFileInfo requestFileInfo = new RequestFileInfo();
        if (kbsVar == null) {
            return requestFileInfo;
        }
        int[] iArr = {kbsVar.d(), kbsVar.b()};
        requestFileInfo.setFileType(22);
        requestFileInfo.setNeedVerify(false);
        requestFileInfo.setTimes(iArr);
        requestFileInfo.setKit(false);
        return requestFileInfo;
    }

    private void d(List<HiHealthData> list, kbu kbuVar) {
        if (kbuVar == null || kbuVar.e() == null) {
            return;
        }
        String d = kcb.d(kbuVar.d());
        LogUtil.a("DictionarySequenceImpl", "setDictionaryToList:", CommonUtil.l(d));
        int b = kbuVar.b();
        int c = kbuVar.c();
        if (c == 2) {
            a(d, list, kbuVar);
        } else if (c == 1) {
            d(d, b, list, kbuVar);
        } else {
            LogUtil.h("DictionarySequenceImpl", "setDictionaryToList healthData fileType error");
        }
    }

    private void a(String str, List<HiHealthData> list, kbu kbuVar) {
        for (kbt kbtVar : kbuVar.e()) {
            HiHealthData e = e(kbuVar.b(), kbtVar.a(), kbtVar.c(), str);
            e.setFieldsValue(kbtVar.h());
            list.add(e);
        }
    }

    private void d(String str, int i, List<HiHealthData> list, kbu kbuVar) {
        SampleSequenceAfterProcess e = cur.e(i);
        for (kbt kbtVar : kbuVar.e()) {
            HiHealthData e2 = e(kbuVar.b(), kbtVar.a(), kbtVar.c(), str);
            e2.setSequenceData(kbtVar.d());
            if (i == DicDataTypeUtil.DataType.SLEEP_DETAILS.value()) {
                e2 = c(e2, kbtVar.h());
            } else {
                e2.setMetaData(kbtVar.h());
            }
            if (e != null && i != DicDataTypeUtil.DataType.SLEEP_DETAILS.value()) {
                e2 = e.onSamplSequenceAfterProcess(e2, kbtVar.b());
            }
            LogUtil.a("DictionarySequenceImpl", "setDictionaryToList healthData:", e2.getMetaData());
            list.add(e2);
        }
    }

    private HiHealthData c(HiHealthData hiHealthData, String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            jSONObject = null;
        }
        try {
            if (new nhv(jSONObject, hiHealthData.getDeviceUuid()).b() == -1) {
                jSONObject.put("validData", 65535L);
            }
        } catch (JSONException unused2) {
            ReleaseLogUtil.c("DictionarySequenceImpl", "metadata jsonException");
            hiHealthData.setMetaData(jSONObject.toString());
            return hiHealthData;
        }
        hiHealthData.setMetaData(jSONObject.toString());
        return hiHealthData;
    }

    private void d(kbu kbuVar) {
        int b = kbuVar.b();
        LogUtil.a("DictionarySequenceImpl", "CoreSleepProcessSequence enter, digitTypeId is ", Integer.valueOf(b));
        SampleSequenceAfterProcess e = cur.e(b);
        String d = kcb.d(kbuVar.d());
        int size = kbuVar.e().size();
        int i = 0;
        String str = "false";
        for (kbt kbtVar : kbuVar.e()) {
            i++;
            HiHealthData e2 = e(kbuVar.b(), kbtVar.a(), kbtVar.c(), d);
            e2.setSequenceData(kbtVar.d());
            e2.setMetaData(kbtVar.h());
            if (i == size) {
                str = "true";
            }
            if (e != null) {
                e.onSamplSequenceAfterProcess(e2, str);
            }
        }
    }

    private HiHealthData e(int i, int i2, int i3, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(i);
        hiHealthData.setDeviceUuid(str);
        long j = i2 * 1000;
        long j2 = i3 * 1000;
        LogUtil.a("DictionarySequenceImpl", " set HiHealthData hiHealthStartTime:", Long.valueOf(j), ",hiHealthEndTime:", Long.valueOf(j2));
        hiHealthData.setStartTime(j);
        hiHealthData.setEndTime(j2);
        return hiHealthData;
    }

    private HiDataReadOption c(int[] iArr, DeviceInfo deviceInfo, long j, long j2, int i) {
        String d = kcb.d(deviceInfo);
        LogUtil.a("DictionarySequenceImpl", "read getHiDataReadOption:", CommonUtil.l(d));
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setDeviceUuid(d);
        hiDataReadOption.setSortOrder(i);
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setCount(1);
        hiDataReadOption.setReadType(2);
        return hiDataReadOption;
    }

    private void b(byte[] bArr, kbu kbuVar) {
        LogUtil.a("DictionarySequenceImpl", "parseHeaderCommon enter");
        if (bArr == null) {
            LogUtil.h("DictionarySequenceImpl", "Header is null");
            return;
        }
        blt.d("DictionarySequenceImpl", bArr, "sequence fileHead: ");
        if (bArr.length != 32) {
            LogUtil.h("DictionarySequenceImpl", "Wrong length of file header");
            return;
        }
        kbuVar.e(CommonUtil.y(d(bArr, 0, 4)));
        kbuVar.a(CommonUtil.w(d(bArr, 4, 8)));
        String d = d(bArr, 8, 10);
        LogUtil.a("DictionarySequenceImpl", "fileTypeString :", d);
        kbuVar.c(CommonUtil.w(d));
        String d2 = d(bArr, 10, 12);
        LogUtil.a("DictionarySequenceImpl", "fileVersionString :", d2);
        kbuVar.b(CommonUtil.w(d2));
        kbuVar.c(cvx.e(d(bArr, 12, 32)));
    }

    private void b(FileInputStream fileInputStream, kbu kbuVar) throws IOException {
        while (true) {
            byte[] bArr = new byte[32];
            int read = fileInputStream.read(bArr);
            if (read != 32) {
                if (read == -1) {
                    LogUtil.h("DictionarySequenceImpl", "End of parsing data");
                    return;
                } else {
                    LogUtil.h("DictionarySequenceImpl", "Profile data length mismatch: ", Integer.valueOf(read));
                    return;
                }
            }
            kbt b = b(bArr, 32);
            if (b == null) {
                LogUtil.h("DictionarySequenceImpl", "sequenceData is null");
                return;
            }
            c(kbuVar, b, fileInputStream);
            a(b, fileInputStream);
            if (kbuVar.e() != null) {
                kbuVar.e().add(b);
            }
            LogUtil.a("DictionarySequenceImpl", "start parsing next");
        }
    }

    private kbt b(byte[] bArr, int i) {
        if (!c(bArr, i)) {
            return null;
        }
        kbt kbtVar = new kbt();
        LogUtil.a("DictionarySequenceImpl", "sequence dataHead:", cvx.d(bArr));
        kbtVar.d(CommonUtil.w(d(bArr, 0, 4)));
        kbtVar.a(CommonUtil.w(d(bArr, 4, 8)));
        kbtVar.e(CommonUtil.w(d(bArr, 8, 9)));
        kbtVar.b(CommonUtil.w(d(bArr, 9, 10)));
        kbtVar.h(CommonUtil.w(d(bArr, 10, 12)));
        kbtVar.c(e(d(bArr, 12, 16)));
        c(bArr, 16, kbtVar);
        LogUtil.a("DictionarySequenceImpl", "parseDataHeadInfo: ", kbtVar.toString());
        return kbtVar;
    }

    private void c(byte[] bArr, int i, kbt kbtVar) {
        kbtVar.e(cvx.e(d(bArr, i, i + 16)));
    }

    private boolean c(byte[] bArr, int i) {
        if (bArr != null && bArr.length == i) {
            return true;
        }
        LogUtil.h("DictionarySequenceImpl", "bytes is error argument");
        return false;
    }

    private void c(kbu kbuVar, kbt kbtVar, FileInputStream fileInputStream) throws IOException {
        if (kbtVar != null && kbtVar.i() == 1) {
            int j = kbtVar.j();
            byte[] bArr = new byte[j];
            int read = fileInputStream.read(bArr);
            if (read != kbtVar.j()) {
                LogUtil.h("DictionarySequenceImpl", "summary data length mismatch SummaryLength:", Integer.valueOf(read));
                return;
            }
            String d = d(bArr, 0, j);
            LogUtil.a("DictionarySequenceImpl", "parseSummaryData format:", Integer.valueOf(kbtVar.g()), ",summaryDataString:", d);
            if (kbtVar.g() == 1) {
                kbtVar.a(cvx.e(d));
            } else if (kbtVar.g() == 2) {
                kbtVar.a(d(kbuVar, d));
            } else if (kbtVar.g() == 3) {
                kbtVar.a(d);
            } else {
                LogUtil.h("DictionarySequenceImpl", "parseSummaryData format exception");
            }
            LogUtil.h("DictionarySequenceImpl", "parseSummaryData summaryData: ", kbtVar.h());
        }
    }

    private String d(kbu kbuVar, String str) {
        cwe cweVar;
        String e;
        try {
            cweVar = new cwl().a(str);
        } catch (cwg unused) {
            LogUtil.b("DictionarySequenceImpl", "parseSummaryData TlvException");
            cweVar = null;
        }
        if (cweVar == null) {
            LogUtil.h("DictionarySequenceImpl", "parseSummaryData tlv tlvFather is null");
            return "";
        }
        List<cwe> a2 = cweVar.a();
        if (a2 == null) {
            LogUtil.h("DictionarySequenceImpl", "parseSummaryData tlv metaDataTlvs is null");
            return "";
        }
        HashMap hashMap = new HashMap(0);
        HashMap<String, Object> hashMap2 = new HashMap<>(0);
        LogUtil.a("DictionarySequenceImpl", "processTlv tlv FileType:", Integer.valueOf(kbuVar.c()));
        if (kbuVar.c() == 2) {
            e = HiJsonUtil.e(hashMap);
        } else {
            a(a2, hashMap2);
            e = HiJsonUtil.e(hashMap2);
        }
        LogUtil.a("DictionarySequenceImpl", "processTlv tlv data:", e);
        return e;
    }

    private void a(List<cwe> list, HashMap<String, Object> hashMap) {
        List<cwe> a2;
        if (list == null) {
            return;
        }
        Iterator<cwe> it = list.iterator();
        while (it.hasNext() && (a2 = it.next().a()) != null) {
            b(a2, hashMap);
        }
    }

    private void b(List<cwe> list, HashMap<String, Object> hashMap) {
        List<cwd> e;
        Iterator<cwe> it = list.iterator();
        while (it.hasNext() && (e = it.next().e()) != null) {
            LogUtil.a("DictionarySequenceImpl", "processSequenceSummaryFieldData headList.size:", Integer.valueOf(e.size()));
            d(e, hashMap);
        }
    }

    private void d(List<cwd> list, HashMap<String, Object> hashMap) {
        String str = null;
        int i = 0;
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            String c = cwdVar.c();
            LogUtil.a("DictionarySequenceImpl", "processSequenceSummaryFieldValue metaDataTlvs-getTag:", Integer.valueOf(w), "metaDataTlvs-getValue:", c);
            if (w == 3) {
                str = a(CommonUtil.w(c));
                LogUtil.a("DictionarySequenceImpl", "parseSequenceSummaryKeyTlv:", str);
            } else if (w == 4) {
                LogUtil.a("DictionarySequenceImpl", "parseSequenceSummaryKeyTlv value format:", str);
                i = CommonUtil.w(c);
            } else if (w == 5) {
                d(i, str, c, hashMap);
            } else {
                LogUtil.a("DictionarySequenceImpl", "no recognize getTag:", Integer.valueOf(w), "metaDataTlvs-getValue:", c);
            }
        }
    }

    private void d(int i, String str, String str2, HashMap<String, Object> hashMap) {
        if (i == 1 || i == 2 || i == 4) {
            hashMap.put(str, Integer.valueOf(b(str2)));
            return;
        }
        if (i == 3) {
            hashMap.put(str, Long.valueOf(d(str2)));
            return;
        }
        if (i == 5) {
            hashMap.put(str, cvx.e(str2));
        } else if (i == 6) {
            hashMap.put(str, Double.valueOf(Double.longBitsToDouble(d(str2))));
        } else {
            LogUtil.a("DictionarySequenceImpl", "parseSequenceSummaryKeyTlv value array");
            hashMap.put(str, str2);
        }
    }

    private int b(String str) {
        int i = -1;
        if (!TextUtils.isEmpty(str) && str.equals("BF800000")) {
            return -1;
        }
        if (CommonUtil.bv()) {
            try {
                i = Integer.parseUnsignedInt(str, 16);
            } catch (NumberFormatException unused) {
                LogUtil.b("DictionarySequenceImpl", "processIntUnsigned NumberFormatException: ", str);
            }
        } else {
            i = Integer.parseUnsignedInt(str, 16);
        }
        LogUtil.a("DictionarySequenceImpl", "processIntUnsigned result:", Integer.valueOf(i));
        return i;
    }

    private long d(String str) {
        long j;
        if (CommonUtil.bv()) {
            try {
                j = Long.parseUnsignedLong(str, 16);
            } catch (NumberFormatException unused) {
                LogUtil.b("DictionarySequenceImpl", "processLongUnsigned NumberFormatException: ", str);
                j = -1;
            }
        } else {
            j = Long.parseUnsignedLong(str, 16);
        }
        LogUtil.a("DictionarySequenceImpl", "processLongUnsigned result:", Long.valueOf(j));
        return j;
    }

    private static String a(int i) {
        HiHealthDictField b = HiHealthDictManager.d(BaseApplication.getContext()).b(i);
        if (b == null) {
            LogUtil.a("DictionarySequenceImpl", "getKeyStrFromHiHealth dictField is null");
            return "";
        }
        return b.a();
    }

    private void a(kbt kbtVar, FileInputStream fileInputStream) throws IOException {
        if (kbtVar == null) {
            return;
        }
        if (kbtVar.e() <= 0) {
            LogUtil.h("DictionarySequenceImpl", "parseDetailData detailLength is 0 or Less than 0");
            return;
        }
        try {
            int e = kbtVar.e();
            byte[] bArr = new byte[e];
            int read = fileInputStream.read(bArr);
            if (read != kbtVar.e()) {
                LogUtil.h("DictionarySequenceImpl", "Raw data length mismatch rawDataLength:", Integer.valueOf(read));
            }
            LogUtil.a("DictionarySequenceImpl", "parseDetailData");
            String d = d(bArr, 0, e);
            LogUtil.a("DictionarySequenceImpl", "parseDetailData dataString: ", d);
            kbtVar.d(d);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("DictionarySequenceImpl", "parseDetailData OutOfMemoryError detailLength:", Integer.valueOf(kbtVar.e()));
        }
    }

    private static int e(String str) {
        int w = CommonUtil.w(str);
        if (w >= Integer.MAX_VALUE || w <= 0) {
            LogUtil.h("DictionarySequenceImpl", "rawDataLength is invalid:", Integer.valueOf(w));
            return w;
        }
        if (w <= 52428800) {
            return w;
        }
        LogUtil.h("DictionarySequenceImpl", "rawDataLength is too large:", 0);
        return 0;
    }

    private String d(byte[] bArr, int i, int i2) {
        if (bArr == null || i > i2) {
            LogUtil.h("DictionarySequenceImpl", "byteToString bytes is null");
            return "";
        }
        if (i < 0) {
            LogUtil.h("DictionarySequenceImpl", "Start less than zero");
            return "";
        }
        if (i2 > bArr.length) {
            LogUtil.h("DictionarySequenceImpl", "End is too large");
            return "";
        }
        int i3 = i2 - i;
        try {
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i, bArr2, 0, i3);
            return cvx.d(bArr2);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("DictionarySequenceImpl", "byteToString OutOfMemoryError Length:", Integer.valueOf(i3));
            return "";
        }
    }
}
