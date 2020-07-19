package java;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class WiningBetService {

    public String winningBet(List<Optional<Bet>> players, List<Optional<Bet>> viewers){
        return players.stream().filter(Optional::isPresent)
                .map(Optional::get).max(Comparator.comparingInt(Bet::getAmount))
                .or(() -> viewers.stream().filter(Optional::isPresent)
                        .map(Optional::get).max(Comparator.comparingInt(Bet::getAmount)))
                .or(() -> {
                    System.out.println("No participants");
                    return Optional.of(new Bet());
                }).get().getName();
    }

}
