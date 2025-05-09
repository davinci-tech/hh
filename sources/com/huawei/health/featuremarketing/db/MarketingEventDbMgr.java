package com.huawei.health.featuremarketing.db;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.health.marketing.datatype.MarketingEventInfo;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class MarketingEventDbMgr extends MarketingDbMgr {
    private static volatile MarketingEventDbMgr b;

    /* renamed from: a, reason: collision with root package name */
    private Context f2351a;

    public MarketingEventDbMgr(Context context) {
        super(context);
        this.f2351a = context;
    }

    public static MarketingEventDbMgr b(Context context) {
        if (b == null) {
            synchronized (MarketingEventDbMgr.class) {
                if (b == null) {
                    b = new MarketingEventDbMgr(context);
                }
            }
        }
        return b;
    }

    public long d(MarketingEventInfo marketingEventInfo) {
        if (marketingEventInfo == null) {
            LogUtil.c("MarketingEventDbMgr", "marketingEventInfo is null.");
            return -1L;
        }
        return MarketingDbMgr.d(this.f2351a).insertStorageData("marketing_trigger_event", 1, WK_(marketingEventInfo)) - 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00c8, code lost:
    
        if (r3 == null) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.health.marketing.datatype.MarketingEventInfo c(java.lang.String r10, int r11, java.lang.String r12) {
        /*
            r9 = this;
            java.lang.String r0 = "MarketingEventDbMgr"
            r1 = 0
            r2 = 1
            r3 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            java.lang.String r5 = "select *  from "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            android.content.Context r5 = r9.f2351a     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            com.huawei.health.featuremarketing.db.MarketingDbMgr r5 = com.huawei.health.featuremarketing.db.MarketingDbMgr.d(r5)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            java.lang.String r6 = "marketing_trigger_event"
            java.lang.String r5 = r5.getTableFullName(r6)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            r4.append(r5)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            java.lang.String r5 = " where huid =? and positionId=? and resourceId=?"
            r4.append(r5)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            java.lang.String r7 = "query selection="
            r6[r1] = r7     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            r6[r2] = r4     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            com.huawei.hwlogsmodel.LogUtil.c(r0, r6)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            android.content.Context r6 = r9.f2351a     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            com.huawei.health.featuremarketing.db.MarketingDbMgr r6 = com.huawei.health.featuremarketing.db.MarketingDbMgr.d(r6)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            r7 = 3
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            r7[r1] = r10     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            java.lang.String r8 = java.lang.String.valueOf(r11)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            r7[r2] = r8     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            r7[r5] = r12     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            android.database.Cursor r4 = r6.rawQueryStorageData(r2, r4, r7)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d android.database.SQLException -> Lbe
            if (r4 == 0) goto L95
        L49:
            boolean r5 = r4.moveToNext()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            if (r5 == 0) goto L95
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r5 = new com.huawei.health.marketing.datatype.MarketingEventInfo$Builder     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            r5.<init>()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r5 = r5.setHuId(r10)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r5 = r5.setPositionId(r11)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            java.lang.String r6 = "typeId"
            int r6 = r4.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r5 = r5.setTypeId(r6)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r5 = r5.setResourceId(r12)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            java.lang.String r6 = "triggerTime"
            int r6 = r4.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            long r6 = r4.getLong(r6)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r5 = r5.setTriggerTime(r6)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            java.lang.String r6 = "value"
            int r6 = r4.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            java.lang.String r6 = r4.getString(r6)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r5 = r5.setValue(r6)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            com.huawei.health.marketing.datatype.MarketingEventInfo r3 = r5.build()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90 android.database.SQLException -> L92
            goto L49
        L8e:
            r10 = move-exception
            goto Ld0
        L90:
            r3 = r4
            goto L9d
        L92:
            r10 = r3
            r3 = r4
            goto Lbf
        L95:
            if (r4 == 0) goto Lce
            r4.close()
            goto Lce
        L9b:
            r10 = move-exception
            goto Lcf
        L9d:
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L9b
            java.lang.String r4 = "queryStorage error Exception. set default info."
            r2[r1] = r4     // Catch: java.lang.Throwable -> L9b
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)     // Catch: java.lang.Throwable -> L9b
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r0 = new com.huawei.health.marketing.datatype.MarketingEventInfo$Builder     // Catch: java.lang.Throwable -> L9b
            r0.<init>()     // Catch: java.lang.Throwable -> L9b
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r10 = r0.setHuId(r10)     // Catch: java.lang.Throwable -> L9b
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r10 = r10.setPositionId(r11)     // Catch: java.lang.Throwable -> L9b
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r10 = r10.setResourceId(r12)     // Catch: java.lang.Throwable -> L9b
            com.huawei.health.marketing.datatype.MarketingEventInfo r10 = r10.build()     // Catch: java.lang.Throwable -> L9b
            if (r3 == 0) goto Lcd
            goto Lca
        Lbe:
            r10 = r3
        Lbf:
            java.lang.Object[] r11 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L9b
            java.lang.String r12 = "queryStorage error: "
            r11[r1] = r12     // Catch: java.lang.Throwable -> L9b
            com.huawei.hwlogsmodel.LogUtil.b(r0, r11)     // Catch: java.lang.Throwable -> L9b
            if (r3 == 0) goto Lcd
        Lca:
            r3.close()
        Lcd:
            r3 = r10
        Lce:
            return r3
        Lcf:
            r4 = r3
        Ld0:
            if (r4 == 0) goto Ld5
            r4.close()
        Ld5:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.featuremarketing.db.MarketingEventDbMgr.c(java.lang.String, int, java.lang.String):com.huawei.health.marketing.datatype.MarketingEventInfo");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x00af, code lost:
    
        if (r4 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x00c2, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00bf, code lost:
    
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00bd, code lost:
    
        if (r4 == null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.huawei.health.marketing.datatype.MarketingEventInfo> c(java.lang.String r10, int r11, java.lang.String r12, java.lang.String r13) {
        /*
            r9 = this;
            java.lang.String r0 = "MarketingEventDbMgr"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            r3 = 1
            r4 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r6 = "select *  from "
            r5.<init>(r6)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            android.content.Context r6 = r9.f2351a     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.health.featuremarketing.db.MarketingDbMgr r6 = com.huawei.health.featuremarketing.db.MarketingDbMgr.d(r6)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r7 = "marketing_trigger_event"
            java.lang.String r6 = r6.getTableFullName(r7)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            r5.append(r6)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r6 = " where huid =? and positionId=? and resourceId=?"
            r5.append(r6)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            r5.append(r13)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r13 = r5.toString()     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r7 = "cursor query selection="
            r6[r2] = r7     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            r6[r3] = r13     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.hwlogsmodel.LogUtil.c(r0, r6)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            android.content.Context r6 = r9.f2351a     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.health.featuremarketing.db.MarketingDbMgr r6 = com.huawei.health.featuremarketing.db.MarketingDbMgr.d(r6)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            r7 = 3
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            r7[r2] = r10     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r8 = java.lang.String.valueOf(r11)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            r7[r3] = r8     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            r7[r5] = r12     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            android.database.Cursor r4 = r6.rawQueryStorageData(r3, r13, r7)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            if (r4 == 0) goto Laf
            java.lang.Object[] r13 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r5 = "cursor is not null"
            r13[r2] = r5     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.hwlogsmodel.LogUtil.a(r0, r13)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
        L5a:
            boolean r13 = r4.moveToNext()     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            if (r13 == 0) goto Laf
            java.lang.Object[] r13 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r5 = "cursor move to next"
            r13[r2] = r5     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.hwlogsmodel.LogUtil.a(r0, r13)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r13 = new com.huawei.health.marketing.datatype.MarketingEventInfo$Builder     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            r13.<init>()     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r13 = r13.setHuId(r10)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r13 = r13.setPositionId(r11)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r5 = "typeId"
            int r5 = r4.getColumnIndex(r5)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            int r5 = r4.getInt(r5)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r13 = r13.setTypeId(r5)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r13 = r13.setResourceId(r12)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r5 = "triggerTime"
            int r5 = r4.getColumnIndex(r5)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            long r5 = r4.getLong(r5)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r13 = r13.setTriggerTime(r5)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r5 = "value"
            int r5 = r4.getColumnIndex(r5)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            java.lang.String r5 = r4.getString(r5)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.health.marketing.datatype.MarketingEventInfo$Builder r13 = r13.setValue(r5)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            com.huawei.health.marketing.datatype.MarketingEventInfo r13 = r13.build()     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            r1.add(r13)     // Catch: java.lang.Throwable -> Lb2 android.database.SQLException -> Lb4
            goto L5a
        Laf:
            if (r4 == 0) goto Lc2
            goto Lbf
        Lb2:
            r10 = move-exception
            goto Lc3
        Lb4:
            java.lang.Object[] r10 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lb2
            java.lang.String r11 = "queryStorage error: "
            r10[r2] = r11     // Catch: java.lang.Throwable -> Lb2
            com.huawei.hwlogsmodel.LogUtil.b(r0, r10)     // Catch: java.lang.Throwable -> Lb2
            if (r4 == 0) goto Lc2
        Lbf:
            r4.close()
        Lc2:
            return r1
        Lc3:
            if (r4 == 0) goto Lc8
            r4.close()
        Lc8:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.featuremarketing.db.MarketingEventDbMgr.c(java.lang.String, int, java.lang.String, java.lang.String):java.util.List");
    }

    private ContentValues WK_(MarketingEventInfo marketingEventInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", marketingEventInfo.getHuId());
        contentValues.put("positionId", Integer.valueOf(marketingEventInfo.getPositionId()));
        contentValues.put("typeId", Integer.valueOf(marketingEventInfo.getTypeId()));
        contentValues.put("resourceId", marketingEventInfo.getResourceId());
        contentValues.put("triggerTime", Long.valueOf(marketingEventInfo.getTriggerTime()));
        contentValues.put("value", marketingEventInfo.getValue());
        contentValues.put("reservedField", marketingEventInfo.getReservedField());
        return contentValues;
    }
}
