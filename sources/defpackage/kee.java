package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.HeartRateRaiseRemindAlarm;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.model.HiHearRateUpMetaData;
import com.huawei.hihealth.data.model.HiHeartRateData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kee {
    public static kee c() {
        return e.e;
    }

    static class e {
        private static final kee e = new kee();
    }

    public void a(DeviceInfo deviceInfo) {
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "enter getHeartRateRaiseAlarm");
        if (TextUtils.isEmpty(deviceIdentify)) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "getHeartRateRaiseAlarm deviceIdentify is null");
            return;
        }
        final DeviceCapability a2 = keg.a(deviceIdentify);
        if (a2 == null) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "getHeartRateRaiseAlarm deviceCapability is null");
        } else if (!a2.isSupportGetHeartRateRaiseAlarmNumber()) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "isSupportGetHeartRateRaiseAlarmNumber is not support");
        } else {
            jrq.b("AlarmData_HeartRateAlarmManager", new Runnable() { // from class: kee.1
                @Override // java.lang.Runnable
                public void run() {
                    kee.this.e(a2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceCapability deviceCapability) {
        kem c = kem.c(kdz.b());
        long c2 = c.c();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "lastStatusTime:", Long.valueOf(c2), "endTime:", Long.valueOf(currentTimeMillis));
        long j = c2 - currentTimeMillis;
        if (j > k.b.l || c2 == 0) {
            c2 = keg.a(currentTimeMillis - k.b.l);
            a(c2);
        } else if (c2 >= currentTimeMillis && j <= 300) {
            ReleaseLogUtil.c("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "syncStatusPoint lastStatusTime is not correct.");
            c2 = currentTimeMillis - 61;
        } else if (j > 300) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "getHeartRateRaiseAlarm lastStatusTime is not correct and need write back.");
            c2 = currentTimeMillis - 61;
            c.b(c2);
        } else {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "getHeartRateRaiseAlarm unknown");
        }
        keh.b(c2, currentTimeMillis, true);
        if (deviceCapability.isSupportHeartRateDownAlarm()) {
            keh.b(c2, currentTimeMillis, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        kem.c(kdz.b()).b(j);
    }

    public void e(byte[] bArr) {
        cwe a2;
        int e2;
        ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "enter processHeartRateRemindAlarm");
        String d = cvx.d(bArr);
        HeartRateRaiseRemindAlarm heartRateRaiseRemindAlarm = new HeartRateRaiseRemindAlarm();
        int i = 0;
        if (d != null && d.length() > 4) {
            try {
                a2 = new cwl().a(d.substring(4, d.length()));
                e2 = e(a2.e());
            } catch (cwg e3) {
                e = e3;
            }
            try {
                ArrayList arrayList = new ArrayList(16);
                heartRateRaiseRemindAlarm.setDataList(arrayList);
                List<cwe> a3 = a2.a();
                if (bkz.e(a3)) {
                    ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "tlvFatherList is null or size is zero");
                    return;
                }
                LogUtil.a("AlarmData_HeartRateAlarmManager", "5.7.30 tlvFatherList.size() :", Integer.valueOf(a3.size()));
                Iterator<cwe> it = a3.iterator();
                while (it.hasNext()) {
                    List<cwe> a4 = it.next().a();
                    if (bkz.e(a4)) {
                        ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "childTLVFatherList is null or size is zero");
                        return;
                    } else {
                        LogUtil.c("AlarmData_HeartRateAlarmManager", "5.7.30 childTLVFatherList.size() :", Integer.valueOf(a4.size()));
                        e(a4, arrayList);
                    }
                }
                LogUtil.a("AlarmData_HeartRateAlarmManager", "5.7.30 parse end :", heartRateRaiseRemindAlarm.toString());
                i = e2;
            } catch (cwg e4) {
                e = e4;
                i = e2;
                ReleaseLogUtil.c("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "processHeartRateRemindAlarm Exception ", ExceptionUtils.d(e));
                e(heartRateRaiseRemindAlarm);
                d(heartRateRaiseRemindAlarm, i);
            }
        } else {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "5.7.30 get wrong command.");
        }
        e(heartRateRaiseRemindAlarm);
        d(heartRateRaiseRemindAlarm, i);
    }

    private int e(List<cwd> list) {
        if (list == null || list.size() <= 0) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "processGetHeartRateAlarmType tlvList is null or size is zero");
            return 0;
        }
        LogUtil.a("AlarmData_HeartRateAlarmManager", "processGetHeartRateAlarmType");
        while (true) {
            int i = 0;
            for (cwd cwdVar : list) {
                int w = CommonUtil.w(cwdVar.e());
                if (w != 14) {
                    if (w == 127) {
                        ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "5.7.30 get error code.");
                    } else {
                        ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "processGetHeartRateAlarmType default");
                    }
                } else if (CommonUtil.w(cwdVar.c()) == 1) {
                    LogUtil.a("AlarmData_HeartRateAlarmManager", "heartRateAlarmType down");
                    i = 1;
                }
            }
            return i;
            LogUtil.a("AlarmData_HeartRateAlarmManager", "heartRateAlarmType raise");
        }
    }

    private void e(List<cwe> list, List<HeartRateRaiseRemindAlarm.Entity> list2) {
        for (cwe cweVar : list) {
            HeartRateRaiseRemindAlarm.Entity entity = new HeartRateRaiseRemindAlarm.Entity();
            a(cweVar.a(), entity);
            List<cwd> e2 = cweVar.e();
            if (bkz.e(e2)) {
                ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "childTlv is null or size is zero");
                return;
            }
            Iterator<cwd> it = e2.iterator();
            while (true) {
                if (it.hasNext()) {
                    cwd next = it.next();
                    int w = CommonUtil.w(next.e());
                    if (w != 5) {
                        if (w != 6) {
                            if (w != 7) {
                                if (w != 8) {
                                    if (w == 15) {
                                        entity.setThreshold(CommonUtil.w(next.c()));
                                    } else {
                                        ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "receive wrong info.This entity is error.");
                                        break;
                                    }
                                } else {
                                    entity.setMaxNumber(CommonUtil.w(next.c()));
                                }
                            } else {
                                entity.setMinNumber(CommonUtil.w(next.c()));
                            }
                        } else {
                            entity.setEndTime(CommonUtil.y(next.c()));
                        }
                    } else {
                        entity.setStartTime(CommonUtil.y(next.c()));
                    }
                } else {
                    LogUtil.a("AlarmData_HeartRateAlarmManager", "entity to string:", entity.toString());
                    list2.add(entity);
                    break;
                }
            }
        }
    }

    private void a(List<cwe> list, HeartRateRaiseRemindAlarm.Entity entity) {
        if (list == null || list.size() <= 0) {
            return;
        }
        LogUtil.a("AlarmData_HeartRateAlarmManager", "5.7.30fatherTlv.size() :", Integer.valueOf(list.size()));
        for (cwe cweVar : list) {
            ArrayList<HiHeartRateData> arrayList = new ArrayList<>(10);
            a(cweVar.a(), arrayList);
            entity.setRateData(arrayList);
        }
    }

    private void a(List<cwe> list, ArrayList<HiHeartRateData> arrayList) {
        if (list == null || list.size() <= 0) {
            return;
        }
        LogUtil.a("AlarmData_HeartRateAlarmManager", "pointStructTlv.size() :", Integer.valueOf(list.size()));
        for (cwe cweVar : list) {
            HiHeartRateData hiHeartRateData = new HiHeartRateData();
            c(cweVar.e(), hiHeartRateData);
            LogUtil.a("AlarmData_HeartRateAlarmManager", "rateData to String:", hiHeartRateData.toString());
            arrayList.add(hiHeartRateData);
        }
    }

    private void c(List<cwd> list, HiHeartRateData hiHeartRateData) {
        if (list == null || list.size() <= 0) {
            return;
        }
        LogUtil.a("AlarmData_HeartRateAlarmManager", "detailTlv.size() :", Integer.valueOf(list.size()));
        for (cwd cwdVar : list) {
            try {
                int parseInt = Integer.parseInt(cwdVar.e(), 16);
                if (parseInt == 11) {
                    hiHeartRateData.setTimestamp(Long.parseLong(cwdVar.c(), 16) * 1000);
                } else if (parseInt == 12) {
                    hiHeartRateData.setHeartRate(Integer.parseInt(cwdVar.c(), 16));
                } else {
                    ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "receive wrong info.pointStructChildTlv is error.");
                }
            } catch (NumberFormatException e2) {
                ReleaseLogUtil.c("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "processDetailTlv Exception ", ExceptionUtils.d(e2));
            }
        }
    }

    private void e(HeartRateRaiseRemindAlarm heartRateRaiseRemindAlarm) {
        LogUtil.a("AlarmData_HeartRateAlarmManager", "enter heartRateRaiseAlarmNumberSortByTime");
        if (heartRateRaiseRemindAlarm != null) {
            if (heartRateRaiseRemindAlarm.getDataList() != null && heartRateRaiseRemindAlarm.getDataList().size() > 1) {
                Collections.sort(heartRateRaiseRemindAlarm.getDataList());
                LogUtil.a("AlarmData_HeartRateAlarmManager", "sort by start time end.");
                return;
            } else {
                ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "data list is empty or size is 1");
                return;
            }
        }
        ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "heartRateRaiseAlarmNumberSortByTime value is null.");
    }

    private void d(HeartRateRaiseRemindAlarm heartRateRaiseRemindAlarm, int i) {
        LogUtil.a("AlarmData_HeartRateAlarmManager", "enter saveOnData.");
        if (heartRateRaiseRemindAlarm != null && heartRateRaiseRemindAlarm.getDataList() != null && heartRateRaiseRemindAlarm.getDataList().size() > 0) {
            List<HeartRateRaiseRemindAlarm.Entity> dataList = heartRateRaiseRemindAlarm.getDataList();
            DeviceInfo e2 = keg.e("AlarmData_HeartRateAlarmManager");
            if (e2 != null) {
                keg.d(e2);
                String e3 = keg.e();
                if (dataList == null) {
                    ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "5.7.30 saveOnData dataInfoList is null.");
                    return;
                }
                ArrayList arrayList = new ArrayList(10);
                for (int i2 = 0; i2 < dataList.size(); i2++) {
                    HiHearRateUpMetaData hiHearRateUpMetaData = new HiHearRateUpMetaData();
                    HeartRateRaiseRemindAlarm.Entity entity = dataList.get(i2);
                    hiHearRateUpMetaData.setMinHeartRate(entity.getMinNumber());
                    hiHearRateUpMetaData.setMaxHeartRate(entity.getMaxNumber());
                    hiHearRateUpMetaData.setThreshold(entity.getThreshold());
                    if (entity.getRateData() != null && entity.getRateData().size() != 0) {
                        hiHearRateUpMetaData.setDetails(entity.getRateData());
                    }
                    HiHealthData hiHealthData = new HiHealthData();
                    if (i == 0) {
                        hiHealthData.setType(2101);
                    } else {
                        hiHealthData.setType(2102);
                    }
                    hiHealthData.setTimeInterval(entity.getStartTime() * 1000, entity.getEndTime() * 1000);
                    hiHealthData.setMetaData(HiJsonUtil.e(hiHearRateUpMetaData));
                    hiHealthData.setDeviceUuid(e3);
                    arrayList.add(hiHealthData);
                }
                LogUtil.a("AlarmData_HeartRateAlarmManager", "dataList :", arrayList.toString());
                HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
                hiDataInsertOption.setDatas(arrayList);
                e(hiDataInsertOption, dataList.get(dataList.size() - 1).getEndTime() + 1);
                return;
            }
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "5.7.30 get deviceInfo fail.Save data fail.");
            return;
        }
        ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "no data save on data.");
    }

    private void e(HiDataInsertOption hiDataInsertOption, final long j) {
        LogUtil.a("AlarmData_HeartRateAlarmManager", "insertData:", hiDataInsertOption.toString());
        HiHealthNativeApi.a(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: kee.5
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if (obj == null) {
                    ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "insertData object is null");
                    return;
                }
                LogUtil.a("AlarmData_HeartRateAlarmManager", "5.7.30 set heart rate raise remind alarm; errorCode :", Integer.valueOf(i), "object :", HiJsonUtil.e(obj));
                if (i == 0) {
                    ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "save success.updateTime is:", Long.valueOf(j));
                    kee.this.a(j);
                } else {
                    ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_HeartRateAlarmManager", "save heart rate raise remind alarm fail.");
                }
            }
        });
        keg.c();
    }
}
