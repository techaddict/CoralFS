package me.techaddict

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.io.IO
import me.techaddict.client.ClientActor
import me.techaddict.storage.StorageActor
import me.techaddict.messages._
import scala.concurrent.duration._
import spray.can.Http

object Main extends App {
	import scala.concurrent.ExecutionContext.Implicits.global

	implicit val system = ActorSystem("CoralFS")
	val storageActor = system.actorOf(Props[StorageActor], "StorageActor")

	val clientActor = system.actorOf(Props[ClientActor], "ClientActor")
	IO(Http) ! Http.Bind(clientActor, interface = "localhost", port = 5678)

	system.scheduler.scheduleOnce(1000 milliseconds, storageActor, StoreRequest("data", "1eoi7w83727397273927 \n"))
}
