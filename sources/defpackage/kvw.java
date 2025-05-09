package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import health.compact.a.HiDateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes5.dex */
public class kvw {
    private static final Object c = new Object();
    private static volatile kvw d;

    /* renamed from: a, reason: collision with root package name */
    private int f14644a = 1;
    private int b = 3;
    private Context e;

    private kvw(Context context) {
        this.e = context;
    }

    public static kvw c(Context context) {
        if (d == null) {
            synchronized (c) {
                if (d == null) {
                    if (context == null) {
                        d = new kvw(BaseApplication.getContext());
                    } else {
                        d = new kvw(context);
                    }
                }
            }
        }
        return d;
    }

    private void b() {
        String b = kwn.b(30005, "ai-common-001", "message_show_to_smartcard_time");
        String b2 = kwn.b(30005, "ai-common-002", "click_info_time_on_smartcard_oneday");
        LogUtil.a("SmartDataInteractors", "msgShowTime = ", b, " clickTime = ", b2);
        if (!TextUtils.isEmpty(b)) {
            try {
                this.b = Integer.parseInt(b);
            } catch (NumberFormatException e) {
                LogUtil.b("SmartDataInteractors", "NumberFormatException = ", e.getMessage());
            }
        }
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        try {
            this.f14644a = Integer.parseInt(b2);
        } catch (NumberFormatException e2) {
            LogUtil.b("SmartDataInteractors", "NumberFormatException = ", e2.getMessage());
        }
    }

