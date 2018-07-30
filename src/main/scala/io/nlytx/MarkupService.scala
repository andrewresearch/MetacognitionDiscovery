package io.nlytx

import io.nlytx.DataStore.Coded

object MarkupService {

  def numFormat[T](num:T):String = num match {
    case n: Int => n.toString
    case n: Double => "%1.2f".format(n)
    case _ => throw new IllegalArgumentException
  }

  def markupIndex(codedSentence:Coded):String = {
    val index = codedSentence.index
    val metaCount = "meta"+codedSentence.metaTags.length
    s"""<div class="badge $metaCount">$index</div>"""
  }

  def markupSentence(codedSentence:Coded): String = markup(codedSentence.sentence,codedSentence.phrases)

  private def markup(text:String,phrases:Vector[String]):String = {
    if(phrases.isEmpty) { text }
    else {
      val phraseParts = phrases.head.split('[')
      val phraseText = phraseParts(0)
      val phraseType = phraseParts(1).split(',')(0)
      val newText = if(phraseType!="generalPronounVerb") {
        text.replaceAll(phraseText,s"""<span class="badge phrase-badge $phraseType" >$phraseText</span>""")
      } else { text }
      if (phrases.tail.isEmpty) newText else markup(newText,phrases.tail)
    }
  }

  def markupMetaTags(codedSentence:Coded):String = {
    val metaTags = codedSentence.metaTags
    metaTags.map { mt =>
      s"""<div class="badge meta-badge $mt">$mt</div>"""
    }.mkString
  }
}
