package me.techaddict.storage

import akka.actor.{Actor, ActorLogging, Props}
import akka.event.Logging
import java.io.{RandomAccessFile, FileOutputStream}
import me.techaddict.messages._

class StorageChildActor extends Actor with ActorLogging {
	val fileName = util.Random.nextLong.abs.toString
	val file = new RandomAccessFile("data/" + fileName, "rw")

	def receive = {
		case StoreRequest(name, data) =>
			try {
				val offset = file.getFilePointer
				val bytesToWrite = data.getBytes
				file.write(bytesToWrite)
				sender() ! Stored(StoreRequest(name, data), FileInfo(fileName, fileName + name.hashCode, offset, bytesToWrite.size))
			} catch {
				case e: Throwable => log.info(s"Writing to file=> $fileName Failed")
			}
	}
}
