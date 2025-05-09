package defpackage;

import android.text.TextUtils;
import com.huawei.health.userprofilemgr.api.RouteApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.route.HeartInfo;
import com.huawei.route.LbsInfo;
import com.huawei.route.TrackInfo;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes7.dex */
public class sjs {
    private static int b(int i) {
        switch (i) {
            case 257:
                return 5;
            case 258:
                return 4;
            case 259:
                return 3;
            default:
                return -1;
        }
    }

    public static String d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        return CommonUtil.c(str + File.separator + str2);
    }

    public static TrackInfo c(String str) {
        int i;
        ArrayList arrayList = new ArrayList(512);
        ArrayList arrayList2 = new ArrayList(512);
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                try {
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            i = 0;
                            if (readLine == null) {
                                break;
                            }
                            if (readLine.startsWith(MotionPath.LBS_DATA_MAP_TAG)) {
                                arrayList.add(readLine);
                                LogUtil.a("SyncDataFileUtil", "lbsList line: ", readLine);
                            }
                            if (readLine.startsWith(MotionPath.HEART_RATE_LIST_TAG)) {
                                arrayList2.add(readLine);
                                LogUtil.a("SyncDataFileUtil", "heartList line: ", readLine);
                            }
                        } finally {
                        }
                    }
                    bufferedReader.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                    double[] dArr = new double[arrayList.size()];
                    double[] dArr2 = new double[arrayList.size()];
                    long[] jArr = new long[arrayList.size()];
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        String[] split = ((String) it.next()).split(";");
                        double parseDouble = Double.parseDouble(split[2].split("=")[1]);
                        double parseDouble2 = Double.parseDouble(split[3].split("=")[1]);
                        if (!b(parseDouble, parseDouble2)) {
                            dArr[i] = parseDouble;
                            dArr2[i] = parseDouble2;
                            long parseDouble3 = (long) Double.parseDouble(split[5].split("=")[1]);
                            if (parseDouble3 < 9999999999L) {
                                parseDouble3 *= 1000;
                            }
                            jArr[i] = parseDouble3;
                            i++;
                        }
                    }
                    ArrayList arrayList3 = new ArrayList();
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        String[] split2 = ((String) it2.next()).split(";");
                        arrayList3.add(new HeartInfo(Integer.parseInt(split2[2].split("=")[1]), Long.parseLong(split2[1].split("=")[1])));
                    }
                    LbsInfo lbsInfo = new LbsInfo(dArr, dArr2, jArr, i);
                    TrackInfo trackInfo = new TrackInfo();
                    trackInfo.setLbsInfo(lbsInfo);
                    trackInfo.setHeartInfo(arrayList3);
                    return trackInfo;
                } finally {
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | NumberFormatException e) {
            LogUtil.h("SyncDataFileUtil", "Generate LbsInfo  error: ", e.getMessage());
            return null;
        }
    }

    private static boolean b(double d, double d2) {
        return Math.abs(90.0d - d) < 1.0E-6d && Math.abs((-80.0d) - d2) < 1.0E-6d;
    }

    public static File c(HiHealthData hiHealthData, HiTrackMetaData hiTrackMetaData, TrackInfo trackInfo, String str, String str2) throws IOException, XmlPullParserException {
        return ((RouteApi) Services.a("module_route", RouteApi.class)).generateGpxFile(hiHealthData, str, str2, b(hiHealthData, hiTrackMetaData), trackInfo);
    }

    private static Map<String, Object> b(HiHealthData hiHealthData, HiTrackMetaData hiTrackMetaData) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(hiHealthData.getTimeZone())) {
            hashMap.put("timeZone", hiHealthData.getTimeZone());
        }
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(b(hiHealthData.getSubType())));
        hashMap.put("totalTime", Long.valueOf(hiTrackMetaData.getTotalTime()));
        hashMap.put(BleConstants.TOTAL_CALORIES, Integer.valueOf(hiTrackMetaData.getTotalCalories()));
        hashMap.put(BleConstants.TOTAL_DISTANCE, Integer.valueOf(hiTrackMetaData.getTotalDistance()));
        hashMap.put("startTime", Long.valueOf(hiHealthData.getStartTime()));
        if (hiHealthData.getEndTime() != 0) {
            hashMap.put("endTime", Long.valueOf(hiHealthData.getEndTime()));
        }
        if (hiTrackMetaData.getAvgPace() != 0.0f) {
            hashMap.put("avgPace", Float.valueOf(hiTrackMetaData.getAvgPace()));
        }
        if (hiTrackMetaData.getBestPace() != 0.0f) {
            hashMap.put("bestPace", Float.valueOf(hiTrackMetaData.getBestPace()));
        }
        if (hiTrackMetaData.getAvgHeartRate() != 0) {
            hashMap.put("avgHeartRate", Integer.valueOf(hiTrackMetaData.getAvgHeartRate()));
        }
        if (hiTrackMetaData.getMaxHeartRate() != 0) {
            hashMap.put("maxHeartRate", Integer.valueOf(hiTrackMetaData.getMaxHeartRate()));
        }
        if (hiTrackMetaData.getMinHeartRate() != 0) {
            hashMap.put("minHeartRate", Integer.valueOf(hiTrackMetaData.getMinHeartRate()));
        }
        if (hiTrackMetaData.getTotalSteps() != 0) {
            hashMap.put("totalSteps", Integer.valueOf(hiTrackMetaData.getTotalSteps()));
        }
        if (hiTrackMetaData.getAvgStepRate() != 0) {
            hashMap.put("avgCadence", Integer.valueOf(hiTrackMetaData.getAvgStepRate()));
        }
        return hashMap;
    }

    public static void d(String str, String str2, String str3, String str4, String str5) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(SyncDataConstant.BI_KEY_ACTIVITY_TYPE, str2);
        linkedHashMap.put(SyncDataConstant.BI_KEY_ACCOUNT_TYPE, str3);
        if (!TextUtils.isEmpty(str4)) {
            str5 = str4 + ":" + str5;
        }
        linkedHashMap.put("msg", str5);
        LogUtil.a("SyncDataFileUtil", "buildAIOpsParam", str2, str3, str5);
        OpAnalyticsUtil.getInstance().setEvent(str, linkedHashMap);
    }
}
