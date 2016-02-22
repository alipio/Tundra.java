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

public final class ClassHelper {
    /**
     * Disallow instantiation of this class.
     */
    private ClassHelper() {}

    /**
     * Returns an array of Class objects associated with the class or interface with the given names.
     *
     * @param classNames A list of class or interface names.
     * @return A list of Class objects associated with the given names.
     * @throws ClassNotFoundException If a class with the given name cannot be found.
     */
    public static Class[] forName(String[] classNames) throws ClassNotFoundException {
        if (classNames == null) return null;

        Class[] classes = new Class[classNames.length];

        for (int i = 0; i < classNames.length; i++) {
            if (classNames[i] != null) {
                classes[i] = Class.forName(classNames[i]);
            }
        }

        return classes;
    }

    /**
     * Returns the array class associated with the given component class and number of dimensions.
     *
     * @param componentClass    The component class to use for the array class.
     * @param dimensions        The array dimensions to use.
     * @param <T>               The component class of the array.
     * @return                  The array class associated with the given component class and number of dimensions.
     */
    public static <T> Class getArrayClass(Class<T> componentClass, int dimensions) {
        if (dimensions < 1) throw new IllegalArgumentException("array dimensions must be >= 1");
        try {
            return Class.forName(StringHelper.repeat("[", dimensions) + "L" + componentClass.getName() + ";");
        } catch(ClassNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
