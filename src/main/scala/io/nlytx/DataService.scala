package io.nlytx

//import com.thoughtworks.binding.Binding.{Var, Vars}
import fr.hmil.roshttp.body.Implicits._
import fr.hmil.roshttp.body.JSONBody._
import fr.hmil.roshttp.HttpRequest
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


  def getText(refType:String) :Unit = {
    val url = s"http://localhost:63342/MetaCogUI/assets/data/${refType}.json"
    println(s"Getting text from $url")
    val request = HttpRequest(url)
    val response = request.send()
    println("Got response, waiting for it to complete...")
    response.onComplete{
      case result:Success[SimpleHttpResponse] => {
        val data = read[Reflection](result.value.body)
        println(s"Selected: ${data.source}")
        reflectionData.value =  data
      }
      case error: Failure[SimpleHttpResponse] => println(s"ERROR: $error")
    }

  }

  def analyseText(): Unit = {
    val url = s"http://tap.is-qut.nlytx.io/graphql"
    println(s"Posting text to tap at $url")
    val request = HttpRequest(url)
    val response = request.post(graphqlQuery(reflectionData.value.text))
    println("Got response, waiting for it to complete...")
    response.onComplete{
      case result:Success[SimpleHttpResponse] => {
        val json = ujson.read(result.value.body)
        val queryData = json("data")("reflectExpressions")
        val timestamp = queryData("timestamp")
        val queryTime = queryData("querytime")
        val message = queryData("message")
        println(s"reflectExpressions query @ $timestamp completed in $queryTime ms with message: $message")
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

  def handleAnalytics(analytics:Js.Value): Unit = {
    //println(s"Result: ${analytics}")
    val counts = analytics("counts")
    println(s"COUNTS: $counts")
    countsData.value = read[Reflect](counts)
    val summary = analytics("summary")
    println(s"SUMMARY: $summary")
    summaryData.value = read[Summary](summary)
    val tags = analytics("tags")
    println(s"TAGS: $tags")
    tagsData.value ++= read[List[Coded]](tags)
  }


  private def graphqlQuery(text:String) = {

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
  /*


    markupText(text) {
        if(text) {
            let phrases = text.phrases;
            //console.log("phrases: " + JSON.stringify(phrases));
            let markedUp = text.sentence;

            let escape = function(s) {
                return s.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&');
            };

            for (let phrase of phrases) {
                //console.log("phrase: " + JSON.stringify(phrase));
                let phraseParts = phrase.split('[');
                let phraseText = phraseParts[0];
                let phraseType = phraseParts[1].split(',')[0];
                markedUp = markedUp.replace(new RegExp(escape(phraseText), "ig"), "<span class=\"" + phraseType + "\">" + phraseText + "</span>");
                //console.log("markedUp: " + markedUp);
            }
            return markedUp;
        } else {
            return "";
        }
    }
   */

}
