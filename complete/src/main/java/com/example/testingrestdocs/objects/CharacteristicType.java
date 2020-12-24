package com.example.testingrestdocs.objects;

public enum CharacteristicType {
    STRING {
        @Override
        public String toString() {
            return "STRING";
        }
    },
    INTEGER {
        @Override
        public String toString() {
            return "INTEGER";
        }
    },
    DOUBLE {
        @Override
        public String toString() {
            return "DOUBLE";
        }
    },
    CATALOG {
        @Override
        public String toString() {
            return "CATALOG";
        }
    }
}
