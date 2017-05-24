package com.test.simulations

import com.test.scenarios._
import io.gatling.core.structure._
import io.gatling.http.request._
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class LoginSimulation extends Simulation {

    //setUp(RegisterScenario.loginUserAndCreateProject.inject(rampUsers(10)over(600)))

    setUp(LoginScenario.loginUserAndCreateProject.inject(atOnceUsers(2000)))
}
