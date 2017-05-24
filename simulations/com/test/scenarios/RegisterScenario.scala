package com.test.scenarios

import io.gatling.core.structure._
import io.gatling.http.request._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.test.request._
import scala.util.Random
import java.sql.Timestamp
import java.util.Date

object RegisterScenario {

    val baseAPIQa = "http://api.bitbloq.k8s-dev.bq.com/"
    val baseURLQa = "http://front.bitbloq.k8s-dev.bq.com/"
    val baseAPIProd = "https://api-beta-bitbloq.bq.com/"
    val baseURLProd = "http://beta-bitbloq.bq.com/"

    val request = new RequestBitbloq(baseAPIProd,baseURLProd,"")
    val feeder = csv("/home/natgomgar/projects/bitbloq-qa-loadtest/simulations/com/data/users.csv")
    // val feeder = Iterator.continually(Map("email"-> ("testload"+Random.alphanumeric.take(4).mkString+System.currentTimeMillis()+"@loadtest.es"),
    // "pass" -> (Random.alphanumeric.take(6).mkString),
    // "username" -> ("testload"+Random.alphanumeric.take(4).mkString+System.currentTimeMillis())))

    val registerUserAndCreateProject = scenario("Register X users and create X project")
        .feed(feeder)
        .exec(request.frontRequest.gotoLanding)
        .exec(request.frontRequest.gotoRegisterForm())
        .exec(request.backRequest.registerAnUser("${username}","${pass}","${email}"))
        .exec(request.frontRequest.createProject())
        .exec(request.backRequest.getIdUser())
        .exec(request.backRequest.saveAProject())


}
