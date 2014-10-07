package me.techaddict.client

import akka.actor.{Actor, ActorLogging, Props}
import akka.event.Logging
import akka.io.IO
import akka.pattern.ask
import akka.routing.RoundRobinPool
import akka.util.Timeout
import me.techaddict.messages._
import scala.concurrent.duration._
import scala.concurrent.Future
import spray.can.Http
import spray.can.Http.RegisterChunkHandler
import spray.can.server.Stats
import spray.http._
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.util._

case object MSG

class ClientActor extends Actor with ActorLogging {

	implicit val timeout: Timeout = 1.second
	import context.dispatcher

	def receive = {
		case _: Http.Connected => sender ! Http.Register(self)
		case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
			sender() ! index(1)
    case HttpRequest(GET, Uri.Path("/ping"), _, _, _) =>
      sender() ! index(2)
	}

	def index(value: Int) = HttpResponse(
    entity = HttpEntity(`text/html`,
      <html>
        <body>
          <h1>CoralFS Web API</h1>
          <p>Defined resources:</p>
          <ul>
            <li><a href="/ping">/ping</a>{value}</li>
          </ul>
        </body>
      </html>.toString()
    )
  )
}
