package io.example.kafka.kafkaproducer;

public class Test {

    public static void main(String[] args) {
        System.out.println(m1());

    }

    public static String m1() {
        try {
            System.out.println("trying..");
            return "Trying..";
        } finally {
            System.out.println("finally..");
            //return "Finally..";
        }
    }
}
