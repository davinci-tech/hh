package com.huawei.ads.adsrec;

import com.huawei.ads.fund.util.StringUtils;

/* loaded from: classes2.dex */
public interface c1 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f1675a = "update AdSlotMapRecord set adShown=1 where pkgName=? and slotId=? and contentId=?";
    public static final String b = "update AdSlotMapRecord set adClicked=1 where pkgName=? and slotId=? and contentId=?";
    public static final String c = "update AdIECImpRecord set showCount=0,lastShowTime=0,updateTime=?  where pkgName=? and contentId=?";
    public static final String d = "select c.* \nfrom (\n    select * \n    from AdSlotMapRecord as asmr \n    inner join AdCreativeContentRecord as accr \n    on asmr.contentId = accr.contentId\n    where asmr.pkgName=? and asmr.slotId=? and asmr.startTime<=? and asmr.endTime>? \n        and asmr.adShown=? \n) c left outer join AdIECImpRecord as aiir \non c.pkgName=aiir.pkgName and c.contentId=aiir.contentId \nwhere (aiir.showCount<? or aiir.showCount is null) and (aiir.lastShowTime<? or aiir.lastShowTime is null) order by c.startTime desc";
    public static final String e = "select c.* from (\n    select * from AdSlotMapRecord as asmr \n    inner join AdCreativeContentRecord as accr \n    on asmr.contentId = accr.contentId\n    where asmr.pkgName=? and asmr.slotId=? and asmr.startTime<=? and asmr.endTime>? \n        and asmr.adClicked=? \n) c left outer join AdIECImpRecord as aiir \non c.pkgName=aiir.pkgName and c.contentId=aiir.contentId \nwhere (aiir.showCount<? or aiir.showCount is null) and (aiir.lastShowTime<? or aiir.lastShowTime is null) order by c.startTime desc";
    public static final String f = "select c.* from (\n    select * from AdSlotMapRecord as asmr \n    inner join AdCreativeContentRecord as accr \n    on asmr.contentId = accr.contentId\n    where asmr.pkgName=? and asmr.slotId=? and asmr.startTime<=? and asmr.endTime>? \n) c left outer join AdIECImpRecord as aiir \non c.pkgName=aiir.pkgName and c.contentId=aiir.contentId \nwhere (aiir.showCount<? or aiir.showCount is null) and (aiir.lastShowTime<? or aiir.lastShowTime is null) order by random()";
    public static final String g = "select c.* from (\n    select * \n    from AdSlotMapRecord as asmr \n    inner join AdCreativeContentRecord as accr \n    on asmr.contentId=accr.contentId\n    where asmr.pkgName=? and asmr.slotId=? and asmr.startTime<=? and asmr.endTime>? \n        and (asmr.adShown=0 or asmr.adClicked=0) \n) c left outer join AdIECImpRecord as aiir \non c.pkgName=aiir.pkgName and c.contentId=aiir.contentId \nwhere (aiir.showCount<? or aiir.showCount is null) and (aiir.lastShowTime<? or aiir.lastShowTime is null) order by c.adShown asc, c.startTime desc";
    public static final String h = "select c.* from ( select * \n    from AdSlotMapRecord as asmr \n    inner join AdCreativeContentRecord as accr \n    on asmr.contentId = accr.contentId\n    where asmr.pkgName=? and asmr.slotId=? and asmr.startTime<=? and asmr.endTime>? \n        and (asmr.adShown=0 or asmr.adClicked=0) \n) c left outer join AdIECImpRecord as aiir \non c.pkgName=aiir.pkgName and c.contentId=aiir.contentId \nwhere (aiir.showCount<? or aiir.showCount is null) and (aiir.lastShowTime<? or aiir.lastShowTime is null) order by c.adShown desc, c.startTime desc";
    public static final String i = "insert into AdIECImpRecord(pkgName,contentId,lastRecallTime,updateTime) values(?,?,?,?);";
    public static final String j = "update AdIECImpRecord set lastRecallTime=?,updateTime=? where pkgName=? and contentId=?;";
    public static final String k;
    public static final String l;
    public static final String m;

    static {
        String listToSelectionArgs = StringUtils.listToSelectionArgs(z.f1688a);
        k = listToSelectionArgs;
        l = "select DCR.* from DsContentRelRecord as DCR where DCR.updateTime >? and DCR.updateTime <=? and DCR.industryId2nd =? and DCR.dspId =? and exists (select 1 from AdEventRecord as AR where AR.contentId = DCR.contentId and AR.clientRequestId = DCR.clientRequestId and AR.eventType in " + listToSelectionArgs + ");";
        m = "select DCR.* from DsContentRelRecord as DCR where DCR.updateTime >? and DCR.updateTime <=? and DCR.dspId =? and exists (select 1 from AdEventRecord as AR where AR.contentId = DCR.contentId and AR.clientRequestId = DCR.clientRequestId and AR.eventType in " + listToSelectionArgs + ");";
    }
}
