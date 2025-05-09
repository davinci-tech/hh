package com.huawei.watchface;

import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.mvp.model.datatype.orderhistory.OrderHistoryBean;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes7.dex */
public class x {
    private static volatile x b;

    /* renamed from: a, reason: collision with root package name */
    private final IBaseResponseCallback f11205a = new IBaseResponseCallback() { // from class: com.huawei.watchface.x$$ExternalSyntheticLambda0
        @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
        public final void onResponse(int i, Object obj) {
            x.a(i, obj);
        }
    };

    public static x a() {
        x xVar;
        synchronized (x.class) {
            if (b == null) {
                synchronized (x.class) {
                    if (b == null) {
                        b = new x();
                    }
                }
            }
            xVar = b;
        }
        return xVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void a(int r2, java.lang.Object r3) {
        /*
            boolean r0 = r3 instanceof java.lang.String
            if (r0 == 0) goto L17
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r0 = "_"
            java.lang.String[] r3 = r3.split(r0)
            int r0 = r3.length
            r1 = 2
            if (r0 < r1) goto L17
            r0 = 0
            r0 = r3[r0]
            r1 = 1
            r3 = r3[r1]
            goto L1a
        L17:
            java.lang.String r0 = ""
            r3 = r0
        L1a:
            r1 = 104(0x68, float:1.46E-43)
            if (r2 != r1) goto L29
            android.app.Application r2 = com.huawei.watchface.environment.Environment.getApplicationContext()
            com.huawei.watchface.api.HwWatchFaceManager r2 = com.huawei.watchface.api.HwWatchFaceManager.getInstance(r2)
            r2.processDeleteFailed(r0, r3)
        L29:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.x.a(int, java.lang.Object):void");
    }

    public void b() {
        HwLog.i("MembershipDeletionHelper", "startDeletionFlow");
        b(ac.a().b());
    }

    private void b(List<OrderHistoryBean> list) {
        WatchResourcesInfo watchResourcesInfo;
        ArrayList arrayList = new ArrayList();
        HashMap<String, WatchResourcesInfo> allWatchInfoHash = HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getAllWatchInfoHash();
        HwLog.i("MembershipDeletionHelper", "addChargedWatchFaces -- enter");
        ArrayList arrayList2 = new ArrayList();
        boolean f = ab.a().f();
        for (OrderHistoryBean orderHistoryBean : list) {
            if (orderHistoryBean.getIsCharge() == v.CHARGED.getValue() && orderHistoryBean.getIsOrdered().equals(z.NOT_ORDERED.getValue()) && (watchResourcesInfo = allWatchInfoHash.get(orderHistoryBean.getId())) != null) {
                String d = ac.a().d(watchResourcesInfo.getWatchInfoId(), watchResourcesInfo.getWatchInfoVersion());
                HwLog.w("MembershipDeletionHelper", "addChargedWatchFaces getContentPrivType:" + orderHistoryBean.getContentPrivType());
                if (f && (orderHistoryBean.getContentPrivType() == 2 || orderHistoryBean.getContentPrivType() == 4)) {
                    arrayList2.add(d);
                } else {
                    arrayList.add(d);
                }
            }
        }
        c(arrayList);
        a(arrayList2);
    }

    private void c(List<String> list) {
        String str;
        String str2;
        HwLog.i("MembershipDeletionHelper", "deleteWatchFaces enter" + GsonHelper.toJson(list));
        WatchResourcesInfo tryOutWatchFaceInfo = HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getTryOutWatchFaceInfo();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String[] split = it.next().split("_");
            if (split.length >= 2) {
                str = split[0];
                str2 = split[1];
            } else {
                str = "";
                str2 = "";
            }
            if (tryOutWatchFaceInfo != null && tryOutWatchFaceInfo.getWatchInfoId().equals(str)) {
                HwLog.i("MembershipDeletionHelper", "deleteWatchfaces -- could not remove tryOutWatchFace");
            } else {
                HwWatchFaceManager.getInstance(Environment.getApplicationContext()).doDeleteWatchFace(str, str2, this.f11205a);
            }
        }
    }

    public void a(List<String> list) {
        synchronized (this) {
            if (list == null) {
                return;
            }
            HwLog.i("MembershipDeletionHelper", "keepSingleMembershipFreeWatchFace -- enter" + GsonHelper.toJson(list));
            WatchResourcesInfo currentWatchFace = HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getCurrentWatchFace();
            String str = "";
            if (currentWatchFace != null) {
                HwLog.i("MembershipDeletionHelper", "current watchResourcesInfo is not null");
                str = currentWatchFace.getWatchInfoId();
            }
            ListIterator<String> listIterator = list.listIterator(0);
            String str2 = "";
            String str3 = "";
            boolean z = false;
            while (listIterator.hasNext()) {
                String[] split = listIterator.next().split("_");
                if (split.length >= 2) {
                    str2 = split[0];
                    str3 = split[1];
                }
                if (str2.equals(str)) {
                    z = true;
                } else if (listIterator.hasNext() || z) {
                    HwWatchFaceManager.getInstance(Environment.getApplicationContext()).doDeleteWatchFace(str2, str3, this.f11205a);
                    listIterator.remove();
                }
            }
            ac.a().b(list);
            HwWatchFaceManager.getInstance(Environment.getApplicationContext()).transmitWatchInfoChange(1);
        }
    }

    public void c() {
        synchronized (this) {
            HwLog.i("MembershipDeletionHelper", "clear:");
            b = null;
        }
    }
}
