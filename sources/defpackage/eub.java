package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.plan.model.data.BaseDao;
import com.huawei.health.plan.model.util.SqlUtil;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.Classify;
import com.huawei.pluginfitnessadvice.EquipmentWorkoutAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.util.List;

/* loaded from: classes3.dex */
public class eub extends BaseDao {
    public static final String e = new SqlUtil.b().a("recordId").e().e(JsbMapKeyNames.H5_USER_ID, SqlUtil.SqliteColumnType.TEXT).e(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, SqlUtil.SqliteColumnType.TEXT).e("name", SqlUtil.SqliteColumnType.TEXT).e("exerciseTimes", SqlUtil.SqliteColumnType.INTEGER).e("isSupportDevice", SqlUtil.SqliteColumnType.INTEGER).e("trainingPoints", SqlUtil.SqliteColumnType.TEXT).e("difficulty", SqlUtil.SqliteColumnType.INTEGER).e("equipments", SqlUtil.SqliteColumnType.TEXT).e("classes", SqlUtil.SqliteColumnType.TEXT).e("duration", SqlUtil.SqliteColumnType.INTEGER).e("calorie", SqlUtil.SqliteColumnType.REAL).e("distance", SqlUtil.SqliteColumnType.REAL).e("distance", SqlUtil.SqliteColumnType.REAL).e("picture", SqlUtil.SqliteColumnType.TEXT).e("midPicture", SqlUtil.SqliteColumnType.TEXT).e("description", SqlUtil.SqliteColumnType.TEXT).e("stage", SqlUtil.SqliteColumnType.INTEGER).e("users", SqlUtil.SqliteColumnType.INTEGER).e("version", SqlUtil.SqliteColumnType.TEXT).e("narrowPicture", SqlUtil.SqliteColumnType.TEXT).e("narrowDesc", SqlUtil.SqliteColumnType.TEXT).e("workoutActions", SqlUtil.SqliteColumnType.TEXT).e("publishDate", SqlUtil.SqliteColumnType.INTEGER).e("modified", SqlUtil.SqliteColumnType.INTEGER).e("type", SqlUtil.SqliteColumnType.INTEGER).e("measurementType", SqlUtil.SqliteColumnType.INTEGER).e("runActionNum", SqlUtil.SqliteColumnType.INTEGER).e("warmUpRunAction", SqlUtil.SqliteColumnType.TEXT).e("coolDownRunAction", SqlUtil.SqliteColumnType.TEXT).e("sequenceName", SqlUtil.SqliteColumnType.TEXT).e("sequenceStage", SqlUtil.SqliteColumnType.TEXT).e("repeatTimes", SqlUtil.SqliteColumnType.INTEGER).e("relativeCourses", SqlUtil.SqliteColumnType.TEXT).e("extendSeaMap", SqlUtil.SqliteColumnType.TEXT).e("secondClassify", SqlUtil.SqliteColumnType.TEXT).e("primaryClassify", SqlUtil.SqliteColumnType.TEXT).e("topicPreviewPicUrl", SqlUtil.SqliteColumnType.TEXT).e("supplierLogoUrl", SqlUtil.SqliteColumnType.TEXT).e(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE, SqlUtil.SqliteColumnType.INTEGER).e("musicRunFlag", SqlUtil.SqliteColumnType.INTEGER).e("courseAttr", SqlUtil.SqliteColumnType.INTEGER).e("category", SqlUtil.SqliteColumnType.INTEGER).e("courseLibraryFlag", SqlUtil.SqliteColumnType.INTEGER).e(WorkoutRecord.Extend.COURSE_DEFINE_TYPE, SqlUtil.SqliteColumnType.INTEGER).e("lan", SqlUtil.SqliteColumnType.TEXT).e("extendProperty", SqlUtil.SqliteColumnType.TEXT).e("commodityFlag", SqlUtil.SqliteColumnType.INTEGER).e("iconDisplay", SqlUtil.SqliteColumnType.INTEGER).e("iconMessages", SqlUtil.SqliteColumnType.TEXT).e("isAi", SqlUtil.SqliteColumnType.INTEGER).b();

    public eub() {
        d();
    }