    public SmartMsgDbObject d(List<SmartMsgDbObject> list, int i, int i2) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        b();
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        ArrayList<SmartMsgDbObject> arrayList3 = new ArrayList(10);
        ArrayList arrayList4 = new ArrayList(10);
        e(list, i, i2, arrayList, arrayList2);
        if (arrayList.isEmpty() && arrayList2.isEmpty()) {
            return null;
        }
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList, new d(0));
        }
        if (!arrayList2.isEmpty()) {
            Collections.sort(arrayList2, new d(2));
            int c2 = HiDateUtil.c(arrayList2.get(0).getUpdateTime());
            for (SmartMsgDbObject smartMsgDbObject : arrayList2) {
                if (c2 == HiDateUtil.c(smartMsgDbObject.getUpdateTime())) {
                    arrayList3.add(smartMsgDbObject);
                }
            }
        }
        if (!arrayList3.isEmpty()) {
            Collections.sort(arrayList3, new d(1));
            int messagePriority = ((SmartMsgDbObject) arrayList3.get(0)).getMessagePriority();
            for (SmartMsgDbObject smartMsgDbObject2 : arrayList3) {
                if (messagePriority == smartMsgDbObject2.getMessagePriority()) {
                    arrayList4.add(smartMsgDbObject2);
                }
            }
        }
        if (!arrayList4.isEmpty()) {
            Collections.sort(arrayList4, new d(0));
        }
        if (arrayList.isEmpty()) {
            return (SmartMsgDbObject) arrayList4.get(0);
        }
        if (arrayList4.isEmpty()) {
            return arrayList.get(0);
        }
        return (SmartMsgDbObject) (arrayList.get(0).getUpdateTime() > ((SmartMsgDbObject) arrayList4.get(0)).getUpdateTime() ? arrayList.get(0) : arrayList4.get(0));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(java.util.List<com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject> r11, int r12, int r13, java.util.List<com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject> r14, java.util.List<com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject> r15) {
        /*
            r10 = this;
            java.lang.String r0 = "health_information_click_count"
            java.util.Iterator r11 = r11.iterator()
        L6:
            boolean r1 = r11.hasNext()
            if (r1 == 0) goto Lcb
            java.lang.Object r1 = r11.next()
            com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject r1 = (com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject) r1
            int r2 = r1.getId()
            java.lang.String r3 = "SmartDataInteractors"
            if (r2 != r13) goto L27
            boolean r2 = defpackage.kvr.d(r1)
            if (r2 != 0) goto L6
            boolean r2 = defpackage.kvr.c(r1)
            if (r2 == 0) goto L3a
            goto L6
        L27:
            int r2 = r10.b
            boolean r2 = defpackage.kvr.a(r1, r2)
            if (r2 != 0) goto L30
            goto L6
        L30:
            java.lang.String r2 = "traversingMsgList, Msg is valid"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
        L3a:
            java.lang.String r2 = r1.getShowTime()
            boolean r2 = defpackage.kvr.d(r2)
            if (r2 != 0) goto L45
            goto L6
        L45:
            boolean r2 = defpackage.kvr.a(r1)
            if (r2 == 0) goto L4e
            if (r12 != 0) goto L4e
            goto L6
        L4e:
            int r2 = r1.getMsgSrc()
            r4 = 4
            if (r2 != r4) goto Lb1
            r2 = 0
            r4 = 10000(0x2710, float:1.4013E-41)
            android.content.Context r5 = r10.e     // Catch: java.lang.NumberFormatException -> L79
            java.lang.String r6 = java.lang.Integer.toString(r4)     // Catch: java.lang.NumberFormatException -> L79
            java.lang.String r5 = health.compact.a.SharedPreferenceManager.b(r5, r6, r0)     // Catch: java.lang.NumberFormatException -> L79
            int r5 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.NumberFormatException -> L79
            android.content.Context r6 = r10.e     // Catch: java.lang.NumberFormatException -> L77
            java.lang.String r7 = java.lang.Integer.toString(r4)     // Catch: java.lang.NumberFormatException -> L77
            java.lang.String r8 = "health_information_click_time"
            java.lang.String r6 = health.compact.a.SharedPreferenceManager.b(r6, r7, r8)     // Catch: java.lang.NumberFormatException -> L77
            long r6 = java.lang.Long.parseLong(r6)     // Catch: java.lang.NumberFormatException -> L77
            goto L8b
        L77:
            r6 = move-exception
            goto L7c
        L79:
            r5 = move-exception
            r6 = r5
            r5 = r2
        L7c:
            java.lang.String r7 = "parseInt Exception "
            java.lang.String r6 = r6.getMessage()
            java.lang.Object[] r6 = new java.lang.Object[]{r7, r6}
            com.huawei.hwlogsmodel.LogUtil.b(r3, r6)
            r6 = 0
        L8b:
            int r3 = r10.f14644a
            if (r5 < r3) goto Lb1
            long r8 = java.lang.System.currentTimeMillis()
            int r3 = health.compact.a.HiDateUtil.c(r8)
            int r5 = health.compact.a.HiDateUtil.c(r6)
            if (r3 != r5) goto L9f
            goto L6
        L9f:
            android.content.Context r3 = r10.e
            java.lang.String r4 = java.lang.Integer.toString(r4)
            java.lang.String r2 = java.lang.Integer.toString(r2)
            health.compact.a.StorageParams r5 = new health.compact.a.StorageParams
            r5.<init>()
            health.compact.a.SharedPreferenceManager.e(r3, r4, r0, r2, r5)
        Lb1:
            int r2 = r1.getMsgSrc()
            r3 = 5
            if (r2 != r3) goto Lc6
            int r2 = r1.getMsgType()
            r3 = 50001(0xc351, float:7.0066E-41)
            if (r2 == r3) goto L6
            r14.add(r1)
            goto L6
        Lc6:
            r15.add(r1)
            goto L6
        Lcb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kvw.e(java.util.List, int, int, java.util.List, java.util.List):void");
    }

    static class d implements Comparator<SmartMsgDbObject>, Serializable {
        private static final long serialVersionUID = 3205347599318091992L;
        private int b;

        d(int i) {
            this.b = i;
        }

        @Override // java.util.Comparator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public int compare(SmartMsgDbObject smartMsgDbObject, SmartMsgDbObject smartMsgDbObject2) {
            int messagePriority;
            int messagePriority2;
            int i = this.b;
            if (i == 0) {
                return smartMsgDbObject2.getUpdateTime() >= smartMsgDbObject.getUpdateTime() ? 1 : -1;
            }
            if (i == 1) {
                messagePriority = smartMsgDbObject2.getMessagePriority();
                messagePriority2 = smartMsgDbObject.getMessagePriority();
            } else {
                if (i != 2) {
                    return 0;
                }
                messagePriority = HiDateUtil.c(smartMsgDbObject2.getUpdateTime());
                messagePriority2 = HiDateUtil.c(smartMsgDbObject.getUpdateTime());
            }
            return messagePriority - messagePriority2;
        }
    }
}
