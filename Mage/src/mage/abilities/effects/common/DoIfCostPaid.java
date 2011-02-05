package mage.abilities.effects.common;

import mage.Constants;
import mage.abilities.Ability;
import mage.abilities.costs.Cost;
import mage.abilities.effects.OneShotEffect;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;

public class DoIfCostPaid extends OneShotEffect<DoIfCostPaid> {
    private OneShotEffect executingEffect;
    private Cost cost;

    public DoIfCostPaid(OneShotEffect effect, Cost cost) {
        super(Constants.Outcome.Benefit);
        this.executingEffect = effect;
        this.cost = cost;
    }

    public DoIfCostPaid(final DoIfCostPaid effect) {
        super(effect);
        this.executingEffect = (OneShotEffect) effect.executingEffect.copy();
        this.cost = effect.cost.copy();
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
		if (player != null) {
			if (player.chooseUse(executingEffect.getOutcome(), "Pay " + cost.getText() + " and " + executingEffect.getText(source), game)) {
				cost.clearPaid();
				if (cost.pay(game, source.getId(), source.getControllerId(), false)) {
                    return executingEffect.apply(game, source);
                }
			}
			return true;
		}
		return false;
    }

    @Override
    public String getText(Ability source) {
        return "you may pay " + cost.getText() + ". If you do, " + executingEffect.getText(source);
    }

    @Override
    public DoIfCostPaid copy() {
        return new DoIfCostPaid(this);
    }
}

