package com.huawei.ui.main.stories.nps.interactors.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import health.compact.a.DbManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class QuestionSurveyDataBase {
    private static final String COLUMN_COUNTRY_CODE = "countryCode";
    private static final String COLUMN_DEVICE_ID = "deviceID";
    private static final String COLUMN_DEVICE_TYPE = "deviceType";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SURVEY_ID = "surveyID";
    private static final String COLUMN_TIMES = "times";
    private static final String COUNTRY_CODE_NVARCHAR = " NVARCHAR(32),";
    private static final String CREATE_TABLE_SQL;
    private static final String DATABASE_TABLE = "questionSurvey";
    private static final long INSERT_TABLE_ERROR = -1;
    private static final String NVARCHAR = " NVARCHAR(64),";
    private static final String PUBLIC_SYMBOL = "'";
    private static final String SQL_COLUMN_DEVICE_ID = " NVARCHAR(128) not null,";
    private static final String SQL_COLUMN_ID = " integer primary key autoincrement,";
    private static final String SQL_COLUMN_LAST_SURVEY_TIME = " Long not null,";
    private static final String SQL_COLUMN_QUESTION_ID = " integer not null,";
    private static final String SQL_COLUMN_TIMES = " integer not null";
    private static final String SQL_STRING_KEY = "='";
    private static final String TAG = "QuestionSurveyDB";
    private static final int UPDATE_SURVEY_ERR = 0;
    private static final String COLUMN_LAST_SURVEY_TIME = "lastSurveyTime";
    private static final String COLUMN_QUESTION_ID = "questionid";
    private static final String COLUMN_DATA_FIRST = "Data1";
    private static final String COLUMN_DATA_SECOND = "Data2";
    private static final String COLUMN_DATA_THIRD = "Data3";
    private static final String COLUMN_DATA_FOURTH = "Data4";
    private static final String COLUMN_DATA_FIFTH = "Data5";
    private static final String[] COLUMNS = {"_id", "deviceID", COLUMN_LAST_SURVEY_TIME, "deviceType", "times", COLUMN_QUESTION_ID, "surveyID", "countryCode", COLUMN_DATA_FIRST, COLUMN_DATA_SECOND, COLUMN_DATA_THIRD, COLUMN_DATA_FOURTH, COLUMN_DATA_FIFTH};

    static {
        StringBuilder sb = new StringBuilder(16);
        sb.append("_id integer primary key autoincrement,deviceID NVARCHAR(128) not null,lastSurveyTime Long not null,deviceType NVARCHAR(64),surveyID NVARCHAR(32),questionid integer not null,countryCode NVARCHAR(32),Data1 NVARCHAR(64),Data2 NVARCHAR(64),Data3 NVARCHAR(64),Data4 NVARCHAR(64),Data5 NVARCHAR(64),times integer not null");
        CREATE_TABLE_SQL = sb.toString();
    }

    public void createDateBaseTable(HwNpsManager hwNpsManager) {
        if (hwNpsManager != null) {
            String str = CREATE_TABLE_SQL;
            if (hwNpsManager.createStorageDataTable(DATABASE_TABLE, 1, str) != 0) {
                LogUtil.h(TAG, "database is bad.");
                if (!hwNpsManager.deleteDatabase()) {
                    LogUtil.h(TAG, "data base error.");
                } else {
                    hwNpsManager.createStorageDataTable(DATABASE_TABLE, 1, str);
                }
            }
        }
    }

    public void upgradeQuestionSurveyDateBase(HwNpsManager hwNpsManager, int i, Context context) {
        if (hwNpsManager == null || context == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        getOldSurveyDateBase(hwNpsManager, arrayList);
        hwNpsManager.deleteStorageData(String.valueOf(i), 1, null);
        DbManager.d(context, String.valueOf(i), DATABASE_TABLE, 1);
        createDateBaseTable(hwNpsManager);
        Iterator<QuestionSurveyTable> it = arrayList.iterator();
        while (it.hasNext()) {
            insertDateBaseTable(hwNpsManager, it.next());
        }
    }

    public void getOldSurveyDateBase(HwNpsManager hwNpsManager, List<QuestionSurveyTable> list) {
        if (hwNpsManager == null || list == null) {
            LogUtil.h(TAG, "getOldSurveyDateBase hwNpsManager is null or questionSurveyTables is null");
            return;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = hwNpsManager.queryStorageData(DATABASE_TABLE, 1, null);
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        QuestionSurveyTable questionSurveyTable = new QuestionSurveyTable();
                        questionSurveyTable.setDeviceId(cursor.getString(cursor.getColumnIndex("deviceID")));
                        questionSurveyTable.setLastSurveyTime(cursor.getLong(cursor.getColumnIndex(COLUMN_LAST_SURVEY_TIME)));
                        questionSurveyTable.setDeviceType(cursor.getString(cursor.getColumnIndex("deviceType")));
                        questionSurveyTable.setTimes(cursor.getInt(cursor.getColumnIndex("times")));
                        questionSurveyTable.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_QUESTION_ID)));
                        questionSurveyTable.setSurveyId("" + questionSurveyTable.getId());
                        list.add(questionSurveyTable);
                    } while (cursor.moveToNext());
                }
                if (cursor == null) {
                    return;
                }
            } catch (SQLException unused) {
                LogUtil.b(TAG, "getOldSurveyDBData SQLException");
                if (cursor == null) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public long insertDateBaseTable(HwNpsManager hwNpsManager, QuestionSurveyTable questionSurveyTable) {
        if (questionSurveyTable == null) {
            return -1L;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("deviceID", questionSurveyTable.getDeviceId());
        contentValues.put(COLUMN_LAST_SURVEY_TIME, Long.valueOf(questionSurveyTable.getLastSurveyTime()));
        contentValues.put("deviceType", questionSurveyTable.getDeviceType());
        contentValues.put("times", Integer.valueOf(questionSurveyTable.getTimes()));
        contentValues.put("surveyID", questionSurveyTable.getSurveyId());
        contentValues.put(COLUMN_QUESTION_ID, Integer.valueOf(questionSurveyTable.getId()));
        return hwNpsManager.insertStorageData(DATABASE_TABLE, 1, contentValues);
    }

    public boolean isDeviceIdInDateBaseTable(HwNpsManager hwNpsManager, String str) {
        boolean z;
        boolean z2 = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = hwNpsManager.queryStorageData(DATABASE_TABLE, 1, "deviceID='" + str + PUBLIC_SYMBOL);
                if (cursor != null) {
                    z = cursor.moveToFirst();
                    try {
                        cursor.close();
                        z2 = z;
                    } catch (SQLException unused) {
                        LogUtil.b(TAG, "isDeviceIDInDBTable SQLException");
                        return z;
                    }
                }
                if (cursor == null) {
                    return z2;
                }
                cursor.close();
                return z2;
            } catch (SQLException unused2) {
                z = false;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public QuestionSurveyTable selectSurveyTableByDeviceId(HwNpsManager hwNpsManager, String str) {
        Throwable th;
        Cursor cursor;
        QuestionSurveyTable questionSurveyTable;
        Cursor cursor2 = null;
        r0 = null;
        QuestionSurveyTable questionSurveyTable2 = null;
        QuestionSurveyTable questionSurveyTable3 = null;
        cursor2 = null;
        if (str == null) {
            return null;
        }
        try {
            try {
                cursor = hwNpsManager.queryStorageData(DATABASE_TABLE, 1, "deviceID='" + str + PUBLIC_SYMBOL);
                if (cursor != null) {
                    try {
                        try {
                            if (cursor.moveToFirst()) {
                                QuestionSurveyTable questionSurveyTable4 = new QuestionSurveyTable();
                                try {
                                    questionSurveyTable4.setDeviceId(cursor.getString(cursor.getColumnIndex("deviceID")));
                                    questionSurveyTable4.setLastSurveyTime(cursor.getLong(cursor.getColumnIndex(COLUMN_LAST_SURVEY_TIME)));
                                    questionSurveyTable4.setDeviceType(cursor.getString(cursor.getColumnIndex("deviceType")));
                                    questionSurveyTable4.setTimes(cursor.getInt(cursor.getColumnIndex("times")));
                                    questionSurveyTable4.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_QUESTION_ID)));
                                    questionSurveyTable4.setSurveyId(cursor.getString(cursor.getColumnIndex("surveyID")));
                                    questionSurveyTable3 = questionSurveyTable4;
                                } catch (SQLException unused) {
                                    questionSurveyTable2 = questionSurveyTable4;
                                    QuestionSurveyTable questionSurveyTable5 = questionSurveyTable2;
                                    cursor2 = cursor;
                                    questionSurveyTable = questionSurveyTable5;
                                    LogUtil.b(TAG, "selectSurveyTableByDeviceId SQLException");
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    return questionSurveyTable;
                                }
                            }
                        } catch (SQLException unused2) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursor == null) {
                    return questionSurveyTable3;
                }
                cursor.close();
                return questionSurveyTable3;
            } catch (SQLException unused3) {
                questionSurveyTable = null;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = cursor2;
        }
    }

    public int updateSurveyTableByDeviceId(HwNpsManager hwNpsManager, QuestionSurveyTable questionSurveyTable) {
        if (questionSurveyTable == null) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LAST_SURVEY_TIME, Long.valueOf(questionSurveyTable.getLastSurveyTime()));
        contentValues.put("times", Integer.valueOf(questionSurveyTable.getTimes()));
        contentValues.put("surveyID", questionSurveyTable.getSurveyId());
        contentValues.put(COLUMN_QUESTION_ID, Integer.valueOf(questionSurveyTable.getId()));
        return hwNpsManager.updateStorageData(DATABASE_TABLE, 1, contentValues, "deviceID ='" + questionSurveyTable.getDeviceId() + PUBLIC_SYMBOL);
    }
}
