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

package permafrost.tundra.content;

import java.util.Collection;
import permafrost.tundra.lang.UnrecoverableException;

/**
 * Throw a UnsupportedException to indicate that the provided data is not supported.
 */
public class UnsupportedException extends StrictException {
    /**
     * Constructs a new UnsupportedException.
     */
    public UnsupportedException() {
        super();
    }

    /**
     * Constructs a new UnsupportedException with the given message.
     *
     * @param message A message describing why the UnsupportedException was thrown.
     */
    public UnsupportedException(String message) {
        super(message);
    }

    /**
     * Constructs a new UnsupportedException with the given cause.
     *
     * @param cause The cause of this UnsupportedException.
     */
    public UnsupportedException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new UnsupportedException with the given message and cause.
     *
     * @param message A message describing why the UnsupportedException was thrown.
     * @param cause   The cause of this Exception.
     */
    public UnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new UnsupportedException with the given list of exceptions.
     *
     * @param exceptions A collection of exceptions this exception will wrap.
     */
    public UnsupportedException(Collection<? extends Throwable> exceptions) {
        super(exceptions);
    }

    /**
     * Constructs a new UnsupportedException with the given list of exceptions.
     *
     * @param exceptions A collection of exceptions this exception will wrap.
     */
    public UnsupportedException(Throwable... exceptions) {
        super(exceptions);
    }
}
