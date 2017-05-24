package com.test.scenarios

import io.gatling.core.structure._
import io.gatling.http.request._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.test.request._
import scala.util.Random
import java.sql.Timestamp
import java.util.Date

object CompilerScenario {

    val compilerUrl = "http://compiler.bitbloq.k8s-dev.bq.com/"
    val compilerUrlPROD = "http://compiler.bitbloq.k8s.bq.com/"

    val request = new RequestBitbloq("","",compilerUrl)
    val feeder = csv("/home/natgomgar/projects/bitbloq-qa-loadtest/simulations/com/data/code.csv")

    val compileSameCode = scenario("Compile a code X times")
        .feed(feeder)
        .exec(request.compilerRequest.compile("${board}","${code}"))

}
