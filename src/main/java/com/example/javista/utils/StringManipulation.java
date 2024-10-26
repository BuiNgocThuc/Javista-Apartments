package com.example.javista.utils;

import com.example.javista.filter.FilteringAndSearchingOperator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringManipulation {
        //define replace method
        public static String replace(String str, char oldChar, char newChar) {
                return str.replace(oldChar, newChar);
        }

        // define substring method
        public static String substring(String str, int start, int end) {
                return str.substring(start, end);
        }

        public static String definingOperatorInParameters(String str,  char endChar) {
                // define index of start and end
                int endIndex = str.indexOf(endChar);
                if (endIndex == -1) {
                        return FilteringAndSearchingOperator.CONTAINS.toString();
                }

                // call substring method
                return substring(str, 0, endIndex);
        }

        public static String definingValueInParameters(String str, char startChar) {
                // define index of start and end
                int startIndex = str.indexOf(startChar);
                // if start index is -1, that will be searching function
                if (startIndex == -1) {
                        return replace(str, '-', ' ');
                }
                // call substring method
                return substring(str, startIndex + 1, str.length());
        }
}

