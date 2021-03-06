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
package mage.cards.c;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.continuous.BecomesCreatureSourceEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Zone;
import mage.game.permanent.token.TokenImpl;

/**
 *
 * @author LoneFox
 */
public class ChimericSphere extends CardImpl {

    public ChimericSphere(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{3}");

        // {2}: Until end of turn, Chimeric Sphere becomes a 2/1 Construct artifact creature with flying.
        this.addAbility(new SimpleActivatedAbility(Zone.BATTLEFIELD, new BecomesCreatureSourceEffect(new ChimericSphereFlyingToken(),
            "", Duration.EndOfTurn), new ManaCostsImpl("{2}")));
        // {2}: Until end of turn, Chimeric Sphere becomes a 3/2 Construct artifact creature without flying.
        this.addAbility(new SimpleActivatedAbility(Zone.BATTLEFIELD, new BecomesCreatureSourceEffect(new ChimericSphereNotFlyingToken(),
            "", Duration.EndOfTurn), new ManaCostsImpl("{2}")));
    }

    public ChimericSphere(final ChimericSphere card) {
        super(card);
    }

    @Override
    public ChimericSphere copy() {
        return new ChimericSphere(this);
    }
}

class ChimericSphereFlyingToken extends TokenImpl {

    public ChimericSphereFlyingToken() {
        super("Chimeric Sphere", "2/1 Construct artifact creature with flying");
        cardType.add(CardType.ARTIFACT);
        cardType.add(CardType.CREATURE);
        power = new MageInt(2);
        toughness = new MageInt(1);

        this.addAbility(FlyingAbility.getInstance());
    }
    public ChimericSphereFlyingToken(final ChimericSphereFlyingToken token) {
        super(token);
    }

    public ChimericSphereFlyingToken copy() {
        return new ChimericSphereFlyingToken(this);
    }
}

class ChimericSphereNotFlyingToken extends TokenImpl {

    public ChimericSphereNotFlyingToken() {
        super("Chimeric Sphere", "3/2 Construct artifact creature without flying");
        cardType.add(CardType.ARTIFACT);
        cardType.add(CardType.CREATURE);
        power = new MageInt(3);
        toughness = new MageInt(2);
    }
    public ChimericSphereNotFlyingToken(final ChimericSphereNotFlyingToken token) {
        super(token);
    }

    public ChimericSphereNotFlyingToken copy() {
        return new ChimericSphereNotFlyingToken(this);
    }
}

