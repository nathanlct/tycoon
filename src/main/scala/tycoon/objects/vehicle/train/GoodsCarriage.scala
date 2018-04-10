package tycoon.objects.vehicle.train

import scala.collection.mutable.ListBuffer

import tycoon.game.{GridLocation, Player}
import tycoon.objects.good._
import tycoon.objects.railway._
import tycoon.objects.structure._
import tycoon.objects.vehicle.Container
import tycoon.ui.Renderable
import tycoon.ui.Tile

import scalafx.beans.property.IntegerProperty


case class GoodsCarriage(_id: Int, initialTown: Structure, _owner: Player) extends Carriage(_id, initialTown, _owner) with Container {
  tile = Tile.goodsWagonR
  val tiles = Array(Tile.goodsWagonT, Tile.goodsWagonR, Tile.goodsWagonB, Tile.goodsWagonL)
  val maxSpace : Double = 100
  var remainingSpace : Double = maxSpace
  val merchandises = new ListBuffer[Merchandise]

  val mManager = new MerchandisesManager

//   def embark(structure: Structure, stops: ListBuffer[Structure]) = {
//     println("merchandises: "+merchandises)
//     println("GoodsCarriage > embark from "+structure)
//     println("stops: "+stops)
//     stops.foreach(s => mManager.addStop(s))
//     //println("planning:"+ mManager.flattenedRequests)
//     structure match {
//       case t: Town => () // must be modified to include requests from towns on stops
//       case f: Facility => {
//         // determine pertinent products to embark
//         var usefulIndices = new ListBuffer[Int]
//         for (i <- 0 to mManager.requests.length - 1) {
//           for (p <- mManager.requests(i)._2) {
//             if (mManager.notVisited(i)) usefulIndices += f.stock.getIndex(p)
//           }
//         }
//         usefulIndices = usefulIndices.filter(_ != -1)
//         println("usefulIndices: "+usefulIndices)
//
//         // embark selected products
//         for (i <- usefulIndices) {
//           val product = f.stock.productsTypes(i)
//           val quantity = f.stock.stocks(i).min((remainingSpace/product.size).toInt)
//           f.stock.giveMerchandisesWIndex(i, product, merchandises, quantity, m => (!m.kind.liquid || m.packed))
//           remainingSpace -= product.size*quantity
//         }
//       }
//     }
//     println("merchandises: "+merchandises)
//     println("quit structure"+structure)
//   }
//
//   def debark(structure: Structure) = {
//     println("merchandises: "+merchandises)
//     println("GoodsCarriage > debark")
//     val i = mManager.stops.indexOf(structure)    // for (m <- mManager.distribution(i)) structure.stock.getMerchandise(m)
//     // mManager.removeStop(structure)
//     mManager.distribute(merchandises, structure)
//     // update remainingSpace
//     remainingSpace = maxSpace
//     merchandises.foreach(m => remainingSpace -= m.quantity*m.kind.size)
//     // for (m <- mManager.distribution(i)) structure.stock.getMerchandise(m)
//     // mManager.removeStop(structure)
//     structure match {
//       case t: Town => ()
//       case _ => ()
//     }
//     mManager.notVisited(i) = false
//   }
// }

// TODO: trouver comment afficher les prix
// var i = 0 // number of satisfied requests
// for (merch <- merchandises) {
//   var i = 0
//   while (i < t.requests.length) {
//     if (merch.kind.label == t.requests(i).label) {
//       val soldQuantity = merch.quantity.min(t.needs(i))
//       // add the product to the town
//       var index = t.products.indexOf(merch.kind)
//       // if this product doesn't exist yet in the town we have to add it
//       if (index == -1) {
//         t.products += merch.kind
//         index = t.datedProducts.length
//         t.datedProducts += new ListBuffer[Merchandise]
//         t.stocksInt += IntegerProperty(0)
//       }
//       // if the merchandise is entirely sold
//       if (soldQuantity == merch.quantity) {
//         t.datedProducts(index) += merch
//         merchandises -= merch
//       }
//       else {
//         t.datedProducts(index) += new Merchandise(merch.kind, soldQuantity, merch.productionDate)
//         merch.quantity -= soldQuantity
//       }
//       t.stocksInt(index).set(t.stocks(index) + soldQuantity)
//       remainingSpace += soldQuantity*merch.kind.size
//       weight -= soldQuantity*merch.kind.weight
//       // be paid
//       owner.earn(t.prices(i)*soldQuantity)
//       // satisfy the request
//       if (!t.satisfyRequest(merch.kind, i, soldQuantity)) i += 1
//     }
//     else i += 1
//   }
// }
// }
// case f: Facility => {
//   for (i <- 0 to f.products.length-1) {
//     for (m <- merchandises) {
//       if (f.areSameGoods(m.kind, f.products(i))) {
//         // take off the merchandise from the carriage
//         merchandises -= m
//         remainingSpace += m.quantity*m.kind.size
//         weight -= m.quantity*m.kind.weight
//         // add the merchandise
//         f.datedProducts(i) += m
//         // // add the product
//         // f.stocksInt(i).set(f.stocks(i) + merch.quantity)
//         // // empty the carriage
//         // merchandises -= merch
//         // remainingSpace += merch.quantity*merch.kind.size
//         // weight -= merch.quantity*merch.kind.weight
//         // println(merch.kind, remainingSpace, f.stocks(i))
//       }
//     }
//   }
//   f.updateStocks()
}


