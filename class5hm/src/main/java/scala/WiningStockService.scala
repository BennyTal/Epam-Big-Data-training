package scala

import scala.util.Try

object WiningStockService {

  def getWinnerName(players:List[Option[Bet]], viewers:List[Option[Bet]]): String = {

    //Case players empty or contains only Option.empty -> viewers list will be flatten, else -> empty name will be returned.
    Try(players.flatten.maxBy(_.amount)).toOption
        .orElse(Try(viewers.flatten.maxBy(_.amount)).toOption
          .orElse(Option(Bet())))
      .get.name
  }

}
