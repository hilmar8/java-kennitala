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
package net.hilmarh.kennitala;

import net.hilmarh.kennitala.validator.KennitalaValidator;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Tests for Kennitala.
 *
 * @author Hilmar Ævar Hilmarsson (hilmarh@gmail.com)
 * @version 1.0
 * @since 1.0
 */
public class KennitalaUtilTest {
    @Test
    public void testCleanKennitala() throws Exception {
        String kennitala;

        kennitala = "140543-3229";
        assertEquals("The hyphen was not removed from the kennitala", "1405433229", KennitalaUtil
                .cleanKennitala(kennitala));

        kennitala = KennitalaUtil.random();
        assertTrue("The random kennitala was not ten letters", KennitalaUtil
                .cleanKennitala(kennitala).matches("[0-9]{10}"));

        kennitala = KennitalaUtil.fromBirthday(19, 8, 1988);
        assertTrue("The kennitala from age was not ten letters", KennitalaUtil
                .cleanKennitala(kennitala).matches("[0-9]{10}"));
    }

    @Test
    public void testKennitala11() throws Exception {
        String kennitala;

        kennitala = "1405433229";
        assertEquals("The hyphen was not added to the kennitala", "140543-3229", KennitalaUtil
                .kennitala11(kennitala));

        kennitala = "140543-3229";
        assertEquals("The hyphen was not added to the kennitala", "140543-3229", KennitalaUtil
                .kennitala11(kennitala));

        kennitala = KennitalaUtil.random();
        assertTrue("The random kennitala had no hyphen", KennitalaUtil
                .kennitala11(kennitala).matches("[0-9]{6}-[0-9]{4}"));

        kennitala = KennitalaUtil.fromBirthday(14, 5, 1943);
        assertTrue("The kennitala from age had no hyphen", KennitalaUtil
                .kennitala11(kennitala).matches("[0-9]{6}-[0-9]{4}"));
    }

    @Test
    public void testGetAge() throws Exception {
        String kennitala;

        Calendar calendar = new GregorianCalendar();

        kennitala = KennitalaUtil.fromBirthday(calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR) - 25);

        assertEquals("Unexpected age", 25, KennitalaUtil.age(kennitala));

        kennitala = KennitalaUtil.fromBirthday(calendar.get(Calendar.DAY_OF_MONTH) - 1,
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR) - 25);

        assertEquals("Unexpected age", 25, KennitalaUtil.age(kennitala));

        kennitala = KennitalaUtil.fromBirthday(calendar.get(Calendar.DAY_OF_MONTH) + 1,
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR) - 25);


        assertEquals("Unexpected age", 24, KennitalaUtil.age(kennitala));
    }

    @Test
    public void testRandom() throws Exception {
        String kennitala;
        for (int i = 0; i != 10000; i++) {
            kennitala = KennitalaUtil.random();

            assertTrue(KennitalaValidator.isValid(kennitala));
            assertTrue(KennitalaValidator.isValid(KennitalaUtil.cleanKennitala(kennitala)));
            assertTrue(KennitalaValidator.isValid(KennitalaUtil.kennitala11(kennitala)));
        }
    }
}
