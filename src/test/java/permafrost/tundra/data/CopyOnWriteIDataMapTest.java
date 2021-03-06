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

package permafrost.tundra.data;

import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CopyOnWriteIDataMapTest {
    @Test
    public void testCopyOnWrite() throws Exception {
        IDataMap document = new IDataMap();
        document.put("a", "1");
        document.put("b", "2");

        IDataMap child = new IDataMap();
        child.put("c", "3");
        document.put("child", child);

        CopyOnWriteIDataMap copyOnWrite = CopyOnWriteIDataMap.of((IData)document);
        IDataCursor cursor = copyOnWrite.getCursor();

        assertEquals("Wrapped document children not mutated", IDataMap.class, document.get("child").getClass());
        assertTrue(child == document.get("child"));

        assertEquals("Document children mutated when returned by wrapper", CopyOnWriteIDataMap.class, copyOnWrite.get("child").getClass());
        assertTrue(child != copyOnWrite.get("child"));

        int i = 1;
        while(cursor.next()) {
            String key = cursor.getKey();
            Object value = cursor.getValue();
            if (key.equals("child") && value instanceof IData) {
                IDataCursor childCursor = ((IData)value).getCursor();
                childCursor.insertAfter("z", "9");

                assertTrue("child should not equal value", child != value);
                assertEquals(2, IDataHelper.size((IData)value));
                assertEquals(1, IDataHelper.size(child));
            }

            cursor.insertAfter("d" + i, "" + i);
            i++;
        }

        assertEquals(2, IDataHelper.size(IDataUtil.getIData(cursor, "child")));
        assertEquals(3, IDataHelper.size(document));
        assertEquals(1, IDataHelper.size(child));
        assertEquals(6, IDataHelper.size(copyOnWrite));
    }
}