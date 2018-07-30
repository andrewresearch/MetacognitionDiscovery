package io.nlytx

import com.thoughtworks.binding.Binding.Vars
import com.thoughtworks.binding.dom

import scala.scalajs.js

import org.scalajs.dom.ext._
import org.scalajs.dom.raw._
import org.scalajs.dom._

//import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.Binding.Var
import fr.hmil.roshttp.body.Implicits._
import fr.hmil.roshttp.body.JSONBody._
import fr.hmil.roshttp.HttpRequest
import fr.hmil.roshttp.body.URLEncodedBody
import fr.hmil.roshttp.response.SimpleHttpResponse
import io.nlytx.DataStore._
//import io.nlytx.expressions.data.{Coded, Reflect, Summary}
import monix.execution.Scheduler.Implicits.global
import ujson.Js

import scala.util.{Failure, Success}
import upickle.default._

object DataService {

  def reset = {
    DataStore.analysed.value = false
  }

  def toggleDetails = {
    DataStore.showDetails.value = !DataStore.showDetails.value
  }

  def toggleData = {
    DataStore.showData.value = !DataStore.showData.value
  }


  def getText(refType:String) :Unit = {
    if (refType.nonEmpty) {
      val url = s"${MetaCogUI.baseUrl}/assets/data/${refType}.json"
      println(s"Getting text from $url")
      val request = HttpRequest(url)
      val response = request.send()
      println("Got response, waiting for it to complete...")
      response.onComplete {
        case result: Success[SimpleHttpResponse] => {
          val data = ujson.read(result.value.body)
          println(s"Selected: ${data("source")}")
          reflectionData.text.value = data("text").str
          reflectionData.source.value = data("source").str
          reflectionData.url.value = data("url").str
        }
        case error: Failure[SimpleHttpResponse] => println(s"ERROR: $error")
      }
    } else {
      reflectionData.text.value = ""
      reflectionData.source.value = ""
      reflectionData.url.value = ""
    }
  }

  def analyseText(): Unit = {
    if(reflectionData.text.value.isEmpty) {
      println("ERROR: No data")
    } else {
      val url = s"http://tap.hi2lab.io/graphql"
      println(s"Posting text to tap at $url with header")
      val request = HttpRequest(url)
      val body = graphqlQuery(reflectionData.text.value)
      //println(s"request body: $body")
      val response = request.post(body)
      println("Got response, waiting for it to complete...")
      response.onComplete{
        case result:Success[SimpleHttpResponse] => {
          //println("RESULT: "+result.value.body.toString)
          val json = ujson.read(result.value.body)
          val queryData = json("data")("reflectExpressions")
          val timestamp = queryData("timestamp")
          val queryTime = queryData("querytime")
          val message = queryData("message")
          println(s"reflectExpressions query @ $timestamp completed in $queryTime ms with message: $message")
          DataStore.queryData.value = DataStore.QueryStats(timestamp.toString(),queryTime.toString(),message.toString())
          val analytics = queryData("analytics")
          handleAnalytics(analytics)
          analysed.value = true
        }
        case error: Failure[SimpleHttpResponse] => {
          println(s"ERROR: $error")
          analysed.value = false
        }
      }
    }
  }

  def handleAnalytics(analytics:Js.Value): Unit = {
    //println(s"Result: ${analytics}")
    val counts = analytics("counts")
    //println(s"COUNTS: $counts")
    countsData.value = read[Reflect](counts)
    val summary = analytics("summary")
    //println(s"SUMMARY: $summary")
    summaryData.value = read[Summary](summary)
    val tags = analytics("tags")
    //println(s"TAGS: $tags")
    val coded = read[List[Coded]](tags)
    tagsData.value ++= coded.zipWithIndex.map{ case(c:Coded,i:Int) => Coded(c.sentence,c.phrases,c.subTags,c.metaTags,i+1) }
  }


  private def graphqlQuery(text:String) = {

    val clean: String = """query Clean($input: String,$parameters:String) { clean(text:$input,parameters:$parameters) { timestamp message querytime analytics }}"""

    val query: String =
      """query ReflectExpressions($input: String,$parameters:String) {
        |  reflectExpressions(text:$input,parameters:$parameters) {
        |    timestamp
        |    message
        |    querytime
        |    analytics {
        |      counts {
        |        wordCount
        |        avgWordLength
        |        sentenceCount
        |        avgSentenceLength
        |      }
        |      summary {
        |        metaTagSummary {
        |          knowledge
        |          experience
        |          regulation
        |          none
        |        }
        |        phraseTagSummary {
        |          outcome
        |          temporal
        |          pertains
        |          consider
        |          anticipate
        |          definite
        |          possible
        |          selfReflexive
        |          emotive
        |          selfPossessive
        |          compare
        |          manner
        |          none
        |        }
        |      }
        |      tags {
        |        sentence
        |        phrases
        |        subTags
        |        metaTags
        |      }
        |    }
        |  }
        |}""".stripMargin

    val variables = JSONObject(
      "input" -> text,
      "parameters" -> ""
    )

    JSONObject(
      "query" -> query,
      "variables" -> variables
    )
  }


}
