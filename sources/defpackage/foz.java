package defpackage;

import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Comment;
import java.util.List;

/* loaded from: classes4.dex */
public class foz {
    public static String e(Motion motion, String str) {
        if (motion == null) {
            LogUtil.h("Suggestion_LongViewContentHelper", "getContent motion == null");
            return str;
        }
        List<Comment> acquireCommentaryGap = motion.acquireCommentaryGap();
        return (acquireCommentaryGap == null || acquireCommentaryGap.size() <= 0) ? str : acquireCommentaryGap.get(0).acquireContent();
    }
}
