package kumamidori.snippets.order

object BlackListSpecification {

  val blackList = List("002")

  def isSatisfiedBy(customer: Customer): Boolean = {
    println(customer.cusId)
    println(blackList.contains(customer.cusId))
    blackList.contains(customer.cusId)
  }
}
