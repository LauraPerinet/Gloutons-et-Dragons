package com.mygdx.game;


import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PERINETM
 */
public class FabricatorActions extends Actions{
    public FabricatorActions (HashMap<CharactersFullGUI, Action> actions) {
	for( CharactersFullGUI actor : actions.keySet()){
            actor.addAction(actions.get(actor));
        }
    }
}
