package defpackage;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.CommonUtils;
import health.compact.a.DbManager;

/* loaded from: classes6.dex */
public class mea extends HwBaseManager {
    private static Context d;
    private static volatile mea e;

    public static int d(int i, int i2) {
        return i2 == 0 ? i : i2;
    }

    private mea(Context context) {
        super(context);
        LogUtil.c("AchieveDBManager", "AchieveDBManager instructor");
        r();
    }

    public static mea b(Context context) {
        if (context != null) {
            d = context.getApplicationContext();
        } else {
            d = BaseApplication.getContext();
        }
        if (e == null) {
            synchronized (mea.class) {
                if (e == null) {
                    e = new mea(d);
                }
            }
        }
        return e;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 20003;
    }

    private void s() {
        n();
        o();
        f();
        m();
        d();
        a();
        g();
        k();
        i();
        l();
        e();
        j();
        h();
        p();
        b();
        c();
    }

    private void f() {
        d("medal_basic_record", 1, "id integer primary key autoincrement, huid text not null,medalID text not null,medalName text not null,timeStamp Long not null,veinUrl text not null", "table_version_medal_basic_record");
    }

    private void b() {
        d("insight_rank", 1, "id integer primary key autoincrement, rankType text not null,rankVersion Long,gender text not null,ageSegment text not null,insightRankItemList text", "table_version_insight_rank");
    }

    private void c() {
        d("annual_info", 1, "id integer primary key autoincrement, huid text not null,type text not null,value text not null,key integer not null,year integer not null,reservedField text", "table_version_annual_info");
    }

    private void d() {
        d("achieve_info", 1, "id integer primary key autoincrement, huid text not null,reach_days integer not null,points integer not null,medal_id text,userReachStandardDays text,syncTimestamp text not null", "table_version_achieve_info");
    }

    private void a() {
        d("kaka_line", 1, "id integer primary key autoincrement, huid text not null,date_timestamp Long not null,description integer not null,kaka_num integer not null,occurDate timestamp unique", "table_version_kaka_line");
    }

    private void e() {
        d("level_event_record", 1, "id integer primary key autoincrement, huid text not null,taskId text not null,rewardExperience integer not null,taskCount integer not null,lastModifyTime Long not null,taskSumCount integer not null,SyncTime Long not null,reservedField text", "table_version_level_event_record");
    }

    private void p() {
        d("week_and_month_record", 1, "id integer primary key autoincrement, huid text not null,value text not null,britishValue text,recentType integer not null,startTimestamp Long not null,endTimeStamp Long not null,dataSource integer not null,maxReportNo integer not null,minReportNo integer not null,reportNo integer not null,reservedField text", "table_version_week_and_month_record");
    }

    private void i() {
        d("medal_event_record", 1, "id integer primary key autoincrement, huid text not null,medalID text not null,medalName text not null,eventType integer not null,value text not null,timeZone text not null,key text not null,keyType text not null,startTime text not null,endTime text not null,eventStatus text not null,timestamp Long not null", "table_version_medal_event_record");
    }

    private void g() {
        d("medal_location_record", 1, "id integer primary key autoincrement, huid text not null,medalName text not null,medalID text not null,firstTabPriority integer not null,firstTabDesc text not null,secondTabPriority integer not null,secondTabDesc text not null,medalWeight integer not null,medalGainedTime text not null,gainedCount integer not null,timestamp Long not null", "table_version_medal_location_record");
    }

    private void m() {
        d("recent", 1, "id integer primary key autoincrement, huid text not null,recentType integer not null,firstDate text not null,endDate text not null,dataType integer not null,value text not null,reportNo integer not null,kakaNum integer not null,price integer not null,medal_id text,comments1_id text not null,commentS2_id text not null,stepsRanking text,distanceRanking text,minReportNo integer", "table_version_recent");
    }

    private void l() {
        d("kaka_task_record", 2, "id integer primary key autoincrement, huid text not null,taskId text not null,taskStatus text not null,taskName text not null,taskDes text not null,taskSpecification text not null,taskDetailUrl text not null,taskFrequency integer not null,taskRewardKaKa integer not null,taskRewardExperience integer not null,taskTypes integer not null,taskConditions text,eventTimeStamp Long not null,taskSyncTimeStamp Long not null,lastTimeStamp Long not null,taskStartTime Long not null,taskEndTime Long not null,taskPriority integer not null,taskIcon text not null,taskIsShow integer not null,taskSyncStatus integer not null,preButton NVARCHAR(100),postButton NVARCHAR(100),category integer not null,taskRule integer not null,level integer not null,bonusType integer not null,updateTime Long not null,timeZoneOffset Long not null,scenario integer not null,isMember integer not null,pictureUrl text,buttonColor text,labelMatch integer,reservedField text", "table_version_kaka_task_record");
    }

    private void k() {
        d("medal_config_info_record", 1, "id integer primary key autoincrement, huid text not null,medalName text not null,medalID text not null,medalType text not null,activityId integer not null,message text not null,grayDescription text not null,lightDescription text not null,grayDetailStyle text not null,lightDetailStyle text not null,grayPromotionName text not null,lightPromotionName text not null,grayPromotionUrl text not null,lightPromotionUrl text not null,grayListStyle text not null,lightListStyle text not null,shareImageUrl text not null,location text not null,actionType integer not null,goal integer not null,startTime text not null,endTime text not null,takeEffectTime text not null,clientTypes text not null,phoneTypes text not null,isNewConfig integer not null,repeatable integer not null,medalLevel integer not null,medalLabel integer not null,medalUnit integer not null,timestamp Long not null,reachStatus integer not null,eventStatus integer not null", "table_version_medal_config_info_record");
    }

