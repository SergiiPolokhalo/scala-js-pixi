package pixi

import basics._
import demos._
import filters._
import org.scalajs.dom.html
import pixigraphics.Application

trait PIXIExample {

  val name: String

  val pixiUrl: String

  def githubUrl(pkg: String): String = s"https://raw.githubusercontent.com/sherpal/scala-js-pixi/master/pixi-examples/scala-project/src/main/scala/$pkg/$toString.scala"

  private var pixiApp: Option[Application] = None

  private def stop(): Unit = {
    pixiApp match {
      case Some(app) =>
        app.destroy(removeView = true, stageOptions = true)
      case None =>
    }
    pixiApp = None
  }

  protected def newApplication(): Application

  def run(pkg: String): Unit = {
    PIXIExample.stopAll()
    pixiApp = Some(newApplication())
    ExampleSelector.changeCanvas(this)
    ExampleSelector.loadAndDisplayCode(this, pkg)
  }

  def canvas: html.Canvas = pixiApp.get.view

}

object PIXIExample {

  val allExamples: Map[String, List[PIXIExample]] = Map(
    "Basics" -> List(
      Basics,
      Container,
      ContainerPivot,
      SpriteSheetAnimation,
      Click,
      TilingSprite,
      Text,
      Graphics,
      Video,
      RenderTexture,
      CustomFilter
    ),
    "Demos" -> List(
      AnimatedSprite,
      AnimationSpeed,
      Interactivity,
      TransparentBackground,
      Dragging,
      DemoRenderTexture,
      DemoGraphics,
      Masking,
      MaskingRenderTexture,
      BlendModes,
      Tinting,
      ParticleContainer,
      Strip,
      TextureSwap,
      TextureRotate,
      AlphaMask,
      MouseTrail,
      CustomMouseIcon,
      StarWarp
    ),
    "Filters" -> List(
      Filter,
      MouseBlending,
      Blur,
      Outline
    )
  )

  def stopAll(): Unit =
    allExamples.values.flatten.foreach(_.stop())

}
