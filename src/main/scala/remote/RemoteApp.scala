package remote

import launcher.LauncherApp.appendPar
import org.scalajs.dom
import org.scalajs.dom.{document,window}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.util.Try

@JSExportTopLevel("RemoteApp")
object RemoteApp {

  def main(args: Array[String]): Unit = {
    start(args(0))
  }

  @JSExport
  def start(msg: String): Unit = {
    sendMessageToParent(s"Hi! $msg. My frame name is ${window.name} and width ${window.innerWidth}.")
    appendPar(document.body,msg)
  }

  def sendMessageToParent(txt: String): Unit = {
      val source =  Option(window.opener) orElse Option(window.parent)
      source.map(_.postMessage(txt,"*")).getOrElse(println("no window"))
  }
  

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }
}