    private void d() {
        int f = f();
        int length = e.split(",").length;
        ReleaseLogUtil.e("Suggestion_FitWorkoutInfoDao", "table column num: ", Integer.valueOf(f), " create column num:", Integer.valueOf(length));
        if (f < length) {
            boolean a2 = a();
            ReleaseLogUtil.e("Suggestion_FitWorkoutInfoDao", "delete table: ", Boolean.valueOf(a2));
            if (a2) {
                b();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0045, code lost:
    
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0043, code lost:
    
        if (r4 == null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0033, code lost:
    
        if (r4 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0048, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int f() {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "select * from "
            r0.<init>(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = e()
            java.lang.String r1 = r1.getTableFullName(r2)
            r0.append(r1)
            java.lang.String r1 = " LIMIT 1"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 0
            java.lang.String[] r2 = new java.lang.String[r1]
            r3 = 1
            r4 = 0
            ett r5 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L36 android.database.sqlite.SQLiteException -> L38
            android.database.Cursor r4 = r5.rawQueryStorageData(r3, r0, r2)     // Catch: java.lang.Throwable -> L36 android.database.sqlite.SQLiteException -> L38
            if (r4 == 0) goto L33
            int r0 = r4.getColumnCount()     // Catch: java.lang.Throwable -> L36 android.database.sqlite.SQLiteException -> L38
            r1 = r0
        L33:
            if (r4 == 0) goto L48
            goto L45
        L36:
            r0 = move-exception
            goto L49
        L38:
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L36
            java.lang.String r2 = "getColumnNum(), SQLiteException"
            r0[r1] = r2     // Catch: java.lang.Throwable -> L36
            java.lang.String r2 = "Suggestion_FitWorkoutInfoDao"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r2, r0)     // Catch: java.lang.Throwable -> L36
            if (r4 == 0) goto L48
        L45:
            r4.close()
        L48:
            return r1
        L49:
            if (r4 == 0) goto L4e
            r4.close()
        L4e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eub.f():int");
    }

    private boolean a() {
        return ett.i().deleteTable(String.valueOf(ett.i().getModuleId()), e(), 1);
    }

    private void b() {
        ReleaseLogUtil.e("Suggestion_FitWorkoutInfoDao", "create table: ", Integer.valueOf(ett.i().createStorageDataTable(e(), 1, e)));
    }

    public void b(String str, FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            ReleaseLogUtil.c("Suggestion_FitWorkoutInfoDao", "fitWorkout is null");
            return;
        }
        ReleaseLogUtil.e("Suggestion_FitWorkoutInfoDao", "enter insertOrUpdateWorkout", fitWorkout.acquireId());
        FitWorkout a2 = a(str, fitWorkout);
        if (a2 != null) {
            int acquireUsers = a2.acquireUsers();
            int acquireUsers2 = fitWorkout.acquireUsers();
            if (acquireUsers > acquireUsers2) {
                fitWorkout.saveUsers(acquireUsers);
            }
            LogUtil.a("Suggestion_FitWorkoutInfoDao", "insertFitWorkout oldFitWorkoutUsers = ", Integer.valueOf(acquireUsers), " fitWorkoutUsers = ", Integer.valueOf(acquireUsers2));
            int acquireExerciseTimes = a2.acquireExerciseTimes();
            int acquireExerciseTimes2 = fitWorkout.acquireExerciseTimes();
            if (acquireExerciseTimes > acquireExerciseTimes2) {
                fitWorkout.saveExerciseTimes(acquireExerciseTimes);
            }
            LogUtil.a("Suggestion_FitWorkoutInfoDao", "insertFitWorkout oldAcquireExerciseTimes = ", Integer.valueOf(acquireExerciseTimes), " acquireExerciseTimes = ", Integer.valueOf(acquireExerciseTimes2));
            if (fitWorkout.acquireWorkoutActions() == null) {
                fitWorkout.saveWorkoutActions(a2.acquireWorkoutActions());
                LogUtil.a("Suggestion_FitWorkoutInfoDao", "insertFitWorkout fitWorkout.acquireWorkoutActions() == null");
            }
            c(fitWorkout, a2);
            e(str, fitWorkout);
            return;
        }
        d(str, fitWorkout);
    }

    private void c(FitWorkout fitWorkout, FitWorkout fitWorkout2) {
        if (fitWorkout == null || fitWorkout2 == null) {
            Object[] objArr = new Object[2];
            objArr[0] = "updateOldFitWorkout ";
            objArr[1] = Boolean.valueOf(fitWorkout == null);
            ReleaseLogUtil.d("Suggestion_FitWorkoutInfoDao", objArr);
            return;
        }
        ReleaseLogUtil.e("Suggestion_FitWorkoutInfoDao", "enter updateOldFitWorkout ");
        if (fitWorkout.acquireRunActionNum() == 0) {
            fitWorkout.saveRunActionNum(fitWorkout2.acquireRunActionNum());
        }
        if (fitWorkout.acquireSequenceStage() == null) {
            fitWorkout.saveSequenceStage(fitWorkout2.acquireSequenceStage());
        }
        if (fitWorkout.acquireRepeatTimes() == 0) {
            fitWorkout.saveRepeatTimes(fitWorkout2.acquireRepeatTimes());
        }
        if (fitWorkout.acquireSequenceName() == null) {
            fitWorkout.saveSequenceName(fitWorkout2.acquireSequenceName());
        }
        if (fitWorkout.acquireWarmUpRunAction() == null) {
            fitWorkout.saveWarmUpRunAction(fitWorkout2.acquireWarmUpRunAction());
        }
        if (fitWorkout.acquireCoolDownRunAction() == null) {
            fitWorkout.saveCoolDownRunAction(fitWorkout2.acquireCoolDownRunAction());
        }
        if (fitWorkout.getMusicRunFlag() == -1) {
            fitWorkout.setMusicRunFlag(fitWorkout2.getMusicRunFlag());
        }
    }

    private void d(String str, FitWorkout fitWorkout) {
        ContentValues asf_ = asf_(str, fitWorkout);
        long insertStorageData = ett.i().insertStorageData(e(), 1, asf_);
        if (insertStorageData < 0) {
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_GET_FITNESS_DATA_85070018.value(), (int) insertStorageData);
        } else {
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_GET_FITNESS_DATA_85070018.value(), 0);
        }
        ReleaseLogUtil.e("Suggestion_FitWorkoutInfoDao", "insertWorkout：", Long.valueOf(insertStorageData));
        LogUtil.c("Suggestion_FitWorkoutInfoDao", "insertWorkout：", asf_);
    }

    public void d(String str, String str2) {
        LogUtil.a("Suggestion_FitWorkoutInfoDao", "delWorkout workoutId= ", str2);
        ett.i().deleteStorageData(e(), 1, "userId=? AND workoutId=?", new String[]{StringUtils.c((Object) str), StringUtils.c((Object) str2)});
    }

    private ContentValues asf_(String str, FitWorkout fitWorkout) {
        ContentValues contentValues = new ContentValues();
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_FitWorkoutInfoDao", "getContentValues, workout == null");
            return contentValues;
        }
        ase_(str, fitWorkout, contentValues);
        if (fitWorkout.isNewRunCourse() && koq.c(fitWorkout.getCourseActions())) {
            contentValues.put("workoutActions", new Gson().toJson(fitWorkout.getCourseActions()));
            LogUtil.a("Suggestion_FitWorkoutInfoDao", "TYPE_RUN_NEW value: ", new Gson().toJson(fitWorkout.getCourseActions()));
        } else if (fitWorkout.isEquipmentCourse() && fitWorkout.getEquipmentWorkoutAction() != null) {
            contentValues.put("workoutActions", new Gson().toJson(fitWorkout.getEquipmentWorkoutAction()));
            LogUtil.a("Suggestion_FitWorkoutInfoDao", "TYPE_EQUIPMENT value:", new Gson().toJson(fitWorkout.getEquipmentWorkoutAction()));
        } else if (fitWorkout.acquireWorkoutActions() != null) {
            contentValues.put("workoutActions", new Gson().toJson(fitWorkout.acquireWorkoutActions()));
            LogUtil.c("Suggestion_FitWorkoutInfoDao", "workout Type: ", Integer.valueOf(fitWorkout.getType()), " value: ", new Gson().toJson(fitWorkout.acquireWorkoutActions()));
        }
        if (fitWorkout.acquireListRelativeWorkouts() != null) {
            contentValues.put("relativeCourses", new Gson().toJson(fitWorkout.acquireListRelativeWorkouts()));
        }
        if (fitWorkout.acquireExtendSeaMap() != null && !fitWorkout.acquireExtendSeaMap().isEmpty()) {
            contentValues.put("extendSeaMap", fitWorkout.acquireExtendSeaMap());
        }
        contentValues.put("secondClassify", String.valueOf(fitWorkout.acquireClassifyInfo()));
        contentValues.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE, Integer.valueOf(fitWorkout.getWorkoutType()));
        contentValues.put("primaryClassify", String.valueOf(fitWorkout.getPrimaryClassify()));
        contentValues.put("topicPreviewPicUrl", fitWorkout.getTopicPreviewPicUrl());
        contentValues.put("supplierLogoUrl", fitWorkout.getSupplierLogoUrl());
        contentValues.put("musicRunFlag", Integer.valueOf(fitWorkout.getMusicRunFlag()));
        contentValues.put("courseAttr", Integer.valueOf(fitWorkout.getCourseAttr()));
        contentValues.put("category", Integer.valueOf(fitWorkout.getCategory()));
        contentValues.put("courseLibraryFlag", Integer.valueOf(fitWorkout.getCourseLibraryFlag()));
        contentValues.put(WorkoutRecord.Extend.COURSE_DEFINE_TYPE, Integer.valueOf(fitWorkout.getCourseDefineType()));
        contentValues.put("lan", fitWorkout.getLan());
        contentValues.put("extendProperty", moj.e(fitWorkout.getExtendProperty()));
        contentValues.put("commodityFlag", Integer.valueOf(fitWorkout.acquireCommodityFlag()));
        contentValues.put("iconDisplay", Integer.valueOf(fitWorkout.getCornerImgDisplay()));
        List<PriceTagBean> acquirePriceTagBeanList = fitWorkout.acquirePriceTagBeanList();
        if (acquirePriceTagBeanList != null && !acquirePriceTagBeanList.isEmpty()) {
            contentValues.put("iconMessages", new Gson().toJson(acquirePriceTagBeanList));
        }
        contentValues.put("isAi", Integer.valueOf(fitWorkout.getIsAi()));
        LogUtil.c("Suggestion_FitWorkoutInfoDao", "getContentValues() workout=", fitWorkout.acquireId(), ", musicFlag=", Integer.valueOf(fitWorkout.getMusicRunFlag()), ", workoutGender=", Integer.valueOf(fitWorkout.getCourseAttr()));
        return contentValues;
    }

