package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.data.constant.HiErrorCode;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.HiDataOperation;
import com.huawei.hihealthservice.auth.HiAuthException;
import com.huawei.hihealthservice.auth.HiAuthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ifo extends HiDataOperation {
    private static volatile ExecutorService b;

    /* renamed from: a, reason: collision with root package name */
    private int f13343a;
    private int c;
    private int d;
    private final ArrayList<Object> e;
    private final int[] f;
    private IDataOperateListener g;
    private HiDataInsertOption h;
    private List<HiHealthData> i;
    private ikv j;
    private String l;
    private int o;

    @Override // com.huawei.hihealthservice.HiDataOperation
    public boolean isMonitorThread() {
        return true;
    }

    ifo(Context context) {
        super(context);
        this.f13343a = 2;
        this.f = new int[]{0};
        ArrayList<Object> arrayList = new ArrayList<>(10);
        this.e = arrayList;
        this.i = new ArrayList(10);
        arrayList.add(HiErrorCode.d(0));
    }

    public boolean a(HiDataInsertOption hiDataInsertOption, IDataOperateListener iDataOperateListener, boolean z, boolean z2) throws RemoteException {
        if (hiDataInsertOption == null) {
            ReleaseLogUtil.d("HiH_HiDataInsOp", "initialize insertOption is null ");
            this.f[0] = 7;
            igb.b(iDataOperateListener, 7, this.e);
            return false;
        }
        if (!d(hiDataInsertOption)) {
            ReleaseLogUtil.d("HiH_HiDataInsOp", "initialize processDictSportSequence is false ");
            this.f[0] = 7;
            igb.b(iDataOperateListener, 7, this.e);
            return false;
        }
        String a2 = ivw.a(this.mContext);
        this.l = c(hiDataInsertOption, a2);
        this.h = hiDataInsertOption;
        this.g = iDataOperateListener;
        this.c = igd.b().d(this.l);
        this.d = igd.b().b(a2);
        if ((z2 && !d()) || !b(z)) {
            return false;
        }
        this.o = igm.e().d();
        this.j = igd.b().c(this.l, this.c);
        this.f13343a = !z ? 1 : 0;
        return true;
    }

    private boolean d(HiDataInsertOption hiDataInsertOption) {
        List<HiHealthData> datas = hiDataInsertOption.getDatas();
        if (HiCommonUtil.d(datas)) {
            return false;
        }
        for (HiHealthData hiHealthData : datas) {
            if (hiHealthData.getType() == 30001) {
                try {
                    int d = CommonUtil.d(new JSONObject(hiHealthData.getMetaData()), BleConstants.SPORT_TYPE);
                    hiHealthData.setSubType(d);
                    int type = hiHealthData.getType() + d;
                    if (HiHealthDictManager.d(this.mContext).l(type)) {
                        hiHealthData.setType(type);
                    }
                    ReleaseLogUtil.e("HiH_HiDataInsOp", "sportType = ", Integer.valueOf(d), ", after process type is ", Integer.valueOf(type));
                } catch (JSONException e) {
                    ReleaseLogUtil.d("HiH_HiDataInsOp", "processDictSportSequence JSONException!, e is ", e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    private String c(HiDataInsertOption hiDataInsertOption, String str) {
        String packageName = hiDataInsertOption.getPackageName();
        if (packageName != null) {
            str = packageName;
        }
        if (!"com.huawei.bone".equals(str) || koq.b(hiDataInsertOption.getDatas())) {
            return str;
        }
        HiHealthData hiHealthData = hiDataInsertOption.getDatas().get(0);
        return (HiHealthDataType.e(hiHealthData.getType()) != HiHealthDataType.Category.SEQUENCE || hiHealthData.getType() >= 30999) ? str : HuaweiHealth.b();
    }

    private boolean b(boolean z) {
        try {
            this.i = iwn.d(this.h, z);
            return true;
        } catch (iwt e) {
            ReleaseLogUtil.c("HiH_HiDataInsOp", "validInsertOption HiValidException = ", e.getMessage());
            this.f[0] = 3;
            this.e.add("Please check the data validity according to the requirement of HiHealth platform ! " + e.getMessage());
            igb.b(this.g, 3, this.e);
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(5);
            linkedHashMap.put("validInsertStatus", "fail " + e.getMessage());
            ivz.d(BaseApplication.getContext()).e(OperationKey.HEALTH_DATA_INSERT_VALID_FAIL_2129013.value(), linkedHashMap, false);
            return false;
        }
    }

    private boolean d() {
        int i = this.d;
        if (i == -1) {
            ReleaseLogUtil.d("HiH_HiDataInsOp", "initialize appType is invalid errorCode is ", 17);
            igb.b(this.g, 17, this.e);
            return false;
        }
        if (i == 0) {
            return true;
        }
        this.f[0] = igm.e().c(this.c);
        int i2 = this.f[0];
        if (i2 != 0) {
            LogUtil.h("HiH_HiDataInsOp", "insertHiHealthData() errorCode is ", Integer.valueOf(i2));
            this.e.add(HiErrorCode.d(this.f[0]));
            igb.b(this.g, this.f[0], this.e);
            return false;
        }
        try {
            HiAuthManager.getInstance().checkInsertAuth(this.c, igm.e().d(), this.h.getDatas());
            return true;
        } catch (HiAuthException e) {
            ReleaseLogUtil.c("HiH_HiDataInsOp", "insertHiHealthData() HiAuthException e = ", LogAnonymous.b((Throwable) e), " appID = ", Integer.valueOf(this.c));
            this.f[0] = 8;
            this.e.add(HiErrorCode.d(this.f[0]) + e.getMessage());
            igb.b(this.g, this.f[0], this.e);
            return false;
        }
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        IDataOperateListener iDataOperateListener;
        int i;
        LogUtil.c("HiH_HiDataInsOp", "execute callingAppID = ", Integer.valueOf(this.c), ", ownerAppId=", Integer.valueOf(this.c), ", ownerUserId=", Integer.valueOf(this.o), ", dataType=", Integer.valueOf(this.f13343a), ", valid time = ", Long.valueOf(System.currentTimeMillis() - this.mStartTime));
        try {
            try {
                if (c()) {
                    ivg.c().c(this.h.getDatas(), e());
                } else {
                    d(e());
                }
                iDataOperateListener = this.g;
                i = this.f[0];
            } catch (Exception e) {
                ReleaseLogUtil.c("HiH_HiDataInsOp", "execute Exception=", LogAnonymous.b((Throwable) e));
                this.f[0] = 2;
                this.e.add(HiErrorCode.d(this.f[0]) + " insert exception");
                iDataOperateListener = this.g;
                i = this.f[0];
            }
            igb.b(iDataOperateListener, i, this.e);
            ReleaseLogUtil.e("HiH_HiDataInsOp", "execEndTtlTm=", Long.valueOf(System.currentTimeMillis() - this.mStartTime));
        } catch (Throwable th) {
            igb.b(this.g, this.f[0], this.e);
            throw th;
        }
    }

    private ikv e() throws RemoteException {
        if (this.j == null) {
            this.j = igd.b().a(this.l);
        }
        return this.j;
    }

    private void d(ikv ikvVar) {
        List<HiHealthData> datas = this.h.getDatas();
        if (datas == null || datas.size() <= 0 || datas.get(0) == null) {
            ReleaseLogUtil.d("HiH_HiDataInsOp", "hiHealthDatas is null or hiHealthDatas.size() <= ", 0, " or hiHealthDatas.get(DEFAULT_INDEX) is null");
            return;
        }
        HiHealthData hiHealthData = datas.get(0);
        int type = hiHealthData.getType();
        ReleaseLogUtil.e("HiH_HiDataInsOp", "insHiHlhDt Sz=", Integer.valueOf(datas.size()), ",app=", Integer.valueOf(this.c), ",1DtTp=", Integer.valueOf(type), ",pkgNm=", this.l, ",wStatTp=", Integer.valueOf(this.h.getWriteStatType()));
        if (type != 40002) {
            ism.f().p();
        }
        if (type == 40054) {
            isq.c(this.h, datas, 40054);
        }
        ikv a2 = a(ikvVar, hiHealthData);
        isf a3 = isf.a(this.mContext);
        this.f[0] = a3.bulkSaveDetailHiHealthData(datas, a2, this.h.getWriteStatType());
        if (this.d != -1 || datas.get(0).getType() == 2200) {
            a3.prepareRealTimeHealthDataStat(datas);
            a3.doRealTimeHealthDataStat();
        }
        if (this.f[0] != 10) {
            ivg.c().c(datas, ikvVar);
        }
        igd.b().c(datas, ikvVar, ism.f(), a());
        if (!HiCommonUtil.d(this.i)) {
            this.f[0] = 27;
            this.e.add(HiJsonUtil.e(this.i));
            return;
        }
        int i = this.f[0];
        if (i != 0) {
            Object d = HiErrorCode.d(i);
            ReleaseLogUtil.d("HiH_HiDataInsOp", "insHiHlhDtSaveDt FailCode=", Integer.valueOf(this.f[0]), ",errorMessage = ", HiErrorCode.d(this.f[0]));
            this.e.add("bulkSaveDetailHiHealthData fail " + d);
            return;
        }
        this.e.add(true);
    }

    private ikv a(ikv ikvVar, HiHealthData hiHealthData) {
        ikv e;
        int c = igm.e().c();
        if (hiHealthData.getType() >= 901 && hiHealthData.getType() <= 906) {
            e = ikr.b(this.mContext).e(c, c, hiHealthData.getOwnerId(), hiHealthData.getDeviceUuid());
        } else {
            e = ikr.b(this.mContext).e(ikvVar.e(), c, hiHealthData.getOwnerId(), hiHealthData.getDeviceUuid());
        }
        if (e == null) {
            ReleaseLogUtil.d("HiH_HiDataInsOp", "getHiHealthContext insertContext is null");
            return e;
        }
        if (this.d != -1) {
            e.c(iks.e().a(e.g()));
            e.c(0);
            HiDeviceInfo a2 = ijf.d(this.mContext).a(e.d());
            if (a2 != null) {
                e.c(a2.getDeviceType());
            }
        } else {
            ArrayList arrayList = new ArrayList(10);
            arrayList.add(Integer.valueOf(e.b()));
            e.c(arrayList);
            e.c(0);
        }
        if (HiHealthDataType.e(hiHealthData.getType()) != HiHealthDataType.Category.BUSINESS) {
            return e;
        }
        ikv a3 = ikr.b(this.mContext).a(0, this.o, 0);
        ArrayList arrayList2 = new ArrayList(10);
        arrayList2.add(Integer.valueOf(a3.b()));
        a3.c(arrayList2);
        return a3;
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public int getDataType() {
        return this.f13343a;
    }

    private boolean c() {
        return getDataType() == 0;
    }

    private static ExecutorService a() {
        if (b == null || b.isShutdown()) {
            b = Executors.newSingleThreadExecutor();
        }
        return b;
    }
}
