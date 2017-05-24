package com.test.simulations

import com.test.scenarios._
import com.test.commons._
import io.gatling.core.structure._
import io.gatling.http.request._
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class CompilerSimulation extends Simulation {

    //.inject(rampUsers(10)over(600)))
    val nusers=100
    Commons.generateDifferentCodeCSV(nusers)
    setUp(CompilerScenario.compileSameCode.inject(atOnceUsers(nusers)))
}
