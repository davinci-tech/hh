package defpackage;

import androidx.exifinterface.media.ExifInterface;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.hms.network.embedded.r3;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlinx.coroutines.ChildHandle;
import kotlinx.coroutines.ChildJob;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Incomplete;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellingNode;
import kotlinx.coroutines.JobNode;
import kotlinx.coroutines.ParentJob;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectInstance;

@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@Metadata(d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0017\u0018\u00002\u00020X2\u00020\u00172\u00020\u007f2\u00030Ã\u0001:\u0006Ò\u0001Ó\u0001Ô\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J'\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\u0015\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u001dJ\u0019\u0010!\u001a\u00020\u00012\b\u0010 \u001a\u0004\u0018\u00010\rH\u0017¢\u0006\u0004\b!\u0010\"J\u001f\u0010!\u001a\u00020\u00112\u000e\u0010 \u001a\n\u0018\u00010#j\u0004\u0018\u0001`$H\u0016¢\u0006\u0004\b!\u0010%J\u0017\u0010&\u001a\u00020\u00012\b\u0010 \u001a\u0004\u0018\u00010\r¢\u0006\u0004\b&\u0010\"J\u0019\u0010)\u001a\u00020\u00012\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\rH\u0016¢\u0006\u0004\b*\u0010+J\u001b\u0010,\u001a\u0004\u0018\u00010\u00052\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\rH\u0002¢\u0006\u0004\b.\u0010\"J\u000f\u00100\u001a\u00020/H\u0014¢\u0006\u0004\b0\u00101J\u0017\u00102\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\rH\u0016¢\u0006\u0004\b2\u0010\"J!\u00105\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b5\u00106J)\u0010;\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u0002072\u0006\u00109\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b;\u0010<J\u0019\u0010=\u001a\u00020\r2\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b=\u0010>J(\u0010C\u001a\u00020@2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010/2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\rH\u0080\b¢\u0006\u0004\bA\u0010BJ#\u0010D\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\bD\u0010EJ\u0019\u0010F\u001a\u0004\u0018\u0001082\u0006\u0010\u0014\u001a\u000203H\u0002¢\u0006\u0004\bF\u0010GJ\u0011\u0010H\u001a\u00060#j\u0002`$¢\u0006\u0004\bH\u0010IJ\u0013\u0010J\u001a\u00060#j\u0002`$H\u0016¢\u0006\u0004\bJ\u0010IJ\u0011\u0010M\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0004\bK\u0010LJ\u000f\u0010N\u001a\u0004\u0018\u00010\r¢\u0006\u0004\bN\u0010OJ'\u0010P\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0014\u001a\u0002072\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002¢\u0006\u0004\bP\u0010QJ\u0019\u0010R\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u000203H\u0002¢\u0006\u0004\bR\u0010SJ\u0017\u0010U\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\rH\u0014¢\u0006\u0004\bU\u0010\"J\u0017\u0010W\u001a\u00020\u00112\u0006\u0010T\u001a\u00020\rH\u0010¢\u0006\u0004\bV\u0010+J\u0019\u0010Z\u001a\u00020\u00112\b\u0010Y\u001a\u0004\u0018\u00010XH\u0004¢\u0006\u0004\bZ\u0010[JF\u0010d\u001a\u00020c2\u0006\u0010\\\u001a\u00020\u00012\u0006\u0010]\u001a\u00020\u00012'\u0010b\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b_\u0012\b\b`\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00110^j\u0002`a¢\u0006\u0004\bd\u0010eJ6\u0010d\u001a\u00020c2'\u0010b\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b_\u0012\b\b`\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00110^j\u0002`a¢\u0006\u0004\bd\u0010fJ\u0013\u0010g\u001a\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0004\bg\u0010\u001dJ\u000f\u0010h\u001a\u00020\u0001H\u0002¢\u0006\u0004\bh\u0010iJ\u0013\u0010j\u001a\u00020\u0011H\u0082@ø\u0001\u0000¢\u0006\u0004\bj\u0010\u001dJ&\u0010m\u001a\u00020l2\u0014\u0010k\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00110^H\u0082\b¢\u0006\u0004\bm\u0010nJ\u001b\u0010o\u001a\u0004\u0018\u00010\u00052\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\bo\u0010-J\u0019\u0010q\u001a\u00020\u00012\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0004\bp\u0010(J\u001b\u0010s\u001a\u0004\u0018\u00010\u00052\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0004\br\u0010-J@\u0010t\u001a\u00020\t2'\u0010b\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b_\u0012\b\b`\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00110^j\u0002`a2\u0006\u0010\\\u001a\u00020\u0001H\u0002¢\u0006\u0004\bt\u0010uJ\u000f\u0010w\u001a\u00020/H\u0010¢\u0006\u0004\bv\u00101J\u001f\u0010x\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\rH\u0002¢\u0006\u0004\bx\u0010yJ.\u0010{\u001a\u00020\u0011\"\n\b\u0000\u0010z\u0018\u0001*\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\rH\u0082\b¢\u0006\u0004\b{\u0010yJ\u0019\u0010\\\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\rH\u0014¢\u0006\u0004\b\\\u0010+J\u0019\u0010|\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0014¢\u0006\u0004\b|\u0010\u0016J\u000f\u0010}\u001a\u00020\u0011H\u0014¢\u0006\u0004\b}\u0010~J\u0019\u0010\u0081\u0001\u001a\u00020\u00112\u0007\u0010\u0080\u0001\u001a\u00020\u007f¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u001b\u0010\u0084\u0001\u001a\u00020\u00112\u0007\u0010\u0014\u001a\u00030\u0083\u0001H\u0002¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u001a\u0010\u0086\u0001\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\tH\u0002¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001JI\u0010\u008c\u0001\u001a\u00020\u0011\"\u0005\b\u0000\u0010\u0088\u00012\u000e\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0089\u00012\u001d\u0010k\u001a\u0019\b\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00000\u008b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00050^ø\u0001\u0000¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001JX\u0010\u0091\u0001\u001a\u00020\u0011\"\u0004\b\u0000\u0010z\"\u0005\b\u0001\u0010\u0088\u00012\u000e\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00028\u00010\u0089\u00012$\u0010k\u001a \b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00010\u008b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u008e\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J\u001a\u0010\u0093\u0001\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0006\b\u0092\u0001\u0010\u0087\u0001JX\u0010\u0095\u0001\u001a\u00020\u0011\"\u0004\b\u0000\u0010z\"\u0005\b\u0001\u0010\u0088\u00012\u000e\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00028\u00010\u0089\u00012$\u0010k\u001a \b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00010\u008b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u008e\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u0094\u0001\u0010\u0090\u0001J\u000f\u0010\u0096\u0001\u001a\u00020\u0001¢\u0006\u0005\b\u0096\u0001\u0010iJ\u001d\u0010\u0098\u0001\u001a\u00030\u0097\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J\u001c\u0010\u009a\u0001\u001a\u00020/2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J\u0011\u0010\u009c\u0001\u001a\u00020/H\u0007¢\u0006\u0005\b\u009c\u0001\u00101J\u0011\u0010\u009d\u0001\u001a\u00020/H\u0016¢\u0006\u0005\b\u009d\u0001\u00101J$\u0010\u009e\u0001\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001J\"\u0010 \u0001\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u0002032\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0006\b \u0001\u0010¡\u0001J(\u0010¢\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u00052\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001J&\u0010¤\u0001\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u0002032\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b¤\u0001\u0010¥\u0001J-\u0010¦\u0001\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u0002072\u0006\u0010\u0018\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0082\u0010¢\u0006\u0006\b¦\u0001\u0010§\u0001J\u0019\u0010©\u0001\u001a\u0004\u0018\u000108*\u00030¨\u0001H\u0002¢\u0006\u0006\b©\u0001\u0010ª\u0001J\u001f\u0010«\u0001\u001a\u00020\u0011*\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0005\b«\u0001\u0010yJ&\u0010¬\u0001\u001a\u00060#j\u0002`$*\u00020\r2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010/H\u0004¢\u0006\u0006\b¬\u0001\u0010\u00ad\u0001R\u001b\u0010±\u0001\u001a\t\u0012\u0004\u0012\u00020X0®\u00018F¢\u0006\b\u001a\u0006\b¯\u0001\u0010°\u0001R\u0018\u0010³\u0001\u001a\u0004\u0018\u00010\r8DX\u0084\u0004¢\u0006\u0007\u001a\u0005\b²\u0001\u0010OR\u0016\u0010µ\u0001\u001a\u00020\u00018DX\u0084\u0004¢\u0006\u0007\u001a\u0005\b´\u0001\u0010iR\u0016\u0010·\u0001\u001a\u00020\u00018PX\u0090\u0004¢\u0006\u0007\u001a\u0005\b¶\u0001\u0010iR\u0016\u0010¸\u0001\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¸\u0001\u0010iR\u0013\u0010¹\u0001\u001a\u00020\u00018F¢\u0006\u0007\u001a\u0005\b¹\u0001\u0010iR\u0013\u0010º\u0001\u001a\u00020\u00018F¢\u0006\u0007\u001a\u0005\bº\u0001\u0010iR\u0013\u0010»\u0001\u001a\u00020\u00018F¢\u0006\u0007\u001a\u0005\b»\u0001\u0010iR\u0016\u0010¼\u0001\u001a\u00020\u00018TX\u0094\u0004¢\u0006\u0007\u001a\u0005\b¼\u0001\u0010iR\u0019\u0010À\u0001\u001a\u0007\u0012\u0002\b\u00030½\u00018F¢\u0006\b\u001a\u0006\b¾\u0001\u0010¿\u0001R\u0016\u0010Â\u0001\u001a\u00020\u00018PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÁ\u0001\u0010iR\u0015\u0010Æ\u0001\u001a\u00030Ã\u00018F¢\u0006\b\u001a\u0006\bÄ\u0001\u0010Å\u0001R.\u0010Ì\u0001\u001a\u0004\u0018\u00010\u00192\t\u0010Ç\u0001\u001a\u0004\u0018\u00010\u00198@@@X\u0080\u000e¢\u0006\u0010\u001a\u0006\bÈ\u0001\u0010É\u0001\"\u0006\bÊ\u0001\u0010Ë\u0001R\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00058@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bÍ\u0001\u0010LR\u001e\u0010Ï\u0001\u001a\u0004\u0018\u00010\r*\u0004\u0018\u00010\u00058BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÎ\u0001\u0010>R\u001b\u0010Ð\u0001\u001a\u00020\u0001*\u0002038BX\u0082\u0004¢\u0006\b\u001a\u0006\bÐ\u0001\u0010Ñ\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Õ\u0001"}, d2 = {"Lkotlinx/coroutines/JobSupport;", "", "active", "<init>", "(Z)V", "", "expect", "Lkotlinx/coroutines/NodeList;", "list", "Lkotlinx/coroutines/JobNode;", "node", "addLastAtomic", "(Ljava/lang/Object;Lkotlinx/coroutines/NodeList;Lkotlinx/coroutines/JobNode;)Z", "", "rootCause", "", "exceptions", "", "addSuppressedExceptions", "(Ljava/lang/Throwable;Ljava/util/List;)V", "state", "afterCompletion", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/ChildJob;", "child", "Lkotlinx/coroutines/ChildHandle;", "attachChild", "(Lkotlinx/coroutines/ChildJob;)Lkotlinx/coroutines/ChildHandle;", "awaitInternal$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitInternal", "awaitSuspend", "cause", "cancel", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "cancelCoroutine", "cancelImpl$kotlinx_coroutines_core", "(Ljava/lang/Object;)Z", "cancelImpl", "cancelInternal", "(Ljava/lang/Throwable;)V", "cancelMakeCompleting", "(Ljava/lang/Object;)Ljava/lang/Object;", "cancelParent", "", "cancellationExceptionMessage", "()Ljava/lang/String;", "childCancelled", "Lkotlinx/coroutines/Incomplete;", "update", "completeStateFinalization", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)V", "Lkotlinx/coroutines/JobSupport$Finishing;", "Lkotlinx/coroutines/ChildHandleNode;", "lastChild", "proposedUpdate", "continueCompleting", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "createCauseException", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "message", "Lkotlinx/coroutines/JobCancellationException;", "defaultCancellationException$kotlinx_coroutines_core", "(Ljava/lang/String;Ljava/lang/Throwable;)Lkotlinx/coroutines/JobCancellationException;", "defaultCancellationException", "finalizeFinishingState", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/lang/Object;)Ljava/lang/Object;", "firstChild", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/ChildHandleNode;", "getCancellationException", "()Ljava/util/concurrent/CancellationException;", "getChildJobCancellationCause", "getCompletedInternal$kotlinx_coroutines_core", "()Ljava/lang/Object;", "getCompletedInternal", "getCompletionExceptionOrNull", "()Ljava/lang/Throwable;", "getFinalRootCause", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/util/List;)Ljava/lang/Throwable;", "getOrPromoteCancellingList", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/NodeList;", TrackConstants$Events.EXCEPTION, "handleJobException", "handleOnCompletionException$kotlinx_coroutines_core", "handleOnCompletionException", "Lkotlinx/coroutines/Job;", "parent", "initParentJob", "(Lkotlinx/coroutines/Job;)V", "onCancelling", "invokeImmediately", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "handler", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnCompletion", "(ZZLkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "join", "joinInternal", "()Z", "joinSuspend", "block", "", "loopOnState", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Void;", "makeCancelling", "makeCompleting$kotlinx_coroutines_core", "makeCompleting", "makeCompletingOnce$kotlinx_coroutines_core", "makeCompletingOnce", "makeNode", "(Lkotlin/jvm/functions/Function1;Z)Lkotlinx/coroutines/JobNode;", "nameString$kotlinx_coroutines_core", "nameString", "notifyCancelling", "(Lkotlinx/coroutines/NodeList;Ljava/lang/Throwable;)V", ExifInterface.GPS_DIRECTION_TRUE, "notifyHandlers", "onCompletionInternal", "onStart", "()V", "Lkotlinx/coroutines/ParentJob;", "parentJob", "parentCancelled", "(Lkotlinx/coroutines/ParentJob;)V", "Lkotlinx/coroutines/Empty;", "promoteEmptyToNodeList", "(Lkotlinx/coroutines/Empty;)V", "promoteSingleToNodeList", "(Lkotlinx/coroutines/JobNode;)V", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/coroutines/Continuation;", "registerSelectClause0", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "Lkotlin/Function2;", "registerSelectClause1Internal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectClause1Internal", "removeNode$kotlinx_coroutines_core", "removeNode", "selectAwaitCompletion$kotlinx_coroutines_core", "selectAwaitCompletion", "start", "", "startInternal", "(Ljava/lang/Object;)I", "stateString", "(Ljava/lang/Object;)Ljava/lang/String;", "toDebugString", "toString", "tryFinalizeSimpleState", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)Z", "tryMakeCancelling", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Throwable;)Z", "tryMakeCompleting", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryMakeCompletingSlowPath", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)Ljava/lang/Object;", "tryWaitForChild", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "nextChild", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/ChildHandleNode;", "notifyCompletion", "toCancellationException", "(Ljava/lang/Throwable;Ljava/lang/String;)Ljava/util/concurrent/CancellationException;", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "children", "getCompletionCause", "completionCause", "getCompletionCauseHandled", "completionCauseHandled", "getHandlesException$kotlinx_coroutines_core", "handlesException", r3.B, "isCancelled", "isCompleted", "isCompletedExceptionally", "isScopedCoroutine", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", MedalConstants.EVENT_KEY, "getOnCancelComplete$kotlinx_coroutines_core", "onCancelComplete", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "onJoin", "value", "getParentHandle$kotlinx_coroutines_core", "()Lkotlinx/coroutines/ChildHandle;", "setParentHandle$kotlinx_coroutines_core", "(Lkotlinx/coroutines/ChildHandle;)V", "parentHandle", "getState$kotlinx_coroutines_core", "getExceptionOrNull", "exceptionOrNull", "isCancelling", "(Lkotlinx/coroutines/Incomplete;)Z", "AwaitContinuation", "ChildCompletion", "Finishing", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public class umd implements Job, ChildJob, ParentJob, SelectClause0 {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(umd.class, Object.class, "_state");
    private volatile /* synthetic */ Object _parentHandle;
    private volatile /* synthetic */ Object _state;

    protected void afterCompletion(Object state) {
    }

    public boolean getHandlesException$kotlinx_coroutines_core() {
        return true;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return false;
    }

    protected boolean handleJobException(Throwable exception) {
        return false;
    }

    protected boolean isScopedCoroutine() {
        return false;
    }

    protected void onCancelling(Throwable cause) {
    }

    protected void onCompletionInternal(Object state) {
    }

    protected void onStart() {
    }

    public umd(boolean z) {
        this._state = z ? COMPLETING_ALREADY.c : COMPLETING_ALREADY.f17466a;
        this._parentHandle = null;
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) Job.e.c(this, r, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return (E) Job.e.b(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return Job.e.c(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return Job.e.b(this, coroutineContext);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public Job plus(Job job) {
        return Job.e.b((Job) this, job);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key<?> getKey() {
        return Job.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/internal/LockFreeLinkedListNode$makeCondAddOp$1", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", ParamConstants.CallbackMethod.ON_PREPARE, "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class a extends LockFreeLinkedListNode.CondAddOp {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f17463a;
        final /* synthetic */ umd d;
        final /* synthetic */ LockFreeLinkedListNode e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(LockFreeLinkedListNode lockFreeLinkedListNode, umd umdVar, Object obj) {
            super(lockFreeLinkedListNode);
            this.e = lockFreeLinkedListNode;
            this.d = umdVar;
            this.f17463a = obj;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Object prepare(LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (this.d.getState$kotlinx_coroutines_core() == this.f17463a) {
                return null;
            }
            return CONDITION_FALSE.d();
        }
    }

    public final ChildHandle getParentHandle$kotlinx_coroutines_core() {
        return (ChildHandle) this._parentHandle;
    }

    public final void setParentHandle$kotlinx_coroutines_core(ChildHandle childHandle) {
        this._parentHandle = childHandle;
    }

    protected final void initParentJob(Job parent) {
        if (ASSERTIONS_ENABLED.a() && getParentHandle$kotlinx_coroutines_core() != null) {
            throw new AssertionError();
        }
        if (parent == null) {
            setParentHandle$kotlinx_coroutines_core(umi.e);
            return;
        }
        parent.start();
        ChildHandle attachChild = parent.attachChild(this);
        setParentHandle$kotlinx_coroutines_core(attachChild);
        if (isCompleted()) {
            attachChild.dispose();
            setParentHandle$kotlinx_coroutines_core(umi.e);
        }
    }

    private final Void loopOnState(Function1<Object, ueu> block) {
        while (true) {
            block.invoke(getState$kotlinx_coroutines_core());
        }
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof Incomplete) && ((Incomplete) state$kotlinx_coroutines_core).getD();
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof Incomplete);
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCancelled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof ula) || ((state$kotlinx_coroutines_core instanceof e) && ((e) state$kotlinx_coroutines_core).d());
    }

    private final Object finalizeFinishingState(e eVar, Object obj) {
        boolean d2;
        Throwable finalRootCause;
        if (ASSERTIONS_ENABLED.a() && getState$kotlinx_coroutines_core() != eVar) {
            throw new AssertionError();
        }
        if (ASSERTIONS_ENABLED.a() && !(!eVar.a())) {
            throw new AssertionError();
        }
        if (ASSERTIONS_ENABLED.a() && !eVar.b()) {
            throw new AssertionError();
        }
        ula ulaVar = obj instanceof ula ? (ula) obj : null;
        Throwable th = ulaVar == null ? null : ulaVar.d;
        synchronized (eVar) {
            d2 = eVar.d();
            List<Throwable> e2 = eVar.e(th);
            finalRootCause = getFinalRootCause(eVar, e2);
            if (finalRootCause != null) {
                addSuppressedExceptions(finalRootCause, e2);
            }
        }
        if (finalRootCause != null && finalRootCause != th) {
            obj = new ula(finalRootCause, false, 2, null);
        }
        if (finalRootCause != null && (cancelParent(finalRootCause) || handleJobException(finalRootCause))) {
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
            }
            ((ula) obj).b();
        }
        if (!d2) {
            onCancelling(finalRootCause);
        }
        onCompletionInternal(obj);
        boolean m = ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, eVar, COMPLETING_ALREADY.e(obj));
        if (ASSERTIONS_ENABLED.a() && !m) {
            throw new AssertionError();
        }
        completeStateFinalization(eVar, obj);
        return obj;
    }

    private final Throwable getFinalRootCause(e eVar, List<? extends Throwable> list) {
        Object obj;
        Object obj2 = null;
        if (list.isEmpty()) {
            if (eVar.d()) {
                return new umg(cancellationExceptionMessage(), null, this);
            }
            return null;
        }
        List<? extends Throwable> list2 = list;
        Iterator<T> it = list2.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (!(((Throwable) obj) instanceof CancellationException)) {
                break;
            }
        }
        Throwable th = (Throwable) obj;
        if (th != null) {
            return th;
        }
        Throwable th2 = list.get(0);
        if (th2 instanceof umn) {
            Iterator<T> it2 = list2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                Throwable th3 = (Throwable) next;
                if (th3 != th2 && (th3 instanceof umn)) {
                    obj2 = next;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj2;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    private final void addSuppressedExceptions(Throwable rootCause, List<? extends Throwable> exceptions) {
        if (exceptions.size() <= 1) {
            return;
        }
        Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap(exceptions.size()));
        Throwable e2 = !ASSERTIONS_ENABLED.b() ? rootCause : baseContinuationImplClass.e(rootCause);
        for (Throwable th : exceptions) {
            if (ASSERTIONS_ENABLED.b()) {
                th = baseContinuationImplClass.e(th);
            }
            if (th != rootCause && th != e2 && !(th instanceof CancellationException) && newSetFromMap.add(th)) {
                ued.c(rootCause, th);
            }
        }
    }

    private final boolean tryFinalizeSimpleState(Incomplete state, Object update) {
        if (ASSERTIONS_ENABLED.a() && !(state instanceof ult) && !(state instanceof JobNode)) {
            throw new AssertionError();
        }
        if (ASSERTIONS_ENABLED.a() && !(!(update instanceof ula))) {
            throw new AssertionError();
        }
        if (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, state, COMPLETING_ALREADY.e(update))) {
            return false;
        }
        onCancelling(null);
        onCompletionInternal(update);
        completeStateFinalization(state, update);
        return true;
    }

    private final void completeStateFinalization(Incomplete state, Object update) {
        ChildHandle parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        if (parentHandle$kotlinx_coroutines_core != null) {
            parentHandle$kotlinx_coroutines_core.dispose();
            setParentHandle$kotlinx_coroutines_core(umi.e);
        }
        ula ulaVar = update instanceof ula ? (ula) update : null;
        Throwable th = ulaVar != null ? ulaVar.d : null;
        if (state instanceof JobNode) {
            try {
                ((JobNode) state).invoke(th);
                return;
            } catch (Throwable th2) {
                handleOnCompletionException$kotlinx_coroutines_core(new ulc("Exception in completion handler " + state + " for " + this, th2));
                return;
            }
        }
        umj c2 = state.getC();
        if (c2 == null) {
            return;
        }
        notifyCompletion(c2, th);
    }

    private final void notifyCancelling(umj umjVar, Throwable th) {
        onCancelling(th);
        umj umjVar2 = umjVar;
        ulc ulcVar = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) umjVar2.getNext(); !uhy.e(lockFreeLinkedListNode, umjVar2); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof JobCancellingNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.invoke(th);
                } catch (Throwable th2) {
                    ulc ulcVar2 = ulcVar;
                    if (ulcVar2 == null) {
                        ulcVar2 = null;
                    } else {
                        ued.c(ulcVar2, th2);
                    }
                    if (ulcVar2 == null) {
                        ulcVar = new ulc("Exception in completion handler " + jobNode + " for " + this, th2);
                    }
                }
            }
        }
        ulc ulcVar3 = ulcVar;
        if (ulcVar3 != null) {
            handleOnCompletionException$kotlinx_coroutines_core(ulcVar3);
        }
        cancelParent(th);
    }

    private final boolean cancelParent(Throwable cause) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean z = cause instanceof CancellationException;
        ChildHandle parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        return (parentHandle$kotlinx_coroutines_core == null || parentHandle$kotlinx_coroutines_core == umi.e) ? z : parentHandle$kotlinx_coroutines_core.childCancelled(cause) || z;
    }

    private final /* synthetic */ <T extends JobNode> void notifyHandlers(umj umjVar, Throwable th) {
        umj umjVar2 = umjVar;
        ulc ulcVar = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) umjVar2.getNext(); !uhy.e(lockFreeLinkedListNode, umjVar2); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            uhy.c(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.invoke(th);
                } catch (Throwable th2) {
                    ulc ulcVar2 = ulcVar;
                    if (ulcVar2 == null) {
                        ulcVar2 = null;
                    } else {
                        ued.c(ulcVar2, th2);
                    }
                    if (ulcVar2 == null) {
                        ulcVar = new ulc("Exception in completion handler " + jobNode + " for " + this, th2);
                    }
                }
            }
        }
        ulc ulcVar3 = ulcVar;
        if (ulcVar3 == null) {
            return;
        }
        handleOnCompletionException$kotlinx_coroutines_core(ulcVar3);
    }

    private final int startInternal(Object state) {
        ult ultVar;
        if (state instanceof ult) {
            if (((ult) state).getD()) {
                return 0;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            ultVar = COMPLETING_ALREADY.c;
            if (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, state, ultVar)) {
                return -1;
            }
            onStart();
            return 1;
        }
        if (!(state instanceof umc)) {
            return 0;
        }
        if (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, state, ((umc) state).getC())) {
            return -1;
        }
        onStart();
        return 1;
    }

    @Override // kotlinx.coroutines.Job
    public final CancellationException getCancellationException() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(state$kotlinx_coroutines_core instanceof e)) {
            if (state$kotlinx_coroutines_core instanceof Incomplete) {
                throw new IllegalStateException(uhy.b("Job is still new or active: ", this).toString());
            }
            return state$kotlinx_coroutines_core instanceof ula ? toCancellationException$default(this, ((ula) state$kotlinx_coroutines_core).d, null, 1, null) : new umg(uhy.b(classSimpleName.b(this), (Object) " has completed normally"), null, this);
        }
        Throwable e2 = ((e) state$kotlinx_coroutines_core).e();
        CancellationException cancellationException = e2 != null ? toCancellationException(e2, uhy.b(classSimpleName.b(this), (Object) " is cancelling")) : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        throw new IllegalStateException(uhy.b("Job is still new or active: ", this).toString());
    }

    public static /* synthetic */ CancellationException toCancellationException$default(umd umdVar, Throwable th, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
        }
        if ((i & 1) != 0) {
            str = null;
        }
        return umdVar.toCancellationException(th, str);
    }

    protected final CancellationException toCancellationException(Throwable th, String str) {
        CancellationException cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        if (str == null) {
            str = cancellationExceptionMessage();
        }
        return new umg(str, th, this);
    }

    protected final Throwable getCompletionCause() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof e) {
            Throwable e2 = ((e) state$kotlinx_coroutines_core).e();
            if (e2 != null) {
                return e2;
            }
            throw new IllegalStateException(uhy.b("Job is still new or active: ", this).toString());
        }
        if (state$kotlinx_coroutines_core instanceof Incomplete) {
            throw new IllegalStateException(uhy.b("Job is still new or active: ", this).toString());
        }
        if (state$kotlinx_coroutines_core instanceof ula) {
            return ((ula) state$kotlinx_coroutines_core).d;
        }
        return null;
    }

    protected final boolean getCompletionCauseHandled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof ula) && ((ula) state$kotlinx_coroutines_core).d();
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(Function1<? super Throwable, ueu> handler) {
        return invokeOnCompletion(false, true, handler);
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(boolean onCancelling, boolean invokeImmediately, Function1<? super Throwable, ueu> handler) {
        JobNode makeNode = makeNode(handler, onCancelling);
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof ult) {
                ult ultVar = (ult) state$kotlinx_coroutines_core;
                if (ultVar.getD()) {
                    if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, state$kotlinx_coroutines_core, makeNode)) {
                        return makeNode;
                    }
                } else {
                    promoteEmptyToNodeList(ultVar);
                }
            } else {
                if (state$kotlinx_coroutines_core instanceof Incomplete) {
                    umj c2 = ((Incomplete) state$kotlinx_coroutines_core).getC();
                    if (c2 != null) {
                        DisposableHandle disposableHandle = umi.e;
                        if (onCancelling && (state$kotlinx_coroutines_core instanceof e)) {
                            synchronized (state$kotlinx_coroutines_core) {
                                r3 = ((e) state$kotlinx_coroutines_core).e();
                                if (r3 == null || ((handler instanceof ukv) && !((e) state$kotlinx_coroutines_core).b())) {
                                    if (addLastAtomic(state$kotlinx_coroutines_core, c2, makeNode)) {
                                        if (r3 == null) {
                                            return makeNode;
                                        }
                                        disposableHandle = makeNode;
                                    }
                                }
                                ueu ueuVar = ueu.d;
                            }
                        }
                        if (r3 != null) {
                            if (invokeImmediately) {
                                handler.invoke(r3);
                            }
                            return disposableHandle;
                        }
                        if (addLastAtomic(state$kotlinx_coroutines_core, c2, makeNode)) {
                            return makeNode;
                        }
                    } else {
                        if (state$kotlinx_coroutines_core == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                        }
                        promoteSingleToNodeList((JobNode) state$kotlinx_coroutines_core);
                    }
                } else {
                    if (invokeImmediately) {
                        ula ulaVar = state$kotlinx_coroutines_core instanceof ula ? (ula) state$kotlinx_coroutines_core : null;
                        handler.invoke(ulaVar != null ? ulaVar.d : null);
                    }
                    return umi.e;
                }
            }
        }
    }

    private final JobNode makeNode(Function1<? super Throwable, ueu> handler, boolean onCancelling) {
        if (onCancelling) {
            r0 = handler instanceof JobCancellingNode ? (JobCancellingNode) handler : null;
            if (r0 == null) {
                r0 = new umb(handler);
            }
            r0 = r0;
        } else {
            JobNode jobNode = handler instanceof JobNode ? (JobNode) handler : null;
            if (jobNode != null) {
                if (ASSERTIONS_ENABLED.a() && !(!(jobNode instanceof JobCancellingNode))) {
                    throw new AssertionError();
                }
                r0 = jobNode;
            }
            if (r0 == null) {
                r0 = new ulz(handler);
            }
        }
        r0.setJob(this);
        return r0;
    }

    private final boolean addLastAtomic(Object obj, umj umjVar, JobNode jobNode) {
        int tryCondAddNext;
        umj umjVar2 = umjVar;
        JobNode jobNode2 = jobNode;
        a aVar = new a(jobNode2, this, obj);
        do {
            tryCondAddNext = umjVar2.getPrevNode().tryCondAddNext(jobNode2, umjVar2, aVar);
            if (tryCondAddNext == 1) {
                return true;
            }
        } while (tryCondAddNext != 2);
        return false;
    }

    private final void promoteEmptyToNodeList(ult ultVar) {
        umj umjVar = new umj();
        ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, ultVar, ultVar.getD() ? umjVar : new umc(umjVar));
    }

    private final void promoteSingleToNodeList(JobNode state) {
        state.addOneIfEmpty(new umj());
        ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, state, state.getNextNode());
    }

    @Override // kotlinx.coroutines.Job
    public final Object join(Continuation<? super ueu> continuation) {
        if (!joinInternal()) {
            umh.a(continuation.getContext());
            return ueu.d;
        }
        Object joinSuspend = joinSuspend(continuation);
        return joinSuspend == ugw.a() ? joinSuspend : ueu.d;
    }

    @Override // kotlinx.coroutines.Job
    public final SelectClause0 getOnJoin() {
        return this;
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Added since 1.2.0 for binary compatibility with versions <= 1.1.x")
    public /* synthetic */ boolean cancel(Throwable cause) {
        umg cancellationException$default = cause == null ? null : toCancellationException$default(this, cause, null, 1, null);
        if (cancellationException$default == null) {
            cancellationException$default = new umg(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException$default);
        return true;
    }

    public void cancelInternal(Throwable cause) {
        cancelImpl$kotlinx_coroutines_core(cause);
    }

    @Override // kotlinx.coroutines.ChildJob
    public final void parentCancelled(ParentJob parentJob) {
        cancelImpl$kotlinx_coroutines_core(parentJob);
    }

    public boolean childCancelled(Throwable cause) {
        if (cause instanceof CancellationException) {
            return true;
        }
        return cancelImpl$kotlinx_coroutines_core(cause) && getHandlesException$kotlinx_coroutines_core();
    }

    public final boolean cancelCoroutine(Throwable cause) {
        return cancelImpl$kotlinx_coroutines_core(cause);
    }

    public final boolean cancelImpl$kotlinx_coroutines_core(Object cause) {
        Object obj;
        upu upuVar;
        upu upuVar2;
        upu upuVar3;
        obj = COMPLETING_ALREADY.e;
        if (getOnCancelComplete$kotlinx_coroutines_core() && (obj = cancelMakeCompleting(cause)) == COMPLETING_ALREADY.d) {
            return true;
        }
        upuVar = COMPLETING_ALREADY.e;
        if (obj == upuVar) {
            obj = makeCancelling(cause);
        }
        upuVar2 = COMPLETING_ALREADY.e;
        if (obj == upuVar2 || obj == COMPLETING_ALREADY.d) {
            return true;
        }
        upuVar3 = COMPLETING_ALREADY.j;
        if (obj == upuVar3) {
            return false;
        }
        afterCompletion(obj);
        return true;
    }

    public static /* synthetic */ umg defaultCancellationException$kotlinx_coroutines_core$default(umd umdVar, String str, Throwable th, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defaultCancellationException");
        }
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            th = null;
        }
        if (str == null) {
            str = umdVar.cancellationExceptionMessage();
        }
        return new umg(str, th, umdVar);
    }

    public final umg defaultCancellationException$kotlinx_coroutines_core(String str, Throwable th) {
        if (str == null) {
            str = cancellationExceptionMessage();
        }
        return new umg(str, th, this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Throwable] */
    @Override // kotlinx.coroutines.ParentJob
    public CancellationException getChildJobCancellationCause() {
        CancellationException cancellationException;
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof e) {
            cancellationException = ((e) state$kotlinx_coroutines_core).e();
        } else if (state$kotlinx_coroutines_core instanceof ula) {
            cancellationException = ((ula) state$kotlinx_coroutines_core).d;
        } else {
            if (state$kotlinx_coroutines_core instanceof Incomplete) {
                throw new IllegalStateException(uhy.b("Cannot be cancelling child in this state: ", state$kotlinx_coroutines_core).toString());
            }
            cancellationException = null;
        }
        CancellationException cancellationException2 = cancellationException instanceof CancellationException ? cancellationException : null;
        return cancellationException2 == null ? new umg(uhy.b("Parent job is ", (Object) stateString(state$kotlinx_coroutines_core)), cancellationException, this) : cancellationException2;
    }

    private final Throwable createCauseException(Object cause) {
        if (cause == null || (cause instanceof Throwable)) {
            Throwable th = (Throwable) cause;
            return th == null ? new umg(cancellationExceptionMessage(), null, this) : th;
        }
        if (cause != null) {
            return ((ParentJob) cause).getChildJobCancellationCause();
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
    }

    private final umj getOrPromoteCancellingList(Incomplete incomplete) {
        umj c2 = incomplete.getC();
        if (c2 != null) {
            return c2;
        }
        if (incomplete instanceof ult) {
            return new umj();
        }
        if (incomplete instanceof JobNode) {
            promoteSingleToNodeList((JobNode) incomplete);
            return null;
        }
        throw new IllegalStateException(uhy.b("State should have list: ", incomplete).toString());
    }

    private final boolean tryMakeCancelling(Incomplete state, Throwable rootCause) {
        if (ASSERTIONS_ENABLED.a() && !(!(state instanceof e))) {
            throw new AssertionError();
        }
        if (ASSERTIONS_ENABLED.a() && !state.getD()) {
            throw new AssertionError();
        }
        umj orPromoteCancellingList = getOrPromoteCancellingList(state);
        if (orPromoteCancellingList == null) {
            return false;
        }
        if (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, state, new e(orPromoteCancellingList, false, rootCause))) {
            return false;
        }
        notifyCancelling(orPromoteCancellingList, rootCause);
        return true;
    }

    private final Object tryMakeCompleting(Object state, Object proposedUpdate) {
        upu upuVar;
        upu upuVar2;
        if (!(state instanceof Incomplete)) {
            upuVar2 = COMPLETING_ALREADY.e;
            return upuVar2;
        }
        if (((state instanceof ult) || (state instanceof JobNode)) && !(state instanceof ukv) && !(proposedUpdate instanceof ula)) {
            if (tryFinalizeSimpleState((Incomplete) state, proposedUpdate)) {
                return proposedUpdate;
            }
            upuVar = COMPLETING_ALREADY.b;
            return upuVar;
        }
        return tryMakeCompletingSlowPath((Incomplete) state, proposedUpdate);
    }

    private final Object tryMakeCompletingSlowPath(Incomplete state, Object proposedUpdate) {
        upu upuVar;
        upu upuVar2;
        upu upuVar3;
        umj orPromoteCancellingList = getOrPromoteCancellingList(state);
        if (orPromoteCancellingList == null) {
            upuVar3 = COMPLETING_ALREADY.b;
            return upuVar3;
        }
        e eVar = state instanceof e ? (e) state : null;
        if (eVar == null) {
            eVar = new e(orPromoteCancellingList, false, null);
        }
        synchronized (eVar) {
            if (eVar.b()) {
                upuVar2 = COMPLETING_ALREADY.e;
                return upuVar2;
            }
            eVar.b(true);
            if (eVar != state && !ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, state, eVar)) {
                upuVar = COMPLETING_ALREADY.b;
                return upuVar;
            }
            if (ASSERTIONS_ENABLED.a() && !(!eVar.a())) {
                throw new AssertionError();
            }
            boolean d2 = eVar.d();
            ula ulaVar = proposedUpdate instanceof ula ? (ula) proposedUpdate : null;
            if (ulaVar != null) {
                eVar.c(ulaVar.d);
            }
            Throwable e2 = true ^ d2 ? eVar.e() : null;
            ueu ueuVar = ueu.d;
            if (e2 != null) {
                notifyCancelling(orPromoteCancellingList, e2);
            }
            ukv firstChild = firstChild(state);
            if (firstChild != null && tryWaitForChild(eVar, firstChild, proposedUpdate)) {
                return COMPLETING_ALREADY.d;
            }
            return finalizeFinishingState(eVar, proposedUpdate);
        }
    }

    private final Throwable getExceptionOrNull(Object obj) {
        ula ulaVar = obj instanceof ula ? (ula) obj : null;
        if (ulaVar == null) {
            return null;
        }
        return ulaVar.d;
    }

    private final ukv firstChild(Incomplete incomplete) {
        ukv ukvVar = incomplete instanceof ukv ? (ukv) incomplete : null;
        if (ukvVar != null) {
            return ukvVar;
        }
        umj c2 = incomplete.getC();
        if (c2 == null) {
            return null;
        }
        return nextChild(c2);
    }

    private final boolean tryWaitForChild(e eVar, ukv ukvVar, Object obj) {
        while (Job.e.a(ukvVar.f17452a, false, false, new b(this, eVar, ukvVar, obj), 1, null) == umi.e) {
            ukvVar = nextChild(ukvVar);
            if (ukvVar == null) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void continueCompleting(e eVar, ukv ukvVar, Object obj) {
        if (ASSERTIONS_ENABLED.a() && getState$kotlinx_coroutines_core() != eVar) {
            throw new AssertionError();
        }
        ukv nextChild = nextChild(ukvVar);
        if (nextChild == null || !tryWaitForChild(eVar, nextChild, obj)) {
            afterCompletion(finalizeFinishingState(eVar, obj));
        }
    }

    private final ukv nextChild(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getPrevNode();
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if (!lockFreeLinkedListNode.isRemoved()) {
                if (lockFreeLinkedListNode instanceof ukv) {
                    return (ukv) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof umj) {
                    return null;
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lkotlinx/coroutines/Job;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.JobSupport$children$1", f = "JobSupport.kt", i = {1, 1, 1}, l = {952, 954}, m = "invokeSuspend", n = {"$this$sequence", "this_$iv", "cur$iv"}, s = {"L$0", "L$1", "L$2"})
    static final class d extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Job>, Continuation<? super ueu>, Object> {

        /* renamed from: a, reason: collision with root package name */
        Object f17465a;
        private /* synthetic */ Object b;
        int c;
        Object e;

        /* JADX WARN: Removed duplicated region for block: B:9:0x006a  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x006c -> B:6:0x0082). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x007f -> B:6:0x0082). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = defpackage.ugw.a()
                int r1 = r6.c
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L2a
                if (r1 == r3) goto L26
                if (r1 != r2) goto L1e
                java.lang.Object r1 = r6.f17465a
                kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
                java.lang.Object r3 = r6.e
                upj r3 = (defpackage.upj) r3
                java.lang.Object r4 = r6.b
                kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
                defpackage.createFailure.b(r7)
                goto L82
            L1e:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L26:
                defpackage.createFailure.b(r7)
                goto L87
            L2a:
                defpackage.createFailure.b(r7)
                java.lang.Object r7 = r6.b
                kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
                umd r1 = defpackage.umd.this
                java.lang.Object r1 = r1.getState$kotlinx_coroutines_core()
                boolean r4 = r1 instanceof defpackage.ukv
                if (r4 == 0) goto L4b
                ukv r1 = (defpackage.ukv) r1
                kotlinx.coroutines.ChildJob r1 = r1.f17452a
                r2 = r6
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r6.c = r3
                java.lang.Object r7 = r7.yield(r1, r2)
                if (r7 != r0) goto L87
                return r0
            L4b:
                boolean r3 = r1 instanceof kotlinx.coroutines.Incomplete
                if (r3 == 0) goto L87
                kotlinx.coroutines.Incomplete r1 = (kotlinx.coroutines.Incomplete) r1
                umj r1 = r1.getC()
                if (r1 != 0) goto L58
                goto L87
            L58:
                upj r1 = (defpackage.upj) r1
                java.lang.Object r3 = r1.getNext()
                kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r3
                r4 = r7
                r5 = r3
                r3 = r1
                r1 = r5
            L64:
                boolean r7 = defpackage.uhy.e(r1, r3)
                if (r7 != 0) goto L87
                boolean r7 = r1 instanceof defpackage.ukv
                if (r7 == 0) goto L82
                r7 = r1
                ukv r7 = (defpackage.ukv) r7
                kotlinx.coroutines.ChildJob r7 = r7.f17452a
                r6.b = r4
                r6.e = r3
                r6.f17465a = r1
                r6.c = r2
                java.lang.Object r7 = r4.yield(r7, r6)
                if (r7 != r0) goto L82
                return r0
            L82:
                kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = r1.getNextNode()
                goto L64
            L87:
                ueu r7 = defpackage.ueu.d
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: umd.d.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final Object invoke(SequenceScope<? super Job> sequenceScope, Continuation<? super ueu> continuation) {
            return ((d) create(sequenceScope, continuation)).invokeSuspend(ueu.d);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<ueu> create(Object obj, Continuation<?> continuation) {
            d dVar = umd.this.new d(continuation);
            dVar.b = obj;
            return dVar;
        }

        d(Continuation<? super d> continuation) {
            super(2, continuation);
        }
    }

    @Override // kotlinx.coroutines.Job
    public final Sequence<Job> getChildren() {
        return ujh.c(new d(null));
    }

    @Override // kotlinx.coroutines.Job
    public final ChildHandle attachChild(ChildJob child) {
        return (ChildHandle) Job.e.a(this, true, false, new ukv(child), 2, null);
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(Throwable exception) {
        throw exception;
    }

    public String toString() {
        return toDebugString() + '@' + classSimpleName.d(this);
    }

    public final String toDebugString() {
        return nameString$kotlinx_coroutines_core() + '{' + stateString(getState$kotlinx_coroutines_core()) + '}';
    }

    public String nameString$kotlinx_coroutines_core() {
        return classSimpleName.b(this);
    }

    private final String stateString(Object state) {
        if (state instanceof e) {
            e eVar = (e) state;
            if (eVar.d()) {
                return "Cancelling";
            }
            if (eVar.b()) {
                return "Completing";
            }
        } else {
            if (!(state instanceof Incomplete)) {
                return state instanceof ula ? "Cancelled" : "Completed";
            }
            if (!((Incomplete) state).getD()) {
                return "New";
            }
        }
        return "Active";
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u00002\u00060\u0018j\u0002`,2\u00020-B!\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00050\rj\b\u0012\u0004\u0012\u00020\u0005`\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R(\u0010\u001e\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00188B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b!\u0010 R$\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010 \"\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b$\u0010 R\u001a\u0010\u0002\u001a\u00020\u00018\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0002\u0010%\u001a\u0004\b&\u0010'R(\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010\f¨\u0006+"}, d2 = {"Lkotlinx/coroutines/JobSupport$Finishing;", "Lkotlinx/coroutines/NodeList;", "list", "", "isCompleting", "", "rootCause", "<init>", "(Lkotlinx/coroutines/NodeList;ZLjava/lang/Throwable;)V", TrackConstants$Events.EXCEPTION, "", "addExceptionLocked", "(Ljava/lang/Throwable;)V", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "allocateList", "()Ljava/util/ArrayList;", "proposedException", "", "sealLocked", "(Ljava/lang/Throwable;)Ljava/util/List;", "", "toString", "()Ljava/lang/String;", "", "value", "getExceptionsHolder", "()Ljava/lang/Object;", "setExceptionsHolder", "(Ljava/lang/Object;)V", "exceptionsHolder", r3.B, "()Z", "isCancelling", "setCompleting", "(Z)V", "isSealed", "Lkotlinx/coroutines/NodeList;", "getList", "()Lkotlinx/coroutines/NodeList;", "getRootCause", "()Ljava/lang/Throwable;", "setRootCause", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/Incomplete;"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class e implements Incomplete {
        private volatile /* synthetic */ Object _exceptionsHolder = null;
        private volatile /* synthetic */ int _isCompleting;
        private volatile /* synthetic */ Object _rootCause;
        private final umj d;

        @Override // kotlinx.coroutines.Incomplete
        /* renamed from: getList, reason: from getter */
        public umj getC() {
            return this.d;
        }

        public e(umj umjVar, boolean z, Throwable th) {
            this.d = umjVar;
            this._isCompleting = z ? 1 : 0;
            this._rootCause = th;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
        public final boolean b() {
            return this._isCompleting;
        }

        public final void b(boolean z) {
            this._isCompleting = z ? 1 : 0;
        }

        public final Throwable e() {
            return (Throwable) this._rootCause;
        }

        public final void b(Throwable th) {
            this._rootCause = th;
        }

        /* renamed from: i, reason: from getter */
        private final Object get_exceptionsHolder() {
            return this._exceptionsHolder;
        }

        private final void b(Object obj) {
            this._exceptionsHolder = obj;
        }

        public final boolean a() {
            upu upuVar;
            Object obj = get_exceptionsHolder();
            upuVar = COMPLETING_ALREADY.i;
            return obj == upuVar;
        }

        public final boolean d() {
            return e() != null;
        }

        @Override // kotlinx.coroutines.Incomplete
        /* renamed from: isActive */
        public boolean getD() {
            return e() == null;
        }

        public final List<Throwable> e(Throwable th) {
            ArrayList<Throwable> arrayList;
            upu upuVar;
            Object obj = get_exceptionsHolder();
            if (obj == null) {
                arrayList = c();
            } else if (obj instanceof Throwable) {
                ArrayList<Throwable> c = c();
                c.add(obj);
                arrayList = c;
            } else {
                if (!(obj instanceof ArrayList)) {
                    throw new IllegalStateException(uhy.b("State is ", obj).toString());
                }
                arrayList = (ArrayList) obj;
            }
            Throwable e = e();
            if (e != null) {
                arrayList.add(0, e);
            }
            if (th != null && !uhy.e(th, e)) {
                arrayList.add(th);
            }
            upuVar = COMPLETING_ALREADY.i;
            b(upuVar);
            return arrayList;
        }

        public final void c(Throwable th) {
            Throwable e = e();
            if (e == null) {
                b(th);
                return;
            }
            if (th == e) {
                return;
            }
            Object obj = get_exceptionsHolder();
            if (obj == null) {
                b((Object) th);
                return;
            }
            if (!(obj instanceof Throwable)) {
                if (!(obj instanceof ArrayList)) {
                    throw new IllegalStateException(uhy.b("State is ", obj).toString());
                }
                ((ArrayList) obj).add(th);
            } else {
                if (th == obj) {
                    return;
                }
                ArrayList<Throwable> c = c();
                c.add(obj);
                c.add(th);
                b(c);
            }
        }

        private final ArrayList<Throwable> c() {
            return new ArrayList<>(4);
        }

        public String toString() {
            return "Finishing[cancelling=" + d() + ", completing=" + b() + ", rootCause=" + e() + ", exceptions=" + get_exceptionsHolder() + ", list=" + getC() + ']';
        }
    }

    private final boolean isCancelling(Incomplete incomplete) {
        return (incomplete instanceof e) && ((e) incomplete).d();
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/JobSupport$ChildCompletion;", "Lkotlinx/coroutines/JobNode;", "parent", "Lkotlinx/coroutines/JobSupport;", "state", "Lkotlinx/coroutines/JobSupport$Finishing;", "child", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", TrackConstants$Opers.INVOKE, "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class b extends JobNode {

        /* renamed from: a, reason: collision with root package name */
        private final ukv f17464a;
        private final e c;
        private final umd d;
        private final Object e;

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ ueu invoke(Throwable th) {
            invoke2(th);
            return ueu.d;
        }

        public b(umd umdVar, e eVar, ukv ukvVar, Object obj) {
            this.d = umdVar;
            this.c = eVar;
            this.f17464a = ukvVar;
            this.e = obj;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(Throwable cause) {
            this.d.continueCompleting(this.c, this.f17464a, this.e);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/JobSupport$AwaitContinuation;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CancellableContinuationImpl;", "delegate", "Lkotlin/coroutines/Continuation;", "job", "Lkotlinx/coroutines/JobSupport;", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/JobSupport;)V", "getContinuationCancellationCause", "", "parent", "Lkotlinx/coroutines/Job;", "nameString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class c<T> extends ukr<T> {
        private final umd b;

        public c(Continuation<? super T> continuation, umd umdVar) {
            super(continuation, 1);
            this.b = umdVar;
        }

        @Override // defpackage.ukr
        public Throwable c(Job job) {
            Throwable e;
            Object state$kotlinx_coroutines_core = this.b.getState$kotlinx_coroutines_core();
            return (!(state$kotlinx_coroutines_core instanceof e) || (e = ((e) state$kotlinx_coroutines_core).e()) == null) ? state$kotlinx_coroutines_core instanceof ula ? ((ula) state$kotlinx_coroutines_core).d : job.getCancellationException() : e;
        }

        @Override // defpackage.ukr
        protected String a() {
            return "AwaitContinuation";
        }
    }

    public final boolean isCompletedExceptionally() {
        return getState$kotlinx_coroutines_core() instanceof ula;
    }

    public final Throwable getCompletionExceptionOrNull() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(!(state$kotlinx_coroutines_core instanceof Incomplete))) {
            throw new IllegalStateException("This job has not completed yet".toString());
        }
        return getExceptionOrNull(state$kotlinx_coroutines_core);
    }

    public final Object getCompletedInternal$kotlinx_coroutines_core() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(!(state$kotlinx_coroutines_core instanceof Incomplete))) {
            throw new IllegalStateException("This job has not completed yet".toString());
        }
        if (state$kotlinx_coroutines_core instanceof ula) {
            throw ((ula) state$kotlinx_coroutines_core).d;
        }
        return COMPLETING_ALREADY.c(state$kotlinx_coroutines_core);
    }

    public final Object awaitInternal$kotlinx_coroutines_core(Continuation<Object> continuation) {
        Object state$kotlinx_coroutines_core;
        Throwable c2;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                if (state$kotlinx_coroutines_core instanceof ula) {
                    Throwable th = ((ula) state$kotlinx_coroutines_core).d;
                    if (!ASSERTIONS_ENABLED.b()) {
                        throw th;
                    }
                    if (!(continuation instanceof CoroutineStackFrame)) {
                        throw th;
                    }
                    c2 = baseContinuationImplClass.c(th, (CoroutineStackFrame) continuation);
                    throw c2;
                }
                return COMPLETING_ALREADY.c(state$kotlinx_coroutines_core);
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return awaitSuspend(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitSuspend(Continuation<Object> continuation) {
        c cVar = new c(ugw.a(continuation), this);
        cVar.initCancellability();
        getOrCreateCancellableContinuation.c(cVar, invokeOnCompletion(new umk(cVar)));
        Object e2 = cVar.e();
        if (e2 == ugw.a()) {
            probeCoroutineCreated.b(continuation);
        }
        return e2;
    }

    public final <T, R> void selectAwaitCompletion$kotlinx_coroutines_core(SelectInstance<? super R> select, Function2<? super T, ? super Continuation<? super R>, ? extends Object> block) {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof ula) {
            select.resumeSelectWithException(((ula) state$kotlinx_coroutines_core).d);
        } else {
            dispatcherFailure.e(block, COMPLETING_ALREADY.c(state$kotlinx_coroutines_core), select.getCompletion(), null, 4, null);
        }
    }

    public final Object getState$kotlinx_coroutines_core() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    private final void notifyCompletion(umj umjVar, Throwable th) {
        umj umjVar2 = umjVar;
        ulc ulcVar = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) umjVar2.getNext(); !uhy.e(lockFreeLinkedListNode, umjVar2); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof JobNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.invoke(th);
                } catch (Throwable th2) {
                    ulc ulcVar2 = ulcVar;
                    if (ulcVar2 == null) {
                        ulcVar2 = null;
                    } else {
                        ued.c(ulcVar2, th2);
                    }
                    if (ulcVar2 == null) {
                        ulcVar = new ulc("Exception in completion handler " + jobNode + " for " + this, th2);
                    }
                }
            }
        }
        ulc ulcVar3 = ulcVar;
        if (ulcVar3 == null) {
            return;
        }
        handleOnCompletionException$kotlinx_coroutines_core(ulcVar3);
    }

    @Override // kotlinx.coroutines.Job
    public final boolean start() {
        int startInternal;
        do {
            startInternal = startInternal(getState$kotlinx_coroutines_core());
            if (startInternal == 0) {
                return false;
            }
        } while (startInternal != 1);
        return true;
    }

    private final boolean joinInternal() {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                return false;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object joinSuspend(Continuation<? super ueu> continuation) {
        ukr ukrVar = new ukr(ugw.a(continuation), 1);
        ukrVar.initCancellability();
        ukr ukrVar2 = ukrVar;
        getOrCreateCancellableContinuation.c(ukrVar2, invokeOnCompletion(new uml(ukrVar2)));
        Object e2 = ukrVar.e();
        if (e2 == ugw.a()) {
            probeCoroutineCreated.b(continuation);
        }
        return e2 == ugw.a() ? e2 : ueu.d;
    }

    @Override // kotlinx.coroutines.selects.SelectClause0
    public final <R> void registerSelectClause0(SelectInstance<? super R> select, Function1<? super Continuation<? super R>, ? extends Object> block) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (select.isSelected()) {
                return;
            }
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                if (select.trySelect()) {
                    startDirect.e(block, select.getCompletion());
                    return;
                }
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) != 0);
        select.disposeOnSelect(invokeOnCompletion(new umq(select, block)));
    }

    public final void removeNode$kotlinx_coroutines_core(JobNode node) {
        Object state$kotlinx_coroutines_core;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        ult ultVar;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof JobNode)) {
                if (!(state$kotlinx_coroutines_core instanceof Incomplete) || ((Incomplete) state$kotlinx_coroutines_core).getC() == null) {
                    return;
                }
                node.remove();
                return;
            }
            if (state$kotlinx_coroutines_core != node) {
                return;
            }
            atomicReferenceFieldUpdater = _state$FU;
            ultVar = COMPLETING_ALREADY.c;
        } while (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, state$kotlinx_coroutines_core, ultVar));
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cause) {
        if (cause == null) {
            cause = new umg(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cause);
    }

    private final Object cancelMakeCompleting(Object cause) {
        upu upuVar;
        Object tryMakeCompleting;
        upu upuVar2;
        do {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete) || ((state$kotlinx_coroutines_core instanceof e) && ((e) state$kotlinx_coroutines_core).b())) {
                upuVar = COMPLETING_ALREADY.e;
                return upuVar;
            }
            tryMakeCompleting = tryMakeCompleting(state$kotlinx_coroutines_core, new ula(createCauseException(cause), false, 2, null));
            upuVar2 = COMPLETING_ALREADY.b;
        } while (tryMakeCompleting == upuVar2);
        return tryMakeCompleting;
    }

    private final Object makeCancelling(Object cause) {
        upu upuVar;
        upu upuVar2;
        upu upuVar3;
        upu upuVar4;
        upu upuVar5;
        upu upuVar6;
        Throwable th = null;
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof e)) {
                if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                    upuVar3 = COMPLETING_ALREADY.j;
                    return upuVar3;
                }
                if (th == null) {
                    th = createCauseException(cause);
                }
                Incomplete incomplete = (Incomplete) state$kotlinx_coroutines_core;
                if (incomplete.getD()) {
                    if (tryMakeCancelling(incomplete, th)) {
                        upuVar4 = COMPLETING_ALREADY.e;
                        return upuVar4;
                    }
                } else {
                    Object tryMakeCompleting = tryMakeCompleting(state$kotlinx_coroutines_core, new ula(th, false, 2, null));
                    upuVar5 = COMPLETING_ALREADY.e;
                    if (tryMakeCompleting != upuVar5) {
                        upuVar6 = COMPLETING_ALREADY.b;
                        if (tryMakeCompleting != upuVar6) {
                            return tryMakeCompleting;
                        }
                    } else {
                        throw new IllegalStateException(uhy.b("Cannot happen in ", state$kotlinx_coroutines_core).toString());
                    }
                }
            } else {
                synchronized (state$kotlinx_coroutines_core) {
                    if (((e) state$kotlinx_coroutines_core).a()) {
                        upuVar2 = COMPLETING_ALREADY.j;
                        return upuVar2;
                    }
                    boolean d2 = ((e) state$kotlinx_coroutines_core).d();
                    if (cause != null || !d2) {
                        if (th == null) {
                            th = createCauseException(cause);
                        }
                        ((e) state$kotlinx_coroutines_core).c(th);
                    }
                    Throwable e2 = d2 ^ true ? ((e) state$kotlinx_coroutines_core).e() : null;
                    if (e2 != null) {
                        notifyCancelling(((e) state$kotlinx_coroutines_core).getC(), e2);
                    }
                    upuVar = COMPLETING_ALREADY.e;
                    return upuVar;
                }
            }
        }
    }

    public final boolean makeCompleting$kotlinx_coroutines_core(Object proposedUpdate) {
        Object tryMakeCompleting;
        upu upuVar;
        upu upuVar2;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), proposedUpdate);
            upuVar = COMPLETING_ALREADY.e;
            if (tryMakeCompleting == upuVar) {
                return false;
            }
            if (tryMakeCompleting == COMPLETING_ALREADY.d) {
                return true;
            }
            upuVar2 = COMPLETING_ALREADY.b;
        } while (tryMakeCompleting == upuVar2);
        afterCompletion(tryMakeCompleting);
        return true;
    }

    public final Object makeCompletingOnce$kotlinx_coroutines_core(Object proposedUpdate) {
        Object tryMakeCompleting;
        upu upuVar;
        upu upuVar2;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), proposedUpdate);
            upuVar = COMPLETING_ALREADY.e;
            if (tryMakeCompleting != upuVar) {
                upuVar2 = COMPLETING_ALREADY.b;
            } else {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + proposedUpdate, getExceptionOrNull(proposedUpdate));
            }
        } while (tryMakeCompleting == upuVar2);
        return tryMakeCompleting;
    }

    public final <T, R> void registerSelectClause1Internal$kotlinx_coroutines_core(SelectInstance<? super R> select, Function2<? super T, ? super Continuation<? super R>, ? extends Object> block) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (select.isSelected()) {
                return;
            }
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                if (select.trySelect()) {
                    if (state$kotlinx_coroutines_core instanceof ula) {
                        select.resumeSelectWithException(((ula) state$kotlinx_coroutines_core).d);
                        return;
                    } else {
                        startDirect.e(block, COMPLETING_ALREADY.c(state$kotlinx_coroutines_core), select.getCompletion());
                        return;
                    }
                }
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) != 0);
        select.disposeOnSelect(invokeOnCompletion(new umo(select, block)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }
}
