package kumamidori.snippets

import java.text.SimpleDateFormat
import kumamidori.snippets.order._

object Main extends App {
  val customer = new Customer("001", "nana", 7)
  val item = new ItemA("P001", Some("ぼのぼの"), 1)
  val lineItems = List(new LineItem(item, 2))
  val order = new Order("A0001", new SimpleDateFormat("yyyy-MM-dd").parse("2015-10-31"), customer, lineItems)
  println(OrderSpecification.isReadyForFulfilment(order))
}
