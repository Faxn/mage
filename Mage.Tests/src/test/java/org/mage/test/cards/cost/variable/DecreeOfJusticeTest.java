/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mage.test.cards.cost.variable;

import mage.constants.PhaseStep;
import mage.constants.Zone;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestPlayerBase;

/**
 *
 * @author campb
 */
public class DecreeOfJusticeTest extends CardTestPlayerBase {
    
    @Test
    public void TestCycling(){
        addCard(Zone.LIBRARY, playerA, "Plains", 4);
        addCard(Zone.BATTLEFIELD, playerA, "Plains", 5);
        addCard(Zone.HAND, playerA, "Decree of Justice");

        //castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Decree of Justice");
        activateAbility(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Cycling");
        activateAbility(1, PhaseStep.PRECOMBAT_MAIN, playerA, "DecreeOfJusticeCycleEffect");
        

        setStopAt(1, PhaseStep.BEGIN_COMBAT);
        execute();

        //assertPermanentCount(playerA, "Mikaeus, the Lunarch", 1);
        assertGraveyardCount(playerA, "Decree of Justice", 1);
        assertHandCount(playerA, 1);
    }
    
}
