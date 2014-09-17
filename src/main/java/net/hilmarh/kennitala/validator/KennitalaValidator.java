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

import net.hilmarh.kennitala.KennitalaUtil;

/**
 * <p>Perform validation on an Icelandic kennitala (social security number).</p>
 *
 * @author Hilmar Ævar Hilmarsson (hilmarh@gmail.com)
 * @version 1.0
 * @since 1.0
 */
public final class KennitalaValidator {

    /**
     * This class cannot be constructed.
     */
    private KennitalaValidator() {
    }

    /**
     * Check whether a kennitala is valid using number derivation. (http://en.wikipedia.org/wiki/Kennitala).
     *
     * The check will check whether the ninth number of the kennitala matches the following formula.
     *
     * y = (x1 * 3) + (x2 * 2) + (x3 * 7) + (x4 * 6) + (x5 * 5) + (x6 * 4) + (x7 * 3) + (x8 * 2)
     * j = y % 11
     *
     * Where x is the kennitala and j should match the ninth number of the kennitala.
     *
     * @param kennitala A kennitala with or without the hyphen.
     * @return True if the kennitala passes the ninth digit check, else false.
     */
    public static boolean isValid(final String kennitala) {
        if (kennitala == null) {
            return false;
        }

        final String kennitalaClean = KennitalaUtil.cleanKennitala(kennitala);

        if (kennitalaClean.length() != 10) {
            return false;
        }

        int sum =
                (Integer.parseInt(kennitalaClean.substring(0, 1)) * 3) +
                        (Integer.parseInt(kennitalaClean.substring(1, 2)) * 2) +
                        (Integer.parseInt(kennitalaClean.substring(2, 3)) * 7) +
                        (Integer.parseInt(kennitalaClean.substring(3, 4)) * 6) +
                        (Integer.parseInt(kennitalaClean.substring(4, 5)) * 5) +
                        (Integer.parseInt(kennitalaClean.substring(5, 6)) * 4) +
                        (Integer.parseInt(kennitalaClean.substring(6, 7)) * 3) +
                        (Integer.parseInt(kennitalaClean.substring(7, 8)) * 2);

        int num = 11 - (sum % 11);
        num = (num == 11) ? 0 : num;

        return num == Integer.parseInt(kennitalaClean.substring(8, 9));
    }
}
