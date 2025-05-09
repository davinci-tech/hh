package defpackage;

import com.google.gson.Gson;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.pluginfitnessadvice.VideoSegment;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.tencent.connect.share.QzonePublish;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mnd extends mmy {
    @Override // defpackage.mmy, com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    /* renamed from: c */
    public AtomicAction parse(String str, JSONObject jSONObject) {
        AtomicAction parse = super.parse(str, jSONObject);
        if (parse == null) {
            return null;
        }
        parse.putExtendProperty("calorie", jSONObject.optString("calorie"));
        parse.putExtendProperty("duration", String.valueOf(jSONObject.optLong("duration")));
        parse.putExtendProperty("actionStep", jSONObject.optString("actionStep"));
        parse.putExtendProperty("breath", jSONObject.optString("breath"));
        parse.putExtendProperty("commonError", jSONObject.optString("commonError"));
        parse.putExtendProperty("feeling", jSONObject.optString("feeling"));
        parse.putExtendProperty("introduceLyric", jSONObject.optString("introduceLyric"));
        List<Video> c = c(jSONObject.optJSONArray("actionVideo"));
        if (koq.c(c)) {
            parse.putExtendProperty("actionVideo", new Gson().toJson(c));
            parse.setCovers(mnh.a(c));
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("pictures");
        if (optJSONArray != null) {
            parse.putExtendProperty("pictures", optJSONArray.toString());
        }
        return parse;
    }

    private List<Video> c(JSONArray jSONArray) {
        if (jSONArray == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                Video video = new Video();
                video.saveId(optJSONObject.optString("id"));
                video.saveName(optJSONObject.optString("name"));
                video.saveType(optJSONObject.optInt("type"));
                video.saveActionCount(optJSONObject.optInt("actionCount"));
                video.saveDuring(optJSONObject.optInt("during"));
                video.saveGender(optJSONObject.optInt("sex"));
                video.saveLength(optJSONObject.optInt(QzonePublish.PUBLISH_TO_QZONE_VIDEO_SIZE));
                video.saveThumbnail(optJSONObject.optString("thumbnail"));
                video.saveUrl(optJSONObject.optString("videoUrl"));
                video.saveLogoImgUrl(optJSONObject.optString("logoImgUrl"));
                video.setIsLongVideo(optJSONObject.optInt("isLongVideo"));
                video.setFileId(optJSONObject.optString(RecommendConstants.FILE_ID));
                JSONArray optJSONArray = optJSONObject.optJSONArray("videoSegmentList");
                if (optJSONArray != null) {
                    video.setVideoSegmentList(mnh.a(optJSONArray, VideoSegment[].class));
                }
                arrayList.add(video);
            }
        }
        return arrayList;
    }
}
