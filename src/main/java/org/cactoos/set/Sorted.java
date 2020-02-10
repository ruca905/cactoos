/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2020 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Unchecked;

/**
 * Sorted Iterable as {@link Set}.
 *
 * <p>This class should be used very carefully. You must understand that
 * it will fetch the entire content of the encapsulated {@link Set} on each
 * method call. It doesn't cache the data anyhow. </p>
 *
 * <p>There is no thread-safety guarantee.
 *
 * @param <T> Set type
 * @since 1.0.0
 */
public final class Sorted<T> extends SetEnvelope<T> {

    /**
     * Ctor.
     * @param cmp Comparator
     * @param array An array of some elements
     */
    @SafeVarargs
    public Sorted(final Comparator<T> cmp, final T... array) {
        this(cmp, new IterableOf<>(array));
    }

    /**
     * Ctor.
     * @param cmp Comparator
     * @param src An {@link Iterable}
     */
    public Sorted(final Comparator<T> cmp, final Iterable<T> src) {
        super(
            new Unchecked<>(
                () -> {
                    final Set<T> set = new TreeSet<>(cmp);
                    src.forEach(set::add);
                    return set;
                }
            ).value()
        );
    }
}
