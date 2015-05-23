package kumamidori.snipeets.order

import java.text.SimpleDateFormat
import scalaz.\/-
import scalaz.-\/
import org.scalatest.FunSpec
import kumamidori.snippets.order._

class OrderSpecificationSpec extends FunSpec {

  describe("A OrderSpecification isReadyForFulfilment") {
    it("should return true case valid") {

      val customer = new Customer("001", "nana", 7)
      val item = new ItemA("P001", Some("ぼのぼの"), 1)
      val lineItems = List(new LineItem(item, 2))
      val order = new Order("A0001", new SimpleDateFormat("yyyy-MM-dd").parse("2015-10-01"), customer, lineItems)

      assert(\/-(true) == OrderSpecification.isReadyForFulfilment(order))
    }
    it("should return error case no Item") {

      val customer = new Customer("001", "nana", 7)
      val order = new Order("A0001", new SimpleDateFormat("yyyy-MM-dd").parse("2015-10-01"), customer, List())

      assert(-\/("Validation failed for order Order(A0001,Thu Oct 01 00:00:00 JST 2015,Customer(001,nana,7),List())") == OrderSpecification.isReadyForFulfilment(order))
    }
    it("should return error case blacklist customer") {

      val blackId = BlackListSpecification.blackList.head
      val customer = new Customer(blackId, "nana", 7)
      val item = new ItemA("P001", Some("ぼのぼの"), 1)
      val lineItems = List(new LineItem(item, 2))
      val order = new Order("A0001", new SimpleDateFormat("yyyy-MM-dd").parse("2015-10-01"), customer, lineItems)

      assert(-\/("Validation failed for blacklist cusId 002") == OrderSpecification.isReadyForFulfilment(order))
    }
  }
}
