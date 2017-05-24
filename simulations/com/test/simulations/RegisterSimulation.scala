package com.test.simulations

import com.test.scenarios._
import com.test.commons._
import io.gatling.core.structure._
import io.gatling.http.request._
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class RegisterSimulation extends Simulation {
    val nusers=2000
    //setUp(RegisterScenario.registerUserAndCreateProject.inject(rampUsers(10)over(600)))
    Commons.generateUserCSV(nusers)
    setUp(RegisterScenario.registerUserAndCreateProject.inject(atOnceUsers(nusers)))
}
