package me.techaddict

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import me.techaddict.storage.StorageActor
import me.techaddict.messages._
import scala.concurrent.duration._

object Main extends App {
	import scala.concurrent.ExecutionContext.Implicits.global

	val system = ActorSystem("CoralFS")
	val storageActor = system.actorOf(Props[StorageActor], "StorageActor")

	system.scheduler.schedule(1000 milliseconds, 1000 milliseconds, storageActor, StoreRequest("data", (util.Random.nextLong.toString * 10000) + "\n"))
}
