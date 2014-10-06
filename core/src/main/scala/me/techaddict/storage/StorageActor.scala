package me.techaddict.storage

import akka.actor.{Actor, ActorLogging, Props}
import akka.event.Logging
import akka.routing.RoundRobinPool
import me.techaddict.messages._

class StorageActor extends Actor with ActorLogging {
	val router = context.actorOf(Props[StorageChildActor].withRouter(RoundRobinPool(nrOfInstances = 2)))

	def receive = {
		case msg: StoreRequest =>
			router ! msg
		case Stored(StoreRequest(name, _), FileInfo(partition, key, offset, size)) =>
			log.info(s"Request that came in for storing file name=> $name,  Stored at partition=> partition with key=> $key offset=> $offset size=> $size")
	}
}
