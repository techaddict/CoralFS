package me.techaddict.messages

case class FileInfo(partition: String, key: String, offset: Long, size: Int)

case class Stored(request: StoreRequest, info: FileInfo)
