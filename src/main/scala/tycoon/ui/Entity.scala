package tycoon.ui

import tycoon.game.GridLocation
import tycoon.game.GridRectangle

import scalafx.geometry.Rectangle2D
import scalafx.scene.image.ImageView
import scala.collection.mutable.ListBuffer
import scalafx.beans.property.StringProperty

trait Entity {
  private var _tile: Tile = Tile.default
  private var _gridRect: GridRectangle = new GridRectangle(new GridLocation(0, 0), 1, 1)

  private val _printData: ListBuffer[(String, StringProperty)] = ListBuffer()
  def printData = _printData

  def getView: ImageView = tile.getView

  def gridIntersects(other: Entity): Boolean = gridRect.intersects(other.gridRect)
  def gridContains(pos: GridLocation): Boolean = gridRect.contains(pos)

  def tile: Tile = _tile
  def tile_=(new_tile: Tile) = _tile = new_tile

  def gridRect: GridRectangle = _gridRect

  // size in pixels
  def width: Int = tile.width
  def height: Int = tile.height

  def getPos: GridLocation = gridRect.pos

  // set pos on grid (in cases)
  def setPos(pos: GridLocation) {
    _gridRect = new GridRectangle(pos, tile.width / Tile.square_width, tile.height / Tile.square_height)
  }
  // set pos on scene (in pixels)
  def setLayout(x: Double, y: Double) {
    tile.setLayout(x, y)
  }

  def visible: Boolean = tile.visible
  def visible_= (new_visible: Boolean) = tile.visible = new_visible
  def toggleVisible: Unit = visible = !visible

  def inScene: Boolean = tile.inScene
  def inScene_= (new_inScene: Boolean) = tile.inScene = new_inScene
}



/*

trait Renderable extends Printable {
  protected val tile: Tile
  private var _gridLoc: GridLocation = new GridLocation(0, 0)

  def getView : ImageView = tile.getView
  def viewport = tile.getView.viewport.get()


  private var _gridRect : Rectangle2D = new Rectangle2D(0, 0, 0, 0)

  def gridIntersects (other: Renderable) : Boolean = {
    (((gridRect.minX <= other.gridRect.minX && other.gridRect.minX <= gridRect.maxX)
    || (gridRect.minX <= other.gridRect.maxX && other.gridRect.maxX <= gridRect.maxX))
    && ((gridRect.minY <= other.gridRect.minY && other.gridRect.minY <= gridRect.maxY)
    || (gridRect.minY <= other.gridRect.maxY && other.gridRect.maxY <= gridRect.maxY)))
    //gridRect.intersects(other.gridRect)
  }
  def gridContains (pos: GridLocation) : Boolean = gridRect.contains(pos.col, pos.row)

  def setLayout(x: Double, y: Double) = tile.setLayout(x, y)

  def gridLoc: GridLocation = _gridLoc
  def gridLoc_= (new_loc: GridLocation) = {
    _gridLoc = new_loc
    // can cause problem if gridLoc_= is called before tile is defined
    _gridRect = new Rectangle2D(new_loc.col, new_loc.row, tile.width / Tile.square_width - 1, tile.height / Tile.square_height - 1)
  }

  def gridRect: Rectangle2D = _gridRect

  def width: Int = tile.width
  def height: Int = tile.height

  def displayed: Boolean = tile.displayed
  def displayed_= (new_displayed: Boolean) = tile.displayed = new_displayed
}
*/
