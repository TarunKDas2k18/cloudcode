package simulations
import baseConfig.BaseSimulation

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.concurrent.duration.DurationInt

class TiamatOnlyTiamat extends BaseSimulation {


	val headers_0 = Map("User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")

	val headers_2 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"cache-control" -> "max-age=0",
		"if-modified-since" -> "Mon, 22 Oct 2018 17:17:41 GMT",
		"if-none-match" -> "a1a3dede25c411096c04ea81f231c875:1540228727.334962",
		"upgrade-insecure-requests" -> "1",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")

    val uri1 = "https://fonts.gstatic.com/s/fondamento/v8/4UaHrEJGsxNmFTPDnkaJ96rp5w.woff2"
    val uri3 = "https://www.tiamat.cloud"


  def tiamatOnly() :ChainBuilder ={
    repeat(times = 3){

    exec(http("TiamatMainUrl")
      .get(uri3 + "/")

      .headers(headers_2)
      .check(status.in(200,304))
    )

  }
  }



//  val scn = scenario("Tiamat3rdpartyItemsnotincluded")
//    .exec(tiamatOnly())
//    .pause(5)
//
//  	setUp(
//  		scn.inject(
//  			nothingFor(5 seconds), // do nothing for 5 seconds
//  			rampUsers(500) over (1800 seconds) // inject 500 users over a period of 30 minutes or 1800 seconds
//  		).protocols(httpConf.inferHtmlResources()) // inferHtmlResources will fetch everything on the page (JS, CSS, images etc.)
//  	)


  val scn = scenario("Tiamat3rdpartyItemsnotincluded")
      .forever() {
      exec(tiamatOnly())
        .pause(5)
            }


  // Run for a fixed duration ie 15 mins below and not completing user journey
  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(10),
      rampUsers(500) over (900 second)
    ).protocols(httpConf.inferHtmlResources()))
    .maxDuration(15 minute)





}