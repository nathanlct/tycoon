package tycoon.ui

import tycoon.GridLocation
import tycoon.objects.Sprite

import scalafx.geometry.Rectangle2D
import scalafx.scene.image.ImageView

trait Renderable {
  protected val tile: Tile
  private var _gridLoc: GridLocation = new GridLocation(0, 0)

  def getView : ImageView = tile.getView
  def viewport = tile.getView.viewport.get()

  private var _gridRect : Rectangle2D = new Rectangle2D(0, 0, 0, 0)

  def gridIntersects (other: Renderable) : Boolean = gridRect.intersects(other.gridRect)

  def setLayout(x: Double, y: Double) = tile.setLayout(x, y)

  def gridLoc: GridLocation = _gridLoc
  def gridLoc_= (new_loc: GridLocation) = {
    _gridLoc = new_loc
    // can cause problem if gridLoc_= is called before tile is defined
    _gridRect = new Rectangle2D(new_loc.column, new_loc.row, tile.width / Sprite.tile_width, tile.height / Sprite.tile_height)
  }

  def gridRect: Rectangle2D = _gridRect

  def width: Int = tile.width
  def height: Int = tile.height

  def displayed: Boolean = tile.displayed
  def displayed_= (new_displayed: Boolean) = tile.displayed = new_displayed
}