/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Lachlan Dowding
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package permafrost.tundra.lang;

import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;
import java.util.Arrays;
import java.util.Date;
import javax.xml.datatype.Duration;

/**
 * A collection of convenience methods for working with Thread objects.
 */
public final class ThreadHelper {
    /**
     * Disallow instantiation of this class.
     */
    private ThreadHelper() {}

    /**
     * Returns the currently executing thread.
     *
     * @return The currently executing thread.
     */
    public static Thread current() {
        return Thread.currentThread();
    }

    /**
     * Returns a list of all the threads in the current context.
     *
     * @return A list of all the threads in the current context.
     */
    public static Thread[] list() {
        ThreadGroup root = root();
        Thread[] threads = new Thread[root.activeCount() * 2];
        int threadCount;

        while ((threadCount = root.enumerate(threads, true)) == threads.length ) {
            threads = new Thread[threads.length * 2];
        }

        return Arrays.copyOf(threads, threadCount);
    }

    /**
     * Returns the root thread group.
     *
     * @return The root thread group.
     */
    public static ThreadGroup root() {
        ThreadGroup root = current().getThreadGroup();
        ThreadGroup parent;

        while ((parent = root.getParent()) != null) {
            root = parent;
        }

        return root;
    }

    /**
     * Sleeps the current thread for the given duration.
     *
     * @param duration The duration to sleep.
     */
    public static void sleep(Duration duration) {
        if (duration != null) {
            sleep(duration.getTimeInMillis(new Date()));
        }
    }

    /**
     * Sleeps the current thread for the given duration.
     *
     * @param milliseconds The number of milliseconds to sleep.
     */
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Returns a within valid range thread priority given a priority
     * that may or may not be within the valid range.
     *
     * @param priority The thread priority to be normalized.
     * @return         If the given priority is less than the minimum allowed, the minimum priority is returned;
     *                 If the given priority is more than the maximum allowed, the maximum priority is returned;
     *                 Otherwise the given priority is returned unmodified.
     */
    public static int normalizePriority(int priority) {
        if (priority < Thread.MIN_PRIORITY) {
            priority = Thread.MIN_PRIORITY;
        } else if (priority > Thread.MAX_PRIORITY) {
            priority = Thread.MAX_PRIORITY;
        }

        return priority;
    }

    /**
     * Converts a Thread object to an IData representation.
     *
     * @param thread The Thread to be converted.
     * @return An IData representation of the given Thread.
     */
    public static IData toIData(Thread thread) {
        if (thread == null) return null;

        IData output = IDataFactory.create();
        IDataCursor cursor = output.getCursor();

        IDataUtil.put(cursor, "id", "" + thread.getId());
        IDataUtil.put(cursor, "name", thread.getName());
        IDataUtil.put(cursor, "description", thread.toString());
        IDataUtil.put(cursor, "state", thread.getState().toString());
        IDataUtil.put(cursor, "priority", "" + thread.getPriority());

        ThreadGroup group = thread.getThreadGroup();
        if (group != null) IDataUtil.put(cursor, "group", group.getName());

        IDataUtil.put(cursor, "alive?", "" + thread.isAlive());
        IDataUtil.put(cursor, "interrupted?", "" + thread.isInterrupted());
        IDataUtil.put(cursor, "daemon?", "" + thread.isDaemon());

        IData[] stack = StackTraceElementHelper.toIDataArray(thread.getStackTrace());
        if (stack != null) {
            IDataUtil.put(cursor, "stack", stack);
            IDataUtil.put(cursor, "stack.length", "" + stack.length);
        } else {
            IDataUtil.put(cursor, "stack.length", "0");
        }

        IDataUtil.put(cursor, "thread", thread);

        cursor.destroy();

        return output;
    }

    /**
     * Converts the given Thread[] to an IData[] representation.
     *
     * @param threads The Thread[] to be converted.
     * @return An IData[] representation of the given Thread[].
     */
    public static IData[] toIDataArray(Thread... threads) {
        if (threads == null) return null;

        IData[] output = new IData[threads.length];

        for (int i = 0; i < threads.length; i++) {
            output[i] = toIData(threads[i]);
        }

        return output;
    }
}
