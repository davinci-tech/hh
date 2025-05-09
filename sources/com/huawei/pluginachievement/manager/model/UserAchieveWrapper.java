package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;
import defpackage.mde;
import defpackage.mdf;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class UserAchieveWrapper {
    private AchieveInfo mAchieveInfo;
    private AchieveLevelEventInfo mAchieveLevelEventInfo;
    private AchieveLevelTotalInfo mAchieveLevelTotalInfo;
    private AchieveUserLevelInfo mAchieveUserLevelInfo;
    private ActivityInfo mActivityInfo;
    private int mContentType;
    private EventRecord mEventRecord;
    private CalorieExchange mExchange;
    private List<mdf> mHealthLifeKakaTaskInfoList;
    private KakaCheckInReturnBody mKakaCheckInReturnBody;
    private List<KakaCheckinRecord> mKakaCheckinRecords;
    private List<GiftInfo> mKakaGiftInfos;
    private KakaLineRecord mKakaLineRecord;
    private KakaRedeemGiftReturnBody mKakaRedeemGiftReturnBody;
    private mdf mKakaTaskInfo;
    private mde mKakaUpdateReturnBody;
    private MultiLanguageRes mLanguageRes;
    private LevelLineRecord mLevelLineRecord;
    private LevelUpdateReturnBody mLevelUpdateReturnBody;
    private MedalConfigInfo mMedalConfigInfo;
    private MedalLocation mMedalLocation;
    private MedalBasic mMedalTexture;
    private MessageReminder mMsgReminder;
    private RecentMonthRecord mRecentMonthRecord;
    private RecentWeekRecord mRecentWeekRecord;
    private List<GiftRecord> mRedeemGiftRecords;
    private KakaRedeemInfo mRedeemInfo;
    private KakaRedeemResult mRedeemResult;
    private String mResultCode;
    private SingleDayRecord mSingleDayRecord;
    private TotalRecord mTotalRecord;

    public UserAchieveWrapper(int i) {
        this(i, null, null, null, null);
    }

    public UserAchieveWrapper(int i, RecentWeekRecord recentWeekRecord, RecentMonthRecord recentMonthRecord, SingleDayRecord singleDayRecord, TotalRecord totalRecord) {
        this.mResultCode = "0";
        this.mContentType = i;
        this.mRecentMonthRecord = recentMonthRecord;
        this.mRecentWeekRecord = recentWeekRecord;
        this.mSingleDayRecord = singleDayRecord;
        this.mTotalRecord = totalRecord;
    }

    public void saveMedalTexture(MedalBasic medalBasic) {
        this.mMedalTexture = medalBasic;
    }

    public void saveMedalLocation(MedalLocation medalLocation) {
        this.mMedalLocation = medalLocation;
    }

    public void saveMedalConfigInfo(MedalConfigInfo medalConfigInfo) {
        this.mMedalConfigInfo = medalConfigInfo;
    }

    public void saveEventRecord(EventRecord eventRecord) {
        this.mEventRecord = eventRecord;
    }

    public mdf acquireKakaTaskInfo() {
        return this.mKakaTaskInfo;
    }

    public void saveKakaTaskInfo(mdf mdfVar) {
        this.mKakaTaskInfo = mdfVar;
    }

    public ActivityInfo acquireActivityInfo() {
        return this.mActivityInfo;
    }

    public void saveActivityInfo(ActivityInfo activityInfo) {
        this.mActivityInfo = activityInfo;
    }

    public mde acquireKakaUpdateReturnBody() {
        return this.mKakaUpdateReturnBody;
    }

    public void saveKakaUpdateReturnBody(mde mdeVar) {
        this.mKakaUpdateReturnBody = mdeVar;
    }

    public AchieveLevelEventInfo acquireAchieveLevelEventInfo() {
        return this.mAchieveLevelEventInfo;
    }

    public void saveAchieveLevelEventInfo(AchieveLevelEventInfo achieveLevelEventInfo) {
        this.mAchieveLevelEventInfo = achieveLevelEventInfo;
    }

    public void saveAchieveUserLevelInfo(AchieveUserLevelInfo achieveUserLevelInfo) {
        this.mAchieveUserLevelInfo = achieveUserLevelInfo;
    }

    public void saveAchieveLevelTotalInfo(AchieveLevelTotalInfo achieveLevelTotalInfo) {
        this.mAchieveLevelTotalInfo = achieveLevelTotalInfo;
    }

    public List<mcz> toList() {
        ArrayList arrayList = new ArrayList(8);
        structListTen(arrayList);
        MedalConfigInfo medalConfigInfo = this.mMedalConfigInfo;
        if (medalConfigInfo != null) {
            arrayList.add(medalConfigInfo);
        }
        EventRecord eventRecord = this.mEventRecord;
        if (eventRecord != null) {
            arrayList.add(eventRecord);
        }
        mdf mdfVar = this.mKakaTaskInfo;
        if (mdfVar != null) {
            arrayList.add(mdfVar);
        }
        mde mdeVar = this.mKakaUpdateReturnBody;
        if (mdeVar != null) {
            arrayList.add(mdeVar);
        }
        AchieveLevelTotalInfo achieveLevelTotalInfo = this.mAchieveLevelTotalInfo;
        if (achieveLevelTotalInfo != null) {
            arrayList.add(achieveLevelTotalInfo);
        }
        AchieveLevelEventInfo achieveLevelEventInfo = this.mAchieveLevelEventInfo;
        if (achieveLevelEventInfo != null) {
            arrayList.add(achieveLevelEventInfo);
        }
        AchieveUserLevelInfo achieveUserLevelInfo = this.mAchieveUserLevelInfo;
        if (achieveUserLevelInfo != null) {
            arrayList.add(achieveUserLevelInfo);
        }
        LevelUpdateReturnBody levelUpdateReturnBody = this.mLevelUpdateReturnBody;
        if (levelUpdateReturnBody != null) {
            arrayList.add(levelUpdateReturnBody);
        }
        return arrayList;
    }

    private void structListTen(List<mcz> list) {
        AchieveInfo achieveInfo = this.mAchieveInfo;
        if (achieveInfo != null) {
            list.add(achieveInfo);
        }
        RecentWeekRecord recentWeekRecord = this.mRecentWeekRecord;
        if (recentWeekRecord != null) {
            list.add(recentWeekRecord);
        }
        RecentMonthRecord recentMonthRecord = this.mRecentMonthRecord;
        if (recentMonthRecord != null) {
            list.add(recentMonthRecord);
        }
        TotalRecord totalRecord = this.mTotalRecord;
        if (totalRecord != null) {
            list.add(totalRecord);
        }
        SingleDayRecord singleDayRecord = this.mSingleDayRecord;
        if (singleDayRecord != null) {
            list.add(singleDayRecord);
        }
        KakaLineRecord kakaLineRecord = this.mKakaLineRecord;
        if (kakaLineRecord != null) {
            list.add(kakaLineRecord);
        }
        LevelLineRecord levelLineRecord = this.mLevelLineRecord;
        if (levelLineRecord != null) {
            list.add(levelLineRecord);
        }
        MedalBasic medalBasic = this.mMedalTexture;
        if (medalBasic != null) {
            list.add(medalBasic);
        }
        MedalLocation medalLocation = this.mMedalLocation;
        if (medalLocation != null) {
            list.add(medalLocation);
        }
    }

    public AchieveInfo getAchieveInfo() {
        return this.mAchieveInfo;
    }

    public void setAchieveInfo(AchieveInfo achieveInfo) {
        this.mAchieveInfo = achieveInfo;
    }

    public RecentWeekRecord acquireRecentWeekRecord() {
        return this.mRecentWeekRecord;
    }

    public void setRecentWeekRecord(RecentWeekRecord recentWeekRecord) {
        this.mRecentWeekRecord = recentWeekRecord;
    }

    public RecentMonthRecord acquireRecentMonthRecord() {
        return this.mRecentMonthRecord;
    }

    public void setRecentMonthRecord(RecentMonthRecord recentMonthRecord) {
        this.mRecentMonthRecord = recentMonthRecord;
    }

    public TotalRecord acquireTotalRecord() {
        return this.mTotalRecord;
    }

    public void setTotalRecord(TotalRecord totalRecord) {
        this.mTotalRecord = totalRecord;
    }

    public SingleDayRecord acquireSingleDayRecord() {
        return this.mSingleDayRecord;
    }

    public void setSingleDayRecord(SingleDayRecord singleDayRecord) {
        this.mSingleDayRecord = singleDayRecord;
    }

    public void setKakaLineRecord(KakaLineRecord kakaLineRecord) {
        this.mKakaLineRecord = kakaLineRecord;
    }

    public KakaLineRecord getKakaLineRecord() {
        return this.mKakaLineRecord;
    }

    public void setLevelLineRecord(LevelLineRecord levelLineRecord) {
        this.mLevelLineRecord = levelLineRecord;
    }

    public LevelLineRecord getLevelLineRecord() {
        return this.mLevelLineRecord;
    }

    public MessageReminder getMsgReminder() {
        return this.mMsgReminder;
    }

    public void setMsgReminder(MessageReminder messageReminder) {
        this.mMsgReminder = messageReminder;
    }

    public int getContentType() {
        return this.mContentType;
    }

    public CalorieExchange getExchange() {
        return this.mExchange;
    }

    public void setExchange(CalorieExchange calorieExchange) {
        this.mExchange = calorieExchange;
    }

    public MultiLanguageRes getLanguageRes() {
        return this.mLanguageRes;
    }

    public void setLanguageRes(MultiLanguageRes multiLanguageRes) {
        this.mLanguageRes = multiLanguageRes;
    }

    public String getResultCode() {
        return this.mResultCode;
    }

    public void setResultCode(String str) {
        this.mResultCode = str;
    }

    public void saveLevelUpdateReturnBody(LevelUpdateReturnBody levelUpdateReturnBody) {
        this.mLevelUpdateReturnBody = levelUpdateReturnBody;
    }

    public KakaRedeemInfo getRedeemInfo() {
        return this.mRedeemInfo;
    }

    public void setRedeemInfo(KakaRedeemInfo kakaRedeemInfo) {
        this.mRedeemInfo = kakaRedeemInfo;
    }

    public KakaRedeemResult getRedeemResult() {
        return this.mRedeemResult;
    }

    public void setRedeemResult(KakaRedeemResult kakaRedeemResult) {
        this.mRedeemResult = kakaRedeemResult;
    }

    public List<mdf> getHealthLifeKakaTaskInfoList() {
        return this.mHealthLifeKakaTaskInfoList;
    }

    public void setHealthLifeKakaTaskInfoList(List<mdf> list) {
        this.mHealthLifeKakaTaskInfoList = list;
    }

    public KakaCheckInReturnBody getKakaCheckInReturnBody() {
        return this.mKakaCheckInReturnBody;
    }

    public void setKakaCheckInReturnBody(KakaCheckInReturnBody kakaCheckInReturnBody) {
        this.mKakaCheckInReturnBody = kakaCheckInReturnBody;
    }

    public List<KakaCheckinRecord> getKakaCheckinRecords() {
        return this.mKakaCheckinRecords;
    }

    public void setKakaCheckinRecords(List<KakaCheckinRecord> list) {
        this.mKakaCheckinRecords = list;
    }

    public List<GiftInfo> getKakaGiftInfos() {
        return this.mKakaGiftInfos;
    }

    public void setKakaGiftInfos(List<GiftInfo> list) {
        this.mKakaGiftInfos = list;
    }

    public KakaRedeemGiftReturnBody getKakaRedeemGiftReturnBody() {
        return this.mKakaRedeemGiftReturnBody;
    }

    public void setKakaRedeemGiftReturnBody(KakaRedeemGiftReturnBody kakaRedeemGiftReturnBody) {
        this.mKakaRedeemGiftReturnBody = kakaRedeemGiftReturnBody;
    }

    public List<GiftRecord> getRedeemGiftRecords() {
        return this.mRedeemGiftRecords;
    }

    public void setRedeemGiftRecords(List<GiftRecord> list) {
        this.mRedeemGiftRecords = list;
    }
}
