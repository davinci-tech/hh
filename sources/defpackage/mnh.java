package defpackage;

import com.huawei.pluginfitnessadvice.Comment;
import com.huawei.pluginfitnessadvice.Cover;
import com.huawei.pluginfitnessadvice.Video;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mnh {
    public static <T> List<T> a(JSONArray jSONArray, Class<T[]> cls) {
        if (jSONArray == null) {
            return Collections.emptyList();
        }
        return moj.b(jSONArray.toString(), (Class) cls);
    }

    public static List<Comment> c(JSONArray jSONArray, int i) {
        ArrayList arrayList = new ArrayList(10);
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null && (i != 1 || optJSONObject.optInt("sex") == 1)) {
                Comment comment = new Comment();
                comment.saveId(optJSONObject.optString("audioId"));
                comment.saveName(optJSONObject.optString("audioUrl"));
                comment.saveTime(optJSONObject.optInt("playTime"));
                comment.saveType(optJSONObject.optInt("displayorder"));
                comment.saveLength(optJSONObject.optInt("audioSize"));
                arrayList.add(comment);
            }
        }
        return arrayList;
    }

    public static List<Cover> a(List<Video> list) {
        if (koq.b(list)) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Video video : list) {
            if (video != null) {
                Cover cover = new Cover();
                cover.saveGender(video.getGender());
                cover.saveUrl(video.getThumbnail());
                arrayList.add(cover);
            }
        }
        return arrayList;
    }
}
