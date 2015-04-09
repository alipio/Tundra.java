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

package permafrost.tundra.math;

import org.junit.Test;
import permafrost.tundra.io.ParseException;

import static org.junit.Assert.*;

public class IntegerHelperTest {
    @Test
    public void testParseNullArgument() throws Exception {
        assertNull(IntegerHelper.parse(null));
    }

    @Test(expected = ParseException.class)
    public void testParseUnparseableArgument() throws Exception {
        IntegerHelper.parse("test");
    }

    @Test
    public void testParseParseableArgument() throws Exception {
        assertEquals(123, IntegerHelper.parse("123").intValue());
    }

    @Test
    public void testParseWithRadix() throws Exception {
        assertEquals(255, IntegerHelper.parse("FF", 16).intValue());
    }
}