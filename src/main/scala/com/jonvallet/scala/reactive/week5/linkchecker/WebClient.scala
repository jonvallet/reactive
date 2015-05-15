package com.jonvallet.scala.reactive.week5.linkchecker

import java.util.concurrent.Executor

import com.ning.http.client.AsyncHttpClient
import org.jsoup.Jsoup

import scala.concurrent.{Promise, Future}
import scala.collection.JavaConverters._

/**
 * @author Jon Vallet
 */
object WebClient {

  val client = new AsyncHttpClient

  def get(url: String)(implicit exec: Executor): Future[String] = {
    val f = client.prepareGet(url).execute()
    val p = Promise[String]()
    f.addListener(new Runnable {
      override def run(): Unit = {
        val response = f.get
        if (response.getStatusCode < 400)
          p.success(response.getResponseBodyExcerpt(131072))
        else
          p.failure(BadStatus(response.getStatusCode))
      }
    }, exec)
    p.future
  }

  def findLinks(body: String): Iterator[String] = {
    val document = Jsoup.parse(body)
    val links = document.select("a[href]")
    for {
      link <- links.iterator().asScala
    } yield link.absUrl("href")
  }

  def shutdown(): Unit = client.close()

}

class BadStatus(status: Int) extends RuntimeException

object BadStatus {
  def apply(status: Int) = new BadStatus(status)
}