    private void r() {
        s();
    }

    private void h() {
        d("level_info_record", 1, "id integer primary key autoincrement, huid text not null,userLevel integer not null,userExperience integer not null,userReachDays text not null,syncTimestamp Long not null,reservedField text", "table_version_level_info_record");
    }

    private void j() {
        d("level_number_record", 1, "id integer primary key autoincrement, huid text not null,level integer not null,levelUserNumber integer not null,timeStamp Long not null,reservedField text", "table_version_level_number_record");
    }

    private void n() {
        d("total_record", 1, "id integer primary key autoincrement, huid text not null,startDate text not null,endDate text not null,dataType integer not null,value text not null,stepsRanking text", "table_version_total_record");
    }

    private void o() {
        d("single_day_record", 1, "id integer primary key autoincrement, huid text not null,dataType integer not null,value text not null,date text not null", "table_version_single_day_record");
    }

    private void d(String str, int i, String str2, String str3) {
        int h = CommonUtils.h(mct.b(d, str3));
        boolean c = c(str);
        LogUtil.a("PLGACHIEVE_AchieveDBManager", "createTable ", str, " oldV ", Integer.valueOf(h), " newV ", Integer.valueOf(i), " spKey ", str3, "isExistTable ", Boolean.valueOf(c));
        if (!c || h < i) {
            if (a(str) != 0) {
                LogUtil.h("PLGACHIEVE_AchieveDBManager", "createTable deleteTable fail!");
                return;
            }
            int createStorageDataTable = createStorageDataTable(str, 1, str2);
            if (createStorageDataTable != 0) {
                LogUtil.h("PLGACHIEVE_AchieveDBManager", "createTable database is bad errorCode ", Integer.valueOf(createStorageDataTable));
                if (!deleteDatabase()) {
                    mct.b(d, str3, "");
                    LogUtil.h("PLGACHIEVE_AchieveDBManager", "createTable deleteDatabase fail!");
                    return;
                }
                createStorageDataTable = createStorageDataTable(str, 1, str2);
            }
            if (createStorageDataTable != 0) {
                LogUtil.h("PLGACHIEVE_AchieveDBManager", "createTable errorCode ", Integer.valueOf(createStorageDataTable));
            } else {
                mct.b(d, str3, String.valueOf(i));
                LogUtil.a("PLGACHIEVE_AchieveDBManager", "createTable result success! tableId = ", str);
            }
        }
    }

    private boolean c(String str) {
        boolean z;
        Cursor queryStorageData = queryStorageData(str, 1, null);
        if (queryStorageData != null) {
            z = b(str);
            queryStorageData.close();
        } else {
            z = false;
        }
        LogUtil.a("PLGACHIEVE_AchieveDBManager", "isExistTable tableId ", str, " isExist ", Boolean.valueOf(z));
        return z;
    }

    private boolean b(String str) {
        if ("kaka_task_record".equals(str)) {
            return q();
        }
        return true;
    }

    private boolean q() {
        boolean b = DbManager.b(String.valueOf(getModuleId()), getTableFullName("kaka_task_record"), ParsedFieldTag.KAKA_TASK_VIP_MATCH);
        LogUtil.a("PLGACHIEVE_AchieveDBManager", "checkColumn isMember isExist = ", Boolean.valueOf(b));
        return b;
    }

    private int a(String str) {
        int i;
        LogUtil.a("PLGACHIEVE_AchieveDBManager", "Enter deleteTable tableId ", str);
        Cursor queryStorageData = queryStorageData(str, 1, null);
        if (queryStorageData != null) {
            i = DbManager.d(d, String.valueOf(getModuleId()), str, 1);
            queryStorageData.close();
        } else {
            i = 0;
        }
        LogUtil.a("PLGACHIEVE_AchieveDBManager", "Leave deleteTable isDelete ", Integer.valueOf(i));
        return i;
    }

    public static String cfS_(Cursor cursor, String str) {
        if (cursor == null || TextUtils.isEmpty(str)) {
            return "1";
        }
        int columnIndex = cursor.getColumnIndex(str);
        if (-1 != columnIndex) {
            return cursor.getString(columnIndex);
        }
        LogUtil.b("PLGACHIEVE_AchieveDBManager", "getStringColumn wrong columnName = ", str);
        return "1";
    }

    public static long cfR_(Cursor cursor, String str) {
        if (cursor == null || TextUtils.isEmpty(str)) {
            return 0L;
        }
        int columnIndex = cursor.getColumnIndex(str);
        if (-1 != columnIndex) {
            return cursor.getLong(columnIndex);
        }
        LogUtil.b("PLGACHIEVE_AchieveDBManager", "getStringColumn wrong columnName = ", str);
        return 0L;
    }

    public static int cfQ_(Cursor cursor, String str) {
        if (cursor == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        int columnIndex = cursor.getColumnIndex(str);
        if (-1 != columnIndex) {
            return cursor.getInt(columnIndex);
        }
        LogUtil.a("PLGACHIEVE_AchieveDBManager", "getStringColumn wrong columnName = ", str);
        return 0;
    }

    public static String a(String str, String str2) {
        return TextUtils.isEmpty(str2) ? str : str2;
    }
}
