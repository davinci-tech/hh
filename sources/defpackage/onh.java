package defpackage;

import com.huawei.health.messagecenter.model.MessageObject;
import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes6.dex */
public class onh implements Comparator, Serializable {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        if (!(obj instanceof MessageObject) || !(obj2 instanceof MessageObject)) {
            return 1;
        }
        MessageObject messageObject = (MessageObject) obj;
        MessageObject messageObject2 = (MessageObject) obj2;
        if (messageObject2.getWeight() > messageObject.getWeight()) {
            return 1;
        }
        if (messageObject2.getWeight() < messageObject.getWeight()) {
            return -1;
        }
        return Long.compare(messageObject2.getCreateTime(), messageObject.getCreateTime());
    }
}
