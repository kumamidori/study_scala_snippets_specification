package kumamidori.snippets.order

// 基底の抽象
sealed trait Item {
  def itemCode: String
}

case class ItemA(itemCode: String, desc: Option[String], minPurchaseUnit: Int) extends Item
