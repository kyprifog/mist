package mist.api.encoding
import mist.api.data.JsData
import mist.api.{Extraction, MObj}
import shadedshapeless.Lazy

object generic {

  def extractor[A](implicit ext: Lazy[ObjectExtractor[A]]): RootExtractor[A] = new RootExtractor[A] {
    override def apply(js: JsData): Extraction[A] = ext.value.apply(js)
    override def `type`: MObj = ext.value.`type`
  }

  def encoder[A](implicit enc: Lazy[ObjectEncoder[A]]): JsEncoder[A] = JsEncoder(enc.value.apply)
}
