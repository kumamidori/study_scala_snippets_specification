package kumamidori.snippets.order

import java.util.Date

// 注文のスケルトン
case class Order(orderNo: String, orderDate: Date, customer: Customer, lineItems: List[LineItem])
