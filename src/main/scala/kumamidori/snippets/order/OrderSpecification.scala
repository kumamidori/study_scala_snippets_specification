package kumamidori.snippets.order

import scalaz.\/._

object OrderSpecification {

  def isReadyForFulfilment(order: Order) = {

    def validate = ReaderTStatus[Order, Boolean] {order =>
      if (order.lineItems.isEmpty) left(s"Validation failed for order $order")
      else right(true)
    }
    def approve = ReaderTStatus[Order, Boolean] {order =>
        println("approved")
        right(true)
    }

    def checkCustomerStatus(customer: Customer) = ReaderTStatus[Order, Boolean] {order =>
      if (BlackListSpecification.isSatisfiedBy(order.customer)) {
        left(s"Validation failed for blacklist cusId " + order.customer.cusId)
      } else {
        right(true)
      }
    }

    def checkInventory = ReaderTStatus[Order, Boolean] {order =>
      println("inventory checked")
      right(true)
    }
    val s = for {

      _ <- validate
      _ <- approve
      _ <- checkCustomerStatus(order.customer)
      c <- checkInventory

    } yield c
    s(order)
  }
}
