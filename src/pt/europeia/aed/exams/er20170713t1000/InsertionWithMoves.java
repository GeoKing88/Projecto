package pt.europeia.aed.exams.er20170713t1000;

public final class InsertionWithMoves {

    private InsertionWithMoves() {
        throw new RuntimeException("Attempt to instantiate package-class");
    }

    public static void sort(final double[] values) {
        for (int numberOfSortedItems = 1; numberOfSortedItems < values.length;
             numberOfSortedItems++) {
            final double valueToInsert = values[numberOfSortedItems];
            int i = numberOfSortedItems;
            while (i != 0 && valueToInsert < values[i - 1]) {
                values[i] = values[i - 1];
                i--;
            }
            values[i] = valueToInsert;
        }
    }

}

/*
 * Copyright 2015-2017, Robert Sedgewick and Kevin Wayne.
 * 
 * Copyright 2015-2017, Manuel Menezes de Sequeira.
 * 
 * This file is a derivative work of the code which accompanies the textbook
 * 
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 * Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 * 
 * This code is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this code. If not, see http://www.gnu.org/licenses.
 * 
 * Any errors found in this code should be assumed to be the responsibility of
 * the author of the modifications to the original code (viz. Manuel Menezes de
 * Sequeira).
 */