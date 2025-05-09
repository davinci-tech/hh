package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes6.dex */
public class mwz {
    private LinkedList<Object> b = new LinkedList<>();

    public void b(Integer num) {
        this.b.add(num);
    }

    public void b(Double d) {
        this.b.add(d);
    }

    public void d(String str) {
        this.b.add(str);
    }

    public boolean d() {
        return this.b.isEmpty();
    }

    public boolean b() {
        if (d()) {
            return false;
        }
        Object peekFirst = this.b.peekFirst();
        return (peekFirst instanceof Integer) || (peekFirst instanceof Double);
    }

    public boolean a() {
        return !d() && (this.b.peekFirst() instanceof String);
    }

    public Number e() {
        if (!b()) {
            LogUtil.b("MultilingualAudio_BusinessInput", "first item is not number");
            return 0;
        }
        return (Number) this.b.pollFirst();
    }

    public String c() {
        if (!a()) {
            LogUtil.b("MultilingualAudio_BusinessInput", "first item is not url");
            return "";
        }
        return (String) this.b.pollFirst();
    }

    public mwz c(List<Integer> list) {
        if (list.isEmpty()) {
            return this;
        }
        if (this.b.size() != list.size()) {
            LogUtil.b("MultilingualAudio_BusinessInput", "sequence size not equal to data size");
            return this;
        }
        LinkedList<Object> linkedList = new LinkedList<>();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            linkedList.add(this.b.get(it.next().intValue()));
        }
        mwz mwzVar = new mwz();
        mwzVar.b = linkedList;
        return mwzVar;
    }

    public String toString() {
        return "BusinessInputParameter{mInputData=" + this.b + '}';
    }
}
