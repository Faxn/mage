/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.p;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.condition.common.KickedCondition;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.keyword.KickerAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.game.Game;
import mage.game.stack.Spell;
import mage.players.Player;
import mage.target.TargetSpell;

/**
 *
 * @author LevelX2
 */
public class Prohibit extends CardImpl {

    public Prohibit(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{1}{U}");

        // Kicker {2}
        this.addAbility(new KickerAbility("{2}"));

        // Counter target spell if its converted mana cost is 2 or less. If Prohibit was kicked, counter that spell if its converted mana cost is 4 or less instead.
        this.getSpellAbility().addEffect(new ProhibitEffect());
        this.getSpellAbility().addTarget(new TargetSpell());
    }

    public Prohibit(final Prohibit card) {
        super(card);
    }

    @Override
    public Prohibit copy() {
        return new Prohibit(this);
    }
}

class ProhibitEffect extends OneShotEffect {

    ProhibitEffect() {
        super(Outcome.DestroyPermanent);
        this.staticText = "Counter target spell if its converted mana cost is 2 or less. if this spell was kicked, counter that spell if its converted mana cost is 4 or less instead.";
    }

    ProhibitEffect(final ProhibitEffect effect) {
        super(effect);
    }

    @Override
    public ProhibitEffect copy() {
        return new ProhibitEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            Spell targetSpell = game.getSpell(this.getTargetPointer().getFirst(game, source));
            if (targetSpell != null) {
                int cmc = targetSpell.getConvertedManaCost();
                if (cmc <= 2 || (KickedCondition.instance.apply(game, source) && cmc <= 4)) {
                    targetSpell.counter(source.getSourceId(), game);
                }
            }
            return true;
        }
        return false;
    }
}
