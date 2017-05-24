package com.test.commons

import java.io._
import au.com.bytecode.opencsv._
import scala.collection.JavaConversions._
import scala.util.Random
import java.sql.Timestamp
import java.util.Date

object Commons {


    var out: BufferedWriter = _;
    var writer: CSVWriter = _;

    def generateUserCSV(num: Int) {
        println("Generando feeder");
        out = new BufferedWriter(new FileWriter("/home/natgomgar/projects/bitbloq-qa-loadtest/simulations/com/data/users.csv"));
        writer = new CSVWriter(out);
        var user = Array("email","pass","username");
        writer.writeNext(user)
        var a = 1
        for (a<-1 to num) {
            var email = "testload"+Random.alphanumeric.take(4).mkString+System.currentTimeMillis()+"@loadtest.es"
            var pass = Random.alphanumeric.take(6).mkString
            var username = "testload"+Random.alphanumeric.take(4).mkString+System.currentTimeMillis()
            user = Array(email,pass,username)
            writer.writeNext(user);
            Thread.sleep(100);
        }
        out.close();
    }

    def generateSameCodeCSV(num: Int) {
        println("Generando feeder");
        out = new BufferedWriter(new FileWriter("/home/natgomgar/projects/bitbloq-qa-loadtest/simulations/com/data/code.csv"));
        writer = new CSVWriter(out);
        var value = Array("board","code");
        writer.writeNext(value)
        var a = 1
        for (a<-1 to num) {
          var boards = Array("uno","bt328")
          var code = """/***   Included libraries  ***/\n#include <SoftwareSerial.h>\n#include <BitbloqSoftwareSerial.h>\n\n\n/***   Global variables and function definition  ***/\nbqSoftwareSerial puerto_serie_0(0, 1, 9600);\n\n/***   Setup  ***/\nvoid setup() {}\n\n/***   Loop  ***/\nvoid loop() {\npuerto_serie_0.println(puerto_serie_0.readString());\ndelay(2000);\n}"""
            value = Array(boards(Random.nextInt(boards.length)),code)
            writer.writeNext(value);
        }
        out.close();
    }

    def generateMixCodeCSV(num: Int) {
        println("Generando feeder");
        out = new BufferedWriter(new FileWriter("/home/natgomgar/projects/bitbloq-qa-loadtest/simulations/com/data/code.csv"));
        writer = new CSVWriter(out);
        var value = Array("board","code");
        writer.writeNext(value)
        var a = 1
        for (a<-1 to num) {
          var boards = Array("uno","bt328")
          var code = Array ("""/***   Included libraries  ***/\n#include <SoftwareSerial.h>\n#include <BitbloqSoftwareSerial.h>\n\n\n/***   Global variables and function definition  ***/\nbqSoftwareSerial puerto_serie_0(0, 1, 9600);\n\n/***   Setup  ***/\nvoid setup() {}\n\n/***   Loop  ***/\nvoid loop() {\npuerto_serie_0.println(puerto_serie_0.readString());\ndelay(2000);\n}""",
                            """/***   Included libraries  ***/\n#include <SoftwareSerial.h>\n#include <BitbloqSoftwareSerial.h>\n\n\n/***   Global variables and function definition  ***/\nbqSoftwareSerial puerto_serie_0(0, 1, 9600);\n\n/***   Setup  ***/\nvoid setup() {}\n\n/***   Loop  ***/\nvoid loop() {\npuerto_serie_0.println(puerto_serie_0.readString());\ndelay("""+Random.nextInt(9999)+""");\n}""")
            value = Array(boards(Random.nextInt(boards.length)),code(Random.nextInt(code.length)));
            writer.writeNext(value);
            Thread.sleep(100);
        }
        out.close();
    }

    def generateDifferentCodeCSV(num: Int) {
        println("Generando feeder");
        out = new BufferedWriter(new FileWriter("/home/natgomgar/projects/bitbloq-qa-loadtest/simulations/com/data/code.csv"));
        writer = new CSVWriter(out);
        var value = Array("board","code");
        writer.writeNext(value)
        var a = 1
        for (a<-1 to num) {
          var boards = Array("uno","bt328")
          var code = """/***   Included libraries  ***/\n#include <SoftwareSerial.h>\n#include <BitbloqSoftwareSerial.h>\n\n\n/***   Global variables and function definition  ***/\nbqSoftwareSerial puerto_serie_0(0, 1, 9600);\n\n/***   Setup  ***/\nvoid setup() {}\n\n/***   Loop  ***/\nvoid loop() {\npuerto_serie_0.println(puerto_serie_0.readString());\ndelay("""+Random.nextInt(99999)+""");\n}"""
            value = Array(boards(Random.nextInt(boards.length)),code);
            writer.writeNext(value);
            Thread.sleep(100);
        }
        out.close();
    }
}
