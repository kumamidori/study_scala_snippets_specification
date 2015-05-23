package kumamidori.snippets.order

import scalaz._

object ReaderTStatus extends KleisliInstances with KleisliFunctions {
  // どの関数からも結果として返す型
  type ValidationStatus[S] = \/[String, S]
  // 結果同士を結びつける型
  type ReaderTStatus[A, S] = ReaderT[ValidationStatus, A, S]

  def apply[A, S](f: A => ValidationStatus[S]): ReaderTStatus[A, S] = kleisli(f)
}
