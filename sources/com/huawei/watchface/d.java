package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.mvp.model.filedownload.PullTask;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.IntegerUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public class d {
    private static d c;
    private boolean e;
    private String f;
    private boolean g = true;
    private CopyOnWriteArrayList<WatchResourcesInfo> h = new CopyOnWriteArrayList<>();
    private f i = new f();

    /* renamed from: a, reason: collision with root package name */
    public f f10982a = new f();
    private f d = new f();
    public f b = new f();

    public static d a() {
        if (c == null) {
            synchronized (d.class) {
                if (c == null) {
                    c = new d();
                    HwLog.i("AodManager", "AodManager instance end ");
                    c.b();
                }
            }
        }
        return c;
    }

    public void b() {
        if (IntegerUtils.a(NewSystemParamManager.getSystemParam("client_support_aod", "")) == 1) {
            this.e = true;
            HwLog.i("AodManager", "getSurportAod : " + this.e);
            return;
        }
        HwLog.i("AodManager", "getSurportAod : " + this.e);
        this.e = false;
    }

    public void a(String str, String str2) {
        f fVar;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (fVar = this.i) == null) {
            HwLog.e("AodManager", "addAodList paramName or  paramContent is null ");
            return;
        }
        if (fVar.b(str)) {
            return;
        }
        HwLog.e("AodManager", "addAodList paramName： " + str + ":::::" + str2);
        this.i.a(str.trim(), str2.trim());
    }

    public boolean c() {
        return this.e;
    }

    public void d() {
        f();
        this.f = "";
        this.g = true;
    }

    public void e() {
        f fVar = this.i;
        if (fVar == null) {
            return;
        }
        fVar.a();
    }

    public void f() {
        synchronized (this) {
            HwLog.i("AodManager", "clearLocalList");
            f fVar = this.f10982a;
            if (fVar != null && this.d != null) {
                fVar.a();
                this.d.a();
            }
        }
    }

    public String a(String str) {
        HwLog.i("AodManager", "operateDeviceid hiTopId : " + str);
        String b = b(str);
        this.f = b;
        return b;
    }

    public String b(String str) {
        HwLog.i("AodManager", "applyHiTopId hiTopId aodlist: " + this.e);
        if (!c() || this.f10982a == null) {
            return str;
        }
        HwLog.i("AodManager", "applyHiTopId hiTopId : ::::" + this.f10982a.b(str) + "hiTopId:::" + str + "json :" + GsonHelper.toJson(this.f10982a.b));
        return g(str);
    }

    public String c(String str) {
        if (!c() || this.f10982a == null) {
            return str;
        }
        HwLog.i("AodManager", "getHiTopId2 : " + str + "json :" + GsonHelper.toJson(this.f10982a.f11027a));
        if (!this.f10982a.b(str)) {
            return str;
        }
        HwLog.i("AodManager", "getHiTopId2 hiTopId2 : " + this.f10982a.d(str));
        return this.f10982a.d(str);
    }

    public String d(String str) {
        f fVar;
        HwLog.i("AodManager", "getHiTopId2ByTransmitDeleteWatchFaceResultMap enter : ");
        if (!c() || (fVar = this.b) == null || !fVar.b(str)) {
            return str;
        }
        HwLog.i("AodManager", "getHiTopId2ByTransmitDeleteWatchFaceResultMap hiTopId2 : " + this.b.d(str) + ",hiTopId: " + str);
        String d = this.b.d(str);
        this.b.a(str);
        return d;
    }

    public void a(WatchResourcesInfo watchResourcesInfo) {
        f fVar;
        if (!c() || (fVar = this.i) == null || this.d == null || !fVar.b(watchResourcesInfo.getWatchInfoId())) {
            return;
        }
        String watchInfoId = watchResourcesInfo.getWatchInfoId();
        String d = this.i.d(watchInfoId);
        if (!this.g || this.d.b(watchInfoId)) {
            return;
        }
        HwLog.i("AodManager", "setChangeHopidList :" + watchInfoId + ",hitopid2:" + d + "");
        this.d.a(watchInfoId, d, watchInfoId + "$#%" + watchResourcesInfo.getWatchInfoVersion());
    }

    public void a(boolean z) {
        HwLog.i("AodManager", "DeviceChange : " + z);
        if (z) {
            a().e();
        }
    }

    public void a(String str, String str2, ConcurrentHashMap<String, PullTask> concurrentHashMap) {
        HwLog.i("AodManager", "removeSaveDownhop : mWatchFaceDownloadList:" + GsonHelper.toJson(concurrentHashMap.keySet()));
        f fVar = this.f10982a;
        if (fVar == null || !fVar.b(str)) {
            return;
        }
        concurrentHashMap.remove(this.f10982a.d(str) + "_" + str2);
        HwLog.i("AodManager", "removeSaveDownhop if ：:::::" + str + ":::::" + this.f10982a.d(str) + "_" + str2);
    }

    public void b(WatchResourcesInfo watchResourcesInfo) {
        String watchInfoVersion = watchResourcesInfo.getWatchInfoVersion();
        String watchInfoId = watchResourcesInfo.getWatchInfoId();
        if (this.f10982a == null) {
            return;
        }
        HwLog.i("AodManager", "deldetUserSelectWatchFace Aod hiTopId: " + watchInfoId + ", version: " + watchInfoVersion);
        StringBuilder sb = new StringBuilder("deldetUserSelectWatchFace Aod json delde befor : ");
        sb.append(GsonHelper.toJson(this.f10982a.f11027a));
        HwLog.i("AodManager", sb.toString());
        if (this.f10982a.c(watchInfoId)) {
            String e = this.f10982a.e(watchInfoId);
            String substring = SafeString.substring(e, 0, e.indexOf("$#%"));
            WatchResourcesInfo watchResourcesInfo2 = HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchResourcesInfo(substring);
            watchResourcesInfo.setWatchInfoId(substring);
            if (watchResourcesInfo2 != null) {
                watchInfoVersion = watchResourcesInfo2.getWatchInfoVersion();
                watchResourcesInfo.setWatchInfoVersion(watchInfoVersion);
            }
            HwLog.i("AodManager", "deldetUserSelectWatchFace Aod hiTopIdVersion : " + e + "hiTopId:" + substring + "  version:" + watchInfoVersion + ":::");
            l(substring);
            this.f10982a.a(substring);
        } else if (this.f10982a.b(watchInfoId)) {
            this.f10982a.a(watchInfoId);
            l(watchInfoId);
        }
        HwLog.i("AodManager", "deldetUserSelectWatchFace Aod json delde after : " + GsonHelper.toJson(this.f10982a.f11027a));
    }

    private void l(String str) {
        f fVar = this.b;
        if (fVar == null || fVar.b(str)) {
            return;
        }
        f fVar2 = this.b;
        String d = this.f10982a.d(str);
        f fVar3 = this.f10982a;
        fVar2.a(str, d, fVar3.e(fVar3.d(str)));
    }

    public void a(HashMap<String, WatchResourcesInfo> hashMap, ArrayList<WatchResourcesInfo> arrayList) {
        synchronized (this) {
            HwLog.i("AodManager", "setpresentMap::" + hashMap.size());
            CopyOnWriteArrayList<WatchResourcesInfo> copyOnWriteArrayList = this.h;
            if (copyOnWriteArrayList != null && this.d != null) {
                copyOnWriteArrayList.clear();
                for (String str : this.d.f11027a.keySet()) {
                    String d = this.d.d(str);
                    if (hashMap.containsKey(str) && !hashMap.containsKey(d)) {
                        HwLog.i("AodManager", "add hideWatchFaceIdStore for:" + str + "_" + d);
                        WatchResourcesInfo watchResourcesInfo = hashMap.get(str);
                        if (watchResourcesInfo != null) {
                            WatchResourcesInfo watchResourcesInfo2 = new WatchResourcesInfo();
                            watchResourcesInfo2.setWatchInfoId(watchResourcesInfo.getWatchInfoId());
                            watchResourcesInfo2.setWatchInfoVersion(watchResourcesInfo.getWatchInfoVersion());
                            watchResourcesInfo2.setWatchInfoName(watchResourcesInfo.getWatchInfoName());
                            watchResourcesInfo2.setWatchInfoBrief(watchResourcesInfo.getWatchInfoBrief());
                            watchResourcesInfo2.setWatchInfoSize(watchResourcesInfo.getWatchInfoSize());
                            watchResourcesInfo2.setFailedNum(watchResourcesInfo.getFailedNum());
                            watchResourcesInfo2.setVipFreeWatchFace(watchResourcesInfo.isVipFreeWatchFace());
                            watchResourcesInfo2.setWatchExpandType(watchResourcesInfo.getWatchExpandType());
                            watchResourcesInfo2.setWatchInfoType(watchResourcesInfo.getWatchInfoType());
                            watchResourcesInfo2.setWatchScreen(watchResourcesInfo.getWatchScreen());
                            watchResourcesInfo.setWatchInfoId(d);
                            this.f10982a.a(str, d, this.d.e(d));
                            this.h.add(watchResourcesInfo2);
                            HwLog.i("AodManager", "add setpresentMap :" + str + "_" + watchResourcesInfo.getWatchInfoVersion() + "???" + this.d.e(d));
                        }
                    }
                }
                arrayList.addAll(this.h);
                HwLog.i("AodManager", "hashWatchFaces for::" + hashMap.size());
                HwLog.i("AodManager", "setpresentMap over::" + this.g + ":::::" + GsonHelper.toJson(this.f10982a.f11027a));
                this.g = false;
            }
        }
    }

    public String g() {
        StringBuilder sb = new StringBuilder("");
        Iterator<WatchResourcesInfo> it = this.h.iterator();
        while (it.hasNext()) {
            WatchResourcesInfo next = it.next();
            String watchInfoId = next.getWatchInfoId();
            String watchInfoVersion = next.getWatchInfoVersion();
            if (watchInfoId != null && watchInfoVersion != null) {
                StringBuffer stringBuffer = new StringBuffer(",");
                stringBuffer.append(watchInfoId);
                stringBuffer.append("_");
                stringBuffer.append(watchInfoVersion);
                sb.append(stringBuffer.toString());
            }
        }
        String sb2 = sb.toString();
        HwLog.i("AodManager", "getWatchFaceIdStore result: " + sb2);
        return sb2;
    }

    public boolean a(String str, String str2, String str3) {
        WatchResourcesInfo currentWatchFace;
        f fVar = this.f10982a;
        if (fVar == null || !fVar.b(str) || (currentWatchFace = HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getCurrentWatchFace()) == null) {
            return false;
        }
        HwLog.i("AodManager", "showToast hiTopId:" + str + "value::" + this.f10982a.d(str) + "Currenthitop::" + currentWatchFace.getWatchInfoId());
        return TextUtils.equals(this.f10982a.d(str), currentWatchFace.getWatchInfoId());
    }

    public String e(String str) {
        f fVar = this.f10982a;
        if (fVar == null || !fVar.c(str)) {
            return str;
        }
        String e = this.f10982a.e(str);
        HwLog.i("AodManager", "aod getCancelInstallWatchFacehiTopId befor: " + str);
        String substring = SafeString.substring(e, 0, e.indexOf("$#%"));
        HwLog.i("AodManager", "aod getCancelInstallWatchFacehiTopId after: " + substring);
        return substring;
    }

    public String f(String str) {
        HwLog.i("AodManager", "getJsCancelInstallWatchFaceResult(): " + str);
        return c(str);
    }

    public String g(String str) {
        f fVar;
        HwLog.i("AodManager", "aod getHihop1(): " + str);
        if (!c() || (fVar = this.f10982a) == null || !fVar.c(str)) {
            return str;
        }
        String e = this.f10982a.e(str);
        String substring = SafeString.substring(e, 0, e.indexOf("$#%"));
        HwLog.i("AodManager", "getHihop1: return Hihop1 :" + substring);
        return substring;
    }

    public boolean h(String str) {
        HwLog.i("AodManager", "isStopTransfer() id :" + str);
        f fVar = this.f10982a;
        return fVar != null && fVar.c(str);
    }

    public String i(String str) {
        HwLog.i("AodManager", "getStopTransfer(): " + str);
        return g(str);
    }

    public String j(String str) {
        HwLog.i("AodManager", "setCommonStopTransferCallbackToH5(): " + str);
        return c(str);
    }

    public String k(String str) {
        HwLog.i("AodManager", "getCallTransmitProgressJsFunction(): " + str);
        return c(str);
    }
}
