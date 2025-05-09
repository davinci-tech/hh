package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.tencent.open.SocialConstants;
import defpackage.coroutineContext;
import defpackage.dispatcherFailure;
import defpackage.startDirect;
import defpackage.uep;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JC\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\n2\u001c\u0010\u000b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0087\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\\\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\n2'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0012¢\u0006\u0002\b\u00132\u0006\u0010\u0014\u001a\u0002H\u00112\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0087\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0015R\u001a\u0010\u0003\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0007j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lkotlinx/coroutines/CoroutineStart;", "", "(Ljava/lang/String;I)V", "isLazy", "", "isLazy$annotations", "()V", "()Z", TrackConstants$Opers.INVOKE, "", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "completion", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", SocialConstants.PARAM_RECEIVER, "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", CommonConstant.StartQrLoginQrValue.QRSCENE_DEFAULT, "LAZY", "ATOMIC", "UNDISPATCHED", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public enum CoroutineStart {
    DEFAULT,
    LAZY,
    ATOMIC,
    UNDISPATCHED;

    public static /* synthetic */ void isLazy$annotations() {
    }

    public final <T> void invoke(Function1<? super Continuation<? super T>, ? extends Object> block, Continuation<? super T> completion) {
        int i = e.e[ordinal()];
        if (i == 1) {
            dispatcherFailure.e(block, completion);
            return;
        }
        if (i == 2) {
            coroutineContext.d(block, completion);
        } else if (i == 3) {
            startDirect.d(block, completion);
        } else if (i != 4) {
            throw new uep();
        }
    }

    public final <R, T> void invoke(Function2<? super R, ? super Continuation<? super T>, ? extends Object> block, R receiver, Continuation<? super T> completion) {
        int i = e.e[ordinal()];
        if (i == 1) {
            dispatcherFailure.e(block, receiver, completion, null, 4, null);
            return;
        }
        if (i == 2) {
            coroutineContext.d(block, receiver, completion);
        } else if (i == 3) {
            startDirect.c(block, receiver, completion);
        } else if (i != 4) {
            throw new uep();
        }
    }

    public final boolean isLazy() {
        return this == LAZY;
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public final /* synthetic */ class e {
        public static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[CoroutineStart.values().length];
            iArr[CoroutineStart.DEFAULT.ordinal()] = 1;
            iArr[CoroutineStart.ATOMIC.ordinal()] = 2;
            iArr[CoroutineStart.UNDISPATCHED.ordinal()] = 3;
            iArr[CoroutineStart.LAZY.ordinal()] = 4;
            e = iArr;
        }
    }
}
