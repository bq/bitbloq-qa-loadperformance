package com.test.request

import io.gatling.core.structure._
import io.gatling.http.request._
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class RequestBitbloq (urlBack: String, urlFront: String, urlCompiler: String) {

    val api = urlBack
    val url = urlFront
    val compiler = urlCompiler
    val headers = Map ("Accept" -> "application/json", "Content-Type" -> "application/json")

    object backRequest {

        def registerAnUser(username:String, pass:String, email:String): ChainBuilder = {
            exec(
                http("Register an User")
                .post(api+"bitbloq/v1/user")
                .headers(headers)
                .body(StringBody("""{ "username": """"+username+"""", "password": """"+pass+
                  """", "birthday":"1988-11-10T23:00:00.000Z","cookiePolicyAccepted":true,"email": """"+email+
                  """", "firstName":"","hasBeenAskedIfTeacher":true,"language":"es-ES","lastName":"","newsletter":false,"takeTour":false }""")).asJSON
                .check(status.is(200),jsonPath("$.token").find.saveAs("token"))
            ).pause(4,6)
        }

        def saveAProject():ChainBuilder = {
            exec(
                http("Save a Project")
                .post(api+"bitbloq/v1/project")
                .header("Authorization","Bearer ${token}")
                .headers(headers)
                .body(StringBody("""{"creator":"${id}","name":"pruebaTEstLoad"""+System.currentTimeMillis()+"""","description":"descriptionLoadTest","userTags":[],"hardwareTags":[],"videoUrl":"","defaultTheme":"infotab_option_colorTheme","software":{"vars":{"name":"varsBloq","content":[[]],"enable":true,"childs":[]},"setup":{"name":"setupBloq","content":[[]],"enable":true,"childs":[]},"loop":{"name":"loopBloq","content":[[]],"enable":true,"childs":[]}},"hardware":{"components":[],"connections":[],"board":null,"robot":null},"code":"\n/***   Included libraries  ***/\n\n\n/***   Global variables and function definition  ***/\n\n\n/***   Setup  ***/\nvoid setup(){}\n\n/***   Loop  ***/\nvoid loop(){}"}""")).asJSON
                .check(status.is(200))
            ).pause(3,5)
        }

        def getIdUser():ChainBuilder = {
            exec(
                http("GEt id of user")
                .get(api+"bitbloq/v1/user/me")
                .header("Authorization","Bearer ${token}")
                .headers(headers)
                .check(status.is(200),jsonPath("$._id").find.saveAs("id"))
            ).pause(3,5)
        }

        def login(user:String,  pass:String):ChainBuilder = {
            exec(
                http("Save a Project")
                .post(api+"bitbloq/v1/auth/local")
                .disableFollowRedirect
                .headers(headers)
                .body(StringBody("""{"email":""""+user+"""", "password":""""+pass+""""}""")).asJSON
                .check(status.is(200),jsonPath("$.token").find.saveAs("token"))
            ).pause(3,5)
        }

    }

    object frontRequest {

        def gotoLanding(): ChainBuilder = {
            exec(
              http("Go to Landing")
              .get(url)
            ).pause(2)
        }

        def gotoRegisterForm(): ChainBuilder = {
            exec(
              http("Go to Register page")
              .get(url+"#/register")
            ).pause(2)
        }

        def gotoLogin(): ChainBuilder = {
            exec(
              http("Go to Login page")
              .get(url+"#/login")
            ).pause(2)
        }

        def createProject(): ChainBuilder = {
            exec(
              http("Create a project")
              .get(url+"#/bloqsproject")
            ).pause(2)
        }
    }

    object compilerRequest {
        def compile(board:String, code:String): ChainBuilder = {
            exec(
                http("Compile a Code")
                .post(compiler+"compile")
                .headers(headers)
                .body(StringBody("""{"board":""""+board+"""","code":""""+code+""""}""")).asJSON
                .check(status.is(200))
            ).pause(3,7)
        }
    }

}
