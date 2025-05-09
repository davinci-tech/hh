package defpackage;

import com.huawei.health.devicemgr.api.phoneprocess.SamplePointAfterProcess;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryActionInterface;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.spn;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kbz implements DictionaryActionInterface {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryActionInterface
    public void readHiHealthData(int i, DeviceInfo deviceInfo, HiDataReadResultListener hiDataReadResultListener) {
        HiDataReadOption b = b(new int[]{i}, deviceInfo, 0L, System.currentTimeMillis(), 1);
        LogUtil.a("DictionaryActionImpl", "readHiHealthData ", b);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(b, hiDataReadResultListener);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryActionInterface
    public void insertToHiHealth(kbn kbnVar, HiDataOperateListener hiDataOperateListener) {
        ArrayList arrayList = new ArrayList(16);
        d(arrayList, kbnVar);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, hiDataOperateListener);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryActionInterface
    public kbo genCacheDicInfo(int i, long j, long j2) {
        kbo kboVar = new kbo();
        kboVar.d(i);
        kboVar.a(j);
        kboVar.d(j2);
        kboVar.a(1);
        kboVar.c(3);
        return kboVar;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryActionInterface
    public kbn parsePointData(cwe cweVar) {
        kbn kbnVar = new kbn();
        d(kbnVar, cweVar);
        a(kbnVar, cweVar);
        LogUtil.a("DictionaryActionImpl", "parsePointData dictionaryInfo:", kbnVar);
        return kbnVar;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryActionInterface
    public boolean syncNextInfo(kbn kbnVar, kbo kboVar) {
        int size;
        kbp kbpVar;
        if (kbnVar == null || kboVar == null) {
            return false;
        }
        if (kbnVar.c() != kboVar.d()) {
            LogUtil.h("DictionaryActionImpl", "syncNextInfo dictionaryInfo:", Integer.valueOf(kbnVar.c()), ",cache id:", Integer.valueOf(kboVar.d()));
            return false;
        }
        if (kbnVar.a() == null || (size = kbnVar.a().size()) <= 0 || (kbpVar = kbnVar.a().get(size - 1)) == null) {
            return false;
        }
        long e = kbpVar.e();
        long a2 = kbpVar.a();
        if (a2 != 0) {
            e = a2;
        }
        long a3 = kboVar.a();
        LogUtil.a("DictionaryActionImpl", "endTime:", Long.valueOf(e), ",request endTime:", Long.valueOf(a3));
        return e >= a3;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryActionInterface
    public spn buildSendMessage(kbo kboVar) {
        if (kboVar == null) {
            return null;
        }
        int c = kboVar.c();
        int d = kboVar.d();
        long e = kboVar.e();
        long a2 = kboVar.a();
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(HiAnalyticsConstant.KeyAndValue.NUMBER_01);
        String e2 = cvx.e(c);
        String d2 = cvx.d(e2.length() / 2);
        stringBuffer.append(cvx.e(1));
        stringBuffer.append(d2);
        stringBuffer.append(e2);
        String e3 = cvx.e(d);
        String d3 = cvx.d(e3.length() / 2);
        stringBuffer.append(cvx.e(2));
        stringBuffer.append(d3);
        stringBuffer.append(e3);
        String c2 = cvx.c(e);
        String d4 = cvx.d(c2.length() / 2);
        stringBuffer.append(cvx.e(5));
        stringBuffer.append(d4);
        stringBuffer.append(c2);
        String c3 = cvx.c(a2);
        String d5 = cvx.d(c3.length() / 2);
        stringBuffer.append(cvx.e(6));
        stringBuffer.append(d5);
        stringBuffer.append(c3);
        String e4 = cvx.e(1);
        String d6 = cvx.d(e4.length() / 2);
        stringBuffer.append(cvx.e(13));
        stringBuffer.append(d6);
        stringBuffer.append(e4);
        spn.b bVar = new spn.b();
        bVar.c(cvx.a(stringBuffer.toString()));
        LogUtil.a("DictionaryActionImpl", "command:", stringBuffer.toString());
        return bVar.e();
    }

    private void d(kbn kbnVar, cwe cweVar) {
        List<cwd> e;
        if (cweVar == null || (e = cweVar.e()) == null) {
            return;
        }
        for (cwd cwdVar : e) {
            String c = cwdVar.c();
            int w = CommonUtil.w(cwdVar.e());
            LogUtil.a("DictionaryActionImpl", "TLV-getTag:", Integer.valueOf(w), "TLV-getValue:", c);
            if (w == 2) {
                kbnVar.c(CommonUtil.w(c));
                return;
            }
            LogUtil.a("DictionaryActionImpl", "TLV-getTag:", Integer.valueOf(w));
        }
    }

    private void a(kbn kbnVar, cwe cweVar) {
        if (cweVar == null) {
            return;
        }
        List<cwe> a2 = cweVar.a();
        LogUtil.a("DictionaryActionImpl", "tlvs.size():", Integer.valueOf(a2.size()));
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it = a2.iterator();
        while (it.hasNext()) {
            List<cwe> a3 = it.next().a();
            LogUtil.a("DictionaryActionImpl", "dataTlvs.size():", Integer.valueOf(a3.size()));
            for (cwe cweVar2 : a3) {
                HashMap<Integer, String> hashMap = new HashMap<>(0);
                HashMap<Integer, Double> hashMap2 = new HashMap<>(0);
                kbp kbpVar = new kbp();
                d(kbpVar, cweVar2.e());
                c(cweVar2.a(), hashMap2, hashMap);
                kbpVar.c(hashMap2);
                kbpVar.a(hashMap);
                arrayList.add(kbpVar);
            }
        }
        kbnVar.c(arrayList);
    }

    private void d(kbp kbpVar, List<cwd> list) {
        if (list == null) {
            return;
        }
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            String c = cwdVar.c();
            LogUtil.a("DictionaryActionImpl", "infoDataTlv-Tag:", Integer.valueOf(w), "infoDataTlv-Value:", c);
            if (w == 5) {
                kbpVar.b(CommonUtil.y(c));
            } else if (w == 6) {
                kbpVar.d(CommonUtil.y(c));
            } else if (w == 12) {
                kbpVar.e(CommonUtil.y(c));
            } else {
                LogUtil.a("DictionaryActionImpl", "no recognize Tag:", Integer.valueOf(w), "infoDataTlv-Value:", c);
            }
        }
    }

    private void c(List<cwe> list, HashMap<Integer, Double> hashMap, HashMap<Integer, String> hashMap2) {
        List<cwe> a2;
        if (list == null) {
            return;
        }
        Iterator<cwe> it = list.iterator();
        while (it.hasNext() && (a2 = it.next().a()) != null) {
            d(a2, hashMap, hashMap2);
        }
    }

    private void d(List<cwe> list, HashMap<Integer, Double> hashMap, HashMap<Integer, String> hashMap2) {
        List<cwd> e;
        Iterator<cwe> it = list.iterator();
        while (it.hasNext() && (e = it.next().e()) != null) {
            LogUtil.a("DictionaryActionImpl", "headList.size:", Integer.valueOf(e.size()));
            a(e, hashMap, hashMap2);
        }
    }

    private void a(List<cwd> list, HashMap<Integer, Double> hashMap, HashMap<Integer, String> hashMap2) {
        int i = -1;
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            String c = cwdVar.c();
            LogUtil.a("DictionaryActionImpl", "metaDataTlvs-getTag:", Integer.valueOf(w), "metaDataTlvs-getValue:", c, ",long value:", Long.valueOf(CommonUtil.y(c)));
            if (w == 9) {
                i = CommonUtil.w(c);
            } else if (w == 10) {
                if (i != -1) {
                    hashMap.put(Integer.valueOf(i), Double.valueOf(Double.longBitsToDouble(d(c))));
                }
            } else if (w != 11) {
                LogUtil.a("DictionaryActionImpl", "no recognize getTag:", Integer.valueOf(w), "metaDataTlvs-getValue:", c);
            } else if (i != -1) {
                hashMap2.put(Integer.valueOf(i), cvx.e(c));
            }
        }
    }

    private long d(String str) {
        long j;
        try {
            try {
                return Long.parseLong(str, 16);
            } catch (NumberFormatException unused) {
                LogUtil.b("DictionaryActionImpl", "hexStringToLong NumberFormatException");
                j = -1;
                LogUtil.a("DictionaryActionImpl", "result:", Long.valueOf(j));
                return j;
            }
        } catch (NumberFormatException unused2) {
            j = Long.parseUnsignedLong(str, 16);
            LogUtil.a("DictionaryActionImpl", "result:", Long.valueOf(j));
            return j;
        }
    }

    private HiDataReadOption b(int[] iArr, DeviceInfo deviceInfo, long j, long j2, int i) {
        String d = kcb.d(deviceInfo);
        LogUtil.a("DictionaryActionImpl", "read getHiDataReadOption:", CommonUtil.l(d));
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

    private HiHealthData c(int i, long j, long j2, long j3, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(i);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setStartTime(j);
        if (j2 == 0) {
            hiHealthData.setEndTime(j);
        } else {
            hiHealthData.setEndTime(j2);
        }
        if ((i == DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE.value() || i == DicDataTypeUtil.DataType.EMOTION_SET.value()) && j3 > 0) {
            hiHealthData.setModifiedTime(j3);
        }
        return hiHealthData;
    }

    private void d(List<HiHealthData> list, kbn kbnVar) {
        if (kbnVar == null) {
            LogUtil.h("DictionaryActionImpl", "In setDictionaryToList, dictionaryInfo is null");
            return;
        }
        String d = kcb.d(kbnVar.b());
        LogUtil.a("DictionaryActionImpl", "setDictionaryToList:", CommonUtil.l(d));
        if (kbnVar.a() == null) {
            LogUtil.h("DictionaryActionImpl", "setDictionaryToList, dictionaryInfo.getPointStruct() is null");
            return;
        }
        SamplePointAfterProcess c = cuq.c(kbnVar.c());
        for (kbp kbpVar : kbnVar.a()) {
            HiHealthData c2 = c(kbnVar.c(), kbpVar.e(), kbpVar.a(), kbpVar.b(), d);
            c2.setFieldsValue(HiJsonUtil.e(kbpVar.c()));
            c2.setFieldsMetaData(HiJsonUtil.e(kbpVar.d()));
            if (c != null) {
                LogUtil.a("DictionaryActionImpl", "setDictionaryToList SamplePointAfterProcess enter");
                c2 = c.onSamplePointAfterProcess(c2, "");
            }
            LogUtil.a("DictionaryActionImpl", "setDictionaryToList healthData:", c2.getFieldsMetaData());
            list.add(c2);
        }
    }
}