    private void ase_(String str, FitWorkout fitWorkout, ContentValues contentValues) {
        contentValues.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, fitWorkout.acquireId());
        contentValues.put("name", fitWorkout.acquireName());
        contentValues.put("isSupportDevice", Integer.valueOf(fitWorkout.acquireIsSupportDevice()));
        contentValues.put("trainingPoints", String.valueOf(fitWorkout.acquireTrainingpoints()));
        contentValues.put("difficulty", Integer.valueOf(fitWorkout.acquireDifficulty()));
        contentValues.put("equipments", String.valueOf(fitWorkout.acquireEquipments()));
        contentValues.put("classes", String.valueOf(fitWorkout.acquireClasses()));
        contentValues.put("duration", Integer.valueOf(fitWorkout.acquireDuration()));
        contentValues.put("calorie", Float.valueOf(fitWorkout.acquireCalorie()));
        contentValues.put("picture", fitWorkout.acquirePicture());
        contentValues.put("description", fitWorkout.acquireDescription());
        contentValues.put("users", Integer.valueOf(fitWorkout.acquireUsers()));
        contentValues.put("stage", Integer.valueOf(fitWorkout.acquireStage()));
        contentValues.put("version", fitWorkout.accquireVersion());
        contentValues.put("publishDate", Long.valueOf(fitWorkout.getPublishDate()));
        contentValues.put("modified", Long.valueOf(fitWorkout.getModified()));
        contentValues.put(JsbMapKeyNames.H5_USER_ID, StringUtils.c((Object) str));
        contentValues.put("exerciseTimes", Integer.valueOf(fitWorkout.acquireExerciseTimes()));
        contentValues.put("narrowPicture", fitWorkout.getNarrowPicture());
        contentValues.put("narrowDesc", fitWorkout.acquireNarrowDesc());
        contentValues.put("midPicture", fitWorkout.acquireMidPicture());
        contentValues.put("distance", Double.valueOf(fitWorkout.acquireDistance()));
        contentValues.put("type", Integer.valueOf(fitWorkout.getType()));
        contentValues.put("measurementType", Integer.valueOf(fitWorkout.acquireMeasurementType()));
        contentValues.put("warmUpRunAction", new Gson().toJson(fitWorkout.acquireWarmUpRunAction()));
        contentValues.put("coolDownRunAction", new Gson().toJson(fitWorkout.acquireCoolDownRunAction()));
        contentValues.put("sequenceName", fitWorkout.acquireSequenceName());
        contentValues.put("sequenceStage", fitWorkout.acquireSequenceStage());
        contentValues.put("repeatTimes", Integer.valueOf(fitWorkout.acquireRepeatTimes()));
        contentValues.put("runActionNum", Integer.valueOf(fitWorkout.acquireRunActionNum()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x006b, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0068, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0066, code lost:
    
        if (r6 == null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0057, code lost:
    
        if (r6 == null) goto L21;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0071  */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.pluginfitnessadvice.FitWorkout a(java.lang.String r6, com.huawei.pluginfitnessadvice.FitWorkout r7) {
        /*
            r5 = this;
            java.lang.String r0 = "Suggestion_FitWorkoutInfoDao"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "select * from "
            r1.<init>(r2)
            ett r2 = defpackage.ett.i()
            java.lang.String r3 = e()
            java.lang.String r2 = r2.getTableFullName(r3)
            r1.append(r2)
            java.lang.String r2 = " where workoutId=? and userId=?"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r7 = r7.acquireId()
            java.lang.String[] r6 = new java.lang.String[]{r7, r6}
            r7 = 0
            r2 = 1
            r3 = 0
            ett r4 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L5a android.database.sqlite.SQLiteException -> L5c
            android.database.Cursor r6 = r4.rawQueryStorageData(r2, r1, r6)     // Catch: java.lang.Throwable -> L5a android.database.sqlite.SQLiteException -> L5c
            if (r6 == 0) goto L42
            boolean r1 = r6.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L6c
            if (r1 == 0) goto L42
            com.huawei.pluginfitnessadvice.FitWorkout r7 = r5.asi_(r6)     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L6c
            r3 = r7
            goto L57
        L42:
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L6c
            java.lang.String r4 = "hasExistWorkout(), cursor is null or end "
            r1[r7] = r4     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L6c
            if (r6 != 0) goto L4d
            r4 = r2
            goto L4e
        L4d:
            r4 = r7
        L4e:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L6c
            r1[r2] = r4     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L6c
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r1)     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L6c
        L57:
            if (r6 == 0) goto L6b
            goto L68
        L5a:
            r6 = move-exception
            goto L6f
        L5c:
            r6 = r3
        L5d:
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L6c
            java.lang.String r2 = "hasExistWorkout(), SQLiteException"
            r1[r7] = r2     // Catch: java.lang.Throwable -> L6c
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r1)     // Catch: java.lang.Throwable -> L6c
            if (r6 == 0) goto L6b
        L68:
            r6.close()
        L6b:
            return r3
        L6c:
            r7 = move-exception
            r3 = r6
            r6 = r7
        L6f:
            if (r3 == 0) goto L74
            r3.close()
        L74:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eub.a(java.lang.String, com.huawei.pluginfitnessadvice.FitWorkout):com.huawei.pluginfitnessadvice.FitWorkout");
    }

    protected FitWorkout asi_(Cursor cursor) {
        FitWorkout fitWorkout = new FitWorkout();
        if (cursor == null) {
            return fitWorkout;
        }
        asg_(cursor, fitWorkout);
        String string = cursor.getString(cursor.getColumnIndex("workoutActions"));
        if (!TextUtils.isEmpty(string)) {
            if (fitWorkout.isNewRunCourse()) {
                try {
                    fitWorkout.setCourseActions(moj.b(string, ChoreographedMultiActions[].class));
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("Suggestion_FitWorkoutInfoDao", "exception message = ", LogAnonymous.b((Throwable) e2));
                    LogUtil.b("Suggestion_FitWorkoutInfoDao", "workoutActionsJson = ", string);
                }
            } else if (fitWorkout.isEquipmentCourse()) {
                try {
                    fitWorkout.setEquipmentWorkoutAction((EquipmentWorkoutAction) moj.e(string, EquipmentWorkoutAction.class));
                } catch (JsonSyntaxException e3) {
                    LogUtil.b("Suggestion_FitWorkoutInfoDao", "exception message = ", LogAnonymous.b((Throwable) e3));
                    LogUtil.b("Suggestion_FitWorkoutInfoDao", "workoutActionsJson = ", string);
                }
            } else {
                try {
                    fitWorkout.saveWorkoutActions(moj.b(string, WorkoutAction[].class));
                } catch (JsonSyntaxException e4) {
                    LogUtil.b("Suggestion_FitWorkoutInfoDao", "exception message = ", LogAnonymous.b((Throwable) e4));
                    LogUtil.b("Suggestion_FitWorkoutInfoDao", "workoutActionsJson = ", string);
                }
            }
        }
        ash_(cursor, fitWorkout);
        fitWorkout.setCourseLibraryFlag(cursor.getInt(cursor.getColumnIndex("courseLibraryFlag")));
        fitWorkout.setCourseDefineType(cursor.getInt(cursor.getColumnIndex(WorkoutRecord.Extend.COURSE_DEFINE_TYPE)));
        fitWorkout.setLan(cursor.getString(cursor.getColumnIndex("lan")));
        fitWorkout.setExtendProperty(moj.a(cursor.getString(cursor.getColumnIndex("extendProperty"))));
        fitWorkout.setCommodityFlag(cursor.getInt(cursor.getColumnIndex("commodityFlag")));
        fitWorkout.setCornerImgDisplay(cursor.getInt(cursor.getColumnIndex("iconDisplay")));
        String string2 = cursor.getString(cursor.getColumnIndex("iconMessages"));
        if (!TextUtils.isEmpty(string2)) {
            try {
                fitWorkout.setPriceTagBeanList(moj.b(string2, PriceTagBean[].class));
            } catch (JsonSyntaxException e5) {
                LogUtil.b("Suggestion_FitWorkoutInfoDao", "exception message = ", LogAnonymous.b((Throwable) e5));
                LogUtil.b("Suggestion_FitWorkoutInfoDao", "workoutActionsJson = ", string2);
            }
        }
        fitWorkout.setIsAi(eup.asu_(cursor, "isAi"));
        asc_(fitWorkout, cursor);
        return fitWorkout;
    }

    private void ash_(Cursor cursor, FitWorkout fitWorkout) {
        fitWorkout.savePublishDate(cursor.getLong(cursor.getColumnIndex("publishDate")));
        fitWorkout.saveModified(cursor.getLong(cursor.getColumnIndex("modified")));
        fitWorkout.saveMidPicture(cursor.getString(cursor.getColumnIndex("midPicture")));
        String string = cursor.getString(cursor.getColumnIndex("warmUpRunAction"));
        if (!TextUtils.isEmpty(string)) {
            fitWorkout.saveWarmUpRunAction((WorkoutAction) moj.e(string, WorkoutAction.class));
        }
        String string2 = cursor.getString(cursor.getColumnIndex("coolDownRunAction"));
        if (!TextUtils.isEmpty(string2)) {
            fitWorkout.saveCoolDownRunAction((WorkoutAction) moj.e(string2, WorkoutAction.class));
        }
        fitWorkout.saveSequenceName(cursor.getString(cursor.getColumnIndex("sequenceName")));
        fitWorkout.saveSequenceStage(cursor.getString(cursor.getColumnIndex("sequenceStage")));
        fitWorkout.saveRepeatTimes(cursor.getInt(cursor.getColumnIndex("repeatTimes")));
        fitWorkout.saveRunActionNum(cursor.getInt(cursor.getColumnIndex("runActionNum")));
        fitWorkout.saveListRelativeWorkouts(moj.b(cursor.getString(cursor.getColumnIndex("relativeCourses")), String[].class));
        fitWorkout.saveExtendSeaMap(cursor.getString(cursor.getColumnIndex("extendSeaMap")));
    }

    private void asg_(Cursor cursor, FitWorkout fitWorkout) {
        fitWorkout.saveId(cursor.getString(cursor.getColumnIndex(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)));
        fitWorkout.saveName(cursor.getString(cursor.getColumnIndex("name")));
        fitWorkout.saveIsSupportDevice(cursor.getInt(cursor.getColumnIndex("isSupportDevice")));
        fitWorkout.saveTrainingpoints(moj.b(cursor.getString(cursor.getColumnIndex("trainingPoints")), Attribute[].class));
        fitWorkout.saveDifficulty(cursor.getInt(cursor.getColumnIndex("difficulty")));
        fitWorkout.saveEquipments(moj.b(cursor.getString(cursor.getColumnIndex("equipments")), Attribute[].class));
        fitWorkout.saveClasses(moj.b(cursor.getString(cursor.getColumnIndex("classes")), Attribute[].class));
        fitWorkout.saveDuration(cursor.getInt(cursor.getColumnIndex("duration")));
        fitWorkout.saveCalorie(cursor.getFloat(cursor.getColumnIndex("calorie")));
        fitWorkout.savePicture(cursor.getString(cursor.getColumnIndex("picture")));
        fitWorkout.saveDescription(cursor.getString(cursor.getColumnIndex("description")));
        fitWorkout.saveUsers(cursor.getInt(cursor.getColumnIndex("users")));
        fitWorkout.saveStage(cursor.getInt(cursor.getColumnIndex("stage")));
        fitWorkout.saveExerciseTimes(cursor.getInt(cursor.getColumnIndex("exerciseTimes")));
        fitWorkout.saveVersion(cursor.getString(cursor.getColumnIndex("version")));
        fitWorkout.saveNarrowPicture(cursor.getString(cursor.getColumnIndex("narrowPicture")));
        fitWorkout.saveNarrowDesc(cursor.getString(cursor.getColumnIndex("narrowDesc")));
        fitWorkout.saveDistance(cursor.getFloat(cursor.getColumnIndex("distance")));
        fitWorkout.setType(cursor.getInt(cursor.getColumnIndex("type")));
        fitWorkout.saveMeasurementType(cursor.getInt(cursor.getColumnIndex("measurementType")));
        fitWorkout.setWorkoutType(cursor.getInt(cursor.getColumnIndex(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE)));
        fitWorkout.setTopicPreviewPicUrl(cursor.getString(cursor.getColumnIndex("topicPreviewPicUrl")));
        fitWorkout.setSupplierLogoUrl(cursor.getString(cursor.getColumnIndex("supplierLogoUrl")));
        fitWorkout.setMusicRunFlag(cursor.getInt(cursor.getColumnIndex("musicRunFlag")));
        fitWorkout.setCourseAttr(cursor.getInt(cursor.getColumnIndex("courseAttr")));
        fitWorkout.setCategory(cursor.getInt(cursor.getColumnIndex("category")));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0084  */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.pluginfitnessadvice.FitWorkout d(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r6 = this;
            java.lang.String r0 = "getWorkout(String userId,String workoutId, String version) workoutId = "
            java.lang.String r1 = ",version="
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r8, r1, r9}
            java.lang.String r1 = "Suggestion_FitWorkoutInfoDao"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "select * from "
            r0.<init>(r2)
            ett r3 = defpackage.ett.i()
            java.lang.String r4 = e()
            java.lang.String r3 = r3.getTableFullName(r4)
            r0.append(r3)
            java.lang.String r3 = " where workoutId=? and userId=? and version=?"
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String[] r3 = new java.lang.String[]{r8, r7, r9}
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 == 0) goto L57
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r2)
            ett r0 = defpackage.ett.i()
            java.lang.String r2 = e()
            java.lang.String r0 = r0.getTableFullName(r2)
            r9.append(r0)
            java.lang.String r0 = " where workoutId=? and userId=? ORDER BY version+0 DESC limit 0,1"
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            java.lang.String[] r3 = new java.lang.String[]{r8, r7}
        L57:
            r7 = 0
            r8 = 1
            r9 = 0
            ett r2 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L8e android.database.sqlite.SQLiteException -> L90
            android.database.Cursor r0 = r2.rawQueryStorageData(r8, r0, r3)     // Catch: java.lang.Throwable -> L8e android.database.sqlite.SQLiteException -> L90
            if (r0 == 0) goto L79
            boolean r2 = r0.moveToNext()     // Catch: java.lang.Throwable -> L88 android.database.sqlite.SQLiteException -> L8a
            if (r2 == 0) goto L79
            com.huawei.pluginfitnessadvice.FitWorkout r2 = r6.asi_(r0)     // Catch: java.lang.Throwable -> L88 android.database.sqlite.SQLiteException -> L8a
            boolean r7 = r6.d(r2)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L88
            if (r7 == 0) goto L75
            goto L82
        L75:
            r9 = r2
            goto L82
        L77:
            r9 = r2
            goto L8a
        L79:
            java.lang.Object[] r2 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L88 android.database.sqlite.SQLiteException -> L8a
            java.lang.String r3 = "getWorkout cursor == null"
            r2[r7] = r3     // Catch: java.lang.Throwable -> L88 android.database.sqlite.SQLiteException -> L8a
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r2)     // Catch: java.lang.Throwable -> L88 android.database.sqlite.SQLiteException -> L8a
        L82:
            if (r0 == 0) goto La0
            r0.close()
            goto La0
        L88:
            r7 = move-exception
            goto Lab
        L8a:
            r5 = r0
            r0 = r9
            r9 = r5
            goto L91
        L8e:
            r7 = move-exception
            goto Laa
        L90:
            r0 = r9
        L91:
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L8e
            java.lang.String r2 = "getWorkout(), SQLiteException"
            r8[r7] = r2     // Catch: java.lang.Throwable -> L8e
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r1, r8)     // Catch: java.lang.Throwable -> L8e
            if (r9 == 0) goto L9f
            r9.close()
        L9f:
            r9 = r0
        La0:
            java.lang.String r7 = "getWorkout(String userId,String workoutId, String version) = "
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r9}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r7)
            return r9
        Laa:
            r0 = r9
        Lab:
            if (r0 == 0) goto Lb0
            r0.close()
        Lb0:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eub.d(java.lang.String, java.lang.String, java.lang.String):com.huawei.pluginfitnessadvice.FitWorkout");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.pluginfitnessadvice.FitWorkout b(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r6 = this;
            java.lang.String r0 = "getWorkout(String userId,String workoutId, String version) workoutId = "
            java.lang.String r2 = ",version="
            java.lang.String r4 = "language="
            r1 = r8
            r3 = r9
            r5 = r10
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2, r3, r4, r5}
            java.lang.String r1 = "Suggestion_FitWorkoutInfoDao"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "select * from "
            r0.<init>(r2)
            ett r3 = defpackage.ett.i()
            java.lang.String r4 = c(r10)
            java.lang.String r3 = r3.getTableFullName(r4)
            r0.append(r3)
            java.lang.String r3 = " where workoutId=? and userId=? and version=?"
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String[] r3 = new java.lang.String[]{r8, r7, r9}
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 == 0) goto L5c
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r2)
            ett r0 = defpackage.ett.i()
            java.lang.String r10 = c(r10)
            java.lang.String r10 = r0.getTableFullName(r10)
            r9.append(r10)
            java.lang.String r10 = " where workoutId=? and userId=? ORDER BY version+0 DESC limit 0,1"
            r9.append(r10)
            java.lang.String r0 = r9.toString()
            java.lang.String[] r3 = new java.lang.String[]{r8, r7}
        L5c:
            r7 = 2
            r8 = 0
            r9 = 0
            r10 = 1
            ett r2 = defpackage.ett.i()     // Catch: java.lang.Throwable -> La4 android.database.sqlite.SQLiteException -> La6
            android.database.Cursor r0 = r2.rawQueryStorageData(r10, r0, r3)     // Catch: java.lang.Throwable -> La4 android.database.sqlite.SQLiteException -> La6
            if (r0 == 0) goto L7b
            boolean r2 = r0.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
            if (r2 == 0) goto L7b
            com.huawei.pluginfitnessadvice.FitWorkout r2 = r6.asi_(r0)     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
            boolean r3 = r6.d(r2)     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
            if (r3 == 0) goto L90
            goto L8f
        L7b:
            java.lang.Object[] r2 = new java.lang.Object[r7]     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
            java.lang.String r3 = "getWorkout cursor is null "
            r2[r8] = r3     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
            if (r0 != 0) goto L85
            r3 = r10
            goto L86
        L85:
            r3 = r8
        L86:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
            r2[r10] = r3     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r2)     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
        L8f:
            r2 = r9
        L90:
            java.lang.Object[] r3 = new java.lang.Object[r7]     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
            java.lang.String r4 = "getWorkout(String userId,String workoutId, String version) = "
            r3[r8] = r4     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
            r3[r10] = r2     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
            com.huawei.hwlogsmodel.LogUtil.c(r1, r3)     // Catch: android.database.sqlite.SQLiteException -> La2 java.lang.Throwable -> Lbe
            if (r0 == 0) goto La0
            r0.close()
        La0:
            r9 = r2
            goto Lbd
        La2:
            r2 = move-exception
            goto La9
        La4:
            r7 = move-exception
            goto Lc0
        La6:
            r0 = move-exception
            r2 = r0
            r0 = r9
        La9:
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> Lbe
            java.lang.String r3 = " getWorkout "
            r7[r8] = r3     // Catch: java.lang.Throwable -> Lbe
            java.lang.String r8 = health.compact.a.LogAnonymous.b(r2)     // Catch: java.lang.Throwable -> Lbe
            r7[r10] = r8     // Catch: java.lang.Throwable -> Lbe
            com.huawei.hwlogsmodel.LogUtil.b(r1, r7)     // Catch: java.lang.Throwable -> Lbe
            if (r0 == 0) goto Lbd
            r0.close()
        Lbd:
            return r9
        Lbe:
            r7 = move-exception
            r9 = r0
        Lc0:
            if (r9 == 0) goto Lc5
            r9.close()
        Lc5:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eub.b(java.lang.String, java.lang.String, java.lang.String, java.lang.String):com.huawei.pluginfitnessadvice.FitWorkout");
    }

    private boolean d(FitWorkout fitWorkout) {
        if (fitWorkout.isNewRunCourse()) {
            return koq.b(fitWorkout.getCourseActions());
        }
        if (fitWorkout.isEquipmentCourse()) {
            return fitWorkout.getEquipmentWorkoutAction() == null;
        }
        return koq.b(fitWorkout.acquireWorkoutActions());
    }

    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.pluginfitnessadvice.FitWorkout a(java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eub.a(java.lang.String):com.huawei.pluginfitnessadvice.FitWorkout");
    }

    private void asc_(FitWorkout fitWorkout, Cursor cursor) {
        String asd_ = asd_(cursor, "secondClassify");
        String asd_2 = asd_(cursor, "primaryClassify");
        if (!TextUtils.isEmpty(asd_) && !Constants.NULL.equals(asd_)) {
            fitWorkout.saveClassifyInfo(moj.b(asd_, Classify[].class));
        }
        if (TextUtils.isEmpty(asd_2) || Constants.NULL.equals(asd_2)) {
            return;
        }
        fitWorkout.setPrimaryClassify(moj.b(asd_2, Classify[].class));
    }

    private String asd_(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        return columnIndex != -1 ? cursor.getString(columnIndex) : "";
    }

    public void e(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("exerciseTimes", Integer.valueOf(a(str, str2, str3) + 1));
        ReleaseLogUtil.e("Suggestion_FitWorkoutInfoDao", "updateWorkoutCompleteTimes result ", Long.valueOf(ett.i().updateStorageData(e(), 1, contentValues, "workoutId=? and version=? and userId=?", new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str3), str})));
        LogUtil.c("Suggestion_FitWorkoutInfoDao", "updateWorkoutCompleteTimes：", contentValues.toString());
    }

    public void e(String str, FitWorkout fitWorkout) {
        ContentValues asf_ = asf_(str, fitWorkout);
        ReleaseLogUtil.e("Suggestion_FitWorkoutInfoDao", "updateWorkout ", Long.valueOf(ett.i().updateStorageData(e(), 1, asf_, "workoutId=? and version=? and userId=?", new String[]{StringUtils.c((Object) fitWorkout.acquireId()), StringUtils.c((Object) fitWorkout.accquireVersion()), str})));
        LogUtil.c("Suggestion_FitWorkoutInfoDao", "updateWorkout: ", asf_);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x008c, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.a("Suggestion_FitWorkoutInfoDao", "completeTimes = ", java.lang.Integer.valueOf(r8));
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0099, code lost:
    
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0089, code lost:
    
        if (r7 == null) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a(java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            java.lang.String r0 = "Suggestion_FitWorkoutInfoDao"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "select * from "
            r1.<init>(r2)
            ett r3 = defpackage.ett.i()
            java.lang.String r4 = e()
            java.lang.String r3 = r3.getTableFullName(r4)
            r1.append(r3)
            java.lang.String r3 = " where workoutId=? and version=? and userId=?"
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = health.compact.a.utils.StringUtils.c(r7)
            java.lang.String r4 = health.compact.a.utils.StringUtils.c(r8)
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r6}
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 == 0) goto L58
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r2)
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = e()
            java.lang.String r1 = r1.getTableFullName(r2)
            r8.append(r1)
            java.lang.String r1 = " where workoutId=? and userId=? ORDER BY version+0 DESC limit 0,1"
            r8.append(r1)
            java.lang.String r1 = r8.toString()
            java.lang.String r7 = health.compact.a.utils.StringUtils.c(r7)
            java.lang.String[] r3 = new java.lang.String[]{r7, r6}
        L58:
            r6 = 1
            r7 = 0
            r8 = 0
            ett r2 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteException -> L7d
            android.database.Cursor r7 = r2.rawQueryStorageData(r6, r1, r3)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteException -> L7d
            if (r7 == 0) goto L75
            boolean r1 = r7.moveToNext()     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteException -> L7d
            if (r1 == 0) goto L75
            java.lang.String r1 = "exerciseTimes"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteException -> L7d
            int r8 = r7.getInt(r1)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteException -> L7d
        L75:
            if (r7 == 0) goto L8c
        L77:
            r7.close()
            goto L8c
        L7b:
            r6 = move-exception
            goto L9a
        L7d:
            r1 = move-exception
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L7b
            java.lang.String r1 = health.compact.a.LogAnonymous.b(r1)     // Catch: java.lang.Throwable -> L7b
            r6[r8] = r1     // Catch: java.lang.Throwable -> L7b
            com.huawei.hwlogsmodel.LogUtil.b(r0, r6)     // Catch: java.lang.Throwable -> L7b
            if (r7 == 0) goto L8c
            goto L77
        L8c:
            java.lang.String r6 = "completeTimes = "
            java.lang.Integer r7 = java.lang.Integer.valueOf(r8)
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r7}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r6)
            return r8
        L9a:
            if (r7 == 0) goto L9f
            r7.close()
        L9f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eub.a(java.lang.String, java.lang.String, java.lang.String):int");
    }

    public static String e() {
        return "fit_workouts_info" + etd.c();
    }

    public static String c(String str) {
        return "fit_workouts_info" + etd.c(str);
    }

    public static String c() {
        return "fit_workouts_info";
    }
}
