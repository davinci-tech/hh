package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.view.AutoFillColorView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Audio;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.Comment;
import com.huawei.pluginfitnessadvice.EquipmentAction;
import com.huawei.pluginfitnessadvice.EquipmentStage;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Pictures;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.pluginfitnessadvice.VideoSegment;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fpq;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.HarmonyBuild;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class fpq {
    private static String c(List<Audio> list, String str) {
        if ("hotbody".equals(str)) {
            return StringUtils.c((Object) list.get(0).getUrl());
        }
        return StringUtils.c((Object) list.get(1).getUrl());
    }

    private static String d(AtomicAction atomicAction, String str, int[] iArr) {
        if ("timer".equals(ffq.e(atomicAction.getActionSportType()))) {
            iArr[0] = 1000;
            iArr[1] = CommonUtils.h(a(atomicAction, "duration"));
            return d(str);
        }
        String d = d(atomicAction, str);
        int h = CommonUtils.h(a(atomicAction, "frequency"));
        if (h != 0) {
            iArr[0] = (CommonUtils.h(a(atomicAction, "duration")) * 1000) / h;
        }
        iArr[1] = h;
        return d;
    }

    private static String d(String str) {
        return !str.equals("hotbody") ? "timer" : str;
    }

    public static void a() {
        String str;
        if (CommonUtil.bu()) {
            try {
                str = BaseApplication.e().getFilesDir().getCanonicalPath();
            } catch (IOException e) {
                LogUtil.h("Suggestion_TrainDetailHelper", "getCanonicalPath", LogAnonymous.b((Throwable) e));
                str = null;
            }
            moh.a(BaseApplication.e(), "suggestion" + File.separator + "files_runcourse" + File.separator + "audios", str);
            moh.a(BaseApplication.e(), "suggestion" + File.separator + "files_runcourse" + File.separator + "audiosBase", str);
        }
    }

    private static String d(AtomicAction atomicAction, String str) {
        if (!str.equals("hotbody")) {
            str = "beating";
        }
        if (CommonUtils.h(a(atomicAction, "duration")) < CommonUtils.h(a(atomicAction, "frequency"))) {
            atomicAction.putExtendProperty("duration", a(atomicAction, "frequency"));
        }
        return str;
    }

    private static void b(String str, Motion motion, List<Comment> list) {
        if (koq.c(list)) {
            d(str, motion, list);
        }
    }

    private static void d(String str, Motion motion, List<Comment> list) {
        if ("hotbody".equals(str) && list.size() > 1) {
            motion.saveTrainPointPath(squ.c(list.get(0).acquireName()));
            motion.setTrainAudioPath(squ.c(list.get(1).acquireName()));
        } else {
            motion.setCommentaryTraining(list);
        }
    }

    private static boolean d(Video video, String str, Motion motion) {
        if (video == null) {
            LogUtil.b("Suggestion_TrainDetailHelper", "video is null");
            return false;
        }
        if (ffy.c(video.getUrl(), video.getThumbnail(), str)) {
            motion.saveNamePath(squ.c(str));
            LogUtil.a("Suggestion_TrainDetailHelper", "url:", video.getUrl());
            motion.saveMotionPath(squ.l(video.getUrl()));
            motion.savePicUrl(video.getThumbnail());
            motion.setOriginLogo(video.getLogoImgUrl());
            return true;
        }
        LogUtil.h("Suggestion_TrainDetailHelper", "action name is wrong");
        return false;
    }

    public static void d(String str, String str2, AutoFillColorView autoFillColorView) {
        if (autoFillColorView == null) {
            LogUtil.h("Suggestion_TrainDetailHelper", "fullPic, autoFillColorView = null");
            return;
        }
        String c = TextUtils.isEmpty(str) ? StringUtils.c((Object) str2) : StringUtils.c((Object) str);
        if (TextUtils.isEmpty(c)) {
            return;
        }
        if (c.startsWith("http")) {
            autoFillColorView.b(c, true);
            return;
        }
        if (CommonUtil.bu()) {
            autoFillColorView.b("file:///android_asset/suggestion/img/" + c, true);
        } else {
            autoFillColorView.b("file:///android_asset/img/" + c, true);
        }
    }

    public static List<Motion> d(FitWorkout fitWorkout, int i) {
        List<ChoreographedSingleAction> list;
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_TrainDetailHelper", "convertCourseAction2Motions failed with null fitworkout.");
            return new ArrayList();
        }
        int acquireRepeatTimes = fitWorkout.getType() == 3 ? fitWorkout.acquireRepeatTimes() : 1;
        if (fitWorkout.isNewRunCourse() && !fitWorkout.isLongVideoCourse()) {
            list = ggs.c(fitWorkout);
        } else {
            if (fitWorkout.isEquipmentCourse()) {
                return c(fitWorkout);
            }
            List<WorkoutAction> acquireWorkoutActions = fitWorkout.acquireWorkoutActions();
            if (koq.c(acquireWorkoutActions)) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < acquireRepeatTimes; i2++) {
                    arrayList.addAll(acquireWorkoutActions);
                }
                list = ggs.a(arrayList);
            } else {
                list = null;
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (koq.b(list)) {
            ReleaseLogUtil.d("Suggestion_TrainDetailHelper", "workoutActionListAll.size is 0");
            return arrayList2;
        }
        if (fitWorkout.isLongVideoCourse()) {
            c(list, fitWorkout.acquireId(), (ArrayList<Motion>) arrayList2);
        } else if (fitWorkout.isRunModelCourse()) {
            a(list, i, fitWorkout.acquireId(), (ArrayList<Motion>) arrayList2);
        } else {
            b(list, i, fitWorkout.acquireId(), arrayList2);
        }
        return arrayList2;
    }

    public static boolean b(List<ChoreographedSingleAction> list, int i, String str, ArrayList<Motion> arrayList) {
        if (list == null) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            AtomicAction action = list.get(i2).getAction();
            ChoreographedSingleAction choreographedSingleAction = list.get(i2);
            if (ffy.c(action, choreographedSingleAction) && !e(action, choreographedSingleAction, i, str, arrayList)) {
                return false;
            }
        }
        return true;
    }

    public static void c(List<ChoreographedSingleAction> list, String str, ArrayList<Motion> arrayList) {
        if (list == null) {
            LogUtil.b("Suggestion_TrainDetailHelper", "buildLongVideoMotions workoutActions is null");
            return;
        }
        for (ChoreographedSingleAction choreographedSingleAction : list) {
            if (choreographedSingleAction != null && choreographedSingleAction.getAction() != null) {
                c(choreographedSingleAction.getAction(), choreographedSingleAction, str, arrayList);
            }
        }
    }

    private static void c(AtomicAction atomicAction, ChoreographedSingleAction choreographedSingleAction, String str, ArrayList<Motion> arrayList) {
        Video video;
        String e = ffq.e(atomicAction.getActionSportType());
        int[] iArr = {0, CommonUtils.h(a(atomicAction, "frequency"))};
        String d = d(atomicAction, e, iArr);
        Motion motion = new Motion(atomicAction.getId(), atomicAction.getName(), CommonUtils.h(a(choreographedSingleAction, MessageConstant.GROUP_MEDAL_TYPE)), iArr[1], iArr[0], d);
        b(d, motion, choreographedSingleAction.getCommentaryTraining());
        motion.saveWorkoutId(str);
        motion.setEquipments(atomicAction.getEquipments());
        motion.setTrainingPoints(atomicAction.getTrainingPoints());
        String a2 = a(atomicAction, "actionVideo");
        List emptyList = TextUtils.isEmpty(a2) ? Collections.emptyList() : (List) new Gson().fromJson(a2, new TypeToken<List<Video>>() { // from class: fpq.5
        }.getType());
        if (koq.d(emptyList, 0) && (video = (Video) emptyList.get(0)) != null) {
            if (koq.d(video.getVideoSegmentList(), 0)) {
                motion.savePicUrl(video.getVideoSegmentList().get(0).getThumbnail());
            }
            motion.setOriginLogo(video.getLogoImgUrl());
            if (koq.d(video.getVideoSegmentList(), 0)) {
                motion.setDuration(moe.c(video.getVideoSegmentList().get(0).getDuration()));
            }
            motion.setVideoSegments(video.getVideoSegmentList());
        }
        motion.saveCalorie(nsn.f(TextUtils.isEmpty(a(atomicAction, "calorie")) ? "0" : a(atomicAction, "calorie")));
        motion.saveDifficulty(atomicAction.getDifficulty());
        motion.setDescription(atomicAction.getDescription());
        motion.setGap(CommonUtils.h(a(choreographedSingleAction, "gap")));
        motion.setCommentaryGap(choreographedSingleAction.getCommentaryGap());
        motion.setActionStep(a(atomicAction, "actionStep"));
        motion.setIntroduceLyric(a(atomicAction, "introduceLyric"));
        motion.setBreath(a(atomicAction, "breath"));
        motion.setFeeling(a(atomicAction, "feeling"));
        motion.setCommonError(a(atomicAction, "commonError"));
        String a3 = a(atomicAction, "pictures");
        if (!TextUtils.isEmpty(a3)) {
            motion.setPictures((List) new Gson().fromJson(a3, new TypeToken<List<Pictures>>() { // from class: fpq.4
            }.getType()));
        }
        arrayList.add(motion);
    }

    public static boolean a(List<ChoreographedSingleAction> list, int i, String str, ArrayList<Motion> arrayList) {
        if (list == null) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            AtomicAction action = list.get(i2).getAction();
            ChoreographedSingleAction choreographedSingleAction = list.get(i2);
            if (ffy.c(action, choreographedSingleAction) && !c(action, choreographedSingleAction, i, str, arrayList)) {
                return false;
            }
        }
        return true;
    }

    public static boolean b() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Suggestion_TrainDetailHelper");
        if (deviceInfo == null) {
            LogUtil.h("Suggestion_TrainDetailHelper", "device info null");
            return false;
        }
        return cwi.c(deviceInfo, 1);
    }

    public static boolean c() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Suggestion_TrainDetailHelper");
        if (deviceInfo == null) {
            LogUtil.h("Suggestion_TrainDetailHelper", "device info null");
            return false;
        }
        return cwi.c(deviceInfo, MachineControlPointResponse.OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE);
    }

    private static boolean a(List list) {
        return list != null && list.size() > 1;
    }

    private static Video e(List<Video> list, ChoreographedSingleAction choreographedSingleAction, String str, int i) {
        if ("hotbody".equals(str)) {
            choreographedSingleAction.putExtendProperty(MessageConstant.GROUP_MEDAL_TYPE, "1");
            return list.get(0);
        }
        if (koq.d(list, i)) {
            return list.get(i);
        }
        return null;
    }

    public static boolean b(IntPlan intPlan, String str) {
        return (intPlan == null || TextUtils.isEmpty(str) || intPlan.getPlanType().equals(IntPlan.PlanType.RUN_PLAN)) ? false : true;
    }

    private static void b(AtomicAction atomicAction, Motion motion, int i) {
        if (koq.d(atomicAction.getCovers(), i)) {
            motion.saveCovers(atomicAction.getCovers().get(i));
        }
    }

    private static boolean e(AtomicAction atomicAction, ChoreographedSingleAction choreographedSingleAction, int i, String str, ArrayList<Motion> arrayList) {
        String e = ffq.e(atomicAction.getActionSportType());
        String a2 = a(atomicAction, "actionVideo");
        List emptyList = TextUtils.isEmpty(a2) ? Collections.emptyList() : (List) new Gson().fromJson(a2, new TypeToken<List<Video>>() { // from class: fpq.3
        }.getType());
        if (a(emptyList)) {
            Video e2 = e(emptyList, choreographedSingleAction, e, i);
            String a3 = a(atomicAction, "audios");
            List emptyList2 = TextUtils.isEmpty(a3) ? Collections.emptyList() : (List) new Gson().fromJson(a3, new TypeToken<List<Audio>>() { // from class: fpq.9
            }.getType());
            if (a(emptyList2)) {
                String c = c(emptyList2, e);
                int[] iArr = {0, CommonUtils.h(a(atomicAction, "frequency"))};
                String d = d(atomicAction, e, iArr);
                Motion motion = new Motion(atomicAction.getId(), atomicAction.getName(), CommonUtils.h(a(choreographedSingleAction, MessageConstant.GROUP_MEDAL_TYPE)), iArr[1], iArr[0], d);
                b(d, motion, choreographedSingleAction.getCommentaryTraining());
                if (!d(e2, c, motion)) {
                    return false;
                }
                motion.saveWorkoutId(str);
                a(motion, atomicAction, i, choreographedSingleAction);
                arrayList.add(motion);
                return true;
            }
            LogUtil.b("Suggestion_TrainDetailHelper", atomicAction.getName(), "audio is null");
            return false;
        }
        LogUtil.b("Suggestion_TrainDetailHelper", atomicAction.getName(), "video is null or data is wrong");
        return false;
    }

    private static void a(Motion motion, AtomicAction atomicAction, int i, ChoreographedSingleAction choreographedSingleAction) {
        motion.setEquipments(atomicAction.getEquipments());
        motion.setTrainingPoints(atomicAction.getTrainingPoints());
        motion.saveCalorie(nsn.f(TextUtils.isEmpty(a(atomicAction, "calorie")) ? "0" : a(atomicAction, "calorie")));
        motion.saveDifficulty(atomicAction.getDifficulty());
        motion.setDescription(atomicAction.getDescription());
        b(atomicAction, motion, i);
        motion.setGap(CommonUtils.h(a(choreographedSingleAction, "gap")));
        motion.setActionStep(a(atomicAction, "actionStep"));
        motion.setIntroduceLyric(a(atomicAction, "introduceLyric"));
        motion.setBreath(a(atomicAction, "breath"));
        motion.setFeeling(a(atomicAction, "feeling"));
        motion.setCommonError(a(atomicAction, "commonError"));
        String a2 = a(atomicAction, "pictures");
        if (!TextUtils.isEmpty(a2)) {
            LogUtil.a("Suggestion_TrainDetailHelper", "pictureString:", a2);
            motion.setPictures((List) new Gson().fromJson(a2, new TypeToken<List<Pictures>>() { // from class: fpq.6
            }.getType()));
        }
        motion.setDuration(moe.c(CommonUtils.h(a(atomicAction, "duration"))));
        motion.setCommentaryGap(choreographedSingleAction.getCommentaryGap());
    }

    private static boolean c(AtomicAction atomicAction, ChoreographedSingleAction choreographedSingleAction, int i, String str, ArrayList<Motion> arrayList) {
        int c;
        Motion motion = new Motion(atomicAction.getId(), atomicAction.getName(), CommonUtils.h(a(choreographedSingleAction, MessageConstant.GROUP_MEDAL_TYPE)), CommonUtils.h(a(atomicAction, "frequency")));
        motion.saveWorkoutId(str);
        motion.setEquipments(atomicAction.getEquipments());
        motion.setTrainingPoints(atomicAction.getTrainingPoints());
        motion.saveDifficulty(atomicAction.getDifficulty());
        motion.setDescription(atomicAction.getDescription());
        b(atomicAction, motion, i);
        motion.setGap(CommonUtils.h(a(choreographedSingleAction, "gap")));
        if (choreographedSingleAction.getTargetConfig() != null) {
            int h = CommonUtils.h(choreographedSingleAction.getTargetConfig().getId());
            motion.saveMeasurementType(h);
            if (h == 0) {
                c = ggs.d(choreographedSingleAction);
            } else {
                c = moe.c(ggs.d(choreographedSingleAction));
            }
            motion.saveMeasurementValue(c);
        }
        motion.savePicUrl(a(choreographedSingleAction, "tabloidPicUrl"));
        if (choreographedSingleAction.getIntensityConfig() != null) {
            motion.saveSpecifiedSlope(choreographedSingleAction.getIntensityConfig().getValueL());
            motion.saveSpecifiedSpeed(choreographedSingleAction.getIntensityConfig().getValueL());
        }
        motion.setOriginLogo(a(atomicAction, "logoImgUrl"));
        motion.setActionStep(a(atomicAction, "actionStep"));
        motion.setIntroduceLyric(a(atomicAction, "introduceLyric"));
        motion.setBreath(a(atomicAction, "breath"));
        motion.setFeeling(a(atomicAction, "feeling"));
        motion.setCommonError(a(atomicAction, "commonError"));
        String a2 = a(atomicAction, "pictures");
        if (!TextUtils.isEmpty(a2)) {
            motion.setPictures((List) new Gson().fromJson(a2, new TypeToken<List<Pictures>>() { // from class: fpq.10
            }.getType()));
        }
        arrayList.add(motion);
        return true;
    }

    private static String a(Object obj, String str) {
        if (obj instanceof AtomicAction) {
            Map<String, String> extendProperty = ((AtomicAction) obj).getExtendProperty();
            if (extendProperty == null) {
                LogUtil.b("Suggestion_TrainDetailHelper", "getExtendProperty getExtendProperty null");
            } else {
                return extendProperty.get(str);
            }
        } else {
            if (obj instanceof ChoreographedSingleAction) {
                return ((ChoreographedSingleAction) obj).getExtendProperty(str);
            }
            LogUtil.b("Suggestion_TrainDetailHelper", "getExtendProperty action null");
        }
        return "";
    }

    public static List<Motion> c(FitWorkout fitWorkout) {
        ArrayList arrayList = new ArrayList();
        if (fitWorkout.getEquipmentWorkoutAction() == null) {
            LogUtil.h("Suggestion_TrainDetailHelper", "equipmentWorkoutAction == null");
            arrayList.add(e(fitWorkout));
            return arrayList;
        }
        List<EquipmentStage> stageList = fitWorkout.getEquipmentWorkoutAction().getStageList();
        if (koq.b(stageList)) {
            LogUtil.h("Suggestion_TrainDetailHelper", "stageList == null");
            arrayList.add(e(fitWorkout));
            return arrayList;
        }
        for (EquipmentStage equipmentStage : stageList) {
            if (equipmentStage != null) {
                for (EquipmentAction equipmentAction : equipmentStage.getActionList()) {
                    if (equipmentAction != null) {
                        arrayList.add(c(equipmentAction));
                    }
                }
            }
        }
        return arrayList;
    }

    private static Motion e(FitWorkout fitWorkout) {
        Motion motion = new Motion();
        motion.saveName(fitWorkout.acquireName());
        motion.setDuration(moe.c(fitWorkout.acquireDuration()));
        if (fitWorkout.acquireDuration() == 0) {
            motion.saveCalorie(0.0f);
        } else {
            motion.saveCalorie(fitWorkout.acquireCalorie() / fitWorkout.acquireDuration());
        }
        VideoSegment videoSegment = new VideoSegment();
        videoSegment.setStartTime(0L);
        videoSegment.setEndTime(fitWorkout.acquireDuration() * 1000);
        videoSegment.setDuration(fitWorkout.acquireDuration());
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(videoSegment);
        motion.setVideoSegments(arrayList);
        motion.saveMotionType("timer");
        motion.saveRepeat(1);
        LogUtil.a("Suggestion_TrainDetailHelper", "default motion:", motion.acquireName(), " ", Float.valueOf(motion.acquireDuration()), " ", Float.valueOf(motion.acquireCalorie()));
        return motion;
    }

    private static Motion c(EquipmentAction equipmentAction) {
        Motion motion = new Motion();
        motion.saveName(equipmentAction.getActionName());
        motion.setDuration((int) (equipmentAction.getEndTime() - equipmentAction.getStartTime()));
        motion.saveCalorie(equipmentAction.getCalorie());
        VideoSegment videoSegment = new VideoSegment();
        videoSegment.setStartTime(equipmentAction.getStartTime());
        videoSegment.setEndTime(equipmentAction.getEndTime());
        videoSegment.setDuration(((int) (equipmentAction.getEndTime() - equipmentAction.getStartTime())) / 1000);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(videoSegment);
        motion.setVideoSegments(arrayList);
        motion.saveMotionType("timer");
        motion.saveRepeat(1);
        return motion;
    }

    public static void a(Context context) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(context.getResources().getString(R$string.IDS_device_bluetooth_open_request)).czC_(com.huawei.ui.commonui.R$string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: fpq.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_TrainDetailHelper", "user choose open BT");
                try {
                    BluetoothAdapter.getDefaultAdapter().enable();
                } catch (RuntimeException e2) {
                    ReleaseLogUtil.c("Suggestion_TrainDetailHelper", "user choose open BT error :", LogAnonymous.b((Throwable) e2));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: fpo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public static void d(final Context context, String str, String str2) {
        LogUtil.a("Suggestion_TrainDetailHelper", "showNotConnectDeviceDialog ", str);
        View inflate = View.inflate(context, R.layout.dialog_not_connect_device, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.not_connect_device_describe);
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.go_support_device_explain);
        if (healthTextView2 != null) {
            healthTextView2.setText(str2);
        }
        final CustomViewDialog e = new CustomViewDialog.Builder(context).czg_(inflate).a((String) null).cze_(R.string._2130848357_res_0x7f022a65, new View.OnClickListener() { // from class: fpq.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName(context.getPackageName(), "com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity");
                gnm.aPB_(context, intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: fpm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
        inflate.findViewById(R.id.go_support_device_explain).setOnClickListener(new View.OnClickListener() { // from class: fpq.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_TrainDetailHelper", "go to support device explain");
                fpq.c(context);
                CustomViewDialog customViewDialog = e;
                if (customViewDialog != null && customViewDialog.isShowing()) {
                    e.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context) {
        bzs.e().initH5Pro();
        H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setImmerse().showStatusBar().setStatusBarTextBlack(true).setForceDarkMode(0).build();
        LogUtil.a("Suggestion_TrainDetailHelper", "goToDevicesIntroduction baseUrl", EnvironmentHelper.getInstance().getUrl());
        H5ProClient.startH5LightApp(context, EnvironmentHelper.getInstance().getUrl() + "weixinScan/dist/index.html#/runPlanDevices", build);
    }

    public static void c(final Context context, String str, String str2) {
        LogUtil.a("Suggestion_TrainDetailHelper", "showNotSupportPlanDialog ", str);
        View inflate = View.inflate(context, R.layout.dialog_not_connect_device, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.not_connect_device_describe);
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.go_support_device_explain);
        if (healthTextView2 != null) {
            healthTextView2.setText(str2);
        }
        final CustomViewDialog e = new CustomViewDialog.Builder(context).czg_(inflate).a((String) null).cze_(R.string._2130848357_res_0x7f022a65, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.helper.TrainDetailHelper$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                fpq.aCO_(context, view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: fpq.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
        inflate.findViewById(R.id.go_support_device_explain).setOnClickListener(new View.OnClickListener() { // from class: fpq.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_TrainDetailHelper", "go to support device explain");
                fpq.c(context);
                CustomViewDialog customViewDialog = e;
                if (customViewDialog != null && customViewDialog.isShowing()) {
                    e.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public static /* synthetic */ void aCO_(Context context, View view) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.ui.device.activity.adddevice.OneKeyScanActivity");
        gnm.aPB_(context, intent);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static String e(Context context) {
        return context.getResources().getString(R.string._2130848684_res_0x7f022bac);
    }

    public static int b(FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            return 0;
        }
        if (koq.c(fitWorkout.getBelongInfoList())) {
            Integer commodityFlag = fitWorkout.getBelongInfoList().get(0).getCommodityFlag();
            if (commodityFlag == null) {
                return fitWorkout.acquireCommodityFlag();
            }
            return commodityFlag.intValue();
        }
        return fitWorkout.acquireCommodityFlag();
    }

    public static boolean b(int i) {
        int modelType = mwt.d().getModelType();
        ReleaseLogUtil.e("Suggestion_TrainDetailHelper", "getIsAi:", Integer.valueOf(i), "modelType: ", Integer.valueOf(modelType), "harmony：", Boolean.valueOf(HarmonyBuild.d));
        return i == 1 && modelType != -1;
    }
}
