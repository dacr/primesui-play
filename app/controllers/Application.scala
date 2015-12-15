package controllers

import play.api._
import play.api.mvc._
import fr.janalyse.primesui._


case class PrimesUIContext(
  homeUrl: String,
  checkUrl: String,
  factorsUrl: String,
  primeUrl: String,
  primesUrl: String,
  populateUrl: String,
  ulamUrl: String,
  issuecheckUrl:String,
  highcpucheckUrl:String,
  overcheckUrl:String,
  slowcheckUrl: String,
  slowsqlcheckUrl: String,
  leakedcheckUrl: String,
  sessionleakedcheckUrl: String,
  jdbcleakcheckUrl: String,
  toomanylogscheckUrl:String,
  badlogscheckUrl:String,
  goodlogscheckUrl:String,
  bigUrl: String,
  aliveUrl: String,
  sysinfoUrl: String,
  configUrl: String
  )


class Application extends Controller {

  val rnd = scala.util.Random
  def nextInt = synchronized { rnd.nextInt(10000) }
  
  
  def url(in:String)=in
  
  lazy val ctx = PrimesUIContext(
    homeUrl = "",
    checkUrl = url("/check"),
    factorsUrl = url("/factors"),
    primeUrl = url("/prime"),
    primesUrl = url("/primes"),
    populateUrl = url("/populate"),
    ulamUrl = url("/ulam"),
    issuecheckUrl = url("/issuecheck"),
    highcpucheckUrl = url("/highcpucheck"),
    overcheckUrl = url("/overcheck"),
    slowcheckUrl = url("/slowcheck"),
    slowsqlcheckUrl = url("/slowsqlcheck"),
    leakedcheckUrl = url("/leakedcheck"),
    sessionleakedcheckUrl = url("/sessionleakedcheck"),
    jdbcleakcheckUrl = url("/jdbcleakcheck"),
    toomanylogscheckUrl = url("/toomanylogscheck"),
    badlogscheckUrl = url("/badlogscheck"),
    goodlogscheckUrl = url("/goodlogscheck"),
    bigUrl = url("/big"),
    aliveUrl = url("/alive"),
    sysinfoUrl = url("/sysinfo"),
    configUrl = url("/config")
    )
  
  def engine = PrimesEngine.engine
  
  def index = Action { request =>
    if (engine.useSession) {
      val newcount = request.session.get("count").map(_.toInt).getOrElse(1)
      Ok(views.html.index(ctx, engine, Some(newcount), "0.0.0")).withSession( "count"-> newcount.toString)
    } else {
      Ok(views.html.index(ctx, engine, None, "0.0.0"))
    }
  }
  
  
  
  def check(num:Long) = Action { request =>
    val value = engine.check(num)
    Ok(views.html.checkResult(ctx, num, value, None, None))
  }
  
  def checkRandom = check(nextInt)
  

}
