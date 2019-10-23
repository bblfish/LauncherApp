package launcher

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import org.scalajs.dom
import dom.{document, window}
import org.scalajs.dom.html
import org.scalajs.dom.raw.Event

@JSExportTopLevel("LauncherApp")
object LauncherApp {
  var i = 0
  def main(args: Array[String]): Unit = {
    start(args(0))
  }

  @JSExport
  def start(msg: String): Unit = {
    appendPar(document.body,msg)
    window.addEventListener("message",parseMessage _)
  }

  def remoteAppURL(): String = {
     document.getElementById("RemoteAppUrl").getAttribute("value")
  }

  @JSExport
  def reloadIFrame(): Unit = {
      document.getElementById("inlineFrameExample").setAttribute("src",remoteAppURL())
  }

  @JSExport
  def openRemoteApp(): Unit = {
     window.open(remoteAppURL(),"RemoteApp")
  }

  def parseMessage(event: dom.MessageEvent): Unit = {
    appendPar(document.body,s"received data $i = ${event.data} " )
    i = i+1
  }
   

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }
}
