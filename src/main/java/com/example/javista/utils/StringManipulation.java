package com.example.javista.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class StringManipulation {
        // define substring method
        public static String substring(String str, int start, int end) {
                return str.substring(start, end);
        }

        public static String definingOperatorInParameters(String str,  char endChar) {
                // define index of start and end
                int endIndex = str.indexOf(endChar);
                if (endIndex == -1) {
                        return null;
                }

                // call substring method
                return substring(str, 0, endIndex);
        }

        public static String definingValueInParameters(String str, char startChar) {
                // define index of start and end
                int startIndex = str.indexOf(startChar);
                if (startIndex == -1) {
                        return null;
                }
                // call substring method
                return substring(str, startIndex + 1, str.length());
        }

//        public static void main(String[] args) {
//                StringManipulation stringManipulation = new StringManipulation();
//                // define string
//                String str = "lt:50.5";
//                // define start and end
//                char milestone = ':';
//                // call substring method
//                String result = StringManipulation.definingOperatorInParameters(str, milestone);
//                String result2 = StringManipulation.definingValueInParameters(str, milestone);
//                // print result
//                System.out.println(result);
//                System.out.println(result2);
//        }
}

