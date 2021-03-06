/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Lachlan Dowding
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

package permafrost.tundra.server;

import com.wm.app.b2b.server.InvokeState;
import com.wm.lang.ns.NSService;
import java.util.Stack;

/**
 * A collection of convenience methods for working with InvokeState objects.
 */
public class InvokeStateHelper {
    /**
     * Disallow instantiation of this class.
     */
    private InvokeStateHelper() {}

    /**
     * Returns a clone of the given InvokeState, with the call stack also cloned.
     *
     * @param invokeState   The object to clone.
     * @return              A clone of the given object, with the call stack also cloned.
     */
    @SuppressWarnings("unchecked")
    public static InvokeState clone(InvokeState invokeState) {
        if (invokeState == null) throw new NullPointerException("invokeState must not be null");

        InvokeState outputState = (InvokeState)invokeState.clone();
        Stack<NSService> stack = invokeState.getCallStack();
        for (NSService service : stack) {
            outputState.pushService(service);
        }
        return outputState;
    }
}
