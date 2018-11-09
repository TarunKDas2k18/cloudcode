
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Tiamat3rdpartynotincluded extends Simulation {

	val httpProtocol = http
		.baseUrl("https://fonts.googleapis.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())

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

	val scn = scenario("Tiamat3rdpartynotincluded")
		.exec(http("request_0")
			.get("/css?family=Fondamento")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/icon?family=Material+Icons")
			.headers(headers_0),
            http("request_2")
			.get(uri3 + "/")
			.headers(headers_2)
			.check(status.is(304)),
            http("request_3")
			.get(uri1 + "")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}