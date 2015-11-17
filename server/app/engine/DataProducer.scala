package engine

import monifu.reactive.{Subscriber, Observable}
import monifu.concurrent.FutureUtils.delayedResult
import monifu.util.Random
import shared.models.{Chat, Signal, Friend}
import scala.concurrent.duration._

final class DataProducer(interval: FiniteDuration, seed: Long)
  extends Observable[Signal] {

  def onSubscribe(subscriber: Subscriber[Signal]): Unit =
    chats.onSubscribe(subscriber)


  private val chatList = Vector(
    Chat(0, "Ben Sparrow", "You on your way?", "https://pbs.twimg.com/profile_images/514549811765211136/9SgAuHeY.png"),
    Chat(1, "Max Lynx", "Hey, it\'s me", "https://avatars3.githubusercontent.com/u/11214?v=3&s=460"),
    Chat(2, "Andrew Jostlin", "Did you get the ice cream?", "https://pbs.twimg.com/profile_images/609810148769427456/dhzhuaNA.jpg"),
    Chat(3, "Adam Bradleyson", "I should buy a boat", "https://pbs.twimg.com/profile_images/479090794058379264/84TKj_qa.jpeg"),
    Chat(4, "Perry Governor", "Look at my mukluks!", "https://pbs.twimg.com/profile_images/467390551830970368/80rkMI5v.jpeg")
  )

  private val chats = Observable.create[Signal] { subscriber =>
    import subscriber.{scheduler => s}

    val random = Observable
      .fromStateAction(Random.intInRange(-20, 20))(s.currentTimeMillis() + seed)
      .flatMap { x => delayedResult(interval)(x) }

    val generator = random.scan(Signal(chatList(0),s.currentTimeMillis())) {
      case (Signal(Chat(value,_,_,_), _), rnd) =>
        val next = math.abs(value + rnd) % chatList.size
        Signal(chatList(next), s.currentTimeMillis())
    }

    generator.drop(1)
      .onSubscribe(subscriber)
  }

  private case class State(x: Int, y: Int, ts: Long)
}