object GoodsCarriage {
  val Price: Int = 30
}



// old embark

// var flag = true
// var i = 0
// var productsIndices = new ListBuffer[Int]
// println("\nbeginMerchandises: "+merchandises)
//
// // determine embarkable products
// while (flag && i < f.products.length) {
//   println("debug loop 1")
//   println(">remainingSpace: "+remainingSpace)
//   println(">stocks: "+f.stocks(i))
//   var j = 0
//   while (j < requests.length && !f.areSameGoods(requests(j), f.products(i))) j += 1
//   if (j < requests.length) productsIndices += j
//   flag = differentContentsAuthorized
//   i += 1
// }
// println(">products: "+productsIndices)
//
// // embark embarkable products
// for (i <- productsIndices) {
//   flag = true
//   while (flag && f.datedProducts(i).length > 0) {
//     println("\n>> debug loop 2")
//     println(">> remainingSpace: "+remainingSpace)
//     println(">> stocks: "+f.stocks(i))
//     val m = f.datedProducts(i)(0)
//     println(">>merch quantity: "+m.quantity)
//     // if the merchandise can be totally embarked
//     if (m.quantity*m.kind.size <= remainingSpace) {
//       // delete the merchandise from the facility
//       f.datedProducts(i) -= m
//       // add the merchandise to the carriage
//       merchandises += m
//       remainingSpace -= m.quantity*m.kind.size
//       weight += m.quantity*m.kind.weight
//     }
//
//     else {
//       // sometimes it is necessary to divide merchandises
//       var quantity = (remainingSpace/m.kind.size).toInt
//       if (quantity > 0) {
//         f.datedProducts(i) -= m
//         f.datedProducts(i) += new Merchandise(m.kind, m.quantity-quantity, m.productionDate)
//         merchandises += new Merchandise(m.kind, quantity, m.productionDate)
//         remainingSpace -= quantity*m.kind.size
//         weight += quantity*m.kind.weight
//       }
//       println("endRemainingSpace: "+remainingSpace)
//       println("endStocks: "+f.stocks(i))
//       flag = false
//     }
//   }
// }

// for (i <- 0 to f.products.length - 1) {
//   val product = f.products(i)
//   var requested = 0
//   while (requested < requests.length && !f.areSameGoods(requests(requested), product)) requested += 1
//   if (requested < requests.length) {
//     println(product, remainingSpace, f.stocks(i))
//     var flag = true
//     while (flag && f.datedProducts(i).length > 0) {
//       val m = f.datedProducts(i)(0)
//       if (m.quantity*m.kind.size <= remainingSpace) {
//         // delete the merchandise from the facility
//         f.datedProducts(i) -= m
//         // add the merchandise to the carriage
//         merchandises += m
//         remainingSpace -= m.quantity*m.kind.size
//         weight += m.quantity*m.kind.weight
//       }
//       else {
//         // sometimes it is necessary to divide merchandises
//         var quantity = (remainingSpace/m.kind.size).toInt
//         if (quantity > 0) {
//           f.datedProducts(i) -= m
//           f.datedProducts(i) += new Merchandise(m.kind, m.quantity-quantity, m.productionDate)
//           merchandises += new Merchandise(m.kind, quantity, m.productionDate)
//           remainingSpace -= quantity*m.kind.size
//           weight += quantity*m.kind.weight
//         }
//         flag = false
//       }
//     }
//     // if (quantity > 0) {
//     //   // remove the product from the facility
//     //   f.stocksInt(i).set(f.stocks(i) - quantity)
//     //   // add the product in the carriage
//     //   merchandises += new Merchandise(product, quantity, f.townManager.getTime())
//     //   println("GoodsCarriage > just embarked" + quantity)
//     //   remainingSpace -= quantity*product.size
//     //   weight += quantity*product.weight
//     //   println(product, remainingSpace, f.stocks(i))
//     // }
//   }
// }
//       f.updateStocks()
//       println("endMerchandises: "+merchandises)
//     }
//   }
// }
