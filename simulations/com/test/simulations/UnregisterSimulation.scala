
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Landing extends Simulation {

    object Landing {
      val landing = exec(http("Home")
        .get("/"))
        .pause(2)
    }

    object Features {
      val features = exec(http("Features")
        .get("/#/features"))
        .pause(2)
    }

    object Download {
      val download = exec(http("Download")
        .get("/#/downloads"))
        .pause(2)
    }

    object Help {
      val help = exec(http("Help")
        .get("/#/bitbloq-help"))
        .pause(2)
    }

    val httpProtocol = http
        .baseURL("http://front.bitbloq.k8s-dev.bq.com")
        .acceptHeader("image/png,image/*;q=0.8,*/*;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.116 Safari/537.36")

        val scn = scenario("Navigation Landing")
        .exec(Landing.landing,Features.features,Download.download,Help.help)


        setUp(
        //scn.inject(rampUsers(1000) over (60 seconds))
        scn.inject(atOnceUsers(1))
      ).protocols(httpProtocol)
}
