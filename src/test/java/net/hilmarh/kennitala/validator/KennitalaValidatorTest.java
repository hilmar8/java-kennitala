/*
    The MIT License (MIT)

    Copyright (c) 2014 Hilmar Ævar Hilmarsson

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
 */
package net.hilmarh.kennitala.validator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tests for KennitalaValidator.
 *
 * @author Hilmar Ævar Hilmarsson (hilmarh@gmail.com)
 * @version 1.0
 * @since 1.0
 */
public class KennitalaValidatorTest {
    @Test
    public void testIsValid() throws Exception {
        final Map<String, Boolean> kennitalaMap = new HashMap<String, Boolean>();

        kennitalaMap.put("", false);
        kennitalaMap.put("5810080150", true);
        kennitalaMap.put("5402697509", true);
        kennitalaMap.put("540269-7509", true);
        kennitalaMap.put("540269-7609", false);
        kennitalaMap.put("1405433229", true);
        kennitalaMap.put("320888-3209", false);
        kennitalaMap.put("108883209", false);
        kennitalaMap.put("14054332010", false);
        kennitalaMap.put("1405433329", false);

        assertEquals("Kennitala was invalid but was reported to be valid", false, KennitalaValidator.isValid(null));

        for (Map.Entry<String, Boolean> booleanEntry : kennitalaMap.entrySet()) {
            assertEquals(
                    "Kennitala was valid or invalid but was reported to be the opposite.",
                    KennitalaValidator.isValid(booleanEntry.getKey()),
                    booleanEntry.getValue());
        }
    }

    @Test
    public void testIsCompany() throws Exception {
        final Map<String, Boolean> kennitalaMap = new HashMap<String, Boolean>();
        kennitalaMap.put("5810080150", true);
        kennitalaMap.put("1405433229", false);

        for (Map.Entry<String, Boolean> booleanEntry : kennitalaMap.entrySet()) {
            assertEquals(
                    "Kennitala was a company or wasn't but was reported to be the opposite.",
                    KennitalaValidator.isCompany(booleanEntry.getKey()),
                    booleanEntry.getValue());
        }
    }
}
