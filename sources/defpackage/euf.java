package defpackage;

import com.huawei.basefitnessadvice.model.Data;
import com.huawei.basefitnessadvice.model.Media;
import com.huawei.health.plan.model.model.BasicPackage;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Audio;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import defpackage.etq;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes3.dex */
public class euf {
    public static List<Data> e(String str) {
        LogUtil.a("Suggestion_ParserHelper", "parseXml start");
        ArrayList arrayList = new ArrayList(10);
        BasicPackage basicPackage = new BasicPackage();
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
                try {
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        StringBuilder sb = new StringBuilder(16);
                        sb.append(d(inputStreamReader));
                        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                        newPullParser.setInput(new StringReader(sb.toString()));
                        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                            if (eventType == 2) {
                                c(arrayList, newPullParser, basicPackage);
                            }
                        }
                        bufferedReader.close();
                        inputStreamReader.close();
                        fileInputStream.close();
                    } finally {
                    }
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
        } catch (IOException | NumberFormatException | XmlPullParserException e) {
            LogUtil.b("Suggestion_ParserHelper", "close failed：", LogAnonymous.b(e));
            LogUtil.b("Suggestion_ParserHelper", "XmlPullParser failed:", LogAnonymous.b(e));
        }
        LogUtil.a("Suggestion_ParserHelper", "parseXml end");
        return arrayList;
    }

    private static void c(List<Data> list, XmlPullParser xmlPullParser, BasicPackage basicPackage) {
        if (xmlPullParser == null || "".equals(xmlPullParser.getName()) || xmlPullParser.getName() == null) {
            return;
        }
        if ("basic_package".equals(xmlPullParser.getName())) {
            basicPackage.saveLanguage(xmlPullParser.getAttributeValue(null, "language"));
            basicPackage.saveType(xmlPullParser.getAttributeValue(null, "type"));
            basicPackage.savePostFix(xmlPullParser.getAttributeValue(null, "postfix"));
            basicPackage.saveProvider(xmlPullParser.getAttributeValue(null, "providor"));
            basicPackage.saveVersion(xmlPullParser.getAttributeValue(null, "version"));
            basicPackage.saveSize(CommonUtil.h(xmlPullParser.getAttributeValue(null, "size")));
            basicPackage.saveGender(xmlPullParser.getAttributeValue(null, CommonConstant.KEY_GENDER));
            return;
        }
        if ("data".equals(xmlPullParser.getName())) {
            Data data = new Data();
            data.setContent(xmlPullParser.getAttributeValue(null, "content"));
            data.setId(xmlPullParser.getAttributeValue(null, "id"));
            data.saveUrl(xmlPullParser.getAttributeValue(null, "url"));
            try {
                data.setSize(Integer.parseInt(xmlPullParser.getAttributeValue(null, "size")));
            } catch (NumberFormatException e) {
                LogUtil.b("Suggestion_ParserHelper", "setParserEventData size:", LogAnonymous.b((Throwable) e));
            }
            data.saveMd5(xmlPullParser.getAttributeValue(null, "md5"));
            data.setFileName(data.acquireId() + "." + basicPackage.acquirePostFix());
            list.add(data);
        }
    }

    public static int b(WorkoutAction workoutAction, Map<String, Object> map, List<Media> list, etq.b bVar) {
        if (workoutAction == null) {
            ReleaseLogUtil.c("Suggestion_ParserHelper", "parseVideoFilesLength workoutAction == null");
            return 0;
        }
        AtomicAction action = workoutAction.getAction();
        if (action == null) {
            ReleaseLogUtil.c("Suggestion_ParserHelper", "parseVideoFilesLength workoutAction.getAction() == null");
            return 0;
        }
        return d(map, list, action, bVar) + c(map, list, action, bVar);
    }

    private static int c(Map<String, Object> map, List<Media> list, AtomicAction atomicAction, etq.b bVar) {
        List<Audio> extendPropertyList = atomicAction.getExtendPropertyList("audios", Audio[].class);
        int i = 0;
        if (koq.b(extendPropertyList)) {
            ReleaseLogUtil.c("Suggestion_ParserHelper", "parseVideoFilesLength action.getAudios() == null");
            return 0;
        }
        for (Audio audio : extendPropertyList) {
            if (audio != null) {
                long b = etq.b(audio.getUrl());
                if (!map.containsKey(audio.getName()) && (!etq.b(bVar) || !squ.d(audio.getUrl(), b))) {
                    map.put(audio.getUrl(), null);
                    i += audio.getLength();
                    if (list != null) {
                        Media media = new Media();
                        media.setUrl(audio.getName());
                        media.setPath(squ.e(media.getUrl()));
                        media.setLength(audio.getLength());
                        media.setType(2);
                        list.add(media);
                    }
                }
            }
        }
        return i;
    }

    private static int d(Map<String, Object> map, List<Media> list, AtomicAction atomicAction, etq.b bVar) {
        int i = 0;
        boolean z = ("hotbody".equals(ffq.e(atomicAction.getActionSportType())) || bVar == null) ? false : true;
        if (z) {
            z = z && bVar.e();
        }
        List<Video> extendPropertyList = atomicAction.getExtendPropertyList("actionVideo", Video[].class);
        if (koq.b(extendPropertyList)) {
            ReleaseLogUtil.c("Suggestion_ParserHelper", "parseVideoFilesLength action.getVideos() == null");
            return 0;
        }
        for (Video video : extendPropertyList) {
            if (video != null && (!z || !bVar.b() || video.getGender() == bVar.a())) {
                long b = etq.b(video.getUrl());
                if (!map.containsKey(video.getUrl()) && (!etq.b(bVar) || !squ.b(video.getUrl(), b))) {
                    map.put(video.getUrl(), null);
                    i += video.getLength();
                    if (list != null) {
                        Media media = new Media();
                        media.setUrl(video.getUrl());
                        media.setPath(squ.n(media.getUrl()));
                        media.setLength(video.getLength());
                        media.setType(1);
                        list.add(media);
                    }
                }
            }
        }
        return i;
    }

    public static int d(WorkoutAction workoutAction, Map<String, Object> map, List<Media> list, etq.b bVar) {
        if (workoutAction == null) {
            ReleaseLogUtil.c("Suggestion_ParserHelper", "parseMediaLength workoutAction == null");
            return 0;
        }
        AtomicAction action = workoutAction.getAction();
        if (action == null) {
            ReleaseLogUtil.c("Suggestion_ParserHelper", "parseMediaLength workoutAction.getAction() == null");
            return 0;
        }
        return d(map, list, action, bVar);
    }

    private static String d(InputStreamReader inputStreamReader) {
        char[] cArr = new char[1024];
        StringBuilder sb = new StringBuilder(16);
        while (true) {
            try {
                int read = inputStreamReader.read(cArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                sb.append(cArr, 0, read);
            } catch (IOException e) {
                LogUtil.b("Suggestion_ParserHelper", "read Line Result exception:", LogAnonymous.b((Throwable) e));
            }
        }
        return sb.toString();
    }
}
